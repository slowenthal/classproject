<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
﻿<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Playlist</title>
    <link href="css/playlist.css" rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" href="images/favicon.png" type="image/png"/>
</head>
<body>
<section class="menu small">
    <h1>Statistics</h1>
    <table class="info">
        <%--@elvariable id="statistics" type="java.util.List<playlist.model.StatisticsDAO>"--%>
        <c:forEach var="stat" items="${statistics}">
            <tr>
                <td>${stat.counter_name} = ${stat.counter_value}</td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
