/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Database.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vntvn
 */
public class NhanVienPanel extends javax.swing.JPanel {
    
    DatabaseManager database = new DatabaseManager("BitSneaker");
    
     public void layDSNhanVien() {
        try {
            DefaultTableModel dtm=(DefaultTableModel)tblNhanVien.getModel();
            dtm.setNumRows(0);
            Vector vt;
//            Connection ketNoi= KetNoiCSDL.layKetNoi();
            String sql="select STAFF.*, ACC.Password from STAFF INNER JOIN (SELECT ACCOUNT.AccountID, ACCOUNT.Password FROM ACCOUNT) AS ACC ON STAFF.AccountID = ACC.AccountID";
//            PreparedStatement ps= ketNoi.prepareStatement(sql);
//            ResultSet rs=ps.executeQuery();
            ResultSet rs = database.queryData(sql);
            while(rs.next()){
                vt=new Vector();
                vt.add(rs.getString("StaffID"));
                vt.add(rs.getString("FirstName"));
                vt.add(rs.getString("LastName"));
                vt.add(rs.getString("DOB"));
                vt.add(rs.getString("IDCardNumber"));
                vt.add(rs.getString("Address"));
                vt.add(rs.getString("PhoneNumber"));
                vt.add(rs.getString("Position"));
                vt.add(rs.getString("AccountID"));
                vt.add(rs.getString("Password"));
                dtm.addRow(vt);
                
                tblNhanVien.setModel(dtm);
                        } 
//            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     public int timNhanVien(String staffID,String lastName){
       int check = 0;
//       Connection ketNoi=KetNoiCSDL.layKetNoi();
       DefaultTableModel dtm=(DefaultTableModel)tblNhanVien.getModel();
       dtm.setNumRows(0);
       if(staffID.length()!=0 &&lastName.length()==0){
        String sql="select STAFF.*, ACC.Password from STAFF INNER JOIN (SELECT ACCOUNT.AccountID, ACCOUNT.Password FROM ACCOUNT) AS ACC ON STAFF.AccountID = ACC.AccountID WHERE StaffID='"+staffID+"'";
        try {
//            Statement ps=ketNoi.createStatement();
//            ResultSet rs=ps.executeQuery(sql);
            ResultSet rs = database.queryData(sql);
            
                while(rs.next()){
                Vector vt = new Vector();
                vt.add(rs.getString("StaffID"));
                vt.add(rs.getString("FirstName"));
                vt.add(rs.getString("LastName"));
                vt.add(rs.getString("DOB"));
                vt.add(rs.getString("IDCardNumber"));
                vt.add(rs.getString("Address"));
                vt.add(rs.getString("PhoneNumber"));
                vt.add(rs.getString("Position"));
                vt.add(rs.getString("AccountID"));
                vt.add(rs.getString("Password"));
                dtm.addRow(vt);
                
                tblNhanVien.setModel(dtm);                       } 
//            ps.close();
            rs.close();
            check=1;
            
            //ps.setString(2, "");
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
       else if(lastName.length()!=0&&staffID.length()==0){
           String sql="select STAFF.*, ACC.Password from STAFF INNER JOIN (SELECT ACCOUNT.AccountID, ACCOUNT.Password FROM ACCOUNT) AS ACC ON STAFF.AccountID = ACC.AccountID WHERE LastName LIKE N'%"+ lastName+"%'";
        try {
//            Statement ps=ketNoi.createStatement();
//            ResultSet rs=ps.executeQuery(sql);
            ResultSet rs = database.queryData(sql);
            
                while(rs.next()){
                Vector vt = new Vector();
                vt.add(rs.getString("StaffID"));
                vt.add(rs.getString("FirstName"));
                vt.add(rs.getString("LastName"));
                vt.add(rs.getString("DOB"));
                vt.add(rs.getString("IDCardNumber"));
                vt.add(rs.getString("Address"));
                vt.add(rs.getString("PhoneNumber"));
                vt.add(rs.getString("Position"));
                vt.add(rs.getString("AccountID"));
                vt.add(rs.getString("Password"));
                dtm.addRow(vt);
                
                tblNhanVien.setModel(dtm);                       } 
//            ps.close();
            rs.close();
                check=1;
            
            //ps.setString(2, "");
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
       else {
           String sql="select STAFF.*, ACC.Password from STAFF INNER JOIN (SELECT ACCOUNT.AccountID, ACCOUNT.Password FROM ACCOUNT) AS ACC ON STAFF.AccountID = ACC.AccountID WHERE StaffID='"+staffID+"' AND LastName='"+lastName+"'";
        try {
//            Statement ps=ketNoi.createStatement();
//            ResultSet rs=ps.executeQuery(sql);
            ResultSet rs = database.queryData(sql);
            
                while(rs.next()){
                Vector vt = new Vector();
                vt.add(rs.getString("StaffID"));
                vt.add(rs.getString("FirstName"));
                vt.add(rs.getString("LastName"));
                vt.add(rs.getString("DOB"));
                vt.add(rs.getString("IDCardNumber"));
                vt.add(rs.getString("Address"));
                vt.add(rs.getString("PhoneNumber"));
                vt.add(rs.getString("Position"));
                vt.add(rs.getString("AccountID"));
                vt.add(rs.getString("Password"));
                dtm.addRow(vt);
                
                tblNhanVien.setModel(dtm);                       } 
//            ps.close();
            rs.close();
                check=1;
            
            //ps.setString(2, "");
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
       return check;
   }
     public int tonTaiNhanVien(String staffID){
        int check = 0;
//       Connection ketNoi=KetNoiCSDL.layKetNoi();    
             String sql="SELECT * FROM STAFF WHERE StaffID='"+staffID+"'";
        try {
            
//            Statement ps=ketNoi.createStatement();
//            ResultSet rs=ps.executeQuery(sql);   
            ResultSet rs = database.queryData(sql);
            if(rs.next()){
            check=1;}}
            
        catch (SQLException ex) {
            Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
     }
     public int tonTaiAccount(String accountID){
        int check = 0;
//       Connection ketNoi=KetNoiCSDL.layKetNoi();    
             String sql="SELECT * FROM ACCOUNT WHERE AccountID='"+accountID+"'";
        try {
            
//            Statement ps=ketNoi.createStatement();
//            ResultSet rs=ps.executeQuery(sql);   
            ResultSet rs = database.queryData(sql);
            if(rs.next()){
            check=1;}}
            
        catch (SQLException ex) {
            Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
     }
       public void themNhanVien(String staffID, String firstName, String lastName, String DOB, String idCardNumber, String address, String phoneNumber, String position,String accountID,String pass){
//        Connection ketNoi=KetNoiCSDL.layKetNoi();       
        String sqlStaff="insert into STAFF (StaffID ,FirstName ,LastName,DOB,IDCardNumber ,Address,PhoneNumber,Position,AccountID) values('"+staffID+"',N'"+firstName+"',N'"+lastName+"','"+DOB+"','"+idCardNumber+"',N'"+address+"','"+phoneNumber+"',N'"+position+"','"+accountID+"')";
        String sqlAccount="insert into ACCOUNT (AccountID ,Password ,Status,Type) values('"+accountID+"','"+pass+"','True','staff')";
//        try {
            
//            Statement psAccount=ketNoi.createStatement();
//            psAccount.executeUpdate(sqlAccount);
//            Statement psStaff=ketNoi.createStatement();
//            psStaff.executeUpdate(sqlStaff);
            
        database.queryUpdate(sqlAccount);
        database.queryUpdate(sqlStaff);

            //ps.setString(2, "");
//        } catch (SQLException ex) {
//            Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    public void xoaNhanVien(String maNhanVien,String accountID){
//        Connection ketNoi=KetNoiCSDL.layKetNoi();
        String sql="delete from STAFF where StaffID='"+maNhanVien+"'";
        String sqlAccount="delete from ACCOUNT where AccountID='"+accountID+"'";
//        Statement ps;
//        Statement psAccount;
//        try {
//            ps = ketNoi.createStatement();
//            ps.executeUpdate(sql);
//            psAccount = ketNoi.createStatement();
//            psAccount.executeUpdate(sqlAccount);
//        } catch (SQLException ex) {
//            Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
        database.queryUpdate(sql);
        database.queryUpdate(sqlAccount);
        
    }
    public void suaThongTinNhanVien(String staffID, String firstName, String lastName, String DOB, String idCardNumber, String address, String phoneNumber, String position, String accountID, String password) {
//        try {
//            Connection ketNoi=KetNoiCSDL.layKetNoi();
        String sql="update STAFF set FirstName=N'"+firstName+"',LastName=N'"+lastName+"',DOB='"+DOB+"',IDCardNumber='"+idCardNumber+"',Address=N'"+address+"',PhoneNumber='"+phoneNumber+"',Position=N'"+position+"' where StaffID='"+staffID+"'";
        String sql2="update ACCOUNT set Password = '"+password+"' where AccountID='"+accountID+"'";
//            PreparedStatement ps=ketNoi.prepareStatement(sql);
//            ps.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
        database.queryUpdate(sql);
        database.queryUpdate(sql2);
        
    }
    public NhanVienPanel() {
        initComponents();
        layDSNhanVien();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtTimMaNhanVien = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTimTenNhanVien = new javax.swing.JTextField();
        btnTimNhanVien = new javax.swing.JButton();
        btnReloadNhanVien = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtMaNhanVien = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtHoNhanVien = new javax.swing.JTextField();
        txtTenNhanVien = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtSoTheNhanVien = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtDiaChiNhanVien = new javax.swing.JTextField();
        txtSDTNhanVien = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtChucVuNhanVien = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        btnThemNhanVien = new javax.swing.JButton();
        btnSuaNhanVien = new javax.swing.JButton();
        btnXoaNhanVien = new javax.swing.JButton();
        txtNgaySinh = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtAccountID = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtPassNhanVien = new javax.swing.JPasswordField();

        setPreferredSize(new java.awt.Dimension(1105, 770));

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1105, 800));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "T??m ki???m", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        jLabel7.setText("T??n nh??n vi??n");

        txtTimMaNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimMaNhanVienActionPerformed(evt);
            }
        });

        jLabel8.setText("M?? nh??n vi??n");

        btnTimNhanVien.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnTimNhanVien.setIcon(new javax.swing.ImageIcon("src//main//java//res//iconManager//loupe.png"));
        btnTimNhanVien.setText("T??m ki???m");
        btnTimNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimNhanVienActionPerformed(evt);
            }
        });

        btnReloadNhanVien.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnReloadNhanVien.setIcon(new javax.swing.ImageIcon("src//main//java//res//iconManager//reload.png"));
        btnReloadNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadNhanVienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtTimTenNhanVien, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnTimNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReloadNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtTimMaNhanVien))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTimTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTimNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReloadNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh s??ch nh??n vi??n", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "M?? nh??n vi??n", "H??? v?? t??n ?????m", "T??n", "Ng??y sinh", "S??? CMND", "?????a ch???", "S??? ??i???n tho???i", "Ch???c v???", "T??i kho???n", "M???t kh???u"
            }
        ));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(130, 130, 130))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Th??ng tin nh??n vi??n", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        jLabel4.setText("M?? nh??n vi??n:");

        jLabel9.setText("H??? v?? t??n ?????m:");

        txtHoNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoNhanVienActionPerformed(evt);
            }
        });

        jLabel10.setText("T??n:");

        jLabel11.setText("Ng??y sinh (mm-dd-yyyy):");

        jLabel12.setText("S??? CMND:");

        jLabel13.setText("?????a ch???:");

        txtSDTNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTNhanVienActionPerformed(evt);
            }
        });

        jLabel14.setText("S??? ??i???n tho???i:");

        jLabel15.setText("Ch???c v???");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ch???c n??ng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        btnThemNhanVien.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnThemNhanVien.setIcon(new javax.swing.ImageIcon("src//main//java//res//iconManager//add-file.png"));
        btnThemNhanVien.setText("Th??m");
        btnThemNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanVienActionPerformed(evt);
            }
        });

        btnSuaNhanVien.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnSuaNhanVien.setIcon(new javax.swing.ImageIcon("src//main//java//res//iconManager//edit.png"));
        btnSuaNhanVien.setText("S???a");
        btnSuaNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNhanVienActionPerformed(evt);
            }
        });

        btnXoaNhanVien.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnXoaNhanVien.setIcon(new javax.swing.ImageIcon("src//main//java//res//iconManager//remove.png"));
        btnXoaNhanVien.setText("X??a");
        btnXoaNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNhanVienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXoaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(btnSuaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSuaNhanVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThemNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnXoaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel17.setText("T??i kho???n:");

        jLabel18.setText("M???t kh???u:");

        txtPassNhanVien.setEchoChar('\u0000');

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtChucVuNhanVien))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtTenNhanVien, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtHoNhanVien)))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(9, 9, 9))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(4, 4, 4)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSDTNhanVien)
                            .addComponent(txtDiaChiNhanVien)
                            .addComponent(txtSoTheNhanVien)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaNhanVien, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtAccountID)
                            .addComponent(txtPassNhanVien))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(4, 4, 4)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtHoNhanVien)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTenNhanVien)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNgaySinh)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSoTheNhanVien)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDiaChiNhanVien)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSDTNhanVien)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtChucVuNhanVien)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtAccountID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPassNhanVien)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Nh??n Vi??n", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimMaNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimMaNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimMaNhanVienActionPerformed

    private void txtHoNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoNhanVienActionPerformed

    private void btnSuaNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNhanVienActionPerformed
         DefaultTableModel dtm=(DefaultTableModel)tblNhanVien.getModel();
