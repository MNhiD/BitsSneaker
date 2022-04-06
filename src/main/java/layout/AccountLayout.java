/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layout;

import CustomComponents.CustomTabLayout;
import CustomComponents.RoundedPanel;
import CustomListener.MouseClick;
import Database.DatabaseManager;
import Entity.CustomerAccount;
import Tools.FileController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author letan
 */
public class AccountLayout extends JPanel{
    
    private String currentCustomerID;
    
    private DatabaseManager database = new DatabaseManager("BitSneaker");
    
    private CustomerAccount customer;
    
    private CustomTabLayout tabLayout;
    
    private RoundedPanel btnLogout;
    
    private Color themeColor = new Color(52, 132, 240);
    private Color themeLightColor = new Color(115, 168, 240);
    
    private MouseClick listener;
    
    public AccountLayout(String currentCustomerID){
        
        this.currentCustomerID = currentCustomerID;
        
        updateCustomerDatabaseInfo();
        
        setLayout(new BorderLayout(0, 0));
        setBackground(Color.WHITE);
        
        JPanel top = new JPanel(new BorderLayout(0, 0));
        top.setPreferredSize(new Dimension(950, 100));
        top.setBackground(Color.WHITE);
        
        JPanel titlePanel = new JPanel(new BorderLayout(0, 0)); 
        titlePanel.setBackground(new Color(0, 0, 0, 0));
        JLabel label = new JLabel("Tài khoản");
        label.setFont(new Font("San-Serif", Font.BOLD, 20));
        label.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 0));
        titlePanel.add(label, BorderLayout.SOUTH);
        
        btnLogout = new RoundedPanel(100, 50, this.themeColor, 5);
        btnLogout.setPreferredSize(new Dimension(100, 50));
        btnLogout.setLayout(new GridLayout(1, 1));
        
        JLabel btnTitle = new JLabel("Đăng xuất");
        btnTitle.setHorizontalAlignment(SwingConstants.CENTER);
        btnTitle.setForeground(Color.WHITE);
        btnTitle.setFont(new Font("San-Serif", Font.PLAIN, 17));
        
        btnLogout.add(btnTitle);
        
        JPanel btnContain = new JPanel(new BorderLayout(15, 15));
        btnContain.setBackground(Color.WHITE);
        btnContain.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 15));
        btnContain.add(btnLogout, BorderLayout.SOUTH);
        
        top.add(titlePanel, BorderLayout.WEST);
        top.add(btnContain, BorderLayout.EAST);
        
        JPanel center = new JPanel(new FlowLayout(0, 0, 0));
        center.setPreferredSize(new Dimension(950, 550));
        center.setBackground(Color.WHITE);
        
        ArrayList<String> tabList = new ArrayList<>(Arrays.asList("Hồ sơ", "Đã mua", "Chờ duyệt", "Thất bại"));
        tabLayout = new CustomTabLayout(950, 550, tabList, this.customer);
        
        center.add(tabLayout);
        
        add(top, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        
        btnLogout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                btnLogout.setBackGroundColor(themeColor);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnLogout.setBackGroundColor(themeLightColor);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    FileController.outputFile("src//main//java//local//login.txt", "");
                    listener.MouseClick();
                } catch (IOException ex) {
                    Logger.getLogger(AccountLayout.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
    }
    
    public void setLogoutListener(MouseClick listener){
        this.listener = listener;
    }
    
    public void updateCustomerDatabaseInfo(){
        ResultSet rsCustomer = database.queryData("SELECT * FROM CUSTOMER WHERE CustomerID = '"+this.currentCustomerID+"'");
        
        try {
            if(rsCustomer.next()){
                this.customer = new CustomerAccount(rsCustomer.getInt(1), rsCustomer.getString(5), rsCustomer.getString(3), rsCustomer.getString(2), rsCustomer.getString(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountLayout.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ResultSet rsAddress = database.queryData("SELECT * FROM ADDRESS WHERE CustomerID = '"+this.currentCustomerID+"'");
        
        try {
            while(rsAddress.next()){
                this.customer.adddAddress(rsAddress.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountLayout.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateAccountLayout(){
        updateCustomerDatabaseInfo();
        tabLayout.setCustomer(this.customer);
        this.tabLayout.createAccountLayout();
        this.tabLayout.createPendingLayout();
        this.tabLayout.createBoughtLayout();
        this.tabLayout.createCancelLayout();
    }
    
}
