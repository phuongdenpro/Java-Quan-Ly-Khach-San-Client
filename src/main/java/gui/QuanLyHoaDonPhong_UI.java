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
import model.HoaDonDV;
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


public class QuanLyHoaDonPhong_UI extends JFrame{
	private Client client;
    private int maHD = 0;
    
    public JPanel pnMain;
    public String maPhong = "0";
    private ImageIcon icon_add = new ImageIcon("data/images/add.png");
    private ImageIcon icon_refresh = new ImageIcon("data/images/refresh.png");
    private ImageIcon icon_trash = new ImageIcon("data/images/trash.png");
    private ImageIcon icon_edit = new ImageIcon("data/images/edit.png");
    private ImageIcon icon_search = new ImageIcon("data/images/magnifying-glass.png");
    private JTextField textField;
    private JTextField txtNgayHetHan;
    private JTextField textField_1;
    private JTextField txtTimKiem;
	private DefaultTableModel modelDSHDPhong;
	private DefaultComboBoxModel<String> modelT;
	private DefaultComboBoxModel<String> modelTenKH;
	private DefaultComboBoxModel<String> modelLoaiKH;
	private JTable tblDSHDPhong;
	private DefaultTableModel modelChiTietDon;
	private JTable tblChiTietDon;
	private JButton btnSuaHoaDon;
	private JButton btnLamMoi;
	private List<KhachHang> dskh;
	private List<LoaiPhong> dslp;
	private List<Phong> dsp;
	private kDatePicker dpTuNgay;
	private kDatePicker dpDenNgay;
	private List<Phong> cthdp = new ArrayList<Phong>();
	private JPanel contentPane;
	private JTextField txtMaHD;
	private JComboBox cboTinhTrang;
	private List<HoaDonPhong> dshdp;
	private List<ChiTietHoaDonPhong> dscthdp = new ArrayList<ChiTietHoaDonPhong>();
	private Date tuNgay;
	private Date denNgay;
	private String error;
	private JComboBox cboTimKiem;
	private JButton btnTimKiem;
	private String where_sql = "";
	private double tongTien;
    
    
    public static void main(String[] args) throws IOException, NotBoundException {
		QuanLyHoaDonPhong_UI datPhongUI = new QuanLyHoaDonPhong_UI();
//		datPhongUI.start();
		datPhongUI.setVisible(true);
		datPhongUI.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
    
    public QuanLyHoaDonPhong_UI() throws IOException, NotBoundException{
    	client = new Client();
    	start();
    }

    public void start() throws MalformedURLException, RemoteException, NotBoundException{

        renderGUI();
        renderData();
    }

    public void renderGUI() {
    	setSize(1350, 600);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_11 = new JPanel();
        contentPane.add(panel_11, BorderLayout.NORTH);
        
        JLabel lblQuanLyHoaDonPhong = new JLabel("Quản lý hóa đơn phòng");
        lblQuanLyHoaDonPhong.setFont(new Font("Tahoma", Font.PLAIN, 20));
        panel_11.add(lblQuanLyHoaDonPhong);
        
        JPanel panel_10 = new JPanel();
        contentPane.add(panel_10, BorderLayout.CENTER);
        panel_10.setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        panel_10.add(panel, BorderLayout.WEST);
        
        JPanel panel_2 = new JPanel();
        panel.add(panel_2);
        panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
        
        JPanel panel_4 = new JPanel();
        panel_2.add(panel_4);
        
        JLabel lblThongTinDatPhong = new JLabel("Thông tin đặt phòng");
        lblThongTinDatPhong.setFont(new Font("Tahoma", Font.PLAIN, 20));
        panel_4.add(lblThongTinDatPhong);
        
        JPanel panel_3 = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        panel_2.add(panel_3);
        
        JLabel lblMaHD = new JLabel("Mã hóa đơn");
        lblMaHD.setPreferredSize(new Dimension(150, 30));
        panel_3.add(lblMaHD);
        
        txtMaHD = new JTextField();
        txtMaHD.setEditable(false);
        panel_3.add(txtMaHD);
        txtMaHD.setColumns(20);
        
        modelTenKH = new DefaultComboBoxModel<String>();
        
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
        
        JPanel panel_3_1_2_1_1_1 = new JPanel();
        FlowLayout flowLayout_1 = (FlowLayout) panel_3_1_2_1_1_1.getLayout();
        flowLayout_1.setAlignment(FlowLayout.LEFT);
        panel_2.add(panel_3_1_2_1_1_1);
        
        JLabel lblTnhTrng = new JLabel("Tình trạng");
        lblTnhTrng.setPreferredSize(new Dimension(150, 30));
        panel_3_1_2_1_1_1.add(lblTnhTrng);
        
        
        cboTinhTrang = new JComboBox();
        cboTinhTrang.setPreferredSize(new Dimension(222, 22));
        panel_3_1_2_1_1_1.add(cboTinhTrang);
        cboTinhTrang.addItem("Chưa nhận phòng");
        cboTinhTrang.addItem("Đã nhận phòng");
        cboTinhTrang.addItem("Đã trả phòng");
        
        JPanel panel_5 = new JPanel();
        panel_2.add(panel_5);
        panel_5.setLayout(new GridLayout(0, 2, 10, 5));
        
        btnSuaHoaDon = new JButton("Sửa hóa đơn");
        btnSuaHoaDon.setBackground(Color.WHITE);
        panel_5.add(btnSuaHoaDon);
        
        JButton btnXoa = new JButton("Xóa");
        btnXoa.setBackground(Color.WHITE);
        panel_5.add(btnXoa);
        
        JButton btnSuaDanhSachPhong = new JButton("Sửa danh sách phòng");
        btnSuaDanhSachPhong.setBackground(Color.WHITE);
        panel_5.add(btnSuaDanhSachPhong);
        
        btnLamMoi = new JButton("Làm mới");
        btnLamMoi.setBackground(Color.WHITE);
        panel_5.add(btnLamMoi);
        
        JPanel panel_1 = new JPanel();
        panel_10.add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_6 = new JPanel();
        panel_1.add(panel_6, BorderLayout.NORTH);
        
        JLabel lblTimKiemTheo = new JLabel("Tìm kiếm theo: ");
        panel_6.add(lblTimKiemTheo);
        
        cboTimKiem = new JComboBox();
        cboTimKiem.addItem("Mã hóa đơn");
        cboTimKiem.addItem("Tên Khách hàng");
        cboTimKiem.addItem("CMND");
        cboTimKiem.addItem("Số điện thoại");
        panel_6.add(cboTimKiem);
        
        txtTimKiem = new JTextField();
        txtTimKiem.setPreferredSize(new Dimension(150, 26));
        panel_6.add(txtTimKiem);
        txtTimKiem.setColumns(10);
        
        btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.setBackground(Color.WHITE);
        panel_6.add(btnTimKiem);
        
        JButton btnDatLai = new JButton("Làm mới dữ liệu");
        btnDatLai.setBackground(Color.WHITE);
        panel_6.add(btnDatLai);
        
        
        
        
        
        JPanel panel_8 = new JPanel();
        panel_1.add(panel_8, BorderLayout.CENTER);
        
        String[] cols = {"Mã hóa đơn", "Tên khách hàng", "CMND", "Số điện thoại", "Loại khách hàng", "Ngày đến", "Ngày đi", "Tình trạng", "Tổng tiền"};
        modelDSHDPhong = new DefaultTableModel(cols, 0);
        panel_8.setLayout(new GridLayout(2, 1, 0, 0));
        tblDSHDPhong = new JTable(modelDSHDPhong);
        JScrollPane scrollPane = new JScrollPane(tblDSHDPhong);
        panel_8.add(scrollPane);
        
        JPanel panel_9 = new JPanel();
        panel_9.setBorder(new TitledBorder(null, "Danh s\u00E1ch ph\u00F2ng \u0111\u1EB7t", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_8.add(panel_9);
        panel_9.setLayout(new BorderLayout(0, 0));
        
                
        JPanel panel_7 = new JPanel();
        FlowLayout flowLayout_2 = (FlowLayout) panel_7.getLayout();
        flowLayout_2.setAlignment(FlowLayout.RIGHT);
        panel_9.add(panel_7, BorderLayout.NORTH);
        
        JButton btnXemLichDat = new JButton("Xem lịch đặt");
        btnXemLichDat.setBackground(Color.WHITE);
        panel_7.add(btnXemLichDat);
        
        JButton btnLamMoiDSP = new JButton("Làm mới dữ liệu");
        btnLamMoiDSP.setBackground(Color.WHITE);
        panel_7.add(btnLamMoiDSP);
        
        String[] cols2 = {"Mã phòng", "Vị trí", "Số giường", "Số người", "Loại phòng", "Đơn giá"};
        modelChiTietDon = new DefaultTableModel(cols2, 0);

        tblChiTietDon = new JTable(modelChiTietDon);
        JScrollPane scrollPane_1 = new JScrollPane(tblChiTietDon);
        panel_9.add(scrollPane_1);
        
        btnLamMoi.addActionListener((e) -> {
        	clear();
        });
        

        
        btnSuaHoaDon.addActionListener((e) -> {
        	int idx = tblDSHDPhong.getSelectedRow();
        	if(idx == -1) {
        		JOptionPane.showMessageDialog(contentPane, "Vui lòng chọn hóa đơn để sửa");
        		return;
        	}
        	
        	if(checkDate() == false) {
        		return;
        	}
        	HoaDonPhong hdp = dshdp.get(idx);
//        	check tình trạng
        	int tinhTrang = cboTinhTrang.getSelectedIndex(); 
        	
        	if(tinhTrang < hdp.getTinhTrang()) {
        		JOptionPane.showMessageDialog(contentPane, "Tình trạng không hợp lệ");
        		return;
        	}
        	
//        	System.out.println(Ngay.homNay());
//        	System.out.println(hdp.getNgayGioNhan().after(Ngay.homNay()));
//        	System.out.println(hdp.getNgayGioTra().before(Ngay.homNay()));
//        	System.out.println();
//        	
//        	if(tinhTrang == 1 && hdp.getTinhTrang() == 0 && (hdp.getNgayGioNhan().after(Ngay.homNay()) || hdp.getNgayGioTra().before(Ngay.homNay()))) {
//        		JOptionPane.showMessageDialog(contentPane, "Ngày nhận phòng không hợp lệ với hóa đơn đặt");
//        		return;
//        	}
        	
    		tuNgay = Ngay.homNay();
    		denNgay = Ngay.homNay();
            
            try {
                tuNgay = dpTuNgay.getFullDate();
                denNgay = dpDenNgay.getFullDate(); 
            } catch (ParseException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        	
        	hdp.setNgayGioNhan(tuNgay);
        	hdp.setNgayGioTra(denNgay);
        	hdp.setTinhTrang(cboTinhTrang.getSelectedIndex());
        	hdp.setDsChiTietHoaDonPhong(dscthdp);
        	try {
				if(client.getHoaDonPhongDao().capNhatHoaDonPhong(hdp)) {
					JOptionPane.showMessageDialog(contentPane, "Sửa thành công");
					clear();
					renderDSHDPhong();
					return;
				}
				
				JOptionPane.showMessageDialog(contentPane, client.getHoaDonPhongDao().getError());
				return;
			} catch (RemoteException | MalformedURLException | NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
        	JOptionPane.showMessageDialog(contentPane, "Sửa thất bại");
        });
        
        btnXoa.addActionListener((e) -> {
        	int idx = tblDSHDPhong.getSelectedRow();
        	if(idx == -1) {
        		JOptionPane.showMessageDialog(contentPane, "Vui lòng chọn hóa đơn để xóa");
        		return ;
        	}
        	
        	int choose = JOptionPane.showConfirmDialog(contentPane, "Chắc chắn xóa ?");
        	
        	if(choose != 0)
        		return;
        	
        	try {
				if(client.getHoaDonPhongDao().xoaHoaDonPhong(dshdp.get(idx).getMaHD())) {
					JOptionPane.showMessageDialog(contentPane, "Xóa thành công");
					renderData();
					clear();
					clearDSP();
	        		return ;
				}
			} catch (RemoteException | MalformedURLException | NotBoundException e1) {
				e1.printStackTrace();
			}
        	
        	JOptionPane.showMessageDialog(contentPane, "Xóa thất bại");
        });
        
        btnXemLichDat.addActionListener((e) -> {
        	int idx2 = tblChiTietDon.getSelectedRow();
        	if(idx2 == -1) {
        		JOptionPane.showMessageDialog(contentPane, "Vui lòng chọn phòng để xem lịch đặt");
        		return ;
        	}
        	String maPhong = "";
        	maPhong = dscthdp.get(idx2).getPhong().getMaPhong();
        	
        	DialogLichDatPhong dialog = new DialogLichDatPhong();
        	dialog.setMaPhong(maPhong);
        	dialog.renderData();
        	dialog.setVisible(true);
        });
        
        btnTimKiem.addActionListener((e) -> {
        	String value = txtTimKiem.getText();
        	where_sql = "";
        	if(cboTimKiem.getSelectedIndex() == 0) {
        		where_sql = " HoaDonPhong.maHD like N'%"+value+"%' ";
        	}else if(cboTimKiem.getSelectedIndex() == 1){
        		where_sql = " KhachHang.TenKH like N'%"+value+"%' ";
        	}else if(cboTimKiem.getSelectedIndex() == 2){
        		where_sql = " KhachHang.CMND like N'%"+value+"%' ";
        	}else if(cboTimKiem.getSelectedIndex() == 3){
        		where_sql = " KhachHang.soDienThoai like N'%"+value+"%' ";
        	}
        	
        	try {
				renderDSHDPhong();
			} catch (MalformedURLException | RemoteException | NotBoundException e1) {
				e1.printStackTrace();
			}
        });
        
        btnDatLai.addActionListener((e) -> {
        	
        	where_sql = "";
        	txtTimKiem.setText("");
        	clearDSP();
        	try {
				renderDSHDPhong();
			} catch (MalformedURLException | RemoteException | NotBoundException e1) {
				e1.printStackTrace();
			}
        });
        
        btnLamMoiDSP.addActionListener((e) -> {
        	int idx = tblDSHDPhong.getSelectedRow();
        	if(idx != -1) {
        		HoaDonPhong hdp = dshdp.get(idx);
        		try {
        			renderDSPhong(hdp);
				} catch (MalformedURLException | RemoteException | NotBoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        	
        });
        
        tblDSHDPhong.getSelectionModel().addListSelectionListener((e) -> {
        	int idx = tblDSHDPhong.getSelectedRow();
        	if(idx != -1) {
        		HoaDonPhong hdp = dshdp.get(idx);
        		txtMaHD.setText(String.valueOf(hdp.getMaHD()));
        		dpTuNgay.setValue(hdp.getNgayGioNhan());
        		dpDenNgay.setValue(hdp.getNgayGioTra());
        		cboTinhTrang.setSelectedIndex(hdp.getTinhTrang());
        		
        		try {
    				renderDSPhong(hdp);
    			} catch (MalformedURLException | RemoteException | NotBoundException e1) {
    				e1.printStackTrace();
    			}
        	}
        	
        });
        
        btnSuaDanhSachPhong.addActionListener((e) -> {
        	int idx = tblDSHDPhong.getSelectedRow();
        	if(idx == -1) {
        		JOptionPane.showMessageDialog(contentPane, "Chọn hóa đơn để sửa");
        		return;
        	}
        	
        	HoaDonPhong hdp = dshdp.get(idx);
        	hdp.setDsChiTietHoaDonPhong(dscthdp);
        	DatPhong_UI pgDatPhong;
			pgDatPhong = new DatPhong_UI();
        	
        	pgDatPhong.set_hdp(hdp);
        	pgDatPhong.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        	pgDatPhong.setVisible(true);
        });
        
    }

    public void renderData() throws MalformedURLException, RemoteException, NotBoundException{
    	renderDSHDPhong();
    }
    
    public void renderDSHDPhong() throws MalformedURLException, RemoteException, NotBoundException{
    	if(where_sql.equals("")) {
    		dshdp = client.getHoaDonPhongDao().getListHoaDonPhong();
    	}else {
    		dshdp = client.getHoaDonPhongDao().timKiemHDP(where_sql);
    		
    		if(dshdp.size() == 0) {
    			JOptionPane.showMessageDialog(contentPane, "Không tìm thấy");
    		}
    	}
    	
    	
    	tblDSHDPhong.clearSelection();
    	modelDSHDPhong.getDataVector().removeAllElements();
    	dshdp.forEach(hdp -> {
    		modelDSHDPhong.addRow(new Object[] {
    			hdp.getMaHD(),
    			hdp.getKhachHang().getTenKH(),
    			hdp.getKhachHang().getCmnd(),
    			hdp.getKhachHang().getSoDienThoai(),
    			hdp.getKhachHang().getLoaiKH(),
    			hdp.getNgayGioNhan(),
    			hdp.getNgayGioTra(),
    			convertTinhTrang(hdp.getTinhTrang()),
    			Currency.format(tinhTongTien(hdp))
    		});
    	});
    	tblDSHDPhong.revalidate();
    	tblDSHDPhong.repaint();
    }
    
   

    public void renderDSPhong(HoaDonPhong hdp) throws MalformedURLException, RemoteException, NotBoundException{
        dscthdp = client.getChiTietHoaDonPhongDao().getListChiTietHDPByMaHD(hdp.getMaHD());
        
        tblChiTietDon.clearSelection();
        modelChiTietDon.getDataVector().removeAllElements();
        dscthdp.forEach(cthdp -> {
        	Phong phong = cthdp.getPhong();
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

    public double tinhTongTien(HoaDonPhong hdp) {
    	tongTien = 0.0;
    	
    	int soNgay = (int) Ngay.tinhKhoangNgay(hdp.getNgayGioNhan(), hdp.getNgayGioTra());
    	List<ChiTietHoaDonPhong> dscthdp = new ArrayList<ChiTietHoaDonPhong>();
    	
		try {
			dscthdp = client.getChiTietHoaDonPhongDao().getListChiTietHDPByMaHD(hdp.getMaHD());
		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			e.printStackTrace();
		}
		
    	dscthdp.forEach(cthdp -> {
    		tongTien += cthdp.getPhong().getLoaiPhong().getDonGia() * soNgay;
    	});
    	
    	return tongTien;
    }
    
    
    
    public void clear() {
    	txtMaHD.setText("");
    	cboTinhTrang.setSelectedIndex(0);
    	dpTuNgay.setValueToDay();
    	dpDenNgay.setValueToDay();
    }
    
    public void clearDSP() {
    	dscthdp.clear();
    	modelChiTietDon.getDataVector().removeAllElements();
    	tblChiTietDon.revalidate();
    	tblChiTietDon.repaint();
    }
    
    public boolean checkDate() {
    	int idx = tblDSHDPhong.getSelectedRow();
    	if(idx != -1) {
    		return true;
    	}
    	
    	HoaDonPhong hdp = dshdp.get(idx);
    	
    	Date homNay = Ngay.homNay(); 
    	Date tuNgay = Ngay.homNay(), denNgay = Ngay.homNay();
      
		try {
			tuNgay = dpTuNgay.getFullDate();
			denNgay = dpDenNgay.getFullDate();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		if(tuNgay.equals(hdp.getNgayGioNhan()) && denNgay.equals(hdp.getNgayGioTra())) {
//			không thay đổi thời gian
			return true;
		}

		if (!tuNgay.toString().equals(homNay.toString()) && tuNgay.before(homNay)) {
			JOptionPane.showMessageDialog(contentPane, "Ngày đến phải không được trước ngày hiện tại");
			return false;
		}

		if (!tuNgay.toString().equals(denNgay.toString()) && denNgay.before(tuNgay)) {
			JOptionPane.showMessageDialog(contentPane, "Ngày đi không được trước ngày đến");
			return false;
		}

		return true;
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


    public void renderError(JTextField a, String message){
        a.requestFocus();
        a.selectAll();
        JOptionPane.showMessageDialog(pnMain, message);
    }

    
    public JPanel getContentPane() {
    	return contentPane;
    }
}
