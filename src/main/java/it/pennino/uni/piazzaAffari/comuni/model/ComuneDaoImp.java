package it.pennino.uni.piazzaAffari.comuni.model;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import it.pennino.uni.piazzaAffari.utils.HibernateUtils;

public class ComuneDaoImp implements ComuneDao{


	public void delete(Comune persistentInstance) {
		Session session = HibernateUtils.getSession();
		try {
			session.delete(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}finally {
			if(session.isConnected()){
				session.close();
			}
		}
	}

	public Comune findById(int id) {
		Session session = HibernateUtils.getSession();
		try {
			Comune instance = (Comune) session.get(Comune.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}finally {
			if(session.isConnected()){
				session.close();
			}
		}
	}
	
	public ArrayList<Comune> findAll() {
		Session session = HibernateUtils.getSession();
		List results =null;
		try {
			Criteria cr = session.createCriteria(Comune.class);
			results = cr.list();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session.isConnected()){
				session.close();
			}
		}
		
		
		if (results != null && results.size() > 0) {
			return (ArrayList<Comune>) results;
		} else {
			return null;
		}
	}

}
