/*
 * @author Julien Caillon
 * 
 * Fonctions de Màj du panier
 */

function majNavBarPanier() {

	$.getJSON('dataPanierJSON.action', function(data) {
		
		// maj du badge "nombre d'items dans le panier"
		if (data.nbItems == 0) {
			var strbadge = '';
			
			// panier vide
			var output = '' +
			'<b class="panier-title"><i class="fa fa-cart-arrow-down"></i> Mon panier</b>' +
			'	Oups! Mon panier est vide! Il est temps que je commence à acheter <i class="fa fa-heart"></i>' +
			'';
			
		} else {

			var strbadge = data.nbItems;
			
			// maj du popup panier
			var output = '' +
			'<b class="panier-title"><i class="fa fa-cart-arrow-down"></i> Mon panier</b>' +
			'<table class="table table-striped table-bordered table-condensed">' +
			'	<tbody>' +
			'		<tr>' +
			'			<th class="bg-primary panier-produit">Produit</th>' +
			'			<th class="bg-primary panier-qte">Qte</th>' +
			'			<th class="bg-primary panier-prix">Prix</th>' +
			'		</tr>';
			
			
			// liste des items
			$.each(data.listeItems, function(index, elt) {
				output += '' +
				'		<tr>' +
				'			<th>' + elt.designation + '</th>' +
				'			<th>' + elt.quantite + '</th>' +
				'			<th>' + elt.prix + ' &euro;</th>' +
				'		</tr>';
			});
			
			
			// cout total
			output += '' +
			'		<tr class="warning">' +
			'			<td colspan="2">Total</td>' +
			'			<td>' + $.number(data.coutTotal, 2, ',', ' ') + ' &euro;</td>' +
			'		</tr>' +
			'	</tbody>' +
			'</table>';
			
			// lien vers panier
			output += '' +
			'<a href="" class="btn btn-success input-block-level form-control">' +
			'	Aller au panier' +
			'</a>';
			
		}
		
		$('#idBadgePanier').html(strbadge);
		$('#idMonPanier').html(output);

	});
}

$(function() {
	
	majNavBarPanier();
	
	// formatte les nombres dans un span avec class="myprice"
	$('span.price').number(true, 2, ',', ' ');

});
//		var output = '';
//		
//		$.each(data.listeProduits, function(index, elt) {
//			// Création de la variable chaîne de sortie et ajout de la photo et la désignation
//			output += '<div class="col-md-3">' + 
//				'<img alt="image" class="img-responsive img-thumbnail"  src="' + elt.photo + '"/>' + 
//				'<h4>' + elt.designation + '</h4>';
//			
//			// Label de disponibilité
//			if(elt.disponible) {
//				output += '<p><span class="label label-success">Disponible</span></p>';
//			} else {
//				output += '<p><span class="label label-danger">Non disponible</span></p>';
//			}
//			
//			// Description (250) et prix
//			output += '<p>' + elt.description.substring(0,250) + '...</p>' + 
//				'<div class="input-group col-md-12"><span class="input-group-addon">' + elt.prix + ' &euro;</span></div>' + 
//				'<div class="btn-group btn-group-justified role="group"">';
//			
//			// Activation / Désactivation du bouton "Ajouter au panier"
//			if(elt.disponible) {
//				output += '<div class="btn-group" role="group">' + 
//					'<button type="button" class="btn btn-default"><small>Ajouter au panier</small></button>' + 
//					'</div>';
//			} else {
//				output += '<div class="btn-group" role="group">' + 
//					'<button type="button" class="btn btn-default" disabled="disabled"><small>Ajouter au panier</small></span></button>' + 
//					'</div>';
//			}
//			
//			// Bouton "Voir les détails"
//			output += '<div class="btn-group" role="group"><a href="detailsProduit.action?idProduit=' + elt.idProduit + '">' + 
//				'<button type="button" class="btn btn-default"><small>D&eacute;tails produit</small></button></div></a></div>' +
//				'</div>';
//			
//		});
//		
//		// Mise du contenu dans la balise #listeProduits
//		$('#listeProduits').html(output);

/*
	$.get("ListeProduits.action", function(data) {
		$("#listeProduits").html(data);
	});
	
	$("#idType1").change(function() {
		var idType1 = $("idType1").val();
		$.get("ListeProduits.action?idType1=" + idType1, function(data) {
			$("#listeProduits").html(data);
		});
	});
	
	$("#idType2").change(function() {
		var idType2 = $("idType2").val();
		$.get("ListeProduits.action?idType2=" + idType2, function(data) {
			$("#listeProduits").html(data);
		});
	});
	
	$("#idProducteur").change(function() {
		var idProducteur = $("idProducteur").val();
		$.get("ListeProduits.action?idProducteur=" + idProducteur, function(data) {
			$("#listeProduits").html(data);
		});
	});
*/