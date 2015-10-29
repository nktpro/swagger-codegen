import java.nio.file.{Files, Paths}
import java.util.UUID

import scala.language.postfixOps

name := "swagger-codegen"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  //  ("io.swagger" %	"swagger-codegen"	% "2.1.3")
  //    .exclude("org.apache.felix", "org.osgi.core")
  //    .exclude("org.apache.felix", "org.apache.felix.bundlerepository"),
  "io.swagger" % "swagger-codegen" % "2.1.4",
  "io.swagger" % "swagger-codegen-cli" % "2.1.4"
)

lazy val cwd = new java.io.File(".").getCanonicalPath
lazy val buildDocker = taskKey[Unit]("Build docker image")
lazy val dockerFilePath = "./src/main/resources/docker/"

buildDocker := {
  println("Building docker image...")
  (packageBin in Compile).value
  s"docker build -t nktpro/swagger-codegen ." !!
}

lazy val genClient = taskKey[Unit]("Test generating client")

lazy val yamlDefinition = "src/test/resources/test.yaml"

genClient := {
  val jsonDefinition = "temp/swagger.json"

  buildDocker.value
  println("Generating client...")

  println(s"Converting $yamlDefinition to $jsonDefinition")
  val json = s"docker run --rm -v $cwd:/var/app -w /var/app nktpro/swagger-cli bundle $yamlDefinition" !!

  Files.write(Paths.get(jsonDefinition), json.getBytes())

  val cmd =
    s"""docker run --rm
        | -v $cwd:/var/app
        | -w /var/app nktpro/swagger-codegen
        | generate -i $jsonDefinition -l nktpro.SwaggerTypeScriptClientCodegen -o temp""".stripMargin.replaceAll("\n", " ")
  cmd !
}

lazy val editSwagger = taskKey[Unit]("Edit swagger file")

editSwagger := {
  val containerId = UUID.randomUUID()

  sys.addShutdownHook({
    s"docker kill $containerId" !
  })

  val cmd = s"docker run -i --rm --name=$containerId -v $cwd:/var/app -p 3001:8080 -w /var/app nktpro/swagger-editor $yamlDefinition"
  println(cmd)
  cmd !
}