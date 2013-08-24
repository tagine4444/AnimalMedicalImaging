<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/" var="rootUrl"/>
<c:url value="/resources" var="resourcesUrl"/>

 
<html lang="en">
<head>
    <title>Animal Medical Imaging</title>
    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="${resourcesUrl}/media/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>

  
<body>
<!--     <script src="http://code.jquery.com/jquery.js"></script> -->
<!--     <script src="js/bootstrap.min.js"></script> -->
	
	<div class="navbar">
	  <div class="navbar-inner">
	    <a class="brand" href="#"><img src="${resourcesUrl}/img/ami_logo.jpg" alt="AMI Logo" width="140px" height="10px"></a>
	    <ul class="nav">
	      <li ><a href="${rootUrl}">Home</a></li>
	      <li class="active"><a href="#">Request Services</a></li>
	      <li ><a href="${rootUrl}hospitalInterpretation">View Interpretation</a></li>
	      <li><a href="#">Update Profile</a></li>
	      <li><a href="#">Help</a></li>
	       <li><a href="${rootUrl}login?logout">Logout</a></li>
	    </ul>
	  </div>
	</div>

	<%@ include file="serviceRequest.jsp" %>
	  
    
</body>
</html>