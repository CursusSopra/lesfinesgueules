<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="row">
	<form class="form-horizontal" id="formType1" method="post"
		action=<s:url action="ajout-type1-produit"/>>
		<fieldset>
		<legend> Formulaire d'ajout d'une nouvelle catégorie de produits à ajouter: (type 1) </legend>
			<div class="form-group">
				<div class="col-sm-6">
					<input type="text" class="form-control input-lg" id="idType1"
						name="libelle1" placeholder="Type1">
				</div>
			</div>
		</fieldset>

		<div class="form-group">
			<div class="col-sm-offset-3 col-sm-3">
				<button type="submit" class="btn btn-primary btn-lg btn-block">Ajouter d'une nouvelle catégorie type</button>
			</div>
		</div>
	</form>
</div>