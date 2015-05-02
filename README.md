# 導入方法

住所録サンプルを取得

```
# git clone 
```

JDKインストール

```
# sudo yum install java-1.8.0-openjdk
# sudo yum install java-1.8.0-openjdk-devel
```

接続先Database確認

```
# vim conf/application.conf

db.default.driver=com.mysql.jdbc.Driver
db.default.url="jdbc:mysql://localhost/addressbook?characterEncoding=UTF8"
db.default.user=【ユーザID】
db.default.password=【接続パスワード】
```

接続Database、Table作成

```
mysql> source addressbook.sql;
```

# 起動方法

```
# cd play-scalal-slick-addressbook
# ./activator run
```

# 画面にアクセス
http://【IPアドレス】:【Port番号】/addressbook/
