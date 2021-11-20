package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import app.Client;
import model.ChiTietDV;
import model.DichVu;
import model.HoaDonDV;
import model.KhachHang;
import utils.Currency;
import utils.Ngay;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.ScrollPaneConstants;

public class SuDungDV_UI extends JFrame {

	private JPanel contentPane;
	private JTextField txtMaHD;
	private JTextField txtSDT;
	private JTextField txtNgayLap;
	private JTextField textField_4;
	private JButton btnThemSP;
	private JButton btnBoSP;
	

	private DefaultComboBoxModel<String> modelKH;
	
	private DefaultTableModel modelDV;
	private DefaultTableModel modelDVDaSD;
	

	private JTextField txtSoLuong;
	private JButton btnThemHD; 
	private JTextField txtTongTien;
	private JTable tblDVDaSD;
	private JTextField txtTenKH;
	private JTextField txtCMND;
	private JTable tblDV;
	
	private Client client;
	private List<DichVu> dsdv;
	private List<ChiTietDV> dscthddv = new ArrayList<ChiTietDV>();
	private List<KhachHang> dskh;
	private JComboBox cboKH;
	private kDatePicker dpNgayLap;
	
	private boolean modeThem = true;
	private JButton btnSua;
	private HoaDonDV _hddv = null;
	private JButton btnLamMoi;
	
	private int indexKH = 0; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SuDungDV_UI frame = new SuDungDV_UI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public SuDungDV_UI(){
		try {
			client = new Client();
		} catch (IOException | NotBoundException e) {
			e.printStackTrace();
		}
		GUI();
	}
	
			
	public void GUI(){
		setTitle("Sử dụng dịch vụ");
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(0, 0, 1300, 700);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnThongTinKH = new JPanel();
		contentPane.add(pnThongTinKH, BorderLayout.WEST);
		
		JPanel pnThongTinKH2 = new JPanel();
		pnThongTinKH.add(pnThongTinKH2);
		pnThongTinKH2.setLayout(new BorderLayout(0, 0));
		
		JPanel pnTTKH = new JPanel();
		pnTTKH.setBorder(new EmptyBorder(40, 0, 0, 0));
		pnThongTinKH2.add(pnTTKH, BorderLayout.NORTH);
		
		JLabel lblTTKH = new JLabel("Thông tin hóa đơn");
		lblTTKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnTTKH.add(lblTTKH);
		
		JPanel pnThongTin = new JPanel();
		pnThongTinKH2.add(pnThongTin, BorderLayout.CENTER);
		pnThongTin.setLayout(new BoxLayout(pnThongTin, BoxLayout.Y_AXIS));
		
		JPanel pnMaKH = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnMaKH.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnMaKH);
		
		JLabel lblMaHD = new JLabel("Mã hóa đơn");
		lblMaHD.setPreferredSize(new Dimension(100, 20));
		pnMaKH.add(lblMaHD);
		
		txtMaHD = new JTextField();
		txtMaHD.setEditable(false);
		pnMaKH.add(txtMaHD);
		txtMaHD.setColumns(20);
		
