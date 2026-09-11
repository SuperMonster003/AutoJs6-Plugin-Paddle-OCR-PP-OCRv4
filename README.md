<!--suppress HtmlDeprecatedAttribute, HttpUrlsUsage -->

<div align="center">
  <p>
    <picture>
      <img src="https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/app/src/main/res/mipmap/ic_launcher.png?raw=true" alt="autojs6-plugin-paddle-ocr-pp-ocrv4-ic-launcher" border="0" width="128" />
    </picture>
  </p>

  <p>用于 Android 文本识别的 Paddle OCR PP-OCRv4 插件</p>

  <p>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/releases"><img alt="GitHub release (latest by date)" src="https://img.shields.io/github/v/release/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4?label=Release"/></a>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/issues"><img alt="GitHub closed issues" src="https://img.shields.io/github/issues/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4?color=A24232&label=Issues"/></a>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/LICENSE"><img alt="GitHub License" src="https://img.shields.io/github/license/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4?color=534BAE&label=License"/></a>
  </p>
</div>

******

### 语言 (Languages)

******

当前 README.md 支持以下语言:

- 简体中文 [zh-Hans] # 当前
- [繁體中文 (香港) [zh-Hant-HK]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-zh-Hant-HK.md)
- [繁體中文 (台灣) [zh-Hant-TW]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-zh-Hant-TW.md)
- [English [en]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-en.md)
- [Français [fr]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-fr.md)
- [Español [es]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-es.md)
- [日本語 [ja]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-ja.md)
- [한국어 [ko]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-ko.md)
- [Русский [ru]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-ru.md)
- [العربية [ar]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-ar.md)

******

### 简介

******

AutoJs6 Paddle OCR PP-OCRv4 插件为 AutoJs6 提供基于百度飞桨 PaddleOCR 的本地 OCR 能力, 支持 PP-OCRv4 Mobile, Server 和 Mobile EN 三种模型配置.

******

### 功能

******

- 提供 `paddle-ocr` 插件服务, 服务发现 action 为 `org.autojs.plugin.PADDLE_OCR`.
- 提供三个插件 ID: `paddle-ocr-pp-ocrv4-mobile`, `paddle-ocr-pp-ocrv4-server`, `paddle-ocr-pp-ocrv4-mobile-en`.
- 支持文本检测和文本识别, 并返回文本, 置信度, 矩形边界和四点坐标.
- 支持 raw image 输入, 并上报 `modelFamily`, `modelProfile`, `language` 和支持 ABI 等能力信息.
- 插件信息, 使用说明, README 与 CHANGELOG 均支持西班牙语/法语/俄语/阿拉伯语/日语/韩语/英语/简体中文/香港繁体/台湾繁体.

******

### 模型配置

******

- `mobile`: 推荐的 Android 默认配置, 使用 PP-OCRv4 mobile 检测和识别模型.
- `server`: 更高精度配置, 使用 PP-OCRv4 server 检测和识别模型, 更适合高性能设备.
- `mobile-en`: 英文和数字识别配置, 共享 mobile 检测模型并使用英文识别模型.

插件 ID:

```text
paddle-ocr-pp-ocrv4-mobile
paddle-ocr-pp-ocrv4-server
paddle-ocr-pp-ocrv4-mobile-en
```

******

### 准备模型

******

仓库不直接包含 PP-OCRv4 模型文件. 先运行准备脚本下载官方 Paddle 静态推理模型并转换为 ONNX, 也可以通过 `--source-dir` 使用本地模型包:

```powershell
python scripts\prepare_ppocrv4_assets.py --profile mobile
python scripts\prepare_ppocrv4_assets.py --profile server
python scripts\prepare_ppocrv4_assets.py --profile mobile-en
python scripts\prepare_ppocrv4_assets.py --profile all
```

默认按 Android flavor 写入各自所需资产, 避免 APK 携带无关模型:

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

### 使用示例

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

### 发行历史

******

# v1.0.2

###### 2026/09/12

* `修复` 修复 16 KB 页大小设备上引擎初始化即崩溃 (SIGSEGV) 的问题: 随包的 `libc++_shared.so` 更新为 NDK r28.2 构建版本, 其 RELRO 段末尾不再与可写数据共用内存页 (arm64-v8a, armeabi-v7a)
* `修复` 修复加载体积较大的识别模型时因 `OutOfMemoryError` 而静默返回空结果的问题: 模型资源现在只会复制到应用私有目录一次, 并由 ONNX Runtime 以内存映射方式创建会话, 不再整体读入 Java 堆
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

##### 更多发行历史可参阅

* [完整发行历史](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/app/src/main/assets/doc/CHANGELOG-zh-Hans.md)

******

### 构建

******

```powershell
.\gradlew.bat :app:assembleMobileRelease
.\gradlew.bat :app:assembleServerRelease
.\gradlew.bat :app:assembleMobileEnRelease
```

构建参数主要来自 `version.properties`, app 当前最低 SDK 为 26, 目标 SDK 为 36.

Gradle 构建会在合并 assets 前校验所需 `inference.onnx` 和 `inference.yml` 文件, 缺失时会提示对应的模型准备命令.

******

### 资源结构

******

```text
.readme/lang_*.json
.changelog/lang_*.json
.python/generate_markdown.py
app/src/main/assets/doc/CHANGELOG*.md
app/src/main/res/values-*/strings.xml
app/src/main/res/raw-*/plugin_instruction.md
```

`strings.xml` 提供插件描述本地化; `plugin_instruction.md` 提供宿主侧展示的插件使用说明. README 与 CHANGELOG 由 `.python/generate_markdown.py` 根据 JSON 源文件生成.

******

### 相关链接

******

- AutoJs6 OCR 文档: https://docs.autojs6.com/#/ocr
- PaddleOCR 官方项目: https://github.com/PaddlePaddle/PaddleOCR
- Paddle2ONNX 项目: https://github.com/PaddlePaddle/Paddle2ONNX


[16 KB page alignment and build verification](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/docs/16kb.md)
