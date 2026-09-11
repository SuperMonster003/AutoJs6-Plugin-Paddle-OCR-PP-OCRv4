import com.android.build.api.variant.FilterConfiguration
import org.gradle.api.GradleException
import org.gradle.api.file.DuplicatesStrategy
import org.gradle.api.file.RelativePath
import org.gradle.api.provider.Property

plugins {
    id("io.github.supermonster003.autojs6-native-alignment")
    id("org.autojs.build.utils")
    id("org.autojs.build.versions")
    id("org.autojs.build.signs")
    id("org.autojs.build.jvm-convention")
    id("com.android.application")
}

val globalApplicationId = "io.github.supermonster003.autojs6.plugin.paddleocr.v4"

val buildTypeDebug = "debug"
val buildTypeRelease = "release"

val ocrModelAssetAlternativesByFlavor = mapOf(
    "mobile" to listOf(
        listOf(
            "app/src/sharedMobileDet/assets/models/ppocrv4-mobile-det/det/inference.onnx",
            "app/src/main/assets/models/ppocrv4-mobile-det/det/inference.onnx",
        ),
        listOf(
            "app/src/mobile/assets/models/ppocrv4-mobile/rec/inference.onnx",
            "app/src/main/assets/models/ppocrv4-mobile/rec/inference.onnx",
        ),
        listOf(
            "app/src/mobile/assets/models/ppocrv4-mobile/rec/inference.yml",
            "app/src/main/assets/models/ppocrv4-mobile/rec/inference.yml",
        ),
    ),
    "server" to listOf(
        listOf(
            "app/src/server/assets/models/ppocrv4-server/det/inference.onnx",
            "app/src/main/assets/models/ppocrv4-server/det/inference.onnx",
        ),
        listOf(
            "app/src/server/assets/models/ppocrv4-server/rec/inference.onnx",
            "app/src/main/assets/models/ppocrv4-server/rec/inference.onnx",
        ),
        listOf(
            "app/src/server/assets/models/ppocrv4-server/rec/inference.yml",
            "app/src/main/assets/models/ppocrv4-server/rec/inference.yml",
        ),
    ),
    "mobileEn" to listOf(
        listOf(
            "app/src/sharedMobileDet/assets/models/ppocrv4-mobile-det/det/inference.onnx",
            "app/src/main/assets/models/ppocrv4-mobile-det/det/inference.onnx",
        ),
        listOf(
            "app/src/mobileEn/assets/models/ppocrv4-mobile-en/rec/inference.onnx",
            "app/src/main/assets/models/ppocrv4-mobile-en/rec/inference.onnx",
        ),
        listOf(
            "app/src/mobileEn/assets/models/ppocrv4-mobile-en/rec/inference.yml",
            "app/src/main/assets/models/ppocrv4-mobile-en/rec/inference.yml",
        ),
    ),
)

fun String.uppercaseFirst(): String = replaceFirstChar { it.uppercase() }

fun cliProfileForFlavor(flavor: String): String = when (flavor) {
    "mobileEn" -> "mobile-en"
    else -> flavor
}

