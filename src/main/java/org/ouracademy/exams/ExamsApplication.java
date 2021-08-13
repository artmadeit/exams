package org.ouracademy.exams;

import java.time.LocalDateTime;

import org.ouracademy.exams.auth.UserAccount;
import org.ouracademy.exams.auth.UserRepository;
import org.ouracademy.exams.domain.DateTimeRange;
import org.ouracademy.exams.domain.Inscription;
import org.ouracademy.exams.domain.InscriptionRepository;
import org.ouracademy.exams.domain.event.ExamEvent;
import org.ouracademy.exams.domain.event.ExamEventRepository;
import org.ouracademy.exams.domain.postulant.Postulant;
import org.ouracademy.exams.domain.postulant.PostulantRepository;
import org.ouracademy.exams.domain.structure.ExamPartRepository;
import org.ouracademy.exams.domain.structure.ExamTestData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
@EnableJpaAuditing
public class ExamsApplication {


	public static void main(String[] args) {
		SpringApplication.run(ExamsApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
		ExamEventRepository eventRepository,
		ExamPartRepository examPartRepository, 
		PostulantRepository postulantRepository,
		UserRepository userRepository,
		InscriptionRepository inscriptionRepository) {
		
		return args -> {
			var examEvent = eventRepository.save(
				ExamEvent.builder()
					.title("Examen postgrado UNMSM 2021 - II")
					.description("Examen virtual de aptitud para maestría")
					.range(new DateTimeRange(LocalDateTime.of(2021, 11, 28, 16, 0, 0), LocalDateTime.of(2021, 11, 28, 17, 0, 0)))
					.build());

			examPartRepository.save(
				ExamTestData.examen(1)
			);
			examPartRepository.save(
				ExamTestData.examen(2)
			);

			userRepository.save(
				UserAccount.admin("unmsm-admin", "unsuperpasword")
			);

			var arthur = postulantRepository.save(
				Postulant.builder()
				.dni("73646447")
				.code("12123123")
				.lastName("mauricio")
				.motherLastName("delgadillo")
				.firstName("arthur")
				.programCode("1")
				.upgCode("12")
				.build()
			);

			
			inscriptionRepository.save(new Inscription(arthur, examEvent));

			var diana = postulantRepository.save(
				Postulant.builder()
				.dni("48484489")
				.code("12312390")
				.lastName("quintanilla")
				.motherLastName("perez")
				.firstName("diana")
				.programCode("1")
				.upgCode("12")
				.build()
			);
			
			inscriptionRepository.save(new Inscription(diana, examEvent));

			postulantRepository.save(
				Postulant.builder()
				.dni("1111111")
				.code("111111")
				.lastName("sin")
				.motherLastName("inscripcion")
				.firstName("alguien")
				.programCode("1")
				.upgCode("12")
				.build()
			);

		};
	}
}
