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
public class Proyecto_Software {
    
    private int idPro;
    private String NomPro; 
    private String fecha_inicio; 
    private String fecha_fin;
    private Team equipo;
    private ArrayList<Bug> listaBugs;
    private ArrayList<Bug> listaFinBugs;
    private String TeamNombre;
    public Proyecto_Software(int idPro, String NomPro, String fecha_inicio, String fecha_fin, Team equipo, ArrayList<Bug> listaBugs, ArrayList<Bug> listaFinBugs) {
        this.idPro = idPro;
        this.NomPro = NomPro;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.equipo = equipo;
        this.listaBugs = listaBugs;
        this.listaFinBugs = listaFinBugs;
        TeamNombre = equipo.getNomDev();
    }
    public Proyecto_Software(int idPro, String NomPro, String fecha_inicio, String fecha_fin, String equipo) {
        this.idPro = idPro;
        this.NomPro = NomPro;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.TeamNombre = equipo;
        listaBugs = new ArrayList();
        listaFinBugs = new ArrayList();
    }

    public String getTeamNombre() {
        return TeamNombre;
    }

    public void setTeamNombre(String TeamNombre) {
        this.TeamNombre = TeamNombre;
    }

    public int getIdPro() {
        return idPro;
    }

    public void setIdPro(int idPro) {
        this.idPro = idPro;
    }

    public String getNomPro() {
        return NomPro;
    }

    public void setNomPro(String NomPro) {
        this.NomPro = NomPro;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Team getEquipo() {
        return equipo;
    }

    public void setEquipo(Team equipo) {
        this.equipo = equipo;
    }

    public ArrayList<Bug> getListaBugs() {
        return listaBugs;
    }

    public void setListaBugs(ArrayList<Bug> listaBugs) {
        this.listaBugs = listaBugs;
    }

    public ArrayList<Bug> getListaFinBugs() {
        return listaFinBugs;
    }

    public void setListaFinBugs(ArrayList<Bug> listaFinBugs) {
        this.listaFinBugs = listaFinBugs;
    }
    
    
    
}
