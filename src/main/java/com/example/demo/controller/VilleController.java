package com.example.demo.controller;

import com.example.demo.entity.Ville;
import com.example.demo.service.VilleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleController {

    /**
     * Service de gestion des villes
     * Ce service est injecté par Spring dans le contrôleur
     * @param villeService
     */
    @Autowired
    private VilleService villeService;


    /**
     * Récupérer la liste des villes
     * @return List<Ville> la liste des villes
     */
    @GetMapping
    public List<Ville> getVilles() {
        return villeService.getVilles();
    }

    /**
     * Récupérer une ville par son id
     * @param id l'id de la ville
     * @return Ville la ville
     */
    @GetMapping("/{id}")
    public Ville getVille(@PathVariable Integer id) {
        return villeService.getVilleById(id);
    }


    /**
     * Ajouter une ville
     * @param ville la ville à ajouter
     * @return String message de confirmation
     */
    @PostMapping
    public String addVille(@Valid @RequestBody Ville ville, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors().get(0).getDefaultMessage();
        }
        villeService.addVille(ville);
        return "Ville ajoutée";
    }


    /**
     * Supprimer une ville
     * @param id l'id de la ville à supprimer
     * @return List<Ville> la liste des villes
     */
    @DeleteMapping("/{id}")
    public List<Ville> deleteVille(@PathVariable Integer id) {
        villeService.deleteVille(id);
        return villeService.getVilles();
    }


    /**
     * Mettre à jour une ville
     * @param id l'id de la ville à mettre à jour
     * @param ville la ville mise à jour
     * @return List<Ville> la liste des villes
     */
    @PutMapping("/{id}")
    public List<Ville> updateVille(@PathVariable Integer id, @RequestBody Ville ville) {
        villeService.updateVille(id, ville);
        return villeService.getVilles();
    }


    /**
     * Rechercher les villes par nom
     *
     * @param prefix
     * @return
     */
    @GetMapping("/search")
    public List<Ville> searchVillesLike(@RequestParam String prefix) {
        prefix = "%" + prefix + "%";
        return villeService.getVillesByNameStartingWith(prefix);
    }

    /**
     * Rechercher les villes par population minimale
     * @param min
     * @return
     */
    @GetMapping("/min")
    public List<Ville> searchVillesByMin(@RequestParam int min) {
        return villeService.getVillesByPopulationGreaterThan(min);
    }

    /**
     * Rechercher les villes par population minimale et maximale
     * @param min
     * @param max
     * @return
     */
    @GetMapping("/between")
    public List<Ville> searchVillesBetween(@RequestParam int min, @RequestParam int max) {
        return villeService.getVillesByPopulationBetween(min, max);
    }
}