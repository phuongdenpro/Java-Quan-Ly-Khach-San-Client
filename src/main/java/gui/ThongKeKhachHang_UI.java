package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import app.Client;
import dao.ChiTietDVDao;
import dao.ChiTietHoaDonPhongDao;
import dao.HoaDonDVDao;
import dao.HoaDonPhongDao;
import dao.KhachHangDao;
import dao.PhongDao;
import model.ChiTietDV;
import model.ChiTietHoaDonPhong;
import model.HoaDonDV;
import model.HoaDonPhong;
import model.KhachHang;
import model.Phong;
import utils.Currency;
import utils.Ngay;

public class ThongKeKhachHang_UI extends JFrame implements ActionListener, MouseListener {

	private int soLuongSP = 0;

	private JPanel contentPane;
	private JTextField txtTuNgay;
	private JTextField txtToiNgay;

	private DefaultTableModel model;
	private JTable table;
	DialogDatePicker f = new DialogDatePicker();
	private kDatePicker dpTuNgay;
	private kDatePicker dpToiNgay;
	private JComboBox comboBox;
	private JComboBox cboLoaiTK;

	private JLabel lblTongSo;

	private JLabel lblTongSoTien;

	private int tongSoLanMua;

	private int tongSoTien;

	private DefaultComboBoxModel<Integer> modelLimit;

	private JComboBox cboLimit;


	private Client client;

	private List<KhachHang> dskh;

	private List<ChiTietDV> dsdv;

	private ChiTietDVDao ctDVdao;

	private List<ChiTietHoaDonPhong> dshdp;

	public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException{
		new ThongKeKhachHang_UI().setVisible(true);
	}

<<<<<<< HEAD
	public ThongKeKhachHang_UI() throws RemoteException, MalformedURLException, NotBoundException{
=======
	public ThongKeKhachHang_UI(){
>>>>>>> 5ffccfce035c97c37f0b44fbffe423375104c9c2
		try {
			client = new Client();
		} catch (IOException | NotBoundException e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(0, 0, 1000, 670);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel panel_3 = new JPanel();
		panel.add(panel_3);

		JLabel lblNewLabel_2 = new JLabel("Thống kê khách hàng");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_3.add(lblNewLabel_2);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.add(Box.createHorizontalStrut(20));
		JLabel lblThongKeTheo = new JLabel("Thống kê theo: ");
		panel_2.add(lblThongKeTheo);

		DefaultComboBoxModel<String> modelLoai = new DefaultComboBoxModel<String>();
		cboLoaiTK = new JComboBox(modelLoai);
		panel_2.add(cboLoaiTK);
		modelLoai.addElement("Tùy chỉnh");
		modelLoai.addElement("Ngày hôm nay");
		modelLoai.addElement("Ngày hôm qua");
		modelLoai.addElement("7 ngày qua");
		modelLoai.addElement("1 tháng qua");
		modelLoai.addElement("1 năm qua");
		

		JLabel lblTuNgay = new JLabel("Từ ngày:  ");
		panel_2.add(lblTuNgay);

		dpTuNgay = new kDatePicker(100);
		dpTuNgay.setPreferredSize(new Dimension(100, 25));
		// dpTuNgay = new kDatePicker();
		panel_2.add(dpTuNgay);

		JLabel lblToiNgay = new JLabel("Tới ngày");
		panel_2.add(lblToiNgay);

		dpToiNgay = new kDatePicker(100);
		dpToiNgay.setPreferredSize(new Dimension(100, 25));
		// dpToiNgay = new kDatePicker();
		panel_2.add(dpToiNgay);

//		JPanel panel_6 = new JPanel();
//		panel_2.add(panel_6);
//
//		JLabel lblSoLuongToiDa = new JLabel("Số lượng KH tối đa: ");
//		panel_6.add(lblSoLuongToiDa);
//
//		modelLimit = new DefaultComboBoxModel<Integer>();
//		cboLimit = new JComboBox(modelLimit);
//		cboLimit.setEditable(true);
//		panel_6.add(cboLimit);
//		modelLimit.addElement(10);
//		modelLimit.addElement(25);
//		modelLimit.addElement(50);
//		modelLimit.addElement(100);
//		modelLimit.addElement(500);
		panel_2.add(Box.createHorizontalStrut(20));
		JButton btnThongKe = new JButton("Thống kê", new ImageIcon("data/images/statistics.png"));
		btnThongKe.setPreferredSize(new Dimension(150, 25));

		btnThongKe.setBackground(Color.WHITE);
		panel_2.add(btnThongKe);

		JButton btnLamMoi = new JButton("Làm mới dữ liệu", new ImageIcon("data/images/refresh.png"));
		btnLamMoi.setPreferredSize(new Dimension(200, 25));
		btnLamMoi.setBackground(Color.WHITE);
		panel_2.add(btnLamMoi);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);

		panel_1.setLayout(new BorderLayout(0, 0));

		String[] cols = { "Mã khách hàng", "Tên khách hàng", "CMND", "Ngày hết hạn", "Số điện thoại", "Loại khách hàng",
				"Số lần đặt phòng","Số lần gọi dịch vụ", "Số tiền đã trả" };
		model = new DefaultTableModel(cols, 0);
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		panel_1.add(scrollPane);

		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4, BorderLayout.SOUTH);

		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);

