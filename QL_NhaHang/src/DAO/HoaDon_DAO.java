/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.HoaDon;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class HoaDon_DAO {
    NhaHang_DAO dao = new NhaHang_DAO();
    CallableStatement callableStatement = null;
    int result = 0;
    ResultSet resultSet = null;
    
    private Connection connection = dao.getConnection();
    
    public Connection getConnection() {
        return connection;
    }
    
    public ResultSet getSelectedMAHD(String maHD)
    {
         try {
            callableStatement = connection.prepareCall("{call sp_SelectHoaDonByMaHD (?)}");
            callableStatement.setString(1, maHD);
            
            resultSet = callableStatement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return resultSet;
    }
    
    public ResultSet getTongDoanhThu()
    {
        try {
            callableStatement = connection.prepareCall("exec sp_GetGrandTotal");
            
            resultSet = callableStatement.executeQuery();
            return resultSet;
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         return null;
    }
    
    public ResultSet getSelectedMHbyMAHD(String maHD)
    {
         try {
            callableStatement = connection.prepareCall("{call sp_SelectMHByMaHD (?)}");
            callableStatement.setString(1, maHD);
            
            resultSet = callableStatement.executeQuery();
            return resultSet;
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         return null;
    }
    
    public ResultSet findHD(String info)
    {
        try {
            callableStatement = connection.prepareCall("{call sp_FindHD (?)}");
            callableStatement.setString(1, info);
            
            resultSet = callableStatement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHang_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return resultSet;
    }

    public int insertHD(HoaDon hd, String tableKey)
    {
        try {
            callableStatement = connection.prepareCall("{call sp_InsertHD(?, ?, ?, ?)}");
            callableStatement.setString(1, hd.getMaHD());
            callableStatement.setString(2, hd.getMaNV());
            callableStatement.setString(3, hd.getMaKH());
            callableStatement.setString(4, tableKey);
            
            result = callableStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int updateHD(String maHD, float tongTien)
    {
        try {
            callableStatement = connection.prepareCall("{call sp_UpdateHD(?, ?)}");
            callableStatement.setString(1, maHD);
            callableStatement.setDouble(2, tongTien);
            
            result = callableStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int updateCTHD_Ban(String maHD, String maBanOld, String maBanNew)
    {
        try {
            callableStatement = connection.prepareCall("{call sp_UpdateCTHD_BAN (?, ?, ?)}");
            callableStatement.setString(1, maHD);
            callableStatement.setString(2, maBanOld);
            callableStatement.setString(3, maBanNew);
            
            result = callableStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int deleteCTHD_Ban(String maHD, String maBan)
    {
        try {
            callableStatement = connection.prepareCall("{call sp_DeleteCTHD_BAN (?, ?)}");
            callableStatement.setString(1, maHD);
            callableStatement.setString(2, maBan);
            
            result = callableStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int insertCTHD_MH(String maMH, String maHD, int soLuong)
    {
        try {
            callableStatement = connection.prepareCall("{call sp_InsertCTHD_MH (?, ?, ?)}");
            callableStatement.setString(1, maMH);
            callableStatement.setString(2, maHD);
            callableStatement.setInt(3, soLuong);
            
            result = callableStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int updateCTHD_MH(String maMH, String maHD, int soLuong)
    {
        try {
            callableStatement = connection.prepareCall("{call sp_UpdateCTHD_MH (?, ?, ?, ?)}");
            callableStatement.setString(1, maMH);
            callableStatement.setString(2, maHD);
            callableStatement.setInt(3, soLuong);
            callableStatement.setString(4, "");
            
            result = callableStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int deleteCTHD_MH(String maMH, String maHD)
    {
        try {
            callableStatement = connection.prepareCall("{call sp_DeleteCTHD_MH (?, ?)}");
            callableStatement.setString(1, maMH);
            callableStatement.setString(2, maHD);
            
            result = callableStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
