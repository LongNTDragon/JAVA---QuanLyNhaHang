/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import DAO.HoaDon_DAO;
import DAO.NhaHang_DAO;
import DTO.Ban;
import DTO.DanhMucHang;
import DTO.MatHang;
import DTO.dtoStatic;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import ql_nhahang.DatBan;
import ql_nhahang.QL_NhanHang;
import ql_nhahang.QL_NhapHang;

/**
 *
 * @author tphan
 */
public class Main_QL extends javax.swing.JFrame implements ActionListener{

    /**
     * Creates new form Main_QL
     */
    NhaHang_DAO dao = new NhaHang_DAO();
    HoaDon_DAO daoHD = new HoaDon_DAO();
    
    StringBuilder sb = null;
    DefaultTableModel dfTableModel = new DefaultTableModel();
    
    public Main_QL() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        setActionForButton();
        setColorForPanel();
        
        loadCBoLoaiMon();
        loadCboBan();
        initTable();
    }
    
    private void initTable()
    {
        String[] colTitle = {"Tên món", "Số lượng", "Giá", "ĐVT"};
        
        dfTableModel.setColumnIdentifiers(colTitle);
        tblMonDaDung.setModel(dfTableModel);
    }

    private void setActionForButton() 
    {
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);
        btn5.addActionListener(this);
        btn6.addActionListener(this);
        btn7.addActionListener(this);
        btn8.addActionListener(this);
        btn9.addActionListener(this);
        btn10.addActionListener(this);
        btn11.addActionListener(this);
        btn12.addActionListener(this);
        btn13.addActionListener(this);
        btn14.addActionListener(this);
        btn15.addActionListener(this);
        btn16.addActionListener(this);
    }
    
    private void setColorForPanel() 
    {
        for(int i = 1; i <= 16; i++)
        {
            String str = "pnl" + i;
            String key = "B" + String.format("%02d", i);
            
            switch (str) {
                case "pnl1":
                    pnl1.setBackground(checkStatus(key));
                    break;
                case "pnl2":
                    pnl2.setBackground(checkStatus(key));
                    break;
                case "pnl3":
                    pnl3.setBackground(checkStatus(key));
                    break;
                case "pnl4":
                    pnl4.setBackground(checkStatus(key));
                    break;
                case "pnl5":
                    pnl5.setBackground(checkStatus(key));
                    break;
                case "pnl6":
                    pnl6.setBackground(checkStatus(key));
                    break;
                case "pnl7":
                    pnl7.setBackground(checkStatus(key));
                    break;
                case "pnl8":
                    pnl8.setBackground(checkStatus(key));
                    break;
                case "pnl9":
                    pnl9.setBackground(checkStatus(key));
                    break;
                case "pnl10":
                    pnl10.setBackground(checkStatus(key));
                    break;
                case "pnl11":
                    pnl11.setBackground(checkStatus(key));
                    break;
                case "pnl12":
                    pnl12.setBackground(checkStatus(key));
                    break;
                case "pnl13":
                    pnl13.setBackground(checkStatus(key));
                    break;
                case "pnl14":
                    pnl14.setBackground(checkStatus(key));
                    break;
                case "pnl15":
                    pnl15.setBackground(checkStatus(key));
                    break;
                case "pnl16":
                    pnl16.setBackground(checkStatus(key));
                    break;
                default:
                    break;
            }
        }
    }
    
    private Color checkStatus(String key)
    {
        try {
            ResultSet resultSet = dao.selectData("SELECT TINHTRANG FROM BAN WHERE MABAN = '" + key + "'");
            if(resultSet.next())
            {
                String result = resultSet.getString("TINHTRANG");
                if(result.equals("Đã đặt"))
                    return Color.GREEN;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main_QL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Color.WHITE;
    }
    
    private void loadCBoLoaiMon()
    {
        try {
            ResultSet resultSet = dao.selectData("SELECT * FROM DANHMUCHANG");
            while(resultSet.next())
            {
                String key = resultSet.getString("MADM");
                String name = resultSet.getString("TENDM");
                cboLoaiMon.addItem(new DanhMucHang(key, name));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main_QL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadCBoMon(String maDM)
    {
        cboTenMon.removeAllItems();
        
        try {
            ResultSet resultSet = dao.selectData("SELECT * FROM MATHANG WHERE MADM = '" + maDM + "'");
            while(resultSet.next())
            {
                String keyMH = resultSet.getString("MAMH");
                String name = resultSet.getString("TENMH");
                int price = resultSet.getInt("GIAMH");
                int quantity = resultSet.getInt("SOLUONG");
                String keyDM = resultSet.getString("MADM");
                String keyDVT = resultSet.getString("MADVT");
                
                cboTenMon.addItem(new MatHang(keyMH, name, price, quantity, keyDM, keyDVT));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main_QL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadCboBan()
    {
        cboTableChange.removeAllItems();
        
        try {
            ResultSet resultSet = dao.selectData("SELECT * FROM BAN WHERE TINHTRANG = N'Trống'");
            while (resultSet.next())
            {
                String key = resultSet.getString("MABAN");
                String name = resultSet.getString("TENBAN");
                int quantity = resultSet.getInt("SOGHE");
                String status = resultSet.getString("TINHTRANG");
                cboTableChange.addItem(new Ban(key, name, quantity, status));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main_QL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        Object obj = e.getSource();
        String tableKey = getTableKey(obj);
        
        dtoStatic.tableKey = tableKey;
        
        String maHD = getMaHD(tableKey);
        if(maHD.isEmpty())
        {
            txtSoLuong.setText("");
            dfTableModel.setRowCount(0);
        }
        else
        {
            showCTHD_MH(maHD);
            txtSoLuong.setText(String.valueOf(getSoBan(maHD)));
        }
        
        showSumMoney(maHD);
        showTableInfo(tableKey);
    }
    
    private void showCTHD_MH(String maHD)
    {
        dfTableModel.setRowCount(0);
        try {
            ResultSet rs = dao.selectData("SELECT TENMH, CT.SLUONG, GIAMH, TENDVT " +
                    "FROM MATHANG MH, DVT, CTHD_MH CT " +
                    "WHERE MH.MADVT = DVT.MADVT AND MH.MAMH = CT.MAMH AND MAHD = '" + maHD + "'");
            while (rs.next())
            {
                String nameMH = rs.getString("TENMH");
                int quantity = rs.getInt("SLUONG");
                float price = rs.getFloat("GIAMH");
                String nameDVT = rs.getString("TENDVT");
                
                dfTableModel.addRow(new String[]{nameMH, String.valueOf(quantity), String.format("%,.0f", price), nameDVT});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main_QL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String getMaHD(String tableKey)
    {
        String result = "";
        try {
            ResultSet rs = dao.selectData("SELECT CT.MAHD FROM CTHD_BAN CT, HOADON HD " +
                                          "WHERE CT.MAHD = HD.MAHD AND TTRANG = 0 AND MABAN = '" + tableKey + "'");
            if(rs.next())
                result = rs.getString("MAHD");
        } catch (SQLException ex) {
            Logger.getLogger(Main_QL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    private int getSoBan(String maHD)
    {
        int result = 0;
        try {
            ResultSet rs = dao.selectData("SELECT COUNT(*) AS SOBAN FROM CTHD_BAN WHERE MAHD = '" + maHD + "'");
            if(rs.next())
                result = rs.getInt("SOBAN");
        } catch (SQLException ex) {
            Logger.getLogger(Main_QL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    private void showTableInfo(String key)
    {
        try {
            ResultSet resultSet = dao.selectData("SELECT * FROM BAN WHERE MABAN = '" + key + "'");
            if (resultSet.next())
            {
                txtTableName.setText(resultSet.getString("TENBAN"));
                txtSucChua.setText(String.valueOf(resultSet.getInt("SOGHE")));
                txtStatus.setText(resultSet.getString("TINHTRANG"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main_QL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getTableKey(Object obj) 
    {
        String tableKey = "";
        if(obj == btn1)
            tableKey = "B01";
        if(obj == btn2)
            tableKey = "B02";
        if(obj == btn3)
            tableKey = "B03";
        if(obj == btn4)
            tableKey = "B04";
        if(obj == btn5)
            tableKey = "B05";
        if(obj == btn6)
            tableKey = "B06";
        if(obj == btn7)
            tableKey = "B07";
        if(obj == btn8)
            tableKey = "B08";
        if(obj == btn9)
            tableKey = "B09";
        if(obj == btn10)
            tableKey = "B10";
        if(obj == btn11)
            tableKey = "B11";
        if(obj == btn12)
            tableKey = "B12";
        if(obj == btn13)
            tableKey = "B13";
        if(obj == btn14)
            tableKey = "B14";
        if(obj == btn15)
            tableKey = "B15";
        if(obj == btn16)
            tableKey = "B16";
        
        return tableKey;
    }
    
    private void showSumMoney(String maHD)
    {
        if(maHD.isEmpty())
            lblTongTien.setText("0 VND");
        else
        {
            try 
            {
                ResultSet rs = dao.selectData("SELECT SUM(TTIEN) AS TOTAL FROM CTHD_MH WHERE MAHD = '" + maHD + "'");
                if(rs.next())
                {
                    float result = rs.getFloat("TOTAL");
                    lblTongTien.setText(String.format("%,.0f", result) + " VND");
                }
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(Main_QL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private int getSLuongMH(String maMH)
    {
        int result = 0;
        try {
            ResultSet rs = dao.selectData("SELECT SOLUONG FROM MATHANG WHERE MAMH = '" + maMH + "'");
            if(rs.next())
                result = rs.getInt("SOLUONG");
        } catch (SQLException ex) {
            Logger.getLogger(Main_QL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBan = new javax.swing.JPanel();
        pnl1 = new javax.swing.JPanel();
        lbl1 = new javax.swing.JLabel();
        btn1 = new javax.swing.JButton();
        pnl2 = new javax.swing.JPanel();
        lbl2 = new javax.swing.JLabel();
        btn2 = new javax.swing.JButton();
        pnl3 = new javax.swing.JPanel();
        lbl3 = new javax.swing.JLabel();
        btn3 = new javax.swing.JButton();
        pnl4 = new javax.swing.JPanel();
        lbl4 = new javax.swing.JLabel();
        btn4 = new javax.swing.JButton();
        pnl5 = new javax.swing.JPanel();
        lbl5 = new javax.swing.JLabel();
        btn5 = new javax.swing.JButton();
        pnl6 = new javax.swing.JPanel();
        lbl6 = new javax.swing.JLabel();
        btn6 = new javax.swing.JButton();
        pnl11 = new javax.swing.JPanel();
        lbl11 = new javax.swing.JLabel();
        btn11 = new javax.swing.JButton();
        pnl16 = new javax.swing.JPanel();
        lbl16 = new javax.swing.JLabel();
        btn16 = new javax.swing.JButton();
        pnl7 = new javax.swing.JPanel();
        lbl7 = new javax.swing.JLabel();
        btn7 = new javax.swing.JButton();
        pnl8 = new javax.swing.JPanel();
        lbl8 = new javax.swing.JLabel();
        btn8 = new javax.swing.JButton();
        pnl9 = new javax.swing.JPanel();
        lbl9 = new javax.swing.JLabel();
        btn9 = new javax.swing.JButton();
        pnl10 = new javax.swing.JPanel();
        lbl10 = new javax.swing.JLabel();
        btn10 = new javax.swing.JButton();
        pnl12 = new javax.swing.JPanel();
        lbl12 = new javax.swing.JLabel();
        btn12 = new javax.swing.JButton();
        pnl13 = new javax.swing.JPanel();
        lbl13 = new javax.swing.JLabel();
        btn13 = new javax.swing.JButton();
        pnl14 = new javax.swing.JPanel();
        lbl14 = new javax.swing.JLabel();
        btn14 = new javax.swing.JButton();
        pnl15 = new javax.swing.JPanel();
        lbl15 = new javax.swing.JLabel();
        btn15 = new javax.swing.JButton();
        pnlCN = new javax.swing.JPanel();
        pnlMon = new javax.swing.JPanel();
        cboLoaiMon = new javax.swing.JComboBox();
        lblLoaiMon = new javax.swing.JLabel();
        lblTenMon = new javax.swing.JLabel();
        cboTenMon = new javax.swing.JComboBox();
        btnThemMon = new javax.swing.JButton();
        lblSoLuong = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        btnSuaSLuong = new javax.swing.JButton();
        btnXoaMon = new javax.swing.JButton();
        pnlDSMon = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMonDaDung = new javax.swing.JTable();
        pnlDoiBan = new javax.swing.JPanel();
        cboTableChange = new javax.swing.JComboBox();
        btnDoiBan = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnDatBan = new javax.swing.JButton();
        txtSucChua = new javax.swing.JTextField();
        lblSoKhach = new javax.swing.JLabel();
        lblSoKhach1 = new javax.swing.JLabel();
        lblSoKhach2 = new javax.swing.JLabel();
        txtStatus = new javax.swing.JTextField();
        txtTableName = new javax.swing.JTextField();
        pnlThanhToan = new javax.swing.JPanel();
        lblTong = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        btnThanhToan = new javax.swing.JButton();
        mebMenu = new javax.swing.JMenuBar();
        MenuMatHang = new javax.swing.JMenu();
        menuNhapHang = new javax.swing.JMenuItem();
        menuNhanHang = new javax.swing.JMenuItem();
        menuQLMH = new javax.swing.JMenuItem();
        MenuHoaDon = new javax.swing.JMenu();
        MenuNhanVien = new javax.swing.JMenu();
        MenuKhachHang = new javax.swing.JMenu();
        btnExit = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quản lý nhà hàng");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pnlBan.setBackground(new java.awt.Color(255, 204, 255));
        pnlBan.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách bàn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N

        pnl1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnl1.setPreferredSize(new java.awt.Dimension(110, 110));

        lbl1.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        lbl1.setText("1");

        btn1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn1.setText("Chọn");
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl1Layout = new javax.swing.GroupLayout(pnl1);
        pnl1.setLayout(pnl1Layout);
        pnl1Layout.setHorizontalGroup(
            pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(lbl1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl1Layout.createSequentialGroup()
                .addGap(0, 28, Short.MAX_VALUE)
                .addComponent(btn1)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        pnl1Layout.setVerticalGroup(
            pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbl1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(btn1)
                .addGap(10, 10, 10))
        );

        pnl2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lbl2.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        lbl2.setText("2");

        btn2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn2.setText("Chọn");

        javax.swing.GroupLayout pnl2Layout = new javax.swing.GroupLayout(pnl2);
        pnl2.setLayout(pnl2Layout);
        pnl2Layout.setHorizontalGroup(
            pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl2)
                .addGap(57, 57, 57))
            .addGroup(pnl2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(btn2)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        pnl2Layout.setVerticalGroup(
            pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbl2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn2)
                .addGap(10, 10, 10))
        );

        pnl3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lbl3.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        lbl3.setText("3");

        btn3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn3.setText("Chọn");

        javax.swing.GroupLayout pnl3Layout = new javax.swing.GroupLayout(pnl3);
        pnl3.setLayout(pnl3Layout);
        pnl3Layout.setHorizontalGroup(
            pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl3Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(lbl3)
                .addContainerGap(57, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl3Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(btn3)
                .addGap(28, 28, 28))
        );
        pnl3Layout.setVerticalGroup(
            pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbl3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn3)
                .addGap(10, 10, 10))
        );

        pnl4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lbl4.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        lbl4.setText("4");

        btn4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn4.setText("Chọn");

        javax.swing.GroupLayout pnl4Layout = new javax.swing.GroupLayout(pnl4);
        pnl4.setLayout(pnl4Layout);
        pnl4Layout.setHorizontalGroup(
            pnl4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(btn4)
                .addContainerGap(28, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl4)
                .addGap(57, 57, 57))
        );
        pnl4Layout.setVerticalGroup(
            pnl4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbl4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn4)
                .addGap(10, 10, 10))
        );

        pnl5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lbl5.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        lbl5.setText("5");

        btn5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn5.setText("Chọn");

        javax.swing.GroupLayout pnl5Layout = new javax.swing.GroupLayout(pnl5);
        pnl5.setLayout(pnl5Layout);
        pnl5Layout.setHorizontalGroup(
            pnl5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl5)
                .addGap(57, 57, 57))
            .addGroup(pnl5Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(btn5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnl5Layout.setVerticalGroup(
            pnl5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl5Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbl5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(btn5)
                .addGap(10, 10, 10))
        );

        pnl6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lbl6.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        lbl6.setText("6");

        btn6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn6.setText("Chọn");

        javax.swing.GroupLayout pnl6Layout = new javax.swing.GroupLayout(pnl6);
        pnl6.setLayout(pnl6Layout);
        pnl6Layout.setHorizontalGroup(
            pnl6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl6)
                .addGap(57, 57, 57))
            .addGroup(pnl6Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(btn6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnl6Layout.setVerticalGroup(
            pnl6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl6Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbl6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn6)
                .addGap(10, 10, 10))
        );

        pnl11.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lbl11.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        lbl11.setText("11");

        btn11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn11.setText("Chọn");

        javax.swing.GroupLayout pnl11Layout = new javax.swing.GroupLayout(pnl11);
        pnl11.setLayout(pnl11Layout);
        pnl11Layout.setHorizontalGroup(
            pnl11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl11Layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addComponent(lbl11)
                .addGap(49, 49, 49))
            .addGroup(pnl11Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(btn11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnl11Layout.setVerticalGroup(
            pnl11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl11Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbl11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(btn11)
                .addGap(10, 10, 10))
        );

        pnl16.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lbl16.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        lbl16.setText("16");

        btn16.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn16.setText("Chọn");

        javax.swing.GroupLayout pnl16Layout = new javax.swing.GroupLayout(pnl16);
        pnl16.setLayout(pnl16Layout);
        pnl16Layout.setHorizontalGroup(
            pnl16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl16Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(btn16)
                .addContainerGap(28, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl16)
                .addGap(49, 49, 49))
        );
        pnl16Layout.setVerticalGroup(
            pnl16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl16Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbl16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn16)
                .addGap(10, 10, 10))
        );

        pnl7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lbl7.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        lbl7.setText("7");

        btn7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn7.setText("Chọn");

        javax.swing.GroupLayout pnl7Layout = new javax.swing.GroupLayout(pnl7);
        pnl7.setLayout(pnl7Layout);
        pnl7Layout.setHorizontalGroup(
            pnl7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl7)
                .addGap(57, 57, 57))
            .addGroup(pnl7Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(btn7)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        pnl7Layout.setVerticalGroup(
            pnl7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl7Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbl7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn7)
                .addGap(10, 10, 10))
        );

        pnl8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lbl8.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        lbl8.setText("8");

        btn8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn8.setText("Chọn");

        javax.swing.GroupLayout pnl8Layout = new javax.swing.GroupLayout(pnl8);
        pnl8.setLayout(pnl8Layout);
        pnl8Layout.setHorizontalGroup(
            pnl8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl8Layout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addComponent(lbl8)
                .addGap(57, 57, 57))
            .addGroup(pnl8Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(btn8)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        pnl8Layout.setVerticalGroup(
            pnl8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl8Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbl8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn8)
                .addGap(10, 10, 10))
        );

        pnl9.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lbl9.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        lbl9.setText("9");

        btn9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn9.setText("Chọn");

        javax.swing.GroupLayout pnl9Layout = new javax.swing.GroupLayout(pnl9);
        pnl9.setLayout(pnl9Layout);
        pnl9Layout.setHorizontalGroup(
            pnl9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl9Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(btn9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl9Layout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addComponent(lbl9)
                .addGap(57, 57, 57))
        );
        pnl9Layout.setVerticalGroup(
            pnl9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl9Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbl9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(btn9)
                .addGap(10, 10, 10))
        );

        pnl10.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lbl10.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        lbl10.setText("10");

        btn10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn10.setText("Chọn");

        javax.swing.GroupLayout pnl10Layout = new javax.swing.GroupLayout(pnl10);
        pnl10.setLayout(pnl10Layout);
        pnl10Layout.setHorizontalGroup(
            pnl10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl10Layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addComponent(lbl10)
                .addGap(49, 49, 49))
            .addGroup(pnl10Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(btn10)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        pnl10Layout.setVerticalGroup(
            pnl10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl10Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbl10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn10)
                .addGap(10, 10, 10))
        );

        pnl12.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lbl12.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        lbl12.setText("12");

        btn12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn12.setText("Chọn");

        javax.swing.GroupLayout pnl12Layout = new javax.swing.GroupLayout(pnl12);
        pnl12.setLayout(pnl12Layout);
        pnl12Layout.setHorizontalGroup(
            pnl12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl12Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(btn12)
                .addContainerGap(28, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl12Layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addComponent(lbl12)
                .addGap(49, 49, 49))
        );
        pnl12Layout.setVerticalGroup(
            pnl12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl12Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbl12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn12)
                .addGap(10, 10, 10))
        );

        pnl13.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lbl13.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        lbl13.setText("13");

        btn13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn13.setText("Chọn");

        javax.swing.GroupLayout pnl13Layout = new javax.swing.GroupLayout(pnl13);
        pnl13.setLayout(pnl13Layout);
        pnl13Layout.setHorizontalGroup(
            pnl13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn13)
                .addGap(28, 28, 28))
            .addGroup(pnl13Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(lbl13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnl13Layout.setVerticalGroup(
            pnl13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl13Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbl13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(btn13)
                .addGap(10, 10, 10))
        );

        pnl14.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lbl14.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        lbl14.setText("14");

        btn14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn14.setText("Chọn");

        javax.swing.GroupLayout pnl14Layout = new javax.swing.GroupLayout(pnl14);
        pnl14.setLayout(pnl14Layout);
        pnl14Layout.setHorizontalGroup(
            pnl14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl14Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(btn14)
                .addContainerGap(29, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl14Layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addComponent(lbl14)
                .addGap(49, 49, 49))
        );
        pnl14Layout.setVerticalGroup(
            pnl14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl14Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbl14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn14)
                .addGap(10, 10, 10))
        );

        pnl15.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lbl15.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        lbl15.setText("15");

        btn15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn15.setText("Chọn");

        javax.swing.GroupLayout pnl15Layout = new javax.swing.GroupLayout(pnl15);
        pnl15.setLayout(pnl15Layout);
        pnl15Layout.setHorizontalGroup(
            pnl15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl15Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(btn15)
                .addContainerGap(28, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl15)
                .addGap(49, 49, 49))
        );
        pnl15Layout.setVerticalGroup(
            pnl15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl15Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbl15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(btn15)
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout pnlBanLayout = new javax.swing.GroupLayout(pnlBan);
        pnlBan.setLayout(pnlBanLayout);
        pnlBanLayout.setHorizontalGroup(
            pnlBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBanLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlBanLayout.createSequentialGroup()
                        .addComponent(pnl1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(pnl2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlBanLayout.createSequentialGroup()
                        .addComponent(pnl13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addComponent(pnl14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlBanLayout.createSequentialGroup()
                        .addGroup(pnlBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(pnl5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnl9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(pnlBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pnl10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnl6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(10, 10, 10)
                .addGroup(pnlBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnl11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(pnlBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnl4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        pnlBanLayout.setVerticalGroup(
            pnlBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBanLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnl2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl1, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(pnlBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnl8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(pnlBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(pnl11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnl10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnl12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pnl9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(pnlBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(pnl15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnl16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnl14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pnl13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlCN.setBackground(new java.awt.Color(255, 204, 255));
        pnlCN.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pnlMon.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Gọi Món", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N

        cboLoaiMon.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cboLoaiMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiMonActionPerformed(evt);
            }
        });

        lblLoaiMon.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblLoaiMon.setText("Loại món:");

        lblTenMon.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblTenMon.setText("Tên món:");

        cboTenMon.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        btnThemMon.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnThemMon.setText("Thêm");
        btnThemMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMonActionPerformed(evt);
            }
        });

        lblSoLuong.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblSoLuong.setText("Số lượng:");

        txtSoLuong.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtSoLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSoLuongKeyPressed(evt);
            }
        });

        btnSuaSLuong.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnSuaSLuong.setText("Sửa");
        btnSuaSLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSLuongActionPerformed(evt);
            }
        });

        btnXoaMon.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnXoaMon.setText("Xóa");
        btnXoaMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaMonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMonLayout = new javax.swing.GroupLayout(pnlMon);
        pnlMon.setLayout(pnlMonLayout);
        pnlMonLayout.setHorizontalGroup(
            pnlMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlMonLayout.createSequentialGroup()
                        .addComponent(lblLoaiMon)
                        .addGap(18, 18, 18)
                        .addComponent(cboLoaiMon, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMonLayout.createSequentialGroup()
                        .addComponent(lblSoLuong)
                        .addGap(18, 18, 18)
                        .addComponent(txtSoLuong)))
                .addGap(18, 18, 18)
                .addGroup(pnlMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMonLayout.createSequentialGroup()
                        .addComponent(lblTenMon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboTenMon, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMonLayout.createSequentialGroup()
                        .addComponent(btnThemMon, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSuaSLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoaMon, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlMonLayout.setVerticalGroup(
            pnlMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboLoaiMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLoaiMon)
                    .addComponent(lblTenMon)
                    .addComponent(cboTenMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemMon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSoLuong)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaSLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaMon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pnlDSMon.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Món đã gọi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N

        tblMonDaDung.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblMonDaDung.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tên món", "Số lượng", "Giá", "ĐVT"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Float.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblMonDaDung.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblMonDaDung.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblMonDaDung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMonDaDungMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMonDaDung);

        javax.swing.GroupLayout pnlDSMonLayout = new javax.swing.GroupLayout(pnlDSMon);
        pnlDSMon.setLayout(pnlDSMonLayout);
        pnlDSMonLayout.setHorizontalGroup(
            pnlDSMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDSMonLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        pnlDSMonLayout.setVerticalGroup(
            pnlDSMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDSMonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );

        pnlDoiBan.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đổi bàn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N

        cboTableChange.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        btnDoiBan.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnDoiBan.setText("Đổi");
        btnDoiBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiBanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDoiBanLayout = new javax.swing.GroupLayout(pnlDoiBan);
        pnlDoiBan.setLayout(pnlDoiBanLayout);
        pnlDoiBanLayout.setHorizontalGroup(
            pnlDoiBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoiBanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDoiBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboTableChange, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDoiBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlDoiBanLayout.setVerticalGroup(
            pnlDoiBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoiBanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboTableChange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDoiBan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đặt bàn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N

        btnDatBan.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnDatBan.setText("Đặt");
        btnDatBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatBanActionPerformed(evt);
            }
        });

        txtSucChua.setEditable(false);
        txtSucChua.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtSucChua.setToolTipText("");
        txtSucChua.setActionCommand("");
        txtSucChua.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        lblSoKhach.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblSoKhach.setText("Sức chứa :");

        lblSoKhach1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblSoKhach1.setText("Bàn :");

        lblSoKhach2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblSoKhach2.setText("TT :");

        txtStatus.setEditable(false);
        txtStatus.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtStatus.setToolTipText("");
        txtStatus.setActionCommand("");
        txtStatus.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        txtTableName.setEditable(false);
        txtTableName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtTableName.setToolTipText("");
        txtTableName.setActionCommand("");
        txtTableName.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lblSoKhach)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSucChua, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lblSoKhach2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnDatBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblSoKhach1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTableName)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSoKhach1)
                    .addComponent(txtTableName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSoKhach)
                    .addComponent(txtSucChua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSoKhach2)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(btnDatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pnlThanhToan.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thanh toán", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N

        lblTong.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTong.setText("Tổng tiền:");

        lblTongTien.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTongTien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTongTien.setText("0 VND");

        btnThanhToan.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlThanhToanLayout = new javax.swing.GroupLayout(pnlThanhToan);
        pnlThanhToan.setLayout(pnlThanhToanLayout);
        pnlThanhToanLayout.setHorizontalGroup(
            pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThanhToanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlThanhToanLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnThanhToan))
                    .addGroup(pnlThanhToanLayout.createSequentialGroup()
                        .addComponent(lblTong)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlThanhToanLayout.setVerticalGroup(
            pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThanhToanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTong)
                    .addComponent(lblTongTien))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlCNLayout = new javax.swing.GroupLayout(pnlCN);
        pnlCN.setLayout(pnlCNLayout);
        pnlCNLayout.setHorizontalGroup(
            pnlCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCNLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlMon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlCNLayout.createSequentialGroup()
                        .addComponent(pnlDSMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlDoiBan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pnlCNLayout.setVerticalGroup(
            pnlCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCNLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlCNLayout.createSequentialGroup()
                        .addComponent(pnlDoiBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlDSMon, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 57, Short.MAX_VALUE))
        );

        mebMenu.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        MenuMatHang.setText("Mặt Hàng");
        MenuMatHang.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        menuNhapHang.setText("Nhập hàng");
        menuNhapHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNhapHangActionPerformed(evt);
            }
        });
        MenuMatHang.add(menuNhapHang);

        menuNhanHang.setText("Nhận hàng");
        menuNhanHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNhanHangActionPerformed(evt);
            }
        });
        MenuMatHang.add(menuNhanHang);

        menuQLMH.setText("Quản lý mặt hàng");
        menuQLMH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuQLMHActionPerformed(evt);
            }
        });
        MenuMatHang.add(menuQLMH);

        mebMenu.add(MenuMatHang);

        MenuHoaDon.setText("Hóa Đơn");
        MenuHoaDon.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        MenuHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuHoaDonMouseClicked(evt);
            }
        });
        mebMenu.add(MenuHoaDon);

        MenuNhanVien.setText("Nhân Viên");
        MenuNhanVien.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        MenuNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuNhanVienMouseClicked(evt);
            }
        });
        mebMenu.add(MenuNhanVien);

        MenuKhachHang.setText("Khách Hàng");
        MenuKhachHang.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        MenuKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuKhachHangMouseClicked(evt);
            }
        });
        mebMenu.add(MenuKhachHang);

        btnExit.setText("Đăng xuất");
        btnExit.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExitMouseClicked(evt);
            }
        });
        mebMenu.add(btnExit);

        setJMenuBar(mebMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlCN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboLoaiMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiMonActionPerformed
        DanhMucHang dmH = (DanhMucHang) cboLoaiMon.getSelectedItem();
        loadCBoMon(dmH.getMaDM());
    }//GEN-LAST:event_cboLoaiMonActionPerformed

    private void btnDatBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatBanActionPerformed
        sb = new StringBuilder();
        
        checkDataForDatBan();
        
        if(sb.length() > 0)
        {
            JOptionPane.showMessageDialog(rootPane, sb, "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        DatBan db = new DatBan();
        db.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnDatBanActionPerformed

    private void checkDataForDatBan() 
    {
        if(txtTableName.getText().isEmpty())
            sb.append("Vui lòng chọn bàn cần đặt");
        
        if(txtStatus.getText().equals("Đã đặt"))
            sb.append("Bạn đã có khách đặt. Vui lòng chọn bàn khác");
    }

    private void btnThemMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMonActionPerformed
        sb = new StringBuilder();
        
        checkDataForInsert_MH();
        
        if(sb.length() > 0)
        {
            JOptionPane.showMessageDialog(rootPane, sb, "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        MatHang mh = (MatHang)cboTenMon.getSelectedItem();
        String maHD = getMaHD(dtoStatic.tableKey);
            
        int sLuong = Integer.parseInt(txtSoLuong.getText());
            
        if(daoHD.insertCTHD_MH(mh.getMaMH(), maHD, sLuong) == 1)
        {
            showCTHD_MH(maHD);
            showSumMoney(maHD);
        }
            
        if(getSLuongMH(mh.getMaMH()) <= 10)
        {
            if(mh.getMaDM().equals("DM08") || mh.getMaDM().equals("DM09") || mh.getMaDM().equals("DM10"))
            {
                JOptionPane.showConfirmDialog(rootPane, "Số lượng mặt hàng " + mh.getTenMH() + " sắp hết. Bạn cần phải nhập hàng", "Thôngp báo", JOptionPane.YES_NO_OPTION);
                return;
            }
        }
    }//GEN-LAST:event_btnThemMonActionPerformed

    private void checkDataForInsert_MH() 
    {
        if(dtoStatic.tableKey.isEmpty())
            sb.append("Bạn phải chọn bàn trước khi đặt món");
        else
        {
            String maHD = getMaHD(dtoStatic.tableKey);
            if(maHD.isEmpty())
                sb.append("Bạn phải đặt bàn trước khi chọn mọn");
            else
            {
                if(txtSoLuong.getText().isEmpty())
                    sb.append("Bạn phải nhập số lượng");
            }
        }
    }

    private void btnSuaSLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSLuongActionPerformed
        sb = new StringBuilder();
        
        int row = tblMonDaDung.getSelectedRow();
        checkDataForUpdate_MH(row);
        
        if(sb.length() > 0)
        {
            JOptionPane.showMessageDialog(rootPane, sb, "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        MatHang mh = getInfoMHByName((String)tblMonDaDung.getValueAt(row, 0));
        String maHD = getMaHD(dtoStatic.tableKey);
            
        int sLuong = Integer.parseInt(txtSoLuong.getText());
            
        if(daoHD.updateCTHD_MH(mh.getMaMH(), maHD, sLuong) == 1)
        {
            showCTHD_MH(maHD);
            showSumMoney(maHD);
        }
            
        if(getSLuongMH(mh.getMaMH()) <= 10)
        {
            if(mh.getMaDM().equals("DM08") || mh.getMaDM().equals("DM09") || mh.getMaDM().equals("DM10"))
            {
                JOptionPane.showConfirmDialog(rootPane, "Số lượng mặt hàng " + mh.getTenMH() + " sắp hết. Bạn cần phải nhập hàng", "Thông báo", JOptionPane.YES_NO_OPTION);
                return;
            }
        }
    }//GEN-LAST:event_btnSuaSLuongActionPerformed

    private void checkDataForUpdate_MH(int row) 
    {
        if(row < 0)
            sb.append("Bạn phải chọn món ăn đã gọi để thay đổi");
        else
        {
            if(txtSoLuong.getText().isEmpty())
                    sb.append("Bạn phải nhập số lượng");
        }
    }
    
    private MatHang getInfoMHByName(String tenMH)
    {
        MatHang mh = new MatHang();
        try {
            ResultSet rs = dao.selectData("SELECT * FROM MATHANG WHERE TENMH = N'" + tenMH + "'");
            if(rs.next())
            {
                mh.setMaMH(rs.getString("MAMH"));
                mh.setMaDM(rs.getString("MADM"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main_QL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mh;
    }

    private void btnXoaMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaMonActionPerformed
        int row = tblMonDaDung.getSelectedRow();
        
        if(row < 0)
        {
            JOptionPane.showMessageDialog(rootPane, "Bạn phải chọn món ăn đã gọi để thay đổi", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        else
        {
            int result = JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc là muốn xóa món này ?", "Thông báo", JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.YES_OPTION)
            {
                MatHang mh = getInfoMHByName((String)tblMonDaDung.getValueAt(row, 0));
                String maHD = getMaHD(dtoStatic.tableKey);
                
                if(daoHD.deleteCTHD_MH(mh.getMaMH(), maHD) == 1)
                {
                    JOptionPane.showMessageDialog(rootPane, "Xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    showCTHD_MH(maHD);
                    showSumMoney(maHD);
                }
                else
                    JOptionPane.showMessageDialog(rootPane, "Xóa thất bại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnXoaMonActionPerformed

    private void tblMonDaDungMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMonDaDungMouseClicked
        int row = tblMonDaDung.getSelectedRow();
        if(row >= 0)
            txtSoLuong.setText(String.valueOf(tblMonDaDung.getValueAt(row, 1)));
    }//GEN-LAST:event_tblMonDaDungMouseClicked

    private void txtSoLuongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongKeyPressed
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9' || evt.getKeyChar() == '\b') 
        {
            txtSoLuong.setEditable(true);
        } 
        else 
        {
            txtSoLuong.setEditable(false);
            JOptionPane.showMessageDialog(rootPane, "Chỉ được nhập số", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            
            txtSoLuong.setEditable(true);
        }
    }//GEN-LAST:event_txtSoLuongKeyPressed

    private void btnDoiBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiBanActionPerformed
        if(txtTableName.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(rootPane, "Bạn cần phải chọn bàn cần đổi trước", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        else
        {
            if(txtStatus.getText().equals("Trống"))
            {
                int result = JOptionPane.showConfirmDialog(rootPane, txtTableName.getText() + " chưa được đặt. Bạn có muốn đặt bàn này ?", "Thông báo", JOptionPane.YES_NO_OPTION);
            
                if(result == JOptionPane.YES_OPTION)
                {
                    DatBan db = new DatBan();
                    db.setVisible(true);
                    this.dispose();
                }
            }
            else
            {
                String maBanOld = dtoStatic.tableKey;
                String maHD = getMaHD(maBanOld);
                Ban b = (Ban) cboTableChange.getSelectedItem();
                int result = JOptionPane.showConfirmDialog(rootPane, " Bạn có chắc là muốn đổi bàn ?", "Thông báo", JOptionPane.YES_NO_OPTION);
            
                if(result == JOptionPane.YES_OPTION)
                {
                    if(daoHD.updateCTHD_Ban(maHD, maBanOld, b.getMaBan()) > 0)
                    {
                        JOptionPane.showMessageDialog(rootPane, "Đổi bàn thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        setColorForPanel();
                        loadCboBan();
                        
                        txtTableName.setText("");
                        txtSucChua.setText("");
                        txtStatus.setText("");
                        txtSoLuong.setText("");
                        dfTableModel.setRowCount(0);
                    }
                    else
                        JOptionPane.showMessageDialog(rootPane, "Đổi bàn thất bại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
            }
        }
    }//GEN-LAST:event_btnDoiBanActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        if(txtTableName.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(rootPane, "Bạn cần phải chọn bàn cần thanh toán", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        else
        {
            if(txtStatus.getText().equals("Trống"))
            {
                JOptionPane.showMessageDialog(rootPane, txtTableName.getText() + " chưa được đặt", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            else
            {
                int row = dfTableModel.getRowCount();
                if(row <= 0)
                {
                    JOptionPane.showMessageDialog(rootPane, txtTableName.getText() + " chưa gọi món", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                else
                {
                    dtoStatic.billKey = getMaHD(dtoStatic.tableKey);
                    float tongTien = 0;
                    try {
                        ResultSet rs = dao.selectData("SELECT SUM(TTIEN) AS TOTAL FROM CTHD_MH WHERE MAHD = '" + dtoStatic.billKey + "'");
                        if(rs.next())
                            tongTien = rs.getFloat("TOTAL");
                    } catch (SQLException ex) {
                        Logger.getLogger(Main_QL.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    daoHD.updateHD(dtoStatic.billKey, tongTien);
                    HoaDon_GUI hd = new HoaDon_GUI();
                    hd.setVisible(true);
                    this.dispose();
                }
            }
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void menuNhapHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNhapHangActionPerformed
        QL_NhapHang frm = new QL_NhapHang();
        frm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menuNhapHangActionPerformed

    private void menuNhanHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNhanHangActionPerformed
        QL_NhanHang frm = new QL_NhanHang();
        frm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menuNhanHangActionPerformed

    private void MenuNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuNhanVienMouseClicked
        String res = dao.checkRole("SELECT * FROM NHANVIEN");
        if(res.isEmpty())
        {
            QL_NhanVien qlnv = new QL_NhanVien();
            qlnv.setVisible(true);
            this.dispose();
        }
        else
            JOptionPane.showMessageDialog(rootPane, "Ban khong co quyen vao chuc nang nay");
    }//GEN-LAST:event_MenuNhanVienMouseClicked

    private void MenuKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuKhachHangMouseClicked
        QL_KhachHang qlkh = new QL_KhachHang();
        qlkh.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_MenuKhachHangMouseClicked

    private void MenuHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuHoaDonMouseClicked
        QL_HoaDon qlhd = new QL_HoaDon();
        qlhd.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_MenuHoaDonMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        DN dn = new DN();
        dn.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void btnExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseClicked
        DN dn = new DN();
        dn.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnExitMouseClicked

    private void menuQLMHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuQLMHActionPerformed
        QL_MatHang qlmh = new QL_MatHang();
        qlmh.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menuQLMHActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main_QL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_QL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_QL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_QL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main_QL().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu MenuHoaDon;
    private javax.swing.JMenu MenuKhachHang;
    private javax.swing.JMenu MenuMatHang;
    private javax.swing.JMenu MenuNhanVien;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn10;
    private javax.swing.JButton btn11;
    private javax.swing.JButton btn12;
    private javax.swing.JButton btn13;
    private javax.swing.JButton btn14;
    private javax.swing.JButton btn15;
    private javax.swing.JButton btn16;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JButton btnDatBan;
    private javax.swing.JButton btnDoiBan;
    private javax.swing.JMenu btnExit;
    private javax.swing.JButton btnSuaSLuong;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThemMon;
    private javax.swing.JButton btnXoaMon;
    private javax.swing.JComboBox cboLoaiMon;
    private javax.swing.JComboBox cboTableChange;
    private javax.swing.JComboBox cboTenMon;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl10;
    private javax.swing.JLabel lbl11;
    private javax.swing.JLabel lbl12;
    private javax.swing.JLabel lbl13;
    private javax.swing.JLabel lbl14;
    private javax.swing.JLabel lbl15;
    private javax.swing.JLabel lbl16;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lbl5;
    private javax.swing.JLabel lbl6;
    private javax.swing.JLabel lbl7;
    private javax.swing.JLabel lbl8;
    private javax.swing.JLabel lbl9;
    private javax.swing.JLabel lblLoaiMon;
    private javax.swing.JLabel lblSoKhach;
    private javax.swing.JLabel lblSoKhach1;
    private javax.swing.JLabel lblSoKhach2;
    private javax.swing.JLabel lblSoLuong;
    private javax.swing.JLabel lblTenMon;
    private javax.swing.JLabel lblTong;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JMenuBar mebMenu;
    private javax.swing.JMenuItem menuNhanHang;
    private javax.swing.JMenuItem menuNhapHang;
    private javax.swing.JMenuItem menuQLMH;
    private javax.swing.JPanel pnl1;
    private javax.swing.JPanel pnl10;
    private javax.swing.JPanel pnl11;
    private javax.swing.JPanel pnl12;
    private javax.swing.JPanel pnl13;
    private javax.swing.JPanel pnl14;
    private javax.swing.JPanel pnl15;
    private javax.swing.JPanel pnl16;
    private javax.swing.JPanel pnl2;
    private javax.swing.JPanel pnl3;
    private javax.swing.JPanel pnl4;
    private javax.swing.JPanel pnl5;
    private javax.swing.JPanel pnl6;
    private javax.swing.JPanel pnl7;
    private javax.swing.JPanel pnl8;
    private javax.swing.JPanel pnl9;
    private javax.swing.JPanel pnlBan;
    private javax.swing.JPanel pnlCN;
    private javax.swing.JPanel pnlDSMon;
    private javax.swing.JPanel pnlDoiBan;
    private javax.swing.JPanel pnlMon;
    private javax.swing.JPanel pnlThanhToan;
    private javax.swing.JTable tblMonDaDung;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JTextField txtSucChua;
    private javax.swing.JTextField txtTableName;
    // End of variables declaration//GEN-END:variables

}
