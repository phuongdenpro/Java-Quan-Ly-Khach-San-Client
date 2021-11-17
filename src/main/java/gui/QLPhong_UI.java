package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.*;

import app.Client;
import dao.PhongDao;
import model.LoaiPhong;
import model.Phong;
import utils.Currency;

import javax.swing.border.*;


public class QLPhong_UI extends JFrame{
    public JPanel contentPane;
    private DefaultTableModel modelTableLP, modelTableP;
    private SpinnerNumberModel modelSpinSC, modelSpinSG;
    private final int SUCCESS = 1, ERROR = 0;
    ImageIcon blueAddIcon = new ImageIcon("data/images/blueAdd_16.png");
    ImageIcon editIcon = new ImageIcon("data/images/edit2_16.png");
    ImageIcon deleteIcon = new ImageIcon("data/images/trash2_16.png");
    ImageIcon refreshIcon = new ImageIcon("data/images/refresh_16.png");
    ImageIcon searchIcon = new ImageIcon("data/images/search_16.png");
    ImageIcon calendarIcon = new ImageIcon("data/images/calender_16.png");
    ImageIcon checkIcon = new ImageIcon("data/images/check2_16.png");
    ImageIcon errorIcon = new ImageIcon("data/images/cancel_16.png");
    private JPanel panel;
    private JPanel panel_1;
    private JPanel panel_2;
    private JPanel pnBL;
    private JPanel pnBR;
    private JPanel panel_3;
    private JLabel lbMaPhong;
    private JTextField txtMaPhong;
    private JPanel panel_4;
    private JLabel lbSucChua;
    private JPanel panel_5;
    private JLabel lbSoGiuong;
    private JPanel panel_6;
    private JLabel lbTinhTrang;
    private JPanel panel_7;
    private JLabel lbViTri;
    private JTextField txtViTri;
    private JPanel panel_9;
    private JButton btnSuaP;
    private JButton btnLamMoi;
    private JPanel panel_10;
    private JLabel lbLoaiPhong;
    private JComboBox<String> cboLoaiPhong;
    private JPanel panel_11;
    private JLabel lblnGi;
    private JTextField txtDonGia;
    private JTextField txtTinhTrang;
    private JPanel panel_8;
    private JLabel lbTimPhong;
    private JTextField txtTimKiem;
    private JButton btnTimKiem;
    private JButton btnXemLichDat;
    private JButton btnThemP;
    private JScrollPane scrollPane;
    private JTextField txtSucChua;
    private JTextField txtSoGiuong;
	private Client client;
	private List<Phong> dsp = new ArrayList<Phong>();
	private JLabel lblLoc;
	private JComboBox cboLoc;
	private DefaultTableModel model;
	private JTable table;
	private DefaultComboBoxModel<String> modelLoc;
	private DefaultComboBoxModel<String> modelLoaiPhong;
	private List<LoaiPhong> dslp;
	private JButton btnXoa;
    

