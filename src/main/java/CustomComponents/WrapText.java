/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomComponents;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JTextArea;

/**
 *
 * @author letan
 */
public class WrapText extends JTextArea{
    
    public WrapText(int column, Font f){
        setWrapStyleWord(true);
        setLineWrap(true);
        setColumns(column);
        setFont(f);
        setEditable(false);
        setOpaque(true);
        setBackground(new Color(0, 0, 0, 0));
        setFocusable(false);
    }
    
}
