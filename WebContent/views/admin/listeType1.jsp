<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
	rel="stylesheet">
<script src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>

<div class="row">

	<div class="col-sm-4">
		<a href="<s:url action="modification-type1-produit-form"/>"
			id="libelle1" data-type="text" data-pk="1" data-url="/post"
			data-title="Enter username">superuser</a>
	</div>
	<!-- 	<table class="table table-stripped table-hover"> -->
	<!-- 		<thead> -->
	<!-- 			<tr> -->
	<!-- 				<th class="col-md-2"></th> -->
	<!-- 				<th class="col-md-10">Libelle</th> -->
	<!-- 			</tr> -->
	<!-- 		</thead> -->
	<!-- 		<tbody> -->
	<%-- 			<s:iterator value="listeType1"> --%>
	<%-- 				<s:url action="modifType1" var="dp"> --%>
	<%-- 					<s:property value="idType1" /> --%>
	<%-- 				</s:url> --%>
	<%-- 			</s:iterator> --%>
	<!-- 		</tbody> -->
	<!-- 	</table> -->

</div>