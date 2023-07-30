/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ql_nhahang;

import DAO.NhaHang_DAO;
import DAO.PhieuNhan_DAO;
import DAO.PhieuNhap_DAO;
import DTO.MatHang;
import DTO.dtoStatic;
import GUI.Main_QL;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tphan
 */
public class QL_NhanHang extends javax.swing.JFrame {

    /**
     * Creates new form QL_NhapHang
     */
    
    NhaHang_DAO dao = new NhaHang_DAO();
    PhieuNhan_DAO daoPN = new PhieuNhan_DAO();
    PhieuNhap_DAO daoPNhap = new PhieuNhap_DAO();
    
    DefaultTableModel dfTableModelPN = new DefaultTableModel();
    DefaultTableModel dfTableModelCT_PN = new DefaultTableModel();
    DefaultTableModel dfTableModelCT_PNhan = new DefaultTableModel();
    
    int flagCreate = 0, flagCreateKey = 0;
    
    public QL_NhanHang() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        initTablePN();
        initTableCT_PN();
        
        showPN();
        initTableCT_PNhan();
    }
    
    private String createMaPhieu()
    {
        String key = "";
        try {
            ResultSet resultSet = dao.selectData("SELECT COUNT(*) SLPN FROM PHIEUNHANHANG");
            if(resultSet.next())
            {
                int count = resultSet.getInt("SLPN");
                key = "PNN" + String.format("%05d", count + 1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatBan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return key;
    }
    
    private void initTablePN()
    {
        String[] colTitle = {"Mã phiếu", "Ngày tạo", "Nhà cung cấp", "Nhân viên"};
        
        dfTableModelPN.setColumnIdentifiers(colTitle);
        tblDSPhieuNhap.setModel(dfTableModelPN);
    }
    
    private void showPN()
    {
        dfTableModelPN.setRowCount(0);
        try {
            ResultSet rs = dao.selectData("SELECT TOP 1 P.MAPHIEUNHAP, CONVERT(VARCHAR,NGAYTAO, 103) AS DATE, TENNCC, TENNV " +
                                          "FROM PHIEUNHAPHANG P, CT_PHIEUNHAP CT, MATHANG MH, NHACUNGCAP NCC, NHANVIEN NV " +
                                          "WHERE P.MAPHIEUNHAP = CT.MAPHIEUNHAP AND CT.MAMH = MH.MAMH AND MH.MANCC = NCC.MANCC " +
                                          "AND P.MANV = NV.MANV AND TTRANG = N'Đợi'");
            while (rs.next())
            {
                String maP = rs.getString("MAPHIEUNHAP");
                String ngayTao = rs.getString("DATE");
                String tenNCC = rs.getString("TENNCC");
                String tTrang = rs.getString("TENNV");
                
                dfTableModelPN.addRow(new String[]{maP, ngayTao, tenNCC, tTrang});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main_QL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initTableCT_PN()
    {
        String[] colTitle = {"Mã MH", "Tên hàng", "Số lượng cần nhập"};
        
        dfTableModelCT_PN.setColumnIdentifiers(colTitle);
        tblCT_PhieuNhap.setModel(dfTableModelCT_PN);
    }
    
    private void showCT_PN(String maP)
    {
        dfTableModelCT_PN.setRowCount(0);
        try {
            ResultSet rs = dao.selectData("SELECT CT.MAMH, TENMH, CT.SLUONG " +
                                          "FROM CT_PHIEUNHAP CT, MATHANG MH " +
                                          "WHERE CT.MAMH = MH.MAMH AND CT.MAPHIEUNHAP = '" + maP+ "'");
            while (rs.next())
            {
                String maMH = rs.getString("MAMH");
                String tenMH = rs.getString("TENMH");
                int sLuong = rs.getInt("SLUONG");
                
                dfTableModelCT_PN.addRow(new String[]{maMH, tenMH, String.valueOf(sLuong)});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main_QL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initTableCT_PNhan()
    {
        String[] colTitle = {"Mã MH", "Tên hàng", "Số lượng nhận", "Giá MH"};
        
        dfTableModelCT_PNhan.setColumnIdentifiers(colTitle);
        tblCT_PhieuNhan.setModel(dfTableModelCT_PNhan);
    }
    
    private void showCT_PNhan(String maPNhan)
    {
        dfTableModelCT_PNhan.setRowCount(0);
        try {
            ResultSet rs = dao.selectData("SELECT CT.MAMH, TENMH, SLUONG, CT.GIAMH " +
                                          "FROM CT_PHIEUNHAN CT, MATHANG MH, PHIEUNHANHANG P " +
                                          "WHERE CT.MAPHIEUNHAN = P.MAPHIEUNHAN AND CT.MAMH = MH.MAMH AND CT.MAPHIEUNHAN = '" + maPNhan+ "'");
            while (rs.next())
            {
                String maMH = rs.getString("MAMH");
                String tenMH = rs.getString("TENMH");
                int sLuong = rs.getInt("SLUONG");
                float gia = rs.getFloat("GIAMH");
                
                dfTableModelCT_PNhan.addRow(new String[]{maMH, tenMH, String.valueOf(sLuong), String.format("%.0f", gia)});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main_QL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlDanhSachNhapHang = new javax.swing.JPanel();
        lblMaPhieu = new javax.swing.JLabel();
        txtMaPhieuNew = new javax.swing.JTextField();
        btnTaoMa = new javax.swing.JButton();
        pnlDSHangNhan = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCT_PhieuNhan = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lblSLuong = new javax.swing.JLabel();
        lblGia = new javax.swing.JLabel();
        txtSLuong = new javax.swing.JTextField();
        txtGia = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        btnLuuPNhan = new javax.swing.JButton();
        btnSuaSLuong = new javax.swing.JButton();
        pnlDSPhieuNhap = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDSPhieuNhap = new javax.swing.JTable();
        pnlDSHangNhap2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblCT_PhieuNhap = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nhận hàng");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pnlDanhSachNhapHang.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tạo Phiếu Nhận Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N

        lblMaPhieu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblMaPhieu.setText("Mã phiếu :");

        txtMaPhieuNew.setEditable(false);
        txtMaPhieuNew.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        btnTaoMa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTaoMa.setText("Tạo mã");
        btnTaoMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoMaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDanhSachNhapHangLayout = new javax.swing.GroupLayout(pnlDanhSachNhapHang);
        pnlDanhSachNhapHang.setLayout(pnlDanhSachNhapHangLayout);
        pnlDanhSachNhapHangLayout.setHorizontalGroup(
            pnlDanhSachNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDanhSachNhapHangLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblMaPhieu)
                .addGap(44, 44, 44)
                .addComponent(txtMaPhieuNew, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                .addGap(31, 31, 31)
                .addComponent(btnTaoMa, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        pnlDanhSachNhapHangLayout.setVerticalGroup(
            pnlDanhSachNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDanhSachNhapHangLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlDanhSachNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaPhieu)
                    .addComponent(txtMaPhieuNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTaoMa, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        pnlDSHangNhan.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Hàng Nhận", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N

        tblCT_PhieuNhan.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblCT_PhieuNhan.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã MH", "Tên hàng", "Số Lượng", "Giá mặt hàng"
            }
        ));
        tblCT_PhieuNhan.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblCT_PhieuNhan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCT_PhieuNhanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCT_PhieuNhan);
        if (tblCT_PhieuNhan.getColumnModel().getColumnCount() > 0) {
            tblCT_PhieuNhan.getColumnModel().getColumn(3).setHeaderValue("Giá mặt hàng");
        }

        javax.swing.GroupLayout pnlDSHangNhanLayout = new javax.swing.GroupLayout(pnlDSHangNhan);
        pnlDSHangNhan.setLayout(pnlDSHangNhanLayout);
        pnlDSHangNhanLayout.setHorizontalGroup(
            pnlDSHangNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDSHangNhanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDSHangNhanLayout.setVerticalGroup(
            pnlDSHangNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cập Nhật Phiếu Nhập Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N

        lblSLuong.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblSLuong.setText("Số lượng :");

        lblGia.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblGia.setText("Giá nhập :");

        txtSLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSLuongKeyPressed(evt);
            }
        });

        txtGia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtGia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGiaKeyPressed(evt);
            }
        });

        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSave.setText("Lưu");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnLuuPNhan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLuuPNhan.setText("Lưu phiếu nhận");
        btnLuuPNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuPNhanActionPerformed(evt);
            }
        });

        btnSuaSLuong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSuaSLuong.setText("Sửa");
        btnSuaSLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSLuongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(lblSLuong)
                        .addGap(18, 18, 18)
                        .addComponent(txtSLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(lblGia)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSuaSLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtGia, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(btnLuuPNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSLuong)
                    .addComponent(txtSLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGia)
                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSuaSLuong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLuuPNhan)
                        .addComponent(btnSave)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlDSPhieuNhap.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Phiếu Nhập Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N

        tblDSPhieuNhap.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblDSPhieuNhap.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã phiếu", "Ngày tạo", "Nhà cung cấp", "Nhân viên"
            }
        ));
        tblDSPhieuNhap.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblDSPhieuNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSPhieuNhapMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblDSPhieuNhap);

        javax.swing.GroupLayout pnlDSPhieuNhapLayout = new javax.swing.GroupLayout(pnlDSPhieuNhap);
        pnlDSPhieuNhap.setLayout(pnlDSPhieuNhapLayout);
        pnlDSPhieuNhapLayout.setHorizontalGroup(
            pnlDSPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDSPhieuNhapLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDSPhieuNhapLayout.setVerticalGroup(
            pnlDSPhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDSPhieuNhapLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlDSHangNhap2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Hàng Cần Nhập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N

        tblCT_PhieuNhap.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblCT_PhieuNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã MH", "Tên hàng", "Số Lượng cần nhập"
            }
        ));
        tblCT_PhieuNhap.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(tblCT_PhieuNhap);

        javax.swing.GroupLayout pnlDSHangNhap2Layout = new javax.swing.GroupLayout(pnlDSHangNhap2);
        pnlDSHangNhap2.setLayout(pnlDSHangNhap2Layout);
        pnlDSHangNhap2Layout.setHorizontalGroup(
            pnlDSHangNhap2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDSHangNhap2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDSHangNhap2Layout.setVerticalGroup(
            pnlDSHangNhap2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlDSPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlDSHangNhap2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDSHangNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlDanhSachNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlDanhSachNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlDSHangNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlDSPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlDSHangNhap2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        String maP = txtMaPhieuNew.getText();
        if(flagCreate == 0 && !maP.isEmpty())
            daoPN.deletePNhan(maP);  
        
        Main_QL frm = new Main_QL();
        frm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void btnTaoMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoMaActionPerformed
        int row = tblDSPhieuNhap.getSelectedRow();
        if(row < 0)
        {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn phiếu nhập hàng", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if(flagCreateKey == 0)
        {
            String maPNhan = createMaPhieu();
            txtMaPhieuNew.setText(maPNhan);
            String maPNhap = (String)tblDSPhieuNhap.getValueAt(row, 0);
            daoPN.insertPNhan(maPNhan, dtoStatic.empKey, maPNhap);
        }
        flagCreateKey = 1;
    }//GEN-LAST:event_btnTaoMaActionPerformed

    private void tblDSPhieuNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSPhieuNhapMouseClicked
        int row = tblDSPhieuNhap.getSelectedRow();
        if(row >= 0)
        {
            String maP = (String)tblDSPhieuNhap.getValueAt(row, 0);
            showCT_PN(maP);
        }
    }//GEN-LAST:event_tblDSPhieuNhapMouseClicked

    private void txtSLuongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSLuongKeyPressed
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9' || evt.getKeyChar() == '\b')
            txtSLuong.setEditable(true);
        else 
        {
            txtSLuong.setEditable(false);
            JOptionPane.showMessageDialog(rootPane, "Chỉ được nhập số", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            
            txtSLuong.setEditable(true);
        }
    }//GEN-LAST:event_txtSLuongKeyPressed

    private void txtGiaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiaKeyPressed
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9' || evt.getKeyChar() == '\b')
            txtGia.setEditable(true);
        else 
        {
            txtGia.setEditable(false);
            JOptionPane.showMessageDialog(rootPane, "Chỉ được nhập số", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            
            txtGia.setEditable(true);
        }
    }//GEN-LAST:event_txtGiaKeyPressed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if(txtMaPhieuNew.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa tạo mã phiếu nhận", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(txtSLuong.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập số lượng mặt hàng", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if(txtGia.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập giá của mặt hàng", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int row = tblCT_PhieuNhap.getSelectedRow();
        if(row < 0)
        {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn mặt hàng cần nhập", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String maMH = (String)tblCT_PhieuNhap.getValueAt(row, 0);
        String maP = txtMaPhieuNew.getText();
        int sLuong = Integer.parseInt(txtSLuong.getText());
        float gia = Float.parseFloat(txtGia.getText());
        if(daoPN.insertCT_PNhan(maP, maMH, sLuong, gia) != 0)
            showCT_PNhan(maP);
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnSuaSLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSLuongActionPerformed
        int selectedRow = tblCT_PhieuNhan.getSelectedRow();
        if(selectedRow < 0)
        {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn mặt hàng để sửa thông tin", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(txtSLuong.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập số lượng mặt hàng", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if(txtGia.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập giá của mặt hàng", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String maMH = (String)tblCT_PhieuNhan.getValueAt(selectedRow, 0);
        String maP = txtMaPhieuNew.getText();
        int sLuong = Integer.parseInt(txtSLuong.getText());
        float gia = Float.parseFloat(txtGia.getText());
        if(daoPN.updateCT_PNhan(maP, maMH, sLuong, gia) != 0)
            showCT_PNhan(maP);
    }//GEN-LAST:event_btnSuaSLuongActionPerformed

    private void tblCT_PhieuNhanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCT_PhieuNhanMouseClicked
        int row = tblCT_PhieuNhan.getSelectedRow();
        if(row >= 0)
        {
            txtSLuong.setText((String)tblCT_PhieuNhan.getValueAt(row, 2));
            txtGia.setText((String)tblCT_PhieuNhan.getValueAt(row, 3));
        }
    }//GEN-LAST:event_tblCT_PhieuNhanMouseClicked

    private void btnLuuPNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuPNhanActionPerformed
        int row = dfTableModelCT_PNhan.getRowCount();
        if(row <= 0)
        {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa thực hiện nhập hàng", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        else
        {
            int selectedRow = tblDSPhieuNhap.getSelectedRow();
            String maPNhap = (String)tblDSPhieuNhap.getValueAt(selectedRow, 0);
            if(!checkNumberOfGoodsImported(maPNhap))
            {
                JOptionPane.showMessageDialog(rootPane, "Bạn vẫn còn mặt hàng chưa nhập", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            else
            {
                int result = JOptionPane.showConfirmDialog(rootPane, "Nhập hàng thành công. Bạn có muốn thoát ?", "Thông báo", JOptionPane.YES_NO_OPTION);
                daoPNhap.updatePNH(maPNhap);
                flagCreate = 1;
                if(result == JOptionPane.YES_OPTION)
                {
                    Main_QL frm = new Main_QL();
                    frm.setVisible(true);
                    this.dispose();
                }
                else
                {
                    this.dispose();
                    QL_NhanHang frm = new QL_NhanHang();
                    frm.setVisible(true);
                }
            }
        }
    }//GEN-LAST:event_btnLuuPNhanActionPerformed

    private boolean checkNumberOfGoodsImported(String maPNhap)
    {
        int count = 0;
        try {
            ResultSet rs = dao.selectData("SELECT COUNT(*) AS SL FROM CT_PHIEUNHAN CT, PHIEUNHANHANG P " +
                                          "WHERE CT.MAPHIEUNHAN = P.MAPHIEUNHAN AND P.MAPHIEUNHAP = '" + maPNhap + "'");
            if(rs.next())
                count = rs.getInt("SL");
            
            if(count == getNumberOfGoodsImport(maPNhap))
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(QL_NhanHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    private int getNumberOfGoodsImport(String maPNhap)
    {
        int count = 0;
        try {
            ResultSet rs = dao.selectData("SELECT COUNT(*) AS SL FROM CT_PHIEUNHAP WHERE MAPHIEUNHAP = '" + maPNhap + "'");
            if(rs.next())
                count = rs.getInt("SL");
        } catch (SQLException ex) {
            Logger.getLogger(QL_NhanHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
    
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
            java.util.logging.Logger.getLogger(QL_NhapHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QL_NhapHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QL_NhapHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QL_NhapHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QL_NhanHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuuPNhan;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSuaSLuong;
    private javax.swing.JButton btnTaoMa;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblGia;
    private javax.swing.JLabel lblMaPhieu;
    private javax.swing.JLabel lblSLuong;
    private javax.swing.JPanel pnlDSHangNhan;
    private javax.swing.JPanel pnlDSHangNhap2;
    private javax.swing.JPanel pnlDSPhieuNhap;
    private javax.swing.JPanel pnlDanhSachNhapHang;
    private javax.swing.JTable tblCT_PhieuNhan;
    private javax.swing.JTable tblCT_PhieuNhap;
    private javax.swing.JTable tblDSPhieuNhap;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtMaPhieuNew;
    private javax.swing.JTextField txtSLuong;
    // End of variables declaration//GEN-END:variables
}
