/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layout;

import CustomComponents.CustomScrollPane;
import CustomComponents.ListViewImage;
import CustomComponents.RoundedPanel;
import CustomListener.AddCartListener;
import CustomListener.MouseClickReturnString;
import CustomListener.UpdateCartListener;
import Database.DatabaseManager;
import Entity.Cart;
import Tools.FormatText;
import Tools.IDGenerator;
import java.awt.BorderLayout;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

/**
 *
 * @author letan
 */
public class CartLayout extends JPanel{
    
    private ListViewImage listView;
    private ArrayList<Cart> listCart = new ArrayList<>();
    
    private DatabaseManager database = new DatabaseManager("BitSneaker");
    
    private String currentCustomerID;
    
    private AddCartListener listener;
    
    private long totalCart = 0;
    
    private JLabel total = new JLabel();
    
    private RoundedPanel btnOrder;
    
    public CartLayout(String currentCustomerID){
        
        this.currentCustomerID = currentCustomerID;
        
        ResultSet rsCart = database.queryData("SELECT SneakerID, Amount FROM CART WHERE CustomerID='"+currentCustomerID+"'");
        try {
            while(rsCart.next()){
                listCart.add(new Cart(rsCart.getString(1), rsCart.getInt(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setLayout(new BorderLayout(0, 0));
        setBackground(Color.WHITE);
        
        JPanel top = new JPanel(new BorderLayout(0, 0));
        top.setPreferredSize(new Dimension(950, 100));
        top.setBackground(Color.WHITE);
        
        JPanel titlePanel = new JPanel(new BorderLayout(0, 0)); 
        titlePanel.setBackground(new Color(0, 0, 0, 0));
        JLabel label = new JLabel("Giỏ hàng");
        label.setFont(new Font("San-Serif", Font.BOLD, 20));
        label.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 0));
        titlePanel.add(label, BorderLayout.SOUTH);
        
        top.add(titlePanel, BorderLayout.WEST);
        
        JPanel bottom = new JPanel();
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));
        bottom.setPreferredSize(new Dimension(950, 100));
        bottom.setBackground(Color.WHITE);
        bottom.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        
        this.totalCart = calTotal();
        total = new JLabel("Tổng cộng: "+FormatText.covertCurrentcyFormat(totalCart));
        total.setFont(new Font("San-Serif", Font.BOLD, 20));
        
        JPanel btnContain = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        btnContain.setPreferredSize(new Dimension(150, 50));
        btnContain.setBackground(Color.WHITE);
        btnContain.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 25));
        btnOrder = new RoundedPanel(150, 50, new Color(52, 132, 240), 5);
        btnOrder.setLayout(new GridLayout(1,1));
        JLabel orderText = new JLabel("Đặt hàng");
        orderText.setHorizontalAlignment(SwingConstants.CENTER);
        orderText.setFont(new Font("San-Serif", Font.PLAIN, 17));
        orderText.setForeground(Color.WHITE);
        btnOrder.add(orderText);
        btnOrder.setPreferredSize(new Dimension(150, 50));
        
        btnContain.add(btnOrder);
        
        bottom.add(total);
        bottom.add(Box.createHorizontalGlue());
        bottom.add(btnContain);
        
        JPanel center = new JPanel(new FlowLayout(0, 15, 5));
        center.setBackground(Color.WHITE);
        
        listView = new ListViewImage(listCart);
        listView.setItemCartChanging(new UpdateCartListener() {
            @Override
            public void updateCart(Cart cart) {
                if(cart.getAmount()>0){
                    database.queryUpdate("UPDATE CART SET Amount = '"+cart.getAmount()+"' WHERE CustomerID = '"+currentCustomerID+"' AND SneakerID = '"+cart.getGoodID()+"'");
                }
                else if(cart.getAmount()==0){
                    database.queryUpdate("DELETE FROM CART WHERE CustomerID = '"+currentCustomerID+"' AND SneakerID = '"+cart.getGoodID()+"'");
                }
                updateCartList();
            }
        });
        
