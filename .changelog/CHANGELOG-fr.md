******

### Historique Des Versions

******

# v1.0.0

###### 2026/07/17

* `Ajout` Service de plugin Paddle OCR PP-OCRv4 avec moteur `paddle-ocr` et variante `v4`
* `Ajout` Trois profils OCR: `mobile`, `server` et `mobile-en`, couvrant le modele general mobile, le modele serveur de precision plus elevee et le modele de reconnaissance anglais
* `Ajout` Decouverte du plugin via `org.autojs.plugin.PADDLE_OCR`, avec appels `recognizeText` et `detect`
* `Ajout` Sortie OCR avec texte, confiance, limites rectangulaires, points quadrilateres et informations de temps
* `Ajout` Script de preparation des modeles `scripts/prepare_ppocrv4_assets.py`, capable de telecharger et convertir les modeles PP-OCRv4 en ONNX
* `Ajout` Metadonnees et instructions du plugin localisees en espagnol/francais/russe/arabe/japonais/coreen/anglais/chinois simplifie/chinois traditionnel de Hong Kong/chinois traditionnel de Taiwan
* `Ajout` Sources JSON et generation par script pour README et CHANGELOG
* `Amelioration` Assets de modele separes par flavor, avec partage du modele de detection entre `mobile` et `mobile-en` afin que les APK ne contiennent pas de modeles de reconnaissance inutiles
* `Amelioration` Validation des fichiers `inference.onnx` et `inference.yml` requis pendant le build, avec commandes de preparation correspondantes si les assets manquent
* `Amelioration` Les noms des APK Release incluent version, profil et variante ABI, avec sorties `arm64-v8a`, `armeabi-v7a` et `universal`
* `Dependance` Integration de `common-plugin-api.aar`, `paddle-ocr-api.aar`, PP-OCRv4 runtime, Paddle OCR Android SDK, OpenCV 4.8.0 et ONNX Runtime
