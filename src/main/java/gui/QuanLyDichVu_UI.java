package gui;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.*;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;


public class QuanLyDichVu_UI extends JFrame{

    public JPanel pnMain;
    private JTable table;
    private DefaultTableModel modelTable;
    private JTextField txtTim, txtMaDV, txtTenDV, txtDonGia;
    private JButton btnTim, btnThem, btnSua, btnXoa, btnLamLai, btnXemTatCa;
    private JLabel lbShowMessages;
    private final int SUCCESS = 1, ERROR = 0;
    ImageIcon blueAddIcon = new ImageIcon("data/images/blueAdd_16.png");
    ImageIcon editIcon = new ImageIcon("data/images/edit2_16.png");
    ImageIcon deleteIcon = new ImageIcon("data/images/trash2_16.png");
    ImageIcon refreshIcon = new ImageIcon("data/images/refresh_16.png");
    ImageIcon searchIcon = new ImageIcon("data/images/search_16.png");
    ImageIcon checkIcon = new ImageIcon("data/images/check2_16.png");
    ImageIcon errorIcon = new ImageIcon("data/images/cancel_16.png");
    

    public static void main(String[] args) {
        new QuanLyDichVu_UI().setVisible(true);
    }
    
    public QuanLyDichVu_UI() {
        setSize(1000, 670);
        setTitle("Quản lý dịch vụ");
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pnMain = new JPanel();
        pnMain.setLayout(null);
        pnMain.setBounds(0, 0, 1000, 670);

        getContentPane().add(pnMain);

        JLabel lbTitle = new JLabel("Danh Má»¥c Dá»‹ch Vá»¥");
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setFont(new Font("Dialog", Font.BOLD, 20));
        lbTitle.setBounds(10, 11, 972, 30);
        pnMain.add(lbTitle);

        JPanel pnInfoDV = new JPanel();
        pnInfoDV.setBorder(
                new TitledBorder(null, "ThĂ´ng tin dá»‹ch vá»¥", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnInfoDV.setBounds(10, 46, 391, 582);
        pnMain.add(pnInfoDV);
        pnInfoDV.setLayout(null);

        JLabel lbMaDV = new JLabel("MĂ£ dá»‹ch vá»¥: ");
        lbMaDV.setBounds(12, 23, 80, 20);
        pnInfoDV.add(lbMaDV);

        txtMaDV = new JTextField();
        txtMaDV.setBounds(100, 23, 279, 20);
        txtMaDV.setEditable(false);
        txtMaDV.setColumns(10);
        pnInfoDV.add(txtMaDV);

        JLabel lbTenDV = new JLabel("TĂªn dá»‹ch vá»¥:");
        lbTenDV.setBounds(12, 55, 80, 20);
        pnInfoDV.add(lbTenDV);

        txtTenDV = new JTextField();
        txtTenDV.setBounds(100, 55, 279, 20);
        pnInfoDV.add(txtTenDV);
        txtTenDV.setColumns(10);

        JLabel lbDonGia = new JLabel("Ä�Æ¡n giĂ¡:");
        lbDonGia.setBounds(12, 87, 80, 16);
        pnInfoDV.add(lbDonGia);

        txtDonGia = new JTextField();
        txtDonGia.setText("0.0");
        txtDonGia.setBounds(100, 85, 279, 20);
        pnInfoDV.add(txtDonGia);
        txtDonGia.setColumns(10);

        btnThem = new JButton("ThĂªm", blueAddIcon);
        btnThem.setBounds(12, 143, 108, 26);
        pnInfoDV.add(btnThem);

        btnSua = new JButton("Sá»­a", editIcon);
        btnSua.setBounds(132, 143, 120, 26);
        pnInfoDV.add(btnSua);

        btnXoa = new JButton("XĂ³a", deleteIcon);
        btnXoa.setBounds(264, 143, 115, 26);
        pnInfoDV.add(btnXoa);

        btnLamLai = new JButton("LĂ m láº¡i", refreshIcon);
        btnLamLai.setBounds(132, 185, 120, 26);
        pnInfoDV.add(btnLamLai);

        lbShowMessages = new JLabel("");
        lbShowMessages.setBounds(12, 115, 367, 16);
        lbShowMessages.setForeground(Color.RED);
        pnInfoDV.add(lbShowMessages);

        String[] cols = { "MĂ£ DV", "TĂªn DV", "Ä�Æ¡n GiĂ¡" };
        modelTable = new DefaultTableModel(cols, 0);

        JPanel pnShowDV = new JPanel();
        pnShowDV.setBorder(
                new TitledBorder(null, "Danh sĂ¡ch dá»‹ch vá»¥", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnShowDV.setBounds(402, 46, 580, 582);
        pnShowDV.setLayout(null);
        pnMain.add(pnShowDV);

        JLabel lbTimKiem = new JLabel("TĂªn dá»‹ch vá»¥:");
        lbTimKiem.setBounds(12, 23, 75, 20);
        pnShowDV.add(lbTimKiem);

        txtTim = new JTextField();
        txtTim.setBounds(85, 23, 225, 20);
        pnShowDV.add(txtTim);
        txtTim.setColumns(10);

        btnTim = new JButton("TĂ¬m", searchIcon);
        btnTim.setBounds(322, 20, 90, 26);
        pnShowDV.add(btnTim);

        JPanel pnTableDV = new JPanel();
        pnTableDV.setBounds(12, 55, 556, 515);
        pnShowDV.add(pnTableDV);
        pnTableDV.setLayout(new BorderLayout(0, 0));

        table = new JTable(modelTable) {
            // khĂ³a sá»­a dá»¯ liá»‡u trá»±c tiáº¿p trĂªn table
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };
        JScrollPane scpTableDV = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        pnTableDV.add(scpTableDV, BorderLayout.CENTER);

        btnXemTatCa = new JButton("Xem táº¥t cáº£");
        btnXemTatCa.setBounds(426, 20, 121, 26);
        pnShowDV.add(btnXemTatCa);

        reSizeColumnTable();
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

    private boolean validData() {
        String tenDV = txtTenDV.getText().trim();
        String donGia = txtDonGia.getText().trim();
        if (!(tenDV.length() > 0)) {
            showMessage("Lá»—i: TĂªn khĂ´ng Ä‘Æ°á»£c Ä‘á»ƒ trá»‘ng", txtTenDV);
            return false;
        }
        if (donGia.length() > 0) {
            try {
                Double x = Double.parseDouble(donGia);
                if (x < 0) {
                    showMessage("Lá»—i: Ä�Æ¡n giĂ¡ >= 0", txtDonGia);
                    return false;
                }
            } catch (NumberFormatException ex) {
                showMessage("Lá»—i: Ä�Æ¡n giĂ¡ pháº£i nháº­p sá»‘.", txtDonGia);
                return false;
            }
        }
        return true;
    }

    private boolean validDataTim() {
        String tenDV = txtTim.getText().trim();
        if (!(tenDV.length() > 0)) {
            showMessage("Lá»—i: TĂªn khĂ´ng Ä‘Æ°á»£c Ä‘á»ƒ trá»‘ng", txtTim);
            return false;
        }
        return true;
    }


    private void reSizeColumnTable() {
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(83);
        table.getColumnModel().getColumn(1).setPreferredWidth(350);
        table.getColumnModel().getColumn(2).setPreferredWidth(120);
    }
}
