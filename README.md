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
- 自前でいじらないようにgit管理からは外す

## klintCheck
- 実行で一通りlint
- Cleanしてから実行するがよい
