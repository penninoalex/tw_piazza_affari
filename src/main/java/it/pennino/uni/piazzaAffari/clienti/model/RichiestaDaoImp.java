package it.pennino.uni.piazzaAffari.clienti.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.context.SecurityContextHolder;

import it.pennino.uni.piazzaAffari.categoria.model.Categoria;
import it.pennino.uni.piazzaAffari.user.controller.UserSession;
import it.pennino.uni.piazzaAffari.user.model.User;
import it.pennino.uni.piazzaAffari.user.model.UsersCategorie;
import it.pennino.uni.piazzaAffari.user.model.UsersCategorieDao;
import it.pennino.uni.piazzaAffari.user.model.UsersCategorieDaoImp;
import it.pennino.uni.piazzaAffari.utils.HibernateUtils;

public class RichiestaDaoImp implements RichiestaDao{
 
	public void save(Richiesta richiesta) {
		Session session = HibernateUtils.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(richiesta);
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
	
	public void delete(Richiesta richiesta) {
		Session session = HibernateUtils.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(richiesta);
			tx.commit();
		} catch (RuntimeException re) {
			tx.rollback();
			throw re;
		}finally {
			if(session.isConnected()){
				session.close();
			}
		}
	}

	public Richiesta findById(java.lang.Integer id) {
		Session session = HibernateUtils.getSession();
		try {
			Richiesta instance = (Richiesta) session.get("it.pennino.uni.piazzaAffari.clienti.model.Richiesta", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}finally {
			if(session.isConnected()){
				session.close();
			}
		}
	}

	public ArrayList<Richiesta> findAll(User utente) {
		Session session = HibernateUtils.getSession();
		List results =null;
		try {
			Criteria cr = session.createCriteria(Richiesta.class);
			
			if(utente!=null){
				cr.add(Restrictions.eq("user", utente));
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
			return (ArrayList<Richiesta>) results;
		} else {
			return null;
		}
	}
	
	

	
	public ArrayList<Richiesta> findAllByCategoria(ArrayList<UsersCategorie> categorieUtente,Integer codIstatComune) {
		Session session = HibernateUtils.getSession();
		List results =null;
		
		//Trasformo la sista di usersCategorie in una lista di stringhe
		ArrayList<String> categorie = new ArrayList<String>();
		if(categorieUtente!=null && categorieUtente.size()>0){
			for(int i=0; i<categorieUtente.size(); i++){
				categorie.add(categorieUtente.get(i).getId().getCategoria());
			}
		}
		
		
		try {
			Criteria cr = session.createCriteria(Richiesta.class);
			cr.add(Restrictions.in("categoria", categorie));
			
			cr.createAlias("user", "user");
			cr.add(Restrictions.eq("user.comune", codIstatComune));
		
			cr.add(Restrictions.eq("approvato", "Y"));
			results = cr.list();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session.isConnected()){
				session.close();
			}
		}
		
		if (results != null && results.size() > 0) {
			return (ArrayList<Richiesta>) results;
		} else {
			return null;
		}
	}
	
	
	public Integer numeroRichieste(ArrayList<UsersCategorie> categoria,Integer codIstatComune) {
		ArrayList<Richiesta> richieste = findAllByCategoria(categoria, codIstatComune);
		if(richieste==null){
			return 0;
		}else{
			return richieste.size();
		}
		
	}
	
	public Integer numeroRichieste() {
		UserSession userSession = (UserSession)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		UsersCategorieDao usCatDao = new UsersCategorieDaoImp();
		ArrayList<UsersCategorie> categorie = usCatDao.findAllByUser(userSession.getUser());
	
		return numeroRichieste(categorie, userSession.getUser().getComune());
	}

}
