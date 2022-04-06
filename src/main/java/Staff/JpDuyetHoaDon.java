/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Staff;

import Database.DatabaseManager;
import Entity.Staff;
import Tools.FormatText;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC_DELL
 */
public class JpDuyetHoaDon extends javax.swing.JPanel {

    /**
     * Creates new form JpDuyetHoaDon
     */
    
    private DatabaseManager database = new DatabaseManager("BitSneaker");
    
    private ArrayList<Staff> listStaff;
    
    public JpDuyetHoaDon(ArrayList<Staff> listStaff) {
        
        this.listStaff = listStaff;
        
        initComponents();
        layDSDonDatHang();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpDuyetHoaDon = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDSDonDatHang = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDSSPTrongDon = new javax.swing.JTable();
        btnDuyetDonHang = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        jpDuyetHoaDon.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 20)); // NOI18N
        jLabel1.setText("DANH SÁCH CÁC ĐƠN ĐẶT HÀNG ONLINE");

        tblDSDonDatHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã đơn hàng", "Thời gian đặt hàng", "Địa chỉ"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDSDonDatHang.setSelectionBackground(new java.awt.Color(204, 204, 255));
        tblDSDonDatHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSDonDatHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDSDonDatHang);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 20)); // NOI18N
        jLabel2.setText("DANH SÁCH CÁC SẢN PHẨM TRONG ĐƠN");

        tblDSSPTrongDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên", "Hình ảnh", "Brand", "Màu", "Size", "Đơn giá", "Số lượng", "Giá thành"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Long.class, java.lang.Integer.class, java.lang.Long.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDSSPTrongDon.setSelectionBackground(new java.awt.Color(204, 204, 255));
        tblDSSPTrongDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSSPTrongDonMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDSSPTrongDon);

        btnDuyetDonHang.setBackground(new java.awt.Color(204, 204, 255));
        btnDuyetDonHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDuyetDonHang.setText("DUYỆT ĐƠN HÀNG");
        btnDuyetDonHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDuyetDonHangMouseClicked(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        btnCancel.setText("HỦY ĐƠN");
        btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jpDuyetHoaDonLayout = new javax.swing.GroupLayout(jpDuyetHoaDon);
        jpDuyetHoaDon.setLayout(jpDuyetHoaDonLayout);
        jpDuyetHoaDonLayout.setHorizontalGroup(
            jpDuyetHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDuyetHoaDonLayout.createSequentialGroup()
                .addGroup(jpDuyetHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpDuyetHoaDonLayout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addGroup(jpDuyetHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpDuyetHoaDonLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(99, 99, 99))))
                    .addGroup(jpDuyetHoaDonLayout.createSequentialGroup()
                        .addGap(504, 504, 504)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDuyetDonHang))
                    .addGroup(jpDuyetHoaDonLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 895, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpDuyetHoaDonLayout.createSequentialGroup()
                        .addGap(254, 254, 254)
                        .addComponent(jLabel2)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jpDuyetHoaDonLayout.setVerticalGroup(
            jpDuyetHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDuyetHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpDuyetHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDuyetDonHang, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpDuyetHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpDuyetHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void layDSDonDatHang(){
        DefaultTableModel dtm = (DefaultTableModel) tblDSDonDatHang.getModel();
        dtm.setNumRows(0);
        Vector vt;
        
        String sql = "SELECT SELLORDER.SellID, SELLORDER.TimeCreate, SELLORDER.Address FROM SELLORDER "
                + "WHERE SELLORDER.Status = '"+"pending"+"'";
        try {

            ResultSet rs = database.queryData(sql);
            
            while(rs.next()){
                vt = new Vector();
                vt.add(rs.getString("SellID"));
                vt.add(rs.getString("TimeCreate"));              
                vt.add(rs.getString(3)); 
                dtm.addRow(vt);
                tblDSDonDatHang.setModel(dtm);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JpDuyetHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public void inDSSPTrongDon(String sellID){       
        DefaultTableModel dtm = (DefaultTableModel) tblDSSPTrongDon.getModel();
        dtm.setNumRows(0);
        Vector vt;
        
        String sql = "SELECT tbl1.SneakerID, Name, Image, Brand, Color, Size, SellPrice, TongSoluong, ThanhToan FROM (SELECT tb1.SneakerID, tb1.Amount, Sale, Image, Name, Brand, Color, Size, SellPrice FROM PRICE INNER JOIN (SELECT SellID, SELLDETAILS.SneakerID, SELLDETAILS.Amount, Sale, Image, Name, Brand, Color, Size FROM SELLDETAILS INNER JOIN SNEAKER ON SELLDETAILS.SneakerID = SNEAKER.SneakerID) AS tb1  ON PRICE.SneakerID = tb1.SneakerID WHERE tb1.SellID = '"+sellID+"') AS tbl1 INNER JOIN (SELECT SneakerID, SUM(Amount) AS TongSoLuong, SUM(Amount*CAST(Sale/100 AS float)*Sellprice) AS ThanhToan FROM (SELECT tb1.SneakerID, tb1.Amount, Sale, Image, Name, Brand, Color, Size, SellPrice FROM PRICE INNER JOIN (SELECT SellID, SELLDETAILS.SneakerID, SELLDETAILS.Amount, Sale, Image, Name, Brand, Color, Size FROM SELLDETAILS INNER JOIN SNEAKER ON SELLDETAILS.SneakerID = SNEAKER.SneakerID) AS tb1 ON PRICE.SneakerID = tb1.SneakerID WHERE tb1.SellID = '"+sellID+"') AS tb2 GROUP BY tb2.SneakerID) AS tbl2 ON tbl1.SneakerID = tbl2.SneakerID";
        try {
            
            ResultSet rs = database.queryData(sql);
            
            while(rs.next()){
                vt = new Vector();
                vt.add(rs.getString("SneakerID"));
                vt.add(rs.getString("Name"));
                vt.add(rs.getString("Image"));               
                vt.add(rs.getString("Brand"));
                vt.add(rs.getString("Color"));
                vt.add(rs.getString("Size"));
                vt.add(FormatText.covertCurrentcyFormat(rs.getLong("SellPrice")));
                vt.add(rs.getString("TongSoLuong"));
                vt.add(FormatText.covertCurrentcyFormat(rs.getLong("ThanhToan")));
                dtm.addRow(vt);           
                tblDSSPTrongDon.setModel(dtm);
            }      
        } catch (SQLException ex) {
            Logger.getLogger(JpTaoHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    public void capNhatDonHang(String staffID, String sellID, String status){
        
        String sql = "UPDATE SELLORDER SET StaffID = '"+staffID+"', Status = '"+status+"', TimePaid = getdate() WHERE SellID = '"+sellID+"'";
        
        database.queryUpdate(sql);
        
    }
    
    public void inDSDonHangChuaDuyet(){
        DefaultTableModel dtm = (DefaultTableModel) tblDSDonDatHang.getModel();
        dtm.setNumRows(0);
        Vector vt;
        
        String sql = "SELECT SELLORDER.SellID, SELLORDER.TimeCreate, SELLORDER.Address FROM SELLORDER WHERE SELLORDER.Status = '"+"pending"+"'";
        try {
            
            ResultSet rs = database.queryData(sql);
            
            while(rs.next()){
                vt = new Vector();
                vt.add(rs.getString("SellID"));
                vt.add(rs.getString("TimeCreate"));
                vt.add(rs.getString(3));
                dtm.addRow(vt);
                tblDSDonDatHang.setModel(dtm);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JpDuyetHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    private void tblDSDonDatHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSDonDatHangMouseClicked
        // TODO add your handling code here:
        DefaultTableModel dtmDon = (DefaultTableModel) tblDSDonDatHang.getModel();
        int selectRow = tblDSDonDatHang.getSelectedRow();
        if(selectRow >= 0){
            String sellID = dtmDon.getValueAt(selectRow,0).toString();
            this.inDSSPTrongDon(sellID);
        }
    }//GEN-LAST:event_tblDSDonDatHangMouseClicked

    private void btnDuyetDonHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDuyetDonHangMouseClicked
        // TODO add your handling code here:
        String staffID = yeuCauDangNhap();
        if(!staffID.equals("")){
            DefaultTableModel dtmDon = (DefaultTableModel) tblDSDonDatHang.getModel();
            DefaultTableModel dtmSP = (DefaultTableModel) tblDSSPTrongDon.getModel();
            int selectRow = tblDSDonDatHang.getSelectedRow();
            if(selectRow >= 0){
                String sellID = dtmDon.getValueAt(selectRow,0).toString();
                this.capNhatDonHang(staffID, sellID, "paid");
                this.inDSDonHangChuaDuyet();
                this.capNhatSPSauDuyet();
                dtmSP.setNumRows(0);
                tblDSSPTrongDon.setModel(dtmSP);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Thao tác thất bại");
        }
    }//GEN-LAST:event_btnDuyetDonHangMouseClicked

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
            int index = username.getSelectedIndex();
            String accountID = listStaff.get(index).getAccountID();
            if(kiemTraAccount(accountID , password.getText().toString(), "Staff") > 0){
                return listStaff.get(index).getStaffID();
            }
            else{
                JOptionPane.showMessageDialog(null, "Sai mật khẩu");
            }
        }
        else{
            return "";
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
    
    private void capNhatSPSauDuyet(){
        for(int i=0; i<tblDSSPTrongDon.getRowCount(); i++){
            database.queryUpdate("UPDATE SNEAKER SET Amount = Amount - '"+this.tblDSSPTrongDon.getValueAt(i, 7)+"' WHERE SneakerID = '"+this.tblDSSPTrongDon.getValueAt(i, 0)+"'");
        }
    }
    
    private void tblDSSPTrongDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSSPTrongDonMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tblDSSPTrongDonMouseClicked

    private void btnCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseClicked
        String staffID = yeuCauDangNhap();
        if(!staffID.equals("")){
            DefaultTableModel dtmDon = (DefaultTableModel) tblDSDonDatHang.getModel();
            DefaultTableModel dtmSP = (DefaultTableModel) tblDSSPTrongDon.getModel();
            int selectRow = tblDSDonDatHang.getSelectedRow();
            if(selectRow >= 0){
                String sellID = dtmDon.getValueAt(selectRow,0).toString();
                this.capNhatDonHang(staffID, sellID, "cancel");
                this.inDSDonHangChuaDuyet();
                dtmSP.setNumRows(0);
                tblDSSPTrongDon.setModel(dtmSP);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Thao tác thất bại");
        }
            
    }//GEN-LAST:event_btnCancelMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDuyetDonHang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jpDuyetHoaDon;
    private javax.swing.JTable tblDSDonDatHang;
    private javax.swing.JTable tblDSSPTrongDon;
    // End of variables declaration//GEN-END:variables
}