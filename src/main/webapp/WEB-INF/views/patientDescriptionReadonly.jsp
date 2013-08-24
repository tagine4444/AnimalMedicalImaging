<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<link rel="stylesheet" href="/resources/demos/style.css" />

<div class="row">
	
   		<div class="span12">
	    	<div class="span2">
	    		<label><b>Patient Name</b></label>
	    		<span>${aCase.patientName}</span>
   			</div>
   			<div class="span2 age">  
	 			<label><b>Age</b></label>
		 		<span>${aCase.patientAge}</span>
   			</div>
   		
	    	<div class="span2">
	    		<label><b>Species</b></label>
				<span>${aCase.patientSpecies}</span>
   			</div>
   			
   			<div class="span2"> 
				<label><b>Breed</b></label>
				<span>${aCase.patientBreed}</span>
			</div>
			
			<div class="span1">
	    		<label><b>Sex</b></label>
	    		<span>${aCase.patientSex}</span>
   			</div>
			
   			<div class="span2">
   				<label><b>Weight</b></label>
   				<span>${aCase.patientWeight} ${aCase.patientWeightUom}</span>
			</div>
   		</div>
</div>


