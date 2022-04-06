/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.ArrayList;

/**
 *
 * @author letan
 */
public class CustomerAccount {
    
    private int customerID;
    private String accountID;
    private String name;
    private String phoneNumber;
    private String gender;
    private ArrayList<String> addressList = new ArrayList<>();

    public CustomerAccount() {
    }

    public CustomerAccount(int customerID, String accountID, String name, String phoneNumber, String gender) {
        this.customerID = customerID;
        this.accountID = accountID;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }
    
    public CustomerAccount(int customerID, String accountID, String name, String phoneNumber, String gender, ArrayList<String> addressList) {
        this.customerID = customerID;
        this.accountID = accountID;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.addressList = addressList;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    
    

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ArrayList<String> getAddressList() {
        return addressList;
    }

    public void setAddressList(ArrayList<String> addressList) {
        this.addressList = addressList;
    }
    
    public void adddAddress(String address){
        this.addressList.add(address);
    }
    
}
