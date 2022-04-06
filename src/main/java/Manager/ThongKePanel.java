/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Database.DatabaseManager;
import Tools.FormatText;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BIT
 */
public class ThongKePanel extends javax.swing.JPanel {

    /**
     * Creates new form ThongKePanel
     */
    
    private DatabaseManager database = new DatabaseManager("BitSneaker");
    
    //Hàm LOAD DỮ LIỆU TÊN NHÂN VIÊN TỪ SQL LÊN COMBOBOX NHÂN VIÊN MUỐN XEM
    public void LoadComboBoxNhanVienMuonXem()
        {
        try {
//            Connection conn = KetNoiCSDL.layKetNoi();
            ResultSet rs = database.queryData("SELECT * FROM STAFF");
            while(rs.next()){
                cbNhanVienThongKe.addItem(rs.getString("LastName"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "LỖI! KHÔNG THỂ LẤY DỮ LIỆU.");
        }
    } 
    
    //HÀM KIỂM TRA NGÀY THÁNG NĂM NHẬP VÀO ĐÃ HỢP LỆ CHƯA
    public boolean KiemTraNgayThangNamHopLe(String inDate) {

        if (inDate == null)
          return false;

        //set format để sử dụng  theo cấu trúc này
       DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
               //"^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
////                                                 ^(?:(?:31(\/|-|\.)(?:0?[13578]|1[02]))\1|(?:(?:29|30)(\/|-|\.)(?:0?[13-9]|1[0-2])\2))(?:(?:1[6-9]|[2-9]\d)?\d{2})$|^(?:29(\/|-|\.)0?2\3(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\d|2[0-8])(\/|-|\.)(?:(?:0?[1-9])|(?:1[0-2]))\4(?:(?:1[6-9]|[2-9]\d)?\d{2})$
        try {
            dateFormat.parse(inDate);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
      }
    
    //HÀM SO SÁNH 2 KHOẢNG THỜI GIAN: BẰNG HOẶC TRƯỚC HOẶC SAU
    public static boolean SoSanh2MocThoiGian(String ngayBatDauThongKe, String ngayKetThucThongKe){

        // Định nghĩa 2 mốc thời gian ban đầu
        Date date1 = Date.valueOf(ngayBatDauThongKe);
        Date date2 = Date.valueOf(ngayKetThucThongKe);
        
        if (date1.equals(date2))
          return false;
        else if (date1.before(date2)) // Hoặc  else if (date1.after(date2)== false)
          return true;
        else
          return false;
    }
    
    //Hàm TÌM STAFFID KHI CÓ HỌ TÊN
    public String TimMaNhanVien (String ten) {
        String check="0";
        try {
//            Connection ketNoi= KetNoiCSDL.layKetNoi();
            ResultSet rs= database.queryData("SELECT * FROM STAFF WHERE LastName=N'"+ten+"'");
            while (rs.next()){
                return ten = rs.getString("StaffID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThongKePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
         return check;       
    }
    
    //HÀM TÌM NHÂN VIÊN PHÙ HỢP VỚI 3 ĐIỀU KIỆN: Ngày bắt đầu, Ngày kết thúc, tên nhân viên
    public  int TimNhanVienThongKe(String ngayBatDauThongKe, String ngayKetThucThongKe, String nhanVienMuonXem){
       int check=0;
//       Connection ketNoi=KetNoiCSDL.layKetNoi();
       DefaultTableModel dtm=(DefaultTableModel)tblThongKe.getModel();
       dtm.setNumRows(0);
       if(ngayBatDauThongKe.length()!=0 && ngayKetThucThongKe.length()!=0 && nhanVienMuonXem.length()!=0){
        String sqlNhanVien="SELECT * FROM STAFF WHERE StaffID='"+nhanVienMuonXem+"'"; 
        String sqlSellOrder= "SELECT COUNT(SellID) FROM SELLORDER WHERE StaffID='"+nhanVienMuonXem+"' AND TimeCreate>='"+ngayBatDauThongKe+"' AND TimeCreate<='"+ngayKetThucThongKe+"'";
        String sqlImportOrder= "SELECT COUNT(ImportID) FROM IMPORTORDER WHERE StaffID='"+nhanVienMuonXem+"' AND OrderTime>='"+ngayBatDauThongKe+"' AND OrderTime<='"+ngayKetThucThongKe+"'";
        try {
            ResultSet rsNhanVien=database.queryData(sqlNhanVien);
            ResultSet rsSellOrder=database.queryData(sqlSellOrder);
            ResultSet rsImportOrder=database.queryData(sqlImportOrder);
                while(rsNhanVien.next()){
                Vector vt = new Vector();
                vt.add(rsNhanVien.getString("StaffID"));
                vt.add(rsNhanVien.getString("FirstName"));
                vt.add(rsNhanVien.getString("LastName"));
                rsSellOrder.next();
                rsImportOrder.next();
                vt.add(rsSellOrder.getString(1));
                vt.add(rsImportOrder.getString(1));
                vt.add(rsSellOrder.getInt(1)+rsImportOrder.getInt(1));
                dtm.addRow(vt);
                tblThongKe.setModel(dtm);            
                }
            
            rsNhanVien.close();
            
            rsSellOrder.close();
            
            rsImportOrder.close();
            //ps.setString(2, "");
            check=1;
        } 
        catch (SQLException ex) {
            Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
        } 
       }
        return check;
    }
  
    //HÀM TÍNH TIỀN BÁN ĐƯỢC
    public boolean tinhTienBanDuoc( String ngayBatDauThongKe, String ngayKetThucThongKe) {
        try {
//            Connection ketNoi= KetNoiCSDL.layKetNoi();
            String sql= "select sum(BANGTONGTIENBANDUOC.TongBillBan) from (select  SELLORDER.*, SELLDETAILS.Amount, SELLDETAILS.Sale, SELLDETAILS.VAT, PRICE.SellPrice, 'TongBillBan' = PRICE.SellPrice*SELLDETAILS.Amount*(SELLDETAILS.Sale/100) from SELLORDER, SELLDETAILS , PRICE where SELLORDER.TimeCreate>= '"+ngayBatDauThongKe+"' and SELLORDER.TimeCreate<='"+ngayKetThucThongKe+"' and SELLORDER.SellID=SELLDETAILS.SellID and SELLDETAILS.SneakerID=PRICE.SneakerID) as BANGTONGTIENBANDUOC";            
            ResultSet rs=database.queryData(sql);
            while(rs.next()){
               txtTongTienBanDuoc.setText(FormatText.covertCurrentcyFormat(rs.getLong(1)));
               return true;
            } 
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonPanel.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return false;
    }
    
    //HÀM TÍNH TIỀN NHẬP HÀNG
    public boolean tinhTienNhapHang( String ngayBatDauThongKe, String ngayKetThucThongKe) {
        try {
//            Connection ketNoi= KetNoiCSDL.layKetNoi();
            String sql= "select sum(BANGTONGTIENNHAPHANG.TongBillNhap) from (select  IMPORTORDER.*, IMPORTDETAILS.Amount, IMPORTDETAILS.Price, 'TongBillNhap' = IMPORTDETAILS.Price*IMPORTDETAILS.Amount from IMPORTORDER, IMPORTDETAILS where IMPORTORDER.OrderTime>= '"+ngayBatDauThongKe+"' and IMPORTORDER.OrderTime<='"+ngayKetThucThongKe+"' and IMPORTORDER.ImportID=IMPORTDETAILS.ImportID) as BANGTONGTIENNHAPHANG";
            ResultSet rs=database.queryData(sql);
            while(rs.next()){
               txtTongTienNhapHang.setText(FormatText.covertCurrentcyFormat(rs.getLong(1)));
               return true;
            } 
            
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonPanel.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return false;
    }
    
    //HÀM TÍNH TỔNG HÓA ĐƠN BÁN ĐƯỢC (ĐANG LÀM THÊM BỔ SUNG)
    public boolean tinhTongHoaDonBanDuoc( String ngayBatDauThongKe, String ngayKetThucThongKe) {
        try {
//            Connection ketNoi= KetNoiCSDL.layKetNoi();
            String sql= "SELECT COUNT(SellID) FROM SELLORDER WHERE TimeCreate>='"+ngayBatDauThongKe+"' AND TimeCreate<='"+ngayKetThucThongKe+"'";
            ResultSet rs=database.queryData(sql);
            while(rs.next()){
               txtTongHoaDonBanDuoc.setText(rs.getString(1));
               return true;
            } 
            
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonPanel.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return false;
    }
    
    
    public ThongKePanel() {
        initComponents();
        LoadComboBoxNhanVienMuonXem();
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
        jLabel1 = new javax.swing.JLabel();
        txtNgayBatDauThongKe = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNgayKetThucThongKe = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbNhanVienThongKe = new javax.swing.JComboBox<>();
        btnThongKeManager = new javax.swing.JButton();
        btnBieuDo = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThongKe = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtTongTienBanDuoc = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTongTienNhapHang = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtLoiNhuan = new javax.swing.JTextField();
        btnXemDoanhThu = new javax.swing.JButton();
        txtTongHoaDonBanDuoc = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(1110, 800));

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1110, 800));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel1.setText("Thống kê từ ngày :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel2.setText("Đến ngày:");

        jLabel3.setText("Chọn nhân viên muốn xem:");

        btnThongKeManager.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnThongKeManager.setIcon(new javax.swing.ImageIcon("src//main//java//res//iconManager//dollar.png"));
        btnThongKeManager.setText("Thống Kê");
        btnThongKeManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeManagerActionPerformed(evt);
            }
        });

        btnBieuDo.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnBieuDo.setIcon(new javax.swing.ImageIcon("src//main//java//res//iconManager//profits.png"));
        btnBieuDo.setText("Biểu đồ");
        btnBieuDo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBieuDoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNgayBatDauThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                    .addComponent(cbNhanVienThongKe, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNgayKetThucThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(btnThongKeManager)
                .addGap(31, 31, 31)
                .addComponent(btnBieuDo)
                .addGap(0, 178, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txtNgayBatDauThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtNgayKetThucThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cbNhanVienThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBieuDo, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThongKeManager, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(21, 21, 21))
        );

        tblThongKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Nhân Viên", "Họ", "Tên", "Hóa Đơn Đã Bán", "Hóa Đơn Đã Mua", "Tổng Hóa Đơn"
            }
        ));
        jScrollPane1.setViewportView(tblThongKe);

        jLabel7.setText("Tổng Tiền Bán Được:");

        jLabel8.setText("Tổng Tiền Nhập Hàng:");

        jLabel9.setText("Lợi Nhuận:");

        btnXemDoanhThu.setText("Xem ");
        btnXemDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemDoanhThuActionPerformed(evt);
            }
        });

        txtTongHoaDonBanDuoc.setFont(new java.awt.Font("Vinhan", 3, 14)); // NOI18N

        jLabel4.setText("Tổng Hóa Đơn Bán Được:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(txtTongTienBanDuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTongTienNhapHang)
                                    .addComponent(txtLoiNhuan))))
                        .addGap(90, 90, 90)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtTongHoaDonBanDuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(262, 262, 262)
                        .addComponent(btnXemDoanhThu)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTongTienBanDuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtTongHoaDonBanDuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTongTienNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtLoiNhuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnXemDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jTextField1.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jTextField1.setText("TỔNG KẾT ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1075, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thống Kê", jPanel1);

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
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThongKeManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeManagerActionPerformed
        // TODO add your handling code here:
        String ngayBatDauThongKe= txtNgayBatDauThongKe.getText(); 
        String ngayKetThucThongKe= txtNgayKetThucThongKe.getText();
        String nhanVienThongKe= (String) cbNhanVienThongKe.getSelectedItem();
        System.out.println("Nhan vien: "+nhanVienThongKe);
        if (ngayBatDauThongKe.length()==0){
            JOptionPane.showMessageDialog(this, "THÔNG TIN NGÀY BẮT ĐẦU THỐNG KÊ KHÔNG ĐƯỢC BỎ TRỐNG. VUI LÒNG NHẬP THÊM THEO CÚ PHÁP yyyy-MM-dd !");
        }else if (ngayKetThucThongKe.length()==0){
            JOptionPane.showMessageDialog(this, "THÔNG TIN NGÀY KẾT THÚC THỐNG KÊ KHÔNG ĐƯỢC BỎ TRỐNG. VUI LÒNG NHẬP THÊM THEO CÚ PHÁP yyyy-MM-dd !");
        }
        
        if (KiemTraNgayThangNamHopLe(ngayBatDauThongKe)==false){
            JOptionPane.showMessageDialog(this, "THÔNG TIN NGÀY BẮT ĐẦU THỐNG KÊ KHÔNG ĐÚNG VỚI CÚ PHÁP yyyy-MM-dd. VUI LÒNG NHẬP LẠI !");
        }else if (KiemTraNgayThangNamHopLe(ngayKetThucThongKe)==false){
            JOptionPane.showMessageDialog(this, "THÔNG TIN NGÀY KẾT THÚC THỐNG KÊ KHÔNG ĐÚNG VỚI CÚ PHÁP yyyy-MM-dd. VUI LÒNG NHẬP LẠI !");
        }
        
        if (SoSanh2MocThoiGian(ngayBatDauThongKe, ngayKetThucThongKe)==false){
            JOptionPane.showMessageDialog(this, "THÔNG TIN PHẠM LỖI NGÀY BẮT ĐẦU THỐNG KÊ >= NGÀY KẾT THÚC THỐNG KÊ. VUI LÒNG NHẬP LẠI !");
        }
        
        if (TimNhanVienThongKe(ngayBatDauThongKe, ngayKetThucThongKe, TimMaNhanVien(nhanVienThongKe))==0){
            JOptionPane.showMessageDialog(this, "LỖI !");
        }
    }//GEN-LAST:event_btnThongKeManagerActionPerformed

    private void btnXemDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemDoanhThuActionPerformed
        // TODO add your handling code here:
        String ngayBatDauThongKe= txtNgayBatDauThongKe.getText(); 
        String ngayKetThucThongKe= txtNgayKetThucThongKe.getText();
      
        if(tinhTienBanDuoc(ngayBatDauThongKe, ngayKetThucThongKe)==false){
            JOptionPane.showMessageDialog(this, "TỒNG TIỀN BÁN RỖNG 1");
        }
        if(tinhTienNhapHang(ngayBatDauThongKe, ngayKetThucThongKe)==false){
            JOptionPane.showMessageDialog(this, "TỒNG TIỀN NHẬP RỖNG !");
        }
        long a= (FormatText.convertFormatTextToLong(txtTongTienBanDuoc.getText()) - FormatText.convertFormatTextToLong(txtTongTienNhapHang.getText())) ;
