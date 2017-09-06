package it.pennino.uni.piazzaAffari.clienti.model;

import java.util.HashSet;
import java.util.Set;
import it.pennino.uni.piazzaAffari.user.model.User;

public class Richiesta implements java.io.Serializable {

	private Integer idRichiesta;
	private User user;
	private String categoria;
	private String titolo;
	private String descrizione;
	private String approvato = "N";
	private Set richiesteRispostes = new HashSet(0);

	public Richiesta() {
	}

	public Richiesta(User user, String categoria, String titolo, String descrizione, String approvato) {
		this.user = user;
		this.categoria = categoria;
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.approvato = approvato;
	}

	public Richiesta(User users, String categoria, String titolo, String descrizione, String approvato,
			Set richiesteRispostes) {
		this.user = users;
		this.categoria = categoria;
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.approvato = approvato;
		this.richiesteRispostes = richiesteRispostes;
	}

	public Integer getIdRichiesta() {
		return this.idRichiesta;
	}

	public void setIdRichiesta(Integer idRichiesta) {
		this.idRichiesta = idRichiesta;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getTitolo() {
		return this.titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getApprovato() {
		return this.approvato;
	}

	public void setApprovato(String approvato) {
		this.approvato = approvato;
	}

	public Set getRichiesteRispostes() {
		return this.richiesteRispostes;
	}

	public void setRichiesteRispostes(Set richiesteRispostes) {
		this.richiesteRispostes = richiesteRispostes;
	}

}
