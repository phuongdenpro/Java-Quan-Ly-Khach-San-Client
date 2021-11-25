package gui;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
//import java.awt.*;
//import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import app.Client;
import dao.PhongDao;
import model.ChiTietHoaDonPhong;
import model.HoaDonPhong;
import model.KhachHang;
import model.LoaiPhong;
import model.Phong;
import utils.Currency;
import utils.Ngay;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
//import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;


public class DatPhong_UI extends JFrame{
	private Client client;
    private int maHD = 0;
    
    public JPanel pnMain;
    public String maPhong = "0";
    private ImageIcon icon_add = new ImageIcon("data/images/add.png");
    private ImageIcon icon_refresh = new ImageIcon("data/images/refresh.png");
    private ImageIcon icon_trash = new ImageIcon("data/images/trash.png");
    private ImageIcon icon_edit = new ImageIcon("data/images/edit.png");
    private ImageIcon icon_search = new ImageIcon("data/images/magnifying-glass.png");
    private JTextField txtTenKH;
    private JTextField txtCMND;
    private JTextField textField;
    private JTextField txtNgayHetHan;
    private JTextField textField_1;
    private JTextField txtTimKiem;
	private DefaultTableModel modelDSPhong;
	private DefaultComboBoxModel<String> modelLoaiPhong;
	private JComboBox cboLoc;
	private DefaultComboBoxModel<String> modelTenKH;
	private JComboBox cboKhachHang;
	private DefaultComboBoxModel<String> modelLoaiKH;
	private JComboBox cboLoaiKH;
	private JCheckBox chkKhachHangMoi;
	private JTable tblDSPhong;
	private DefaultTableModel modelChiTietDon;
	private JTable tblChiTietDon;
	private JButton btnDatPhong;
	private JButton btnLamMoi;
	private List<KhachHang> dskh;
	private List<LoaiPhong> dslp;
	private List<Phong> dsp;
	private kDatePicker dpNgayHetHan;
	private kDatePicker dpTuNgay;
	private kDatePicker dpDenNgay;
	private JTextField txtSoDienThoai;
	private List<Phong> cthdp = new ArrayList<Phong>();
	private JPanel contentPane;
	private JTextField txtMaHD;
	
	private HoaDonPhong _hdp;
	private JButton btnSua;
	private JTextField txtTongTien;
	private int tongTien;
	private JButton btnThem;
    
    
    
