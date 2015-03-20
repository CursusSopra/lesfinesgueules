<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<!-- On ajoute ce qui est dans le "if" si "saveBDD" n'est pas vrai -->
<s:if test="saveBDD">
	<p class="bg-danger"><br/>Il y a des probl�mes, voyez les messages ci-dessous:
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
		<s:if test="!coordonneesGPSOK">
			<br/> Vos coordonn�es GPS ne sont pas valides
		</s:if>
		<s:if test="!descriptionOK">
			<br/> Veuillez d�crire votre exploitation
		</s:if>
	</p>
</s:if>





<div class="row" id="divFormProducteur" data-messErrorCodePostal="<s:property value="messErrorCodePostal"/>">
	<form class="form-horizontal" id="FormProducteur" method="post"
		action="<s:url action='createProducteur' />">
		<fieldset>
		
			<legend> Producteur : </legend>
			
			<div class="form-group">
				<label for="idRaisonSociale" class="col-sm-2 control-label">Raison sociale du
					producteur</label>
				<div class="col-sm-6">
					<input type="text" class="form-control input-lg" id="idRaisonSociale"
						name="raisonSociale" value="<s:property value="raisonSociale"/>"
						placeholder="Raison sociale du producteur"> 
				</div>
			</div>
			
			<div class="form-group">
				<label for="idSiren" class="col-sm-2 control-label">SIREN</label>
				<div class="col-sm-6">
					<input type="text" class="form-control input-lg" id="idSiren"
						name="siren" value="<s:property value="siren"/>"
						placeholder="numero Siren"> 
				</div>
			</div>
			
			<div class="form-group">
				<label for="idDelai" class="col-sm-2 control-label">D�lai de livraison</label>
				<div class="col-sm-2">
					<input type="number" class="form-control" id="idDelai" name="delaiLivraison" 
						value="<s:property value="delaiLivraison" />" >
				</div>
			</div>
			
			<div class="form-group"> -->
				<label for="idDescription" class="col-sm-2 control-label">Description</label>
				<div class="col-sm-6">
					<textarea class="form-control" name="description" id="idDescription" rows="5"
						placeholder="Vous avez droit � 100 caract�res">
					<s:property value="description" />
					</textarea>
				</div>
			</div>
		</fieldset>
			
			
			
			
			
		<fieldset>
			<legend> Adresse : </legend>
			<div class="form-group">
				<label for="idLigneAdresse1" class="col-sm-2 control-label">Rue</label>
				<div class="col-sm-3">
					<input type="text" class="form-control" id="idLigneAdresse1"
						name="ligneAdresse1" value="<s:property value="ligneAdresse1" />"
						placeholder="Rue">
				</div>
			</div>
			
			<div class="form-group">
				<label for="idCodePostal" class="col-sm-2 control-label">Code
					Postal</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" id="idCodePostal"
						name="codePostal" pattern="\d{5}"
						value="<s:property value="codePostal" />"> <span
 						id="idHelpCodePostal" class="help-block"></span>
				</div>
			</div>
			
			<div class="form-group">
				<label for="inputVille" class="col-sm-2 control-label">Ville</label>
				<div class="col-sm-3">
					<input type="text" class="form-control" id="idVille" name="ville"
						value="<s:property value="ville" />" placeholder="Ville">
				</div>
			</div>
			
			<div class="form-group">
				<label for="idGPS" class="col-sm-2 control-label">Coordonn�es GPS</label>
				<div class="col-sm-6">
					<input type="text" class="form-control input-lg" id="idGPS"
						name="coordonneesgps" value="<s:property value="coordonneesgps"/>"
						placeholder="Coordonn�es GPS">
				</div>
			</div>
		</fieldset>
			
			
			
			
			

<!-- 			<div class="form-group"> -->
<!-- 				<label for="inputNombreDeSalles" class="col-sm-2 control-label">Nombre -->
<!-- 					De Salles</label> -->
<!-- 				<div class="col-sm-2"> -->
<!-- 					<input id="idNbSalles" name="nbSalles" type="number" min="0" -->
<%-- 						max="99" value="<s:property value="nbSalles" />" --%>
<!-- 						class="form-control" id="inputNombreDeSalles" required> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 			<div class="form-group"> -->
<!-- 				<div class="col-sm-offset-2 col-sm-6"> -->
<!-- 					<div class="checkbox"> -->
<!-- 						<label> <input type="checkbox" id="idStationnement" -->
<!-- 							name="stationnement" value="1" -->
<!-- 							<s:if test="stationnement">checked</s:if>> Stationnement -->
<!-- 							� proximit� -->
<!-- 						</label> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 			
<!-- 			<div class="form-group"> -->
<!-- 				<label for="idType" class="col-sm-2 control-label">Type</label> -->
<!-- 				<div class="col-sm-6"> -->
<!-- 					<SELECT name="type" size="1" id="idType" class="form-control"> -->
<!-- 						<OPTION value="-1">Veuillez choisir un Type de cinema</OPTION> -->
<%-- 						<s:iterator value="listeTypeCinema"> --%>

<%-- 							<OPTION value="<s:property value="idType" />"> --%>

<%-- 								<s:property value="nom" /> --%>

<!-- 							</OPTION> -->
<%-- 						</s:iterator> --%>
<!-- 					</SELECT> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</fieldset> -->

		
		
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-primary btn-lg">Enregistrer Producteur</button>
			</div>
		</div>
	</form>

</div>