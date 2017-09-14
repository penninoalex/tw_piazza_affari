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
import it.pennino.uni.piazzaAffari.user.model.UsersCategorie;
import it.pennino.uni.piazzaAffari.user.model.UsersCategorieId;
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
			@RequestParam(value = "cat" , required = false) String[] cat,
			@RequestParam(value = "comune" , required = false) Integer codiceIstat){
		
		System.out.println("USERS");
		System.out.println("NOME = "+nome);
		System.out.println("COGNOME = "+cognome);
		System.out.println("EMAIL = "+email);
		System.out.println("PASSWORD = "+password);
		System.out.println("CLIENTE = "+cliente);
		System.out.println("PROFESSIONISTA = "+professionista);
		System.out.println("cat = "+cat);
		System.out.println("comune = "+codiceIstat);
		
		
		ModelAndView model = new ModelAndView("view/registrazione");
		model.addObject("titolo", "Registrazione");
		
		
		if(cliente==null && professionista==null){
			model.addObject("errore","Selezionare almeno un opzione tra 'Cliente' e/o 'Professionista'");
			return model;
		}
		
		
		User utente = new User();
		utente.setCognome(cognome);
		utente.setNome(nome);
		utente.setEmail(email);
		utente.setPassword(password);
		utente.setComune(codiceIstat);
		
		Transaction tx = HibernateUtils.getSession().beginTransaction();
		Session session = HibernateUtils.getSession();
		try {
			session.saveOrUpdate(utente);
			
			if(professionista!=null){
				if(cat!=null && cat.length>0){
					//UsersCategorieDao ucDao = new UsersCategorieDaoImp();
					for(int i=0; i<cat.length; i++){
						System.out.println("Categoria = "+cat[i]);
						
						UsersCategorie uc = new UsersCategorie();
						uc.setId(new UsersCategorieId(utente.getId(), cat[i]));
						System.out.println("uc"+uc);
						//ucDao.save(uc);
						session.save(uc);
					}
				}
				
				UserRuoli ruolo = new UserRuoli();
				ruolo.setUsers(utente);
				ruolo.setRuolo("ROLE_PROFESSIONISTA");
				session.save(ruolo);
			}
			
			if(cliente!=null){
				UserRuoli ruolo = new UserRuoli();
				ruolo.setUsers(utente);
				ruolo.setRuolo("ROLE_CLIENTE");
				session.save(ruolo);
			}
			
			tx.commit();
			model.addObject("msgOk","Registrazione avvenuta con successo. Attendere la validazione da parte di un moderatore.");
		} catch (Exception e) {
			model.addObject("errore",e.getMessage());
			tx.rollback();
			e.printStackTrace();
		}finally {
			if(session.isConnected()){
				session.close();
			}
		}
		
		
		return model;
		
	}
}
