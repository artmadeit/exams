package org.ouracademy.exams.auth;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.ouracademy.exams.auth.jwt.JwtTokenProvider;

@AllArgsConstructor
@Service
public class SignInService {

    @Data
    public static class LoginInput {
        @NotBlank
        protected String name;

        @NotBlank
        protected String password;
    }

    private UserRepository repository;
    private JwtTokenProvider jwtTokenProvider;
    private AuthenticationManager authenticationManager;

    public String run(LoginInput input) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(input.name, input.password));
        var user = repository.findByName(input.name).get();
        return jwtTokenProvider.createToken(input.name, user.getStatus(), user.getAuthorities());
    }
}