		panel_5.add(Box.createHorizontalStrut(230));
		JLabel lblTong = new JLabel("Tổng số khách hàng: ");
		lblTong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_5.add(lblTong);

		lblTongSo = new JLabel("0");
		lblTongSo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_5.add(lblTongSo);

		JPanel panel_5_1 = new JPanel();
		panel_4.add(panel_5_1);

		JLabel lblTngSTin = new JLabel("Tổng doanh thu: ");
		lblTngSTin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_5_1.add(lblTngSTin);

		lblTongSoTien = new JLabel("0");
		lblTongSoTien.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_5_1.add(lblTongSoTien);
		panel_5_1.add(Box.createHorizontalStrut(150));
		JButton btnIn = new JButton("In báo cáo", new ImageIcon("data/images/printer.png"));
		panel_5_1.add(btnIn);

		try {
			renderData();
		} catch (MalformedURLException | RemoteException | SQLException | NotBoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		btnThongKe.addActionListener((e) -> {
			long ml = System.currentTimeMillis();
//	        ml = ml/86400000*86400000;
			Date now = new Date(ml);

			Date tuNgay = new Date(ml), toiNgay = new Date(ml); // hom nay

			if (cboLoaiTK.getSelectedIndex() == 0) { // tuy chinh
				try {
					tuNgay = dpTuNgay.getFullDate();
					toiNgay = dpToiNgay.getFullDate();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}

				if (tuNgay.after(now)) {
					JOptionPane.showMessageDialog(contentPane, "Từ ngày không hợp lệ");
					return;
				}

				if (toiNgay.after(now)) {
					JOptionPane.showMessageDialog(contentPane, "Tới ngày không hợp lệ");
					return;
				}

				if (tuNgay.after(toiNgay)) {
					JOptionPane.showMessageDialog(contentPane, "Ngày không hợp lệ");
					return;
				}
			} else if (cboLoaiTK.getSelectedIndex() == 2) { // hom qua
				tuNgay = utils.Ngay.homQua();
				toiNgay = utils.Ngay.homQua();
			} else if (cboLoaiTK.getSelectedIndex() == 3) { // 7 ngay qua
				tuNgay = utils.Ngay._7NgayQua();
			} else if (cboLoaiTK.getSelectedIndex() == 4) { // 1 thang qua
				tuNgay = utils.Ngay._1ThangQua();
			} else if (cboLoaiTK.getSelectedIndex() == 5) { // 1 nam qua
				tuNgay = utils.Ngay._1NamQua();
			}

//			if (!String.valueOf(cboLimit.getSelectedItem()).matches("^\\d+$")) {
//				JOptionPane.showMessageDialog(contentPane, "Số lượng khách hàng tối đa không hợp lệ");
//				return;
//			}
//			
			try {
				renderData(tuNgay, toiNgay);
			} catch (MalformedURLException | RemoteException | NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		btnLamMoi.addActionListener((e) -> {
			try {
				renderData();
			} catch (MalformedURLException | RemoteException | NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		cboLoaiTK.addActionListener((e) -> {
			if (cboLoaiTK.getSelectedIndex() == 0) {
				dpTuNgay.btn.setEnabled(true);
				dpToiNgay.btn.setEnabled(true);
			} else {
				dpTuNgay.btn.setEnabled(false);
				dpToiNgay.btn.setEnabled(false);
			}
		});
		btnIn.addActionListener((e) -> {
			JOptionPane.showMessageDialog(contentPane, "In báo cáo thành công");
		});

	}

	public JPanel getContentPane() {
		return contentPane;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public void renderData() throws MalformedURLException, RemoteException, NotBoundException {
		KhachHangDao khachHangDao = client.getKhachHangDao();
		ChiTietDVDao ctDVdao = client.getChiTietDVDao();
		 ChiTietHoaDonPhongDao cthoaDonPhongDao = client.getChiTietHoaDonPhongDao();
		dskh = khachHangDao.getListKhachHang();
		int tongKH = 0;
		double tongdoanhthu = 0;
		table.clearSelection();
		model.getDataVector().removeAllElements();
		for (int j = 0; j < dskh.size(); j++) {
			KhachHang khachhang = dskh.get(j);
			dsdv = ctDVdao.getListChiTietDVByMaKH(khachhang.getMaKH());
			dshdp = cthoaDonPhongDao.getListChiTietHDPByMaKH(khachhang.getMaKH());
			double tongTien = 0;
			
			for (ChiTietDV dv : dsdv) {
				tongTien += dv.getDonGia() * dv.getSoLuong();
			}
			for (ChiTietHoaDonPhong ds : dshdp) {
				int soNgay = (int) Ngay.tinhKhoangNgay(ds.getHoaDonPhong().getNgayGioNhan(), ds.getHoaDonPhong().getNgayGioTra());
				tongTien += ds.getPhong().getLoaiPhong().getDonGia() * soNgay;
			}

			model.addRow(new Object[] { khachhang.getMaKH(), khachhang.getTenKH(), khachhang.getCmnd(),
					formatDate(khachhang.getNgayHetHan()), khachhang.getSoDienThoai(), khachhang.getLoaiKH(),
					dshdp.size(),dsdv.size(), Currency.format(tongTien) });
			tongKH++;
			tongdoanhthu+=tongTien;
		}
		;
		lblTongSo.setText(String.valueOf(tongKH));
		lblTongSoTien.setText(Currency.format(tongdoanhthu));
		table.revalidate();
		table.repaint();
	}
	public void renderData(Date tuNgay, Date toiNgay)
			throws MalformedURLException, RemoteException, NotBoundException {
		KhachHangDao khachHangDao = client.getKhachHangDao();
		ChiTietDVDao ctDVdao = client.getChiTietDVDao();
		 ChiTietHoaDonPhongDao cthoaDonPhongDao = client.getChiTietHoaDonPhongDao();
		dskh = khachHangDao.getListKhachHang();
	
		int tongKH = 0;
		double tongdoanhthu = 0;
		table.clearSelection();
		model.getDataVector().removeAllElements();
		for (int j = 0; j < dskh.size(); j++) {
			KhachHang khachhang = dskh.get(j);
			dsdv = ctDVdao.getListChiTietDVByMaKHAndDate(khachhang.getMaKH(),tuNgay,toiNgay);
			dshdp = cthoaDonPhongDao.getListChiTietHDPByMaKHAndDate(khachhang.getMaKH(), tuNgay, toiNgay);
			double tongTien = 0;
			for (ChiTietDV dv : dsdv) {
				tongTien += dv.getDonGia() * dv.getSoLuong();
			}
			for (ChiTietHoaDonPhong ds : dshdp) {
				int soNgay = (int) Ngay.tinhKhoangNgay(ds.getHoaDonPhong().getNgayGioNhan(), ds.getHoaDonPhong().getNgayGioTra());
				tongTien += ds.getPhong().getLoaiPhong().getDonGia() * soNgay;
			}

			model.addRow(new Object[] { khachhang.getMaKH(), khachhang.getTenKH(), khachhang.getCmnd(),
					formatDate(khachhang.getNgayHetHan()) , khachhang.getSoDienThoai(), khachhang.getLoaiKH(),
					dshdp.size(),dsdv.size(), Currency.format(tongTien) });
			tongKH++;
			tongdoanhthu+=tongTien;
		}
		;
		lblTongSo.setText(String.valueOf(tongKH));
		lblTongSoTien.setText(Currency.format(tongdoanhthu));
		table.revalidate();
		table.repaint();
	}

	private String formatDate(Date date) {
		if (date == null)
			return "Chưa cập nhật";
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(date);
	}
	
}
