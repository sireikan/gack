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
