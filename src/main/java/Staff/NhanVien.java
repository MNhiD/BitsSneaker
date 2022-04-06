
package Staff;

import Database.DatabaseManager;
import Entity.Staff;
import Tools.ImageController;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC_DELL
 */
public class NhanVien extends javax.swing.JFrame {

    /**
     * Creates new form NhanVien
     */
    
    ArrayList<Staff> listStaffLogin = new ArrayList<>();
    
    DatabaseManager database = new DatabaseManager("BitSneaker");
    
    public NhanVien(String accountID) {

        listStaffLogin.add(this.getStaffInfo(accountID));
        
        initComponents();
        setTitle("Quản Lý Nhân Viên");
        
        ArrayList<ChucNangNhanVien> list = new ArrayList<ChucNangNhanVien>();
        
        list.add(new ChucNangNhanVien("QuanLy", jPanel1, jLabel1));
        list.add(new ChucNangNhanVien("TaoHoaDon", tab1, lblTaoHD));
        list.add(new ChucNangNhanVien("DuyetHoaDon", tab2, lblDuyetHD));
        list.add(new ChucNangNhanVien("TaoDonNhapHang", tab3, lblTaoDonNH));
        list.add(new ChucNangNhanVien("NhanDonNhapHang", tab4, lblNhanDonNH));
        list.add(new ChucNangNhanVien("ThongKeTrongNgay", tab5, lblThongKe));
        list.add(new ChucNangNhanVien("DangNhap",tab6,lblDangNhap));
        list.add(new ChucNangNhanVien("DangXuat",tab7,lblDangXuat));
        
        ChuyenManHinh control = new ChuyenManHinh(jpView, listStaffLogin, this);
        control.setView(jPanel1, jLabel1);
        control.setEvent(list);
         
        
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


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpNhanVien = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tab3 = new javax.swing.JPanel();
        lblTaoDonNH = new javax.swing.JLabel();
        tab2 = new javax.swing.JPanel();
        lblDuyetHD = new javax.swing.JLabel();
        tab1 = new javax.swing.JPanel();
        lblTaoHD = new javax.swing.JLabel();
        tab4 = new javax.swing.JPanel();
        lblNhanDonNH = new javax.swing.JLabel();
        tab5 = new javax.swing.JPanel();
        lblThongKe = new javax.swing.JLabel();
        tab7 = new javax.swing.JPanel();
        lblDangXuat = new javax.swing.JLabel();
        tab6 = new javax.swing.JPanel();
        lblDangNhap = new javax.swing.JLabel();
        jpTimKiem2 = new javax.swing.JPanel();
        lblDanhSachNhanVien2 = new javax.swing.JLabel();
        cbxAccActive2 = new javax.swing.JComboBox<>();
        txtSearch2 = new javax.swing.JTextField();
        lblSearch2 = new javax.swing.JLabel();
        jpView = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("NhanVien");
        setBackground(new java.awt.Color(102, 153, 255));

        jPanel1.setBackground(new java.awt.Color(102, 153, 255));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon("src//main//java//res//iconStaff//seller.png"));
        jLabel1.setText("QUẢN LÝ NHÂN VIÊN");

