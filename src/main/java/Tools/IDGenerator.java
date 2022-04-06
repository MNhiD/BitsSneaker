/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Database.DatabaseManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author letan
 */
public class IDGenerator {
    
    private static DatabaseManager database = new DatabaseManager("BitSneaker");
    
    public static String generateSellOrderID(String account){
        
        String id="";
        if(account.equals("customer")){
            id=id+"CST";
        }
        else if(account.equals("staff")){
            id=id+"STF";
        }
        
        String number="0";
        try {
            ResultSet rs = database.queryData("SELECT TOP 1 SellID FROM SELLORDER ORDER BY TimeCreate DESC");
            if(rs.next()){
                number = (Integer.parseInt(rs.getString(1).substring(3))+1)+"";
            }
        } catch (SQLException ex) {
            Logger.getLogger(IDGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while(id.length()<(10-number.length())){
            id = id+"0";
        }
        
        id = id+number;
        
        return id;
    }
    
    public static String generateImportOrderID(){
        
        String id="ORD";
        
        String number="0";
        try {
            ResultSet rs = database.queryData("SELECT TOP 1 ImportID FROM IMPORTORDER ORDER BY OrderTime DESC");
            if(rs.next()){
                number = (Integer.parseInt(rs.getString(1).substring(3))+1)+"";
            }
        } catch (SQLException ex) {
            Logger.getLogger(IDGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while(id.length()<(10-number.length())){
            id = id+"0";
        }
        
        id = id+number;
        
        return id;
    }
    
}
