<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Parcels</title>
    <link rel="stylesheet" href="resources/css/kickstart.css">
</head>
<body>

<%@ include file='header.jsp' %>

<h2> Add or Update a Parcel</h2>

<c:url var="addAction" value="/parcels/add"/>

<div class="col_12 column">
    <form:form method="post" action="${addAction}" modelAttribute="parcel" class="vertical">
    <div class="col_3 column">
        <form:input path="id" type="hidden"/>

        <form:label path="sender">Sender</form:label>
        <form:input path="sender" placeholder="Input parcel's sender"/>

        <form:label path="message">Parcel's message</form:label>
        <form:input path="message" placeholder="Input parcel's message"/>

        <form:label path="path">Path</form:label>
        <form:input path="path" placeholder="Input parcelss path"/>

        <fieldset>
            <legend>Parcel's Status</legend>
            <form:radiobutton class="radio" name="radio" path="status" value="Transit"/>
            <form:label class="inline" path="status">Transit</form:label>
            <br>
            <form:radiobutton class="radio" path="status" value="On Next Point"/>
            <form:label class="inline" path="status">On Next Point</form:label>
            <br>
            <form:radiobutton class="radio" path="status" value="Delivered"/>
            <form:label class="inline" path="status">Delivered</form:label>
        </fieldset>
        <div class="col_4 column">
            <input type="submit" value="Submit"/>
        </div>
        </form:form>
    </div>
</div>
<br>
<c:if test="${!empty listOfParcels}">
    <h4>Parcels List</h4>
    <table>
        <thead>
        <tr>
            <th>Parcel ID</th>
            <th>Parcel Sender</th>
            <th>Parcel Message</th>
            <th>Parcel Path</th>
            <th>Parcel Status</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listOfParcels}" var="parcel">
            <tr>
                <td>${parcel.id}</td>
                <td>${parcel.sender}</td>
                <td>${parcel.message}</td>
                <td>${parcel.path}</td>
                <td>${parcel.status}</td>
                <td>
                    <a href="<c:url value='parcels/${parcel.id}/update' />">
                        Edit
                    </a>
                </td>
                <td>
                    <a href="<c:url value='parcels/${parcel.id}/delete' />">
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