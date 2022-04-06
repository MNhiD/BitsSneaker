/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author letan
 */
public class Cart {
    
    private String goodID;
    private int amount;

    public Cart(String goodID, int amount) {
        this.goodID = goodID;
        this.amount = amount;
    }
    
    public Cart(){
        
    }

    public String getGoodID() {
        return goodID;
    }

    public void setGoodID(String goodID) {
        this.goodID = goodID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    
    
}
