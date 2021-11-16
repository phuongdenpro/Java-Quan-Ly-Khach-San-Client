package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import app.Client;
import model.ChiTietDV;
import model.ChiTietHoaDonPhong;
import model.HoaDonDV;
import model.HoaDonPhong;
import utils.Currency;
import utils.Ngay;

import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;



public class ThanhToan_UI extends JFrame{

    private ImageIcon icon_pay = new ImageIcon("data/images/purse.png");
    private ImageIcon icon_in = new ImageIcon("data/images/printer.png");
    public JPanel pnMain;
    private DefaultTableModel modelHD;
    private DefaultTableModel modelDV;
    private JLabel lbTienPhong;
    private JLabel lbTienDV;
    private JLabel lbTongTien;
    private JButton btnThanhToan;
    private JButton btnIn;
    private JTextField txtTenKH;
    private JTextField txtSDT;
    private JTextField txtCMND;
    private JTextField txtNgayDen;
    private JTextField txtNgayDi;
    
    private HoaDonPhong hdp;
    private HoaDonDV hddv;
	private Client client;
	private JTable tblDSP;
	private JTable tblDSDV;
	private int tienPhong;
	private int tienDV;
	private List<ChiTietHoaDonPhong> dsCTHDP = new ArrayList<ChiTietHoaDonPhong>();
	private List<ChiTietDV> dsCTDV = new ArrayList<ChiTietDV>();
    

    public static void main(String[] args) {
		ThanhToan_UI thanhToanUI = new ThanhToan_UI();
//		thanhToanUI.start();
		thanhToanUI.setVisible(true);
	}
    
