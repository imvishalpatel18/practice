package com.workout.api.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class SecurityContextDTO extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3642702605797475211L;

	private Long userId;

	public SecurityContextDTO(final Long userId, String username, String password, boolean enabled,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, true, true, true, authorities);
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

}
