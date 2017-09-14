package it.pennino.uni.piazzaAffari.user.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import it.pennino.uni.piazzaAffari.categoria.model.Categoria;
import it.pennino.uni.piazzaAffari.clienti.model.Richiesta;
import it.pennino.uni.piazzaAffari.utils.HibernateUtils;


public class UsersCategorieDaoImp implements UsersCategorieDao{
	public void save(UsersCategorie persistentInstance) {
		Session session = HibernateUtils.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(persistentInstance);
			tx.commit();
		} catch (RuntimeException re) {
			throw re;
		}finally {
			if(session.isConnected()){
				session.close();
			}
		}
	}
	public void delete(UsersCategorie persistentInstance) {
		Session session = HibernateUtils.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(persistentInstance);
			tx.commit();
		} catch (RuntimeException re) {
			throw re;
		}finally {
			if(session.isConnected()){
				session.close();
			}
		}
	}

	public UsersCategorie findById(UsersCategorieId id) {
		Session session = HibernateUtils.getSession();
		try {
			UsersCategorie instance = (UsersCategorie) session.get(UsersCategorie.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}finally {
			if(session.isConnected()){
				session.close();
			}
		}
	}
	
	public ArrayList<UsersCategorie> findAllByUser(User utente) {
		Session session = HibernateUtils.getSession();
		List results =null;
		try {
			Criteria cr = session.createCriteria(UsersCategorie.class);
			
			cr.add(Restrictions.in("id.idUser", utente.getId()));
			results = cr.list();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session.isConnected()){
				session.close();
			}
		}
		
		if (results != null && results.size() > 0) {
			return (ArrayList<UsersCategorie>) results;
		} else {
			return null;
		}
	}

}
