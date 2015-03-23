<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
		

<div class="row" id="divFormProduit" >
	<form class="form-horizontal" id="formProduit" method="post" action="<s:url action='createProduit' />">
		<fieldset>
			<legend > Produit :	</legend>
			<div class="form-group">
				<label for="idProduit" class="col-sm-2 control-label">Désignation du Produit</label>
				<div class="col-sm-6">
					<input type="text" class="form-control input-lg" id="idNom" name="designation" value="<s:property value="designation"/>"
						placeholder="Nom du Produit">
						<span id="idHelpNom" class="help-block"></span>
				</div>
			</div>
			
			<div class="form-group">
				<label for="idTypeProduit" class="col-sm-2 control-label">Votre type de produit:</label>
				<div class="col-sm-4">
					<select name="idTypeProduit" id="idTypeProduit">
						<option value="-1">Sélectionnez un type de produit</option>
						<s:iterator value="listeDesTypesProduit">
							<option value="<s:property value="idTypeProduit"/>">
								<s:property value="typeProduit"/>
							</option>
						</s:iterator>
			        </select>
				</div>
			</div>
			
			<div class="form-group">
				<label for="idPrix" class="col-sm-2 control-label">Prix du produit</label>
				<div class="col-sm-2">
					<input type="number" class="form-control" id="idPrix" name="prix" value="<s:property value="prix" />" >
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-6">
					<div class="checkbox">
						<label> <input type="checkbox"id="idDisponible" name="disponible" value="1"
							<s:if test="disponible">checked</s:if>  > Disponibilité du produit
						</label>
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<label for="idDescription" class="col-sm-2 control-label">Description du produit</label>
				<div class="col-sm-6">
					<textarea class="form-control" name="description" id="idDescription" rows="5" placeholder="Vous avez droit à 100 caractères">
					<s:property value="description" />
					</textarea>
				</div>
			</div>
			
			<div class="form-group">
				<label for="idPhotoProduit" class="col-sm-2 control-label">Description du produit</label>
				<div class="col-sm-6">
					<input type="file" name="photoProduit" id="idPhotoProduit" />
				</div>
			</div>
		
		
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-primary btn-lg">Enregistrer</button>
			</div>
		</div>
	</form>
</div>