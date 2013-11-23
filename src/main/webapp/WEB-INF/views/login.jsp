<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:url value="/" var="rootUrl"/>
<c:url value="/resources" var="resourcesUrl"/>
<c:url value="/j_spring_security_check" var="authPage"/>

<html>
<head>
	<title>Animal Medical Imaging</title>
    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="${resourcesUrl}/media/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    
     <link href="${resourcesUrl}/media/css/ami.css" rel="stylesheet" media="screen">
	
<style>
.error {
	color: #ff0000;
	font-style: italic;
}
</style>
</head>
<body>

<%-- 	<%@ include file="toolBar.jsp" %> --%>
	

  
<div class="row-fluid">
<div class="navbar">
	    <div class="navbar-inner">
	    <a class="brand" ></a>
		    <ul class="nav">
		    	<li class="active"><a href="${rootUrl}">Home</a></li>
	    	<li><a href="${rootUrl}ourservices">Our Services</a></li>
	    	<li><a href="${rootUrl}contactus">Contact us</a></li>
	    	<li><a href="${rootUrl}aboutus">About us</a></li>
	    	<li><a href="${rootUrl}hospitalCreateAccount">Create an account</a></li>
	    </ul>
    </div>
 </div> 



<div class="span12">

<div class="span12 offset1">
	<h1>Animal Medical Imaging</h1>
</div>	

<div class="span1">
<span>&nbsp;</span>
</div>

<div class="span2">
	<img src="${resourcesUrl}/img/dog.jpg" alt="Dog" width="200" >
</div>



<div class="span5">
	<div class="span12">
		Animal Medical Imaging is the first veterinary Radiology practise in the Seattle Area. It was founded by Dr Charles.
		Read more <a href="${rootUrl}aboutus">About us</a>.<br/><br/>
	</div>
	
	<div class="span12">
		<b>What is Veterinary Radiology?</b>
		<br/>Veterinary Radiology is a medical specialty that employs the use of imaging to both diagnose and treat disease visualised within the animal body. 
	</div>
	
	<div class="span12">
		<br/>The services we provide are aimed to assist other veterinary specialties in their diagnosis.
		Animal owners are required to consult their veterinary of choice which then can refer 
		them to Animal Medical Imaging when necessary. 
		
	</div>
	
	
</div>

<div class="span3">
									<form:form class="well" name="f" action="${authPage}" method="post" >
										
										<h3 class="titleText">Member Login</h3>
										<span class="error">${errorMessage}</span>
										<br />
										<div class="control-group">
											<input type="text"   class="input-large jq_watermark" placeholder="Username" id="j_username" name="j_username" />
									  	</div>
									  	<div class="control-group">
									  		<input type="password" class="input-large jq_watermark" placeholder="Password" id="j_password" name="j_password"/>
									  	</div>
									  	<button type="submit" name="login" class="btn">Login</button>
									</form:form>
</div>
					
</div class="span12">
	<div class="span7 offset1">
		Animal Medical Imaging strives to provide to best quality services. Response Time and communication 
		is central to the way we deliver our services.
		<br/> You can easily register with us and submit your requests.
	</div>
</div>	

			
		
	
	
	<script type="text/javascript">
		//<![CDATA[   
	       	$(document).ready(function() {
		    	$('#j_username').focus();
			});
	    //]]>
	</script>
	
</body>
</html>
