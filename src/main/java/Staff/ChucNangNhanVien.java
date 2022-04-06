/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Staff;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Dell
 */
public class ChucNangNhanVien {
    private String module;
    private JPanel jpn;
    private JLabel lbl;

    public ChucNangNhanVien() {
    }

    public ChucNangNhanVien(String module, JPanel jpn, JLabel lbl) {
        this.module = module;
        this.jpn = jpn;
        this.lbl = lbl;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public JPanel getJpn() {
        return jpn;
    }

    public void setJpn(JPanel jpn) {
        this.jpn = jpn;
    }

    public JLabel getLbl() {
        return lbl;
    }

    public void setLbl(JLabel lbl) {
        this.lbl = lbl;
    }
    
    
}
