<%@ page import="jetbrains.teamcity.EchoRunnerConstants" %>
<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bs" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>
<c:set var="messageId" value="<%=EchoRunnerConstants.MESSAGE_KEY%>"/>

<l:settingsGroup title="My Runner settings">
    <tr>
        <th><label for="${messageId}">Message: <l:star/></label></th>
        <td>
            <div class="posRel">
                <props:textProperty name="${messageId}" size="56" maxlength="100"/>
                <span class="error" id="error_${messageId}"></span>
            </div>
        </td>
    </tr>
</l:settingsGroup>