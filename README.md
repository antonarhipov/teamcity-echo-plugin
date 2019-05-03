# Demo plugin for TeamCity

The project makes use of [TeamCity SDK Maven plugin](https://github.com/JetBrains/teamcity-sdk-maven-plugin)

To build nd run the plugin in the development mode:

1. In [pom.xml](https://github.com/antonarhipov/teamcity-echo-plugin/blob/master/pom.xml#L41) Set the path to your local TeamCity installation folder
1. Run `mvn clean package` to build the plugin
3. Run `mvn tc-sdk:start` to start TeamCity server with the plugin
