package it.pennino.uni.piazzaAffari.comuni.model;
// Generated 11-set-2017 21.30.31 by Hibernate Tools 5.2.1.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Regioni.
 * @see it.pennino.uni.piazzaAffari.user.tmp.Regioni
 * @author Hibernate Tools
 */
public class RegioniHome {

	private static final Log log = LogFactory.getLog(RegioniHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Regioni transientInstance) {
		log.debug("persisting Regioni instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Regioni instance) {
		log.debug("attaching dirty Regioni instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Regioni instance) {
		log.debug("attaching clean Regioni instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Regioni persistentInstance) {
		log.debug("deleting Regioni instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Regioni merge(Regioni detachedInstance) {
		log.debug("merging Regioni instance");
		try {
			Regioni result = (Regioni) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Regioni findById(java.lang.Integer id) {
		log.debug("getting Regioni instance with id: " + id);
		try {
			Regioni instance = (Regioni) sessionFactory.getCurrentSession()
					.get("it.pennino.uni.piazzaAffari.user.tmp.Regioni", id);
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

	public List findByExample(Regioni instance) {
		log.debug("finding Regioni instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("it.pennino.uni.piazzaAffari.user.tmp.Regioni").add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
