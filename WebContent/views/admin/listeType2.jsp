<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="row">
	<h2>Liste des Types1</h2>
	

	<table class="table">
		<caption>Optional table caption.</caption>
		<thead>
			<tr>
				<th>Libelle</th>
				<th>Type1 Associé</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<a href="#" id="<s:property value="libelle2"/>"
						data-type="text" data-pk="<s:property value="idType2" />"
						data-url="<s:url action="modification-type1-produit"/>"
						data-title="Enter une nouvelle valeur de libelle">
						 	<s:property value="libelle2" />

					</a>
				</td>
				<td>
					<a href="#" id="<s:property value="idType1"/>"
						data-type="text" data-pk="<s:property value="idType2" />"
						data-url="<s:url action="modification-type1-produit"/>"
						data-title="Choisir un nouveau type 1">
						 	<s:property value="libelle1" />

					</a>
				</td>
				</tr>
		</tbody>
	</table>

	<s:iterator value="listeType2">
		<!--L'URL après href sert d'a --> 
		<a href="#"
			id="<s:property value="libelle2"/>" data-type="text"
			data-pk="<s:property value="idType2" />"
			data-url="<s:url action="modification-type1-produit"/>"
			data-title="Enter une nouvelle valeur de libelle1"> <s:property
					value="libelle2" />
			</a>
		</s:iterator>
	
	<a href="<s:url action="modification-type1-produit-form"/>">Liste
		des Types 1-Modification</a>
</div>