<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="row">
	
	<div class="span12">
	
		<div class="span12">
			<table >
				<tr >
					<td> <form:label path="anesthesized" class="inline" >Anesthetized:${aCase.anesthesizedStringString}&nbsp;</form:label></td>
					<td> <form:label path="sedated" class="inline">Sedated:${aCase.sedatedString}&nbsp;</form:label></td>
					<td> <form:label path="fasted" class="inline">Fasted:${aCase.fastedString}&nbsp;</form:label></td>
					<td> <form:label path="enema" class="inline">Enema:${aCase.enemaString}&nbsp;</form:label></td>
					<td> <form:label path="painful" class="inline">Painful:${aCase.painfulString}&nbsp;</form:label></td>
					<td> <form:label path="fractious" class="inline">Fractious:${aCase.fractiousString}&nbsp;</form:label></td>
					<td> <form:label path="shocky" class="inline">Shocky:${aCase.shockyString}&nbsp;</form:label></td>
					<td> <form:label path="dyspneic" class="inline">Dyspneic:${aCase.dyspneicString}&nbsp;</form:label></td>
					<td> <form:label path="died" class="inline">Died:${aCase.diedString}&nbsp;</form:label></td>
					<td> <form:label path="euthanized">Euthanized:${aCase.euthanizedString}</form:label></td>
				</tr>
			</table>
			
		</div>
		<div class="span12">
			<pre><u><b>Exam</b></u><br/><form:label  path="consultation" >${aCase.consultation}</form:label></pre>
		</div>
		
		
	</div>
</div>