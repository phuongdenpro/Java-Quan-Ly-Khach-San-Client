package gui;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;


public class QLPhong_UI extends JFrame{
    public JPanel pnMain;
    private JTextField txtMaLPhong, txtTenLPhong, txtDonGia, txtMaPhong, txtViTri, txtTimLP, txtTimP;
    private DefaultTableModel modelTableLP, modelTableP;
    private JTable tableLP, tableP;
    private JLabel lbShowMessagesP, lbShowMessagesLP;
    private JButton btnThemLP, btnSuaLP, btnXoaLP, btnXoaP, btnLamLaiLP, btnThemP, btnSuaP, btnLamLaiP, btnTimLP,
            btnTimP, btnXemLich, btnXemTatCaLP, btnXemTatCaP;
    private SpinnerNumberModel modelSpinSC, modelSpinSG;
    private JSpinner spinSoGiuong, spinSucChua;
    private final int SUCCESS = 1, ERROR = 0;
    private JComboBox<String> cboTinhTrang, cboLoaiPhong;
    ImageIcon blueAddIcon = new ImageIcon("data/images/blueAdd_16.png");
    ImageIcon editIcon = new ImageIcon("data/images/edit2_16.png");
    ImageIcon deleteIcon = new ImageIcon("data/images/trash2_16.png");
    ImageIcon refreshIcon = new ImageIcon("data/images/refresh_16.png");
    ImageIcon searchIcon = new ImageIcon("data/images/search_16.png");
    ImageIcon calendarIcon = new ImageIcon("data/images/calender_16.png");
    ImageIcon checkIcon = new ImageIcon("data/images/check2_16.png");
    ImageIcon errorIcon = new ImageIcon("data/images/cancel_16.png");
    

