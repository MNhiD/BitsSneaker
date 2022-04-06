/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomComponents;

import Entity.Goods;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author letan
 */
public class GridView extends JPanel{
    
    private ArrayList<GoodGridItem> listItem = null;
    private int dW, h, c;
    
    public GridView(int w, int h, int c, ArrayList<Goods> list){
        
        this.dW = (w-100)/c;
        this.h = h;
        this.c = c;
        
        setLayout(new GridLayout(0, c, 10, 15));
        
        listItem = new ArrayList<>();
        for(int i=0; i<list.size(); i++){
            listItem.add(new GoodGridItem(dW, h, list.get(i), i));
        }
        
        for(GoodGridItem g:listItem){
            add(g);
        }
    }
    
    public void setItemClickListener(CustomListener.CustomMouseClick listener){
        for(GoodGridItem item : this.listItem){
            item.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    listener.CustomItemClick(item);
                }
                
            });
        }
    }
    
    public void setList(ArrayList<Goods> list){
        listItem.clear();
        for(int i=0; i<list.size(); i++){
            GoodGridItem item = new GoodGridItem(dW, h, list.get(i), i);
            listItem.add(item);
        }
        
        removeAll();
        revalidate();
        repaint();
        
        for(int i=0; i<list.size(); i++){
            add(listItem.get(i));
        }
    }
    
}
