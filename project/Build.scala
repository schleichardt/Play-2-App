import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "Play-2-App"
    val appVersion      = "0.2-SNAPSHOT"

    val appDependencies = Seq(
        "org.springframework.security" % "spring-security-core" % "3.1.0.RELEASE",
        "org.springframework.security" % "spring-security-crypto" % "3.1.0.RELEASE"
    )

    val secureModule = Project("secure", file("modules/secure"))

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).dependsOn(secureModule).settings(
        ebeanEnabled := true
    )
}
