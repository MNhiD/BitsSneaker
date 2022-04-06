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
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dell
 */
public class JpNhanDonDatHang extends javax.swing.JPanel {
    static String nhanVienNhanDon;
    static DefaultTableModel dtm;
    private DatabaseManager database = new DatabaseManager("BitSneaker");
    
    private ArrayList<Staff> listStaff;
    
    public JpNhanDonDatHang(ArrayList<Staff> listStaff) {
        
        this.listStaff = listStaff;
        
        initComponents();
        
        tbDonDH.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD,14));
        tbDonDH.getTableHeader().setOpaque(false);
        //tbDonDH.getTableHeader().setBackground(new Color(255, 0, 0));
        
        tbChiTietDDH.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD,14)); 
        tbDonDH.getTableHeader().setOpaque(false);
        
        inDanhSachDonNhapHang();
    }
    public int kiemTraAccount(String accountID, String password, String type){
        
        int kq = -1;
        
        try {
            ResultSet rs = database.queryData("SELECT AccountID, Password, Type FROM ACCOUNT WHERE AccountID = ('"+accountID+"') AND Password = ('"+password+"') AND Type =('"+type+"') ");
            if(rs.next()){
                kq=1;
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi hàm kiểm tra tài khoản");
        }
        return kq;
    }
    
    public void inDanhSachDonNhapHang(){
        
        DefaultTableModel dtm = (DefaultTableModel) tbDonDH.getModel();
        dtm.setNumRows(0);
        Vector vt;
        try {
            ResultSet rs = database.queryData("SELECT Ord.ImportID, SupplierID, TongSoLuong,StaffName, OrderTime ,TongThanhToan FROM (SELECT ImportID, SupplierID, StaffName, OrderTime, Done FROM (SELECT IMPORTORDER.ImportID, IMPORTORDER.SupplierID, IMPORTORDER.StaffID, IMPORTORDER.OrderTime, IMPORTORDER.Done FROM IMPORTORDER) AS importOrd INNER JOIN (SELECT CONCAT_WS(' ',FirstName, LastName) AS StaffName, StaffID FROM STAFF) AS stf ON importOrd.StaffID = stf.StaffID) AS Ord INNER JOIN (SELECT IMPORTDETAILS.ImportID, SUM(Amount) AS TongSoLuong, SUM(Price*Amount) AS TongThanhToan FROM IMPORTDETAILS GROUP BY ImportID) AS details ON Ord.ImportID = details.ImportID WHERE details.TongSoLuong > 0 AND Ord.Done=0");
            //ResultSet rs = conn.createStatement().executeQuery("SELECT importOrd.ImportID, SupplierID, StaffName, OrderTime  FROM (SELECT ImportID, SupplierID, StaffID, OrderTime FROM IMPORTORDER )AS importOrd INNER JOIN (SELECT CONCAT_WS(' ',FirstName, LastName) AS StaffName, StaffID FROM STAFF ) AS stf ON importOrd.StaffID = stf.StaffID ");
            //ResultSet rs = conn.createStatement().executeQuery("SELECT ImportID, SUM(Amount) AS TongSoLuong, SUM(Price*Amount) AS TongThanhToan FROM IMPORTDETAILS GROUP BY ImportID ");
            //ResultSet rs = conn.createStatement().executeQuery("SELECT Ord.ImportID, SupplierID, TongSoLuong,StaffID, OrderTime ,TongThanhToan FROM (SELECT * FROM IMPORTORDER) AS Ord INNER JOIN (SELECT IMPORTDETAILS.ImportID, SUM(Amount) AS TongSoLuong, SUM(Price*Amount) AS TongThanhToan FROM IMPORTDETAILS GROUP BY ImportID) AS details ON Ord.ImportID = details.ImportID");
            while(rs.next()){
                vt = new Vector();
                vt.add(rs.getString(1));
                vt.add(rs.getString(2));
                vt.add(rs.getInt(3));
                vt.add(rs.getString(4));
                vt.add(rs.getString(5));
                vt.add(FormatText.covertCurrentcyFormat(rs.getLong(6)));
                dtm.addRow(vt);
            }
            tbDonDH.setModel(dtm);
        } catch (SQLException ex) {
            Logger.getLogger(JpNhanDonDatHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int kiemTonTaiMaDonHang(String importID){
        
        int kq =-1;
        try {
            ResultSet rs = database.queryData("SELECT ImportID FROM IMPORTORDER WHERE ImportID = ('"+importID+"')");
            if(rs.next()){
                kq=1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(JpNhanDonDatHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }
    public void inThongTinDonNhapHang(String importID){
        
        DefaultTableModel dtm = (DefaultTableModel) tbDonDH.getModel();
        dtm.setNumRows(0);
        Vector vt;
        try {
            ResultSet rs = database.queryData("SELECT Ord.ImportID, SupplierID, TongSoLuong, StaffName, OrderTime ,TongThanhToan FROM (SELECT ImportID, SupplierID, StaffName, OrderTime  FROM (SELECT IMPORTORDER.ImportID, IMPORTORDER.SupplierID, IMPORTORDER.StaffID, IMPORTORDER.OrderTime FROM IMPORTORDER) AS importOrd INNER JOIN (SELECT CONCAT_WS(' ',FirstName, LastName) AS StaffName, StaffID FROM STAFF) AS stf ON importOrd.StaffID = stf.StaffID ) AS Ord INNER JOIN (SELECT IMPORTDETAILS.ImportID, SUM(Amount) AS TongSoLuong, SUM(Price*Amount) AS TongThanhToan FROM IMPORTDETAILS GROUP BY ImportID) AS details ON Ord.ImportID = details.ImportID  WHERE Ord.ImportID = ('"+importID+"')");
            //ResultSet rs = conn.createStatement().executeQuery("SELECT importOrd.ImportID, SupplierID, StaffName, OrderTime  FROM (SELECT ImportID, SupplierID, StaffID, OrderTime FROM IMPORTORDER )AS importOrd INNER JOIN (SELECT CONCAT_WS(' ',FirstName, LastName) AS StaffName, StaffID FROM STAFF ) AS stf ON importOrd.StaffID = stf.StaffID ");
            //ResultSet rs = conn.createStatement().executeQuery("SELECT ImportID, SUM(Amount) AS TongSoLuong, SUM(Price*Amount) AS TongThanhToan FROM IMPORTDETAILS GROUP BY ImportID ");
            //ResultSet rs = conn.createStatement().executeQuery("SELECT Ord.ImportID, SupplierID, TongSoLuong,StaffID, OrderTime ,TongThanhToan FROM (SELECT * FROM IMPORTORDER) AS Ord INNER JOIN (SELECT IMPORTDETAILS.ImportID, SUM(Amount) AS TongSoLuong, SUM(Price*Amount) AS TongThanhToan FROM IMPORTDETAILS GROUP BY ImportID) AS details ON Ord.ImportID = details.ImportID");
            while(rs.next()){
                vt = new Vector();
                vt.add(rs.getString(1));
                vt.add(rs.getString(2));
                vt.add(rs.getInt(3));
                vt.add(rs.getString(4));
                vt.add(rs.getString(5));
                vt.add(rs.getLong(6));
                dtm.addRow(vt);
            }
            tbDonDH.setModel(dtm);
        } catch (SQLException ex) {
            Logger.getLogger(JpNhanDonDatHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void inChiTietDonHang(String importID, String supplierID, String staffName){
        
        DefaultTableModel dtmImportDetails = (DefaultTableModel) tbChiTietDDH.getModel();
        dtmImportDetails.setNumRows(0);
        Vector vt;
        try {
            ResultSet rs = database.queryData("SELECT details.SneakerID, details.Name, details.Amount, details.Price, express.ExpressAmount FROM (SELECT sn.SneakerID, Name, Amount, Price, ImportID FROM (SELECT ImportID, SneakerID, Amount, Price FROM IMPORTDETAILS WHERE ImportID = ('"+importID+"')) AS tb INNER JOIN (SELECT SneakerID, Name FROM SNEAKER) AS sn ON tb.SneakerID = sn.SneakerID) AS details LEFT JOIN (SELECT IMPORTEXPRESS.ImportID, IMPORTEXPRESS.SneakerID, 'ExpressAmount' = SUM(IMPORTEXPRESS.Amount) FROM IMPORTEXPRESS WHERE IMPORTEXPRESS.ImportID = '"+importID+"' GROUP BY IMPORTEXPRESS.SneakerID, IMPORTEXPRESS.ImportID) AS express ON details.ImportID = express.ImportID AND details.SneakerID = express.SneakerID");
            while(rs.next()){
                vt = new Vector();
                vt.add(rs.getString(1));
//                System.out.println(rs.getString(1));
                vt.add(rs.getString(2));
                vt.add(supplierID);
                vt.add(rs.getInt(3));
                vt.add(rs.getInt(5));
                vt.add(FormatText.covertCurrentcyFormat(rs.getLong(4)));
                vt.add(staffName);
                vt.add(0);
                dtmImportDetails.addRow(vt);
            }
            tbChiTietDDH.setModel(dtmImportDetails);
        } catch (SQLException ex) {
            Logger.getLogger(JpNhanDonDatHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void themDonHangDaNhan(String importID,String sneakerID,String timeExpress, int amount, String staffID){

        database.queryUpdate("INSERT INTO IMPORTEXPRESS (ImportID, SneakerID, Amount, StaffID) VALUES ('"+importID+"','"+sneakerID+"','"+amount+"','"+staffID+"')");

     }
     
//     public int laySoLuongMotSanPham(String importID, String sneakerID){
//         
//         int amount=0;
//        try {
//            ResultSet rs = database.queryData("SELECT Amount FROM IMPORTDETAILS WHERE ImportID=('"+importID+"') AND SneakerID = ('"+sneakerID+"')");
//            while(rs.next()){
//                amount = rs.getInt(1);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(JpNhanDonDatHang.class.getName()).log(Level.SEVERE, null, ex);
//        }
//         return amount;
//     }
  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpNhanDonDatHang = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDonDH = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtMaDon = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbChiTietDDH = new javax.swing.JTable();
        btnReceived = new javax.swing.JButton();
        btnDetails = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();

        jpNhanDonDatHang.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(0, 0, 153));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon("src//main//java//res//iconStaff//compliance.png"));
        jLabel1.setText("Đơn nhập hàng");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 2, 13)); // NOI18N
        jLabel2.setText("Đơn nhập hàng chờ nhận ");

        tbDonDH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Đơn", "Nhà Cung Cấp", "Tổng Số Lượng", "Nhân Viên Đặt", "Ngày Đặt Đơn", "Tổng Thanh Toán"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Long.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbDonDH.setGridColor(new java.awt.Color(204, 204, 204));
        tbDonDH.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tbDonDH.setRowHeight(25);
        tbDonDH.setSelectionBackground(new java.awt.Color(204, 204, 255));
        tbDonDH.setSelectionForeground(new java.awt.Color(51, 51, 51));
        jScrollPane1.setViewportView(tbDonDH);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 2, 13)); // NOI18N
        jLabel3.setText("Thông tin chi tiết đơn nhập hàng");

        txtMaDon.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtMaDon.setForeground(new java.awt.Color(0, 51, 51));
        txtMaDon.setEnabled(false);
        txtMaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaDonActionPerformed(evt);
            }
        });

        tbChiTietDDH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Sản Phẩm", "Tên Sản Phẩm", "Nhà Cung Cấp", "Số Lượng Đã Đặt", "Số Lượng Đã Nhận", "Giá Nhập", "Nhân Viên Đặt", "Nhận"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbChiTietDDH.setGridColor(new java.awt.Color(204, 204, 204));
        tbChiTietDDH.setRowHeight(25);
        tbChiTietDDH.setSelectionBackground(new java.awt.Color(204, 204, 255));
        tbChiTietDDH.setSelectionForeground(new java.awt.Color(51, 51, 51));
        jScrollPane2.setViewportView(tbChiTietDDH);
        if (tbChiTietDDH.getColumnModel().getColumnCount() > 0) {
            tbChiTietDDH.getColumnModel().getColumn(0).setResizable(false);
            tbChiTietDDH.getColumnModel().getColumn(1).setResizable(false);
            tbChiTietDDH.getColumnModel().getColumn(2).setResizable(false);
            tbChiTietDDH.getColumnModel().getColumn(3).setResizable(false);
            tbChiTietDDH.getColumnModel().getColumn(4).setResizable(false);
            tbChiTietDDH.getColumnModel().getColumn(5).setResizable(false);
            tbChiTietDDH.getColumnModel().getColumn(6).setResizable(false);
        }

        btnReceived.setBackground(new java.awt.Color(204, 204, 255));
        btnReceived.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnReceived.setIcon(new javax.swing.ImageIcon("src//main//java//res//iconStaff//receive.png"));
        btnReceived.setText("ĐÃ NHẬN ");
        btnReceived.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReceivedMouseClicked(evt);
            }
        });
        btnReceived.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReceivedActionPerformed(evt);
            }
        });

        btnDetails.setBackground(new java.awt.Color(204, 204, 255));
        btnDetails.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnDetails.setIcon(new javax.swing.ImageIcon("src//main//java//res//iconStaff//details.png"));
        btnDetails.setText("CHI TIẾT");
        btnDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDetailsMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Mã Đơn :");

        txtSearch.setText("Tìm Đơn Nhập Hàng");
        txtSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSearchMouseClicked(evt);
            }
        });

        btnSearch.setBackground(new java.awt.Color(255, 255, 255));
        btnSearch.setIcon(new javax.swing.ImageIcon("src//main//java//res//iconStaff//search2.png"));
        btnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jpNhanDonDatHangLayout = new javax.swing.GroupLayout(jpNhanDonDatHang);
        jpNhanDonDatHang.setLayout(jpNhanDonDatHangLayout);
        jpNhanDonDatHangLayout.setHorizontalGroup(
            jpNhanDonDatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpNhanDonDatHangLayout.createSequentialGroup()
                .addGroup(jpNhanDonDatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpNhanDonDatHangLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnReceived, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpNhanDonDatHangLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpNhanDonDatHangLayout.createSequentialGroup()
                        .addGroup(jpNhanDonDatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpNhanDonDatHangLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)
                                .addGap(37, 37, 37)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 42, Short.MAX_VALUE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(311, 311, 311)))
                .addContainerGap())
        );
        jpNhanDonDatHangLayout.setVerticalGroup(
            jpNhanDonDatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpNhanDonDatHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addGroup(jpNhanDonDatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jpNhanDonDatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpNhanDonDatHangLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jpNhanDonDatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(jpNhanDonDatHangLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnReceived, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpNhanDonDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpNhanDonDatHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnReceivedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReceivedActionPerformed
        // TODO add your handling code here:
        //InputDialogInFrame frame = new InputDialogInFrame();
       
    }//GEN-LAST:event_btnReceivedActionPerformed

    private void btnReceivedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReceivedMouseClicked
        // TODO add your handling code here:
        DefaultTableModel dtmImportDetails = (DefaultTableModel) tbChiTietDDH.getModel();
        if(!txtMaDon.getText().equals("")){
            
            String staffID =  yeuCauDangNhap();
            
            if(!staffID.equals("")){
                String importID = txtMaDon.getText();

                for(int i = 0; i< tbChiTietDDH.getRowCount() ;i++){
                    String sneakerID = dtmImportDetails.getValueAt(i, 0).toString();
                    int amountExpress = Integer.parseInt(dtmImportDetails.getValueAt(i,7).toString());

                    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date date = new Date();
                    String timeExpress = format.format(date);
                    if(amountExpress>=0 && amountExpress<=Integer.parseInt(tbChiTietDDH.getValueAt(i, 3)+"")-Integer.parseInt(tbChiTietDDH.getValueAt(i, 4)+"")){
    //                    int residual = amountTotal-amountExpress;
                        if(amountExpress>0){
                            themDonHangDaNhan(importID, sneakerID, timeExpress, amountExpress, staffID);
                            updateDonNhanHang(sneakerID, amountExpress);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Có một lỗi hãy kiểm tra lại khi hoàn thành");
                    }
                }

                updateImportOrder(importID);
                inDanhSachDonNhapHang();
                txtSearch.setText("Tìm Đơn Nhập Hàng");
                txtMaDon.setText("");
                DefaultTableModel cls1 = (DefaultTableModel) tbChiTietDDH.getModel();
                cls1.setRowCount(0);
                tbChiTietDDH.setModel(cls1);
            }
            else{
                JOptionPane.showMessageDialog(null, "Thao tác thất bại");
            }
            
        }
        else{
            JOptionPane.showMessageDialog(null, "Chưa chọn đơn hàng cần duyệt");
        }
    }//GEN-LAST:event_btnReceivedMouseClicked

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
    
    private void updateDonNhanHang(String sneakerID, int amount){
        
        database.queryUpdate("UPDATE SNEAKER SET Amount = Amount + '"+amount+"' WHERE SneakerID = '"+sneakerID+"'");
        
    }
    
    private void updateImportOrder(String importID){
        
        String sql = "SELECT DONE.ExpressAmountDone, TOTAL.TotalAmount FROM (SELECT 'ExpressAmountDone' = COUNT(*) FROM IMPORTDETAILS INNER JOIN (SELECT IMPORTEXPRESS.ImportID, IMPORTEXPRESS.SneakerID, 'ExpressAmount' = SUM(IMPORTEXPRESS.Amount) FROM IMPORTEXPRESS WHERE IMPORTEXPRESS.ImportID = '"+importID+"' GROUP BY IMPORTEXPRESS.ImportID, IMPORTEXPRESS.SneakerID) AS express ON IMPORTDETAILS.ImportID = express.ImportID AND IMPORTDETAILS.SneakerID = express.SneakerID AND IMPORTDETAILS.Amount = express.ExpressAmount WHERE IMPORTDETAILS.ImportID = '"+importID+"') AS DONE, (SELECT 'TotalAmount' = COUNT(*) FROM IMPORTDETAILS WHERE IMPORTDETAILS.ImportID = '"+importID+"') AS TOTAL";
        
        ResultSet rs = database.queryData(sql);
        
        try {
            if(rs.next()){
                if(rs.getInt(1)==rs.getInt(2)){
                    database.queryUpdate("UPDATE IMPORTORDER SET Done = '"+1+"' WHERE IMPORTORDER.ImportID = '"+importID+"'");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(JpNhanDonDatHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void btnDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDetailsMouseClicked
        // TODO add your handling code here:
        DefaultTableModel dtmImportOrder = (DefaultTableModel) tbDonDH.getModel();
        DefaultTableModel dtmImportDetails = (DefaultTableModel) tbChiTietDDH.getModel();
        
        int selectNumRow = tbDonDH.getSelectedRow();
        if(selectNumRow>=0){
            txtMaDon.setText(dtmImportOrder.getValueAt(selectNumRow, 0).toString());
            String supplierID = dtmImportOrder.getValueAt(selectNumRow, 1).toString();
            String staffName = dtmImportOrder.getValueAt(selectNumRow, 3).toString();
            inChiTietDonHang(txtMaDon.getText(),supplierID,staffName);
        }
    }//GEN-LAST:event_btnDetailsMouseClicked

    private void btnSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseClicked
        // TODO add your handling code here:
        if(txtSearch.getText().equals("Tìm Đơn Nhập Hàng")||txtSearch.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Bạn cần nhập mã đơn cần tìm");
        }
        else{
            if(kiemTonTaiMaDonHang(txtSearch.getText())>0){
                inThongTinDonNhapHang(txtSearch.getText());
            }
        }
    }//GEN-LAST:event_btnSearchMouseClicked

    private void txtSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSearchMouseClicked
        // TODO add your handling code here:
        if(txtSearch.getText().equals("Tìm Đơn Nhập Hàng")){
            txtSearch.setText("");
        }
    }//GEN-LAST:event_txtSearchMouseClicked

    private void txtMaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaDonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDetails;
    private javax.swing.JButton btnReceived;
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jpNhanDonDatHang;
    private javax.swing.JTable tbChiTietDDH;
    private javax.swing.JTable tbDonDH;
    private javax.swing.JTextField txtMaDon;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
