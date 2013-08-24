<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>

<c:url value="/" var="rootUrl"/>
<c:url value="/resources" var="resourcesUrl"/>

 
<html lang="en">
<head>   
    <title>Animal Medical Imaging</title>
    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="${resourcesUrl}/media/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>

<body>
	<div class="navbar">
		 	<div class="navbar-inner">
		    	<a class="brand" href="#"><img src="${resourcesUrl}/img/ami_logo.jpg" alt="AMI Logo" width="140px" height="10px"></a>
		    	<ul class="nav">
			       <li ><a href="${rootUrl}requestDashboard">Process Requests</a></li>
			       <li ><a href="${rootUrl}caseDashboard">Verify Requests</a></li>
			       <li ><a href="${rootUrl}amiInterpretation" >Requests Completed Today</a></li>
			       <li ><a href="${rootUrl}searchRequestAmi">Search</a></li>
			       <li ><a href="${rootUrl}hospitalAdmin" >Hospital Admin</a></li>
			       <li ><a href="${rootUrl}amiServices" >Services</a></li>
			       <li><a href="${rootUrl}login?logout">Logout</a></li>
		    	</ul>
		  	</div>
		</div>


<form:form id="form" method="post"  modelAttribute="amiChargesModel" >
<form:errors path="*" cssClass="error" />

<form:hidden path="caseId"/>   
<form:hidden path="requestId"/>
   
<div class="container row">
	  	<legend>Add Charges</legend>
		<div class="span12">
			
			<div class="span6">
	    		<label><b>Interpretation Only</b></label>
	    		<form:select path="interpretationOnlySvc" itemValue="id" itemLabel="name" items="${amiChargesModel.interpretationOnlySvcList}" class="input-xlarge"/>
	    		<button type="submit" name="addInterOnlyCharge" class="btn" >Add</button>
   			</div>
   			<div class="span5">
	    		<label><b>Ultrasound Services</b></label> 
	    		<form:select path="ultrasoundSvc" itemValue="id" itemLabel="name" items="${amiChargesModel.ultrasoundSvcList}" class="input-xlarge"/>
	    		<button type="submit" name="addUltrasoundCharge" class="btn" >Add</button>
   			</div>
   			
			<div class="span6">
	    		<label><b>MRI Services</b></label>
	    		<form:select path="MRISvc" itemValue="id" itemLabel="name" items="${amiChargesModel.MRISvcList}" class="input-xlarge"/>
	    		<button type="submit" name="addMriCharge" class="btn" >Add</button>
   			</div>
			<div class="span5">
	    		<label><b>Contrast Radio</b></label> 
	    		<form:select path="contrastRadioSvc" itemValue="id" itemLabel="name" items="${amiChargesModel.contrastRadioSvcList}" class="input-xlarge"/>
	    		<button type="submit" name="addContrastRadioCharge" class="btn" >Add</button>
   			</div>
   			
			<div class="span6">
	    		<label><b>Computed Tomogrophy</b></label> 
	    		<form:select path="computedTomoSvc" itemValue="id" itemLabel="name" items="${amiChargesModel.computedTomoSvcList}" class="input-xlarge"/>
	    		<button type="submit" name="addComputedTomoCharge" class="btn" >Add</button>
   			</div>
			<div class="span5">
	    		<label><b>Radiography/Fluoroscopy</b></label> 
	    		<form:select path="radioFluoSvc" itemValue="id" itemLabel="name" items="${amiChargesModel.radioFluoSvcList}" class="input-xlarge"/>
	    		<button type="submit" name="addRadioFluoCharge" class="btn" >Add</button>
   			</div>
	  	</div>
	  	
	  	<div class="span12">
			<div class="span4">  
	 			<label>Description</label>
		 		<form:input id="miscDescriptionId" path="miscDescription" class="input-xlarge" />
   			</div>
   			
   			
   			<div class="span4">  
	 			<label>Amount</label>
		 		<form:input id="miscPriceId" path="miscPrice" class="input-small"  />
		 		<button type="submit" name="addMiscCharge" class="btn" >Add</button>
   			</div>
	  	</div>
	  	</div>
</form:form>

</body>
</html>