<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
﻿<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Track a Package</title>
<link href="css/packagetracker.css" rel="stylesheet" type="text/css" />
</head>
<body>
<h1>Track a Package</h1>
<p>Use this form to view the progress of a package:</p>
<form id="form1" name="form1" method="get" action="">
  Package id: 
    <input type="text" name="q" id="q" />
  <input type="submit" name="button" id="button" value="Search" />
</form>
<p>&nbsp;</p>
<B>Results</B>
<table>
    <c:forEach var="waypoint" items="${trackings}">
        <tr>
            <td>${waypoint.status_timestamp}</td>
            <td>${waypoint.location}</td>
            <td>${waypoint.notes}</td>
        </tr>
    </c:forEach>
<table>

</body>
</html>
