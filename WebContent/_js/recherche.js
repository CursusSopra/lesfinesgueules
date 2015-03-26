/*
 * @author Benoît
 * 
 * Fichier de fonctions javascript pour l'affichage de la liste des produits
 */

$(function() {
	$.getJSON('getProduitsJSON?idType1=' + $('#idType1').val() + '&idType2=' + $('#idType2').val() + '&idProducteur=' + $('#idProducteur').val(), function(data) {
				
		var output = '';
		
		$.each(data.listeProduits, function(index, elt) {
			
			// Disponible / Non disponible
			if(elt.disponible) {
				var badgeDisponible =	'<p><span class="label label-success">Disponible</span></p>';
				var enableAddCart	=	'<input type="hidden" id="idProduit' + index + '" value="' + elt.idProduit + '"/>' +
										'<div class="input-group">' +
											'<div class="input-group-btn">' +
												'<button type="button" class="btn btn-default" id="idButton' + index + '">' +
													'<small>Ajouter au panier</small>' +
												'</button>' +
												'<button type="button" class="btn btn-default" id="buttonPlus' + index + '">' +
													'<span class="glyphicon glyphicon-plus"></span>' +
												'</button>' +
											'</div>' +
											'<input type="text" class="form-control" id="idQuantite' + index + '" value="1" readonly/>' +
											'<span class="input-group-btn">' +
												'<button type="button" class="btn btn-default" id="buttonMoins' + index + '">' +
													'<span class="glyphicon glyphicon-minus"></span>' +
												'</button>' +
											'</span>' +
										'</div>';	// input-group
			} else {
				var badgeDisponible = 	'<p><span class="label label-danger">Non disponible</span></p>';
				var enableAddCart	=	'<input type="hidden" id="idProduit' + index + '" value="' + elt.idProduit + '"/>' +
										'<div class="input-group">' +
											'<div class="input-group-btn">' +
												'<button type="button" class="btn btn-default" id="idButton' + index + '" disabled="disabled">' +
													'<small>Ajouter au panier</small>' +
												'</button>' +
												'<button type="button" class="btn btn-default" id="buttonPlus' + index + '" disabled="disabled">' +
													'<span class="glyphicon glyphicon-plus"></span>' +
												'</button>' +
											'</div>' +
											'<input type="text" class="form-control" id="idQuantite' + index + '" value="1" readonly/>' +
											'<span class="input-group-btn">' +
												'<button type="button" class="btn btn-default" id="buttonMoins' + index + '" disabled="disabled">' +
													'<span class="glyphicon glyphicon-minus"></span>' +
												'</button>' +
											'</span>' +
										'</div>';	// input-group
			}
			
			var prixDetails =	'<div class="input-group">' +
									'<span class="input-group-btn">' +
										'<a  class="btn btn-default" href="detailsProduit.action?idProduit=' + elt.idProduit + '">' + // Bouton "Détails"
											'<small>D&eacute;tails produit</small>' +
										'</a>' +
									'</span>' +
									'<input type="text" class="form-control" value="' + $.number(elt.prix, 2, ',', ' ') + ' &euro;" readonly>' +
								'</div>';
			
			// Ajout de l'image par défaut si non présente
			if(elt.photo.length == 0){
				elt.photo = 'images/default.jpg';
			}
			
			// Création de la variable chaîne de sortie
			output +=
				'<div class="col-md-3">' + 
					'<a href="detailsProduit.action?idProduit=' + elt.idProduit + '">' +
						'<img alt="image" class="img-responsive img-thumbnail" id="idImage' + index + '" src="' + elt.photo + '"/>' + 
					'</a>'+
					
					'<h4>' + elt.designation +
						' <span class="badge">' + elt.listeCommentaires.length + ' avis</span>' + 
					'</h4>' +
					
					badgeDisponible + // Label de disponibilité
					
					prixDetails +	// Affichage du prix et du bouton détails
					
					'<p>' + elt.description.substring(0,250) + '...</p>' + 
					
					enableAddCart + // Bouton ajout au panier
					
				'</div>' +	// col-md-3
			'</div>';	// ???
				
		});
		
		// Mise du contenu dans la balise #listeProduits
		$('#listeProduits').html(output);
		
		// Ajout évènement 'onclick' sur tous les boutons 'Ajout produit'
		addClickEventPanier();
		
		// Increment the number of product (max 99)
		$("button[id^='buttonPlus']").click(function () {
			// Récupération de l'ID complet
			var thisId = $(this).attr("id");
			
			var thisQuantite = $("#" + thisId.replace("buttonPlus","idQuantite"));
			
			if(thisQuantite.attr('value') < 99) {
				thisQuantite.attr('value', parseInt(thisQuantite.attr('value')) + 1);
			} else {
				alert('Nombre de produit maximum atteint.')
			}
		});
		
		// Decrement the number of product (min 1)
		$("button[id^='buttonMoins']").click(function () {
			// Récupération de l'ID complet
			var thisId = $(this).attr("id");
			
			var thisQuantite = $("#" + thisId.replace("buttonMoins","idQuantite"));
			
			if(thisQuantite.attr('value') > 1) {
				thisQuantite.attr('value', parseInt(thisQuantite.attr('value')) - 1);
			} else {
				alert('Vous devez avoir au moins un produit.')
			}
		});
	});
	
});

