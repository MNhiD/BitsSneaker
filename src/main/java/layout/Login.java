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
import Manager.MainFrame;
import Staff.NhanVien;
import Tools.FileController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author letan
 */
public class Login {
    
    private static String account = "";
    
    private JFrame loginFrame;
    
    private JLabel forgotPassword, guestLogin;
    
    private JLabel lblLogin, lblSignin;
    
    private JLabel btnFace, btnGoogle;
    private JLabel btnLogin;
    
    InputText emailPanel;
    PasswordInputText passwordPanel;
    
    private Color themeColor = new Color(52, 132, 240);
    private Color themeLightColor = new Color(115, 168, 240);
    
    private DatabaseManager database = new DatabaseManager("BitSneaker");
    
    public Login(){
        
        loginFrame = new JFrame();
        loginFrame.setSize(900, 500);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.setUndecorated(true);
        loginFrame.setBackground(new Color(1, 1, 1, 0));
        
        ImagePanel loginPanel = new ImagePanel("src\\main\\java\\res\\Login.png", loginFrame);
        loginPanel.setLayout(new BorderLayout());
        
        JPanel layoutLogin = new JPanel();
        layoutLogin.setLayout(new FlowLayout(0,0,0));
        layoutLogin.setPreferredSize(new Dimension(350, 500));
        layoutLogin.setOpaque(true);
        layoutLogin.setBackground(new Color(0,0,0,0));
        
        JPanel p0 = new JPanel();
        p0.setPreferredSize(new Dimension(40, 500));
        p0.setOpaque(true);
        p0.setBackground(new Color(0,0,0,0));
        layoutLogin.add(p0);

        JPanel groupLogin = new JPanel();
        groupLogin.setPreferredSize(new Dimension(279, 500));
        groupLogin.setLayout(new FlowLayout(0,0,0));
        groupLogin.setOpaque(true);
        groupLogin.setBackground(new Color(0,0,0,0));
        
        JPanel p1 = new JPanel();
        p1.setPreferredSize(new Dimension(279, 65));
        p1.setOpaque(true);
        p1.setBackground(new Color(0,0,0,0));
        JPanel p2 = new JPanel();
        p2.setPreferredSize(new Dimension(279, 29));
        p2.setOpaque(true);
        p2.setBackground(new Color(0,0,0,0));
        JPanel p3 = new JPanel();
        p3.setPreferredSize(new Dimension(279, 35));
        p3.setOpaque(true);
        p3.setBackground(new Color(0,0,0,0));
        JPanel p4 = new JPanel();
        p4.setPreferredSize(new Dimension(279, 30));
        p4.setOpaque(true);
        p4.setBackground(new Color(0,0,0,0));
        
        groupLogin.add(p1);
        JPanel titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(279, 31));
        titlePanel.setOpaque(true);
        titlePanel.setBackground(new Color(0, 0, 0, 0));
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        
        lblLogin = new JLabel("Đăng nhập");
        lblLogin.setForeground(Color.darkGray);
        lblLogin.setFont(new Font("San-Serif", Font.BOLD, 25));
        JPanel lblSigninPanel = new JPanel(new BorderLayout(0,0));
        lblSigninPanel.setOpaque(true);
        lblSigninPanel.setBackground(new Color(0,0,0,0));
        JPanel lblSigninPanel2 = new JPanel(new BorderLayout(0,0));
        lblSigninPanel2.setOpaque(true);
        lblSigninPanel2.setBackground(new Color(0,0,0,0));
        lblSignin = new JLabel("Đăng ký");
        lblSignin.setForeground(Color.lightGray);
        lblSignin.setFont(new Font("San-Serif", Font.BOLD, 20));
        lblSigninPanel2.add(lblSignin, BorderLayout.EAST);
        lblSigninPanel.add(lblSigninPanel2, BorderLayout.PAGE_END);
        titlePanel.add(lblLogin);
        titlePanel.add(Box.createHorizontalGlue());
        titlePanel.add(lblSigninPanel);
        
        groupLogin.add(titlePanel);
        groupLogin.add(p2);
        JPanel inputPanel = new JPanel();
        inputPanel.setPreferredSize(new Dimension(279, 192));
        inputPanel.setOpaque(true);
        inputPanel.setBackground(new Color(0,0,0,0));
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        
        JPanel accountGroup = new JPanel();
        accountGroup.setPreferredSize(new Dimension(279, 129));
        accountGroup.setOpaque(true);
        accountGroup.setBackground(new Color(0,0,0,0));
        accountGroup.setLayout(new BoxLayout(accountGroup, BoxLayout.Y_AXIS));
        
        emailPanel = new InputText("Email", "Email", "Email không hợp lệ", AccountCheck.emailStandard, Color.WHITE, 279);
        passwordPanel = new PasswordInputText("Mật khẩu", "Mật khẩu", "Mật khẩu không đúng", AccountCheck.passwordStandard, Color.WHITE, 279);
//        JPanel passwordPanel = new InputText("Mật khẩu", "Mật khẩu", "Mật khẩu không đúng", AccountCheck.emailStandard, Color.WHITE);
        
        accountGroup.add(emailPanel);
        accountGroup.add(Box.createVerticalGlue());
        accountGroup.add(passwordPanel);
        
        JPanel buttonLoginPanel = new JPanel();
        buttonLoginPanel.setPreferredSize(new Dimension(279, 44));
        buttonLoginPanel.setOpaque(true);
        buttonLoginPanel.setBackground(Color.WHITE);
        buttonLoginPanel.setLayout(new BoxLayout(buttonLoginPanel, BoxLayout.X_AXIS));
        
