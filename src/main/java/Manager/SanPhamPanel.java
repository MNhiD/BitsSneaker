/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Database.DatabaseManager;
import Tools.FormatText;
import Tools.ImageController;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vntvn
 */
public class SanPhamPanel extends javax.swing.JPanel {
    
    DatabaseManager database = new DatabaseManager("BitSneaker");
    
    private String fileName;

    public void layDSSanPham() {
        try {
            DefaultTableModel dtm=(DefaultTableModel)tblDSSanPham.getModel();
            dtm.setNumRows(0);
            Vector vt;
//            Connection ketNoi= KetNoiCSDL.layKetNoi();
            String sql="select SNEAKER.*,PRICE.SellPrice from PRICE inner join SNEAKER ON SNEAKER.SneakerID=PRICE.SneakerID";
//            PreparedStatement ps= ketNoi.prepareStatement(sql);
//            ResultSet rs=ps.executeQuery();
            ResultSet rs = database.queryData(sql);
            while(rs.next()){
                vt=new Vector();
                vt.add(rs.getString("SneakerID"));
                vt.add(rs.getString("Image"));
                vt.add(rs.getString("Name"));
                vt.add(rs.getString("Brand"));
                vt.add(rs.getString("Color"));
                vt.add(rs.getString("Size"));
                vt.add(rs.getString("Amount"));
                vt.add(rs.getString("Unit"));
                vt.add(rs.getString("Category"));
                vt.add(rs.getString("Description"));
                vt.add(FormatText.covertCurrentcyFormat(rs.getLong("SellPrice")));
               
                dtm.addRow(vt);
                
                tblDSSanPham.setModel(dtm);
                        } 
//            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public int timSanPham(String name,String category){
       int check = 0;
//       Connection ketNoi=KetNoiCSDL.layKetNoi();
       DefaultTableModel dtm=(DefaultTableModel)tblDSSanPham.getModel();
       dtm.setNumRows(0);
       if(name.length()!=0 &&category.length()==0){
        String sql="SELECT SNEAKER.*,PRICE.SellPrice FROM PRICE INNER JOIN SNEAKER ON SNEAKER.SneakerID=PRICE.SneakerID AND Name LIKE N'"+ name+"%'";
        try {
//            Statement ps=ketNoi.createStatement();
//            ResultSet rs=ps.executeQuery(sql);
            ResultSet rs = database.queryData(sql);
            
                while(rs.next()){
                Vector vt = new Vector();
                vt.add(rs.getString("SneakerID"));
                vt.add(rs.getString("Image"));
                vt.add(rs.getString("Name"));
                vt.add(rs.getString("Brand"));
                vt.add(rs.getString("Color"));
                vt.add(rs.getString("Size"));
                vt.add(rs.getString("Amount"));
                vt.add(rs.getString("Unit"));
                vt.add(rs.getString("Category"));
                vt.add(rs.getString("Description"));
                vt.add(FormatText.covertCurrentcyFormat(rs.getLong("SellPrice")));
                
                dtm.addRow(vt);
                
                tblDSSanPham.setModel(dtm);                       } 
//            ps.close();
            rs.close();
            check=1;
            
            //ps.setString(2, "");
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
       else if(category.length()!=0&&name.length()==0){
           String sql="SELECT SNEAKER.*,PRICE.SellPrice FROM PRICE INNER JOIN SNEAKER ON SNEAKER.SneakerID=PRICE.SneakerID AND Category LIKE N'"+ category+"%'";
        try {
//            Statement ps=ketNoi.createStatement();
//            ResultSet rs=ps.executeQuery(sql);
            ResultSet rs = database.queryData(sql);
            
                while(rs.next()){
                Vector vt = new Vector();
                vt.add(rs.getString("SneakerID"));
                vt.add(rs.getString("Image"));
                vt.add(rs.getString("Name"));
                vt.add(rs.getString("Brand"));
                vt.add(rs.getString("Color"));
                vt.add(rs.getString("Size"));
                vt.add(rs.getString("Amount"));
                vt.add(rs.getString("Unit"));
                vt.add(rs.getString("Category"));
                vt.add(rs.getString("Description"));
                vt.add(FormatText.covertCurrentcyFormat(rs.getLong("SellPrice")));
                dtm.addRow(vt);
                
                tblDSSanPham.setModel(dtm);                       } 
//            ps.close();
            rs.close();
                check=1;
            
            //ps.setString(2, "");
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
       else {
           String sql="SELECT SNEAKER.*,PRICE.SellPrice FROM PRICE INNER JOIN SNEAKER ON SNEAKER.SneakerID=PRICE.SneakerID AND Name LIKE N'"+ name+"%' AND Category LIKE N'"+ category+"%'";
        try {
//            Statement ps=ketNoi.createStatement();
//            ResultSet rs=ps.executeQuery(sql);
            ResultSet rs = database.queryData(sql);
            
                while(rs.next()){
                Vector vt = new Vector();
                vt.add(rs.getString("SneakerID"));
                vt.add(rs.getString("Image"));
                vt.add(rs.getString("Name"));
                vt.add(rs.getString("Brand"));
                vt.add(rs.getString("Color"));
                vt.add(rs.getString("Size"));
                vt.add(rs.getString("Amount"));
                vt.add(rs.getString("Unit"));
                vt.add(rs.getString("Category"));
                vt.add(rs.getString("Description"));
                vt.add(FormatText.covertCurrentcyFormat(rs.getLong("SellPrice")));
                dtm.addRow(vt);
                
                tblDSSanPham.setModel(dtm);                       } 
//            ps.close();
            rs.close();
                check=1;
            
            //ps.setString(2, "");
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
       return check;
   }
    public int tonTaiSanPham(String sneakerID){
        int check = 0;
//       Connection ketNoi=KetNoiCSDL.layKetNoi();    
             String sql="SELECT * FROM SNEAKER WHERE SneakerID='"+sneakerID+"'";
        try {
//            Statement ps=ketNoi.createStatement();
//            ResultSet rs=ps.executeQuery(sql);   
            ResultSet rs = database.queryData(sql);
            if(rs.next()){
            check=1;}}
        catch (SQLException ex) {
            Logger.getLogger(SanPhamPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
     }
      public void themSanPham(String sneakerID, String image, String name, String brand, String color, String size, String amount, String unit,String category,String description,String price){
//        Connection ketNoi=KetNoiCSDL.layKetNoi();       
        
        String sql="insert into SNEAKER (SneakerID ,Image ,Name,Brand,Color,Size,Amount,Unit,Category,Description) values('"+sneakerID+"','"+image+"',N'"+name+"','"+brand+"','"+color+"','"+size+"','"+amount+"',N'"+unit+"','"+category+"',N'"+description+"')";
        String sqlPrice="insert into PRICE(SneakerID ,SellPrice) values('"+sneakerID+"','"+price+"')";
//        try {
            
//            Statement ps=ketNoi.createStatement();
//            ps.executeUpdate(sql);
//            Statement psPrice=ketNoi.createStatement();
//            psPrice.executeUpdate(sqlPrice);
            database.queryUpdate(sql);
            database.queryUpdate(sqlPrice);
            
            //ps.setString(2, "");
//        } catch (SQLException ex) {
//            Logger.getLogger(SanPhamPanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
      
    public void xoaSanPham(String sneakerID){
//        Connection ketNoi=KetNoiCSDL.layKetNoi();
        String sql="delete from SNEAKER where SneakerID='"+sneakerID+"'";
        String sqlPrice="delete from PRICE where SneakerID='"+sneakerID+"'";
//        Statement ps;
//        Statement psPrice;
        
//        try {
//            psPrice=ketNoi.createStatement();
//            psPrice.executeUpdate(sqlPrice);
//            ps = ketNoi.createStatement();
//            ps.executeUpdate(sql);
//            
        database.queryUpdate(sqlPrice);
        database.queryUpdate(sql);
//        } catch (SQLException ex) {
//            Logger.getLogger(SanPhamPanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
    }
    public void suaThongTinSanPham(String sneakerID, String image, String name, String brand, String color, String size, String amount, String unit,String category,String description,String price) {
//        try {
//            Connection ketNoi=KetNoiCSDL.layKetNoi();
        String sql="update SNEAKER set Name=N'"+name+"',Image='"+image+"',Brand='"+brand+"',Color='"+color+"',Size='"+size+"',Amount='"+amount+"',Unit=N'"+unit+"',Category='"+category+"',Description=N'"+description+"' where SneakerID='"+sneakerID+"'";
        String sqlPrice="update PRICE set TimeUpdate='"+lbNgayCapNhat.getText()+"',SellPrice='"+price+"' where SneakerID='"+sneakerID+"'";
        
        database.queryUpdate(sql);
        database.queryUpdate(sqlPrice);
//            PreparedStatement ps=ketNoi.prepareStatement(sql);
//            ps.executeUpdate();
//            PreparedStatement psPrice=ketNoi.prepareStatement(sqlPrice);
//            psPrice.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(SanPhamPanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
    }
    public SanPhamPanel() {
        initComponents();
        layDSSanPham();
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());       
        lbNgayCapNhat.setText(timeStamp);
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
        jLabel7 = new javax.swing.JLabel();
        txtTimTenSanPham = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTimLoaiSanPham = new javax.swing.JTextField();
        btnTimKiemSanPham = new javax.swing.JButton();
        btnReloadSanPham = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lbNgayCapNhat = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDSSanPham = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtMaSanPham = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTenSanPham = new javax.swing.JTextField();
        txtNhanHieu = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtKichCoSanPham = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtSoLuongSanPham = new javax.swing.JTextField();
        txtDonViSanPham = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtPhanLoaiSanPham = new javax.swing.JTextField();
        txtMauSanPham = new javax.swing.JTextField();
        btnSuaSanPham = new javax.swing.JButton();
        btnThemSanPham = new javax.swing.JButton();
        btnXoaSanPham = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        btnThemHinhAnh = new javax.swing.JButton();
        txtHinhAnh = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        jLabel17 = new javax.swing.JLabel();
        txtGiaSanPham = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        lbHinhAnhSanPham = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1110, 770));

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1000, 700));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setText("T??m ki???m");

        jLabel7.setText("Lo???i s???n ph???m");

        txtTimTenSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimTenSanPhamActionPerformed(evt);
            }
        });

        jLabel8.setText("T??n s???n ph???m");

        txtTimLoaiSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimLoaiSanPhamActionPerformed(evt);
            }
        });

