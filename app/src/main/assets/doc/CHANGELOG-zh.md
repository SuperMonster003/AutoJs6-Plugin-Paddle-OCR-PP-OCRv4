******

### 发行历史

******

# v1.0.4

###### 2026/09/19

* `修复` AGP 9.1 构建时的 SDK XML v4 解析警告及 JVM 单元测试组装任务误触发 APK 原生库对齐检查的问题 (共享构建插件 1.8.3)
* `优化` 将 compileSdk 与 targetSdk 提升到 37 (Android 17), 插件行为不受新目标版本影响

# v1.0.3

###### 2026/09/13

* `修复` 插件中心显示的版本与 ABI 信息匹配实际安装的 APK
* `修复` 编码图像最大为 64 MiB, 支持文件描述符和管道传输
* `修复` 版本日期保持统一的英文格式
* `优化` 发布下载文件生成前校验 APK 版本, 签名与完整变体集合
* `优化` 图像最多包含 16777216 个像素, 原始图像缓冲区上限为 64 MiB
* `优化` 扩展原生 ABI 打包与插件元数据至 arm64-v8a, armeabi-v7a, x86 和 x86_64, 同步通用 APK 与各 ABI 独立 APK

# v1.0.2

###### 2026/09/12

* `修复` 修复 16 KB 页大小设备上引擎初始化即崩溃 (SIGSEGV) 的问题: 随包的 `libc++_shared.so` 更新为 NDK r28.2 构建版本, 其 RELRO 段末尾不再与可写数据共用内存页 (arm64-v8a, armeabi-v7a)
* `修复` 修复加载体积较大的识别模型时因 `OutOfMemoryError` 而静默返回空结果的问题: 模型资源现在只会复制到应用私有目录一次, 并由 ONNX Runtime 以内存映射方式创建会话, 不再整体读入 Java 堆
* `修复` 部分 Gradle/AGP 组合下 Kotlin 源码未参与编译, 以及重复定义 `WakeActivity` 导致的构建失败
* `优化` 同步 OpenCV 4.8.0 原生库至 NDK r28c (Clang 19.0.1) 重编版本 (donor: AutoJs6-Plugin-OpenCV), 4 个 ABI 的 `libopencv_java4.so` 保持 16 KB `PT_LOAD` 对齐并附带 provenance 清单

# v1.0.1

###### 2026/09/11

* `优化` 构建阶段校验 64 位原生库的 16 KB 页大小对齐, 检查 manifest 契约并输出 JSON 报告

# v1.0.0

###### 2026/09/01

* `新增` Paddle OCR PP-OCRv4 插件服务, 引擎为 `paddle-ocr`, 变体为 `v4`
* `新增` 三个 OCR profile: `mobile`, `server`, `mobile-en`, 分别对应移动端通用模型, 高精度服务端模型和英文识别模型
* `新增` 支持通过 `org.autojs.plugin.PADDLE_OCR` 发现插件, 并支持 `recognizeText` 与 `detect` 调用
* `新增` 支持返回 OCR 文本, 置信度, 矩形边界, 四点坐标和耗时信息
* `新增` 模型准备脚本 `scripts/prepare_ppocrv4_assets.py`, 可下载并转换 PP-OCRv4 模型到 ONNX
* `新增` 插件信息和使用说明的多语言资源: 西班牙语/法语/俄语/阿拉伯语/日语/韩语/英语/简体中文/香港繁体/台湾繁体
* `新增` README 与 CHANGELOG 的 JSON 源和脚本生成流程
* `修复` 部分系统安装后无法通过插件中心激活的问题
* `优化` 按 flavor 拆分模型资产, `mobile` 和 `mobile-en` 共享检测模型, 避免 APK 携带无关识别模型
* `优化` 构建阶段校验所需 `inference.onnx` 和 `inference.yml`, 缺失时提示对应准备命令
* `优化` Release APK 文件名包含版本号, profile 和 ABI 变体, 并支持 `arm64-v8a`, `armeabi-v7a` 与 `universal` 输出
* `优化` 统一 README 版式与 Gradle 平台版本管理方式
* `依赖` 集成 `common-plugin-api.aar`, `paddle-ocr-api.aar`, PP-OCRv4 runtime, Paddle OCR Android SDK, OpenCV 4.8.0 和 ONNX Runtime
