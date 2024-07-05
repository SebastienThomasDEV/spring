package com.example.demo.dto;

import java.util.List;

public class DepartementDto {


    private Long id;
    private String nom;
    private String code;
    private List<String> villes;

    public DepartementDto() {

    }

    public DepartementDto(Long id, String nom, String code, List<String> villes) {
        this.id = id;
        this.nom = nom;
        this.code = code;
        this.villes = villes;
    }

    // Getters et Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<String> getVilles() {
        return villes;
    }

    public void setVilles(List<String> villes) {
        this.villes = villes;
    }

}