//        int i=Integer.parseInt((String) dtm.getValueAt(tblNhanVien.getSelectedRow(), 0)) ;
//        System.out.println(i);
        String chuanHoaNgay="^(0?[1-9]|1[012])[- /.](0?[1-9]|[12][0-9]|3[01])[- /.](19|20)?[0-9]{2}$";
        String chuanHoaSDT="(84|0[3|5|7|8|9])+([0-9]{8})";
        String staffID=txtMaNhanVien.getText();
        String firstName=txtHoNhanVien.getText();
        String lastName=txtTenNhanVien.getText();
        String DOB=txtNgaySinh.getText();
        String idCardNumber=txtSoTheNhanVien.getText();
        String address=txtDiaChiNhanVien.getText();
        String phoneNumber=txtSDTNhanVien.getText();
        String position=txtChucVuNhanVien.getText();
        String accountID = txtAccountID.getText();
        String password = String.valueOf(txtPassNhanVien.getPassword());
        if(staffID.length()==0){
            JOptionPane.showMessageDialog(this, "H??y ch???n nh??n vi??n mu???n s???a th??ng tin");
        }
        else {
        int luaChon=JOptionPane.showConfirmDialog(this, "B???n c?? mu???n s???a th??ng tin nh??n vi??n?");
        if(JOptionPane.YES_OPTION==luaChon){
            if(staffID.length()==0){
            JOptionPane.showMessageDialog(this, "M?? nh??n vi??n kh??ng ???????c ????? tr???ng!");
        }
        else if(tonTaiNhanVien(staffID)==0){
            JOptionPane.showMessageDialog(this, "M?? nh??n vi??n n??y kh??ng t???n t???i trong danh s??ch!");
        }
        else if(firstName.length()==0){
            JOptionPane.showMessageDialog(this, "H??? v?? t??n ?????m kh??ng ???????c ????? tr???ng!");
        }
        else if(lastName.length()==0){
            JOptionPane.showMessageDialog(this, "T??n nh??n vi??n kh??ng ???????c ????? tr???ng!");
        }
        else if(DOB.length()==0){
            JOptionPane.showMessageDialog(this, "Ng??y sinh kh??ng ???????c ????? tr???ng!");
        }
        else if(DOB.matches(chuanHoaNgay)==false){
            JOptionPane.showMessageDialog(this, "Ng??y sinh kh??ng h???p l??? (mm/dd/yyyy)!");
        }
        else if(idCardNumber.length()==0){
            JOptionPane.showMessageDialog(this, "S??? th??? nh??n vi??n kh??ng ???????c ????? tr???ng!");
        }
        else if(address.length()==0){
            JOptionPane.showMessageDialog(this, "?????a ch??? kh??ng ???????c ????? tr???ng!");
        }
        else if(phoneNumber.length()==0) {
            
            JOptionPane.showMessageDialog(this, "S??? ??i???n tho???i kh??ng ???????c ????? tr???ng!");
        }else if(phoneNumber.matches(chuanHoaSDT)==false) {
            
            JOptionPane.showMessageDialog(this, "S??? ??i???n tho???i kh??ng h???p l???!");
        }
        else if(position.length()==0) {
            
            JOptionPane.showMessageDialog(this, "Ch???c v??? kh??ng ???????c ????? tr???ng!");
        }
        
        else {
            suaThongTinNhanVien(staffID, firstName, lastName, DOB, idCardNumber, address, phoneNumber, position, accountID, password);
            this.layDSNhanVien();
            txtMaNhanVien.setText("");
            txtHoNhanVien.setText("");
            txtTenNhanVien.setText("");
            txtNgaySinh.setText("");
            txtSoTheNhanVien.setText("");
            txtDiaChiNhanVien.setText("");
            txtSDTNhanVien.setText("");
            txtChucVuNhanVien.setText("");
            txtAccountID.setText("");
            txtPassNhanVien.setText("");
            JOptionPane.showMessageDialog(this, "S???a th??nh c??ng!");
        }
//            suaThongTinNhanVien(staffID, firstName, lastName, DOB, idCardNumber, address, phoneNumber, position);
            txtAccountID.setEditable(true);
            txtPassNhanVien.setEditable(true);
//            this.layDSNhanVien();
            
        }
        }
    }//GEN-LAST:event_btnSuaNhanVienActionPerformed

    private void txtSDTNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTNhanVienActionPerformed

    private void btnThemNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanVienActionPerformed
