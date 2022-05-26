/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tbd2_proyecto;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;
import org.bson.types.ObjectId;


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
        doc.append("Desarrolladores",proy.getTeamNombre());
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
public DefaultComboBoxModel DesarroladoresComboModel(){
    DefaultComboBoxModel modelo = new DefaultComboBoxModel();
    DB db = mongo.getDB("Proyecto");
        DBCollection colec = db.getCollection("Desarrolaodres");
        DBCursor cursor = colec.find();
        while(cursor.hasNext()) {
            
            modelo.addElement(cursor.next().get("NomDev"));
        }
    return modelo;
}
public DefaultTableModel ModeloTablaproyectosf(){
    DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Codigo", "Nombre", "Inicio", "Fin"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
        };
    DB db = mongo.getDB("Proyecto");
        DBCollection colec = db.getCollection("Proyecto_Software");
        
        DBCursor cursor = colec.find();
        
        while(cursor.hasNext()) {
            Object[] nrow ={cursor.next().get("_id"),cursor.curr().get("NomPro"),cursor.curr().get("Fecha_Inicio"),cursor.curr().get("Fecha_fin")};
            modelo.addRow(nrow);
        }
    return modelo;
}
public DefaultTableModel ModeloTablaDevelopper(){
    DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Codigo", "Nombre", "Lenguajes", "Tecnologias"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
        };
    DB db = mongo.getDB("Proyecto");
        DBCollection colec = db.getCollection("Desarrolaodres");
        
        DBCursor cursor = colec.find();
        
        while(cursor.hasNext()) {
            Object[] nrow ={cursor.next().get("_id"),cursor.curr().get("NomDev"),cursor.curr().get("Lenguajes"),cursor.curr().get("Tecnologias")};
            modelo.addRow(nrow);
        }
    return modelo;
}
public  void borrarDocumento( String coleccion, String id) {
    DB db = mongo.getDB("Proyecto");
    DBCollection colec = db.getCollection(coleccion);
    colec.remove(new BasicDBObject().append("_id", new ObjectId(id)));
}
public  void actualizarDocumento(String coleccion, String id ,String Atributo , String valor) {
        DB db = mongo.getDB("Proyecto");
        DBCollection colec = db.getCollection(coleccion);
        
        // SENTENCIA CON LA INFORMACION A REMPLAZAR
        BasicDBObject actualizar = new BasicDBObject();
        actualizar.append("$set", new BasicDBObject().append(Atributo, valor));
        
        // BUSCA EL DOCUMENTO EN LA COLECCION
        BasicDBObject buscarPorNombre = new BasicDBObject();
        buscarPorNombre.append("_id", new ObjectId(id));
        
        // REALIZA EL UPDATE
        colec.updateMulti(buscarPorNombre, actualizar);
}
}
