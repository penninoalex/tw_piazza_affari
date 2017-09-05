package it.pennino.uni.piazzaAffari.chat;

import javax.websocket.Session;

public class UtenteConnesso {
	private Session session;
	private String username;
	
	
	public UtenteConnesso(Session session, String username) {
		super();
		this.session = session;
		this.username = username;
	}
	
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.getSession().getId().equals(((UtenteConnesso)obj).getSession().getId());
	}
	
}
