<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%-- <s:if test="!firstDisplay"> --%>
<!-- 	<p class="bg-danger text-center"> -->
<%-- 		<br /> <strong>Il y a des problèmes de données, voyez les --%>
<%-- 			messages ci-dessous.</strong><br />&nbsp; --%>
<!-- 	</p> -->
<%-- </s:if> --%>

<div class="row" id="divFormProfil">

	<form class="form-horizontal" id="FormProfil" method="post"
		action="<s:url action='inscription'/>">
		
		<fieldset>
			<legend> Vos données personnelles</legend>
			
			<div class="form-group">
				<label for="idNom" class="col-sm-2 control-label">Nom</label>
				<div class="col-sm-6">
					<input type="text" class="form-control input-lg" id="idNom"
						name="nom" value="<s:property value="nom"/>"
						placeholder="Nom"> 
				</div>
			</div>
			<div class="form-group">
				<label for="idPrenom" class="col-sm-2 control-label">Prénom</label>
				<div class="col-sm-6">
					<input type="text" class="form-control input-lg" id="idPrenom"
						name="prenom" value="<s:property value="prenom"/>"
						placeholder="Prenom"> 
				</div>
			</div>
			<div class="form-group">
				<label for="idLigneAdresse1" class="col-sm-2 control-label">Rue</label>
				<div class="col-sm-6">
					<input type="text" class="form-control input-lg" id="idLigneAdresse1"
						name="ligneAdresse1" value="<s:property value="ligneAdresse1"/>"
						placeholder="Rue"> 
				</div>
			</div>
			<div class="form-group">
				<label for="idCodePostal" class="col-sm-2 control-label">Code postal</label>
				<div class="col-sm-6">
					<input type="text" class="form-control input-lg" id="idCodePostal"
						name="codePostal" value="<s:property value="codePostal"/>"
						placeholder="Code postal"> 
				</div>
			</div>
			<div class="form-group">
				<label for="idVille" class="col-sm-2 control-label">Ville</label>
				<div class="col-sm-6">
					<input type="text" class="form-control input-lg" id="idVille"
						name="ville" value="<s:property value="ville"/>"
						placeholder="Ville"> 
				</div>
			</div>
			<div class="form-group">
				<label for="idTel" class="col-sm-2 control-label">Numéro de téléphone</label>
				<div class="col-sm-6">
					<input type="text" class="form-control input-lg" id="idTel"
						name="tel" value="<s:property value="ville"/>"
						placeholder="Numéro de téléphone"> 
				</div>
			</div>
		</fieldset>
		
		<fieldset>
			<legend> Votre compte utilisateur : </legend>
			<div class="form-group">
				<label for="idEmail" class="col-sm-2 control-label">Email</label>
				<div class="col-sm-6">
					<input type="text" class="form-control input-lg" id="idEmail"
						name="email" value="<s:property value="email"/>"
						placeholder="email"> 
				</div>
			</div>
			<div class="form-group">
				<label for="idMdp" class="col-sm-2 control-label">Mot de passe</label>
				<div class="col-sm-6">
					<input type="text" class="form-control input-lg" id="idMdp"
						name="mdp" value="<s:property value="mdp"/>"
						placeholder="Mot de passe"> 
				</div>
			</div>
		</fieldset>
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-primary btn-lg">Enregistrer</button>
			</div>
		</div>
	</form>
</div>
