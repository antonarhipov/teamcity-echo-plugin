
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

plugins {
    id ("com.github.rodm.teamcity-server") version "1.2"
}

val defaultVersion = "SNAPSHOT-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
extra["versionNumber"] = project.findProperty("version.number") as String? ?: defaultVersion
extra["teamcityVersion"] = project.findProperty("teamcity.api.version") as String? ?: "2018.2"

group = "jetbrains.teamcity"
version = extra["versionNumber"] as String

teamcity {
    version = extra["teamcityVersion"] as String
}
