/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class PhieuNhan_DAO {
    NhaHang_DAO dao = new NhaHang_DAO();
    CallableStatement callableStatement = null;
    int result = 0;
    
    private Connection connection = dao.getConnection();
    
    public Connection getConnection() {
        return connection;
    }

    public int insertPNhan(String maPNhan, String maNV, String maPNhap)
    {
        try {
            callableStatement = connection.prepareCall("{call sp_InsertPNHAN(?, ?, ?)}");
            callableStatement.setString(1, maPNhan);
            callableStatement.setString(2, maNV);
            callableStatement.setString(3, maPNhap);
            
            result = callableStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int deletePNhan(String maPNhan)
    {
        try {
            callableStatement = connection.prepareCall("{call sp_DeletePNHAN(?)}");
            callableStatement.setString(1, maPNhan);
            
            result = callableStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int insertCT_PNhan(String maPNhan, String maMH, int sLuong, float gia)
    {
        try {
            callableStatement = connection.prepareCall("{call sp_InsertCT_PNHAN(?, ?, ?, ?)}");
            callableStatement.setString(1, maPNhan);
            callableStatement.setString(2, maMH);
            callableStatement.setInt(3, sLuong);
            callableStatement.setFloat(4, gia);
            
            result = callableStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int updateCT_PNhan(String maPNhan, String maMH, int sLuong, float gia)
    {
        try {
            callableStatement = connection.prepareCall("{call sp_UpdateCT_PNHAN(?, ?, ?, ?)}");
            callableStatement.setString(1, maPNhan);
            callableStatement.setString(2, maMH);
            callableStatement.setInt(3, sLuong);
            callableStatement.setFloat(4, gia);
            
            result = callableStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
