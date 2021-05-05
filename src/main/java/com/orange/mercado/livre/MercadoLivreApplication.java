package com.orange.mercado.livre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

//A gente habilita o @EnableSpringDataWebSupport para o spring pegar dos campos da URL e passar para o spring data

@SpringBootApplication
@EnableSpringDataWebSupport
public class MercadoLivreApplication {

	public static void main(String[] args) {
		SpringApplication.run(MercadoLivreApplication.class, args);
	
		/*
		 * COMO DESCOBRIR A HASH DE UMA SENHA BCRYPT
		 * System.out.println(new BCryptPasswordEncoder().encode("123456"));
		 */	
	}

}
