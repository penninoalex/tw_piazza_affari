package it.pennino.uni.piazzaAffari.clienti.model;

import java.util.ArrayList;

import it.pennino.uni.piazzaAffari.user.model.User;
import it.pennino.uni.piazzaAffari.user.model.UsersCategorie;


public interface RichiestaDao {

	public void delete(Richiesta persistentInstance);
	public Richiesta findById(java.lang.Integer id);
	public void save(Richiesta richiesta);
	public Integer numeroRichieste();
	public ArrayList<Richiesta> findAll(User utente);
	public ArrayList<Richiesta> findAllByCategoria(ArrayList<UsersCategorie> categoria,Integer codIstatComune);
		
}
