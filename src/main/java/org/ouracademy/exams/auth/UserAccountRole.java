package org.ouracademy.exams.auth;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum UserAccountRole {
	ADMIN, POSTULANT, EXTERNAL_POSTULANT;

	public Collection<GrantedAuthority> authorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_" + this.name()));
	}
}