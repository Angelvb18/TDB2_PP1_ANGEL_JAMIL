/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tbd2_proyecto;

/**
 *
 * @author garci
 */
public class Bug {
    
    private int idBug; 
    private String descrip; 
    private int idPro; 
    private int NvlSOS;
    private String Estado;
    private String fecha_inicio;
    private String fecha_fin;

    public Bug(int idBug, String descrip, int idPro, int NvlSOS, String Estado, String fecha_inicio, String fecha_fin) {
        this.idBug = idBug;
        this.descrip = descrip;
        this.idPro = idPro;
        this.NvlSOS = NvlSOS;
        this.Estado = Estado;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
    }

    public int getIdBug() {
        return idBug;
    }

    public void setIdBug(int idBug) {
        this.idBug = idBug;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public int getIdPro() {
        return idPro;
    }

    public void setIdPro(int idPro) {
        this.idPro = idPro;
    }

    public int getNvlSOS() {
        return NvlSOS;
    }

    public void setNvlSOS(int NvlSOS) {
        this.NvlSOS = NvlSOS;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
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
    
}
