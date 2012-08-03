import sbt._
import Keys._
import PlayProject._
import de.johoop.jacoco4sbt.JacocoPlugin._

object ApplicationBuild extends Build {

    val appName         = "Play-2-App"
    val appVersion      = "0.2-SNAPSHOT"

    val appDependencies = Seq(
        "org.springframework.security" % "spring-security-core" % "3.1.0.RELEASE",
        "org.springframework.security" % "spring-security-crypto" % "3.1.0.RELEASE",
        "commons-dbutils" % "commons-dbutils" % "1.4",
        "org.slf4j" % "slf4j-api" % "1.6.1" from "http://repo1.maven.org/maven2/"
    )

    // tip from http://ronalleva.com/2012/04/25/jacoco-and-play.html#shutup
    lazy val s = Defaults.defaultSettings ++ Seq(jacoco.settings:_*)

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA, settings = s).settings(
        templatesImport += "helper.twitterBootstrap._",
        ebeanEnabled := true,
        parallelExecution in jacoco.Config := false
    )
}
