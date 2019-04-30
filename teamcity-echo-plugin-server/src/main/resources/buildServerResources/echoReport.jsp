<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--@elvariable id="text" type="java.lang.String"--%>

<b>This is the report</b>
<p>
    <c:out value="${text}" default="no content to display"/><br/>
</p>