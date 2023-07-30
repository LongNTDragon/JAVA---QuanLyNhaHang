/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import DAO.MatHang_DAO;
import DAO.NhaHang_DAO;
import DTO.DanhMucHang;
import DTO.NhaCungCap;
import DTO.DVT;
import DTO.MatHang;
import static java.awt.image.ImageObserver.HEIGHT;
import javax.swing.table.DefaultTableModel;
import ql_nhahang.QL_NhanHang;
import ql_nhahang.QL_NhapHang;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import netscape.javascript.JSObject;

/**
 *
 * @author Admin
 */
public class QL_MatHang extends javax.swing.JFrame {

    /**
     * Creates new form QL_MatHang
     */
    
    NhaHang_DAO dao = new NhaHang_DAO();
    MatHang_DAO daoMH = new MatHang_DAO();
    
    DefaultTableModel dfTableModel = new DefaultTableModel();
    
    public QL_MatHang() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        initTable();
        loadCboNCC("");
        loadCboDM("");
        loadCboDVT("");
        loadDataTable();
    }
    
    private void initTable()
    {
        String[] colTitle = {"Mã MH", "Tên MH", "Giá", "Số lượng", "Danh mục", "ĐVT", "Nhà cung cấp"};
        
        dfTableModel.setColumnIdentifiers(colTitle);
        tblDSMH.setModel(dfTableModel);
    }
    
    private void loadCboNCC(String tenNCC)
    {
        cboNCC.removeAllItems();
        int index = 0;
        try {
            ResultSet resultSet = dao.selectData("SELECT * FROM NHACUNGCAP");
            while(resultSet.next())
            {
                String key = resultSet.getString("MANCC");
                String name = resultSet.getString("TENNCC");
                String dChi = resultSet.getString("DCHI");
                String SDT = resultSet.getString("SDT");
                cboNCC.addItem(new NhaCungCap(key, name, dChi, SDT));
                if(name.equals(tenNCC))
                    cboNCC.setSelectedIndex(index);
                index++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main_QL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadCboDM(String tenDM)
    {
        cboDM.removeAllItems();
        int index = 0;
        try {
            ResultSet resultSet = dao.selectData("SELECT * FROM DANHMUCHANG");
            while(resultSet.next())
            {
                String key = resultSet.getString("MADM");
                String name = resultSet.getString("TENDM");
                cboDM.addItem(new DanhMucHang(key, name));
                if(name.equals(tenDM))
                    cboDM.setSelectedIndex(index);
                index++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main_QL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadCboDVT(String tenDVT)
    {
        cboDVT.removeAllItems();
        int index = 0;
        try {
            ResultSet resultSet = dao.selectData("SELECT * FROM DVT");
            while(resultSet.next())
            {
                String key = resultSet.getString("MADVT");
                String name = resultSet.getString("TENDVT");
                cboDVT.addItem(new DVT(key, name));
                if(name.equals(tenDVT))
                    cboDVT.setSelectedIndex(index);
                index++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main_QL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadDataTable()
    {
        dfTableModel.setRowCount(0);
        try {
            ResultSet rs = dao.selectData("{call sp_ShowAll}");
            while (rs.next())
            {
                String maMH = rs.getString("MAMH");
                String nameMH = rs.getString("TENMH");
                float price = rs.getFloat("GIAMH");
                int quantity = rs.getInt("SOLUONG");
                String nameDM = rs.getString("TENDM");
                String nameDVT = rs.getString("TENDVT");
                String nameNCC = rs.getString("TENNCC");
                
                dfTableModel.addRow(new String[]{maMH, nameMH, String.format("%,.0f", price), String.valueOf(quantity), nameDM, nameDVT, nameNCC});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main_QL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean checkMaMH(String maMH) {
        try {
            ResultSet rs = dao.selectData("SELECT * FROM MATHANG WHERE MAMH = '" + maMH + "'");
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QL_MatHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    private boolean checkMaMH_In_CTHD(String maMH) {
        try {
            ResultSet rs = dao.selectData("SELECT * FROM CTHD_MH WHERE MAMH = '" + maMH + "'");
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QL_MatHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    private boolean checkMaMH_In_CTPNhap(String maMH) {
        try {
            ResultSet rs = dao.selectData("SELECT * FROM CT_PHIEUNHAP WHERE MAMH = '" + maMH + "'");
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QL_MatHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    private boolean checkMaMH_In_CTPNhan(String maMH) {
        try {
            ResultSet rs = dao.selectData("SELECT * FROM CT_PHIEUNHAN WHERE MAMH = '" + maMH + "'");
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QL_MatHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public Integer findDataByName()
    {
        dfTableModel.setRowCount(0);
        int count = 0;
        try {
            ResultSet rs = daoMH.findMHByName(txtTimKiem.getText());
            
            while(rs.next())
            {
                String maMH = rs.getString("MAMH");
                String nameMH = rs.getString("TENMH");
                float price = rs.getFloat("GIAMH");
                int quantity = rs.getInt("SOLUONG");
                String nameDM = rs.getString("TENDM");
                String nameDVT = rs.getString("TENDVT");
                String nameNCC = rs.getString("TENNCC");
                
                dfTableModel.addRow(new String[]{maMH, nameMH, String.format("%,.0f", price), String.valueOf(quantity), nameDM, nameDVT, nameNCC});
                count++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QL_NhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
    
    public Integer findDataByKey()
    {
        dfTableModel.setRowCount(0);
        int count = 0;
        try {
            ResultSet rs = daoMH.findMHByKey(txtTimKiem.getText());
            
            while(rs.next())
            {
                String maMH = rs.getString("MAMH");
                String nameMH = rs.getString("TENMH");
                float price = rs.getFloat("GIAMH");
                int quantity = rs.getInt("SOLUONG");
                String nameDM = rs.getString("TENDM");
                String nameDVT = rs.getString("TENDVT");
                String nameNCC = rs.getString("TENNCC");
                
                dfTableModel.addRow(new String[]{maMH, nameMH, String.format("%,.0f", price), String.valueOf(quantity), nameDM, nameDVT, nameNCC});
                count++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QL_NhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlThongTinNhanVien = new javax.swing.JPanel();
        lblMaMH = new javax.swing.JLabel();
        txtMaMH = new javax.swing.JTextField();
        lblTenMH = new javax.swing.JLabel();
        lblSoLuong = new javax.swing.JLabel();
        lblNCC = new javax.swing.JLabel();
        btnTimMH = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        cboNCC = new javax.swing.JComboBox();
        btnTaoMaMH = new javax.swing.JButton();
        lblGia = new javax.swing.JLabel();
        lblDVT = new javax.swing.JLabel();
        lblDanhMuc = new javax.swing.JLabel();
        cboDM = new javax.swing.JComboBox();
        cboDVT = new javax.swing.JComboBox();
        btnShowAll = new javax.swing.JButton();
        txtTenMH = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        txtGia = new javax.swing.JTextField();
        pnlChucNangNhanVien = new javax.swing.JPanel();
        btnThemMH = new javax.swing.JButton();
        btnXoaMH = new javax.swing.JButton();
        btnSuaMH = new javax.swing.JButton();
        spnDSKhachHang = new javax.swing.JScrollPane();
        tblDSMH = new javax.swing.JTable();
        mebMenu = new javax.swing.JMenuBar();
        MenuTrangChu = new javax.swing.JMenu();
        MenuMatHang = new javax.swing.JMenu();
        menuNhapHang = new javax.swing.JMenuItem();
        menuNhanHang = new javax.swing.JMenuItem();
        MenuHoaDon = new javax.swing.JMenu();
        MenuKhachHang = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pnlThongTinNhanVien.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Mặt Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N

        lblMaMH.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblMaMH.setText("Mã MH");

        txtMaMH.setEditable(false);
        txtMaMH.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        lblTenMH.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblTenMH.setText("Tên MH");

        lblSoLuong.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblSoLuong.setText("Số lượng");

        lblNCC.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblNCC.setText("Nhà cung cấp");

        btnTimMH.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnTimMH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/search.png"))); // NOI18N
        btnTimMH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimMHActionPerformed(evt);
            }
        });

        txtTimKiem.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        cboNCC.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        btnTaoMaMH.setText("Tạo");
        btnTaoMaMH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoMaMHActionPerformed(evt);
            }
        });

        lblGia.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblGia.setText("Giá");

        lblDVT.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblDVT.setText("Đơn vị tính");

        lblDanhMuc.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblDanhMuc.setText("Danh mục");

        cboDM.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cboDM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDMActionPerformed(evt);
            }
        });

        cboDVT.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        btnShowAll.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnShowAll.setText("Hiển thị tất cả");
        btnShowAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowAllActionPerformed(evt);
            }
        });

        txtTenMH.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        txtSoLuong.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        txtGia.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        javax.swing.GroupLayout pnlThongTinNhanVienLayout = new javax.swing.GroupLayout(pnlThongTinNhanVien);
        pnlThongTinNhanVien.setLayout(pnlThongTinNhanVienLayout);
        pnlThongTinNhanVienLayout.setHorizontalGroup(
            pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinNhanVienLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongTinNhanVienLayout.createSequentialGroup()
                        .addGroup(pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlThongTinNhanVienLayout.createSequentialGroup()
                                .addComponent(lblMaMH)
                                .addGap(13, 13, 13)
                                .addComponent(txtMaMH, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnTaoMaMH))
                            .addGroup(pnlThongTinNhanVienLayout.createSequentialGroup()
                                .addComponent(lblNCC)
                                .addGap(18, 18, 18)
                                .addComponent(cboNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlThongTinNhanVienLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                                .addComponent(lblDanhMuc)
                                .addGap(18, 18, 18)
                                .addComponent(cboDM, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlThongTinNhanVienLayout.createSequentialGroup()
                                .addGap(98, 98, 98)
                                .addComponent(lblTenMH)
                                .addGap(18, 18, 18)
                                .addComponent(txtTenMH)
                                .addGap(44, 44, 44))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongTinNhanVienLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTimMH, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)))
                .addGroup(pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongTinNhanVienLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(lblSoLuong)
                        .addGap(18, 18, 18)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(lblGia)
                        .addGap(18, 18, 18)
                        .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlThongTinNhanVienLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnShowAll)
                            .addComponent(lblDVT))
                        .addGap(18, 18, 18)
                        .addComponent(cboDVT, 0, 147, Short.MAX_VALUE)
                        .addGap(100, 100, 100))))
        );
        pnlThongTinNhanVienLayout.setVerticalGroup(
            pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenMH, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblMaMH)
                        .addComponent(txtMaMH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTaoMaMH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTenMH)
                        .addComponent(lblSoLuong)
                        .addComponent(txtSoLuong)
                        .addComponent(lblGia)
                        .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addGroup(pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNCC)
                    .addComponent(cboNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDanhMuc)
                    .addComponent(cboDM, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDVT)
                    .addComponent(cboDVT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(pnlThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimMH)
                    .addComponent(btnShowAll))
                .addContainerGap())
        );

        pnlChucNangNhanVien.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnThemMH.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        btnThemMH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/insert.png"))); // NOI18N
        btnThemMH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMHActionPerformed(evt);
            }
        });

        btnXoaMH.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        btnXoaMH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/delete .png"))); // NOI18N
        btnXoaMH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaMHActionPerformed(evt);
            }
        });

        btnSuaMH.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        btnSuaMH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/update.png"))); // NOI18N
        btnSuaMH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaMHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlChucNangNhanVienLayout = new javax.swing.GroupLayout(pnlChucNangNhanVien);
        pnlChucNangNhanVien.setLayout(pnlChucNangNhanVienLayout);
        pnlChucNangNhanVienLayout.setHorizontalGroup(
            pnlChucNangNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChucNangNhanVienLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnlChucNangNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThemMH, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(btnXoaMH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSuaMH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        pnlChucNangNhanVienLayout.setVerticalGroup(
            pnlChucNangNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChucNangNhanVienLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(btnThemMH, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoaMH, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSuaMH, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        tblDSMH.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tblDSMH.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Nhân viên", "Tên Nhân viên", "SĐT", "Chức Vụ"
            }
        ));
        tblDSMH.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblDSMH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSMHMouseClicked(evt);
            }
        });
        spnDSKhachHang.setViewportView(tblDSMH);

        mebMenu.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        MenuTrangChu.setText("Trang chủ");
        MenuTrangChu.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        MenuTrangChu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuTrangChuMouseClicked(evt);
            }
        });
        mebMenu.add(MenuTrangChu);

        MenuMatHang.setText("Mặt hàng");
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

        mebMenu.add(MenuMatHang);

        MenuHoaDon.setText("Hóa Đơn");
        MenuHoaDon.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        MenuHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuHoaDonMouseClicked(evt);
            }
        });
        mebMenu.add(MenuHoaDon);

        MenuKhachHang.setText("Khách Hàng");
        MenuKhachHang.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        MenuKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuKhachHangMouseClicked(evt);
            }
        });
        mebMenu.add(MenuKhachHang);

        setJMenuBar(mebMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlThongTinNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlChucNangNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spnDSKhachHang)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlThongTinNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spnDSKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                    .addComponent(pnlChucNangNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MenuTrangChuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuTrangChuMouseClicked
        Main_QL frmMain = new Main_QL();
        frmMain.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_MenuTrangChuMouseClicked

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

    private void MenuHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuHoaDonMouseClicked
        QL_HoaDon qlhd = new QL_HoaDon();
        qlhd.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_MenuHoaDonMouseClicked

    private void MenuKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuKhachHangMouseClicked
        QL_KhachHang qlkh = new QL_KhachHang();
        qlkh.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_MenuKhachHangMouseClicked

    private void btnTaoMaMHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoMaMHActionPerformed
        txtMaMH.setText(daoMH.Auto_Increasement_MaMH());
        txtTenMH.setText("");
        txtSoLuong.setText("");
        txtGia.setText("");
        txtTenMH.requestFocus();
    }//GEN-LAST:event_btnTaoMaMHActionPerformed

    private void btnThemMHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMHActionPerformed
        DanhMucHang dm = (DanhMucHang) cboDM.getSelectedItem();
        NhaCungCap ncc = (NhaCungCap) cboNCC.getSelectedItem();
        DVT dvt = (DVT)cboDVT.getSelectedItem();
        
        String key = txtMaMH.getText();
        String name = txtTenMH.getText();
        String quantity = txtSoLuong.getText();
        
        String price = txtGia.getText();
        String priceFormated = price.replace(",", "");
        if(key.isEmpty() || name.isEmpty() || quantity.isEmpty() || price.isEmpty())
            JOptionPane.showMessageDialog(this,"Vui lòng nhập đầy đủ thông tin mặt hàng!", "Lỗi nhập liệu!",HEIGHT);
        else
        {
            MatHang mh = new MatHang(key, name, Float.parseFloat(priceFormated), Integer.parseInt(quantity), dm.getMaDM(), dvt.getMaDVT(), ncc.getMaNCC());
            if(checkMaMH(key))
            {
                int result = JOptionPane.showConfirmDialog(this, "Mã mặt hàng đã tồn tại. Bạn có muốn thay đổi thông tin của mặt hàng không?","Xác nhận thay đổi thông tin!", JOptionPane.YES_NO_OPTION);
                if(result == JOptionPane.YES_OPTION)
                {
                    daoMH.updateMH(mh);
                    loadDataTable();
                }
            }
            else
            {
                daoMH.insertMH(mh);
                loadDataTable();
            }
            txtTenMH.setText("");
            txtSoLuong.setText("");
            txtGia.setText("");
            txtTenMH.requestFocus();
        }
    }//GEN-LAST:event_btnThemMHActionPerformed

    private void tblDSMHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSMHMouseClicked
        int row = tblDSMH.getSelectedRow();
        txtMaMH.setText((String)tblDSMH.getValueAt(row, 0));
        txtTenMH.setText((String)tblDSMH.getValueAt(row, 1));
        txtGia.setText((String)tblDSMH.getValueAt(row, 2));
        txtSoLuong.setText((String)tblDSMH.getValueAt(row, 3));
        
        String tenDM = (String)tblDSMH.getValueAt(row, 4);
        loadCboDM(tenDM);
        
        String tenDVT = (String)tblDSMH.getValueAt(row, 5);
        loadCboDVT(tenDVT);
        
        String tenNCC = (String)tblDSMH.getValueAt(row, 6);
        loadCboNCC(tenNCC);
    }//GEN-LAST:event_tblDSMHMouseClicked

    private void cboDMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDMActionPerformed
        
    }//GEN-LAST:event_cboDMActionPerformed

    private void btnSuaMHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaMHActionPerformed
        int row = tblDSMH.getSelectedRow();
        if(row < 0)
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn mặt hàng cần chỉnh sửa thông tin.");
        else
        {
            DanhMucHang dm = (DanhMucHang) cboDM.getSelectedItem();
            NhaCungCap ncc = (NhaCungCap) cboNCC.getSelectedItem();
            DVT dvt = (DVT)cboDVT.getSelectedItem();

            String key = txtMaMH.getText();
            String name = txtTenMH.getText();
            String quantity = txtSoLuong.getText();

            String price = txtGia.getText();
            String priceFormated = price.replace(",", "");
            if(key.isEmpty() || name.isEmpty() || quantity.isEmpty() || price.isEmpty())
                JOptionPane.showMessageDialog(this,"Vui lòng nhập đầy đủ thông tin mặt hàng!", "Lỗi nhập liệu!",HEIGHT);
            else
            {
                MatHang mh = new MatHang(key, name, Float.parseFloat(priceFormated), Integer.parseInt(quantity), dm.getMaDM(), dvt.getMaDVT(), ncc.getMaNCC());
                if(checkMaMH(key))
                {
                    daoMH.updateMH(mh);
                    loadDataTable();
                    txtTenMH.setText("");
                    txtSoLuong.setText("");
                    txtGia.setText("");
                    txtTenMH.requestFocus();
                }
                else
                    JOptionPane.showMessageDialog(rootPane, "Mã mặt hàng không tồn tại.");
            }
        }
    }//GEN-LAST:event_btnSuaMHActionPerformed

    private void btnXoaMHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaMHActionPerformed
        int row = tblDSMH.getSelectedRow();
        if(row < 0)
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn mặt hàng cần xóa.");
        else
        {
            String key = (String)tblDSMH.getValueAt(row, 0);
            if(checkMaMH(key))
            {
                if(checkMaMH_In_CTHD(key) || checkMaMH_In_CTPNhan(key) || checkMaMH_In_CTPNhap(key))
                    JOptionPane.showMessageDialog(rootPane, "Bạn không thể xóa mặt hàng này");
                else
                {
                    daoMH.deleteKH(key);
                    loadDataTable();
                    txtTenMH.setText("");
                    txtSoLuong.setText("");
                    txtGia.setText("");
                    txtTenMH.requestFocus();
                }
            }
            else
                JOptionPane.showMessageDialog(rootPane, "Mã mặt hàng không tồn tại.");
        }
    }//GEN-LAST:event_btnXoaMHActionPerformed

    private void btnShowAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowAllActionPerformed
        loadDataTable();
    }//GEN-LAST:event_btnShowAllActionPerformed

    private void btnTimMHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimMHActionPerformed
        if(txtTimKiem.getText().isEmpty())
            JOptionPane.showMessageDialog(this,"Vui lòng nhập thông tin mặt hàng muốn tìm!", "Lỗi nhập liệu!",HEIGHT);
        else
        {
            int countKey = findDataByKey();
            if(countKey == 0)
            {
                int countName = findDataByName();
                if(countName == 0)
                    JOptionPane.showMessageDialog(this,"Không tìm thấy!", "Lỗi tìm kiếm!",HEIGHT);
                else
                    JOptionPane.showMessageDialog(this,"Tìm thấy " + countName + " kết quả!", "Thành công!",HEIGHT);
            }
            else
                JOptionPane.showMessageDialog(this,"Tìm thấy " + countKey + " kết quả!", "Thành công!",HEIGHT);
        }
    }//GEN-LAST:event_btnTimMHActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Main_QL frmMain = new Main_QL();
        frmMain.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(QL_MatHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QL_MatHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QL_MatHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QL_MatHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QL_MatHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu MenuHoaDon;
    private javax.swing.JMenu MenuKhachHang;
    private javax.swing.JMenu MenuMatHang;
    private javax.swing.JMenu MenuTrangChu;
    private javax.swing.JButton btnShowAll;
    private javax.swing.JButton btnSuaMH;
    private javax.swing.JButton btnTaoMaMH;
    private javax.swing.JButton btnThemMH;
    private javax.swing.JButton btnTimMH;
    private javax.swing.JButton btnXoaMH;
    private javax.swing.JComboBox cboDM;
    private javax.swing.JComboBox cboDVT;
    private javax.swing.JComboBox cboNCC;
    private javax.swing.JLabel lblDVT;
    private javax.swing.JLabel lblDanhMuc;
    private javax.swing.JLabel lblGia;
    private javax.swing.JLabel lblMaMH;
    private javax.swing.JLabel lblNCC;
    private javax.swing.JLabel lblSoLuong;
    private javax.swing.JLabel lblTenMH;
    private javax.swing.JMenuBar mebMenu;
    private javax.swing.JMenuItem menuNhanHang;
    private javax.swing.JMenuItem menuNhapHang;
    private javax.swing.JPanel pnlChucNangNhanVien;
    private javax.swing.JPanel pnlThongTinNhanVien;
    private javax.swing.JScrollPane spnDSKhachHang;
    private javax.swing.JTable tblDSMH;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtMaMH;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenMH;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

}