        btnTimKiemSanPham.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnTimKiemSanPham.setIcon(new javax.swing.ImageIcon("src//main//java//res//iconManager//loupe.png"));
        btnTimKiemSanPham.setText("T??m ki???m");
        btnTimKiemSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemSanPhamActionPerformed(evt);
            }
        });

        btnReloadSanPham.setIcon(new javax.swing.ImageIcon("src//main//java//res//iconManager//reload.png"));
        btnReloadSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadSanPhamActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 2, 13)); // NOI18N
        jLabel2.setText("Ng??y c???p nh???t:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTimTenSanPham)
                            .addComponent(txtTimLoaiSanPham)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(btnTimKiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnReloadSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lbNgayCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(7, 7, 7))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTimLoaiSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTimKiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnReloadSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(lbNgayCapNhat, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh s??ch s???n ph???m", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        tblDSSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "M?? s???n ph???m", "H??nh ???nh", "T??n s???n ph???m", "Nh??n hi???u", "M??u", "K??ch c???", "S??? l?????ng", "????n v???", "Ph??n lo???i", "M?? t???", "Gi?? ti???n"
            }
        ));
        tblDSSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDSSanPham);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Th??ng tin s???n ph???m", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        jLabel4.setText("M?? s???n ph???m:");
        jLabel4.setPreferredSize(new java.awt.Dimension(68, 30));

        txtMaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSanPhamActionPerformed(evt);
            }
        });

        jLabel9.setText("T??n s???n ph???m:");

        txtTenSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSanPhamActionPerformed(evt);
            }
        });

        jLabel10.setText("Nh??n hi???u:");

        jLabel11.setText("M??u");

        jLabel12.setText("K??ch c???");

        jLabel13.setText("S??? l?????ng:");

        txtSoLuongSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongSanPhamActionPerformed(evt);
            }
        });

        txtDonViSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDonViSanPhamActionPerformed(evt);
            }
        });

        jLabel14.setText("????n v???:");

        jLabel15.setText("Ph??n lo???i:");

        txtPhanLoaiSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhanLoaiSanPhamActionPerformed(evt);
            }
        });

        btnSuaSanPham.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnSuaSanPham.setIcon(new javax.swing.ImageIcon("src//main//java//res//iconManager//edit.png"));
        btnSuaSanPham.setText("S???a");
        btnSuaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSanPhamActionPerformed(evt);
            }
        });

        btnThemSanPham.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnThemSanPham.setIcon(new javax.swing.ImageIcon("src//main//java//res//iconManager//add-file.png"));
        btnThemSanPham.setText("Th??m");
        btnThemSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSanPhamActionPerformed(evt);
            }
        });

        btnXoaSanPham.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnXoaSanPham.setIcon(new javax.swing.ImageIcon("src//main//java//res//iconManager//remove.png"));
        btnXoaSanPham.setText("X??a");
        btnXoaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSanPhamActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel16.setText("Ch???c n??ng");

        btnThemHinhAnh.setIcon(new javax.swing.ImageIcon("src//main//java//res//iconManager//add-image.png"));
        btnThemHinhAnh.setText("H??nh ???nh");
        btnThemHinhAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemHinhAnhActionPerformed(evt);
            }
        });

        txtHinhAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHinhAnhActionPerformed(evt);
            }
        });

        jLabel5.setText("M?? t???");

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane2.setViewportView(txtMoTa);

        jLabel17.setText("Gi?? ti???n:");

        txtGiaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaSanPhamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnThemHinhAnh))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtHinhAnh)
                                    .addComponent(txtPhanLoaiSanPham)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jScrollPane2))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtDonViSanPham)
                                            .addComponent(txtSoLuongSanPham)))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addComponent(txtKichCoSanPham))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMauSanPham)
                                    .addComponent(txtNhanHieu)
                                    .addComponent(txtTenSanPham)
                                    .addComponent(txtMaSanPham)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(txtGiaSanPham)))
                        .addGap(65, 65, 65))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnThemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addComponent(btnXoaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btnSuaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNhanHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMauSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtKichCoSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSoLuongSanPham)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDonViSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPhanLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemHinhAnh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                        .addGap(16, 16, 16))
                    .addComponent(txtGiaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "H??nh ???nh", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbHinhAnhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbHinhAnhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("S???n ph???m", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1092, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimTenSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimTenSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimTenSanPhamActionPerformed

    private void txtTenSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSanPhamActionPerformed

    private void txtDonViSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDonViSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDonViSanPhamActionPerformed

    private void btnSuaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSanPhamActionPerformed
        // TODO add your handling code here:
          DefaultTableModel dtm=(DefaultTableModel)tblDSSanPham.getModel();
        String s;
        s = (String) dtm.getValueAt(tblDSSanPham.getSelectedRow(),0);
        String sneakerID=txtMaSanPham.getText();
        String image=txtHinhAnh.getText();
        String name=txtTenSanPham.getText();
        String brand=txtNhanHieu.getText();
        String color=txtMauSanPham.getText();
        String size=txtKichCoSanPham.getText();
        String amount=txtSoLuongSanPham.getText();
        String unit=txtDonViSanPham.getText();
        String category=txtPhanLoaiSanPham.getText();
        String description=txtMoTa.getText();
        String price=txtGiaSanPham.getText();
        if(sneakerID.length()==0){
            JOptionPane.showMessageDialog(this, "H??y ch???n s???n ph???m mu???n s???a th??ng tin");
        }
        else {
            int luaChon=JOptionPane.showConfirmDialog(this, "B???n c?? mu???n s???a th??ng tin s???n ph???m?");
            if(JOptionPane.YES_OPTION==luaChon){
                suaThongTinSanPham(sneakerID, image, name, brand, color, size, amount, unit, category, description,price);
                JOptionPane.showMessageDialog(this, "S???a th??nh c??ng!");

                this.layDSSanPham();
                String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());       
                lbNgayCapNhat.setText(timeStamp);

            }
        }
    }//GEN-LAST:event_btnSuaSanPhamActionPerformed

    private void txtTimLoaiSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimLoaiSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimLoaiSanPhamActionPerformed

    private void btnXoaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSanPhamActionPerformed
        // TODO add your handling code here:
        String maSanPham=txtMaSanPham.getText();
        if(maSanPham.length()==0){
            JOptionPane.showMessageDialog(this, "H??y ch???n s???n ph???m ????? x??a!");
        }
        else {
            DefaultTableModel dtm=(DefaultTableModel) tblDSSanPham.getModel();
            String s;
            s = (String) dtm.getValueAt(tblDSSanPham.getSelectedRow(), 0);
         
            int luaChon=JOptionPane.showConfirmDialog(this, "B???n c?? mu???n x??a s???n ph???m?");
            if(JOptionPane.YES_OPTION==luaChon){
                xoaSanPham(maSanPham);
                this.layDSSanPham();
            }
        }
        txtDonViSanPham.setText("");
        txtGiaSanPham.setText("");
        txtHinhAnh.setText("");
        txtKichCoSanPham.setText("");
        txtMaSanPham.setText("");
        txtMauSanPham.setText("");
        txtMoTa.setText("");
        txtNhanHieu.setText("");
        txtPhanLoaiSanPham.setText("");
        txtSoLuongSanPham.setText("");
        txtTenSanPham.setText("");
        lbHinhAnhSanPham.setIcon(null);
    }//GEN-LAST:event_btnXoaSanPhamActionPerformed

    private void btnThemSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSanPhamActionPerformed
        // TODO add your handling code here:
        String chuanHoaSo="\\D";
