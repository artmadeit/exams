package org.ouracademy.exams;

import org.ouracademy.exams.domain.postulant.Postulant;
import org.ouracademy.exams.domain.postulant.PostulantRepository;
import org.ouracademy.exams.domain.structure.ExamPartRepository;
import org.ouracademy.exams.domain.structure.ExamTestData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ExamsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamsApplication.class, args);
	}

	@Value("${cors_origin}")
	private String corsOrigin;

	@Bean
	public CommandLineRunner commandLineRunner(ExamPartRepository examPartRepository, PostulantRepository postulantRepository) {
		return args -> {
			examPartRepository.save(
				ExamTestData.examen(1)
			);
			examPartRepository.save(
				ExamTestData.examen(2)
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

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
					.allowedOrigins(corsOrigin)
					.allowedMethods("*");
			}
		};
	}

}
