# v1.0.0

###### 2026/07/17

* `신규` 엔진 `paddle-ocr`, 변형 `v4` 를 사용하는 Paddle OCR PP-OCRv4 플러그인 서비스
* `신규` 세 가지 OCR profile: `mobile`, `server`, `mobile-en`, 각각 mobile 범용 모델, 고정확도 server 모델, 영어 인식 모델에 대응
* `신규` `org.autojs.plugin.PADDLE_OCR` 를 통한 플러그인 발견과 `recognizeText`, `detect` 호출 지원
* `신규` OCR 출력으로 텍스트, 신뢰도, 사각형 경계, 네 점 좌표, 처리 시간 정보를 반환
* `신규` PP-OCRv4 모델을 다운로드하고 ONNX 로 변환할 수 있는 모델 준비 스크립트 `scripts/prepare_ppocrv4_assets.py`
* `신규` 스페인어/프랑스어/러시아어/아랍어/일본어/한국어/영어/중국어 간체/홍콩 번체 중국어/대만 번체 중국어 플러그인 메타데이터와 사용 설명
* `신규` README 와 CHANGELOG 의 JSON 소스 및 스크립트 생성 흐름
* `개선` flavor 별로 모델 assets 를 분리하고 `mobile` 과 `mobile-en` 이 감지 모델을 공유하여 APK 가 관련 없는 인식 모델을 포함하지 않도록 변경
* `개선` 빌드 중 필요한 `inference.onnx` 와 `inference.yml` 을 검증하고 assets 가 없으면 해당 준비 명령을 표시
* `개선` Release APK 이름에 version, profile, ABI variant 를 포함하며 `arm64-v8a`, `armeabi-v7a`, `universal` 출력을 지원
* `의존성` `common-plugin-api.aar`, `paddle-ocr-api.aar`, PP-OCRv4 runtime, Paddle OCR Android SDK, OpenCV 4.8.0, ONNX Runtime 통합
