/*
 * @author Julien Caillon
 * 
 * Fonctions du panier
 */

// on page load
$(function() {
	
	$.getJSON('dataPanierJSON.action', function(data) {
		majMonPanier(data)
	});
	
});

// Met a jour le panier dans la navbar
function majMonPanier(data) {

	var output = '' +
	'<h1><i class="fa fa-cart-arrow-down"></i> Mon panier</h1>';
	
	if (data.nbItems == 0) {
		
		// panier vide
		var output = '' +
		'	Oups! Mon panier est vide! Il est temps que je commence à acheter <i class="fa fa-heart"></i>' +
		'';
		
	} else {

		// maj du popup panier
		var output = '' +
		'<table class="table table-striped table-hover table-bordered">' +
		'	<tbody>' +
		'		<tr class="info">' +
		'			<th colspan="2" class="text-center">Produit</th>' +
		'			<th class="text-right">Prix unitaire</th>' +
		'			<th class="text-right">Prix total article</th>' +
		'		</tr>';
		
		
		// liste des items
		$.each(data.listeItems, function(index, elt) {
			output += '' +
			'		<tr>' +
			'			<td class="text-left monpanier-table-buttons">' +
			'				<a href="#" id="idMinus' + index + '"><i class="fa fa-minus-circle"></i></a> <i class="fa fa-times"></i>' +
			'				<b id="idTextQuantite' + index + '">' + elt.quantite + '</b>' +
			'				<a href="#" id="idPlus' + index + '"><i class="fa fa-plus-circle"></i></a>' +
			'				<input id="idProduit' + index + '" type="hidden" value="' + elt.idProduit + '">' +
			'				<input id="idQuantite' + index + '" type="hidden" value="' + elt.quantite + '">&nbsp;' +
			'				<a href="#" id="idTrash' + index + '"><i class="glyphicon glyphicon-trash"></i></a>' +
			'			</td>' +
			'			<td class="text-left"><a href="detailsProduit.action?idProduit=' + elt.idProduit + '">' + elt.designation + '</a></td>' +
			'			<td class="text-right">' +  $.number(elt.prix, 2, ',', ' ') + ' &euro;</td>' +
			'			<td class="text-right" id="idPrix' + index + '">' +  $.number(elt.prix * elt.quantite, 2, ',', ' ') + ' &euro;</td>' +
			'		</tr>';
		});
		
		// cout total
		output += '' +
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
	
	$('#idMyContent').html(output);
	
	//Ajout des events click sur les boutons de gestion du panier
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