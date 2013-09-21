﻿<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%--
  Created by IntelliJ IDEA.
  User: stevelowenthal
  Date: 9/18/13
  Time: 6:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<B>Get Stuff Done</B>
<ul>
    <c:forEach var="item" items="${todo}">
        <li>${item}</li>
    </c:forEach>
</ul>
</body>
</html>