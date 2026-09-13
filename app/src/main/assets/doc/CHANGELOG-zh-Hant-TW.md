******

### 發行歷史

******

# v1.0.3

###### 2026/09/13

* `修復` 外掛中心顯示的版本與 ABI 資訊符合實際安裝的 APK
* `修復` 編碼影像最大為 64 MiB, 支援檔案描述元和管線傳輸
* `最佳化` 發行下載檔案產生前驗證 APK 版本, 簽章與完整變體集合
* `最佳化` 影像最多包含 16777216 個像素, 原始影像緩衝區上限為 64 MiB

# v1.0.2

###### 2026/09/12

* `修復` 修復 16 KB 分頁大小裝置上引擎初始化即當機 (SIGSEGV) 的問題: 隨附的 `libc++_shared.so` 更新為 NDK r28.2 建置版本, 其 RELRO 區段末尾不再與可寫資料共用記憶體頁 (arm64-v8a, armeabi-v7a)
* `修復` 修復載入體積較大的辨識模型時因 `OutOfMemoryError` 而靜默回傳空結果的問題: 模型資源現在只會複製到應用私有目錄一次, 並由 ONNX Runtime 以記憶體映射方式建立工作階段, 不再整體讀入 Java 堆積
* `修復` 部分 Gradle/AGP 組合下 Kotlin 原始碼未參與編譯, 以及重複定義 `WakeActivity` 導致的建置失敗
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
