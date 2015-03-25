/*
 * @author Julien J
 * 
 * Fichier de fonctions javascript pour la modification de la base de donnée en mode inline Editing
 */

//turn to inline mode
$.fn.editable.defaults.mode = 'inline';

//Zone de test
$(document).ready(function() {
	
	//Fonction trigger sur le changement de valeur du champ type1
	$('#type1').change(function() {
		loadType2();
	});
	
	//Fonction d'inlineEditing
	$('.list-group-item a').editable({
		
		clear : false,
	    params: function(params) {
	        //originally params contain pk, name and value
	        params.a = 1;
	        return params;
	        }

	});
        
});

function loadType2() {	
	var idType1 = $('#type1').val();
	var action = $('#hiddenData').attr("data-lienUrl");
	console.log(idType1);
	$.getJSON('getJSONType2?idType1=' + idType1)
	.success(function (data) {
        var szOption = '';
        $.each(data.listType2, function (index, elt) {
        	szOption += '<li class="list-group-item">';
            szOption += '<a href="#" id="';
            szOption += elt.libelle2;
            szOption += '" data-type="text" data-title="Enter une nouvelle valeur de libelle1" data-pk="';
            szOption += idType1+ '" data-url="' + action + '" >';
            szOption += elt.libelle2 ;
            szOption += '</a>';
            szOption += '</li>';
        });

        $('#retourJSON').html(szOption);
        $('.list-group-item a').editable({
    		
    		clear : false,
    	    params: function(params) {
    	        //originally params contain pk, name and value
    	        params.a = 1;
    	        return params;
    	        }

    	});
    })
    .fail(function () {
    });
}
/**
 * 
 */