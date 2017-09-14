package it.pennino.uni.piazzaAffari.user.model;

import java.util.HashSet;
import java.util.Set;

//it.pennino.uni.piazzaAffari.user.model.User
public class User implements java.io.Serializable {

	private Integer id;
	private String nome;
	private String cognome;
	private String email;
	private Integer comune;
	private String password;
	private Set userRuoli= new HashSet(0);
	private Set userCategorie= new HashSet(0);
	private Set annunci = new HashSet(0);
	private String approvato="N";


	public User() {
	}

	public User(String nome, String cognome, String email,Integer comune, String password) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.comune = comune;
		
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set getUserRuoli() {
		return this.userRuoli;
	}

	public void setUserRuoli(Set userRuoli) {
		this.userRuoli = userRuoli;
	}
	
	public Set getAnnunci() {
		return this.annunci;
	}

	public void setAnnunci(Set annunci) {
		this.annunci = annunci;
	}
	public String getApprovato() {
		return this.approvato;
	}

	public void setApprovato(String approvato) {
		this.approvato = approvato;
	}

	public Integer getComune() {
		return comune;
	}

	public void setComune(Integer comune) {
		this.comune = comune;
	}

	public Set getUserCategorie() {
		return userCategorie;
	}

	public void setUserCategorie(Set userCategorie) {
		this.userCategorie = userCategorie;
	}

	
	
}
