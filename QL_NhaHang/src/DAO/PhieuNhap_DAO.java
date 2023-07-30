package DAO;

import DTO.HoaDon;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class PhieuNhap_DAO {
    NhaHang_DAO dao = new NhaHang_DAO();
    CallableStatement callableStatement = null;
    int result = 0;
    
    private Connection connection = dao.getConnection();
    
    public Connection getConnection() {
        return connection;
    }

    public int insertPNH(String maP, String maNV)
    {
        try {
            callableStatement = connection.prepareCall("{call sp_InsertPNH(?, ?)}");
            callableStatement.setString(1, maP);
            callableStatement.setString(2, maNV);
            
            result = callableStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int deletePNH(String maP)
    {
        try {
            callableStatement = connection.prepareCall("{call sp_DeletePNH(?)}");
            callableStatement.setString(1, maP);
            
            result = callableStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int updatePNH(String maP)
    {
        try {
            callableStatement = connection.prepareCall("{call sp_UpdatePNH(?)}");
            callableStatement.setString(1, maP);
            
            result = callableStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int insertCT_PN(String maP, String maMH, int sLuong)
    {
        try {
            callableStatement = connection.prepareCall("{call sp_InsertCT_PN(?, ?, ?)}");
            callableStatement.setString(1, maP);
            callableStatement.setString(2, maMH);
            callableStatement.setInt(3, sLuong);
            
            result = callableStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int deleteCT_PN(String maP, String maMH)
    {
        try {
            callableStatement = connection.prepareCall("{call sp_DeleteCT_PN(?, ?)}");
            callableStatement.setString(1, maP);
            callableStatement.setString(2, maMH);
            
            result = callableStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int updateCT_PN(String maP, String maMH, int sLuong)
    {
        try {
            callableStatement = connection.prepareCall("{call sp_UpdateCT_PN(?, ?, ?)}");
            callableStatement.setString(1, maP);
            callableStatement.setString(2, maMH);
            callableStatement.setInt(3, sLuong);
            
            result = callableStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
