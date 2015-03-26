/*
 * @author Julien Caillon
 * 
 * Fonctions de la page "mon-panier"
 */

// on page load
$(function() {
	
	majMonPanierContent();
	
});

function majMonPanierContent() {
	$.getJSON('dataPanierJSON.action', function(data) {
		majMonPanier(data)
	});
}

// Met a jour le content principal
function majMonPanier(data) {

	// quel tab doit on afficher??
	var numTabCourant = $("#idTabCourant").attr("value");
	
	// ######################################################################
	// RECAP HTML
	var recapHtml = '' +
	'<h1><i class="fa fa-cart-arrow-down"></i> Mon panier</h1>';

	if (data.nbItems == 0) {
		
		// panier vide
		recapHtml += '' +
		'	Oups! Mon panier est vide! Il est temps que je commence à acheter <i class="fa fa-heart"></i>' +
		'';
		
	} else {
		
		// maj du popup panier
		recapHtml += '' +
		'<table class="table table-striped table-hover table-bordered">' +
		'	<tbody>' +
		'		<tr class="info">' +
		'			<th colspan="2" class="text-center">Produit</th>' +
		'			<th class="text-right">Prix unitaire</th>' +
		'			<th class="text-right">Prix total article</th>' +
		'		</tr>';
		
		
		// liste des items
		$.each(data.listeItems, function(index, elt) {
			recapHtml += '' +
			'		<tr>' +
			'			<td class="text-left monpanier-table-buttons">' +
			'					<a href="#" id="idMinus' + index + '" role="button" class="btn btn-warning btn-sm"><i class="fa fa-minus-circle"></i></a>' +
			'					<a href="#" id="idTextQuantite' + index + '" role="button" class="btn btn-default disabled btn-sm"><i class="fa fa-times"></i> ' + elt.quantite + '</a>' +
			'					<a href="#" id="idPlus' + index + '" role="button" class="btn btn-success btn-sm"><i class="fa fa-plus-circle"></i></a>' +
			'					<input id="idProduit' + index + '" type="hidden" value="' + elt.idProduit + '">' +
			'					<input id="idQuantite' + index + '" type="hidden" value="' + elt.quantite + '">&nbsp; &nbsp;' +
			'				<a href="#" id="idTrash' + index + '" role="button" class="btn btn-danger btn-sm"><i class="glyphicon glyphicon-trash"></i></a>' +
			'			</td>' +
			'			<td class="text-left"><a href="detailsProduit.action?idProduit=' + elt.idProduit + '">' + elt.designation + '</a></td>' +
			'			<td class="text-right">' +  $.number(elt.prix, 2, ',', ' ') + ' &euro;</td>' +
			'			<td class="text-right" id="idPrix' + index + '">' +  $.number(elt.prix * elt.quantite, 2, ',', ' ') + ' &euro;</td>' +
			'		</tr>';
		});
		
		// cout total
		recapHtml += '' +
		'		<tr class="warning">' +
		'			<td colspan="3">Prix total de la commande</td>' +
		'			<td class="text-right">' + $.number(data.coutTotal, 2, ',', ' ') + ' &euro;</td>' +
		'		</tr>' +
		'		<tr class="warning">' +
		'			<td colspan="3">Frais de port</td>' +
		'			<td class="text-right">' + $.number(data.fraisPort, 2, ',', ' ') + ' &euro;</td>' +
		'		</tr>' +
		'	</tbody>' +
		'</table>';
		
	}
	// ######################################################################
	
	var loginHtml = '' + 
	'<h1><i class="fa fa-user-secret "></i> Merci de vous connecter avant de continuer!</h1>';
	
	var paiementHtml = '' + 
	'<h1><i class="fa fa-credit-card"></i> Modalités de paiement</h1>';
	
	var valideeHtml = '' + 
	'<h1><i class="fa fa-credit-card"></i> Cool! Commande passée!</h1>';
	
	// ######################################################################
	// TABS HTML
	var tabsHtml = '' +
	'<div role="tabpanel">' +
	'	<ul class="nav nav-tabs" role="tablist" id="myTab">' +
	'		<li role="presentation"><a href="#idPanier1" aria-controls="idPanier1" id="idPanier1-tab" role="tab">1. Récapitulatif panier</a></li>' +
	'		<li role="presentation"><a href="#idPanier2" aria-controls="idPanier2" id="idPanier2-tab" role="tab">2. Login</a></li>' +
	'		<li role="presentation"><a href="#idPanier3" aria-controls="idPanier3" id="idPanier3-tab" role="tab">3. Paiement</a></li>' +
	'		<li role="presentation"><a href="#idPanier4" aria-controls="idPanier4" id="idPanier4-tab" role="tab">4. Commande finalisée</a></li>' +
	'	</ul>'+
	'	<div class="tab-content">'+
	'		<div role="tabpanel" class="tab-pane fade" id="idPanier1" aria-labelledby="idPanier1-tab">' +
				recapHtml +
	'		</div>' +
	'		<div role="tabpanel" class="tab-pane fade" id="idPanier2" aria-labelledby="idPanier2-tab">' +
				loginHtml + 
	'		</div>' +
	'		<div role="tabpanel" class="tab-pane fade" id="idPanier3" aria-labelledby="idPanier3-tab">' +
				paiementHtml + 
	'		</div>' +
	'		<div role="tabpanel" class="tab-pane fade" id="idPanier4" aria-labelledby="idPanier3-tab">' +
				valideeHtml + 
	'		</div>' +
	'	</div>';
	'</div>';
	
	// PAGER
	var pagerHtml = '' +
	'<nav>' +
	'  <ul class="pager">';
	
	if (numTabCourant == 1) {
		pagerHtml += '' +
		'    <li class="previous"><a href="listeProduits"><span aria-hidden="true">&larr;</span> Continuer mon shopping</a></li>' +
		'    <li class="next"><a href="#" id="idAfter1">Procéder au paiement <span aria-hidden="true">&rarr;</span></a></li>';
	} else if (numTabCourant == 2) {
		pagerHtml += '' +
		'    <li class="previous"><a href="mon-panier"><span aria-hidden="true">&larr;</span> Retour panier</a></li>';
	} else if (numTabCourant == 3) {
		pagerHtml += '' +
		'    <li class="previous"><a href="mon-panier"><span aria-hidden="true">&larr;</span> Retour panier</a></li>' +
		'    <li class="next"><a href="#" id="idAfter3">Acheter! <span aria-hidden="true">&rarr;</span></a></li>';
	}
	
	pagerHtml += '' +
	'  </ul>' +
	'</nav>';
	
	$('#idMyContent').html(tabsHtml + pagerHtml);
	// ######################################################################
	
	// Change the current tab!
	$('#myTab a[href="#idPanier' + numTabCourant + '"]').tab('show');
	
	
	// Ajoute des events click sur les boutons next previous
    $("#idAfter1").unbind('click').click(function() {
    	if (data.idUtilisateur != -1) {
    		$("#idTabCourant").attr("value", 3);
    	} else {
    		$("#idTabCourant").attr("value", 2);
    	}
    	majMonPanierContent();
    });
    $("#idAfter3").unbind('click').click(function() {
		$("#idTabCourant").attr("value", 4);
		majMonPanierContent();
    });
    
	
	// Ajout des events click sur les boutons de gestion du panier
	$.each(data.listeItems, function(index, elt) {
	    $("#idPlus" + index).unbind('click').click(function() {
	    	addItemMonPanier(elt.idProduit, 1, index, elt.prix);
	    });
		
	    $("#idMinus" + index).unbind('click').click(function() {
	    	removeItemPanier(elt.idProduit, 1, index, elt.prix);
	    });
	    
	    $("#idTrash" + index).unbind('click').click(function() {
	    	removeItemPanier(elt.idProduit, 999, index, elt.prix);
	    });
	});
}


//Ajout d'un item produit dans le panier avec une certaine quantite
function addItemMonPanier(idProduit, toAdd, index, prix) {
	$.getJSON('addItemJSON.action?idProduit=' + idProduit + '&quantite=' + toAdd, function(data) {
		if(data.updateSuccessful) {	
			// maj du panier dans la navbar!
			majNavBarPanier(data);
			
			majMonPanier(data);
		}
	});
}


// Retrait d'un item produit dans le panier avec une certaine quantite
function removeItemPanier(idProduit, toRem, index, prix) {
	$.getJSON('removeItemJSON.action?idProduit=' + idProduit + '&quantite=' + toRem, function(data) {
		if(data.updateSuccessful) {
			// maj du panier dans la navbar!
			majNavBarPanier(data);
			
			majMonPanier(data);
		}
	});
}