android {

    namespace = globalApplicationId
    compileSdk = versions.sdkVersionCompile

    defaultConfig {
        applicationId = globalApplicationId

        minSdk = 26
        targetSdk = versions.sdkVersionTarget

        versionCode = versions.appVersionCode
        versionName = versions.appVersionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "VERSION_DATE", "\"${utils.getDateString("MMM d, yyyy", "GMT+08:00")}\"")
        buildConfigField("String", "PLUGIN_ENGINE", "\"paddle-ocr\"")
        buildConfigField("String", "PLUGIN_VARIANT", "\"v4\"")
        buildConfigField("String", "MODEL_FAMILY", "\"PP-OCRv4\"")
        resValue("string", "plugin_author", "SuperMonster003")
        resValue("string", "plugin_engine", "paddle-ocr")
        resValue("string", "plugin_variant", "v4")
        resValue("string", "model_family", "PP-OCRv4")
        resValue("string", "plugin_version_date", utils.getDateString("MMM d, yyyy", "GMT+08:00"))

        ndk {
            abiFilters += listOf("arm64-v8a", "armeabi-v7a")
        }
    }

    flavorDimensions += "ocrProfile"

    productFlavors {
        create("mobile") {
            dimension = "ocrProfile"
            applicationIdSuffix = ".mobile"
            versionNameSuffix = "-mobile"
            buildConfigField("String", "PLUGIN_ID", "\"paddle-ocr-pp-ocrv4-mobile\"")
            buildConfigField("String", "MODEL_PROFILE", "\"mobile\"")
            resValue("string", "app_name", "Paddle OCR (PP-OCRv4 Mobile)")
            resValue("string", "plugin_id", "paddle-ocr-pp-ocrv4-mobile")
            resValue("string", "model_profile", "Mobile")
        }

        create("server") {
            dimension = "ocrProfile"
            applicationIdSuffix = ".server"
            versionNameSuffix = "-server"
            buildConfigField("String", "PLUGIN_ID", "\"paddle-ocr-pp-ocrv4-server\"")
            buildConfigField("String", "MODEL_PROFILE", "\"server\"")
            resValue("string", "app_name", "Paddle OCR (PP-OCRv4 Server)")
            resValue("string", "plugin_id", "paddle-ocr-pp-ocrv4-server")
            resValue("string", "model_profile", "Server")
        }

        create("mobileEn") {
            dimension = "ocrProfile"
            applicationIdSuffix = ".mobile.en"
            versionNameSuffix = "-mobile-en"
            buildConfigField("String", "PLUGIN_ID", "\"paddle-ocr-pp-ocrv4-mobile-en\"")
            buildConfigField("String", "MODEL_PROFILE", "\"mobile-en\"")
            resValue("string", "app_name", "Paddle OCR (PP-OCRv4 Mobile EN)")
            resValue("string", "plugin_id", "paddle-ocr-pp-ocrv4-mobile-en")
            resValue("string", "model_profile", "Mobile EN")
        }
    }

    lint {
        abortOnError = false
    }

    signingConfigs {
        if (signs.isValid) {
            create(buildTypeRelease) {
                storeFile = signs.properties["storeFile"]?.let { file(it as String) }
                keyPassword = signs.properties["keyPassword"] as String
                keyAlias = signs.properties["keyAlias"] as String
                storePassword = signs.properties["storePassword"] as String
            }
        }
    }

    buildTypes {
        val proguardFiles = arrayOf<Any>(
            getDefaultProguardFile("proguard-android-optimize.txt"),
            "proguard-rules.pro",
        )
        val niceSigningConfig = takeIf { signs.isValid }?.let {
            signingConfigs.getByName(buildTypeRelease)
        }
        debug {
            isMinifyEnabled = false
            proguardFiles(*proguardFiles)
            niceSigningConfig?.let { signingConfig = it }
        }
        release {
            isMinifyEnabled = true
            proguardFiles(*proguardFiles)
            niceSigningConfig?.let { signingConfig = it }
        }
    }

    buildFeatures {
        aidl = true
        buildConfig = true
        resValues = true
    }

    sourceSets.named("main") {
        kotlin.directories += "src/main/java"
    }

    listOf("mobile", "mobileEn").forEach { profile ->
        sourceSets.named(profile) {
            assets.srcDir("src/sharedMobileDet/assets")
        }
    }

    // @Hint by SuperMonster003 on Sep 25, 2024.
    //  ! To maintain compatibility with lower versions of Gradle (such as 7.4.2).
    //  ! zh-CN: 为了兼容低版本 Gradle (如 7.4.2).
    //  # packaging { ... }
    @Suppress("DEPRECATION")
    packagingOptions {
        jniLibs.useLegacyPackaging = true

        listOf(
            "META-INF/DEPENDENCIES",
            "META-INF/LICENSE",
            "META-INF/LICENSE.*",
            "META-INF/LICENSE-notice.*",
            "META-INF/license.*",
            "META-INF/NOTICE",
            "META-INF/NOTICE.*",
            "META-INF/notice.*",
            "META-INF/ASL2.0",
            "META-INF/*.kotlin_module",
        ).let { resources.pickFirsts.addAll(it) }
    }

    splits {
        abi {
            isEnable = true
            reset()
            include("arm64-v8a", "armeabi-v7a")
            isUniversalApk = true
        }
    }

    bundle {
        language {
            enableSplit = false
        }
        density {
            enableSplit = false
        }
        abi {
            enableSplit = false
        }
    }
}

