<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:url value="/" var="rootUrl"/>
<c:url value="/resources" var="resourcesUrl"/>

<html>
	<head>
	
	<title>Animal Medical Imaging</title>
		
	<link href="${resourcesUrl}/media/bootstrap/css/bootstrap.css" rel="stylesheet" media="screen">
	<link rel="stylesheet" href="${resourcesUrl}/media/css/jquery-ui-1.9.2.custom.css" />
	<link rel="stylesheet" href="${resourcesUrl}/media/css/ami.css" />
	
       	<script src="${resourcesUrl}/media/js/jquery-1.8.3.js"></script>
   		<script src="${resourcesUrl}/media/bootstrap/js/bootstrap.js"></script>
   		<script src="${resourcesUrl}/media/js/jquery.blockUI.js"></script>
   		<script src="${resourcesUrl}/media/js/jquery-ui-1.9.2.custom.js"></script>
   		<script src="${resourcesUrl}/media/js/serviceRequest.js"></script>
   		
   		<style type="text/css">
	   		.bu {
				font-weight:bold;
				text-decoration:underline;
			}
			
			h5 {text-decoration:underline}
   		
   		</style>
	</head>
	
<body>
<div class="container-fluid">	

	
		<div class="navbar">
		 	<div class="navbar-inner">
		    	<a class="brand" href="#">
		    	</a>
		    	<ul class="nav">
		    	  <li><img src="${resourcesUrl}/img/ami_logo.jpg" alt="AMI Logo" >&nbsp;</li>
			      <li ><a href="${rootUrl}serviceRequest">Request Interpretation</a></li>
			      <li ><a href="${rootUrl}hospitalPendingRequest">Pending Interpretations</a></li>
			      <li ><a href="${rootUrl}hospitalInterpretation">Completed Interpretation</a></li>
			      <li ><a href="${rootUrl}searchRequest">Search</a></li>
			      <li ><a href="${rootUrl}hospitalProfileUpdate" >Update Profile</a></li>
			      
			      
			      <li class="active"><a href="${rootUrl}faq">Help</a></li>
			      <li><a href="${rootUrl}login?logout">Logout</a></li>
			       
		    	</ul>
		    	
		  	</div>
		</div>
		
	
		<div class="span12">
			
				<!-- questions -->
				<div class="span12">
					<a href="#1">1. I just submitted a request now what?</a>
				</div>
				<div class="span12">
					<a href="#2">2. How do I know the interpretation is ready?</a>
				</div>
				
				<div class="span12">
					<a href="#3">3. How do I get a hard copy of my interpretation?</a>
				</div>
				
				
				
				<!-- responses -->
				<div class="span12">
					<a id="1"></a>
					<h5>1. I just submitted a request now what?</h5>
					<blockquote>
						As soon as you submit a request, the Radiologist is notified. The interpretation is pending
						until the Radiologist picks it up. You can view your pending 
						interpretation by clicking on the Pending Interpretation tab on the menu bar.
						During this time, you can edit your request and upload lab work that go along 
						with with the request. Once the radiologist starts reading the request, 
						its status changes to  In Progress. Once it is in progress, you can no longer edit the request.
						At this point you will have to call the radiologist to communicate any changes to 
						the request. These changes will be captured as Amendments by the radiologist.
					</blockquote>
				</div>
	
				<div class="span12">
					<a id="2"></a>
					<h5>2. How do I know the interpretation is ready?</h5>
					
					<blockquote>
						An email is sent to the address you used when you registered as soon as the
						radiologist is done.
						This email contains a link to view the interpretation online and a
						A PDF attachement. . Alternatively, you can also
						click on the Completed Interpretation tab on the menu bar which shows all
						the interpretations completed today. 
					</blockquote>
				</div>
				
				<div class="span12">
					<h5>3. How do I get a hard copy of my interpretation?</h5>
					<blockquote>
						You can print your interpretation, or have it emailed to you. Either way
						you must view your interpretation.
						You can view interpretations completed today by clicking on the 
						Completed Interpretation tab on the menu bar. Older interpretations can be
						viewed by clicking on the Search tab on the menu bar.
						Once you open the interpretation, you can click on either the Print link or the
						Email PDF link
					</blockquote>
				</div>
		
		</div>		
</body>
</html>
