******

### Release History

******

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
