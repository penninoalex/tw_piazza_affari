package it.pennino.uni.piazzaAffari.user.model;

import java.util.ArrayList;

public interface UsersCategorieDao {
	public void delete(UsersCategorie persistentInstance);
	public void save(UsersCategorie persistentInstance);
	public UsersCategorie findById(UsersCategorieId id);
	public ArrayList<UsersCategorie> findAllByUser(User utente);
}
