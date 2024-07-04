package com.example.demo.controller;


import com.example.demo.entity.Departement;
import com.example.demo.entity.Ville;
import com.example.demo.service.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Contrôleur des départements
 */
@RestController
@RequestMapping("/departements")
public class DepartementController {


    /**
     * Service de gestion des départements
     * Ce service est injecté par Spring dans le contrôleur
     * @param departementService
     */
    @Autowired
    private DepartementService departementService;


    /**
     * Récupérer la liste des départements
     * @return List<Departement> la liste des départements
     */
    @GetMapping()
    public List<Departement> getDepartements() {
        return departementService.getDepartements();
    }


    /**
     * Récupérer un département par son id
     * @param id l'id du département
     * @return Departement le département
     */
    @GetMapping("/{id}")
    public Departement getDepartement(@PathVariable long id) {
        return departementService.getDepartementById(id);
    }


    /**
     * Ajouter un département
     * @param departement le département à ajouter
     * @return String message de confirmation
     */
    @PostMapping
    public String addDepartement(@RequestBody Departement departement) {
        departementService.addDepartement(departement);
        return "Departement ajouté";
    }


    /**
     * Supprimer un département
     * @param id l'id du département à supprimer
     * @return List<Departement> la liste des départements
     */
    @DeleteMapping("/{id}")
    public List<Departement> deleteDepartement(@PathVariable long id) {
        departementService.deleteDepartement(id);
        return departementService.getDepartements();
    }


    /**
     * Mettre à jour un département
     * @param departement le département à mettre à jour
     * @return Departement le département mis à jour
     */
    @PutMapping
    public Departement updateDepartement(@RequestBody Departement departement) {
        return departementService.updateDepartement(departement);
    }


    /**
     * Récupérer la liste des villes d'un département
     * @param id l'id du département
     * @return List<Ville> la liste des villes
     */
    @GetMapping("/{id}/villes")
    public List<Ville> getVilles(@PathVariable long id) {
        return departementService.getVilles(id);
    }


    /**
     * Récupérer une ville d'un département
     * @param id l'id du département
     * @param villeId l'id de la ville
     * @return
     */
    @GetMapping("/{id}/villes/{villeId}")
    public Ville getVille(@PathVariable long id, @PathVariable long villeId) {
        return departementService.getVille(id, villeId);
    }


    /**
     * Ajouter une ville à un département
     * @param id l'id du département
     * @param ville la ville à ajouter
     * @return
     */
    @PostMapping("/{id}/villes")
    public String addVille(@PathVariable long id, @RequestBody Ville ville) {
        departementService.addVille(id, ville);
        return "Ville ajoutée";
    }

    /**
     * Supprimer une ville d'un département
     * @param id l'id du département
     * @param villeId l'id de la ville
     * @return
     */
    @DeleteMapping("/{id}/villes/{villeId}")
    public List<Ville> deleteVille(@PathVariable long id, @PathVariable long villeId) {
        departementService.deleteVille(id, villeId);
        return departementService.getVilles(id);
    }


    /**
     * Mettre à jour une ville d'un département
     * @param id l'id du département
     * @param villeId l'id de la ville
     * @param ville la ville à mettre à jour
     * @return
     */
    @PutMapping("/{id}/villes/{villeId}")
    public List<Ville> updateVille(@PathVariable long id, @PathVariable long villeId, @RequestBody Ville ville) {
        departementService.updateVille(id, villeId, ville);
        return departementService.getVilles(id);
    }

    /**
     * Récupérer la liste des villes d'un département triées par nombre d'habitants
     * @param id l'id du département
     * @return List<Ville> la liste des villes
     */
    @GetMapping("/{id}/villes/most_populated")
    public List<Ville> getVillesSortedByNbHabitants(@PathVariable long id) {
        return departementService.getVillesSortedByNbHabitants(id);
    }


    /**
     * Récupérer la liste des villes d'un département triées par ordre croissant du nombre d'habitants
     * @param id l'id du département
     * @return List<Ville> la liste des villes
     */
    @GetMapping("/{id}/villes/range")
    public List<Ville> getVillesByNbHabitantsRange(@PathVariable long id, @RequestParam int min, @RequestParam int max) {
        if (min > max) throw new IllegalArgumentException("Le nombre minimum d'habitants doit être inférieur au nombre maximum");
        return departementService.getVillesByNbHabitantsRange(id, min, max);
    }
}
