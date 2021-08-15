package org.ouracademy.exams;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${cors_origin}")
	private String corsOrigin;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins(corsOrigin)
            .allowedMethods("*");
    }

    // @Override
    // public void addInterceptors(InterceptorRegistry registry) {
    //     registry.addInterceptor(localeChangeInterceptor());
    // }

    /**
     * Source: 
     * https://lokalise.com/blog/spring-boot-internationalization/
     * https://www.baeldung.com/spring-boot-internationalization
     */
    // @Bean
	// public LocaleResolver localeResolver() {
	// 	var slr = new AcceptHeaderLocaleResolver();
	// 	slr.setDefaultLocale(Locale.forLanguageTag("es"));
	// 	return slr;
	// }

	// @Bean
	// public LocaleChangeInterceptor localeChangeInterceptor() {
	// 	var lci = new LocaleChangeInterceptor();
	// 	lci.setParamName("lang");
	// 	return lci;
	// }
}
