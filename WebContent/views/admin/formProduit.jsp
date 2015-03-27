<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<s:if test="!firstDisplay">
	<p class="bg-danger"><br/>Il y a des problèmes, voyez les messages ci-dessous:
		<s:if test="!idProducteurOK">
			<br/> <span class="glyphicon glyphicon-remove"></span>&nbsp;
					Veuillez sélectionner un producteur
		</s:if>
		<s:if test="!idType2OK">
			<br/> <span class="glyphicon glyphicon-remove"></span>&nbsp;
					Sélectionner le type de produit
		</s:if>
		<s:if test="!prixOK">
			<br/><span class="glyphicon glyphicon-remove"></span>&nbsp;
					 Veuillez saisir un prix correcte
		</s:if>
		<s:if test="!designationOK">
			<br/> <span class="glyphicon glyphicon-remove"></span>&nbsp;
					Désignez votre produit
		</s:if>
		<br/><br/>
	</p>
</s:if>
<s:if test="firstDisplay">
	<s:if test="idProduit > 0">
		<p class="bg-success"><br/><span class="glyphicon glyphicon-save"></span>&nbsp;
				Bravo, votre produit a été enregistré
		</p>
	</s:if>
	<s:if test="idProduit > 0">
		<p class="bg-danger"><br/><span class="glyphicon glyphicon-remove"></span>&nbsp;
				L'enregistrement des paramètres de votre produit a échoué.
		</p>
	</s:if>
	<br/><br/>
</s:if>

<div class="col-md-8 col-md-offset-2 row" style="display: inline-block;">
	<span class="glyphicon glyphicon-map-marker" style="font-size: 2em;color:red"></span>
	<div><p>
	Vous allez saisir un produit. Nous vous informons que seul la photo et la description ne sont pas obligatoire.
	Cepandant, nous vous conseillons de fournir ces informations, cela donnera une meilleur visibilité à votre produit.
	</p></div>
</div>

<div class="row" id="divFormProduit" >
	
	
	<s:actionerror/>
<%-- 	<s:form action="ajout-produit" method="post" enctype="multipart/form-data"> --%>
	<form class="form-horizontal row" id="formProduit" method="post" enctype="multipart/form-data" action="<s:url action='ajout-produit' />">


		<fieldset>
			<legend > Produit :	</legend>
			<div class="form-group row">
				<label for="idProduit" class="col-sm-2 control-label">Désignation du Produit : </label>
				<div class="col-sm-6">
					<input type="text" class="form-control input-lg" id="idProduit" name="designation" value="<s:property value="designation"/>"
						placeholder="Nom du Produit">
						<span id="idHelpProduit" class="help-block"></span>
				</div>
			</div>
			
			<div class="form-group row">
				<label for="idProducteur" class="col-sm-2 control-label">Producteur : </label>
				<div class="col-sm-4">
					<select name="idProducteur" id="idProducteur" class="form-control">
						<option value="-1">Sélectionnez un producteur</option>
						<s:iterator value="listeProducteur">
							<option value="<s:property value="idProducteur"/>">
								<s:property value="siren"/>-<s:property value="raisonSociale"/>
							</option>
						</s:iterator>
			        </select>
				</div>
			</div>
			
			<div class="form-group row">
				<label for="idType1" class="col-sm-2 control-label">Boutique : </label>
				<div class="col-sm-4">
					<select name="idType1" id="idType1" class="form-control">
						<option value="-1">Sélectionnez un type de produit</option>
						<s:iterator value="listeType1">
							<option value="<s:property value="idType1"/>">
								<s:property value="libelle1"/>
							</option>
						</s:iterator>
			        </select>
				</div>
			</div>
			
			<div class="form-group row">
				<label for="idListType2" class="col-sm-2 control-label">Type de produit : </label>
				<div class="col-sm-4">
					<select name="idType2" id="idListType2" class="form-control">
			        </select>
				</div>
			</div>
			
			<div class="form-group row">
				<label for="idPrix" class="col-sm-2 control-label">Prix du produit : </label>
				<div class="col-sm-2">
					<input type="number" step="0.01" min="0" class="form-control" id="idPrix" name="prix" value="<s:property value="prix" />" >
				</div>
			</div>
			
			<div class="form-group row">
				<div class="col-sm-offset-2 col-sm-6">
					<div class="checkbox">
						<label> <input type="checkbox"id="idDisponible" name="disponible" value="true"
							<s:if test="disponible">checked</s:if>  > Disponibilité du produit
						</label>
					</div>
				</div>
			</div>
			
			<div class="form-group row">
				<label for="idDescription" class="col-sm-2 control-label">Description du produit : </label>
				<div class="col-sm-6">
					<textarea class="form-control" name="description" id="idDescription" rows="5" placeholder="Vous avez droit à 100 caractères"><s:property value="description" /></textarea>
				</div>
			</div>
			
<!-- 			<div class="form-group row"> -->
<!-- 				<div class="col-sm-6"> -->
<%-- 					<s:file name="photoProduit" label="User Image"> <s:property value="imageName" /></s:file> --%>
<!-- 				</div> -->
<!-- 			</div> -->
			
			<div class="form-group">
				<label for="idPhotoProduit" class="col-sm-2 control-label">Photo du produit : </label>
				<div class="col-sm-6">
					<input type="file" name="photoProduit" id="idPhotoProduit" />
				</div>
			</div>
		</fieldset>
		<br/>
		
<!-- 		<div class="form-group"> -->
<!-- 			<div class="col-sm-offset-2 col-sm-10"> -->
<%-- 				<s:submit value="Upload" align="center"></s:submit> --%>
<!-- 			</div>  -->
<!-- 		</div>  -->
		
		
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-primary btn-lg">Enregistrer</button>
			</div>
		</div>
		
		
		
	</form>
<%-- 	</s:form> --%>
</div>