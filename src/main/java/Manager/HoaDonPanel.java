
package Manager;

import Database.DatabaseManager;
import Tools.FormatText;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vntvn
 */
public class HoaDonPanel extends javax.swing.JPanel {

    /**
     * Creates new form HoaDonPanel
     */
    
    DatabaseManager database = new DatabaseManager("BitSneaker");
    
    public void layDSHoaDonNhap() {
        try {
            DefaultTableModel dtm=(DefaultTableModel)tblHoaDonNhap.getModel();
            dtm.setNumRows(0);
            Vector vt;
//            Connection ketNoi= KetNoiCSDL.layKetNoi();
            String sql="select IMPORTORDER.*,SUPPLIER.Name,STAFF.FirstName,STAFF.LastName from IMPORTORDER,SUPPLIER,STAFF where IMPORTORDER.SupplierID=SUPPLIER.SupplierID and STAFF.StaffID=IMPORTORDER.StaffID";
//            PreparedStatement ps= ketNoi.prepareStatement(sql);
//            ResultSet rs=ps.executeQuery();
            ResultSet rs = database.queryData(sql);
            while(rs.next()){
                vt=new Vector();
                vt.add(rs.getString("ImportID"));
                vt.add(rs.getString("OrderTime"));
                vt.add(rs.getString("Name"));
                vt.add(rs.getString("FirstName")+" "+rs.getString("LastName"));                 
//                vt.add(rs.getString("Address"));
                dtm.addRow(vt);
                
                tblHoaDonNhap.setModel(dtm);                       } 
//            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void layThongTinHoaDonNhap(String importID) {
        try {
            DefaultTableModel dtm=(DefaultTableModel)tblChiTietHoaDonNhap.getModel();
            dtm.setNumRows(0);
            Vector vt;
//            Connection ketNoi= KetNoiCSDL.layKetNoi();
            String sql="select IMPORTDETAILS.ImportID,SNEAKER.Name,IMPORTDETAILS.Amount,IMPORTDETAILS.Price from IMPORTDETAILS,SNEAKER,PRICE where IMPORTDETAILS.ImportID='"+importID+"' and  IMPORTDETAILS.SneakerID=SNEAKER.SneakerID and SNEAKER.SneakerID=PRICE.SneakerID";
//            PreparedStatement ps= ketNoi.prepareStatement(sql);
//            ResultSet rs=ps.executeQuery();
            ResultSet rs = database.queryData(sql);
            while(rs.next()){
                vt=new Vector();
                vt.add(rs.getString("ImportID"));
                vt.add(rs.getString("Name"));
                vt.add(rs.getInt("Amount"));
                vt.add(FormatText.covertCurrentcyFormat(rs.getLong("Price")));                 
//                vt.add(rs.getString("Address"));
                dtm.addRow(vt);
                
                tblChiTietHoaDonNhap.setModel(dtm);                       } 
//            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    //Hóa đơn bán
    public void layDSHoaDonBan() {
        try {
            DefaultTableModel dtm=(DefaultTableModel)tblHoaDonBan.getModel();
            dtm.setNumRows(0);
            Vector vt;
//            Connection ketNoi= KetNoiCSDL.layKetNoi();
            String sql="select SELLORDER.*,STAFF.FirstName,STAFF.LastName from SELLORDER, STAFF where SELLORDER.StaffID=STAFF.StaffID AND SELLORDER.Status='paid'";
//            PreparedStatement ps= ketNoi.prepareStatement(sql);
//            ResultSet rs=ps.executeQuery();
            ResultSet rs = database.queryData(sql);
            while(rs.next()){
                vt=new Vector();
                vt.add(rs.getString("SellID"));
                vt.add(rs.getString("FirstName")+" "+rs.getString("LastName"));   
                vt.add(rs.getString("TimeCreate"));
                vt.add(rs.getString("Status"));
//                vt.add(rs.getString("Address"));
                dtm.addRow(vt);
                
                tblHoaDonBan.setModel(dtm);                       } 
//            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void layThongTinHoaDonBan(String sellID) {
        try {
            DefaultTableModel dtm=(DefaultTableModel)tblChiTietHoaDonBan.getModel();
            dtm.setNumRows(0);
            Vector vt;
//            Connection ketNoi= KetNoiCSDL.layKetNoi();
            String sql="select SELLDETAILS.SellID,SNEAKER.Name,SELLDETAILS.Amount,SELLDETAILS.Sale,SELLDETAILS.VAT,PRICE.SellPrice from SELLDETAILS,SNEAKER,PRICE where SELLDETAILS.SellID='"+sellID+"' and SELLDETAILS.SneakerID=SNEAKER.SneakerID and SNEAKER.SneakerID=PRICE.SneakerID";
//            PreparedStatement ps= ketNoi.prepareStatement(sql);
//            ResultSet rs=ps.executeQuery();
            ResultSet rs = database.queryData(sql);
            while(rs.next()){
                vt=new Vector();
                vt.add(rs.getString("SellID"));
                vt.add(rs.getString("Name"));
                vt.add(rs.getInt("Amount"));
                vt.add(rs.getInt("Sale"));                 
                vt.add(rs.getInt("VAT"));                 
                vt.add(FormatText.covertCurrentcyFormat((long)(rs.getInt("Amount")*rs.getInt("SellPrice")*(rs.getFloat("Sale")/100))));
                dtm.addRow(vt);
                
                tblChiTietHoaDonBan.setModel(dtm);                       } 
//            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public HoaDonPanel() {
        initComponents();
        layDSHoaDonNhap();
        layDSHoaDonBan();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel12 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        txtMaHoaDonChiTietBan = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtSanPhamHoaDonBan = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtSoLuongHoaDonBan = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtGiamGiaHoaDonBan = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtVAT = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtTongTienHoaDonBan = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDonNhap = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblChiTietHoaDonNhap = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDonBan = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblChiTietHoaDonBan = new javax.swing.JTable();

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin chi tiết hóa đơn bán", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        jLabel19.setText("Mã hóa đơn:");

        jLabel20.setText("Sản phẩm:");

        txtSanPhamHoaDonBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSanPhamHoaDonBanActionPerformed(evt);
            }
        });

        jLabel21.setText("Số lượng:");

        txtSoLuongHoaDonBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongHoaDonBanActionPerformed(evt);
            }
        });

