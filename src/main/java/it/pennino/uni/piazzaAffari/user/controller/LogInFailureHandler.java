package it.pennino.uni.piazzaAffari.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class LogInFailureHandler implements AuthenticationFailureHandler {

	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		 System.out.println("authentication = "+exception);
		if(exception instanceof BadCredentialsException){
			System.out.println("Username o password errati.");
			response.sendRedirect("accedi?errore=Username o password errati.");
		}else if(exception instanceof DisabledException){
			System.out.println("Utente disabilitato");
			response.sendRedirect("accedi?errore=Utente non abilitato.");
		}
		
			
	}

}
