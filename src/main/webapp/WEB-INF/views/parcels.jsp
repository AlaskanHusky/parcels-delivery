<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Parcels</title>
</head>
<body>

<%@ include file='header.jsp' %>

<h1> Add or Update a Parcel</h1>

<c:url var="addAction" value="/parcels/add"/>

<form:form method="post" action="${addAction}" modelAttribute="parcel">
    <table>
        <tr>
            <td>
                <form:input path="id" type="hidden"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="sender">
                    <h5>Sender</h5>
                </form:label>
            </td>
            <td>
                <form:input path="sender" placeholder="Input parcel's sender"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="message">
                    <h5>Parcel's message</h5>
                </form:label>
            </td>
            <td>
                <form:input path="message" placeholder="Input parcel's message"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="path">
                    <h5>Path</h5>
                </form:label>
            </td>
            <td>
                <form:input path="path" placeholder="Input parcelss path"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="status">
                    Status
                </form:label>
            </td>
            <td>
                <form:radiobutton path="status" value="Transit" />
                    Transit
                <form:radiobutton path="status" value="On Next Point" />
                    On Next Point
                <form:radiobutton path="status" value="Delivered" />
                    Delivered
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
<c:if test="${!empty listOfParcels}">
    <h3>Parcels List</h3>
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