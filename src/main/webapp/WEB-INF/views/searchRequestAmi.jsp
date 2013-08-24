<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>


<c:url value="/" var="rootUrl"/>
<c:url value="/resources" var="resourcesUrl"/>


<html>
	<head>
		<title>Animal Medical Imaging</title>
	    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1.0">
	    <link href="${resourcesUrl}/media/bootstrap/css/bootstrap.css" rel="stylesheet" media="screen">
	    
	    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.1/themes/base/jquery-ui.css" />
		<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
		<script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script>
		<link rel="stylesheet" href="/resources/demos/style.css" />
		<script src="${resourcesUrl}/media/js/jquery-1.8.3.js"></script>
		<script src="${resourcesUrl}/media/js/jquery-ui-1.9.2.custom.js"></script>
		<script src="${resourcesUrl}/media/js/serviceRequest.js"></script>	
		<script src="${resourcesUrl}/media/js/jquery.blockUI.js"></script>	
		<link rel="stylesheet" href="/resources/demos/style.css" />
		
		
		<style>
		.error {
			color: #ff0000;
			font-style: italic;
		}
		</style>
		
<!-- 		 	     <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.1/themes/base/jquery-ui.css" /> -->
<!-- 		<script src="http://code.jquery.com/jquery-1.9.1.js"></script> -->
<!-- 		<script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script> -->
		<link rel="stylesheet" href="/resources/demos/style.css" />
			<script>
			$(function() {
			$( "#from" ).datepicker({
			defaultDate: "+1w",
			changeMonth: true,
			numberOfMonths: 1,
			onClose: function( selectedDate ) {
			$( "#to" ).datepicker( "option", "minDate", selectedDate );
			}
			});
			$( "#to" ).datepicker({
			defaultDate: "+1w",
			changeMonth: true,
			numberOfMonths: 1,
			onClose: function( selectedDate ) {
			$( "#from" ).datepicker( "option", "maxDate", selectedDate );
			}
			});
			});
</script>
	</head>
	
	<body class="ami">
	
		<div class="navbar">
		 	<div class="navbar-inner">
		    	<a class="brand" href="#"><img src="${resourcesUrl}/img/ami_logo.jpg" alt="AMI Logo" width="140px" height="10px"></a>
		    	<ul class="nav">
			      <li><a href="${rootUrl}requestDashboard">Process Requests</a></li>
		  		  <li><a href="${rootUrl}caseDashboard">Verify Requests</a></li>
			      <li ><a href="${rootUrl}amiInterpretation" >Requests Completed Today</a></li>
			      <li class="active"><a href="${rootUrl}searchRequestAmi">Search</a></li>
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
		
		<form:form id="form" class="form-horizontal" method="get" modelAttribute="searchRequest"   >
			<form:errors path="*" cssClass="error" />
			
			
			<form:hidden path="hospitalId"/>
			<div class="span12">
				<div class="span12">
					<span class="error">${searchMsg}</span>
				</div>
				<div class="span12">
					  <form:input path="requestNumber" class="input-medium" placeholder="Request Number" />		
						<form:input  id="from" name="from" path="begDate" class="input-medium" title="Begining date" placeholder="From"/>
						<form:input  id="to" name="from" path="endDate" class="input-medium" title="End date" placeholder="To"/>

				      <button type="submit" name="searchRequestNumber" value="searchRequestNumber" class="btn" onclick="$.blockUI();">Search</button>
				</div>
				<br/><br/><br/><br/>
				<div class="span12">
					
				      <button type="submit" name="last60" class="btn" onclick="$.blockUI();">Last 60 Requests</button>
				      <button type="submit" name=last60Stat class="btn" onclick="$.blockUI();">Last 60 STATs</button>
				      <button type="submit" name=notInQuickbook class="btn" onclick="$.blockUI();">Not In Quickbook</button>
				</div>
				<br/><br/><br/><br/>
				  	
			</div>
			
			
			<div class="span12">
			<table id="table_id" class="table table-bordered table-condensed">
			    <thead>
			        <tr>
			            <th>Request#</th>
			            <th>Request Date</th>
			            <th>Reading Date</th>
			            <th>Verified Date</th>
			            <th>Client</th>
			            <th>Patient</th>
			            <th>Species</th>
			            <th>Breed</th>
			            <th>Status</th>
			            
			        </tr>
			    </thead>
			    <tbody>
			       <c:forEach var="aServiceReq" items="${serviceRequests}">
						<tr>
				            <td>${aServiceReq.requestNumber}</td>
							<td><joda:format value="${aServiceReq.requestDate}" pattern="MM/dd/yy HH:mm" />					
				            
					            <c:choose>
									<c:when test="${aServiceReq.notYetInProgress}">
										<a href="${rootUrl}serviceRequest?edit=${aServiceReq.id}">Edit</a>
									</c:when>
								</c:choose>	
				            
				            </td>
				            <td><joda:format value="${aServiceReq.readingInCompleteDate}" pattern="MM/dd/yy HH:mm:ss" /></td>
				            <td><joda:format value="${aServiceReq.trascribedDate}" pattern="MM/dd/yy HH:mm:ss" /></td>
				            
							<td>${aServiceReq.clientLastName}, ${aServiceReq.clientFirstName} </td>					
							<td>"${aServiceReq.patientName}" </td>					
							<td>${aServiceReq.patientSpecies} </td>					
							<td>${aServiceReq.patientBreed} </td>		
							
							 <c:choose>
									<c:when test="${aServiceReq.transcribed}">
										<td><a href="${rootUrl}case?readonlyadmin=${aServiceReq.caseId}">View Interpretation</a></td>
									</c:when>
									<c:otherwise>
				            			<td>Not Ready</td>
									</c:otherwise>
							</c:choose>				
						</tr>
					</c:forEach>
			    </tbody>
			</table>
	  	</div>
		    
		</form:form>
	</body>
</html>
