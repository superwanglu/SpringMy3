<%@ page language="java" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>timeOut</title>
		<script type="text/javascript">
		   alert("用户未登录或登录已超时，请重新登录！");
		   if(window.parent)		   
		   {		
		      window.top.location.href="<%=basePath%>LoginSys/login.html";
		   }
		   else
		   {				  
		      window.top.location.href="<%=basePath%>LoginSys/login.html";
		   } 
		   
		</script>
	</head>
</html>
