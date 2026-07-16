<!--suppress HtmlDeprecatedAttribute, HttpUrlsUsage -->

<div align="center">
  <p>
    <img src="https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/app/src/main/res/mipmap/ic_launcher.png?raw=true" alt="autojs6-plugin-paddle-ocr-pp-ocrv4-ic-launcher" border="0" width="128" />
  </p>

  <p>Android 文字認識用 Paddle OCR PP-OCRv4 プラグイン</p>

  <p>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/releases"><img alt="GitHub release (latest by date)" src="https://img.shields.io/github/v/release/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4?label=Release"/></a>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/issues"><img alt="GitHub closed issues" src="https://img.shields.io/github/issues/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4?color=A24232&label=Issues"/></a>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/commit/668bfc2f23256c89bbad49590c946a0d3efb9b74"><img alt="Created" src="https://img.shields.io/date/1783168887?color=2e7d32&label=Created"/></a>
    <br>
    <a href="https://developer.android.com/studio/archive"><img alt="Android Studio" src="https://img.shields.io/badge/Android%20Studio-2023.3+-B64FC8"/></a>
    <a href="https://www.jetbrains.com/idea/download/other.html"><img alt="IntelliJ IDEA" src="https://img.shields.io/badge/IntelliJ%20IDEA-2023.3+-EE4677"/></a>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/LICENSE"><img alt="GitHub License" src="https://img.shields.io/github/license/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4?color=534BAE&label=License"/></a>
  </p>
</div>

******

### 言語 (Languages)

******

README.md は次の言語で利用できます:

- [简体中文 [zh-Hans]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-zh-Hans.md)
- [繁體中文 (香港) [zh-Hant-HK]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-zh-Hant-HK.md)
- [繁體中文 (台灣) [zh-Hant-TW]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-zh-Hant-TW.md)
- [English [en]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-en.md)
- [Français [fr]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-fr.md)
- [Español [es]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-es.md)
- 日本語 [ja] # 現在
- [한국어 [ko]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-ko.md)
- [Русский [ru]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-ru.md)
- [العربية [ar]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-ar.md)

******

### 概要

******

AutoJs6 Paddle OCR PP-OCRv4 プラグインは Baidu PaddleOCR を基盤にしたローカル OCR を AutoJs6 に提供します. PP-OCRv4 Mobile, Server, Mobile EN の 3 つのモデルプロファイルに対応します.

******

### 機能

******

- `org.autojs.plugin.PADDLE_OCR` action で検出される `paddle-ocr` プラグインサービスを提供します.
- 3 つのプラグイン ID を提供します: `paddle-ocr-pp-ocrv4-mobile`, `paddle-ocr-pp-ocrv4-server`, `paddle-ocr-pp-ocrv4-mobile-en`.
- 文字検出と文字認識に対応し, テキスト, 信頼度, 矩形境界, 四点座標を返します.
- raw image 入力に対応し, `modelFamily`, `modelProfile`, `language`, 対応 ABI などの能力情報を報告します.
- プラグインメタデータ, 使用説明, README, CHANGELOG はスペイン語/フランス語/ロシア語/アラビア語/日本語/韓国語/英語/簡体字中国語/香港繁体字/台湾繁体字にローカライズされています.

******

### プロファイル

******

- `mobile`: Android の推奨デフォルトプロファイルで, PP-OCRv4 mobile の検出モデルと認識モデルを使用します.
- `server`: 高精度プロファイルで, 高性能デバイス向けに PP-OCRv4 server の検出モデルと認識モデルを使用します.
- `mobile-en`: 英語と数字の認識プロファイルで, mobile 検出モデルを共有し英語認識モデルを使用します.

プラグイン ID:

```text
paddle-ocr-pp-ocrv4-mobile
paddle-ocr-pp-ocrv4-server
paddle-ocr-pp-ocrv4-mobile-en
```

******

### モデル準備

******

このリポジトリは PP-OCRv4 モデルファイルを直接同梱しません. 準備スクリプトで公式 Paddle 静的推論モデルをダウンロードして ONNX に変換するか, `--source-dir` でローカルモデルパッケージを使用します:

```powershell
python scripts\prepare_ppocrv4_assets.py --profile mobile
python scripts\prepare_ppocrv4_assets.py --profile server
python scripts\prepare_ppocrv4_assets.py --profile mobile-en
python scripts\prepare_ppocrv4_assets.py --profile all
```

デフォルトでは Android flavor ごとに必要な assets を書き込み, APK が無関係なモデルを含まないようにします:

