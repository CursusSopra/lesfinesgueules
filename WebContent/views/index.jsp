<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="container-fluid">
	<div class="row">Hello Index</div>
	
	<p><a href="<s:url action="listeProduits"/>">Liste des produits</a></p><!-- BENOIT -->
	<p><a href="<s:url action="listeProducteurs"/>">Liste des producteurs</a></p><!-- BENOIT -->
	<p><a href="<s:url action="recherche"/>">Recherche</a></p><!-- NICOLAS -->
	
	<p><a href="<s:url action="ajout-producteur-form"/>">Ajout Producteur</a></p><!-- PA -->
	<p><a href="<s:url action="ajout-produit-form"/>">Ajout Produit</a></p><!-- PA -->
	<p><a href="<s:url action="modification-type1-produit-form"/>">Liste des Types 1-Modification</a></p>
</div>