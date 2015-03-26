<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<form class="form-horizontal row" id="FormPhotoProducteur" method="post" enctype="multipart/form-data"
		action="<s:url action='ajout-producteur' />">
	<div class="row">


		<h1 class="text-center">Modification Photo</h1>
		
		<input type="hidden" class="form-control" id="idProducteur" name="idProducteur" 
				value="<s:property value="idProducteur" />" >
		<input type="hidden" class="form-control input-lg" id="idRaisonSociale" maxlength="50"
						name="raisonSociale" value="<s:property value="raisonSociale"/>">
		<input type="hidden" class="form-control input-lg" id="idSiren" maxlength="50"
						name="siren" value="<s:property value="siren"/>"> 
		<input type="hidden" class="form-control" id="idDelai" name="delaiLivraison" min="0"
						value="<s:property value="delaiLivraison" />" >
		<input type="hidden" class="form-control" name="description" id="idDescription" 
						value="<s:property value="description" />"/>
		<input type="hidden" class="form-control" id="idLigneAdresse1" maxlength="50"
						name="ligneAdresse1" value="<s:property value="ligneAdresse1" />">
		<input type="hidden" class="form-control" id="idLigneAdresse2" maxlength="50"
						name="ligneAdresse2" value="<s:property value="ligneAdresse2" />">
		<input type="hidden" class="form-control" id="idCodePostal"
						name="codePostal" pattern="\d{5}"
						value="<s:property value="codePostal" />">	
		<input type="hidden" class="form-control" id="idVille" name="ville" maxlength="50"
						value="<s:property value="ville" />" placeholder="Ville">		
		<input type="hidden" class="form-control" id="idLatitude"
						name="latitude" value="<s:property value="latitude"/>">
		<input type="hidden" class="form-control" id="idLongitude"
						name="longitude" value="<s:property value="longitude"/>">
		
		<div class="row">				
			<div class="form-group">
				<label for="idPhotoProducteur">Photo de votre producteur : </label>
				<img  alt="image" class="img-responsive img-thumbnail" width="200px" src="/lesfinesgueules/content/images/<s:property value="imageName" default="default.jpg"/>" />
				<div class="">
					<input class="control-label" type="file" name="userImage" id="idPhotoProducteur"/>
				</div>
			</div>	
		
			<div class="form-group">
				<div>
					<button type="submit" class="btn btn-primary btn-lg">Valider</button>
				</div>
			</div>		
		</div>
		
	</div>
</form>