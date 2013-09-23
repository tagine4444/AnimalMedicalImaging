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
    
    
        <script src="${resourcesUrl}/media/js/jquery-1.8.3.js"></script>
<%-- 		<script src="${resourcesUrl}/media/js/jquery-ui-1.9.2.custom.js"></script> --%>
<%-- 		<script src="${resourcesUrl}/media/js/jquery.blockUI.js"></script>	 --%>
    	<script src="${resourcesUrl}/media/bootstrap/js/bootstrap.js"></script>
    	
    
    <script type="text/javascript">

$(document).ready(function () {
    if ($("[rel=tooltip]").length) {
        $("[rel=tooltip]").tooltip();
    }
    
    $('#id1').popover('show');
	$('#element').tooltip('show');
	
});
</script>    
</head>


  
<body>
	<div class="navbar">
		 	<div class="navbar-inner">
		    	<a class="brand" href="#"><img src="${resourcesUrl}/img/ami_logo.jpg" alt="AMI Logo" width="140px" height="10px"></a>
		    	<ul class="nav">
			      <li ><a href="${rootUrl}serviceRequest">Request Interpretation</a></li>
			      <li class="active"><a href="${rootUrl}hospitalPendingRequest">Pending Interpretations</a></li>
			      <li ><a href="${rootUrl}hospitalInterpretation">Completed Interpretation</a></li>
			       <li ><a href="${rootUrl}searchRequest">Search</a></li>
			      <li ><a href="${rootUrl}hospitalProfileUpdate" >Update Profile</a></li>
			       <li><a href="${rootUrl}faq">Help</a></li>
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

<form:form id="form" method="post"  modelAttribute="pendingRequest" >
<form:errors path="*" cssClass="error" />
   
<div class="container row">
										
	<div class="span12">
		<p>
			Requests listed on this page are awaiting interpretation. 
			<a href="${rootUrl}hospitalInterpretation">Completed Interpretation are availabe here</a> or on the menu.
		</p>
	</div>
	<fieldset>
		<legend><span style="color:red">STAT Interpretation Requests</span></legend>
		<div class="span12">
			<table id="table_id" class="table table-bordered table-condensed">
			    <thead>
			        <tr>
			            <th>Request#</th>
			            <th>Request Date</th>
			            <th>Client</th>
			            <th>Patient</th>
			            <th>Status</th>
			            <th>Edit</th>
			            <th>Upload</th>
			        </tr>
			    </thead>
			    <tbody>
			       <c:forEach var="aServiceReq" items="${pendingRequest.statRequests}">
						<tr>
							<td>${aServiceReq.requestNumber}</td>					
							<td><joda:format value="${aServiceReq.requestDate}" pattern="MM/dd/yy HH:mm" /></td>					
							<td>${aServiceReq.clientLastName}, ${aServiceReq.clientFirstName} - ${aServiceReq.clientId}</td>					
							<td>"${aServiceReq.patientName}"</td>					
				            <td>${aServiceReq.status}</td>
				            <td>
								 <c:choose>
									<c:when test="${aServiceReq.notYetInProgress}">
										<a href="${rootUrl}serviceRequest?edit=${aServiceReq.id}">Edit Request</a>
									</c:when>
								</c:choose>	
							</td>
							<td>
								<a href="${rootUrl}upload?svcReqId=${aServiceReq.id}&requestNumber=${aServiceReq.requestNumber}">Upload Documents</a>
							</td>
						</tr>
					</c:forEach>
			    </tbody>
			</table>
	  	</div>
	  	
		<legend>Pending Interpretations</legend>
		<div class="span12">
			<table id="table_id" class="table table-bordered table-condensed">
			    <thead>
			        <tr>
			            <th>Request#</th>
			            <th>Request Date</th>
			            <th>Client</th>
			            <th>Patient</th>
			            <th>Status</th>
			            <th>Edit</th>
			            <th>Upload</th>
			        </tr>
			    </thead>
			    <tbody>
			       <c:forEach var="aServiceReq" items="${pendingRequest.requests}">
						<tr>
							<td>${aServiceReq.requestNumber}</td>	
							<td><joda:format value="${aServiceReq.requestDate}" pattern="MM/dd/yy HH:mm" /></td>					
							<td>${aServiceReq.clientLastName}, ${aServiceReq.clientFirstName} - ${aServiceReq.clientId}</td>					
							<td>"${aServiceReq.patientName}"</td>					
				            <td>${aServiceReq.status}</td>
				            <td>
					             <c:choose>
									<c:when test="${aServiceReq.notYetInProgress}">
										<a href="${rootUrl}serviceRequest?edit=${aServiceReq.id}">Edit Request</a>
									</c:when>
								</c:choose>	
							</td>
							<td>
								<a href="${rootUrl}upload?svcReqId=${aServiceReq.id}&requestNumber=${aServiceReq.requestNumber}">Upload Documents</a>
							</td>
						</tr>
					</c:forEach>
			    </tbody>
			</table>
	  	</div>
		
	</fieldset>
	
	
</div>	  	
</form:form>

<script type="text/javascript">	
	//<![CDATA[   
				$('#table_id').dataTable(s);
				
	//]]>
	</script>
</body>
</html>