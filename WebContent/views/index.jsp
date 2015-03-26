<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="container-fluid">
	<div class="row">Hello Index</div>
	
	<p><a href="<s:url action="listeProduits"/>">Liste des produits</a></p><!-- BENOIT -->
	<p><a href="<s:url action="listeProducteurs"/>">Liste des producteurs</a></p><!-- BENOIT -->
	<p><a href="<s:url action="recherche"/>">Recherche</a></p><!-- NICOLAS -->
	
	<p><a href="<s:url action="ajout-producteur-form"/>">Ajout Producteur</a></p><!-- PA -->
	<p><a href="<s:url action="ajout-produit-form"/>">Ajout Produit</a></p><!-- PA -->
	<p><a href="<s:url action="ajout-type1-produit-form"/>">Ajout type 1</a></p><!-- JJ -->
	<p><a href="<s:url action="ajout-type2-produit-form"/>">Ajout type 2</a></p><!-- JJ -->
	<p><a href="<s:url action="modification-type1-produit-form"/>">Liste des Types 1-Modification</a></p><!-- JJ -->
	<p><a href="<s:url action="testInline"/>">Test Inline</a></p>
	
	<p><a href="<s:url action="listeUtilisateurs"/>"> Liste des utilisateurs</a></p><!-- CECILE -->
	<p><a href="<s:url action="formulaire"/>"> Ajout Utilisateur</a></p><!-- CECILE -->
		<p><a href="<s:url action="modifType2Form"/>">Liste des Types 2-Modification</a></p><!-- JJ -->
	<p><a href="<s:url action="testInline"/>">Test Inline</a></p><!-- JJ -->
</div>
