<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="span12 row">
	
<%-- 	<form:textarea disabled="true" path="tentativeDiagnosis"  rows="4" class="input-block-level"/> --%>
	<pre><u><b>Tentative Diagnosis</b></u><br/><form:label path="tentativeDiagnosis" >${aCase.tentativeDiagnosis}</form:label></pre>
</div>