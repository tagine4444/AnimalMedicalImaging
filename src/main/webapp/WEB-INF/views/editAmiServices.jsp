<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>

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
	<div class="navbar">
		 	<div class="navbar-inner">
		    	<a class="brand" href="#"><img src="${resourcesUrl}/img/ami_logo.jpg" alt="AMI Logo" width="140px" height="10px"></a>
		    	<ul class="nav">
			       <li ><a href="${rootUrl}requestDashboard">Process Requests</a></li>
			       <li ><a href="${rootUrl}caseDashboard">Verify Requests</a></li>
			       <li ><a href="${rootUrl}amiInterpretation" >Requests Completed Today</a></li>
			       <li ><a href="${rootUrl}searchRequestAmi">Search</a></li>
			       <li ><a href="${rootUrl}hospitalAdmin" >Hospital Admin</a></li>
			       <li ><a href="${rootUrl}amiServices" >Services</a></li>
			       <li><a href="${rootUrl}login?logout">Logout</a></li>
		    	</ul>
		  	</div>
		</div>

<%@ include file="metadata.jsp" %> 
<div class="span12">
	<fieldset>
		<legend><span style="color:green;"><strong><%=hosptitalName %></strong></span></legend>
	</fieldset>
</div>
<form:form id="form" method="post"  modelAttribute="amiServiceModel" >
<form:hidden path="Id"/>
	<div class="row">
		<div class="span12">
		<%@ include file="addAmiServices.jsp" %>
		</div>
		
		<div class="span12">
			<div class="span2">	
	    		<label>&nbsp;</label>
	    		<button type="submit" name="saveEditedService" class="btn" >Edit</button>
	    		<button type="submit" name="saveEditedService" class="btn" >Cancel</button>
	    	</div>
		</div>
		
		
	</div>
</form:form>



</body>
</html>