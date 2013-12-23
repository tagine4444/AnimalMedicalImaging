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
		       <li><a href="${rootUrl}faq">Help</a></li>
		       <li><a href="${rootUrl}login?logout">Logout</a></li>
		       <li><a href="${rootUrl}upload">upload</a></li>
	    	</ul>
	  	</div>
	</div>
    <div class="span12">
        <form:form modelAttribute="uploadItem" method="post" enctype="multipart/form-data">
        	<form:hidden path="svcReqId" />
			<form:hidden path="requestNumber" />
			
			<div class="span12">
				<c:if test="${!empty error}"> 
					<div style="color: red; background-color: yellow; border-style: solid;"> ${error} </div>
					<br/><br/>
				</c:if>
			</div>
                <div class="span12">${successMsg}<span style="color: red">${errorMsg}</span></div>
					
                <legend>Digital Documents for Request# ${uploadItem.requestNumber}</legend>
                
                <div class="span12">
                	<div class="span12">
						<ol>
							<li> Select the file to upload:
								<form:input path="fileData" type="file"/>
                			</li>
							<li>Upload the selected file by clicking the upload button:
								<input type="submit" name="uploadFile" value="uploadFile"/>
                			</li>
							<li>When you are done uploading the document, click on the Submit Request button
								below (in red) to make your request visible to the radiologist. 
								<br><b>Note</b> The radiologist will not treat your request until
								click on the submit button. 
                			</li>
                		</ol>
                	</div>
		             
				</div> 
            
              <fieldset>
                <legend>Uploaded Documents</legend>
                
                	
<!--                 <div class="span12"> -->
<!-- 					<b>Notes about uploaded documents</b> -->
<!-- 					<textarea id="consTxtId" rows="4" class="input-block-level" maxlength="3000"/> -->
					
<!-- 				</div> -->
                
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
				            <td><a href="${rootUrl}deleteDocuments?openDoc=${anUploadDoc.id}&svcReqId=${anUploadDoc.requestId}">Delete ReqNumber${anUploadDoc.requestId}</a></td>
						</tr>
					</c:forEach>
			    </tbody>
			</table>
			
			<p>
				I am done uploading documents. 
<%-- 				<input type="submit" name="uploadFile" value="${uploadItem.requestNumber}" style="color: red"/> --%>
				<div class="span12">
     		   		<button type="submit" name="doneUploadingDocs" value="${uploadItem.requestNumber}" class="btn" ><font color="red">Notify Radiologist</font></button>
	    		</div>
            </p>
            
        </form:form>
    </div>    
    </body>
</html>