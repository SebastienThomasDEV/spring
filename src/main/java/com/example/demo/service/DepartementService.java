package com.example.demo.service;


import com.example.demo.entity.Departement;
import com.example.demo.entity.Ville;
import com.example.demo.repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service de gestion des départements
 */
@Service
public class DepartementService {


    /**
     * Repository des départements
     * Ce repository est injecté par Spring dans le service
     *
     * @param DepartementRepository
     */
    @Autowired
    DepartementRepository DepartementRepository;

    /**
     * Récupérer la liste des départements
     *
     * @return List<Departement> la liste des départements
     */
    public List<Departement> getDepartements() {
        return DepartementRepository.findAll();
    }


    /**
     * Récupérer un département par son id
     *
     * @param id l'id du département
     * @return Departement le département
     */
    public Departement getDepartementById(Long id) {
        return DepartementRepository.findById(id).orElse(null);
    }

    /**
     * Ajouter un département
     *
     * @param Departement le département à ajouter
     * @return Departement le département ajouté
     */
    public Departement addDepartement(Departement Departement) {
        return DepartementRepository.save(Departement);
    }

    /**
     * Supprimer un département
     *
     * @param id l'id du département à supprimer
     */
    public void deleteDepartement(Long id) {
        DepartementRepository.deleteById(id);
    }

    /**
     * Mettre à jour un département
     *
     * @param Departement le département à mettre à jour
     * @return Departement le département mis à jour
     */

    public Departement updateDepartement(Departement Departement) {
        return DepartementRepository.save(Departement);
    }


    /**
     * Récupérer la liste des villes d'un département
     *
     * @param id l'id du département
     * @return List<Ville> la liste des villes
     */
    public List<Ville> getVilles(Long id) {
        Departement departement = DepartementRepository.findById(id).orElse(null);
        if (departement != null) {
            return departement.getVilles();
        }
        return null;
    }


    /**
     * Récupérer une ville d'un département par son id
     *
     * @param id      l'id du département
     * @param idVille l'id de la ville
     * @return Ville la ville
     */
    public Ville getVille(Long id, Long idVille) {
        Departement departement = DepartementRepository.findById(id).orElse(null);
        if (departement != null) {
            return departement.getVilles().stream().filter(v -> v.getId() == idVille).findFirst().orElse(null);
        }
        return null;
    }


    /**
     * Ajouter une ville à un département
     *
     * @param id    l'id du département
     * @param ville la ville à ajouter
     * @return List<Ville> la liste des villes
     */
    public List<Ville> addVille(Long id, Ville ville) {
        Departement departement = DepartementRepository.findById(id).orElse(null);
        if (departement != null) {
            departement.addVille(ville);
            DepartementRepository.save(departement);
            return departement.getVilles();
        }
        return null;
    }

    /**
     * Supprimer une ville d'un département
     *
     * @param id      l'id du département
     * @param idVille l'id de la ville à supprimer
     * @return List<Ville> la liste des villes
     */
    public List<Ville> deleteVille(Long id, Long idVille) {
        Departement departement = DepartementRepository.findById(id).orElse(null);
        if (departement != null) {
            Ville ville = departement.getVilles().stream().filter(v -> v.getId() == idVille).findFirst().orElse(null);
            if (ville != null) {
                departement.removeVille(ville);
                DepartementRepository.save(departement);
                return departement.getVilles();
            }
        }
        return null;
    }

    /**
     * Mettre à jour une ville d'un département
     *
     * @param id      l'id du département
     * @param idVille l'id de la ville à mettre à jour
     * @param ville   la ville mise à jour
     * @return List<Ville> la liste des villes
     */
    public List<Ville> updateVille(Long id, Long idVille, Ville ville) {
        Departement departement = DepartementRepository.findById(id).orElse(null);
        if (departement != null) {
            Ville v = departement.getVilles().stream().filter(vi -> vi.getId() == idVille).findFirst().orElse(null);
            if (v != null) {
                v.setNom(ville.getNom());
                v.setNbHabitants(ville.getNbHabitants());
                DepartementRepository.save(departement);
                return departement.getVilles();
            }
        }
        return null;
    }

    /**
     * Récupérer la liste des villes d'un département triées par ordre croissant du nombre d'habitants
     * @param id l'id du département
     * @return List<Ville> la liste des villes
     */
    public List<Ville> getVillesSortedByNbHabitants(Long id) {
        Departement departement = DepartementRepository.findById(id).orElse(null);
        if (departement != null) {
            return departement.getVilles().stream().sorted(Comparator.comparingInt(Ville::getNbHabitants)).collect(Collectors.toList());
        }
        return null;
    }

    /**
     * Récupérer la liste des villes d'un département triées par ordre décroissant du nombre d'habitants
     * @param id l'id du département
     * @return List<Ville> la liste des villes
     */
    public List<Ville> getVillesByNbHabitantsRange(Long id, int min, int max) {
        Departement departement = DepartementRepository.findById(id).orElse(null);
        if (departement != null) {
            return departement.getVilles().stream().filter(v -> v.getNbHabitants() >= min && v.getNbHabitants() <= max).collect(Collectors.toList());
        }
        return null;
    }

}
