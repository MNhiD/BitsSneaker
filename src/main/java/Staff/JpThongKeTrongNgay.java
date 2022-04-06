/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Staff;

import Database.DatabaseManager;
import static Staff.JpThongKeTrongNgay.dtmThongKe;
import Tools.FormatText;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dell
 */
public class JpThongKeTrongNgay extends javax.swing.JPanel {

    /**
     * Creates new form JpThongKeTrongNgay
     */
    
    private DatabaseManager database = new DatabaseManager("BitSneaker");
    
    static DefaultTableModel dtmThongKe;
    
    public JpThongKeTrongNgay(){
        initComponents();
        
//        dtmThongKe = (DefaultTableModel) tbThongKe.getModel();
        if(cbxLocDon.getSelectedItem().toString().equals("Tất cả")){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            inDanhSachHoaDonBanHang(format.format(date));
            inDanhSachHoaDonNhanHang(format.format(date));
        }
    }
    
    public void inDanhSachHoaDonBanHang(String date){
        
        dtmThongKe = (DefaultTableModel) tbThongKe.getModel();
        Vector vt;
        try {         
            ResultSet rs = database.queryData("SELECT tb.SellID, TimeCreate, StaffID, TongThanhToan FROM (SELECT pr.SellID, SUM(Amount) AS TongSoLuong, SUM(Amount*CAST(Sale/100 AS float)*SellPrice) AS TongThanhToan FROM (SELECT SellID, SELLDETAILS.SneakerID, Amount, Sale, SellPrice FROM SELLDETAILS INNER JOIN PRICE ON SELLDETAILS.SneakerID = PRICE.SneakerID) AS pr GROUP BY pr.SellID) AS tb INNER JOIN (SELECT * FROM SELLORDER WHERE Status = 'paid' AND cast (TimePaid as date) = '"+date+"') AS tb1 ON tb.SellID = tb1.SellID");
            while(rs.next()){
                 vt = new Vector();
                 vt.add(rs.getString(1));
                 vt.add(rs.getString(2));
                 vt.add(rs.getString(3));
                 vt.add(FormatText.covertCurrentcyFormat(rs.getLong(4)));
                 dtmThongKe.addRow(vt);
            }
            tbThongKe.setModel(dtmThongKe);
        } catch (SQLException ex) {
            Logger.getLogger(JpThongKeTrongNgay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void inDanhSachHoaDonBanHang(String date, String staffID){
        
        dtmThongKe = (DefaultTableModel) tbThongKe.getModel();
        Vector vt;
        try {         
            ResultSet rs = database.queryData("SELECT tb.SellID, TimeCreate, StaffID, TongThanhToan FROM (SELECT pr.SellID, SUM(Amount) AS TongSoLuong, SUM(Amount*CAST(Sale/100 AS float)*SellPrice) AS TongThanhToan FROM (SELECT SellID, SELLDETAILS.SneakerID, Amount, Sale, SellPrice FROM SELLDETAILS INNER JOIN PRICE ON SELLDETAILS.SneakerID = PRICE.SneakerID) AS pr GROUP BY pr.SellID) AS tb INNER JOIN (SELECT * FROM SELLORDER WHERE Status = 'paid' AND cast (TimePaid as date) = '"+date+"' AND StaffID = '"+staffID+"') AS tb1 ON tb.SellID = tb1.SellID");
            while(rs.next()){
                 vt = new Vector();
                 vt.add(rs.getString(1));
                 vt.add(rs.getString(2));
                 vt.add(rs.getString(3));
                 vt.add(FormatText.covertCurrentcyFormat(rs.getLong(4)));
                 dtmThongKe.addRow(vt);
            }
            tbThongKe.setModel(dtmThongKe);
        } catch (SQLException ex) {
            Logger.getLogger(JpThongKeTrongNgay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void inDanhSachHoaDonNhanHang(String date){
        
        dtmThongKe = (DefaultTableModel) tbThongKe.getModel();
        //dtmThongKe.setNumRows(0);
        Vector vt;
        try {
           ResultSet rs = database.queryData("SELECT IMPORTORDER.ImportID, IMPORTORDER.OrderTime, IMPORTORDER.StaffID, DETAILS.Total FROM IMPORTORDER INNER JOIN (SELECT IMPORTDETAILS.ImportID, 'Total'=SUM(IMPORTDETAILS.Price*IMPORTDETAILS.Amount) FROM IMPORTDETAILS GROUP BY IMPORTDETAILS.ImportID) AS DETAILS ON IMPORTORDER.ImportID = DETAILS.ImportID WHERE CAST (IMPORTORDER.OrderTime AS DATE) = '"+date+"'");
           
            while(rs.next()){
                vt = new Vector();
                vt.add(rs.getString(1));
                vt.add(rs.getString(2));
                vt.add(rs.getString(3));
                vt.add(FormatText.covertCurrentcyFormat(rs.getLong(4)));
                dtmThongKe.addRow(vt);
            }
            tbThongKe.setModel(dtmThongKe);
           
        } catch (SQLException ex) {
            Logger.getLogger(JpThongKeTrongNgay.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void inDanhSachHoaDonNhanHang(String date, String stafID){
        
        dtmThongKe = (DefaultTableModel) tbThongKe.getModel();
        //dtmThongKe.setNumRows(0);
        Vector vt;
        try {
           ResultSet rs = database.queryData("SELECT IMPORTORDER.ImportID, IMPORTORDER.OrderTime, IMPORTORDER.StaffID, DETAILS.Total FROM IMPORTORDER INNER JOIN (SELECT IMPORTDETAILS.ImportID, 'Total'=SUM(IMPORTDETAILS.Price*IMPORTDETAILS.Amount) FROM IMPORTDETAILS GROUP BY IMPORTDETAILS.ImportID) AS DETAILS ON IMPORTORDER.ImportID = DETAILS.ImportID WHERE CAST (IMPORTORDER.OrderTime AS DATE) = '"+date+"' AND StaffID = '"+stafID+"'");
           
            while(rs.next()){
                vt = new Vector();
                vt.add(rs.getString(1));
                vt.add(rs.getString(2));
                vt.add(rs.getString(3));
                vt.add(FormatText.covertCurrentcyFormat(rs.getLong(4)));
                dtmThongKe.addRow(vt);
            }
            tbThongKe.setModel(dtmThongKe);
           
        } catch (SQLException ex) {
            Logger.getLogger(JpThongKeTrongNgay.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
       public int kiemTraMaNhanVien(String staffID){
           
           int kq = -1;
        try {
            ResultSet rs = database.queryData("SELECT StaffID FROM STAFF WHERE StaffID = '"+staffID+"'");
            if(rs.next()){
                kq = 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(JpThongKeTrongNgay.class.getName()).log(Level.SEVERE, null, ex);
        }
           
           return kq;
       }

       
    
     /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblThongKe = new javax.swing.JLabel();
        lblTatCaHD = new javax.swing.JLabel();
        cbxLocDon = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbThongKe = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtIDStaff = new javax.swing.JTextField();
        btnLocDon = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblThongKe.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblThongKe.setForeground(new java.awt.Color(0, 0, 102));
        lblThongKe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThongKe.setText("Thống kê trong ngày ");

        lblTatCaHD.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblTatCaHD.setText("Các hóa đơn đã thanh toán");

        cbxLocDon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Hóa Đơn Bán Hàng", "Hóa Đơn Nhập Hàng", " ", " " }));
        cbxLocDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbxLocDonMouseClicked(evt);
            }
        });
        cbxLocDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxLocDonActionPerformed(evt);
            }
        });

        tbThongKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Đơn", "Thời Gian Tạo ", "Nhân Viên Tạo/Duyệt", "Tổng ThanhToán"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Long.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbThongKe.setRowHeight(22);
        jScrollPane1.setViewportView(tbThongKe);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel1.setText("Lọc theo mã nhân viên");

        btnLocDon.setBackground(new java.awt.Color(255, 255, 255));
        btnLocDon.setIcon(new javax.swing.ImageIcon("src//main//java//res//iconStaff//filter.png"));
        btnLocDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLocDonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblThongKe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTatCaHD)
                            .addComponent(cbxLocDon, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtIDStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLocDon, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 983, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTatCaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxLocDon)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtIDStaff)
                                .addComponent(btnLocDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbxLocDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxLocDonMouseClicked
         //TODO add your handling code here:
        if(cbxLocDon.getSelectedItem().toString().equals("Tất cả")){
            dtmThongKe.setRowCount(0);
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            String timeExpress = format.format(date);
            inDanhSachHoaDonBanHang(format.format(date));
            inDanhSachHoaDonNhanHang(timeExpress);
        }
    }//GEN-LAST:event_cbxLocDonMouseClicked

    private void btnLocDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLocDonMouseClicked
        // TODO add your handling code here:
        
        if(!txtIDStaff.getText().equals("")){
            if(kiemTraMaNhanVien(txtIDStaff.getText())>0){
                inHoaDonThaoTacBoiNhanVien(txtIDStaff.getText());
            }
            else{
                JOptionPane.showMessageDialog(null, "Mã nhân viên không tồn tại");
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Nhập Mã Nhân viên để tìm kiếm");
        }
    }//GEN-LAST:event_btnLocDonMouseClicked

    private void cbxLocDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxLocDonActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        if(txtIDStaff.getText().equals("")){
            inHoaDonThaoTacBoiNhanVien("");
        }
        else{
            if(kiemTraMaNhanVien(txtIDStaff.getText())>0){
                inHoaDonThaoTacBoiNhanVien(txtIDStaff.getText());
            }
            else{
                JOptionPane.showMessageDialog(null, "Mã nhân viên không tồn tại");
            }
        }
//        if(cbxLocDon.getSelectedItem().toString().equals("Tất cả")){
//            dtmThongKe.setRowCount(0);
//            String timeExpress = format.format(date);
//            inDanhSachHoaDonBanHang(format.format(date));
//            inDanhSachHoaDonNhanHang(timeExpress);
//            
//        }
//        if(cbxLocDon.getSelectedItem().toString().equals("Hóa Đơn Bán Hàng")){
//            dtmThongKe.setNumRows(0);
//            inDanhSachHoaDonBanHang(format.format(date));
//            
//        }
//        else{
//            dtmThongKe.setNumRows(0);
//            String timeExpress = format.format(date);
//            inDanhSachHoaDonNhanHang(timeExpress);
//            
//        }
    }//GEN-LAST:event_cbxLocDonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLocDon;
    private javax.swing.JComboBox<String> cbxLocDon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTatCaHD;
    private javax.swing.JLabel lblThongKe;
    private javax.swing.JTable tbThongKe;
    private javax.swing.JTextField txtIDStaff;
    // End of variables declaration//GEN-END:variables

    private void inHoaDonThaoTacBoiNhanVien(String staffID) {
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        
        if(staffID.equals("")){
            System.out.println("Khogn co gi het ne");
            if(cbxLocDon.getSelectedItem().toString().equals("Tất cả")){
                dtmThongKe.setRowCount(0);
                String timeExpress = format.format(date);
                inDanhSachHoaDonBanHang(format.format(date));
                inDanhSachHoaDonNhanHang(format.format(date));
            }
            else if(cbxLocDon.getSelectedItem().toString().equals("Hóa Đơn Bán Hàng")){
                dtmThongKe.setNumRows(0);
                inDanhSachHoaDonBanHang(format.format(date));
            }
            else{
                dtmThongKe.setNumRows(0);
                String timeExpress = format.format(date);
                inDanhSachHoaDonNhanHang(timeExpress);
            }
        }
        else{
            if(cbxLocDon.getSelectedItem().toString().equals("Tất cả")){
                dtmThongKe.setRowCount(0);
                String timeExpress = format.format(date);
                inDanhSachHoaDonBanHang(format.format(date), staffID);
                inDanhSachHoaDonNhanHang(format.format(date), staffID);
            }
            else if(cbxLocDon.getSelectedItem().toString().equals("Hóa Đơn Bán Hàng")){
                dtmThongKe.setNumRows(0);
                inDanhSachHoaDonBanHang(format.format(date), staffID);
            }
            else{
                dtmThongKe.setNumRows(0);
                String timeExpress = format.format(date);
                inDanhSachHoaDonNhanHang(timeExpress, staffID);
            }
        }
        
        
            
        
    }
}
