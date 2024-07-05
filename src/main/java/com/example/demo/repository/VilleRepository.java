package com.example.demo.repository;

import com.example.demo.entity.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;


/**
 * Repository des villes
 * Ce repository est une interface qui hérite de JpaRepository
 * JpaRepository est une interface de Spring Data JPA qui contient des méthodes pour manipuler les entités
 * Le corps de fonction de ces méthodes est généré automatiquement par Spring Data JPA
 * @Repository permet de dire à Spring que c'est un repository
 */
@Repository
public interface VilleRepository extends JpaRepository<Ville, Integer> {
    /**
     * Récupérer des villes par leur nom
     * @param nom
     * @return List<Ville>
     */
    List<Ville> findByNomIsLike(String nom);
    /**
     * Récupérer des villes par les nombres d'habitants
     * @param nbHabitants
     * @return List<Ville>
     */
    List<Ville> findByNbHabitantsIsGreaterThan(int nbHabitants);
    /**
     * Récupérer des villes par les nombres d'habitants (entre min et max)
     *
     * @param min
     * @param max
     * @return List<Ville>
     */
    List<Ville> findByNbHabitantsBetween(int min, int max);
}