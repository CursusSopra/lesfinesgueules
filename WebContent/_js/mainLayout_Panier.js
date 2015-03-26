/*
 * @author Julien Caillon
 * 
 * Fonctions du panier
 */

// on page load
$(function() {
	
	majNavBarPanier();
	
	// formatte les nombres dans un span avec class="myprice"
	$('span.price').number(true, 2, ',', ' ');

	addClickEventPanier();
});

// Met a jour le panier dans la navbar
function majNavBarPanier() {

	$.getJSON('dataPanierJSON.action', function(data) {
		
		var output = '' +
		'<b class="panier-title"><i class="fa fa-cart-arrow-down"></i> Mon panier</b>';
		
		// maj du badge "nombre d'items dans le panier"
		if (data.nbItems == 0) {
			var strbadge = '';
			
			// panier vide
			var output = '' +
			'	Oups! Mon panier est vide! Il est temps que je commence à acheter <i class="fa fa-heart"></i>' +
			'';
			
		} else {

			var strbadge = data.nbItems;
			
			// maj du popup panier
			var output = '' +
			'<table class="table table-striped table-hover table-condensed tablepanier">' +
			'	<tbody>' +
			'		<tr class="info">' +
			'			<th><i class="fa fa-level-down"></i></th>' +
			'			<th>Produit</th>' +
			'			<th class="text-right">Prix</th>' +
			'		</tr>';
			
			
			// liste des items
			$.each(data.listeItems, function(index, elt) {
				output += '' +
				'		<tr>' +
				'			<td><i class="fa fa-times"></i> <b>' + elt.quantite + '</b></td>' +
				'			<td><a href="detailsProduit.action?idProduit=' + elt.idProduit + '">' + elt.designation + '</a></td>' +
				'			<td class="text-right">' +  $.number(elt.prix, 2, ',', ' ') + ' &euro;</td>' +
				'		</tr>';
			});
			
			
			// cout total & frais de port
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
			
			//lien vers panier
			output += '' +
			'<a href="mon-panier" class="btn btn-success input-block-level form-control">' +
			'Aller au panier' +
			'</a>';
				
		}
		
		$('#idBadgePanier').html(strbadge);
		$('#idMonPanierContent').html(output);

	});
}

// Ajout d'un item produit dans le panier avec une certaine quantite
function addItem(idProduit, quantite) {
	$.getJSON('addItemJSON.action?idProduit=' + idProduit + '&quantite=' + quantite, function(data) {
		if(data.updateSuccessful) {
			// maj du panier dans la navbar!
			majNavBarPanier();
			
			// on affiche le popup panier!
			sleep(800);
			$('#idMonPanier').dropdown('toggle');
		}
	});
	
	addClickEventPanier();
}

// Ajout des events click sur les boutons de gestion du panier
function addClickEventPanier() {
    $("button[id^='idButton']").unbind('click').click(function() {
    	var monId = $(this).attr("id");
    	
    	// animation de l'image vers le panier
		var cart = $('#idPanierIcon');
	    var imgtofly = $("#" + monId.replace("idButton", "idImage"));
		if (imgtofly) {
			var imgclone = imgtofly.clone()
			.offset({ top:imgtofly.offset().top, left:imgtofly.offset().left })
			.css({'opacity':'0.7', 'position':'absolute', 'height':'150px', 'width':'150px', 'z-index':'1000'})
			.appendTo($('body'))
			.animate({
				'top':cart.offset().top + 10,
				'left':cart.offset().left + 30,
				'width':55,
				'height':55
				}, 800, 'easeInElastic');
			imgclone.animate({'width':0, 'height':0}, function(){ $(this).detach() });
		}

		// on recupere l'id produit, la quantite voulue et go
    	var idProduit = $("#" + monId.replace("idButton", "idProduit")).attr("value");
    	var quantite = $("#" + monId.replace("idButton", "idQuantite")).attr("value");
    	addItem(idProduit, quantite);
    });
}

function sleep(milliseconds) {
	  var start = new Date().getTime();
	  for (var i = 0; i < 1e7; i++) {
		    if ((new Date().getTime() - start) > milliseconds) {
			break;
		}
	}
}
