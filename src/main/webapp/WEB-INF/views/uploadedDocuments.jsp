<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
 
<c:url value="/" var="rootUrl"/>
<c:url value="/resources" var="resourcesUrl"/>

<html>
    <head>
        <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <title>Animal Medical Imaging Uploaded Documents</title>
        <script src="${resourcesUrl}/media/js/jquery-1.8.3.js"></script>	 
        <link href="${resourcesUrl}/media/bootstrap/css/bootstrap.css" rel="stylesheet" media="screen">
	    <link href="${resourcesUrl}/media/css/ami.css" rel="stylesheet" media="screen">
		
    </head>
    <body>
   	<div class="navbar">
		 	<div class="navbar-inner">
		    	<a class="brand" href="#"><img src="${resourcesUrl}/img/ami_logo.jpg" alt="AMI Logo" width="140px" height="10px"></a>
		    	<ul class="nav">
			      <li class="active"><a href="${rootUrl}requestDashboard">Process Requests</a></li>
		  		  <li><a href="${rootUrl}caseDashboard">Verify Requests</a></li>
			      <li ><a href="${rootUrl}amiInterpretation" >Requests Completed Today</a></li>
			      <li ><a href="${rootUrl}searchRequestAmi">Search</a></li>
			      <li ><a href="${rootUrl}hospitalAdmin" >Hospital Admin</a></li>
			      <li ><a href="${rootUrl}amiServices" >Services</a></li>
			      <li><a href="${rootUrl}login?logout">Logout</a></li>
		    	</ul>
		  	</div>
	</div>
	
    <div class="span12">
        <form:form modelAttribute="uploadDocItem" method="post" enctype="multipart/form-data">
        	<form:hidden path="requestId" />
<%-- 			<form:hidden path="requestNumber" /> --%>
<%-- 			<form:hidden path="fileName" /> --%>
<%-- 			<form:hidden path="contentType" /> --%>
					
            <fieldset>
                <legend>Uploaded Documents</legend>
                
	   			<table id="table_id" class="table table-bordered table-condensed">
			    <thead>
			        <tr>
			            <th>Request #</th>
<!-- 			            <th>Request Date</th> -->
			            <th>Hospital</th>
			            <th>File Name</th>
			        </tr>
			    </thead>
			    <tbody>
			       <c:forEach var="anUploadDoc" items="${uploadDocs}">
						<tr>
							<td>${anUploadDoc.requestNumber}</td>					
<%-- 							<td><joda:format value="${anUploadDoc.requestDate}" pattern="MM/dd/yy HH:mm" />					 --%>
				            <td>${anUploadDoc.hospitalName}</td>
				            <td>${anUploadDoc.fileName}</td>
				            <td><a href="${rootUrl}uploadedDocuments?openDoc=${anUploadDoc.id}">Download</a></td>
						</tr>
					</c:forEach>
			    </tbody>
			</table>
 
            </fieldset>
        </form:form>
    </div>    
    </body>
</html>