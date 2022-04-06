/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layout;

import CustomComponents.CustomScrollBarUI;
import CustomComponents.CustomScrollPane;
import CustomComponents.ImagePanel;
import CustomComponents.Item;
import CustomComponents.ListView;
import CustomComponents.SimpleItem;
import CustomListener.AddCartListener;
import CustomListener.CustomMouseClick;
import CustomListener.MouseClick;
import Database.DatabaseManager;
import Entity.Cart;
import Entity.Goods;
import Tools.ImageController;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.spi.DirStateFactory;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

/**
 *
 * @author letan
 */
public class HomePage{
    
    private JFrame homeFrame;
    private JPanel centerHomePage;
    
    private ArrayList<String> listCategory = new ArrayList<String>(Arrays.asList("Trang chủ","Sneaker","Sandals","Phụ kiện","Giỏ hàng","Tài khoản","Liên hệ"));
    private ArrayList<Goods> listGoods, listSneaker, listSandal, listAccessory;
    private ResultSet rs;
    
    private DatabaseManager database = new DatabaseManager("BitSneaker");
    
    private MainPage home, sneakers, sandals, accessories, contact;
    private CartLayout cart;
    private AccountLayout account;
    private InformationGood infoGoods;
    ListView listNavButton;
    
    private CardLayout card;
    
    private String prevCard="homecard";
    
    private String currentCustomerID="";
    
//    private ArrayList<Cart> listCart;
    
