package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
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
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import app.Client;
import dao.ChiTietDVDao;
import dao.ChiTietHoaDonPhongDao;
import dao.DichVuDao;
import dao.KhachHangDao;
import model.ChiTietDV;
import model.ChiTietHoaDonPhong;
import model.DichVu;
import model.KhachHang;

public class ThongKeDoanhThu_UI extends JFrame {

	private int soLuongSP = 0;

	private JPanel contentPane;
	private JTextField txtTuNgay;
	private JTextField txtToiNgay;
	private DefaultTableModel model;
	DialogDatePicker f = new DialogDatePicker();
	private kDatePicker dpTuNgay;
	private kDatePicker dpToiNgay;
	private JComboBox comboBox;
	private JComboBox cboLoaiTK;

	private int tongSoLanMua;

	private int tongSoTien;

	private DefaultComboBoxModel<Integer> modelLimit;

	private JLabel lblTongSo;

	private JLabel lblKH;

	private JLabel lblSP;

	private JLabel lblDoanhThu;

	private JLabel lblVon;

	private JLabel lblLoiNhuan;

	private int tongSo;

	private int doanhThu;

	private List<Map<String, String>> ls;

	private JLabel lblSach;

	private JLabel lblDungCu;

	private int soVon;

	private int soLuongSach;

	private int soLuongDungCu;

	private JLabel lblTuNgay01;

	private JLabel lblToiNgay01;

	private JLabel lblCaKQ;

	private JComboBox cboCaLam;

	private Client client;

	private List<ChiTietDV> dsdv;

	private List<ChiTietHoaDonPhong> dshdp;

	private List<KhachHang> dskh;

	private JLabel lblTongSodv;

	private JLabel lblTongdv;

	private List<DichVu> dsdvKS;

	public static void main(String[] args)
			throws RemoteException, MalformedURLException, NotBoundException{
		new ThongKeDoanhThu_UI().setVisible(true);
	}

	public ThongKeDoanhThu_UI() throws RemoteException, MalformedURLException, NotBoundException{
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

		JLabel lblNewLabel_2 = new JLabel("Thống kê doanh thu");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_3.add(lblNewLabel_2);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2);

		JLabel lblThongKeTheo = new JLabel("Thống kê theo: ");
		panel_2.add(lblThongKeTheo);

		DefaultComboBoxModel<String> modelLoai = new DefaultComboBoxModel<String>();
		cboLoaiTK = new JComboBox(modelLoai);
		cboLoaiTK.setPreferredSize(new Dimension(120, 22));
		panel_2.add(cboLoaiTK);
		modelLoai.addElement("Tất cả");
		modelLoai.addElement("Tùy chỉnh");
		modelLoai.addElement("Ngày hôm nay");
		modelLoai.addElement("Ngày hôm qua");
		modelLoai.addElement("7 ngày qua");
		modelLoai.addElement("1 tháng qua");
		modelLoai.addElement("1 năm qua");

		JLabel lblTuNgay = new JLabel("Từ ngày: ");
		lblTuNgay.setPreferredSize(new Dimension(55, 30));
		panel_2.add(lblTuNgay);

		dpTuNgay = new kDatePicker(100);
		dpTuNgay.setPreferredSize(new Dimension(100, 22));
		panel_2.add(dpTuNgay);

		JLabel lblToiNgay = new JLabel("Tới ngày: ");
		lblToiNgay.setPreferredSize(new Dimension(60, 30));
		panel_2.add(lblToiNgay);

		dpToiNgay = new kDatePicker(100);
		dpToiNgay.setPreferredSize(new Dimension(100, 22));
		panel_2.add(dpToiNgay);

		modelLimit = new DefaultComboBoxModel<Integer>();
		modelLimit.addElement(10);
		modelLimit.addElement(25);
		modelLimit.addElement(50);
		modelLimit.addElement(100);
		modelLimit.addElement(500);

//			
		panel_2.add(Box.createHorizontalStrut(30));
		JButton btnThongKe = new JButton("Thống kê", new ImageIcon("data/images/statistics.png"));
		btnThongKe.setPreferredSize(new Dimension(120, 30));
		btnThongKe.setBackground(Color.WHITE);
		panel_2.add(btnThongKe);
		
		JButton btnLamMoi = new JButton("Làm mới", new ImageIcon("data/images/statistics.png"));
		btnLamMoi.setPreferredSize(new Dimension(120, 30));
		btnLamMoi.setBackground(Color.WHITE);
		panel_2.add(btnLamMoi);

		JButton btnIn = new JButton("In báo cáo", new ImageIcon("data/images/printer.png"));
		btnIn.setPreferredSize(new Dimension(120, 30));
		btnIn.setBackground(Color.WHITE);
		panel_2.add(btnIn);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		contentPane.add(panel_1, BorderLayout.CENTER);

