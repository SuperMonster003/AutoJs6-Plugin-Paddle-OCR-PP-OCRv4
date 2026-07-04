#!/usr/bin/env python3

import argparse
import shutil
import subprocess
import tarfile
import urllib.request
from pathlib import Path


PADDLE3_BOS_BASE = (
    "https://paddle-model-ecology.bj.bcebos.com/"
    "paddlex/official_inference_model/paddle3.0.0"
)

PADDLEOCR_RAW_BASE = "https://raw.githubusercontent.com/PaddlePaddle/PaddleOCR/main"

PROFILES = {
    "mobile": {
        "det": "PP-OCRv4_mobile_det",
        "rec": "PP-OCRv4_mobile_rec",
        "det_asset": "ppocrv4-mobile-det",
        "rec_asset": "ppocrv4-mobile",
        "det_source_set": "sharedMobileDet",
        "rec_source_set": "mobile",
        "dict_url": f"{PADDLEOCR_RAW_BASE}/ppocr/utils/ppocr_keys_v1.txt",
    },
    "server": {
        "det": "PP-OCRv4_server_det",
        "rec": "PP-OCRv4_server_rec",
        "det_asset": "ppocrv4-server",
        "rec_asset": "ppocrv4-server",
        "det_source_set": "server",
        "rec_source_set": "server",
        "dict_url": f"{PADDLEOCR_RAW_BASE}/ppocr/utils/ppocr_keys_v1.txt",
    },
    "mobile-en": {
        "det": "PP-OCRv4_mobile_det",
        "rec": "en_PP-OCRv4_mobile_rec",
        "det_asset": "ppocrv4-mobile-det",
        "rec_asset": "ppocrv4-mobile-en",
        "det_source_set": "sharedMobileDet",
        "rec_source_set": "mobileEn",
        "dict_url": f"{PADDLEOCR_RAW_BASE}/ppocr/utils/en_dict.txt",
    },
}

SOURCE_SET_ASSET_ROOTS = {
    "sharedMobileDet": Path("app/src/sharedMobileDet/assets"),
    "mobile": Path("app/src/mobile/assets"),
    "server": Path("app/src/server/assets"),
    "mobileEn": Path("app/src/mobileEn/assets"),
}


def download(url: str, target: Path) -> None:
    target.parent.mkdir(parents=True, exist_ok=True)
    if target.exists() and target.stat().st_size > 0:
        print(f"skip: {target}")
        return

    print(f"download: {url}")
    urllib.request.urlretrieve(url, target)


def safe_extract_tar(tar_path: Path, out_dir: Path) -> Path:
    if out_dir.exists():
        shutil.rmtree(out_dir)
    out_dir.mkdir(parents=True, exist_ok=True)

    out_root = out_dir.resolve()
    with tarfile.open(tar_path) as tf:
        for member in tf.getmembers():
            target = (out_dir / member.name).resolve()
            if not str(target).startswith(str(out_root)):
                raise RuntimeError(f"unsafe tar member: {member.name}")
        try:
            tf.extractall(out_dir, filter="data")
        except TypeError:
            tf.extractall(out_dir)

    dirs = [p for p in out_dir.iterdir() if p.is_dir()]
    if len(dirs) == 1:
        return dirs[0]
    return out_dir


def find_first(root: Path, names: tuple[str, ...]) -> Path | None:
    for name in names:
        found = next(root.rglob(name), None)
        if found is not None:
            return found
    return None


def find_model_dir(root: Path) -> Path:
    onnx = find_first(root, ("inference.onnx", "model.onnx"))
    if onnx is not None:
        return onnx.parent

    paddle_model = find_first(root, ("inference.pdmodel", "inference.json", "__model__"))
    if paddle_model is not None:
        return paddle_model.parent

    raise FileNotFoundError(f"model files not found under {root}")


