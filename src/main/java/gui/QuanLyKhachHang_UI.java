package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.sql.*;
import java.text.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.util.List;

import app.Client;
import utils.Currency;
import utils.Ngay;
import model.DichVu;
import model.KhachHang;
import model.LoaiPhong;
import dao.KhachHangDao;
import dao.impl.KhachHangImpl;


public class QuanLyKhachHang_UI extends JFrame{

    public JPanel pnMain;
    private DefaultTableModel modelTable;
    private JTable tableShowInfo;
    private kDatePicker dpNgayHetHan;
    private JTextField txtMaKH, txtTenKH, txtCMND, txtSoLanDat, txtTim,txtSDT;
    private JButton btnTim, btnThem, btnSua, btnXoa, btnLamLai, btnXemTatCa;
    private JComboBox<String> cboLoaiKhach;
    private JLabel lbShowMessages;
    private List<KhachHang> dskh;
    private Client client;
    private final int SUCCESS = 1, ERROR = 0, ADD = 1, UPDATE = 2;
    ImageIcon blueAddIcon = new ImageIcon("data/images/blueAdd_16.png");
    ImageIcon editIcon = new ImageIcon("data/images/edit2_16.png");
    ImageIcon deleteIcon = new ImageIcon("data/images/trash2_16.png");
    ImageIcon refreshIcon = new ImageIcon("data/images/refresh_16.png");
    ImageIcon searchIcon = new ImageIcon("data/images/search_16.png");
    ImageIcon checkIcon = new ImageIcon("data/images/check2_16.png");
    ImageIcon errorIcon = new ImageIcon("data/images/cancel_16.png");

