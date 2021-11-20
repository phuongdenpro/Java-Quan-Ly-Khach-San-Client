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
import java.sql.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.SwingConstants;


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
	private JPanel contentPane;;
    
    
    
    public static void main(String[] args) throws IOException, NotBoundException {
		DatPhong_UI datPhongUI = new DatPhong_UI();
//		datPhongUI.start();
		datPhongUI.setVisible(true);
		datPhongUI.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
    
    public DatPhong_UI() throws IOException, NotBoundException{
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
        panel_2.add(panel_7);
        
        chkKhachHangMoi = new JCheckBox("Khách hàng mới");
        panel_7.add(chkKhachHangMoi);
        
        JPanel panel_5 = new JPanel();
        panel_2.add(panel_5);
        
        btnDatPhong = new JButton("Đặt phòng");
        btnDatPhong.setBackground(Color.WHITE);
        panel_5.add(btnDatPhong);
        
        btnLamMoi = new JButton("Làm mới");
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
        
        JButton btnThem = new JButton("Thêm phòng này");
        btnThem.setBackground(Color.WHITE);
        panel_6.add(btnThem);
        
        JButton btnHuy = new JButton("Xóa phòng này");
        btnHuy.setBackground(Color.WHITE);
        panel_6.add(btnHuy);
        
        
        
        JPanel panel_8 = new JPanel();
        panel_1.add(panel_8, BorderLayout.CENTER);
        
        String[] cols = {"Mã phòng", "Vị trí", "Số giường", "Số người", "Loại phòng", "Giá phòng"};
        modelDSPhong = new DefaultTableModel(cols, 0);
        panel_8.setLayout(new GridLayout(2, 1, 0, 0));
        tblDSPhong = new JTable(modelDSPhong);
        JScrollPane scrollPane = new JScrollPane(tblDSPhong);
        panel_8.add(scrollPane);
        
        JPanel panel_9 = new JPanel();
        panel_8.add(panel_9);
        panel_9.setLayout(new BorderLayout(0, 0));
        
        JLabel lblNewLabel = new JLabel("Dang sách phòng đặt");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel_9.add(lblNewLabel, BorderLayout.NORTH);
        
        modelChiTietDon = new DefaultTableModel(cols, 0);
        tblChiTietDon = new JTable(modelChiTietDon);
        JScrollPane scrollPane_1 = new JScrollPane(tblChiTietDon);
        panel_9.add(scrollPane_1);
        
        btnLamMoi.addActionListener((e) -> {
        	clear();
        });
        
        cboLoc.addActionListener((e) -> {
        	try {
				renderDSPhong();
			} catch (MalformedURLException | RemoteException | NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
        		return ;
        	}
        	
        	Phong phong = dsp.get(idx);
        	
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
		        	try {
						renderDSPhong();
						return;
					} catch (MalformedURLException | RemoteException | NotBoundException e1) {
						e1.printStackTrace();
					}
				}
			} catch (RemoteException | MalformedURLException | NotBoundException e2) {
				e2.printStackTrace();
			}
        	
        	JOptionPane.showMessageDialog(contentPane, "Phòng này không trống trong thời điểm bạn đặt");
        	
        });
        
        btnHuy.addActionListener((e) -> {
        	int idx = tblChiTietDon.getSelectedRow();
        	if(idx == -1) {
        		JOptionPane.showMessageDialog(contentPane, "Vui lòng chọn phòng trong danh sách phòng đặt để hủy");
        		return ;
        	}
        	
        	cthdp.remove(idx);
        	try {
				renderDSPhong();
			} catch (MalformedURLException | RemoteException | NotBoundException e1) {
				e1.printStackTrace();
			}
        	
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
            
            KhachHang kh;
            if(chkKhachHangMoi.isSelected()) {
            	kh = new KhachHang(
            			txtTenKH.getText(), 
            			txtCMND.getText(), 
            			txtSoDienThoai.getText(), 
            			ngayHetHan, 
            			(String) cboLoaiKH.getSelectedItem());
            }else {
            	kh = dskh.get(cboKhachHang.getSelectedIndex()-1);
            }
            
            
            
            List<ChiTietHoaDonPhong> dscthdp = new ArrayList<ChiTietHoaDonPhong>();
            cthdp.forEach(phong -> {
            	dscthdp.add(new ChiTietHoaDonPhong(phong, phong.getLoaiPhong().getDonGia()));
            });
            HoaDonPhong hdp = new HoaDonPhong(tuNgay, denNgay, kh, dscthdp);
            try {
				if(client.getHoaDonPhongDao().themHoaDonPhong(hdp)) {
					JOptionPane.showMessageDialog(contentPane, "Đặt phòng thành công");
					clear();
					cthdp = new ArrayList<Phong>();
					renderData();
					return;
				}	
				
			} catch (HeadlessException | RemoteException | MalformedURLException | NotBoundException e1) {

				e1.printStackTrace();
			}
            
            JOptionPane.showMessageDialog(contentPane, "Đặt phòng thất bại");
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
    }

    public void renderData() throws MalformedURLException, RemoteException, NotBoundException{
    	renderDSPhong();
    	renderKhachHang();
    	renderLoaiPhong();
    }

    public void renderDSPhong() throws MalformedURLException, RemoteException, NotBoundException{
        PhongDao phongDao = client.getPhongDao();
        
        int idx = cboLoc.getSelectedIndex();
        if(idx <= 0)
        	dsp = phongDao.getListPhong();
        else {
        	dsp = phongDao.getPhongByMaLoaiPhong(dslp.get(idx-1).getMaLoaiPhong());
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

    public void renderLoaiPhong() throws RemoteException, MalformedURLException, NotBoundException{
        modelLoaiPhong.removeAllElements();
        modelLoaiPhong.addElement("Tất cả");
        dslp = client.getLoaiPhongDao().getDSLoaiPhong();
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

    public void renderKhachHang() throws RemoteException, MalformedURLException, NotBoundException{
        modelTenKH.removeAllElements();
        modelTenKH.addElement("");
        dskh = client.getKhachHangDao().getListKhachHang();
        dskh.forEach(kh -> {
        	modelTenKH.addElement("#"+kh.getMaKH()+" "+kh.getTenKH());
        });
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


    public void renderError(JTextField a, String message){
        a.requestFocus();
        a.selectAll();
        JOptionPane.showMessageDialog(pnMain, message);
    }

    
    public JPanel getContentPane() {
    	return contentPane;
    }
}
