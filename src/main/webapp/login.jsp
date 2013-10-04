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
<c:if test="${not empty error}">
<p class="error">
</c:if>
${error}
</p>
<form id="form1" name="form1" class="login_form" method="get" action="">
    <h1>PLAYLIST LOGIN</h1>
    <p>
        <label for="email">E-mail Address</label>
        <input type="text" name="email" placeholder="Enter your email here" required>
    </p>
    <p>
         <label for="password">Password</label>
         <input type="password" name="password" placeholder="Password" required>
    </p>
 
    <p>
        <button type="submit" name="button" id="login" value="login">Login</button>
        <button type="submit" name="button" id="register" value="newAccount">I Don't Have an Account</button>
    </p>       
</form>

</body>
</html>