		panel_1.setLayout(new BorderLayout(0, 0));

		String[] cols = { "STT", "Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Địa chỉ", "Số lần mua hàng",
				"Số tiền mua hàng" };
		model = new DefaultTableModel(cols, 0);

		JPanel panel_7 = new JPanel();
		panel_1.add(panel_7, BorderLayout.NORTH);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new EmptyBorder(20, 0, 0, 0));
		panel_7.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));

		JPanel pnItem00 = new JPanel();
		FlowLayout flowLayout00 = (FlowLayout) pnItem00.getLayout();
		flowLayout00.setAlignment(FlowLayout.LEFT);
		panel_4.add(pnItem00);

		JLabel lblTuNgay00 = new JLabel("Từ ngày: ");
		lblTuNgay00.setPreferredSize(new Dimension(300, 30));
		lblTuNgay00.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnItem00.add(lblTuNgay00);

		lblTuNgay01 = new JLabel();
		lblTuNgay01.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnItem00.add(lblTuNgay01);

		JPanel pnItem01 = new JPanel();
		FlowLayout flowLayout01 = (FlowLayout) pnItem01.getLayout();
		flowLayout01.setAlignment(FlowLayout.LEFT);
		panel_4.add(pnItem01);

		JLabel lblToiNgay00 = new JLabel("Tới ngày: ");
		lblToiNgay00.setPreferredSize(new Dimension(300, 30));
		lblToiNgay00.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnItem01.add(lblToiNgay00);

		lblToiNgay01 = new JLabel();
		lblToiNgay01.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnItem01.add(lblToiNgay01);

		JPanel pnCa = new JPanel();
		FlowLayout flowLayout02 = (FlowLayout) pnCa.getLayout();
		flowLayout02.setAlignment(FlowLayout.LEFT);
		panel_4.add(pnCa);

		JPanel pnItem2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) pnItem2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_4.add(pnItem2);

		JLabel lblSKhchHng = new JLabel("Tổng số khách hàng:");
		lblSKhchHng.setPreferredSize(new Dimension(300, 30));
		lblSKhchHng.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnItem2.add(lblSKhchHng);

		lblKH = new JLabel("0");
		lblKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnItem2.add(lblKH);
		
		JPanel pnItem6 = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) pnItem6.getLayout();
		flowLayout_6.setAlignment(FlowLayout.LEFT);
		panel_4.add(pnItem6);
		
		JLabel lblTongsodv = new JLabel("Tổng số dịch vụ của khách sạn:");
		lblTongsodv.setPreferredSize(new Dimension(300, 30));
		lblTongsodv.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnItem6.add(lblTongsodv);
		
		lblTongdv = new JLabel("0");
		lblTongdv.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnItem6.add(lblTongdv);

		JPanel pnItem1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnItem1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_4.add(pnItem1);

		JLabel lblTong = new JLabel("Tổng số lần đặt phòng: ");
		lblTong.setPreferredSize(new Dimension(300, 30));
		lblTong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnItem1.add(lblTong);

		lblTongSo = new JLabel("0");
		lblTongSo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnItem1.add(lblTongSo);
		
		

		JPanel pnItem5 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) pnItem5.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		panel_4.add(pnItem5);

		JLabel lblTongdv = new JLabel("Tổng số lần đặt dịch vụ: ");
		lblTongdv.setPreferredSize(new Dimension(300, 30));
		lblTongdv.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnItem5.add(lblTongdv);

		lblTongSodv = new JLabel("0");
		lblTongSodv.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnItem5.add(lblTongSodv);

		JPanel pnItem3 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) pnItem3.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_4.add(pnItem3);

		JLabel lblSSnPhm = new JLabel("Số lượng dịch vụ đã bán:");
		lblSSnPhm.setPreferredSize(new Dimension(300, 30));
		lblSSnPhm.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnItem3.add(lblSSnPhm);

		lblSach = new JLabel("0");
		lblSach.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnItem3.add(lblSach);

		JPanel pnItem3_1 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) pnItem3_1.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_4.add(pnItem3_1);

		JPanel pnItem4 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) pnItem4.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panel_4.add(pnItem4);

		JLabel lblDoanhThuNhn = new JLabel("Doanh thu nhận được:");
		lblDoanhThuNhn.setPreferredSize(new Dimension(300, 30));
		lblDoanhThuNhn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnItem4.add(lblDoanhThuNhn);

		lblDoanhThu = new JLabel("0");
		lblDoanhThu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnItem4.add(lblDoanhThu);

