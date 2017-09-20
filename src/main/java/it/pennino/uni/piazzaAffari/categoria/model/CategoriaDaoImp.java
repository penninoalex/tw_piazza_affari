package it.pennino.uni.piazzaAffari.categoria.model;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import it.pennino.uni.piazzaAffari.utils.HibernateUtils;

public class CategoriaDaoImp implements CategoriaDao{
	
	public void delete(Categoria categoria) {
		Session session = HibernateUtils.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(categoria);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			if(session.isConnected()){
				session.close();
			}
		}
	}
	
	public Categoria findById(java.lang.String id) {
		Session session = HibernateUtils.getSession();
		try {
			Categoria instance = (Categoria) session.get("it.pennino.uni.piazzaAffari.categoria.model.Categoria", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}finally {
			if(session.isConnected()){
				session.close();
			}
		}
	}


	
	public ArrayList<Categoria> findAll() {
		Session session = HibernateUtils.getSession();
		Criteria cr = session.createCriteria(Categoria.class);
		List results = cr.list();
		
		session.close();
		if (results != null && results.size() > 0) {
			return (ArrayList<Categoria>) results;
		} else {
			return null;
		}
	}
}