```text
app/src/sharedMobileDet/assets/models/ppocrv4-mobile-det/det/inference.onnx
app/src/mobile/assets/models/ppocrv4-mobile/rec/inference.onnx
app/src/mobile/assets/models/ppocrv4-mobile/rec/inference.yml
app/src/server/assets/models/ppocrv4-server/det/inference.onnx
app/src/server/assets/models/ppocrv4-server/rec/inference.onnx
app/src/server/assets/models/ppocrv4-server/rec/inference.yml
app/src/mobileEn/assets/models/ppocrv4-mobile-en/rec/inference.onnx
app/src/mobileEn/assets/models/ppocrv4-mobile-en/rec/inference.yml
```

******

### 使用例

******

```js
ocr.tap("paddle");

const img = images.read("/sdcard/Download/ocr-test.png");
const results = ocr.detect(img, {
  engineId: "paddle-ocr-pp-ocrv4-mobile",
  profile: "mobile",
  useRaw: true,
  cpuThreadNum: 4,
  scoreThreshold: 0.5,
  detLimitSideLen: 736,
  detLimitType: "min",
});

console.log(JSON.stringify(results, null, 2));
img.recycle();
```

******

### リリース履歴

******

# v1.0.0

###### 2026/07/17

* `機能` エンジン `paddle-ocr`, バリアント `v4` の Paddle OCR PP-OCRv4 プラグインサービス
* `機能` 3 つの OCR profile: `mobile`, `server`, `mobile-en`, mobile 汎用モデル, 高精度 server モデル, 英語認識モデルに対応
* `機能` `org.autojs.plugin.PADDLE_OCR` によるプラグイン検出と, `recognizeText` および `detect` 呼び出しに対応
* `機能` OCR 出力としてテキスト, 信頼度, 矩形境界, 四点座標, 処理時間情報を返す機能
* `機能` PP-OCRv4 モデルをダウンロードして ONNX に変換できるモデル準備スクリプト `scripts/prepare_ppocrv4_assets.py`
* `機能` スペイン語/フランス語/ロシア語/アラビア語/日本語/韓国語/英語/簡体字中国語/香港繁体字/台湾繁体字のプラグインメタデータと使用説明
* `機能` README と CHANGELOG の JSON ソースおよびスクリプト生成フロー
* `改善` flavor ごとにモデル assets を分割し, `mobile` と `mobile-en` で検出モデルを共有して APK が無関係な認識モデルを含まないように変更
* `改善` ビルド中に必要な `inference.onnx` と `inference.yml` を検証し, assets が不足する場合は対応する準備コマンドを表示
* `改善` Release APK 名に version, profile, ABI variant を含め, `arm64-v8a`, `armeabi-v7a`, `universal` 出力に対応
* `依存関係` `common-plugin-api.aar`, `paddle-ocr-api.aar`, PP-OCRv4 runtime, Paddle OCR Android SDK, OpenCV 4.8.0, ONNX Runtime を統合

##### さらに詳しいリリース履歴

* [完全なリリース履歴](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.changelog/CHANGELOG-ja.md)

******

### ビルド

******

```powershell
.\gradlew.bat :app:assembleMobileRelease
.\gradlew.bat :app:assembleServerRelease
.\gradlew.bat :app:assembleMobileEnRelease
```

ビルドパラメータは主に `version.properties` から取得され, app は現在 min SDK 26 と target SDK 36 を使用します.

Gradle ビルドは assets をマージする前に必要な `inference.onnx` と `inference.yml` ファイルを検証し, 不足している場合は対応するモデル準備コマンドを表示します.

******

### リソース構成

******

```text
.readme/lang_*.json
.changelog/lang_*.json
.python/generate_markdown.py
app/src/main/assets/doc/CHANGELOG*.md
app/src/main/res/values-*/strings.xml
app/src/main/res/raw-*/plugin_instruction.md
```

`strings.xml` はローカライズされたプラグイン説明を提供します; `plugin_instruction.md` はホスト側で表示されるプラグイン使用説明を提供します. README と CHANGELOG は JSON ソースファイルから `.python/generate_markdown.py` によって生成されます.

******

### リンク

******

- AutoJs6 OCR ドキュメント: https://docs.autojs6.com/#/ocr
- PaddleOCR 公式プロジェクト: https://github.com/PaddlePaddle/PaddleOCR
- Paddle2ONNX プロジェクト: https://github.com/PaddlePaddle/Paddle2ONNX
