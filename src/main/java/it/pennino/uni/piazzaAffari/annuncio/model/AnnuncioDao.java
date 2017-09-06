package it.pennino.uni.piazzaAffari.annuncio.model;

import java.util.ArrayList;
import it.pennino.uni.piazzaAffari.user.model.User;

public interface AnnuncioDao {
	public void save(Annuncio annuncio);
	public void delete(Annuncio persistentInstance);
	public Annuncio findById(java.lang.Integer id);
	public ArrayList<Annuncio> findAll(User utente);
		
}
