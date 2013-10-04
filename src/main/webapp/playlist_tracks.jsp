<%--@elvariable id="email" type="java.lang.String"--%>
<%--@elvariable id="playlist" type="playlist.model.PlaylistDAO"--%>
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
            window.location.href = 'playlist_tracks?pl=${playlist.playlist_name}&button=addTrack&track_id=' + event.data;
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
<section class="tracks">
    <a href="login?button=logout">Logout</a> <br/>
    <a href="playlists">Back to My Playlists</a> <br/>
    <h2>Playlist ${playlist.playlist_name} for ${email}</h2>
    <h2>Total length: ${playlist.playlist_length_in_seconds}</h2>
    <form id="form1" name="form1" method="get" action="">
    <input type="hidden" name="pl" value="${playlist.playlist_name}"/>
    <button type="submit" class="track_delete" name="button" value="deletePlaylist">Delete this Playlist</button>
    <div id="playlist_tracks" style="height:auto;width:500px;float:left;">
        <table class="tracktable">
            <tr>
                <th></th>
                <th>Track Name</th>
                <th>Artist</th>
                <th>Genre</th>
                <th>Length (s)</th>
            </tr>
            <c:forEach var="track" items="${playlist.trackList}">
                <tr>
                    <td><button type="submit" name="deleteTrack" value="${track.sequence_no}">-</button></td>
                    <td>${track.track_name}</td>
                    <td>${track.artist}</td>
                    <td>${track.genre}</td>
                    <td>${track.track_length_in_seconds}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div id="picker" style="height:800px;width:800px;">
        <h2>Song Picker: </h2>
        <iframe src="tracks" height="auto" width="400"></iframe>
    </div>
    </form>
</section>
</body>
</html>
