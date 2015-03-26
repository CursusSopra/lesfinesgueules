
$(function () {
	
		initialize();
	});

function initialize(){

	var mapCanvas = document.getElementById('map-canvas');
	
	// c'est l'emplacement du paysan
	var mapOptions = {
    center: new google.maps.LatLng($('#latitude').attr('value'), $(longitude).attr('value')),
    zoom: 12,
    mapTypeId: google.maps.MapTypeId.ROADMAP
  }
	
	var map = new google.maps.Map(mapCanvas, mapOptions);
	
	var marqueur = new google.maps.Marker({

        position: new google.maps.LatLng($('#latitude').attr('value'), $(longitude).attr('value')),

        map: map

    });
	

};

