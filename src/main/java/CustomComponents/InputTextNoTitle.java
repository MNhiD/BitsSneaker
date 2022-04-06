/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomComponents;

import CustomListener.KeyListener;
import Input.AccountCheck;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author letan
 */
public class InputTextNoTitle extends JPanel{
    
    protected JTextField edtText;
    protected JLabel errText;
    protected JSeparator line;
    
    private String content="";
    private String hint="";   
    
    private KeyListener listener;
    
    
    public InputTextNoTitle(String hint, Color c, int w){
        
        this.hint = hint;
        
        int width = w;
        
        setLayout(new FlowLayout(0,0,0));
        setPreferredSize(new Dimension(width, 27));
        setOpaque(true);
        setBackground(new Color(0,0,0,0));
        
        JPanel txtBoxPanel = new JPanel();
        txtBoxPanel.setLayout(new BoxLayout(txtBoxPanel, BoxLayout.Y_AXIS));
        txtBoxPanel.setPreferredSize(new Dimension(width, 27));
        txtBoxPanel.setBackground(c);
        edtText = new JTextField(hint);
        edtText.setForeground((this.content.equals(""))?Color.LIGHT_GRAY:Color.DARK_GRAY);
        edtText.setFont(new Font("San-Serif", Font.PLAIN, 15));
        edtText.setBorder(BorderFactory.createEmptyBorder());
        edtText.setPreferredSize(new Dimension(width, 21));
        line = new JSeparator(SwingConstants.HORIZONTAL);
        line.setForeground(Color.lightGray);
        line.setBackground(Color.lightGray);
        txtBoxPanel.add(edtText);
        txtBoxPanel.add(Box.createVerticalGlue());
        txtBoxPanel.add(line);
        
        add(txtBoxPanel);
        
        edtText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                line.setForeground(Color.lightGray);
                line.setBackground(Color.lightGray);
                if(edtText.getText().equals("")){
                    edtText.setText(getHint());
                    setContent("");
                }
                else{
                    setContent(edtText.getText());
                }
                edtText.setForeground((getContent().equals(""))?Color.LIGHT_GRAY:Color.DARK_GRAY);
            }

            @Override
            public void focusGained(FocusEvent e) {
                line.setForeground(new Color(52, 132, 240));
                line.setBackground(new Color(52, 132, 240));
                edtText.setForeground(Color.darkGray);
                errText.setVisible(false);
                if(getContent().equals("")){
                    edtText.setText("");
                }
                else{
                    
                }
            }
            
        });
        
        edtText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                setContent(edtText.getText());
                listener.KeyTypeListener();
            }
        });
        
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    protected void addContent(String s){
        this.content += s;
    }
    
    protected void subContent(String s){
        this.content = this.content.substring(0, this.content.length()-1);
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
    
    public void setKeyListener(KeyListener listener){
        this.listener = listener;
    }
    
    
}

