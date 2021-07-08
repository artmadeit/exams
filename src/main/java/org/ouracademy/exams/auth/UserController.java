package org.ouracademy.exams.auth;

import java.security.Principal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.ouracademy.exams.auth.RegisterUserService.UserNameExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class UserController {

    private RegisterUserService registerService;
    private SignInService signInService;

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNameExistException.class)
    public void registerException() {
        // do nothing
    }

    @PostMapping("/register")
    public UserDetails register(@RequestBody @Valid RegisterUserService.UserInput input) {
        return this.registerService.run(input);
    }

    @GetMapping("/verify")
    public ResponseEntity<UserAccount> verify(@RequestParam @NotBlank String name) {
        return ResponseEntity.of(this.registerService.verify(name));
    }

    @PostMapping("/login")
    public String login(@RequestBody @Valid SignInService.LoginInput input) {
        return signInService.run(input);
    }

    @GetMapping
    public Principal user(Principal principal) {
        return principal;
    }
}