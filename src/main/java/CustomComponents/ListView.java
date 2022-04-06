/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomComponents;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author letan
 */
public class ListView extends JPanel{
    
    private ArrayList<SimpleItem> itemList=null;
    
    public ListView(){
        
    }
    
    public ListView(ArrayList<String> list, int w, int h){
        this(list, w, h, 0);
    }
    
    public ListView(ArrayList<String> list, int w, int h, int dh){
        
        int dH = dh;
        
        if(dh==0){
            dH = h/list.size();
        }
        
        
        setLayout(new GridLayout(0, 1));
        itemList = new ArrayList<>();
        for(int i=0; i<list.size(); i++){
            SimpleItem item = new SimpleItem(list.get(i), w, dH, i);
            itemList.add(item);
        }
        
        for(int i=0; i<list.size(); i++){
            add(itemList.get(i));
        }
        
    }
    
    public void setItem(String s, int pos){
        itemList.get(pos).setLable(s);
    }
    
    public void setItemClickListener(CustomListener.CustomMouseClick listener){
        for(SimpleItem item : this.itemList){
            item.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    listener.CustomItemClick(item);
                }
                
            });
        }
    }

}
