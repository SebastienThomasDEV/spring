package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.ArrayList;
import java.util.List;

/**
 * Entité représentant un département
 *
 * @param id     l'identifiant du département
 * @param nom    le nom du département
 * @param code   le code du département
 * @param villes la liste des villes du département
 * @Entity permet de dire à Spring que c'est une entité
 * @Id permet de dire à Spring que c'est l'identifiant de l'entité
 * @GeneratedValue(strategy = GenerationType.IDENTITY) permet de dire à Spring que l'identifiant est auto-généré
 * @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL, orphanRemoval = true) permet de dire à Spring que c'est une relation OneToMany
 * @see Ville
 */
@Entity
public class Departement {

    /**
     * L'identifiant du département
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Le nom du département
     */
    @Min(value = 3, message = "Le nom du département doit contenir au moins 3 caractères")
    @Max(value = 50, message = "Le nom du département doit contenir au maximum 50 caractères")
    @Column(nullable = false)
    private String nom;

    /**
     * Le code du département
     */
    @Column(nullable = false)
    @Min(value = 2, message = "Le code du département doit contenir au moins 2 caractères")
    @Max(value = 5, message = "Le code du département doit contenir au maximum 5 caractères")
    private String code;

    /**
     * La liste des villes du département
     */
    @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ville> villes = new ArrayList<>();


    // Constructeurs
    public Departement() {
    }

    public Departement(String nom, String code) {
        this.nom = nom;
        this.code = code;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Ville> getVilles() {
        return villes;
    }

    public void addVille(Ville ville) {
        villes.add(ville);
        ville.setDepartement(this);
    }

    public void removeVille(Ville ville) {
        villes.remove(ville);
        ville.setDepartement(null);
    }


    @Override
    public String toString() {
        return "Departement{" +
                "nom='" + nom + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

}
