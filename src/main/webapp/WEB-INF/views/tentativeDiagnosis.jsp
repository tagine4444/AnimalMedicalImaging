<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="row">
	<fieldset>
	<div class="span12">
	<legend>Tentative Diagnosis <form:errors path="tentativeDiagnosis" cssClass="error" /></legend>
	
	<div class="span12">
	
		<div class="span12">
			<form:textarea path="tentativeDiagnosis"  rows="4" class="input-block-level"/>
		</div>
		<br/>
		
	</div>
	</div>
	</fieldset>
</div>