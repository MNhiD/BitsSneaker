/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomComponents;

import CustomListener.CustomMouseClick;
import Entity.Goods;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JSeparator;

/**
 *
 * @author letan
 */
public class TabGridView extends JPanel{
    
    private ArrayList<SimpleItem> listItem = new ArrayList<>();
    private ArrayList<JSeparator> listLine = new ArrayList<>();
    
    private int width, height;
    
    private Color themeColor = new Color(52, 132, 240);
    private Color themeLightColor = new Color(115, 168, 240);
    
    private CustomMouseClick listener;
    
    private int selectedItemIndex = 0;
    
    public TabGridView(int w, int h, ArrayList<String> list){
        
        this.width = w;
        this.height = h;
        
        int dW = w/list.size();
        
        setLayout(new GridLayout(0, list.size(), 0, 0));
        
        for(int i=0; i<list.size(); i++){
            SimpleItem item = new SimpleItem(list.get(i), dW, h, i);
            item.setItemCenterHorizontal();
            
            item.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    listener.CustomItemClick(item);
                }
                
            });
            
            listItem.add(item);
            JSeparator line = new JSeparator(JSeparator.HORIZONTAL);
            line.setBackground(this.themeColor);
            line.setBackground(this.themeLightColor);
            line.setVisible(false);
            listLine.add(line);
        }
        
        for(int i=0; i<listItem.size(); i++){
            JPanel item = new JPanel(new BorderLayout(0, 0));
            item.setPreferredSize(new Dimension(w, h+2));
            item.add(listItem.get(i));
            item.add(listLine.get(i), BorderLayout.SOUTH);
            
            add(item);
        }
        
        setSelectedTabItem(0);
    }
    
    public void setSelectedTabItem(int position){
        this.selectedItemIndex = position;
        for(int i=0; i<this.listItem.size(); i++){
            listLine.get(i).setVisible(false);
            if(i==position) listLine.get(i).setVisible(true);
        }
    }
    
    public void setItemClickListener(CustomMouseClick listener){
        this.listener = listener;
    }
    
    public int getSelectedIndex(){
        return this.selectedItemIndex;
    }
}
