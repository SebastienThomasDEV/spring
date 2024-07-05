package com.example.demo.dto;

public class VilleDto {

    private Integer id;
    private String nom;
    private int nbHabitants;
    private DepartementDto departement;


    public VilleDto() {
    }

    public VilleDto(Integer id, String nom, int nbHabitants, DepartementDto departement) {
        this.id = id;
        this.nom = nom;
        this.nbHabitants = nbHabitants;
        this.departement = departement;
    }

    // Getters et Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public DepartementDto getDepartement() {
        return departement;
    }

    public void setDepartement(DepartementDto departement) {
        this.departement = departement;
    }
}