        tab3.setBackground(new java.awt.Color(204, 204, 255));
        tab3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab3MouseClicked(evt);
            }
        });

        lblTaoDonNH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTaoDonNH.setIcon(new javax.swing.ImageIcon(ImageController.getScaledImage(new ImageIcon("src//main//java//res//iconStaff//import.png").getImage(), 40, 0)));
        lblTaoDonNH.setText("TẠO ĐƠN NHẬP HÀNG");
        lblTaoDonNH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTaoDonNHMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout tab3Layout = new javax.swing.GroupLayout(tab3);
        tab3.setLayout(tab3Layout);
        tab3Layout.setHorizontalGroup(
            tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab3Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(lblTaoDonNH, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tab3Layout.setVerticalGroup(
            tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTaoDonNH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tab2.setBackground(new java.awt.Color(204, 204, 255));
        tab2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab2MouseClicked(evt);
            }
        });

        lblDuyetHD.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDuyetHD.setIcon(new javax.swing.ImageIcon(ImageController.getScaledImage(new ImageIcon("src//main//java//res//iconStaff//billcheck.png").getImage(), 40, 0)));
        lblDuyetHD.setText("DUYỆT HÓA ĐƠN");

        javax.swing.GroupLayout tab2Layout = new javax.swing.GroupLayout(tab2);
        tab2.setLayout(tab2Layout);
        tab2Layout.setHorizontalGroup(
            tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab2Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(lblDuyetHD, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tab2Layout.setVerticalGroup(
            tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDuyetHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tab1.setBackground(new java.awt.Color(204, 204, 255));
        tab1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab1MouseClicked(evt);
            }
        });

        lblTaoHD.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTaoHD.setIcon(new javax.swing.ImageIcon(ImageController.getScaledImage(new ImageIcon("src//main//java//res//iconStaff//bill.png").getImage(), 40, 0)));
        lblTaoHD.setText("TẠO HÓA ĐƠN");
        lblTaoHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTaoHDMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout tab1Layout = new javax.swing.GroupLayout(tab1);
        tab1.setLayout(tab1Layout);
        tab1Layout.setHorizontalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(lblTaoHD, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tab1Layout.setVerticalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTaoHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tab4.setBackground(new java.awt.Color(204, 204, 255));
        tab4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab4MouseClicked(evt);
            }
        });

        lblNhanDonNH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNhanDonNH.setIcon(new javax.swing.ImageIcon(ImageController.getScaledImage(new ImageIcon("src//main//java//res//iconStaff//nhanDonNhapHang.png").getImage(), 40, 0)));
        lblNhanDonNH.setText("NHẬN ĐƠN NHẬP HÀNG");

        javax.swing.GroupLayout tab4Layout = new javax.swing.GroupLayout(tab4);
        tab4.setLayout(tab4Layout);
        tab4Layout.setHorizontalGroup(
            tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab4Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(lblNhanDonNH, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tab4Layout.setVerticalGroup(
            tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNhanDonNH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tab5.setBackground(new java.awt.Color(204, 204, 255));
        tab5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab5MouseClicked(evt);
            }
        });

        lblThongKe.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblThongKe.setIcon(new javax.swing.ImageIcon(ImageController.getScaledImage(new ImageIcon("src//main//java//res//iconStaff//thongKe.png").getImage(), 40, 0)));
        lblThongKe.setText("THỐNG KÊ TRONG NGÀY");

        javax.swing.GroupLayout tab5Layout = new javax.swing.GroupLayout(tab5);
        tab5.setLayout(tab5Layout);
        tab5Layout.setHorizontalGroup(
            tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab5Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(lblThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tab5Layout.setVerticalGroup(
            tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tab7.setBackground(new java.awt.Color(204, 204, 255));
        tab7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab7MouseClicked(evt);
            }
        });

        lblDangXuat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDangXuat.setIcon(new javax.swing.ImageIcon(ImageController.getScaledImage(new ImageIcon("src//main//java//res//iconStaff//logout.png").getImage(), 40, 0)));
        lblDangXuat.setText("ĐĂNG XUẤT");
        lblDangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDangXuatMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout tab7Layout = new javax.swing.GroupLayout(tab7);
        tab7.setLayout(tab7Layout);
        tab7Layout.setHorizontalGroup(
            tab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab7Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(lblDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tab7Layout.setVerticalGroup(
            tab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDangXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tab6.setBackground(new java.awt.Color(204, 204, 255));
        tab6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab6MouseClicked(evt);
            }
        });

        lblDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDangNhap.setIcon(new javax.swing.ImageIcon(ImageController.getScaledImage(new ImageIcon("src//main//java//res//iconStaff//login.png").getImage(), 40, 0)));
        lblDangNhap.setText("ĐĂNG NHẬP");
        lblDangNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDangNhapMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout tab6Layout = new javax.swing.GroupLayout(tab6);
        tab6.setLayout(tab6Layout);
        tab6Layout.setHorizontalGroup(
            tab6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab6Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(lblDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tab6Layout.setVerticalGroup(
            tab6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tab3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tab4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tab7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tab2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tab5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tab6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(tab1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tab2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tab3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tab4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tab5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tab7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tab6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jpTimKiem2.setBackground(new java.awt.Color(102, 153, 255));

        lblDanhSachNhanVien2.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        lblDanhSachNhanVien2.setText("TÀI KHOẢN ĐANG HOẠT ĐỘNG");

        cbxAccActive2.setBackground(new java.awt.Color(240, 240, 240));
        cbxAccActive2.setEditable(true);
        cbxAccActive2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhi ", "Trang" }));

        txtSearch2.setFont(new java.awt.Font("Consolas", 2, 11)); // NOI18N
        txtSearch2.setText("Search");

        javax.swing.GroupLayout jpTimKiem2Layout = new javax.swing.GroupLayout(jpTimKiem2);
        jpTimKiem2.setLayout(jpTimKiem2Layout);
        jpTimKiem2Layout.setHorizontalGroup(
            jpTimKiem2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTimKiem2Layout.createSequentialGroup()
                .addGap(185, 185, 185)
                .addComponent(txtSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDanhSachNhanVien2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbxAccActive2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108))
        );
        jpTimKiem2Layout.setVerticalGroup(
            jpTimKiem2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTimKiem2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpTimKiem2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpTimKiem2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTimKiem2Layout.createSequentialGroup()
                            .addGroup(jpTimKiem2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblDanhSachNhanVien2)
                                .addComponent(cbxAccActive2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(10, 10, 10)))))
        );

        javax.swing.GroupLayout jpViewLayout = new javax.swing.GroupLayout(jpView);
        jpView.setLayout(jpViewLayout);
        jpViewLayout.setHorizontalGroup(
            jpViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1003, Short.MAX_VALUE)
        );
        jpViewLayout.setVerticalGroup(
            jpViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpNhanVienLayout = new javax.swing.GroupLayout(jpNhanVien);
        jpNhanVien.setLayout(jpNhanVienLayout);
        jpNhanVienLayout.setHorizontalGroup(
            jpNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpNhanVienLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpTimKiem2, javax.swing.GroupLayout.PREFERRED_SIZE, 1028, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jpNhanVienLayout.setVerticalGroup(
            jpNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpNhanVienLayout.createSequentialGroup()
                .addGroup(jpNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpNhanVienLayout.createSequentialGroup()
                        .addComponent(jpTimKiem2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1MouseClicked

    private void lblTaoDonNHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTaoDonNHMouseClicked
        // TODO add your handling code here:
        //jpTaoDonNhapHang.setVisible(true);
    }//GEN-LAST:event_lblTaoDonNHMouseClicked

    private void tab3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab3MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tab3MouseClicked

    private void tab2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab2MouseClicked
        // TODO add your handling code here:
        //        j2.setVisible(true);
        //        j0.setVisible(false);
        //        j1.setVisible(false);
        //        j3.setVisible(false);
        //        j4.setVisible(false);
        //        j5.setVisible(false);
        //        j6.setVisible(false);
        //        j7.setVisible(false);
    }//GEN-LAST:event_tab2MouseClicked

    private void lblTaoHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTaoHDMouseClicked
        // TODO add your handling code here:
        //JOptionPane.showInputDialog(rootPane, "Nhập tên của bạn:");
    }//GEN-LAST:event_lblTaoHDMouseClicked

    private void tab1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab1MouseClicked
        // TODO add your handling code here:
        //        j1.setVisible(true);
        //        j0.setVisible(false);
        //        j2.setVisible(false);
        //        j3.setVisible(false);
        //        j4.setVisible(false);
        //        j5.setVisible(false);
        //        j6.setVisible(false);
        //        j7.setVisible(false);
    }//GEN-LAST:event_tab1MouseClicked

    private void tab4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab4MouseClicked
        // TODO add your handling code here:
        //        j4.setVisible(true);
        //        j0.setVisible(false);
        //        j1.setVisible(false);
        //        j2.setVisible(false);
        //        j3.setVisible(false);
        //        j5.setVisible(false);
        //        j6.setVisible(false);
        //        j7.setVisible(false);
    }//GEN-LAST:event_tab4MouseClicked

    private void tab5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab5MouseClicked
        // TODO add your handling code here:
        //        j5.setVisible(true);
        //        j0.setVisible(false);
        //        j1.setVisible(false);
        //        j2.setVisible(false);
        //        j3.setVisible(false);
        //        j4.setVisible(false);
        //        j6.setVisible(false);
        //        j7.setVisible(false);
    }//GEN-LAST:event_tab5MouseClicked

    private void lblDangXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDangXuatMouseClicked
        // TODO add your handling code here:
