<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<div class="row" id="divFormRecherche">
	<div class="col-md-8 col-md-offset-2">
	
	<form class="form-horizontal" id="formRecherche" method="post" action="<s:url action='' />">
		<fieldset>
		<legend > Recherche par type :	</legend>
		<div class="form-group">
			<label for="idNom" class="col-sm-2 control-label">Nom du Cinema</label>
			<div class="col-sm-6">
				<input type="text" class="form-control input-lg" id="idNom" name="nom" value="<s:property value="nom"/>"
					placeholder="Nom du Cinema">
					<span id="idHelpNom" class="help-block"></span>
			</div>
		</div>
		<div class="form-group">
			<label for="inputNombreDeSalles" class="col-sm-2 control-label">Nombre De Salles</label>
			<div class="col-sm-2">
				<input id="idNbSalles" name="nbSalles" type="number" min="0" max="99" value="<s:property value="nbSalles" />" class="form-control" id="inputNombreDeSalles" required>
			</div>
		</div>
		<div class="form-group">
			<label for="inputNombreDeSalles" class="col-sm-2 control-label">Type du cinéma</label>
			<div class="col-sm-4">
				 <select name="idType">
				 	<s:iterator value="listeDesTypes">
					  <option value="<s:property value="key"/>">
					  <s:property value="value"/>
					  </option>
					</s:iterator>
				</select> 
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-6">
				<div class="checkbox">
					<label> <input type="checkbox"id="idStationnement" name="stationnement" value="1"
						<s:if test="stationnement">checked</s:if>> Stationnement à proximité
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="idDescription" class="col-sm-2 control-label">Description</label>
			<div class="col-sm-6">
				<textarea class="form-control" name="description" id="idDescription" rows="5" placeholder="Vous avez droit à 100 caractères">
				<s:property value="description" />
				</textarea>
			</div>
		</div>
		</fieldset>
		<fieldset>
		<legend > Adresse :	</legend>
		<div class="form-group">
			<label for="idLigneAdresse1" class="col-sm-2 control-label">Rue</label>
			<div class="col-sm-3">
				<input type="text" class="form-control" id="idLigneAdresse1" name="ligneAdresse1" value="<s:property value="ligneAdresse1" />"
					placeholder="Rue">
			</div>
		</div>
		<div class="form-group">
			<label for="idCodePostal" class="col-sm-2 control-label">Code Postal</label>
			<div class="col-sm-2">
				<input type="text" class="form-control" id="idCodePostal" name="codePostal" pattern="\d{5}" value="<s:property value="codePostal" />">
					<span id="idHelpCodePostal" class="help-block"></span>
			</div>
		</div>
		<div class="form-group">
			<label for="inputVille" class="col-sm-2 control-label">Ville</label>
			<div class="col-sm-3">
				<input type="text" class="form-control" id="idVille" name="ville" value="<s:property value="ville" />"
					placeholder="Ville">
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

