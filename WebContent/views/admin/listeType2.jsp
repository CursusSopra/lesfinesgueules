<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="row" data-lienUrl="<s:url action="modifType2"/>" id="hiddenData">
	<h2>Liste de Modification des types 1</h2>
	<form class="form-control">
		<select id="type1">
		<option value="Choisisez un type 1"></option>
			<s:iterator value="listeType1">
				<option value="<s:property value="idType1" />">
					<s:property value="libelle1" />
				</option>
			</s:iterator>
		</select>
	</form>
	
	<ul id="retourJSON">
	</ul>
</div>
<span id="hiddenPath" data-lienUrl="<s:url action="testInline"/>"></span>