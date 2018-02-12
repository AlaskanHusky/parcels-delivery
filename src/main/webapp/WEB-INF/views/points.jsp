<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Parcels Delivery</title>
</head>
<body>
<h1> Add a Point</h1>

<c:url var="addAction" value="/point/add" />

<form:form action="${addAction}" modelAttribute="point">
    <table>
        <tr>
            <td>
                <form:label path="name">
                    <spring:message text="Name"/>
                </form:label>
            </td>
            <td>
                <form:input path="name" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="pointNumber">
                    <spring:message text="Point Number"/>
                </form:label>
            </td>
            <td>
                <form:input path="pointNumber" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="uri">
                    <spring:message text="URL"/>
                </form:label>
            </td>
            <td>
                <form:input path="uri" />
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${empty point.name}">
                    <input type="submit"
                           value="<spring:message text="Add Point"/>" />
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
<br>
<h3>Points List</h3>
<c:if test="${!empty listPoints}">
    <table class="tg">
        <tr>
            <th width="80">Point ID</th>
            <th width="120">Point Name</th>
            <th width="120">Point Status</th>
        </tr>
        <c:forEach items="${listPoints}" var="point">
            <tr>
                <td>${point.id}</td>
                <td>${point.name}</td>
                <td>${point.pointNumber}</td>
                <td>${point.uri}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>