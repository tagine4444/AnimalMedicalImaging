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
<!--     <script src="http://code.jquery.com/jquery.js"></script> -->
<!--     <script src="js/bootstrap.min.js"></script> -->
	
	<div class="navbar">
		 	<div class="navbar-inner">
		    	<a class="brand" href="#"><img src="${resourcesUrl}/img/ami_logo.jpg" alt="AMI Logo" width="140px" height="10px"></a>
		    	<ul class="nav">
			       <li><a href="${rootUrl}requestDashboard">Process Requests</a></li>
			       <li ><a href="${rootUrl}caseDashboard">Verify Requests</a></li>
			       <li class="active"><a href="${rootUrl}amiInterpretation" >Requests Completed Today</a></li>
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

<form:form id="form" method="post"  modelAttribute="interpretation" >
<form:errors path="*" cssClass="error" />
   
<div class="row">
<div class="span12">
<div class="span12">
<div class="span12">
<div class="span12">
<!-- 	<fieldset> -->
		<legend><span style="color:red">STAT Interpretation</span></legend>
		<div class="span12">
			<table id="table_id" class="table table-bordered table-condensed">
			    <thead>
			        <tr>
			            <th>Request#</th>
			            <th>Request Date</th>
			            <th>Client</th>
			            <th>Patient</th>
			            <th>Status</th>
			        </tr>
			    </thead>
			    <tbody>
			       <c:forEach var="aRequestCompleted" items="${interpretation.statRequestCompleted}">
						<tr>
							<td>${aRequestCompleted.requestNumber}</td>					
							<td><joda:format value="${aRequestCompleted.requestDate}" pattern="MM/dd/yy HH:mm" /></td>					
							<td>${aRequestCompleted.clientLastName}, ${aRequestCompleted.clientFirstName}</td>					
							<td>"${aRequestCompleted.patientName}"</td>					
				            <td><a href="${rootUrl}case?readonlyadmin=${aRequestCompleted.caseId}">View Interpretation</a></td>
						</tr>
					</c:forEach>
			    </tbody>
			</table>
	  	</div>
</div>
<div class="span12">		  	
		<legend>Interpretations</legend>
		<div class="span12">
			<table id="table_id" class="table table-bordered table-condensed">
			    <thead>
			        <tr>
			            <th>Request#</th>
			            <th>Request Date</th>
			            <th>Client</th>
			            <th>Patient</th>
			            <th>Status</th>
			        </tr>
			    </thead>
			    <tbody>
			       <c:forEach var="aRequestCompleted" items="${interpretation.requestCompleted}">
						<tr>
							<td>${aRequestCompleted.requestNumber}</td>
							<td><joda:format value="${aRequestCompleted.requestDate}" pattern="MM/dd/yy HH:mm" /></td>					
							<td>${aRequestCompleted.clientLastName}, ${aRequestCompleted.clientFirstName}</td>					
							<td>"${aRequestCompleted.patientName}"</td>					
				            <td><a href="${rootUrl}case?readonlyadmin=${aRequestCompleted.caseId}">View Interpretation</a></td>
						</tr>
					</c:forEach>
			    </tbody>
			</table>
	  	</div>
	</fieldset>
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