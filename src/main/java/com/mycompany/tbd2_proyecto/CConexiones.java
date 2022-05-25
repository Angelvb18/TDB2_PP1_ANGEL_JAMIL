/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tbd2_proyecto;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


/**
 *
 * @author garci
 */
public class CConexiones {
    public MongoClient mongo;
    public MongoDatabase data;
    public MongoCollection<Document> colection;
    public Document doc;
    public CConexiones() {
        crearConexion();
        data = mongo.getDatabase("Proyecto");
        System.out.println("Se obtuvo la base de Datos");
    }
    public void CamBiarColletion(String nombrec){
        colection = data.getCollection(nombrec);
        System.out.println("Colletions Changed");
    }
    public void InsertarBug(Bug bug){
        doc = new Document("idBug",bug.getIdBug());
        doc.append("descrip", bug.getDescrip());
        doc.append("idPro", bug.getIdPro());
        doc.append("NvlSOS", bug.getNvlSOS());
        doc.append("Estado", bug.getEstado());
        doc.append("fecha_inicio", bug.getFecha_inicio());
        doc.append("fecha_fin", bug.getFecha_fin());
        colection.insertOne(doc);
        System.out.println("se inserto el bug");
        
        
    }
    public void InsertarTeam(Team team){
       doc = new Document("idDev",team.getIdDev());
       doc.append("NomDev",team.getNomDev());
       String Lenguajes = "";
        for (int i = 0; i < team.getLenguajes().size(); i++) {
            Lenguajes+=team.getLenguajes().get(i);
            if(i!= team.getLenguajes().size()-1){
                Lenguajes+=",";
            }
        }
        doc.append("Lenguajes", Lenguajes);
        String Tecnologias = "";
        for (int i = 0; i < team.getTecnologias().size(); i++) {
            Tecnologias+=team.getTecnologias().get(i);
            if(i!= team.getTecnologias().size()-1){
                Tecnologias+=",";
            }
        }
        doc.append("Tecnologias", Tecnologias);
         colection.insertOne(doc);
        System.out.println("se inserto el team");
    }
    public void InsertarProyecto_Software(Proyecto_Software proy){
        doc = new Document("idPro",proy.getIdPro());
        doc.append("NomPro", proy.getNomPro());
        doc.append("Fecha_Inicio", proy.getFecha_inicio());
        doc.append("Fecha_fin", proy.getFecha_fin());
        String bugs = "";
        for(int i = 0 ; i < proy.getListaBugs().size();i++){
            bugs+=proy.getListaBugs().get(i).getIdBug()+"";
            if (i != proy.getListaBugs().size()-1) {
                bugs+=",";
            }
        }
        doc.append("Lista_Bugs", bugs);
        bugs = "";
        for(int i = 0 ; i < proy.getListaFinBugs().size();i++){
            bugs+=proy.getListaFinBugs().get(i).getIdBug()+"";
            if (i != proy.getListaFinBugs().size()-1) {
                bugs+=",";
            }
        }
        doc.append("Fin_Bugs", bugs);
         colection.insertOne(doc);
        System.out.println("se inserto el proyecto");
    }
    public  void crearConexion() {
        System.out.println("Prueba: ");
         mongo = null;

        mongo = new MongoClient("localhost", 27017);

        System.out.println("Conexion Establecida");
    }
public  void mostrarColeccionDesarrolaodres() {
        DB db = mongo.getDB("Proyecto");
        DBCollection colec = db.getCollection("Desarrolaodres");
        
        DBCursor cursor = colec.find();
        
        while(cursor.hasNext()) {
            System.out.println("* "+ cursor.next().get("idDev") + " - " + cursor.curr().get("NomDev"));
        }
    }
    
}
