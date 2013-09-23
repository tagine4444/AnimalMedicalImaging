<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<c:url value="/" var="rootUrl" />
<c:url value="/resources" var="resourcesUrl" />

<html>
<head>
<title>Animal Medical Imaging</title>
<meta charset="utf-8" name="viewport"
	content="width=device-width, initial-scale=1.0">
<link href="${resourcesUrl}/media/bootstrap/css/bootstrap.css"
	rel="stylesheet" media="screen">
<%-- 	    <link href="${resourcesUrl}/media/css/ami.css" rel="stylesheet" media="screen"> --%>


<script src="${resourcesUrl}/media/js/jquery-1.8.3.js"></script>
<script src="${resourcesUrl}/media/js/serviceRequest.js"></script>

<style>
.error {
	color: #ff0000;
	font-style: italic;
}
</style>
</head>

<body>
	<div class="navbar">
		<div class="navbar-inner">
			<a class="brand" href="#"><img
				src="${resourcesUrl}/img/ami_logo.jpg" alt="AMI Logo" width="140px"
				height="10px"></a>
			<ul class="nav">
				<li><a href="${rootUrl}requestDashboard">Process Requests</a></li>
				<li><a href="${rootUrl}caseDashboard">Verify Requests</a></li>
				<li><a href="${rootUrl}amiInterpretation">Requests
						Completed Today</a></li>
				<li><a href="${rootUrl}searchRequestAmi">Search</a></li>
				<li class="active"><a href="${rootUrl}hospitalAdmin">Hospital
						Admin</a></li>
				<li><a href="${rootUrl}amiServices">Services</a></li>
				<li><a href="${rootUrl}login?logout">Logout</a></li>
			</ul>
		</div>
	</div>

	<%@ include file="metadata.jsp"%>
	<div class="span12">
		<fieldset>
			<legend>
				<span style="color: green;"><strong><%=hosptitalName%></strong></span>
			</legend>
		</fieldset>
	</div>
	<%-- <div class="error">${errors}</div> --%>
	<form:form id="form" method="post" action="hospitalProfileUpdateAdminHospital" modelAttribute="hospital">


		<div class="row">
			<div class="span12">
				<div class="span12">
					<span style="color: red">${message}</span>
					<form:errors path="*" cssClass="error" />

					<fieldset>

						<form:hidden path="id" />
						<form:hidden path="name" />

						<div class="span12">
							<div class="span12">
								<h3>Update ${hospital.name}</h3>
<!-- 								<br/> -->
							</div>
						</div>	
						<div class="span12">
							<div class="span2">
								<label>Phone Number</label>
								<form:input path="office" class="input-medium" />
							</div>
							<div class="span2">
								<label>Back line</label>
								<form:input path="backLine" class="input-medium" />
							</div>
							<div class="span2">
								<label>Fax</label>
								<form:input path="fax" class="input-medium" />
							</div>
						</div>
						<div class="span12">
							<div class="span2">
								<label>Email</label>
								<form:input path="hospitalEmail" class="input-xxlarge" />
							</div>
						</div>	
						
						<div class="span12">

							<div class="span12">
								<label>Address</label>
								<form:input path="address" class="input-xxlarge" />
							</div>
						</div>	
						<div class="span12">

							
							<div class="span2">
								<label>City</label>
								<form:input path="city" class="input-medium" />
							</div>
							<div class="span2">
								<label>Zip Code</label>
								<form:input path="stateProvince" class="input-medium" />
							</div>
							<div class="span2">
								<label>Country</label>
								<form:input path="country" class="input-medium" />
							</div>
						</div>
						<div class="span12">
							<div class="span2">
								<label>Lab</label>
								<form:select path="lab" items="${labsList}" class="input-medium" />
							</div>

							<div class="span5">
								<label>Lab Account</label>
								<form:input path="labAccount" class="input-medium" />
							</div>
						</div>
						
						<div class="span12">
							<div class="span2">	
								<label class="radio inline input-small	">
								<form:radiobutton value="true" path="underContract"  />Contract</label>
								
								<label class="radio input-large">
								<form:radiobutton value="false" path="underContract"  />Non Contract</label>
							</div>
							<div class="span2">
					    		<label>Acronym</label>
				    			<form:input path="acronym" class="input-small" />
				   			</div>
				   			
				   			<div class="span2">
					    		<label>Priority</label>
								<form:select path="priority" class="input-medium" >
									<form:option value="0" label="Normal"/>
									<form:option value="1" label="High"/>
				   				 </form:select>
				   			</div>
				   			
				   			<div class="span2">
					    		<label>Billing Cycle</label>
				    			<form:input path="billingCycle" class="input-small" />
				   			</div>

						</div>
						<div class="span12">
							<div class="span3">
								<button type="submit" name="updateHospital" value="${hospital.id}" class="btn">Update Hospital</button>
								<button type="submit" name="" value="" class="btn"  onclick="history.go(-1);return false;">Cancel</button>
							</div>
							<br />
							<br />
							<br />
						</div>

					</fieldset>
				</div>
			</div>
		</div>


	</form:form>

</body>
</html>
