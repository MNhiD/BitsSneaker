/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layout;

import CustomComponents.CustomScrollPane;
import CustomComponents.InputTextNoTitle;
import CustomComponents.RoundedPanel;
import CustomComponents.WrapText;
import CustomListener.AddCartListener;
import Database.DatabaseManager;
import Entity.Cart;
import Entity.Goods;
import Tools.FormatText;
import Tools.ImageController;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.Utilities;

/**
 *
 * @author letan
 */
public class InformationGood extends JPanel{
    
    private CardLayout containCard;
    private JPanel parent;
    private String prevCard;
    
    private Goods goods;
    
    private int number = 1;
    
    private JLabel mainImage = new JLabel();
    private WrapText mainName = new WrapText(28, new Font("San-Serif", Font.BOLD, 20));
    private JLabel mainPrice = new JLabel();
    private JLabel mainBrand = new JLabel();
    private JLabel mainColor = new JLabel();
    private JLabel mainSize = new JLabel();
    private JLabel mainAmountUnit = new JLabel();
    
    private RoundedPanel btnSub = new RoundedPanel(40, 40, Color.LIGHT_GRAY, 3);
    private RoundedPanel btnAdd = new RoundedPanel(40, 40, new Color(52, 132, 240), 3);
    private JTextField numberInput = new JTextField();
    
    private CustomScrollPane scrollInfoPane;
    private JPanel inforPanel;
    
    private AddCartListener listener;
    
    DatabaseManager database = new DatabaseManager("BitSneaker");
            
    private static WrapText mainDescription = new WrapText(35, new Font("San-Serif", Font.PLAIN, 17));
    
    public InformationGood(CardLayout containCard, JPanel parent, String prevCard, String customerID){
        
        this.containCard = containCard;
        this.parent = parent;
        this.prevCard = prevCard;
        
        setLayout(new BorderLayout(0, 0));
        setBackground(Color.WHITE);
        
        JPanel top = new JPanel(new BorderLayout(0, 0));
        top.setPreferredSize(new Dimension(950, 100));
        top.setBackground(Color.WHITE);
        
        JPanel back = new JPanel(new BorderLayout(0, 0));
//        back.setPreferredSize(new Dimension(100, 100));
        back.setBackground(Color.WHITE);
        
        JLabel backText = new JLabel("Trở về");
        backText.setFont(new Font("San-Serif", Font.PLAIN, 15));
        JLabel iconBack = new JLabel(new ImageIcon(ImageController.getScaledImage(new ImageIcon("src//main//java//res//back.png").getImage(), 0, backText.getPreferredSize().height)));
        
        RoundedPanel btnBack = new RoundedPanel(85, 50, Color.WHITE, 10);
        btnBack.add(iconBack);
        btnBack.add(backText);
        btnBack.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 20));
        
        back.add(btnBack, BorderLayout.SOUTH);
        
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showCard();
            }
            
        });
        
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                btnBack.setBackGroundColor(Color.WHITE);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnBack.setBackGroundColor(new Color(176, 231, 255));
            }
            
        });
        
        top.add(back, BorderLayout.WEST);
        
        JPanel centerLayout = new JPanel(new BorderLayout(0, 0));
        centerLayout.setBackground(Color.WHITE);
        
