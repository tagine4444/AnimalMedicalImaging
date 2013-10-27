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
    
    
    <script type="text/JavaScript">
	function timedRefresh(timeoutPeriod) {
		setTimeout("location.reload(true);",timeoutPeriod);
	}
</script>
    
</head>

<body  onload="JavaScript:timedRefresh(10000);">
	<div class="navbar">
		 	<div class="navbar-inner">
		    	<a class="brand" href="#"><img src="${resourcesUrl}/img/ami_logo.jpg" alt="AMI Logo" width="140px" height="10px"></a>
		    	<ul class="nav">
			       <li ><a href="${rootUrl}requestDashboard">Process Requests</a></li>
			       <li class="active"><a href="${rootUrl}caseDashboard">Verify Requests</a></li>
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
		<c:when test="${hasStatCasesToTranscribe}">
		<div class="span12">
			<table id="table_id" class="table table-bordered table-condensed">
			    <thead>
			        <tr>
			            <th>Request#</th>
			            <th>Request Date</th>
			            <th>Reading Date</th>
			            <th>Hospital</th>
			            <th>Client</th>
			            <th>Patient</th>
			            <th>Action</th>
			        </tr>
			    </thead>
			    <tbody>
			       <c:forEach var="aCase" items="${statCasesToTranscribe}">
						<tr>
							<td>${aCase.requestNumber}</td>
							<td><joda:format value="${aCase.requestDate}" pattern="MM/dd/yy HH:mm:ss" /></td>
							<td><joda:format value="${aCase.readingInCompleteDate}" pattern="MM/dd/yy HH:mm:ss" /></td>
				            <td>${aCase.hospitalName}</td>
							<td>${aCase.clientFirstName} ${aCase.clientLastName} </td>					
							<td>${aCase.patientName} - ${aCase.patientSpecies} ${aCase.patientBreed} </td>					
				            <td><a href="${rootUrl}case?requestToRead=${aCase.requestId}">Verify Case</a></td>
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
		<c:when test="${hasnonStatToTranscribeHigh}">  
		<div class="span12">
			<table id="table_id" class="table table-bordered table-condensed">
			    <thead>
			        <tr>
			            <th>Request#</th>
			            <th>Request Date</th>
			            <th>Reading Date</th>
			            <th>Hospital</th>
			            <th>Client</th>
			            <th>Patient</th>
			            <th>Species</th>
			            <th>Breed</th>
			            <th>Action</th>
			        </tr>
			    </thead>
			    <tbody>
			       <c:forEach var="aCase" items="${nonStatToTranscribeHigh}">
						<tr>
							<td>${aCase.requestNumber}</td>
							<td><joda:format value="${aCase.requestDate}" pattern="MM/dd/yy HH:mm:ss" /></td>
							<td><joda:format value="${aCase.readingInCompleteDate}" pattern="MM/dd/yy HH:mm:ss" /></td>
				            <td>${aCase.hospitalName}</td>
							<td>${aCase.clientLastName}, ${aCase.clientFirstName} </td>					
							<td>"${aCase.patientName}" </td>					
							<td>${aCase.patientSpecies} </td>					
							<td>${aCase.patientBreed} </td>					
				            <td><a href="${rootUrl}case?requestToRead=${aCase.requestId}">Verify Case</a></td>
						</tr>
					</c:forEach>
			    </tbody>
			</table>
	  	</div>
	  	</c:when>
		</c:choose>
<!-- 	</fieldset> -->
	
</div>









<div class="span12">	  	
	  	<legend>Requests</legend>
	  	<c:choose>
		<c:when test="${hasNonStatToTranscribe}">  
		<div class="span12">
			<table id="table_id" class="table table-bordered table-condensed">
			    <thead>
			        <tr>
			            <th>Request#</th>
			            <th>Request Date</th>
			            <th>Reading Date</th>
			            <th>Hospital</th>
			            <th>Client</th>
			            <th>Patient</th>
			            <th>Species</th>
			            <th>Breed</th>
			            <th>Action</th>
			        </tr>
			    </thead>
			    <tbody>
			       <c:forEach var="aCase" items="${nonStatToTranscribe}">
						<tr>
							<td>${aCase.requestNumber}</td>
							<td><joda:format value="${aCase.requestDate}" pattern="MM/dd/yy HH:mm:ss" /></td>
							<td><joda:format value="${aCase.readingInCompleteDate}" pattern="MM/dd/yy HH:mm:ss" /></td>
				            <td>${aCase.hospitalName}</td>
							<td>${aCase.clientLastName}, ${aCase.clientFirstName} </td>					
							<td>"${aCase.patientName}" </td>					
							<td>${aCase.patientSpecies} </td>					
							<td>${aCase.patientBreed} </td>					
				            <td><a href="${rootUrl}case?requestToRead=${aCase.requestId}">Verify Case</a></td>
						</tr>
					</c:forEach>
			    </tbody>
			</table>
	  	</div>
	  	</c:when>
		</c:choose>
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