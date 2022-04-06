/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Staff;

import Database.DatabaseManager;
import Tools.FormatText;
import Tools.IDGenerator;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dell
 */
public class JpTaoDonNhapHang extends javax.swing.JPanel {

    /**
     * Creates new form JpTaoDonNhapHang
     */
    static DefaultTableModel dtm;
    static DefaultTableModel dtmCart;
    static Vector IDVector = new Vector();
    private DatabaseManager database = new DatabaseManager("BitSneaker");
    public JpTaoDonNhapHang(String accountID) {
        initComponents();
        
        inThongTinNhanVien(accountID);
        
        loadComboBoxNhaCungCap();
        
        txtMaDon.setText(IDGenerator.generateImportOrderID());
        txtMaDon.setEditable(false);
        
//        IDVector = new Vector();
//        if(layMaDonHangMoiNhat().equals("")){
//            txtMaDon.setText("ORD0000001");
//        }
//        else{
//            String importID = tangMaHoaDon(layMaDonHangMoiNhat());
//            txtMaDon.setText(importID);
//        }
    }
    public void loadComboBoxNhaCungCap(){
        
        try {
            ResultSet rs = database.queryData("SELECT SupplierID FROM SUPPLIER");
            while(rs.next()){
                cbxSupplier.addItem(rs.getString("SupplierID"));
            }
        } catch (SQLException ex) {
            System.out.println("Load ComboBox failed");
        }
    }
    public void inKetQuaTimKiem(String sneakerID){
        
        DefaultTableModel dtm = (DefaultTableModel) tbSearchGoods.getModel();
        dtm.setNumRows(0);
        Vector vt;
        try {
            ResultSet rs = database.queryData("SELECT * FROM SNEAKER WHERE SneakerID = '"+sneakerID+"'");
            while(rs.next()){
                vt = new Vector();
                vt.add(rs.getString("SneakerID"));
                vt.add(rs.getString("Image"));
                vt.add(rs.getString("Name"));
                vt.add(rs.getString("Brand"));
                vt.add(rs.getString("Color"));
                vt.add(rs.getString("Size"));
                vt.add(1);
                vt.add(rs.getString("Unit"));
                vt.add(rs.getString("Category"));
                vt.add(rs.getString("Description"));
                vt.add(0);
                dtm.addRow(vt);
            }
            tbSearchGoods.setModel(dtm);
        } catch (SQLException ex) {
            Logger.getLogger(JpTaoDonNhapHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int kiemTraSneakerID(String sneakerID){
        int kq = -1;
        
        try {
            ResultSet rs = database.queryData("SELECT * FROM SNEAKER WHERE SneakerID = '"+sneakerID+"'");
            if(rs.next()){
                return 1;
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi kiểm tra ID Sneaker!");
        }
        return kq;
    }
    
    public  int kiemTraImportID(String importID){
        int kq = -1;
        
        try {
            ResultSet rs = database.queryData("SELECT ImportID FROM IMPORTORDER WHERE ImportID = '"+importID+"'");
            if(rs.next()){
                kq=1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(JpTaoDonNhapHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }
    public void inTongThanhToan(){
        long sum = 0;
        for(int i = 0 ;i<tbCart.getRowCount();i++){
            sum += FormatText.convertFormatTextToLong(tbCart.getValueAt(i, 11)+"");
        }
        txtTotal.setText(FormatText.covertCurrentcyFormat(sum));
    }
    public void inTongSoLuong(){
        int amount=0;
        for(int i = 0 ;i<tbCart.getRowCount();i++){
            amount += Integer.parseInt(tbCart.getValueAt(i, 6)+"");
        }
        txtAmount.setText(amount+"");
    }
    public int kiemTraAccount(String accountID, String password, String type){
        
        int kq = -1;
        
        try {
            ResultSet rs = database.queryData("SELECT AccountID, Password, Type FROM ACCOUNT WHERE AccountID = ('"+accountID+"') AND Password = ('"+password+"') AND Type =('"+type+"')");
            if(rs.next()){
                kq=1;
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi hàm kiểm tra tài khoản");
        }
        return kq;
    }
     public void inThongTinNhanVien(String accountID){
        
        Vector vt;
        try {
            ResultSet rs = database.queryData("SELECT StaffID, CONCAT_WS(' ',FirstName,LastName) FROM STAFF WHERE STAFF.AccountID = ('"+accountID+"')");
            while(rs.next()){
                lblStaffID.setText(rs.getString(1));
                lblStaffName.setText(rs.getString(2));
            }

        } catch (SQLException ex) {
            System.out.println("Lỗi hàm in thông tin nhân viên");
        }
    }
     public void themDonNhapHang(String importID, String supplierID, String StaffID){
         
        
        database.queryUpdate("INSERT INTO IMPORTORDER(ImportID, SupplierID, StaffID) VALUES ('"+importID+"','"+supplierID+"','"+StaffID+"')");
        
     }
     public  void themChiTietDonNhapHang(String importID, String sneakerID, int amount, long price){
        
        database.queryUpdate("INSERT INTO IMPORTDETAILS(ImportID, SneakerID, Amount, Price) VALUES ('"+importID+"', '"+sneakerID+"','"+amount+"','"+price+"')");
        
     }
     public String layMaDonHangMoiNhat(){
         
         String importID ="";
        try {
            ResultSet rs = database.queryData("SELECT TOP (1) ImportID FROM IMPORTORDER WHERE ImportID LIKE 'ORD%' ORDER BY ImportID DESC");
            while(rs.next()){
                importID = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JpTaoDonNhapHang.class.getName()).log(Level.SEVERE, null, ex);
        }
         return importID;
     }
     public String tangMaHoaDon(String importID){
         String index = importID.substring(3, 10);
         String indexNew = "";
         int id = Integer.parseInt(index)+1;
         int x7,x6,x5,x4,x3,x2,x1;
         x7 =  id/1000000;
         x6 = (id/100000)%10;
         x5 = (id/10000)%10;
         x4 = (id/1000)%10;
         x3 = (id/100)%10;
         x2 = (id/10)%10;
         x1 = (id%10);
         indexNew =importID.substring(0,3)+ Integer.toString(x7)+Integer.toString(x6)+Integer.toString(x5)+Integer.toString(x4)+Integer.toString(x3)+Integer.toString(x2)+Integer.toString(x1);
         return indexNew;
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
        lblTaoDonNhapHang = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cbxSupplier = new javax.swing.JComboBox<>();
        btnSearch = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSearchGoods = new javax.swing.JTable();
        btnAddToCart = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbCart = new javax.swing.JTable();
        btnOrder = new javax.swing.JButton();
        btnInfoSupplier = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtMaDon = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblStaffID = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        lblStaffName = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        btnRemove = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblTaoDonNhapHang.setBackground(new java.awt.Color(204, 204, 255));
        lblTaoDonNhapHang.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTaoDonNhapHang.setForeground(new java.awt.Color(0, 0, 102));
        lblTaoDonNhapHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTaoDonNhapHang.setIcon(new javax.swing.ImageIcon("src//main//java//res//iconStaff//note.png"));
        lblTaoDonNhapHang.setText("Tạo đơn nhập hàng");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Nhà cung cấp");

        cbxSupplier.setEditable(true);

        btnSearch.setBackground(new java.awt.Color(255, 255, 255));
        btnSearch.setIcon(new javax.swing.ImageIcon("src//main//java//res//iconStaff//search2.png"));
        btnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Chọn sản phẩm");

        txtSearch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtSearch.setText("Search");
        txtSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSearchMouseClicked(evt);
            }
        });

        tbSearchGoods.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sneaker ID", "Image", "Name", "Branch ", "Color", "Size", "Amount", "Unit", "Category", "Description", "Import Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbSearchGoods.setSelectionBackground(new java.awt.Color(255, 204, 204));
        tbSearchGoods.setSelectionForeground(new java.awt.Color(51, 51, 51));
        tbSearchGoods.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSearchGoodsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbSearchGoods);
        if (tbSearchGoods.getColumnModel().getColumnCount() > 0) {
            tbSearchGoods.getColumnModel().getColumn(0).setResizable(false);
            tbSearchGoods.getColumnModel().getColumn(1).setResizable(false);
            tbSearchGoods.getColumnModel().getColumn(2).setResizable(false);
            tbSearchGoods.getColumnModel().getColumn(3).setResizable(false);
            tbSearchGoods.getColumnModel().getColumn(4).setResizable(false);
            tbSearchGoods.getColumnModel().getColumn(5).setResizable(false);
            tbSearchGoods.getColumnModel().getColumn(6).setResizable(false);
            tbSearchGoods.getColumnModel().getColumn(7).setResizable(false);
            tbSearchGoods.getColumnModel().getColumn(8).setResizable(false);
            tbSearchGoods.getColumnModel().getColumn(9).setResizable(false);
            tbSearchGoods.getColumnModel().getColumn(10).setResizable(false);
        }

        btnAddToCart.setBackground(new java.awt.Color(204, 204, 255));
        btnAddToCart.setIcon(new javax.swing.ImageIcon("src//main//java//res//iconStaff//add-to-cart.png"));
        btnAddToCart.setText("THÊM VÀO GIỎ HÀNG");
        btnAddToCart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddToCartMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Cart");

        tbCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sneaker ID", "Image", "Name", "Branch", "Color", "Size", "Amount", "Unit", "Category", "Description", "Import Price", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Long.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbCart.setSelectionBackground(new java.awt.Color(255, 204, 204));
        tbCart.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tbCart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCartMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbCart);
        if (tbCart.getColumnModel().getColumnCount() > 0) {
            tbCart.getColumnModel().getColumn(0).setResizable(false);
            tbCart.getColumnModel().getColumn(1).setResizable(false);
            tbCart.getColumnModel().getColumn(2).setResizable(false);
            tbCart.getColumnModel().getColumn(3).setResizable(false);
            tbCart.getColumnModel().getColumn(4).setResizable(false);
            tbCart.getColumnModel().getColumn(5).setResizable(false);
            tbCart.getColumnModel().getColumn(6).setResizable(false);
            tbCart.getColumnModel().getColumn(7).setResizable(false);
            tbCart.getColumnModel().getColumn(8).setResizable(false);
            tbCart.getColumnModel().getColumn(9).setResizable(false);
            tbCart.getColumnModel().getColumn(10).setResizable(false);
            tbCart.getColumnModel().getColumn(11).setResizable(false);
        }

        btnOrder.setBackground(new java.awt.Color(153, 153, 255));
        btnOrder.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnOrder.setIcon(new javax.swing.ImageIcon("src//main//java//res//iconStaff//shoe-box.png"));
        btnOrder.setText("ĐẶT HÀNG");
        btnOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOrderMouseClicked(evt);
            }
        });

        btnInfoSupplier.setBackground(new java.awt.Color(255, 255, 255));
        btnInfoSupplier.setIcon(new javax.swing.ImageIcon("src//main//java//res//iconStaff//info.png"));
        btnInfoSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInfoSupplierMouseClicked(evt);
            }
        });

        jLabel6.setText("TỔNG THANH TOÁN");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("VND");

        jLabel4.setText("TỔNG SỐ LƯỢNG");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("MÃ ĐƠN NHẬP HÀNG:");

        txtMaDon.setEnabled(false);

        jLabel8.setText("TÀI KHOẢN ĐANG ĐĂNG NHẬP");

        jLabel9.setText("Mã Nhân Viên:");

        jLabel11.setText("Tên:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel8)
                        .addGap(29, 29, 29))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel9)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblStaffID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2)
                            .addComponent(lblStaffName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(1, 1, 1)))
                .addGap(29, 29, 29))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStaffID, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(lblStaffName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        btnRemove.setIcon(new javax.swing.ImageIcon("src//main//java//res//iconStaff//delete.png"));
        btnRemove.setText("Loại");
        btnRemove.setEnabled(false);
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbxSupplier, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(btnInfoSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(74, 74, 74))
                                    .addComponent(lblTaoDonNhapHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(21, 21, 21)
                                        .addComponent(txtMaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAddToCart)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                                .addComponent(txtAmount))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel7)
                            .addGap(428, 428, 428)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnRemove, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnOrder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTaoDonNhapHang)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnInfoSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxSupplier, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAddToCart)
                .addGap(15, 15, 15)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemove)
                        .addGap(37, 37, 37)
                        .addComponent(btnOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseClicked
        // TODO add your handling code here:
        if(txtSearch.getText().equals("") || txtSearch.getText().equals("Search")){
            JOptionPane.showMessageDialog(null, "Yêu cầu nhập ID Sneaker cần tìm!");
        }
        else{
            if(kiemTraSneakerID(txtSearch.getText())>0){
                inKetQuaTimKiem(txtSearch.getText());
            }
            else{
                JOptionPane.showMessageDialog(null, "Sneaker ID cần tìm không tồn tại!");
            }
            
        }
        
    }//GEN-LAST:event_btnSearchMouseClicked

    private void txtSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSearchMouseClicked
        // TODO add your handling code here:
        if(txtSearch.getText().equals("Search")){
            txtSearch.setText("");
        }
    }//GEN-LAST:event_txtSearchMouseClicked

    private void tbSearchGoodsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSearchGoodsMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbSearchGoodsMouseClicked

    private void btnAddToCartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddToCartMouseClicked
        // TODO add your handling code here:
        
        DefaultTableModel dtm = (DefaultTableModel) tbSearchGoods.getModel();
        DefaultTableModel dtmCart = (DefaultTableModel) tbCart.getModel();
        //dtmCart.setNumRows(0);
        
        int selectNumRow = tbSearchGoods.getSelectedRow();
        if(selectNumRow>=0){
            Vector vt = new Vector();
                vt.add(dtm.getValueAt(selectNumRow, 0).toString());
                vt.add(dtm.getValueAt(selectNumRow, 1).toString());
                vt.add(dtm.getValueAt(selectNumRow, 2).toString());
                vt.add(dtm.getValueAt(selectNumRow, 3).toString());
                vt.add(dtm.getValueAt(selectNumRow, 4).toString());
                vt.add(dtm.getValueAt(selectNumRow, 5).toString());
                vt.add(dtm.getValueAt(selectNumRow, 6).toString());
                vt.add(dtm.getValueAt(selectNumRow, 7).toString());
                vt.add(dtm.getValueAt(selectNumRow, 8).toString());
                vt.add(dtm.getValueAt(selectNumRow, 9).toString());

            String tmp = dtm.getValueAt(selectNumRow, 10).toString();
            long importPrice = Long.parseLong(tmp);
            tmp = dtm.getValueAt(selectNumRow, 6).toString();
            int amount = Integer.parseInt(tmp);

            if(importPrice==0){
                JOptionPane.showMessageDialog(null, "Gía nhập không hợp lệ");
            }
            else{ 
                vt.add(FormatText.covertCurrentcyFormat(Long.parseLong(dtm.getValueAt(selectNumRow, 10)+"")));
                long total = (long)(importPrice*amount);
                vt.add(FormatText.covertCurrentcyFormat(total));
                if(IDVector.size()>0){
                    int n = IDVector.size();
                    String E1 = vt.firstElement().toString();
                    int x = 0;
                    for(int i = 0 ; i<n ;i++ ){
                        String E2 = IDVector.get(i).toString();
                        if(E1.equals(E2)){
                            x++;
                        }
                    }
                    if(x>0){
                        JOptionPane.showMessageDialog(null, "Sản phẩm này đã tồn tại trong giỏ hàng! Vui lòng chỉnh sửa ở giỏ hàng.");     
                    }
                    else{
                        dtmCart.addRow(vt);
                        IDVector.add(vt.firstElement());
                        inTongThanhToan();
                        inTongSoLuong();
                    }
                }
                else{
                    dtmCart.addRow(vt);
                    IDVector.add(vt.firstElement());
                    inTongThanhToan();
                    inTongSoLuong();    
                }

            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Yêu cầu chọn sản phẩm để thêm vào giỏ hàng!");
        }
        
        this.inTongSoLuong();
        this.inTongThanhToan();
        
    }//GEN-LAST:event_btnAddToCartMouseClicked

    private void btnInfoSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInfoSupplierMouseClicked
        // TODO add your handling code here:
        String name = cbxSupplier.getSelectedItem().toString();
        JFXemThongTinNhaCungCap ncc = new JFXemThongTinNhaCungCap(name);
        ncc.setVisible(true);
        
    }//GEN-LAST:event_btnInfoSupplierMouseClicked

    private void btnOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOrderMouseClicked
        // TODO add your handling code here:
            if(IDVector.size()==0){
                JOptionPane.showMessageDialog(null, "Giỏ hàng đang rỗng!");
            }
            else{
                
//                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//                Date date = new Date();
                if(kiemTraImportID(txtMaDon.getText())>0){
                    JOptionPane.showMessageDialog(null, "MÃ ĐƠN HÀNG ĐÃ TỒN TẠI!");
                }
                else{
                    themDonNhapHang(txtMaDon.getText(), cbxSupplier.getSelectedItem().toString(), lblStaffID.getText());
                    for(int i =0 ; i<tbCart.getRowCount();i++){
                        String sneakerID = tbCart.getValueAt(i,0).toString();
                        int amount = Integer.parseInt(tbCart.getValueAt(i, 6).toString());
                        long importPrice = FormatText.convertFormatTextToLong(tbCart.getValueAt(i, 10)+"");
                        themChiTietDonNhapHang(txtMaDon.getText(), sneakerID, amount, importPrice);
                    }
                    JOptionPane.showMessageDialog(null, "ĐƠN NHẬP HÀNG ĐÃ ĐƯỢC TẠO");
                    cbxSupplier.setSelectedIndex(0);
                    txtSearch.setText("Search");
                    txtMaDon.setText(tangMaHoaDon(layMaDonHangMoiNhat()));
                    txtAmount.setText("");
                    txtTotal.setText("");
                    
                    DefaultTableModel cls1 = (DefaultTableModel) tbSearchGoods.getModel();
                    cls1.setRowCount(0);
                    DefaultTableModel cls2 = (DefaultTableModel) tbCart.getModel();
                    cls2.setRowCount(0);
                    
                    IDVector.clear();
                    
                    this.tbSearchGoods.setModel(cls1);
                    this.tbCart.setModel(cls2);
                }
                    
            }
        
        
    }//GEN-LAST:event_btnOrderMouseClicked

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        
        int index = tbCart.getSelectedRow();
        if(index>=0){
            DefaultTableModel dtm = (DefaultTableModel) tbCart.getModel();
            dtm.removeRow(index);
            IDVector.remove(index);
            tbCart.setModel(dtm);
        }
        
        this.inTongSoLuong();
        this.inTongThanhToan();
        
        btnRemove.setEnabled(false);
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void tbCartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCartMouseClicked
        int index = tbCart.getSelectedRow();
        if(index>=0){
            btnRemove.setEnabled(true);
        }
    }//GEN-LAST:event_tbCartMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddToCart;
    private javax.swing.JButton btnInfoSupplier;
    private javax.swing.JButton btnOrder;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cbxSupplier;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblStaffID;
    private javax.swing.JLabel lblStaffName;
    private javax.swing.JLabel lblTaoDonNhapHang;
    private javax.swing.JTable tbCart;
    private javax.swing.JTable tbSearchGoods;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtMaDon;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
