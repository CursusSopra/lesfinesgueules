/*
 * @author Benoît
 * 
 * Fichier de fonctions javascript pour l'affichage de la liste des produits
 */

$(function() {
	$.getJSON('getProduitsJSON.action', function(data) {
		
		var output = '';
		
		$.each(data.listeProduits, function(index, elt) {
			// Création de la variable chaîne de sortie et ajout de la photo et la désignation
			output += '<tr><td>' + 
				'<img alt="image" class="img-responsive img-thumbnail" width="200px" src="' + elt.photo + '"/>' + 
				'</td><td>' + 
				'<h4>' + elt.designation + '</h4>';
			
			// Label de disponibilité
			if(elt.disponible) {
				output +='<span class="label label-success">Disponible</span>';
			} else {
				output += '<span class="label label-danger">Non disponible</span>';
			}
			
			// Description (250) et prix
			output += '<p>' + elt.description.substring(0,250) + '...</p><p><div class="input-group">' +
				'<span class="input-group-addon">' + elt.prix + ' &euro;</span>';
			
			// Activation / Désactivation du bouton "Ajouter au panier"
			if(elt.disponible) {
				output += '<button type="button" class="btn btn-default">Ajouter au panier</button>';
			} else {
				output += '<button type="button" class="btn btn-default" disabled="disabled">Ajouter au panier</button>';
			}
			
			// Bouton "Voir les détails"
			output += '<a href="detailsProduit.action?idProduit=' + elt.idProduit + '">' + 
				'<button type="button" class="btn btn-default">Voir les d&eacute;tails</button></a>' +
				'</div></p></td></tr>';
			
		});
		
		// Mise du contenu dans la balise #listeProduits
		$('#listeProduits').html(output);
		
	});
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
});
