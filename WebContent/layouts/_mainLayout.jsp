<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">



<tiles:useAttribute name="moreStyles" scope="request" ignore="true" />
<c:if test="${not empty moreStyles}">
	<c:forEach items="${moreStyles}" var="cssFile">
		<link href="./${cssFile}" media="all" rel="stylesheet" type="text/css" />
	</c:forEach>
</c:if>

<tiles:useAttribute name="moreHttpStyles" scope="request" ignore="true" />
<c:if test="${not empty moreHttpStyles}">
	<c:forEach items="${moreHttpStyles}" var="cssFile">
		<link href="./${cssFile}" media="all" rel="stylesheet" type="text/css" />
	</c:forEach>
</c:if>
<title><tiles:getAsString name="title" /></title>
</head>
<body>
	<div class="container-fluid">

		<nav class="navbar navbar-default">


			<!-- /.navbar-collapse -->
		</nav>
		<div class="row">
			<div class="col-lg-12 col-sm-12">
				<tiles:insertAttribute name="body" />
			</div>
		</div>
		<!-- /.container-fluid -->
	</div>
	<script type="text/javascript" src="js/jquery-2.1.3.min.js"></script>
	<!-- Latest compiled and minified JavaScript -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	
	<tiles:useAttribute name="moreHttpScripts" scope="request"
		ignore="true" />
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
