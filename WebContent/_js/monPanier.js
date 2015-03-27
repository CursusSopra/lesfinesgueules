/*
 * @author Julien Caillon
 * 
 * Fonctions de la page "mon-panier"
 */

// on page load
$(function() {
	
	majMonPanierJSON();
	
});

function majMonPanierJSON() {
	$.getJSON('dataPanierJSON.action', function(data) {
		majMonPanier(data)
	});
}

// Met a jour le content principal
function majMonPanier(data) {

	// quel tab doit on afficher??
	var numTabCourant = $("#idTabCourant").attr("value");
	
	// suivant l'onglet sur lequel on est on charge le contenu.
	switch(parseInt(numTabCourant)) {
    case 2:
    	$('#idPanier2').load('loginHtml.action', function(data) {
    	    $("#idMeConnecter").unbind('click').click(function() {
    	    	$("#idNavBarLogin").dropdown("toggle");
    	    	//window.location.replace($(this).attr("href"));
    	    });
    	});
        break;
    case 3:
    	$('#idPanier3').load('paiementHtml.action', function(data) {
        	// Ajoute des events click sur les boutons next previous
            $("#idAfter3").unbind('click').click(function() {
            	var moyenPaiement = $('input[name="optionsPaiement"]:checked').val();
            	$("#idOptionsPaiement").attr("value", moyenPaiement);
        		$("#idTabCourant").attr("value", 4);
        		majMonPanierJSON();
            });
    	});
        break;
    case 4:
		// requete pour valider la commande
    	var moyenPaiement = $("#idOptionsPaiement").attr("value")
		$('#idPanier4').load('validerCommandeHtml.action?moyenPaiement=' + moyenPaiement, function() {
			majNavBarPanierJSON();
//			$('.my-price').number(true, 2, ',', ' ');
		});
		// autre maniere:
		// $.get('validerCommandeHtml.action', function(data) { $('#idPanier4').html(data); majNavBarPanierJSON(); });
        break;
    default:
    	// ######################################################################
    	// CONSTRUIT LA TABLE DE RECAP COMMANDE
    	var recapHtml = '' +
    	'<h1><i class="fa fa-cart-arrow-down"></i> Mon panier</h1>';

    	if (data.nbItems == 0) {
    		// panier vide
    		recapHtml += '<br>Oups! Mon panier est vide! Il est temps que je commence à acheter <i class="fa fa-heart"></i>';
        	var pagerHtml = '' +
        	'<nav>' +
        	'  <ul class="pager">' +
        	'    <li class="previous"><a href="listeProduits"><span aria-hidden="true">&larr;</span> Continuer mon shopping</a></li>' +
        	'  </ul>' +
        	'</nav>';
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
    			'					<a role="button" id="idMinus' + index + '" role="button" class="btn btn-warning btn-sm"><i class="fa fa-minus-circle"></i></a>' +
    			'					<a role="button" id="idTextQuantite' + index + '" role="button" class="btn btn-default disabled btn-sm"><i class="fa fa-times"></i> ' + elt.quantite + '</a>' +
    			'					<a role="button" id="idPlus' + index + '" role="button" class="btn btn-success btn-sm"><i class="fa fa-plus-circle"></i></a>' +
    			'					<input id="idProduit' + index + '" type="hidden" value="' + elt.idProduit + '">' +
    			'					<input id="idQuantite' + index + '" type="hidden" value="' + elt.quantite + '">&nbsp; &nbsp;' +
    			'				<a role="button" id="idTrash' + index + '" role="button" class="btn btn-danger btn-sm"><i class="glyphicon glyphicon-trash"></i></a>' +
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
        	var pagerHtml = '' +
        	'<nav>' +
        	'  <ul class="pager">' +
        	'    <li class="previous"><a href="listeProduits"><span aria-hidden="true">&larr;</span> Continuer mon shopping</a></li>' +
        	'    <li class="next"><a role="button" id="idAfter1">Procéder au paiement <span aria-hidden="true">&rarr;</span></a></li>' +
        	'  </ul>' +
        	'</nav>';
    	}
    	
    	$('#idPanier1').html(recapHtml + pagerHtml);
    	
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
    	
    	// Ajoute des events click sur les boutons next previous
        $("#idAfter1").unbind('click').click(function() {
        	if (data.idUtilisateur != -1) {
        		$("#idTabCourant").attr("value", 3);
        	} else {
        		$("#idTabCourant").attr("value", 2);
        	}
        	majMonPanierJSON();
        });
    	// ######################################################################
	} 
	
	// Change the current tab!
	$('#myTab a[href="#idPanier' + numTabCourant + '"]').tab('show');
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