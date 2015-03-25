

// Show/Hide comments
$(function() {
	$('#listeComments').hide();
	
	$('#caretComments').click(function () {
		$('#listeComments').slideToggle();
	});
});