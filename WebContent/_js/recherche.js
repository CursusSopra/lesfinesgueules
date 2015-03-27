/*
 * @author Benoît
 */

// Chargement de la page
$(function() {
	
	$.getJSON('getProduitsJSON?idType1=' + $('#idType1').val() + 
			'&idType2=' + $('#idType2').val() + 
			'&idProducteur=' + $('#idProducteur').val() +
			'&displayNumber=' + $('#displayNumber').val() +
			'&pageNumber=' + $('#pageNumber').val(), function(data) {
		
		console.log('pageNumber = ' + $('#pageNumber').val());
		console.log('displayNumber = ' + $('#displayNumber').val());
		console.log('data.numberOfPages = ' + data.numberOfPages);
		
		getPage(data);
	});
	
});

// Fonction JSON
function getPage(data) {
	
	// Mise du contenu dans la balise #listeProduits
	$('#listeProduits').html(displayList(data));
	
	// Ajout évènement 'onclick' sur tous les boutons 'Ajout produit'
	addClickEventPanier();
	
	// Changement du nombre de produits affichés
	$('#displayNumber').change(function () {
		changeDisplay();
	});
	
	// Aller à la page précédente
	$('#pPage').unbind('click').click(function () {
		previousPage();
	});
	
	// Aller à la page suivante
	$('#nPage').unbind('click').click(function () {
		nextPage();
	});
	
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
	
}

// UTILITARY FUNCTIONS ========================================================================== //

function changeDisplay() {
	$.getJSON('getProduitsJSON?idType1=' + $('#idType1').val() + 
			'&idType2=' + $('#idType2').val() + 
			'&idProducteur=' + $('#idProducteur').val() +
			'&displayNumber=' + $('#displayNumber').val() +
			'&pageNumber=' + 1, function(data) {
		
		getPage(data);
	});
}

function nextPage() {
	if($('#pageNumber').val() < $('#numberOfPages').val()) {
		var tempPageNumber = parseInt($('#pageNumber').val()) + 1;
		$.getJSON('getProduitsJSON?idType1=' + $('#idType1').val() + 
				'&idType2=' + $('#idType2').val() + 
				'&idProducteur=' + $('#idProducteur').val() +
				'&displayNumber=' + $('#displayNumber').val() +
				'&pageNumber=' + tempPageNumber, function(data) {
			
			getPage(data);
		});
		
		$('#pageNumber').val(tempPageNumber);
		console.log('pageNumber = ' + tempPageNumber);
	}
}

function previousPage() {
	if(parseInt($('#pageNumber').val()) > 1) {
		var tempPageNumber = parseInt($('#pageNumber').val()) - 1;
		$.getJSON('getProduitsJSON?idType1=' + $('#idType1').val() + 
				'&idType2=' + $('#idType2').val() + 
				'&idProducteur=' + $('#idProducteur').val() +
				'&displayNumber=' + $('#displayNumber').val() +
				'&pageNumber=' + tempPageNumber, function(data) {
			
			getPage(data);
		});
		
		$('#pageNumber').val(tempPageNumber);
		console.log('pageNumber = ' + tempPageNumber);
	}
}

// DISPLAY FUNCTION ============================================================================= //

function displayList(data) {
	var output = '';
	
	// Ecriture des propriété d'affichage dans les champs cachés
	$('#numberOfPages').val(data.numberOfPages);
	$('#pageNumber').val(data.pageNumber);
	console.log($('#pageNumber').val());
	
	// Affichage de la navigation des pages
	$('#nbPages').html(data.pageNumber + '/' + data.numberOfPages);
	
	// Boucle de création de la liste
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
//		if(elt.photo.length == 0){
//			elt.photo = 'images/default.jpg';
//		}
		
		// Création de la variable chaîne de sortie
		output +=
			'<div class="col-md-3">' + 
				'<a href="detailsProduit.action?idProduit=' + elt.idProduit + '">' +
					'<img alt="image" class="img-responsive img-thumbnail" id="idImage' + index + '" src="/lesfinesgueules/content/images/' + elt.photo + '"/>' + 
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
	
	return output;
}
