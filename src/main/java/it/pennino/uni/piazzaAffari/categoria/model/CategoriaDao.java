package it.pennino.uni.piazzaAffari.categoria.model;

import java.util.ArrayList;
import java.util.List;

public interface CategoriaDao {
	public void persist(Categoria transientInstance);
	public void delete(Categoria persistentInstance);
	public Categoria findById(java.lang.String id);
	public List findByExample(Categoria instance);
	public ArrayList<Categoria> findAll();
}
