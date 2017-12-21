<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<!DOCTYPE HTML>
<html>
<head>
<title>Simple Login Form</title>
<meta charset="UTF-8" />
<link rel="stylesheet" type="text/css" href="../css/reset.css">
<link rel="stylesheet" type="text/css" href="../css/structure.css">
</head>

<body>
<form class="box login"
action="${pageContext.servletContext.contextPath }/LoginSys/loginCheck.html"
autocomplete="off" method="post">
	<fieldset class="boxBody">
	  <label>Username</label>
	  <input type="text" id="username" name="username" tabindex="1" placeholder="请输入用户名" required>
	  <label><a href="#" class="rLink" tabindex="5">Forget your password?</a>Password</label>
	  <input type="password" id="password" name="password" tabindex="2" placeholder="请输入密码" required>
	</fieldset>
	<footer>
	  <label><input type="checkbox" tabindex="3" name="loginkeeping" id="loginkeeping">Keep me logged in</label>
	  <input type="submit" class="btnLogin" value="Login" tabindex="4">
	</footer>
</form>
</body>
</html>