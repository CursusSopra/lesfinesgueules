<%@ taglib prefix="s" uri="/struts-tags"%>

<!-- @author Julien Caillon -->

<!-- NAVIGATION -->
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container">

		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<s:url action='retourIndex' />"><span class="glyphicon glyphicon-home" aria-hidden="true"></span></a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">


<!-- 			MENU PRINCIPAL -->
			<ul class="nav navbar-nav">
				<li><a href="<s:url action='listeProduits' />" title="Liste de tous nos produits">Tous nos produits</a></li>
				<s:iterator value="listeType1">
					<s:url action="listeProduits" var="act1">
						<s:param name="idType1">
							<s:property value="idType1" />
						</s:param>
					</s:url>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" title="">
							<s:property value="libelle1" /> <i class="caret"></i>
						</a>
						<ul class="dropdown-menu">
							<li><a href="<s:property value='#act1'/>"><i class="fa fa-arrow-circle-down"></i> <s:property value="libelle1" /></a></li>
	 						<li class="dropdown-header">Sous-catégories :</li>
							<s:iterator value="listeType2">
								<s:url action="listeProduits" var="act2">
									<s:param name="idType2">
										<s:property value="idType2" />
									</s:param>
								</s:url>
								<li>
									<a href="<s:property value='#act2'/>">
										<i class="fa fa-arrow-circle-right"></i> <s:property value="libelle2" />
									</a>
								</li>
							</s:iterator>
						</ul>
					</li>
				</s:iterator>
				<li><a href="<s:url action='listeProducteurs' />" title="Liste de nos producteurs">Producteurs</a></li>
			</ul>

			
<!-- 			RECHERCHE -->
<!-- 			<form class="navbar-form navbar-left" role="search"> -->
<!-- 				<div class="input-group"> -->
<!-- 					<input type="text" class="form-control" placeholder="Rechercher" name="id" id="idrech"> -->
<!-- 					<div class="input-group-btn"> -->
<!-- 						<button class="btn btn-default" type="submit"> -->
<!-- 							<i class="glyphicon glyphicon-search"></i> -->
<!-- 						</button> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</form> -->


<!-- 			RIGHT ALIGN -->
			<ul class="nav navbar-nav navbar-right">


<!-- 				ADMIN -->
				<li class="dropdown">
					<a class="dropdown-toggle btn btn-default navbarbutton" data-toggle="dropdown">
						<b class="caret"></b>&nbsp;&nbsp; <i class="fa fa-cogs navbaricon"></i>
					</a>
					<ul class="dropdown-menu">
						<li class="dropdown-header">Mon compte :</li>
						<li><a href="details.action?idUtilisateur=1"><i class="fa fa-arrow-circle-right"></i> Mon compte (id=1)</a></li>
						<li><a href="<s:url action="mes-commandes-passees"/>"><i class="fa fa-arrow-circle-right"></i> Mes commandes passées</a></li>
						<li class="divider"></li>
						<li class="dropdown-header">Utilisateurs :</li>
						<li><a href="<s:url action="liste-utilisateurs"/>"><i class="fa fa-arrow-circle-right"></i> Liste des utilisateurs</a></li>
						<li class="divider"></li>
						<li class="dropdown-header">Contenu du site :</li>
						<li><a href="<s:url action="ajout-produit-form"/>"><i class="fa fa-arrow-circle-right"></i> Ajout Produit</a></li><!-- PA -->
						<li><a href="<s:url action="ajout-type1-produit-form"/>"><i class="fa fa-arrow-circle-right"></i> Ajout type 1</a></li><!-- JJ -->
						<li><a href="<s:url action="ajout-type2-produit-form"/>"><i class="fa fa-arrow-circle-right"></i> Ajout type 2</a></li><!-- JJ -->
						<li><a href="<s:url action="modification-type1-produit-form"/>"><i class="fa fa-arrow-circle-right"></i> Modifs type1</a></li><!-- JJ -->
						<li><a href="<s:url action="ajout-producteur-form"/>"><i class="fa fa-arrow-circle-right"></i> Ajout Producteur</a></li><!-- PA -->
						<li class="divider"></li>
						<li class="dropdown-header">Autres? :</li>
					</ul>
				</li>
				
<!-- 				UTILISATEUR -->
				<li class="dropdown">
					<a class="dropdown-toggle btn btn-default navbarbutton" data-toggle="dropdown" id="idNavBarLogin">
						<b class="caret"></b>&nbsp;&nbsp; <i class="glyphicon glyphicon-user navbaricon"></i>
					</a>
					<div class="dropdown-menu userdropdown">
<!-- 				LOGGED IN -->
						<s:if test="utilisateur != null">
							<div class="row">
								<div class="col-md-12">
									<b class="panier-title">
										<i class="fa fa-child "></i> Bienvenu(e), 
										<s:property value="utilisateur.prenom" /> <s:property value="utilisateur.nom" />
									</b>
									<div class="row addspace">
										<div class="col-md-12">
											<a class="btn btn-primary input-block-level form-control" href="<s:url action='mon-compte' />" role="button">
												Voir mon compte
											</a>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12">
											<a class="btn btn-danger input-block-level form-control" href="<s:url action='' />" role="button">
												Déconnexion
											</a>
										</div>
									</div>
								</div>
							</div>
						</s:if>
				
<!-- 				NOT LOGGED IN -->
						<s:else>
							<div class="row">
								<div class="col-md-12">
									<form class="form" role="form" method="post" action="loginControl" accept-charset="UTF-8" id="login-nav">
										<div class="form-group">
											<label class="sr-only" for="idEmail">Adresse mail</label> <input type="email" class="form-control" id="idEmail"
												placeholder="Adresse mail" name="email" required>
										</div>
										<div class="form-group">
											<label class="sr-only" for="idMdp">Mot de passe</label> <input type="password" class="form-control" id="idMdp"
												placeholder="Mot de passe" name="mdp" required>
										</div>
										<div class="checkbox">
											<label> <input type="checkbox"> Se souvenir de moi
											</label>
										</div>
										<div class="form-group">
											<button type="submit" class="btn btn-success btn-block">S'identifier</button>
										</div>
									</form>
								</div>
							</div>
							<a class="btn btn-primary input-block-level form-control" href="<s:url action='formulaire-inscription' />" role="button">
								Créer un compte
							</a>
						</s:else>
						</div>
					</li>
					

<!-- 				PANIER -->
				<li class="dropdown">
					<a class="dropdown-toggle btn btn-default navbarbutton" data-toggle="dropdown">
						<b class="caret"></b>&nbsp;&nbsp; <i class="glyphicon glyphicon-shopping-cart navbaricon" id="idPanierIcon"></i> <span class="badge" id="idBadgePanier"></span>
					</a>
					<div class="dropdown-menu table-responsive panier" id="idMonPanier">
						<!-- 	Contenu de mon panier -->
						<div id="idMonPanierContent">
						</div>
					</div>
				</li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container -->
</nav>