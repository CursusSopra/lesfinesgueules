<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s"	uri="/struts-tags"%>

<div class="row">
	<h1 class="h1">Liste des produits</h1>

	<div class="container">
		<div class="pull-left">
			<p>R&eacute;galez-vous les yeux avec nos produits du terroir.</p>
		</div>
		<div class="pull-rigth">
			<p class="text-right">Nombre de produits &agrave; afficher :
				<select id="displayNumber" name="displayNumber">
					<option value="4">4</option>
					<option value="8">8</option>
					<option value="12">12</option>
					<option value="24">24</option>
				</select>
			</p>
		</div>
	</div>
	
	<input type="hidden" id="pageNumber" name="pageNumber" value="1"/>	
	<input type="hidden" id="numberOfPages" name="numberOfPages" value=""/>
	
	<input type="hidden" id="idType1" name="idType1" value="<s:property value="idType1"/>"/>
	<input type="hidden" id="idType2" name="idType2" value="<s:property value="idType2"/>"/>
	<input type="hidden" id="idProducteur" name="idProducteur" value="<s:property value="idProducteur"/>"/>
	
	<div class="row" id="listeProduits">
	
		<!-- Contenu de la liste des produits -->
	
	</div>
	
	<div class="row">
		<ul class="pager">
			<li><a id="pPage">Pr&eacute;c&eacute;dents</a></li>
			<li><span id="nbPages"></span></li>
			<li><a id="nPage">Suivants</a></li>
		</ul>
	</div>
</div>