<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Points</title>
</head>
<body>

<%@ include file='header.jsp' %>

<h1> Add or Update a Point</h1>

<c:url var="addAction" value="/points/add"/>

<form:form method="post" action="${addAction}" modelAttribute="point">
    <table>
        <tr>
            <td>
                <form:input path="id" type="hidden"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="name">
                    Name
                </form:label>
            </td>
            <td>
                <form:input path="name" type="text" maxlength="5" placeholder="Input point name"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="pointNumber">
                    Point's number
                </form:label>
            </td>
            <td>
                <form:input path="pointNumber" type="text" placeholder="Input point's number"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="uri">
                    URL
                </form:label>
            </td>
            <td>
                <form:input path="uri" type="URL" placeholder="Input link to point"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit"/>
            </td>
        </tr>
    </table>
</form:form>
<br>
<c:if test="${!empty listOfPoints}">
    <h3>Points List</h3>
    <table>
        <thead>
        <tr>
            <th>Point ID</th>
            <th>Point Name</th>
            <th>Point Number</th>
            <th>Point URL</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listOfPoints}" var="parcel">
            <tr>
                <td>${parcel.id}</td>
                <td>${parcel.name}</td>
                <td>${parcel.pointNumber}</td>
                <td>${parcel.uri}</td>
                <td>
                    <a href="<c:url value='points/${parcel.id}/update' />">
                        Edit
                    </a>
                </td>
                <td>
                    <a href="<c:url value='points/${parcel.id}/delete' />">
                        Delete
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

</body>
</html>