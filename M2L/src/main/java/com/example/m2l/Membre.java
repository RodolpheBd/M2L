package com.example.m2l;

import java.util.Date;

public class Membre {
    private int idMembre;
    private int idEquipe;
    private String nomMembre;
    private String prenomMembre;
    private String sexeMembre;
    private Date dateNaissanceMembre;

    // Getters
    public int getIdMembre() {
        return idMembre;
    }

    public int getIdEquipe() {
        return idEquipe;
    }

    public String getNomMembre() {
        return nomMembre;
    }

    public String getPrenomMembre() {
        return prenomMembre;
    }

    public String getSexeMembre() {
        return sexeMembre;
    }

    public Date getDateNaissanceMembre() {
        return dateNaissanceMembre;
    }

    // Setters
    public void setIdMembre(int idMembre) {
        this.idMembre = idMembre;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    public void setNomMembre(String nomMembre) {
        this.nomMembre = nomMembre;
    }

    public void setPrenomMembre(String prenomMembre) {
        this.prenomMembre = prenomMembre;
    }

    public void setSexeMembre(String sexeMembre) {
        this.sexeMembre = sexeMembre;
    }

    public void setDateNaissanceMembre(Date dateNaissanceMembre) {
        this.dateNaissanceMembre = dateNaissanceMembre;
    }
}
