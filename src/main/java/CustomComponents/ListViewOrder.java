/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomComponents;

import CustomListener.UpdateCartListener;
import Entity.Cart;
import Entity.Order;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author letan
 */
public class ListViewOrder extends JPanel{
    
    private ArrayList<OrderItem> itemList=null;
    
    public ListViewOrder(ArrayList<Order> list){
        
        setLayout(new GridLayout(0, 1));

        itemList = new ArrayList<>();
        for(int i=0; i<list.size(); i++){
            OrderItem item = new OrderItem(list.get(i));
            itemList.add(item);
        }
        
        for(int i=0; i<list.size(); i++){
            add(itemList.get(i));
        }
        
    }
    
//    public void setItemCartChanging(UpdateCartListener listener){
//        for(ImageGoodsItem i: itemList){
//            i.setChangeCartItemListener(listener);
//        }
//    }
    
//    public void setList(ArrayList<Cart> list){
//        itemList.clear();
//        for(int i=0; i<list.size(); i++){
//            ImageGoodsItem item = new ImageGoodsItem(list.get(i));
//            itemList.add(item);
//        }
//        
//        removeAll();
//        revalidate();
//        repaint();
//        
//        for(int i=0; i<list.size(); i++){
//            add(itemList.get(i));
//        }
//    }
    
    public int getListSize(){
        return itemList.size();
    }
    
}