androidComponents {
    onVariants { variant ->
        val flavorName = variant.productFlavors.firstOrNull { it.first == "ocrProfile" }?.second
        val requiredAssetAlternatives = ocrModelAssetAlternativesByFlavor[flavorName]
        if (flavorName != null && requiredAssetAlternatives != null) {
            val variantName = variant.name.uppercaseFirst()
            val validateAssets = tasks.register("validate${variantName}PpOcrV4Assets") {
                group = "verification"
                description = "Validates PP-OCRv4 model assets for the ${variant.name} variant."
                inputs.files(requiredAssetAlternatives.flatten().map { rootProject.layout.projectDirectory.file(it) })

                doLast {
                    val missing = requiredAssetAlternatives.filter { alternatives ->
                        alternatives.none { assetPath ->
                            rootProject.layout.projectDirectory.file(assetPath).asFile.let { it.isFile && it.length() > 0L }
                        }
                    }
                    if (missing.isNotEmpty()) {
                        val message = buildString {
                            appendLine("Missing PP-OCRv4 model assets for ${variant.name}.")
                            appendLine("Run:")
                            appendLine("  python scripts\\prepare_ppocrv4_assets.py --profile ${cliProfileForFlavor(flavorName)}")
                            appendLine("Missing asset alternatives:")
                            missing.forEach { alternatives ->
                                appendLine(alternatives.joinToString(prefix = "  - ", separator = " OR "))
                            }
                        }
                        throw GradleException(message)
                    }
                }
            }
            tasks.matching { it.name == "merge${variantName}Assets" }.configureEach {
                dependsOn(validateAssets)
            }
        }

        variant.outputs.forEach { output ->
            val architecture = output.filters.find {
                it.filterType == FilterConfiguration.FilterType.ABI
            }?.identifier ?: "universal"
            val outputFileNameProperty = output.javaClass.methods.firstOrNull {
                it.name == "getOutputFileName" && it.parameterTypes.isEmpty()
            }?.invoke(output) as? Property<*>

            @Suppress("UNCHECKED_CAST")
            (outputFileNameProperty as? Property<String>)?.set(
                output.versionName.map { versionName ->
                    val version = versionName.replace("\\s".toRegex(), "-")
                    val extension = utils.FILE_EXTENSION_APK
                    "${rootProject.name}-v$version-$architecture.$extension".lowercase()
                }
            )
        }
    }
}

dependencies {

    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.2.21")
    implementation("org.jetbrains.kotlin:kotlin-parcelize-runtime:2.2.21")

    implementation("org.jetbrains:annotations:26.0.2")

    implementation(files("$rootDir/libs/common-plugin-api.aar"))

    implementation(files("$rootDir/libs/paddle-ocr-api.aar"))

    implementation(project(":libs:ppocrv4-plugin-runtime"))
    implementation(project(":libs:ppocr-android-sdk"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")
    implementation(libs.core.ktx)
    implementation(libs.annotation.jvm)
}

tasks {
    withType(JavaCompile::class.java) {
        options.encoding = "UTF-8"
    }

    register<Sync>("appendDigestToReleasedFiles") {
        val ext = utils.FILE_EXTENSION_APK
        val src = fileTree(projectDir) {
            include("$buildTypeRelease/*.$ext")
            include("*/$buildTypeRelease/*.$ext")
        }
        val dst = layout.projectDirectory.dir("${buildTypeRelease}s")

        from(src)
        into(dst)
        includeEmptyDirs = false
        duplicatesStrategy = DuplicatesStrategy.FAIL

        eachFile {
            val digest = utils.digestCRC32(file)
            val digestedName = "${name.removeSuffix(".$ext")}-$digest.$ext"
            relativePath = RelativePath(true, digestedName)
        }

        doLast { println("Destination: ${dst.asFile}") }
    }
}

extra {
    versions.handleIfNeeded(project, listOf(buildTypeDebug, buildTypeRelease))
}
