<%@ taglib prefix="s" uri="/struts-tags"%>

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

			<ul class="nav navbar-nav">
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
							<li><a href="<s:property value='#act1'/>"><i class="fa fa-caret-right"></i> <s:property value="libelle1" /></a></li>
	 						<li class="divider"></li>
	 						<li class="dropdown-header">Sous-catégories :</li>
							<s:iterator value="listeType2">
								<s:url action="listeProduits" var="act2">
									<s:param name="categorie">
										<s:property value="idType2" />
									</s:param>
								</s:url>
								<li>
									<a href="<s:property value='#act2'/>">
										<i class="fa fa-caret-right"></i> <s:property value="libelle2" />
									</a>
								</li>
							</s:iterator>
						</ul>
					</li>
				</s:iterator>
				<li><a href="#" title="nos prod">Producteurs</a></li>
			</ul>

<!-- 			<ul class="nav navbar-nav"> -->
<!-- 				<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" title="selection de charcut">Fromagerie <b -->
<!-- 						class="caret"></b></a> -->
<!-- 					<ul class="dropdown-menu"> -->
<!-- 						<li><a href="#"><i class="fa fa-globe fa-fw"></i> Vaches</a></li> -->
<!-- 						<li><a href="#"><i class="fa fa-star fa-fw"></i> Chèvre</a></li> -->
<!-- 						<li class="divider"></li> -->
<!-- 						<li class="dropdown-header">Catégories de fromage :</li> -->
<!-- 						<li><a href="#">Doux</a></li> -->
<!-- 						<li><a href="">Fort</a></li> -->
<!-- 					</ul></li> -->
<!-- 				<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" title="selection de charcut">Charcuterie -->
<!-- 						<b class="caret"></b> -->
<!-- 				</a> -->
<!-- 					<ul class="dropdown-menu"> -->
<!-- 						<li><a href="#">1</a></li> -->
<!-- 						<li><a href="#">3</a></li> -->
<!-- 					</ul></li> -->
<!-- 				<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" title="selection de charcut">Epicerie <b -->
<!-- 						class="caret"></b></a> -->
<!-- 					<ul class="dropdown-menu"> -->
<!-- 						<li><a href="#">1</a></li> -->
<!-- 						<li><a href="#">3</a></li> -->
<!-- 					</ul></li> -->
<!-- 				<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" title="selection de charcut">Caviste <b -->
<!-- 						class="caret"></b></a> -->
<!-- 					<ul class="dropdown-menu"> -->
<!-- 						<li><a href="#">1</a></li> -->
<!-- 						<li><a href="#">3</a></li> -->
<!-- 					</ul></li> -->
<!-- 				<li><a href="#" title="nos prod">Producteurs</a></li> -->
<!-- 			</ul> -->

			<form class="navbar-form navbar-left" role="search">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="Rechercher" name="id" id="idrech">
					<div class="input-group-btn">
						<button class="btn btn-default" type="submit">
							<i class="glyphicon glyphicon-search"></i>
						</button>
					</div>
				</div>
			</form>

			<ul class="nav navbar-nav navbar-right">

				<li class="dropdown">
					<a class="dropdown-toggle btn btn-default navbarbutton" data-toggle="dropdown">
						<b class="caret"></b>&nbsp;&nbsp; <i class="glyphicon glyphicon-user navbaricon"></i>
					</a>
					<div class="dropdown-menu userdropdown">
						<a class="btn btn-primary" href="<s:url action='mon-compte' />" role="button">
							Voir mon compte
						</a>
						<hr>
						<a class="btn btn-danger" href="<s:url action='' />" role="button">
							Déconnexion
						</a>
					</div>
				</li>

				<li class="dropdown">
					<a class="dropdown-toggle btn btn-default navbarbutton" data-toggle="dropdown">
						<b class="caret"></b>&nbsp;&nbsp; <i class="glyphicon glyphicon-user navbaricon"></i>
					</a>
					<div class="dropdown-menu userdropdown">
						<div class="row">
							<div class="col-md-12">
								<form class="form" role="form" method="post" action="login" accept-charset="UTF-8" id="login-nav">
									<div class="form-group">
										<label class="sr-only" for="idEmail">Adresse mail</label> <input type="email" class="form-control" id="idEmail"
											placeholder="Adresse mail" required>
									</div>
									<div class="form-group">
										<label class="sr-only" for="idMdp">Mot de passe</label> <input type="password" class="form-control" id="idMdp"
											placeholder="Mot de passe" required>
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
						<hr>
						<a class="btn btn-primary" href="<s:url action='inscription' />" role="button">
							Créer un compte
						</a>
					</div>
				</li>

				<li class="dropdown">
					<a class="dropdown-toggle btn btn-default navbarbutton" data-toggle="dropdown">
						<b class="caret"></b>&nbsp;&nbsp; <i class="glyphicon glyphicon-shopping-cart navbaricon"></i> <span class="badge">4</span>
					</a>
					<div class="dropdown-menu table-responsive userdropdown" id="idMonPanier">
						<table class="table table-striped table-bordered table-condensed">
							<tbody>
								<tr>
									<th></th>
									<th>Produit</th>
									<th>Qte</th>
									<th>Prix</th>
								</tr>
								<tr>
									<td><i class="glyphicon glyphicon glyphicon-trash"></i></td>
									<td><a href="#">Frometon 1</a></td>
									<td><span class="label label-primary">x1</span></td>
									<td>110 &euro;</td>
								</tr>
								<tr>
									<td><i class="glyphicon glyphicon glyphicon-trash"></i></td>
									<td><a href="#">Frometon 3</a></td>
									<td><span class="label label-primary">x1</span></td>
									<td>110 &euro;</td>
								</tr>
								<tr class="warning">
									<td colspan="3">Total</td>
									<td>220 &euro;</td>
								</tr>
							</tbody>
						</table>
						<a href="" class="btn btn-success btn-block">
							Aller au panier
						</a>
					</div>
				</li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container -->
</nav>