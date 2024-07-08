package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

/**
 * Entité Ville
 * Une ville est caractérisée par un nom et un nombre d'habitants
 * Une ville appartient à un département
 *
 * @Entity permet de dire à Spring que c'est une entité
 * @Id permet de dire à Spring que c'est l'identifiant de l'entité
 * @GeneratedValue(strategy = GenerationType.IDENTITY) permet de dire à Spring que l'identifiant est auto-généré
 * @Column(nullable = false) permet de dire à Spring que la colonne ne peut pas être nulle
 * @ManyToOne permet de dire à Spring que c'est une relation ManyToOne
 * @JoinColumn permet de dire à Spring que c'est une colonne de jointure
 *
 * @param id l'identifiant de la ville
 * @param nom le nom de la ville
 * @param nbHabitants le nombre d'habitants de la ville
 * @param departement le département de la ville
 *
 */
@Entity
public class Ville {


    /**
     * L'identifiant de la ville
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Le nom de la ville
     */
    @Column(nullable = false)
    @NotBlank
    @NotNull(message = "Le nom de la ville ne peut pas être nul")
    @Size(min = 3, max = 50, message = "Le nom de la ville doit contenir entre 3 et 50 caractères")
    private String nom;

    /**
     * Le nombre d'habitants de la ville
     */
    @NotNull(message = "Le nombre d'habitants de la ville ne peut pas être nul")
    @Size(min = 10, message = "Le nombre d'habitants de la ville doit être supérieur à 0")
    @NotBlank
    @Column(nullable = false)
    private int nbHabitants;


    /**
     * Le département de la ville
     */
    @ManyToOne
    @JoinColumn(name = "departement_id")
    @NotNull(message = "Le département de la ville ne peut pas être nul")
    @JsonBackReference
    private Departement departement;


    // Constructeurs
    public Ville() {}


    /**
     * Constructeur de la ville
     * @param nom le nom de la ville
     * @param nbHabitants le nombre d'habitants de la ville
     */
    public Ville(String nom, int nbHabitants) {
        this.nom = nom;
        this.nbHabitants = nbHabitants;
    }


    // Getters et Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbHabitants() {
        return nbHabitants;
    }

    public void setNbHabitants(int nbHabitants) {
        this.nbHabitants = nbHabitants;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }



    @Override
    public String toString() {
        return "Ville{" +
                "nom='" + nom + '\'' +
                ", nbHabitants=" + nbHabitants +
                '}';
    }
}
