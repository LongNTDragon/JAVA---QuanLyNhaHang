/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class NhaHang_DAO {
    private Connection connection = null;

    public Connection getConnection() {
        return connection;
    }
    Statement statement = null;
    
    private static String db_server = "DESKTOP-VDRAQI5\\SQLEXPRESS";
    public static String db_user = "";
    public static String db_pass = "";
    private static String db_name = "QL_NhaHang";
    
    public NhaHang_DAO()
    {
        SQLServerDataSource db = new SQLServerDataSource();
        
        db.setServerName(db_server);
        db.setUser(db_user);
        db.setPassword(db_pass);
        db.setDatabaseName(db_name);
        db.setPortNumber(1433);
        db.setEncrypt(false);
        
        try {
            connection = db.getConnection();
        } catch (SQLServerException ex) {
            
        }
    }
    
    public static String checkConnect()
    {
        String result = "";
        SQLServerDataSource db = new SQLServerDataSource();
        
        db.setServerName(db_server);
        db.setUser(db_user);
        db.setPassword(db_pass);
        db.setDatabaseName(db_name);
        db.setPortNumber(1433);
        db.setEncrypt(false);
        
        try {
            if(db.getConnection() != null)
                result = "";
        } catch (SQLServerException ex) {
            result = ex.getMessage();
        }
        return result;
    }
    
    public String checkRole(String sql)
    {
        String result = "";
        ResultSet rs = null;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
        } catch (SQLException ex) {
            result = ex.getMessage();
        }
        return result;
    }
    
    public ResultSet selectData(String sql)
    {
        ResultSet rs = null;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
        } catch (SQLException ex) {
            
        }
        
        return rs;
    }
    
    public void createUser(String user, String pass)
    {
        try {
            ResultSet rs = statement.executeQuery("use master exec sp_addlogin '" + user + "', '" + pass + "' " +
                                       "use QL_NhaHang exec sp_adduser '" + user + "', '" + user + "'");
        } catch (SQLException ex) {
            
        }
    }
    
    public void addUserToRole(String user)
    {
        try {
            CallableStatement callableStatement = connection.prepareCall("{call sp_addrolemember(?, ?)}");
            callableStatement.setString(1, "NhanVien");
            callableStatement.setString(2, user);
            ResultSet rs = callableStatement.executeQuery();
        } catch (SQLException ex) {
           
        }
    }
    
    public void dropLogin(String user)
    {
        try {
            CallableStatement callableStatement = connection.prepareCall("{call sp_droplogin(?)}");
            callableStatement.setString(1, user);
            ResultSet rs = callableStatement.executeQuery();
        } catch (SQLException ex) {
           
        }
    }
    
    public void dropUser(String user)
    {
        try {
            CallableStatement callableStatement = connection.prepareCall("{call sp_dropuser(?)}");
            callableStatement.setString(1, user);
            ResultSet rs = callableStatement.executeQuery();
        } catch (SQLException ex) {
           
        }
    }
}
