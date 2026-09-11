******

### 發行歷史

******

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