def find_local_model(source_dir: Path | None, model_name: str) -> Path | None:
    if source_dir is None:
        return None
    if not source_dir.exists():
        raise FileNotFoundError(f"source dir does not exist: {source_dir}")

    exact_names = (
        f"{model_name}_onnx_infer.tar",
        f"{model_name}_infer.tar",
        f"{model_name}_onnx_infer",
        f"{model_name}_infer",
        model_name,
    )

    for name in exact_names:
        candidate = source_dir / name
        if candidate.exists():
            return candidate

    for name in exact_names:
        found = next(source_dir.rglob(name), None)
        if found is not None:
            return found

    marker = model_name.lower()
    for file_name in ("inference.onnx", "model.onnx", "inference.pdmodel", "inference.json", "__model__"):
        for found in source_dir.rglob(file_name):
            if marker in str(found.parent).lower():
                return found.parent

    return None


def prepare_model_source(
    model_name: str,
    cache_dir: Path,
    source_dir: Path | None,
) -> Path:
    local = find_local_model(source_dir, model_name)
    if local is not None:
        print(f"use local model: {local}")
        if local.is_file() and local.suffix == ".tar":
            return find_model_dir(safe_extract_tar(local, cache_dir / local.stem))
        if local.is_dir():
            return find_model_dir(local)
        raise RuntimeError(f"unsupported local model source: {local}")

    tar_path = cache_dir / f"{model_name}_infer.tar"
    download(f"{PADDLE3_BOS_BASE}/{model_name}_infer.tar", tar_path)
    return find_model_dir(safe_extract_tar(tar_path, cache_dir / f"{model_name}_paddle"))


def copy_onnx_model(onnx_dir: Path, dst_dir: Path) -> bool:
    onnx = find_first(onnx_dir, ("inference.onnx", "model.onnx"))
    if onnx is None:
        return False

    dst_dir.mkdir(parents=True, exist_ok=True)
    shutil.copy2(onnx, dst_dir / "inference.onnx")
    return True


def run_command(cmd: list[str]) -> None:
    print("run:", " ".join(cmd))
    subprocess.check_call(cmd)


def command_exists(name: str) -> bool:
    return shutil.which(name) is not None


def convert_paddle_to_onnx(paddle_model_dir: Path, onnx_output_dir: Path) -> Path:
    model_file = find_first(paddle_model_dir, ("inference.pdmodel", "inference.json", "__model__"))
    params_file = find_first(paddle_model_dir, ("inference.pdiparams",))
    if model_file is None:
        raise FileNotFoundError(f"Paddle model file not found under {paddle_model_dir}")

    errors: list[str] = []

    if command_exists("paddle2onnx"):
        if onnx_output_dir.exists():
            shutil.rmtree(onnx_output_dir)
        onnx_output_dir.mkdir(parents=True, exist_ok=True)
        cmd = [
            "paddle2onnx",
            "--model_dir", str(model_file.parent),
            "--model_filename", model_file.name,
            "--save_file", str(onnx_output_dir / "inference.onnx"),
            "--opset_version", "7",
        ]
        if params_file is not None:
            cmd += ["--params_filename", params_file.name]
        try:
            run_command(cmd)
            if find_first(onnx_output_dir, ("inference.onnx", "model.onnx")) is not None:
                return onnx_output_dir
        except Exception as exc:
            errors.append(f"paddle2onnx failed: {exc}")

    if command_exists("paddlex"):
        if onnx_output_dir.exists():
            shutil.rmtree(onnx_output_dir)
        onnx_output_dir.mkdir(parents=True, exist_ok=True)
        try:
            run_command([
                "paddlex",
                "--paddle2onnx",
                "--paddle_model_dir", str(model_file.parent),
                "--onnx_model_dir", str(onnx_output_dir),
                "--opset_version", "7",
            ])
            return onnx_output_dir
        except Exception as exc:
            errors.append(f"paddlex failed: {exc}")

    if errors:
        raise RuntimeError("; ".join(errors))

    raise RuntimeError(
        "Neither paddle2onnx nor paddlex is available. "
        "Install PaddleX paddle2onnx plugin or put preconverted ONNX models under --source-dir."
    )


