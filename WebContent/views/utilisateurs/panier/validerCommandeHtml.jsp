<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- @author: Julien Caillon -->

<h1><i class="fa fa-thumbs-o-up"></i> Félicitation! Votre commande est passée!</h1>

<div class="alert alert-success" role="alert">
	Votre numéro de commande est le <s:property value="panier.idCommande" />, un email de confirmation vous a été envoyé (ou pas).
</div>

<table class="table table-striped table-hover table-bordered">
	<tbody>
		<tr class="info">
			<th class="text-left">Produit</th>
			<th class="text-right">Quantité</th>
			<th class="text-right">Prix unitaire</th>
		</tr>
<!-- 				<a href="detailsProduit.action?idProduit=' + elt.idProduit + '"> -->
<!-- 					<img alt="image" class="img-responsive img-thumbnail" id="idImage' + index + '" src="' + elt.photo + '"/> -->
<!-- 				</a> -->
		<s:iterator value="panier.listeItems">
			<s:url action="detailsProduit" var="act2">
				<s:param name="idProduit">
					<s:property value="idProduit" />
				</s:param>
			</s:url>
			<tr>
				<td class="text-left"><a href="<s:property value='#act2'/>"><s:property value="designation" /></a></td>
				<td class="text-right"><s:property value="quantite" /></td>
				<td class="text-right my-price"><s:property value="prix" /> &euro;</td>
			</tr>
		</s:iterator>
		
		<tr class="warning">
			<td colspan="2">Prix total de la commande</td>
			<td class="text-right my-price"> <s:property value="coutTotal" /> &euro;</td>
		</tr>
		<tr class="warning">
			<td colspan="2">Frais de port</td>
			<td class="text-right my-price"> <s:property value="fraisPort" /> &euro;</td>
		</tr>
	</tbody>
</table>

<nav>
	<ul class="pager">
		<li class="previous"><a href="mon-panier"><span aria-hidden="true">&larr;</span> Retour panier</a></li>
	</ul>
</nav>