//        String chuanHoaEmail="^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        String chuanHoaEmail="^(.+)@(.+)$";
//        String chuanHoaNgay="^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$";
        String chuanHoaNgay="^(0?[1-9]|1[012])[- /.](0?[1-9]|[12][0-9]|3[01])[- /.](19|20)?[0-9]{2}$";
        String chuanHoaSDT="(84|0[3|5|7|8|9])+([0-9]{8})";
        String staffID=txtMaNhanVien.getText();
        String firstName=txtHoNhanVien.getText();
        String lastName=txtTenNhanVien.getText();
        String DOB=txtNgaySinh.getText();
        String idCardNumber=txtSoTheNhanVien.getText();
        String address=txtDiaChiNhanVien.getText();
        String phoneNumber=txtSDTNhanVien.getText();
        String position=txtChucVuNhanVien.getText();
        String accountID=txtAccountID.getText();
        String pass=txtPassNhanVien.getText();
        
        if(staffID.length()==0){
            JOptionPane.showMessageDialog(this, "M?? nh??n vi??n kh??ng ???????c ????? tr???ng!");
        }
        else if(tonTaiNhanVien(staffID)==1){
            JOptionPane.showMessageDialog(this, "M?? nh??n vi??n n??y ???? t???n t???i trong danh s??ch!");
        }
        else if(firstName.length()==0){
            JOptionPane.showMessageDialog(this, "H??? v?? t??n ?????m kh??ng ???????c ????? tr???ng!");
        }
        else if(lastName.length()==0){
            JOptionPane.showMessageDialog(this, "T??n nh??n vi??n kh??ng ???????c ????? tr???ng!");
        }
        else if(DOB.length()==0){
            JOptionPane.showMessageDialog(this, "Ng??y sinh kh??ng ???????c ????? tr???ng!");
        }
        else if(DOB.matches(chuanHoaNgay)==false){
            JOptionPane.showMessageDialog(this, "Ng??y sinh kh??ng h???p l??? (mm/dd/yyyy)!");
        }
        else if(idCardNumber.length()==0){
            JOptionPane.showMessageDialog(this, "S??? th??? nh??n vi??n kh??ng ???????c ????? tr???ng!");
        }
        else if(address.length()==0){
            JOptionPane.showMessageDialog(this, "?????a ch??? kh??ng ???????c ????? tr???ng!");
        }
        else if(phoneNumber.length()==0) {
            
            JOptionPane.showMessageDialog(this, "S??? ??i???n tho???i kh??ng ???????c ????? tr???ng!");
        }else if(phoneNumber.matches(chuanHoaSDT)==false) {
            
            JOptionPane.showMessageDialog(this, "S??? ??i???n tho???i kh??ng h???p l???!");
        }
        else if(position.length()==0) {
            
            JOptionPane.showMessageDialog(this, "Ch???c v??? kh??ng ???????c ????? tr???ng!");
        }
        else if(accountID.length()==0) {
            
            JOptionPane.showMessageDialog(this, "T??n t??i kho???n kh??ng ???????c ????? tr???ng!");
        }
        else if(tonTaiAccount(accountID)==1) {
            
            JOptionPane.showMessageDialog(this, "T??i kho???n n??y ???? t???n t???i!");
        }
        else if(pass.length()==0) {
            
            JOptionPane.showMessageDialog(this, "M???t kh???u kh??ng ???????c ????? tr???ng!");
        }
        else if(accountID.matches(chuanHoaEmail)==false){
            JOptionPane.showMessageDialog(this, "Email kh??ng h???p l???");
            System.out.println(accountID);
        }
        else {
        themNhanVien(staffID, firstName, lastName, DOB, idCardNumber, address, phoneNumber, position,accountID,pass);
        this.layDSNhanVien();
        txtMaNhanVien.setText("");
        txtHoNhanVien.setText("");
        txtTenNhanVien.setText("");
        txtNgaySinh.setText("");
        txtSoTheNhanVien.setText("");
        txtDiaChiNhanVien.setText("");
        txtSDTNhanVien.setText("");
        txtChucVuNhanVien.setText("");
        txtAccountID.setText("");
        txtPassNhanVien.setText("");
       
        }
    }//GEN-LAST:event_btnThemNhanVienActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        DefaultTableModel dtm=(DefaultTableModel) tblNhanVien.getModel();
         int luaChon=tblNhanVien.getSelectedRow();
        txtMaNhanVien.setText((String) dtm.getValueAt(luaChon, 0));
        txtHoNhanVien.setText((String) dtm.getValueAt(luaChon, 1));
        txtTenNhanVien.setText((String) dtm.getValueAt(luaChon, 2));
        txtNgaySinh.setText((String) dtm.getValueAt(luaChon, 3));
        txtSoTheNhanVien.setText((String) dtm.getValueAt(luaChon, 4));
        txtDiaChiNhanVien.setText((String) dtm.getValueAt(luaChon, 5));
        txtSDTNhanVien.setText((String) dtm.getValueAt(luaChon, 6));
        txtChucVuNhanVien.setText((String) dtm.getValueAt(luaChon, 7));
        txtAccountID.setText((String) dtm.getValueAt(luaChon, 8));
        txtPassNhanVien.setText((String) dtm.getValueAt(luaChon, 9));
