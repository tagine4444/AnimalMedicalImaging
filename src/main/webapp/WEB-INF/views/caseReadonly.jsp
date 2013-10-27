<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	
	<body>
	
	<div class="navbar">
		 	<div class="navbar-inner">
		    	<a class="brand" href="#">
<%-- 		    		<img src="${resourcesUrl}/img/ami_logo.jpg" alt="AMI Logo" width="140px" height="10px"> --%>
		    	</a>
		    	<ul class="nav">
		    	  <li><img src="${resourcesUrl}/img/ami_logo.jpg" alt="AMI Logo" >&nbsp;</li>
			      <li ><a href="${rootUrl}serviceRequest">Request Interpretation</a></li>
			      <li ><a href="${rootUrl}hospitalPendingRequest">Pending Interpretations</a></li>
			      <li ><a href="${rootUrl}hospitalInterpretation">Completed Interpretation</a></li>
			      <li ><a href="${rootUrl}searchRequest">Search</a></li>
			      <li ><a href="${rootUrl}hospitalProfileUpdate" >Update Profile</a></li>
			       <li><a href="${rootUrl}faq">Help</a></li>
			       <li><a href="${rootUrl}login?logout">Logout</a></li>
			       
		    	</ul>
		  	</div>
		</div>
		<form:form id="form" method="post" modelAttribute="aCase"   >
		
			<form:hidden path="Id" />
			<form:hidden path="hospitalId"/>
			<form:errors path="*" cssClass="error" />
	
			<div class="span12">

				<div class="span12">
						<p class="text-center lead" >Animal Medical Imaging <br/>Request# ${aCase.requestNumber}<br/>for ${aCase.hospitalName}</p>
						<p class="text-center lead" ><a href="javascript:window.print()">Print</a> - <a href="${rootUrl}emailPdf?caseId=${aCase.id}">Email PDF</a></p>
				</div>
				
				
<!-- 				<div class="span12"> -->
<!-- 					<div class="span12"> -->
<%-- 						<a href="#" onclick="history.go(-1);return false;">Back</a> - <a href="javascript:window.print()">Print</a> - <a href="${rootUrl}emailPdf?caseId=${aCase.id}">Email PDF</a> --%>
<!-- 					</div> -->
<!-- 				</div> -->
				
				
				<div class="span12">
					<%@ include file="serviceRequestReadonly.jsp" %>
				</div>
				
				<c:if test="${!aCase.underContract}">
				<div class="span12">
					<div class="span12">
						<pre><u><b>Radiographic Interpretation</b></u><br/><form:label path="radioInterpretation" >${aCase.radioInterpretation}</form:label></pre>
					</div>
				</div>	
				</c:if>
							
				<div class="span12">
					<div class="span12">
						<pre><u><b>Radiographic impression</b></u><br/><form:label path="radioImpression" >${aCase.radioImpression}</form:label></pre>
					</div>
				</div>
					
				<div class="span12">
					<div class="span12">
						<pre><u><b>Recommendation</b></u><br/><form:label path="recommendations" >${aCase.recommendations}</form:label></pre>
					</div>
				</div>	
				
				
				<div class="span12">
					
<%-- 					<c:if test="${!empty amendedVetNotes}"> --%>
					<div class="span12">
						<pre><u><b>Request Amendments</b></u><br/><form:label path="amendedVetNotes" >${aCase.amendedVetNotes}</form:label></pre>
					</div>
<%-- 					</c:if> --%>
				</div>
				
			</div>
			
			
			
			
			
		
		    
		</form:form>
	</body>
</html>

