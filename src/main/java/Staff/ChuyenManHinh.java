/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Staff;

import Database.DatabaseManager;
import Entity.Staff;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import layout.Login;

/**
 *
 * @author Dell
 */
public class ChuyenManHinh {
    private JPanel jpView;
    private String moduleSelect ="";
    
    private DatabaseManager database =new DatabaseManager("BitSneaker");
    
    ArrayList<ChucNangNhanVien> list = new ArrayList<ChucNangNhanVien>();
    
    private ArrayList<Staff> listStaff;
    
    private JFrame mainFrame;
    
    public ChuyenManHinh(JPanel jpView, ArrayList<Staff> list, JFrame parent) {
        this.jpView = jpView;
        this.listStaff = list;
        this.mainFrame = parent;
    }
    
    public void setView(JPanel jpModule, JLabel lblModule){
        moduleSelect = "QuanLy";
//        jpModule.setBackground(new Color(96,100,191));
//        lblModule.setBackground(new Color(96,100,191));
        JPanel trangChu = new JpTrangChu(this.listStaff);
        try {
            jpView.removeAll();
            jpView.setLayout(new BorderLayout());
        } catch (Exception e) {
            System.out.println("");
        }
        jpView.add(trangChu);
        jpView.validate();
        jpView.repaint();
    }
    
    public void setEvent(ArrayList<ChucNangNhanVien> list){
        this.list = list;
        for (ChucNangNhanVien i : list){
            i.getLbl().addMouseListener(new LabelEvent(i.getModule(),i.getJpn(),i.getLbl()));
        }
    }
    
    class LabelEvent implements MouseListener{
        private JPanel node;
        private String kind;
        
        private JPanel jpItem;
        private JLabel lblModule;

        public LabelEvent( String kind, JPanel jpItem, JLabel lblModule) {
            this.kind = kind;
            this.jpItem = jpItem;
            this.lblModule = lblModule;
        }
        
        
        @Override
        public void mouseClicked(MouseEvent e) {
            String username="";
            switch(kind){
                case "QuanLy":
                    node = new JpTrangChu(listStaff);
                    break;
                case "TaoHoaDon":
                    username = yeuCauDangNhap();
                    if(!username.equals("")){
                        node = new JpTaoHoaDon(username);
                    }
                    else{
                        node = new JpTrangChu(listStaff);
                    }
                    break;
                case "DuyetHoaDon":
                    node = new JpDuyetHoaDon(listStaff);
                    break;
                case "TaoDonNhapHang":
                    username = yeuCauDangNhap();
                    if(!username.equals("")){
                        node = new JpTaoDonNhapHang(username);
                    }
                    else{
                        node = new JpTrangChu(listStaff);
                    }
                    break;
                case "NhanDonNhapHang":
                    node = new JpNhanDonDatHang(listStaff);
                    break;
                case "ThongKeTrongNgay":
                    node = new JpThongKeTrongNgay();
                    break;
                case "DangNhap":
                    username = dangNhap();
                    if(!username.equals("")){
                        listStaff.add(getStaffInfo(username));
                        node = new JpTrangChu(listStaff);
                    }
                    else{
                        node = new JpTrangChu(listStaff);
                    }
                    break;
                case "DangXuat":
                    username = yeuCauDangNhap();
                    if(!username.equals("")){
                        for(Staff s:listStaff){
                            if(s.getAccountID().equals(username)){
                                listStaff.remove(s);
                                break;
                            }
                        }
                        if(listStaff.size()>0){
                            node = new JpTrangChu(listStaff);
                        }
                        else{
                            mainFrame.dispose();
                            new Login();
                        }
                    }
                    else{
                        node = new JpTrangChu(listStaff);
                    }
                    break;
                default:
                    break;
            }
            jpView.removeAll();
            jpView.setLayout(new BorderLayout());
            jpView.add(node);
            jpView.validate();
            jpView.repaint();
           
            setChangeBackground(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) { //nhấn
//            moduleSelect = kind;
//            jpItem.setBackground(new Color(204,204,255));
//            lblModule.setBackground(new Color(90,100,191));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {   //rê qua
//            if (moduleSelect.equalsIgnoreCase("QuanLy")) {
//                jpItem.setBackground(new Color(102,153,255));
//                //lblModule.setBackground(new Color(204,204,255));
//            }
            //jpItem.setBackground(new Color());
            //lblModule.setBackground(new Color(90,100,191));
        }

        @Override
        public void mouseExited(MouseEvent e) { //
//            if (!moduleSelect.equalsIgnoreCase(kind)) {
//                jpItem.setBackground(new Color(204,204,255));
                //lblModule.setBackground(new Color(204,204,255));
//        }
        
    }

    }   
    private void setChangeBackground (String kind){
        for(ChucNangNhanVien i : list){
            if(i.getModule().equalsIgnoreCase("QuanLy")){
                i.getJpn().setBackground(new Color(102,153,255));
            }
            else if(i.getModule().equalsIgnoreCase(kind)){
                i.getJpn().setBackground(new Color(170, 221, 248));
                //i.getLbl().setBackground(new Color());
            }
            else{
                i.getJpn().setBackground(new Color(204,204,255));
//                i.getLbl().setBackground(new Color(170, 5, 7));
            }
        }
    }
    
