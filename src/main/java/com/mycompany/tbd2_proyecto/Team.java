/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tbd2_proyecto;

import java.util.ArrayList;

/**
 *
 * @author garci
 */
public class Team {
    
    private int idDev;
    private String NomDev;
    private ArrayList<String> Lenguajes;
    private ArrayList<String> Tecnologias;
    private  ArrayList<String> BugsAsignados;
    public Team(int idDev, String NomDev, ArrayList<String> Lenguajes, ArrayList<String> Tecnologias) {
        this.idDev = idDev;
        this.NomDev = NomDev;
        this.Lenguajes = Lenguajes;
        this.Tecnologias = Tecnologias;
    }

    public int getIdDev() {
        return idDev;
    }

    public void setIdDev(int idDev) {
        this.idDev = idDev;
    }

    public String getNomDev() {
        return NomDev;
    }

    public void setNomDev(String NomDev) {
        this.NomDev = NomDev;
    }

    public ArrayList<String> getLenguajes() {
        return Lenguajes;
    }

    public void setLenguajes(ArrayList<String> Lenguajes) {
        this.Lenguajes = Lenguajes;
    }

    public ArrayList<String> getTecnologias() {
        return Tecnologias;
    }

    public void setTecnologias(ArrayList<String> Tecnologias) {
        this.Tecnologias = Tecnologias;
    }
    
    
    
}
