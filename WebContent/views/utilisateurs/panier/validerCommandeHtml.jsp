<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- @author: Julien Caillon -->

<h1><i class="fa fa-thumbs-o-up"></i> Cool! Commande pass�e!</h1>

<div>
	Num�ro de commande <s:property value="panier.idCommande" />
</div>

<nav>
	<ul class="pager">
		<li class="previous"><a href="mon-panier"><span aria-hidden="true">&larr;</span> Retour panier</a></li>
	</ul>
</nav>