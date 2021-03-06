package it.pennino.uni.piazzaAffari.annuncio.model;

import java.math.BigDecimal;
import it.pennino.uni.piazzaAffari.categoria.model.Categoria;
import it.pennino.uni.piazzaAffari.user.model.User;

public class Annuncio implements java.io.Serializable {

	private Integer idAnnuncio;
	private Categoria categoria;
	private User users;
	private String titolo;
	private String descrizione;
	private BigDecimal prezzo;
	private String tipoPrezzo="O";
	private String approvato="N";

	public Annuncio() {
	}

	public Annuncio(Categoria categorie, User users, String titolo, String descrizione, BigDecimal prezzo,
			String tipoPrezzo, String approvato) {
		this.categoria = categorie;
		this.users = users;
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.tipoPrezzo = tipoPrezzo;
		this.approvato = approvato;
	}

	public Integer getIdAnnuncio() {
		return this.idAnnuncio;
	}

	public void setIdAnnuncio(Integer idAnnuncio) {
		this.idAnnuncio = idAnnuncio;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public User getUsers() {
		return this.users;
	}

	public void setUsers(User users) {
		this.users = users;
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

	public BigDecimal getPrezzo() {
		return this.prezzo;
	}

	public void setPrezzo(BigDecimal prezzo) {
		this.prezzo = prezzo;
	}

	public String getTipoPrezzo() {
		return this.tipoPrezzo;
	}

	public void setTipoPrezzo(String tipoPrezzo) {
		this.tipoPrezzo = tipoPrezzo;
	}

	public String getApprovato() {
		return this.approvato;
	}

	public void setApprovato(String approvato) {
		this.approvato = approvato;
	}

}
