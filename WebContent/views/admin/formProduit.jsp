<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
		

<div class="row" id="divFormProduit" >
	<form class="form-horizontal" id="formProduit" method="post" action="<s:url action='ajout-produit' />">
		<fieldset>
			<legend > Produit :	</legend>
			<div class="form-group">
				<label for="idProduit" class="col-sm-2 control-label">D�signation du Produit : </label>
				<div class="col-sm-6">
					<input type="text" class="form-control input-lg" id="idProduit" name="designation" value="<s:property value="designation"/>"
						placeholder="Nom du Produit">
						<span id="idHelpProduit" class="help-block"></span>
				</div>
			</div>
			
			<div class="form-group">
				<label for="idProducteur" class="col-sm-2 control-label">Producteur : </label>
				<div class="col-sm-4">
					<select name="idProducteur" id="idProducteur" class="form-control">
						<option value="-1">S�lectionnez un producteur</option>
						<s:iterator value="listeProducteur">
							<option value="<s:property value="idProducteur"/>">
								<s:property value="siren"/>-<s:property value="raisonSociale"/>
							</option>
						</s:iterator>
			        </select>
				</div>
			</div>
			
			<div class="form-group">
				<label for="idType1" class="col-sm-2 control-label">Boutique : </label>
				<div class="col-sm-4">
					<select name="idType1" id="idType1" class="form-control">
						<option value="-1">S�lectionnez un type de produit</option>
						<s:iterator value="listeType1">
							<option value="<s:property value="idType1"/>">
								<s:property value="libelle1"/>
							</option>
						</s:iterator>
			        </select>
				</div>
			</div>
			
			<div class="form-group">
				<label for="idListType2" class="col-sm-2 control-label">Type de produit : </label>
				<div class="col-sm-4">
					<select name="idType2" id="idListType2" class="form-control">
					
					
<!-- 						<option value="-1">S�lectionnez un type de produit</option> -->
<%-- 						<s:iterator value="listeType2"> --%>
<%-- 							<option value="<s:property value="idTyp2Produit"/>"> --%>
<%-- 								<s:property value="type2"/> --%>
<!-- 							</option> -->
<%-- 						</s:iterator> --%>
			        </select>
				</div>
			</div>
			
			<div class="form-group">
				<label for="idPrix" class="col-sm-2 control-label">Prix du produit : </label>
				<div class="col-sm-2">
					<input type="number" step="0.01" class="form-control" id="idPrix" name="prix" value="<s:property value="prix" />" >
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-6">
					<div class="checkbox">
						<label> <input type="checkbox"id="idDisponible" name="disponible" value="1"
							<s:if test="disponible">checked</s:if>  > Disponibilit� du produit
						</label>
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<label for="idDescription" class="col-sm-2 control-label">Description du produit : </label>
				<div class="col-sm-6">
					<textarea class="form-control" name="description" id="idDescription" rows="5" placeholder="Vous avez droit � 100 caract�res">
					<s:property value="description" />
					</textarea>
				</div>
			</div>
			
			<div class="form-group">
				<label for="idPhotoProduit" class="col-sm-2 control-label">Photo du produit : </label>
				<div class="col-sm-6">
					<input type="file" name="photoProduit" id="idPhotoProduit" />
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