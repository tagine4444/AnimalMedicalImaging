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
			           <th>Requested</th>
			           <th>Completed</th>
			            <th>Client</th>
			            <th>Patient</th>
			            <th>Age</th>
			            <th>Species</th>
			            <th>Breed</th>
			            <th>Sex</th>
			            <th>Weight</th>
			             
			            <th>Veterinarian</th>
			            <th>Lab</th>
			        </tr>
					<tr>
						<td><joda:format value="${aCase.requestDate}" pattern="MM/dd/yy" /></td>
						<td><joda:format value="${aCase.transcriptionCompleteDate}" pattern="MM/dd/yy" /></td>
						<td>${aCase.clientLastName}, ${aCase.clientFirstName} ${aCase.clientId}</td>					
						<td>"${aCase.patientName}" </td>					
						<td>"${aCase.patientAge}" </td>					
						<td>${aCase.patientSpecies} </td>					
						<td>${aCase.patientBreed} </td>					
						<td>${aCase.patientSex} </td>					
						<td>${aCase.patientWeight} ${aCase.patientWeightUom}</td>		
						
			            <td>${aCase.veterinarian}</td>
			            <td>${aCase.lab} - ${aCase.labAccount} </td>			
					</tr>
			</table>
	</div>
			
<!-- 	<div class="span6"> -->
<!-- 		<table id="table_id" class="table"> -->
<!-- 			    <thead> -->
<!-- 			        <tr> -->
			            
<!-- 			            <th>Hospital</th> -->
<!-- 			            <th>Veterinarian</th> -->
<!-- 			            <th>Lab</th> -->
<!-- 			        </tr> -->
<!-- 			    </thead> -->
<!-- 			    <tbody> -->
<!-- 					<tr> -->
<%-- 			            <td>${aCase.hospitalName}</td> --%>
<%-- 			            <td>${aCase.veterinarian}</td> --%>
<%-- 			            <td>${aCase.lab} - ${aCase.labAccount} </td> --%>
<!-- 					</tr> -->
<!-- 			    </tbody> -->
<!-- 			</table> -->
<!-- 		</div>	 -->
		
</div>
</div>
	
<%-- <%@ include file="patientDescriptionReadonly.jsp" %>  --%>

<%@ include file="servicesReadonly.jsp" %>
		
<%@ include file="consultationReadonly.jsp" %>

<%@ include file="tentativeDiagnosisReadonly.jsp" %>
			
