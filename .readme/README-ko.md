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
- 이미지는 최대 16777216픽셀, 원시 이미지 버퍼는 최대 64 MiB까지 지원
- 인코딩된 이미지는 최대 64 MiB이며 파일 디스크립터와 파이프를 지원합니다

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

# v1.0.3

###### 2026/09/13

* `수정` 플러그인 센터의 버전과 ABI 정보가 설치된 APK와 일치
* `수정` 인코딩된 이미지는 최대 64 MiB이며 파일 디스크립터와 파이프를 지원합니다
* `수정` 버전 날짜를 일관된 영어 형식으로 표시
* `개선` 다운로드 파일 생성 전에 릴리스 APK의 버전, 서명 및 전체 변형 구성을 검증
* `개선` 이미지는 최대 16777216픽셀, 원시 이미지 버퍼는 최대 64 MiB까지 지원
* `개선` 네이티브 ABI 패키징과 플러그인 메타데이터를 arm64-v8a, armeabi-v7a, x86, x86_64로 확장하고 범용 APK와 ABI별 APK를 일치시킴

# v1.0.2

###### 2026/09/12

* `수정` 16 KB 페이지 크기 기기에서 엔진 초기화 시 크래시(SIGSEGV)가 발생하던 문제 수정: 번들된 `libc++_shared.so`를 NDK r28.2 빌드로 교체하여 RELRO 세그먼트가 쓰기 가능한 데이터와 페이지를 공유하지 않도록 함 (arm64-v8a, armeabi-v7a)
* `수정` 큰 인식 모델을 로드할 때 `OutOfMemoryError`로 인해 빈 결과가 조용히 반환되던 문제 수정: 모델 자산을 앱 전용 저장소에 한 번만 복사하고 Java 힙으로 읽는 대신 ONNX Runtime이 메모리 매핑하도록 변경
* `수정` 일부 Gradle/AGP 조합에서 Kotlin 소스가 컴파일되지 않거나 `WakeActivity` 중복 정의로 빌드가 실패하는 문제
* `개선` OpenCV 4.8.0 네이티브 라이브러리를 NDK r28c (Clang 19.0.1) 재빌드 버전으로 동기화 (donor: AutoJs6-Plugin-OpenCV); 4개 ABI의 `libopencv_java4.so`는 16 KB `PT_LOAD` 정렬을 유지하며 provenance 매니페스트를 포함

# v1.0.1

###### 2026/09/11

* `개선` 64비트 네이티브 라이브러리의 16 KB 페이지 정렬을 빌드 시 검증, manifest 계약 검사 및 JSON 보고서 지원

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


[16 KB page alignment and build verification](https://github.com/SuperMonster003/AutoJs6-Plugin-Paddle-OCR-PP-OCRv4/blob/master/docs/16kb.md)
