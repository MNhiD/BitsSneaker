/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layout;

import CustomComponents.CustomScrollPane;
import CustomComponents.GoodGridItem;
import CustomComponents.GridView;
import CustomComponents.InputTextNoTitle;
import CustomComponents.Item;
import CustomComponents.RoundedPanel;
import CustomListener.CustomMouseClick;
import CustomListener.KeyListener;
import Database.DatabaseManager;
import Entity.Goods;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author letan
 */
public class MainPage extends JPanel{
    
    private CardLayout card;
    private JPanel parent;
    private ArrayList<Goods> listGoods;
    
    private Color themeColor = new Color(52, 132, 240);
    private Color themeLightColor = new Color(115, 168, 240);
    
    private InputTextNoTitle searchBar;
    
    private GridView goodGridView;
    
    private String tagLayout = "";
    
    private DatabaseManager database = new DatabaseManager("BitSneaker");
    
    private JPanel listPanel;
    
    private InformationGood infoGood;
    
    
    public MainPage(String title, ArrayList<Goods> list, CardLayout card, JPanel parent, String tagLayout, InformationGood infoGood){
        
        this.tagLayout = tagLayout;
        this.card = card;
        this.parent = parent;
        this.listGoods = list;
        this.infoGood = infoGood;
        
        setLayout(new BorderLayout(0, 0));
        setBackground(Color.WHITE);
        
        JPanel top = new JPanel(new BorderLayout(0, 0));
        top.setPreferredSize(new Dimension(950, 100));
        top.setBackground(Color.WHITE);
        
        JPanel titlePanel = new JPanel(new BorderLayout(0, 0)); 
        titlePanel.setBackground(new Color(0, 0, 0, 0));
        JLabel label = new JLabel(title);
        label.setFont(new Font("San-Serif", Font.BOLD, 20));
        label.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 0));
        titlePanel.add(label, BorderLayout.SOUTH);
        titlePanel.setPreferredSize(new Dimension(125, 100));
        
        JPanel searchPanel = new JPanel(new BorderLayout(0, 0));
        searchPanel.setBackground(new Color(0,0,0,0));
        JPanel searchTextPanel = new JPanel(new FlowLayout(0, 0, 0));
        searchTextPanel.setBackground(new Color(0, 0, 0, 0));
        
        searchBar = new InputTextNoTitle("", Color.WHITE, 700);
        searchBar.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        
        searchBar.setKeyListener(new KeyListener() {
            @Override
            public void KeyTypeListener() {
                updateSearch();
            }
        });
        
        searchTextPanel.setPreferredSize(new Dimension(searchBar.getPreferredSize().width, searchBar.getPreferredSize().height));
        searchTextPanel.add(searchBar);
        
        JPanel searchGroup = new JPanel(new FlowLayout(0, 0, 0));
        searchGroup.setBackground(Color.WHITE);
        
        RoundedPanel btnSearch = new RoundedPanel(30, 30, themeColor, 5);
        btnSearch.setPreferredSize(new Dimension(30, 30));
        
        JPanel p2 = new JPanel();
        p2.setPreferredSize(new Dimension(10, 5));
        
        searchGroup.add(searchTextPanel);
        searchGroup.add(p2);
        searchGroup.add(btnSearch);
        
        searchPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        searchPanel.add(searchGroup, BorderLayout.SOUTH);
        
        top.add(titlePanel, BorderLayout.WEST);
        top.add(searchPanel, BorderLayout.CENTER);
        
        
        listPanel = new JPanel();
        listPanel.setPreferredSize(new Dimension(950, 550));
