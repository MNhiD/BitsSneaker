/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layout;

import CustomComponents.ImagePanel;
import CustomComponents.InputText;
import CustomComponents.PasswordInputText;
import Database.DatabaseManager;
import Input.AccountCheck;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author letan
 */
public class Signin {
    
    private JFrame signinFrame;
    
    private JLabel lblSignin, lblLogin;
    
    private JLabel btnSignin;
    
    private InputText emailPanel;
    private PasswordInputText passwordPanel, confirmPasswordPanel;
    
    private DatabaseManager database = new DatabaseManager("BitSneaker");
    
    public Signin(){
        
        signinFrame = new JFrame();
        signinFrame.setSize(900, 500);
        signinFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        signinFrame.setUndecorated(true);
        signinFrame.setBackground(new Color(1, 1, 1, 0));
        
        ImagePanel signinPanel = new ImagePanel("src\\main\\java\\res\\Login.png", signinFrame);
        signinPanel.setLayout(new BorderLayout());
        
        JPanel layoutSignin = new JPanel();
        layoutSignin.setLayout(new FlowLayout(0,0,0));
        layoutSignin.setPreferredSize(new Dimension(350, 500));
        layoutSignin.setOpaque(true);
        layoutSignin.setBackground(new Color(0,0,0,0));
        
        JPanel p0 = new JPanel();
        p0.setPreferredSize(new Dimension(40, 500));
        p0.setOpaque(true);
        p0.setBackground(new Color(0,0,0,0));
        layoutSignin.add(p0);

        JPanel groupSignin = new JPanel();
        groupSignin.setPreferredSize(new Dimension(279, 500));
        groupSignin.setLayout(new FlowLayout(0,0,0));
        groupSignin.setOpaque(true);
        groupSignin.setBackground(new Color(0,0,0,0));
        
        JPanel p1 = new JPanel();
        p1.setPreferredSize(new Dimension(279, 65));
        p1.setOpaque(true);
        p1.setBackground(new Color(0,0,0,0));
        JPanel p2 = new JPanel();
        p2.setPreferredSize(new Dimension(279, 29));
        p2.setOpaque(true);
        p2.setBackground(new Color(0,0,0,0));
        
        groupSignin.add(p1);
        JPanel titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(279, 31));
        titlePanel.setOpaque(true);
        titlePanel.setBackground(new Color(0, 0, 0, 0));
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        
        lblSignin = new JLabel("Đăng ký");
        lblSignin.setForeground(Color.darkGray);
        lblSignin.setFont(new Font("San-Serif", Font.BOLD, 25));
        JPanel lblLoginPanel = new JPanel(new BorderLayout(0,0));
        lblLoginPanel.setOpaque(true);
        lblLoginPanel.setBackground(new Color(0,0,0,0));
        JPanel lblLoginPanel2 = new JPanel(new BorderLayout(0,0));
        lblLoginPanel2.setOpaque(true);
        lblLoginPanel2.setBackground(new Color(0,0,0,0));
        lblLogin = new JLabel("Đăng nhập");
        lblLogin.setForeground(Color.lightGray);
        lblLogin.setFont(new Font("San-Serif", Font.BOLD, 20));
        lblLoginPanel2.add(lblLogin, BorderLayout.EAST);
        lblLoginPanel.add(lblLoginPanel2, BorderLayout.PAGE_END);
        titlePanel.add(lblSignin);
        titlePanel.add(Box.createHorizontalGlue());
        titlePanel.add(lblLoginPanel);
        
        groupSignin.add(titlePanel);
        groupSignin.add(p2);
        
        JPanel inputPanel = new JPanel();
        inputPanel.setPreferredSize(new Dimension(279, 261));
        inputPanel.setOpaque(true);
        inputPanel.setBackground(new Color(0,0,0,0));
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        
        JPanel accountGroup = new JPanel();
        accountGroup.setPreferredSize(new Dimension(279, 202));
        accountGroup.setOpaque(true);
        accountGroup.setBackground(new Color(0,0,0,0));
        accountGroup.setLayout(new BoxLayout(accountGroup, BoxLayout.Y_AXIS));
        
