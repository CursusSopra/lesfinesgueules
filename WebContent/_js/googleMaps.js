
$(function () {
	
		initialize();
	});

function initialize(){

	var mapCanvas = document.getElementById('map-canvas');
	
	// c'est l'emplacement du paysan
	var mapOptions = {
    center: new google.maps.LatLng(44.5403, -78.5463),
    zoom: 8,
    mapTypeId: google.maps.MapTypeId.ROADMAP
  }
  var map = new google.maps.Map(mapCanvas, mapOptions)
	

};

