<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
	<link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
	
	<!-- Bootswatch theme -->
	<link href="http:////maxcdn.bootstrapcdn.com/bootswatch/3.3.4/cosmo/bootstrap.min.css" rel="stylesheet">
	
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
			<link href="./${cssFile}" media="all" rel="stylesheet" type="text/css" />
		</c:forEach>
	</c:if>
	
	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>

<body>


<!-- NAVIGATION -->
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container">
	
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<s:url action='retourIndex' />"><span class="glyphicon glyphicon-home" aria-hidden="true"></span></a>
		</div>
		
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

			<ul class="nav navbar-nav">
             <li class="dropdown">
                 <a href="#" class="dropdown-toggle" data-toggle="dropdown" title="selection de charcut">Fromagerie <b class="caret"></b></a>
                 <ul class="dropdown-menu">
                     <li>
                         <a href="#"><i class="fa fa-globe fa-fw"></i> Vaches</a>
                     </li>
                     <li>
                         <a href="#"><i class="fa fa-star fa-fw"></i> Chèvre</a>
                     </li>
                     <li class="divider"></li>
                     <li class="dropdown-header">
                         Catégories de fromage :
                     </li>
                     <li>
                         <a href="#">Doux</a>
                     </li>
                     <li>
                         <a href="">Fort</a>
                     </li>
                 </ul>
             </li>
             <li class="dropdown">
                 <a href="#" class="dropdown-toggle" data-toggle="dropdown" title="selection de charcut">Charcuterie <b class="caret"></b></a>
                 <ul class="dropdown-menu">
                     <li>
                         <a href="#">1</a>
                     </li>
                     <li>
                         <a href="#">3</a>
                     </li>
                 </ul>
             </li>
             <li class="dropdown">
                 <a href="#" class="dropdown-toggle" data-toggle="dropdown" title="selection de charcut">Epicerie <b class="caret"></b></a>
                 <ul class="dropdown-menu">
                     <li>
                         <a href="#">1</a>
                     </li>
                     <li>
                         <a href="#">3</a>
                     </li>
                 </ul>
             </li>
             <li class="dropdown">
                 <a href="#" class="dropdown-toggle" data-toggle="dropdown" title="selection de charcut">Caviste <b class="caret"></b></a>
                 <ul class="dropdown-menu">
                     <li>
                         <a href="#">1</a>
                     </li>
                     <li>
                         <a href="#">3</a>
                     </li>
                 </ul>
             </li>
             <li>
                 <a href="#" title="nos prod">Producteurs</a>
             </li>
         </ul>
         
			<form class="navbar-form navbar-left" role="search">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="Rechercher" name="id" id="idrech">
					<div class="input-group-btn">
						<button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
					</div>
				</div>
	      </form>

         <ul class="nav navbar-nav navbar-right">

            <li class="dropdown">
              <a class="dropdown-toggle btn btn-default navbarbutton" data-toggle="dropdown"><b class="caret"></b>&nbsp;&nbsp; <i class="glyphicon glyphicon-user navbaricon"></i></a>
              <ul class="dropdown-menu userdropdown">
                <li>
                  <div class="row">
                    <div class="col-md-12">
                      <form class="form" role="form" method="post" action="login" accept-charset="UTF-8" id="login-nav">
                        <div class="form-group">
                          <label class="sr-only" for="idEmail">Adresse mail</label>
                          <input type="email" class="form-control" id="idEmail" placeholder="Adresse mail" required>
                        </div>
                        <div class="form-group">
                          <label class="sr-only" for="idMdp">Mot de passe</label>
                          <input type="password" class="form-control" id="idMdp" placeholder="Mot de passe" required>
                        </div>
                        <div class="checkbox">
                          <label>
                            <input type="checkbox"> Se souvenir de moi
                          </label>
                        </div>
                        <div class="form-group">
                          <button type="submit" class="btn btn-success btn-block">S'identifier</button>
                        </div>
                      </form>
                    </div>
                  </div>
                </li>
                <li class="divider"></li>
                <li>
                  <input class="btn btn-primary btn-block" type="button" id="idDeco" value="Créer un compte">
                  <input class="btn btn-danger btn-block" type="button" id="idDeco" value="Déconnexion">
                </li>
              </ul>
            </li>
            
				<li class="dropdown">
					<a class="dropdown-toggle btn btn-default navbarbutton" data-toggle="dropdown">
						<b class="caret"></b>&nbsp;&nbsp; <i class="glyphicon glyphicon-shopping-cart navbaricon"></i> <span class="badge">4</span>
					</a>
					<div class="dropdown-menu table-responsive userdropdown">
						<table class="table table-striped table-bordered table-condensed">
							<tbody>
							<tr>
								<th></th>
								<th>Produit</th>
								<th>Qte</th>
								<th>Prix</th>
							</tr>
							<tr>
								<td><i class="glyphicon glyphicon glyphicon-trash"></i></td>
								<td><a href="#">Frometon 1</a></td>
								<td><span class="label label-primary">x1</span></td>
								<td>110 &euro;</td>
							</tr>
							<tr>
								<td><i class="glyphicon glyphicon glyphicon-trash"></i></td>
								<td><a href="#">Frometon 3</a></td>
								<td><span class="label label-primary">x1</span></td>
								<td>110 &euro;</td>
							</tr>
							<tr class="warning">
								<td colspan="3">Total</td>
								<td>220 &euro;</td>
							</tr>
							</tbody>
						</table>
						<a href="" class="btn btn-success btn-block">Aller au panier</a>
					</div>
				</li>
         </ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container -->
</nav>


<!-- HEADER -->
<header>
	<div class="container">
		<object type="image/svg+xml" data="images/logo_lfg_new.svg"><img src="images/logo_lfg.png" /></object>
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
<%-- 					<s:url action="displayCinema" var="dc"> --%>
<%-- 						<s:param name="idc"> --%>
<%-- 							<s:property value="idCinema" /> --%>
<%-- 						</s:param> --%>
<%-- 					</s:url> --%>
<%-- 					<li><a href="<s:property value='#dc'/>"><s:property value="nom" /></a></li> --%>
		</s:iterator>
		<li class="active"><tiles:getAsString name="title" /></li>
	</ol>

	<div class="row">
		<div class="col-lg-12">

			<!-- PAGE BODY -->
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
			<p class="text-muted"><small>Copyright &copy; 2015, SOPRA-STERIA - All Rights Reserved.</small></p>
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
