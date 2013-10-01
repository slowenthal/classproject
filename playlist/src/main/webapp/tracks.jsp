<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
﻿<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Playlist</title>
<link href="css/playlist.css" rel="stylesheet" type="text/css" />
</head>
<body>
<h1>Songs By ${artist}</h1>

<b>Results</b>
<table>
    <c:forEach var="track" items="${tracks}">
        <tr>
            <td>${track.track}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