        btnLogin = new JLabel(new ImageIcon("src\\main\\java\\res\\buttonLogin.png"));
        forgotPassword = new JLabel("Quên mật khẩu?");
        forgotPassword.setForeground(Color.darkGray);
        forgotPassword.setFont(new Font("San-Serif", Font.BOLD, 13));
        
        buttonLoginPanel.add(forgotPassword);
        buttonLoginPanel.add(Box.createHorizontalGlue());
        buttonLoginPanel.add(btnLogin);
        
        
        inputPanel.add(accountGroup);
        inputPanel.add(Box.createVerticalGlue());
        inputPanel.add(buttonLoginPanel);
        
        groupLogin.add(inputPanel);
        
        groupLogin.add(p4);
        
        guestLogin = new JLabel("Vào không cần đăng nhập");
        guestLogin.setHorizontalAlignment(SwingConstants.CENTER);
        guestLogin.setForeground(themeColor);
//        guestLogin.setBackground(Color.WHITE);
        guestLogin.setFont(new Font("San-Serif", Font.BOLD, 13));
        
        JPanel guestLoginPane = new JPanel(new BorderLayout());
        guestLoginPane.setBackground(Color.WHITE);
        guestLoginPane.setPreferredSize(new Dimension(279, 20));
        guestLoginPane.add(guestLogin, BorderLayout.CENTER);
        
        guestLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                guestLogin.setForeground(themeColor);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                guestLogin.setForeground(themeLightColor);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                new HomePage("");
                loginFrame.dispose();
            }
            
        });
        
        groupLogin.add(guestLoginPane);
        
        groupLogin.add(p3);
        
        JPanel otherLoginPanel = new JPanel();
        otherLoginPanel.setLayout(new BoxLayout(otherLoginPanel, BoxLayout.Y_AXIS));
        otherLoginPanel.setPreferredSize(new Dimension(279, 66));
        otherLoginPanel.setBackground(Color.WHITE);
        
        JPanel lblOrPanel = new JPanel();
        lblOrPanel.setBackground(Color.WHITE);
        lblOrPanel.setPreferredSize(new Dimension(279, 21));
        JLabel lblOr = new JLabel("hoặc");
        lblOr.setFont(new Font("San-Serif", Font.PLAIN, 15));
        lblOr.setForeground(Color.lightGray);
        lblOrPanel.add(lblOr);
        
        JPanel mediaLoginPanel = new JPanel();
        mediaLoginPanel.setLayout(new BoxLayout(mediaLoginPanel, BoxLayout.X_AXIS));
        mediaLoginPanel.setBackground(Color.WHITE);
        mediaLoginPanel.setPreferredSize(new Dimension(279, 30));
        
        btnFace = new JLabel(new ImageIcon("src\\main\\java\\res\\facebook.png"));
        btnGoogle = new JLabel(new ImageIcon("src\\main\\java\\res\\google-plus.png"));
        
        mediaLoginPanel.add(btnFace);
        mediaLoginPanel.add(Box.createRigidArea(new Dimension(23, 30)));
        mediaLoginPanel.add(btnGoogle);
        
        otherLoginPanel.add(lblOrPanel);
        otherLoginPanel.add(Box.createRigidArea(new Dimension(279, 10)));
        otherLoginPanel.add(mediaLoginPanel);
        
        groupLogin.add(otherLoginPanel);
        
        layoutLogin.add(groupLogin);
        loginPanel.add(layoutLogin, BorderLayout.EAST);

        
        forgotPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                forgotPassword.setForeground(Color.darkGray);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                forgotPassword.setForeground(themeColor);
            }
            
        });
        
        lblSignin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                lblSignin.setForeground(Color.lightGray);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lblSignin.setForeground(Color.gray);
            }
            
        });
        
        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(emailPanel.getContent().length()==0 || passwordPanel.getContent().length()==0){
                    JOptionPane.showMessageDialog(null, "Không được để trống");
                }
                else if(!emailPanel.getContent().matches(AccountCheck.emailStandard)){
                    JOptionPane.showMessageDialog(null, "Email không hợp lệ");
                }
                else if(!passwordPanel.getContent().matches(AccountCheck.passwordStandard)){
                    JOptionPane.showMessageDialog(null, "Mật khẩu quá ngắn");
                }
                else{
                    try {
                        String sql = "SELECT * FROM ACCOUNT WHERE AccountID = '"+emailPanel.getContent()+"'";
                        ResultSet rs = database.queryData(sql);
                        if(rs.next()){
                            if(rs.getString(2).equals(passwordPanel.getContent())){
                                loginFrame.dispose();
                                if(rs.getString(4).equals("customer")){
                                    try {
                                        FileController.outputFile("src//main//java//local//login.txt", emailPanel.getContent());
                                    } catch (IOException ex) {
                                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    new HomePage(emailPanel.getContent());
                                }
                                else if(rs.getString(4).equals("manager")){
                                    new MainFrame().setVisible(true);
                                }
                                else if(rs.getString(4).equals("staff")){
                                    new NhanVien(emailPanel.getContent()).setVisible(true);
                                }
                                    
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Sai mật khẩu");
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Không tìm thấy email");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
        });
        
        forgotPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Quên mk nè");
            }
            
        });
        
        lblSignin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loginFrame.dispose();
                new Signin();
            }
            
        });
        
        btnFace.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Facebook nè");
            }
            
        });
        
        btnGoogle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Google nè");
            }
            
        });
        
        loginFrame.setContentPane(loginPanel);
        loginFrame.setVisible(true);
    }
    
    public static void main(String[] args) {
        try {
            account = FileController.inputFile("src//main//java//local//login.txt");
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(account);
        new HomePage(account);
    }
    
}
