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
    
    <script src="js/bootstrap.min.js"></script>		

<%@ include file="toolBar.jsp" %>

<form:form id="form" class="form-horizontal" method="post"  >
	    
	   	<h1>Load Data, Press the below button only once</h1>
	   		
		<div class="control-group">
		    <div class="controls">
		      <button type="submit" name="loadData" class="btn" >load data</button>
		  </div>
  		</div>
			
	</form:form>  
  
  
  
    
</body>
</html>