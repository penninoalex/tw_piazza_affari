package it.pennino.uni.piazzaAffari.user.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

//it.pennino.uni.piazzaAffari.user.controller.MySuccessHandler
public class MySuccessHandler implements AuthenticationSuccessHandler {

	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
	
		Set<String> ruoli = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		
		if (ruoli.contains("ROLE_ADMIN")) {
			response.sendRedirect("admin/home");
			return;
		}else if (ruoli.contains("ROLE_MODERATORE")) {
			response.sendRedirect("moderatori/home");
			return;
		}else if (ruoli.contains("ROLE_PROFESSIONISTA")) {
			response.sendRedirect("professionisti/home");
			return;
		}
		
		
		response.sendRedirect("home");
	}

}
