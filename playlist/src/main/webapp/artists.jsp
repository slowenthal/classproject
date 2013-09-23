<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
﻿<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Track a Package</title>
<link href="css/packagetracker.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="images/favicon.png" type="image/png" />
</head>
<body>
<h1>Artists</h1>
<a href="artists?q=A">A</a>&nbsp;&nbsp;
<a href="artists?q=B">B</a>&nbsp;&nbsp;
<a href="artists?q=C">C</a>&nbsp;&nbsp;
<a href="artists?q=D">D</a>&nbsp;&nbsp;
<a href="artists?q=E">E</a>&nbsp;&nbsp;
<a href="artists?q=F">F</a>&nbsp;&nbsp;
<a href="artists?q=G">G</a>&nbsp;&nbsp;
<a href="artists?q=H">H</a>&nbsp;&nbsp;
<a href="artists?q=I">I</a>&nbsp;&nbsp;
<a href="artists?q=J">J</a>&nbsp;&nbsp;
<a href="artists?q=K">K</a>&nbsp;&nbsp;
<a href="artists?q=L">L</a>&nbsp;&nbsp;
<a href="artists?q=M">M</a>&nbsp;&nbsp;
<a href="artists?q=N">N</a>&nbsp;&nbsp;
<a href="artists?q=O">O</a>&nbsp;&nbsp;
<a href="artists?q=P">P</a>&nbsp;&nbsp;
<a href="artists?q=Q">Q</a>&nbsp;&nbsp;
<a href="artists?q=R">R</a>&nbsp;&nbsp;
<a href="artists?q=S">S</a>&nbsp;&nbsp;
<a href="artists?q=T">T</a>&nbsp;&nbsp;
<a href="artists?q=U">U</a>&nbsp;&nbsp;
<a href="artists?q=V">V</a>&nbsp;&nbsp;
<a href="artists?q=W">W</a>&nbsp;&nbsp;
<a href="artists?q=X">X</a>&nbsp;&nbsp;
<a href="artists?q=Y">Y</a>&nbsp;&nbsp;
<a href="artists?q=Z">Z</a>&nbsp;&nbsp;


<b>Results</b>
<table>
    <c:forEach var="artist" items="${artists}">
        <tr>
            <td><a href="titles?artist=${artist}">${artist}</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
