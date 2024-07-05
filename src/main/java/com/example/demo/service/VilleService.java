package com.example.demo.service;

import com.example.demo.entity.Ville;
import com.example.demo.repository.VilleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class VilleService {

    /**
     * Repository des villes
     * Ce repository est injecté par Spring dans le service
     * @param villeRepository
     */
    @Autowired
    private VilleRepository villeRepository;


    /**
     * Récupérer la liste des villes
     *
     * @return List<Ville> la liste des villes
     */
    @Transactional
    public List<Ville> getVilles() {
        return villeRepository.findAll();
    }

    /**
     * Récupérer une ville par son id
     * @param id l'id de la ville
     * @return Ville la ville
     */
    @Transactional
    public Ville getVilleById(Integer id) {
        return villeRepository.findById(id).orElse(null);
    }

    /**
     * Ajouter une ville
     * @param ville la ville à ajouter
     * @return Ville la ville ajoutée
     */
    @Transactional
    public Ville addVille(Ville ville) {
        return villeRepository.save(ville);
    }

    /**
     * Supprimer une ville
     * @param id l'id de la ville à supprimer
     */
    @Transactional
    public void deleteVille(Integer id) {
        villeRepository.deleteById(id);
    }


    /**
     * Mettre à jour une ville
     *
     * @param id           l'id de la ville à mettre à jour
     * @param ville la ville à mettre à jour
     */
    @Transactional
    public void updateVille(Integer id, Ville ville) {
        if (villeRepository.existsById(id)) {
            ville.setId(id);
            villeRepository.save(ville);
        } else {
            throw new RuntimeException("Ville non trouvée");
        }
    }


    /**
     * Récupérer les villes dont le nom commence par un préfixe
     * @param prefix le préfixe
     * @return List<Ville> la liste des villes
     */
    @Transactional
    public List<Ville> getVillesByNameStartingWith(@RequestParam String prefix) {
        return villeRepository.findByNomIsLike(prefix);
    }


    /**
     * Récupérer les villes par population
     * @param min la population minimale
     * @return List<Ville> la liste des villes
     */
    @Transactional
    public List<Ville> getVillesByPopulationGreaterThan(@RequestParam int min) {
        return villeRepository.findByNbHabitantsIsGreaterThan(min);
    }

    /**
     * Récupérer les villes par population
     * @param min la population minimale
     * @param max la population maximale
     * @return List<Ville> la liste des villes
     */
    @Transactional
    public List<Ville> getVillesByPopulationBetween(@RequestParam int min, @RequestParam int max) {
        return villeRepository.findByNbHabitantsBetween(min, max);
    }




}
