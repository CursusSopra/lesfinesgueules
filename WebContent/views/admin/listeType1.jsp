<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="row">
	<h2>Liste des Types1</h2>
	<ul class="list-group">


		<s:iterator value="listeType1">
	
			<li class="list-group-item">
			
				<!--L'URL après href sert d'a -->
				<a href="#"
				   id="<s:property value="libelle1"/>" data-type="text" 
				   data-pk="<s:property value="idType1" />" 
				   data-url="<s:url action="modification-type1-produit"/>"
				   data-title="Enter une nouvelle valeur de libelle1"  >
				
						<s:property value="libelle1"/>
				
				</a>
			</li>
			
		</s:iterator>


		
	</ul>
	<a href="<s:url action="modification-type1-produit-form"/>">Liste des Types 1-Modification</a>
</div>