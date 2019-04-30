package jetbrains.teamcity;

import jetbrains.buildServer.serverSide.InvalidProperty;
import jetbrains.buildServer.serverSide.PropertiesProcessor;
import jetbrains.buildServer.serverSide.RunType;
import jetbrains.buildServer.serverSide.RunTypeRegistry;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static jetbrains.teamcity.EchoRunnerConstants.MESSAGE_KEY;

public class EchoRunner extends RunType {

  private final PluginDescriptor descriptor;

  public EchoRunner(RunTypeRegistry registry, PluginDescriptor descriptor) {
    this.descriptor = descriptor;
    registry.registerRunType(this);
  }

  @NotNull
  @Override
  public String getType() {
    return EchoRunnerConstants.RUNNER_TYPE;
  }

  @NotNull
  @Override
  public String getDisplayName() {
    return "Echo Runner";
  }

  @NotNull
  @Override
  public String getDescription() {
    return "This is Echo Runner";
  }

  @Nullable
  @Override
  public PropertiesProcessor getRunnerPropertiesProcessor() {
    return properties -> {
      final List<InvalidProperty> invalidProperties = new ArrayList<>();

      final String message = properties.get(MESSAGE_KEY);
      if (message == null) {
        invalidProperties.add(new InvalidProperty(MESSAGE_KEY, "Should not be null"));
      } else if (message.startsWith("fail")) {
        invalidProperties.add(new InvalidProperty(MESSAGE_KEY, "Should not start with fail"));
      }

      return invalidProperties;
    };
  }

  @Nullable
  @Override
  public String getEditRunnerParamsJspFilePath() {
    return descriptor.getPluginResourcesPath("editEchoRunnerParameters.jsp");
  }

  @Nullable
  @Override
  public String getViewRunnerParamsJspFilePath() {
    return descriptor.getPluginResourcesPath("viewEchoRunnerParameters.jsp");
  }

  @Nullable
  @Override
  public Map<String, String> getDefaultRunnerProperties() {
    return new HashMap<>();
  }


  @NotNull
  @Override
  public String describeParameters(@NotNull Map<String, String> parameters) {
    return "Message: '" + parameters.get(MESSAGE_KEY) + "'";
  }
}