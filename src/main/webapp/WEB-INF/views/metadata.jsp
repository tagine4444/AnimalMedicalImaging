<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>

<%@ page import="java.util.*"%>
<%@ page import="org.springframework.context.ApplicationContext"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="com.bouacheria.ami.security.SecurityUtil"%>


<%

final ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
	SecurityUtil securityUtil = (SecurityUtil) ctx.getBean("securityUtil");
	String hosptitalName = securityUtil.getLoggedHospitalName();
%>

</body>
</html>