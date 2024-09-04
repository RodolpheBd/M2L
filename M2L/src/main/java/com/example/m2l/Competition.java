package com.example.m2l;

import java.util.Date;

public class Competition {
    private int idCompetition;
    private String nomCompetition;
    private String typeSport;
    private String lieuCompetition;
    private String dateCompetition;

    // Getters
    public int getIdCompetition() {
        return idCompetition;
    }

    public String getNomCompetition() {
        return nomCompetition;
    }

    public String getTypeSport() {
        return typeSport;
    }

    public String getLieuCompetition() {
        return lieuCompetition;
    }

    public String getDateCompetition() {
        return dateCompetition;
    }

    // Setters
    public void setIdCompetition(int idCompetition) {
        this.idCompetition = idCompetition;
    }

    public void setNomCompetition(String nomCompetition) {
        this.nomCompetition = nomCompetition;
    }

    public void setTypeSport(String typeSport) {
        this.typeSport = typeSport;
    }

    public void setLieuCompetition(String lieuCompetition) {
        this.lieuCompetition = lieuCompetition;
    }

    public void setDateCompetition(String dateCompetition) {
        this.dateCompetition = dateCompetition;
    }
}