    public static void main(String[] args){
		DatPhong_UI datPhongUI = new DatPhong_UI();
//		datPhongUI.start();
		datPhongUI.setVisible(true);
		datPhongUI.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
    
    public DatPhong_UI(){
    	try {
			client = new Client();
		} catch (IOException | NotBoundException e) {
			e.printStackTrace();
		}
    	start();
    }

    public void start(){

        renderGUI();
        renderData();
    }

    public void renderGUI() {
    	setSize(1350, 600);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.WEST);
        
        JPanel panel_2 = new JPanel();
        panel.add(panel_2);
        panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
        
        JPanel panel_4 = new JPanel();
        panel_2.add(panel_4);
        
        JLabel lblThongTinDatPhong = new JLabel("Thông tin đặt phòng");
        lblThongTinDatPhong.setFont(new Font("Tahoma", Font.PLAIN, 20));
        panel_4.add(lblThongTinDatPhong);
        
        JPanel panel_3_1_3 = new JPanel();
        FlowLayout flowLayout_10 = (FlowLayout) panel_3_1_3.getLayout();
        flowLayout_10.setAlignment(FlowLayout.LEFT);
        panel_2.add(panel_3_1_3);
        
        JLabel lblMaHD = new JLabel("Mã hóa đơn");
        lblMaHD.setPreferredSize(new Dimension(150, 30));
        panel_3_1_3.add(lblMaHD);
        
        txtMaHD = new JTextField();
        txtMaHD.setEnabled(false);
        txtMaHD.setColumns(20);
        panel_3_1_3.add(txtMaHD);
        
        JPanel panel_3 = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        panel_2.add(panel_3);
        
        JLabel lblMaKH = new JLabel("Mã Khách hàng");
        lblMaKH.setPreferredSize(new Dimension(150, 30));
        panel_3.add(lblMaKH);
        
        modelTenKH = new DefaultComboBoxModel<String>();
        cboKhachHang = new JComboBox(modelTenKH);
        cboKhachHang.setPreferredSize(new Dimension(222, 22));
        panel_3.add(cboKhachHang);
        
        
        JPanel panel_3_1 = new JPanel();
        FlowLayout flowLayout_1 = (FlowLayout) panel_3_1.getLayout();
        flowLayout_1.setAlignment(FlowLayout.LEFT);
        panel_2.add(panel_3_1);
        
        JLabel lblTenKH = new JLabel("Tên khách hàng");
        lblTenKH.setPreferredSize(new Dimension(150, 30));
        panel_3_1.add(lblTenKH);
        
        txtTenKH = new JTextField();
        panel_3_1.add(txtTenKH);
        txtTenKH.setColumns(20);
        
        JPanel panel_3_1_1 = new JPanel();
        FlowLayout flowLayout_7 = (FlowLayout) panel_3_1_1.getLayout();
        flowLayout_7.setAlignment(FlowLayout.LEFT);
        panel_2.add(panel_3_1_1);
        
        JLabel lblCMND = new JLabel("CMND");
        lblCMND.setPreferredSize(new Dimension(150, 30));
        panel_3_1_1.add(lblCMND);
        
        txtCMND = new JTextField();
        txtCMND.setColumns(20);
        panel_3_1_1.add(txtCMND);
        
        JPanel panel_3_1_1_1 = new JPanel();
        FlowLayout flowLayout_8 = (FlowLayout) panel_3_1_1_1.getLayout();
        flowLayout_8.setAlignment(FlowLayout.LEFT);
        panel_2.add(panel_3_1_1_1);
        
        JLabel lblSDT = new JLabel("Số điện thoại");
        lblSDT.setPreferredSize(new Dimension(150, 30));
        panel_3_1_1_1.add(lblSDT);
        
        txtSoDienThoai = new JTextField();
        txtSoDienThoai.setColumns(20);
        panel_3_1_1_1.add(txtSoDienThoai);
        
        JPanel panel_3_1_2_2 = new JPanel();
        FlowLayout flowLayout_4 = (FlowLayout) panel_3_1_2_2.getLayout();
        flowLayout_4.setAlignment(FlowLayout.LEFT);
        panel_2.add(panel_3_1_2_2);
        
        JLabel lblNgyHtHn = new JLabel("Ngày hết hạn");
        lblNgyHtHn.setPreferredSize(new Dimension(150, 30));
        panel_3_1_2_2.add(lblNgyHtHn);
        
        dpNgayHetHan = new kDatePicker(222);
        dpNgayHetHan.setPreferredSize(new Dimension(222, 20));
        panel_3_1_2_2.add(dpNgayHetHan);
        
        JPanel panel_3_1_2 = new JPanel();
        FlowLayout flowLayout_3 = (FlowLayout) panel_3_1_2.getLayout();
        flowLayout_3.setAlignment(FlowLayout.LEFT);
        panel_2.add(panel_3_1_2);
        
        JLabel lblLoaiKH = new JLabel("Loại khách hàng");
        lblLoaiKH.setPreferredSize(new Dimension(150, 30));
        panel_3_1_2.add(lblLoaiKH);
        
        cboLoaiKH = new JComboBox();
        cboLoaiKH.setPreferredSize(new Dimension(222, 22));
        panel_3_1_2.add(cboLoaiKH);
        cboLoaiKH.addItem("Việt Nam");
        cboLoaiKH.addItem("Nước ngoài");
        
        JPanel panel_3_1_2_1 = new JPanel();
        FlowLayout flowLayout_5 = (FlowLayout) panel_3_1_2_1.getLayout();
        flowLayout_5.setAlignment(FlowLayout.LEFT);
        panel_2.add(panel_3_1_2_1);
        
        JLabel lblNgayDen = new JLabel("Ngày đến");
        lblNgayDen.setPreferredSize(new Dimension(150, 30));
        panel_3_1_2_1.add(lblNgayDen);
        
        dpTuNgay = new kDatePicker(222);
        dpTuNgay.setPreferredSize(new Dimension(222, 20));
        panel_3_1_2_1.add(dpTuNgay);
        
        JPanel panel_3_1_2_1_1 = new JPanel();
        FlowLayout flowLayout_6 = (FlowLayout) panel_3_1_2_1_1.getLayout();
        flowLayout_6.setAlignment(FlowLayout.LEFT);
        panel_2.add(panel_3_1_2_1_1);
        
        JLabel lblNgyi = new JLabel("Ngày đi");
        lblNgyi.setPreferredSize(new Dimension(150, 30));
        panel_3_1_2_1_1.add(lblNgyi);
        
        dpDenNgay = new kDatePicker(222);
        dpDenNgay.setPreferredSize(new Dimension(222, 20));
        panel_3_1_2_1_1.add(dpDenNgay);
        
        JPanel panel_7 = new JPanel();
        FlowLayout flowLayout_2 = (FlowLayout) panel_7.getLayout();
        flowLayout_2.setAlignment(FlowLayout.RIGHT);
//        panel_2.add(panel_7);
        
        chkKhachHangMoi = new JCheckBox("Khách hàng mới");
        panel_7.add(chkKhachHangMoi);
        
        JPanel panel_3_1_1_1_1 = new JPanel();
        FlowLayout flowLayout_11 = (FlowLayout) panel_3_1_1_1_1.getLayout();
        flowLayout_11.setAlignment(FlowLayout.LEFT);
        panel_2.add(panel_3_1_1_1_1);
        
        JLabel lblTngTin = new JLabel("Tổng tiền");
        lblTngTin.setPreferredSize(new Dimension(150, 30));
        panel_3_1_1_1_1.add(lblTngTin);
        
        txtTongTien = new JTextField();
        txtTongTien.setEnabled(false);
        txtTongTien.setColumns(20);
        panel_3_1_1_1_1.add(txtTongTien);
        
        JPanel panel_5 = new JPanel();
        panel_2.add(panel_5);
        
        btnDatPhong = new JButton("Đặt phòng");
        btnDatPhong.setBackground(Color.WHITE);
        panel_5.add(btnDatPhong);
        
        btnSua = new JButton("Sửa lịch đặt");
        btnSua.setBackground(Color.WHITE);
        panel_5.add(btnSua);
        
        btnLamMoi = new JButton("Tạo đơn đặt phòng khác");
        btnLamMoi.setBackground(Color.WHITE);
        panel_5.add(btnLamMoi);
        
        JPanel panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_6 = new JPanel();
        panel_1.add(panel_6, BorderLayout.NORTH);
        
        JLabel lblTimKiem = new JLabel("Mã phòng: ");
        panel_6.add(lblTimKiem);
        
        txtTimKiem = new JTextField();
        txtTimKiem.setPreferredSize(new Dimension(150, 26));
        panel_6.add(txtTimKiem);
        txtTimKiem.setColumns(10);
        
        JButton btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.setBackground(Color.WHITE);
        panel_6.add(btnTimKiem);
        
        JLabel lblLoc = new JLabel("Lọc theo loại phòng");
        panel_6.add(lblLoc);
        
        modelLoaiPhong = new DefaultComboBoxModel<String>();
        cboLoc = new JComboBox(modelLoaiPhong);
        panel_6.add(cboLoc);
        
        JButton btnXemLichDat = new JButton("Xem lịch đặt");
        btnXemLichDat.setBackground(Color.WHITE);
        panel_6.add(btnXemLichDat);
        
        btnThem = new JButton("Thêm phòng này");
        btnThem.setBackground(Color.WHITE);
        panel_6.add(btnThem);
        
        
        
        JPanel panel_8 = new JPanel();
        panel_1.add(panel_8, BorderLayout.CENTER);
        
        String[] cols = {"Mã phòng", "Vị trí", "Số giường", "Số người", "Loại phòng", "Giá phòng"};
        modelDSPhong = new DefaultTableModel(cols, 0);
        panel_8.setLayout(new GridLayout(2, 1, 0, 0));
        tblDSPhong = new JTable(modelDSPhong);
        JScrollPane scrollPane = new JScrollPane(tblDSPhong);
        panel_8.add(scrollPane);
        
        JPanel panel_9 = new JPanel();
        panel_9.setBorder(new TitledBorder(null, "Danh s\u00E1ch ph\u00F2ng \u0111\u00E3 ch\u1ECDn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_8.add(panel_9);
        panel_9.setLayout(new BorderLayout(0, 0));
        
        modelChiTietDon = new DefaultTableModel(cols, 0);
        
        JPanel panel_10 = new JPanel();
        FlowLayout flowLayout_9 = (FlowLayout) panel_10.getLayout();
        flowLayout_9.setAlignment(FlowLayout.RIGHT);
        panel_9.add(panel_10, BorderLayout.NORTH);
        
        JButton btnHuy = new JButton("Bỏ đặt phòng này");
        btnHuy.setBackground(Color.WHITE);
        panel_10.add(btnHuy);
        tblChiTietDon = new JTable(modelChiTietDon);
        JScrollPane scrollPane_1 = new JScrollPane(tblChiTietDon);
        panel_9.add(scrollPane_1);
        
        btnLamMoi.addActionListener((e) -> {
        	txtMaHD.setText("");
			modeThem();
        	clear();
			cthdp.clear();
			tinhTongTien();
			
			renderDSPhong();
			renderChiTietHDP();
			cboKhachHang.setSelectedIndex(0);
        });
        
        cboLoc.addActionListener((e) -> {
        	renderDSPhong();
        });
        
        cboKhachHang.addActionListener((e) -> {
        	int idx = cboKhachHang.getSelectedIndex();
        	if(idx <= 0)
        		return;
        	
        	chkKhachHangMoi.setSelected(false);
        	showKhachHang(dskh.get(idx-1));
        	disableForm();
        	
        });
        
        chkKhachHangMoi.addActionListener((e) -> {
        	if(chkKhachHangMoi.isSelected()) {
        		enableForm();
        	}else {
        		disableForm();
        	}
        });
        
        btnThem.addActionListener((e) -> {
        	int idx = tblDSPhong.getSelectedRow();
        	if(idx == -1) {
        		JOptionPane.showMessageDialog(contentPane, "Vui lòng chọn phòng");
        		enableThemPhongNay();
        		return ;
        	}
        	Phong phong = dsp.get(idx);
        	
        	disableThemPhongNay(phong.getMaPhong());
        	new Thread(() -> {
	        	
	        	
	        	
	        	
	        	Date d1 = Ngay.homNay();
	        	Date d2 = Ngay.homNay();
	        	
	        	try {
					d1 = dpTuNgay.getFullDate();
					d2 = dpDenNgay.getFullDate();
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
	        	
	        	
	        	try {
					if(client.getPhongDao().kiemTraPhongTrong(phong.getMaPhong(), d1, d2) == true) {
						cthdp.add(phong);
			        	renderDSPhong();
			        	tinhTongTien();
			        	enableThemPhongNay();
						return;
					}
				} catch (RemoteException | MalformedURLException | NotBoundException e2) {
					e2.printStackTrace();
				}
	        	enableThemPhongNay();
	        	JOptionPane.showMessageDialog(contentPane, "Phòng "+ phong.getMaPhong() +" không trống trong thời điểm bạn đặt");
        	}).start();
        });
        
        btnHuy.addActionListener((e) -> {
        	int idx = tblChiTietDon.getSelectedRow();
        	if(idx == -1) {
        		JOptionPane.showMessageDialog(contentPane, "Vui lòng chọn phòng trong danh sách phòng đã chọn để hủy");
        		return ;
        	}
        	
        	cthdp.remove(idx);
        	renderDSPhong();
        	tinhTongTien();
        });
        
        btnDatPhong.addActionListener((e) -> {
        	if(cthdp.size() == 0) {
        		JOptionPane.showMessageDialog(contentPane, "Vui lòng thêm phòng");
        		return;
        	}
        	
        	if(txtTenKH.getText().trim().equals("")){
                renderError(txtTenKH, "Tên khách hàng không được để trống");
                return;
            }

            if(!txtTenKH.getText().matches("^[^0-9]{2,25}$")){
                renderError(txtTenKH, "Tên khách hàng không được chứa chữ số, ít nhất là 2 ký tự");
                return;
            }

            if(txtCMND.getText().trim().equals("")){
                renderError(txtCMND, "Cmnd không được để trống");
                return;
            }

            if(!txtCMND.getText().matches("^(\\d{9}|\\d{12})$")){
                renderError(txtTenKH, "Cmnd chỉ được chứa chữ số, bao gồm 9 hoặc 12 ký tự");
                return;
            }
            
            if(!txtSoDienThoai.getText().matches("^0[0-9]{9}$")){
	            renderError(txtSoDienThoai, "Số điện thoại không hợp lệ");
	            return;
	        }
            
            Date homNay = Ngay.homNay(); 
            Date ngayHetHan = Ngay.homNay();
            try {
                ngayHetHan = dpNgayHetHan.getFullDate();
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            
            if(!ngayHetHan.after(homNay)){
                JOptionPane.showMessageDialog(contentPane, "Giấy tờ đã hết hạn, không thể đặt phòng");
                return;
            }
            
        	Date tuNgay = Ngay.homNay(), denNgay = Ngay.homNay();
            
            try {
                tuNgay = dpTuNgay.getFullDate();
                denNgay = dpDenNgay.getFullDate(); 
            } catch (ParseException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            
            if(!tuNgay.toString().equals(homNay.toString()) && tuNgay.before(homNay)){
                JOptionPane.showMessageDialog(pnMain, "Ngày đến phải không được trước ngày hiện tại");
                return;
            }
            
            if(!tuNgay.toString().equals(denNgay.toString()) && denNgay.before(tuNgay)){
                JOptionPane.showMessageDialog(pnMain, "Ngày đi không được trước ngày đến");
                return;
            }
            
            if(!denNgay.toString().equals(ngayHetHan.toString()) && ngayHetHan.before(denNgay)){
                JOptionPane.showMessageDialog(pnMain, "Giấy tờ không đủ hạn, không thể đặt phòng");
                return;
            }
            
        	int idx = cboKhachHang.getSelectedIndex();
        	if(idx <= 0) {
        		JOptionPane.showMessageDialog(contentPane, "Vui lòng chọn khách hàng");
        	}
        	
        	KhachHang kh = dskh.get(cboKhachHang.getSelectedIndex()-1);
            
            
            
            List<ChiTietHoaDonPhong> dscthdp = new ArrayList<ChiTietHoaDonPhong>();
            cthdp.forEach(phong -> {
            	dscthdp.add(new ChiTietHoaDonPhong(phong, phong.getLoaiPhong().getDonGia()));
            });
            HoaDonPhong hdp = new HoaDonPhong(tuNgay, denNgay, kh, dscthdp);
            try {
            	int maHD = client.getHoaDonPhongDao().themHoaDonPhong(hdp);
				if(maHD != -1) {
					txtMaHD.setText(String.valueOf(maHD));
					JOptionPane.showMessageDialog(contentPane, "Đặt phòng thành công");
//					clear();
//					cthdp = new ArrayList<Phong>();
					modeSua();
//					renderData();
					return;
				}	
				
				JOptionPane.showMessageDialog(contentPane, client.getHoaDonPhongDao().getError());
				return;
				
			} catch (HeadlessException | RemoteException | MalformedURLException | NotBoundException e1) {

				e1.printStackTrace();
			}
            
            JOptionPane.showMessageDialog(contentPane, "Đặt phòng thất bại");
        });
        
        btnSua.addActionListener((e) -> {
        	if(cthdp.size() == 0) {
        		JOptionPane.showMessageDialog(contentPane, "Vui lòng thêm phòng");
        		return;
        	}
        	
            
            Date homNay = Ngay.homNay(); 
            Date ngayHetHan = Ngay.homNay();
            try {
                ngayHetHan = dpNgayHetHan.getFullDate();
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            
            if(!ngayHetHan.after(homNay)){
                JOptionPane.showMessageDialog(contentPane, "Giấy tờ đã hết hạn, không thể đặt phòng");
                return;
            }
            
        	Date tuNgay = Ngay.homNay(), denNgay = Ngay.homNay();
            
            try {
                tuNgay = dpTuNgay.getFullDate();
                denNgay = dpDenNgay.getFullDate(); 
            } catch (ParseException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            
            if(!tuNgay.toString().equals(homNay.toString()) && tuNgay.before(homNay)){
                JOptionPane.showMessageDialog(pnMain, "Ngày đến phải không được trước ngày hiện tại");
                return;
            }
            
            if(!tuNgay.toString().equals(denNgay.toString()) && denNgay.before(tuNgay)){
                JOptionPane.showMessageDialog(pnMain, "Ngày đi không được trước ngày đến");
                return;
            }
            
            if(!denNgay.toString().equals(ngayHetHan.toString()) && ngayHetHan.before(denNgay)){
                JOptionPane.showMessageDialog(pnMain, "Giấy tờ không đủ hạn, không thể đặt phòng");
                return;
            }
            
        	int idx = cboKhachHang.getSelectedIndex();
        	if(idx <= 0) {
        		JOptionPane.showMessageDialog(contentPane, "Vui lòng chọn khách hàng");
        	}
        	
        	KhachHang kh = dskh.get(cboKhachHang.getSelectedIndex()-1);
            
            
            
            List<ChiTietHoaDonPhong> dscthdp = new ArrayList<ChiTietHoaDonPhong>();
            cthdp.forEach(phong -> {
            	dscthdp.add(new ChiTietHoaDonPhong(phong, phong.getLoaiPhong().getDonGia()));
            });
            HoaDonPhong hdp = new HoaDonPhong(Integer.parseInt(txtMaHD.getText()), tuNgay, denNgay, kh, dscthdp);
            try {
				if(client.getHoaDonPhongDao().capNhatHoaDonPhong(hdp)) {
					
					JOptionPane.showMessageDialog(contentPane, "Sửa thành công");
					return;
				}	
				
				JOptionPane.showMessageDialog(contentPane, client.getHoaDonPhongDao().getError());
				return;
				
			} catch (HeadlessException | RemoteException | MalformedURLException | NotBoundException e1) {

				e1.printStackTrace();
			}
            
            JOptionPane.showMessageDialog(contentPane, "Sửa thất bại");
        });
        
        btnTimKiem.addActionListener((e) -> {
        	String maPhong = txtTimKiem.getText();
        	for(int i=0; i<dsp.size(); i++) {
        		if(dsp.get(i).getMaPhong().equals(maPhong)) {
        			System.out.println(i);
        			tblDSPhong.setRowSelectionInterval(i, i);
        			return;
        		}
        	}
        	JOptionPane.showMessageDialog(contentPane, "Không tìm thấy");
        });
        
        tblDSPhong.getSelectionModel().addListSelectionListener((e) -> {
        	tblChiTietDon.clearSelection();
        });
        
        tblChiTietDon.getSelectionModel().addListSelectionListener((e) -> {
        	tblDSPhong.clearSelection();
        });
        
        btnXemLichDat.addActionListener((e) -> {
        	int idx1 = tblDSPhong.getSelectedRow();
        	int idx2 = tblChiTietDon.getSelectedRow();
        	if(idx1 == -1 && idx2 == -1) {
        		JOptionPane.showMessageDialog(contentPane, "Vui lòng chọn phòng để xem lịch đặt");
        		return ;
        	}
        	String maPhong = "";
        	if(idx1 != -1)
        		maPhong = dsp.get(idx1).getMaPhong();
        	else
        		maPhong = cthdp.get(idx2).getMaPhong();
        	
        	DialogLichDatPhong dialog = new DialogLichDatPhong();
        	dialog.setMaPhong(maPhong);
        	dialog.renderData();
        	dialog.setVisible(true);
        });
        
        disableForm();
        modeThem();
        handleChangeDate();
    }

    public void renderData(){
    	renderDSPhong();
    	renderKhachHang();
    	renderLoaiPhong();
    	tinhTongTien();
    }

    public void renderDSPhong(){
        PhongDao phongDao = null;
		try {
			phongDao = client.getPhongDao();
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
        
        int idx = cboLoc.getSelectedIndex();
        if(idx <= 0)
			try {
				dsp = phongDao.getListPhong();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		else {
        	try {
				dsp = phongDao.getPhongByMaLoaiPhong(dslp.get(idx-1).getMaLoaiPhong());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
        }
        
        tblDSPhong.clearSelection();
        modelDSPhong.getDataVector().removeAllElements();
        for(int j=0; j<dsp.size(); j++) {
        	Phong phong = dsp.get(j);
        	
        	boolean flag = true;
        	for(int i=0; i<cthdp.size(); i++) {
        		if(phong.getMaPhong().equals(cthdp.get(i).getMaPhong()))
        			flag = false;
        	}
        	if(flag == false) {
        		dsp.remove(j);
        		j--;
        		continue;
        	}
        	modelDSPhong.addRow(new Object[] {
        		phong.getMaPhong(),
        		phong.getViTri(),
        		phong.getSoGiuong(),
        		phong.getSucChua(),
        		phong.getLoaiPhong().getTenLoaiPhong(),
        		phong.getLoaiPhong().getDonGia()
        	});
        };
        tblDSPhong.revalidate();
        tblDSPhong.repaint();
        
        renderChiTietHDP();
    }

    public void renderLoaiPhong(){
        modelLoaiPhong.removeAllElements();
        modelLoaiPhong.addElement("Tất cả");
        try {
			dslp = client.getLoaiPhongDao().getDSLoaiPhong();
		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        dslp.forEach(lp -> {
        	modelLoaiPhong.addElement(lp.getTenLoaiPhong());
        });
    }
    
    
    public void renderChiTietHDP() {
    	tblChiTietDon.clearSelection();
    	modelChiTietDon.getDataVector().removeAllElements();
    	cthdp.forEach(phong -> {
    		modelChiTietDon.addRow(new Object[] {
        		phong.getMaPhong(),
        		phong.getViTri(),
        		phong.getSoGiuong(),
        		phong.getSucChua(),
        		phong.getLoaiPhong().getTenLoaiPhong(),
        		phong.getLoaiPhong().getDonGia()
        	});
    	});
    	
    	tblChiTietDon.revalidate();
    	tblChiTietDon.repaint();
    }

    public void renderKhachHang(){
        modelTenKH.removeAllElements();
        modelTenKH.addElement("");
        try {
			dskh = client.getKhachHangDao().getListKhachHang();
		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			e.printStackTrace();
		}
        dskh.forEach(kh -> {
        	modelTenKH.addElement("#"+kh.getMaKH()+" "+kh.getTenKH());
        });
    }
    
    public void handleChangeDate() {
    	
    	dpTuNgay.btn.addActionListener(e -> {
    		Timer tuNgayInterval = new Timer();
    		tuNgayInterval.scheduleAtFixedRate(new TimerTask(){
    			private int c = 100; // 10s
    		    @Override
    		    public void run(){
    		    	tinhTongTien();
    		    	if(c == 0) {
    		    		tuNgayInterval.cancel();
    		    	}
    		    	c--;
    		    }
    		},0,100);
    	});
    	
    	dpDenNgay.btn.addActionListener(e -> {
    		Timer tuNgayInterval = new Timer();
    		tuNgayInterval.scheduleAtFixedRate(new TimerTask(){
    			private int c = 100; // 10s
    		    @Override
    		    public void run(){
    		    	tinhTongTien();
    		    	if(c == 0) {
    		    		tuNgayInterval.cancel();
    		    	}
    		    	c--;
    		    }
    		},0,100);
    	});
    }
    
    public void tinhTongTien() {
    	tongTien = 0;
    	
    	Date tuNgay = Ngay.homNay(), denNgay = Ngay.homNay();
        
        try {
            tuNgay = dpTuNgay.getFullDate();
            denNgay = dpDenNgay.getFullDate(); 
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    	
    	int soNgay = (int) Ngay.tinhKhoangNgay(tuNgay, denNgay);
    	cthdp.forEach(phong -> {
    		tongTien += phong.getLoaiPhong().getDonGia() * soNgay;
    	});
    	
    	txtTongTien.setText(Currency.format(tongTien));
    }
    
    public void showKhachHang(KhachHang kh) {
    	txtTenKH.setText(String.valueOf(kh.getTenKH()));
    	txtCMND.setText(kh.getCmnd());
    	txtSoDienThoai.setText(kh.getSoDienThoai());
    	dpNgayHetHan.setValue(kh.getNgayHetHan());
    	if(kh.getLoaiKH().toLowerCase().equals("nước ngoài"))
    		cboLoaiKH.setSelectedIndex(1);
    	else
    		cboLoaiKH.setSelectedIndex(0);
    }
    
    public void clear() {
    	cboKhachHang.setSelectedIndex(0);
    	txtTenKH.setText("");
    	txtCMND.setText("");
    	txtSoDienThoai.setText("");
    	cboLoaiKH.setSelectedIndex(0);
    	dpTuNgay.setValueToDay();
    	dpDenNgay.setValueToDay();
    }
    
    public void disableForm() {
    	txtTenKH.setEnabled(false);
    	txtCMND.setEnabled(false);
    	txtSoDienThoai.setEnabled(false);
    	dpNgayHetHan.btn.setEnabled(false);
    	cboLoaiKH.setEnabled(false);
    }
    
    public void enableForm() {
    	cboKhachHang.setSelectedIndex(0);
    	txtTenKH.setEnabled(true);
    	txtCMND.setEnabled(true);
    	txtSoDienThoai.setEnabled(true);
    	dpNgayHetHan.btn.setEnabled(true);
    	cboLoaiKH.setEnabled(true);
    }
    
    public void modeThem() {
    	txtMaHD.setText("");
    	btnDatPhong.setVisible(true);
    	btnSua.setVisible(false);
    	cboKhachHang.setEnabled(true);
    }
    
    public void modeSua() {
    	btnDatPhong.setVisible(false);
    	btnSua.setVisible(true);
    	cboKhachHang.setEnabled(false);
    }
    
    


    public HoaDonPhong get_hdp() {
		return _hdp;
	}

	public void set_hdp(HoaDonPhong _hdp) {
		this._hdp = _hdp;
		txtMaHD.setText(String.valueOf(_hdp.getMaHD()));
		for(int i=0; i<dskh.size(); i++) {
			if(_hdp.getKhachHang().getMaKH() == dskh.get(i).getMaKH()) {
				cboKhachHang.setSelectedIndex(i+1);
				break;
			}
		}
		cthdp = new ArrayList<Phong>();
		
		_hdp.getDsChiTietHoaDonPhong().forEach(cthd -> {
			cthdp.add(cthd.getPhong());
		});
		dpTuNgay.setValue(_hdp.getNgayGioNhan());
		dpDenNgay.setValue(_hdp.getNgayGioTra());
		renderDSPhong();
		renderChiTietHDP();
		tinhTongTien();
		modeSua();
		
	}
	
	public void setPhong(String maPhong) {
		for(int i=0; i<dsp.size(); i++) {
			if(dsp.get(i).getMaPhong().equals(maPhong)) {
				tblDSPhong.setRowSelectionInterval(i, i);
				break;
			}
		}
	}
	
	public void disableThemPhongNay(String maPhong) {
		btnThem.setText("Đang kiểm tra tình trạng phòng "+maPhong);
		btnThem.setEnabled(false);
	}
	
	public void enableThemPhongNay() {
		btnThem.setText("Thêm phòng này");
		btnThem.setEnabled(true);
	}

	public void renderError(JTextField a, String message){
        a.requestFocus();
        a.selectAll();
        JOptionPane.showMessageDialog(pnMain, message);
    }

    
    public JPanel getContentPane() {
    	return contentPane;
    }
}
