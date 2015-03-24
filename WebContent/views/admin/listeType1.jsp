<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="row">
	<h2>Liste des Types1</h2>
	<ul class="list-group">
		<li class="list-group-item">Element De la liste</li>
		<li class="list-group-item">Dapibus ac facilisis in</li>
		<li class="list-group-item">Morbi leo risus</li>
		<li class="list-group-item">Porta ac consectetur ac</li>
		<li class="list-group-item">Vestibulum at eros</li>
	</ul>
	<div class="col-sm-4">
		<a href="<s:url action="modification-type1-produit-form"/>"
			id="libelle1" data-type="text" data-pk="1" data-url="/post"
			data-title="Enter username">superuser</a>
	</div>

</div>