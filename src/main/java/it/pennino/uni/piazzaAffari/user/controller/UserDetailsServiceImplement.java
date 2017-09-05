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

//it.pennino.uni.piazzaAffari.user.controller.UserDetailsServiceImplement
public class UserDetailsServiceImplement implements UserDetailsService {
	private UserDao userDao = new UserDaoImp();

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("****************************");
		System.out.println("loadUserByUsername = OK");
		
		User user = userDao.findByUserName(username);
		System.out.println("user = " + user);

		List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRuoli());

		return buildUserForAuthentication(user, authorities);
	}

	// Converts com.mkyong.users.model.User user to
	// org.springframework.security.core.userdetails.User
	private UserSession buildUserForAuthentication(it.pennino.uni.piazzaAffari.user.model.User user,
			List<GrantedAuthority> authorities) {
		UserSession usrTmp = new UserSession(user.getEmail(), user.getPassword(), true, true, true, true, authorities);
		usrTmp.setUser(user);
		System.out.println("usrTmp =" + usrTmp);
		System.out.println("usrTmp.password =" + usrTmp.getPassword());
		

		
		return usrTmp;
	}

	private List<GrantedAuthority> buildUserAuthority(Set<UserRuoli> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (UserRuoli userRole : userRoles) {
			System.out.println("RUOLO : " + userRole.getRuolo());
			setAuths.add(new SimpleGrantedAuthority(userRole.getRuolo()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}
}
