package com.example.demo.controller;

import com.example.demo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Contrôleur pour dire bonjour
 * Ce contrôleur est accessible via l'URL /hello et toutes les routes définies ici sont préfixées par /hello
 * @RestController permet de définir un contrôleur REST
 * @RequestMapping permet de définir le préfixe de l'URL pour accéder à ce contrôleur
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    /**
     * Service de gestion des salutations
     * Ce service est injecté par Spring dans le contrôleur
     * @param helloService
     */
    @Autowired
    private HelloService helloService;


    /**
     * Dire bonjour
     * @return String le message de salutation
     */
    @GetMapping
    public String direHello(){
        return helloService.salutation();
    }
}