package it.pennino.uni.piazzaAffari.comuni.model;
// Generated 11-set-2017 21.30.31 by Hibernate Tools 5.2.1.Final

/**
 * Comuni generated by hbm2java
 */
public class Comune implements java.io.Serializable {

	private int istat;
	private String comune;
	private String provincia;
	private String regione;
	private String cap;

	public Comune() {
	}

	public Comune(int istat) {
		this.istat = istat;
	}

	public Comune(int istat, String comune, String provincia, String regione, String cap) {
		this.istat = istat;
		this.comune = comune;
		this.provincia = provincia;
		this.regione = regione;
		this.cap = cap;
	}

	public int getIstat() {
		return this.istat;
	}

	public void setIstat(int istat) {
		this.istat = istat;
	}

	public String getComune() {
		return this.comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getRegione() {
		return this.regione;
	}

	public void setRegione(String regione) {
		this.regione = regione;
	}

	public String getCap() {
		return this.cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

}
