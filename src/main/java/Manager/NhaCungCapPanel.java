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
public class NhaCungCapPanel extends javax.swing.JPanel {

    DatabaseManager database = new DatabaseManager("BitSneaker");
    
     public void layDSNhaCungCap() {
        try {
            DefaultTableModel dtm=(DefaultTableModel)tblDSNhaCungCap.getModel();
            dtm.setNumRows(0);
            Vector vt;
//            Connection ketNoi= KetNoiCSDL.layKetNoi();
            String sql="select * from SUPPLIER";
//            PreparedStatement ps= ketNoi.prepareStatement(sql);
//            ResultSet rs=ps.executeQuery();
            ResultSet rs = database.queryData(sql);
            while(rs.next()){
                vt=new Vector();
                vt.add(rs.getString("SupplierID"));
                vt.add(rs.getString("Name"));
                vt.add(rs.getString("City"));
                vt.add(rs.getString("PhoneNumber"));
                vt.add(rs.getString("Address"));
                dtm.addRow(vt);
                
                tblDSNhaCungCap.setModel(dtm);                       } 
//            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     public int timNhaCungCap(String supplierID,String name){
       int check = 0;
//       Connection ketNoi=KetNoiCSDL.layKetNoi();
       DefaultTableModel dtm=(DefaultTableModel)tblDSNhaCungCap.getModel();
       dtm.setNumRows(0);
       if(supplierID.length()!=0 &&name.length()==0){
        String sql="SELECT * FROM SUPPLIER WHERE SupplierID='"+supplierID+"'";
        try {
//            Statement ps=ketNoi.createStatement();
//            ResultSet rs=ps.executeQuery(sql);
            ResultSet rs = database.queryData(sql);
            
                while(rs.next()){
                Vector vt = new Vector();
                vt.add(rs.getString("SupplierID"));
                vt.add(rs.getString("Name"));
                vt.add(rs.getString("City"));
                vt.add(rs.getString("PhoneNumber"));
                vt.add(rs.getString("Address"));
                dtm.addRow(vt);
                
                tblDSNhaCungCap.setModel(dtm);                       } 
//            ps.close();
            rs.close();
            check=1;
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
       else if(name.length()!=0&&supplierID.length()==0){
           String sql="SELECT * FROM SUPPLIER WHERE Name LIKE N'"+ name+"%'";
        try {
//            Statement ps=ketNoi.createStatement();
//            ResultSet rs=ps.executeQuery(sql);
            ResultSet rs = database.queryData(sql);
            
                while(rs.next()){
                Vector vt = new Vector();
                vt.add(rs.getString("SupplierID"));
                vt.add(rs.getString("Name"));
                vt.add(rs.getString("City"));
                vt.add(rs.getString("PhoneNumber"));
                vt.add(rs.getString("Address"));
                dtm.addRow(vt);
                
                tblDSNhaCungCap.setModel(dtm);                       } 
//            ps.close();
            rs.close();
                check=1;
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
       else {
           String sql="SELECT * FROM SUPPLIER WHERE SupplierID='"+supplierID+"' AND Name LIKE N'"+ name+"%'";
        try {
//            Statement ps=ketNoi.createStatement();
//            ResultSet rs=ps.executeQuery(sql);
            ResultSet rs = database.queryData(sql);
            
                while(rs.next()){
                Vector vt = new Vector();
                vt.add(rs.getString("CustomerID"));
                vt.add(rs.getString("Name"));
                vt.add(rs.getString("City"));
                vt.add(rs.getString("PhoneNumber"));
                vt.add(rs.getString("Address"));
                dtm.addRow(vt);
                
                tblDSNhaCungCap.setModel(dtm);                       } 
//            ps.close();
            rs.close();
                check=1;            
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
       return check;
   }
     
     
     public int tonTaiNhaCungCap(String supplierID){
        int check = 0;
//       Connection ketNoi=KetNoiCSDL.layKetNoi();    
             String sql="SELECT * FROM SUPPLIER WHERE SupplierID='"+supplierID+"'";
        try {
//            Statement ps=ketNoi.createStatement();
//            ResultSet rs=ps.executeQuery(sql);     
            ResultSet rs = database.queryData(sql);
            if(rs.next()){
            check=1;}}
        catch (SQLException ex) {
            Logger.getLogger(NhaCungCapPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
     }

     
     
     
      public void themNhaCungCap(String supplierID, String name, String city, String phoneNumber, String address){
//        Connection ketNoi=KetNoiCSDL.layKetNoi();
        String sql="insert into SUPPLIER (SupplierID ,Name ,City,PhoneNumber,Address) values('"+supplierID+"',N'"+name+"',N'"+city+"','"+phoneNumber+"',N'"+address+"')";
//        try {
//            Statement ps=ketNoi.createStatement();
//            ps.executeUpdate(sql);
//            //ps.setString(2, "");
//        } catch (SQLException ex) {
//            Logger.getLogger(NhaCungCapPanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
        database.queryUpdate(sql);
    }
    public void xoaNhaCungCap(String supplierID){
//        Connection ketNoi=KetNoiCSDL.layKetNoi();
        String sql="delete from SUPPLIER where SupplierID='"+supplierID+"'";
//        Statement ps;
//        try {
//            ps = ketNoi.createStatement();
//            ps.executeUpdate(sql);
//        } catch (SQLException ex) {
//            Logger.getLogger(NhaCungCapPanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
        database.queryUpdate(sql);
        
    }
    public void suaThongTinNhaCungCap(String supplierID, String name, String city, String phoneNumber, String address) {
//        try {
//            Connection ketNoi=KetNoiCSDL.layKetNoi();
        String sql="update SUPPLIER set Name=N'"+name+"',City=N'"+city+"', PhoneNumber='"+phoneNumber+"', Address=N'"+address+"' where SupplierID='"+supplierID+"'";
//            PreparedStatement ps=ketNoi.prepareStatement(sql);
//            ps.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(NhaCungCapPanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
        database.queryUpdate(sql);
        
    }
    public NhaCungCapPanel() {
        initComponents();
        layDSNhaCungCap();
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
        txtTimMaNhaCungCap = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTimTenNhaCungCap = new javax.swing.JTextField();
        btnTimNhaCungCap = new javax.swing.JButton();
        btnReloadNhaCungCap = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDSNhaCungCap = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtMaNhaCungCap = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTenNhaCungCap = new javax.swing.JTextField();
        txtThanhPhoNhaCungCap = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtDiaChiNhaCungCap = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        btnThemNhaCungCap = new javax.swing.JButton();
        btnSuaNhaCungCap = new javax.swing.JButton();
        btnXoaNhaCungCap = new javax.swing.JButton();
        txtSDTNhaCungCap = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(1105, 800));

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1104, 800));

        jPanel1.setPreferredSize(new java.awt.Dimension(1022, 800));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        jLabel7.setText("Tên nhà cung cấp");

        txtTimMaNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimMaNhaCungCapActionPerformed(evt);
            }
        });

        jLabel8.setText("Mã nhà cung cấp");

        btnTimNhaCungCap.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnTimNhaCungCap.setIcon(new javax.swing.ImageIcon("src//main//java//res//iconManager//loupe.png"));
        btnTimNhaCungCap.setText("Tìm kiếm");
        btnTimNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimNhaCungCapActionPerformed(evt);
            }
        });

        btnReloadNhaCungCap.setIcon(new javax.swing.ImageIcon("src//main//java//res//iconManager//reload.png"));
        btnReloadNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadNhaCungCapActionPerformed(evt);
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnTimNhaCungCap)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReloadNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtTimMaNhaCungCap)
                    .addComponent(txtTimTenNhaCungCap))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimMaNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTimTenNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTimNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReloadNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách nhà cung cấp", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        tblDSNhaCungCap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã nhà cung cấp", "Tên nhà cung cấp", "Thành phố", "Số điện thoại", "Địa chỉ"
            }
        ));
        tblDSNhaCungCap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSNhaCungCapMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDSNhaCungCap);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin nhà cung cấp", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        jLabel4.setText("Mã nhà cung cấp:");

        jLabel9.setText("Tên nhà cung cấp:");

        txtTenNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenNhaCungCapActionPerformed(evt);
            }
        });

        jLabel10.setText("Thành phố:");

        jLabel11.setText("Số điện thoại:");

        jLabel12.setText("Địa chỉ:");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        btnThemNhaCungCap.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnThemNhaCungCap.setIcon(new javax.swing.ImageIcon("src//main//java//res//iconManager//add-file.png"));
        btnThemNhaCungCap.setText("Thêm");
        btnThemNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhaCungCapActionPerformed(evt);
            }
        });

        btnSuaNhaCungCap.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnSuaNhaCungCap.setIcon(new javax.swing.ImageIcon("src//main//java//res//iconManager//edit.png"));
        btnSuaNhaCungCap.setText("Sửa");
        btnSuaNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNhaCungCapActionPerformed(evt);
            }
        });

        btnXoaNhaCungCap.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnXoaNhaCungCap.setIcon(new javax.swing.ImageIcon("src//main//java//res//iconManager//remove.png"));
        btnXoaNhaCungCap.setText("Xóa");
        btnXoaNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNhaCungCapActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnXoaNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnSuaNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThemNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSuaNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnXoaNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtDiaChiNhaCungCap, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtThanhPhoNhaCungCap, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenNhaCungCap, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaNhaCungCap, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                            .addComponent(txtSDTNhaCungCap))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMaNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTenNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtThanhPhoNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSDTNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDiaChiNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28))
        );

        jTabbedPane1.addTab("Nhà cung cấp", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 964, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenNhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenNhaCungCapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenNhaCungCapActionPerformed

    private void btnSuaNhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNhaCungCapActionPerformed
        // TODO add your handling code here:
         DefaultTableModel dtm=(DefaultTableModel)tblDSNhaCungCap.getModel();
//        int i=Integer.parseInt((String) dtm.getValueAt(tblDSNhaCungCap.getSelectedRow(), 0)) ;
//        System.out.println(i);
        String supplierID=txtMaNhaCungCap.getText();
        String name=txtTenNhaCungCap.getText();
        String city=txtThanhPhoNhaCungCap.getText();
        String phoneNumber=txtSDTNhaCungCap.getText();
        String address=txtDiaChiNhaCungCap.getText();
        
        if(supplierID.length()==0){
            JOptionPane.showMessageDialog(this, "Hãy chọn nhà cung cấp muốn sửa thông tin");
        }
        else {
        int luaChon=JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa thông tin nhà cung cấp?");
        if(JOptionPane.YES_OPTION==luaChon){
            suaThongTinNhaCungCap(supplierID, name, city, phoneNumber, address);
            JOptionPane.showMessageDialog(this, "Sửa thành công!");          
            this.layDSNhaCungCap();
            
        }
        }
    }//GEN-LAST:event_btnSuaNhaCungCapActionPerformed

    private void tblDSNhaCungCapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSNhaCungCapMouseClicked
        // TODO add your handling code here:
        DefaultTableModel dtm=(DefaultTableModel) tblDSNhaCungCap.getModel();
         int luaChon=tblDSNhaCungCap.getSelectedRow();
        txtMaNhaCungCap.setText((String) dtm.getValueAt(luaChon, 0));
        txtTenNhaCungCap.setText((String) dtm.getValueAt(luaChon, 1));
        txtThanhPhoNhaCungCap.setText((String) dtm.getValueAt(luaChon, 2));
        txtSDTNhaCungCap.setText((String) dtm.getValueAt(luaChon, 3));
        txtDiaChiNhaCungCap.setText((String) dtm.getValueAt(luaChon, 4));
    }//GEN-LAST:event_tblDSNhaCungCapMouseClicked

    private void btnThemNhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhaCungCapActionPerformed
        // TODO add your handling code here:
         String chuanHoaSDT="(84|0[3|5|7|8|9])+([0-9]{8})";
        String supplierID=txtMaNhaCungCap.getText();
        String name=txtTenNhaCungCap.getText();
        String city=txtThanhPhoNhaCungCap.getText();
        String phoneNumber=txtSDTNhaCungCap.getText();
        String address=txtDiaChiNhaCungCap.getText();
        
        
        if(supplierID.length()==0){
            JOptionPane.showMessageDialog(this, "Mã nhà cung cấp không được để trống!");
        }
        else if(tonTaiNhaCungCap(supplierID)==1){
            JOptionPane.showMessageDialog(this, "Mã nhà cung cấp này đã tồn tại trong danh sách!");
        }
        else if(name.length()==0){
            JOptionPane.showMessageDialog(this, "Tên nhà cung cấp không được để trống!");
        }
        else if(city.length()==0){
            JOptionPane.showMessageDialog(this, "Thành phố không được để trống!");
        }
        else if(address.length()==0){
            JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống!");
        }
        else if(phoneNumber.length()==0) {
            
            JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống!");
        }
       else if(phoneNumber.matches(chuanHoaSDT)==false) {
            
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ!");
        }
        else {
        themNhaCungCap(supplierID, name, city, phoneNumber, address);
        this.layDSNhaCungCap();
        txtMaNhaCungCap.setText("");
        txtTenNhaCungCap.setText("");
        txtThanhPhoNhaCungCap.setText("");
        txtSDTNhaCungCap.setText("");
        txtDiaChiNhaCungCap.setText("");
        
    }//GEN-LAST:event_btnThemNhaCungCapActionPerformed
    }
    private void btnXoaNhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNhaCungCapActionPerformed
        // TODO add your handling code here:
        String supplierID=txtMaNhaCungCap.getText();
        if(supplierID.length()==0){
            JOptionPane.showMessageDialog(this, "Hãy chọn nhà cung cấp để xóa!");
        }
        else {
//            DefaultTableModel dtm=(DefaultTableModel) tblDSNhaCungCap.getModel();
            
            int luaChon=JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa thông tin nhà cung cấp?");
            if(JOptionPane.YES_OPTION==luaChon){
                xoaNhaCungCap(supplierID);
                this.layDSNhaCungCap();
            }
        }
        txtMaNhaCungCap.setText("");
        txtTenNhaCungCap.setText("");
        txtThanhPhoNhaCungCap.setText("");
        txtSDTNhaCungCap.setText("");
        txtDiaChiNhaCungCap.setText("");
    }//GEN-LAST:event_btnXoaNhaCungCapActionPerformed

    private void btnReloadNhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadNhaCungCapActionPerformed
        // TODO add your handling code here:
        this.layDSNhaCungCap();
    }//GEN-LAST:event_btnReloadNhaCungCapActionPerformed

    private void btnTimNhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimNhaCungCapActionPerformed
        // TODO add your handling code here:
        String supplierID=txtTimMaNhaCungCap.getText();
        String name=txtTimTenNhaCungCap.getText();
        if(supplierID.length()==0 && name.length()==0){
            JOptionPane.showMessageDialog(this, "Hãy nhập thông tin nhà cung cấp cần tìm!");
        }
        else {
            DefaultTableModel dtm=(DefaultTableModel) tblDSNhaCungCap.getModel();
            if(timNhaCungCap(supplierID, name)==1){

            }
            else{
                JOptionPane.showMessageDialog(this, "Không tìm thấy nhà cung cấp cần tìm trong danh sách");
                this.layDSNhaCungCap();

            }
        }
    }//GEN-LAST:event_btnTimNhaCungCapActionPerformed

    private void txtTimMaNhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimMaNhaCungCapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimMaNhaCungCapActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReloadNhaCungCap;
    private javax.swing.JButton btnSuaNhaCungCap;
    private javax.swing.JButton btnThemNhaCungCap;
    private javax.swing.JButton btnTimNhaCungCap;
    private javax.swing.JButton btnXoaNhaCungCap;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JTable tblDSNhaCungCap;
    private javax.swing.JTextField txtDiaChiNhaCungCap;
    private javax.swing.JTextField txtMaNhaCungCap;
    private javax.swing.JTextField txtSDTNhaCungCap;
    private javax.swing.JTextField txtTenNhaCungCap;
    private javax.swing.JTextField txtThanhPhoNhaCungCap;
    private javax.swing.JTextField txtTimMaNhaCungCap;
    private javax.swing.JTextField txtTimTenNhaCungCap;
    // End of variables declaration//GEN-END:variables
}
