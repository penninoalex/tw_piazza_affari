package it.pennino.uni.piazzaAffari.moderatori;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import it.pennino.uni.piazzaAffari.annuncio.model.Annuncio;
import it.pennino.uni.piazzaAffari.annuncio.model.AnnuncioDao;
import it.pennino.uni.piazzaAffari.annuncio.model.AnnuncioDaoImp;
import it.pennino.uni.piazzaAffari.clienti.model.Richiesta;
import it.pennino.uni.piazzaAffari.clienti.model.RichiestaDao;
import it.pennino.uni.piazzaAffari.clienti.model.RichiestaDaoImp;
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
		//System.out.println("Annuncio selezionato = "+id_annuncio);
		
		
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
		ModelAndView view;
		
		try{
			if(annuncio.getApprovato().equals("N")){
				aDao.delete(annuncio);
			}else{
				annuncio.setApprovato("N");
				aDao.save(annuncio);
			}
			view = new ModelAndView("view/moderatori/listaAnnunci");
			view.addObject("annuncioOld",annuncio);
			view.addObject("deleteOk",true);
		}catch (Exception e) {
			e.printStackTrace();
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
	
	@RequestMapping(value = {"/moderatori/lista_richieste"} , method = RequestMethod.GET)
	public ModelAndView listaRichieste(){
		ModelAndView view = new ModelAndView("view/moderatori/listaRichieste");
		 
		return view;
	}
	
	
	
	
	
	@RequestMapping(value = {"/moderatori/utente/{id_utente}"} , method = RequestMethod.GET)
	public ModelAndView visualizzaUtente(@PathVariable Integer id_utente){
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
		UserDao uDao = new UserDaoImp();
		User utente = uDao.findById(id_utente);
		utente.setApprovato("Y");
		uDao.save(utente);
		return "redirect:/moderatori/lista_utenti";
	}
	
					
	@RequestMapping( value = {"moderatori/utente/delete/{id_utente}"} ,method = RequestMethod.GET)
	public String cancellaUtente(@PathVariable Integer id_utente){
		ModelAndView view;
		UserDao uDao = new UserDaoImp();
		User utente = null;
		try{
			utente = uDao.findById(id_utente);
			
			if(utente.getApprovato().equals("N")){
				uDao.delete(utente);
			}else{
				utente.setApprovato("N");
				uDao.save(utente);
			}
			view = new ModelAndView("view/moderatori/listaAnnunci");
			view.addObject("utenteOld",utente);
			view.addObject("deleteOk",true);
		}catch (Exception e) {
			e.printStackTrace();
			view = new ModelAndView("view/moderatori/listaAnnunci");
			view.addObject("utenteOld",utente);
			view.addObject("deleteOk",false);
		}
		return "redirect:/moderatori/lista_utenti";
	}
	
	@RequestMapping( value = {"moderatori/richiesta/approva/{id_richiesta}"} ,method = RequestMethod.GET)
	public String approvaRichiesta(@PathVariable Integer id_richiesta){
		RichiestaDao rDao = new RichiestaDaoImp();
		Richiesta richiesta = rDao.findById(id_richiesta);
		richiesta.setApprovato("Y");
		rDao.save(richiesta);
		
		return "redirect:/moderatori/lista_richieste";
	}
	
	@RequestMapping( value = {"moderatori/richiesta/annulla/{id_richiesta}"} ,method = RequestMethod.GET)
	public String approvaAnnull(@PathVariable Integer id_richiesta){
		RichiestaDao rDao = new RichiestaDaoImp();
		Richiesta richiesta = rDao.findById(id_richiesta);
		
		if(richiesta.getApprovato().equals("N")){
			//cancello la richeista
			rDao.delete(richiesta);
		}else{
			richiesta.setApprovato("N");
			rDao.save(richiesta);
		}
		
		
		return "redirect:/moderatori/lista_richieste";
	}
	
	@RequestMapping(value = {"/moderatori/richiesta/{id_richiesta}"} , method = RequestMethod.GET)
	public ModelAndView visualizzaRichiesta(@PathVariable Integer id_richiesta){
		RichiestaDao rDao = new RichiestaDaoImp();
		Richiesta richiesta = rDao.findById(id_richiesta);
		
		ModelAndView view = null;
		
		if(richiesta!=null){
			view = new ModelAndView("view/moderatori/schedaRichiesta");
			view.addObject("richiesta",richiesta);
		}else{
			view = new ModelAndView("view/moderatori/schedaRichiesta-err");
		}
		
		return view;
	}
}
