<!--suppress HtmlDeprecatedAttribute, HttpUrlsUsage -->

<div align="center">
  <p>
    <picture>
      <img src="https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/app/src/main/res/mipmap/ic_launcher.png?raw=true" alt="autojs6-plugin-paddle-ocr-pp-ocrv4-ic-launcher" border="0" width="128" />
    </picture>
  </p>

  <p>用於 Android 文本識別的 Paddle OCR PP-OCRv4 插件</p>

  <p>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/releases"><img alt="GitHub release (latest by date)" src="https://img.shields.io/github/v/release/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4?label=Release"/></a>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/issues"><img alt="GitHub closed issues" src="https://img.shields.io/github/issues/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4?color=A24232&label=Issues"/></a>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/LICENSE"><img alt="GitHub License" src="https://img.shields.io/github/license/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4?color=534BAE&label=License"/></a>
  </p>
</div>

******

### 語言 (Languages)

******

目前 README.md 支援以下語言:

- [简体中文 [zh-Hans]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-zh-Hans.md)
- 繁體中文 (香港) [zh-Hant-HK] # 目前
- [繁體中文 (台灣) [zh-Hant-TW]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-zh-Hant-TW.md)
- [English [en]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-en.md)
- [Français [fr]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-fr.md)
- [Español [es]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-es.md)
- [日本語 [ja]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-ja.md)
- [한국어 [ko]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-ko.md)
- [Русский [ru]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-ru.md)
- [العربية [ar]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-ar.md)

******

### 簡介

******

AutoJs6 Paddle OCR PP-OCRv4 插件為 AutoJs6 提供基於百度飛槳 PaddleOCR 的本地 OCR 能力, 支援 PP-OCRv4 Mobile, Server 和 Mobile EN 三種模型配置.

******

### 功能

******

- 提供 `paddle-ocr` 插件服務, 服務發現 action 為 `org.autojs.plugin.PADDLE_OCR`.
- 提供三個插件 ID: `paddle-ocr-pp-ocrv4-mobile`, `paddle-ocr-pp-ocrv4-server`, `paddle-ocr-pp-ocrv4-mobile-en`.
- 支援文本檢測和文本識別, 並返回文本, 置信度, 矩形邊界和四點座標.
- 支援 raw image 輸入, 並上報 `modelFamily`, `modelProfile`, `language` 和支援 ABI 等能力資訊.
- 插件資訊, 使用說明, README 與 CHANGELOG 均支援西班牙語/法語/俄語/阿拉伯語/日語/韓語/英語/簡體中文/香港繁體/台灣繁體.

******

### 模型配置

******

- `mobile`: 推薦的 Android 預設配置, 使用 PP-OCRv4 mobile 檢測和識別模型.
- `server`: 更高精度配置, 使用 PP-OCRv4 server 檢測和識別模型, 更適合高效能裝置.
- `mobile-en`: 英文和數字識別配置, 共用 mobile 檢測模型並使用英文識別模型.

插件 ID:

```text
paddle-ocr-pp-ocrv4-mobile
paddle-ocr-pp-ocrv4-server
paddle-ocr-pp-ocrv4-mobile-en
```

******

### 準備模型

******

倉庫不直接包含 PP-OCRv4 模型文件. 先執行準備腳本下載官方 Paddle 靜態推理模型並轉換為 ONNX, 也可以透過 `--source-dir` 使用本地模型包:

```powershell
python scripts\prepare_ppocrv4_assets.py --profile mobile
python scripts\prepare_ppocrv4_assets.py --profile server
python scripts\prepare_ppocrv4_assets.py --profile mobile-en
python scripts\prepare_ppocrv4_assets.py --profile all
```

預設按 Android flavor 寫入各自所需資產, 避免 APK 攜帶無關模型:

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

### 使用範例

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

### 發行歷史

******

# v1.0.1

###### 2026/09/11

* `優化` 建置階段校驗 64 位原生程式庫的 16 KB 頁面大小對齊, 檢查 manifest 契約並輸出 JSON 報告

# v1.0.0

###### 2026/09/01

* `新增` Paddle OCR PP-OCRv4 插件服務, 引擎為 `paddle-ocr`, 變體為 `v4`
* `新增` 三個 OCR profile: `mobile`, `server`, `mobile-en`, 分別對應移動端通用模型, 高精度服務端模型和英文識別模型
* `新增` 支援透過 `org.autojs.plugin.PADDLE_OCR` 發現插件, 並支援 `recognizeText` 與 `detect` 調用
* `新增` 支援返回 OCR 文本, 置信度, 矩形邊界, 四點座標和耗時資訊
* `新增` 模型準備腳本 `scripts/prepare_ppocrv4_assets.py`, 可下載並轉換 PP-OCRv4 模型到 ONNX
* `新增` 插件資訊和使用說明的多語言資源: 西班牙語/法語/俄語/阿拉伯語/日語/韓語/英語/簡體中文/香港繁體/台灣繁體
* `新增` README 與 CHANGELOG 的 JSON 源和腳本生成流程
* `修復` 部分系統安裝後無法透過插件中心激活的問題
* `優化` 按 flavor 拆分模型資產, `mobile` 和 `mobile-en` 共用檢測模型, 避免 APK 攜帶無關識別模型
* `優化` 建置階段校驗所需 `inference.onnx` 和 `inference.yml`, 缺失時提示對應準備命令
* `優化` Release APK 文件名包含版本號, profile 和 ABI 變體, 並支援 `arm64-v8a`, `armeabi-v7a` 與 `universal` 輸出
* `優化` 統一 README 版式與 Gradle 平台版本管理方式
* `依賴` 集成 `common-plugin-api.aar`, `paddle-ocr-api.aar`, PP-OCRv4 runtime, Paddle OCR Android SDK, OpenCV 4.8.0 和 ONNX Runtime

##### 更多發行歷史可參閱

* [完整發行歷史](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/app/src/main/assets/doc/CHANGELOG-zh-Hant-HK.md)

******

### 建置

******

```powershell
.\gradlew.bat :app:assembleMobileRelease
.\gradlew.bat :app:assembleServerRelease
.\gradlew.bat :app:assembleMobileEnRelease
```

建置參數主要來自 `version.properties`, app 目前最低 SDK 為 26, 目標 SDK 為 36.

Gradle 建置會在合併 assets 前校驗所需 `inference.onnx` 和 `inference.yml` 文件, 缺失時會提示對應的模型準備命令.

******

### 資源結構

******

```text
.readme/lang_*.json
.changelog/lang_*.json
.python/generate_markdown.py
app/src/main/assets/doc/CHANGELOG*.md
app/src/main/res/values-*/strings.xml
app/src/main/res/raw-*/plugin_instruction.md
```

`strings.xml` 提供插件描述本地化; `plugin_instruction.md` 提供宿主側展示的插件使用說明. README 與 CHANGELOG 由 `.python/generate_markdown.py` 根據 JSON 源文件生成.

******

### 相關連結

******

- AutoJs6 OCR 文檔: https://docs.autojs6.com/#/ocr
- PaddleOCR 官方項目: https://github.com/PaddlePaddle/PaddleOCR
- Paddle2ONNX 項目: https://github.com/PaddlePaddle/Paddle2ONNX


[16 KB page alignment and build verification](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/docs/16kb.md)
