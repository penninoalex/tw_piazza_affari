package it.pennino.uni.piazzaAffari.annuncio.model;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import it.pennino.uni.piazzaAffari.categoria.model.Categoria;
import it.pennino.uni.piazzaAffari.user.model.User;
import it.pennino.uni.piazzaAffari.utils.HibernateUtils;

public class AnnuncioDaoImp implements AnnuncioDao{

	private static final Log log = LogFactory.getLog(AnnuncioDaoImp.class);

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
		log.debug("getting Annunci instance with id: " + id);
		Session session = HibernateUtils.getSession();
		try {
			Annuncio instance = (Annuncio) session.get("it.pennino.uni.piazzaAffari.annuncio.model.Annuncio", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
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

	public List findByExample(Annuncio instance) {
		log.debug("finding Annunci instance by example");
		try {
			List results = HibernateUtils.getSession().createCriteria("it.pennino.uni.piazzaAffari.annuncio.model.Annuncio").add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
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
}
