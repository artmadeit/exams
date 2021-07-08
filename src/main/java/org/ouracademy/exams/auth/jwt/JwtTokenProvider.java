package org.ouracademy.exams.auth.jwt;

import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.ouracademy.exams.auth.AccountStatus;

@Component
public class JwtTokenProvider {

    /**
     * THIS IS NOT A SECURE PRACTICE! For simplicity, we are storing a static key
     * here. Ideally, in a microservices environment, this key would be kept on a
     * config-server.
     */
    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds = 3600000; // 1h

    @Value("${security.jwt.token.prefix:Bearer }")
    private String prefix;

    @Autowired
    private UserDetailsService userDetailService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String username, AccountStatus status, Collection<? extends GrantedAuthority> authorities) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return this.prefix +  Jwts.builder()//
                .setClaims(claims(username, authorities, status))//
                .setIssuedAt(now).setExpiration(validity)//
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    private Claims claims(String username, Collection<? extends GrantedAuthority> authorities, AccountStatus status ) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("auth", authorities.stream().collect(Collectors.toList()));
        claims.put("status", status);
        return claims;
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith(prefix)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new CredentialsExpiredException("Expired or invalid JWT token");
        }
    }

}