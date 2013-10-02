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
        function listener(event){
            window.location.href = 'playlist_tracks?pl=${playlist_name}&button=addTrack&track_id=' + event.data;
            document.getElementById("test").innerHTML = "received: "+event.data
        }

        if (window.addEventListener){
            addEventListener("message", listener, false)
        } else {
            attachEvent("onmessage", listener)
        }
    </script>

</head>

<body>
<a href="login?button=logout">Logout</a> <br/>
<a href="playlists">Back to My Playlists</a> <br/>
<h1>Playlist ${playlist_name} for ${email}</h1>
<form id="form1" name="form1" method="get" action="">
<input type="hidden" name="pl" value="${playlist_name}"/>
<button type="submit" name="button" value="deletePlaylist">Delete this Playlist</button>
<div id="playlist_tracks" style="height:400px;width:500px;float:left;">
<table class="tracktable">
    <tr>
        <th></th>
        <th>Track Name</th>
        <th>Artist</th>
        <th>Length (s)</th>
    </tr>
    <c:forEach var="track" items="${tracks}">
        <tr>
            <td><button type="submit" name="deleteTrack" value="${track.sequence_no}">-</button></td>
            <td>${track.track_name}</td>
            <td>${track.artist}</td>
            <td>${track.track_length_in_seconds}</td>
        </tr>
    </c:forEach>
</table>
</div>
<h1>Song Picker: </h1>
<div id="picker" style="height:800px;width:500px;float:left;">
<iframe src="artists" height="400" width="400"></iframe>
</div>
</form>
</body>
</html>
