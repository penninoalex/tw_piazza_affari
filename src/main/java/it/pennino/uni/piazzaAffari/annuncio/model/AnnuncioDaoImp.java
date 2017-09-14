package it.pennino.uni.piazzaAffari.annuncio.model;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import it.pennino.uni.piazzaAffari.categoria.model.Categoria;
import it.pennino.uni.piazzaAffari.comuni.model.Comune;
import it.pennino.uni.piazzaAffari.user.model.User;
import it.pennino.uni.piazzaAffari.utils.HibernateUtils;

public class AnnuncioDaoImp implements AnnuncioDao{

	public void save(Annuncio annuncio) {
		Session session = HibernateUtils.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(annuncio);
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

	public void delete(Annuncio annuncio) {
		Session session = HibernateUtils.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(annuncio);
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
	
	public Annuncio findById(java.lang.Integer id) {
		Session session = HibernateUtils.getSession();
		try {
			Annuncio instance = (Annuncio) session.get("it.pennino.uni.piazzaAffari.annuncio.model.Annuncio", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}finally {
			if(session.isConnected()){
				session.close();
			}
		}
	}

	public ArrayList<Annuncio> findAll(User utente) {
		Session session = HibernateUtils.getSession();
		List results =null;
		try {
			Criteria cr = session.createCriteria(Annuncio.class);
			
			if(utente!=null){
				cr.add(Restrictions.eq("users", utente));
			}
			results = cr.list();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session.isConnected()){
				session.close();
			}
		}
		
		
		if (results != null && results.size() > 0) {
			return (ArrayList<Annuncio>) results;
		} else {
			return null;
		}
	}
	
	
	public ArrayList<Annuncio> findByCategoriaCitta(Categoria categoria, Integer codIstat) {
		Session session = HibernateUtils.getSession();
		List results =null;
		try {
			Criteria cr = session.createCriteria(Annuncio.class);
			
			cr.add(Restrictions.eq("categoria", categoria));
			//TODO Modificare
			//cr.add(Restrictions.eq("users.comune", codIstat));
			cr.add(Restrictions.eq("approvato", "Y"));
			
			results = cr.list();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session.isConnected()){
				session.close();
			}
		}
		
		
		if (results != null && results.size() > 0) {
			return (ArrayList<Annuncio>) results;
		} else {
			return null;
		}
	}
}
