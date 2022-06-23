/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tbd2_proyecto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author angel
 */
public class Usuario {
    private String correo, password,rol,nombre , id_Desarrollador;

    public Usuario(String correo, String password, String rol, String nombre, String id_Desarrollador) {
        this.correo = correo;
        this.password = convertirSHA256(password);
        this.rol = rol;
        this.nombre = nombre;
        this.id_Desarrollador = id_Desarrollador;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = convertirSHA256(password);
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId_Desarrollador() {
        return id_Desarrollador;
    }

    public void setId_Desarrollador(String id_Desarrollador) {
        this.id_Desarrollador = id_Desarrollador;
    }

    
    public String convertirSHA256(String password) {
	MessageDigest md = null;
	try {
		md = MessageDigest.getInstance("SHA-256");
	} 
	catch (NoSuchAlgorithmException e) {		
		e.printStackTrace();
		return null;
	}
	    
	byte[] hash = md.digest(password.getBytes());
	StringBuffer sb = new StringBuffer();
	    
	for(byte b : hash) {        
		sb.append(String.format("%02x", b));
	}
	    
	return sb.toString();
}
}
