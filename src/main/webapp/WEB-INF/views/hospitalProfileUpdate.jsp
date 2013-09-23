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
		<link rel="stylesheet" href="${resourcesUrl}/media/css/ami.css" />	
		
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
			       <li><a href="${rootUrl}faq">Help</a></li>
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
<form:form id="form" method="post"  modelAttribute="hospital" >
   
   
	<div class="row">	
	<div class="span12">
		<div class="span12">
			<span style="color: red">${message}</span>
			<form:errors path="*" cssClass="error" />
			
			<fieldset>
			    <legend>Update Hospital</legend>
			    
			    <form:hidden path="Id" />
			    
			    <div class="span12">
		   			
		   			<div class="span2">
			    		<label>Lab</label>
						<form:select path="lab" items="${labsList}" class="input-medium" />
		   			</div>
					<div class="span2">
			    		<label>Lab Account</label>
			    		<form:input path="labAccount" class="input-medium" />
		   				
<%-- 		   				<a href="${rootUrl}hospitalProfileUpdate?updateLab=true">Update Lab Info</a> --%>
	   				</div>
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
		   			
		   			<div class="span4">
			    		<label>Address</label>
						<form:input path="address" class="input-xlarge" />
		   			</div>
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
		   			<div class="span3">
		   				<button type="submit" name="updateLab" class="btn">Update Hospital</button>
		   			</div>
		   			<br/><br/><br/>
		   		</div>	
		   		
		   		
		   		<legend>Update User</legend>
		   		
		   		<div class="span12">
		   		<a href="${rootUrl}hospitalUser?addUser=${anAmiUser.userName}">Add User</a>
		   		</div>
		   		<div class="span12">
					<table id="table_id" class="table table-bordered table-condensed">
					    <thead>
					        <tr>
					            <th>User</th>
					            <th>First Name</th>
					            <th>Last Name</th>
					            <th>Email</th>
					            <th>Delete</th>
					            <th>Edit</th>
					            
					        </tr>
					    </thead>
					    <tbody>
					       <c:forEach var="anAmiUser" items="${amiUsers}">
								<tr>
									
						            <td>${anAmiUser.userName}</td>
						            <td>${anAmiUser.firstName}</td>
									<td>${anAmiUser.lastName}</td>					
									<td>${anAmiUser.email} </td>					
						            <td><a href="${rootUrl}hospitalProfileUpdate?userName=${anAmiUser.userName}&delete=true">Delete</a></td>
						            <td><a href="${rootUrl}hospitalUser?editUser=${anAmiUser.userName}">Edit User</a></td>
								</tr>
							</c:forEach>
					    </tbody>
					</table>
			  	</div>
		   		
		   </fieldset>
	  	</div>
	  </div>	
	</div>	  	
	  	
	  	
</form:form>

</body>
</html>
