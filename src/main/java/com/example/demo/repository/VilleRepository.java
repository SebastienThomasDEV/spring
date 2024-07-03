package com.example.demo.repository;

import com.example.demo.entity.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Repository des villes
 * Ce repository est une interface qui hérite de JpaRepository
 * JpaRepository est une interface de Spring Data JPA qui contient des méthodes pour manipuler les entités
 * @Repository permet de dire à Spring que c'est un repository
 */
@Repository
public interface VilleRepository extends JpaRepository<Ville, Long> {}