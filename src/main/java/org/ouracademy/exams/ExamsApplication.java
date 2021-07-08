package org.ouracademy.exams;

import org.ouracademy.exams.api.ExamPartRepository;
import org.ouracademy.exams.domain.ExamTestData;
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

	@Bean
	public CommandLineRunner commandLineRunner(ExamPartRepository repository) {
		return args -> {
			repository.save(
				ExamTestData.examen(1)
			);
			repository.save(
				ExamTestData.examen(2)
			);
		};
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			// TODO: configure this
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
			}
		};
	}

}
