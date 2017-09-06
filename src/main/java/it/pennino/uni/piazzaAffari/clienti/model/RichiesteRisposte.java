package it.pennino.uni.piazzaAffari.clienti.model;

import it.pennino.uni.piazzaAffari.user.model.User;

public class RichiesteRisposte implements java.io.Serializable {

	private RichiesteRisposteId id;
	private Richiesta richieste;
	private User user;
	private String testo;

	public RichiesteRisposte() {
	}

	public RichiesteRisposte(RichiesteRisposteId id, Richiesta richieste, User user, String testo) {
		this.id = id;
		this.richieste = richieste;
		this.user = user;
		this.testo = testo;
	}

	public RichiesteRisposteId getId() {
		return this.id;
	}

	public void setId(RichiesteRisposteId id) {
		this.id = id;
	}

	public Richiesta getRichieste() {
		return this.richieste;
	}

	public void setRichieste(Richiesta richieste) {
		this.richieste = richieste;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTesto() {
		return this.testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

}
