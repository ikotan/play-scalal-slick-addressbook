# 導入方法
住所録サンプルを取得
```
# git clone git@gitlab.adways.net:endo.munetoshi/play-scalal-slick-addressbook.git
```
JDKインストール
```
# sudo yum install java-1.8.0-openjdk
# sudo yum install java-1.8.0-openjdk-devel
```
接続Database、Table作成
```
mysql> CREATE DATABASE `addressbook` DEFAULT CHARACTER SET utf8;
mysql> source addressbook.sql;
```

# 起動方法
```
# cd play-scalal-slick-addressbook
# ./activator run
```
