/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.MatHang;
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
public class MatHang_DAO {
    NhaHang_DAO dao = new NhaHang_DAO();
    private Connection connection = dao.getConnection();
    
    ResultSet resultSet = null;
    CallableStatement callableStatement = null;
    
    public String Auto_Increasement_MaMH()
    {
        try {
            callableStatement = connection.prepareCall("{call sp_SelectLastMaMH}");
            resultSet = callableStatement.executeQuery();
            while(resultSet.next())
            {
                return "MH" + String.format("%01d" ,resultSet.getInt(1) + 1);
            }
            return "MH01";
        } catch (SQLException ex) {
            Logger.getLogger(NhanVien_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ResultSet findMHByName(String info)
    {
        try {
            callableStatement = connection.prepareCall("{call sp_FindMH (?)}");
            callableStatement.setString(1, info);
            
            resultSet = callableStatement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(NhaHang_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return resultSet;
    }
    
    public ResultSet findMHByKey(String maMH)
    {
        try {
            callableStatement = connection.prepareCall("{call sp_FindMHByKey (?)}");
            callableStatement.setString(1, maMH);
            
            resultSet = callableStatement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(NhaHang_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return resultSet;
    }

    public int insertMH(MatHang mh)
    {
        int result = 0;
        try {
            callableStatement = connection.prepareCall("{call sp_InsertMH(?, ?, ?, ?, ?, ?, ?)}");
            callableStatement.setString(1, mh.getMaMH());
            callableStatement.setString(2, mh.getTenMH());
            callableStatement.setFloat(3, mh.getGia());
            callableStatement.setInt(4, mh.getSoLuong());
            callableStatement.setString(5, mh.getMaDM());
            callableStatement.setString(6, mh.getMaDVT());
            callableStatement.setString(7, mh.getMaNCC());
            
            result = callableStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHang_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int updateMH(MatHang mh)
    {
        int result = 0;
        try {
            callableStatement = connection.prepareCall("{call sp_UpdateMH(?, ?, ?, ?, ?, ?, ?)}");
            callableStatement.setString(1, mh.getMaMH());
            callableStatement.setString(2, mh.getTenMH());
            callableStatement.setFloat(3, mh.getGia());
            callableStatement.setInt(4, mh.getSoLuong());
            callableStatement.setString(5, mh.getMaDM());
            callableStatement.setString(6, mh.getMaDVT());
            callableStatement.setString(7, mh.getMaNCC());
            
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
            callableStatement = connection.prepareCall("{call sp_DeleteMH(?)}");
            callableStatement.setString(1, ID);
            
            result = callableStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHang_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
