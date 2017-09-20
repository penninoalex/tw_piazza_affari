package it.pennino.uni.piazzaAffari;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import it.pennino.uni.piazzaAffari.annuncio.model.Annuncio;
import it.pennino.uni.piazzaAffari.annuncio.model.AnnuncioDao;
import it.pennino.uni.piazzaAffari.annuncio.model.AnnuncioDaoImp;
import it.pennino.uni.piazzaAffari.categoria.model.Categoria;
import it.pennino.uni.piazzaAffari.categoria.model.CategoriaDaoImp;

@Controller 
public class BaseController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("index");
		model.addObject("titolo", "Index");
		
		return model;
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView model = new ModelAndView("index");
		model.addObject("titolo", "Index");
		
		return model;
	}
	
	@RequestMapping(value = "/blog", method = RequestMethod.GET) 
	public ModelAndView blog(){
		
		ModelAndView model = new ModelAndView("articoloSingolo");
		model.addObject("titolo", "Blog");
		
		return model;
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/home";
	}
	
	
	@RequestMapping(value = "/ricerca", method = RequestMethod.GET) 
	public String listaAnnunciOspiteGet(){
			return "redirect:/home";
	}
	
	@RequestMapping(value = "/ricerca", method = RequestMethod.POST) 
	public ModelAndView listaAnnunciOspite(
			@RequestParam String categoria,
			@RequestParam Integer comune){
		Categoria cat = (new CategoriaDaoImp()).findById(categoria);
		
		AnnuncioDao aDao = new AnnuncioDaoImp();
		ArrayList<Annuncio> annunci = aDao.findByCategoriaCitta(cat, comune);
		
		
		ModelAndView model = new ModelAndView("ricerca");
		model.addObject("titolo", "Ricerca annuncio");
		model.addObject("listaAnnunci", annunci);
		return model;
	}
	
}
