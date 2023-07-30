/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Admin
 */
public class MatHang {
    private String maMH;
    private String tenMH;
    private float gia;
    private int soLuong;
    private String maDM;
    private String maDVT;
    private String maNCC;

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public String getTenMH() {
        return tenMH;
    }

    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getMaDM() {
        return maDM;
    }

    public void setMaDM(String maDM) {
        this.maDM = maDM;
    }

    public String getMaDVT() {
        return maDVT;
    }

    public void setMaDVT(String maDVT) {
        this.maDVT = maDVT;
    }

    public MatHang() {
    }

    public MatHang(String maMH, String tenMH, float gia, int soLuong, String maDM, String maDVT) {
        this.maMH = maMH;
        this.tenMH = tenMH;
        this.gia = gia;
        this.soLuong = soLuong;
        this.maDM = maDM;
        this.maDVT = maDVT;
    }
    
    public MatHang(String maMH, String tenMH, String maNCC) {
        this.maMH = maMH;
        this.tenMH = tenMH;
        this.maNCC = maNCC;
    }
    
    public MatHang(String maMH, String tenMH, float gia, int soLuong, String maDM, String maDVT, String maNCC) {
        this.maMH = maMH;
        this.tenMH = tenMH;
        this.gia = gia;
        this.soLuong = soLuong;
        this.maDM = maDM;
        this.maDVT = maDVT;
        this.maNCC = maNCC;
    }
    
    @Override
    public String toString() {
        return this.tenMH;
    }
}
