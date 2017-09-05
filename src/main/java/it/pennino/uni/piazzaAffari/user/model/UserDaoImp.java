package it.pennino.uni.piazzaAffari.user.model;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import it.pennino.uni.piazzaAffari.utils.HibernateUtils;


public class UserDaoImp implements UserDao{
	private static final Log log = LogFactory.getLog(UserDaoImp.class);
	
	public void save(User utente) {
		Session session = HibernateUtils.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(utente);
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

	

	public void delete(User persistentInstance) {
		Session session = HibernateUtils.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(persistentInstance);
			tx.commit();
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}finally {
			if(session.isConnected()){
				session.close();
			}
		}
	}


	public User findById(java.lang.Integer id) {
		Session session = HibernateUtils.getSession();
		try {
			User instance = (User)session.get(User.class, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally {
			if(session.isConnected()){
				session.close();
			}
		}
	}

	public List findByExample(User instance) {
		Session session = HibernateUtils.getSession();
		try {
			List results = session.createCriteria(User.class).add(Example.create(instance)).list();
			return results;
		} catch (RuntimeException re) {
			throw re;
		}finally {
			if(session.isConnected()){
				session.close();
			}
		}
	}
	
	public User verificaCredenziali(String username, String password) {
		Session session = HibernateUtils.getSession();
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("email", username));
		cr.add(Restrictions.eq("password", password));

		List results = cr.list();
		
		session.close();
		if (results != null && results.size() > 0) {
			User tmp = (User) results.get(0);
			return tmp;
		} else {
			return null;
		}
	}
	
	public User findByUserName(String username) {
		Session session = HibernateUtils.getSession();
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("email", username));

		List results = cr.list();
		
		session.close();
		if (results != null && results.size() > 0) {
			User tmp = (User) results.get(0);
			return tmp;
		} else {
			return null;
		}
	}
	
	public ArrayList<User> findAll(String stato) {
		Session session = HibernateUtils.getSession();
		Criteria cr = session.createCriteria(User.class);
		//TODO Gestire il caso di stato valorizzato
		
		List results = cr.list();
		
		session.close();
		if (results != null && results.size() > 0) {
			return (ArrayList<User>) results;
		} else {
			return null;
		}
	}
}
