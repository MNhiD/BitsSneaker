/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Input;

/**
 *
 * @author letan
 */
public class AccountCheck {
    
    public static String emailStandard = "^(.+)@(.+)$";
    public static String passwordStandard = ".{6,}";
    
    public static boolean isCorrect(String text, String standard){
        if(text.matches(standard)) return true;
        return false;
    }
    
//    public static boolean isPassword(String pass){
//        if(pass.length()<6) return false;
//        return true;
//    }
    
}