    public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
        new QLPhong_UI().setVisible(true);
    }

    
    public QLPhong_UI() throws RemoteException, MalformedURLException, NotBoundException {
    	try {
			client = new Client();
		} catch (IOException | NotBoundException e) {
			e.printStackTrace();
		}
    	
        setSize(1000, 670);
        setTitle("Quản Lý Phòng");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JLabel lbTitle = new JLabel("Quản Lý Phòng");
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setFont(new Font("Dialog", Font.BOLD, 20));
        contentPane.add(lbTitle, BorderLayout.NORTH);
        
        panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(0, 0));
        
        panel_2 = new JPanel();
        panel.add(panel_2, BorderLayout.WEST);
        
        pnBL = new JPanel();
        pnBL.setBorder(new TitledBorder(null, "Thông tin phòng ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_2.add(pnBL);
        pnBL.setLayout(new BoxLayout(pnBL, BoxLayout.Y_AXIS));
        
        panel_3 = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        pnBL.add(panel_3);
        
        lbMaPhong = new JLabel("Mã phòng: ");
        lbMaPhong.setPreferredSize(new Dimension(100, 30));
        panel_3.add(lbMaPhong);
        
        txtMaPhong = new JTextField();
        txtMaPhong.setColumns(20);
        panel_3.add(txtMaPhong);
        
        panel_4 = new JPanel();
        FlowLayout flowLayout_1 = (FlowLayout) panel_4.getLayout();
        flowLayout_1.setAlignment(FlowLayout.LEFT);
        pnBL.add(panel_4);
        
        lbSucChua = new JLabel("Sức chứa");
        lbSucChua.setPreferredSize(new Dimension(100, 30));
        panel_4.add(lbSucChua);
        
        txtSucChua = new JTextField();
        panel_4.add(txtSucChua);
        txtSucChua.setColumns(20);
        
        panel_5 = new JPanel();
        FlowLayout flowLayout_2 = (FlowLayout) panel_5.getLayout();
        flowLayout_2.setAlignment(FlowLayout.LEFT);
        pnBL.add(panel_5);
        
        lbSoGiuong = new JLabel("Số giường: ");
        lbSoGiuong.setPreferredSize(new Dimension(100, 30));
        panel_5.add(lbSoGiuong);
        
        txtSoGiuong = new JTextField();
        panel_5.add(txtSoGiuong);
        txtSoGiuong.setColumns(20);
        
        panel_6 = new JPanel();
        FlowLayout flowLayout_3 = (FlowLayout) panel_6.getLayout();
        flowLayout_3.setAlignment(FlowLayout.LEFT);
        pnBL.add(panel_6);
        
        lbTinhTrang = new JLabel("Tình trạng: ");
        lbTinhTrang.setPreferredSize(new Dimension(100, 30));
        panel_6.add(lbTinhTrang);
        
        txtTinhTrang = new JTextField();
        txtTinhTrang.setEditable(false);
        panel_6.add(txtTinhTrang);
        txtTinhTrang.setColumns(20);
        
        panel_7 = new JPanel();
        FlowLayout flowLayout_4 = (FlowLayout) panel_7.getLayout();
        flowLayout_4.setAlignment(FlowLayout.LEFT);
        pnBL.add(panel_7);
        
        lbViTri = new JLabel("Vị trí: ");
        lbViTri.setPreferredSize(new Dimension(100, 30));
        panel_7.add(lbViTri);
        
        txtViTri = new JTextField();
        txtViTri.setColumns(20);
        panel_7.add(txtViTri);
        
        panel_10 = new JPanel();
        FlowLayout flowLayout_6 = (FlowLayout) panel_10.getLayout();
        flowLayout_6.setAlignment(FlowLayout.LEFT);
        pnBL.add(panel_10);
        
       
        lbLoaiPhong = new JLabel("Loại phòng: ");
        lbLoaiPhong.setPreferredSize(new Dimension(100, 30));
        panel_10.add(lbLoaiPhong);
        
        modelLoaiPhong = new DefaultComboBoxModel<String>();
        cboLoaiPhong = new JComboBox<String>(modelLoaiPhong);
        cboLoaiPhong.setPreferredSize(new Dimension(222, 20));
        panel_10.add(cboLoaiPhong);
        
        panel_11 = new JPanel();
        FlowLayout flowLayout_7 = (FlowLayout) panel_11.getLayout();
        flowLayout_7.setAlignment(FlowLayout.LEFT);
        pnBL.add(panel_11);
        
        lblnGi = new JLabel("Đơn giá: ");
        lblnGi.setPreferredSize(new Dimension(100, 30));
        panel_11.add(lblnGi);
        
        txtDonGia = new JTextField();
        txtDonGia.setEditable(false);
        panel_11.add(txtDonGia);
        txtDonGia.setColumns(20);
        
        panel_9 = new JPanel();
        pnBL.add(panel_9);
        panel_9.setLayout(new GridLayout(0, 2, 10, 5));
        
        btnThemP = new JButton("Thêm", null);
        btnThemP.setBackground(Color.WHITE);
        panel_9.add(btnThemP);
        
        btnSuaP = new JButton("Sửa", null);
        btnSuaP.setBackground(Color.WHITE);
        panel_9.add(btnSuaP);
        
        btnXoa = new JButton("Xóa", null);
        btnXoa.setBackground(Color.WHITE);
        panel_9.add(btnXoa);
        
        btnLamMoi = new JButton("Làm mới", null);
        btnLamMoi.setBackground(Color.WHITE);
        panel_9.add(btnLamMoi);
        
        panel_1 = new JPanel();
        panel.add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(new BorderLayout(0, 0));
        
        panel_8 = new JPanel();
        panel_1.add(panel_8, BorderLayout.NORTH);
        
        lbTimPhong = new JLabel("Mã phòng: ");
        panel_8.add(lbTimPhong);
        
        txtTimKiem = new JTextField();
        txtTimKiem.setPreferredSize(new Dimension(150, 26));
        txtTimKiem.setColumns(10);
        panel_8.add(txtTimKiem);
        
        btnTimKiem = new JButton("Tìm", null);
        btnTimKiem.setBackground(Color.WHITE);
        panel_8.add(btnTimKiem);
        
        lblLoc = new JLabel("Lọc theo: ");
        panel_8.add(lblLoc);
        
        modelLoc = new DefaultComboBoxModel<String>();
        cboLoc = new JComboBox(modelLoc);
        panel_8.add(cboLoc);
        
        btnXemLichDat = new JButton("Xem lịch đặt");
        btnXemLichDat.setBackground(Color.WHITE);
        panel_8.add(btnXemLichDat);
        
        pnBR = new JPanel();
        panel_1.add(pnBR);
        pnBR.setLayout(new BorderLayout(0, 0));
        
        String[] colsP = {"Mã phòng", "Vị trí", "Số giường", "Số người", "Loại phòng", "Giá phòng"};
        model = new DefaultTableModel(colsP, 0);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        pnBR.add(scrollPane, BorderLayout.CENTER);

        table.getSelectionModel().addListSelectionListener((e) -> {
        	int idx = table.getSelectedRow();
        	if(idx != -1) {
        		Phong phong = dsp.get(idx);
        		txtMaPhong.setText(phong.getMaPhong());
        		txtSucChua.setText(String.valueOf(phong.getSucChua()));
        		txtSoGiuong.setText(String.valueOf(phong.getSoGiuong()));
        		txtTinhTrang.setText(this.convertTinhTrang(phong.getTinhTrang()));
        		txtViTri.setText(phong.getViTri());
        		
        		for(int i=0; i<dslp.size(); i++) {
        			if(dslp.get(i).getMaLoaiPhong() == phong.getLoaiPhong().getMaLoaiPhong()) {
        				cboLoaiPhong.setSelectedIndex(i);
        				break;
        			}
        		}
        		
        		
        		
        	}
        });
        
        cboLoaiPhong.addActionListener((e) -> {
        	int idx = cboLoaiPhong.getSelectedIndex();
        	if(idx != -1) {
        		txtDonGia.setText(Currency.format(dslp.get(idx).getDonGia()));
        	}
        });
        
        cboLoc.addActionListener((e) -> {
        	try {
				renderDSPhong();
			} catch (MalformedURLException | RemoteException | NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
        
        btnTimKiem.addActionListener((e) -> {
        	String maPhong = txtTimKiem.getText();
        	for(int i=0; i<dsp.size(); i++) {
        		if(dsp.get(i).getMaPhong().equals(maPhong)) {
        			System.out.println(i);
        			table.setRowSelectionInterval(i, i);
        			return;
        		}
        	}
        	JOptionPane.showMessageDialog(contentPane, "Không tìm thấy");
        });
        
        btnLamMoi.addActionListener((e) -> {
        	clear();
        });
        
        btnThemP.addActionListener((e) -> {
        	
        	if(checkData() == false)
        		return;
        	
            Phong phong = new Phong(
            		txtMaPhong.getText(),
            		Integer.parseInt(txtSucChua.getText()),
            		Integer.parseInt(txtSoGiuong.getText()),
            		txtViTri.getText(),
            		0,
            		dslp.get(cboLoaiPhong.getSelectedIndex()));
            
            try {
				if(client.getPhongDao().themPhong(phong)) {
					JOptionPane.showMessageDialog(contentPane, "Thêm thành công");
					renderData();
					return;
				}
			} catch (HeadlessException | RemoteException | MalformedURLException | NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            
            JOptionPane.showMessageDialog(contentPane, "Thêm thất bại");
        });
        
        btnSuaP.addActionListener((e) -> {
        	
        	if(checkData() == false)
        		return;
        	
            Phong phong = new Phong(
            		txtMaPhong.getText(),
            		Integer.parseInt(txtSucChua.getText()),
            		Integer.parseInt(txtSoGiuong.getText()),
            		txtViTri.getText(),
            		0,
            		dslp.get(cboLoaiPhong.getSelectedIndex()));
            
            try {
				if(client.getPhongDao().suaPhong(phong)) {
					JOptionPane.showMessageDialog(contentPane, "Sửa thành công");
					renderData();
					return;
				}
			} catch (HeadlessException | RemoteException | MalformedURLException | NotBoundException e1) {
				e1.printStackTrace();
			}
            
            JOptionPane.showMessageDialog(contentPane, "Sửa thất bại");
        });
        
        btnXoa.addActionListener((e) -> {
        	int idx = table.getSelectedRow();
        	if(idx != -1) {
        		String maPhong = dsp.get(idx).getMaPhong();
        		
        		try {
					if(client.getPhongDao().xoaPhong(maPhong)) {
						JOptionPane.showMessageDialog(contentPane, "Xóa thành công");
						renderData();
						return;
					}
				} catch (RemoteException | MalformedURLException | NotBoundException e1) {
					e1.printStackTrace();
				}
        		
        		JOptionPane.showMessageDialog(contentPane, "Xóa thất bại");
        	}
        });
        
        btnXemLichDat.addActionListener((e) -> {
        	int idx = table.getSelectedRow();
        	if(idx == -1) {
        		JOptionPane.showMessageDialog(contentPane, "Vui lòng chọn phòng để xem lịch đặt");
        		return ;
        	}
        	String maPhong = dsp.get(idx).getMaPhong();
        	
        	DialogLichDatPhong dialog = new DialogLichDatPhong();
        	dialog.setMaPhong(maPhong);
        	dialog.renderData();
        	dialog.setVisible(true);
        });
        
        renderData();
    }
    
    public void renderData() throws RemoteException, MalformedURLException, NotBoundException {
    	
    	renderLoaiPhong();
    	renderDSPhong();
    	
    }
    
    public void renderDSPhong() throws MalformedURLException, RemoteException, NotBoundException {
		PhongDao phongDao = client.getPhongDao();
    	
    	int idx = cboLoc.getSelectedIndex();
        if(idx <= 0)
        	dsp = phongDao.getListPhong();
        else {
        	dsp = phongDao.getPhongByMaLoaiPhong(dslp.get(idx-1).getMaLoaiPhong());
        }
        
        table.clearSelection();
        model.getDataVector().removeAllElements();
        for(int j=0; j<dsp.size(); j++) {
        	Phong phong = dsp.get(j);
        	model.addRow(new Object[] {
        		phong.getMaPhong(),
        		phong.getViTri(),
        		phong.getSoGiuong(),
        		phong.getSucChua(),
        		phong.getLoaiPhong().getTenLoaiPhong(),
        		phong.getLoaiPhong().getDonGia()
        	});
        };
        table.revalidate();
        table.repaint();
    }
    
    public void renderLoaiPhong() throws RemoteException, MalformedURLException, NotBoundException{
        modelLoaiPhong.removeAllElements();
        modelLoc.removeAllElements();
        modelLoc.addElement("Tất cả");
        dslp = client.getLoaiPhongDao().getDSLoaiPhong();
        dslp.forEach(lp -> {
        	modelLoaiPhong.addElement(lp.getTenLoaiPhong());
        	modelLoc.addElement(lp.getTenLoaiPhong());
        });
    }


    private void showMessage(String message, JTextField txt, JLabel lbl) {
        lbl.setForeground(Color.RED);
        txt.requestFocus();
        txt.selectAll();
        lbl.setText(message);
        lbl.setIcon(errorIcon);
    }
    
    public void clear() {
    	txtMaPhong.setText("");
    	txtSucChua.setText("");
    	txtSoGiuong.setText("");
    	txtTinhTrang.setText("");
    	txtViTri.setText("");
    	cboLoaiPhong.setSelectedIndex(0);
    }
    
    public void renderError(JTextField a, String message){
        a.requestFocus();
        a.selectAll();
        JOptionPane.showMessageDialog(contentPane, message);
    }


    public boolean checkData() {
    	if(txtMaPhong.getText().trim().equals("")){
            renderError(txtMaPhong, "Mã phòng không được để trống");
            return false;
        }

        if(!txtSucChua.getText().matches("^\\d+$")){
            renderError(txtSucChua, "Sức chứa phải là số");
            return false;
        }
        
        if(!txtSoGiuong.getText().matches("^\\d+$")){
            renderError(txtSoGiuong, "Số giường phải là số");
            return false;
        }

        if(txtViTri.getText().trim().equals("")){
            renderError(txtViTri, "Vị trí không được để trống");
            return false;
        }
        
        return true;
    }

    private String convertTinhTrang(int tinhTrang) {
        String result = "";
        if (tinhTrang == 0)
            result = "Trống";
        else if (tinhTrang == 1)
            result = "Đã được đặt";
        else if (tinhTrang == 2)
            result = "Đã cho thuê";
        return result;
    }
    
    public JPanel getContentPane() {
		return contentPane;
    }
}
