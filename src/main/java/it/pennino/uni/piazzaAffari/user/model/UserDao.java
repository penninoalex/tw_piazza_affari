package it.pennino.uni.piazzaAffari.user.model;

import java.util.ArrayList;
import java.util.List;


public interface UserDao {
	public void save(User utente);
	public void delete(User persistentInstance);
	public User findById(java.lang.Integer id);
	public List findByExample(User instance);
	public User verificaCredenziali(String username, String password);
	public User findByUserName(String username);
	public ArrayList<User> findAll(String stato);
}
