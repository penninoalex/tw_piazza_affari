package it.pennino.uni.piazzaAffari.cms.model;

public class Pagine implements java.io.Serializable {

	private Integer id;
	private String titolo;
	private String contenuto;
	private String url;

	public Pagine() {
	}

	public Pagine(String titolo, String contenuto) {
		this.titolo = titolo;
		this.contenuto = contenuto;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitolo() {
		return this.titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getContenuto() {
		return this.contenuto;
	}

	public void setContenuto(String contenuto) {
		this.contenuto = contenuto;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
}
