<!--suppress HtmlDeprecatedAttribute, HttpUrlsUsage -->

<div align="center">
  <p>
    <picture>
      <img src="https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/app/src/main/res/mipmap/ic_launcher.png?raw=true" alt="autojs6-plugin-paddle-ocr-pp-ocrv4-ic-launcher" border="0" width="128" />
    </picture>
  </p>

  <p>ملحق Paddle OCR PP-OCRv4 للتعرف على النص في Android</p>

  <p>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/releases"><img alt="GitHub release (latest by date)" src="https://img.shields.io/github/v/release/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4?label=Release"/></a>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/issues"><img alt="GitHub closed issues" src="https://img.shields.io/github/issues/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4?color=A24232&label=Issues"/></a>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/LICENSE"><img alt="GitHub License" src="https://img.shields.io/github/license/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4?color=534BAE&label=License"/></a>
  </p>
</div>

******

### اللغات (Languages)

******

يتوفر README.md باللغات التالية:

- [简体中文 [zh-Hans]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-zh-Hans.md)
- [繁體中文 (香港) [zh-Hant-HK]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-zh-Hant-HK.md)
- [繁體中文 (台灣) [zh-Hant-TW]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-zh-Hant-TW.md)
- [English [en]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-en.md)
- [Français [fr]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-fr.md)
- [Español [es]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-es.md)
- [日本語 [ja]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-ja.md)
- [한국어 [ko]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-ko.md)
- [Русский [ru]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-ru.md)
- العربية [ar] # الحالي

******

### مقدمة

******

يوفر ملحق AutoJs6 Paddle OCR PP-OCRv4 قدرة OCR محلية ل AutoJs6 مبنية على Baidu PaddleOCR, مع ملفات تعريف النماذج PP-OCRv4 Mobile و Server و Mobile EN.

******

### الميزات

******

- يوفر خدمة الملحق `paddle-ocr`, ويتم اكتشافها بواسطة action `org.autojs.plugin.PADDLE_OCR`.
- يوفر ثلاثة معرفات للملحق: `paddle-ocr-pp-ocrv4-mobile`, `paddle-ocr-pp-ocrv4-server`, `paddle-ocr-pp-ocrv4-mobile-en`.
- يدعم اكتشاف النص والتعرف على النص, ويعيد النص, والثقة, والحدود المستطيلة, ونقاط الرباعي.
- يدعم ادخال raw image ويبلغ عن قدرات مثل `modelFamily`, `modelProfile`, `language`, و ABI المدعومة.
- بيانات الملحق, والتعليمات, و README, و CHANGELOG مترجمة للاسبانية/الفرنسية/الروسية/العربية/اليابانية/الكورية/الانجليزية/الصينية المبسطة/الصينية التقليدية لهونغ كونغ/الصينية التقليدية لتايوان.

******

### ملفات التعريف

******

- `mobile`: ملف التعريف الافتراضي الموصى به ل Android, يستخدم نماذج PP-OCRv4 mobile للاكتشاف والتعرف.
- `server`: ملف تعريف بدقة اعلى, يستخدم نماذج PP-OCRv4 server للاكتشاف والتعرف على الاجهزة الاعلى اداء.
- `mobile-en`: ملف تعريف للتعرف على الانجليزية والارقام, يشارك نموذج اكتشاف mobile ويستخدم نموذج تعرف انجليزي.

معرفات الملحق:

```text
paddle-ocr-pp-ocrv4-mobile
paddle-ocr-pp-ocrv4-server
paddle-ocr-pp-ocrv4-mobile-en
```

******

### تحضير النماذج

******

لا يحتوي المستودع على ملفات نموذج PP-OCRv4 مباشرة. شغل سكربت التحضير لتنزيل نماذج Paddle الرسمية للاستدلال الثابت وتحويلها الى ONNX, او استخدم حزم نماذج محلية مع `--source-dir`:

```powershell
python scripts\prepare_ppocrv4_assets.py --profile mobile
python scripts\prepare_ppocrv4_assets.py --profile server
python scripts\prepare_ppocrv4_assets.py --profile mobile-en
python scripts\prepare_ppocrv4_assets.py --profile all
```

افتراضيا تكتب assets حسب Android flavor حتى لا تحمل ملفات APK نماذج غير مرتبطة:

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

### مثال استخدام

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

### سجل الاصدارات

******

# v1.0.2

###### 2026/09/12

