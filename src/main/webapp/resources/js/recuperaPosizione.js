
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
}

function recuperaPosizioneError(err) {
	console.warn("ERRORE("+err.code+"): "+err.message);
};