<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="container-fluid">
	<div class="row">Hello Index</div>

	<p><a href="<s:url action="listeProduits"/>">Liste des produits</a></p><!-- BENOIT -->
	<p><a href="<s:url action="listeProducteurs"/>">Liste des producteurs</a></p><!-- BENOIT -->
	<p><a href="<s:url action="recherche"/>">Recherche</a></p><!-- NICOLAS -->

<%-- 	<p><a href="<s:url action="ajout-producteur-form"/>">Ajout Producteur</a></p><!-- PA --> --%>
<%-- 	<p><a href="<s:url action="ajout-produit-form"/>">Ajout Produit</a></p><!-- PA --> --%>
<%-- 	<p><a href="<s:url action="ajout-type1-produit-form"/>">Ajout type 1</a></p><!-- JJ --> --%>
<%-- 	<p><a href="<s:url action="ajout-type2-produit-form"/>">Ajout type 2</a></p><!-- JJ --> --%>
<%-- 	<p><a href="<s:url action="modification-type1-produit-form"/>">Liste des Types 1-Modification</a></p><!-- JJ --> --%>
	<p><a href="<s:url action="testInline"/>">Test Inline</a></p>

	<p><a href="<s:url action="listeUtilisateurs"/>"> Liste des utilisateurs</a></p><!-- CECILE -->
	<p><a href="<s:url action="formulaire"/>"> Ajout Utilisateur</a></p><!-- CECILE -->
		<p><a href="<s:url action="modifType2Form"/>">Liste des Types 2-Modification</a></p><!-- JJ -->
	<p><a href="<s:url action="testInline"/>">Test Inline</a></p><!-- JJ -->
</div>




<div id="carousel-example-generic" class="carousel slide" data-ride="carousel" >
	<!-- Indicators -->
	<ol class="carousel-indicators">
		<li data-target="#carousel-example-generic" data-slide-to="0"
			class="active"></li>
		<li data-target="#carousel-example-generic" data-slide-to="1"></li>
		<li data-target="#carousel-example-generic" data-slide-to="2"></li>
	</ol>

	<!-- Wrapper for slides -->
	<div class="carousel-inner" role="listbox">
		<div class="item active">
			<img src="/lesfinesgueules/content/images/Vache.jpg" alt="..." style="margin: 0 auto">
			<div class="carousel-caption">
				<p style="font-size: 1.0em;">
				Je m'appelle marguerite. Je serai votre h�te tout au long de votre navigation pour d�couvrir les
				meilleurs produits de ma r�gion RHONE-ALPES.
				</p>
			</div>
		</div>
		<s:iterator value="listeProduitsRandom">
			<div class="item">
<!-- 				<img src="/lesfinesgueules/content/images/default.jpg" alt="..." style="margin: 0 auto"> -->
				<img src="/lesfinesgueules/content/images/<s:property value="photo"/>" alt="..." style="margin: 0 auto">
				<div class="carousel-caption">
					<table class="table table-stripped table-hover">
						<thead>
							<tr>
								<th>
									<button type="button" class="btn btn-primary">
										<s:property value="designation"/>
									</button>
								</th>
								<th>
									<button type="button" class="btn btn-danger">
										<s:property value="prix" />&nbsp;<span class="glyphicon glyphicon-euro"></span>
									</button>
								</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					
				</div>
			</div>
		</s:iterator>
	</div>

	<!-- Controls -->
	<a class="left carousel-control" href="#carousel-example-generic"
		role="button" data-slide="prev"> <span
		class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> <span
		class="sr-only">Previous</span>
	</a> <a class="right carousel-control" href="#carousel-example-generic"
		role="button" data-slide="next"> <span
		class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> <span
		class="sr-only">Next</span>
	</a>
</div>


