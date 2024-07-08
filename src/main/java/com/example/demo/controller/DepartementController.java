package com.example.demo.controller;

import com.example.demo.ApiException;
import com.example.demo.entity.Departement;
import com.example.demo.entity.Ville;
import com.example.demo.service.DepartementService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;


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
    public ResponseEntity<List<Departement>> getDepartements() throws ApiException {
        return ResponseEntity.ok(departementService.getDepartements());
    }


    /**
     * Récupérer un département par son id
     * @param id l'id du département
     * @return Departement le département
     */
    @GetMapping("/{id}")
    public ResponseEntity<Departement> getDepartement(@PathVariable long id) throws ApiException {
        return ResponseEntity.ok(departementService.getDepartementById(id));
    }


    /**
     * Ajouter un département
     * @param departement le département à ajouter
     * @return String message de confirmation
     */
    @PostMapping
    public ResponseEntity<String> addDepartement(@RequestBody Departement departement) throws ApiException {
        departementService.addDepartement(departement);
        return ResponseEntity.ok("Département ajouté");
    }


    /**
     * Supprimer un département
     * @param id l'id du département à supprimer
     * @return List<Departement> la liste des départements
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<List<Departement>> deleteDepartement(@PathVariable long id) throws ApiException {
        departementService.deleteDepartement(id);
        return ResponseEntity.ok(departementService.getDepartements());
    }


    /**
     * Mettre à jour un département
     * @param departement le département à mettre à jour
     * @return Departement le département mis à jour
     */
    @PutMapping
    public ResponseEntity<Departement> updateDepartement(@RequestBody Departement departement) throws ApiException {
        return ResponseEntity.ok(departementService.updateDepartement(departement));
    }

    /**
     * Récupérer la liste des villes d'un département
     * @param id l'id du département
     * @return List<Ville> la liste des villes
     */
    @GetMapping("/{id}/villes")
    public ResponseEntity<List<Ville>> getVilles(@PathVariable long id) throws ApiException {
        return ResponseEntity.ok(departementService.getVilles(id));
    }

    /**
     * Récupérer une ville d'un département
     * @param id l'id du département
     * @param villeId l'id de la ville
     * @return
     */
    @GetMapping("/{id}/villes/{villeId}")
    public ResponseEntity<Ville> getVille(@PathVariable long id, @PathVariable long villeId) throws ApiException {
        return ResponseEntity.ok(departementService.getVille(id, villeId));
    }

    /**
     * Ajouter une ville à un département
     * @param id l'id du département
     * @param ville la ville à ajouter
     * @return
     */
    @PostMapping("/{id}/villes")
    public ResponseEntity<List<Ville>> addVille(@PathVariable long id, @RequestBody Ville ville) throws ApiException {
        departementService.addVille(id, ville);
        return ResponseEntity.ok(departementService.getVilles(id));
    }


    /**
     * Supprimer une ville d'un département
     * @param id l'id du département
     * @param villeId l'id de la ville
     * @return
     */
    @DeleteMapping("/{id}/villes/{villeId}")
    public ResponseEntity<List<Ville>> deleteVille(@PathVariable long id, @PathVariable long villeId) throws ApiException {
        departementService.deleteVille(id, villeId);
        return ResponseEntity.ok(departementService.getVilles(id));
    }

    /**
     * Mettre à jour une ville d'un département
     * @param id l'id du département
     * @param villeId l'id de la ville
     * @param ville la ville à mettre à jour
     * @return
     */
    @PutMapping("/{id}/villes/{villeId}")
    public ResponseEntity<List<Ville>> updateVille(@PathVariable long id, @PathVariable long villeId, @RequestBody Ville ville ) throws ApiException  {
        departementService.updateVille(id, villeId, ville);
        return ResponseEntity.ok(departementService.getVilles(id));
    }

    /**
     * Récupérer les villes d'un département par ordre croissant de nombre d'habitants
     * @param id l'id du département
     * @return List<Ville> la liste des villes
     */
    @GetMapping("/{id}/villes/desc")
    public ResponseEntity<List<Ville>> getVillesDesc(@PathVariable long id) throws ApiException {
        Departement departement = getDepartement(id).getBody();
        assert departement != null;
        return ResponseEntity.ok(departement.getVilles().stream().sorted((v1, v2) -> v2.getNbHabitants() - v1.getNbHabitants()).collect(Collectors.toList()));
    }

    /**
     * Récupérer les villes d'un département triées par un maximun et un minimum de nombre d'habitants
     *
     * @param id l'id du département
     * @param min le minimum de nombre d'habitants
     * @param max le maximum de nombre d'habitants
     * @return List<Ville> la liste des villes
     */
    @GetMapping("/{id}/villes/between")
    public ResponseEntity<List<Ville>> getVillesBetween(@PathVariable long id, @RequestParam int min, @RequestParam int max) throws ApiException {
        Departement departement = getDepartement(id).getBody();
        assert departement != null;
        return ResponseEntity.ok(departement.getVilles().stream().filter(v -> v.getNbHabitants() >= min && v.getNbHabitants() <= max).collect(Collectors.toList()));
    }


    /**
     * Exporter les département en CSV
     *
     * @return
     */
    @GetMapping("/export")
    public String exportVilles(HttpServletResponse response) throws ApiException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"departements.csv\"");
        return departementService.exportDepartementsToCSV();
    }


}
