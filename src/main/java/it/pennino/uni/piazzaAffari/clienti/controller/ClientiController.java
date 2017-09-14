package it.pennino.uni.piazzaAffari.clienti.controller;

import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.pennino.uni.piazzaAffari.annuncio.model.Annuncio;
import it.pennino.uni.piazzaAffari.annuncio.model.AnnuncioDao;
import it.pennino.uni.piazzaAffari.annuncio.model.AnnuncioDaoImp;
import it.pennino.uni.piazzaAffari.categoria.model.Categoria;
import it.pennino.uni.piazzaAffari.categoria.model.CategoriaDao;
import it.pennino.uni.piazzaAffari.categoria.model.CategoriaDaoImp;
import it.pennino.uni.piazzaAffari.clienti.model.Richiesta;
import it.pennino.uni.piazzaAffari.clienti.model.RichiestaDao;
import it.pennino.uni.piazzaAffari.clienti.model.RichiestaDaoImp;
import it.pennino.uni.piazzaAffari.user.controller.UserSession;

@Controller
public class ClientiController {
	
	@RequestMapping(value = {"/clienti**","/clienti/home" }, method = RequestMethod.GET)
	public ModelAndView homePage() {

		ModelAndView model = new ModelAndView();
		model.setViewName("view/clienti/index");

		return model;
	}
	
	@RequestMapping(value = {"/clienti/nuova_richiesta" }, method = RequestMethod.GET)
	public ModelAndView nuovaRichiesta() {

		ModelAndView model = new ModelAndView();
		model.setViewName("view/clienti/nuovaRichiesta");

		return model;
	}
	
	
	@RequestMapping(value = {"/clienti/salvaNuovaRichiesta" }, method = RequestMethod.POST)
	public ModelAndView salvaNuovaRichiesta(@RequestParam("idRichiesta")Integer idRichiesta,
											@RequestParam("selCat")String categoria,
											@RequestParam("titolo")String titolo,
											@RequestParam("descrizione")String descrizione) {
		
		
		System.out.println("idRichiesta = "+idRichiesta);
		System.out.println("categoria = "+categoria);
		System.out.println("titolo = "+titolo);
		System.out.println("descrizione = "+descrizione);
		

		ModelAndView model = new ModelAndView();
		try{
			UserSession userSession = (UserSession)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
			RichiestaDao rDao = new RichiestaDaoImp();
			Richiesta richiesta = new Richiesta();
			richiesta.setIdRichiesta(idRichiesta);
			richiesta.setCategoria(categoria);
			richiesta.setTitolo(titolo);
			richiesta.setDescrizione(descrizione);;
			richiesta.setUser(userSession.getUser());
			
			rDao.save(richiesta);
			
			model = new ModelAndView("redirect:/clienti/lista_richieste/ins-ok");
		}catch (Exception e) {
			e.printStackTrace();
			model = new ModelAndView("view/clienti/nuova_richiesta/errore");
		}
		return model;
	}
	
	@RequestMapping(value = {"/clienti/lista_richieste","/clienti/lista_richieste/{msg}"}, method = RequestMethod.GET)
	public ModelAndView listaAnnunci(@PathVariable Optional<String> msg) {
		
		ModelAndView model = new ModelAndView();
		model.setViewName("view/clienti/listaRichieste");
		if(msg!=null && msg.isPresent()){
			System.out.println("msg = "+msg.get());
			model.addObject("msgStr",msg.get());
		}
		return model;
	}
	
	@RequestMapping( value = {"/clienti/richiesta/edit/{id_richiesta}"} ,method = RequestMethod.GET)
	public ModelAndView richiestaEdit(@PathVariable Integer id_richiesta){
		RichiestaDao rDao = new RichiestaDaoImp();
		Richiesta richiesta = rDao.findById(id_richiesta);
		
		ModelAndView model = new ModelAndView();
		model.setViewName("view/clienti/nuovaRichiesta");
		model.addObject("richiesta",richiesta);
		
		return model;
	}
	
	@RequestMapping( value = {"/clienti/richiesta/delete/{id_richiesta}"} ,method = RequestMethod.GET)
	public String annuncioDelete(@PathVariable Integer id_richiesta){
		RichiestaDao rDao = new RichiestaDaoImp();
		Richiesta richiesta = rDao.findById(id_richiesta);
		
		if(richiesta!=null){
			rDao.delete(richiesta);
		}
		
		return "redirect:/clienti/lista_richieste/del-ok";
	}
	
	@RequestMapping( value = {"/clienti/richiesta/risposte/{id_richiesta}"} ,method = RequestMethod.GET)
	public ModelAndView richiestaRisposte(@PathVariable Integer id_richiesta){
		RichiestaDao rDao = new RichiestaDaoImp();
		Richiesta richiesta = rDao.findById(id_richiesta);
		
		ModelAndView model = new ModelAndView();
		model.setViewName("view/clienti/risposta");
		model.addObject("richiesta",richiesta);
		
		return model;
	}

}