    public QuanLyKhachHang_UI() throws MalformedURLException, RemoteException, NotBoundException {
  		try {
  			client = new Client();
  		} catch (IOException e2) {
  			// TODO Auto-generated catch block
  			e2.printStackTrace();
  		}
    	setSize(1000, 670);
        setTitle("Quản Lý Khách Hàng");
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pnMain = new JPanel();
        pnMain.setLayout(null);
        pnMain.setBounds(0, 0, 1000, 670);

        JLabel lbTitle = new JLabel("Quản Lý Khách Hàng");
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setFont(new Font("Dialog", Font.BOLD, 20));
        lbTitle.setBounds(0, 0, 984, 30);
        pnMain.add(lbTitle);

        getContentPane().add(pnMain);

        JPanel pnThongTinKH = new JPanel();
        pnThongTinKH.setBorder(
                new TitledBorder(null, "Thông tin khách hàng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnThongTinKH.setBounds(0, 41, 359, 588);
        pnMain.add(pnThongTinKH);
        pnThongTinKH.setLayout(null);

        JLabel lbMaKH = new JLabel("Mã khách hàng:");
        lbMaKH.setBounds(10, 21, 100, 14);
        pnThongTinKH.add(lbMaKH);

        txtMaKH = new JTextField();
        txtMaKH.setEditable(false);
        txtMaKH.setBounds(145, 18, 205, 20);
        pnThongTinKH.add(txtMaKH);
        txtMaKH.setColumns(10);

        JLabel lbTenKH = new JLabel("Tên khách hàng:");
        lbTenKH.setBounds(10, 46, 100, 14);
        pnThongTinKH.add(lbTenKH);

        txtTenKH = new JTextField();
        txtTenKH.setBounds(145, 43, 205, 20);
        pnThongTinKH.add(txtTenKH);
        txtTenKH.setColumns(10);

        JLabel lbLoaiKhach = new JLabel("Quốc tịch:");
        lbLoaiKhach.setBounds(10, 124, 105, 14);
        pnThongTinKH.add(lbLoaiKhach);

        cboLoaiKhach = new JComboBox<String>();
        cboLoaiKhach.setBounds(145, 121, 205, 20);
        cboLoaiKhach.addItem("Việt Nam");
        cboLoaiKhach.addItem("Nước ngoài");
        pnThongTinKH.add(cboLoaiKhach);

        JLabel lbCMND = new JLabel("CMND/CCCD/Hộ chiếu:");
        lbCMND.setBounds(10, 71, 136, 14);
        pnThongTinKH.add(lbCMND);

        JLabel lbNgayHetHan = new JLabel("Ngày hết hạn hộ chiếu:");
        lbNgayHetHan.setBounds(10, 95, 136, 16);
        pnThongTinKH.add(lbNgayHetHan);

        txtCMND = new JTextField();
        txtCMND.setBounds(145, 68, 205, 20);
        pnThongTinKH.add(txtCMND);
        txtCMND.setColumns(10);

        dpNgayHetHan = new kDatePicker(205);
        dpNgayHetHan.setBounds(145, 93, 205, 20);
        pnThongTinKH.add(dpNgayHetHan);
 
        JLabel lbSDT = new JLabel("Số Điện Thoại:");
        lbSDT.setBounds(10, 150, 112, 16);
        pnThongTinKH.add(lbSDT);
        
        txtSDT = new JTextField();
        txtSDT.setText("");
        txtSDT.setBounds(145, 148, 205, 20);
        pnThongTinKH.add(txtSDT);
        txtSDT.setColumns(10);

        btnThem = new JButton("Thêm", blueAddIcon);
        btnThem.setBounds(10, 207, 98, 26);
        pnThongTinKH.add(btnThem);

        btnSua = new JButton("Sửa", editIcon);
        btnSua.setBounds(125, 207, 98, 26);
        pnThongTinKH.add(btnSua);

        btnXoa = new JButton("Xóa", deleteIcon);
        btnXoa.setBounds(235, 207, 98, 26);
        pnThongTinKH.add(btnXoa);

        btnLamLai = new JButton("Làm lại", refreshIcon);
        btnLamLai.setBounds(125, 245, 98, 26);
        pnThongTinKH.add(btnLamLai);

        lbShowMessages = new JLabel("");
        lbShowMessages.setBounds(10, 179, 340, 16);
        pnThongTinKH.add(lbShowMessages);

        JPanel pbTableKH = new JPanel();
        pbTableKH.setBorder(
                new TitledBorder(null, "Danh sách khách hàng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pbTableKH.setBounds(360, 41, 624, 588);
        pnMain.add(pbTableKH);
        pbTableKH.setLayout(null);

        JLabel lbTimTenKH = new JLabel("Tên khách hàng:");
        lbTimTenKH.setBounds(12, 23, 106, 16);
        pbTableKH.add(lbTimTenKH);

        txtTim = new JTextField();
        txtTim.setBounds(118, 21, 200, 20);
        pbTableKH.add(txtTim);
        txtTim.setColumns(10);

        btnTim = new JButton("Tìm", searchIcon);
        btnTim.setBounds(323, 18, 80, 26);
        pbTableKH.add(btnTim);

        JPanel pnShowTableKH = new JPanel();
        pnShowTableKH.setBounds(12, 49, 600, 527);
        pbTableKH.add(pnShowTableKH);
        pnShowTableKH.setLayout(new BorderLayout(0, 0));

        String[] cols = { "Mã KH", "Tên KH", "CMND","SĐT", "Ngày hết hạn", "Quốc Tịch" };
        modelTable = new DefaultTableModel(cols, 0) {
            // khóa sửa dữ liệu trực tiếp trên table
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };
        tableShowInfo = new JTable(modelTable);
        JScrollPane scpShowTableKH = new JScrollPane(tableShowInfo, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pnShowTableKH.add(scpShowTableKH, BorderLayout.CENTER);

        //btnXemTatCa = new JButton("Xem tất cả");
        //btnXemTatCa.setBounds(411, 18, 121, 26);
        //pbTableKH.add(btnXemTatCa);
        
        reSizeColumnTable();
        
       renderData();
       tableShowInfo.getSelectionModel().addListSelectionListener((e) -> {
        	int idx = tableShowInfo.getSelectedRow();
        	if(idx != -1) {
        		KhachHang kh = dskh.get(idx);
        		showKhachHang(kh);
        		
        		
        	}
        });
        btnThem.addActionListener((e) -> {
        	
     //   
        	
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
               
               if(!txtSDT.getText().matches("^0[0-9]{9}$")){
   	            renderError(txtSDT, "Số điện thoại gồm 10 số và phải bắt đầu từ số 0");
   	            return;
               }
        	String ten = txtTenKH.getText();
        	String cmnd = txtCMND.getText();
        	String sdt = txtSDT.getText();
        	
			Date date = Ngay.homNay();
			try {
				date = dpNgayHetHan.getFullDate();
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
        	

        	KhachHang kh = new KhachHang(ten, cmnd, sdt, date,(String)cboLoaiKhach.getSelectedItem());
            try {
    				if(client.getKhachHangDao().themKhachHang(kh)) {
    					JOptionPane.showMessageDialog(pnMain, "Thêm thành công");
    					
    					renderData();
    					
    					return;
    				}
    			} catch (HeadlessException | RemoteException | MalformedURLException | NotBoundException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}
                
                JOptionPane.showMessageDialog(pnMain, "Thêm thất bại");
        	
        });
        
        btnSua.addActionListener((e) -> {
        	
            //   
               	
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
                      
                      if(!txtSDT.getText().matches("^0[0-9]{9}$")){
          	            renderError(txtSDT, "Số điện thoại gồm 10 số và phải bắt đầu từ số 0");
          	            return;
                      }
               	String ten = txtTenKH.getText();
               	String cmnd = txtCMND.getText();
               	String sdt = txtSDT.getText();
               //	int makh = Integer.parseInt(s)
       			Date date = Ngay.homNay();
       			try {
       				date = dpNgayHetHan.getFullDate();
       			} catch (ParseException e2) {
       				// TODO Auto-generated catch block
       				e2.printStackTrace();
       			}
       			
               	
       			
               	KhachHang a = new KhachHang(,ten, cmnd, sdt, date,(String)cboLoaiKhach.getSelectedItem());
                   try {
           				if(client.getKhachHangDao().capNhatKhachHang(a)) {
           					JOptionPane.showMessageDialog(pnMain, "Sửa thành công");
           					
           					renderData();
           					
           					return;
           				}
           			} catch (HeadlessException | RemoteException | MalformedURLException | NotBoundException e1) {
           				// TODO Auto-generated catch block
           				e1.printStackTrace();
           			}
                       
                       JOptionPane.showMessageDialog(pnMain, "Sửa thất bại");
               	
               });
        
 
        btnTim.addActionListener((e) -> {
        	String tenKH = txtTim.getText();
        	for(int i=0; i<dskh.size(); i++) {
        		if(dskh.get(i).getTenKH().equals(tenKH)) {
        			System.out.println(i);
        			tableShowInfo.setRowSelectionInterval(i, i);
        			return;
        		}
        	}
        	JOptionPane.showMessageDialog(pnMain, "Không tìm thấy");
        });
        btnLamLai.addActionListener((e) -> {
        	clear();
        });
        btnXoa.addActionListener((e) -> {
          	int idx = tableShowInfo.getSelectedRow();
          	if(idx != -1) {
          		int makh = dskh.get(idx).getMaKH();
          		try {
    					if(client.getKhachHangDao().xoaKhachHang(makh)) {
    						JOptionPane.showMessageDialog(pnMain, "Xóa thành công");
    						renderData();
    						return;
    					}
    				} catch (RemoteException | MalformedURLException | NotBoundException e1) {
    					e1.printStackTrace();
    				}
          		
          		JOptionPane.showMessageDialog(pnMain, "Xóa thất bại");
          	}
          });
          
    }
    
    
    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        new QuanLyKhachHang_UI().setVisible(true);
    }

    private void showMessage(String message, JTextField txt) {
        lbShowMessages.setForeground(Color.RED);
        txt.requestFocus();
        txt.selectAll();
        lbShowMessages.setText(message);
        lbShowMessages.setIcon(errorIcon);
    }

    private void showMessage(String message, int type) {
        if (type == SUCCESS) {
            lbShowMessages.setForeground(Color.GREEN);
            lbShowMessages.setIcon(checkIcon);
        } else if (type == ERROR) {
            lbShowMessages.setForeground(Color.RED);
            lbShowMessages.setIcon(errorIcon);
        } else {
            lbShowMessages.setForeground(Color.BLACK);
            lbShowMessages.setIcon(null);
        }
        lbShowMessages.setText(message);
    }

    private boolean validData(int type) {
        
        return true;
    }

    private boolean validDataTim() {
        String TenKH = txtTim.getText().trim();
        if (!(TenKH.length() > 0)) {
            showMessage("Lỗi: Tên không được để trống", txtTim);
           return false;
        }
        return true;
    }


    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
        return sdf.format(date);
    }

    private void reSizeColumnTable() {
        // table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableShowInfo.getColumnModel().getColumn(0).setPreferredWidth(40);
        tableShowInfo.getColumnModel().getColumn(1).setPreferredWidth(115);
        tableShowInfo.getColumnModel().getColumn(2).setPreferredWidth(70);
        tableShowInfo.getColumnModel().getColumn(3).setPreferredWidth(85);
        tableShowInfo.getColumnModel().getColumn(4).setPreferredWidth(70);
        tableShowInfo.getColumnModel().getColumn(5).setPreferredWidth(80);
    }
    public void renderData() throws MalformedURLException, RemoteException, NotBoundException {
    	
    	dskh = client.getKhachHangDao().getListKhachHang();
    	tableShowInfo.clearSelection();
    	modelTable.getDataVector().removeAllElements();
        for(int j=0; j<dskh.size(); j++) {
        	KhachHang khachhang = dskh.get(j);
        	modelTable.addRow(new Object[] {
        		khachhang.getMaKH(),
        		khachhang.getTenKH(),
        		khachhang.getCmnd(),
        		khachhang.getSoDienThoai(),
        		khachhang.getNgayHetHan(),
        		khachhang.getLoaiKH(),
        		khachhang.getSoLanDatPhong()
        		    		
        	});
        };
        tableShowInfo.revalidate();
        tableShowInfo.repaint();
    }
    public void clear() {
    	txtMaKH.setText("");
    	txtTenKH.setText("");
    	txtCMND.setText("0");
    	txtSDT.setText("");
    	cboLoaiKhach.setSelectedIndex(0);
    	
    }
    public void renderError(JTextField a, String message){
        a.requestFocus();
        a.selectAll();
        JOptionPane.showMessageDialog(pnMain, message);
    }
    public void showKhachHang(KhachHang kh) {
    	txtMaKH.setText(String.valueOf(kh.getMaKH()));
    	txtTenKH.setText(String.valueOf(kh.getTenKH()));
    	txtCMND.setText(kh.getCmnd());
    	txtSDT.setText(kh.getSoDienThoai());
    	dpNgayHetHan.setValue(kh.getNgayHetHan());
    	if(kh.getLoaiKH().toLowerCase().equals("nước ngoài"))
    		cboLoaiKhach.setSelectedIndex(1);
    	else
    		cboLoaiKhach.setSelectedIndex(0);
    }
}
