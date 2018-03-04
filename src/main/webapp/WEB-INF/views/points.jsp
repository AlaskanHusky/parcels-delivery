<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Points</title>
    <link rel="stylesheet" href="resources/css/kickstart.css">
</head>
<body>

<%@ include file='header.jsp' %>

<h2> Add or Update a Point</h2>

<c:url var="addAction" value="/points/add"/>
<div class="col_12 column">
    <form:form method="post" action="${addAction}" modelAttribute="point" class="vertical">
        <div class="col_3 column">
            <form:input path="id" type="hidden"/>

            <form:label path="name">Name</form:label>
            <form:input path="name" type="text" maxlength="5" placeholder="Input point name"/>

            <form:label path="pointNumber">Point's number</form:label>
            <form:input path="pointNumber" type="text" placeholder="Input point's number"/>

            <form:label path="uri">URL</form:label>
            <form:input path="uri" type="URL" placeholder="Input link to point"/>
            <div class="col_4 column">
                <input type="submit" value="Submit"/>
            </div>
        </div>
    </form:form>
</div>
<br>
<c:if test="${!empty listOfPoints}">
    <h4>Points List</h4>
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