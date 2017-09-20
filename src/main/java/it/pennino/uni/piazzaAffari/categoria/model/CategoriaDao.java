package it.pennino.uni.piazzaAffari.categoria.model;

import java.util.ArrayList;
import java.util.List;

public interface CategoriaDao {
	public void delete(Categoria persistentInstance);
	public Categoria findById(java.lang.String id);
	public ArrayList<Categoria> findAll();
}
