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
	     <script src="${resourcesUrl}/media/js/jquery-1.8.3.js"></script>
		<script src="${resourcesUrl}/media/js/jquery-ui-1.9.2.custom.js"></script>
		<script src="${resourcesUrl}/media/js/serviceRequest.js"></script>	
		<script src="${resourcesUrl}/media/js/jquery.blockUI.js"></script>	
		
		
		
		<style>  
		.error {
			color: #ff0000;
			font-style: italic;
		}
		</style>
		
		<script src="http://code.jquery.com/jquery.js"></script>
		<script src="js/bootstrap.min.js"></script>			
		
		<style>
		.saved {
			color: blue;
			font-style: italic;
		}
		
		</style>
	</head>
	
	<body>
	<div class="navbar">
		 	<div class="navbar-inner">
		    	<a class="brand" href="#"><img src="${resourcesUrl}/img/ami_logo.jpg" alt="AMI Logo" width="140px" height="10px"></a>
		    	<ul class="nav">
			       <li ><a href="${rootUrl}requestDashboard">Process Requests</a></li>
			       <li ><a href="${rootUrl}caseDashboard">Verify Requests</a></li>
			       <li class="active"><a href="${rootUrl}amiInterpretation" >Requests Completed Today</a></li>
			       <li ><a href="${rootUrl}searchRequestAmi">Search</a></li>
			       <li ><a href="${rootUrl}hospitalAdmin" >Hospital Admin</a></li>
			       <li ><a href="${rootUrl}amiServices" >Services</a></li>
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
						<p class="text-center lead" >Animal Medical Imaging <br/>Request# ${aCase.requestNumber}<br/>${aCase.hospitalName}</p>
						<p class="text-center lead" ><a href="javascript:window.print()">Print</a> - <a href="${rootUrl}emailPdf?caseId=${aCase.id}">Email PDF</a></p>
						
						<c:if test="${!aCase.capturedInQuickBook}">
							<p class="text-center lead" ><font color="red">Not In Quick Book</font></p>
						</c:if>
						<c:if test="${aCase.capturedInQuickBook}">
							<p class="text-center lead" ><font color="green">In Quick Book</font></p>
						</c:if>
						
				</div>
				
				<div class="span12">
					<div class="span12">
						<button type="submit" name="capturedInQuickBook" value="true" class="btn" ><font color="green" onclick="$.blockUI();">In QuickBook</font></button>
						<button type="submit" name="capturedInQuickBook" value="false" class="btn" ><font color="red" onclick="$.blockUI();">Not In QuickBook</font></button>
					</div>
				</div>
				
				<div class="span12">
					<br/><br/>
					<div class="span12">
						<label><b>Notes</b> <span class="saved">${notesMsg} </span> </label>
						<form:textarea path="notes" rows="6" class="input-block-level" />
					</div>
					<div class="span12">
					 <button type="submit" name="saveNotesReadOnlyCase" value="true" class="btn" onclick="$.blockUI();">Save Notes</button>
					</div>
				</div>
				
				<div class="span12">
					<br/><br/>
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
					
					<c:if test="${!empty amendedVetNotes}">
					<div class="span12">
						<label><b>Request Amendments</b></label>
						<form:textarea disabled="true" path="amendedVetNotes" rows="4" class="input-block-level"/>
						<pre><u><b>Request Amendments</b></u><br/><form:label path="amendedVetNotes" >${aCase.amendedVetNotes}</form:label></pre>
					</div>
					</c:if>
				</div>
				
		 <div class="span12">
		 <div class="span12">
		 
		 	<div class="span2">
			    	<a id="addChargesLinkId" href="${rootUrl}addCharges?caseId=${aCase.id}&requestId=${aCase.requestId}">Add Charges</a>
				</div>
				<div class="span2">
			  		<a id="addChargesLinkId" href="${rootUrl}addMilageFee?caseId=${aCase.id}&requestId=${aCase.requestId}">Milage Fee</a> &nbsp; &nbsp;
				</div>
				<div class="span2">
			  		<a id="addChargesLinkId" href="${rootUrl}addLateFee?caseId=${aCase.id}&requestId=${aCase.requestId}">Late Fee</a> &nbsp; &nbsp;
			  	</div>	
		 	<br/><br/>
			<table id="table_id" class="table table-bordered">
			    <thead>
			        <tr>
			            <th>Service Name</th>
			            <th>Service Description</th>
			            <th>Price</th>
			        </tr>
			    </thead>
			    <tbody>
			       <c:forEach var="aCharge" items="${amiCharges}">
						<tr>
							<td>${aCharge.serviceName}</td>
							<td>${aCharge.serviceDescription}</td>
							<td>${aCharge.price}</td>
						
						
						</tr>
					</c:forEach>
			    </tbody>
			    <tfoot>
			    	<tr>
			    		<th>Total</th>
			    		<td/>
			    		<td/>
			    	</tr>
			    	<tr>
			    		<td/>
			    		<td/>
						<td>${amiChargesTotal.price}</td>							    		
			    	</tr>
			    </tfoot>
			</table>
	  	</div>	
	  	</div>	
				
			</div>
			
			
		
			
		
		    
		</form:form>
	</body>
</html>

