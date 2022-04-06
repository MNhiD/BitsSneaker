/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomComponents;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author letan
 */
public class SimpleItem extends Item{
    
    private JLabel label = new JLabel();
    private RoundedPanel highlightPanel;
    private Color bkColor=Color.WHITE;
    private Color highlightColor=new Color(176, 231, 255);
//    private int position;
    
    public SimpleItem(String text, int w, int h, int position){
        
        this.position = position;
        
        setPreferredSize(new Dimension(w, h));
        setBackground(this.bkColor);
        highlightPanel = new RoundedPanel(w-60, h-10, bkColor, 7);
        highlightPanel.setLayout(new BorderLayout());
        highlightPanel.setPreferredSize(new Dimension(w-60, h-10));
        highlightPanel.setBackground(new Color(0, 0, 0, 0));
        label.setText(text);
        label.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        label.setBackground(new Color(0, 0, 0, 0));
        highlightPanel.add(this.label, BorderLayout.CENTER);
        add(highlightPanel);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                highlightPanel.setBackGroundColor(bkColor);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                highlightPanel.setBackGroundColor(highlightColor);
            }
            
        });
        
    }
    
    public void setLable(String lable){
        this.label.setText(lable);
    }
    
    public void setItemCenterHorizontal(){
        this.label.setHorizontalAlignment(SwingConstants.CENTER);
    }
    
//    public int getPosition(){
//        return position;
//    }
    
}
