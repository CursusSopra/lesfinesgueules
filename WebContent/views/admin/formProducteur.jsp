<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<s:if test="!firstDisplay">
	<p class="bg-danger"><br/>Il y a des problèmes, voyez les messages ci-dessous:
		<s:if test="!raisonSocialeOK">
			<br/> La raison sociale de votre exploitation est obligatoire
		</s:if>
		<s:if test="!sirenOK">
			<br/> Votre code siren est obligatoire
		</s:if>
		<s:if test="!ligneAdresse1OK">
			<br/> Vous devez saisir votre adresse
		</s:if>
		<s:if test="!codePostalOK">
			<br/> Votre code postal contient des erreurs
		</s:if>
		<s:if test="!villeOK">
			<br/> Vous devez saisir une ville
		</s:if>
		<s:if test="!latitudeOK">
			<br/> Problème de latitude
		</s:if>
		<s:if test="!longitudeOK">
			<br/> Problème de longitude
		</s:if>
		<s:if test="!descriptionOK">
			<br/> Veuillez décrire votre exploitation
		</s:if>
		<s:if test="!delaiLivraisonOK">
			<br/> Votre délai n'est pas correct
		</s:if>
	</p>
</s:if>

<s:if test="firstDisplay">
	<p class="bg-danger">
		<s:if test="idProducteur > 0">
			<br/>Bravo, vous êtes référencé(e) parmi nos fines gueules.
		</s:if>
	</p>
</s:if>


<div class="row" id="divFormProducteur" data-messErrorCodePostal="<s:property value="messErrorCodePostal"/>">
	<form class="form-horizontal" id="FormProducteur" method="post" enctype="multipart/form-data"
		action="<s:url action='ajout-producteur' />">
		<fieldset>
		
			<legend> Producteur : </legend>
			
			<div class="form-group">
				<label for="idRaisonSociale" class="col-sm-2 control-label">Raison sociale du producteur : </label>
				<div class="col-sm-6">
					<input type="text" class="form-control input-lg" id="idRaisonSociale" maxlength="50"
						name="raisonSociale" value="<s:property value="raisonSociale"/>"> 
				</div>
			</div>
			
			<div class="form-group">
				<label for="idSiren" class="col-sm-2 control-label">SIREN : </label>
				<div class="col-sm-6">
					<input type="text" class="form-control input-lg" id="idSiren" maxlength="50"
						name="siren" value="<s:property value="siren"/>"> 
				</div>
			</div>
			
			<div class="form-group">
				<label for="idDelai" class="col-sm-2 control-label">Délai de livraison : </label>
				<div class="col-sm-2">
					<input type="number" class="form-control" id="idDelai" name="delaiLivraison" min="0"
						value="<s:property value="delaiLivraison" />" >
				</div>
			</div>
			
			<div class="form-group">
				<label for="idDescription" class="col-sm-2 control-label">Description : </label>
				<div class="col-sm-6">
					<textarea class="form-control" name="description" id="idDescription" rows="5"><s:property value="description" /></textarea>
				</div>
			</div>
			
			<div class="form-group">
				<label for="idPhotoProducteur" class="col-sm-2 control-label">Photo de votre producteur : </label>
				<div class="col-sm-6">
					<input type="file" name="photo" id="idPhotoProducteur" />
				</div>
			</div>
		</fieldset>
			
			
		<fieldset>
			<legend> Adresse : </legend>
			<div class="form-group">
				<label for="idLigneAdresse1" class="col-sm-2 control-label">Rue : </label>
				<div class="col-sm-3">
					<input type="text" class="form-control" id="idLigneAdresse1" maxlength="50"
						name="ligneAdresse1" value="<s:property value="ligneAdresse1" />">
				</div>
				<div class="col-sm-3">
					<input type="text" class="form-control" id="idLigneAdresse2" maxlength="50"
						name="ligneAdresse2" value="<s:property value="ligneAdresse2" />">
				</div>
			</div>
			
			<div class="form-group">
				<label for="idCodePostal" class="col-sm-2 control-label">Code Postal : </label>
				<div class="col-sm-2">
					<input type="text" class="form-control" id="idCodePostal"
						name="codePostal" pattern="\d{5}"
						value="<s:property value="codePostal" />"> <span
 						id="idHelpCodePostal" class="help-block"></span>
				</div>
			</div>
			
			<div class="form-group">
				<label for="inputVille" class="col-sm-2 control-label">Ville : </label>
				<div class="col-sm-3">
					<input type="text" class="form-control" id="idVille" name="ville" maxlength="50"
						value="<s:property value="ville" />" placeholder="Ville">
				</div>
			</div>
			
			<div class="form-group">
				<label for="idLatitude" class="col-sm-2 control-label">Latitude : </label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="idLatitude"
						name="latitude" value="<s:property value="latitude"/>">
				</div>
			</div>
			
			<div class="form-group">
				<label for="idLongitude" class="col-sm-2 control-label">Longitude : </label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="idLongitude"
						name="longitude" value="<s:property value="longitude"/>">
				</div>
			</div>
		</fieldset>
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-primary btn-lg">Enregistrer Producteur</button>
			</div>
		</div>
	</form>

</div>