package it.pennino.uni.piazzaAffari.comuni.model;

import java.util.ArrayList;

public interface ComuneDao {


	public void delete(Comune persistentInstance);
	public Comune findById(int id);
	public ArrayList<Comune> findAll();
}
