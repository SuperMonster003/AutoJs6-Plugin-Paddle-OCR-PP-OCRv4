<!--suppress HtmlDeprecatedAttribute, HttpUrlsUsage -->

<div align="center">
  <p>
    <picture>
      <img src="https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/app/src/main/res/mipmap/ic_launcher.png?raw=true" alt="autojs6-plugin-paddle-ocr-pp-ocrv4-ic-launcher" border="0" width="128" />
    </picture>
  </p>

  <p>用於 Android 文字辨識的 Paddle OCR PP-OCRv4 外掛</p>

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
- [繁體中文 (香港) [zh-Hant-HK]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-zh-Hant-HK.md)
- 繁體中文 (台灣) [zh-Hant-TW] # 目前
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

AutoJs6 Paddle OCR PP-OCRv4 外掛為 AutoJs6 提供基於百度飛槳 PaddleOCR 的本機 OCR 能力, 支援 PP-OCRv4 Mobile, Server 和 Mobile EN 三種模型設定.

******

### 功能

******

- 提供 `paddle-ocr` 外掛服務, 服務發現 action 為 `org.autojs.plugin.PADDLE_OCR`.
- 提供三個外掛 ID: `paddle-ocr-pp-ocrv4-mobile`, `paddle-ocr-pp-ocrv4-server`, `paddle-ocr-pp-ocrv4-mobile-en`.
- 支援文字偵測和文字辨識, 並回傳文字, 信賴度, 矩形邊界和四點座標.
- 支援 raw image 輸入, 並回報 `modelFamily`, `modelProfile`, `language` 和支援 ABI 等能力資訊.
- 外掛資訊, 使用說明, README 與 CHANGELOG 均支援西班牙文/法文/俄文/阿拉伯文/日文/韓文/英文/簡體中文/香港繁體/台灣繁體.

******

### 模型設定

******

- `mobile`: 建議的 Android 預設設定, 使用 PP-OCRv4 mobile 偵測和辨識模型.
- `server`: 較高精度設定, 使用 PP-OCRv4 server 偵測和辨識模型, 較適合高效能裝置.
- `mobile-en`: 英文和數字辨識設定, 共用 mobile 偵測模型並使用英文辨識模型.

外掛 ID:

```text
paddle-ocr-pp-ocrv4-mobile
paddle-ocr-pp-ocrv4-server
paddle-ocr-pp-ocrv4-mobile-en
```

******

### 準備模型

******

倉庫不直接包含 PP-OCRv4 模型檔案. 先執行準備腳本下載官方 Paddle 靜態推論模型並轉換為 ONNX, 也可以透過 `--source-dir` 使用本機模型包:

```powershell
python scripts\prepare_ppocrv4_assets.py --profile mobile
python scripts\prepare_ppocrv4_assets.py --profile server
python scripts\prepare_ppocrv4_assets.py --profile mobile-en
python scripts\prepare_ppocrv4_assets.py --profile all
```

預設依 Android flavor 寫入各自所需資產, 避免 APK 攜帶無關模型:

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

# v1.0.2

###### 2026/09/12

* `修復` 修復 16 KB 分頁大小裝置上引擎初始化即當機 (SIGSEGV) 的問題: 隨附的 `libc++_shared.so` 更新為 NDK r28.2 建置版本, 其 RELRO 區段末尾不再與可寫資料共用記憶體頁 (arm64-v8a, armeabi-v7a)
* `修復` 修復載入體積較大的辨識模型時因 `OutOfMemoryError` 而靜默回傳空結果的問題: 模型資源現在只會複製到應用私有目錄一次, 並由 ONNX Runtime 以記憶體映射方式建立工作階段, 不再整體讀入 Java 堆積
* `最佳化` 同步 OpenCV 4.8.0 原生程式庫至 NDK r28c (Clang 19.0.1) 重新建置版本 (donor: AutoJs6-Plugin-OpenCV), 4 個 ABI 的 `libopencv_java4.so` 保持 16 KB `PT_LOAD` 對齊並附帶 provenance 清單

# v1.0.1

###### 2026/09/11

* `最佳化` 建置階段校驗 64 位原生函式庫的 16 KB 頁面大小對齊, 檢查 manifest 契約並輸出 JSON 報告

# v1.0.0

###### 2026/09/01

* `新增` Paddle OCR PP-OCRv4 外掛服務, 引擎為 `paddle-ocr`, 變體為 `v4`
* `新增` 三個 OCR profile: `mobile`, `server`, `mobile-en`, 分別對應行動端通用模型, 高精度伺服器模型和英文辨識模型
* `新增` 支援透過 `org.autojs.plugin.PADDLE_OCR` 發現外掛, 並支援 `recognizeText` 與 `detect` 呼叫
* `新增` 支援回傳 OCR 文字, 信賴度, 矩形邊界, 四點座標和耗時資訊
* `新增` 模型準備腳本 `scripts/prepare_ppocrv4_assets.py`, 可下載並轉換 PP-OCRv4 模型到 ONNX
* `新增` 外掛資訊和使用說明的多語言資源: 西班牙文/法文/俄文/阿拉伯文/日文/韓文/英文/簡體中文/香港繁體/台灣繁體
* `新增` README 與 CHANGELOG 的 JSON 來源和腳本生成流程
* `修復` 部分系統安裝後無法透過外掛中心啟用的問題
* `最佳化` 依 flavor 拆分模型資產, `mobile` 和 `mobile-en` 共用偵測模型, 避免 APK 攜帶無關辨識模型
* `最佳化` 建置階段校驗所需 `inference.onnx` 和 `inference.yml`, 缺失時提示對應準備命令
* `最佳化` Release APK 檔名包含版本號, profile 和 ABI 變體, 並支援 `arm64-v8a`, `armeabi-v7a` 與 `universal` 輸出
* `最佳化` 統一 README 版式與 Gradle 平台版本管理方式
* `依賴` 整合 `common-plugin-api.aar`, `paddle-ocr-api.aar`, PP-OCRv4 runtime, Paddle OCR Android SDK, OpenCV 4.8.0 和 ONNX Runtime

##### 更多發行歷史可參閱

* [完整發行歷史](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/app/src/main/assets/doc/CHANGELOG-zh-Hant-TW.md)

******

### 建置

******

```powershell
.\gradlew.bat :app:assembleMobileRelease
.\gradlew.bat :app:assembleServerRelease
.\gradlew.bat :app:assembleMobileEnRelease
```

建置參數主要來自 `version.properties`, app 目前最低 SDK 為 26, 目標 SDK 為 36.

Gradle 建置會在合併 assets 前校驗所需 `inference.onnx` 和 `inference.yml` 檔案, 缺失時會提示對應的模型準備命令.

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

`strings.xml` 提供外掛描述在地化; `plugin_instruction.md` 提供宿主側展示的外掛使用說明. README 與 CHANGELOG 由 `.python/generate_markdown.py` 根據 JSON 來源檔案生成.

******

### 相關連結

******

- AutoJs6 OCR 文件: https://docs.autojs6.com/#/ocr
- PaddleOCR 官方專案: https://github.com/PaddlePaddle/PaddleOCR
- Paddle2ONNX 專案: https://github.com/PaddlePaddle/Paddle2ONNX


[16 KB page alignment and build verification](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/docs/16kb.md)
