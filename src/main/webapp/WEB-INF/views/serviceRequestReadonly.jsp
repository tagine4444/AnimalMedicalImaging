<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
		
<%-- <%@ include file="hospitalDescriptionReadonly.jsp" %> --%>

<style>
table,th, td
{
	width:1005;
}

</style>

<div class="row">
	<div class="span12">
	
	
								
	<div class="span12">
		<table class="table table-bordered">
			        <tr>
			            <th>Documents</th>
<!-- 			            <th>Is Employee</th> -->
			            <th>Veterinarian</th>
			            <th>Patient</th>
			            <th>Species</th>
			            <th>Breed</th>
			            <th>Sex</th>
			            <th>Weight</th>
			             
			            <th>Lab/Lab Id</th>
			            <th>Requested</th>
			           <th>Completed</th>
			        </tr>
					<tr>
						<td>
							<c:if test="${aCase.hasDocuments}">
								<a href="${rootUrl}uploadedDocuments?svcReqId=${aCase.requestId}">Uploaded Documents</a>
							</c:if>
						</td>
<!-- 						<td> -->
<%-- 							<c:choose> --%>
<%-- 								<c:when test="${aCase.hospitalEmployee}"> --%>
<!-- 									<b><font color="red"> Yes</font></b> -->
<%-- 								</c:when> --%>
<%-- 								<c:otherwise> --%>
<!-- 									No -->
<%-- 								</c:otherwise> --%>
<%-- 							</c:choose> --%>
<!-- 						</td> -->
						<td>${aCase.veterinarian}</td>
						<td>${aCase.patientName}</td>
						<td>${aCase.patientSpecies} </td>					
						<td>${aCase.patientBreed} </td>					
						<td>${aCase.patientSex}  </td>					
						<td>${aCase.patientWeight} ${aCase.patientWeightUom}</td>		
						
			            
			            <td>${aCase.lab}/${aCase.labAccount} </td>
			            
			            <td><joda:format value="${aCase.requestDate}" pattern="MM/dd/yy" /></td>		
			            <td><joda:format value="${aCase.transcriptionCompleteDate}" pattern="MM/dd/yy" /></td>	
					</tr>
			</table>
			
			
			
			<table class="table table-bordered">
			        <tr>
			            
<!-- 			            <th>Documents</th> -->
<!-- 			            <th>Veterinarian</th> -->
			            <th>Client Name</th>
			            <th>Client ID</th>
			            <th>Is client an employee?</th>
<!-- 			            <th>Patient</th> -->
<!-- 			            <th>Age</th> -->
<!-- 			            <th>Species</th> -->
<!-- 			            <th>Breed</th> -->
<!-- 			            <th>Sex</th> -->
<!-- 			            <th>Weight</th> -->
			             
<!-- 			            <th>Lab</th> -->
<!-- 			            <th>Requested</th> -->
<!-- 			           <th>Completed</th> -->
			        </tr>
					<tr>
<!-- 						<td> -->
<%-- 							<c:if test="${aCase.hasDocuments}"> --%>
<%-- 								<a href="${rootUrl}uploadedDocuments?svcReqId=${aCase.requestId}">Uploaded Documents</a> --%>
<%-- 							</c:if> --%>
<!-- 						</td> -->
						
<%-- 						<td>${aCase.veterinarian}</td> --%>
						
						<td>${aCase.clientLastName}, ${aCase.clientFirstName} </td>					
						<td>${aCase.clientId}</td>					
						<td>
							<c:choose>
								<c:when test="${aCase.hospitalEmployee}">
									<b><font color="red"> Yes</font></b>
								</c:when>
								<c:otherwise>
									No
								</c:otherwise>
							</c:choose>
						</td>					
<%-- 						<td>${aCase.patientName}</td>					 --%>
<%-- 						<td>${aCase.patientAge}</td>					 --%>
<%-- 						<td>${aCase.patientSpecies} </td>					 --%>
<%-- 						<td>${aCase.patientBreed} </td>					 --%>
<%-- 						<td>${aCase.patientSex} </td>					 --%>
<%-- 						<td>${aCase.patientWeight} ${aCase.patientWeightUom}</td>		 --%>
						
			            
<%-- 			            <td>${aCase.lab} - ${aCase.labAccount} </td> --%>
			            
<%-- 			            <td><joda:format value="${aCase.requestDate}" pattern="MM/dd/yy" /></td>		 --%>
<%-- 			            <td><joda:format value="${aCase.transcriptionCompleteDate}" pattern="MM/dd/yy" /></td>	 --%>
					</tr>
			</table>
	</div>
			
</div>
</div>
	
<%-- <%@ include file="patientDescriptionReadonly.jsp" %>  --%>

<%@ include file="servicesReadonly.jsp" %>
		
<%@ include file="consultationReadonly.jsp" %>

<%@ include file="tentativeDiagnosisReadonly.jsp" %>
			
