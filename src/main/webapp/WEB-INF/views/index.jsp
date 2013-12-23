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
	

  
<div class="row">
<div class="navbar">
	    <div class="navbar-inner">
	    <a class="brand" ></a>
		    <ul class="nav">
		    <li class="active"><a href="${rootUrl}">Home</a></li>
	    	<li><a href="${rootUrl}ourservices">Our Services</a></li>
	    	<li><a href="${rootUrl}contactus">Contact us</a></li>
	    	<li><a href="${rootUrl}aboutus">About us</a></li>
	    	<li><a href="${rootUrl}hospitalCreateAccount">Create an account</a></li>
	    	<li><a href="${rootUrl}login">Member Login</a></li>
	    </ul>
    </div>
 </div> 



<div class="span12">

	<%@ include file="headerLogo.jsp" %>

	<div class="span12">
		<br/>
		Animal Medical Imaging is the first <b>Veterinary Radiology</b> practise in the Seattle Area. <br/>
		It was founded by Dr Charles Root in 1986. Read more <a href="${rootUrl}aboutus">About us</a>.<br/><br/>
	</div>
	
	<div class="span12">
		<b>What is Veterinary Radiology?</b>
		<br/>Veterinary Radiology is a medical specialty that employs the use of imaging to both diagnose and treat disease visualised within the animal body. 
	</div>
	
	<div class="span12">
		<br/><b>Our expertise</b>
		<br/>The <a href="${rootUrl}ourservices">services</a> we provide are aimed to assist other veterinary specialties in their diagnosis.
		Animal owners must consult their veterinary which can request Animal Medical Imaging's expertise. In other words, Animal owners
		do not directly contact Animal Medical Imaging. If you are a veterinarian, you can find <a href="${rootUrl}contactus">our contact details here</a>.
	</div>
					
	<div class="span12">
		<br/><b>Animal Medical Imaging online</b>
		<br/>Animal Medical Imaging goal is to make it simple for veterinarians to access our radiology expertise as simply and easily as possible. 
		And because response time and communication are central to us we decided to expose our services online.
		You can easily <a href="${rootUrl}hospitalCreateAccount">Create an account</a> with us and start submitting your requests online.
		The process is designed to make it easy and quick to submit your request and receive notification as soon as the case is ready.
	</div>	
</div>	
	
</body>
</html>
