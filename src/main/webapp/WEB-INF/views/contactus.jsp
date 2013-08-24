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
    <link href="${resourcesUrl}/media/bootstrap/css/bootstrap.css" rel="stylesheet" media="screen">
    
    
    
        <script src="${resourcesUrl}/media/js/jquery-1.8.3.js"></script>
<%-- 		<script src="${resourcesUrl}/media/js/jquery-ui-1.9.2.custom.js"></script> --%>
<%-- 		<script src="${resourcesUrl}/media/js/jquery.blockUI.js"></script>	 --%>
<%--     	<script src="${resourcesUrl}/media/bootstrap/js/bootstrap.js"></script> --%>
	
<style>
.error {
	color: #ff0000;
	font-style: italic;
}
</style>

  
</head>
<body>

<%-- 	<%@ include file="toolBar.jsp" %> --%>
	
	<%@ include file="toolBar.jsp" %>
	<div class="row">
		
					<div class="span12" >
						
						<div class="span12">
							<div class="span12">
								<h1>Animal Medical Imaging</h1>
							</div>
						</div>
						
						<div class="span12">
							<div class="span12">
							<br/><br/><br/>
							</div>
							<div class="span12">
								<p>
									Our offices are open From 8AM to 6PM Pacific Time Monday thru Friday.
								</p>
									<div class="span3">
										<b>Office Phone:</b>
										206 206 2060
									</div>
									<div class="span3">
										<b>Office Fax:</b>
										206 206 2060
									</div>
									<div class="span4">
										<b>E-Mail:</b>
										amiradsin@gmail.com
										
									</div>
								
							</div>
						</div>
					
					</div>
		</div>
		

</body>
</html>
