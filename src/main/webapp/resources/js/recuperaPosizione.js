var googleKeyApi = "AIzaSyC5nXK8QyOWuhQFshzOmNal3ZiAVGJwz9w";


function recuperaPosizione(){
	var opzioni = {
		enableHighAccuracy: true,
		timeout: 5000,
		maximumAge: 0
	};
	
	if(navigator.geolocation){
		navigator.geolocation.getCurrentPosition(recuperaPosizioneSuccess,recuperaPosizioneError,opzioni);
		
	}else{
		alert("Il browser non supporta l'oggetto navigator");
	}
}

function recuperaPosizioneSuccess(pos){
	console.log(pos);
	var coordinate = pos.coords;
	
	console.log("lat : "+coordinate.latitude);
	console.log("lon : "+coordinate.longitude);
	console.log("Precisione  : "+coordinate.accuracy+" metri");
	
	$.ajax({
	    url: 'https://maps.googleapis.com/maps/api/geocode/json?latlng='+coordinate.latitude+','+coordinate.longitude+'&key='+googleKeyApi, 
	    type: 'GET',
	    success: function(res) {
	        console.log(res);
	    	var comune_sel = res.results[0].address_components[2].long_name;
	        var provincia = res.results[0].address_components[4].long_name;
	        var regione = res.results[0].address_components[5].long_name;
	        var nazione = res.results[0].address_components[6].long_name;
	        
	        console.log("comune"+comune_sel);
	        console.log("provincia"+provincia);
	        console.log("regione"+regione);
	        console.log("nazione"+nazione);
	       
	        try {
	        	console.log("Imposto il comune "+comune_sel);
	        	comune.selectedIndex = comune.children.namedItem(comune_sel).index;
	        }catch(err) {
	            console.log(err);
	        }
	    }
	});
}






function recuperaPosizioneError(err) {
	console.warn("ERRORE("+err.code+"): "+err.message);
};