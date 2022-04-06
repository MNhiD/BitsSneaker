/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Database.DatabaseManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vntvn
 */

    
 public class KhachHangPanel extends javax.swing.JPanel {
     
     DatabaseManager database = new DatabaseManager("BitSneaker");
     
//      private void initializeUI() {
//        this.setLayout(new BorderLayout());
//        this.setPreferredSize(new Dimension(500, 200));
//                 
//        JTabbedPane pane = new JTabbedPane();
//
//        ImageIcon tabKhachHang = new ImageIcon(
//            this.getClass().getResource("D:/D/img/cus.png"));
//        ImageIcon tab2Icon = new ImageIcon(
//            this.getClass().getResource("D:/D/img/cus.png"));
//        ImageIcon tab3Icon = new ImageIcon(
//            this.getClass().getResource("D:/D/img/clerk.png"));
//
//        JPanel content1 = new JPanel();
//        JPanel content2 = new JPanel();
//        JPanel content3 = new JPanel();
//          Icon tab1Icon = null;
//
//        pane.addTab("Pass", tab1Icon, content1);
//        pane.addTab("Fail", tab2Icon, content2);
//        pane.addTab("Error", tab3Icon, content3);
//
//        this.add(pane, BorderLayout.CENTER);
//    }
   
   public void layDSKhachHang() {
        try {
            DefaultTableModel dtm=(DefaultTableModel)tblDSKH.getModel();
            dtm.setNumRows(0);
            Vector vt;
//            Connection ketNoi= KetNoiCSDL.layKetNoi();
            String sql="select * from CUSTOMER";
//            PreparedStatement ps= ketNoi.prepareStatement(sql);
//            ResultSet rs=ps.executeQuery();
            ResultSet rs = database.queryData(sql);
            while(rs.next()){
                vt=new Vector();
                vt.add(rs.getString("CustomerID"));
                vt.add(rs.getString("Name"));
                vt.add(rs.getString("PhoneNumber"));
//                vt.add(rs.getString("Address"));
                dtm.addRow(vt);
                
                tblDSKH.setModel(dtm);                       } 
//            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   public void layDSDiaChi (String customerID) {
        try {
            cbDiaChiKhachHang.removeAllItems();
//            Connection ketNoi=KetNoiCSDL.layKetNoi();
            String sql="select * from ADDRESS WHERE CustomerID='"+customerID+"'";
//            PreparedStatement ps=ketNoi.prepareStatement(sql);
//            ResultSet rs=ps.executeQuery();
            ResultSet rs = database.queryData(sql);
            while(rs.next()){
                cbDiaChiKhachHang.addItem(rs.getString("Address"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   public int timKhachHang(String customerID,String name){
       int check = 0;
//       Connection ketNoi=KetNoiCSDL.layKetNoi();
       DefaultTableModel dtm=(DefaultTableModel)tblDSKH.getModel();
       dtm.setNumRows(0);
       if(customerID.length()!=0 &&name.length()==0){
        String sql="SELECT * FROM CUSTOMER WHERE CustomerID='"+customerID+"'";
        try {
//            Statement ps=ketNoi.createStatement();
//            ResultSet rs=ps.executeQuery(sql);
            ResultSet rs = database.queryData(sql);
            
                while(rs.next()){
                Vector vt = new Vector();
                vt.add(rs.getString("CustomerID"));
                vt.add(rs.getString("Name"));
                vt.add(rs.getString("PhoneNumber"));
                dtm.addRow(vt);
                
                tblDSKH.setModel(dtm);                       } 
//            ps.close();
            rs.close();
            check=1;
            
            //ps.setString(2, "");
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
       else if(name.length()!=0&&customerID.length()==0){
           String sql="SELECT * FROM CUSTOMER WHERE Name LIKE N'"+ name+"%'";
        try {
//            Statement ps=ketNoi.createStatement();
//            ResultSet rs=ps.executeQuery(sql);
            ResultSet rs = database.queryData(sql);
            
                while(rs.next()){
                Vector vt = new Vector();
                vt.add(rs.getString("CustomerID"));
                vt.add(rs.getString("Name"));
                vt.add(rs.getString("PhoneNumber"));
                dtm.addRow(vt);
                
                tblDSKH.setModel(dtm);                       } 
//            ps.close();
            rs.close();
                check=1;
            
            //ps.setString(2, "");
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
       else {
           String sql="SELECT * FROM CUSTOMER WHERE CustomerID='"+customerID+"' AND Name LIKE N'"+ name+"%'";
        try {
//            Statement ps=ketNoi.createStatement();
//            ResultSet rs=ps.executeQuery(sql);
            ResultSet rs = database.queryData(sql);
            
                while(rs.next()){
                Vector vt = new Vector();
                vt.add(rs.getString("CustomerID"));
                vt.add(rs.getString("Name"));
                vt.add(rs.getString("PhoneNumber"));
                dtm.addRow(vt);
                
                tblDSKH.setModel(dtm);                       } 
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
    public KhachHangPanel() {
        initComponents();
        layDSKhachHang();
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
        txtTimMaKH = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTimTenKH = new javax.swing.JTextField();
        btnTimKiemKH = new javax.swing.JButton();
        btnReloadKhachHang = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDSKH = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        txtTenKH = new javax.swing.JTextField();
        txtSDTKH = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cbDiaChiKhachHang = new javax.swing.JComboBox<>();

        setPreferredSize(new java.awt.Dimension(1104, 800));

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1105, 800));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        jLabel7.setText("Tên khách hàng:");

        txtTimMaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimMaKHActionPerformed(evt);
            }
        });

        jLabel8.setText("Mã KH:");

        btnTimKiemKH.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnTimKiemKH.setIcon(new javax.swing.ImageIcon("src//main//java//res//iconManager//loupe.png"));
        btnTimKiemKH.setText("Tìm kiếm");
        btnTimKiemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemKHActionPerformed(evt);
            }
        });

        btnReloadKhachHang.setIcon(new javax.swing.ImageIcon("src//main//java//res//iconManager//reload.png"));
        btnReloadKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadKhachHangActionPerformed(evt);
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
                    .addComponent(txtTimTenKH, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnTimKiemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReloadKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtTimMaKH))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTimTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReloadKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setText("Danh sách khách hàng");

        tblDSKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã khách hàng", "Tên khách hàng", "Số điện thoại"
            }
        ));
        tblDSKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSKHMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDSKH);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin cá nhân", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        jLabel4.setText("Mã KH:");

        jLabel5.setText("Tên khách hàng:");

        jLabel6.setText("Số điện thoại:");

        jLabel9.setText("Địa chỉ:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                    .addComponent(txtTenKH)
                    .addComponent(txtSDTKH)
                    .addComponent(cbDiaChiKhachHang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(97, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDTKH, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbDiaChiKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(281, 281, 281))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Khách Hàng", jPanel1);

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
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimMaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimMaKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimMaKHActionPerformed

    private void btnTimKiemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemKHActionPerformed
        String customerID=txtTimMaKH.getText();
        String name=txtTimTenKH.getText();
        if(customerID.length()==0 && name.length()==0){
            JOptionPane.showMessageDialog(this, "Hãy nhập thông tin khách hàng cần tìm!");
        }
        else {
         DefaultTableModel dtm=(DefaultTableModel) tblDSKH.getModel();        
        if(timKhachHang(customerID, name)==1){
            
        }
        else{
            JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng cần tìm trong danh sách");
            this.layDSKhachHang();
        
        }
        }
    }//GEN-LAST:event_btnTimKiemKHActionPerformed

    private void tblDSKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSKHMouseClicked
        // TODO add your handling code here:
        DefaultTableModel dtm=(DefaultTableModel) tblDSKH.getModel();
         int luaChon=tblDSKH.getSelectedRow();
        txtMaKH.setText((String) dtm.getValueAt(luaChon, 0));
        txtTenKH.setText((String) dtm.getValueAt(luaChon, 1));
        txtSDTKH.setText((String) dtm.getValueAt(luaChon, 2));
        layDSDiaChi(txtMaKH.getText());
        
    }//GEN-LAST:event_tblDSKHMouseClicked

    private void btnReloadKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadKhachHangActionPerformed
        // TODO add your handling code here:
        this.layDSKhachHang();
    }//GEN-LAST:event_btnReloadKhachHangActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReloadKhachHang;
    private javax.swing.JButton btnTimKiemKH;
    private javax.swing.JComboBox<String> cbDiaChiKhachHang;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblDSKH;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtSDTKH;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTimMaKH;
    private javax.swing.JTextField txtTimTenKH;
    // End of variables declaration//GEN-END:variables
}
