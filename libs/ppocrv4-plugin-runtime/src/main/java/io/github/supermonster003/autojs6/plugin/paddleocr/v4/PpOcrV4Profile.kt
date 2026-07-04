package io.github.supermonster003.autojs6.plugin.paddleocr.v4

enum class PpOcrV4Profile(
    val value: String,
    val detAssetPath: String,
    val recAssetPath: String,
    val recConfigAssetPath: String,
    val language: String,
    val recommendedForMobileDefault: Boolean,
) {
    MOBILE(
        value = "Mobile",
        detAssetPath = "models/ppocrv4-mobile-det/det/inference.onnx",
        recAssetPath = "models/ppocrv4-mobile/rec/inference.onnx",
        recConfigAssetPath = "models/ppocrv4-mobile/rec/inference.yml",
        language = "zh-CN/mixed",
        recommendedForMobileDefault = true,
    ),
    SERVER(
        value = "Server",
        detAssetPath = "models/ppocrv4-server/det/inference.onnx",
        recAssetPath = "models/ppocrv4-server/rec/inference.onnx",
        recConfigAssetPath = "models/ppocrv4-server/rec/inference.yml",
        language = "zh-CN/mixed",
        recommendedForMobileDefault = false,
    ),
    MOBILE_EN(
        value = "Mobile EN",
        detAssetPath = "models/ppocrv4-mobile-det/det/inference.onnx",
        recAssetPath = "models/ppocrv4-mobile-en/rec/inference.onnx",
        recConfigAssetPath = "models/ppocrv4-mobile-en/rec/inference.yml",
        language = "en",
        recommendedForMobileDefault = false,
    );

    companion object {
        internal fun fromConfig(config: PpOcrV4RuntimeConfig): PpOcrV4Profile {
            return values().firstOrNull { it.value == config.modelProfile } ?: MOBILE
        }
    }
}
