/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomComponents;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JScrollPane;

/**
 *
 * @author letan
 */
public class CustomScrollPane extends JScrollPane{
    
    public CustomScrollPane(JComponent comp, int vP, int hP, int w, int h){
//        JScrollPane scrollNavPane = new JScrollPane(comp, vP, hP);
        getViewport().add(comp);
        setVerticalScrollBarPolicy(vP);
        setHorizontalScrollBarPolicy(hP);
        getVerticalScrollBar().setBackground(new Color(0, 0, 0, 0));
        getVerticalScrollBar().setUI(new CustomScrollBarUI());
        setPreferredSize(new Dimension(w,h));
        setBackground(new Color(0, 0, 0, 0));
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    }
    
}
