<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="row">
	<h1 class="h1">Liste des types 1 de produits</h1>

	<table class="table table-stripped table-hover">
		<thead>
			<tr>
				<th class="col-md-2"></th>
				<th class="col-md-10">Libelle</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="listeType1">
				<s:url action="modifType1" var="dp">
					<s:property value="idType1" />
				</s:url>
			</s:iterator>
		</tbody>
	</table>

</div>