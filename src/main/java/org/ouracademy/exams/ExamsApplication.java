package org.ouracademy.exams;

import org.ouracademy.exams.auth.UserAccount;
import org.ouracademy.exams.auth.UserRepository;
import org.ouracademy.exams.domain.postulant.Postulant;
import org.ouracademy.exams.domain.postulant.PostulantRepository;
import org.ouracademy.exams.domain.structure.ExamPartRepository;
import org.ouracademy.exams.domain.structure.ExamTestData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class ExamsApplication {


	public static void main(String[] args) {
		SpringApplication.run(ExamsApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
		ExamPartRepository examPartRepository, 
		PostulantRepository postulantRepository,
		UserRepository userRepository) {
		
		return args -> {
			examPartRepository.save(
				ExamTestData.examen(1)
			);
			examPartRepository.save(
				ExamTestData.examen(2)
			);

			userRepository.save(
				UserAccount.admin("unmsm-admin", "unsuperpasword")
			);

			postulantRepository.save(
				Postulant.builder()
				.code("12123123")
				.dni("73646447")
				.lastName("mauricio")
				.motherLastName("delgadillo")
				.firstName("arthur")
				.programCode("1")
				.upgCode("12")
				.build()
			);

			
			postulantRepository.save(
				Postulant.builder()
				.code("12312390")
				.dni("48484489")
				.lastName("quintanilla")
				.motherLastName("perez")
				.firstName("diana")
				.programCode("1")
				.upgCode("12")
				.build()
			);
		};
	}
}
