package it.pennino.uni.piazzaAffari.user.model;


public class UserRuoli implements java.io.Serializable {

	private Integer idRuolo;
	private User users;
	private String ruolo;

	public UserRuoli() {
	}

	public UserRuoli(User users, String ruolo) {
		this.users = users;
		this.ruolo = ruolo;
	}

	public Integer getIdRuolo() {
		return this.idRuolo;
	}

	public void setIdRuolo(Integer idRuolo) {
		this.idRuolo = idRuolo;
	}

	public User getUsers() {
		return this.users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public String getRuolo() {
		return this.ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	@Override
	public String toString() {
		return ruolo;
	}
	
	

}
