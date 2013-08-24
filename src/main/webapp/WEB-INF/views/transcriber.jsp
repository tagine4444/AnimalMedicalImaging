<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:url value="/" var="rootUrl"/>
<c:url value="/resources" var="resourcesUrl"/>

<html>
<head>
	<title>Animal Medical Imaging</title>
	
	 <title>jQuery UI Tabs - Vertical Tabs functionality</title>
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



	<%@ include file="toolBar.jsp" %>
    
	<form:form id="form" class="form-horizontal" method="get"  >
	    
	    <h1>Transcriber Page</h1>
			
	</form:form>

</body>
</html>
