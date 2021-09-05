package org.ouracademy.exams.auth;

import java.util.Collection;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
public class UserAccount implements UserDetails {
	
	private static final long serialVersionUID = 2474883796077234405L;
	
    @Id
	@EqualsAndHashCode.Include
	private String name;
	
	@JsonIgnore
	private String password;

	@Enumerated(EnumType.STRING)
	@Setter private UserAccountRole role;

	protected UserAccount() {}
	
	protected UserAccount(String name, String password, UserAccountRole role) {
		this.name = name;
		this.password = password;
		this.role = role;
	}
	
	public static UserAccount admin(String name, String password) {
		return new UserAccount(name, password, UserAccountRole.ADMIN);
	}
	public static UserAccount external(String name) {
		return new UserAccount(name, "contraseña", UserAccountRole.EXTERNAL_POSTULANT);
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return role.authorities();
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getId() {
		return this.name;
	}

}
