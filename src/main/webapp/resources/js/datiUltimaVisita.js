//Aggiorno a che punto si è fermato il video 
function aggiornaDatiVideo(){
	sessionStorage.lastTimeVideo = Math.floor(video.currentTime);
}

function  datiUtente(){
	recuperaPosizione();
	//Imposta dati ultima visita
	if(sessionStorage.lastTimeVideo != undefined)
		video.currentTime = sessionStorage.lastTimeVideo;		
}

function salvaDatiLogin(username,password){
	localStorage.username = username;
	localStorage.password = password;
}

function impostaDatiLogin(username,password){
	if(localStorage.username != null){
		document.getElementById("email").value=localStorage.username
	}
	if(localStorage.password != null){
		document.getElementById("password").value=localStorage.password
	}
}