name := """primeAPI"""
organization := "com.simscale"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)
scalaVersion := "2.12.2"

libraryDependencies += guice

libraryDependencies += javaJdbc

libraryDependencies += "commons-cli" % "commons-cli" % "1.4"

// Uncomment to use command line client instead of server
//mainClass in Compile := Some("services.SieveService")

// Ignore duplicates
assemblyMergeStrategy in assembly := {
	case PathList("META-INF", xs @ _*) => MergeStrategy.discard
	case x => MergeStrategy.first
}
