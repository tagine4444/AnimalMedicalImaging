<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<div class="span12">
		
		<div class="span3">
			<c:choose>
				<c:when test="${aCase.interpretationOnly}">
					<h4>Interpretation only</h4>
				</c:when>
			</c:choose>
			
			<c:choose>
				<c:when test="${aCase.stat}">
					<h4><span style="color:red">STAT</span></h4>
				</c:when>
			</c:choose>
		</div>	
	</div>

			
	<div class="span12">
	<c:choose>
	<c:when test="${aCase.interpretationOnly}">
	</c:when>
	<c:otherwise>

		<div class="span12">
			<table class="table table-bordered">
			<tr>
				<th>MRI</th>
				<th>Contrast Radiography</th>
				<th>Computed Tomography</th>
				<th>Radiography/Fluoroscopy</th>
				<th>Ultrasound</th>
			</tr>
			<tr>
				<td>${aCase.mriSvc}</td>
				<td>${aCase.contrastRadiographySvc}</td>
				<td>${aCase.computedTomographySvc}</td>
				<td>${aCase.radiographyFluoroscopy }</td>
				<td>${aCase.ultrasoundSvc}</td>
			</tr>	
			</table>	
			
		</div>
		
	</c:otherwise>
	</c:choose>	
		
		
	</div>
</div>