def ensure_onnx_model(
    model_name: str,
    dst_dir: Path,
    cache_dir: Path,
    source_dir: Path | None,
) -> None:
    existing = dst_dir / "inference.onnx"
    if existing.exists() and existing.stat().st_size > 0:
        print(f"skip existing onnx: {model_name} -> {dst_dir}")
        return

    model_source = prepare_model_source(model_name, cache_dir, source_dir)
    if copy_onnx_model(model_source, dst_dir):
        print(f"copied onnx: {model_name} -> {dst_dir}")
        return

    converted_dir = convert_paddle_to_onnx(model_source, cache_dir / f"{model_name}_onnx")
    if not copy_onnx_model(converted_dir, dst_dir):
        raise FileNotFoundError(f"converted ONNX model not found under {converted_dir}")
    print(f"converted: {model_name} -> {dst_dir}")


def yaml_quote(value: str) -> str:
    return "'" + value.replace("'", "''") + "'"


def download_dict(dict_url: str, cache_path: Path) -> list[str]:
    download(dict_url, cache_path)
    lines = cache_path.read_text("utf-8").replace("\r\n", "\n").replace("\r", "\n").split("\n")
    chars = [line for line in lines if line != ""]
    if not chars:
        raise RuntimeError(f"empty dict: {cache_path}")
    return chars


def write_android_inference_yml(chars: list[str], target: Path) -> None:
    target.parent.mkdir(parents=True, exist_ok=True)

    lines = [
        "PostProcess:",
        "  name: CTCLabelDecode",
        "  character_dict:",
    ]
    lines.extend(f"    - {yaml_quote(ch)}" for ch in chars)
    target.write_text("\n".join(lines) + "\n", "utf-8")


def resolve_profile_asset_roots(spec: dict[str, str], assets_root: Path | None) -> tuple[Path, Path]:
    if assets_root is not None:
        return assets_root, assets_root
    return (
        SOURCE_SET_ASSET_ROOTS[spec["det_source_set"]],
        SOURCE_SET_ASSET_ROOTS[spec["rec_source_set"]],
    )


def prepare_one(
    profile: str,
    assets_root: Path | None,
    cache_dir: Path,
    source_dir: Path | None,
) -> None:
    spec = PROFILES[profile]
    det_assets_root, rec_assets_root = resolve_profile_asset_roots(spec, assets_root)

    det_dst = det_assets_root / "models" / spec["det_asset"] / "det"
    rec_dst = rec_assets_root / "models" / spec["rec_asset"] / "rec"

    ensure_onnx_model(spec["det"], det_dst, cache_dir / "det", source_dir)
    ensure_onnx_model(spec["rec"], rec_dst, cache_dir / "rec", source_dir)

    chars = download_dict(spec["dict_url"], cache_dir / f"{profile}-dict.txt")
    write_android_inference_yml(chars, rec_dst / "inference.yml")

    print(f"prepared: {profile}")


def main() -> None:
    parser = argparse.ArgumentParser()
    parser.add_argument("--profile", choices=[*PROFILES.keys(), "all"], default="mobile")
    parser.add_argument(
        "--assets-root",
        default=None,
        help=(
            "Optional override root for all generated assets. By default, models are written "
            "to the Android source set used by each selected profile."
        ),
    )
    parser.add_argument("--cache-dir", default=".cache/ppocrv4")
    parser.add_argument(
        "--source-dir",
        default=None,
        help="Optional local directory containing official tar files or preconverted ONNX model folders.",
    )
    args = parser.parse_args()

    assets_root = Path(args.assets_root) if args.assets_root else None
    cache_dir = Path(args.cache_dir)
    source_dir = Path(args.source_dir) if args.source_dir else None

    profiles = list(PROFILES.keys()) if args.profile == "all" else [args.profile]
    for profile in profiles:
        prepare_one(profile, assets_root, cache_dir, source_dir)


if __name__ == "__main__":
    main()
