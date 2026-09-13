******

### リリース履歴

******

# v1.0.3

###### 2026/09/13

* `修正` プラグインセンターのバージョンと ABI 情報がインストール済み APK と一致
* `修正` エンコード済み画像は 64 MiB まで対応し ファイル記述子とパイプを使用できます
* `改善` ダウンロード用ファイルの作成前に, リリース APK のバージョン, 署名, バリアントの完全性を検証
* `改善` 画像は最大 16777216 ピクセルまで, 生画像バッファーは 64 MiB まで対応

# v1.0.2

###### 2026/09/12

* `修正` 16 KB ページサイズ端末でエンジン初期化時にクラッシュ (SIGSEGV) する問題を修正: 同梱の `libc++_shared.so` を NDK r28.2 ビルドに更新し, RELRO セグメントが書き込み可能データとページを共有しないように (arm64-v8a, armeabi-v7a)
* `修正` 大きな認識モデルの読み込み時に `OutOfMemoryError` が発生して空の結果が返される問題を修正: モデルアセットをアプリ専用ストレージへ一度だけ展開し, Java ヒープに読み込む代わりに ONNX Runtime がメモリマップするように
* `修正` 一部の Gradle/AGP の組み合わせで Kotlin ソースがコンパイルされず, `WakeActivity` の重複定義によりビルドが失敗する問題
* `改善` OpenCV 4.8.0 ネイティブライブラリを NDK r28c (Clang 19.0.1) 再ビルド版に同期 (donor: AutoJs6-Plugin-OpenCV); 4 つの ABI の `libopencv_java4.so` は 16 KB `PT_LOAD` アラインメントを維持し provenance マニフェストを同梱

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