//        listPanel.setBackground(Color.LIGHT_GRAY);
//        listPanel.add(new GoodGridItem(150, 300, new Goods("1", "TestGood.jpg", "Tên sản phẩm", "Trên brand", "Xanh", "41", 1, 13000000, "đôi", "sneaker")));
        
        goodGridView = new GridView(950, 300, 5, list);
        goodGridView.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 20));
        goodGridView.setItemClickListener(new CustomMouseClick() {
            @Override
            public void CustomItemClick(Item item) {
                System.out.println("GridLayout id: "+item.getPosition());
                infoGood.setCurrentLayout(list.get(item.getPosition()));
                card.show(parent, "infogood");
            }
        });
        
        CustomComponents.CustomScrollPane scrollPaneCenter = new CustomScrollPane(goodGridView, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER, listPanel.getPreferredSize().width, listPanel.getPreferredSize().height);
        
        listPanel.add(scrollPaneCenter);
        
        add(top, BorderLayout.NORTH);
        add(listPanel, BorderLayout.CENTER);
        
    }
    
    public void updateSearch(){
        String s = searchBar.getContent();
        ResultSet rs;
        listGoods.clear();
        if(s.equals("")){
            if(tagLayout.equals("")){
                rs = database.queryData("SELECT SNEAKER.SneakerID, SNEAKER.Image, SNEAKER.Name, SNEAKER.Brand, SNEAKER.Color, SNEAKER.Size, SNEAKER.Amount, PRICE.SellPrice, SNEAKER.Unit, SNEAKER.Category, SNEAKER.Description FROM SNEAKER INNER JOIN PRICE ON SNEAKER.SneakerID=PRICE.SneakerID");
                try {
                    while(rs.next()){
                        listGoods.add(new Goods(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getLong(8), rs.getString(9), rs.getString(10), rs.getString(11)));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                rs = database.queryData("SELECT SNEAKER.SneakerID, SNEAKER.Image, SNEAKER.Name, SNEAKER.Brand, SNEAKER.Color, SNEAKER.Size, SNEAKER.Amount, PRICE.SellPrice, SNEAKER.Unit, SNEAKER.Category, SNEAKER.Description FROM SNEAKER INNER JOIN PRICE ON SNEAKER.SneakerID=PRICE.SneakerID WHERE SNEAKER.Category='"+tagLayout+"'");
                try {
                    while(rs.next()){
                        listGoods.add(new Goods(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getLong(8), rs.getString(9), rs.getString(10), rs.getString(11)));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else{
            if(tagLayout.equals("")){
                rs = database.queryData("SELECT SNEAKER.SneakerID, SNEAKER.Image, SNEAKER.Name, SNEAKER.Brand, SNEAKER.Color, SNEAKER.Size, SNEAKER.Amount, PRICE.SellPrice, SNEAKER.Unit, SNEAKER.Category, SNEAKER.Description FROM SNEAKER INNER JOIN PRICE ON SNEAKER.SneakerID=PRICE.SneakerID WHERE SNEAKER.Name LIKE N'%"+s+"%'");
                try {
                    while(rs.next()){
                        listGoods.add(new Goods(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getLong(8), rs.getString(9), rs.getString(10), rs.getString(11)));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                rs = database.queryData("SELECT SNEAKER.SneakerID, SNEAKER.Image, SNEAKER.Name, SNEAKER.Brand, SNEAKER.Color, SNEAKER.Size, SNEAKER.Amount, PRICE.SellPrice, SNEAKER.Unit, SNEAKER.Category, SNEAKER.Description FROM SNEAKER INNER JOIN PRICE ON SNEAKER.SneakerID=PRICE.SneakerID WHERE SNEAKER.Category='"+tagLayout+"' AND SNEAKER.Name LIKE N'%"+s+"%'");
                try {
                    while(rs.next()){
                        listGoods.add(new Goods(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getLong(8), rs.getString(9), rs.getString(10), rs.getString(11)));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        goodGridView.setList(listGoods);
        CustomComponents.CustomScrollPane scrollPaneCenter = new CustomScrollPane(goodGridView, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER, listPanel.getPreferredSize().width, listPanel.getPreferredSize().height);
        
        listPanel.removeAll();
        listPanel.revalidate();
        listPanel.repaint();
        
        listPanel.add(scrollPaneCenter);
//        add(listPanel, BorderLayout.CENTER);
    }
    
}
