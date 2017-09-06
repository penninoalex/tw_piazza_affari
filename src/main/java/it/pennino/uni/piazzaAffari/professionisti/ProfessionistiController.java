package it.pennino.uni.piazzaAffari.professionisti;

import java.math.BigDecimal;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import it.pennino.uni.piazzaAffari.user.controller.UserSession;

@Controller
public class ProfessionistiController {
	
	
	
	@RequestMapping(value = {"/professionisti**","/professionisti/home" ,"/professionisti/pubblica_annuncio"}, method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.setViewName("view/professionisti/index");

		return model;
	}
	
	@RequestMapping(value = {"/professionisti/lista_annunci","/professionisti/lista_annunci/{msg}"}, method = RequestMethod.GET)
	public ModelAndView listaAnnunci(@PathVariable Optional<String> msg) {
		//del-ok
		System.out.println("msg = "+msg);
		ModelAndView model = new ModelAndView();
		model.setViewName("view/professionisti/listaAnnunci");
		if(msg!=null && msg.isPresent()){
			System.out.println("msg = "+msg.get());
			model.addObject("msgStr",msg.get());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/professionisti/pubblica_annuncio", method = RequestMethod.POST)
	public ModelAndView pubblicaAnnncio(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("titolo") String titolo,
			@RequestParam("descrizione") String descrizione,
			@RequestParam("prezzo") BigDecimal prezzo,
			@RequestParam("tipoPrezzo") String tipoPrezzo,
			@RequestParam("selCat") String categoria,
			@RequestParam("idAnnuncio") Integer idAnnuncio) {
		
		System.out.println("pubblicaAnnncio");
		System.out.println("idAnnuncio="+idAnnuncio);
		System.out.println("titol="+titolo);
		System.out.println("descrizione="+descrizione);
		System.out.println("prezzo="+prezzo);
		System.out.println("tipoPrezzo="+tipoPrezzo);
		System.out.println("categoria="+categoria);
		
		ModelAndView model = new ModelAndView();
		
		try{
			//Recupero la categoria
			CategoriaDao cDao = new CategoriaDaoImp();
			Categoria cat = cDao.findById(categoria);
			
			//Recupero l'utente corrente
			UserSession userSession = (UserSession)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
			Annuncio annuncio = new Annuncio();
			
			if(idAnnuncio!=null)
				annuncio.setIdAnnuncio(idAnnuncio);
			
			annuncio.setUsers(userSession.getUser());
			annuncio.setCategoria(cat);
			annuncio.setTitolo(titolo);
			annuncio.setDescrizione(descrizione);
			annuncio.setPrezzo(prezzo);
			annuncio.setTipoPrezzo(tipoPrezzo);
			
			AnnuncioDao aDao = new AnnuncioDaoImp();
			aDao.save(annuncio);
			
			model = new ModelAndView("redirect:/professionisti/lista_annunci/ins-ok");
		}catch (Exception e) {
			e.printStackTrace();
			model = new ModelAndView("view/professionisti/annuncioErrore");
		}
		
		return model;
	}
	
	
	@RequestMapping( value = {"/professionisti/annuncio/edit/{id_annuncio}"} ,method = RequestMethod.GET)
	public ModelAndView annuncioEdit(@PathVariable Integer id_annuncio){
		AnnuncioDao aDao = new AnnuncioDaoImp();
		Annuncio annuncio = aDao.findById(id_annuncio);
		
		ModelAndView model = new ModelAndView();
		model.setViewName("view/professionisti/index");
		model.addObject("annuncio",annuncio);
		
		return model;
	}
	
	@RequestMapping( value = {"/professionisti/annuncio/delete/{id_annuncio}"} ,method = RequestMethod.GET)
	public String annuncioDelete(@PathVariable Integer id_annuncio){
		AnnuncioDao aDao = new AnnuncioDaoImp();
		Annuncio annuncio = aDao.findById(id_annuncio);
		
		if(annuncio!=null){
			aDao.delete(annuncio);
		}
		
		return "redirect:/professionisti/lista_annunci/del-ok";
	}
}
