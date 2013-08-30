<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="row">
	<div class="span12">
	<fieldset>
	<div class="span12">
	<div class="span12">
	<legend>Services</legend>
	<div class="span12">
	
			<label class="radio inline input-medium">
			<form:radiobutton value="true" path="interpretationOnly" onchange="toggleServiceType();" />Interpretation only</label>
			
			<label class="radio inline input-large">
			<form:radiobutton value="false" path="interpretationOnly" onchange="toggleServiceType();" />Imaging (includes interpretation)</label>
			
			<label class="radio inline input-medium">
				<form:checkbox path="stat"/>
			  <b><span style="color:purple">STAT</span></b>
			</label>
	</div>
	
	<div class="span12">
		<br/>
		<div class="span12">
			Please only select the services you want Animal Medical Imaging to perform. <br/>
		</div>
		
		<div class="span12">
<!-- 			<div class="span4"> -->
<!-- 				<label><strong>MRI</strong></label> -->
<%-- 				<form:select path="mriSvc" items="${MRISvcList}"  --%>
<%-- 					class="input-xlarge imagingSvc" disabled="${true}" /> --%>
<!-- 			</div> -->
			<div class="span4">
				<label><strong>Contrast Radiography</strong></label>
				<form:select path="contrastRadiographySvc" items="${contrastRadiographySvcList}" 
					class="input-xlarge imagingSvc" disabled="${true}" />
			</div>
			
			<div class="span3">
				<label><strong>Computed Tomography</strong></label>
				<form:select path="computedTomographySvc" items="${computedTomographySvcList}" 
					class="input-xlarge imagingSvc" disabled="${true}" />
			</div>
		</div>	
		<br/><br/><br/>
		<div class="span12">
		<div class="span4">
			<label><strong>Radiography/Fluoroscopy</strong></label>
			<form:select path="radiographyFluoroscopy" items="${radiographyFluoroscopySvcList}" 
				class="input-xlarge imagingSvc" disabled="${true}" />
		</div>
		<div class="span4">
			<label><strong>Ultrasound</strong></label>
			<form:select path="ultrasoundSvc" items="${ultrasoundSvcList}" 
				class="input-xlarge imagingSvc" disabled="${true}" />
		</div>
		
		</div>
	</div>
	</div>
	</div>
	</fieldset>
	</div>
</div>