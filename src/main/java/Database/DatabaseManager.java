/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author letan
 */
public class DatabaseManager {
    
    private Connection conn;
    private String databaseName;
    
    public DatabaseManager(String databaseName){
        try {
            this.databaseName = databaseName;
            String dbURL = "jdbc:sqlserver://localhost;databaseName="+databaseName+";user=sa;password=12345678";
            conn = DriverManager.getConnection(dbURL);
        } catch (SQLException ex) {
            System.err.println("Cannot connect database, " + ex);
        }
    }
    
    public ResultSet queryData(String sql){
        try {
            ResultSet rs;
            rs = this.conn.createStatement().executeQuery(sql);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean queryUpdate(String sql){
        try {
            this.conn.createStatement().executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
