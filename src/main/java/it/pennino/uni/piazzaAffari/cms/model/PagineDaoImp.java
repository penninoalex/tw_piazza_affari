package it.pennino.uni.piazzaAffari.cms.model;

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

import it.pennino.uni.piazzaAffari.categoria.model.Categoria;
import it.pennino.uni.piazzaAffari.utils.HibernateUtils;


public class PagineDaoImp implements PagineDao {

	private static final Log log = LogFactory.getLog(PagineDaoImp.class);

	
	public void delete(Pagine persistentInstance) {
		log.debug("deleting Pagine instance");
		Session session = HibernateUtils.getSession();
		try {
			session.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}finally {
			session.close();
		}
	}

	public Pagine findById(java.lang.Integer id) {
		Session session = HibernateUtils.getSession();
		
		log.debug("getting Pagine instance with id: " + id);
		try {
			Pagine instance = (Pagine) session.get("it.pennino.uni.piazzaAffari.cms.model.Pagine", id);
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

	public List findByExample(Pagine instance) {
		Session session = HibernateUtils.getSession();
		log.debug("finding Pagine instance by example");
		try {
			List results = session.createCriteria("it.pennino.uni.piazzaAffari.cms.model.Pagine").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public Pagine findByUrl(String url) {
		Session session = HibernateUtils.getSession();
		Criteria cr = session.createCriteria(Pagine.class);
		
		cr.add(Restrictions.eq("url", url));
		
		List results = cr.list();
		
		session.close();
		if (results != null && results.size() > 0) {
			return  (Pagine) results.get(0);
		} else {
			return null;
		}
	}
}
