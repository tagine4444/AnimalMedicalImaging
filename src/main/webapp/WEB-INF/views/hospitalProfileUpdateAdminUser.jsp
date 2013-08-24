<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:url value="/" var="rootUrl"/>
<c:url value="/resources" var="resourcesUrl"/>

<html>
	<head>
		<title>Animal Medical Imaging</title>
	    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1.0">
	    <link href="${resourcesUrl}/media/bootstrap/css/bootstrap.css" rel="stylesheet" media="screen">
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
    	<a class="brand" href="#"><img src="${resourcesUrl}/img/ami_logo.jpg" alt="AMI Logo" width="140px" height="10px"></a>
    	<ul class="nav">
	      <li><a href="${rootUrl}requestDashboard">Process Requests</a></li>
		  <li><a href="${rootUrl}caseDashboard">Verify Requests</a></li>
	      <li ><a href="${rootUrl}amiInterpretation" >Requests Completed Today</a></li>
	      <li ><a href="${rootUrl}searchRequestAmi">Search</a></li>
	      <li class="active"><a href="${rootUrl}hospitalAdmin" >Hospital Admin</a></li>
	      <li ><a href="${rootUrl}amiServices" >Services</a></li>
	      <li><a href="${rootUrl}login?logout">Logout</a></li>
    	</ul>
  	</div>
</div>

<%@ include file="metadata.jsp" %>
<div class="span12">
	<fieldset>
		<legend><span style="color:green;"><strong><%=hosptitalName %></strong></span></legend>
	</fieldset>
</div>
<%-- <div class="error">${errors}</div> --%>
<form:form id="form" method="post"  modelAttribute="amiUser" >
   
   
	<div class="row">	
	<div class="span12">
		<div class="span12">
			<span style="color: red">${message}</span>
			<form:errors path="*" cssClass="error" />
			
			<fieldset>
			    
<%-- 		   		<legend>Update User: ${amiUser.userName}</legend> --%>
		   		
		   		
		   		<div class="span12">
		   		
		   		<div class="span12">
					<div class="span12">
						<h3>User Name (Login): ${amiUser.userName}</h3>
					</div>
					<br/><br/>
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
			    		<label>Password</label>
			    		<form:input type="password" path="pwd" class="input-medium" />
		   			</div>
		   			<div class="span2">
			    		<label>Confirm Password</label>
			    		<form:input type="password"  path="confirmPassword" class="input-medium" />
		   			</div> 
	   			<div class="span12">
		   			<button type="submit" name="updateUser" class="btn">Update User</button>
		   			<button type="submit" name="" value="" class="btn"  onclick="history.go(-1);return false;">Cancel</button>
		   		</div>
		   		</div>
			  	</div>
		   		
		   </fieldset>
	  	</div>
	  </div>	
	</div>	  	
	  	
	  	
</form:form>

</body>
</html>
