/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author kylet
 */
public class NhanVien {
    private String maNV;
    private String tenNV;
    private String SDT;
    private String chucVu;

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getMaNV() {
        return maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public String getSDT() {
        return SDT;
    }

    public String getChucVu() {
        return chucVu;
    }

    public NhanVien(String maNV, String tenNV, String SDT, String chucVu) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.SDT = SDT;
        this.chucVu = chucVu;
    }
    public NhanVien()
    {
        
    }
}
