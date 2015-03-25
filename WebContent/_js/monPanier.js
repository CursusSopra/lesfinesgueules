/*
 * @author Julien Caillon
 * 
 * Fonctions du panier
 */

// on page load
$(function() {
	
	majMonPanier();

});

// Met a jour le panier dans la navbar
function majMonPanier() {

	$.getJSON('dataPanierJSON.action', function(data) {
		
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
			'			<th>Produit</th>' +
			'			<th>Quantité</th>' +
			'			<th class="text-right">Prix</th>' +
			'		</tr>';
			
			
			// liste des items
			$.each(data.listeItems, function(index, elt) {
				output += '' +
				'		<tr>' +
				'			<td><a href="detailsProduit.action?idProduit=' + elt.idProduit + '">' + elt.designation + '</a></td>' +
				'			<td>' +
				'				<a href="#" id="idMinus' + index + '"><i class="fa fa-minus-circle"></i></a> <i class="fa fa-times"></i>' +
				'				<b id="idTextQuantite' + index + '">' + elt.quantite + '</b>' +
				'				<a href="#" id="idPlus' + index + '"><i class="fa fa-plus-circle"></i></a>' +
				'				<input id="idProduit' + index + '" type="hidden" value="' + elt.idProduit + '">' +
				'				&nbsp;&nbsp;<a href="#" id="idTrash' + index + '"><i class="glyphicon glyphicon-trash"></i></a>' +
				'			</td>' +
				'			<td class="text-right">' +  $.number(elt.prix, 2, ',', ' ') + ' &euro;</td>' +
				'		</tr>';
			});
			
			// cout total
			output += '' +
			'		<tr class="warning">' +
			'			<td colspan="2">Total</td>' +
			'			<td class="text-right">' + $.number(data.coutTotal, 2, ',', ' ') + ' &euro;</td>' +
			'		</tr>' +
			'		<tr class="warning">' +
			'			<td colspan="2">Frais de port</td>' +
			'			<td class="text-right">' + $.number(data.fraisPort, 2, ',', ' ') + ' &euro;</td>' +
			'		</tr>' +
			'	</tbody>' +
			'</table>';
			
		}
		
		$('#idMyContent').html(output);
		
		//Ajout des events click sur les boutons de gestion du panier
		$.each(data.listeItems, function(index, elt) {
		    $("#idMinus" + index).unbind('click').click(function() {
				// on recupere l'id produit, la quantite voulue et go
		    	var idProduit = $("#idProduit" + index).attr("value");
		    	var quantite = 1;
		    	removeItemPanier(elt.idProduit, quantite);
		    });
		    
		    $("#idPlus" + index).unbind('click').click(function() {
				// on recupere l'id produit, la quantite voulue et go
		    	var idProduit = $("#idProduit" + index).attr("value");
		    	var quantite = 1;
		    	addItemMonPanier(elt.idProduit, quantite);
		    });
		    
		    $("#idTrash" + index).unbind('click').click(function() {
				// on recupere l'id produit, la quantite voulue et go
		    	var idProduit = $("#idProduit" + index).attr("value");
		    	var quantite = 999;
		    	removeItemPanier(elt.idProduit, quantite);
		    });
		});

	});
}

//Ajout d'un item produit dans le panier avec une certaine quantite
function addItemMonPanier(idProduit, quantite) {
	$.getJSON('addItemJSON.action?idProduit=' + idProduit + '&quantite=' + quantite, function(data) {
		if(data.updateSuccessful) {
			// maj du panier dans la navbar!
			majMonPanier();
			majNavBarPanier();
		}
	});
	
	addClickEventPanier();
}

// Retrait d'un item produit dans le panier avec une certaine quantite
function removeItemPanier(idProduit, quantite) {
	$.getJSON('removeItemJSON.action?idProduit=' + idProduit + '&quantite=' + quantite, function(data) {
		if(data.updateSuccessful) {
			// maj du panier dans la navbar!
			majMonPanier();
			majNavBarPanier();
		}
	});
}
//
////Ajout des events click sur les boutons de gestion du panier
//function addClickEventMonPanier() {
//    $("[id^='idMinus']").unbind('click').click(function() {
//    	var monId = $(this).attr("id");
//    	
//    	majMonPanier();
//    	
//		// on recupere l'id produit, la quantite voulue et go
//    	var idProduit = $("#" + monId.replace("idMinus", "idProduit")).attr("value");
//    	var quantite = $("#" + monId.replace("idMinus", "idQuantite")).attr("value");
//    	removeItem(idProduit, quantite);
//    });
//}