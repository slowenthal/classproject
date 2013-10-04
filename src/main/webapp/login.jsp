<%--@elvariable id="error" type="java.lang.String"--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
﻿<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Playlist</title>
<link href="css/playlist.css" rel="stylesheet" type="text/css" />
    <link rel="shortcut icon" href="images/favicon.png" type="image/png"/>

</head>
<body>
<h1>Playlist Login</h1>
${error}
<form id="form1" name="form1" method="get" action="">
    E-mail Address:
    <input type="text" name="email" id="email" />
    <br/>
    Password:
    <input type="password" name="password" id="password" />
    <br/>
    <button type="submit" name="button" id="login" value="login">Login</button>
    <button type="submit" name="button" id="register" value="newAccount">I Don't Have an Account</button>
</form>
</body>
</html>
