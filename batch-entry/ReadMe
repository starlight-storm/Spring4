***サンプルの実行方法***
[HSQLDBの起動]
1.HSQLDBが起動していない場合、HSQLDBをインメモリで起動する。
1-1. HSQLDB(1.8.0)をダウンロードし、libフォルダで以下のコマンドをうって起動する(このサンプルではインメモリ)。
・java -cp hsqldb.jar org.hsqldb.util.DatabaseManager

[Eclipseからの動作確認]
1.batch-entryプロジェクトを選択し、右クリックでRun as > Run Configurations...を選択する。
2.ダイアログが表示されるので、mainタブのプロジェクトに「batch-entry」を、mainクラスに「org.springframework.batch.core.launch.support.CommandLineJobRunner」を設定する。
3.Argumentタブを選択し、Proguram argumentsに「classpath:/batch-context.xml job1」を設定する。
4.Runボタンをクリックすると、バッチが実行される（Consoleビューで動作が確認可能）。

[エラー処理の確認]
EntryItemReaderクラスのinputを以下のように変更することで、エラーが確認できる。
private String[] input = {"Hello World", "hoge", "hoge", null};