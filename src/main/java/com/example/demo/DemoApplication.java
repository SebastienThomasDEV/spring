package com.example.demo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Classe principale de l'application
 * Cette classe est annotée avec @SpringBootApplication
 * l'appel à run() permet de lancer l'application et de démarrer le serveur Tomcat
 * Spring Boot va scanner les classes du package courant et des sous-packages pour trouver les classes annotées avec
 * "@Component, @Service, @Repository, @Controller, @RestController, @Configuration, @Bean" et les enregistrer dans le contexte de l'application
 */
@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
    }

}
