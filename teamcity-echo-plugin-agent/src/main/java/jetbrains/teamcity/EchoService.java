package jetbrains.teamcity;

import jetbrains.buildServer.RunBuildException;
import jetbrains.buildServer.agent.runner.BuildServiceAdapter;
import jetbrains.buildServer.agent.runner.ProgramCommandLine;
import jetbrains.buildServer.agent.runner.SimpleProgramCommandLine;
import jetbrains.buildServer.util.FileUtil;
import jetbrains.buildServer.util.TCStreamUtil;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class EchoService extends BuildServiceAdapter {
  private final Set<File> myFilesToDelete = new HashSet<File>();

  @NotNull
  @Override
  public ProgramCommandLine makeProgramCommandLine() throws RunBuildException {

    final String message = getRunnerParameters().get(EchoRunnerConstants.MESSAGE_KEY);

    final String scriptContent = "/bin/echo " + message + " | tee echo.txt";

    final String script = getCustomScript(scriptContent);

    setExecutableAttribute(script);

    return new SimpleProgramCommandLine(getRunnerContext(), script, Collections.<String>emptyList());
  }

  String getCustomScript(String scriptContent) throws RunBuildException {
    try {
      final File scriptFile = File.createTempFile("custom_script", ".sh", getAgentTempDirectory());
      FileUtil.writeFileAndReportErrors(scriptFile, scriptContent);
      myFilesToDelete.add(scriptFile);
      return scriptFile.getAbsolutePath();
    } catch (IOException e) {
      RunBuildException exception = new RunBuildException("Failed to create temporary custom script file in directory '" + getAgentTempDirectory() + "': " + e
          .toString(), e);
      exception.setLogStacktrace(false);
      throw exception;
    }
  }

  private void setExecutableAttribute(@NotNull final String script) throws RunBuildException {
    try {
      TCStreamUtil.setFileMode(new File(script), "a+x");
    } catch (Throwable t) {
      throw new RunBuildException("Failed to set executable attribute for custom script '" + script + "'", t);
    }
  }

  @Override
  public void afterProcessFinished() throws RunBuildException {
    super.afterProcessFinished();
    for (File file : myFilesToDelete) {
      FileUtil.delete(file);
    }
    myFilesToDelete.clear();
  }
}
