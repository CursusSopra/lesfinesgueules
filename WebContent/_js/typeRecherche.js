/*
 * Fichier de fonctions javascript pour l'affichage de la deuxième list déroulante
 * 
 */

$(function () {
	$('#idType1').change(function() {
		loadType2();
	});
});

function loadType2() {	
	var idType1 = $('#idType1').val();
	console.log(idType1);
	$.getJSON('getJSONType2?idType1=' + idType1)
	.success(function (data) {
        var szOption = '<option value="-1">Choisissez...</option>';
        $.each(data.listType2, function (index, elt) {
            szOption += '<option value="' + elt.idType2 + '">' + elt.libelle2 + '</option>';
        });

        $('#idListType2').html(szOption);
    })
    .fail(function () {
    });
}