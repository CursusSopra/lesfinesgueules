<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="row" data-lienUrl="<s:url action="testInline"/>" id="hiddenData">
	<h2>Liste des Types1</h2>
	<form>
		<select id="type1">
			<s:iterator value="listeType1">
				<option value="<s:property value="idType1" />">
					<s:property value="libelle1" />
				</option>
			</s:iterator>
		</select>
	</form>
	
	<ul id="retourJSON">
	</ul>

	<a href="<s:url action="testInline"/>">Liste des Types
		1-Modification</a>
</div>

<!-- //Rempli par JSON -->
<!-- <a id="Boeuf"  -->
<!--    data-url="/00-Template/testInline.action"  -->
<!--    data-pk="3"  -->
<!--    data-title="Enter une nouvelle valeur de libelle1"  -->
<!--    data-type="text"  -->
<!--    href="#"> -->
   
<!--    Boeuf -->
   
<!-- </a> -->
   
<!-- <a id="Fromage"  -->
<!-- class="editable editable-click"  -->
<!-- data-title="Enter une nouvelle valeur de libelle1"  -->
<!-- data-url="/00-Template/modifType1.action"  -->
<!-- data-pk="1"  -->
<!-- data-type="text"  -->
<!-- href="#" style="display: inline;"> Fromage </a> -->