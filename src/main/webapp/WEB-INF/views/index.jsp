<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Parcels Delivery</title>
    <link rel="stylesheet" href="resources/css/kickstart.css">
</head>
<body>

<%@ include file='header.jsp' %>

<h2> Send your parcel</h2>

<c:url var="sendAction" value="/parcels/send"/>

<div class="col_12 column">
    <form:form method="post" action="${sendAction}" modelAttribute="parcel" class="vertical">
        <div class="col_3 column">
            <form:input path="id" type="hidden"/>

            <form:label path="sender">Sender</form:label>
            <form:input path="sender" placeholder="Input parcel's sender"/>

            <form:label path="message">Parcel's message</form:label>
            <form:input path="message" placeholder="Input parcel's message"/>

            <form:label path="path">Path</form:label>
            <form:input path="path" placeholder="Input parcelss path"/>

            <form:input path="status" type="hidden" value="Transit"/>
            <div class="col_4 column">
                <input type="submit" value="Submit"/>
            </div>
        </div>
    </form:form>
    <div class="col_9 column">
        <img alt="map" src="<c:url value="/resources/img/map.png"/>" />
    </div>
</div>
</body>
</html>