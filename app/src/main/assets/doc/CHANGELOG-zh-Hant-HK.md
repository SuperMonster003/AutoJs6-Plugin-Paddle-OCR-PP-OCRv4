******

### 發行歷史

******

# v1.0.4

###### 2026/09/19

* `修復` AGP 9.1 構建時的 SDK XML v4 解析警告及 JVM 單元測試組裝任務誤觸發 APK 原生程式庫對齊檢查的問題 (共用構建外掛 1.8.3)
* `優化` 將 compileSdk 與 targetSdk 提升到 37 (Android 17), 插件行為不受新目標版本影響

# v1.0.3

###### 2026/09/13

* `修復` 外掛中心顯示的版本與 ABI 資訊符合實際安裝的 APK
* `修復` 編碼圖像最大為 64 MiB, 支援檔案描述符和管道傳輸
* `修復` 版本日期保持統一的英文格式
* `優化` 發佈下載檔案產生前校驗 APK 版本, 簽署與完整變體集合
* `優化` 影像最多包含 16777216 個像素, 原始影像緩衝區上限為 64 MiB
* `優化` 擴展原生 ABI 打包與插件中繼資料至 arm64-v8a, armeabi-v7a, x86 和 x86_64, 同步通用 APK 與各 ABI 獨立 APK

# v1.0.2

###### 2026/09/12

* `修復` 修復 16 KB 頁大小設備上引擎初始化即崩潰 (SIGSEGV) 的問題: 隨包的 `libc++_shared.so` 更新為 NDK r28.2 構建版本, 其 RELRO 段末尾不再與可寫資料共用記憶體頁 (arm64-v8a, armeabi-v7a)
* `修復` 修復載入體積較大的識別模型時因 `OutOfMemoryError` 而靜默返回空結果的問題: 模型資源現在只會複製到應用私有目錄一次, 並由 ONNX Runtime 以記憶體映射方式建立會話, 不再整體讀入 Java 堆
* `修復` 部分 Gradle/AGP 組合下 Kotlin 原始碼未參與編譯, 以及重複定義 `WakeActivity` 導致的建置失敗
* `優化` 同步 OpenCV 4.8.0 原生庫至 NDK r28c (Clang 19.0.1) 重編版本 (donor: AutoJs6-Plugin-OpenCV), 4 個 ABI 的 `libopencv_java4.so` 保持 16 KB `PT_LOAD` 對齊並附帶 provenance 清單

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