//        JOptionPane.showConfirmDialog(rootPane, "Bạn muốn đăng xuất khỏi tài khoản này?");
    }//GEN-LAST:event_lblDangXuatMouseClicked

    private void tab7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab7MouseClicked
        // TODO add your handling code here:
        //        j7.setVisible(true);
        //        j0.setVisible(false);
        //        j1.setVisible(false);
        //        j2.setVisible(false);
        //        j3.setVisible(false);
        //        j4.setVisible(false);
        //        j5.setVisible(false);
        //        j6.setVisible(false);
    }//GEN-LAST:event_tab7MouseClicked

    private void lblDangNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDangNhapMouseClicked
        // TODO add your handling code here:
//        new FrameLogin().setVisible(true);
    }//GEN-LAST:event_lblDangNhapMouseClicked

    private void tab6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab6MouseClicked
        // TODO add your handling code here:
        //        j6.setVisible(true);
        //        j0.setVisible(false);
        //        j1.setVisible(false);
        //        j2.setVisible(false);
        //        j3.setVisible(false);
        //        j4.setVisible(false);
        //        j5.setVisible(false);
        //        j7.setVisible(false);
    }//GEN-LAST:event_tab6MouseClicked

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new NhanVien().setVisible(true);
//               
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbxAccActive2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jpNhanVien;
    private javax.swing.JPanel jpTimKiem2;
    private javax.swing.JPanel jpView;
    private javax.swing.JLabel lblDangNhap;
    private javax.swing.JLabel lblDangXuat;
    private javax.swing.JLabel lblDanhSachNhanVien2;
    private javax.swing.JLabel lblDuyetHD;
    private javax.swing.JLabel lblNhanDonNH;
    private javax.swing.JLabel lblSearch2;
    private javax.swing.JLabel lblTaoDonNH;
    private javax.swing.JLabel lblTaoHD;
    private javax.swing.JLabel lblThongKe;
    private javax.swing.JPanel tab1;
    private javax.swing.JPanel tab2;
    private javax.swing.JPanel tab3;
    private javax.swing.JPanel tab4;
    private javax.swing.JPanel tab5;
    private javax.swing.JPanel tab6;
    private javax.swing.JPanel tab7;
    private javax.swing.JTextField txtSearch2;
    // End of variables declaration//GEN-END:variables
}
