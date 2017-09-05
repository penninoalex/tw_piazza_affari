package it.pennino.uni.piazzaAffari.cms.controller;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.pennino.uni.piazzaAffari.cms.model.Pagine;
import it.pennino.uni.piazzaAffari.cms.model.PagineDao;
import it.pennino.uni.piazzaAffari.cms.model.PagineDaoImp;

@Controller
public class CmsController {

	@RequestMapping( value = {"/cms/{url}"} ,method = RequestMethod.GET)
	public ModelAndView cmsController(@PathVariable String url, HttpServletRequest request){
		ModelAndView model = new ModelAndView("articoloSingolo");
		
		PagineDao pDao = new PagineDaoImp();
		Pagine pagina = pDao.findByUrl(url);
		
		model.addObject("titolo", pagina.getTitolo());
		model.addObject("contenuto", pagina.getContenuto().replace("${pageContext.request.contextPath}", request.getContextPath()));
		
		return model;
	}
}
