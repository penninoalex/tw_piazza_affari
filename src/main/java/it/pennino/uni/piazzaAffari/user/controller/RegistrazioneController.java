package it.pennino.uni.piazzaAffari.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.pennino.uni.piazzaAffari.user.model.User;
import it.pennino.uni.piazzaAffari.user.model.UserRuoli;
import it.pennino.uni.piazzaAffari.utils.HibernateUtils;

@Controller
public class RegistrazioneController {

	@RequestMapping(value = "/registrazione" , method = RequestMethod.GET)
	public ModelAndView registrazione(){
		ModelAndView model = new ModelAndView("view/registrazione");
		model.addObject("titolo", "Registrazione");
		
		return model;
		
	}
	

	//saveUser
	@RequestMapping(value = "/saveUser" , method = RequestMethod.POST)
	public ModelAndView salvaUtente(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("nome") String nome,
			@RequestParam("cognome") String cognome,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam(value = "rCliente" , required = false) String cliente,
			@RequestParam(value = "rProf" , required = false) String professionista,
			@RequestParam(value = "cat" , required = false) String[] cat){
		
		System.out.println("USERS");
		System.out.println("NOME = "+nome);
		System.out.println("COGNOME = "+cognome);
		System.out.println("EMAIL = "+email);
		System.out.println("PASSWORD = "+password);
		System.out.println("CLIENTE = "+cliente);
		System.out.println("PROFESSIONISTA = "+professionista);
		System.out.println("cat = "+cat[0]);
		
		User utente = new User();
		utente.setCognome(cognome);
		utente.setNome(nome);
		utente.setEmail(email);
		utente.setPassword(password);
		
		Transaction tx = HibernateUtils.getSession().beginTransaction();
		Session session = HibernateUtils.getSession();
		try {
			session.saveOrUpdate(utente);
			
			UserRuoli ruolo;
			if(professionista!=null){
				ruolo = new UserRuoli();
				ruolo.setUsers(utente);
				ruolo.setRuolo("ROLE_PROFESSIONISTA");
				session.save(ruolo);
				
				ruolo = new UserRuoli();
				ruolo.setUsers(utente);
				ruolo.setRuolo("ROLE_CLIENTE");
				session.save(ruolo);
			
				//TODO imposta categorie professionista
				
				
			}else{
				ruolo = new UserRuoli();
				ruolo.setUsers(utente);
				ruolo.setRuolo("ROLE_CLIENTE");
				session.save(ruolo);
			}
			
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			if(session.isConnected()){
				session.close();
			}
		}
		
		ModelAndView model = new ModelAndView("view/registrazioneOK");
		model.addObject("titolo", "Registrazione");
		
		return model;
		
	}
}
