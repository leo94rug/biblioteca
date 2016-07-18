
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uti;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author leo
 */
public class Intermedio {
	protected static String url = "jdbc:mysql://localhost/biblioteca";
	protected static String user = "root";
	protected static String psw = "";


	private static Connection db;
    
    /**
     * Connessione al database
     * @throws Exception 
     */
    public static String annida(String par1,String val1,String par2,String val2){
        return par1 + "=" + "'" + val1 + "' && '" + par2 + "=" + "'" + val2 + "'";
    }
    public static void connect() throws Exception{
        try{ 	                    
    	    Class.forName("com.mysql.jdbc.Driver");                 
    	    db = DriverManager.getConnection(url,user,psw);
    	}
    	catch(SQLException e){
    	    System.out.println(e.getMessage());
   	} 	
    }
    
    public static void close() throws SQLException{
        Intermedio.db.close();
    }
    
    public static ResultSet selectRecord(String table, String condition) throws SQLException, IOException {
        // Generazione query
      String query = "SELECT * FROM " + table + " WHERE " + condition;
        // Esecuzione query
        return Intermedio.executeQuery(query);
    }
    /**
     * Select record con condizione e ordinamento
     * @param table         tabella da cui prelevare i dati
     * @param condition     condizione per il filtro dei dati
     * @param order         ordinamento dei dati
     * @return              dati prelevati
     * @throws java.sql.SQLException
     */
    public static ResultSet selectRecord(String table, String condition, String order) throws SQLException{
        // Generazione query
        String query = "SELECT * FROM " + table + " WHERE " + condition + " ORDER BY " + order;
        // Esecuzione query
        return Intermedio.executeQuery(query);
    }
    
    /**
     * Select record con join tra due tabelle
     * @param table_1           nome della prima tabella
     * @param table_2           nome della seconda tabella
     * @param join_condition    condizione del join tra la tabelle
     * @param where_condition   condizione per il filtro dei dati
     * @return                  dati prelevati
     * @throws java.sql.SQLException
     */
    public static ResultSet selectJoin(String table_1, String table_2, String join_condition, String where_condition) throws SQLException{
        // Generazione query
        String query = "SELECT * FROM " + table_1 + " JOIN " + table_2 + " ON " + join_condition + " WHERE " + where_condition;
        // Esecuzione query
        return Intermedio.executeQuery(query);
    }
    
    /**
     * Select record con join tra due tabelle e ordinamento
     * @param table_1           nome della prima tabella
     * @param table_2           nome della seconda tabella
     * @param join_condition    condizione del join tra la tabelle
     * @param where_condition   condizione per il filtro dei dati
     * @param order             ordinamento dei dati
     * @return                  dati prelevati
     * @throws java.sql.SQLException
     */
    public static ResultSet selectJoin(String table_1, String table_2, String join_condition, String where_condition, String order) throws SQLException{
        // Generazione query
        String query = "SELECT * FROM " + table_1 + " JOIN " + table_2 + " ON " + join_condition + " WHERE " + where_condition + "ORDER BY" + order;
        // Esecuzione query
        return Intermedio.executeQuery(query);
    }
    
