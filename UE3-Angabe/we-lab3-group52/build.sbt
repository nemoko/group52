name := "we-lab3-group52"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  javaCore,
  cache,
  javaJpa,
  "org.hibernate" % "hibernate-entitymanager" % "4.3.1.Final",
  "com.google.code.gson" % "gson" % "2.2"
)     

templatesImport += "scala.collection._"

templatesImport += "lib._"

play.Project.playJavaSettings
