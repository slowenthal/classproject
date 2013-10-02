<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
﻿<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Playlist</title>
<link href="css/playlist.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript">
        function addTrack(id) {
            parent.postMessage(id, '*');
        }
    </script>
</head>
<body>
<%@ include file="alphabet.jspf" %>
<h1>Songs By ${artist}</h1>

<b>Results</b>
<form id="form1" name="form1" method="get" action="">
<table>
    <c:forEach var="track" items="${tracks}">
        <tr>
            <td><input type="button" name="add" value="+" onclick="addTrack('${track.track_id}')"/>${track.track}</td>
        </tr>
    </c:forEach>
</table>
 </form>
</body>
</html>
