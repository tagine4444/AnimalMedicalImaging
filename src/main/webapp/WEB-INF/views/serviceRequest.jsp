<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:url value="/" var="rootUrl"/>
<c:url value="/resources" var="resourcesUrl"/>

<html>
	<head>
		<title>Animal Medical Imaging</title>
<!-- 	    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1.0"> -->
	    
		
	<link href="${resourcesUrl}/media/bootstrap/css/bootstrap.css" rel="stylesheet" media="screen">
	<link rel="stylesheet" href="${resourcesUrl}/media/css/jquery-ui-1.9.2.custom.css" />
	<link rel="stylesheet" href="${resourcesUrl}/media/css/ami.css" />
	
        <script src="${resourcesUrl}/media/js/jquery-1.8.3.js"></script>
    	<script src="${resourcesUrl}/media/bootstrap/js/bootstrap.js"></script>
    	<script src="${resourcesUrl}/media/js/jquery.blockUI.js"></script>
    	<script src="${resourcesUrl}/media/js/jquery-ui-1.9.2.custom.js"></script>
    	<script src="${resourcesUrl}/media/js/serviceRequest.js"></script>
	
	<script type="text/javascript">

	$(document).ready(function () {
	    $('#id1').popover('hide');
	    $('#requestedbyPopover').popover('hide');
		
	});
	</script>
	
		
	</head>
	
<body>
<div class="container-fluid">	

	
		<div class="navbar">
		 	<div class="navbar-inner">
		    	<a class="brand" href="#">
<%-- 		    		<img src="${resourcesUrl}/img/ami_logo.jpg" alt="AMI Logo" width="140px" height="10px"> --%>
		    	</a>
		    	<ul class="nav">
		    	  <li><img src="${resourcesUrl}/img/ami_logo.jpg" alt="AMI Logo" >&nbsp;</li>
			      <li class="active"><a href="${rootUrl}serviceRequest">Request Interpretation</a></li>
			      <li ><a href="${rootUrl}hospitalPendingRequest">Pending Interpretations</a></li>
			      <li ><a href="${rootUrl}hospitalInterpretation">Completed Interpretation</a></li>
			      <li ><a href="${rootUrl}searchRequest">Search</a></li>
			      <li ><a href="${rootUrl}hospitalProfileUpdate" >Update Profile</a></li>
			      
			      
			      <li><a href="#" onclick="alert('Not implemented yet');">Help</a></li>
			      <li><a href="${rootUrl}login?logout">Logout</a></li>
			       
		    	</ul>
		    	
		  	</div>
		</div>
		
		<div class="span12">
			<fieldset>
				<legend><span style="color:green;"><strong>${serviceRequest.hospitalName}</strong></span></legend>
			</fieldset>
		</div>
		
		<form:form id="form" method="post" modelAttribute="serviceRequest"   >
		
<%-- 			<form:errors path="*" cssClass="error"  /> --%>
			<form:hidden path="Id"/>
			<form:hidden path="requestNumber"/>
	
		<div class="span12">
			<span style="color: red"> ${serviceRequestMessage} </span>
			<%@ include file="hospitalDescription.jsp" %>
			<%@ include file="patientDescription.jsp" %> 
			
			<%@ include file="services.jsp" %>
					
			<%@ include file="consultation.jsp" %>

			<%@ include file="tentativeDiagnosis.jsp" %>
			
			<div class="span12">
			<div class="span12">
				<button type="submit" name="Save"  onclick="$.blockUI();" class="btn">Submit</button>
			 </div>
			 	&nbsp;
			 	&nbsp;
			 </div>
			
		</div>	
		</form:form>
</div>		
</body>
</html>
