package it.pennino.uni.piazzaAffari.webSocket;

import java.io.IOException;
import java.util.ArrayList;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import it.pennino.uni.piazzaAffari.chat.UtenteConnesso;

@ServerEndpoint( value = "/chat")
public class WebSocketServer {
	private static ArrayList<UtenteConnesso> utentiConnessi = new ArrayList<UtenteConnesso>();
	@OnOpen
    public void onOpen(Session session) {
        // Metodo eseguito all'apertura della connessione
		//System.out.println("session.getId() = "+session.getId());
		
		UtenteConnesso ut = new UtenteConnesso(session, null);
		utentiConnessi.add(ut);
	}
	
	 @OnMessage
	 public String onMessage(String message, Session session) {
		 // Metodo eseguito alla ricezione di un messaggio
	     // La stringa 'message' rappresenta il messaggio
	 
		 if(message.contains("!USER!:")){
			 //System.out.println("Imposto username");
				
			 UtenteConnesso ut = new UtenteConnesso(session, null);
			 utentiConnessi.get(utentiConnessi.indexOf(ut)).setUsername(message.substring(7));;
			 
			 
			 sendBroadcast(null, "ADD_USR&"+utentiConnessi.get(utentiConnessi.indexOf(ut)).getSession().getId()+"&"+utentiConnessi.get(utentiConnessi.indexOf(ut)).getUsername());
			 sendDirect(null,ut.getSession().getId(), null, "SET_ID&"+ut.getSession().getId());
			 sendBroadcast(utentiConnessi.get(utentiConnessi.indexOf(ut)).getUsername(), utentiConnessi.get(utentiConnessi.indexOf(ut)).getUsername()+" si è aggiunto alla chat.");
			 return null;
		 }else{
			 return "Server received [" + message + "]";
		 }
	 }
	 
	 @OnClose
	 public void onClose(Session session) {
		 // Metodo eseguito alla chiusura della connessione
		 UtenteConnesso ut = new UtenteConnesso(session, null);
		 utentiConnessi.remove(ut);
	 }
	 
	 @OnError
	 public void onError(Throwable exception, Session session) {
		// Metodo eseguito in caso di errore
		//System.out.println("exception"+exception);
		System.out.println("Session"+session);
		System.out.println("Exception"+exception);
	}
	 
	 public void sendBroadcast(String nome ,String msg){
		 for(int i=0; i<utentiConnessi.size(); i++){
			 try {
				//System.out.println("Invio il msg alla sessione : "+utentiConnessi.get(i).getSession().getId());
				if(nome!=null)
					utentiConnessi.get(i).getSession().getBasicRemote().sendText(nome+" : "+msg);
				else utentiConnessi.get(i).getSession().getBasicRemote().sendText(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		 }
		 
	 }
	 
	 
	 public void sendDirect(String idMittente,String idDestinatario ,String nome ,String msg){
		 for(int i=0; i<utentiConnessi.size(); i++){
			 if(utentiConnessi.get(i).getSession().getId().equals(idDestinatario) || utentiConnessi.get(i).getSession().getId().equals(idMittente)){
				 try {
					//System.out.println("Invio il msg alla sessione : "+utentiConnessi.get(i).getSession().getId());
					if(nome!=null)
						utentiConnessi.get(i).getSession().getBasicRemote().sendText(nome+" : "+msg);
					else utentiConnessi.get(i).getSession().getBasicRemote().sendText(msg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		 }
		 
	 }

	public static ArrayList<UtenteConnesso> getUtentiConnessi() {
		return utentiConnessi;
	}

	
}
