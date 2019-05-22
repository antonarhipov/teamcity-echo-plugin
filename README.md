# Demo plugin for TeamCity

The plugin implements a custom runner for the echo command. 

The project makes use of [Gradle TeamCity plugin](https://github.com/rodm/gradle-teamcity-plugin)

To build and run the plugin in the development mode:

1. Create a `gradle.properties` file and add the property `teamcity.home` to set the path to your local TeamCity installation folder
1. Run `./gradlew clean build` to build the plugin
1. Run `./gradlew startTeamcity` to start TeamCity server with the plugin
1. Run `./gradlew deployToTeamcity` to deploy the plugin to the TeamCity Server. If the server is running the plugin will be re-loaded.
