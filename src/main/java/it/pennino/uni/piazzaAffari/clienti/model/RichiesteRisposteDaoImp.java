package it.pennino.uni.piazzaAffari.clienti.model;

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import it.pennino.uni.piazzaAffari.utils.HibernateUtils;

public class RichiesteRisposteDaoImp implements RichiesteRisposteDao{

	public void delete(RichiesteRisposte persistentInstance) {
		Session session = HibernateUtils.getSession();
		try {
			session.delete(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}finally {
			session.close();
		}
	}


	public RichiesteRisposte findById(RichiesteRisposteId id) {
		Session session = HibernateUtils.getSession();
		try {
			RichiesteRisposte instance = (RichiesteRisposte) session.get("it.pennino.uni.piazzaAffari.clienti.model.RichiesteRisposte", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

}
