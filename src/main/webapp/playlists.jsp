<%--@elvariable id="email" type="java.lang.String"--%>
<%--@elvariable id="playlist_names" type="java.util.List"--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--

DataStax Academy Sample Application

Copyright 2013 DataStax

--%>
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
    <a href="login?button=Logout">Logout</a> <br/>
    <h1>Playlists for ${email}</h1>

    <form id="form1" class="playlist_form" name="form1" method="get" action="">
    Playlist Name:    <input type="text" name="new_playlist_name" id="new_playlist_name" />
        <input type="submit" name="button" id="add" value="Add" />
        <br/>

    <table>
        <c:forEach var="playlist_name" items="${playlist_names}">
            <tr>
                <td><a href="playlist_tracks?pl=${playlist_name}">${playlist_name}</a></td>
            </tr>
        </c:forEach>
    </table>
    </form>
</section>
</body>
</html>
