package it.pennino.uni.piazzaAffari.categoria.model;

import java.util.ArrayList;
import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import it.pennino.uni.piazzaAffari.user.model.User;
import it.pennino.uni.piazzaAffari.utils.HibernateUtils;

public class CategoriaDaoImp implements CategoriaDao{

	private static final Log log = LogFactory.getLog(CategoriaDaoImp.class);

	public void persist(Categoria transientInstance) {
		log.debug("persisting Categorie instance");
		try {
			HibernateUtils.getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void delete(Categoria persistentInstance) {
		log.debug("deleting Categorie instance");
		try {
			HibernateUtils.getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Categoria merge(Categoria detachedInstance) {
		log.debug("merging Categorie instance");
		try {
			Categoria result = (Categoria) HibernateUtils.getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Categoria findById(java.lang.String id) {
		log.debug("getting Categorie instance with id: " + id);
		try {
			Categoria instance = (Categoria) HibernateUtils.getSession().get("it.pennino.uni.piazzaAffari.categoria.model.Categoria", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Categoria instance) {
		log.debug("finding Categorie instance by example");
		try {
			List results = HibernateUtils.getSession().createCriteria("it.pennino.uni.piazzaAffari.categoria.model.Categoria").add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
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