        jLabel22.setText("Giảm giá:");

        txtGiamGiaHoaDonBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiamGiaHoaDonBanActionPerformed(evt);
            }
        });

        jLabel24.setText("VAT");

        txtVAT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVATActionPerformed(evt);
            }
        });

        jLabel25.setText("Tổng tiền:");

        txtTongTienHoaDonBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongTienHoaDonBanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtSanPhamHoaDonBan, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaHoaDonChiTietBan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                    .addComponent(txtSoLuongHoaDonBan, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGiamGiaHoaDonBan, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtVAT, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTongTienHoaDonBan, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaHoaDonChiTietBan)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSanPhamHoaDonBan)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSoLuongHoaDonBan)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtGiamGiaHoaDonBan)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtVAT)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTongTienHoaDonBan)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1000, 700));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        tblHoaDonNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Ngày lập hóa đơn", "Nhà cung cấp", "Nhân viên"
            }
        ));
        tblHoaDonNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonNhapMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDonNhap);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1037, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin chi tiết hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        tblChiTietHoaDonNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Sản phẩm", "Số lượng ", "Giá"
            }
        ));
        tblChiTietHoaDonNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietHoaDonNhapMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblChiTietHoaDonNhap);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        jTabbedPane1.addTab("Hóa đơn nhập", jPanel1);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        tblHoaDonBan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Nhân viên", "Ngày lập hóa đơn", "Tình trạng"
            }
        ));
        tblHoaDonBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonBanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblHoaDonBan);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin chi tiết hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        tblChiTietHoaDonBan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Sản phẩm", "Số lượng ", "Giảm giá", "VAT", "Tổng tiền"
            }
        ));
        tblChiTietHoaDonBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietHoaDonBanMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblChiTietHoaDonBan);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1068, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1104, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 788, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Hóa đơn bán", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1104, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 797, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblHoaDonNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonNhapMouseClicked
        // TODO add your handling code here:
        DefaultTableModel dtm=(DefaultTableModel) tblHoaDonNhap.getModel();
        int luaChon=tblHoaDonNhap.getSelectedRow();
        String importID=(String) dtm.getValueAt(luaChon, 0);
        layThongTinHoaDonNhap(importID);
//       

    }//GEN-LAST:event_tblHoaDonNhapMouseClicked

    private void tblChiTietHoaDonNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietHoaDonNhapMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblChiTietHoaDonNhapMouseClicked

    private void tblHoaDonBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonBanMouseClicked
        // TODO add your handling code here:
        DefaultTableModel dtm=(DefaultTableModel) tblHoaDonBan.getModel();
        int luaChon=tblHoaDonBan.getSelectedRow();
        String sellID=(String) dtm.getValueAt(luaChon, 0);
        layThongTinHoaDonBan(sellID);
    }//GEN-LAST:event_tblHoaDonBanMouseClicked

    private void tblChiTietHoaDonBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietHoaDonBanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblChiTietHoaDonBanMouseClicked

    private void txtSoLuongHoaDonBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongHoaDonBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongHoaDonBanActionPerformed

    private void txtGiamGiaHoaDonBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiamGiaHoaDonBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiamGiaHoaDonBanActionPerformed

    private void txtVATActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVATActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVATActionPerformed

    private void txtTongTienHoaDonBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongTienHoaDonBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongTienHoaDonBanActionPerformed

    private void txtSanPhamHoaDonBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSanPhamHoaDonBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSanPhamHoaDonBanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblChiTietHoaDonBan;
    private javax.swing.JTable tblChiTietHoaDonNhap;
    private javax.swing.JTable tblHoaDonBan;
    private javax.swing.JTable tblHoaDonNhap;
    private javax.swing.JTextField txtGiamGiaHoaDonBan;
    private javax.swing.JTextField txtMaHoaDonChiTietBan;
    private javax.swing.JTextField txtSanPhamHoaDonBan;
    private javax.swing.JTextField txtSoLuongHoaDonBan;
    private javax.swing.JTextField txtTongTienHoaDonBan;
    private javax.swing.JTextField txtVAT;
    // End of variables declaration//GEN-END:variables
}
