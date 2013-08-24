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
    
        <script src="${resourcesUrl}/media/js/jquery-1.8.3.js"></script>
    	<script src="${resourcesUrl}/media/bootstrap/js/bootstrap.js"></script>


<script type="text/javascript">

// $(document).ready(function () {
//     if ($("[rel=tooltip]").length) {
//         $("[rel=tooltip]").tooltip();
//     }
    
//     $('#id1').popover('show');
// 	$('#requestedbyTooltip').tooltip('show');
	
// });
</script>	
	
<style>
.error {
	color: #ff0000;
	font-style: italic;
}
</style>
</head>
<body>

<%-- 	<%@ include file="toolBar.jsp" %> --%>
<%-- 	<%@ include file="toolBar.jsp" %> --%>
	
	<%@ include file="toolBar.jsp" %>
	<div class="row">
	
<!-- 	<a href="#" class="tooltip-test" title="sdfjsdsklj" data-original-title="Tooltip">This link</a> -->
<!-- 										<br/> -->
<!-- 										<a href="#" id ="element" data-toggle="tooltip" data-content="And here's some amazing content. It's very engaging. right?"  title="first tooltip">hover over me</a> -->
										
<!-- 										<a id="id1" href="#" class="btn btn-large btn-danger" data-toggle="popover"  -->
<!-- 										title="" data-content="And here's some amazing content. It's very engaging. right?"  -->
<!-- 										data-original-title="A Title">Click to toggle popover heee</a> -->
		
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
									Animal Medical Imaging was the 1st Veterinary Radiology practise in the Seattle Area and was founded by Dr Charles.
									It was founded in 1986 after Dr Charles Root moved from Oklahoma, where he used to teach.
								</p>
								<p>
									<b> Dr Charles Root's History</b><br/>
									Originally from Montana area, Dr Root grew up in a farm where he spent most of his younger years. The love of
									animals as well as their care started almost at birth.
								</p>
								<p>
									After graduating from the university of Washington, Charles Root moved to New York city where he worked in an 
									animal shelter for 10 years. He then decided to share his knowledge by taking a teaching position at the university
									of Baton Rouge.
								</p>
								<p>
									In 1986 he decides to move to the Seattle area and open Animal Medical Imaging. 
									Since 1986 many years have past, and Dr Root is well known. His warm personality makes him 
									very easy accessible and easy to talk to.
								</p>
								
								<p>
									<b> Animal Medical Imaging</b><br/>
									<a href="${rootUrl}ourservices">Our Services</a> are offered to other veterinarians. They can help them
									diagnos their patient's health issues. 
									As a result We do not see animal owners directly. They must have a referal.
								</p>
								
							</div>
						</div>
					
					</div>
		</div>
	
	
</body>
</html>
