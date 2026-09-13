******

### Historique Des Versions

******

# v1.0.3

###### 2026/09/13

* `Correction` Les informations de version et d'ABI du centre des plugins correspondent à l'APK installé
* `Correction` Les images encodées sont limitées à 64 MiB avec prise en charge des fichiers et des tubes
* `Amelioration` Validation des versions, signatures et variantes complètes des APK avant la création des fichiers à télécharger
* `Amelioration` Les images peuvent contenir jusqu'à 16777216 pixels; les tampons bruts sont limités à 64 MiB

# v1.0.2

###### 2026/09/12

* `Correction` Correction du plantage du moteur à l'initialisation (SIGSEGV) sur les appareils à pages de 16 Ko : la `libc++_shared.so` embarquée est désormais la version compilée avec le NDK r28.2, dont le segment RELRO ne partage plus de page avec des données inscriptibles (arm64-v8a, armeabi-v7a)
* `Correction` Correction des modèles de reconnaissance volumineux renvoyant silencieusement un résultat vide après un `OutOfMemoryError` : les modèles sont copiés une seule fois dans le stockage privé de l'application et mappés en mémoire par ONNX Runtime au lieu d'être lus dans le tas Java
* `Correction` Échecs de compilation avec certaines combinaisons Gradle/AGP dus aux sources Kotlin non compilées ou aux définitions en double de `WakeActivity`
* `Amelioration` Bibliothèque native OpenCV 4.8.0 synchronisée avec la recompilation NDK r28c (Clang 19.0.1) (donneur : AutoJs6-Plugin-OpenCV) ; `libopencv_java4.so` des 4 ABI conserve l'alignement `PT_LOAD` de 16 Ko et embarque un manifeste de provenance

# v1.0.1

###### 2026/09/11

* `Amelioration` Vérification à la compilation de l'alignement des pages de 16 KB des bibliothèques natives 64 bits, avec contrôle du contrat manifest et rapports JSON

# v1.0.0

###### 2026/09/01

* `Ajout` Service de plugin Paddle OCR PP-OCRv4 avec moteur `paddle-ocr` et variante `v4`
* `Ajout` Trois profils OCR: `mobile`, `server` et `mobile-en`, couvrant le modele general mobile, le modele serveur de precision plus elevee et le modele de reconnaissance anglais
* `Ajout` Decouverte du plugin via `org.autojs.plugin.PADDLE_OCR`, avec appels `recognizeText` et `detect`
* `Ajout` Sortie OCR avec texte, confiance, limites rectangulaires, points quadrilateres et informations de temps
* `Ajout` Script de preparation des modeles `scripts/prepare_ppocrv4_assets.py`, capable de telecharger et convertir les modeles PP-OCRv4 en ONNX
* `Ajout` Metadonnees et instructions du plugin localisees en espagnol/francais/russe/arabe/japonais/coreen/anglais/chinois simplifie/chinois traditionnel de Hong Kong/chinois traditionnel de Taiwan
* `Ajout` Sources JSON et generation par script pour README et CHANGELOG
* `Correction` Le plugin ne pouvait pas être activé depuis le centre de plugins après son installation sur certains systèmes
* `Amelioration` Assets de modele separes par flavor, avec partage du modele de detection entre `mobile` et `mobile-en` afin que les APK ne contiennent pas de modeles de reconnaissance inutiles
* `Amelioration` Validation des fichiers `inference.onnx` et `inference.yml` requis pendant le build, avec commandes de preparation correspondantes si les assets manquent
* `Amelioration` Les noms des APK Release incluent version, profil et variante ABI, avec sorties `arm64-v8a`, `armeabi-v7a` et `universal`
* `Amelioration` Uniformiser la mise en page du README et la gestion des versions de la plateforme Gradle
* `Dependance` Integration de `common-plugin-api.aar`, `paddle-ocr-api.aar`, PP-OCRv4 runtime, Paddle OCR Android SDK, OpenCV 4.8.0 et ONNX Runtime
