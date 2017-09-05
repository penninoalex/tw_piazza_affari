package it.pennino.uni.piazzaAffari.user.controller;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import it.pennino.uni.piazzaAffari.user.model.User;

public class UserSession extends org.springframework.security.core.userdetails.User {
	private User user;
	
	public UserSession(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
