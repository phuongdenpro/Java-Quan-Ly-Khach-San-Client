package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.sql.*;
import java.text.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;


public class QuanLyKhachHang_UI extends JFrame{

    public JPanel pnMain;
    private DefaultTableModel modelTable;
    private JTable tableShowInfo;
    private kDatePicker dpNgayHetHan;
    private JTextField txtMaKH, txtTenKH, txtCMND, txtSoLanDat, txtTim;
    private JButton btnTim, btnThem, btnSua, btnXoa, btnLamLai, btnXemTatCa;
    private JComboBox<String> cboLoaiKhach;
    private JLabel lbShowMessages;
    private final int SUCCESS = 1, ERROR = 0, ADD = 1, UPDATE = 2;
    ImageIcon blueAddIcon = new ImageIcon("data/images/blueAdd_16.png");
    ImageIcon editIcon = new ImageIcon("data/images/edit2_16.png");
    ImageIcon deleteIcon = new ImageIcon("data/images/trash2_16.png");
    ImageIcon refreshIcon = new ImageIcon("data/images/refresh_16.png");
    ImageIcon searchIcon = new ImageIcon("data/images/search_16.png");
    ImageIcon checkIcon = new ImageIcon("data/images/check2_16.png");
    ImageIcon errorIcon = new ImageIcon("data/images/cancel_16.png");

