package com.example.m2l;

public class Equipe {
    private int idEquipe;
    private int idCompetition;
    private String nomEquipe;
    private String typeSport;

    // Getters
    public int getIdEquipe() {
        return idEquipe;
    }

    public int getIdCompetition() {
        return idCompetition;
    }

    public String getNomEquipe() {
        return nomEquipe;
    }

    public String getTypeSport() {
        return typeSport;
    }

    // Setters
    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    public void setIdCompetition(int idCompetition) {
        this.idCompetition = idCompetition;
    }

    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public void setTypeSport(String typeSport) {
        this.typeSport = typeSport;
    }
}

