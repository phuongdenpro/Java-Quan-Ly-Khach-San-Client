package gui;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;


public class DialogLichDatPhong extends JDialog{
    private DefaultTableModel modelTable;
    private JTable table;
    private kDatePicker dpTuNgay, dpDenNgay;
    private JButton btnXem;
    String maPhong = "";
    private JLabel lbTitle;

    public DialogLichDatPhong() {
        
        setTitle("Lịch Đặt Phòng ");
        setSize(950, 450);
        setLocationRelativeTo(null);

        JPanel pnMain = new JPanel();
        this.add(pnMain, BorderLayout.CENTER);
        pnMain.setLayout(new BorderLayout(0, 0));

        JPanel pnTitle = new JPanel();
        pnMain.add(pnTitle, BorderLayout.NORTH);

        lbTitle = new JLabel("Lịch Đặt Phòng ");
        lbTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        pnTitle.add(lbTitle);

        JPanel pnView = new JPanel();
        pnMain.add(pnView, BorderLayout.CENTER);
        pnView.setLayout(new BorderLayout(0, 0));

        JPanel pnInput = new JPanel();
        pnView.add(pnInput, BorderLayout.NORTH);
        pnInput.setLayout(null);
        pnInput.setPreferredSize(new Dimension(pnView.getWidth(), 30));

        JLabel lbTuNgay = new JLabel("Từ ngày: ");
        lbTuNgay.setBounds(12, 6, 70, 20);
        pnInput.add(lbTuNgay);

        dpTuNgay = new kDatePicker();
        dpTuNgay.setBounds(80, 6, 150, 20);
        pnInput.add(dpTuNgay);

        JLabel lbDenNgay = new JLabel("Đến ngày: ");
        lbDenNgay.setBounds(248, 6, 70, 20);
        pnInput.add(lbDenNgay);

        dpDenNgay = new kDatePicker();
        dpDenNgay.setBounds(320, 6, 150, 20);
        pnInput.add(dpDenNgay);

        btnXem = new JButton("Xem");
        btnXem.setBounds(482, 3, 92, 26);
        pnInput.add(btnXem);

        JPanel pnTable = new JPanel();
        pnView.add(pnTable, BorderLayout.CENTER);
        pnTable.setLayout(new BorderLayout(0, 0));

        String[] cols = { "Mã phòng", "Sức chứa", "Số Giường", "Vị trí", "Tình Trạng", "Loại phòng", "Đơn giá",
                "Ngày đặt", "Ngày trả", "Mã KH", "Tên KH" };
        modelTable = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };
        table = new JTable(modelTable);
        JScrollPane scpTable = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pnTable.add(scpTable, BorderLayout.CENTER);

        
        DocDuLieuVaoTable();
    }

    public static void main(final String[] args) {
        new DialogLichDatPhong().setVisible(true);
    }

    public void setMaPhong(String maPhong) {
    }

    private void DocDuLieuVaoTable() {
        
    }

    private String formatDate(Date date) {
        if (date == null)
            return "Chưa cập nhật";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(date);
    }

    public boolean validData() throws ParseException {
        Date tuNgay = dpTuNgay.getFullDate();
        Date denNgay = dpDenNgay.getFullDate();
        Date toDay = dpTuNgay.getValueToDay();
        if (tuNgay.before(toDay)) {
            return false;
        }
        if (denNgay.before(toDay) || denNgay.before(tuNgay)) {
            return false;
        }
        return true;
    }
}