//        txtLoiNhuan.setText(String.valueOf(Integer.parseInt(txtTongTienBanDuoc.getText()) - Integer.parseInt(txtTongTienNhapHang.getText())));
        txtLoiNhuan.setText(FormatText.covertCurrentcyFormat(a));
       
        if(tinhTongHoaDonBanDuoc(ngayBatDauThongKe, ngayKetThucThongKe)==false){
            JOptionPane.showMessageDialog(this, "TỒNG HÓA ĐƠN RỖNG !");
        }
    }//GEN-LAST:event_btnXemDoanhThuActionPerformed

    private void btnBieuDoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBieuDoActionPerformed
        // TODO add your handling code here:
        new BieuDoThongKe().setVisible(true);
    }//GEN-LAST:event_btnBieuDoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBieuDo;
    private javax.swing.JButton btnThongKeManager;
    private javax.swing.JButton btnXemDoanhThu;
    private javax.swing.JComboBox<String> cbNhanVienThongKe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tblThongKe;
    private javax.swing.JTextField txtLoiNhuan;
    private javax.swing.JTextField txtNgayBatDauThongKe;
    private javax.swing.JTextField txtNgayKetThucThongKe;
    private javax.swing.JTextField txtTongHoaDonBanDuoc;
    private javax.swing.JTextField txtTongTienBanDuoc;
    private javax.swing.JTextField txtTongTienNhapHang;
    // End of variables declaration//GEN-END:variables

    

    
}
