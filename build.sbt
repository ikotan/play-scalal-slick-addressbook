name := """play-slick-bootstrap3"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)
lazy val tasks = project in file("tasks") dependsOn(root)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc,
  "com.typesafe.slick" %% "slick" % "2.1.0-M2",
  "mysql" % "mysql-connector-java" % "5.1.20",
  "joda-time" % "joda-time" % "2.4",
  "org.joda" % "joda-convert" % "1.6",
  "com.github.tototoshi" %% "slick-joda-mapper" % "1.2.0",
  "org.specs2" %% "specs2-core" % "2.4.15" % "test",
  cache,
  ws
)
