name := "kafka-producer"
version := "1.0"

scalaVersion := "2.12.3"
scalacOptions := Seq("-unchecked", "-deprecation", "-feature")

mainClass in Compile := Some("Main")
scalaSource in Compile := baseDirectory.value / "src"
scalaSource in Test := baseDirectory.value / "test"

libraryDependencies += "org.apache.kafka" % "kafka-clients" % "1.0.0"
libraryDependencies += "com.twitter" % "hbc-core" % "2.2.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "test"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.3" % Runtime

