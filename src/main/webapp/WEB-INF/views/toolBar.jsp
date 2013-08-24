<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/" var="rootUrl"/>
<c:url value="/resources" var="resourcesUrl"/>


<link href="${resourcesUrl}/media/bootstrap/css/bootstrap.css" rel="stylesheet" media="screen">
<link href="${resourcesUrl}/media/css/ami.css" rel="stylesheet" media="screen">

<!-- <script src="http://code.jquery.com/jquery.js"></script> -->
<!-- <script src="js/bootstrap.min.js"></script> -->

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