<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:url value="/" var="rootUrl"/>
<c:url value="/resources" var="resourcesUrl"/>

<html>
	<head>
		<title>Animal Medical Imaging</title>
	    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1.0">
	    <link href="${resourcesUrl}/media/bootstrap/css/bootstrap.css" rel="stylesheet" media="screen">
	    <link href="${resourcesUrl}/media/css/ami.css" rel="stylesheet" media="screen">
		
		<style>e
		.error {
			color: #ff0000;
			font-style: italic;
		}
		</style>
		
		<script src="http://code.jquery.com/jquery.js"></script>
		<script src="js/bootstrap.min.js"></script>			
	</head>
	
	<body>
	
			<form:errors path="*" cssClass="error" />
	
			<div class="span12">
				<div class="span12">
					${caseMsg} <a href="#" onclick="history.go(-1);return false;">Back</a> 
				</div>
		</div>
	</body>
</html>

