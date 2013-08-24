<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>


 <link href="${resourcesUrl}/media/css/ami.css" rel="stylesheet" media="screen">
    
    <style>
		.error {
			color: #ff0000;
			font-style: italic;
		}
		
 </style>
		

<%-- <form:form id="form" method="post"  modelAttribute="amiServiceModel" > --%>

<div class="span12">
	<legend>Add A Service</legend>
		<div class="span12">
			<div class="span3">
	    		<label><b>Name</b>&nbsp;<form:errors path="name" cssClass="error" /></label> 
	    		<form:input path="name" class="input-medium" />
	    	</div>
			<div class="span4">
	    		<label><b>Description</b> &nbsp;<form:errors path="description" cssClass="error" /> </label>
	    		<form:input path="description" class="input-xlarge"/>
	    	</div>
	    	
	    	<div class="span3">	
	    		<label><b>Category</b></label>
	    		<form:select path="category" items="${serviceCategories}" class="input-large"/>
	    		
	    	</div>
	    </div>
	    	
	    <div class="span12">
	    	<div class="span2">	
	    		<label><b>Default Price</b></label>
	    		<form:input path="defaultPrice" class="input-small"/>
	    	</div>	
	    	<div class="span2">	
	    		<label><b>Price Min</b></label>
	    		<form:input path="priceMin" class="input-small"/>
	    	</div>
	    	<div class="span2">	
	    		<label><b>Price Max</b></label>
	    		<form:input path="priceMax" class="input-small"/>
	    	</div>	
	    </div>
	    		
</div>

<%-- </form:form> --%>

