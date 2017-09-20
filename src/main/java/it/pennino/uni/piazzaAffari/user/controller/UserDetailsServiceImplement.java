package it.pennino.uni.piazzaAffari.user.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import it.pennino.uni.piazzaAffari.user.model.User;
import it.pennino.uni.piazzaAffari.user.model.UserDao;
import it.pennino.uni.piazzaAffari.user.model.UserDaoImp;
import it.pennino.uni.piazzaAffari.user.model.UserRuoli;

public class UserDetailsServiceImplement implements UserDetailsService {
	private UserDao userDao = new UserDaoImp();

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUserName(username);
		if(user!=null){
			List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRuoli());
			return buildUserForAuthentication(user, authorities);
		}else{
			throw new UsernameNotFoundException("Utente non trovato");
		}
	}

	private UserSession buildUserForAuthentication(it.pennino.uni.piazzaAffari.user.model.User user,List<GrantedAuthority> authorities) {
		
		boolean abilitato = false;
		if(user.getApprovato().equals("Y"))
			abilitato = true;
		
		UserSession usrTmp = new UserSession(user.getEmail(), user.getPassword(), abilitato, true, true, true, authorities);
		usrTmp.setUser(user);
		return usrTmp;
	}

	private List<GrantedAuthority> buildUserAuthority(Set<UserRuoli> userRoles) {
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		for (UserRuoli userRole : userRoles) {
			//System.out.println("RUOLO : " + userRole.getRuolo());
			setAuths.add(new SimpleGrantedAuthority(userRole.getRuolo()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
		return Result;
	}
}
