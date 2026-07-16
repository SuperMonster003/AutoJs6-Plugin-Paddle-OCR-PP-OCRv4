# v1.0.0

###### 2026/07/17

* `اضافة` خدمة ملحق Paddle OCR PP-OCRv4 مع المحرك `paddle-ocr` والمتغير `v4`
* `اضافة` ثلاثة OCR profile: `mobile`, `server`, و `mobile-en`, تغطي نموذج mobile العام, ونموذج server الاعلى دقة, ونموذج التعرف الانجليزي
* `اضافة` اكتشاف الملحق عبر `org.autojs.plugin.PADDLE_OCR`, مع دعمي `recognizeText` و `detect`
* `اضافة` مخرجات OCR مع النص, والثقة, والحدود المستطيلة, ونقاط الرباعي, ومعلومات الوقت
* `اضافة` سكربت تحضير النماذج `scripts/prepare_ppocrv4_assets.py`, ويمكنه تنزيل نماذج PP-OCRv4 وتحويلها الى ONNX
* `اضافة` بيانات وتعليمات الملحق مترجمة للاسبانية/الفرنسية/الروسية/العربية/اليابانية/الكورية/الانجليزية/الصينية المبسطة/الصينية التقليدية لهونغ كونغ/الصينية التقليدية لتايوان
* `اضافة` مصادر JSON وتدفق التوليد بالسكربت ل README و CHANGELOG
* `تحسين` تقسيم model assets حسب flavor, مع مشاركة نموذج الاكتشاف بين `mobile` و `mobile-en` حتى لا تحمل ملفات APK نماذج تعرف غير مرتبطة
* `تحسين` التحقق من ملفات `inference.onnx` و `inference.yml` المطلوبة اثناء البناء, مع عرض اوامر التحضير المناسبة عند فقدان assets
* `تحسين` تتضمن اسماء APK Release رقم version و profile و ABI variant, مع مخرجات `arm64-v8a`, `armeabi-v7a`, و `universal`
* `اعتماد` دمج `common-plugin-api.aar`, و `paddle-ocr-api.aar`, و PP-OCRv4 runtime, و Paddle OCR Android SDK, و OpenCV 4.8.0, و ONNX Runtime
