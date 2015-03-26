<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- @author: Julien Caillon -->

<input id="idTabCourant" type="hidden" value="1">
<input id="idOptionsPaiement" type="hidden" value="0">

<div role="tabpanel">
	<ul class="nav nav-tabs" role="tablist" id="myTab">
		<li role="presentation">
			<a href="#idPanier1" aria-controls="idPanier1" id="idPanier1-tab" role="tab">1. Récapitulatif panier</a>
		</li>
		<li role="presentation">
			<a href="#idPanier2" aria-controls="idPanier2" id="idPanier2-tab" role="tab">2. Login</a>
		</li>
		<li role="presentation">
			<a href="#idPanier3" aria-controls="idPanier3" id="idPanier3-tab" role="tab">3. Paiement</a>
		</li>
		<li role="presentation">
			<a href="#idPanier4" aria-controls="idPanier4" id="idPanier4-tab" role="tab">4. Commande finalisée</a>
		</li>
	</ul>
	<div class="tab-content">
		<div role="tabpanel" class="tab-pane fade" id="idPanier1" aria-labelledby="idPanier1-tab">
		</div>
		<div role="tabpanel" class="tab-pane fade" id="idPanier2" aria-labelledby="idPanier2-tab">
		</div>
		<div role="tabpanel" class="tab-pane fade" id="idPanier3" aria-labelledby="idPanier3-tab">
		</div>
		<div role="tabpanel" class="tab-pane fade" id="idPanier4" aria-labelledby="idPanier3-tab">
		</div>
	</div>
</div>