//        txtAccountID.setEditable(false);
//        txtPassNhanVien.setEditable(false);
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void btnXoaNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNhanVienActionPerformed
       String maNhanVien=txtMaNhanVien.getText();
       String accountID=txtAccountID.getText();
        if(maNhanVien.length()==0){
            JOptionPane.showMessageDialog(this, "H??y ch???n nh??n vi??n ????? x??a!");
        }
        else {
         DefaultTableModel dtm=(DefaultTableModel) tblNhanVien.getModel();
//         int i=Integer.parseInt((String) dtm.getValueAt(tblNhanVien.getSelectedRow(), 0)); 
         int luaChon=JOptionPane.showConfirmDialog(this, "B???n c?? mu???n x??a nh??n vi??n n??y?");
        if(JOptionPane.YES_OPTION==luaChon){
            xoaNhanVien(maNhanVien,accountID);
            this.layDSNhanVien();
            JOptionPane.showMessageDialog(this, "X??a th??nh c??ng!");
            txtAccountID.setEditable(true);
            txtPassNhanVien.setEditable(true);
            this.layDSNhanVien();
            
        }
        }
        txtMaNhanVien.setText("");
        txtHoNhanVien.setText("");
        txtTenNhanVien.setText("");
        txtNgaySinh.setText("");
        txtSoTheNhanVien.setText("");
        txtDiaChiNhanVien.setText("");
        txtSDTNhanVien.setText("");
        txtChucVuNhanVien.setText("");
        txtAccountID.setText("");
        
    }//GEN-LAST:event_btnXoaNhanVienActionPerformed

    private void btnReloadNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadNhanVienActionPerformed
        // TODO add your handling code here:
        this.layDSNhanVien();
        txtTimMaNhanVien.setText("");
        txtTimTenNhanVien.setText("");
        txtAccountID.setEditable(true);
        txtPassNhanVien.setEditable(true);
    }//GEN-LAST:event_btnReloadNhanVienActionPerformed

    private void btnTimNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimNhanVienActionPerformed
        // TODO add your handling code here:
         String staffID=txtTimMaNhanVien.getText();
        String name=txtTimTenNhanVien.getText();
        if(staffID.length()==0 && name.length()==0){
            JOptionPane.showMessageDialog(this, "H??y nh???p th??ng tin nh??n vi??n c???n t??m!");
        }
        else {
         DefaultTableModel dtm=(DefaultTableModel) tblNhanVien.getModel();        
        if(timNhanVien(staffID, name)==1){
            
        }
        else{
            JOptionPane.showMessageDialog(this, "Kh??ng t??m th???y nh??n vi??n c???n t??m trong danh s??ch");
            this.layDSNhanVien();
        
        }
        }
    }//GEN-LAST:event_btnTimNhanVienActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReloadNhanVien;
    private javax.swing.JButton btnSuaNhanVien;
    private javax.swing.JButton btnThemNhanVien;
    private javax.swing.JButton btnTimNhanVien;
    private javax.swing.JButton btnXoaNhanVien;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtAccountID;
    private javax.swing.JTextField txtChucVuNhanVien;
    private javax.swing.JTextField txtDiaChiNhanVien;
    private javax.swing.JTextField txtHoNhanVien;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JPasswordField txtPassNhanVien;
    private javax.swing.JTextField txtSDTNhanVien;
    private javax.swing.JTextField txtSoTheNhanVien;
    private javax.swing.JTextField txtTenNhanVien;
    private javax.swing.JTextField txtTimMaNhanVien;
    private javax.swing.JTextField txtTimTenNhanVien;
    // End of variables declaration//GEN-END:variables
}
