/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Admin
 */
public class Ban {
    private String maBan;
    private String tenBan;
    private int soGhe;
    private String tinhTrang;

    public String getMaBan() {
        return maBan;
    }

    public void setMaBan(String maBan) {
        this.maBan = maBan;
    }
    
    public String getTenBan() {
        return tenBan;
    }

    public void setTenBan(String tenBan) {
        this.tenBan = tenBan;
    }

    public int getSoGhe() {
        return soGhe;
    }

    public void setSoGhe(int soGhe) {
        this.soGhe = soGhe;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public Ban() {
    }

    public Ban(String maBan, String tenBan, int soGhe, String tinhTrang) {
        this.maBan = maBan;
        this.tenBan = tenBan;
        this.soGhe = soGhe;
        this.tinhTrang = tinhTrang;
    }
    
    public Ban(String maBan, String tenBan) {
        this.maBan = maBan;
        this.tenBan = tenBan;
    }

    @Override
    public String toString() {
        return getTenBan();
    }
}
