package org.ouracademy.exams.auth;

import java.util.Optional;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class RegisterUserService {
    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class UserInput extends SignInService.LoginInput {

        @NotNull
        private final Long personId;

        @NotBlank
        @Email
        private final String email;

        @NotBlank
        @Size(min = 7, max = 15)
        private final String phoneNumber;

        private final String commitmentDocument;

        public UserInput(String name, String password, Long personId, String email, String phoneNumber,
                String commitmentDocument) {
            this.name = name;
            this.password = password;
            this.personId = personId;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.commitmentDocument = commitmentDocument;
        }
    }


    public static class UserNameExistException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public UserNameExistException(String name) {
            super("There is an account with that username" + name);
        }
    }

    private UserRepository repository;
    private PasswordEncoder passwordEncoder;

   
    public UserAccount run(UserInput input) {
        var existUser = verify(input.getName());

        if(existUser.isPresent()) 
            throw new UserNameExistException(input.getName()); 

        log.info("Registering user {}", input.getName());
        var user = repository.save(new UserAccount(
            input.getName(), 
            passwordEncoder.encode(input.getPassword()),
            input.getPersonId(),
            input.getEmail(),
            input.getPhoneNumber(),
            input.getCommitmentDocument(),
            AccountStatus.ASIGNED
        ));
        log.info("Created user {}", user.getName());
        return user;
    }


	public Optional<UserAccount> verify(String name) {
		return repository.findByName(name);
	}

}