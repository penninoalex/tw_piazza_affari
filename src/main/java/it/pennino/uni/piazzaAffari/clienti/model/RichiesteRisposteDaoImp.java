package it.pennino.uni.piazzaAffari.clienti.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

import it.pennino.uni.piazzaAffari.utils.HibernateUtils;

public class RichiesteRisposteDaoImp implements RichiesteRisposteDao{

	public void delete(RichiesteRisposte persistentInstance) {
		Session session = HibernateUtils.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(persistentInstance);
			tx.commit();
		} catch (RuntimeException re) {
			tx.rollback();
			throw re;
		}finally {
			session.close();
		}
	}
	
	public void save(RichiesteRisposte persistentInstance) {
		Session session = HibernateUtils.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(persistentInstance);
			tx.commit();
		} catch (RuntimeException re) {
			tx.rollback();
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

	public static Integer nextIdRisposta() {
		Session session = HibernateUtils.getSession();
		Criteria cr = session.createCriteria(RichiesteRisposte.class);
		ProjectionList proj = Projections.projectionList();
		proj = proj.add(Projections.max("id.idRisposta"));
		cr = cr.setProjection(proj);
		List ris = cr.list();
		session.clear();
		session.close();
		if (ris != null && ris.size() > 0) {
			Integer tmoPbj = (Integer) ris.get(0);
			if (tmoPbj != null) {
				return (tmoPbj + 1);
			} else {
				return 1;
			}
		} else {
			return 1;
		}

	}

}
