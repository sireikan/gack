## 起動方法

```shell
cd docker
docker-compose build --no-cache
docker-compose up
docker-compose up -d
```

## コンテナ停止

```shell
docker-compose down
```

## volume削除
```shell
docker-compose down --volume
```

## compile
```shell
./gradlew build
```

## run
```shell
./gradlew bootRun
```

## test
```shell
./gradlew test
```

## openapi
- generateApiServer実行でopenapi.yamlからAPIクラスを自動生成する
- openapi-generator側がkotlin対応できていない部分があるのでそのままだとコンパイルエラーになる
- そのためコード管理に自動生成ファイルを含むようにしている
- コンパイルエラーがなくなったら自動生成先を参照するようにすればよい

## klintCheck
- 実行で一通りlint
- Cleanしてから実行するがよい
