# v1.0.0

###### 2026/07/17

* `新增` Paddle OCR PP-OCRv4 插件服务, 引擎为 `paddle-ocr`, 变体为 `v4`
* `新增` 三个 OCR profile: `mobile`, `server`, `mobile-en`, 分别对应移动端通用模型, 高精度服务端模型和英文识别模型
* `新增` 支持通过 `org.autojs.plugin.PADDLE_OCR` 发现插件, 并支持 `recognizeText` 与 `detect` 调用
* `新增` 支持返回 OCR 文本, 置信度, 矩形边界, 四点坐标和耗时信息
* `新增` 模型准备脚本 `scripts/prepare_ppocrv4_assets.py`, 可下载并转换 PP-OCRv4 模型到 ONNX
* `新增` 插件信息和使用说明的多语言资源: 西班牙语/法语/俄语/阿拉伯语/日语/韩语/英语/简体中文/香港繁体/台湾繁体
* `新增` README 与 CHANGELOG 的 JSON 源和脚本生成流程
* `优化` 按 flavor 拆分模型资产, `mobile` 和 `mobile-en` 共享检测模型, 避免 APK 携带无关识别模型
* `优化` 构建阶段校验所需 `inference.onnx` 和 `inference.yml`, 缺失时提示对应准备命令
* `优化` Release APK 文件名包含版本号, profile 和 ABI 变体, 并支持 `arm64-v8a`, `armeabi-v7a` 与 `universal` 输出
* `依赖` 集成 `common-plugin-api.aar`, `paddle-ocr-api.aar`, PP-OCRv4 runtime, Paddle OCR Android SDK, OpenCV 4.8.0 和 ONNX Runtime
