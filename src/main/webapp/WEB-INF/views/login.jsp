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
		    <li><a href="${rootUrl}">Home</a></li>
	    	<li><a href="${rootUrl}ourservices">Our Services</a></li>
	    	<li><a href="${rootUrl}contactus">Contact us</a></li>
	    	<li><a href="${rootUrl}aboutus">About us</a></li>
	    	<li><a href="${rootUrl}hospitalCreateAccount">Create an account</a></li>
	    	<li class="active"><a href="${rootUrl}">Member Login</a></li>
	    </ul>
    </div>
 </div> 




<div class="span12">
	<h2>Animal Medical Imaging</h2>
</div>	


<div class="span12">
	<span class="error">${errorMessage}</span>
</div>	



<div class="span3">
	<form:form class="well" name="f" action="${authPage}" method="post" >
		
		<span><b>Member Login</b></span>
		
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
	
	
<script type="text/javascript">
	//<![CDATA[   
       	$(document).ready(function() {
	    	$('#j_username').focus();
		});
    //]]>
</script>
	
</body>
</html>
