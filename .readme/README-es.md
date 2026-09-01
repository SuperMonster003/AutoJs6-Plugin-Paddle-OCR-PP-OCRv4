<!--suppress HtmlDeprecatedAttribute, HttpUrlsUsage -->

<div align="center">
  <p>
    <picture>
      <img src="https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/app/src/main/res/mipmap/ic_launcher.png?raw=true" alt="autojs6-plugin-paddle-ocr-pp-ocrv4-ic-launcher" border="0" width="128" />
    </picture>
  </p>

  <p>Complemento Paddle OCR PP-OCRv4 para reconocimiento de texto en Android</p>

  <p>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/releases"><img alt="GitHub release (latest by date)" src="https://img.shields.io/github/v/release/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4?label=Release"/></a>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/issues"><img alt="GitHub closed issues" src="https://img.shields.io/github/issues/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4?color=A24232&label=Issues"/></a>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/LICENSE"><img alt="GitHub License" src="https://img.shields.io/github/license/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4?color=534BAE&label=License"/></a>
  </p>
</div>

******

### Idiomas (Languages)

******

README.md esta disponible en los siguientes idiomas:

- [简体中文 [zh-Hans]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-zh-Hans.md)
- [繁體中文 (香港) [zh-Hant-HK]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-zh-Hant-HK.md)
- [繁體中文 (台灣) [zh-Hant-TW]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-zh-Hant-TW.md)
- [English [en]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-en.md)
- [Français [fr]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-fr.md)
- Español [es] # actual
- [日本語 [ja]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-ja.md)
- [한국어 [ko]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-ko.md)
- [Русский [ru]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-ru.md)
- [العربية [ar]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-ar.md)

******

### Introduccion

******

El complemento AutoJs6 Paddle OCR PP-OCRv4 proporciona OCR local para AutoJs6 basado en Baidu PaddleOCR, con perfiles de modelo PP-OCRv4 Mobile, Server y Mobile EN.

******

### Funciones

******

- Proporciona el servicio de complemento `paddle-ocr`, descubierto con la accion `org.autojs.plugin.PADDLE_OCR`.
- Proporciona tres ID de complemento: `paddle-ocr-pp-ocrv4-mobile`, `paddle-ocr-pp-ocrv4-server`, `paddle-ocr-pp-ocrv4-mobile-en`.
- Admite deteccion y reconocimiento de texto, devolviendo texto, confianza, limites rectangulares y puntos de cuadrilatero.
- Admite entrada raw image e informa capacidades como `modelFamily`, `modelProfile`, `language` y ABI compatibles.
- Los metadatos del complemento, las instrucciones, README y CHANGELOG estan localizados en espanol/frances/ruso/arabe/japones/coreano/ingles/chino simplificado/chino tradicional de Hong Kong/chino tradicional de Taiwan.

******

### Perfiles

******

- `mobile`: Perfil predeterminado recomendado para Android, usa modelos PP-OCRv4 mobile de deteccion y reconocimiento.
- `server`: Perfil de mayor precision, usa modelos PP-OCRv4 server de deteccion y reconocimiento para dispositivos de mayor rendimiento.
- `mobile-en`: Perfil para ingles y numeros, comparte el modelo de deteccion mobile con un modelo de reconocimiento ingles.

ID de complemento:

```text
paddle-ocr-pp-ocrv4-mobile
paddle-ocr-pp-ocrv4-server
paddle-ocr-pp-ocrv4-mobile-en
```

******

### Preparar Modelos

******

El repositorio no incluye directamente archivos de modelo PP-OCRv4. Ejecuta el script de preparacion para descargar modelos oficiales Paddle de inferencia estatica y convertirlos a ONNX, o usa paquetes locales con `--source-dir`:

```powershell
python scripts\prepare_ppocrv4_assets.py --profile mobile
python scripts\prepare_ppocrv4_assets.py --profile server
python scripts\prepare_ppocrv4_assets.py --profile mobile-en
python scripts\prepare_ppocrv4_assets.py --profile all
```

De forma predeterminada, los assets se escriben por flavor de Android para que los APK no incluyan modelos no relacionados:

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

### Uso

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

### Historial De Versiones

******

# v1.0.0

###### 2026/09/01

* `Nuevo` Servicio de complemento Paddle OCR PP-OCRv4 con motor `paddle-ocr` y variante `v4`
* `Nuevo` Tres perfiles OCR: `mobile`, `server` y `mobile-en`, cubriendo el modelo general mobile, el modelo server de mayor precision y el modelo de reconocimiento ingles
* `Nuevo` Descubrimiento del complemento mediante `org.autojs.plugin.PADDLE_OCR`, con llamadas `recognizeText` y `detect`
* `Nuevo` Salida OCR con texto, confianza, limites rectangulares, puntos de cuadrilatero e informacion de tiempos
* `Nuevo` Script de preparacion de modelos `scripts/prepare_ppocrv4_assets.py`, capaz de descargar y convertir modelos PP-OCRv4 a ONNX
* `Nuevo` Metadatos e instrucciones del complemento localizados en espanol/frances/ruso/arabe/japones/coreano/ingles/chino simplificado/chino tradicional de Hong Kong/chino tradicional de Taiwan
* `Nuevo` Fuente JSON y flujo de generacion por script para README y CHANGELOG
* `Correccion` El complemento no se podía activar desde el centro de complementos después de instalarlo en algunos sistemas
* `Mejora` Assets de modelo separados por flavor, compartiendo el modelo de deteccion entre `mobile` y `mobile-en` para que los APK no incluyan modelos de reconocimiento no relacionados
* `Mejora` Validacion de los archivos `inference.onnx` e `inference.yml` requeridos durante el build, con comandos de preparacion correspondientes cuando faltan assets
* `Mejora` Los nombres de APK Release incluyen version, perfil y variante ABI, con salidas `arm64-v8a`, `armeabi-v7a` y `universal`
* `Mejora` Unificar el diseño del README y la gestión de versiones de la plataforma Gradle
* `Dependencia` Integracion de `common-plugin-api.aar`, `paddle-ocr-api.aar`, PP-OCRv4 runtime, Paddle OCR Android SDK, OpenCV 4.8.0 y ONNX Runtime

##### Para mas historial

* [Historial completo](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/app/src/main/assets/doc/CHANGELOG-es.md)

******

### Build

******

```powershell
.\gradlew.bat :app:assembleMobileRelease
.\gradlew.bat :app:assembleServerRelease
.\gradlew.bat :app:assembleMobileEnRelease
```

Los parametros de build provienen principalmente de `version.properties`, mientras que la app usa actualmente min SDK 26 y target SDK 36.

El build de Gradle valida los archivos `inference.onnx` e `inference.yml` requeridos antes de fusionar assets, y muestra el comando de preparacion correspondiente si falta algun archivo.

******

### Estructura De Recursos

******

```text
.readme/lang_*.json
.changelog/lang_*.json
.python/generate_markdown.py
app/src/main/assets/doc/CHANGELOG*.md
app/src/main/res/values-*/strings.xml
app/src/main/res/raw-*/plugin_instruction.md
```

`strings.xml` proporciona descripciones localizadas del complemento; `plugin_instruction.md` proporciona instrucciones del complemento para el host. README y CHANGELOG se generan con `.python/generate_markdown.py` desde archivos fuente JSON.

******

### Enlaces

******

- Documentacion OCR de AutoJs6: https://docs.autojs6.com/#/ocr
- Proyecto oficial PaddleOCR: https://github.com/PaddlePaddle/PaddleOCR
- Proyecto Paddle2ONNX: https://github.com/PaddlePaddle/Paddle2ONNX
