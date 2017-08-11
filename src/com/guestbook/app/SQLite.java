package com.guestbook.app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class SQLite {
    //Static Variables
    static java.sql.Connection conn  = null;
    static java.sql.Statement stmt = null;
    static String url = "jdbc:sqlite:C:/Users/Criscia/Documents/NetBeansProjects/DAMA-ESTILLOSO_Guestbook_080417/src/com/guestbook/app/gbook.sqlite";
    static String error = "";
    
    //Open DB Session Method
    public static boolean openDB(){
        boolean result = false;
        try{
            Class.forName("org.sqlite.JDBC");
            conn = java.sql.DriverManager.getConnection(url);

            System.out.println("OK! SQLite DB session connected.");
            result = true;
        }
        catch(ClassNotFoundException | SQLException e){
            error = e.getMessage();
            System.out.println(e.getMessage() + "Open DB Error:");
        } 
        return result;
    }

    //Close DB Session Method
    public static boolean closeDB(){
        boolean result = false;
        try{
            conn.close();

            System.out.println("OK! SQLite DB session closed.");
            result = true;
        }
        catch(SQLException e){
            error = e.getMessage();
            System.out.println("Close DB Error: " + e.getMessage());
        }
        return result;
    }    

    //Create Record Method
    public static boolean create(String table, String values){
        boolean result = false;
        try{
            SQLite.stmt = conn.createStatement();
            String query = "INSERT INTO "+ table +" VALUES(" + values + ")";
            stmt.executeUpdate(query);
            //You can include exception handling here. (e.g. Duplicate Data, etc.)
            result = true;
        }
        catch(SQLException e){
            System.out.println("Create Error: " + e.getMessage());
        }
        return result;
    }    

    //Update Record Method
    public static boolean update(String table, String set, int id){
        boolean result = false;
        try{
            SQLite.stmt = conn.createStatement();
            String query = "UPDATE "+ table +" SET " + set + " WHERE id=" + id;
            stmt.executeUpdate(query);
            //You can include exception handling here. (e.g. Duplicate Data, etc.)
            result = true;
        }
        catch(SQLException e){
            System.out.println("Create Error: " + e.getMessage());
        }
        return result;
    }    

    //Delete Record Method
    public static boolean delete(String table, int id){
        boolean result = false;
        try{
            SQLite.stmt = conn.createStatement();
            String query = "DELETE FROM "+ table + " WHERE id=" + id;
            stmt.executeUpdate(query);
            result = true;
        }
        catch(SQLException e){
            System.out.println("Create Error: " + e.getMessage());
        }
        return result;
    }     

    //Read Record Method
    public static String[][] read(String table){
        String[][] records = null;
        try{
            SQLite.stmt = conn.createStatement();

            //Count total rows
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM " + table);
            int totalRows = rs.getInt(1);

            //Count total columns
            rs = stmt.executeQuery("SELECT * FROM " + table);
            ResultSetMetaData rsmd = rs.getMetaData();
            int totalColumns = rsmd.getColumnCount();

            //Initialize 2D Array "records" with totalRows by totalColumns
            records = new String[totalRows][totalColumns];

            //Retrieve the record and store it to 2D Array "records"
            int row=0;
            while(rs.next()){                
                for(int col=0,index=1;col<totalColumns;col++,index++){
                    records[row][col] = rs.getObject(index).toString();
                }
                row++;
            }            
        }
        catch(SQLException e){
            System.out.println("Read Error: " + e.getMessage());
        }
        return records;
    }
    
    /**
     * JDBC Routine
     * 
     * 1. Open a DB Session
     * 2. Execute a query
     * 3. Expect Return Value/s
     * 4. Close DB Session
     * @param args
     ***/
    
    public static void main(String[] args) {
        /*
        //Test a openDB and closeDB Methods
        if(SQLite.openDB()){
            //Execute a query...
            //Expect Return Value/s
            SQLite.closeDB();
        }
        */
        
        /*
        if(SQLite.openDB()){
            String id = "3";
            String name = "Mark";
            String values = id + "," + "'" + name + "'"; // 3,'Mark'
            SQLite.create("tblGuestbook", values);
            SQLite.closeDB();
        }
        */
        
        /** github.com/clydeatuic/java-todo
         * tblGuestbook
         *  ID          INT             Primary Key
         *  NAME        VARCHAR(50)     Not Null
         *  CONTACTNO   VARCHAR(50)     Not Null
         *  EMAIL       VARCHAR(50)     Not Null
         *  GENDER      CHAR(1)         Not Null
         */
        
        /*
        if(SQLite.openDB()){
            String updatedName = "Marky";
            String set = "name=" + "'" + updatedName + "'";
            SQLite.update("tblGuestbook", set, 3);
            SQLite.closeDB();
        }
        */

    }

    static Connection ConnecrDB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
