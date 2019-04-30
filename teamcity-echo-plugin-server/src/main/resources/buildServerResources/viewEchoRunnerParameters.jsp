<%@ page import="jetbrains.teamcity.EchoRunnerConstants" %>
<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<c:set var="messageId" value="<%=EchoRunnerConstants.MESSAGE_KEY%>"/>

<div class="parameter">
    Message: <props:displayValue name="${messageId}" emptyValue=""/>
</div>