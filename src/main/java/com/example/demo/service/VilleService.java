package com.example.demo.service;

import com.example.demo.ApiException;
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
    public List<Ville> getVilles() throws ApiException {
        try {
            return villeRepository.findAll();
        } catch (Exception e) {
            throw new ApiException("Impossible de récupérer les villes");
        }
    }

    /**
     * Récupérer une ville par son id
     * @param id l'id de la ville
     * @return Ville la ville
     */
    @Transactional
    public Ville getVilleById(Integer id) throws ApiException {
        try {
            return villeRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new ApiException("Impossible de récupérer la ville");
        }
    }

    /**
     * Ajouter une ville
     * @param ville la ville à ajouter
     * @return Ville la ville ajoutée
     */
    @Transactional
    public Ville addVille(Ville ville) throws ApiException {
        try {
            return villeRepository.save(ville);
        } catch (Exception e) {
            throw new ApiException("Impossible d'ajouter la ville");
        }
    }
    /**
     * Supprimer une ville
     * @param id l'id de la ville à supprimer
     */
    @Transactional
    public void deleteVille(Integer id) throws ApiException {
        try {
            villeRepository.deleteById(id);
        } catch (Exception e) {
            throw new ApiException("Impossible de supprimer la ville");
        }
    }


    /**
     * Mettre à jour une ville
     *
     * @param id           l'id de la ville à mettre à jour
     * @param ville la ville à mettre à jour
     */
    @Transactional
    public void updateVille(Integer id, Ville ville) throws ApiException {
        try {
            Ville villeToUpdate = villeRepository.findById(id).orElse(null);
            if (villeToUpdate != null) {
                villeToUpdate.setNom(ville.getNom());
                villeToUpdate.setNbHabitants(ville.getNbHabitants());
                villeRepository.save(villeToUpdate);
            }
        } catch (Exception e) {
            throw new ApiException("Impossible de mettre à jour la ville");
        }
    }

    /**
     * Récupérer les villes dont le nom commence par un préfixe
     * @param prefix le préfixe
     * @return List<Ville> la liste des villes
     */
    @Transactional
    public List<Ville> getVillesByNameStartingWith(@RequestParam String prefix) throws ApiException {
        try {
            return villeRepository.findByNomIsLike(prefix);
        } catch (Exception e) {
            throw new ApiException("Impossible de récupérer les villes");
        }
    }

    /**
     * Récupérer les villes par population
     * @param min la population minimale
     * @return List<Ville> la liste des villes
     */
    @Transactional
    public List<Ville> getVillesByPopulationGreaterThan(@RequestParam int min) throws ApiException {
        try {
            return villeRepository.findByNbHabitantsIsGreaterThan(min);
        } catch (Exception e) {
            throw new ApiException("Impossible de récupérer les villes");
        }
    }
    /**
     * Récupérer les villes par population
     * @param min la population minimale
     * @param max la population maximale
     * @return List<Ville> la liste des villes
     */
    @Transactional
    public List<Ville> getVillesByPopulationBetween(@RequestParam int min, @RequestParam int max) throws ApiException {
        try {
            return villeRepository.findByNbHabitantsBetween(min, max);
        } catch (Exception e) {
            throw new ApiException("Impossible de récupérer les villes");
        }
    }

    /**
     * Exporter les villes en CSV
     *
     * @return String le contenu du fichier CSV
     */
    public String exportVillesToCSV() throws ApiException {
        try {
            List<Ville> villes = villeRepository.findAll();
            StringBuilder csv = new StringBuilder();
            csv.append("id,nom,nbHabitants\n");
            for (Ville ville : villes) {
                csv.append(ville.getId()).append(",").append(ville.getNom()).append(",").append(ville.getNbHabitants()).append("\n");
            }
            return csv.toString();
        } catch (Exception e) {
            throw new ApiException("Impossible d'exporter les villes");
        }
    }



}
