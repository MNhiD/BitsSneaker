/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author letan
 */
public class RoundedPanel extends JPanel{

    private int width, height;
    private Color color;
    private int roundedValue;
    
    public RoundedPanel(int w, int h, Color c, int round){
        this.width = w;
        this.height = h;
        this.color = c;
        this.roundedValue = round;
    }
    
    public void setBackGroundColor(Color c){
        this.color = c;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(this.roundedValue,this.roundedValue);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics.setColor(Color.WHITE);
        graphics.fillRoundRect(0, 0, width-1, height-1, 0, 0);
        graphics.setColor(this.color);
        graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
    }
    
    
    
}
