<!--suppress HtmlDeprecatedAttribute, HttpUrlsUsage -->

<div align="center">
  <p>
    <img src="https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/app/src/main/res/mipmap/ic_launcher.png?raw=true" alt="autojs6-plugin-paddle-ocr-pp-ocrv4-ic-launcher" border="0" width="128" />
  </p>

  <p>Paddle OCR PP-OCRv4 plugin for Android text recognition</p>

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

### Languages

******

README.md is available in the following languages:

- [简体中文 [zh-Hans]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-zh-Hans.md)
- [繁體中文 (香港) [zh-Hant-HK]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-zh-Hant-HK.md)
- [繁體中文 (台灣) [zh-Hant-TW]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-zh-Hant-TW.md)
- English [en] # current
- [Français [fr]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-fr.md)
- [Español [es]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-es.md)
- [日本語 [ja]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-ja.md)
- [한국어 [ko]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-ko.md)
- [Русский [ru]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-ru.md)
- [العربية [ar]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-ar.md)

******

### Introduction

******

AutoJs6 Paddle OCR PP-OCRv4 Plugin provides local OCR for AutoJs6 based on Baidu PaddleOCR, with PP-OCRv4 Mobile, Server, and Mobile EN model profiles.

******

### Features

******

- Provides the `paddle-ocr` plugin service, discovered with action `org.autojs.plugin.PADDLE_OCR`.
- Provides three plugin IDs: `paddle-ocr-pp-ocrv4-mobile`, `paddle-ocr-pp-ocrv4-server`, `paddle-ocr-pp-ocrv4-mobile-en`.
- Supports text detection and text recognition, returning text, confidence, rectangular bounds, and quad points.
- Supports raw image input and reports capabilities such as `modelFamily`, `modelProfile`, `language`, and supported ABIs.
- Plugin metadata, instructions, README, and CHANGELOG are localized for Spanish/French/Russian/Arabic/Japanese/Korean/English/Simplified Chinese/Hong Kong Traditional Chinese/Taiwan Traditional Chinese.

******

### Profiles

******

- `mobile`: Recommended Android default profile, using PP-OCRv4 mobile detection and recognition models.
- `server`: Higher-accuracy profile, using PP-OCRv4 server detection and recognition models for higher-performance devices.
- `mobile-en`: English and number recognition profile, sharing the mobile detection model with an English recognition model.

Plugin IDs:

```text
paddle-ocr-pp-ocrv4-mobile
paddle-ocr-pp-ocrv4-server
paddle-ocr-pp-ocrv4-mobile-en
```

******

### Prepare Models

******

The repository does not ship PP-OCRv4 model files directly. Run the preparation script to download official Paddle static inference models and convert them to ONNX, or use local model packages with `--source-dir`:

```powershell
python scripts\prepare_ppocrv4_assets.py --profile mobile
python scripts\prepare_ppocrv4_assets.py --profile server
python scripts\prepare_ppocrv4_assets.py --profile mobile-en
python scripts\prepare_ppocrv4_assets.py --profile all
```

By default, assets are written per Android flavor so APKs do not carry unrelated models:

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

### Usage

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

### Release History

******

# v1.0.0

###### 2026/07/17

* `Feature` Paddle OCR PP-OCRv4 plugin service with engine `paddle-ocr` and variant `v4`
* `Feature` Three OCR profiles: `mobile`, `server`, and `mobile-en`, covering the mobile general model, higher-accuracy server model, and English recognition model
* `Feature` Plugin discovery through `org.autojs.plugin.PADDLE_OCR`, with `recognizeText` and `detect` calls
* `Feature` OCR output with text, confidence, rectangular bounds, quad points, and timing information
* `Feature` Model preparation script `scripts/prepare_ppocrv4_assets.py`, which can download and convert PP-OCRv4 models to ONNX
* `Feature` Localized plugin metadata and instructions for Spanish/French/Russian/Arabic/Japanese/Korean/English/Simplified Chinese/Hong Kong Traditional Chinese/Taiwan Traditional Chinese
* `Feature` JSON source and script generation flow for README and CHANGELOG
* `Improvement` Split model assets by flavor, sharing the detection model between `mobile` and `mobile-en` so APKs do not ship unrelated recognition models
* `Improvement` Validate required `inference.onnx` and `inference.yml` files during the build, with matching preparation commands when assets are missing
* `Improvement` Release APK names include version, profile, and ABI variant, with `arm64-v8a`, `armeabi-v7a`, and `universal` outputs
* `Dependency` Integrated `common-plugin-api.aar`, `paddle-ocr-api.aar`, PP-OCRv4 runtime, Paddle OCR Android SDK, OpenCV 4.8.0, and ONNX Runtime

##### For more release history

* [Full release history](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.changelog/CHANGELOG-en.md)

******

### Build

******

```powershell
.\gradlew.bat :app:assembleMobileRelease
.\gradlew.bat :app:assembleServerRelease
.\gradlew.bat :app:assembleMobileEnRelease
```

Build parameters mainly come from `version.properties`, while the app currently uses min SDK 26 and target SDK 36.

The Gradle build validates required `inference.onnx` and `inference.yml` files before merging assets, and prints the matching model preparation command if any file is missing.

******

### Resource Layout

******

```text
.readme/lang_*.json
.changelog/lang_*.json
.python/generate_markdown.py
app/src/main/assets/doc/CHANGELOG*.md
app/src/main/res/values-*/strings.xml
app/src/main/res/raw-*/plugin_instruction.md
```

`strings.xml` provides localized plugin descriptions; `plugin_instruction.md` provides host-side plugin instructions. README and CHANGELOG are generated by `.python/generate_markdown.py` from JSON source files.

******

### Links

******

- AutoJs6 OCR documentation: https://docs.autojs6.com/#/ocr
- PaddleOCR official project: https://github.com/PaddlePaddle/PaddleOCR
- Paddle2ONNX project: https://github.com/PaddlePaddle/Paddle2ONNX
