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


<form:form id="form" method="post"  modelAttribute="milageFeeModel" >
<form:errors path="*" cssClass="error" />

<form:hidden path="caseId"/>   
<form:hidden path="requestId"/>
   
<div class="container row">
	  	<legend>Add Milage Fee</legend>
		<div class="span12">
		
			<div class="span2">  
	 			<label>Dollars Per Mile</label>
		 		<form:input id="dollarAmountId" path="dollarAmount" class="input-small" readonly="true"/>
   			</div>
   			<div class="span1">  
	  			<label> </label>
		 		<span> X </span>
   			</div>
   			
   			<div class="span2">  
	 			<label>Number Of miles</label>
		 		<form:input id="perAmountId" path="perAmount" class="input-small"  onchange="recaclculateMilageFee();"/>
   			</div>
   			
   			<div class="span1">  
	  			<label> </label>
		 		<span> = </span> 
   			</div>
   			
	  		<div class="span2">  
	 			<label>Total</label>
		 		<form:input id ="amountId"  path="amount"  class="input-small" readonly="true"/> 
   			</div>
	  		
	  		
   			
	  		
	  	</div>
	  	
	  	<div class="span12">
	  		<div class="span12">
		  		<button type="submit" name="addCharge" class="btn" >Add Milage Fee</button>
		  	</div>	
	  	</div>
	  	</div>
</form:form>

	<script type="text/javascript">
			function recaclculateMilageFee()
			{
 				var perAmount = document.getElementById("perAmountId").value;
 				var dollarAmount = document.getElementById("dollarAmountId").value;
				
 				var tot = perAmount * dollarAmount;
 				document.getElementById("amountId").value = tot;
				
			}	
		
		</script>

</body>
</html>