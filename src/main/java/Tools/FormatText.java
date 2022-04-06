/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.text.DecimalFormat;

/**
 *
 * @author letan
 */
public class FormatText {
    
    public static String covertCurrentcyFormat(long price){
        DecimalFormat formater = new DecimalFormat("###,###,###");
        return formater.format(price)+" VNĐ";
    }
    
    public static long convertFormatTextToLong(String price){
        price = price.replace(",", "");
        return Long.parseLong(price.substring(0, price.length()-4));
    }
    
}
