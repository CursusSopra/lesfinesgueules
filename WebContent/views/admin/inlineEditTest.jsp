<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!-- <div class="row"> -->
<!-- 	<h2>Testing is awesome !</h2> -->
<!-- 	<a href="#" id="address" data-type="address" data-pk="1">awesome</a> -->
<!-- </div> -->

<s:iterator value="listeProduits" status="stat">
	<div class="row">
	<div class="address">
		<a href="#" id="<s:property value="idProduit" />" data-type="address" data-pk="1"><s:property value="designation" /></a>
	</div>
	</div>
</s:iterator>
<div class="retourJSON"></div>
<%-- <span id="hiddenData"  --%>
<%-- 	data-lienUrl="<s:url action="testInline"/>" --%>
<%-- 	data-designation="Designation" --%>
<%-- 	data-description="Description" --%>
<%-- 	data-prix="16" --%>
<%-- 	data-idProduit="<s:property value="idProduit"/>" --%>
<%-- 			></span> --%>

<%-- <span id="hiddenData"  --%>
<%--  	data-lienUrl="<s:url action="testInline"/>"  --%>
<%--  	data-designation="Designation"  --%>
<%--  	data-description="Description"  --%>
<%--  	data-prix="16"  --%>
<%-- 	data-idProduit=""  --%>
<%--  			></span>  --%>