    public ThanhToan_UI(HoaDonPhong hdp, HoaDonDV hddv){
    	try {
			client = new Client();
		} catch (IOException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	this.hdp = hdp;
    	this.hddv = hddv;
    	try {
        	dsCTHDP = client.getChiTietHoaDonPhongDao().getListChiTietHDPByMaHD(hdp.getMaHD());
			dsCTDV = client.getChiTietDVDao().getListChiTietDVByMaHDDV(hddv.getMaHDDV());
		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			e.printStackTrace();
		}
    	start();
    }
    
    public ThanhToan_UI(){
    	try {
			client = new Client();
		} catch (IOException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	try {
        	hdp = client.getHoaDonPhongDao().getHDPbyMaHD(1);
        	dsCTHDP = client.getChiTietHoaDonPhongDao().getListChiTietHDPByMaHD(hdp.getMaHD());
			hddv = client.getHoaDonDVDao().getHDDVbyMaHDDV(1);
			dsCTDV = client.getChiTietDVDao().getListChiTietDVByMaHDDV(hddv.getMaHDDV());
		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			// TODO Auto-generated catch block
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
    	
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        JLabel lbHeader = new JLabel("Hóa đơn thanh toán");
        lbHeader.setHorizontalAlignment(SwingConstants.CENTER);
        lbHeader.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        lbHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbHeader.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        contentPane.add(lbHeader, BorderLayout.NORTH);
        // pnMain1.setLayout(new BoxLayout(pnMain1, BoxLayout.X_AXIS));
        JPanel pnMain = new JPanel();
        contentPane.add(pnMain);
        pnMain.setLayout(new BorderLayout(0, 0));
        // thông tin hóa đơn

        JPanel p_left = new JPanel();
        pnMain.add(p_left);
        GridBagLayout gbl_p_left = new GridBagLayout();
        gbl_p_left.columnWidths = new int[] {550, 350};
        gbl_p_left.rowHeights = new int[]{515, 0};
        gbl_p_left.columnWeights = new double[]{0.0, 0.0};
        gbl_p_left.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        p_left.setLayout(gbl_p_left);
        
        String[] cols = {"Mã phòng", "Vị trí", "Số giường", "Số người", "Loại phòng", "Giá phòng", "Thành tiền"};
        modelHD = new DefaultTableModel(cols, 0);
        
        JPanel panel_2 = new JPanel();
        GridBagConstraints gbc_panel_2 = new GridBagConstraints();
        gbc_panel_2.fill = GridBagConstraints.BOTH;
        gbc_panel_2.insets = new Insets(0, 0, 0, 5);
        gbc_panel_2.gridx = 0;
        gbc_panel_2.gridy = 0;
        p_left.add(panel_2, gbc_panel_2);
        panel_2.setLayout(new BorderLayout(0, 0));
        
        JLabel lblDSP = new JLabel("Danh sách phòng đặt");
        lblDSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblDSP.setHorizontalAlignment(SwingConstants.CENTER);
        panel_2.add(lblDSP, BorderLayout.NORTH);
        tblDSP = new JTable(modelHD);
        JScrollPane scrollPane = new JScrollPane(tblDSP);
        panel_2.add(scrollPane, BorderLayout.CENTER);
        
        String[] cols2 = {"Dịch vụ", "Số lượng", "Đơn giá", "Thành tiền"};
        modelDV = new DefaultTableModel(cols2, 0);
        
        JPanel panel_4 = new JPanel();
        GridBagConstraints gbc_panel_4 = new GridBagConstraints();
        gbc_panel_4.fill = GridBagConstraints.BOTH;
        gbc_panel_4.gridx = 1;
        gbc_panel_4.gridy = 0;
        p_left.add(panel_4, gbc_panel_4);
        panel_4.setLayout(new BorderLayout(0, 0));
        
        JLabel lblDSDV = new JLabel("Danh sách dịch vụ");
        lblDSDV.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblDSDV.setHorizontalAlignment(SwingConstants.CENTER);
        panel_4.add(lblDSDV, BorderLayout.NORTH);
        tblDSDV = new JTable(modelDV);
        JScrollPane scrollPane_1 = new JScrollPane(tblDSDV);
        panel_4.add(scrollPane_1);
        
        
        
        // p_sec_info.add(space(10, 10));
        
        // tổng tiền
        JPanel p_right = new JPanel();
        pnMain.add(p_right, BorderLayout.WEST);
        
        JPanel panel = new JPanel();
        p_right.add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JPanel p_sec_total = new JPanel();
        p_sec_total.setPreferredSize(new Dimension(400, 400));
        p_sec_total.setBorder(BorderFactory.createEmptyBorder(0, 20, 30, 30));
        
        panel.add(p_sec_total);
        p_sec_total.setLayout(new BoxLayout(p_sec_total, BoxLayout.Y_AXIS));
        
        JPanel panel_3 = new JPanel();
        panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        p_sec_total.add(panel_3);
        panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
        
        JPanel panel_1 = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        panel_3.add(panel_1);
        
        JLabel lblTenKH = new JLabel("Tên khách hàng");
        lblTenKH.setPreferredSize(new Dimension(120, 20));
        panel_1.add(lblTenKH);
        
        txtTenKH = new JTextField();
        txtTenKH.setEditable(false);
        panel_1.add(txtTenKH);
        txtTenKH.setColumns(15);
        
        JPanel panel_1_1 = new JPanel();
        FlowLayout flowLayout_1 = (FlowLayout) panel_1_1.getLayout();
        flowLayout_1.setAlignment(FlowLayout.LEFT);
        panel_3.add(panel_1_1);
        
        JLabel lblSDT = new JLabel("Số điện thoại");
        lblSDT.setPreferredSize(new Dimension(120, 20));
        panel_1_1.add(lblSDT);
        
        txtSDT = new JTextField();
        txtSDT.setEditable(false);
        txtSDT.setColumns(15);
        panel_1_1.add(txtSDT);
        
        JPanel panel_1_2 = new JPanel();
        FlowLayout flowLayout_2 = (FlowLayout) panel_1_2.getLayout();
        flowLayout_2.setAlignment(FlowLayout.LEFT);
        panel_3.add(panel_1_2);
        
        JLabel lblCmnd = new JLabel("CMND");
        lblCmnd.setPreferredSize(new Dimension(120, 20));
        panel_1_2.add(lblCmnd);
        
        txtCMND = new JTextField();
        txtCMND.setEditable(false);
        txtCMND.setColumns(15);
        panel_1_2.add(txtCMND);
        
        JPanel panel_1_3 = new JPanel();
        FlowLayout flowLayout_3 = (FlowLayout) panel_1_3.getLayout();
        flowLayout_3.setAlignment(FlowLayout.LEFT);
        panel_3.add(panel_1_3);
        
        JLabel lblNgayDen = new JLabel("Ngày đến");
        lblNgayDen.setPreferredSize(new Dimension(120, 20));
        panel_1_3.add(lblNgayDen);
        
        txtNgayDen = new JTextField();
        txtNgayDen.setEditable(false);
        txtNgayDen.setColumns(15);
        panel_1_3.add(txtNgayDen);
        
        JPanel panel_1_3_1 = new JPanel();
        FlowLayout flowLayout_4 = (FlowLayout) panel_1_3_1.getLayout();
        flowLayout_4.setAlignment(FlowLayout.LEFT);
        panel_3.add(panel_1_3_1);
        
        JLabel lblNgayDi = new JLabel("Ngày đi");
        lblNgayDi.setPreferredSize(new Dimension(120, 20));
        panel_1_3_1.add(lblNgayDi);
        
        txtNgayDi = new JTextField();
        txtNgayDi.setEditable(false);
        txtNgayDi.setColumns(15);
        panel_1_3_1.add(txtNgayDi);

        JPanel p_sec_t = new JPanel();
        p_sec_total.add(p_sec_t);
        p_sec_t.setBorder(BorderFactory.createTitledBorder("Tổng tiền"));

        // JLabel lbThanhToan = new JLabel("Tong tien thanh toan:");
        // p_sec_t.add(lbThanhToan);

        GridLayout grid = new GridLayout(3, 2);
        grid.setHgap(30);
        grid.setHgap(20);
        JPanel pGeneral = new JPanel(grid);
        p_sec_t.add(pGeneral);
        pGeneral.add(new JLabel("Tiền phòng: "));
        lbTienPhong = new JLabel("100.000đ");
        lbTienPhong.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        pGeneral.add(lbTienPhong);
        

        pGeneral.add(new JLabel("Tiền dịch vụ: "));
        lbTienDV = new JLabel("230.000đ");
        lbTienDV.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        pGeneral.add(lbTienDV);

        pGeneral.add(new JLabel("Tổng tiền: "));
        lbTongTien = new JLabel("330.000đ");
        lbTongTien.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        pGeneral.add(lbTongTien);

        JPanel p_sec_action = new JPanel();
        p_sec_total.add(p_sec_action);
        p_sec_action.setBorder(BorderFactory.createTitledBorder("Hành động"));

        GridLayout grid2 = new GridLayout(1, 2);
        grid2.setHgap(30);
        grid2.setHgap(20);
        JPanel pBtn = new JPanel(grid2);
        p_sec_action.add(pBtn);
        btnThanhToan = new JButton("Thanh toán", icon_pay);
        btnThanhToan.setBackground(Color.WHITE);
        btnIn = new JButton("In hóa đơn", icon_in);
        btnIn.setBackground(Color.WHITE);

        pBtn.add(btnThanhToan);
        pBtn.add(btnIn);
        
        btnThanhToan.addActionListener((e) -> {
        	int choose = JOptionPane.showConfirmDialog(contentPane, "Chắc chắn muốn thanh toán ?");
        	if(choose != 0)
        		return;
        	
        	hdp.setTinhTrang(2);
        	hddv.setTinhTrang(1);
        	try {
				if(client.getHoaDonPhongDao().capNhatHoaDonPhong(hdp) == false) {
					JOptionPane.showMessageDialog(contentPane, "Có lỗi xảy ra");
					return;
				}
				
				if(client.getHoaDonDVDao().capNhatHoaDonDV(hddv) == false) {
					JOptionPane.showMessageDialog(contentPane, "Có lỗi xảy ra");
					return;
				}
				
				btnThanhToan.setEnabled(false);
				JOptionPane.showMessageDialog(contentPane, "Thanh toán thành công");
				return;
			} catch (RemoteException | MalformedURLException | NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
        	JOptionPane.showMessageDialog(contentPane, "Có lỗi xảy ra");
        });
        
        btnIn.addActionListener((e) -> {
        	JOptionPane.showMessageDialog(contentPane, "In thành công");
        });

    }

    public void renderData(){
    	
        int soNgay = (int) Ngay.tinhKhoangNgay(hdp.getNgayGioNhan(), hdp.getNgayGioTra());
        
        txtTenKH.setText(hdp.getKhachHang().getTenKH());
        txtSDT.setText(hdp.getKhachHang().getSoDienThoai());
        txtCMND.setText(hdp.getKhachHang().getCmnd());
        txtNgayDen.setText(hdp.getNgayGioNhan().toString());
        txtNgayDi.setText(hdp.getNgayGioTra().toString());
        
//        dsp
        tienPhong = 0;
        modelHD.getDataVector().removeAllElements();
//        Mã phòng", "Vị trí", "Số giường", "Số người", "Loại phòng", "Giá phòng
        dsCTHDP.forEach(cthdp -> {
        	modelHD.addRow(new Object[] {
        			cthdp.getPhong().getMaPhong(),
        			cthdp.getPhong().getViTri(),
        			cthdp.getPhong().getSoGiuong(),
        			cthdp.getPhong().getSucChua(),
        			cthdp.getPhong().getLoaiPhong().getTenLoaiPhong(),
        			Currency.format(cthdp.getPhong().getLoaiPhong().getDonGia()),
        			Currency.format(cthdp.getPhong().getLoaiPhong().getDonGia() * soNgay),
        	});
        	
        	tienPhong += cthdp.getPhong().getLoaiPhong().getDonGia() * soNgay;
        });
        tblDSP.revalidate();
        tblDSP.repaint();
        lbTienPhong.setText(Currency.format(tienPhong));
        
//        dsdv
        tienDV = 0;
        modelDV.getDataVector().removeAllElements();
        dsCTDV.forEach(ctdv -> {
        	modelDV.addRow(new Object[] {
        			ctdv.getDichVu().getTenDV(),
        			ctdv.getSoLuong(),
        			Currency.format(ctdv.getDichVu().getDonGia()),
        			Currency.format(ctdv.getDichVu().getDonGia() * ctdv.getSoLuong())
        	});
        	
        	tienDV += ctdv.getDichVu().getDonGia() * ctdv.getSoLuong();
        });
        
        tblDSDV.revalidate();
        tblDSDV.repaint();
        lbTienDV.setText(Currency.format(tienDV));
        lbTongTien.setText(Currency.format(tienPhong + tienDV));
    }   
    

    public JLabel space(int w, int h){
        JLabel space = new JLabel("");
        space.setBorder(BorderFactory.createEmptyBorder(h/2, w/2, h/2, w/2));
        return space;
    }

    
}

