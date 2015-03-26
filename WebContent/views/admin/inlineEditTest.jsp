<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="row">
	<h2>Testing is awesome !</h2>
	<a href="#" id="address" data-type="address" data-pk="1">awesome</a>
</div>
<span id="hiddenData" 
	data-lienUrl="<s:url action="testInline"/>"
	data-designation="Designation"
	data-description="Description"
	data-prix="16"
			></span>
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