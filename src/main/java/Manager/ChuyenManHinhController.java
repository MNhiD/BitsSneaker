/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.PopupMenu;
import java.util.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author vntvn
 */
public class ChuyenManHinhController {
    private JPanel root;
    private String kindSelected="";
    List<DanhMucBean> listItem=null;

    public JPanel getRoot() {
        return root;
    }

    public void setRoot(JPanel root) {
        this.root = root;
    }

    public String getKindSelected() {
        return kindSelected;
    }

    public void setKindSelected(String kindSelected) {
        this.kindSelected = kindSelected;
    }

    public List<DanhMucBean> getListItem() {
        return listItem;
    }

    public void setListItem(List<DanhMucBean> listItem) {
        this.listItem = listItem;
    }

    
    
        
    public ChuyenManHinhController(JPanel jpnRoot){
       this.root=jpnRoot;
    }
    
    public void setView(JPanel jpnItem, JLabel jlbItem) {
        kindSelected = "KhachHang";

//       jpnItem.setBackground(new Color(96));
//       jlbItem.setBackground(new Color(96));
//       JPanel node = new KhachHangPanel();
       root.removeAll();
       root.setLayout(new BorderLayout());
       root.add(new KhachHangPanel());
       root.validate();
       root.repaint();
}
    public void setEvent(List<DanhMucBean> listItem) {
     this.listItem = listItem;
     for (DanhMucBean item : listItem) {
           item.getJlb().addMouseListener(new LabelEvent(item.getKind(), item.getJpn(), item.getJlb()));
     }
    }


class LabelEvent implements MouseListener {

      private JPanel node;
      private String kind;

      private JPanel jpnItem;
      private JLabel jlbItem;

      public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
           this.kind = kind;
           this.jpnItem = jpnItem;
           this.jlbItem = jlbItem;
      }

      @Override
      public void mouseClicked(MouseEvent e) {
            switch (kind) {
                case "KhachHang":
                    node=new KhachHangPanel();
                    break;
                case "NhanVien":
                    node = new NhanVienPanel();
                    break;
                case "SanPham":
                    node = new SanPhamPanel();
                    break;
                case "HoaDon":
                    node = new HoaDonPanel();
                    break;
                case "NhaCungCap":
                    node = new NhaCungCapPanel();
                    break;
                case "ThongKe":
                    System.out.println("Thong ke");
                    node = new ThongKePanel();
                    break;
                // more
                default:
                    break;
           }
           root.removeAll();
           root.setLayout(new BorderLayout());
           root.add(node);
           root.validate();
           root.repaint();
//           setChangeBackgroud(kind);
//           jpnItem.setBackground(new Color(96));
      }

      @Override
      public void mousePressed(MouseEvent e) {
           kindSelected = kind;
           jpnItem.setBackground(Color.lightGray);
           jlbItem.setBackground(Color.lightGray);
      }

      @Override
      public void mouseReleased(MouseEvent e) {
          jpnItem.setBackground(Color.white);
          jlbItem.setBackground(Color.white);
      }

      @Override
      public void mouseEntered(MouseEvent e) {
          jpnItem.setBackground(Color.LIGHT_GRAY);
          jlbItem.setBackground(Color.LIGHT_GRAY);
      }

      @Override
      public void mouseExited(MouseEvent e) {
          if (!kindSelected.equalsIgnoreCase(kind)) {
                jpnItem.setBackground(Color.white);
                jlbItem.setBackground(Color.white);
          }
      }


        

        private void setChangeBackgroud(String kind) {
            throw new UnsupportedOperationException("Not supported ."); //To change body of generated methods, choose Tools | Templates.
        }

}

}