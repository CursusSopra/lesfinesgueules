<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="row">
	<div class="col-xs-10 col-xs-offset-1">
		<h1>
			Liste des utilisateurs
		</h1>
	</div>
</div>

<div class="row">
	<div class="col-xs-3 col-xs-offset-1">
		<img class="img-thumbnail" src="<s:property value="utilisateur.photo" />">
	</div>
	<div class="col-xs-7 bg-warning">
		<h2 class="text-primary">ADRESSE</h2>
		<address>
			<strong><s:property value="utilisateur.nom" /></strong><br>
			<s:property value="utilisateur.ligneAdresse1" />
			<br>
			<s:if test="!utilisateur.adresse.isEmpty()">
				<s:property value="utilisateur.ligneAdresse2" />
				<br>
			</s:if>
			<s:property value="utilisateur.codePostal" />
			<br>
			<s:property value="utilisateur.ville" />
		</address>
	</div>
</div>

<div class="row">
	<div class="col-xs-5 col-lg-offset-1">
		<b>Email :</b>
	</div>
	<div class="col-xs-5">
		<s:property value="utilisateur.email" />
	</div>
</div>
