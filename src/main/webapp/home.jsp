﻿﻿<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%--

  Created by IntelliJ IDEA.
  User: stevelowenthal
  Date: 9/18/13
  Time: 6:13 PM
  To change this template use File | Settings | File Templates.
--%>

<%--@elvariable id="cassandra_info" type="playlist.model.CassandraInfo"--%>
<%--@elvariable id="java_version" type="java.lang.String"--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Playlist</title>
    <link rel="shortcut icon" href="images/favicon.png"/>

</head>
<body>
<B>Playlist</B>
<br/>
<img src="images/favicon.png"/>
Welcome to the playlist application. <br/>
Java version: ${java_version} <br/>
Cassandra version: ${cassandra_info.cassandraVersion} <br/>
Cluster name: ${cassandra_info.clusterName} <br/>
<br/>
<br/>
<b>Places to Go:</b> <br/>
<a href="login">Login</a>
<br/>
<a href="artists">Visit the Song Database</a>
</body>
</html>
