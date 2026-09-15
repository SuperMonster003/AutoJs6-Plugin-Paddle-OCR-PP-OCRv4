<!--suppress HtmlDeprecatedAttribute, HttpUrlsUsage -->

<div align="center">
  <p>
    <picture>
      <img src="https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/app/src/main/res/mipmap/ic_launcher.png?raw=true" alt="autojs6-plugin-paddle-ocr-pp-ocrv4-ic-launcher" border="0" width="128" />
    </picture>
  </p>

  <p>Плагин Paddle OCR PP-OCRv4 для распознавания текста на Android</p>

  <p>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/releases"><img alt="GitHub release (latest by date)" src="https://img.shields.io/github/v/release/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4?label=Release"/></a>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/issues"><img alt="GitHub closed issues" src="https://img.shields.io/github/issues/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4?color=A24232&label=Issues"/></a>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/LICENSE"><img alt="GitHub License" src="https://img.shields.io/github/license/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4?color=534BAE&label=License"/></a>
  </p>
</div>

******

### Языки (Languages)

******

README.md доступен на следующих языках:

- [简体中文 [zh-Hans]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-zh-Hans.md)
- [繁體中文 (香港) [zh-Hant-HK]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-zh-Hant-HK.md)
- [繁體中文 (台灣) [zh-Hant-TW]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-zh-Hant-TW.md)
- [English [en]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-en.md)
- [Français [fr]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-fr.md)
- [Español [es]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-es.md)
- [日本語 [ja]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-ja.md)
- [한국어 [ko]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-ko.md)
- Русский [ru] # текущий
- [العربية [ar]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-ar.md)

******

### Введение

******

Плагин AutoJs6 Paddle OCR PP-OCRv4 предоставляет локальный OCR для AutoJs6 на основе Baidu PaddleOCR, с профилями моделей PP-OCRv4 Mobile, Server и Mobile EN.

******

### Возможности

******

- Предоставляет сервис плагина `paddle-ocr`, обнаруживаемый через action `org.autojs.plugin.PADDLE_OCR`.
- Предоставляет три ID плагина: `paddle-ocr-pp-ocrv4-mobile`, `paddle-ocr-pp-ocrv4-server`, `paddle-ocr-pp-ocrv4-mobile-en`.
- Поддерживает обнаружение и распознавание текста, возвращая текст, уверенность, прямоугольные границы и четыре точки.
- Поддерживает ввод raw image и сообщает возможности, такие как `modelFamily`, `modelProfile`, `language` и поддерживаемые ABI.
- Метаданные плагина, инструкции, README и CHANGELOG локализованы на испанский/французский/русский/арабский/японский/корейский/английский/упрощенный китайский/гонконгский традиционный китайский/тайваньский традиционный китайский.
- Изображения могут содержать до 16777216 пикселей; размер буферов необработанных изображений ограничен 64 MiB
- Размер закодированного изображения ограничен 64 MiB с поддержкой файловых дескрипторов и каналов

******

### Профили

******

- `mobile`: Рекомендуемый профиль Android по умолчанию, использует модели обнаружения и распознавания PP-OCRv4 mobile.
- `server`: Профиль с более высокой точностью, использует модели обнаружения и распознавания PP-OCRv4 server для более производительных устройств.
- `mobile-en`: Профиль для английского и чисел, использует общую модель обнаружения mobile и английскую модель распознавания.

ID плагинов:

```text
paddle-ocr-pp-ocrv4-mobile
paddle-ocr-pp-ocrv4-server
paddle-ocr-pp-ocrv4-mobile-en
```

******

### Подготовка Моделей

******

Репозиторий не поставляет файлы моделей PP-OCRv4 напрямую. Запустите скрипт подготовки, чтобы скачать официальные статические модели вывода Paddle и преобразовать их в ONNX, либо используйте локальные пакеты моделей с `--source-dir`:

