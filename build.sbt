scalaVersion := "2.12.1"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-free" % "0.9.0",
  "org.scalatest" %% "scalatest" % "3.0.1" % Test,
  "com.typesafe.akka" %% "akka-http" % "10.1.3",
  "com.typesafe.akka" %% "akka-stream" % "2.5.14",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.3",
  "com.typesafe.akka" %% "akka-http-testkit" % "10.1.3",
  "org.scalacheck" %% "scalacheck" % "1.14.0",

  "org.scala-lang" % "scala-compiler" % scalaVersion.value // for REPLesent
)

scalacOptions ++= Seq("-language:_", "-feature", "-nowarn") // for REPLesent
