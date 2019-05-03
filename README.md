# Demo plugin for TeamCity

The plugin implements a custom runner for the echo command. 

The project makes use of [TeamCity SDK Maven plugin](https://github.com/JetBrains/teamcity-sdk-maven-plugin)

To build and run the plugin in the development mode:

1. In [pom.xml](https://github.com/antonarhipov/teamcity-echo-plugin/blob/master/pom.xml#L41) Set the path to your local TeamCity installation folder
1. Run `mvn clean package` to build the plugin
3. Run `mvn tc-sdk:start` to start TeamCity server with the plugin

Developed during the [Getting Started with TeamCity Plugins @ April 30th, 2019](https://blog.jetbrains.com/teamcity/2019/05/webinar-recording-getting-started-with-teamcity-plugins/) webinar.
