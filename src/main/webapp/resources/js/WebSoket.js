var chat = document.getElementById("textareaChat");

function connectWs(username){
	chat = document.getElementById("textareaChat");
	var socket = new WebSocket("ws:/localhost:8080/PiazzaAffari/chat");

	socket.addEventListener("open",function(event){
		//console.log("Connesso "+username)
	
		socket.send("!USER!:"+username);
	});
	
	
	
	socket.addEventListener("message",function(event){
		
		var msg = event.data;
		
		//Verifico il tipo di msg ricevuto:
		var txt = msg.split("&");
		
		if(txt[0] === "ADD_USR"){
			//Aggiorno la lista degli utenti collegati
			addUserLista(txt[1],txt[2]);
		}else if(txt[0] === "SET_ID"){
			//Aggiorno la lista degli utenti collegati
			setIdMittente(txt[1])
			//addUserLista(txt[1],txt[2]);
		}else{
			//Visualizzo il msg
			//chat.value = chat.value+" \n "+event.data;
			chat.innerHTML += "<p>"+msg.trim()+"</p>";
		}
		
		//console.log("Hai ricevuto : "+event.data)
	});

	
	socket.addEventListener("error",function(event){
		console.log("Errore");
	})
	
	
	socket.addEventListener("close",function(event){
		console.log("Connessione chiusa");
	});
	

}

function setIdMittente(id){
	idMittente.value=id;
	
}

function addUserLista(id,username){
	var option = document.createElement("option");
	option.text = username;
	option.value = id;
	
	utentiCollegatiList.add(option);
}

