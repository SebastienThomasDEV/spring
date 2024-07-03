package com.example.demo.service;

import com.example.demo.entity.Ville;
import com.example.demo.repository.VilleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
    public Ville getVilleById(Long id) {
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
    public void deleteVille(Long id) {
        villeRepository.deleteById(id);
    }


    /**
     * Mettre à jour une ville
     * @param id l'id de la ville à mettre à jour
     * @param updatedVille la ville mise à jour
     * @return Ville la ville mise à jour
     */
    @Transactional
    public Ville updateVille(Long id, Ville updatedVille) {
        if (villeRepository.existsById(id)) {
            updatedVille.setId(id);
            return villeRepository.save(updatedVille);
        } else {
            return null;
        }
    }


}
