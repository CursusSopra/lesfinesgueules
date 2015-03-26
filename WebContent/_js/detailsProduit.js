


$(function() {
	// Default comments display : comments hidden
	$('#listeComments').hide();
	
	// Show/Hide comments
	$('#caretComments').click(function () {
		$('#listeComments').slideToggle();
		
		// Changing chevron orientation
		if($('#chevronListe').attr( "class") == "glyphicon glyphicon-chevron-up" ) {
			$('#chevronListe').attr( "class", "glyphicon glyphicon-chevron-down" );
		} else {
			$('#chevronListe').attr( "class", "glyphicon glyphicon-chevron-up" );
		}
	});
	
	// Increment the number of product (max 99)
	$('#buttonPlus').click(function () {
		if($('#idQuantite1').attr('value') < 99) {
			$('#idQuantite1').attr('value', parseInt($('#idQuantite1').attr('value')) + 1);
		} else {
			alert('Nombre de produit maximum atteint.')
		}
	});
	
	// Decrement the number of product (min 1)
	$('#buttonMoins').click(function () {
		if($('#idQuantite1').attr('value') > 1) {
			$('#idQuantite1').attr('value', parseInt($('#idQuantite1').attr('value')) - 1);
		} else {
			alert('Vous devez avoir au moins un produit.')
		}
	});
});