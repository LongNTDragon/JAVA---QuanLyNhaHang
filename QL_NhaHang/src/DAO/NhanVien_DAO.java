/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.NhanVien;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kylet
 */
public class NhanVien_DAO {
    NhaHang_DAO dao = new NhaHang_DAO();
    private Connection connection = dao.getConnection();
    
    ResultSet resultSet = null;
    CallableStatement callableStatement = null;
    public String Auto_Increasement_MaNV()
    {
        try {
            callableStatement = connection.prepareCall("{call sp_SelectLastMaNV}");
            resultSet = callableStatement.executeQuery();
            while(resultSet.next())
            {
                return "NV"+String.format("%04d" ,resultSet.getInt(1)+1);
            }
            return "NV0001";
        } catch (SQLException ex) {
            Logger.getLogger(NhanVien_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public boolean validate_PhoneNumber(String Number,String ID)
    {
         try {
            callableStatement = connection.prepareCall("{call sp_SelectNVByNumber(?,?)}");
            callableStatement.setString(1,Number);
            callableStatement.setString(2,ID);
            resultSet = callableStatement.executeQuery();
            while(resultSet.next())
                return false;
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(NhanVien_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
     public boolean validate_ID(String ID)
    {
         try {
            callableStatement = connection.prepareCall("{call sp_SelectNVByMaNV(?)}");
            callableStatement.setString(1,ID);
            resultSet = callableStatement.executeQuery();
             
            while(resultSet.next())
            {
                System.out.println(resultSet.getString(1));
                return false;
                
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(NhanVien_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
     public ResultSet findNV(String info)
    {
        try {
            callableStatement = connection.prepareCall("{call sp_FindNV (?)}");
            callableStatement.setString(1, info);
            
            resultSet = callableStatement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHang_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return resultSet;
    }
    
    public int insertNV(NhanVien nv)
    {
        int result = 0;
        try {
            callableStatement = connection.prepareCall("{call sp_InsertNV(?, ?, ?,?)}");
            callableStatement.setString(1, nv.getMaNV());
            callableStatement.setString(2, nv.getTenNV());
            callableStatement.setString(3, nv.getSDT());
            callableStatement.setString(4, nv.getChucVu());
            result = callableStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHang_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int updateNV(NhanVien nv)
    {
        int result = 0;
        try {
            callableStatement = connection.prepareCall("{call sp_UpdateNV(?, ?, ?,?)}");
            callableStatement.setString(1, nv.getMaNV());
            callableStatement.setString(2, nv.getTenNV());
            callableStatement.setString(3, nv.getSDT());
            callableStatement.setString(4, nv.getChucVu());
            result = callableStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHang_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public int deleteNV(String ID)
    {
        int result = 0;
        try {
            callableStatement = connection.prepareCall("{call sp_DeleteNV(?)}");
            callableStatement.setString(1, ID);
            
            result = callableStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVien_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
