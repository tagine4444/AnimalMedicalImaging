<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="row">
<fieldset>
	<div class="span12">
	<legend>Exam <form:errors path="consultation" cssClass="error" /></legend>
	
	
	<div class="span12">
	
		<div class="span12">
			<label class="checkbox inline">
			<form:checkbox path="anesthesized" id="inlineCheckbox1" value="Anesthetized?" />Anesthetized? </label>
			
			<label class="checkbox inline">
			<form:checkbox path="sedated" />Sedated? </label>
			
			<label class="checkbox inline">
			<form:checkbox path="fasted" />Fasted? </label>
			
			<label class="checkbox inline">
			<form:checkbox path="enema" />Enema?</label>
			
			<label class="checkbox inline">
			<form:checkbox path="painful" />Painful?</label>
			
			<label class="checkbox inline">
			<form:checkbox path="fractious" />Fractious?</label>

			
			<label class="checkbox inline">
			<form:checkbox path="shocky" />Shocky?</label>

						
			<label class="checkbox inline">
			<form:checkbox path="dyspneic" />Dyspneic?</label>
						
			<label class="checkbox inline">
			<form:checkbox path="died" />Died?</label>

			
			<label class="checkbox inline">
			<form:checkbox path="euthanized" />Euthanized?</label>
		</div>
		<div class="span12">
			<strong>HISTORY, CHIEF COMPLAIN, PHYSICAL FINDINGS (S.O.A.P)</strong>. Please provide a case <u>summary</u>, <strong>not the entire record.</strong>
		</div>
		<div class="span12">
			<form:textarea path="consultation" rows="4" class="input-block-level"/>
		</div>
		
		
	</div>
	</div>
</fieldset>
</div>