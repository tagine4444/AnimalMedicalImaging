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
    
    
<form>

<%@ include file="toolBar.jsp" %>

  
  
  <div class="row">
  <div class="span12">
	  <div class="span12">
	
			<p>
	  			Thank you! Your account has been created. <br/>
	  			You can now sign in by clicking on the Sign In button in the tool bar.
	  		<p>				
	  </div>
  </div>
  </div>
</form>
    
</body>
</html>