```powershell
python scripts\prepare_ppocrv4_assets.py --profile mobile
python scripts\prepare_ppocrv4_assets.py --profile server
python scripts\prepare_ppocrv4_assets.py --profile mobile-en
python scripts\prepare_ppocrv4_assets.py --profile all
```

По умолчанию assets записываются по flavor Android, чтобы APK не содержали лишние модели:

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

### Пример

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

### История Выпусков

******

# v1.0.3

###### 2026/09/13

* `Исправлено` Информация о версии и ABI в центре плагинов соответствует установленному APK
* `Исправлено` Размер закодированного изображения ограничен 64 MiB с поддержкой файловых дескрипторов и каналов
* `Исправлено` Даты версий используют единый английский формат
* `Улучшено` Проверка версий, подписей и полного набора вариантов APK перед подготовкой файлов для загрузки
* `Улучшено` Изображения могут содержать до 16777216 пикселей; размер буферов необработанных изображений ограничен 64 MiB
* `Улучшено` Расширение набора нативных ABI и метаданных плагина до arm64-v8a, armeabi-v7a, x86 и x86_64 с согласованными универсальными и отдельными APK для каждой ABI

# v1.0.2

###### 2026/09/12

* `Исправлено` Исправлен сбой движка при инициализации (SIGSEGV) на устройствах со страницами 16 КБ: встроенная `libc++_shared.so` теперь собрана NDK r28.2, и её сегмент RELRO больше не делит страницу с записываемыми данными (arm64-v8a, armeabi-v7a)
* `Исправлено` Исправлен молчаливый пустой результат для крупных моделей распознавания после `OutOfMemoryError`: модели один раз копируются в приватное хранилище приложения и отображаются в память ONNX Runtime вместо чтения в кучу Java
* `Исправлено` Сбои сборки при некоторых сочетаниях Gradle/AGP из-за некомпилируемых исходников Kotlin или повторных определений `WakeActivity`
* `Улучшено` Нативная библиотека OpenCV 4.8.0 синхронизирована с пересборкой NDK r28c (Clang 19.0.1) (донор: AutoJs6-Plugin-OpenCV); `libopencv_java4.so` для всех 4 ABI сохраняет выравнивание `PT_LOAD` 16 КБ и поставляется с манифестом provenance

# v1.0.1

###### 2026/09/11

* `Улучшено` Проверка выравнивания страниц 16 KB для 64-битных нативных библиотек при сборке, включая контракт manifest и отчеты JSON

##### Более полная история выпусков

* [Полная история выпусков](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/app/src/main/assets/doc/CHANGELOG-ru.md)

******

### Сборка

******

```powershell
.\gradlew.bat :app:assembleMobileRelease
.\gradlew.bat :app:assembleServerRelease
.\gradlew.bat :app:assembleMobileEnRelease
```

Параметры сборки в основном берутся из `version.properties`, а app сейчас использует min SDK 26 и target SDK 36.

Сборка Gradle проверяет необходимые файлы `inference.onnx` и `inference.yml` перед слиянием assets, и показывает подходящую команду подготовки моделей, если файл отсутствует.

******

### Структура Ресурсов

******

```text
.readme/lang_*.json
.changelog/lang_*.json
.python/generate_markdown.py
app/src/main/assets/doc/CHANGELOG*.md
app/src/main/res/values-*/strings.xml
app/src/main/res/raw-*/plugin_instruction.md
```

`strings.xml` предоставляет локализованные описания плагина; `plugin_instruction.md` предоставляет инструкции плагина для отображения на стороне хоста. README и CHANGELOG создаются `.python/generate_markdown.py` из исходных JSON файлов.

******

### Ссылки

******

- Документация AutoJs6 OCR: https://docs.autojs6.com/#/ocr
- Официальный проект PaddleOCR: https://github.com/PaddlePaddle/PaddleOCR
- Проект Paddle2ONNX: https://github.com/PaddlePaddle/Paddle2ONNX


[16 KB page alignment and build verification](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/docs/16kb.md)
