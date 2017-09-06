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
	public void delete(Pagine persistentInstance) {
		Session session = HibernateUtils.getSession();
		try {
			session.delete(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}finally {
			session.close();
		}
	}

	public Pagine findById(java.lang.Integer id) {
		Session session = HibernateUtils.getSession();
		try {
			Pagine instance = (Pagine) session.get("it.pennino.uni.piazzaAffari.cms.model.Pagine", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List findByExample(Pagine instance) {
		Session session = HibernateUtils.getSession();
		try {
			List results = session.createCriteria("it.pennino.uni.piazzaAffari.cms.model.Pagine").add(Example.create(instance)).list();
			return results;
		} catch (RuntimeException re) {
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
