/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomComponents;

import Input.AccountCheck;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author letan
 */
public class InputText extends JPanel{   
    
    protected JLabel lblTitle;
    protected JTextField edtText;
    protected JLabel errText;
    protected JSeparator line;
    
    private String content="";
    private String hint="";
    private String error="";
    private String regex = "";
    
    
    public InputText(String lbl, String hint, String error, String regex, Color c, int w){
        
        this.hint = hint;
        this.error = error;
        this.regex = regex;
        
        int width = w;
        
        setLayout(new FlowLayout(0,0,0));
        setPreferredSize(new Dimension(width, 64));
        setOpaque(true);
        setBackground(new Color(0,0,0,0));
        
        JPanel lblPanel = new JPanel(new BorderLayout(0,0));
        lblPanel.setPreferredSize(new Dimension(width, 14));
        lblPanel.setBackground(c);
        lblTitle = new JLabel(lbl);
        lblTitle.setForeground(Color.lightGray);
        lblTitle.setFont(new Font("San-Serif", Font.PLAIN, 12));
        lblTitle.setVisible(false);
        lblPanel.add(lblTitle, BorderLayout.WEST);
        
        JPanel txtBoxPanel = new JPanel();
        txtBoxPanel.setLayout(new BoxLayout(txtBoxPanel, BoxLayout.Y_AXIS));
        txtBoxPanel.setPreferredSize(new Dimension(width, 27));
        txtBoxPanel.setBackground(c);
        edtText = new JTextField(hint);
        edtText.setForeground((content.equals(""))?Color.LIGHT_GRAY:Color.DARK_GRAY);
        edtText.setFont(new Font("San-Serif", Font.PLAIN, 15));
        edtText.setBorder(BorderFactory.createEmptyBorder());
        edtText.setPreferredSize(new Dimension(width, 21));
        line = new JSeparator(SwingConstants.HORIZONTAL);
        line.setForeground(Color.lightGray);
        line.setBackground(Color.lightGray);
        txtBoxPanel.add(edtText);
        txtBoxPanel.add(Box.createVerticalGlue());
        txtBoxPanel.add(line);
        
        JPanel p4 = new JPanel();
        p4.setPreferredSize(new Dimension(width, 7));
        p4.setOpaque(true);
        p4.setBackground(new Color(0,0,0,0));
        JPanel p5 = new JPanel();
        p5.setPreferredSize(new Dimension(width, 3));
        p5.setOpaque(true);
        p5.setBackground(new Color(0,0,0,0));
        
        JPanel errPanel = new JPanel(new BorderLayout(0,0));
        errPanel.setPreferredSize(new Dimension(width, 14));
        errPanel.setBackground(Color.WHITE);
        errText = new JLabel(error);
        errText.setForeground(Color.red);
        errText.setFont(new Font("San-Serif", Font.PLAIN, 12));
        errText.setVisible(false);
        errPanel.add(errText, BorderLayout.EAST);
        
        add(lblPanel);
        add(p4);
        add(txtBoxPanel);
        add(p5);
        add(errPanel);
        
        edtText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                line.setForeground(Color.lightGray);
                line.setBackground(Color.lightGray);
                if(edtText.getText().equals("")){
                    lblTitle.setVisible(false);
                    edtText.setText(getHint());
                    setContent("");
                }
                else{
                    lblTitle.setVisible(true);
                    setContent(edtText.getText());
                    if(!AccountCheck.isCorrect(getContent(), getRegex())){
                        errText.setVisible(true);
                        line.setForeground(Color.red);
                        line.setBackground(Color.red);
                    }
                }
                edtText.setForeground((getContent().equals(""))?Color.LIGHT_GRAY:Color.DARK_GRAY);
            }

            @Override
            public void focusGained(FocusEvent e) {
                line.setForeground(new Color(52, 132, 240));
                line.setBackground(new Color(52, 132, 240));
                lblTitle.setVisible(true);
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
            }
        });
        
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public void setDefaultContent(String content){
        if(content.length()>0){
            setContent(content);
            this.edtText.setText(this.content);
            edtText.setForeground((content.equals(""))?Color.LIGHT_GRAY:Color.DARK_GRAY);
            lblTitle.setVisible(true);
        }
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }
    
    public void setEditable(boolean f){
//        this.edtText.setEnabled(f);
        this.edtText.setEditable(f);
    }
    
}
