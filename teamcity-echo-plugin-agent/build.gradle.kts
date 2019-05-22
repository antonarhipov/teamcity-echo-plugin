
plugins {
    id ("java")
    id ("com.github.rodm.teamcity-agent")
}

dependencies {
    "compile" (project(":teamcity-echo-plugin-common"))
}

teamcity {
    agent {
        archiveName = "teamcity-echo-plugin-agent.zip"
        descriptor {
            pluginDeployment {
                useSeparateClassloader = true
            }
        }
    }
}
