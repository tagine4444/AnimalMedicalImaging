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
		
		<script type="text/javascript">
			function showAmendments()
			{
				document.getElementById("amNotesTextId").style.display = "block";
				document.getElementById("amNotesLinkId").style.display = "none";
			}	
		
		</script>
	</head>
	
	<body class="ami">
	
		
		
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
	
		<form:form id="form" class="form-horizontal" method="post" modelAttribute="aCase"   >
		
			<form:hidden path="Id" />
			<form:hidden path="hospitalId"/>
			<form:errors path="*" cssClass="error" />
			<div class="span12">
				<div class="span12">
					<fieldset>
						<label>
							<h3>Request# ${aCase.requestNumber}</h3>
						</label>
					</fieldset>
					<div class="span12">
							<c:if test="${aCase.readingComplete}">
								<div class="span12">
				      		   		<button type="submit" name="saveTranscribed" class="btn" onclick="$.blockUI();"><font color="green">Looks good, Make it visible to customer</font></button>
						    	</div>
						    </c:if>
						<%@ include file="serviceRequestReadonly.jsp" %>
					</div>
					<div class="span12">
						<c:if test="${!aCase.underContract}">
						<div class="span12">
							<label><b>Radiographic Interpretation</b></label>
							<form:textarea path="radioInterpretation" rows="4" class="input-block-level"/>
						</div>
						</c:if>
						<div class="span12">
							<label><b>Radiographic impression</b></label>
							<form:textarea path="radioImpression" rows="4" class="input-block-level"/>
						</div>
						<div class="span12">
							<label><b>Recommendation</b></label>
							<form:textarea path="recommendations" rows="4" class="input-block-level"/>
						</div>
					</div>		

					
					<c:choose>
						<c:when test="${aCase.hasAmendedNotes}">
							<c:set value="block" var="showAmendmentNotes" />
							<c:set value="none" var="showAmendmentLink" />
						</c:when>
						<c:otherwise>
							<c:set value="none" var="showAmendmentNotes" />
							<c:set value="block" var="showAmendmentLink" />
						</c:otherwise>
					
					</c:choose>				
					<div  class="span12">
						<div class="span12" >
							<a id="amNotesLinkId" href="#" onclick="showAmendments();retrun;"  style="display:${showAmendmentLink}">Add Amendment</a>
							
							<div id="amNotesTextId" style="display:${showAmendmentNotes}">
							<label><b>Request Amendments</b></label>
							<form:textarea id="amNotesTxtArId" path="amendedVetNotes" rows="4" class="input-block-level"/>
							</div>
						</div>
					</div>
						
					<div class="span12">	
						<div style="height: 5px;"></div>		
						<div class="span12">
						 	<c:if test="${aCase.readingNotDone}">
								<button type="submit" name="saveDraft" class="btn" onclick="$.blockUI();">Save As Draft</button>
						 	</c:if>
							<button type="submit" name="saveComplete" class="btn" onclick="$.blockUI();"><font color="red">Save For Review</font></button>
				      		
						 </div>
						 
						 <c:if test="${aCase.readingComplete}">
						 <div class="span12">
						 	<br/><br/>
						 	
						 	
						 	
						 	
							<div class="span2">
						    	<a id="addChargesLinkId" href="${rootUrl}addCharges?caseId=${aCase.id}&requestId=${aCase.requestId}" onclick="$.blockUI();">Add Charges</a>
							</div>
							<div class="span2">
						  		<a id="addChargesLinkId" href="${rootUrl}addMilageFee?caseId=${aCase.id}&requestId=${aCase.requestId}" onclick="$.blockUI();">Milage Fee</a> &nbsp; &nbsp;
							</div>
							<div class="span2">
						  		<a id="addChargesLinkId" href="${rootUrl}addLateFee?caseId=${aCase.id}&requestId=${aCase.requestId}" onclick="$.blockUI();">Late Fee</a> &nbsp; &nbsp;
						  	</div>	
							
							
							<table id="table_id" class="table table-bordered">
							    <thead>
							        <tr>
							            <th>Service Name</th>
							            <th>Service Description</th>
							            <th>Price</th>
							            <th></th>
							        </tr>
							    </thead>
							    <tbody>
							       <c:forEach var="aCharge" items="${amiCharges}">
										<tr>
											<td>${aCharge.serviceName}</td>
											<td>${aCharge.serviceDescription}</td>
											<td>${aCharge.price}
												<c:choose>
													<c:when test="${aCharge.percentage}">
														(%)
													</c:when>
													<c:otherwise>
														($)
													</c:otherwise>
												</c:choose>
											</td>
											<td>
											
											<a id="deleteChargesLinkId" href="${rootUrl}deleteCharges?chargeId=${aCharge.id}&caseId=${aCase.id}">Delete</a>
											
											</td>
										
										
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
						</c:if> 
						 
						 <div style="height: 10px;">&nbsp;</div>	
					</div>	
					
				</div>
			</div>
			
		    
		</form:form>
	</body>
</html>

