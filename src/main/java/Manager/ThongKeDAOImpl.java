/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

/**
 *
 * @author vntvn
 */
import Database.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



   public abstract class ThongKeDAOImpl implements ThongKeDAO{
       
        private DatabaseManager databaseManager = new DatabaseManager("BitSneaker");
       
        @Override
        public List<DoanhThuBean> getListByDoanhThu() {
        
//        String sql = "select 'TimeCreate'=CAST (SELLORDER.TimeCreate AS date),'DoanhThu'=SUM(SELLDETAILS.Amount*(PRICE.SellPrice*( CAST(SELLDETAILS.Sale AS float) / CAST(100 AS FLOAT) ))) from SELLDETAILS, SELLORDER, PRICE where SELLDETAILS.SellID=SELLORDER.SellID and SELLDETAILS.SneakerID=PRICE.SneakerID GROUP BY CAST(SELLORDER.TimeCreate AS date)";
        String sql = "SELECT SELL.TimeCreate, DoanhThu, BanRa FROM (SELECT 'TimeCreate'=CAST (SELLORDER.TimeCreate AS date), 'DoanhThu'=SUM(SELLDETAILS.Amount*(PRICE.SellPrice*( CAST(SELLDETAILS.Sale AS float) / CAST(100 AS FLOAT) ))) FROM SELLDETAILS, SELLORDER, PRICE WHERE SELLDETAILS.SellID=SELLORDER.SellID and SELLDETAILS.SneakerID=PRICE.SneakerID AND SELLORDER.Status='paid' GROUP BY CAST(SELLORDER.TimeCreate AS date)) AS SELL FULL OUTER JOIN (SELECT 'OrderTime'=CAST(IMPORTORDER.OrderTime AS date), 'BanRa'=SUM(IMPORTDETAILS.Amount*IMPORTDETAILS.Price) FROM IMPORTORDER, IMPORTDETAILS WHERE IMPORTORDER.ImportID = IMPORTDETAILS.ImportID GROUP BY CAST(IMPORTORDER.OrderTime AS date)) AS IMPORT ON IMPORT.OrderTime=SELL.TimeCreate ORDER BY SELL.TimeCreate";
        List<DoanhThuBean> list = new ArrayList<>();
        try {
            ResultSet rs = databaseManager.queryData(sql);
            while (rs.next()) {
                DoanhThuBean doanhThuBean = new DoanhThuBean();
                doanhThuBean.setThoiGian(rs.getString("TimeCreate"));
                doanhThuBean.setDoanhThu(rs.getFloat("DoanhThu"));
                doanhThuBean.setBanRa(rs.getFloat("BanRa"));
                list.add(doanhThuBean);
             }
            
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



       
   }