/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomComponents;

import CustomListener.CustomMouseClick;
import CustomListener.MouseClickReturnButton;
import Database.DatabaseManager;
import Entity.Cart;
import Entity.CustomerAccount;
import Entity.Order;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import layout.Dialog;

/**
 *
 * @author letan
 */
public class CustomTabLayout extends JPanel{
    
    private int tabHeight = 40;
    private int width, height;
    
    private CustomerAccount customer;
    
    private TabGridView tabPanel;
    
    private JPanel account = new JPanel();
    private JPanel bought = new JPanel();
    private JPanel pending = new JPanel();
    private JPanel cancel = new JPanel();
    
    private JPanel centerPanel = new JPanel();
    
    private Color themeColor = new Color(52, 132, 240);
    private Color themeLightColor = new Color(115, 168, 240);
    
    private ListViewOrder pendingListView, cancelListView, boughtListView;
    
    private DatabaseManager database = new DatabaseManager("BitSneaker");
    
    //Acount Component
    private TabGridView genderTab;
    private InputText name, phoneNumber, address;
    private RoundedPanel btnSave, btnAdd;
    
    private CardLayout card;
    
    public CustomTabLayout(int w, int h, ArrayList<String> tabList, CustomerAccount customer){
        
        this.width = w;
        this.height = h;
        this.customer = customer;
        
        setLayout(new BorderLayout(0, 0));
        setBackground(Color.WHITE);
        
        tabPanel = new TabGridView(w, tabHeight, tabList);
        tabPanel.setPreferredSize(new Dimension(w, tabHeight));
        
        tabPanel.setItemClickListener(new CustomMouseClick() {
            @Override
            public void CustomItemClick(Item item) {
                tabPanel.setSelectedTabItem(item.getPosition());
                if(item.getPosition()==0){
                    card.show(centerPanel, "accountcard");
                }
                else if(item.getPosition()==1){
                    card.show(centerPanel, "boughtcard");
                }
                else if(item.getPosition()==2){
                    card.show(centerPanel, "pendingcard");
                }
                else if(item.getPosition()==3){
                    card.show(centerPanel, "cancelcard");
                }
            }
        });
        
        centerPanel = new JPanel(new CardLayout(0, 0));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setPreferredSize(new Dimension(w, h-tabHeight));
        
        card = (CardLayout) centerPanel.getLayout();
        
        createAccountLayout();
        centerPanel.add(account, "accountcard");
        createBoughtLayout();
        centerPanel.add(bought, "boughtcard");
        createPendingLayout();
        centerPanel.add(pending, "pendingcard");
        createCancelLayout();
        centerPanel.add(cancel, "cancelcard");
        
        add(tabPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        
    }
    
    public void createAccountLayout(){
        
        account.removeAll();
        account.revalidate();
        account.repaint();
        
        account.setLayout(new FlowLayout(0, 20, 10));
        account.setPreferredSize(new Dimension(this.width, this.height-this.tabHeight));
        account.setBackground(Color.WHITE);
        
        InputText email = new InputText("Email", "", "", ".*", Color.WHITE, 900);
        email.setDefaultContent(this.customer.getAccountID());
        email.setEditable(false);

        name = new InputText("Tên", "Tên", "", ".*", Color.WHITE, 900);
        name.setDefaultContent(this.customer.getName());
        
        JPanel genderChooser = new JPanel(new FlowLayout(0, 0, 0));
        genderChooser.setPreferredSize(new Dimension(this.width-40, 70));
        genderChooser.setBackground(Color.WHITE);
        
        JLabel genderTitle = new JLabel("Giới tính");
        genderTitle.setFont(new Font("San-Serif", Font.PLAIN, 12));
        genderTitle.setPreferredSize(new Dimension(70, 14));
        genderTitle.setBackground(Color.WHITE);
        genderTitle.setForeground(Color.LIGHT_GRAY);
        
        genderTab = new TabGridView(500, 50, new ArrayList<String>(Arrays.asList("Nam", "Nữ", "Khác")));
        genderTab.setPreferredSize(new Dimension(500, 50));
        if(this.customer.getGender().equals("Nam")){
            genderTab.setSelectedTabItem(0);
        }
        else if(this.customer.getGender().equals("Nữ")){
            genderTab.setSelectedTabItem(1);
        }
        else{
            genderTab.setSelectedTabItem(2);
        }
        genderTab.setItemClickListener(new CustomMouseClick() {
            @Override
            public void CustomItemClick(Item item) {
                genderTab.setSelectedTabItem(item.getPosition());
            }
        });
        
        genderChooser.add(genderTitle);
        genderChooser.add(genderTab);
        
        phoneNumber = new InputText("Số điện thoại", "Số điện thoại", "", "^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$", Color.WHITE, 900);
        phoneNumber.setDefaultContent(this.customer.getPhoneNumber());
        
        address = new InputText("Địa chỉ", "Địa chỉ", "", ".*", Color.WHITE, 800);
        
        btnAdd = new RoundedPanel(80, 40, this.themeColor, 5);
        btnAdd.setPreferredSize(new Dimension(80, 40));
        btnAdd.setLayout(new GridLayout(1, 1));
        
        JLabel btnTitleAdd = new JLabel("Thêm");
        btnTitleAdd.setHorizontalAlignment(SwingConstants.CENTER);
        btnTitleAdd.setForeground(Color.WHITE);
        btnTitleAdd.setFont(new Font("San-Serif", Font.PLAIN, 13));
        
        btnAdd.add(btnTitleAdd);
        
        ListView listAddress = new ListView(this.customer.getAddressList(), 900, 0, 40);
        listAddress.setItemClickListener(new CustomMouseClick() {
            @Override
            public void CustomItemClick(Item item) {
                new Dialog(250, 100, "Bạn muốn xóa địa chị này?", "Xóa", "Không", new MouseClickReturnButton() {
                    @Override
                    public void ClickListener(int i) {
                        if(i==0){
                            customer.getAddressList().remove(item.getPosition());
                        }
                        createAccountLayout();
                    }
                });
            }
        });
        
        btnSave = new RoundedPanel(100, 50, this.themeColor, 5);
        btnSave.setPreferredSize(new Dimension(100, 50));
        btnSave.setLayout(new GridLayout(1, 1));
        
        JLabel btnTitle = new JLabel("Lưu");
        btnTitle.setHorizontalAlignment(SwingConstants.CENTER);
        btnTitle.setForeground(Color.WHITE);
        btnTitle.setFont(new Font("San-Serif", Font.PLAIN, 17));
        
        btnSave.add(btnTitle);
        
        JPanel accountContainer = new JPanel(new FlowLayout(0, 20, 10));
        accountContainer.setBackground(Color.WHITE);
        
        accountContainer.add(email);
        accountContainer.add(name);
        accountContainer.add(genderChooser);
        accountContainer.add(phoneNumber);
        accountContainer.add(address);
        accountContainer.add(btnAdd);
        accountContainer.add(listAddress);
        accountContainer.add(btnSave);
        
        accountContainer.setPreferredSize(new Dimension(this.width, 500+this.customer.getAddressList().size()*45));
        
        CustomScrollPane accountScroll = new CustomScrollPane(accountContainer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER, this.width, this.height-this.tabHeight);
        accountScroll.setPreferredSize(new Dimension(this.width, this.height-this.tabHeight));
        accountScroll.setBackground(Color.WHITE);
        
        account.add(accountScroll);
        
//        account.add(email);
//        account.add(name);
//        account.add(genderChooser);
//        account.add(phoneNumber);
//        account.add(address);
//        account.add(btnAdd);
//        account.add(listAddress);
//        account.add(btnSave);
        
        btnSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                btnSave.setBackGroundColor(themeColor);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnSave.setBackGroundColor(themeLightColor);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                updateCustomerInformation();
            }
            
        });
        
        btnAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                btnAdd.setBackGroundColor(themeColor);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAdd.setBackGroundColor(themeLightColor);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                customer.adddAddress(address.getContent());
                createAccountLayout();
            }
            
        });
    }
    
    private void updateCustomerInformation(){
        this.customer.setName(this.name.getContent());
        if(this.genderTab.getSelectedIndex()==0){
            this.customer.setGender("Nam");
        }
        else if(this.genderTab.getSelectedIndex()==1){
            this.customer.setGender("Nữ");
        }
        else{
            this.customer.setGender("Khác");
        }
        this.customer.setPhoneNumber(this.phoneNumber.getContent());
        
        database.queryUpdate("UPDATE CUSTOMER SET PhoneNumber = '"+this.customer.getPhoneNumber()+"', Name = N'"+this.customer.getName()+"', Gender = N'"+this.customer.getGender()+"' WHERE CustomerID = '"+this.customer.getCustomerID()+"'");
        
        database.queryUpdate("DELETE ADDRESS WHERE CustomerID = '"+this.customer.getCustomerID()+"'");
        for(int i=0; i<this.customer.getAddressList().size(); i++){
            database.queryUpdate("INSERT INTO ADDRESS(CustomerID, Address) VALUES('"+this.customer.getCustomerID()+"', N'"+this.customer.getAddressList().get(i)+"')");
        }
        
    }
    
    public void createBoughtLayout(){
        bought.removeAll();
        bought.revalidate();
        bought.repaint();
        
        bought.setLayout(new FlowLayout(0, 20, 10));
        bought.setPreferredSize(new Dimension(this.width, this.height-this.tabHeight));
        bought.setBackground(Color.WHITE);
        
        ResultSet rsBought = database.queryData("SELECT INFO.SellID, INFO.CustomerID, INFO.SneakerID, INFO.Amount, INFO.Sale, INFO.Address, PRICE.SellPrice FROM (SELECT SELLORDER.SellID, SELLORDER.CustomerID, SELLDETAILS.SneakerID, SELLDETAILS.Amount, SELLDETAILS.Sale, SELLDETAILS.VAT, SELLORDER.Address FROM SELLORDER INNER JOIN SELLDETAILS ON SELLORDER.SellID=SELLDETAILS.SellID WHERE SELLORDER.CustomerID='"+this.customer.getCustomerID()+"' AND SELLORDER.Status='paid') AS INFO INNER JOIN PRICE ON INFO.SneakerID = PRICE.SneakerID");
        
        ArrayList<Order> listOrder = new ArrayList<>();
        try {
            while(rsBought.next()){
                listOrder.add(new Order(rsBought.getString(1), rsBought.getInt(2), rsBought.getString(3), rsBought.getInt(4), rsBought.getInt(5), rsBought.getString(6), rsBought.getLong(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomTabLayout.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        boughtListView = new ListViewOrder(listOrder);
        
        JPanel listViewContainer = new JPanel(new FlowLayout(0, 0, 0));
        listViewContainer.add(boughtListView);
        listViewContainer.setBackground(Color.WHITE);
        
        CustomScrollPane boughtScroll = new CustomScrollPane(listViewContainer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER, this.width, this.height-this.tabHeight);
        boughtScroll.setPreferredSize(new Dimension(this.width, this.height-this.tabHeight-15));
        boughtScroll.setBackground(Color.WHITE);
        
        bought.add(boughtScroll);
    }
    
    public void createPendingLayout(){
        pending.removeAll();
        pending.revalidate();
        pending.repaint();
        
        pending.setLayout(new FlowLayout(0, 20, 10));
        pending.setPreferredSize(new Dimension(this.width, this.height-this.tabHeight));
        pending.setBackground(Color.WHITE);
        
        ResultSet rsPending = database.queryData("SELECT INFO.SellID, INFO.CustomerID, INFO.SneakerID, INFO.Amount, INFO.Sale, INFO.Address, PRICE.SellPrice FROM (SELECT SELLORDER.SellID, SELLORDER.CustomerID, SELLDETAILS.SneakerID, SELLDETAILS.Amount, SELLDETAILS.Sale, SELLDETAILS.VAT, SELLORDER.Address FROM SELLORDER INNER JOIN SELLDETAILS ON SELLORDER.SellID=SELLDETAILS.SellID WHERE SELLORDER.CustomerID='"+this.customer.getCustomerID()+"' AND SELLORDER.Status='pending') AS INFO INNER JOIN PRICE ON INFO.SneakerID = PRICE.SneakerID");
        
        ArrayList<Order> listOrder = new ArrayList<>();
        try {
            while(rsPending.next()){
                listOrder.add(new Order(rsPending.getString(1), rsPending.getInt(2), rsPending.getString(3), rsPending.getInt(4), rsPending.getInt(5), rsPending.getString(6), rsPending.getLong(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomTabLayout.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        pendingListView = new ListViewOrder(listOrder);
        
        JPanel listViewContainer = new JPanel(new FlowLayout(0, 0, 0));
        listViewContainer.add(pendingListView);
        listViewContainer.setBackground(Color.WHITE);
        
        CustomScrollPane pendingScroll = new CustomScrollPane(listViewContainer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER, this.width, this.height-this.tabHeight);
        pendingScroll.setPreferredSize(new Dimension(this.width, this.height-this.tabHeight-15));
        pendingScroll.setBackground(Color.WHITE);
        
        pending.add(pendingScroll);
    }
    
    public void createCancelLayout(){
        cancel.removeAll();
        cancel.revalidate();
        cancel.repaint();
        
        cancel.setLayout(new FlowLayout(0, 20, 10));
        cancel.setPreferredSize(new Dimension(this.width, this.height-this.tabHeight));
        cancel.setBackground(Color.WHITE);
        
        ResultSet rsCancel = database.queryData("SELECT INFO.SellID, INFO.CustomerID, INFO.SneakerID, INFO.Amount, INFO.Sale, INFO.Address, PRICE.SellPrice FROM (SELECT SELLORDER.SellID, SELLORDER.CustomerID, SELLDETAILS.SneakerID, SELLDETAILS.Amount, SELLDETAILS.Sale, SELLDETAILS.VAT, SELLORDER.Address FROM SELLORDER INNER JOIN SELLDETAILS ON SELLORDER.SellID=SELLDETAILS.SellID WHERE SELLORDER.CustomerID='"+this.customer.getCustomerID()+"' AND SELLORDER.Status='cancel') AS INFO INNER JOIN PRICE ON INFO.SneakerID = PRICE.SneakerID");
        
        ArrayList<Order> listOrder = new ArrayList<>();
        try {
            while(rsCancel.next()){
                listOrder.add(new Order(rsCancel.getString(1), rsCancel.getInt(2), rsCancel.getString(3), rsCancel.getInt(4), rsCancel.getInt(5), rsCancel.getString(6), rsCancel.getLong(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomTabLayout.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        cancelListView = new ListViewOrder(listOrder);
        
        JPanel listViewContainer = new JPanel(new FlowLayout(0, 0, 0));
        listViewContainer.add(cancelListView);
        listViewContainer.setBackground(Color.WHITE);
        
        CustomScrollPane cancelScroll = new CustomScrollPane(listViewContainer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER, this.width, this.height-this.tabHeight);
        cancelScroll.setPreferredSize(new Dimension(this.width, this.height-this.tabHeight-15));
        cancelScroll.setBackground(Color.WHITE);
        
        cancel.add(cancelScroll);
    }
    
    public void setCustomer(CustomerAccount customer){
        this.customer = customer;
    }
    
}
