package org.ouracademy.exams.auth;

import java.security.Principal;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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

    MessageSource messageSource;

    @PostMapping("/login")
    public String login(@RequestBody @Valid SignInService.LoginInput input) {

        var hola = LocaleContextHolder.getLocale();
        // var hola = Locale.getDefault();
        System.out.println("aaaaa" + hola);
        System.out.println(messageSource.getMessage("welcome.text", new Object[] {}, hola));


        return signInService.run(input);
    }

    @GetMapping
    public Principal user(Principal principal) {
        return principal;
    }
}