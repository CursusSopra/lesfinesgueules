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

