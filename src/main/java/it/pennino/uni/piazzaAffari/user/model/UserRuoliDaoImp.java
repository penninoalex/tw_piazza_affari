package it.pennino.uni.piazzaAffari.user.model;

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import it.pennino.uni.piazzaAffari.utils.HibernateUtils;

public class UserRuoliDaoImp implements UserRuoliDao{

	private static final Log log = LogFactory.getLog(UserRuoliDaoImp.class);
	
	

	public void persist(UserRuoli transientInstance) {
		log.debug("persisting UserRuoli instance");
		try {
			HibernateUtils.getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(UserRuoli instance) {
		log.debug("attaching dirty UserRuoli instance");
		try {
			HibernateUtils.getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UserRuoli instance) {
		log.debug("attaching clean UserRuoli instance");
		try {
			HibernateUtils.getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(UserRuoli persistentInstance) {
		log.debug("deleting UserRuoli instance");
		try {
			HibernateUtils.getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UserRuoli merge(UserRuoli detachedInstance) {
		log.debug("merging UserRuoli instance");
		try {
			UserRuoli result = (UserRuoli) HibernateUtils.getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public UserRuoli findById(java.lang.Integer id) {
		log.debug("getting UserRuoli instance with id: " + id);
		try {
			UserRuoli instance = (UserRuoli) HibernateUtils.getSession().get("it.pennino.uni.piazzaAffari.user.model.UserRuoli", id);
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

	public List findByExample(UserRuoli instance) {
		log.debug("finding UserRuoli instance by example");
		try {
			List results = HibernateUtils.getSession().createCriteria("it.pennino.uni.piazzaAffari.user.model.UserRuoli").add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
