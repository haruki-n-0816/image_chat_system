# プロジェクトの起動方法
##　起動順
1.mysql
2.flyway
3.springboot
4.vue

## 起動コマンド
### mysql
1.docker-compose.ymlがあるディレクトリに移動
2.以下のコマンドを実行
,,,
docker compose up image_chat_system_db
,,,

### flyway
1.flywayプロジェクトのpom.xmlがあるディレクトリに移動
2.以下のコマンドを実行
,,,
mvn flyway:clean
mvn flyway:migrate
,,,

### springboot
1.backend-apiプロジェクトのpom.xmlがあるディレクトリに移動
2.以下のコマンドを実行
,,,
mvn spring-boot:run
,,,

### vue
1.frontendプロジェクトのpackage.jsonがあるディレクトリに移動
2.以下のコマンドを実行
,,,
npm install(初回のみ)
npm run serve
,,,