    public HomePage(String accountID){
        
        if(!accountID.equals("")){
            ResultSet rsCustomer = database.queryData("SELECT CustomerID FROM CUSTOMER WHERE AccountID='"+accountID+"'");
            try {
                rsCustomer.next();
                currentCustomerID = rsCustomer.getString(1);
            } catch (SQLException ex) {
                Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            ResultSet rsCart = database.queryData("SELECT COUNT(*) FROM CART WHERE CustomerID='"+currentCustomerID+"'");
            try {
                if(rsCart.next()){
                    listCategory.set(4, "Giỏ hàng ("+rsCart.getString(1)+")");
                }
            } catch (SQLException ex) {
                Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            listCategory.set(5, "Đăng nhập");
        }
        
        rs = database.queryData("SELECT SNEAKER.SneakerID, SNEAKER.Image, SNEAKER.Name, SNEAKER.Brand, SNEAKER.Color, SNEAKER.Size, SNEAKER.Amount, PRICE.SellPrice, SNEAKER.Unit, SNEAKER.Category, SNEAKER.Description FROM SNEAKER INNER JOIN PRICE ON SNEAKER.SneakerID=PRICE.SneakerID");
        listGoods = new ArrayList<>();
        try {
            while(rs.next()){
                listGoods.add(new Goods(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getLong(8), rs.getString(9), rs.getString(10), rs.getString(11)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        rs = database.queryData("SELECT SNEAKER.SneakerID, SNEAKER.Image, SNEAKER.Name, SNEAKER.Brand, SNEAKER.Color, SNEAKER.Size, SNEAKER.Amount, PRICE.SellPrice, SNEAKER.Unit, SNEAKER.Category, SNEAKER.Description FROM SNEAKER INNER JOIN PRICE ON SNEAKER.SneakerID=PRICE.SneakerID WHERE SNEAKER.Category='sneaker'");
        listSneaker = new ArrayList<>();
        try {
            while(rs.next()){
                listSneaker.add(new Goods(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getLong(8), rs.getString(9), rs.getString(10), rs.getString(11)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        rs = database.queryData("SELECT SNEAKER.SneakerID, SNEAKER.Image, SNEAKER.Name, SNEAKER.Brand, SNEAKER.Color, SNEAKER.Size, SNEAKER.Amount, PRICE.SellPrice, SNEAKER.Unit, SNEAKER.Category, SNEAKER.Description FROM SNEAKER INNER JOIN PRICE ON SNEAKER.SneakerID=PRICE.SneakerID WHERE SNEAKER.Category='sandals'");
        listSandal = new ArrayList<>();
        try {
            while(rs.next()){
                listSandal.add(new Goods(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getLong(8), rs.getString(9), rs.getString(10), rs.getString(11)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        rs = database.queryData("SELECT SNEAKER.SneakerID, SNEAKER.Image, SNEAKER.Name, SNEAKER.Brand, SNEAKER.Color, SNEAKER.Size, SNEAKER.Amount, PRICE.SellPrice, SNEAKER.Unit, SNEAKER.Category, SNEAKER.Description FROM SNEAKER INNER JOIN PRICE ON SNEAKER.SneakerID=PRICE.SneakerID WHERE SNEAKER.Category='accessory'");
        listAccessory = new ArrayList<>();
        try {
            while(rs.next()){
                listAccessory.add(new Goods(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getLong(8), rs.getString(9), rs.getString(10), rs.getString(11)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        homeFrame = new JFrame();
        homeFrame.setSize(1200, 650);
        homeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        homeFrame.setUndecorated(true);
        homeFrame.setBackground(new Color(1, 1, 1, 0));
        
        ImagePanel mainPanel = new ImagePanel("src//main//java//res//background.png", homeFrame);
        mainPanel.setLayout(new BorderLayout(0, 0));
        
        JPanel navPanel = new JPanel(new FlowLayout(0, 0, 0));
        navPanel.setPreferredSize(new Dimension(250, 650));
        navPanel.setBackground(Color.WHITE);
        
        JLabel logo = new JLabel(new ImageIcon(ImageController.getScaledImage(new ImageIcon("src//main//java//res//Logo-03.png").getImage(), 170, 0)));
        logo.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 0));
        
        listNavButton = new ListView(listCategory, 250, listCategory.size()*50);
        listNavButton.setItemClickListener(new CustomMouseClick() {
            @Override
            public void CustomItemClick(Item item) {
//                System.out.println("Main: "+item.getPosition());
                switch(item.getPosition()){
                    case 0:
                        card.show(centerHomePage, "homecard");
                        prevCard = "homecard";
                        break;
                    case 1:
                        card.show(centerHomePage, "sneakerscard");
                        prevCard = "sneakerscard";
                        break;
                    case 2:
                        card.show(centerHomePage, "sandalscard");
                        prevCard = "sandalscard";
                        break;
                    case 3:
                        card.show(centerHomePage, "accessoriescard");
                        prevCard = "accessoriescard";
                        break;
                    case 4:
                        if(accountID.equals("")){
                            new Dialog(250, 100, "Đăng nhập để vào giỏ hàng");
                        }
                        else{
                            card.show(centerHomePage, "cartcard");
                            prevCard = "cartcard";
                        }
                        break;
                    case 5:
                        if(accountID.equals("")){
                            homeFrame.dispose();
                            new Login();
                        }
                        else{
                            account.updateAccountLayout();
                            card.show(centerHomePage, "accountcard");
                            prevCard = "accountcard";
                        }
                        break;
                    case 6:
                        card.show(centerHomePage, "contactcard");
                        prevCard = "contactcard";
                        break;
                }
                infoGoods.setPrevCard(prevCard);
            }
        });
        CustomScrollPane scrollNavPane = new CustomScrollPane(listNavButton, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER, 250, listCategory.size()*50);
        
        navPanel.add(logo);
        navPanel.add(scrollNavPane);
        
        JPanel navPanelAndSeparator = new JPanel(new FlowLayout(0, 0, 0));
        JSeparator j1 = new JSeparator(JSeparator.VERTICAL);
        j1.setForeground(Color.LIGHT_GRAY);
        j1.setBackground(Color.LIGHT_GRAY);
        navPanelAndSeparator.add(navPanel);
        navPanelAndSeparator.add(j1);
        
        centerHomePage = new JPanel();
        centerHomePage.setLayout(new CardLayout(0, 0));
        centerHomePage.setBackground(new Color(0, 0, 0, 0));
        
        card = (CardLayout) centerHomePage.getLayout();
        infoGoods = new InformationGood(card, centerHomePage, prevCard, currentCustomerID);
        if(!accountID.equals("")){
            infoGoods.setUpdateCartListener(new AddCartListener() {
                @Override
                public void updateCart() {
                    ResultSet rsCart = database.queryData("SELECT COUNT(*) FROM CART WHERE CustomerID='"+currentCustomerID+"'");
                    try {
                        if(rsCart.next()){
                            listCategory.set(4, "Giỏ hàng ("+rsCart.getString(1)+")");
                            listNavButton.setItem(listCategory.get(4), 4);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    cart.updateCartList();
                }

                @Override
                public void updateList() {
                    cart.updateCartList();
                }
            });
        }

        home = new MainPage("Trang chủ", listGoods, card, centerHomePage, "", this.infoGoods);
        sneakers = new MainPage("Sneakers", listSneaker, card, centerHomePage, "sneaker", this.infoGoods);
        sandals = new MainPage("Sandals", listSandal, card, centerHomePage, "sandals", this.infoGoods);
        accessories = new MainPage("Phụ kiện", listAccessory, card, centerHomePage, "accessory", this.infoGoods);
//        cart = new MainPage("Giỏ hàng", listGoods, card, centerHomePage);
        if(!accountID.equals("")){
            cart = new CartLayout(currentCustomerID);
            cart.setChangeCartListener(new AddCartListener() {
                @Override
                public void updateCart() {
                    ResultSet rsCart = database.queryData("SELECT COUNT(*) FROM CART WHERE CustomerID='"+currentCustomerID+"'");
                    try {
                        if(rsCart.next()){
                            listCategory.set(4, "Giỏ hàng ("+rsCart.getString(1)+")");
                            listNavButton.setItem(listCategory.get(4), 4);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                @Override
                public void updateList() {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

            });
    //        account = new MainPage("Tài khoản", listGoods, card, centerHomePage);
            account = new AccountLayout(this.currentCustomerID);
            account.setLogoutListener(new MouseClick() {
                @Override
                public void MouseClick() {
                    new Login();
                    homeFrame.dispose();
                }
            });
        }
            
        contact = new MainPage("Liên hệ", listGoods, card, centerHomePage, "contact", this.infoGoods);
        
        centerHomePage.add(home,"homecard");
        centerHomePage.add(sneakers,"sneakerscard");
        centerHomePage.add(sandals,"sandalscard");
        centerHomePage.add(accessories,"accessoriescard");
        if(!accountID.equals("")){
            centerHomePage.add(cart,"cartcard");
            centerHomePage.add(account,"accountcard");
        }
        else{
            centerHomePage.add(new JPanel(),"cartcard");
            centerHomePage.add(new JPanel(),"accountcard");
        }
        centerHomePage.add(contact,"contactcard");
        centerHomePage.add(infoGoods, "infogood");
        
        mainPanel.add(navPanelAndSeparator, BorderLayout.WEST);
        mainPanel.add(centerHomePage, BorderLayout.CENTER);
        
        homeFrame.add(mainPanel);
        
        homeFrame.setVisible(true);
        
    }
    
}
