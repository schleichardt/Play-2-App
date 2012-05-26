import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "Play-2-App"
    val appVersion      = "0.2-SNAPSHOT"

    val appDependencies = Seq(
    )

    val secureModule = Project("secure", file("modules/secure"))

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).dependsOn(secureModule)

}
