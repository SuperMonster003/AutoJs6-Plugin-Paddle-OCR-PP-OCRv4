# v1.0.0

###### 2026/07/17

* `Nuevo` Servicio de complemento Paddle OCR PP-OCRv4 con motor `paddle-ocr` y variante `v4`
* `Nuevo` Tres perfiles OCR: `mobile`, `server` y `mobile-en`, cubriendo el modelo general mobile, el modelo server de mayor precision y el modelo de reconocimiento ingles
* `Nuevo` Descubrimiento del complemento mediante `org.autojs.plugin.PADDLE_OCR`, con llamadas `recognizeText` y `detect`
* `Nuevo` Salida OCR con texto, confianza, limites rectangulares, puntos de cuadrilatero e informacion de tiempos
* `Nuevo` Script de preparacion de modelos `scripts/prepare_ppocrv4_assets.py`, capaz de descargar y convertir modelos PP-OCRv4 a ONNX
* `Nuevo` Metadatos e instrucciones del complemento localizados en espanol/frances/ruso/arabe/japones/coreano/ingles/chino simplificado/chino tradicional de Hong Kong/chino tradicional de Taiwan
* `Nuevo` Fuente JSON y flujo de generacion por script para README y CHANGELOG
* `Mejora` Assets de modelo separados por flavor, compartiendo el modelo de deteccion entre `mobile` y `mobile-en` para que los APK no incluyan modelos de reconocimiento no relacionados
* `Mejora` Validacion de los archivos `inference.onnx` e `inference.yml` requeridos durante el build, con comandos de preparacion correspondientes cuando faltan assets
* `Mejora` Los nombres de APK Release incluyen version, perfil y variante ABI, con salidas `arm64-v8a`, `armeabi-v7a` y `universal`
* `Dependencia` Integracion de `common-plugin-api.aar`, `paddle-ocr-api.aar`, PP-OCRv4 runtime, Paddle OCR Android SDK, OpenCV 4.8.0 y ONNX Runtime
