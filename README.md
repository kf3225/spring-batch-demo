## Spring Batchを使った業務でメモしておきたいこと

SpringBatch起動方法
--

1. `mvn install`でjarを生成
2. 起動引数を指定しての実行は`java -jar jarファイル filename=hoge`
3. 起動引数を設定した場合Javaファイル内で以下の方法で取得可能

```java
@Value("#{jobParameters['hoge.param001']}")
private String filename;
```

SingleItemPeekableItemReader
--

- FlatItemFileReaderと違って先読みが可能なので簡単にコントロールブレーク処理（処理単位の区切り）を実装できる
- 使い方としてはJavaConfigクラスを作ってその中で@BeanでDIコンテナに登録されるようにする

```java
SingleItemPeekableItemReader<Person> reader(@Value("#{jobExecutionContext['" + INPUT_FILE + "']}") String filename)
```

上記のように引数を設定してあげることによってjobExecutionContextのINPUT_FILEキーから値を取得してfilenameとしてコンストラクタ内で使用することができる

- SingleItemPeekableItemReaderの設定（デリミタ、リソースファイルの在り処、マッピングするオブジェクトのフィールド名やクラス名etc...）を
  SingleItemPeekableItemReaderにdelegate（移譲）することで使用可能になる