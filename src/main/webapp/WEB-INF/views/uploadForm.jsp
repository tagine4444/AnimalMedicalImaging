<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 
<c:url value="/" var="rootUrl"/>
<c:url value="/resources" var="resourcesUrl"/>

<html>
    <head>
        <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <title>Animal Medical Imaging Upload</title>
        <script src="${resourcesUrl}/media/js/jquery-1.8.3.js"></script>	 
        <link href="${resourcesUrl}/media/bootstrap/css/bootstrap.css" rel="stylesheet" media="screen">
	    <link href="${resourcesUrl}/media/css/ami.css" rel="stylesheet" media="screen">
		
    </head>
    <body>
   	<div class="navbar">
	 	<div class="navbar-inner">
	    	<a class="brand" href="#">
	    		<img src="${resourcesUrl}/img/ami_logo.jpg" alt="AMI Logo" width="140px" height="10px">
	    	</a>
	    	<ul class="nav">
		      <li class="active"><a href="${rootUrl}serviceRequest">Request Interpretation</a></li>
		      <li ><a href="${rootUrl}hospitalPendingRequest">Pending Interpretations</a></li>
		      <li ><a href="${rootUrl}hospitalInterpretation">Completed Interpretation</a></li>
		       <li ><a href="${rootUrl}searchRequest">Search</a></li>
		      <li ><a href="${rootUrl}hospitalProfileUpdate" >Update Profile</a></li>
		      <li><a href="#" onclick="alert('Not implemented yet');">Help</a></li>
		       <li><a href="${rootUrl}login?logout">Logout</a></li>
		       <li><a href="${rootUrl}upload">upload</a></li>
	    	</ul>
	  	</div>
	</div>
    <div class="span12">
        <form:form modelAttribute="uploadItem" method="post" enctype="multipart/form-data">
        	<form:hidden path="svcReqId" />
			<form:hidden path="requestNumber" />
					
            <fieldset>
                <legend>Upload Documents for Request# ${uploadItem.requestNumber}</legend>
                <div class="span12">${successMsg}<span style="color: red">${errorMsg}</span></div>
                <br/><br/>
                <h5>Please do not upload x rays. This page should only be used to upload documents like blood samples.<br/>
                If you want to upload x rays, please contact us and we'll get you set up.</h5>
	   			<div class="span12">
					<form:input path="fileData" type="file"/>
                </div>
                <br/>
 				<div class="span12">
                    <input type="submit" name="uploadFile" value="uploadFile"/>
	   			</div>
 
            </fieldset>
        </form:form>
    </div>    
    </body>
</html>