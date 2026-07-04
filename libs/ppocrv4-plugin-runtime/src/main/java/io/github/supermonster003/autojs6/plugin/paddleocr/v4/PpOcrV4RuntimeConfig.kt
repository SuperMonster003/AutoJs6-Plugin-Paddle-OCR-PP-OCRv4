package io.github.supermonster003.autojs6.plugin.paddleocr.v4

import android.content.Context
import android.os.Build

internal class PpOcrV4RuntimeConfig private constructor(
    val pluginId: String,
    val pluginEngine: String,
    val pluginVariant: String,
    val pluginAuthor: String,
    val modelFamily: String,
    val modelProfile: String,
    val versionName: String,
    val versionCode: Long,
    val versionDate: String,
) {
    companion object {
        fun from(context: Context): PpOcrV4RuntimeConfig {
            val appContext = context.applicationContext
            val packageInfo = appContext.packageManager.getPackageInfo(appContext.packageName, 0)
            return PpOcrV4RuntimeConfig(
                pluginId = appContext.stringResource("plugin_id", "paddle-ocr-pp-ocrv4-mobile"),
                pluginEngine = appContext.stringResource("plugin_engine", "paddle-ocr"),
                pluginVariant = appContext.stringResource("plugin_variant", "v4"),
                pluginAuthor = appContext.stringResource("plugin_author", "SuperMonster003"),
                modelFamily = appContext.stringResource("model_family", "PP-OCRv4"),
                modelProfile = appContext.stringResource("model_profile", "Mobile"),
                versionName = packageInfo.versionName ?: "",
                versionCode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    packageInfo.longVersionCode
                } else {
                    @Suppress("DEPRECATION")
                    packageInfo.versionCode.toLong()
                },
                versionDate = appContext.stringResource("plugin_version_date", ""),
            )
        }

        private fun Context.stringResource(name: String, fallback: String): String {
            val id = resources.getIdentifier(name, "string", packageName)
            return if (id != 0) resources.getString(id) else fallback
        }
    }
}
