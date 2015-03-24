<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- @author : Julien Caillon -->
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="images/favicon.ico">

<!-- PAGE TITLE -->
<title><tiles:getAsString name="title" /></title>


<!-- Core CSS
	    ================================================== -->
<!-- Bootstrap -->
<!-- Boostrap linter : http://www.bootlint.com/ -->
<!-- Boostrap switch : http://www.bootstrap-switch.org/examples.html -->
<!-- <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet"> -->
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">

<!-- Bootswatch theme -->
<link href="http://maxcdn.bootstrapcdn.com/bootswatch/3.3.4/cosmo/bootstrap.min.css" rel="stylesheet">

<!-- Font awesome -->
<link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">

<!-- Normalize css (included in boostrap) -->
<!-- 	<link href="http://cdnjs.cloudflare.com/ajax/libs/normalize/3.0.2/normalize.min.css" rel="stylesheet"> -->


<!-- Extra CSS
	    ================================================== -->
<tiles:useAttribute name="moreStyles" scope="request" ignore="true" />
<c:if test="${not empty moreStyles}">
	<c:forEach items="${moreStyles}" var="cssFile">
		<link href="./${cssFile}" media="all" rel="stylesheet" type="text/css" />
	</c:forEach>
</c:if>

<tiles:useAttribute name="moreHttpStyles" scope="request" ignore="true" />
<c:if test="${not empty moreHttpStyles}">
	<c:forEach items="${moreHttpStyles}" var="cssFile">
		<link href="${cssFile}" media="all" rel="stylesheet" type="text/css" />
	</c:forEach>
</c:if>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>

<body>

	<!-- NAVBAR -->
	<tiles:insertAttribute name="navbar" />


	<!-- HEADER -->
	<header>
		<div class="container">
			<object type="image/svg+xml" data="images/logo_lfg_new.svg">
				<img src="images/logo_lfg.png" />
			</object>
			<!-- 		<p class="text-muted text-right">Vente en ligne de produits régionaux en Rhône-Alpes</p> -->
		</div>
	</header>


	<!-- PAGE CONTENT -->
	<div class="container">


		<!-- BREADCRUMBS -->
		<ol class="breadcrumb">
			<s:iterator value="listeBreadcrumbs">
				<s:set var="targetAction" value="action" />
				<li><a href="<s:url action="%{#targetAction}" />"><s:property value='nom' /></a></li>
			</s:iterator>
			<li class="active"><tiles:getAsString name="title" /></li>
		</ol>


		<!-- PAGE BODY -->
		<div class="row">
			<div class="col-lg-12 bodycontent">
				<tiles:insertAttribute name="body" />
			</div>
		</div>

	</div>
	

	<!-- FOOTER -->
	<footer>
		<div class="container">
			<div class="pull-left">
				<ul class="list-inline">
					<li><a href="<s:url action='contact' />">Contact</a></li>
					<li><a href="<s:url action='contact' />">À propos</a></li>
				</ul>
			</div>

			<!-- 		<div class="clearfix">&nbsp;</div> -->

			<div class="pull-right">
				<p class="text-muted">
					<small>Copyright &copy; 2015, SOPRA-STERIA - All Rights Reserved.</small>
				</p>
			</div>
		</div>
	</footer>


	<!-- Core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

	<!-- Angular.js -->
	<%--     <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script> --%>

	<!-- bootbox.js -->
	<%--     <script src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/4.3.0/bootbox.min.js"></script> --%>


	<!-- Extra JavaScript
    ================================================== -->
	<tiles:useAttribute name="moreHttpScripts" scope="request" ignore="true" />
	<c:if test="${not empty moreHttpScripts}">
		<c:forEach items="${moreHttpScripts}" var="jsFile">
			<script type="text/javascript" src="${jsFile}"></script>
		</c:forEach>
	</c:if>

	<tiles:useAttribute name="moreScripts" scope="request" ignore="true" />
	<c:if test="${not empty moreScripts}">
		<c:forEach items="${moreScripts}" var="jsFile">
			<script type="text/javascript" src="./${jsFile}"></script>
		</c:forEach>
	</c:if>

</body>
</html>
