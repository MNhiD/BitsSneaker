/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author letan
 */
public class FileController {
    
    public static void outputFile(String filename, String data) throws IOException{
        FileWriter out = null;
        try {
            out = new FileWriter(filename);
            out.write(data);
        } catch (IOException ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            if(out!=null){
                out.close();
            }
        }
    }
    
    public static String inputFile(String filename) throws IOException{
        BufferedReader in = null;
        String s="";
        try {
            in = new BufferedReader(new FileReader(filename));
            s = in.readLine();
        } catch (IOException ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            if(in!=null){
                in.close();
            }
        }
        
        return (s==null)?"":s;
    }
    
}
