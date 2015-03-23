/*
 * Fichier de fonctions javascript pour l'affichage de la liste des produits
 */

$(function() {
	$.getJSON('getProduitsJSON.action', function(data) {
		
		$.each(data.listeProduits, function(index, elt) {
		/*	
			$('#inputHoraires').append('<tr><td><input type="hidden" class="jour" value="'
				+ elt.idJour + '"/><span>' + elt.jour + '</span></td><td>'
				+ selectHoraireDebut + '</td><td>' + selectHoraireFin
				+ '</td><td>' + boutonPlus + boutonMoins + '</td><td>'
				+ checkboxFerme + '</td></tr>');
		*/
			
			// Mise du contenu dans la balise #listeProduits
			$('#listeProduits').html();
			
			// Ajout du code HTML dans la balise #listeProduits
			$("#listeProduits").append(
				'<tr><td><img alt="image" class="img-responsive img-thumbnail" width="200px" src="' +
				elt.photo + '"/></td><td><h4>' + elt.designation + 
				
				'</h4><p>' +
				elt.description.substring(0,250) + '...</p><p><div class="input-group">' +
				'<span class="input-group-addon">' + elt.prix + ' &euro;</span>' +
				'<button type="button" class="btn btn-default">Ajouter au panier</button>' +
				'<a href="detailsProduit.action?idProduit=' + elt.idProduit + '"><button type="button" class="btn btn-default">Voir les d&eacute;tails</button></a>' +
				'</div></p></td></tr>'
			);
			
		});
		
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