        center.add(listView);
        
        CustomScrollPane scrollCenter = new CustomScrollPane(center, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER, 950, 450);
        
        add(top, BorderLayout.NORTH);
        add(bottom, BorderLayout.SOUTH);
        add(scrollCenter, BorderLayout.CENTER);
        
        btnOrder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                btnOrder.setBackGroundColor(new Color(52, 132, 240));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnOrder.setBackGroundColor(new Color(115, 168, 240));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if(listView.getListSize()==0){
                    new Dialog(200, 100, "Giỏ hàng trống");
                }
                else{
                    ResultSet rsAddress = database.queryData("SELECT * FROM ADDRESS WHERE CustomerID='"+currentCustomerID+"'");
                    
                    ArrayList<String> listAddress = new ArrayList<>();
                    
                    try {
                        while(rsAddress.next()){
                            listAddress.add(rsAddress.getString(2));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(CartLayout.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    if(listAddress.size()==0){
                        new Dialog(350, 100, "Cập nhật địa chỉ tài khoản trước");
                    }
                    else{
                        String id = IDGenerator.generateSellOrderID("customer");
                        
                        new Dialog(700, 300, "Hãy chọn địa chỉ muốn giao", listAddress, new MouseClickReturnString() {
                            @Override
                            public void ClickListener(String s) {
                                if(database.queryUpdate("INSERT INTO SELLORDER(SellID, CustomerID, Status, Address) VALUES('"+id+"', '"+currentCustomerID+"', 'pending', N'"+s+"')")){
                                    for(Cart c : listCart){
                                        database.queryUpdate("INSERT INTO SELLDETAILS(SellID, SneakerID, Amount) VALUES('"+id+"', '"+c.getGoodID()+"', '"+c.getAmount()+"')");
                                    }
                                    new Dialog(250, 100, "Đặt hàng thành công");
                                    database.queryUpdate("DELETE FROM CART WHERE CustomerID = '"+currentCustomerID+"'");
                                    updateCartList();
                                }
                                else{
                                    new Dialog(250, 100, "Đặt hàng không thành công");
                                }
                            }
                        });
                    }
                }
            }
            
        });
    }
    
    public void updateCartList(){
        this.listCart.clear();
        ResultSet rsCart = database.queryData("SELECT SneakerID, Amount FROM CART WHERE CustomerID='"+currentCustomerID+"'");
        try {
            while(rsCart.next()){
                this.listCart.add(new Cart(rsCart.getString(1), rsCart.getInt(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
        listView.setList(this.listCart);
        listView.setItemCartChanging(new UpdateCartListener() {
            @Override
            public void updateCart(Cart cart) {
                if(cart.getAmount()>0){
                    database.queryUpdate("UPDATE CART SET Amount = '"+cart.getAmount()+"' WHERE CustomerID = '"+currentCustomerID+"' AND SneakerID = '"+cart.getGoodID()+"'");
                }
                else if(cart.getAmount()==0){
                    database.queryUpdate("DELETE FROM CART WHERE CustomerID = '"+currentCustomerID+"' AND SneakerID = '"+cart.getGoodID()+"'");
                }
                updateCartList();
            }
        });
        this.totalCart = calTotal();
        total.setText("Tổng cộng: "+FormatText.covertCurrentcyFormat(totalCart));
        listener.updateCart();
    }
    
    public void setChangeCartListener(AddCartListener listener){
        this.listener = listener;
    }
    
    private long calTotal(){
        try {
            ResultSet rsTotal = database.queryData("SELECT SUM(PRICE.SellPrice*CART.Amount) FROM CART INNER JOIN PRICE ON CART.SneakerID=PRICE.SneakerID WHERE CART.CustomerID = '"+currentCustomerID+"'");
            if(rsTotal.next()){
                return rsTotal.getLong(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartLayout.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
}