    public QuanLyKhachHang_UI() {
        setSize(1000, 670);
        setTitle("Quản Lý Khách Hàng");
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pnMain = new JPanel();
        pnMain.setLayout(null);
        pnMain.setBounds(0, 0, 1000, 670);

        JLabel lbTitle = new JLabel("Quản Lý Khách Hàng");
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setFont(new Font("Dialog", Font.BOLD, 20));
        lbTitle.setBounds(0, 0, 984, 30);
        pnMain.add(lbTitle);

        getContentPane().add(pnMain);

        JPanel pnThongTinKH = new JPanel();
        pnThongTinKH.setBorder(
                new TitledBorder(null, "Thông tin khách hàng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnThongTinKH.setBounds(0, 41, 359, 588);
        pnMain.add(pnThongTinKH);
        pnThongTinKH.setLayout(null);

        JLabel lbMaKH = new JLabel("Mã khách hàng:");
        lbMaKH.setBounds(10, 21, 100, 14);
        pnThongTinKH.add(lbMaKH);

        txtMaKH = new JTextField();
        txtMaKH.setEditable(false);
        txtMaKH.setBounds(145, 18, 205, 20);
        pnThongTinKH.add(txtMaKH);
        txtMaKH.setColumns(10);

        JLabel lbTenKH = new JLabel("Tên khách hàng:");
        lbTenKH.setBounds(10, 46, 100, 14);
        pnThongTinKH.add(lbTenKH);

        txtTenKH = new JTextField();
        txtTenKH.setBounds(145, 43, 205, 20);
        pnThongTinKH.add(txtTenKH);
        txtTenKH.setColumns(10);

        JLabel lbLoaiKhach = new JLabel("Quốc tịch:");
        lbLoaiKhach.setBounds(10, 124, 105, 14);
        pnThongTinKH.add(lbLoaiKhach);

        cboLoaiKhach = new JComboBox<String>();
        cboLoaiKhach.setBounds(145, 121, 205, 20);
        cboLoaiKhach.addItem("Việt Nam");
        cboLoaiKhach.addItem("Nước ngoài");
        pnThongTinKH.add(cboLoaiKhach);

        JLabel lbCMND = new JLabel("CMND/CCCD/Hộ chiếu:");
        lbCMND.setBounds(10, 71, 136, 14);
        pnThongTinKH.add(lbCMND);

        JLabel lbNgayHetHan = new JLabel("Ngày hết hạn hộ chiếu:");
        lbNgayHetHan.setBounds(10, 95, 136, 16);
        pnThongTinKH.add(lbNgayHetHan);

        txtCMND = new JTextField();
        txtCMND.setBounds(145, 68, 205, 20);
        pnThongTinKH.add(txtCMND);
        txtCMND.setColumns(10);

        dpNgayHetHan = new kDatePicker(205);
        dpNgayHetHan.setBounds(145, 93, 205, 20);
        pnThongTinKH.add(dpNgayHetHan);

        JLabel lbSoLanDat = new JLabel("Số lần đặt phòng:");
        lbSoLanDat.setBounds(10, 150, 112, 16);
        pnThongTinKH.add(lbSoLanDat);

        txtSoLanDat = new JTextField();
        txtSoLanDat.setText("0");
        txtSoLanDat.setBounds(145, 148, 205, 20);
        pnThongTinKH.add(txtSoLanDat);
        txtSoLanDat.setColumns(10);

        btnThem = new JButton("Thêm", blueAddIcon);
        btnThem.setBounds(10, 207, 98, 26);
        pnThongTinKH.add(btnThem);

        btnSua = new JButton("Sửa", editIcon);
        btnSua.setBounds(125, 207, 98, 26);
        pnThongTinKH.add(btnSua);

        btnXoa = new JButton("Xóa", deleteIcon);
        btnXoa.setBounds(235, 207, 98, 26);
        pnThongTinKH.add(btnXoa);

        btnLamLai = new JButton("Làm lại", refreshIcon);
        btnLamLai.setBounds(125, 245, 98, 26);
        pnThongTinKH.add(btnLamLai);

        lbShowMessages = new JLabel("");
        lbShowMessages.setBounds(10, 179, 340, 16);
        pnThongTinKH.add(lbShowMessages);

        JPanel pbTableKH = new JPanel();
        pbTableKH.setBorder(
                new TitledBorder(null, "Danh sách khách hàng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pbTableKH.setBounds(360, 41, 624, 588);
        pnMain.add(pbTableKH);
        pbTableKH.setLayout(null);

        JLabel lbTimTenKH = new JLabel("Tên khách hàng:");
        lbTimTenKH.setBounds(12, 23, 106, 16);
        pbTableKH.add(lbTimTenKH);

        txtTim = new JTextField();
        txtTim.setBounds(118, 21, 200, 20);
        pbTableKH.add(txtTim);
        txtTim.setColumns(10);

        btnTim = new JButton("Tìm", searchIcon);
        btnTim.setBounds(323, 18, 80, 26);
        pbTableKH.add(btnTim);

        JPanel pnShowTableKH = new JPanel();
        pnShowTableKH.setBounds(12, 49, 600, 527);
        pbTableKH.add(pnShowTableKH);
        pnShowTableKH.setLayout(new BorderLayout(0, 0));

        String[] cols = { "Mã KH", "Tên KH", "CMND", "Ngày hết hạn", "Loại KH", "Số lần đặt phòng" };
        modelTable = new DefaultTableModel(cols, 0) {
            // khóa sửa dữ liệu trực tiếp trên table
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };
        tableShowInfo = new JTable(modelTable);
        JScrollPane scpShowTableKH = new JScrollPane(tableShowInfo, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pnShowTableKH.add(scpShowTableKH, BorderLayout.CENTER);

        btnXemTatCa = new JButton("Xem tất cả");
        btnXemTatCa.setBounds(411, 18, 121, 26);
        pbTableKH.add(btnXemTatCa);

    }

    public static void main(String[] args) {
        new QuanLyKhachHang_UI().setVisible(true);
    }

    private void showMessage(String message, JTextField txt) {
        lbShowMessages.setForeground(Color.RED);
        txt.requestFocus();
        txt.selectAll();
        lbShowMessages.setText(message);
        lbShowMessages.setIcon(errorIcon);
    }

    private void showMessage(String message, int type) {
        if (type == SUCCESS) {
            lbShowMessages.setForeground(Color.GREEN);
            lbShowMessages.setIcon(checkIcon);
        } else if (type == ERROR) {
            lbShowMessages.setForeground(Color.RED);
            lbShowMessages.setIcon(errorIcon);
        } else {
            lbShowMessages.setForeground(Color.BLACK);
            lbShowMessages.setIcon(null);
        }
        lbShowMessages.setText(message);
    }

    private boolean validData(int type) {
        
        return true;
    }

    private boolean validDataTim() {
        String TenKH = txtTim.getText().trim();
        if (!(TenKH.length() > 0)) {
            showMessage("Lỗi: Tên không được để trống", txtTim);
            return false;
        }
        return true;
    }


    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
        return sdf.format(date);
    }

    private void reSizeColumnTable() {
        // table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableShowInfo.getColumnModel().getColumn(0).setPreferredWidth(40);
        tableShowInfo.getColumnModel().getColumn(1).setPreferredWidth(115);
        tableShowInfo.getColumnModel().getColumn(2).setPreferredWidth(70);
        tableShowInfo.getColumnModel().getColumn(3).setPreferredWidth(85);
        tableShowInfo.getColumnModel().getColumn(4).setPreferredWidth(70);
        tableShowInfo.getColumnModel().getColumn(5).setPreferredWidth(80);
    }
}
