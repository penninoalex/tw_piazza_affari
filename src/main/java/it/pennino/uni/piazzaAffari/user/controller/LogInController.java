package it.pennino.uni.piazzaAffari.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import it.pennino.uni.piazzaAffari.user.model.User;
import it.pennino.uni.piazzaAffari.user.model.UserDaoImp;
import it.pennino.uni.piazzaAffari.utils.HibernateUtils;

@Controller
public class LogInController {

	@RequestMapping(value = "/accedi" , method = RequestMethod.GET)
	public ModelAndView registrazione(){
		ModelAndView model = new ModelAndView("view/logIn");
		model.addObject("titolo", "LogIn");
		
		return model;
		
	}

	/*
	@RequestMapping(value = "/verificaCredenziali" , method = RequestMethod.POST)
	public ModelAndView salvaUtente(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("email") String email,
			@RequestParam("password") String password){

		Session session = HibernateUtils.getSession();
		User utente =null;
		try {
			utente = (new UserDaoImp()).verificaCredenziali(email, password);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session.isConnected()){
				session.close();
			}
		}
		
		ModelAndView model = null;
		
		if(utente!=null && utente.getApprovato().equals("Y")){
			//Imposta sessione
			
			model = new ModelAndView("view/loginOK");
			model.addObject("titolo", "Login");
			model.addObject("utente", utente);
			
		}else{
			
			model = new ModelAndView("view/logIn");
			model.addObject("titolo", "Login");
			
			if(utente!=null && utente.getApprovato().equals("N")){
				model.addObject("errore", "Utente non ancora approvato");
			}else if(utente!=null){
				model.addObject("errore", "E-Mail o Password errati");
			}
		}
		
		
		return model;
		
	}
	*/
}
