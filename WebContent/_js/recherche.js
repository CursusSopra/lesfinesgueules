/*
 * @author Benoît
 * 
 * Fichier de fonctions javascript pour l'affichage de la liste des produits
 */

$(function() {
	$.getJSON('getProduitsJSON?idType1=' + $('#idType1').val() + '&idType2=' + $('#idType2').val() + '&idProducteur=' + $('#idProducteur').val(), function(data) {
		
		var output = '';
		
		$.each(data.listeProduits, function(index, elt) {
			// Ajout de l'image par défaut si non présente
			if(elt.photo.length == 0){
				elt.photo = 'images/default.jpg';
			}
			
			// Création de la variable chaîne de sortie et ajout de la photo et la désignation
			output += '<div class="col-md-3">' + 
				'<img alt="image" class="img-responsive img-thumbnail" id="idImage' + index + '" src="' + elt.photo + '"/>' + 
				'<h4>' + elt.designation + ' <span class="badge">' + elt.listeCommentaires.length + ' avis</span></h4>';
			
			// Label de disponibilité
			if(elt.disponible) {
				output += '<p><span class="label label-success">Disponible</span></p>';
			} else {
				output += '<p><span class="label label-danger">Non disponible</span></p>';
			}
			
			// Description (250) et prix
			output += '<p>' + elt.description.substring(0,250) + '...</p>' + 
				'<div class="input-group col-md-12"><span class="input-group-addon">' + $.number(elt.prix, 2, ',', ' ') + ' &euro;</span></div>' + 
				'<div class="btn-group btn-group-justified role="group"">';
			
			// Activation / Désactivation du bouton "Ajouter au panier"
			if(elt.disponible) {
				output += '<div class="btn-group" role="group">' + 
					'<button type="button" class="btn btn-default" id="idButton' + index + '"><small>Ajouter au panier</small></button>' + 
					'<input type="hidden" id="idQuantite' + index + '" value="1"/>' +
					'<input type="hidden" id="idProduit' + index + '" value="' + elt.idProduit + '"/>' +
					'</div>';
			} else {
				output += '<div class="btn-group" role="group">' + 
					'<button type="button" class="btn btn-default" disabled="disabled"><small>Ajouter au panier</small></span></button>' + 
					'</div>';
			}
			
			// Bouton "Voir les détails"
			output += '<div class="btn-group" role="group"><a href="detailsProduit.action?idProduit=' + elt.idProduit + '">' + 
				'<button type="button" class="btn btn-default"><small>D&eacute;tails produit</small></button></div></a></div>' +
				'</div>';
			
		});
		
		// Mise du contenu dans la balise #listeProduits
		$('#listeProduits').html(output);
		
	});
});

