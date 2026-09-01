<!--suppress HtmlDeprecatedAttribute, HttpUrlsUsage -->

<div align="center">
  <p>
    <picture>
      <img src="https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/app/src/main/res/mipmap/ic_launcher.png?raw=true" alt="autojs6-plugin-paddle-ocr-pp-ocrv4-ic-launcher" border="0" width="128" />
    </picture>
  </p>

  <p>Android 텍스트 인식용 Paddle OCR PP-OCRv4 플러그인</p>

  <p>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/releases"><img alt="GitHub release (latest by date)" src="https://img.shields.io/github/v/release/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4?label=Release"/></a>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/issues"><img alt="GitHub closed issues" src="https://img.shields.io/github/issues/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4?color=A24232&label=Issues"/></a>
    <a href="https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/LICENSE"><img alt="GitHub License" src="https://img.shields.io/github/license/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4?color=534BAE&label=License"/></a>
  </p>
</div>

******

### 언어 (Languages)

******

README.md 는 다음 언어를 지원합니다:

- [简体中文 [zh-Hans]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-zh-Hans.md)
- [繁體中文 (香港) [zh-Hant-HK]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-zh-Hant-HK.md)
- [繁體中文 (台灣) [zh-Hant-TW]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-zh-Hant-TW.md)
- [English [en]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-en.md)
- [Français [fr]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-fr.md)
- [Español [es]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-es.md)
- [日本語 [ja]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-ja.md)
- 한국어 [ko] # 현재
- [Русский [ru]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-ru.md)
- [العربية [ar]](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/.readme/README-ar.md)

******

### 소개

******

AutoJs6 Paddle OCR PP-OCRv4 플러그인은 Baidu PaddleOCR 기반 로컬 OCR 기능을 AutoJs6 에 제공하며, PP-OCRv4 Mobile, Server, Mobile EN 세 가지 모델 프로필을 지원합니다.

******

### 기능

******

- `org.autojs.plugin.PADDLE_OCR` action 으로 발견되는 `paddle-ocr` 플러그인 서비스를 제공합니다.
- 세 가지 플러그인 ID 를 제공합니다: `paddle-ocr-pp-ocrv4-mobile`, `paddle-ocr-pp-ocrv4-server`, `paddle-ocr-pp-ocrv4-mobile-en`.
- 텍스트 감지와 텍스트 인식을 지원하고 텍스트, 신뢰도, 사각형 경계, 네 점 좌표를 반환합니다.
- raw image 입력을 지원하고 `modelFamily`, `modelProfile`, `language`, 지원 ABI 같은 기능 정보를 보고합니다.
- 플러그인 메타데이터, 사용 설명, README, CHANGELOG 는 스페인어/프랑스어/러시아어/아랍어/일본어/한국어/영어/중국어 간체/홍콩 번체 중국어/대만 번체 중국어로 현지화됩니다.

******

### 프로필

******

- `mobile`: 권장 Android 기본 프로필이며 PP-OCRv4 mobile 감지 및 인식 모델을 사용합니다.
- `server`: 더 높은 정확도의 프로필이며 고성능 기기용 PP-OCRv4 server 감지 및 인식 모델을 사용합니다.
- `mobile-en`: 영어와 숫자 인식 프로필이며 mobile 감지 모델을 공유하고 영어 인식 모델을 사용합니다.

플러그인 ID:

```text
paddle-ocr-pp-ocrv4-mobile
paddle-ocr-pp-ocrv4-server
paddle-ocr-pp-ocrv4-mobile-en
```

******

### 모델 준비

******

저장소는 PP-OCRv4 모델 파일을 직접 포함하지 않습니다. 준비 스크립트를 실행해 공식 Paddle 정적 추론 모델을 내려받고 ONNX 로 변환하거나, `--source-dir` 로 로컬 모델 패키지를 사용할 수 있습니다:

```powershell
python scripts\prepare_ppocrv4_assets.py --profile mobile
python scripts\prepare_ppocrv4_assets.py --profile server
python scripts\prepare_ppocrv4_assets.py --profile mobile-en
python scripts\prepare_ppocrv4_assets.py --profile all
```

