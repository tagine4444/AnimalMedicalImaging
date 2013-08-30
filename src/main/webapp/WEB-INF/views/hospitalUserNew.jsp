<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:url value="/" var="rootUrl"/>
<c:url value="/resources" var="resourcesUrl"/>

<html>
<head>
	<title>Animal Medical Imaging</title>
    <!-- Bootstrap -->
    <link href="${resourcesUrl}/media/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
   

 	<link rel="stylesheet" href="${resourcesUrl}/media/css/jquery-ui-1.9.2.custom.css" />
	<script src="${resourcesUrl}/media/js/jquery-1.8.3.js"></script>
	<script src="${resourcesUrl}/media/js/jquery-ui-1.9.2.custom.js"></script>
	<script src="${resourcesUrl}/media/js/serviceRequest.js"></script>	
<style>
.error 
{
	color: #ff0000;
	font-style: italic;
}
</style>
</head>
<body>

	<div class="navbar">
		 	<div class="navbar-inner">
		    	<a class="brand" href="#">
<%-- 		    		<img src="${resourcesUrl}/img/ami_logo.jpg" alt="AMI Logo" width="140px" height="10px"> --%>
		    	</a>
		    	<ul class="nav">
		    	  <li><img src="${resourcesUrl}/img/ami_logo.jpg" alt="AMI Logo" >&nbsp;</li>
			      <li ><a href="${rootUrl}serviceRequest">Request Interpretation</a></li>
			      <li ><a href="${rootUrl}hospitalPendingRequest">Pending Interpretations</a></li>
			      <li ><a href="${rootUrl}hospitalInterpretation">Completed Interpretation</a></li>
			      <li ><a href="${rootUrl}searchRequest">Search</a></li>
			      <li class="active"><a href="${rootUrl}hospitalProfileUpdate" >Update Profile</a></li>
			      
			      
			      <li><a href="#" onclick="alert('Not implemented yet');">Help</a></li>
			       <li><a href="${rootUrl}login?logout">Logout</a></li>
			       
		    	</ul>
		  	</div>
		</div>


<form:form id="form" method="post"  modelAttribute="newAmiUser" >
<form:errors path="*" cssClass="error" />
   
   
	<div class="row">
	<div class="span12">	
		<div class="span12">
			<div class="error">${message}</div>
			<fieldset>
			    <legend>Edit User</legend>
			    
		   		<div class="span12">
			    	<div class="span2">
			    		<label>User</label>
			    		<form:input path="userName" class="input-medium" title="create a user"/>
		   			</div>
		   			<div class="span2">
			    		<label>Password</label>
			    		<form:input type="password" path="pwd" class="input-medium" />
		   			</div>
		   			<div class="span2">
			    		<label>Confirm Password</label>
			    		<form:input type="password"  path="confirmPassword" class="input-medium" />
		   			</div> 
		   		</div>
		   		<div class="span12">
			    	<div class="span2">
			    		<label>First Name</label>
			    		<form:input path="firstName" class="input-medium" title="First Name"/>
		   			</div>
		   			<div class="span2">
			    		<label>Last Name</label>
			    		<form:input path="lastName" class="input-medium" title="First Name"/>
		   			</div>
		   			<div class="span2">
			    		<label>Occupation</label>
			    		<form:input id="occupationId" path="occupation" title="Occupation" class="input-large" />
		   			</div>
		   		</div>
		   		
		   		<div class="span12">
			    	<div class="span3">
			    		<label>Email</label>
			    		<form:input  path="email" class="input-large" />
		   			</div>
		   			<div class="span5">
			    		<label>Confirm Email</label>
			    		<form:input path="confirmEmail" class="input-large" />
		   			</div> 
		   		</div>	
		   		
		   		<div class="span12">
		   			<div class="span2">
		   				<button type="submit" name="saveNewUser" class="btn">Submit</button>
	   				</div>
		   		</div>
		   		
		   </fieldset>
	  	</div>
	</div>  	
	</div>	  	
	  	
	  	
</form:form>

</body>
</html>
