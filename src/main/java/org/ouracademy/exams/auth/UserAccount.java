package org.ouracademy.exams.auth;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;


@Entity
@Data
public class UserAccount implements UserDetails {

	private static final long serialVersionUID = 2474883796077234405L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * In person dni: 7364646447 or passport: AB1234567
	 * In procuradoria PROC-R01
	 */
	@Column(unique=true)
	private String name;
	
	@JsonIgnore
	private String password;
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
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

}
