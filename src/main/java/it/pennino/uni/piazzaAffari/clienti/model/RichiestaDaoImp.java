package it.pennino.uni.piazzaAffari.clienti.model;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import it.pennino.uni.piazzaAffari.user.model.User;
import it.pennino.uni.piazzaAffari.utils.HibernateUtils;

public class RichiestaDaoImp implements RichiestaDao{
 
	public void save(Richiesta richiesta) {
		Session session = HibernateUtils.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(richiesta);
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
	
	public void delete(Richiesta richiesta) {
		Session session = HibernateUtils.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(richiesta);
			tx.commit();
		} catch (RuntimeException re) {
			tx.rollback();
			throw re;
		}finally {
			if(session.isConnected()){
				session.close();
			}
		}
	}

	public Richiesta findById(java.lang.Integer id) {
		Session session = HibernateUtils.getSession();
		try {
			Richiesta instance = (Richiesta) session.get("it.pennino.uni.piazzaAffari.clienti.model.Richiesta", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}finally {
			if(session.isConnected()){
				session.close();
			}
		}
	}

	public ArrayList<Richiesta> findAll(User utente) {
		Session session = HibernateUtils.getSession();
		List results =null;
		try {
			Criteria cr = session.createCriteria(Richiesta.class);
			
			if(utente!=null){
				cr.add(Restrictions.eq("user", utente));
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
			return (ArrayList<Richiesta>) results;
		} else {
			return null;
		}
	}

}
