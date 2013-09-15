<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div class="row span12">
   		<div class="span12">
   		 <legend>Hospital  <span style="color:green;"><strong>(${serviceRequest.hospitalName})</strong></span></legend>	
   			
   			<div class="span12">
	   			<div class="span2">
		    		<label>Veterinarian</label>
		    		<form:input path="veterinarian" class="input-medium" title="Veterinarian" />
		    		 <br/><form:errors path="veterinarian" cssClass="error" />
	   			</div>
	   			
	   			<div class="span2">
		    		<label>
		    		Your Name
		    		<a id="requestedbyPopover" href="#" data-toggle="popover" tabindex="-1"
							title="" data-content="It helps us know who filled out this form on behalf of the veterinarian. 
							Please enter your name if you are the veterinarian" 
							data-original-title="Your First & Last Name e.g: Sarah Smith"
							data-trigger="hover">Help</a>
		    		
											</label>
		    		<form:input path="requestBy" class="input-medium" title="The name of the peron filling out this form" placeholder="Refer to Help link"/>
	   				 <br/><form:errors path="requestBy" cssClass="error" />
	   			</div>
   			
   				<div class="span2">
		    		<label>Lab</label>
					<form:select path="lab" items="${labsList}" class="input-medium" />
	   			</div>
	   			<div class="span4">
		    		<label>Lab Account#</label>
		    		<form:input path="labAccount" class="input-medium" title="Account#" />
	   			</div>
   			</div>
   			
   			<div class="span12">
	   			<div class="span2">
		    		<label>Client First Name</label>
		    		<form:input path="clientFirstName" class="input-medium" title="Client First Name" />
	   				<br/><form:errors path="clientFirstName" cssClass="error" />
	   			</div> 
	   			<div class="span2">
		    		<label>Client Last Name</label>
		    		<form:input path="clientLastName" class="input-medium" title="Client Last Name" />
	   				<br/><form:errors path="clientLastName" cssClass="error" />
	   			</div> 
	   			<div class="span2">
		    		<label>Client Id#</label>
		    		<form:input path="clientId" class="input-medium" title="Client Id#" />
		    		<br/><form:errors path="clientId" cssClass="error" />
	   			</div>
	   			<div class="span3">
		   			<div class="control-group">
				    <label class="control-label">Is the client an employee?</label>
				
				    <div class="controls">
					   <div class="btn-group" data-toggle="buttons-radio">
						   <label class="radio inline input-micro">
						   <form:radiobutton value="true" path="clientHospitalEmployee"  />Yes</label>
								
							<label class="radio inline input-mini">
							<form:radiobutton value="false" path="clientHospitalEmployee"  />No</label>
					  </div>
				    </div>
				</div>
				
			  </div>
   			</div>
   			<c:if test="${!serviceRequest.underContract}">
   				<div class="span12">
		   			<div class="span12">
			    		<label>Client Address</label>
			    		<form:input path="clientAddress" class="input-xxlarge" title="Client Address" />
			    		<br/><form:errors path="clientAddress" cssClass="error" />
		   			</div>
		   			<div class="span2">
			    		<label>Client Home Phone</label>
			    		<form:input path="clientHomePhone" class="input-medium" title="Client Home Phone" />
			    		<br/><form:errors path="clientHomePhone" cssClass="error" />
		   			</div>
		   			<div class="span2">
			    		<label>Client Cell Phone</label>
			    		<form:input path="clientCell" class="input-medium" title="Client Cell Phone" />
			    		<br/><form:errors path="clientCell" cssClass="error" />
		   			</div>
		   		</div>	
   			</c:if>
	    	
   		</div>
<!-- 	</fieldset> -->
</div>