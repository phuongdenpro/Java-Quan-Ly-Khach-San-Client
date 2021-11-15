package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import app.Client;
import dao.PhongDao;

import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.sql.*;


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
    private JTextField txtMaKH;
    private JTextField txtTenKH;
    private DefaultComboBoxModel<String> modelMaPhong;
    private JComboBox<String> cboMaPhong;
    private kDatePicker dpTuNgay, dpDenNgay;
    private JTextField txtGhiChu;
    private JButton btnDatPhong;
    private JButton btnSua;
    private JButton btnHuy;
    private JButton btnClear;
    private DefaultTableModel modelAvail;
    private JTable tblAvail;
    public DefaultTableModel modelDatPhong;
    public JTable tblDatPhong;
    private DefaultComboBoxModel modelMaKH;
    private JComboBox cboMaKH;
    private JCheckBox chkIsNotKH;
    private JCheckBox chkIsNhanPhong;
    private JTextField txtCMND;
    private JTextField txtNgayHetHan;
    private kDatePicker dpNgayHetHan;
    private DefaultComboBoxModel<String> modelLoaiKH;
    private JComboBox<String> cboLoaiKH;
    private JFrame popup = new JFrame();;
    private JButton btn_NhanPhong;
    private JButton btn_HuyPhong;
    public JButton btn_TraPhong = new JButton("Trả phòng");
    private JTextField txtTim;
    private JButton btnTim;
    private JComboBox cboTinhTrang;
    
    public static void main(String[] args) throws IOException, NotBoundException {
		DatPhong_UI datPhongUI = new DatPhong_UI();
		datPhongUI.start();
		datPhongUI.setVisible(true);
	}
    
    public DatPhong_UI() throws IOException, NotBoundException{
    	client = new Client();
    	
    }

    public void start() throws MalformedURLException, RemoteException, NotBoundException{

        renderGUI();
        renderHoaDon();
        renderKhachHang();
        renderDSPhong();
    }

    public void renderGUI() {
    	setSize(1350, 600);
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JPanel pTop = new JPanel();
        pTop.setPreferredSize(new Dimension(1000, 400));
        pTop.setLayout(new BoxLayout(pTop, BoxLayout.X_AXIS));
        contentPane.add(pTop);
        
        // Fields
        JPanel p_sec_Fields = new JPanel();
        p_sec_Fields.setLayout(new BoxLayout(p_sec_Fields, BoxLayout.Y_AXIS));
        pTop.add(p_sec_Fields);
        pTop.add(space(20, 20));

        JPanel p_sec_f_top = new JPanel();
        p_sec_f_top.setLayout(new BoxLayout(p_sec_f_top, BoxLayout.X_AXIS));
        p_sec_Fields.add(p_sec_f_top);
        

        Box p_l = Box.createVerticalBox();
        Box p_r = Box.createVerticalBox();
        p_l.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        // p_r.setLayout(new BoxLayout(p_r, BoxLayout.Y_AXIS));
        p_sec_f_top.add(p_l);
        p_sec_f_top.add(p_r);

        JLabel lbMaKH = new JLabel("Mã khách hàng");
        lbMaKH.setFont(fontSize(20));
        JLabel lbTenKH = new JLabel("Tên khách hàng");
        lbTenKH.setFont(fontSize(20));
        JLabel lbCMND = new JLabel("Cmnd");
        lbCMND.setFont(fontSize(20));
        JLabel lbNgayHetHan = new JLabel("Ngày hết hạn");
        lbNgayHetHan.setFont(fontSize(20));
        JLabel lbLoaiKH = new JLabel("Loại khách hàng");
        lbLoaiKH.setFont(fontSize(20));
        JLabel lbMaPhong = new JLabel("Mã phòng");
        lbMaPhong.setFont(fontSize(20));
        JLabel lbNgayDen = new JLabel("Ngày đến");
        lbNgayDen.setFont(fontSize(20));
        JLabel lbNgayDi = new JLabel("Ngày đi");
        lbNgayDi.setFont(fontSize(20));
        
        p_l.add(lbMaKH);
        p_l.add(Box.createVerticalStrut(7));
        p_l.add(lbTenKH);
        p_l.add(Box.createVerticalStrut(7));
        p_l.add(lbCMND);
        p_l.add(Box.createVerticalStrut(7));
        p_l.add(lbNgayHetHan);
        p_l.add(Box.createVerticalStrut(7));
        p_l.add(lbLoaiKH);
        p_l.add(Box.createVerticalStrut(7));
        p_l.add(lbMaPhong);
        p_l.add(Box.createVerticalStrut(7));
        p_l.add(lbNgayDen);
        p_l.add(Box.createVerticalStrut(7));
        p_l.add(lbNgayDi);
        p_l.add(Box.createVerticalStrut(10));

        // txtMaKH = new JTextField(10);
        modelMaKH = new DefaultComboBoxModel();
        cboMaKH = new JComboBox(modelMaKH);
        

        txtTenKH = new JTextField(10);
        txtTenKH.setEnabled(false);
        txtCMND = new JTextField(10);
        txtCMND.setEnabled(false);

        dpNgayHetHan = new kDatePicker(200);
        dpNgayHetHan.setPreferredSize(new Dimension(200, 40));
        dpNgayHetHan.setEnabled(false);
        
       
        modelLoaiKH = new DefaultComboBoxModel<String>();
        modelLoaiKH.addElement("Việt nam");
        modelLoaiKH.addElement("Nước ngoài");
        cboLoaiKH = new JComboBox<String>(modelLoaiKH);
        cboLoaiKH.setEnabled(false);

        modelMaPhong = new DefaultComboBoxModel<String>();
        cboMaPhong = new JComboBox<String>(modelMaPhong);

        dpTuNgay = new kDatePicker(200);
        dpTuNgay.setPreferredSize(new Dimension(200, 40));
        dpDenNgay = new kDatePicker(200);
        dpDenNgay.setPreferredSize(new Dimension(200, 40));

        txtGhiChu = new JTextField(10);
        p_r.add(Box.createVerticalStrut(15));
        p_r.add(cboMaKH);
        p_r.add(Box.createVerticalStrut(10));
        p_r.add(txtTenKH);
        p_r.add(Box.createVerticalStrut(10));
        p_r.add(txtCMND);
        p_r.add(Box.createVerticalStrut(10));
        p_r.add(dpNgayHetHan);
        p_r.add(Box.createVerticalStrut(0));
        p_r.add(cboLoaiKH);
        p_r.add(Box.createVerticalStrut(10));
        p_r.add(cboMaPhong);
        p_r.add(Box.createVerticalStrut(10));
        p_r.add(dpTuNgay);
        p_r.add(Box.createVerticalStrut(5));
        p_r.add(dpDenNgay);

        // check box
        JPanel p_sec_f_center = new JPanel();
        p_sec_Fields.add(p_sec_f_center);
        chkIsNotKH = new JCheckBox("Khách hàng mới");
        p_sec_f_center.add(chkIsNotKH);
        // action
        JPanel p_sec_f_bottom = new JPanel();
        GridLayout grid = new GridLayout(0, 2);
        grid.setHgap(10);
        grid.setVgap(10);
        p_sec_f_bottom.setLayout(grid);
        p_sec_Fields.add(p_sec_f_bottom);

        btnDatPhong = new JButton("Đặt phòng", icon_add);
        btnSua = new JButton("Sửa", icon_edit);
        btnHuy = new JButton("Hủy", icon_trash);
        btnClear = new JButton("Làm lại", icon_refresh);
        p_sec_f_bottom.add(btnDatPhong);
        p_sec_f_bottom.add(btnClear);

        // Danh sách phòng trống
        JPanel p_sec_DS = new JPanel();
        p_sec_DS.setLayout(new BoxLayout(p_sec_DS, BoxLayout.Y_AXIS));
        
        pTop.add(p_sec_DS);
        JLabel lbAvail = new JLabel("Danh sách phòng");
        lbAvail.setAlignmentX(Component.CENTER_ALIGNMENT);
        p_sec_DS.add(lbAvail);
        String[] cols_avail = {"Mã phòng", "Loại phòng", "Sức chứa", "Số giường", "Vị trí", "Giá phòng"};
        modelAvail = new DefaultTableModel(cols_avail, 0);
        tblAvail = new JTable(modelAvail);


        // tblAvail.setLayout(new FlowLayout());
        // tblAvail.setPreferredSize(new Dimension(2000, 150));
        p_sec_DS.add(new JScrollPane(tblAvail));

        // modelAvail.addRow(new Object[]{"1", "vip", "2", "1", "Lau 1", "120.000"});
        // modelAvail.addRow(new Object[]{"2", "vip", "2", "1", "Lau 2", "120.000"});
        // modelAvail.addRow(new Object[]{"3", "vip", "2", "1", "Lau 3", "120.000"});


        // danh sách đặt phòng
        JPanel p_sec_table = new JPanel();
        contentPane.add(p_sec_table);

        JPanel pTimKiem = new JPanel();
        p_sec_table.add(pTimKiem);
        pTimKiem.add(new JLabel("Mã hóa đơn: "));
        txtTim = new JTextField(20);
        pTimKiem.add(txtTim);
        btnTim = new JButton("Tim kiếm", icon_search);
        pTimKiem.add(btnTim);

        pTimKiem.add(new JLabel("Lọc: "));
        DefaultComboBoxModel<String> modelTinhTrang = new DefaultComboBoxModel<String>();
        cboTinhTrang = new JComboBox(modelTinhTrang);
        modelTinhTrang.addElement("Tất cả");
        modelTinhTrang.addElement("Đã đặt phòng");
        modelTinhTrang.addElement("Đã nhận phòng");
        modelTinhTrang.addElement("Đã trả phòng");
        pTimKiem.add(cboTinhTrang);


        JPanel pTable = new JPanel();
        pTable.setLayout(new BoxLayout(pTable, BoxLayout.X_AXIS));
        contentPane.add(pTable);

        String[] cols_datphong = {"Mã hóa đơn", "Mã khách hàng", "Tên khách hàng", "Mã phòng", "Loại phòng", "Ngày đến", "Ngày đi", "Tình trạng"};
        modelDatPhong = new DefaultTableModel(cols_datphong, 0);
        tblDatPhong = new JTable(modelDatPhong);
        pTable.add(new JScrollPane(tblDatPhong));
        // modelDatPhong.addRow(new Object[]{"1", "1", "Tran Van Nhan", "1", "01-01-2001", "01-01-2001", ""});
        // modelDatPhong.addRow(new Object[]{"2", "1", "Tran Van Nhan", "1", "01-01-2001", "01-01-2001", ""});
        // modelDatPhong.addRow(new Object[]{"3", "1", "Tran Van Nhan", "1", "01-01-2001", "01-01-2001", ""});
        // modelDatPhong.addRow(new Object[]{"4", "1", "Tran Van Nhan", "1", "01-01-2001", "01-01-2001", ""});
        

    }

    public void renderDSPhongAvail(){
    	
    }

    public void renderDSPhong() throws MalformedURLException, RemoteException, NotBoundException{
        PhongDao phongDao = client.getPhongDao();
        
    }

    public void renderHoaDon(){
        
    }

    public void renderKhachHang(){
        
    }

    public JLabel space(int w, int h){
        JLabel space = new JLabel("");
        space.setBorder(BorderFactory.createEmptyBorder(h/2, w/2, h/2, w/2));
        return space;
    }

    public Font fontSize(int size){
        return new Font(Font.DIALOG, Font.PLAIN, size);
    }


    public void renderError(JTextField a, String message){
        a.requestFocus();
        a.selectAll();
        JOptionPane.showMessageDialog(pnMain, message);
    }

}
