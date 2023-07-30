/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Admin
 */
public class DanhMucHang {
    private String maDM;
    private String tenDM;

    public String getMaDM() {
        return maDM;
    }

    public void setMaDM(String maDM) {
        this.maDM = maDM;
    }

    public String getTenDM() {
        return tenDM;
    }

    public void setTenDM(String tenDM) {
        this.tenDM = tenDM;
    }

    public DanhMucHang() {
    }

    public DanhMucHang(String maDM, String tenDM) {
        this.maDM = maDM;
        this.tenDM = tenDM;
    }

    @Override
    public String toString() {
        return this.tenDM;
    }
}
