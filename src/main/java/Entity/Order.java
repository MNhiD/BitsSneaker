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
public class Order {
    
    private String sellID;
    private int customerID;
    private String sneakerID;
    private int amount;
    private int sale;
//    private int VAT;
    private String Address;
    private long price;

    public Order() {
    }

    public Order(String sellID, int customerID, String sneakerID, int amount, int sale, String Address, long price) {
        this.sellID = sellID;
        this.customerID = customerID;
        this.sneakerID = sneakerID;
        this.amount = amount;
        this.sale = sale;
//        this.VAT = VAT;
        this.Address = Address;
        this.price = price;
    }

    public String getSellID() {
        return sellID;
    }

    public void setSellID(String sellID) {
        this.sellID = sellID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getSneakerID() {
        return sneakerID;
    }

    public void setSneakerID(String sneakerID) {
        this.sneakerID = sneakerID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

//    public int getVAT() {
//        return VAT;
//    }
//
//    public void setVAT(int VAT) {
//        this.VAT = VAT;
//    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
    
    
    
}
