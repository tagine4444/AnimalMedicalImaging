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
						<table >
							<tr>
								<td>
								
									<table>
											<tr>
												<td>
													<img src="${resourcesUrl}/img/dog.jpg" alt="Dog" width="150" >
												</td>
												
												<td>
													Animal Medical Imaging was the 1st Veterinary Radiology practise in the Seattle Area, and was founded by Dr Charles.
													Read more <a href="${rootUrl}aboutus">About us</a>.<br/><br/>
													<b>But what is Veterinary Radiology?</b><br/>
													Veterinary Radiology is a medical specialty that employs the use of imaging to both diagnose and treat disease visualised within the animal body. 
												</td>
											</tr>
											
											<tr>
												<td>
													
												</td>
												
												<td>
												</td>
											</tr>
									
									
									</table>
<!-- 									<div class="span12"> -->
									
<!-- 												Animal Medical Imaging was the 1st Radiology practise in the Seattle Area, and was founded in 1986 by Dr Charles -->
<!-- 												Root whom has been a practising veterinarian since 1912 -->
												
												
<!-- 									</div> -->
								</td>
								<td>
									<div class="span12">
									<form:form class="well span3" name="f" action="${authPage}" method="post" >
										
										<h3 class="titleText">Member Login</h3>
										<span class="error">${errorMessage}</span>
										<br />
										<div class="control-group">
											<input type="text" class="span3 jq_watermark" placeholder="Username" id="j_username" name="j_username" />
									  	</div>
									  	<div class="control-group">
									  		<input type="password" class="span3 jq_watermark" placeholder="Password" id="j_password" name="j_password"/>
									  	</div>
									  	<button type="submit" name="login" class="btn">Login</button>
									</form:form>
									</div>
								</td>
							</tr>
							
							<tr>
								<td>
									<div class="span12">
										The services we offer are aimed to assist other veterinary specialties in their complicated diagnosis.
										As a result Animal owners are required to consult their veterinary of choice which then can refer 
										them to Animal Medical Imaging. 
										
									</div>
									
								</td>
							</tr>	
							<tr>
								<td>
									<br/>
									<div class="span12">
										Animal Medical Imaging strives to provide to best quality services delivered the most convenient way
										to the other fellow veterinarians. This is the reason we provide a paperless and easy way to communicate
										with us										
									</div>
									
								</td>
							</tr>	
						</table>
						</div>
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
