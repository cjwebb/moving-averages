name := "moving-averages"

version := "1.0"

scalaVersion := "2.10.2"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
    "com.typesafe.akka" % "akka-actor_2.10" % "2.2.0",
    "org.scalatest" % "scalatest_2.10" % "1.9.1" % "test",
    "com.typesafe.akka" %% "akka-testkit" % "2.2.0" % "test"
    )
