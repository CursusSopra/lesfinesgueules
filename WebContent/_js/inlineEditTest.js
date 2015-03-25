/*
 * @author Julien J
 * 
 * Fichier de fonctions javascript pour la modification de la base de donnée en mode inline Editing
 */

//turn to inline mode
$.fn.editable.defaults.mode = 'popup';

//Zone de test
$(document).ready(function() {
	$('.list-group-item a').editable({
		
			
	    params: function(params) {
	        //originally params contain pk, name and value
	        params.parametreBidon = 6;
	        return params;
	    }
	});
        
});/**
 * 
 */