        emailPanel = new InputText("Email", "Email", "Email không hợp lệ", AccountCheck.emailStandard, Color.WHITE, 279);
        passwordPanel = new PasswordInputText("Mật khẩu", "Mật khẩu", "Mật khẩu không đúng", AccountCheck.passwordStandard, Color.WHITE, 279);
        confirmPasswordPanel = new PasswordInputText("Xác nhận mật khẩu", "Xác nhận mật khẩu", "Mật khẩu không đúng", AccountCheck.passwordStandard, Color.WHITE, 279);
//        JPanel passwordPanel = new InputText("Mật khẩu", "Mật khẩu", "Mật khẩu không đúng", AccountCheck.emailStandard, Color.WHITE);
        
        accountGroup.add(emailPanel);
        accountGroup.add(Box.createVerticalGlue());
        accountGroup.add(passwordPanel);
        accountGroup.add(Box.createVerticalGlue());
        accountGroup.add(confirmPasswordPanel);
        
        JPanel buttonSigninPanel = new JPanel();
        buttonSigninPanel.setPreferredSize(new Dimension(279, 44));
        buttonSigninPanel.setOpaque(true);
        buttonSigninPanel.setBackground(Color.WHITE);
        buttonSigninPanel.setLayout(new BorderLayout(0, 0));
        
        btnSignin = new JLabel(new ImageIcon("src\\main\\java\\res\\buttonLogin.png"));
        
        buttonSigninPanel.add(btnSignin, BorderLayout.EAST);
        
        inputPanel.add(accountGroup);
        inputPanel.add(Box.createVerticalGlue());
        inputPanel.add(buttonSigninPanel);
        
        groupSignin.add(inputPanel);
        
        layoutSignin.add(groupSignin);
        signinPanel.add(layoutSignin, BorderLayout.EAST);
        
        lblLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                lblLogin.setForeground(Color.lightGray);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lblLogin.setForeground(Color.gray);
            }
            
        });
        
        btnSignin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(emailPanel.getContent().length()==0 || passwordPanel.getContent().length()==0 || confirmPasswordPanel.getContent().length()==0){
                    JOptionPane.showMessageDialog(null, "Không được để trống");
                }
                else if(!emailPanel.getContent().matches(AccountCheck.emailStandard)){
                    JOptionPane.showMessageDialog(null, "Email không hợp lệ");
                }
                else if(!passwordPanel.getContent().matches(AccountCheck.passwordStandard)){
                    JOptionPane.showMessageDialog(null, "Mật khẩu quá ngắn");
                }
                else if(!confirmPasswordPanel.getContent().equals(passwordPanel.getContent())){
                    JOptionPane.showMessageDialog(null, "Xác nhận mật khẩu không đúng");
                }
                else{
                    
                    String sqlEmail = "SELECT * FROM ACCOUNT WHERE AccountID = '"+emailPanel.getContent()+"'";
                    ResultSet rs = database.queryData(sqlEmail);
                    
                    try {
                        if(rs.next()){
                            JOptionPane.showMessageDialog(null, "Email đã được dùng");
                        }
                        else{
                            String sql = "INSERT INTO CUSTOMER(PhoneNumber, Name, AccountID) VALUES('','','"+emailPanel.getContent()+"')";
                            String sql2 = "INSERT INTO ACCOUNT(AccountID, Password, Status, Type) VALUES('"+emailPanel.getContent()+"','"+passwordPanel.getContent()+"','1', 'customer')";
                            database.queryUpdate(sql2);
                            database.queryUpdate(sql);
                            
                            new HomePage(emailPanel.getContent());
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Signin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        lblLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                signinFrame.dispose();
                new Login();
            }
            
        });
        
        signinFrame.setContentPane(signinPanel);
        signinFrame.setVisible(true);
    }
    
}
