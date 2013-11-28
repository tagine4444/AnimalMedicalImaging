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
	<script src="${resourcesUrl}/media/js/jquery.blockUI.js"></script>	
	
	

    
<style>
.error 
{
	color: #ff0000;
	font-style: italic;
}
</style>


</head>
<body>


<%-- <%@ include file="toolBar.jsp" %> --%>

<%-- <div class="error">${errors}</div> --%>
<form:form id="form" method="post"  modelAttribute="hospital" >
<form:errors path="*" cssClass="error" />
   
   
	<div class="row">	
	
		<div class="navbar">
		    <div class="navbar-inner">
			    <a class="brand" ></a>
				    <ul class="nav">
				    	<li><a href="${rootUrl}">Home</a></li>
				    	<li><a href="${rootUrl}ourservices">Our Services</a></li>
				    	<li><a href="${rootUrl}contactus">Contact us</a></li>
				    	<li><a href="${rootUrl}aboutus">About us</a></li>
				    	<li class="active"><a href="${rootUrl}hospitalCreateAccount">Create an account</a></li>
				    	<li><a href="${rootUrl}login">Member Login</a></li>
				    </ul>
		    </div>
   		</div> 
		<div class="span12">
		<%@ include file="headerLogo.jsp" %>
		
		<div class="span12">
			<fieldset>
			    <legend>Create an Account</legend>
			    
			    <div class="span12">
			    	<h4>User</h4>
			    </div>
		   		<div class="span12">
			    	<div class="span2">
			    		<label>User</label>
			    		<form:input path="userName" class="input-medium" title="create a user"/>
		   			</div>
		   			<div class="span2">
			    		<label>Password</label>
			    		<form:input type="password" path="userPwd" class="input-medium" />
		   			</div>
		   			<div class="span2">
			    		<label>Confirm Password</label>
			    		<form:input type="password"  path="confirmUserPassword" class="input-medium" />
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
			    		<form:input id="occupationId" path="occupation" class="input-large" />
		   			</div>
		   		</div>
		   		
		   		<div class="span12">
			    	<div class="span3">
			    		<label>Email</label>
			    		<form:input  path="userEmail" class="input-large" />
		   			</div>
		   			<div class="span5">
			    		<label>Confirm Email</label>
			    		<form:input path="userConfirmEmail" class="input-large" />
		   			</div> 
		   		</div>	
		   		
		   		 <div class="span12">
			    	<h4>Hospital</h4>
			    </div>
			    <div class="span12">
				    <div class="span12">Hospital Name</div>
				    <div class="span7">
				    	<form:input path="name" class="input-large" />	
			   		</div>
		   		</div>
		   		<div class="span12">
				    <div class="span5">
				    		<span class="help-inline">Address</span>
			   		</div>
				    <div class="span7">
				    	<form:input path="address" class="input-xxlarge"  />
			   		</div>
		   		</div>
		   		
		   			
		   		<div class="span12">
			    	<div class="span2">
			    		<label>City</label>
			    		<form:input path="city" class="input-medium" />
		   			</div>
		   			<div class="span1">
			    		<label>State</label>
<%-- 			    		<form:select path="stateProvince" items="${countryList}" class="input-mini" /> --%>
			    		
			    		<form:select path="stateProvince" items="${usStates}" class="input-mini" />
		   			</div>
			    	<div class="span1">
			    		<label>Zip</label>
			    		<form:input path="zip" class="input-mini" />
		   			</div> 
			    	
			    	<div class="span2">
			    		<label>Country</label>
			    		<form:input path="country" class="input-medium" />
		   			</div>
		   		</div>	
		   		
		   		<div class="span8">
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
			    	<div class="span3">
			    		<label>Email</label>
			    		<form:input  path="hospitalEmail" class="input-large" />
		   			</div>
		   			<div class="span5">
			    		<label>Confirm Email</label>
			    		<form:input path="hospitalEmailConfirm" class="input-large" />
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
		   				<button type="submit" name="saveHospital" value="saveHospital" onclick="$.blockUI();" class="btn">Submit</button>
	   				</div>
		   		</div>
		   		
		   </fieldset>
	  	</div>
	  	</div>
	</div>	  	
	  	
	  	
</form:form>

</body>
</html>
