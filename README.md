# AutoJs6 Paddle OCR PP-OCRv4 Plugin

This project packages Paddle OCR PP-OCRv4 as standalone AutoJs6 OCR plugins.

## Profiles

- `mobile`: default recommended profile. Uses `PP-OCRv4_mobile_det` and `PP-OCRv4_mobile_rec`.
- `server`: high-accuracy profile. Uses `PP-OCRv4_server_det` and `PP-OCRv4_server_rec`.
- `mobile-en`: Mobile EN profile. Uses shared `PP-OCRv4_mobile_det` and `en_PP-OCRv4_mobile_rec`.

Plugin ids:

```text
paddle-ocr-pp-ocrv4-mobile
paddle-ocr-pp-ocrv4-server
paddle-ocr-pp-ocrv4-mobile-en
```

## Prepare Models

PP-OCRv4 has no official Android ONNX package in the current PaddleOCR Android deployment table. The script downloads official Paddle static inference models and converts them with `paddle2onnx` or `paddlex`. It can also consume local official tar files or preconverted ONNX folders through `--source-dir`.

```powershell
python scripts\prepare_ppocrv4_assets.py --profile mobile
python scripts\prepare_ppocrv4_assets.py --profile server
python scripts\prepare_ppocrv4_assets.py --profile mobile-en
```

By default the script writes only the assets needed by each Android flavor:

```text
app/src/sharedMobileDet/assets/models/ppocrv4-mobile-det/det/inference.onnx
app/src/mobile/assets/models/ppocrv4-mobile/rec/inference.onnx
app/src/server/assets/models/ppocrv4-server/{det,rec}/inference.onnx
app/src/mobileEn/assets/models/ppocrv4-mobile-en/rec/inference.onnx
```

You can still force a single shared asset root, for example `--assets-root app/src/main/assets`, but the per-flavor layout keeps APKs from shipping unrelated models.

Prepare all profiles:

```powershell
python scripts\prepare_ppocrv4_assets.py --profile all
```

Use local resources:

```powershell
python scripts\prepare_ppocrv4_assets.py --profile all --source-dir E:\tmp\paddle-ocr-pp-ocrv4
```

If `paddle2onnx` fails on Windows with a native DLL load error, use a Python version and Paddle/Paddle2ONNX combination supported by the current Paddle2ONNX wheel, run the script from that activated environment, or provide preconverted official ONNX folders through `--source-dir`.

On Windows, `paddle2onnx==1.3.1` is currently a practical fallback for the official PP-OCRv4 `inference.pdmodel` packages used by the mobile profiles:

```powershell
python -m pip install --upgrade "paddle2onnx==1.3.1"
```

## Build

The Gradle build validates the required `inference.onnx` and `inference.yml` files before merging assets. If the models are missing, the build fails with the matching `prepare_ppocrv4_assets.py --profile ...` command instead of producing an APK that returns empty OCR results.

```powershell
./gradlew :app:assembleMobileRelease
./gradlew :app:assembleServerRelease
./gradlew :app:assembleMobileEnRelease
```

## Install

```powershell
./gradlew :app:installMobileRelease
./gradlew :app:installServerRelease
./gradlew :app:installMobileEnRelease
```

## AutoJs6 Usage

```javascript
ocr.tap('paddle');

const img = images.read('/sdcard/Download/ocr-test.png');
const results = ocr.detect(img, {
  engineId: 'paddle-ocr-pp-ocrv4-mobile',
  profile: 'mobile',
  useRaw: true,
  cpuThreadNum: 4,
  scoreThreshold: 0.5,
  detLimitSideLen: 736,
  detLimitType: 'min',
});

console.log(JSON.stringify(results, null, 2));
img.recycle();
```
