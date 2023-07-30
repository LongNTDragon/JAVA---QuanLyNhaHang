/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import DAO.HoaDon_DAO;
import DAO.NhaHang_DAO;
import DTO.HoaDon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import ql_nhahang.QL_NhanHang;
import ql_nhahang.QL_NhapHang;

/**
 *
 * @author tphan
 */
public class QL_HoaDon extends javax.swing.JFrame {

    /**
     * Creates new form QL_HoaDon
     */
    HoaDon_DAO dao=new HoaDon_DAO();
    NhaHang_DAO exec=new NhaHang_DAO();
    DefaultTableModel tblModel =new DefaultTableModel();
    DefaultTableModel tblModel2 =new DefaultTableModel();
    String tinhTrang="";
    public void loadData(){
        tblModel.setRowCount(0);
        try {
            ResultSet resultSet =exec.selectData("exec sp_SelectAllHD");
            while(resultSet.next())
            {
                if(resultSet.getInt(6)==1)
                    tinhTrang="Đã thanh toán";
                else
                    tinhTrang="Chưa thanh toán";
                tblModel.addRow(new String[]{resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),String.format("%,.0f",resultSet.getFloat(5))+" VND",tinhTrang});
            }
        } catch (SQLException ex) {
            Logger.getLogger(QL_HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void loadTblChiTietHoaDon_MatHang(ResultSet resultSet)
    {
        tblModel2.setRowCount(0);
     
            try {
                while(resultSet.next())
                {
                    tblModel2.addRow(new String[]{resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),String.format("%,.0f",resultSet.getFloat(5))+" VND",String.format("%,.0f",resultSet.getFloat(6))+" VND"});
                }
            } catch (Exception ex) {
                Logger.getLogger(QL_HoaDon.class.getName()).log(Level.SEVERE, null, ex);
            }
        tblChiTietHoaDon_MHang.setModel(tblModel2);

    }
    public QL_HoaDon() {
        initComponents();
        String[]header={"Mã hóa đơn","Mã nhân viên","Mã khách hàng", "Ngày lập", "Tổng tiền", "Tình trạng"};
        tblModel.setColumnIdentifiers(header);
        tblDSHoaDon.setModel(tblModel);
        txtMAHD.setEnabled(false);
        txtSoBan.setEnabled(false);
        txtTenKhachHang.setEnabled(false);
        txtTenNV.setEnabled(false);
        txtNgayLap.setEnabled(false);
        String[]header2={"Mã mặt hàng","Tên mặt hàng","Số lượng","Đơn vị","Giá", "Tổng tiền",};
        tblModel2.setColumnIdentifiers(header2);;
        
        tblChiTietHoaDon_MHang.setModel(tblModel2);
        loadData();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlThongTinHoaDon = new javax.swing.JPanel();
        inputSearch = new javax.swing.JTextField();
        btnTimHoaDon = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDSHoaDon = new javax.swing.JTable();
        btnRefesh = new javax.swing.JButton();
        pnlChiTietHoaDon = new javax.swing.JPanel();
        txt_MaHD = new javax.swing.JLabel();
        lblChiTietMaKhachHang = new javax.swing.JLabel();
        lblChiTietNgayLap = new javax.swing.JLabel();
        lblChiTietThanhTien = new javax.swing.JLabel();
        txtChiTietThanhTien = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblChiTietHoaDon_MHang = new javax.swing.JTable();
        lblChiTietMaHoaDon1 = new javax.swing.JLabel();
        lblChiTietMaHoaDon2 = new javax.swing.JLabel();
        txtMAHD = new javax.swing.JTextField();
        txtSoBan = new javax.swing.JTextField();
        txtNgayLap = new javax.swing.JTextField();
        txtTenNV = new javax.swing.JTextField();
        txtTenKhachHang = new javax.swing.JTextField();
        txtTotal = new javax.swing.JLabel();
        pnlTongDoanhThu = new javax.swing.JPanel();
        btnInTongDoanhThu = new javax.swing.JButton();
        lblTongDoanhThu = new javax.swing.JLabel();
        pnlTongTienDoanhThu = new javax.swing.JPanel();
        txtTongDoanhThu = new javax.swing.JLabel();
        mebMenu = new javax.swing.JMenuBar();
        MenuHeThong = new javax.swing.JMenu();
        MenuMatHang = new javax.swing.JMenu();
        menuNhapHang = new javax.swing.JMenuItem();
        menuNhanHang = new javax.swing.JMenuItem();
        MenuNhanVien = new javax.swing.JMenu();
        MenuKhachHang = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pnlThongTinHoaDon.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Hóa Đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N

        inputSearch.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        btnTimHoaDon.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnTimHoaDon.setText("Tìm");
        btnTimHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimHoaDonActionPerformed(evt);
            }
        });

        tblDSHoaDon.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblDSHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Hóa Đơn", "Mã Nhân Viên", "Mã Khách Hàng", "Ngày Lập", "Tổng  Tiền", "Tình Trạng"
            }
        ));
        tblDSHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDSHoaDon);

        btnRefesh.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnRefesh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/refresh.png"))); // NOI18N
        btnRefesh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefeshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlThongTinHoaDonLayout = new javax.swing.GroupLayout(pnlThongTinHoaDon);
        pnlThongTinHoaDon.setLayout(pnlThongTinHoaDonLayout);
        pnlThongTinHoaDonLayout.setHorizontalGroup(
            pnlThongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(pnlThongTinHoaDonLayout.createSequentialGroup()
                        .addComponent(inputSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTimHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRefesh, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlThongTinHoaDonLayout.setVerticalGroup(
            pnlThongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(inputSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTimHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlThongTinHoaDonLayout.createSequentialGroup()
                        .addComponent(btnRefesh)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlChiTietHoaDon.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi Tiết Hóa Đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N

        txt_MaHD.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txt_MaHD.setText("Mã hóa đơn:");

        lblChiTietMaKhachHang.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblChiTietMaKhachHang.setText("Số bàn:");

        lblChiTietNgayLap.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblChiTietNgayLap.setText("Ngày lập:");

        lblChiTietThanhTien.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblChiTietThanhTien.setText("Tổng tiền:");

        txtChiTietThanhTien.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        tblChiTietHoaDon_MHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblChiTietHoaDon_MHang);

        lblChiTietMaHoaDon1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblChiTietMaHoaDon1.setText("Tên nhân viên:");

        lblChiTietMaHoaDon2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblChiTietMaHoaDon2.setText("Tên khách hàng:");

        txtTotal.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtTotal.setText("VND");

        javax.swing.GroupLayout pnlChiTietHoaDonLayout = new javax.swing.GroupLayout(pnlChiTietHoaDon);
        pnlChiTietHoaDon.setLayout(pnlChiTietHoaDonLayout);
        pnlChiTietHoaDonLayout.setHorizontalGroup(
            pnlChiTietHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChiTietHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlChiTietHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlChiTietHoaDonLayout.createSequentialGroup()
                        .addGap(646, 646, 646)
                        .addComponent(txtChiTietThanhTien)
                        .addContainerGap())
                    .addGroup(pnlChiTietHoaDonLayout.createSequentialGroup()
                        .addGroup(pnlChiTietHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlChiTietHoaDonLayout.createSequentialGroup()
                                .addComponent(lblChiTietThanhTien)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlChiTietHoaDonLayout.createSequentialGroup()
                                .addComponent(txt_MaHD)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMAHD, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblChiTietMaKhachHang)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSoBan, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblChiTietNgayLap)
                                .addGap(18, 18, 18)
                                .addComponent(txtNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlChiTietHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlChiTietHoaDonLayout.createSequentialGroup()
                                    .addComponent(lblChiTietMaHoaDon1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblChiTietMaHoaDon2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        pnlChiTietHoaDonLayout.setVerticalGroup(
            pnlChiTietHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChiTietHoaDonLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlChiTietHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlChiTietHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblChiTietMaKhachHang)
                        .addComponent(lblChiTietNgayLap)
                        .addComponent(txtNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSoBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlChiTietHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_MaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMAHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlChiTietHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblChiTietMaHoaDon2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblChiTietMaHoaDon1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlChiTietHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblChiTietThanhTien)
                    .addComponent(txtTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtChiTietThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlTongDoanhThu.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnInTongDoanhThu.setText("Tính");
        btnInTongDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInTongDoanhThuActionPerformed(evt);
            }
        });

        lblTongDoanhThu.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTongDoanhThu.setText("Tổng Doanh Thu:");

        pnlTongTienDoanhThu.setBorder(new javax.swing.border.MatteBorder(null));

        txtTongDoanhThu.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        txtTongDoanhThu.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTongDoanhThu.setText("VND");

        javax.swing.GroupLayout pnlTongTienDoanhThuLayout = new javax.swing.GroupLayout(pnlTongTienDoanhThu);
        pnlTongTienDoanhThu.setLayout(pnlTongTienDoanhThuLayout);
        pnlTongTienDoanhThuLayout.setHorizontalGroup(
            pnlTongTienDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTongTienDoanhThuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(155, 155, 155))
        );
        pnlTongTienDoanhThuLayout.setVerticalGroup(
            pnlTongTienDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTongTienDoanhThuLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(txtTongDoanhThu)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout pnlTongDoanhThuLayout = new javax.swing.GroupLayout(pnlTongDoanhThu);
        pnlTongDoanhThu.setLayout(pnlTongDoanhThuLayout);
        pnlTongDoanhThuLayout.setHorizontalGroup(
            pnlTongDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTongDoanhThuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTongDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlTongDoanhThuLayout.createSequentialGroup()
                        .addComponent(lblTongDoanhThu)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlTongDoanhThuLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(pnlTongTienDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnInTongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(739, 739, 739))))
        );
        pnlTongDoanhThuLayout.setVerticalGroup(
            pnlTongDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTongDoanhThuLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblTongDoanhThu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTongDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnInTongDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTongTienDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mebMenu.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        MenuHeThong.setText("Trang chủ");
        MenuHeThong.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        MenuHeThong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenuHeThongMouseClicked(evt);
            }
        });
        mebMenu.add(MenuHeThong);

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

        setJMenuBar(mebMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlThongTinHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlChiTietHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 633, Short.MAX_VALUE)
                    .addComponent(pnlTongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlThongTinHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlChiTietHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnlTongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblDSHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSHoaDonMouseClicked
        // TODO add your handling code here:
        int row=tblDSHoaDon.getSelectedRow();
        String maHD=(String)tblDSHoaDon.getValueAt(row, 0);
        txtMAHD.setText(maHD);
        txtNgayLap.setText((String)tblDSHoaDon.getValueAt(row, 3));
        try {
            ResultSet resultSet =dao.getSelectedMAHD(maHD);
            while(resultSet.next())
            {
                txtSoBan.setText(resultSet.getString(1));
                txtTenNV.setText(resultSet.getString(2));
                txtTenKhachHang.setText(resultSet.getString(3));
                loadTblChiTietHoaDon_MatHang(dao.getSelectedMHbyMAHD(maHD));
                txtTotal.setText((String)tblDSHoaDon.getValueAt(row, 4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QL_HoaDon.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin hóa đơn", "Lỗi", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_tblDSHoaDonMouseClicked

    private void btnInTongDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInTongDoanhThuActionPerformed
        // TODO add your handling code here:
        try{
            ResultSet resultSet =exec.selectData("exec sp_GetGrandTotal");
            while(resultSet.next())
            {
                txtTongDoanhThu.setText(String.format("%,.0f",resultSet.getFloat(1))+" VND");
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(QL_HoaDon.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Không thể tính được doanh thu", "Lỗi", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnInTongDoanhThuActionPerformed

     public Integer updateSearchedData()
    {
        tblModel.setRowCount(0);
        int count=0;
        try {
            ResultSet resultSet =dao.findHD(inputSearch.getText());
            
            while(resultSet.next())
            {
                tblModel.addRow(new String[]{resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),String.format("%,.0f",resultSet.getFloat(5))+" VND",tinhTrang});
                count++;
            }
            return count;
        } catch (SQLException ex) {
            Logger.getLogger(QL_KhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
    private void btnTimHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimHoaDonActionPerformed
        // TODO add your handling code here:
        if(inputSearch.getText().isEmpty())
            JOptionPane.showMessageDialog(this,"Vui lòng nhập thông tin hóa đơn muốn tìm!", "Lỗi nhập liệu!",HEIGHT);
        else
        {
            int count=updateSearchedData();
            if(count==0)
                JOptionPane.showMessageDialog(this,"Không tìm thấy!", "Lỗi tìm kiếm!",HEIGHT);
            else
                JOptionPane.showMessageDialog(this,"Tìm thấy "+count+" kết quả!", "Thành công!",HEIGHT);
        }
    }//GEN-LAST:event_btnTimHoaDonActionPerformed

    private void btnRefeshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefeshActionPerformed
        // TODO add your handling code here:
        loadData();
    }//GEN-LAST:event_btnRefeshActionPerformed

    private void MenuNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuNhanVienMouseClicked
        QL_NhanVien qlnv = new QL_NhanVien();
        qlnv.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_MenuNhanVienMouseClicked

    private void MenuKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuKhachHangMouseClicked
        // TODO add your handling code here:
        QL_KhachHang qlkh = new QL_KhachHang();
        qlkh.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_MenuKhachHangMouseClicked

    private void MenuHeThongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuHeThongMouseClicked
        // TODO add your handling code here:
        Main_QL frm = new Main_QL();
        frm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_MenuHeThongMouseClicked

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
            java.util.logging.Logger.getLogger(QL_HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QL_HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QL_HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QL_HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QL_HoaDon().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu MenuHeThong;
    private javax.swing.JMenu MenuKhachHang;
    private javax.swing.JMenu MenuMatHang;
    private javax.swing.JMenu MenuNhanVien;
    private javax.swing.JButton btnInTongDoanhThu;
    private javax.swing.JButton btnRefesh;
    private javax.swing.JButton btnTimHoaDon;
    private javax.swing.JTextField inputSearch;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblChiTietMaHoaDon1;
    private javax.swing.JLabel lblChiTietMaHoaDon2;
    private javax.swing.JLabel lblChiTietMaKhachHang;
    private javax.swing.JLabel lblChiTietNgayLap;
    private javax.swing.JLabel lblChiTietThanhTien;
    private javax.swing.JLabel lblTongDoanhThu;
    private javax.swing.JMenuBar mebMenu;
    private javax.swing.JMenuItem menuNhanHang;
    private javax.swing.JMenuItem menuNhapHang;
    private javax.swing.JPanel pnlChiTietHoaDon;
    private javax.swing.JPanel pnlThongTinHoaDon;
    private javax.swing.JPanel pnlTongDoanhThu;
    private javax.swing.JPanel pnlTongTienDoanhThu;
    private javax.swing.JTable tblChiTietHoaDon_MHang;
    private javax.swing.JTable tblDSHoaDon;
    private javax.swing.JTextField txtChiTietThanhTien;
    private javax.swing.JTextField txtMAHD;
    private javax.swing.JTextField txtNgayLap;
    private javax.swing.JTextField txtSoBan;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JLabel txtTongDoanhThu;
    private javax.swing.JLabel txtTotal;
    private javax.swing.JLabel txt_MaHD;
    // End of variables declaration//GEN-END:variables
}