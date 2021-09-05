package org.ouracademy.exams.auth;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class UserController {

    private SignInService signInService;

    @PostMapping("/login")
    public String login(@RequestBody @Valid SignInService.LoginInput input) {
        return signInService.run(input);
    }

    @PostMapping("/login-username")
    public String loginWithUsername(@RequestBody @Valid SignInService.LoginExternalInput input) {
        return signInService.runExternal(input);
    }

    @GetMapping("/me")
    public Principal user(Principal principal) {
        return principal;
    }
}