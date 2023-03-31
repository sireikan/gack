## 仮のDB作成
```sql
CREATE DATABASE IF NOT EXISTS sample;
```

## 起動方法

```shell
cd docker

## 通常
docker-compose build --no-cache && docker-compose up

## enable remote debug
docker-compose -f docker-compose-debug.yml build --no-cache && docker-compose up

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

## api test
- http://localhost:8080/swagger-ui.html
