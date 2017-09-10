name := """primeAPI"""
organization := "com.simscale"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)
scalaVersion := "2.12.2"

libraryDependencies += guice

libraryDependencies += javaJdbc
