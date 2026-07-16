<!--suppress HtmlDeprecatedAttribute, HttpUrlsUsage -->

<div align="center">
  <p>
    <img src="https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/app/src/main/res/mipmap/ic_launcher.png?raw=true" alt="autojs6-plugin-paddle-ocr-pp-ocrv4-ic-launcher" border="0" width="128" />
  </p>

  <p>Plugin Paddle OCR PP-OCRv4 pour la reconnaissance de texte Android</p>

  <p>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/releases"><img alt="GitHub release (latest by date)" src="https://img.shields.io/github/v/release/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4?label=Release"/></a>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/issues"><img alt="GitHub closed issues" src="https://img.shields.io/github/issues/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4?color=A24232&label=Issues"/></a>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/commit/668bfc2f23256c89bbad49590c946a0d3efb9b74"><img alt="Created" src="https://img.shields.io/date/1783168887?color=2e7d32&label=Created"/></a>
    <br>
    <a href="https://developer.android.com/studio/archive"><img alt="Android Studio" src="https://img.shields.io/badge/Android%20Studio-2023.3+-B64FC8"/></a>
    <a href="https://www.jetbrains.com/idea/download/other.html"><img alt="IntelliJ IDEA" src="https://img.shields.io/badge/IntelliJ%20IDEA-2023.3+-EE4677"/></a>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/LICENSE"><img alt="GitHub License" src="https://img.shields.io/github/license/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4?color=534BAE&label=License"/></a>
  </p>
</div>

******

### Langues (Languages)

******

README.md est disponible dans les langues suivantes:

- [简体中文 [zh-Hans]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-zh-Hans.md)
- [繁體中文 (香港) [zh-Hant-HK]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-zh-Hant-HK.md)
- [繁體中文 (台灣) [zh-Hant-TW]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-zh-Hant-TW.md)
- [English [en]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-en.md)
- Français [fr] # actuel
- [Español [es]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-es.md)
- [日本語 [ja]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-ja.md)
- [한국어 [ko]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-ko.md)
- [Русский [ru]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-ru.md)
- [العربية [ar]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-ar.md)

******

### Introduction

******

Le plugin AutoJs6 Paddle OCR PP-OCRv4 fournit une capacite OCR locale pour AutoJs6 basee sur Baidu PaddleOCR, avec les profils de modele PP-OCRv4 Mobile, Server et Mobile EN.

******

### Fonctions

******

- Fournit le service de plugin `paddle-ocr`, decouvert avec l'action `org.autojs.plugin.PADDLE_OCR`.
- Fournit trois ID de plugin: `paddle-ocr-pp-ocrv4-mobile`, `paddle-ocr-pp-ocrv4-server`, `paddle-ocr-pp-ocrv4-mobile-en`.
- Prend en charge la detection et la reconnaissance de texte, avec texte, confiance, limites rectangulaires et points quadrilateres.
- Prend en charge l'entree raw image et annonce des capacites comme `modelFamily`, `modelProfile`, `language` et les ABI pris en charge.
- Les metadonnees du plugin, les instructions, README et CHANGELOG sont localises en espagnol/francais/russe/arabe/japonais/coreen/anglais/chinois simplifie/chinois traditionnel de Hong Kong/chinois traditionnel de Taiwan.

******

### Profils

******

- `mobile`: Profil Android recommande par defaut, avec les modeles de detection et de reconnaissance PP-OCRv4 mobile.
- `server`: Profil de precision plus elevee, avec les modeles de detection et de reconnaissance PP-OCRv4 server pour les appareils plus performants.
- `mobile-en`: Profil pour l'anglais et les nombres, partageant le modele de detection mobile avec un modele de reconnaissance anglais.

ID de plugin:

```text
paddle-ocr-pp-ocrv4-mobile
paddle-ocr-pp-ocrv4-server
paddle-ocr-pp-ocrv4-mobile-en
```

******

### Preparation Des Modeles

******

Le depot ne fournit pas directement les fichiers de modele PP-OCRv4. Executez le script de preparation pour telecharger les modeles Paddle officiels d'inference statique et les convertir en ONNX, ou utilisez des packages locaux avec `--source-dir`:

```powershell
python scripts\prepare_ppocrv4_assets.py --profile mobile
python scripts\prepare_ppocrv4_assets.py --profile server
python scripts\prepare_ppocrv4_assets.py --profile mobile-en
python scripts\prepare_ppocrv4_assets.py --profile all
```

Par defaut, les assets sont ecrits par flavor Android afin que les APK ne contiennent pas de modeles sans rapport:

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

### Exemple

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

##### Pour plus d'historique

* [Historique complet](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.changelog/CHANGELOG-fr.md)

******

### Build

******

```powershell
.\gradlew.bat :app:assembleMobileRelease
.\gradlew.bat :app:assembleServerRelease
.\gradlew.bat :app:assembleMobileEnRelease
```

Les parametres de build proviennent principalement de `version.properties`, tandis que l'app utilise actuellement min SDK 26 et target SDK 36.

Le build Gradle valide les fichiers `inference.onnx` et `inference.yml` requis avant de fusionner les assets, et affiche la commande de preparation correspondante si un fichier manque.

******

### Structure Des Ressources

******

```text
.readme/lang_*.json
.changelog/lang_*.json
.python/generate_markdown.py
app/src/main/assets/doc/CHANGELOG*.md
app/src/main/res/values-*/strings.xml
app/src/main/res/raw-*/plugin_instruction.md
```

`strings.xml` fournit les descriptions localisees du plugin; `plugin_instruction.md` fournit les instructions du plugin cote hote. README et CHANGELOG sont generes par `.python/generate_markdown.py` depuis les fichiers source JSON.

******

### Liens

******

- Documentation OCR AutoJs6: https://docs.autojs6.com/#/ocr
- Projet officiel PaddleOCR: https://github.com/PaddlePaddle/PaddleOCR
- Projet Paddle2ONNX: https://github.com/PaddlePaddle/Paddle2ONNX
