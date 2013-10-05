<%--@elvariable id="artist" type="java.lang.String"--%>
<%--@elvariable id="genre" type="java.lang.String"--%>
<%--@elvariable id="tracks" type="List"--%>
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
<section class="menu small">
<%@ include file="alphabet.jspf" %>
    <c:choose>
        <c:when test="${not empty genre}">
            <h2>${genre} Songs</h2>
        </c:when>
        <c:when test="${not empty artist}">
            <h2>Songs By ${artist}</h2>
        </c:when>
        <c:otherwise>
            <h2>Click a Genre or Artist Letter Above</h2>
        </c:otherwise>
    </c:choose>

<script type="text/javascript">
    function addTrack(id) {
        parent.postMessage(id, '*');
    }
</script>

<form id="form1" name="form1" method="get" action="">
    <table class="table">
        <c:forEach var="track" items="${tracks}">
            <tr>
                <td class="field_plus"><input type="button" name="add" value="+" onclick="addTrack('${track.track_id}')"/></td>
                <td class="field_track">${track.track}</td>
                <td class="field_genre">${track.genre}</td>
                <td class="field_sec">${track.track_length_in_MS}</td>
            </tr>
        </c:forEach>
    </table>
</form>
</section>
</body>
</html>
