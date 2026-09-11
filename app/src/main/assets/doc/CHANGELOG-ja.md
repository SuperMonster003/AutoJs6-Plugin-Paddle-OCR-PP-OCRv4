******

### リリース履歴

******

# v1.0.1

###### 2026/09/11

* `改善` 64 ビットのネイティブライブラリの 16 KB ページアラインメントをビルド時に検証, manifest 契約の検査と JSON レポートに対応

# v1.0.0

###### 2026/09/01

* `機能` エンジン `paddle-ocr`, バリアント `v4` の Paddle OCR PP-OCRv4 プラグインサービス
* `機能` 3 つの OCR profile: `mobile`, `server`, `mobile-en`, mobile 汎用モデル, 高精度 server モデル, 英語認識モデルに対応
* `機能` `org.autojs.plugin.PADDLE_OCR` によるプラグイン検出と, `recognizeText` および `detect` 呼び出しに対応
* `機能` OCR 出力としてテキスト, 信頼度, 矩形境界, 四点座標, 処理時間情報を返す機能
* `機能` PP-OCRv4 モデルをダウンロードして ONNX に変換できるモデル準備スクリプト `scripts/prepare_ppocrv4_assets.py`
* `機能` スペイン語/フランス語/ロシア語/アラビア語/日本語/韓国語/英語/簡体字中国語/香港繁体字/台湾繁体字のプラグインメタデータと使用説明
* `機能` README と CHANGELOG の JSON ソースおよびスクリプト生成フロー
* `修正` 一部のシステムでインストール後にプラグインセンターからプラグインを有効化できない問題
* `改善` flavor ごとにモデル assets を分割し, `mobile` と `mobile-en` で検出モデルを共有して APK が無関係な認識モデルを含まないように変更
* `改善` ビルド中に必要な `inference.onnx` と `inference.yml` を検証し, assets が不足する場合は対応する準備コマンドを表示
* `改善` Release APK 名に version, profile, ABI variant を含め, `arm64-v8a`, `armeabi-v7a`, `universal` 出力に対応
* `改善` README のレイアウトと Gradle プラットフォームのバージョン管理方式を統一
* `依存関係` `common-plugin-api.aar`, `paddle-ocr-api.aar`, PP-OCRv4 runtime, Paddle OCR Android SDK, OpenCV 4.8.0, ONNX Runtime を統合
