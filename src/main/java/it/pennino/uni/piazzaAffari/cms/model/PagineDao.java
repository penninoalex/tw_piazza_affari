package it.pennino.uni.piazzaAffari.cms.model;

import java.util.List;

public interface PagineDao {
	public void delete(Pagine persistentInstance);
	public Pagine findById(java.lang.Integer id);
	public List findByExample(Pagine instance);
	public Pagine findByUrl(String url);
	
}
