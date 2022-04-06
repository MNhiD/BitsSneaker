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
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;

public class QuanLyThongKeController {

    private ThongKeService thongKeService = null;

    public QuanLyThongKeController() {
        this.thongKeService = (ThongKeService) new ThongKeServiceImpl() {} ;
    }

    public void setDataToChart1(JPanel jpnItem) {
        List<DoanhThuBean> listItem = thongKeService.getListByDoanhThu();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (listItem != null) {
            for (DoanhThuBean item : listItem) {
                dataset.addValue(item.getDoanhThu(), "Doanh thu", item.getThoiGian());
                dataset.addValue(item.getBanRa(), "Nhập Hàng", item.getThoiGian());
            }
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ thống kê doanh thu".toUpperCase(),
                "Thời gian", "Doanh thu",
                dataset, PlotOrientation.VERTICAL, true, true, false);
        

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 321));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }

//    public void setDataToChart2(JPanel jpnItem) {
//        List<KhoaHocBean> listItem = thongKeService.getListByKhoaHoc();
//
//        TaskSeriesCollection ds = new TaskSeriesCollection();
//        JFreeChart ganttChart = ChartFactory.createGanttChart(
//                "BIỂU ĐỒ THEO DÕI TÌNH TRẠNG KHÓA HỌC",
//                "Khóa học", "Thời gian", ds, true, false, false
//        );
//
//        TaskSeries taskSeries;
//        Task task;
//
//        if (listItem != null) {
//            for (KhoaHocBean item : listItem) {
//                taskSeries = new TaskSeries(item.getTen_khoa_hoc());
//                task = new Task(item.getTen_khoa_hoc(), new SimpleTimePeriod(item.getNgay_bat_dau(), item.getNgay_ket_thuc()));
//                taskSeries.add(task);
//                ds.add(taskSeries);
//            }
//        }
//
//        ChartPanel chartPanel = new ChartPanel(ganttChart);
//        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 321));
//
//        jpnItem.removeAll();
//        jpnItem.setLayout(new CardLayout());
//        jpnItem.add(chartPanel);
//        jpnItem.validate();
//        jpnItem.repaint();
//    }

}