* `اصلاح` إصلاح انهيار المحرك عند التهيئة (SIGSEGV) على الأجهزة ذات صفحات 16 كيلوبايت: أصبحت `libc++_shared.so` المضمنة من بناء NDK r28.2 الذي لم يعد مقطع RELRO فيه يشارك صفحة مع بيانات قابلة للكتابة (arm64-v8a, armeabi-v7a)
* `اصلاح` إصلاح إرجاع نتائج فارغة بصمت لنماذج التعرف الكبيرة بعد `OutOfMemoryError`: تُنسخ أصول النموذج مرة واحدة إلى التخزين الخاص بالتطبيق ويقوم ONNX Runtime بتعيينها في الذاكرة بدلاً من قراءتها في كومة Java
* `اصلاح` تعذر البناء مع بعض توليفات Gradle/AGP بسبب عدم تجميع مصادر Kotlin أو تكرار تعريف `WakeActivity`
* `تحسين` مزامنة مكتبة OpenCV 4.8.0 الأصلية مع إعادة بناء NDK r28c (Clang 19.0.1) (المصدر: AutoJs6-Plugin-OpenCV); تحافظ `libopencv_java4.so` لجميع ABI الأربعة على محاذاة `PT_LOAD` بحجم 16 كيلوبايت وتأتي مع بيان provenance

# v1.0.1

###### 2026/09/11

* `تحسين` التحقق أثناء البناء من محاذاة صفحات 16 KB للمكتبات الأصلية ذات 64 بت, مع فحص عقد manifest وتقارير JSON

# v1.0.0

###### 2026/09/01

* `اضافة` خدمة ملحق Paddle OCR PP-OCRv4 مع المحرك `paddle-ocr` والمتغير `v4`
* `اضافة` ثلاثة OCR profile: `mobile`, `server`, و `mobile-en`, تغطي نموذج mobile العام, ونموذج server الاعلى دقة, ونموذج التعرف الانجليزي
* `اضافة` اكتشاف الملحق عبر `org.autojs.plugin.PADDLE_OCR`, مع دعمي `recognizeText` و `detect`
* `اضافة` مخرجات OCR مع النص, والثقة, والحدود المستطيلة, ونقاط الرباعي, ومعلومات الوقت
* `اضافة` سكربت تحضير النماذج `scripts/prepare_ppocrv4_assets.py`, ويمكنه تنزيل نماذج PP-OCRv4 وتحويلها الى ONNX
* `اضافة` بيانات وتعليمات الملحق مترجمة للاسبانية/الفرنسية/الروسية/العربية/اليابانية/الكورية/الانجليزية/الصينية المبسطة/الصينية التقليدية لهونغ كونغ/الصينية التقليدية لتايوان
* `اضافة` مصادر JSON وتدفق التوليد بالسكربت ل README و CHANGELOG
* `اصلاح` تعذر تنشيط المكون الإضافي من مركز المكونات الإضافية بعد التثبيت على بعض الأنظمة
* `تحسين` تقسيم model assets حسب flavor, مع مشاركة نموذج الاكتشاف بين `mobile` و `mobile-en` حتى لا تحمل ملفات APK نماذج تعرف غير مرتبطة
* `تحسين` التحقق من ملفات `inference.onnx` و `inference.yml` المطلوبة اثناء البناء, مع عرض اوامر التحضير المناسبة عند فقدان assets
* `تحسين` تتضمن اسماء APK Release رقم version و profile و ABI variant, مع مخرجات `arm64-v8a`, `armeabi-v7a`, و `universal`
* `تحسين` توحيد تخطيط README وطريقة إدارة إصدارات منصة Gradle
* `اعتماد` دمج `common-plugin-api.aar`, و `paddle-ocr-api.aar`, و PP-OCRv4 runtime, و Paddle OCR Android SDK, و OpenCV 4.8.0, و ONNX Runtime

##### لمزيد من سجل الاصدارات

* [سجل الاصدارات الكامل](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/app/src/main/assets/doc/CHANGELOG-ar.md)

******

### البناء

******

```powershell
.\gradlew.bat :app:assembleMobileRelease
.\gradlew.bat :app:assembleServerRelease
.\gradlew.bat :app:assembleMobileEnRelease
```

تاتي معلمات البناء اساسا من `version.properties`, بينما يستخدم app حاليا min SDK 26 و target SDK 36.

يتحقق Gradle build من ملفات `inference.onnx` و `inference.yml` المطلوبة قبل دمج assets, ويعرض امر تحضير النموذج المناسب اذا كان اي ملف مفقودا.

******

### هيكل الموارد

******

```text
.readme/lang_*.json
.changelog/lang_*.json
.python/generate_markdown.py
app/src/main/assets/doc/CHANGELOG*.md
app/src/main/res/values-*/strings.xml
app/src/main/res/raw-*/plugin_instruction.md
```

يوفر `strings.xml` اوصاف الملحق المترجمة; ويوفر `plugin_instruction.md` تعليمات الملحق المعروضة في المضيف. يتم توليد README و CHANGELOG بواسطة `.python/generate_markdown.py` من ملفات JSON المصدر.

******

### روابط

******

- وثائق AutoJs6 OCR: https://docs.autojs6.com/#/ocr
- مشروع PaddleOCR الرسمي: https://github.com/PaddlePaddle/PaddleOCR
- مشروع Paddle2ONNX: https://github.com/PaddlePaddle/Paddle2ONNX


[16 KB page alignment and build verification](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/docs/16kb.md)
