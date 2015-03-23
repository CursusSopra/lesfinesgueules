/*
 * Fichier de fonctions javascript pour l'affichage de la deuxième list déroulante
 * 
 */

$(function() {
	
	$.getJSON("ListeProduits.action", function(data) {
		$("#listeProduits").html(data);
	});
});

