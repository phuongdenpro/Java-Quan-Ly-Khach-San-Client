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
import dao.ChiTietDVDao;
import dao.ChiTietHoaDonPhongDao;
import dao.KhachHangDao;
import model.ChiTietDV;
import model.ChiTietHoaDonPhong;
import model.HoaDonPhong;
import model.KhachHang;
import utils.Currency;
import utils.Ngay;

public class DialogLichDatKhachHang extends JDialog {
	private DefaultTableModel modelTable;
	private JTable table;
	private kDatePicker dpTuNgay, dpDenNgay;
	private JButton btnXem;
	String maPhong = "P303";
	private JLabel lbTitle;
	Client client;
	private List<HoaDonPhong> dshd;
	private DefaultTableModel modelTable2;
	private JTable table2;

	private List<ChiTietDV> dsdv;

	private Date tuNgay;

	private Date toiNgay;
	private int maKH = 0;
	private boolean result = false;

	private List<ChiTietHoaDonPhong> dshdp;
	private KhachHang kh;

	public static void main(final String[] args) throws MalformedURLException, RemoteException, NotBoundException {

		DialogLichDatKhachHang dialog = new DialogLichDatKhachHang();
		// dialog.setMaPhong("P303");
		dialog.setVisible(true);
	}

	public DialogLichDatKhachHang() throws MalformedURLException, RemoteException, NotBoundException {
		try {
			client = new Client();
		} catch (IOException | NotBoundException e) {
			e.printStackTrace();
		}
		renderGUI();
	}

	public void renderGUI() throws MalformedURLException, RemoteException, NotBoundException {
		try {
			client = new Client();
		} catch (IOException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long ml = System.currentTimeMillis();
//	    ml = ml/86400000*86400000;
		Date now = new Date(ml);
		// kh = new KhachHang();
		tuNgay = new Date(ml);
		toiNgay = new Date(ml);
		setTitle("Lịch Đặt Phòng Và Dịch Vụ ");
		setSize(1000, 550);
		setLocationRelativeTo(null);

		JPanel pnMain = new JPanel();
		getContentPane().add(pnMain, BorderLayout.CENTER);
		pnMain.setLayout(new BorderLayout(0, 0));

		JPanel pnTitle = new JPanel();
		pnMain.add(pnTitle, BorderLayout.NORTH);

		lbTitle = new JLabel("Hóa đơn đặt phòng và dịch vụ của khách hàng ");
		lbTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		pnTitle.add(lbTitle);

		JPanel pnView = new JPanel();
		pnMain.add(pnView, BorderLayout.CENTER);
		pnView.setLayout(new BorderLayout(0, 0));

		JPanel pnTable = new JPanel();
		pnView.add(pnTable, BorderLayout.CENTER);
		pnTable.setLayout(new BoxLayout(pnTable, BoxLayout.Y_AXIS));

		String[] cols = { "Mã hóa đơn", "Mã phòng", "Loại phòng", "Giá phòng", "Ngày CheckIn", "Ngày CheckOut",
				"Số Ngày", "Thành tiền", "Mã khách hàng", "Tên khách hàng" };
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

		String[] cols2 = { "Mã hóa đơn", "Mã dịch vụ", "Tên dịch vụ", "Số lượng", "Đơn giá", "Thành Tiền", "Ngày Đặt",
				"Tình trạng", "Mã khách hàng", "Tên khách hàng" };
		modelTable2 = new DefaultTableModel(cols2, 0) {
			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
			}
		};
		table2 = new JTable(modelTable2);
		JScrollPane scpTable2 = new JScrollPane(table2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnTable.add(scpTable2, BorderLayout.CENTER);

		renderData();
	}

	public Date getTuNgay() {
		return tuNgay;
	}

	public void setTuNgay(Date tuNgay) {
		this.tuNgay = tuNgay;
	}

	public Date getToiNgay() {
		return toiNgay;
	}

	public void setToiNgay(Date toiNgay) {
		this.toiNgay = toiNgay;
	}

	public int getMaKH() {
		return maKH;
	}

	public void setMaKH(int maKH) {
		this.maKH = maKH;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
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

	public void renderData() throws MalformedURLException, RemoteException, NotBoundException {
		table.clearSelection();
		modelTable.getDataVector().removeAllElements();
		table2.clearSelection();
		modelTable2.getDataVector().removeAllElements();

		ChiTietDVDao ctdichVuDao = client.getChiTietDVDao();
		ChiTietHoaDonPhongDao cthoaDonPhongDao = client.getChiTietHoaDonPhongDao();
		KhachHangDao khachHangDao = client.getKhachHangDao();
		kh = khachHangDao.getKhachHangByMaKH(maKH);
		if (kh == null) {
			return;
		} else
			lbTitle.setText("Hóa đơn đặt phòng và dịch vụ của khách hàng " + kh.getTenKH());
		if (result == true) {
			dsdv = ctdichVuDao.getListChiTietDVByMaKHAndDate(maKH, tuNgay, toiNgay);
			dshdp = cthoaDonPhongDao.getListChiTietHDPByMaKHAndDate(maKH, tuNgay, toiNgay);
		} else {
			dsdv = ctdichVuDao.getListChiTietDVByMaKH(maKH);
			dshdp = cthoaDonPhongDao.getListChiTietHDPByMaKH(maKH);
		}

		for (int i = 0; i < dshdp.size(); i++) {
			ChiTietHoaDonPhong hdp = dshdp.get(i);
			int soNgay = (int) Ngay.tinhKhoangNgay(hdp.getHoaDonPhong().getNgayGioNhan(),
					hdp.getHoaDonPhong().getNgayGioTra());
			modelTable.addRow(new Object[] { hdp.getHoaDonPhong().getMaHD(), hdp.getPhong().getMaPhong(),
					hdp.getPhong().getLoaiPhong().getTenLoaiPhong(), hdp.getDonGia(),
					hdp.getHoaDonPhong().getNgayGioNhan(), hdp.getHoaDonPhong().getNgayGioTra(), soNgay,
					hdp.getPhong().getLoaiPhong().getDonGia() * soNgay, hdp.getHoaDonPhong().getKhachHang().getMaKH(),
					hdp.getHoaDonPhong().getKhachHang().getTenKH()

			});
		}
		;

		for (int j = 0; j < dsdv.size(); j++) {
			ChiTietDV dichvu = dsdv.get(j);
			String tinhTrang = "";
			if (dichvu.getHoaDonDV().getTinhTrang() == 0)
				tinhTrang = "Chưa thanh toán";
			else if (dichvu.getHoaDonDV().getTinhTrang() == 1)
				tinhTrang = "Đã thanh toán";
			modelTable2.addRow(new Object[] { dichvu.getHoaDonDV().getMaHDDV(), dichvu.getDichVu().getMaDV(),
					dichvu.getDichVu().getTenDV(), dichvu.getSoLuong(), Currency.format(dichvu.getDonGia()),
					Currency.format(dichvu.getSoLuong() * dichvu.getDonGia()),
					formatDate(dichvu.getHoaDonDV().getNgayGioDat()), tinhTrang,
					dichvu.getHoaDonDV().getKhachHang().getMaKH(), dichvu.getHoaDonDV().getKhachHang().getTenKH()

			});
		}
		;
		table.revalidate();
		table.repaint();
		table2.revalidate();
		table2.repaint();
	}
}
