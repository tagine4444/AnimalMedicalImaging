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
	<link rel="stylesheet" href="${resourcesUrl}/media/css/jquery-ui-1.9.2.custom.css" />
	<script src="${resourcesUrl}/media/js/jquery-1.8.3.js"></script>
	<script src="${resourcesUrl}/media/js/jquery-ui-1.9.2.custom.js"></script>
	<script src="${resourcesUrl}/media/js/serviceRequest.js"></script>	
	<script src="${resourcesUrl}/media/js/jquery.blockUI.js"></script>	
	
    

<script type="text/JavaScript">
<!--
	function timedRefresh(timeoutPeriod) {
		setTimeout("location.reload(true);",timeoutPeriod);
	}
//   -->
</script>

</head>

  
<body  onload="JavaScript:timedRefresh(10000);">
	<div class="navbar">
		 	<div class="navbar-inner">
		    	<a class="brand" href="#"><img src="${resourcesUrl}/img/ami_logo.jpg" alt="AMI Logo" width="140px" height="10px"></a>
		    	<ul class="nav">
			      <li class="active"><a href="${rootUrl}requestDashboard">Process Requests</a></li>
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

<form:form id="form" method="post" >
<form:errors path="*" cssClass="error" />
 
   
<div class="row">
<div class="span12">
<div class="span12">
<div class="span12">
<div class="span12">
<!-- 	<fieldset> -->
		<legend><span style="color:red">STAT Requests</span></legend>
     	<c:choose>
		<c:when test="${hasStatToProcess}">
		<div class="span12">
			<table id="table_id" class="table table-bordered table-condensed">
			    <thead>
			        <tr>
			            <th>Service Request</th>
			            <th>Hospital</th>
			            <th>Client</th>
			            <th>Patient</th>
			            <th>Status</th>
			            <th>Documents</th>
			        </tr>
			    </thead>
			    <tbody>
			       <c:forEach var="aServiceReq" items="${statToProcess}">
						<tr>
							<td>
								<joda:format value="${aServiceReq.requestDate}" pattern="MM/dd/yy HH:mm" />					
					            <c:if test="${aServiceReq.notYetInProgress}">
									<a href="${rootUrl}serviceRequest?edit=${aServiceReq.id}">Edit</a>
								</c:if>
				            </td>
				            <td>${aServiceReq.hospitalName}</td>
							<td>${aServiceReq.clientFirstName} ${aServiceReq.clientLastName} </td>					
							<td>${aServiceReq.patientName} - ${aServiceReq.patientSpecies} ${aServiceReq.patientBreed} </td>					
				            <td><a href="${rootUrl}case?requestToRead=${aServiceReq.id}" onclick="$.blockUI();">${aServiceReq.caseStatus} </a></td>
				            <td>
				              	<c:if test="${aServiceReq.hasDocuments}">
									<a href="${rootUrl}uploadedDocuments?svcReqId=${aServiceReq.id}">Uploaded Documents</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
			    </tbody>
			</table>
	  	</div>
	  	</c:when>
		</c:choose>
</div>
<div class="span12">	  	
	  	<legend> <span style="color:purple">High Priority Requests</span></legend>
	  	<c:choose>
		<c:when test="${hasNonStatToProcessPriorityHigh}">  
		<div class="span12">
			<table id="table_id" class="table table-bordered table-condensed">
			    <thead>
			        <tr>
			            <th>Request Date</th>
			            <th>Request#</th>
			            <th>Hospital</th>
			            <th>Client</th>
			            <th>Patient</th>
			            <th>Species</th>
			            <th>Breed</th>
			            <th>Status</th>
			            <th>Documents</th>
			            
			        </tr>
			    </thead>
			    <tbody>
			       <c:forEach var="aServiceReq" items="${nonStatToProcessHigh}">
						<tr>
							<td>
									<joda:format value="${aServiceReq.requestDate}" pattern="MM/dd/yy HH:mm" />					
									<c:if test="${aServiceReq.notYetInProgress}">
										<a href="${rootUrl}serviceRequest?edit=${aServiceReq.id}">Edit</a>
									</c:if>
				            </td>
				            <td>${aServiceReq.requestNumber}</td>
				            <td>${aServiceReq.hospitalName}</td>
							<td>${aServiceReq.clientLastName}, ${aServiceReq.clientFirstName} </td>					
							<td>"${aServiceReq.patientName}" </td>					
							<td>${aServiceReq.patientSpecies} </td>					
							<td>${aServiceReq.patientBreed} </td>					
				            <td><a href="${rootUrl}case?requestToRead=${aServiceReq.id}" onclick="$.blockUI();">${aServiceReq.caseStatus}</a></td>
				            <td>
								<c:if test="${aServiceReq.hasDocuments}">
									<a href="${rootUrl}uploadedDocuments?svcReqId=${aServiceReq.id}">Uploaded Documents</a>
							 	</c:if>
							</td>
						</tr>
					</c:forEach>
			    </tbody>
			</table>
	  	</div>
	  	</c:when>
		</c:choose>
</div>
<div class="span12">		
		<!-- Normal priority requests -->
		<legend>Requests</legend>
	  	<c:choose>
		<c:when test="${hasNonStatToProcess}">  
		<div class="span12">
			<table id="table_id" class="table table-bordered table-condensed">
			    <thead>
			        <tr>
			            <th>Request Date</th>
			            <th>Request#</th>
			            <th>Hospital</th>
			            <th>Client</th>
			            <th>Patient</th>
			            <th>Species</th>
			            <th>Breed</th>
			            <th>Status</th>
			            <th>Documents</th>
			            
			        </tr>
			    </thead>
			    <tbody>
			       <c:forEach var="aServiceReq" items="${nonStatToProcess}">
						<tr>
							<td>
									<joda:format value="${aServiceReq.requestDate}" pattern="MM/dd/yy HH:mm" />					
									<c:if test="${aServiceReq.notYetInProgress}">
										<a href="${rootUrl}serviceRequest?edit=${aServiceReq.id}">Edit</a>
									</c:if>
				            </td>
				            <td>${aServiceReq.requestNumber}</td>
				            <td>${aServiceReq.hospitalName}</td>
							<td>${aServiceReq.clientLastName}, ${aServiceReq.clientFirstName} </td>					
							<td>"${aServiceReq.patientName}" </td>					
							<td>${aServiceReq.patientSpecies} </td>					
							<td>${aServiceReq.patientBreed} </td>					
				            <td><a href="${rootUrl}case?requestToRead=${aServiceReq.id}" onclick="$.blockUI();">${aServiceReq.caseStatus}</a></td>
				            <td>
								<c:if test="${aServiceReq.hasDocuments}">
									<a href="${rootUrl}uploadedDocuments?svcReqId=${aServiceReq.id}">Uploaded Documents</a>
							 	</c:if>
							</td>
						</tr>
					</c:forEach>
			    </tbody>
			</table>
	  	</div>
	  	</c:when>
		</c:choose>
<!-- 	</fieldset> -->
</div>
</div>
</div>
</div>
	
	
</div>	  	
</form:form>

<script type="text/javascript">	
	//<![CDATA[   
				$('#table_id').dataTable(s);
				
	//]]>
	</script>
</body>
</html>