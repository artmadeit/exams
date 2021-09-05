package org.ouracademy.exams.auth;

import javax.validation.constraints.NotBlank;

import org.ouracademy.exams.auth.jwt.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;

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

    @Data
    public static class LoginExternalInput {
        @NotBlank
        protected String username;
    }

    private JwtTokenProvider jwtTokenProvider;
    private AuthenticationManager authenticationManager;

    public String run(LoginInput input) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(input.name, input.password));
        return jwtTokenProvider.createToken(input.name, authentication.getAuthorities());
    }
    private UserRepository userRepository;

    public String runExternal(LoginExternalInput input) {
        //TODO: verify is unique

        var account = UserAccount.external(input.username);
        userRepository.save(account);

        Authentication authentication = authenticationManager
        .authenticate(
            new UsernamePasswordAuthenticationToken(input.username, "contraseña"));
        return jwtTokenProvider.createToken(input.username, authentication.getAuthorities());
    }
}