//        String chuanHoaEmail= " ^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        String chuanHoaNgay="^(0?[1-9]|[12][0-9]|3[01])[./](0?[1-9]|1[012])[./](19|20)\\d\\d$";
        String sneakerID=txtMaSanPham.getText();
        String image=txtHinhAnh.getText();
        String name=txtTenSanPham.getText();
        String brand=txtNhanHieu.getText();
        String color=txtMauSanPham.getText();
        String size=txtKichCoSanPham.getText();
        String amount=txtSoLuongSanPham.getText();
        String unit=txtDonViSanPham.getText();
        String category=txtPhanLoaiSanPham.getText();
        String description=txtMoTa.getText();
        String price=txtGiaSanPham.getText();
        
        if(sneakerID.length()==0){
            JOptionPane.showMessageDialog(this, "M?? s???n ph???m kh??ng ???????c ????? tr???ng!");
        }
        else if(tonTaiSanPham(sneakerID)==1){
            JOptionPane.showMessageDialog(this, "M?? s???n ph???m n??y ???? t???n t???i trong danh s??ch!");
        }
        else if(name.length()==0){
            JOptionPane.showMessageDialog(this, "T??n s???n ph???m kh??ng ???????c ????? tr???ng!");
        }
        else if(brand.length()==0){
            JOptionPane.showMessageDialog(this, "Nh??n hi???u kh??ng ???????c ????? tr???ng!");
        }
        else if(color.length()==0){
            JOptionPane.showMessageDialog(this, "M??u s???n ph???m kh??ng ???????c ????? tr???ng!");
        }
        else if(size.length()==0){
            JOptionPane.showMessageDialog(this, "K??ch c??? s???n ph???m kh??ng ???????c ????? tr???ng!");
        }
        else if(size.matches(chuanHoaSo)==true){
            JOptionPane.showMessageDialog(this, "K??ch c??? s???n ph???m ph???i l?? s???!");
        }
        else if(amount.length()==0){
            JOptionPane.showMessageDialog(this, "S??? l?????ng s???n ph???m kh??ng ???????c ????? tr???ng!");
        }
        else if(amount.matches(chuanHoaSo)==true){
            JOptionPane.showMessageDialog(this, "S??? l?????ng s???n ph???m ph???i l?? s???!");
        }
        else if(unit.length()==0) {
            
            JOptionPane.showMessageDialog(this, "????n v??? s???n ph???m kh??ng ???????c ????? tr???ng!");
        }
        else if(category.length()==0) {
            
            JOptionPane.showMessageDialog(this, "Ph??n lo???i s???n ph???m kh??ng ???????c ????? tr???ng!");
        }
        else if(price.length()==0) {
            
            JOptionPane.showMessageDialog(this, "Gi?? s???n ph???m kh??ng ???????c ????? tr???ng!");
        }
