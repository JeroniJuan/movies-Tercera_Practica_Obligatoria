package com.esliceu.movies;

import com.esliceu.movies.filters.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MoviesApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(MoviesApplication.class, args);
	}

	@Autowired
	LoginInterceptor loginInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(
				loginInterceptor
		).addPathPatterns("/request", "/createCountry", "/deleteCountry/", "/createLanguage", "/deleteLanguage/",
				"/createLanguageRole", "/deleteLanguageRole/", "/createGenre", "/deleteGenre/", "/deleteMovieCompany/",
				"/createMovieCompany", "/deleteProductionCompany/", "/createProductionCompany/", "/createPerson",
				"/deletePerson/", "/deleteGender/", "/createGender", "/manageRequests", "/checkPermissions");
	}
}
