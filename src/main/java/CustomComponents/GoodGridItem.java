/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomComponents;

import Entity.Goods;
import static Tools.FormatText.covertCurrentcyFormat;
import Tools.ImageController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

/**
 *
 * @author letan
 */
public class GoodGridItem extends Item{
    
//    private int position;
    private Color bkColor=Color.WHITE;
    private Color highlightColor=new Color(176, 231, 255);
    
    public GoodGridItem(int w, int h, Goods g, int position){
        
        this.position = position;
        
        setPreferredSize(new Dimension(w, h));
        setLayout(new FlowLayout(0, 0, 0));
        
        JLabel img = new JLabel(new ImageIcon(ImageController.getScaledImage(new ImageIcon("src//main//java//res//goods//"+g.getImg()).getImage(), 0, Math.round(h*3/5))));
        img.setPreferredSize(new Dimension(w, (int) (h*((float)3/5))));
        
        JPanel text = new JPanel();
        text.setBackground(bkColor);
        text.setPreferredSize(new Dimension(w, (int) (h*((float)2/5))));
        text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));
        
        JPanel infoText = new JPanel(new FlowLayout(0, 0, 0));
        infoText.setBackground(new Color(0, 0, 0, 0));
        JLabel name = new JLabel(g.getName());
        name.setFont(new Font("San-Serif", Font.BOLD, 15));
        name.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 10));
        name.setPreferredSize(new Dimension(w, name.getPreferredSize().height));
        JLabel brand = new JLabel("Brand: "+g.getBrand());
        brand.setFont(new Font("San-Serif", Font.PLAIN, 13));
        brand.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        brand.setForeground(Color.GRAY);
        brand.setPreferredSize(new Dimension(w, brand.getPreferredSize().height));
        JLabel color = new JLabel("Màu: "+g.getColor());
        color.setFont(new Font("San-Serif", Font.PLAIN, 13));
        color.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        color.setForeground(Color.GRAY);
        color.setPreferredSize(new Dimension(w, color.getPreferredSize().height));
        JLabel des = new JLabel(g.getDescription());
        des.setFont(new Font("San-Serif", Font.PLAIN, 13));
        des.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        des.setForeground(Color.GRAY);
        des.setPreferredSize(new Dimension(w, des.getPreferredSize().height));
        infoText.setPreferredSize(new Dimension(name.getPreferredSize().width, name.getPreferredSize().height+brand.getPreferredSize().height+color.getPreferredSize().height+des.getPreferredSize().height));
        infoText.add(name);
        infoText.add(brand);
        infoText.add(color);
        infoText.add(des);
        
        JPanel pricePanel = new JPanel(new BorderLayout(0, 0));
        pricePanel.setBackground(new Color(0, 0, 0, 0));
//        JLabel price = new JLabel(String.valueOf(g.getPrice()));
        JLabel price = new JLabel(covertCurrentcyFormat(g.getPrice()));
        price.setForeground(new Color(52, 132, 240));
        price.setFont(new Font("San-Serif", Font.BOLD, 17));
        price.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        pricePanel.setPreferredSize(new Dimension(infoText.getPreferredSize().width, price.getPreferredSize().height));
        pricePanel.add(price,BorderLayout.SOUTH);
        
        text.add(infoText);
        text.add(Box.createVerticalGlue());
        text.add(pricePanel);
        
        add(img);
        add(new JSeparator(JSeparator.HORIZONTAL));
        add(text);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                text.setBackground(bkColor);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                text.setBackground(highlightColor);
            }
            
        });
        
    }

//    public int getPosition() {
//        return position;
//    }
    
}
