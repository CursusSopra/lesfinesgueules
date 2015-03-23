<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="row">
	<form class="form-horizontal" id="formType2" method="post"
		action=<s:url action="ajout-type2-produit"/>>
		<fieldset>
		<legend> Formulaire d'ajout d'un nouveau type à ajouter: (type 2) </legend>
			<div class="form-group">
				<label for="idType1" class="col-sm-2 control-label">Type général du produit: (type 1)</label>
				<div class="col-sm-4">
			        <select name="type1" size="1" id="idType1" class="form-control">
						<option value="-1">Sélectionnez un type de produit</option>
						<s:iterator value="listeType1">
							<option value="<s:property value="idType1" />">
								<s:property value="libelle1" />
							</option>
						</s:iterator>
					</select>
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-6">
					<input type="text" class="form-control input-lg" id="idType2"
						name="type2" placeholder="Type2">
				</div>
			</div>
		</fieldset>

		<div class="form-group">
			<div class="col-sm-offset-3 col-sm-3">
				<button type="submit" class="btn btn-primary btn-lg btn-block">Ajouter un nouveau type</button>
			</div>
		</div>
	</form>
</div>