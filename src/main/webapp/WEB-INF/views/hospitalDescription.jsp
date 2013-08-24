<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div class="row span12">
<!--    		<div class="span12"> -->
   			<div class="span2">
	    		<label>Veterinarian</label>
	    		<form:input path="veterinarian" class="input-medium" title="Veterinarian" />
	    		 <br/><form:errors path="veterinarian" cssClass="error" />
   			</div>
   			<div class="span2">
	    		<label>Requested By 
	    		
	    		<a id="requestedbyPopover" href="#" data-toggle="popover" tabindex="-1"
						title="" data-content="Enter your name. This form is requested by you as you are entring the data" 
						data-original-title="Who is the Requester?"
						data-trigger="hover">Help</a>
	    		
										</label>
	    		<form:input path="requestBy" class="input-medium" title="The name of the peron filling out this form" />
   				 <br/><form:errors path="requestBy" cssClass="error" />
   			</div>
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
   		
	    	<div class="span2">
	    		<label>Lab</label>
				<form:select path="lab" items="${labsList}" class="input-medium" />
   			</div>
   			<div class="span2">
	    		<label>Lab Account#</label>
	    		<form:input path="labAccount" class="input-medium" title="Account#" />
   			</div>
<!--    		</div> -->
<!-- 	</fieldset> -->
</div>