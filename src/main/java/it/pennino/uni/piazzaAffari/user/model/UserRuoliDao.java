package it.pennino.uni.piazzaAffari.user.model;

import java.util.List;

public interface UserRuoliDao {
	public void persist(UserRuoli transientInstance);
	public void delete(UserRuoli persistentInstance);
	public UserRuoli findById(java.lang.Integer id);
	public List findByExample(UserRuoli instance);
	
}
