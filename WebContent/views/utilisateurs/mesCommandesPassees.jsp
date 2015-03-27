<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h1><i class="fa fa-calendar"></i> Liste de mes commandes :</h1>

<s:iterator value="listeCommandesPassees">

	<table class="table table-striped table-hover table-bordered">
		<caption>
			<i class="fa fa-arrow-circle-o-right"></i> 
			Commande n°<s:property value="idCommande" />, 
			effectuée le <s:property value="tsValidation" />
		</caption>
		<tbody>
			<tr class="info">
				<th class="text-left">Produit</th>
				<th class="text-right">Quantité</th>
				<th class="text-right">Prix unitaire</th>
			</tr>
			<s:iterator value="listeItems">
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

</s:iterator>