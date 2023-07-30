/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.KhachHang;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class KhachHang_DAO {
    NhaHang_DAO dao = new NhaHang_DAO();
    private Connection connection = dao.getConnection();
    
    ResultSet resultSet = null;
    CallableStatement callableStatement = null;
    
    public ResultSet findKH(String sdt)
    {
        try {
            callableStatement = connection.prepareCall("{call sp_FindKH (?)}");
            callableStatement.setString(1, sdt);
            
            resultSet = callableStatement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(NhaHang_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return resultSet;
    }
    
    public int insertKH(KhachHang kh)
    {
        int result = 0;
        try {
            callableStatement = connection.prepareCall("{call sp_InsertKH(?, ?, ?)}");
            callableStatement.setString(1, kh.getMaKH());
            callableStatement.setString(2, kh.getTenKH());
            callableStatement.setString(3, kh.getSDT());
            
            result = callableStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHang_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int updateKH(KhachHang kh)
    {
        int result = 0;
        try {
            callableStatement = connection.prepareCall("{call sp_UpdateKH(?, ?, ?)}");
            callableStatement.setString(1, kh.getMaKH());
            callableStatement.setString(2, kh.getTenKH());
            callableStatement.setString(3, kh.getSDT());
            
            result = callableStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHang_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int deleteKH(String ID)
    {
        int result = 0;
        try {
            callableStatement = connection.prepareCall("{call sp_DeleteKH(?)}");
            callableStatement.setString(1, ID);
            
            result = callableStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHang_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public String Auto_Increasement_MaKH()
    {
        try {
            callableStatement = connection.prepareCall("{call sp_SelectLastMaKH}");
            resultSet = callableStatement.executeQuery();
            while(resultSet.next())
            {
                return "KH"+String.format("%05d" ,resultSet.getInt(1)+1);
            }
            return "KH00001";
        } catch (SQLException ex) {
            Logger.getLogger(KhachHang_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean validate_PhoneNumber(String Number,String ID)
    {
         try {
            callableStatement = connection.prepareCall("{call sp_SelectKHByNumber(?,?)}");
            callableStatement.setString(1,Number);
            callableStatement.setString(2,ID);
            resultSet = callableStatement.executeQuery();
            while(resultSet.next())
                return false;
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(KhachHang_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
     public boolean validate_ID(String ID)
    {
         try {
            callableStatement = connection.prepareCall("{call sp_SelectKHByMaKH(?)}");
            callableStatement.setString(1,ID);
            resultSet = callableStatement.executeQuery();
             
            while(resultSet.next())
            {
                return false;
                
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(KhachHang_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
