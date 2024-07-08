package com.example.demo.service;


import com.example.demo.ApiException;
import com.example.demo.entity.Departement;
import com.example.demo.entity.Ville;
import com.example.demo.repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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
    public List<Departement> getDepartements() throws ApiException {
        try {
            return DepartementRepository.findAll();
        } catch (Exception e) {
            throw new ApiException("Impossible de récupérer les départements");
        }
    }


    /**
     * Récupérer un département par son id
     *
     * @param id l'id du département
     * @return Departement le département
     */
    public Departement getDepartementById(Long id) throws ApiException {
        try {
            return DepartementRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new ApiException("Impossible de récupérer le département");
        }
    }
    /**
     * Ajouter un département
     *
     * @param Departement le département à ajouter
     * @return Departement le département ajouté
     */
    public Departement addDepartement(Departement Departement) throws ApiException {
        try {
            return DepartementRepository.save(Departement);
        } catch (Exception e) {
            throw new ApiException("Impossible d'ajouter le département");
        }
    }

    /**
     * Supprimer un département
     *
     * @param id l'id du département à supprimer
     */
    public void deleteDepartement(Long id) throws ApiException {
        try {
            DepartementRepository.deleteById(id);
        } catch (Exception e) {
            throw new ApiException("Impossible de supprimer le département");
        }
    }

    /**
     * Mettre à jour un département
     *
     * @param Departement le département à mettre à jour
     * @return Departement le département mis à jour
     */

    public Departement updateDepartement(Departement Departement) throws ApiException {
        try {
            return DepartementRepository.save(Departement);
        } catch (Exception e) {
            throw new ApiException("Impossible de mettre à jour le département");
        }
    }

    /**
     * Récupérer la liste des villes d'un département
     *
     * @param id l'id du département
     * @return List<Ville> la liste des villes
     */
    public List<Ville> getVilles(Long id) throws ApiException {
        try {
            Departement departement = DepartementRepository.findById(id).orElse(null);
            if (departement != null) {
                return departement.getVilles();
            }
            return null;
        } catch (Exception e) {
            throw new ApiException("Impossible de récupérer les villes");
        }
    }

    /**
     * Récupérer une ville d'un département par son id
     *
     * @param id      l'id du département
     * @param idVille l'id de la ville
     * @return Ville la ville
     */
    public Ville getVille(Long id, Long idVille) throws ApiException {
        try {
            Departement departement = DepartementRepository.findById(id).orElse(null);
            if (departement != null) {
                return departement.getVilles().stream().filter(v -> v.getId() == idVille).findFirst().orElse(null);
            }
            return null;
        } catch (Exception e) {
            throw new ApiException("Impossible de récupérer la ville");
        }
    }


    /**
     * Ajouter une ville à un département
     *
     * @param id    l'id du département
     * @param ville la ville à ajouter
     * @return List<Ville> la liste des villes
     */
    public List<Ville> addVille(Long id, Ville ville) throws ApiException {
        try {
            Departement departement = DepartementRepository.findById(id).orElse(null);
            if (departement != null) {
                departement.addVille(ville);
                DepartementRepository.save(departement);
                return departement.getVilles();
            }
            return null;
        } catch (Exception e) {
            throw new ApiException("Impossible d'ajouter la ville");
        }
    }

    /**
     * Supprimer une ville d'un département
     *
     * @param id      l'id du département
     * @param idVille l'id de la ville à supprimer
     * @return List<Ville> la liste des villes
     */
    public List<Ville> deleteVille(Long id, Long idVille) throws ApiException {
        try {
            Departement departement = DepartementRepository.findById(id).orElse(null);
            if (departement != null) {
                departement.getVilles().removeIf(v -> v.getId() == idVille);
                DepartementRepository.save(departement);
                return departement.getVilles();
            }
            return null;
        } catch (Exception e) {
            throw new ApiException("Impossible de supprimer la ville");
        }
    }

    /**
     * Mettre à jour une ville d'un département
     *
     * @param id      l'id du département
     * @param idVille l'id de la ville à mettre à jour
     * @param ville   la ville mise à jour
     * @return List<Ville> la liste des villes
     */
    public List<Ville> updateVille(Long id, Long idVille, Ville ville) throws ApiException {
        try {
            Departement departement = DepartementRepository.findById(id).orElse(null);
            if (departement != null) {
                departement.getVilles().stream().filter(v -> v.getId() == idVille).findFirst().ifPresent(v -> {
                    v.setNom(ville.getNom());
                    v.setNbHabitants(ville.getNbHabitants());
                });
                DepartementRepository.save(departement);
                return departement.getVilles();
            }
            return null;
        } catch (Exception e) {
            throw new ApiException("Impossible de mettre à jour la ville");
        }
    }

    /**
     * Exporter tous les départements au format CSV
     *
     * @return String le contenu du fichier CSV
     */
    public String exportDepartementsToCSV() throws ApiException {
        try {
            List<Departement> departements = DepartementRepository.findAll();
            StringBuilder csv = new StringBuilder();
            csv.append("id,nom\n");
            departements.forEach(d -> csv.append(d.getId()).append(",").append(d.getNom()).append("\n"));
            return csv.toString();
        } catch (Exception e) {
            throw new ApiException("Impossible d'exporter les départements");
        }
    }


}
