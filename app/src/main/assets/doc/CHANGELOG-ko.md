******

### 릴리스 기록

******

# v1.0.3

###### 2026/09/13

* `수정` 플러그인 센터의 버전과 ABI 정보가 설치된 APK와 일치
* `수정` 인코딩된 이미지는 최대 64 MiB이며 파일 디스크립터와 파이프를 지원합니다
* `개선` 다운로드 파일 생성 전에 릴리스 APK의 버전, 서명 및 전체 변형 구성을 검증
* `개선` 이미지는 최대 16777216픽셀, 원시 이미지 버퍼는 최대 64 MiB까지 지원

# v1.0.2

###### 2026/09/12

* `수정` 16 KB 페이지 크기 기기에서 엔진 초기화 시 크래시(SIGSEGV)가 발생하던 문제 수정: 번들된 `libc++_shared.so`를 NDK r28.2 빌드로 교체하여 RELRO 세그먼트가 쓰기 가능한 데이터와 페이지를 공유하지 않도록 함 (arm64-v8a, armeabi-v7a)
* `수정` 큰 인식 모델을 로드할 때 `OutOfMemoryError`로 인해 빈 결과가 조용히 반환되던 문제 수정: 모델 자산을 앱 전용 저장소에 한 번만 복사하고 Java 힙으로 읽는 대신 ONNX Runtime이 메모리 매핑하도록 변경
* `수정` 일부 Gradle/AGP 조합에서 Kotlin 소스가 컴파일되지 않거나 `WakeActivity` 중복 정의로 빌드가 실패하는 문제
* `개선` OpenCV 4.8.0 네이티브 라이브러리를 NDK r28c (Clang 19.0.1) 재빌드 버전으로 동기화 (donor: AutoJs6-Plugin-OpenCV); 4개 ABI의 `libopencv_java4.so`는 16 KB `PT_LOAD` 정렬을 유지하며 provenance 매니페스트를 포함

# v1.0.1

###### 2026/09/11

* `개선` 64비트 네이티브 라이브러리의 16 KB 페이지 정렬을 빌드 시 검증, manifest 계약 검사 및 JSON 보고서 지원

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
