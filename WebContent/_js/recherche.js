/*
 * Fichier de fonctions javascript pour l'affichage de la liste des produits
 */

$(function() {
		$.getJSON('/lesfinesgueules/getProduitsJSON.action', function(data) {
			$("#listeProduits").html(data);
			$.each(data.listeDesJours, function(index, elt) {
				$('#inputHoraires').append('<tr><td><input type="hidden" class="jour" value="'
						+ elt.idJour + '"/><span>' + elt.jour + '</span></td><td>'
						+ selectHoraireDebut + '</td><td>' + selectHoraireFin
						+ '</td><td>' + boutonPlus + boutonMoins + '</td><td>'
						+ checkboxFerme + '</td></tr>');
			});
			
			$('.boutonPlusHoraires').click(plusHoraires);
			$('.boutonMoinsHoraires').click(moinsHoraires);
			$('.boutonFerme').click(fermeAction);
			$('#selectDebutTousLesJours').children().change(changeTousLesHorairesDebut);
			$('#selectFinTousLesJours').children().change(changeTousLesHorairesFin);
		});
	
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
	
});