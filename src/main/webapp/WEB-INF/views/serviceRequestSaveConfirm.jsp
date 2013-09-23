ad<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:url value="/" var="rootUrl"/>
<c:url value="/resources" var="resourcesUrl"/>

<html>
	<head>
		<title>Animal Medical Imaging</title>
	    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1.0">
	    <link href="${resourcesUrl}/media/bootstrap/css/bootstrap.css" rel="stylesheet" media="screen">
	    <link href="${resourcesUrl}/media/css/ami.css" rel="stylesheet" media="screen">
		
		<style>
		.error {
			color: #ff0000;
			font-style: italic;
		}
		</style>
		
		<script src="http://code.jquery.com/jquery.js"></script>
		<script src="js/bootstrap.min.js"></script>			
	</head>
	
	<body class="ami">
	
		<div class="navbar">
		 	<div class="navbar-inner">
		    	<a class="brand" href="#"><img src="${resourcesUrl}/img/ami_logo.jpg" alt="AMI Logo" width="140px" height="10px"></a>
		    	<ul class="nav">
			      <li class="active"><a href="${rootUrl}serviceRequest">Request Interpretation</a></li>
			      <li ><a href="${rootUrl}hospitalPendingRequest">Pending Interpretations</a></li>
			      <li ><a href="${rootUrl}hospitalInterpretation">Completed Interpretation</a></li>
			       <li ><a href="${rootUrl}searchRequest">Search</a></li>
			      <li ><a href="${rootUrl}hospitalProfileUpdate" >Update Profile</a></li>
			       <li><a href="${rootUrl}faq">Help</a></li>
			      <li><a href="${rootUrl}login?logout">Logout</a></li>
			      <li><a href="#" onclick="return;"><span style="color:green;" ><strong>${serviceRequest.hospitalName}</strong></span></a></li>
		    	</ul>
		  	</div>
		</div>
	
		<form:form id="form" class="form-horizontal" method="get" modelAttribute="aCase"   >
			<form:errors path="*" cssClass="error" />
				
				<div class="span12">
					<div class="span12">
						<p>Your Ser<li ><a href="${rootUrl}hospitalProfileUpdate" >Update Profile</a></li>vice Request has been submitted. You can <a href="${rootUrl}hospitalPendingRequest">edit</a> until the radiologist
						   starts processing it. At that point, it'll be locked. If you need to add or update any information, please
						   contact us.
						</p>
						
					</div>
				</div>				
				
		    
		</form:form>
	</body>
</html>

