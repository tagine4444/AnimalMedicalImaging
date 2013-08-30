<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!--  <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.1/themes/base/jquery-ui.css" /> -->
<!-- <script src="http://code.jquery.com/jquery-1.9.1.js"></script> -->
<!-- <script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script> -->
<!-- <link rel="stylesheet" href="/resources/demos/style.css" /> -->

<%-- <link href="${resourcesUrl}/media/bootstrap/css/bootstrap.css" rel="stylesheet" media="screen"> --%>

<%-- 	<link rel="stylesheet" href="${resourcesUrl}/media/css/jquery-ui-1.9.2.custom.css" /> --%>
	 
<%-- 	<script src="${resourcesUrl}/media/bootstrap/js/bootstrap.js"></script> --%>
<%-- 	<script src="${resourcesUrl}/media/js/jquery-1.8.3.js"></script> --%>
<%-- 	<script src="${resourcesUrl}/media/js/jquery-ui-1.9.2.custom.js"></script> --%>
<%-- 	<script src="${resourcesUrl}/media/js/serviceRequest.js"></script>	 --%>
<%-- 	<script src="${resourcesUrl}/media/js/jquery.blockUI.js"></script>	 --%>



<script type="text/javascript">

// 	$(document).ready(function () {
// 	    $('#id1').popover('hide');
		
// 	});



var speciesBreedMap = {};
<c:forEach items="${speciesBreedMap}" var="entry">  
	var breedArray = [];
	<c:forEach items="${entry.value}" var="breed">  
		breedArray.push('<c:out value="${breed}" />');
	</c:forEach>
	speciesBreedMap['<c:out value="${entry.key}" />'] = breedArray;
</c:forEach>

function updateBreedList(value) {
	var selectBreed = $('.breed');
	
	// Remove all options
	selectBreed.find('option').remove();
	
	// Add default options
	selectBreed.append($('<option value=""></option>'));
	selectBreed.append($('<option value="Unknown">Unknown</option>'));
	
	// Disable the breed dropdown if no species is selected.
	selectBreed.attr("disabled", value.length == 0);
	
	// Add breed list for the selected species
	if (value) {
		var breedOptions = speciesBreedMap[value] || [];
		for (var i=0; i<breedOptions.length; i++) {
			var breed = breedOptions[i];
			selectBreed.append($('<option></option>').attr('value',breed).text(breed)); 
		}
	} 
}

$(function() {
	var selectedSpeciesValue = $('.species').val();
	var selectedBreedOption = $('.breed option:selected');
	if (selectedSpeciesValue && selectedSpeciesValue.length > 0) {
		updateBreedList(selectedSpeciesValue);
		$('.breed').val(selectedBreedOption.val() || '');
	} else {
		$('.breed').attr('disabled', true);
	}
	
});



</script>

<div class="row">
										
	<div class="span12">
	<fieldset>
   		<div class="span12">
   		<div class="span12">
		<legend>Patient</legend>
   		<div class="span12">
	    	<div class="span2">
	    		<label>Patient Name </label>
	    		<form:input path="patientName" class="input-medium" title="Patient Name" /><br/>
	    		<form:errors path="patientName" cssClass="error" />
   			</div>
<!--    			<div class="span2 age">   -->
   			<div class="span2">  
				
	 			<label>Age <a id="id1" href="#" data-toggle="popover" tabindex="-1"
						title="" data-content="Use d for Day, m for Month and y for Year. 
						For instance, 3 Years is 3y. 3 Years and 2 Months is 3y2m. 6 Days 6d. 2 Months and 10 Days is 2m10d. 2 Years 3 Months and 10 Days is 2y3m10d" 
						data-original-title="How to enter the patient's age?"
						data-trigger="hover">Help</a></label>  
										
										
										
<%-- 		 		<form:input id="datepicker" path="patientAge" class="input-medium" placeholder="1y3m2d" title="Age Only Select Month and Year" onchange="renderAge(this.value);"/> --%>
   				<form:input path="patientAge" class="input-medium" title="Patient Age" placeholder="Refer to Help link" />
   				
   				<c:if test="${!empty ageError}"> 
   					<br/>
   					<span class="error"> ${ageError}</span>. Click on the Help Link  above for help.
   				</c:if>
   				
   			</div>
   		
	    	<div class="span2">
	    		<label>Species</label>
				<form:select path="patientSpecies" class="input-medium species" onchange="updateBreedList(this.value);">
					<form:option value="" label=""/>
					<form:option value="Unknown" label="Unknown"/>
   				 	<form:options items="${speciesList}" />
   				 </form:select>
	    		 <br/><form:errors path="patientSpecies" cssClass="error" />
   			</div>

   			<div class="span2"> 
				<label>Breed</label>
				<form:select path="patientBreed" class="input-medium breed">
					<form:option value="" label=""/>
					<form:option value="Unknown" label="Unknown"/>
   				 	<form:options items="${breedList}" />
   				</form:select>
   				<br/><form:errors path="patientBreed" cssClass="error" />
			</div>
			
			<div class="span1">
	    		<label>Sex</label>
	    		<form:select path="patientSex" class="input-mini" >
	    			<form:option value="" label="" />
	    			<form:option value="FS" label="F/s" />
	    			<form:option value="MC" label="M/c" />
	    			<form:option value="F" label="F" />
	    			<form:option value="M" label="M" />
	    		</form:select>
	    		<br/><form:errors path="patientBreed" cssClass="error" />
   			</div>
			
   			<div class="span2">
   				<label>Weight</label>
				<form:input path="patientWeight" class="input-mini" placeholder="Weight" />
				<form:select path="patientWeightUom" class="input-mini" >
					  <form:option value="LB" label="LB" />
					  <form:option value="KG" label="KG" />
				</form:select>
				<br/><form:errors path="patientWeight" cssClass="error" />
			</div>
   		</div>
   		</div>
   		</div>
	</fieldset>
	</div>
</div>


