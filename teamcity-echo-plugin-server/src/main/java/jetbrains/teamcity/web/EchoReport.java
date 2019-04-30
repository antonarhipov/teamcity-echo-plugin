package jetbrains.teamcity.web;

import com.intellij.openapi.util.io.StreamUtil;
import jetbrains.buildServer.serverSide.BuildsManager;
import jetbrains.buildServer.serverSide.SBuild;
import jetbrains.buildServer.serverSide.artifacts.BuildArtifactHolder;
import jetbrains.buildServer.serverSide.artifacts.BuildArtifacts;
import jetbrains.buildServer.serverSide.artifacts.BuildArtifactsViewMode;
import jetbrains.buildServer.web.openapi.BuildTab;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import jetbrains.buildServer.web.openapi.WebControllerManager;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;

public class EchoReport extends BuildTab {
  protected EchoReport(WebControllerManager manager, BuildsManager buildManager, PluginDescriptor descriptor) {
    super("echoReportTab", "Echo Report", manager, buildManager,
    descriptor.getPluginResourcesPath("echoReport.jsp"));
  }

  @Override
  protected void fillModel(@NotNull Map<String, Object> model, @NotNull SBuild build) {

    final BuildArtifacts buildArtifacts = build.getArtifacts(BuildArtifactsViewMode.VIEW_DEFAULT);
    final BuildArtifactHolder artifact = buildArtifacts.findArtifact("echo.txt");
    if (artifact.isAvailable()) {
      try {
        final String text = StreamUtil.readText(artifact.getArtifact().getInputStream());
        model.put("text", text);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
