package it.pennino.uni.piazzaAffari.user.model;
public class UsersCategorie implements java.io.Serializable {

	private UsersCategorieId id;

	public UsersCategorie() {
	}

	public UsersCategorie(UsersCategorieId id) {
		this.id = id;
	}

	public UsersCategorieId getId() {
		return this.id;
	}

	public void setId(UsersCategorieId id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UsersCategorie [id=" + id + "]";
	}
	
	
	

}
