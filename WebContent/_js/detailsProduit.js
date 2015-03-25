

// Show/Hide comments
$(function() {
	$('#listeComments').hide();
	
	$('#caretComments').click(function () {
		$('#listeComments').slideToggle();
		
		if($('#chevronListe').attr( "class") == "glyphicon glyphicon-chevron-up" ) {
			$('#chevronListe').attr( "class", "glyphicon glyphicon-chevron-down" );
		} else {
			$('#chevronListe').attr( "class", "glyphicon glyphicon-chevron-up" );
		}
		
	});
});