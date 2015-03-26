<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- @author: Julien Caillon -->

<h1>
	<i class="fa fa-credit-card"></i> Sélection du mode de paiement
</h1>

<table class="table table-striped table-hover">
	<tr class="radio addspace">
		<td><label> <input type="radio" name="optionsPaiement" id="optionsPaiement1" value="0" checked> 
			<img src="images/site/paiement0.png" /> Paiement sécurisé par cb ou paypal
		</label>
		<td>
	</tr>
	<tr class="radio addspace">
		<td><label> <input type="radio" name="optionsPaiement" id="optionsPaiement2" value="1"> 
			<img src="images/site/paiement1.jpg" /> Paiement par chèque (le traitement de la commande sera plus long!)
		</label>
		<td>
	</tr>
	<tr class="radio addspace">
		<td><label> <input type="radio" name="optionsPaiement" id="optionsPaiement3" value="2"> 
			<img src="images/site/paiement2.jpg" /> Paiement par virement bancaire (le traitement de la commande sera plus long!)
		</label>
		<td>
	</tr>
</table>

<nav>
	<ul class="pager">
		<li class="previous"><a href="mon-panier"><span aria-hidden="true">&larr;</span> Retour panier</a></li>
		<li class="next"><a role="button" id="idAfter3">Acheter! <span aria-hidden="true">&rarr;</span></a></li>
	</ul>
</nav>