    private String yeuCauDangNhap(){
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
        label.add(new JLabel("AccountID", SwingConstants.RIGHT));
        label.add(new JLabel("Password", SwingConstants.RIGHT));
        panel.add(label, BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
        Vector<String> vt = new Vector<>();
        for(Staff s: listStaff)
            vt.add(s.getfName()+" "+s.getlName());
        JComboBox<String> username = new JComboBox<>(vt);
        controls.add(username);
        JTextField password = new JTextField();
        controls.add(password);
        panel.add(controls, BorderLayout.CENTER);

        int asw = JOptionPane.showConfirmDialog(null, panel, "Đăng nhập để thực hiện chức năng", JOptionPane.OK_CANCEL_OPTION); 

        if(asw == JOptionPane.OK_OPTION){
            String accountID = listStaff.get(username.getSelectedIndex()).getAccountID();
            if(kiemTraAccount(accountID , password.getText().toString(), "Staff") > 0){
                return accountID;
            }
            else{
                JOptionPane.showMessageDialog(null, "Sai mật khẩu");
                JOptionPane.showMessageDialog(null, "Vui lòng đăng nhập trước khi tạo đơn nhé!");
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Vui lòng đăng nhập trước khi tạo đơn nhé!");
            return "";
        }
        return "";
    }
    
    private String dangNhap(){
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
        label.add(new JLabel("AccountID", SwingConstants.RIGHT));
        label.add(new JLabel("Password", SwingConstants.RIGHT));
        panel.add(label, BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
        JTextField username = new JTextField();
        controls.add(username);
        JTextField password = new JTextField();
        controls.add(password);
        panel.add(controls, BorderLayout.CENTER);

        int asw = JOptionPane.showConfirmDialog(null, panel, "Đăng nhập", JOptionPane.OK_CANCEL_OPTION); 

        if(asw == JOptionPane.OK_OPTION){
            if(kiemTraAccount(username.getText(), password.getText().toString(), "Staff") > 0){
                return username.getText();
            }
            else{
                JOptionPane.showMessageDialog(null, "Sai mật khẩu");
            }
        }
        
        return "";
    }
    
    public int kiemTraAccount(String accountID, String password, String type){
        
        int kq = -1;
        
        try {
            ResultSet rs = database.queryData("SELECT AccountID, Password, Type FROM ACCOUNT WHERE AccountID = ('"+accountID+"') AND Password = ('"+password+"') AND Type =('"+type+"')");
            if(rs.next()){
                kq = 1;
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi hàm kiểm tra tài khoản");
        }
        return kq;
    }
    
    private Staff getStaffInfo(String accountID){
        
        ResultSet rs = database.queryData("SELECT * FROM STAFF WHERE AccountID = '"+accountID+"'");
        try {
            if(rs.next()){
                return new Staff(rs.getString(1), accountID, rs.getString(2), rs.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
}
