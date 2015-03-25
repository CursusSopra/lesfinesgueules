
/*
 * @author Julien J
 * 
 * Fichier de fonctions javascript pour la modification de la base de donnée en mode inline Editing
 */

//turn to inline mode
$.fn.editable.defaults.mode = 'inline';

$(document).ready(function() {
	$('.list-group-item a').editable();
});

//function modifyType1() {
//	$.getJSON('getProduitsJSON.action', function(data) {
//		
//		var libelle1 = $('#libelle1').val();
//		console.log(libelle1);
//		$.getJSON('getJSONType2?libelle1=' + libelle1).success(
//				function(data) {
//					var szOption = '<option value="">Choisissez...</option>';
//					$.each(data.listType2, function(index, elt) {
//						szOption += '<option value="' + elt.idType2 + '">'
//								+ elt.libelle2 + '</option>';
//					});
//
//					$('#idListType2').html(szOption);
//				}).fail(function() {
//		});
//	});
//}