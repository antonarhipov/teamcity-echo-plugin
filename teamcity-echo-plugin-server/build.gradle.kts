
import com.github.rodm.teamcity.TeamCityEnvironment

plugins {
    id ("java")
    id ("com.github.rodm.teamcity-server")
    id ("com.github.rodm.teamcity-environments")
}

extra["homeDir"] = project.findProperty("teamcity.home") ?: ""
extra["vendorName"] = project.findProperty("vendor.name") ?: "Plugin vendor name"
extra["vendorUrl"] = project.findProperty("vendor.url") ?: "Plugin vendor URL"

dependencies {
    "compile" (project(":teamcity-echo-plugin-common"))
    "agent" (project(path = ":teamcity-echo-plugin-agent", configuration = "plugin"))
}

teamcity {
    server {
        archiveName = "teamcity-echo-plugin.zip"

        descriptor {
            name = "teamcity-echo-plugin"
            displayName = "teamcity-echo-plugin"
            version = rootProject.version as String?
            description = "Plugin description"
            vendorName = extra["vendorName"] as String
            vendorUrl = extra["vendorUrl"] as String
            vendorLogo = "Plugin vendor logo URL"
            downloadUrl = "Plugin download URL"
            email = "Plugin author e-mail"
            useSeparateClassloader = true
        }
    }

    environments {
        operator fun String.invoke(block: TeamCityEnvironment.() -> Unit) = environments.create(this, closureOf(block))

        "teamcity" {
            version = "2018.2.4"
            homeDir = file(extra["homeDir"] as String)
            serverOptions ("-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005")
            agentOptions ("-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5006")
        }
    }
}
