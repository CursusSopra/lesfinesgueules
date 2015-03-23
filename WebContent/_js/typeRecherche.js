/*
 * Fichier de fonctions javascript pour l'affichage de la deuxième list déroulante
 * 
 */

$(function() {
	i = form.Rubrique.selectedIndex;
	if (i == -1) { 
		return;
	}
	
	$.get("ListeProduits.action", function(data) {
		$("#listeProduits").html(data);
	});
});

