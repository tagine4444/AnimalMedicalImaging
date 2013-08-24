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
	      <li><a href="${rootUrl}amiInterpretation" >Requests Completed Today</a></li>
	      <li><a href="${rootUrl}searchRequestAmi">Search</a></li>
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
<form:form id="form" method="get" >
<form:errors path="*" cssClass="error" />
   
<%--    <form:hidden path="Id"/> --%>
	<div class="row">	
		<div class="span12">
		<div class="span12">
			<fieldset>
			    <legend>Hospital Admin</legend>
			    <div class="span12">
			    	<table id="table_id" class="table table-bordered table-condensed">
					    <thead>
					        <tr>
					            <th>Hospital ID</th>
					            <th>Hospital Name</th>
					            <th>Contract</th>
					            <th>Acronym</th>
					            <th>Priority</th>
					            <th>Action</th>
					        </tr>
					    </thead>
					    <tbody>
					       <c:forEach var="aHospital" items="${hospitalList}">
								<tr>
									
						            <td>${aHospital.id}</td>
						            <td>${aHospital.name}</td>
						            <td>${aHospital.underContract}</td>
						            <td>${aHospital.acronym}</td>
						            <td>${aHospital.priorityString}</td>
						            <td><a href="${rootUrl}hospitalContractAdmin?updateContract=${aHospital.id}">Hospital Admin</a></td>
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
