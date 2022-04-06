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
import java.util.List;

public abstract class ThongKeServiceImpl implements ThongKeService {

    private ThongKeDAO thongKeDAO = null;

    public ThongKeServiceImpl() {
        this.thongKeDAO = (ThongKeDAO) new ThongKeDAOImpl() {};
    }

    @Override
    public List<DoanhThuBean> getListByDoanhThu() {
        return thongKeDAO.getListByDoanhThu();
    }

    
}