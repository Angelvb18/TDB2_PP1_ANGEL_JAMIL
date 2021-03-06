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
import java.util.ArrayList;
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
public DefaultComboBoxModel ProyectosComboModel(){
    DefaultComboBoxModel modelo = new DefaultComboBoxModel();
    DB db = mongo.getDB("Proyecto");
        DBCollection colec = db.getCollection("Proyecto_Software");
        DBCursor cursor = colec.find();
        while(cursor.hasNext()) {
            
            modelo.addElement(cursor.next().get("_id")+"");
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
        System.out.println(id + " AQUI");
        // SENTENCIA CON LA INFORMACION A REMPLAZAR
        BasicDBObject actualizar = new BasicDBObject();
        actualizar.append("$set", new BasicDBObject().append(Atributo, valor));
        
        // BUSCA EL DOCUMENTO EN LA COLECCION
        BasicDBObject buscarPorNombre = new BasicDBObject();
        buscarPorNombre.append("_id", new ObjectId(id));
        
        // REALIZA EL UPDATE
        colec.updateMulti(buscarPorNombre, actualizar);
}

//JG
    public DefaultTableModel ModelTableBugsQA() {
        DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Codigo", "Descripcion", "CodigoProyecto", "NvlUrgencia", "Estado"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
        };

        DB db = mongo.getDB("Proyecto");
        DBCollection colec = db.getCollection("Bugs");
        DBCursor cursor = colec.find();
        while (cursor.hasNext()) {
            Object[] nrow = {cursor.next().get("_id"), cursor.curr().get("descrip"), cursor.curr().get("idPro"), cursor.curr().get("NvlSOS"), cursor.curr().get("Estado")};
            modelo.addRow(nrow);
        }

        return modelo;
    }

    public DefaultTableModel ModelTableProyectosQA() {
        DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Codigo", "Nombre", "FechaInicio"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
        };

        DB db = mongo.getDB("Proyecto");
        DBCollection colec = db.getCollection("Proyecto_Software");
        DBCursor cursor = colec.find();
        while (cursor.hasNext()) {
            Object[] nrow = {cursor.next().get("_id"), cursor.curr().get("NomPro"), cursor.curr().get("fecha_inicio")};
            modelo.addRow(nrow);
        }

        return modelo;
    }

    public DefaultTableModel ModelTableAsignados(String Name) {
        DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Codigo", "Nombre", "FechaInicio"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
        };
        DB db = mongo.getDB("Proyecto");
        DBCollection colec = db.getCollection("Proyecto_Software");
        DBCursor cursor = colec.find();
        while (cursor.hasNext()) {
            
            if (BuscarAsignado(Name,  cursor.next().get("Desarrolladores")+"")) {
                
                Object[] nrow = {cursor.curr().get("_id"), cursor.curr().get("NomPro"), cursor.curr().get("Fecha_Inicio")};
                modelo.addRow(nrow);
            }
        }

        return modelo;
    }

    public DefaultTableModel ModelTableDevs() {
        DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Nombre"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class
            };
        };
        DB db = mongo.getDB("Proyecto");
        DBCollection colec = db.getCollection("Desarrolaodres");
        DBCursor cursor = colec.find();
        while (cursor.hasNext()) {
            Object[] nrow = {cursor.next().get("NomDev")};
            modelo.addRow(nrow);
        }

        return modelo;
    }

    public DefaultTableModel ModelTableDevsBugs(String id) {
        DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Codigo", "Descripcion", "NvlUrgencia", "FechaIncio", "Estado"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
        };
        DB db = mongo.getDB("Proyecto");
        DBCollection colec = db.getCollection("Bugs");
        DBCursor cursor = colec.find();
        while (cursor.hasNext()) {
            if (BuscarAsignado(id,  cursor.next().get("idPro")+"")) {
                Object[] nrow = {cursor.curr().get("_id"), cursor.curr().get("descrip"),cursor.curr().get("NvlSOS"), cursor.curr().get("fecha_inicio"), cursor.curr().get("Estado")};
                modelo.addRow(nrow);
            }

        }

        return modelo;
    }

    public boolean BuscarAsignado(String Name, String lista) {
        //revisar si no hay []
        System.out.println(lista);
        String[] listaP = lista.split(",");
        for (int i = 0; i < listaP.length; i++) {
            if (listaP[i].equals(Name)) {
                return true;
            }
        }
        return false;
    }
    public  String buscarPorObjectIDNombre( String coleccion, String valor,String Atributo) {
        DB db = mongo.getDB("Proyecto");
        DBCollection colect = db.getCollection(coleccion);
        String result="";
        // CREAMOS LA CONSULTA CON EL CAMPO NOMBRE
        BasicDBObject consulta = new BasicDBObject();
        consulta.put("_id", new ObjectId(valor));
        
        // BUSCA Y MUESTRA TODOS LOS DOCUMENTOS QUE COINCIDAN CON LA CONSULTA
        DBCursor cursor = colect.find(consulta);
        while(cursor.hasNext()) {
             result = cursor.next().get(Atributo)+"";
             break;
        }
        return result;
    }
    public DefaultComboBoxModel DesarroladoresComboModelObjectId(){
    DefaultComboBoxModel modelo = new DefaultComboBoxModel();
    DB db = mongo.getDB("Proyecto");
        DBCollection colec = db.getCollection("Desarrolaodres");
        DBCursor cursor = colec.find();
        while(cursor.hasNext()) {
            
            modelo.addElement(cursor.next().get("_id")+"");
        }
    return modelo;
}
    public DefaultTableModel BugsProyecto(String name){
        DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Codigo", "Descripcion", "CodigoProyecto", "NvlUrgencia", "Estado"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
        };
        DB db = mongo.getDB("Proyecto");
        DBCollection colect = db.getCollection("Bugs");
        
        // CREAMOS LA CONSULTA CON EL CAMPO NOMBRE
        BasicDBObject consulta = new BasicDBObject();
        consulta.put("idPro", name);
        
        // BUSCA Y MUESTRA TODOS LOS DOCUMENTOS QUE COINCIDAN CON LA CONSULTA
        DBCursor cursor = colect.find(consulta);
        while(cursor.hasNext()) {
             Object[] nrow = {cursor.next().get("_id"), cursor.curr().get("descrip"), cursor.curr().get("idPro"), cursor.curr().get("NvlSOS"),cursor.curr().get("Estado")};
              modelo.addRow(nrow);
             
        }
        return modelo;
    }
    public void InsertarUsuario(Usuario user){
       doc = new Document("id_Desarrollador",user.getId_Desarrollador());
       doc.append("Nombre",user.getNombre());
        doc.append("Password", user.getPassword());
        doc.append("Correo", user.getCorreo());
        doc.append("Rol", user.getRol());
         colection.insertOne(doc);
        System.out.println("se inserto el Usuario");
    }
    
    public String login(String coleccion , String correo, String pass ){
        ArrayList<String> lista1 = new ArrayList();
        ArrayList<String> lista2 = new ArrayList();
        DB db = mongo.getDB("Proyecto");
        DBCollection colect = db.getCollection(coleccion);
        String result="";
        // CREAMOS LA CONSULTA CON EL CAMPO NOMBRE
        BasicDBObject consulta = new BasicDBObject();
        BasicDBObject consulta2 = new BasicDBObject();
        consulta.put("Correo", correo);
        consulta2.put("Password", pass);
        // BUSCA Y MUESTRA TODOS LOS DOCUMENTOS QUE COINCIDAN CON LA CONSULTA
        DBCursor cursor = colect.find(consulta);
        
        while(cursor.hasNext()) {
            lista1.add(cursor.next().get("_id")+"");
        }
        DBCursor cursor2 = colect.find(consulta2);
        while(cursor2.hasNext()) {
            lista2.add(cursor2.next().get("_id")+"");
        }
        for (int i = 0; i < lista1.size(); i++) {
            if (lista2.contains(lista1.get(i)) == true) {
                result = buscarPorObjectIDNombre("Usuario",lista1.get(i), "Rol");
            }
        }
        System.out.println(result);
        return result;
    }
}