//        mainImage = new JLabel(new ImageIcon(ImageController.getScaledImage(new ImageIcon("src//main//java//res//goods//001.jpg").getImage(), 400, 0)));
        mainImage.setPreferredSize(new Dimension(400, 550));
        
        inforPanel = new JPanel(new FlowLayout(0, 0, 0));
        inforPanel.setBackground(Color.WHITE);
        
        mainName.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));
        mainName.setBackground(Color.WHITE);

        mainPrice.setBorder(BorderFactory.createEmptyBorder(25, 20, 0, 20));
        mainPrice.setPreferredSize(new Dimension(550, 50));
        mainPrice.setFont(new Font("San-Serif", Font.BOLD, 20));
        mainPrice.setForeground(new Color(52, 132, 240));
        
        mainBrand.setBorder(BorderFactory.createEmptyBorder(25, 20, 0, 20));
        mainBrand.setPreferredSize(new Dimension(550, 50));
        mainBrand.setFont(new Font("San-Serif", Font.PLAIN, 17));
        
        mainColor.setBorder(BorderFactory.createEmptyBorder(25, 20, 0, 20));
        mainColor.setPreferredSize(new Dimension(550, 50));
        mainColor.setFont(new Font("San-Serif", Font.PLAIN, 17));
        
        mainSize.setBorder(BorderFactory.createEmptyBorder(25, 20, 0, 20));
        mainSize.setPreferredSize(new Dimension(550, 50));
        mainSize.setFont(new Font("San-Serif", Font.PLAIN, 17));
        
        mainAmountUnit.setBorder(BorderFactory.createEmptyBorder(25, 20, 0, 20));
        mainAmountUnit.setPreferredSize(new Dimension(550, 50));
        mainAmountUnit.setFont(new Font("San-Serif", Font.PLAIN, 17));
        
        JLabel mainNumberLabel = new JLabel("Số lượng");
        mainNumberLabel.setBorder(BorderFactory.createEmptyBorder(40, 20, 10, 20));
        mainNumberLabel.setPreferredSize(new Dimension(550, 50));
        mainNumberLabel.setFont(new Font("San-Serif", Font.PLAIN, 13));
        mainNumberLabel.setForeground(Color.GRAY);
        
        JPanel subAddNumberPanel = new JPanel(new FlowLayout(0, 10, 10));
        subAddNumberPanel.setPreferredSize(new Dimension(550, 50));
        subAddNumberPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        subAddNumberPanel.setBackground(Color.WHITE);
        
        btnSub.removeAll();
        btnSub.revalidate();
        btnSub.repaint();
        btnAdd.removeAll();
        btnAdd.revalidate();
        btnAdd.repaint();
        
        btnSub.setLayout(new GridLayout(1, 1));
        JLabel plus = new JLabel("+");
        plus.setHorizontalAlignment(JLabel.CENTER);
        plus.setFont(new Font("San-Serif", Font.PLAIN, 15));
        JLabel sub = new JLabel("-");
        sub.setHorizontalAlignment(SwingConstants.CENTER);
        sub.setFont(new Font("San-Serif", Font.PLAIN, 15));
        btnSub.add(sub);
        btnSub.setPreferredSize(new Dimension(40, 40));
        btnAdd.setLayout(new GridLayout(1, 1));
        btnAdd.add(plus);
        btnAdd.setPreferredSize(new Dimension(40, 40));
        numberInput.setText(String.valueOf(number));
        numberInput.setHorizontalAlignment(SwingConstants.CENTER);
        numberInput.setPreferredSize(new Dimension(50, 30));
        numberInput.setFont(new Font("San-Serif", Font.PLAIN, 15));
        
        subAddNumberPanel.add(btnSub);
        subAddNumberPanel.add(numberInput);
        subAddNumberPanel.add(btnAdd);
        
        RoundedPanel btnOrder = new RoundedPanel(150, 50, new Color(52, 132, 240), 5);
        btnOrder.setLayout(new GridLayout(1,1));
        JLabel orderText = new JLabel("Thêm vào giỏ hàng");
        orderText.setFont(new Font("San-Serif", Font.BOLD, 15));
        orderText.setForeground(Color.WHITE);
        orderText.setHorizontalAlignment(SwingConstants.CENTER);
        btnOrder.add(orderText);
        btnOrder.setPreferredSize(new Dimension(150, 50));
        
        JPanel btnOrderContain = new JPanel(new FlowLayout(0, 0, 0));
        btnOrderContain.setBorder(BorderFactory.createEmptyBorder(40, 20, 0, 0));
        btnOrderContain.add(btnOrder);
        btnOrderContain.setBackground(Color.WHITE);
        
        mainDescription.setBorder(BorderFactory.createEmptyBorder(40, 20, 0, 20));
        
        inforPanel.add(mainName);
        inforPanel.add(mainPrice);
        inforPanel.add(mainBrand);
        inforPanel.add(mainColor);
        inforPanel.add(mainSize);
        inforPanel.add(mainAmountUnit);
        inforPanel.add(mainNumberLabel);
        inforPanel.add(subAddNumberPanel);
        inforPanel.add(btnOrderContain);
        inforPanel.add(mainDescription);
        
        inforPanel.setPreferredSize(new Dimension(550, mainName.getRows()*30+75+75+75+75+75+100+50+90+40+mainDescription.getRows()*25));
        
        scrollInfoPane = new CustomScrollPane(inforPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER, 550, 550);
        
        centerLayout.add(mainImage, BorderLayout.WEST);
        centerLayout.add(scrollInfoPane, BorderLayout.CENTER);
        
        add(top, BorderLayout.NORTH);
        add(centerLayout, BorderLayout.CENTER);
        
        btnSub.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(number>1){
                    number--;
                }
                if(number<goods.getAmount()){
                    btnAdd.setBackGroundColor(new Color(52, 132, 240));
                }
                if(number<=1){
                    btnSub.setBackGroundColor(Color.LIGHT_GRAY);
                }
                numberInput.setText(String.valueOf(number));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(number>1){
                    btnSub.setBackGroundColor(new Color(52, 132, 240));
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(number>1){
                    btnSub.setBackGroundColor(new Color(115, 168, 240));
                }
            }
            
            
            
        });
        
        btnAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(number<goods.getAmount()){
                    number++;
                }
                if(number>1){
                    btnSub.setBackGroundColor(new Color(52, 132, 240));
                }
                if(number>=13){
                    btnAdd.setBackGroundColor(Color.LIGHT_GRAY);
                }
                numberInput.setText(String.valueOf(number));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(number<goods.getAmount()){
                    btnAdd.setBackGroundColor(new Color(52, 132, 240));
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(number<goods.getAmount()){
                    btnAdd.setBackGroundColor(new Color(115, 168, 240));
                }
            }
            
            
            
        });
        
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
                
                if(customerID.equals("")){
                    new Dialog(250, 100, "Đăng nhập để thực hiện");
                }
                else{
                    int a = 0;
                
                    ResultSet rs = database.queryData("SELECT * FROM CART WHERE CustomerID='"+customerID+"' AND SneakerID='"+goods.getId()+"'");

                    try {
                        if(rs.next()){
                           a = rs.getInt(3);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(InformationGood.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if(Integer.parseInt(numberInput.getText())+a>goods.getAmount()){
                        new Dialog(350, 100, "Số lượng lớn hơn số lượng còn lại");
                        number = 1;
                        if(goods.getAmount()==0){
                            number = 0;
                            btnSub.setBackGroundColor(Color.LIGHT_GRAY);
                            btnAdd.setBackGroundColor(Color.LIGHT_GRAY);
                        }
                        if(goods.getAmount()==1){
                            btnSub.setBackGroundColor(Color.LIGHT_GRAY);
                            btnAdd.setBackGroundColor(Color.LIGHT_GRAY);
                        }
                        numberInput.setText(String.valueOf(number));
                    }
                    else{


                        if(a>0){
                            if(database.queryUpdate("UPDATE CART SET Amount = '"+(a+number)+"' WHERE CustomerID='"+customerID+"' AND SneakerID='"+goods.getId()+"'")){
                                new Dialog(200, 100, "Thêm thành công");
                                listener.updateList();
                            }
                            else{
                                new Dialog(200, 100, "Thêm thất bại");
                            }
                        }
                        else{
                            if(database.queryUpdate("INSERT INTO CART(CustomerID, SneakerID, Amount) VALUES('"+customerID+"','"+goods.getId()+"','"+number+"')")){
                                new Dialog(200, 100, "Thêm thành công");
                                listener.updateCart();
                            }
                            else{
                                new Dialog(200, 100, "Thêm thất bại");
                            }
                        }

                    }
                }
                
                
            }
            
        });
        
        numberInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                number = Integer.parseInt(numberInput.getText());
            }
            
        });
        
    }
    
    public void setUpdateCartListener(AddCartListener listener){
        this.listener = listener;
    }
    
    private void showCard(){
        containCard.show(parent, this.prevCard);
    }
    
    public void setPrevCard(String prevCard){
        this.prevCard = prevCard;
    }
    
    public void setCurrentLayout(Goods g){
        goods=g;
        number = 1;
        btnSub.setBackGroundColor(Color.LIGHT_GRAY);
        btnAdd.setBackGroundColor(new Color(52, 132, 240));
        if(g.getAmount()==0){
            number = 0;
            btnSub.setBackGroundColor(Color.LIGHT_GRAY);
            btnAdd.setBackGroundColor(Color.LIGHT_GRAY);
        }
        if(g.getAmount()==1){
            btnSub.setBackGroundColor(Color.LIGHT_GRAY);
            btnAdd.setBackGroundColor(Color.LIGHT_GRAY);
        }
        numberInput.setText(String.valueOf(number));
        mainImage.setIcon(new ImageIcon(ImageController.getScaledImage(new ImageIcon("src//main//java//res//goods//"+g.getImg()).getImage(), 400, 0)));
        mainName.setText(g.getName());
//        mainPrice.setText(String.valueOf(g.getPrice()));
        mainPrice.setText(FormatText.covertCurrentcyFormat(g.getPrice()));
        mainBrand.setText("Thương hiệu: "+g.getBrand());
        mainColor.setText("Màu: "+g.getColor());
        mainSize.setText("Size: "+g.getSize());
        mainAmountUnit.setText("Còn lại: "+g.getAmount()+" "+g.getUnit());
        mainDescription.setText("Mô tả: "+g.getDescription());
        
        inforPanel.setPreferredSize(new Dimension(550, mainName.getHeight()+75+75+75+75+75+100+50+90+40+mainDescription.getHeight()));
    }
    
}
