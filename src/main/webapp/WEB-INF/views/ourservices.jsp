<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:url value="/" var="rootUrl"/>
<c:url value="/resources" var="resourcesUrl"/>
<c:url value="/j_spring_security_check" var="authPage"/>

<html>
<head>
	<title>Animal Medical Imaging</title>
    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="${resourcesUrl}/media/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
	
<style>
.error {
	color: #ff0000;
	font-style: italic;
}
</style>
</head>
<body>

<%-- 	<%@ include file="toolBar.jsp" %> --%>
	
<%-- 	<%@ include file="toolBar.jsp" %> --%>
	<div class="row">
					<div class="navbar">
					    <div class="navbar-inner">
					    <a class="brand" ></a>
						    <ul class="nav">
						    	<li><a href="${rootUrl}">Home</a></li>
						    	<li class="active"><a href="${rootUrl}ourservices">Our Services</a></li>
						    	<li><a href="${rootUrl}contactus">Contact us</a></li>
						    	<li><a href="${rootUrl}aboutus">About us</a></li>
						    	<li><a href="${rootUrl}hospitalCreateAccount">Create an account</a></li>
						    </ul>
					    </div>
				   </div> 
		
					<div class="span12" >
						
						<div class="span12">
							<div class="span12">
								<h1>Animal Medical Imaging</h1>
							</div>
						</div>
						
						<div class="span12">
							<div class="span12">
							<br/><br/><br/>
							</div>
							<div class="span12">
								<ul>
									<li>Contrast Radiography</li>
									<li>Computed Tomography</li>
									<li>Fluoroscopy</li>
									<li>Ultrasound</li>
									<li>CT scan</li>
								</ul> 
																									
				

							
								
							</div>
						</div>
					
					</div>
		</div>
	
	
</body>
</html>
