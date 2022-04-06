/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layout;

import CustomComponents.CustomScrollPane;
import CustomComponents.Item;
import CustomComponents.ListView;
import CustomComponents.RoundedPanel;
import CustomListener.CustomMouseClick;
import CustomListener.MouseClickReturnButton;
import CustomListener.MouseClickReturnString;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

/**
 *
 * @author letan
 */
public class Dialog{
    
    private JFrame dialog = new JFrame();
    
    private JPanel content = new JPanel();
    
    private Point initialClick;
    
    private RoundedPanel btnConfirm, btnDeny;
    
    private MouseClickReturnString listener;
    private MouseClickReturnButton btnListener;
    
    private String selectedString = "";
    
    public Dialog(int w, int h, String message){
        dialog.setUndecorated(true);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.setBackground(new Color(1, 1, 1, 1));
        dialog.setSize(new Dimension(w, h));
        
        JLabel mess = new JLabel(message);
        mess.setHorizontalAlignment(SwingConstants.CENTER);
        
        btnConfirm = new RoundedPanel(60, 30, new Color(52, 132, 240), 5);
        btnConfirm.setLayout(new GridLayout(1,1));
        JLabel confirmText = new JLabel("OK");
        confirmText.setHorizontalAlignment(SwingConstants.CENTER);
        confirmText.setFont(new Font("San-Serif", Font.PLAIN, 13));
        confirmText.setForeground(Color.WHITE);
        btnConfirm.add(confirmText);
        btnConfirm.setPreferredSize(new Dimension(150, 50));
        
        content.setPreferredSize(new Dimension(w, h));
        content.setLayout(new GridLayout(0, 1));
        content.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        content.add(mess);
        content.add(btnConfirm);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        dialog.setLocation(dim.width/2-dialog.getSize().width/2, dim.height/2-dialog.getSize().height/2);

        content.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                dialog.getComponentAt(initialClick);
            }
        });

        content.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

                // get location of Window
                int thisX = dialog.getLocation().x;
                int thisY = dialog.getLocation().y;

                // Determine how much the mouse moved since the initial click
                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;

                // Move window to this position
                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                dialog.setLocation(X, Y);
            }
        });
        
        btnConfirm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                btnConfirm.setBackGroundColor(new Color(52, 132, 240));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnConfirm.setBackGroundColor(new Color(115, 168, 240));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                dialog.dispose();
            }
            
        });
        
        dialog.add(content);
        dialog.setVisible(true);
    }
    
    public Dialog(int w, int h, String message, String btnConfirmText, String btnDeniedText, MouseClickReturnButton listener){
        
        this.btnListener = listener;
        
        dialog.setUndecorated(true);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.setBackground(new Color(1, 1, 1, 1));
        dialog.setSize(new Dimension(w, h));
        
        JLabel mess = new JLabel(message);
        mess.setHorizontalAlignment(SwingConstants.CENTER);
        
        this.btnConfirm = new RoundedPanel(60, 30, new Color(52, 132, 240), 5);
        this.btnConfirm.setLayout(new GridLayout(1,1));
        JLabel confirmText = new JLabel(btnConfirmText);
        confirmText.setHorizontalAlignment(SwingConstants.CENTER);
        confirmText.setFont(new Font("San-Serif", Font.PLAIN, 13));
        confirmText.setForeground(Color.WHITE);
        this.btnConfirm.add(confirmText);
        this.btnConfirm.setPreferredSize(new Dimension(70, 50));
        
        this.btnDeny = new RoundedPanel(60, 30, new Color(52, 132, 240), 5);
        this.btnDeny.setLayout(new GridLayout(1,1));
        JLabel denyText = new JLabel(btnDeniedText);
        denyText.setHorizontalAlignment(SwingConstants.CENTER);
        denyText.setFont(new Font("San-Serif", Font.PLAIN, 13));
        denyText.setForeground(Color.WHITE);
        this.btnDeny.add(denyText);
        this.btnDeny.setPreferredSize(new Dimension(70, 50));
        
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
        
        btnPanel.add(this.btnConfirm);
        btnPanel.add(Box.createHorizontalGlue());
        btnPanel.add(this.btnDeny);
        
        content.setPreferredSize(new Dimension(w, h));
        content.setLayout(new BorderLayout(0, 10));
        content.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        content.add(mess);
        content.add(btnPanel, BorderLayout.SOUTH);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        dialog.setLocation(dim.width/2-dialog.getSize().width/2, dim.height/2-dialog.getSize().height/2);

        content.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                dialog.getComponentAt(initialClick);
            }
        });

        content.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

                // get location of Window
                int thisX = dialog.getLocation().x;
                int thisY = dialog.getLocation().y;

                // Determine how much the mouse moved since the initial click
                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;

                // Move window to this position
                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                dialog.setLocation(X, Y);
            }
        });
        
        this.btnConfirm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                btnConfirm.setBackGroundColor(new Color(52, 132, 240));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnConfirm.setBackGroundColor(new Color(115, 168, 240));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                btnListener.ClickListener(0);
                dialog.dispose();
            }
            
        });
        
        this.btnDeny.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                btnDeny.setBackGroundColor(new Color(52, 132, 240));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnDeny.setBackGroundColor(new Color(115, 168, 240));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                dialog.dispose();
            }
            
        });
        
        dialog.add(content);
        dialog.setVisible(true);
    }
    
    public Dialog(int w, int h, String message, ArrayList<String> list, MouseClickReturnString listener){
        
        this.listener = listener;
        
        dialog.setUndecorated(true);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.setBackground(new Color(1, 1, 1, 1));
        dialog.setSize(new Dimension(w, h));
        
        JLabel mess = new JLabel(message);
        mess.setHorizontalAlignment(SwingConstants.CENTER);
        
        btnConfirm = new RoundedPanel(60, 30, new Color(52, 132, 240), 5);
        btnConfirm.setLayout(new GridLayout(1,1));
        JLabel confirmText = new JLabel("OK");
        confirmText.setHorizontalAlignment(SwingConstants.CENTER);
        confirmText.setFont(new Font("San-Serif", Font.PLAIN, 13));
        confirmText.setForeground(Color.WHITE);
        btnConfirm.add(confirmText);
        btnConfirm.setPreferredSize(new Dimension(150, 50));
        
        ListView listView = new ListView(list, w, 0, 45);
        listView.setItemClickListener(new CustomMouseClick() {
            @Override
            public void CustomItemClick(Item item) {
                selectedString = list.get(item.getPosition());
                mess.setText("Giao đến: "+selectedString);
            }
        });
        JPanel listViewContain = new JPanel(new FlowLayout(0, 0, 0));
        listViewContain.add(listView);
        CustomScrollPane scrollPane = new CustomScrollPane(listViewContain, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER, w, 150);
        scrollPane.setPreferredSize(new Dimension(w, 150));
        
        content.setPreferredSize(new Dimension(w, h));
        content.setLayout(new BorderLayout(5, 10));
        content.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        content.add(mess, BorderLayout.NORTH);
        content.add(scrollPane);
        content.add(btnConfirm, BorderLayout.SOUTH);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        dialog.setLocation(dim.width/2-dialog.getSize().width/2, dim.height/2-dialog.getSize().height/2);

        content.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                dialog.getComponentAt(initialClick);
            }
        });

        content.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

                // get location of Window
                int thisX = dialog.getLocation().x;
                int thisY = dialog.getLocation().y;

                // Determine how much the mouse moved since the initial click
                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;

                // Move window to this position
                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                dialog.setLocation(X, Y);
            }
        });
        
        btnConfirm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                btnConfirm.setBackGroundColor(new Color(52, 132, 240));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnConfirm.setBackGroundColor(new Color(115, 168, 240));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                listener.ClickListener(selectedString);
                dialog.dispose();
            }
            
        });
        
        dialog.add(content);
        dialog.setVisible(true);
    }
    
}
