package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.*;

import app.Client;
import model.HoaDonPhong;
import utils.Ngay;


public class DialogLichDatPhong extends JDialog{
    private DefaultTableModel modelTable;
    private JTable table;
    private kDatePicker dpTuNgay, dpDenNgay;
    private JButton btnXem;
    String maPhong = "P303";
    private JLabel lbTitle;
    Client client;
	private List<HoaDonPhong> dshd;

    public static void main(final String[] args) {
        DialogLichDatPhong dialog = new DialogLichDatPhong();
        dialog.setMaPhong("P303");
        dialog.setVisible(true);
    }

    public DialogLichDatPhong() {
    	try {
			client = new Client();
		} catch (IOException | NotBoundException e) {
			e.printStackTrace();
		}
    	renderGUI();
    }
        
    public void renderGUI() {
        setTitle("Lịch Đặt Phòng ");
        setSize(950, 450);
        setLocationRelativeTo(null);

        JPanel pnMain = new JPanel();
        getContentPane().add(pnMain, BorderLayout.CENTER);
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
        btnXem.setBackground(Color.WHITE);
        btnXem.setBounds(482, 3, 92, 26);
        pnInput.add(btnXem);
        
        JButton btnLamMoi = new JButton("Làm mới");
        btnLamMoi.setBackground(Color.WHITE);
        btnLamMoi.setBounds(588, 3, 92, 26);
        pnInput.add(btnLamMoi);

        JPanel pnTable = new JPanel();
        pnView.add(pnTable, BorderLayout.CENTER);
        pnTable.setLayout(new BorderLayout(0, 0));

        String[] cols = { "Mã hóa đơn", "Ngày đến", "Ngày đi", "Tình trạng", "Mã KH", "Tên KH", "Số điện thoại"};
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

        
        btnXem.addActionListener((e) -> {
        	renderDataNgay();
        });
        
        btnLamMoi.addActionListener((e) -> {
        	renderData();
        });
        
        renderData();
    }
    
    public void renderData() {
    	try {
			dshd = client.getHoaDonPhongDao().getListHDPByMaPhong(maPhong);
		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			e.printStackTrace();
		}
    	
    	lbTitle.setText("Lịch đặt phòng "+ maPhong);
    	
    	modelTable.getDataVector().removeAllElements();
    	dshd.forEach(hdp -> {
    		modelTable.addRow(new Object[] {
    				hdp.getMaHD(),
    				hdp.getNgayGioNhan(),
    				hdp.getNgayGioTra(),
    				convertTinhTrang(hdp.getTinhTrang()),
    				hdp.getKhachHang().getMaKH(),
    				hdp.getKhachHang().getTenKH(),
    				hdp.getKhachHang().getSoDienThoai()
    		});
    	});
    	table.revalidate();
    	table.repaint();
    }
    
    public void renderDataNgay() {
    	Date d1 = Ngay.homNay();
    	Date d2 = Ngay.homNay();
    	try {
			d1 = dpTuNgay.getFullDate();
			d2 = dpDenNgay.getFullDate();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
    	try {
			dshd = client.getHoaDonPhongDao().getListHDPByMaPhong(maPhong, d1, d2);
		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			e.printStackTrace();
		}
    	
    	lbTitle.setText("Lịch đặt phòng "+ maPhong);
    	
    	modelTable.getDataVector().removeAllElements();
    	dshd.forEach(hdp -> {
    		modelTable.addRow(new Object[] {
    				hdp.getMaHD(),
    				hdp.getNgayGioNhan(),
    				hdp.getNgayGioTra(),
    				convertTinhTrang(hdp.getTinhTrang()),
    				hdp.getKhachHang().getMaKH(),
    				hdp.getKhachHang().getTenKH(),
    				hdp.getKhachHang().getSoDienThoai()
    		});
    	});
    	table.revalidate();
    	table.repaint();
    }


    public void setMaPhong(String maPhong) {
    	this.maPhong = maPhong;
    }

    private String convertTinhTrang(int tinhTrang) {
        String result = "";
        if (tinhTrang == 0)
            result = "Chưa nhận phòng";
        else if (tinhTrang == 1)
            result = "Đã nhận phòng";
        else if (tinhTrang == 2)
            result = "Đã trả phòng";
        return result;
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
