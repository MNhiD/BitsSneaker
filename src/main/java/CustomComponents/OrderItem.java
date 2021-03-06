/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomComponents;

import CustomListener.UpdateCartListener;
import Database.DatabaseManager;
import Entity.Cart;
import Entity.Goods;
import Entity.Order;
import Tools.FormatText;
import Tools.ImageController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author letan
 */
public class OrderItem extends Item{
    
    private DatabaseManager database = new DatabaseManager("BitSneaker");
    
    private Goods g;
    
//    private UpdateCartListener listener;
    
    public OrderItem(Order order){
        
        ResultSet rs = database.queryData("SELECT SNEAKER.SneakerID, SNEAKER.Image, SNEAKER.Name, SNEAKER.Brand, SNEAKER.Color, SNEAKER.Size, SNEAKER.Amount, PRICE.SellPrice, SNEAKER.Unit, SNEAKER.Category, SNEAKER.Description FROM SNEAKER INNER JOIN PRICE ON SNEAKER.SneakerID=PRICE.SneakerID AND SNEAKER.SneakerID='"+order.getSneakerID()+"'");
        try {
            rs.next();
            g = new Goods(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getLong(8), rs.getString(9), rs.getString(10), rs.getString(11));
        } catch (SQLException ex) {
            Logger.getLogger(ImageGoodsItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setBackground(Color.WHITE);
        setLayout(new FlowLayout(0, 0, 0));    
//        setPreferredSize(new Dimension(950, 100));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        JLabel img = new JLabel(new ImageIcon(ImageController.getScaledImage(new ImageIcon("src//main//java//res//goods//"+g.getImg()).getImage(), 0, 100)));
        img.setPreferredSize(new Dimension(100, 100));
        
        JPanel infoPanel = new JPanel(new FlowLayout(0, 0, 0));
        infoPanel.setBackground(Color.WHITE);
        
        JLabel name = new JLabel(g.getName());
        name.setPreferredSize(new Dimension(500, 20));
        name.setFont(new Font("San-Serif", Font.BOLD, 15));
        name.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        JLabel sellID = new JLabel("M?? ????n: "+order.getSellID());
        sellID.setPreferredSize(new Dimension(500, 20));
        sellID.setFont(new Font("San-Serif", Font.PLAIN, 13));
        sellID.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        JLabel amount = new JLabel("S??? l?????ng: "+order.getAmount());
        amount.setPreferredSize(new Dimension(500, 20));
        amount.setFont(new Font("San-Serif", Font.PLAIN, 13));
        amount.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        JLabel sale = new JLabel("Sale: "+(100-order.getSale())+"%");
        sale.setPreferredSize(new Dimension(500, 20));
        sale.setFont(new Font("San-Serif", Font.PLAIN, 13));
        sale.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
//        JLabel price = new JLabel("????n gi??: "+order.getPrice()+"");
        JLabel price = new JLabel("????n gi??: "+FormatText.covertCurrentcyFormat(order.getPrice()));
        price.setPreferredSize(new Dimension(500, 20));
//        price.setFont(new Font("San-Serif", Font.BOLD, 15));
        sale.setFont(new Font("San-Serif", Font.PLAIN, 13));
//        price.setForeground(new Color(52, 132, 240));
        price.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        
        infoPanel.setPreferredSize(new Dimension(500, 100));
        
        infoPanel.add(name);
        infoPanel.add(sellID);
        infoPanel.add(amount);
        infoPanel.add(sale);
        infoPanel.add(price);
        
        JPanel amountControl = new JPanel(new GridLayout(1, 1));
        amountControl.setBackground(Color.WHITE);
        amountControl.setPreferredSize(new Dimension(100, 100));
        
//        JLabel totalPrice = new JLabel(Math.round(order.getPrice()*((float) order.getSale()/100)*order.getAmount())+"");
        JLabel totalPrice = new JLabel(FormatText.covertCurrentcyFormat(Math.round(order.getPrice()*((float) order.getSale()/100)*order.getAmount())));
        totalPrice.setPreferredSize(new Dimension(150, 20));
        totalPrice.setFont(new Font("San-Serif", Font.BOLD, 15));
//        sale.setFont(new Font("San-Serif", Font.PLAIN, 13));
        totalPrice.setForeground(new Color(52, 132, 240));
        totalPrice.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        
//        if(cart.getAmount()==g.getAmount()){
//            btnAdd.setBackGroundColor(Color.LIGHT_GRAY);
//        }
//        
//        btnSub.setLayout(new GridLayout(1, 1));
//        JLabel plus = new JLabel("+");
//        plus.setHorizontalAlignment(JLabel.CENTER);
//        plus.setFont(new Font("San-Serif", Font.PLAIN, 15));
//        JLabel sub = new JLabel("-");
//        sub.setHorizontalAlignment(SwingConstants.CENTER);
//        sub.setFont(new Font("San-Serif", Font.PLAIN, 15));
//        btnSub.add(sub);
//        btnSub.setPreferredSize(new Dimension(40, 40));
//        btnAdd.setLayout(new GridLayout(1, 1));
//        btnAdd.add(plus);
//        btnAdd.setPreferredSize(new Dimension(40, 40));
//        numberInput.setText(String.valueOf(cart.getAmount()));
//        numberInput.setHorizontalAlignment(SwingConstants.CENTER);
//        numberInput.setPreferredSize(new Dimension(50, 30));
//        numberInput.setFont(new Font("San-Serif", Font.PLAIN, 15));
//        
//        amountControl.add(btnSub);
//        amountControl.add(numberInput);
//        amountControl.add(btnAdd);
        amountControl.add(totalPrice);
        
        JPanel infoAndAmountPanel = new JPanel();
        infoAndAmountPanel.setLayout(new BoxLayout(infoAndAmountPanel, BoxLayout.X_AXIS));
        infoAndAmountPanel.setPreferredSize(new Dimension(700, 100));
        infoAndAmountPanel.setBackground(Color.WHITE);
        
        infoAndAmountPanel.add(infoPanel);
        infoAndAmountPanel.add(Box.createHorizontalGlue());
        infoAndAmountPanel.add(amountControl);
        
        add(img);
        add(infoAndAmountPanel);
        
//        btnSub.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if(cart.getAmount()>0){
//                    cart.setAmount(cart.getAmount()-1);
//                    listener.updateCart(cart);
//                    numberInput.setText(String.valueOf(cart.getAmount()));
//                }
//                if(cart.getAmount()<g.getAmount()){
//                    btnAdd.setBackGroundColor(new Color(52, 132, 240));
//                }
//                if(cart.getAmount()==0){
//                    listener.updateCart(cart);
//                }
//                
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//                btnSub.setBackGroundColor(new Color(52, 132, 240));
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                btnSub.setBackGroundColor(new Color(115, 168, 240));
//            }
//            
//            
//            
//        });
//        
//        btnAdd.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if(cart.getAmount()<g.getAmount()){
//                    cart.setAmount(cart.getAmount()+1);
//                    listener.updateCart(cart);
//                }
//                if(cart.getAmount()>1){
//                    btnSub.setBackGroundColor(new Color(52, 132, 240));
//                }
//                if(cart.getAmount()>=g.getAmount()){
//                    btnAdd.setBackGroundColor(Color.LIGHT_GRAY);
//                }
//                numberInput.setText(String.valueOf(cart.getAmount()));
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//                if(cart.getAmount()<g.getAmount()){
//                    btnAdd.setBackGroundColor(new Color(52, 132, 240));
//                }
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                if(cart.getAmount()<g.getAmount()){
//                    btnAdd.setBackGroundColor(new Color(115, 168, 240));
//                }
//            }
//            
//        });
        
    }
    
//    public void setChangeCartItemListener(UpdateCartListener listener){
//        this.listener = listener;
//    }
    
}

