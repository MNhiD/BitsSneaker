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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author letan
 */
public class PasswordInputText extends JPanel{
    
    protected JLabel lblTitle;
    protected JPasswordField edtText;
    protected JLabel errText;
    protected JSeparator line;
    protected JLabel btnReveal;
    
    private String content="";
    private String hint="";
    private String error="";
    private String regex = "";
    
    public PasswordInputText(String lbl, String hint, String error, String regex, Color c, int w){
        
        this.hint = hint;
        this.error = error;
        this.regex = regex;
        
        int width=w;
        
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
        
        JPanel passwordAndShowPanel = new JPanel();
        passwordAndShowPanel.setLayout(new BoxLayout(passwordAndShowPanel, BoxLayout.X_AXIS));
        passwordAndShowPanel.setPreferredSize(new Dimension(width, 21));
        passwordAndShowPanel.setBackground(c);
        
        edtText = new JPasswordField(hint);
        if(content==""){
            edtText.setEchoChar((char)0);
        }
        else{
            edtText.setEchoChar('\u2022');
        }
        edtText.setForeground((content.equals(""))?Color.LIGHT_GRAY:Color.DARK_GRAY);
        edtText.setFont(new Font("San-Serif", Font.PLAIN, 15));
        edtText.setBorder(BorderFactory.createEmptyBorder());
        edtText.setPreferredSize(new Dimension(width-25, 21));
        
        btnReveal = new JLabel(new ImageIcon("src\\main\\java\\res\\reveal.png"));
        
        passwordAndShowPanel.add(edtText);
        passwordAndShowPanel.add(Box.createHorizontalGlue());
        passwordAndShowPanel.add(btnReveal);
        
        line = new JSeparator(SwingConstants.HORIZONTAL);
        line.setForeground(Color.lightGray);
        line.setBackground(Color.lightGray);
        txtBoxPanel.add(passwordAndShowPanel);
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
                if(String.valueOf(edtText.getPassword()).equals("")){
                    lblTitle.setVisible(false);
                    edtText.setText(getHint());
                    edtText.setEchoChar((char)0);
                    setContent("");
                }
                else{
                    lblTitle.setVisible(true);
                    setContent(String.valueOf(edtText.getPassword()));
                    edtText.setEchoChar('\u2022');
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
                edtText.setEchoChar('\u2022');
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
                setContent(String.valueOf(edtText.getPassword()));
            }
        });
        
        btnReveal.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                edtText.setEchoChar('\u2022');
            }

            @Override
            public void mousePressed(MouseEvent e) {
                edtText.setEchoChar((char)0);
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
    
}