    /**
     * Insert record
     * @param table     tabella in cui inserire i dati
     * @param data      dati da inserire
     * @return dati     prelevati
     * @throws java.sql.SQLException
     */
        public static int insertRecord1(String table, Map<String, Object> data, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException{
        String query1="";
        String query2="";
        Object value;
        String attr;
        for(Map.Entry<String,Object> e:data.entrySet()){
            attr = e.getKey();
            value = e.getValue();
            System.out.println(value);
            if(value instanceof Integer){
                query2 = query2 + value + ", ";
            }
            else{
                value = value.toString().replace("\'", "\\'");
                query2=query2+"'" + value + "', ";
            }
        }
        query1 = query1.substring(0, query1.length() - 2);
        query2 = query2.substring(0, query2.length() - 2);
        String query = "INSERT INTO " + table + " (" + query1 + ") VALUES (" + query2 + ")";
        return Intermedio.updateQuery(query);
    }
    public static int insertRecord(String table, Map<String, Object> data) throws SQLException{
        // Generazione query
        String query = "INSERT INTO " + table + " SET ";
        Object value;
        String attr;
        
        for(Map.Entry<String,Object> e:data.entrySet()){
            attr = e.getKey();
            value = e.getValue();
            System.out.print(value);
            if(value instanceof Integer){
                query = query + attr + " = " + value + ", ";
            }else{
                value = value.toString().replace("\'", "\\'");
                query = query + attr + " = '" + value + "', ";
            }
        }
        query = query.substring(0, query.length() - 2);
        // Esecuzione query
        return Intermedio.updateQuery(query);
    }
    
    /**
     * Update record
     * @param table         tabella in cui aggiornare i dati
     * @param data          dati da inserire
     * @param condition     condizione per il filtro dei dati
     * @return              true se l'inserimento è andato a buon fine, false altrimenti
     * @throws java.sql.SQLException
     */
    public static int updateRecord(String table, Map<String,Object> data, String condition) throws SQLException{
        // Generazione query
        String query = "UPDATE " + table + " SET ";
        Object value;
        String attr;
        
        for(Map.Entry<String,Object> e:data.entrySet()){
            attr = e.getKey();
            value = e.getValue();
            if(value instanceof String){
                value = value.toString().replace("\'", "\\'");
                query = query + attr + " = '" + value + "', ";
            }else{
                query = query + attr + " = " + value + ", ";
            }
            
            
        }
        query = query.substring(0, query.length()-2) + " WHERE " + condition;
        
        // Esecuzione query
        return Intermedio.updateQuery(query);
    }
    
    /**
     * Delete record
     * @param table         tabella in cui eliminare i dati
     * @param condition     condizione per il filtro dei dati
     * @return              true se l'eliminazione è andata a buon fine, false altrimenti
     * @throws java.sql.SQLException
     */
    public static int deleteRecord(String table, String condition) throws SQLException{
        // Generazione query
        String query = "DELETE FROM " + table + " WHERE " + condition;
        // Esecuzione query
        return Intermedio.updateQuery(query);
    }
    
    /**
     * Count record
     * @param table         tabella in cui contare i dati
     * @param condition     condizione per il filtro dei dati
     * @return              numero dei record se la query è stata eseguita on successo, -1 altrimenti
     * @throws java.sql.SQLException
     */
    public static int countRecord(String table, String condition) throws SQLException{

        // Generazione query
        String query = "SELECT COUNT(*) FROM " + table + " WHERE " + condition;
        // Esecuzione query
        ResultSet record = Intermedio.executeQuery(query);
        record.next();
        // Restituzione del risultato
        return record.getInt(1);

    }
    
    /**
     * Imposta a NULL un attributo di una tabella  
     * @param table         tabella in cui è presente l'attributo
     * @param attribute     attributo da impostare a NULL
     * @param condition     condizione
     * @return
     * @throws java.sql.SQLException
     */
    public static int resetAttribute(String table, String attribute, String condition) throws SQLException{
        String query = "UPDATE " + table + " SET " + attribute + " = NULL WHERE " + condition;
        return Intermedio.updateQuery(query);
    }

    // <editor-fold defaultstate="collapsed" desc="Metodi ausiliari.">
    
    /**
     * executeQuery personalizzata
     * @param query query da eseguire
     */
    private static ResultSet executeQuery(String query) throws SQLException{
        
        Statement s1 = Intermedio.db.createStatement();
        ResultSet records = s1.executeQuery(query);

        return records; 
            
    }
    
    /**
     * updateQuery personalizzata
     * @param query query da eseguire
     */
    private static int updateQuery(String query) throws SQLException{
        
        Statement s1;
        
        s1 = Intermedio.db.createStatement();
        int c=s1.executeUpdate(query); 
        s1.close();
        return c; 

    }
   // </editor-fold>
    
}

