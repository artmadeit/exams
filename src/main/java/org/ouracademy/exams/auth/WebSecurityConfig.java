package org.ouracademy.exams.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.ouracademy.exams.auth.jwt.JwtTokenFilter;
import org.ouracademy.exams.auth.jwt.JwtTokenProvider;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()//
		.cors().and()
				.addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()//
				.antMatchers("/", "/browser/**","/auth/**").permitAll()//
				.antMatchers("/actuator/health", "/actuator/info", "/api-docs*", "/api-docs/*","/swagger-ui","/swagger-ui/*", "/swagger-ui.html").permitAll()//                                
				.antMatchers().permitAll()//
				.anyRequest().authenticated();//
				

		// No session will be created or used by spring security
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService(UserRepository userRepository) {
		return new UserDetailsServiceImpl(userRepository);
	}

	@Bean
	public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
		// Spring security & Spring data integration
		// more on:
		// https://docs.spring.io/spring-security/site/docs/5.3.4.RELEASE/reference/html5/#data-configuration
		return new SecurityEvaluationContextExtension();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