//			JPanel pnItem6 = new JPanel();
//			FlowLayout flowLayout_6 = (FlowLayout) pnItem6.getLayout();
//			flowLayout_6.setAlignment(FlowLayout.LEFT);
//			panel_4.add(pnItem6);
//			
//			JLabel lblSVnB = new JLabel("Số vốn bỏ ra:");
//			lblSVnB.setPreferredSize(new Dimension(300, 30));
//			lblSVnB.setFont(new Font("Tahoma", Font.PLAIN, 15));
//			pnItem6.add(lblSVnB);
//			
//			lblVon = new JLabel("0");
//			lblVon.setFont(new Font("Tahoma", Font.PLAIN, 15));
//			pnItem6.add(lblVon);
//			
//			JPanel pnItem7 = new JPanel();
//			FlowLayout flowLayout_7 = (FlowLayout) pnItem7.getLayout();
//			flowLayout_7.setAlignment(FlowLayout.LEFT);
//			panel_4.add(pnItem7);
//			
//			JLabel lblLiNhunNhn = new JLabel("Lợi nhuận thu được:");
//			lblLiNhunNhn.setPreferredSize(new Dimension(300, 30));
//			lblLiNhunNhn.setFont(new Font("Tahoma", Font.PLAIN, 15));
//			pnItem7.add(lblLiNhunNhn);
//			
//			lblLoiNhuan = new JLabel("0");
//			lblLoiNhuan.setFont(new Font("Tahoma", Font.PLAIN, 15));
//			pnItem7.add(lblLoiNhuan);

		panel_4.add(Box.createVerticalStrut(100));

		JPanel pnItem8 = new JPanel();
		FlowLayout flowLayout_8 = (FlowLayout) pnItem8.getLayout();
		flowLayout_8.setAlignment(FlowLayout.LEFT);
		panel_4.add(pnItem8);

		JButton btnXem = new JButton("Xem chi tiết bằng biểu đồ");
		btnXem.setPreferredSize(new Dimension(300, 30));
		btnXem.setBackground(Color.WHITE);
		pnItem8.add(btnXem);

		renderData();
			btnThongKe.addActionListener((e) -> {
				long ml = System.currentTimeMillis();
//		        ml = ml/86400000*86400000;
				Date now = new Date(ml);

				Date tuNgay = new Date(ml), toiNgay = new Date(ml); // hom nay

				if (cboLoaiTK.getSelectedIndex() == 1) { // tuy chinh
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
					try {
						renderData(tuNgay, toiNgay);
					} catch (MalformedURLException | RemoteException | SQLException | NotBoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else if (cboLoaiTK.getSelectedIndex() == 2) { //hom nay
					try {
						renderData(tuNgay, toiNgay);
					} catch (MalformedURLException | RemoteException | SQLException | NotBoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (cboLoaiTK.getSelectedIndex() == 3) { // hom qua
					tuNgay = utils.Ngay.homQua();
					toiNgay = utils.Ngay.homQua();
					try {
						renderData(tuNgay, toiNgay);
					} catch (MalformedURLException | RemoteException | SQLException | NotBoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (cboLoaiTK.getSelectedIndex() == 4) { // 7 ngay qua
					tuNgay = utils.Ngay._7NgayQua();
					try {
						renderData(tuNgay, toiNgay);
					} catch (MalformedURLException | RemoteException | SQLException | NotBoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (cboLoaiTK.getSelectedIndex() == 5) { // 1 thang qua
					tuNgay = utils.Ngay._1ThangQua();
					try {
						renderData(tuNgay, toiNgay);
					} catch (MalformedURLException | RemoteException | SQLException | NotBoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (cboLoaiTK.getSelectedIndex() == 6) { // 1 nam qua
					tuNgay = utils.Ngay._1NamQua();
					try {
						renderData(tuNgay, toiNgay);
					} catch (MalformedURLException | RemoteException | SQLException | NotBoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else if (cboLoaiTK.getSelectedIndex() == 0) { // 1 nam qua
					try {
						renderData();
					} catch (MalformedURLException | RemoteException | NotBoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
		
				
			});
			btnLamMoi.addActionListener((e) -> {
				dpTuNgay.btn.setEnabled(false);
				dpToiNgay.btn.setEnabled(false);
				cboLoaiTK.setSelectedIndex(0);
				try {
					renderData();
				} catch (MalformedURLException | RemoteException | NotBoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			});
			btnIn.addActionListener((e) -> {
				JOptionPane.showMessageDialog(contentPane, "In báo cáo thành công");
			});
		cboLoaiTK.addActionListener((e) -> {
			if (cboLoaiTK.getSelectedIndex() == 1) {
				dpTuNgay.btn.setEnabled(true);
				dpToiNgay.btn.setEnabled(true);
			} else {
				dpTuNgay.btn.setEnabled(false);
				dpToiNgay.btn.setEnabled(false);
			}
		});

	}

	public void renderData() throws MalformedURLException, RemoteException, NotBoundException {
		dpTuNgay.btn.setEnabled(false);
		dpToiNgay.btn.setEnabled(false);
		cboLoaiTK.setSelectedIndex(0);
		KhachHangDao khachHangDao = client.getKhachHangDao();
		ChiTietDVDao ctDVdao = client.getChiTietDVDao();
		ChiTietHoaDonPhongDao cthoaDonPhongDao = client.getChiTietHoaDonPhongDao();
		DichVuDao dichVuDao = client.getDichVuDao();
		dskh = khachHangDao.getListKhachHang();
		dsdvKS = dichVuDao.getListDichVu();
		int tongKH = 0;
		int tongDatphong = 0;
		int tongDatDV = 0;
		int soLuongDVBan = 0;
		double tongdoanhthu = 0;
		model.getDataVector().removeAllElements();
		for (int j = 0; j < dskh.size(); j++) {
			KhachHang khachhang = dskh.get(j);
			dsdv = ctDVdao.getListChiTietDVByMaKH(khachhang.getMaKH());
			dshdp = cthoaDonPhongDao.getListChiTietHDPByMaKH(khachhang.getMaKH());
			double tongTien = 0;

			for (ChiTietDV dv : dsdv) {
				soLuongDVBan += dv.getSoLuong();
				tongTien += dv.getDonGia() * dv.getSoLuong();
			}
			for (ChiTietHoaDonPhong ds : dshdp) {
				int soNgay = (int) utils.Ngay.tinhKhoangNgay(ds.getHoaDonPhong().getNgayGioNhan(),
						ds.getHoaDonPhong().getNgayGioTra());
				tongTien += ds.getPhong().getLoaiPhong().getDonGia() * soNgay;
			}

			tongDatDV += dsdv.size();
			tongDatphong += dshdp.size();
			tongKH++;
			tongdoanhthu += tongTien;
		}
		;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		lblTuNgay01.setText("Tất cả");
		lblToiNgay01.setText(sdf.format(utils.Ngay.homNay()));
		lblTongSo.setText(String.valueOf(tongDatphong));
		lblKH.setText(String.valueOf(tongKH));
		lblTongdv.setText(String.valueOf(dsdvKS.size()));
		lblTongSodv.setText(String.valueOf(tongDatDV));
		lblSach.setText(String.valueOf(soLuongDVBan));

		lblDoanhThu.setText(utils.Currency.format(tongdoanhthu).toString());

	}

		public void renderData(Date tuNgay, Date toiNgay) throws SQLException, RemoteException, MalformedURLException, NotBoundException {
			
			KhachHangDao khachHangDao = client.getKhachHangDao();
			ChiTietDVDao ctDVdao = client.getChiTietDVDao();
			ChiTietHoaDonPhongDao cthoaDonPhongDao = client.getChiTietHoaDonPhongDao();
			DichVuDao dichVuDao = client.getDichVuDao();
			dskh = khachHangDao.getListKhachHang();
			dsdvKS = dichVuDao.getListDichVu();
			int tongKH = 0;
			int tongDatphong = 0;
			int tongDatDV = 0;
			int soLuongDVBan = 0;
			double tongdoanhthu = 0;
			model.getDataVector().removeAllElements();
			for (int j = 0; j < dskh.size(); j++) {
				KhachHang khachhang = dskh.get(j);
				dsdv = ctDVdao.getListChiTietDVByMaKHAndDate(khachhang.getMaKH(),tuNgay,toiNgay);
				dshdp = cthoaDonPhongDao.getListChiTietHDPByMaKHAndDate(khachhang.getMaKH(), tuNgay, toiNgay);
				double tongTien = 0;

				for (ChiTietDV dv : dsdv) {
					soLuongDVBan += dv.getSoLuong();
					tongTien += dv.getDonGia() * dv.getSoLuong();
				}
				for (ChiTietHoaDonPhong ds : dshdp) {
					int soNgay = (int) utils.Ngay.tinhKhoangNgay(ds.getHoaDonPhong().getNgayGioNhan(),
							ds.getHoaDonPhong().getNgayGioTra());
					tongTien += ds.getPhong().getLoaiPhong().getDonGia() * soNgay;
				}

				tongDatDV += dsdv.size();
				tongDatphong += dshdp.size();
				tongKH++;
				tongdoanhthu += tongTien;
			}
			;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			lblTuNgay01.setText(sdf.format(tuNgay));
			lblToiNgay01.setText(sdf.format(toiNgay));
			lblTongSo.setText(String.valueOf(tongDatphong));
			lblKH.setText(String.valueOf(tongKH));
			lblTongdv.setText(String.valueOf(dsdvKS.size()));
			lblTongSodv.setText(String.valueOf(tongDatDV));
			lblSach.setText(String.valueOf(soLuongDVBan));

			lblDoanhThu.setText(utils.Currency.format(tongdoanhthu).toString());
		}
	public JPanel getContentPane() {
		return contentPane;
	}
}
