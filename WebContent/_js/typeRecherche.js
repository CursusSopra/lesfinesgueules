/*
 * Fichier de fonctions javascript pour l'affichage de la deuxième list déroulante
 * 
 */

$(function() {
	
	$.get("ListeProduits.action", function(data) {
		$("#listeProduits").html(data);
	});
});


$(function() {
	$.get("getSVG.action?axis=child", function(data) {
		$("#graphSvg").html(data);
	});
	$("#idAxis").change(function() {
		var axis = $("#idAxis").val();
		$.get("getSVG.action?axis=" + axis, function(data) {
			$("#graphSvg").html(data);
		});
	});
});