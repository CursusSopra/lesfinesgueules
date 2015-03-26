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
			output +=
				'<div class="col-md-3">' + 
					'<a href="detailsProduit.action?idProduit=' + elt.idProduit + '">' +
						'<img alt="image" class="img-responsive img-thumbnail" id="idImage' + index + '" src="' + elt.photo + '"/>' + 
					'</a>'+
					'<h4>' + elt.designation + ' <span class="badge">' + elt.listeCommentaires.length + ' avis</span></h4>';
			
			// Label de disponibilité
			if(elt.disponible) {
				output += '<p><span class="label label-success">Disponible</span></p>';
			} else {
				output += '<p><span class="label label-danger">Non disponible</span></p>';
			}
			
			// Description (250) et prix
			output +=
				'<p>' + elt.description.substring(0,250) + '...</p>' + 
				'<div class="input-group">'+
					'<span class="input-group-addon">' + $.number(elt.prix, 2, ',', ' ') + ' &euro;</span>';
			
			if(elt.disponible) {
				output += '<input type="number" class="form-control" id="idQuantite1" value="1" min="1" step="1"/>';
			} else {
				output += '<input type="number" class="form-control" id="idQuantite1" value="" disabled="disabled"/>';
			}
			output +=
				'</div>';	// input-group
					
			output += '<div class="btn-group btn-group-justified" role="group">';
			
			// Bouton "Voir les détails"
			output +=	'<div class="btn-group" role="group">'+
							'<a href="detailsProduit.action?idProduit=' + elt.idProduit + '">' + 
								'<button type="button" class="btn btn-default"><small>D&eacute;tails produit</small></button>'+
							'</a>'+
						'</div>';	// btn-group
			
			// Activation / Désactivation du bouton "Ajouter au panier"
			if(elt.disponible) {
				output +=
					'<div class="btn-group" role="group">' + 
						'<button type="button" class="btn btn-default" id="idButton' + index + '">'+
							'<small>Ajouter au panier</small>'+
						'</button>' + 
						'<input type="hidden" id="idQuantite' + index + '" value="1"/>' +
						'<input type="hidden" id="idProduit' + index + '" value="' + elt.idProduit + '"/>' +
					'</div>';	// btn-group
			} else {
				output +=
					'<div class="btn-group" role="group">' + 
						'<button type="button" class="btn btn-default" disabled="disabled">'+
							'<small>Ajouter au panier</small>'+
						'</button>' + 
					'</div>';	// btn-group
			}
			
			
			
			// Fermeture des 'div'
			output +='</div>'+
				'</div>'+	// btn-group btn-group-justified
			'</div>';	// col-md-3
			
		});
		
		// Mise du contenu dans la balise #listeProduits
		$('#listeProduits').html(output);
		
		// Ajout évènement 'onclick' sur tous les boutons 'Ajout produit'
		addClickEventPanier();
	});
});

