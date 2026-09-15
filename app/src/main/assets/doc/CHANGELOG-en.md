******

### Release History

******

# v1.0.4

###### 2026/09/15

* `Improvement` Raise compileSdk and targetSdk to 37 (Android 17); the plugin's behavior does not depend on the new target

# v1.0.3

###### 2026/09/13

* `Fix` Plugin center version and ABI information matches the installed plugin APK
* `Fix` Encoded image input is limited to 64 MiB and supports file descriptors and pipes
* `Fix` Version dates use a consistent English format
* `Improvement` Validate release APK versions, signing and the complete variant set before creating download artifacts
* `Improvement` Images may contain at most 16777216 pixels; raw image buffers are limited to 64 MiB
* `Improvement` Extend native ABI packaging and plugin metadata to arm64-v8a, armeabi-v7a, x86 and x86_64, with matching universal and per-ABI APKs

# v1.0.2

###### 2026/09/12

* `Fix` Fixed the engine crashing at initialization (SIGSEGV) on 16 KB page size devices: the bundled `libc++_shared.so` is now the NDK r28.2 build, whose RELRO segment no longer shares a page with writable data (arm64-v8a, armeabi-v7a)
* `Fix` Fixed large recognition models silently returning empty results after an `OutOfMemoryError`: model assets are now materialized once into app-private storage and memory-mapped by ONNX Runtime instead of being read into the Java heap
* `Fix` Kotlin sources not compiled and duplicate `WakeActivity` definitions could cause build failures with some Gradle/AGP combinations
* `Improvement` Synced the OpenCV 4.8.0 native library to the NDK r28c (Clang 19.0.1) rebuild (donor: AutoJs6-Plugin-OpenCV); `libopencv_java4.so` for all 4 ABIs keeps 16 KB `PT_LOAD` alignment and ships with a provenance manifest

# v1.0.1

###### 2026/09/11

* `Improvement` Build verification of 16 KB page alignment for 64-bit native libraries, including manifest contract checks and JSON reports

# v1.0.0

###### 2026/09/01

* `Feature` Paddle OCR PP-OCRv4 plugin service with engine `paddle-ocr` and variant `v4`
* `Feature` Three OCR profiles: `mobile`, `server`, and `mobile-en`, covering the mobile general model, higher-accuracy server model, and English recognition model
* `Feature` Plugin discovery through `org.autojs.plugin.PADDLE_OCR`, with `recognizeText` and `detect` calls
* `Feature` OCR output with text, confidence, rectangular bounds, quad points, and timing information
* `Feature` Model preparation script `scripts/prepare_ppocrv4_assets.py`, which can download and convert PP-OCRv4 models to ONNX
* `Feature` Localized plugin metadata and instructions for Spanish/French/Russian/Arabic/Japanese/Korean/English/Simplified Chinese/Hong Kong Traditional Chinese/Taiwan Traditional Chinese
* `Feature` JSON source and script generation flow for README and CHANGELOG
* `Fix` The plugin could not be activated from Plugin Center after installation on some systems
* `Improvement` Split model assets by flavor, sharing the detection model between `mobile` and `mobile-en` so APKs do not ship unrelated recognition models
* `Improvement` Validate required `inference.onnx` and `inference.yml` files during the build, with matching preparation commands when assets are missing
* `Improvement` Release APK names include version, profile, and ABI variant, with `arm64-v8a`, `armeabi-v7a`, and `universal` outputs
* `Improvement` Standardize the README layout and Gradle platform version management
* `Dependency` Integrated `common-plugin-api.aar`, `paddle-ocr-api.aar`, PP-OCRv4 runtime, Paddle OCR Android SDK, OpenCV 4.8.0, and ONNX Runtime
