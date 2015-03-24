<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="row">
	<h2>Liste des Types1</h2>
	<ul class="list-group">


		<s:iterator value="listeType1">
			<li class="list-group-item">
				<a href="<s:url action="modification-type1-produit-form"/>"
				   id="libelle1" data-type="text" 
				   data-pk="<s:property value="idType1" />" 
				   data-url="/post"
				   data-title="Enter une nouvelle valeur de libelle1">
					<s:property value="libelle1" />
				</a>
			</li>
		</s:iterator>


		
	</ul>
	<a href="<s:url action="modification-type1-produit-form"/>">ggd</a>
</div>