    public static void main(String[] args) {
        new QLPhong_UI().setVisible(true);
    }

    
    public QLPhong_UI() {
        setSize(1000, 670);
        setTitle("Quản Lý Phòng Và Loại Phòng");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        pnMain = new JPanel();
        pnMain.setLayout(null);
        getContentPane().add(pnMain, BorderLayout.CENTER);

        JLabel lbTitle = new JLabel("Quản Lý Phòng và Loại Phòng");
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setFont(new Font("Dialog", Font.BOLD, 20));
        lbTitle.setBounds(0, 0, 984, 30);
        pnMain.add(lbTitle);

        JPanel pnTL = new JPanel();
        pnTL.setBorder(
                new TitledBorder(null, "Thông tin loại phòng ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnTL.setBounds(0, 33, 340, 297);
        pnMain.add(pnTL);
        pnTL.setLayout(null);

        JLabel lbMaLPhong = new JLabel("Mã loại phòng: ");
        lbMaLPhong.setBounds(10, 21, 100, 16);
        pnTL.add(lbMaLPhong);

        txtMaLPhong = new JTextField();
        txtMaLPhong.setEditable(false);
        txtMaLPhong.setBounds(115, 19, 210, 20);
        pnTL.add(txtMaLPhong);
        txtMaLPhong.setColumns(10);

        JLabel lbTenLPhong = new JLabel("Tên loại phòng: ");
        lbTenLPhong.setBounds(10, 49, 100, 16);
        pnTL.add(lbTenLPhong);

        txtTenLPhong = new JTextField();
        txtTenLPhong.setBounds(115, 47, 210, 20);
        pnTL.add(txtTenLPhong);
        txtTenLPhong.setColumns(10);

        JLabel lbDonGia = new JLabel("Đơn giá: ");
        lbDonGia.setBounds(12, 77, 98, 16);
        pnTL.add(lbDonGia);

        txtDonGia = new JTextField();
        txtDonGia.setBounds(115, 75, 210, 20);
        pnTL.add(txtDonGia);
        txtDonGia.setColumns(10);

        lbShowMessagesLP = new JLabel("");
        lbShowMessagesLP.setBounds(10, 105, 315, 16);
        pnTL.add(lbShowMessagesLP);

        btnThemLP = new JButton("Thêm", blueAddIcon);
        btnThemLP.setBounds(10, 133, 98, 26);
        pnTL.add(btnThemLP);

        btnSuaLP = new JButton("Sửa", editIcon);
        btnSuaLP.setBounds(225, 133, 98, 26);
        pnTL.add(btnSuaLP);

        btnXoaLP = new JButton("Xóa", deleteIcon);
        btnXoaLP.setBounds(115, 133, 98, 26);
        pnTL.add(btnXoaLP);

        btnLamLaiLP = new JButton("Làm lại", refreshIcon);
        btnLamLaiLP.setBounds(115, 171, 98, 26);
        pnTL.add(btnLamLaiLP);

        JPanel pnBL = new JPanel();
        pnBL.setBorder(new TitledBorder(null, "Thông tin phòng ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnBL.setBounds(0, 333, 340, 297);
        pnMain.add(pnBL);
        pnBL.setLayout(null);

        JLabel lbMaPhong = new JLabel("Mã phòng: ");
        lbMaPhong.setBounds(10, 21, 79, 16);
        pnBL.add(lbMaPhong);

        txtMaPhong = new JTextField();
        txtMaPhong.setBounds(90, 19, 235, 20);
        pnBL.add(txtMaPhong);
        txtMaPhong.setColumns(10);

        JLabel lbSucChua = new JLabel("Sức chứa");
        lbSucChua.setBounds(10, 49, 79, 16);
        pnBL.add(lbSucChua);

        modelSpinSC = new SpinnerNumberModel(1, 1, null, 1);
        modelSpinSG = new SpinnerNumberModel(1, 1, null, 1);

        spinSucChua = new JSpinner(modelSpinSC);
        spinSucChua.setBounds(89, 47, 236, 20);
        pnBL.add(spinSucChua);

        JLabel lbSoGiuong = new JLabel("Số giường: ");
        lbSoGiuong.setBounds(10, 77, 79, 16);
        pnBL.add(lbSoGiuong);

        spinSoGiuong = new JSpinner(modelSpinSG);
        spinSoGiuong.setBounds(90, 75, 235, 20);
        pnBL.add(spinSoGiuong);

        JLabel lbTinhTrang = new JLabel("Tình trạng: ");
        lbTinhTrang.setBounds(10, 133, 79, 16);
        pnBL.add(lbTinhTrang);

        cboTinhTrang = new JComboBox<String>();
        cboTinhTrang.setEnabled(false);
        cboTinhTrang.setBounds(90, 131, 235, 20);
        cboTinhTrang.addItem("Trống");
        cboTinhTrang.addItem("Đã được đặt");
        cboTinhTrang.addItem("Đã cho thuê");
        pnBL.add(cboTinhTrang);

        JLabel lbViTri = new JLabel("Vị trí: ");
        lbViTri.setBounds(10, 105, 55, 16);
        pnBL.add(lbViTri);

        txtViTri = new JTextField();
        txtViTri.setBounds(90, 103, 235, 20);
        pnBL.add(txtViTri);
        txtViTri.setColumns(10);

        lbShowMessagesP = new JLabel("");
        lbShowMessagesP.setBounds(10, 189, 315, 16);
        pnBL.add(lbShowMessagesP);

        btnThemP = new JButton("Thêm", blueAddIcon);
        btnThemP.setBounds(7, 217, 98, 26);
        pnBL.add(btnThemP);

        btnSuaP = new JButton("Sửa", editIcon);
        btnSuaP.setBounds(227, 217, 98, 26);
        pnBL.add(btnSuaP);

        btnLamLaiP = new JButton("Làm lại", refreshIcon);
        btnLamLaiP.setBounds(7, 255, 98, 26);
        pnBL.add(btnLamLaiP);

        JLabel lbLoaiPhong = new JLabel("Loại phòng: ");
        lbLoaiPhong.setBounds(10, 161, 79, 16);
        pnBL.add(lbLoaiPhong);

        cboLoaiPhong = new JComboBox<String>();
        cboLoaiPhong.setBounds(90, 159, 235, 20);
        pnBL.add(cboLoaiPhong);

        btnXemLich = new JButton("Xem lịch đặt phòng", calendarIcon);
        btnXemLich.setBounds(117, 255, 208, 26);
        pnBL.add(btnXemLich);

        btnXoaP = new JButton("Xóa", deleteIcon);
        btnXoaP.setBounds(114, 217, 101, 26);
        pnBL.add(btnXoaP);

        JPanel pnTR = new JPanel();
        pnTR.setBorder(
                new TitledBorder(null, "Danh sách loại phòng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnTR.setBounds(340, 33, 645, 297);
        pnMain.add(pnTR);
        pnTR.setLayout(null);

        JLabel lbTimLP = new JLabel("Tên loại phòng: ");
        lbTimLP.setBounds(12, 21, 100, 16);
        pnTR.add(lbTimLP);

        txtTimLP = new JTextField();
        txtTimLP.setBounds(110, 19, 150, 20);
        pnTR.add(txtTimLP);
        txtTimLP.setColumns(10);

        btnTimLP = new JButton("Tìm", searchIcon);
        btnTimLP.setBounds(265, 16, 98, 26);
        pnTR.add(btnTimLP);

        JPanel pnTableLP = new JPanel();
        pnTableLP.setBounds(12, 49, 621, 242);
        pnTR.add(pnTableLP);
        pnTableLP.setLayout(new BorderLayout(0, 0));

        String[] colsLP = { "Mã loại phòng", "Tên loại phòng", "Đơn giá" };
        modelTableLP = new DefaultTableModel(colsLP, 0) {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };
        tableLP = new JTable(modelTableLP);
        JScrollPane scpTableLP = new JScrollPane(tableLP, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pnTableLP.add(scpTableLP, BorderLayout.CENTER);

        btnXemTatCaLP = new JButton("Xem tất cả");
        btnXemTatCaLP.setBounds(370, 16, 121, 26);
        pnTR.add(btnXemTatCaLP);

        JPanel pnBR = new JPanel();
        pnBR.setBorder(new TitledBorder(null, "Danh sách phòng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnBR.setBounds(340, 333, 644, 297);
        pnMain.add(pnBR);
        pnBR.setLayout(null);

        JLabel lbTimPhong = new JLabel("Mã phòng: ");
        lbTimPhong.setBounds(12, 21, 75, 16);
        pnBR.add(lbTimPhong);

        txtTimP = new JTextField();
        txtTimP.setBounds(110, 19, 150, 20);
        pnBR.add(txtTimP);
        txtTimP.setColumns(10);

        btnTimP = new JButton("Tìm", searchIcon);
        btnTimP.setBounds(265, 16, 98, 26);
        pnBR.add(btnTimP);

        JPanel pnTableP = new JPanel();
        pnTableP.setBounds(12, 49, 620, 236);
        pnBR.add(pnTableP);

        String[] colsP = { "Mã phòng", "Sức chứa", "Số Giường", "Vị trí", "Tình Trạng", "Loại phòng" };
        modelTableP = new DefaultTableModel(colsP, 0) {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };
        pnTableP.setLayout(new BorderLayout(0, 0));
        tableP = new JTable(modelTableP);
        JScrollPane scpTableP = new JScrollPane(tableP, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pnTableP.add(scpTableP);

        btnXemTatCaP = new JButton("Xem tất cả");
        btnXemTatCaP.setBounds(375, 16, 121, 26);
        pnBR.add(btnXemTatCaP);

    }


    private void showMessage(String message, JTextField txt, JLabel lbl) {
        lbl.setForeground(Color.RED);
        txt.requestFocus();
        txt.selectAll();
        lbl.setText(message);
        lbl.setIcon(errorIcon);
    }

    private void showMessage(String message, int type, JLabel lbl) {
        if (type == SUCCESS) {
            lbl.setForeground(Color.GREEN);
            lbl.setIcon(checkIcon);
        } else if (type == ERROR) {
            lbl.setForeground(Color.RED);
            lbl.setIcon(errorIcon);
        } else {
            lbl.setForeground(Color.BLACK);
            lbl.setIcon(null);
        }
        lbl.setText(message);
    }

    private boolean validDataLoaiPhong() {
        String tenLP = txtTenLPhong.getText().trim();
        String donGia = txtDonGia.getText().trim().replace(",", "");

        if (!(tenLP.length() > 0)) {
            showMessage("Lỗi: Tên không được để trống", txtTenLPhong, lbShowMessagesLP);
            return false;
        } else if (tenLP.matches("\\d+")) {
            showMessage("Lỗi: Tên không được chứa số", txtTenLPhong, lbShowMessagesLP);
            return false;
        }
        if (donGia.length() > 0) {
            try {
                Double x = Double.parseDouble(donGia);
                if (x < 0) {
                    showMessage("Lỗi: Đơn giá >= 0", txtDonGia, lbShowMessagesLP);
                    return false;
                }
            } catch (Exception e) {
                showMessage("Lỗi: Đơn giá phải là số", txtDonGia, lbShowMessagesLP);
                return false;
            }
        }
        return true;
    }

    private boolean validDataPhong() {
        String maPhong = txtMaPhong.getText().trim();
        String viTri = txtViTri.getText().trim();
        if (!(maPhong.length() > 0)) {
            showMessage("Lỗi: Mã phòng không được để trống", txtMaPhong, lbShowMessagesP);
            return false;
        }
        if (!(viTri.length() > 0)) {
            showMessage("Lỗi: Mã phòng không được để trống", txtMaPhong, lbShowMessagesP);
            return false;
        }
        return true;
    }


    private String convertTinhTrang(int tinhTrang) {
        String result = "";
        if (tinhTrang == 0)
            result = "Trống";
        else if (tinhTrang == 1)
            result = "Đã được đặt";
        else if (tinhTrang == 2)
            result = "Đã cho thuê";
        return result;
    }
}
