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
    


<style>
.error {
	color: #ff0000;
	font-style: italic;
}
</style>
</head>
<body>

		
					<div class="span12" >
						
						<div class="span12">
							<div class="span12">
								<h1>Error Page</h1>
							</div>
						</div>
						
						<div class="span12">
							<div class="span12">
							<br/><br/><br/>
							</div>
							<div class="span12">
								<p>
									Oops, we are sorry. An error occured. 
									
									${errorString}
								</p>
								
							</div>
						</div>
					
					</div>
	
	
</body>
</html>