//        else if(accountID.matches(chuanHoaEmail)==false){
//            JOptionPane.showMessageDialog(this, "Email kh??ng h???p l???");
//        }
        else {
            File source = new File(fileName);
            File dest = new File("src//main//java//res//goods//"+image);

            try {
                ImageController.copyDirectory(source, dest);
            } catch (IOException ex) {
                Logger.getLogger(SanPhamPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            themSanPham(sneakerID, image, name, brand, color, size, amount, unit, category, description,price);
            this.layDSSanPham();
            txtMaSanPham.setText("");
            txtHinhAnh.setText("");
            txtNhanHieu.setText("");
            txtTenSanPham.setText("");
            txtKichCoSanPham.setText("");
            txtSoLuongSanPham.setText("");
            txtDonViSanPham.setText("");
            txtMoTa.setText("");
            txtMauSanPham.setText("");
            txtPhanLoaiSanPham.setText("");
            txtGiaSanPham.setText("");
            String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());       
            lbNgayCapNhat.setText(timeStamp);
            
            
        }
    }//GEN-LAST:event_btnThemSanPhamActionPerformed
    
    private void txtHinhAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHinhAnhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHinhAnhActionPerformed

    private void txtMaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSanPhamActionPerformed

    private void txtPhanLoaiSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhanLoaiSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhanLoaiSanPhamActionPerformed

    private void txtSoLuongSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongSanPhamActionPerformed

    
    private void tblDSSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSSanPhamMouseClicked
        // TODO add your handling code here:
        DefaultTableModel dtm=(DefaultTableModel) tblDSSanPham.getModel();
         int luaChon=tblDSSanPham.getSelectedRow();
        txtMaSanPham.setText((String) dtm.getValueAt(luaChon, 0));
        txtHinhAnh.setText("src//main//java//res//goods//"+(String) dtm.getValueAt(luaChon, 1));
        txtTenSanPham.setText((String) dtm.getValueAt(luaChon, 2));
        txtNhanHieu.setText((String) dtm.getValueAt(luaChon, 3));
        txtMauSanPham.setText((String) dtm.getValueAt(luaChon, 4));
        txtKichCoSanPham.setText((String) dtm.getValueAt(luaChon, 5));
        txtSoLuongSanPham.setText((String) dtm.getValueAt(luaChon, 6));
        txtDonViSanPham.setText((String) dtm.getValueAt(luaChon, 7));
        txtPhanLoaiSanPham.setText((String)dtm.getValueAt(luaChon, 8));
        txtMoTa.setText((String)dtm.getValueAt(luaChon, 9));
        txtGiaSanPham.setText(FormatText.convertFormatTextToLong(dtm.getValueAt(luaChon, 10)+"")+"");
        //set h??nh s???n ph???m
        String path=txtHinhAnh.getText();
        ImageIcon icon=new ImageIcon(path);
        Image img=ImageController.getScaledImage(icon.getImage(), 250, 179);
        icon.setImage(img);
        lbHinhAnhSanPham.setIcon(icon);
        
        //l???y path h??nh ???nh
        String tmp=txtHinhAnh.getText();
        int viTriCatChuoi=tmp.lastIndexOf("//");
        System.out.println(viTriCatChuoi);       
        String s=tmp.substring(viTriCatChuoi+2);
        System.out.println(s);
        txtHinhAnh.setText(s);
        
    }//GEN-LAST:event_tblDSSanPhamMouseClicked

    private void btnReloadSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadSanPhamActionPerformed
        // TODO add your handling code here:
        this.layDSSanPham();
        txtTimTenSanPham.setText("");
        txtTimLoaiSanPham.setText("");
    }//GEN-LAST:event_btnReloadSanPhamActionPerformed

    private void btnTimKiemSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemSanPhamActionPerformed
        // TODO add your handling code here:
         String name=txtTimTenSanPham.getText();
        String category =txtTimLoaiSanPham.getText();
        if(name.length()==0 && category.length()==0){
            JOptionPane.showMessageDialog(this, "H??y nh???p th??ng tin s???n ph???m c???n t??m!");
        }
        else {
         DefaultTableModel dtm=(DefaultTableModel) tblDSSanPham.getModel();        
        if(timSanPham(name, category)==1){
            
        }
        else{
            JOptionPane.showMessageDialog(this, "Kh??ng t??m th???y s???n ph???m c???n t??m trong danh s??ch");
            this.layDSSanPham();
        
        }
        }
    }//GEN-LAST:event_btnTimKiemSanPhamActionPerformed

    private void btnThemHinhAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemHinhAnhActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser= new JFileChooser();
        chooser.showOpenDialog(null);
        File f=chooser.getSelectedFile();
        this.fileName=f.getPath();
        
