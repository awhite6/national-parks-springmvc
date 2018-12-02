<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>National Park Geek</title>
    <c:url value="/css/site.css" var="cssHref" />
    <link rel="stylesheet" href="${cssHref}">
</head>

<body>
    <header>
    	<div class="header">
    		<div id="header-left">
	    	<c:url value="/home" var="homePage" />
	    	<c:url value="/img/logo.png" var="logoSrc" />
	    	<div class="header-image">
	    	
		        <a href="${homePage}" id="logo-link">
		        	<img src="${logoSrc}" alt="National Park Geek logo" id="logo"/>
		        </a>
<%-- 		        <div class="page-title">
		        	<h1>${pageTitle}</h1>
		        </div> --%>
	        </div>
	        <ul class="links">
	        	<c:url var="survey" value="/survey" />
	        	<c:url var="favorites" value="/surveyResults" />
	            <li><a href="${homePage }" class="navbar-link">Home</a></li>
	            <li id="navbar-pipe"> | <li>
	            <li><a href="${survey }" class="navbar-link">Survey</a></li>   
	            <li id="navbar-pipe"> | <li> 
	            <li><a href="${favorites }" class="navbar-link">Survey Results</a></li>
	        </ul>
	        </div>
	   	</div>