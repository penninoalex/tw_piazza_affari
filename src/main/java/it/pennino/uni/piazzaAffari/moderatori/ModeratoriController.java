package it.pennino.uni.piazzaAffari.moderatori;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import it.pennino.uni.piazzaAffari.annuncio.model.Annuncio;
import it.pennino.uni.piazzaAffari.annuncio.model.AnnuncioDao;
import it.pennino.uni.piazzaAffari.annuncio.model.AnnuncioDaoImp;
import it.pennino.uni.piazzaAffari.user.model.User;
import it.pennino.uni.piazzaAffari.user.model.UserDao;
import it.pennino.uni.piazzaAffari.user.model.UserDaoImp;

@Controller
public class ModeratoriController {

	@RequestMapping(value = {"/moderatori","/moderatori/home"} , method = RequestMethod.GET)
	public ModelAndView moderatoriHome(){
		ModelAndView view = new ModelAndView("view/moderatori/index");
		
		return view;
	}
	
	
	@RequestMapping(value = {"/moderatori/annuncio/{id_annuncio}"} , method = RequestMethod.GET)
	public ModelAndView visualizzaAnnuncio(@PathVariable Integer id_annuncio){
		
		System.out.println("Annuncio selezionato = "+id_annuncio);
		
		
		AnnuncioDao aDao = new AnnuncioDaoImp();
		Annuncio annuncio = aDao.findById(id_annuncio);
		
		ModelAndView view = null;
		
		if(annuncio!=null){
			view = new ModelAndView("view/moderatori/annuncio");
			view.addObject("annuncio",annuncio);
		}else{
			view = new ModelAndView("view/moderatori/annuncio-err");
		}
		
		return view;
	}
	
	@RequestMapping( value = {"moderatori/annuncio/delete/{id_annuncio}"} ,method = RequestMethod.GET)
	public String cancellaAnnuncio(@PathVariable Integer id_annuncio){
		AnnuncioDao aDao = new AnnuncioDaoImp();
		Annuncio annuncio = aDao.findById(id_annuncio);
		aDao.delete(annuncio);
		
		ModelAndView view;
		if(annuncio!=null){
			view = new ModelAndView("view/moderatori/listaAnnunci");
			view.addObject("annuncioOld",annuncio);
			view.addObject("deleteOk",true);
		}else{
			view = new ModelAndView("view/moderatori/listaAnnunci");
			view.addObject("annuncioOld",annuncio);
			view.addObject("deleteOk",false);
		}
		
		return "redirect:/moderatori/lista_annunci";
		
	}
	
	@RequestMapping( value = {"moderatori/annuncio/approva/{id_annuncio}"} ,method = RequestMethod.GET)
	public String approvaAnnuncio(@PathVariable Integer id_annuncio){
		AnnuncioDao aDao = new AnnuncioDaoImp();
		Annuncio annuncio = aDao.findById(id_annuncio);
		annuncio.setApprovato("Y");
		aDao.save(annuncio);
		
		return "redirect:/moderatori/lista_annunci";
		
	}
	
	
	@RequestMapping(value = {"/moderatori/lista_annunci"} , method = RequestMethod.GET)
	public ModelAndView listaAnnunci(){
		ModelAndView view = new ModelAndView("view/moderatori/listaAnnunci");
		 
		return view;
	}
	
	@RequestMapping(value = {"/moderatori/lista_utenti"} , method = RequestMethod.GET)
	public ModelAndView listaUtenti(){
		ModelAndView view = new ModelAndView("view/moderatori/listaUtenti");
		 
		return view;
	}
	
	
	
	
	
	@RequestMapping(value = {"/moderatori/utente/{id_utente}"} , method = RequestMethod.GET)
	public ModelAndView visualizzaUtente(@PathVariable Integer id_utente){
		
		System.out.println("Utente selezionato = "+id_utente);
		
		
		UserDao uDao = new UserDaoImp();
		User utente = uDao.findById(id_utente);
		
		ModelAndView view = null;
		
		if(utente!=null){
			view = new ModelAndView("view/moderatori/schedaUtente");
			view.addObject("user",utente);
		}else{
			view = new ModelAndView("view/moderatori/schedaUtente-err");
		}
		
		return view;
	}
	
	
	@RequestMapping(value = {"/moderatori/utente/approva/{id_utente}"} , method = RequestMethod.GET)
	public String approvaUtente(@PathVariable Integer id_utente){
		System.out.println("Utente selezionato = "+id_utente);
	
		UserDao uDao = new UserDaoImp();
		User utente = uDao.findById(id_utente);
		utente.setApprovato("Y");
		uDao.save(utente);
		return "redirect:/moderatori/lista_utenti";
	}
	
					
	@RequestMapping( value = {"moderatori/utente/delete/{id_utente}"} ,method = RequestMethod.GET)
	public String cancellaUtente(@PathVariable Integer id_utente){
		UserDao uDao = new UserDaoImp();
		User utente = uDao.findById(id_utente);
		uDao.delete(utente);
		
		ModelAndView view;
		if(utente!=null){
			view = new ModelAndView("view/moderatori/listaAnnunci");
			view.addObject("utenteOld",utente);
			view.addObject("deleteOk",true);
		}else{
			view = new ModelAndView("view/moderatori/listaAnnunci");
			view.addObject("utenteOld",utente);
			view.addObject("deleteOk",false);
		}
		
		return "redirect:/moderatori/lista_utenti";
	}
}