//        txtHinhAnh.setText(fileName.split("[C:\\Users\\vntvn\\OneDrive\\M??y t??nh\\GiaoDien\\GiaoDien\\src\\icon\\]"));
        Image getAbsolutePath=null;
        ImageIcon icon=new ImageIcon(fileName);
        Image img=icon.getImage().getScaledInstance(lbHinhAnhSanPham.getWidth(), lbHinhAnhSanPham.getHeight(), Image.SCALE_SMOOTH);
        lbHinhAnhSanPham.setIcon(icon);
        int viTriCatChuoi=fileName.lastIndexOf('\\');
//        System.out.println(viTriCatChuoi);       
        String s=fileName.substring(viTriCatChuoi+1);
//        System.out.println(s);
        txtHinhAnh.setText(s);
        
    }//GEN-LAST:event_btnThemHinhAnhActionPerformed

    private void txtGiaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaSanPhamActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReloadSanPham;
    private javax.swing.JButton btnSuaSanPham;
    private javax.swing.JButton btnThemHinhAnh;
    private javax.swing.JButton btnThemSanPham;
    private javax.swing.JButton btnTimKiemSanPham;
    private javax.swing.JButton btnXoaSanPham;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbHinhAnhSanPham;
    private javax.swing.JLabel lbNgayCapNhat;
    private javax.swing.JTable tblDSSanPham;
    private javax.swing.JTextField txtDonViSanPham;
    private javax.swing.JTextField txtGiaSanPham;
    private javax.swing.JTextField txtHinhAnh;
    private javax.swing.JTextField txtKichCoSanPham;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextField txtMauSanPham;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtNhanHieu;
    private javax.swing.JTextField txtPhanLoaiSanPham;
    private javax.swing.JTextField txtSoLuongSanPham;
    private javax.swing.JTextField txtTenSanPham;
    private javax.swing.JTextField txtTimLoaiSanPham;
    private javax.swing.JTextField txtTimTenSanPham;
    // End of variables declaration//GEN-END:variables
}
