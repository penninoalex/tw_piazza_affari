package it.pennino.uni.piazzaAffari.clienti.model;


public class RichiesteRisposteId implements java.io.Serializable {

	private int idRichiesta;
	private int idRisposta;

	public RichiesteRisposteId() {
	}

	public RichiesteRisposteId(int idRichiesta, int idRisposta) {
		this.idRichiesta = idRichiesta;
		this.idRisposta = idRisposta;
	}

	public int getIdRichiesta() {
		return this.idRichiesta;
	}

	public void setIdRichiesta(int idRichiesta) {
		this.idRichiesta = idRichiesta;
	}

	public int getIdRisposta() {
		return this.idRisposta;
	}

	public void setIdRisposta(int idRisposta) {
		this.idRisposta = idRisposta;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RichiesteRisposteId))
			return false;
		RichiesteRisposteId castOther = (RichiesteRisposteId) other;

		return (this.getIdRichiesta() == castOther.getIdRichiesta())
				&& (this.getIdRisposta() == castOther.getIdRisposta());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdRichiesta();
		result = 37 * result + this.getIdRisposta();
		return result;
	}

}