		JPanel pnMaKH_1 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) pnMaKH_1.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnMaKH_1);
		
		JLabel lblMaKH = new JLabel("Mã khách hàng");
		lblMaKH.setPreferredSize(new Dimension(100, 20));
		pnMaKH_1.add(lblMaKH);
		
		modelKH = new DefaultComboBoxModel<String>();
		cboKH = new JComboBox(modelKH);
		cboKH.setPreferredSize(new Dimension(222, 22));
		pnMaKH_1.add(cboKH);
		
		JPanel pnTenKH = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) pnTenKH.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnTenKH);
		
		JLabel lblTenKH = new JLabel("Tên khách hàng");
		lblTenKH.setPreferredSize(new Dimension(100, 20));
		pnTenKH.add(lblTenKH);
		
		txtTenKH = new JTextField();
		txtTenKH.setEditable(false);
		pnTenKH.add(txtTenKH);
		txtTenKH.setColumns(20);
		
		
		JPanel pnSoDienThoai_1 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) pnSoDienThoai_1.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnSoDienThoai_1);
		
		JLabel lblCMND = new JLabel("CMND");
		lblCMND.setPreferredSize(new Dimension(100, 20));
		pnSoDienThoai_1.add(lblCMND);
		
		txtCMND = new JTextField();
		txtCMND.setEditable(false);
		txtCMND.setColumns(20);
		pnSoDienThoai_1.add(txtCMND);
		
		JPanel pnSoDienThoai = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) pnSoDienThoai.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnSoDienThoai);
		
		JLabel lblSDT = new JLabel("Số điện thoại");
		lblSDT.setPreferredSize(new Dimension(100, 20));
		pnSoDienThoai.add(lblSDT);
		
		txtSDT = new JTextField();
		txtSDT.setEditable(false);
		txtSDT.setColumns(20);
		pnSoDienThoai.add(txtSDT);
		
		JPanel pnNgayLap = new JPanel();
		FlowLayout fl_pnNgayLap = (FlowLayout) pnNgayLap.getLayout();
		fl_pnNgayLap.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnNgayLap);
		
		JLabel lblNgayLap = new JLabel("Ngày lập");
		lblNgayLap.setPreferredSize(new Dimension(100, 20));
		pnNgayLap.add(lblNgayLap);
		
		dpNgayLap = new kDatePicker(222);
        dpNgayLap.setPreferredSize(new Dimension(222, 20));
        dpNgayLap.btn.setEnabled(false);
		pnNgayLap.add(dpNgayLap);
		
		JPanel pnTongTien = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) pnTongTien.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnTongTien);
		
		JLabel lblTongTien = new JLabel("Tổng tiền");
		lblTongTien.setPreferredSize(new Dimension(100, 20));
		pnTongTien.add(lblTongTien);
		
		txtTongTien = new JTextField("0");
		txtTongTien.setEditable(false);
		txtTongTien.setColumns(20);
		pnTongTien.add(txtTongTien);
		
		JPanel pnBtn = new JPanel();
		pnThongTin.add(pnBtn);
		
		btnThemHD = new JButton("Thêm hóa đơn", new ImageIcon("data/images/blueAdd_16.png"));
		btnThemHD.setBackground(Color.WHITE);
		pnBtn.add(btnThemHD);
		
		btnSua = new JButton("Sửa hóa đơn");
		btnSua.setBackground(Color.WHITE);
		pnBtn.add(btnSua);
		
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setBackground(Color.WHITE);
		pnBtn.add(btnLamMoi);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		panel_1.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Danh sách dịch vụ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_4.add(lblNewLabel);
		
		
		
		JPanel panel_7 = new JPanel();
		panel.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.Y_AXIS));
		
		String[] cols = {"Mã dịch vụ", "Tên dịch vụ", "Đơn giá"};
		modelDV = new DefaultTableModel(cols, 0);
		tblDV = new JTable(modelDV);
		JScrollPane scrollPane = new JScrollPane(tblDV);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_7.add(scrollPane);
		

		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));
		
		Component verticalStrut = Box.createVerticalStrut(250);
		panel_5.add(verticalStrut);
		
		JPanel panel_8 = new JPanel();
		panel_5.add(panel_8);
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.Y_AXIS));
		
		JPanel panel_9 = new JPanel();
		panel_8.add(panel_9);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.Y_AXIS));
		
		btnThemSP = new JButton(">>");
		btnThemSP.setBackground(Color.WHITE);
		btnThemSP.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_9.add(btnThemSP);
		
		JPanel panel_10 = new JPanel();
		panel_8.add(panel_10);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setHorizontalAlignment(SwingConstants.CENTER);
		txtSoLuong.setText("1");
		txtSoLuong.setPreferredSize(new Dimension(49, 30));
		txtSoLuong.setColumns(4);
		panel_10.add(txtSoLuong);
		
		JPanel panel_11 = new JPanel();
		panel_8.add(panel_11);
		
		btnBoSP = new JButton("<<");
		btnBoSP.setBackground(Color.WHITE);
		btnBoSP.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_11.add(btnBoSP);
		
		
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6, BorderLayout.NORTH);
		panel_6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_1 = new JLabel("Danh sách dịch vụ đã sử dụng");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_6.add(lblNewLabel_1);
		
		String[] cols3 = {"Mã dịch vụ", "Tên dịch vụ", "Đơn giá", "Số lượng", "Thành tiền"};
		modelDVDaSD = new DefaultTableModel(cols3, 0);
		tblDVDaSD = new JTable(modelDVDaSD);
		JScrollPane scrollPane_1 = new JScrollPane(tblDVDaSD);
		scrollPane_1.setPreferredSize(new Dimension(450, 500));
		panel_3.add(scrollPane_1, BorderLayout.CENTER);
		
		try {
			renderKhachHang();
			renderData();
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		modeThem();
		
		
		cboKH.addActionListener((e) -> {
			int idx = cboKH.getSelectedIndex();
			if(idx <= 0) {
				clearKH();
				indexKH = idx;
				return;
			}
			
			KhachHang kh = dskh.get(cboKH.getSelectedIndex() - 1);
			HoaDonDV hddv = null;
			if(idx == indexKH) {
				return;
			}
			try {
				hddv = client.getHoaDonDVDao().getHDDVChuaThanhToanByMaKH(kh.getMaKH());
			} catch (RemoteException | MalformedURLException | NotBoundException e1) {
				
//					e1.printStackTrace();
			}
			
			if(hddv != null) {
				try {
					hddv .setDsChiTietDV(client.getChiTietDVDao().getListChiTietDVByMaHDDV(hddv.getMaHDDV()));
				} catch (RemoteException | MalformedURLException | NotBoundException e1) {
					e1.printStackTrace();
				}
				
				if(dscthddv.size() != 0) {
					int choose = JOptionPane.showConfirmDialog(contentPane, "Phát hiện 1 hóa đơn dịch vụ khác của " + kh.getTenKH() +" chưa được thanh toán, thay đổi khách hàng sẽ không lưu các dịch vụ vừa thêm. Tiếp tục thay đổi ?");
					if(choose != 0) {
						cboKH.setSelectedIndex(indexKH);
						return;
					}
				}
				this.set_hddv(hddv);
//				modeSua();
			}
			
			
			showKhachHang(kh);
			indexKH = idx;
		});
		
		btnThemSP.addActionListener((e) -> {
			int idx2 = cboKH.getSelectedIndex(); 
			if(idx2 <= 0) {
				JOptionPane.showMessageDialog(contentPane, "Vui lòng chọn khách hàng trước khi thêm sản phẩm");
				return;
			}
			
			int idx = tblDV.getSelectedRow();
			if(idx == -1) {
				return;
			}

			int soLuong = 0;
			try {
				soLuong = Integer.parseInt(txtSoLuong.getText());
			}catch (Exception e2) {
			}
			
			if(soLuong <= 0) {
				JOptionPane.showMessageDialog(contentPane, "Số lượng phải lớn hơn 0");
				txtSoLuong.requestFocus();
				return;
			}
			
//			kiểm tra xem đã có sp đó trong giỏ chưa
			DichVu dv = dsdv.get(idx);
			int vt = -1;
			for(int i=0; i<dscthddv.size(); i++) {
				if(dscthddv.get(i).getDichVu().getMaDV() == dv.getMaDV()) {
					vt = i;
				}
			}
			if(vt != -1) { // thêm số lượng
				dscthddv.get(vt).setSoLuong(dscthddv.get(vt).getSoLuong() + soLuong);
			}else { // thêm sp
				ChiTietDV cthdhv = new ChiTietDV(dv, soLuong, dv.getDonGia());
				dscthddv.add(cthdhv);
			}
			
			tinhTongTien();
			
			renderCTHDDV();
			
			
		});
		
		
		btnBoSP.addActionListener((e) -> {
			int idx = tblDVDaSD.getSelectedRow();
			if(idx == -1) {
				return;
			}
			int soLuong = 0;
			try {
				soLuong = Integer.parseInt(txtSoLuong.getText());
			}catch (Exception e2) {
			}
			if(soLuong <= 0) {
				JOptionPane.showMessageDialog(contentPane, "Số lượng phải lớn hơn 0");
				txtSoLuong.requestFocus();
				return;
			}
			
			if(soLuong > dscthddv.get(idx).getSoLuong()) {
				JOptionPane.showMessageDialog(contentPane, "Số lượng không hợp lệ");
				txtSoLuong.requestFocus();
				return;
			}

			if(soLuong == dscthddv.get(idx).getSoLuong()) {	
				dscthddv.remove(idx);
			}else {
				dscthddv.get(idx).setSoLuong(dscthddv.get(idx).getSoLuong() - soLuong);
			}
			
			tinhTongTien();
			renderCTHDDV();
		});
		
		btnThemHD.addActionListener((e) -> {
			int idx = cboKH.getSelectedIndex(); 
			if(idx <= 0) {
				JOptionPane.showMessageDialog(contentPane, "Vui lòng chọn khách hàng");
				return;
			}
			
			if(dscthddv.size() == 0) {
				JOptionPane.showMessageDialog(contentPane, "Vui lòng thêm dịch vụ");
				return;
			}
			
			int choose = JOptionPane.showConfirmDialog(contentPane, "Chắc chắn tạo hóa đơn");
			if(choose == 0) {
				KhachHang kh = dskh.get(idx-1);
				HoaDonDV hddv = new HoaDonDV(kh, Ngay.homNay(), dscthddv);
				
				try {
					int maHD = client.getHoaDonDVDao().themHoaDonDV(hddv);
					if(maHD != -1) {
						txtMaHD.setText(String.valueOf(maHD));
						JOptionPane.showMessageDialog(contentPane, "Thêm thành công");
						modeSua();
						return;
					}
				} catch (HeadlessException | RemoteException | MalformedURLException | NotBoundException e1) {
				
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(contentPane, "Thêm thất bại");
			}
		});
		
		btnSua.addActionListener((e) -> {
			int idx = cboKH.getSelectedIndex(); 
			if(idx <= 0) {
				JOptionPane.showMessageDialog(contentPane, "Vui lòng chọn khách hàng");
				return;
			}
			
			if(dscthddv.size() == 0) {
				JOptionPane.showMessageDialog(contentPane, "Vui lòng thêm dịch vụ");
				return;
			}
			
			int choose = JOptionPane.showConfirmDialog(contentPane, "Chắc chắn sửa hóa đơn");
			if(choose == 0) {
				KhachHang kh = dskh.get(idx-1);
				HoaDonDV hddv = new HoaDonDV(Integer.parseInt(txtMaHD.getText()), kh, Ngay.homNay(), dscthddv);
				
				try {
					if(client.getHoaDonDVDao().capNhatHoaDonDV(hddv)) {
						JOptionPane.showMessageDialog(contentPane, "Sửa thành công");
						modeSua();
						return;
					}
				} catch (HeadlessException | RemoteException | MalformedURLException | NotBoundException e1) {
				
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(contentPane, "Sửa thất bại");
			}
		});
		
		btnLamMoi.addActionListener((e) -> {
			txtMaHD.setText("");
			modeThem();
			dscthddv.clear();
			tinhTongTien();
			renderCTHDDV();
			cboKH.setSelectedIndex(0);
		});
//		
//		tblSach.getSelectionModel().addListSelectionListener((x) -> {
//			tblSPK.clearSelection();
//		});
	}
	
	public void renderKhachHang() throws SQLException {
		try {
			dskh = client.getKhachHangDao().getListKhachHang();
		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		modelKH.removeAllElements();
		modelKH.addElement("");
		dskh.forEach(kh -> {
			modelKH.addElement("#"+kh.getMaKH() + " "+ kh.getTenKH());
		});
	}
	
	
	public void renderData() throws SQLException {
		try {
			dsdv = client.getDichVuDao().getListDichVu();
		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tblDV.clearSelection();
		modelDV.getDataVector().removeAllElements();
		dsdv.forEach(dv -> {
			modelDV.addRow(new Object[] {
				dv.getMaDV(),
				dv.getTenDV(),
				Currency.format(dv.getDonGia())
			});
		});
		tblDV.revalidate();
		tblDV.repaint();
//		

	}
	
	public void renderCTHDDV() {
		tblDVDaSD.clearSelection();
		modelDVDaSD.getDataVector().removeAllElements();
		dscthddv.forEach(cthddv -> {
			modelDVDaSD.addRow(new Object[] {
					cthddv.getDichVu().getMaDV(),
					cthddv.getDichVu().getTenDV(), 
					Currency.format(cthddv.getDonGia()), 
					cthddv.getSoLuong(), 
					Currency.format(cthddv.getSoLuong()*cthddv.getDonGia())});
		});
		tblDVDaSD.revalidate();
		tblDVDaSD.repaint();
	}
	
	public void showKhachHang(KhachHang kh) {
    	txtTenKH.setText(String.valueOf(kh.getTenKH()));
    	txtCMND.setText(kh.getCmnd());
    	txtSDT.setText(kh.getSoDienThoai());
    }
	
	public void clearKH() {
    	txtTenKH.setText("");
    	txtCMND.setText("");
    	txtSDT.setText("");
    }
    
    public void clear() {
    	txtMaHD.setText("");
    	cboKH.setSelectedIndex(0);
    	txtTenKH.setText("");
    	txtCMND.setText("");
    	txtSDT.setText("");
    	dpNgayLap.setValue(Ngay.homNay());
    	txtTongTien.setText(Currency.format(0));;
    }
	
	public void tinhTongTien() {
		double tongTien = 0;
		for(int i=0; i<dscthddv.size(); i++) {
			tongTien += dscthddv.get(i).getDonGia() * dscthddv.get(i).getSoLuong();
		}
		txtTongTien.setText(Currency.format(tongTien));
	}
	
	public void modeThem() {
		cboKH.setEnabled(true);
		btnThemHD.setVisible(true);
		btnSua.setVisible(false);
		dpNgayLap.btn.setEnabled(true);
//		btnLamMoi.setVisible(true);
	}
	
	public void modeSua() {
		cboKH.setEnabled(false);
		btnThemHD.setVisible(false);
		btnSua.setVisible(true);
		dpNgayLap.btn.setEnabled(false);
	}
	
	
	
	public HoaDonDV get_hddv() {
		return _hddv;
	}

	public void set_hddv(HoaDonDV _hddv) {
		this._hddv = _hddv;
		txtMaHD.setText(String.valueOf(_hddv.getMaHDDV()));
//		btnLamMoi.setVisible(false);
		for(int i=0; i<dskh.size(); i++) {
			if(_hddv.getKhachHang().getMaKH() == dskh.get(i).getMaKH()) {
				cboKH.setSelectedIndex(i+1);
				break;
			}
		}
		
		dscthddv = _hddv.getDsChiTietDV();
		modeSua();
		renderCTHDDV();
		tinhTongTien();
	}

	public JPanel getContentPane() {
		return this.contentPane;
	}

}
