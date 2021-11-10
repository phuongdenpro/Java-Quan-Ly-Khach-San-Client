package gui;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.*;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.table.*;


public class ThongKeKhachHang_UI extends JFrame{
    JPanel pnMain;
    private JTextField txtMaKH, txtTenKH, txtThanhTien;
    private kDatePicker dpTuNgay, dpDenNgay;
    private DefaultTableModel modelTable;
    private JButton btnThongKe;
    private JTable table;
    private JLabel lbShowMessages;
    private final int SUCCESS = 1, ERROR = 0, NORMAL = 2;
    ImageIcon analyticsIcon = new ImageIcon("data/images/analytics_16.png");
    ImageIcon checkIcon = new ImageIcon("data/images/check2_16.png");
    ImageIcon errorIcon = new ImageIcon("data/images/cancel_16.png");
    

    public static void main(String[] args) {
        new ThongKeKhachHang_UI().setVisible(true);
    }

    
    public ThongKeKhachHang_UI() {
        
        setTitle("Thống Kê Tổng Hợp Khách Hàng");
        setSize(1000, 670);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pnMain = new JPanel();
        pnMain.setLayout(null);
        pnMain.setBounds(0, 0, 1000, 670);

        getContentPane().add(pnMain);

        JLabel lbTitle = new JLabel("Báo Cáo Tổng Hợp Khách Hàng");
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setFont(new Font("Dialog", Font.BOLD, 20));
        lbTitle.setBounds(10, 11, 972, 30);
        pnMain.add(lbTitle);

        JLabel lbMaKH = new JLabel("Mã khách hàng: ");
        lbMaKH.setBounds(10, 52, 110, 16);
        pnMain.add(lbMaKH);

        txtMaKH = new JTextField();
        txtMaKH.setBounds(120, 50, 170, 20);
        pnMain.add(txtMaKH);
        txtMaKH.setColumns(10);

        JLabel lbTuNgay = new JLabel("Từ ngày");
        lbTuNgay.setBounds(10, 80, 55, 16);

        dpTuNgay = new kDatePicker(170);
        dpTuNgay.setBounds(120, 76, 170, 20);
        pnMain.add(dpTuNgay);

        pnMain.add(lbTuNgay);

        JLabel lbTenKH = new JLabel("Tên Khách Hàng:");
        lbTenKH.setBounds(348, 52, 104, 16);
        pnMain.add(lbTenKH);

        JLabel lbDenNgay = new JLabel("Đến ngày:");
        lbDenNgay.setBounds(348, 80, 70, 16);
        pnMain.add(lbDenNgay);

        dpDenNgay = new kDatePicker(170);
        dpDenNgay.setBounds(466, 76, 170, 20);
        pnMain.add(dpDenNgay);

        txtTenKH = new JTextField();
        txtTenKH.setBounds(466, 50, 170, 20);
        pnMain.add(txtTenKH);
        txtTenKH.setColumns(10);

        JPanel pnTable = new JPanel();
        pnTable.setBounds(10, 128, 972, 458);
        pnMain.add(pnTable);
        pnTable.setLayout(new BorderLayout(0, 0));

        // mã hóa đơn phòng
        String[] cols = { "Mã HD", "Mã phòng", "Loại phòng", "Giá phòng", "Ngày CheckIn", "Ngày CheckOut", "Số Ngày",
                "Thành tiền", "Mã KH", "Tên KH" };
        modelTable = new DefaultTableModel(cols, 0) {
            // khóa sửa dữ liệu trực tiếp trên table
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };

        table = new JTable(modelTable);
        JScrollPane scpTableDV = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        pnTable.add(scpTableDV, BorderLayout.CENTER);

        JPanel pnThongKe = new JPanel();
        pnThongKe.setBounds(10, 589, 972, 40);
        pnMain.add(pnThongKe);
        pnThongKe.setLayout(null);

        JLabel lbTongDoanhThu = new JLabel("Tổng doanh thu:");
        lbTongDoanhThu.setBounds(0, 12, 105, 16);
        pnThongKe.add(lbTongDoanhThu);

        txtThanhTien = new JTextField();
        txtThanhTien.setBounds(100, 10, 205, 20);
        txtThanhTien.setHorizontalAlignment(SwingConstants.RIGHT);
        txtThanhTien.setText("0.0");
        txtThanhTien.setEditable(false);
        txtThanhTien.setColumns(10);
        txtThanhTien.setBackground(new Color(127, 255, 212));
        pnThongKe.add(txtThanhTien);

        // JLabel lbA = new JLabel("345678", blueAddIcon, JLabel.LEFT);
        JLabel lbVND = new JLabel("VND");
        lbVND.setBounds(309, 12, 35, 16);
        pnThongKe.add(lbVND);

        btnThongKe = new JButton("Thống kê", analyticsIcon);
        btnThongKe.setBounds(793, 0, 179, 40);
        pnThongKe.add(btnThongKe);

        lbShowMessages = new JLabel("");
        lbShowMessages.setBounds(10, 107, 626, 14);
        pnMain.add(lbShowMessages);

    }

}
