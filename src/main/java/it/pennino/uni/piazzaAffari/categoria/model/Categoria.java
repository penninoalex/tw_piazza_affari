package it.pennino.uni.piazzaAffari.categoria.model;

import java.util.HashSet;
import java.util.Set;

//it.pennino.uni.piazzaAffari.categoria.model.Categoria
public class Categoria implements java.io.Serializable {

	private String nome;
	private Set annunci = new HashSet(0);

	public Categoria() {
	}

	public Categoria(String nome) {
		this.nome = nome;
	}

	public Categoria(String nome, Set annunci) {
		this.nome = nome;
		this.annunci = annunci;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set getAnnunci() {
		return this.annunci;
	}

	public void setAnnunci(Set annunci) {
		this.annunci = annunci;
	}

}
