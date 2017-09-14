package it.pennino.uni.piazzaAffari.user.model;

public class UsersCategorieId implements java.io.Serializable {

	private int idUser;
	private String categoria;

	public UsersCategorieId() {
	}

	public UsersCategorieId(int idUser, String categoria) {
		this.idUser = idUser;
		this.categoria = categoria;
	}

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UsersCategorieId))
			return false;
		UsersCategorieId castOther = (UsersCategorieId) other;

		return (this.getIdUser() == castOther.getIdUser())
				&& ((this.getCategoria() == castOther.getCategoria()) || (this.getCategoria() != null
						&& castOther.getCategoria() != null && this.getCategoria().equals(castOther.getCategoria())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdUser();
		result = 37 * result + (getCategoria() == null ? 0 : this.getCategoria().hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "UsersCategorieId [idUser=" + idUser + ", categoria=" + categoria + "]";
	}
	
	

}