기본적으로 assets 는 Android flavor 별로 작성되어 APK 가 관련 없는 모델을 포함하지 않게 합니다:

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

### 사용 예

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

### 릴리스 기록

******

# v1.0.0

###### 2026/09/01

* `신규` 엔진 `paddle-ocr`, 변형 `v4` 를 사용하는 Paddle OCR PP-OCRv4 플러그인 서비스
* `신규` 세 가지 OCR profile: `mobile`, `server`, `mobile-en`, 각각 mobile 범용 모델, 고정확도 server 모델, 영어 인식 모델에 대응
* `신규` `org.autojs.plugin.PADDLE_OCR` 를 통한 플러그인 발견과 `recognizeText`, `detect` 호출 지원
* `신규` OCR 출력으로 텍스트, 신뢰도, 사각형 경계, 네 점 좌표, 처리 시간 정보를 반환
* `신규` PP-OCRv4 모델을 다운로드하고 ONNX 로 변환할 수 있는 모델 준비 스크립트 `scripts/prepare_ppocrv4_assets.py`
* `신규` 스페인어/프랑스어/러시아어/아랍어/일본어/한국어/영어/중국어 간체/홍콩 번체 중국어/대만 번체 중국어 플러그인 메타데이터와 사용 설명
* `신규` README 와 CHANGELOG 의 JSON 소스 및 스크립트 생성 흐름
* `수정` 일부 시스템에서 설치 후 플러그인 센터를 통해 플러그인을 활성화할 수 없는 문제
* `개선` flavor 별로 모델 assets 를 분리하고 `mobile` 과 `mobile-en` 이 감지 모델을 공유하여 APK 가 관련 없는 인식 모델을 포함하지 않도록 변경
* `개선` 빌드 중 필요한 `inference.onnx` 와 `inference.yml` 을 검증하고 assets 가 없으면 해당 준비 명령을 표시
* `개선` Release APK 이름에 version, profile, ABI variant 를 포함하며 `arm64-v8a`, `armeabi-v7a`, `universal` 출력을 지원
* `개선` README 레이아웃과 Gradle 플랫폼 버전 관리 방식을 통일
* `의존성` `common-plugin-api.aar`, `paddle-ocr-api.aar`, PP-OCRv4 runtime, Paddle OCR Android SDK, OpenCV 4.8.0, ONNX Runtime 통합

##### 더 많은 릴리스 기록

* [전체 릴리스 기록](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/app/src/main/assets/doc/CHANGELOG-ko.md)

******

### 빌드

******

```powershell
.\gradlew.bat :app:assembleMobileRelease
.\gradlew.bat :app:assembleServerRelease
.\gradlew.bat :app:assembleMobileEnRelease
```

빌드 매개변수는 주로 `version.properties` 에서 가져오며, app 은 현재 min SDK 26 과 target SDK 36 을 사용합니다.

Gradle 빌드는 assets 병합 전에 필요한 `inference.onnx` 와 `inference.yml` 파일을 검증하며, 파일이 없으면 해당 모델 준비 명령을 표시합니다.

******

### 리소스 구조

******

```text
.readme/lang_*.json
.changelog/lang_*.json
.python/generate_markdown.py
app/src/main/assets/doc/CHANGELOG*.md
app/src/main/res/values-*/strings.xml
app/src/main/res/raw-*/plugin_instruction.md
```

`strings.xml` 은 현지화된 플러그인 설명을 제공하고; `plugin_instruction.md` 는 호스트 측에 표시되는 플러그인 사용 설명을 제공합니다. README 와 CHANGELOG 는 JSON 소스 파일에서 `.python/generate_markdown.py` 로 생성됩니다.

******

### 링크

******

- AutoJs6 OCR 문서: https://docs.autojs6.com/#/ocr
- PaddleOCR 공식 프로젝트: https://github.com/PaddlePaddle/PaddleOCR
- Paddle2ONNX 프로젝트: https://github.com/PaddlePaddle/Paddle2ONNX
