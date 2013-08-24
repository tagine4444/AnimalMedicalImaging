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
<div class="span12">
	<span style="color: red">${message}</span>
</div>
</div>	
<div class="span12">
	<fieldset>
		<legend><span style="color:green;"><strong><%=hosptitalName %></strong></span></legend>
	</fieldset>
</div>

<form:form id="form" method="post"  modelAttribute="hospitalContractAdmin" >
		
		<form:hidden path="hospitalId" />
		<form:errors path="*" cssClass="error" />
   
		
		
		<div class="span12">
			<div class="span12">
			
			<table id="table_id" class="table table-bordered table-condensed">
			    	<tr>
			    		<td colspan="6"><b><font color="red">Hospital</font> </b> <a href="${rootUrl}hospitalProfileUpdateAdminHospital?hospitalId=${hospitalContractAdmin.hospital.id}">Edit</a></td>
			    	</tr>
			        <tr>
			        	 <th >Name</th>
			        	 <th >Office</th>
			        	 <th >Back Line</th>
			        	 <th >Fax</th>
			        	 <th >Email</th>
			        	 <th >Lab</th>
			        	 <th >Lab Acct</th>
			        </tr>
			       
					<tr>
			            <td><font color="blue">${hospitalContractAdmin.hospital.name}</font></td>
			            <td><font color="blue">${hospitalContractAdmin.hospital.office}</font></td>
			            <td><font color="blue">${hospitalContractAdmin.hospital.backLine}</font></td>
			            <td><font color="blue">${hospitalContractAdmin.hospital.fax}</font></td>
			            <td><font color="blue">${hospitalContractAdmin.hospital.hospitalEmail}</font></td>
			             <td><font color="blue">${hospitalContractAdmin.hospital.lab}</font></td>
			            <td><font color="blue">${hospitalContractAdmin.hospital.labAccount}</font></td>
					</tr>
					<tr>
						<td colspan="7">&nbsp;</td>
					</tr>
					 <tr>
			        	 <th  colspan="4">Address</th>
			        	 <th >City</th>
			        	 <th >State</th>
			        	 <th >Country</th>
			        </tr>
					<tr>
			            <td colspan="3"><font color="blue">${hospitalContractAdmin.hospital.address}</font></td>
			            <td><font color="blue">${hospitalContractAdmin.hospital.city}</font></td>
			            <td><font color="blue">${hospitalContractAdmin.hospital.stateProvince}</font></td>
			            <td><font color="blue">${hospitalContractAdmin.hospital.country}</font></td>
					</tr>
					<tr>
						<td colspan="7">&nbsp;</td>
					</tr>
					<tr>
						<th>Under Contract</th>
						<th>Acronym</th>
						<th>Priority</th>
						<th>Billing Cycle</th>
					</tr>
					<tr>
						<td>
							<c:choose>
								<c:when test="${hospitalContractAdmin.hospital.underContract}">
									<font color="blue">Yes</font>
								</c:when>
								<c:otherwise>
									<font color="blue">No</font>
								</c:otherwise>
							</c:choose>						
						</td>
						
						<td>
				    		<font color="blue">${hospitalContractAdmin.hospital.acronym}</font>
						</td>
						<td>
				    		<form:select path="priority" class="input-medium" disabled="true" cssStyle="color:blue">
								<form:option value="0" label="Normal"/>
								<form:option value="1" label="High"/>
			   				 </form:select>
						</td>
						<td>
							<font color="blue">${hospitalContractAdmin.hospital.billingCycle}</font>
						</td>
					</tr>
					
			</table>     
			<table id="table_id" class="table table-bordered table-condensed">
			    <thead>
			    	<tr>
			    		<td colspan="6"><b><font color="red">Users</font> </b> </td>
			    	</tr>
			        <tr>
			            <th >Login</th>
			            <th >First Name</th>
			            <th >Last Name</th>
			            <th >Occupation</th>
			            <th >Email</th>
			            <th >Is Active?</th>
			            <th >Action</th>
			             <c:if test="${anAmiUser.adminUser}">
			            	<th >Action</th>
					 	</c:if>
					 	<th >Action</th>
			        </tr>
			    </thead>
			    <tbody>
			       <c:forEach var="anAmiUser" items="${amiUsers}">
						<tr>
				            <td><font color="blue">${anAmiUser.userName}</font></td>
				            <td><font color="blue">${anAmiUser.firstName}</font></td>
				            <td><font color="blue">${anAmiUser.lastName}</font></td>
				            <td><font color="blue">${anAmiUser.occupation}</font></td>
				            <td><font color="blue">${anAmiUser.email}</font></td>
				            
				            
				            <c:choose>
				            <c:when test="${anAmiUser.accountActive}">
				            	<td><font color="green">${anAmiUser.accountActiveString}</font> </td>
				            	<td><button type="submit" name="deactivateUser" value="${anAmiUser.userName}"  class="btn"><font color="red">Deactivate</font> </button></td>
						 	</c:when>
						 	<c:otherwise>
						 		<td><font color="red">${anAmiUser.accountActiveString}</font></td>
				            	<td><button type="submit" name="deactivateUser" value="${anAmiUser.userName}"  class="btn"><font color="green">Activate</font></button></td>
						 	</c:otherwise>
						 	</c:choose>
						 	
				            
<%-- 				            <c:if test="${anAmiUser.adminUser}"> --%>
				            	<td><a href="${rootUrl}hospitalProfileUpdateAdminUser?userName=${anAmiUser.userName}&hospitalId=${hospitalContractAdmin.hospital.id}">Edit User</a></td>
<%-- 						 	</c:if> --%>
						</tr>
					</c:forEach>
			    </tbody>
			</table>
			
	  	</div>
	</div>	  	
	
</form:form>

</body>
</html>
