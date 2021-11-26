package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.*;

import app.Client;
import model.DichVu;
import model.LoaiPhong;
import utils.Currency;

import javax.swing.border.*;


public class QuanLyDichVu_UI extends JFrame{
    public JPanel pnMain;
    private JTable table;
    private DefaultTableModel modelTable;
    private JTextField txtTim, txtMaDV, txtTenDV, txtDonGia;
    private JButton btnTim, btnThem, btnSua, btnXoa, btnLamLai, btnXemTatCa;
    private JLabel lbShowMessages;
	private List<DichVu> dsdv;
	private Client client;
    private final int SUCCESS = 1, ERROR = 0;
    ImageIcon blueAddIcon = new ImageIcon("data/images/blueAdd_16.png");
    ImageIcon editIcon = new ImageIcon("data/images/edit2_16.png");
    ImageIcon deleteIcon = new ImageIcon("data/images/trash2_16.png");
    ImageIcon refreshIcon = new ImageIcon("data/images/refresh_16.png");
    ImageIcon searchIcon = new ImageIcon("data/images/search_16.png");
    ImageIcon checkIcon = new ImageIcon("data/images/check2_16.png");
    ImageIcon errorIcon = new ImageIcon("data/images/cancel_16.png");
	private DefaultComboBoxModel<String> modelLoc;
	private JComboBox cmbLoaiTimKiem;
    

    public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException{
        new QuanLyDichVu_UI().setVisible(true);
    }
    
    public QuanLyDichVu_UI() throws MalformedURLException, RemoteException, NotBoundException {
    	try {
			client = new Client();
		} catch (IOException | NotBoundException e) {
			e.printStackTrace();
		}
        setSize(1000, 670);
        setTitle("Quản lý dịch vụ");
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pnMain = new JPanel();
        pnMain.setLayout(null);
        pnMain.setBounds(0, 0, 1000, 670);

        getContentPane().add(pnMain);

        JLabel lbTitle = new JLabel("Danh Mục Dịch Vụ");
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setFont(new Font("Dialog", Font.BOLD, 20));
        lbTitle.setBounds(10, 11, 972, 30);
        pnMain.add(lbTitle);

        JPanel pnInfoDV = new JPanel();
        pnInfoDV.setBorder(
                new TitledBorder(null, "Thông Tin Dịch Vụ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnInfoDV.setBounds(10, 46, 391, 582);
        pnMain.add(pnInfoDV);
        pnInfoDV.setLayout(null);

        JLabel lbMaDV = new JLabel("Mã dịch vụ: ");
        lbMaDV.setBounds(12, 33, 80, 25);
        pnInfoDV.add(lbMaDV);

        txtMaDV = new JTextField();
        txtMaDV.setBounds(100, 33, 279, 25);
        txtMaDV.setEditable(false);
        txtMaDV.setColumns(10);
        pnInfoDV.add(txtMaDV);

        JLabel lbTenDV = new JLabel("Tên dịch vụ:");
        lbTenDV.setBounds(12, 70, 80, 25);
        pnInfoDV.add(lbTenDV);

        txtTenDV = new JTextField();
        txtTenDV.setBounds(100, 70, 279, 25);
        pnInfoDV.add(txtTenDV);
        txtTenDV.setColumns(10);

        JLabel lbDonGia = new JLabel("Đơn giá:");
        lbDonGia.setBounds(12, 105, 80, 25);
        pnInfoDV.add(lbDonGia);

        txtDonGia = new JTextField();
     //   txtDonGia.setText("0.0");
        txtDonGia.setBounds(100, 105, 279, 25);
        pnInfoDV.add(txtDonGia);
        txtDonGia.setColumns(10);

        btnThem = new JButton("Thêm", blueAddIcon);
        btnThem.setBounds(12, 163, 108, 26);
        pnInfoDV.add(btnThem);

        btnSua = new JButton("Sửa", editIcon);
        btnSua.setBounds(132, 163, 120, 26);
        pnInfoDV.add(btnSua);

        btnXoa = new JButton("Xóa", deleteIcon);
        btnXoa.setBounds(264, 163, 115, 26);
        pnInfoDV.add(btnXoa);

        btnLamLai = new JButton("Làm lại", refreshIcon);
        btnLamLai.setBounds(132, 205, 120, 26);
        pnInfoDV.add(btnLamLai);

        lbShowMessages = new JLabel("");
        lbShowMessages.setBounds(12, 115, 367, 16);
        lbShowMessages.setForeground(Color.RED);
        pnInfoDV.add(lbShowMessages);

        String[] cols = { "Mã DV", "Tên DV", "Đơn giá" };
        modelTable = new DefaultTableModel(cols, 0);

        JPanel pnShowDV = new JPanel();
        pnShowDV.setBorder(
                new TitledBorder(null, "Danh sách dịch vụ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnShowDV.setBounds(402, 46, 580, 582);
        pnShowDV.setLayout(null);
        pnMain.add(pnShowDV);

        modelLoc = new DefaultComboBoxModel<String>();
		cmbLoaiTimKiem = new JComboBox(modelLoc);
		cmbLoaiTimKiem.setBackground(Color.WHITE);
		modelLoc.addElement("Mã dịch vụ");
		modelLoc.addElement("Tên dịch vụ");
    //    JLabel lbTimKiem = new JLabel("Tìm dịch vụ:");
		cmbLoaiTimKiem.setBounds(12, 20, 100, 26);
        pnShowDV.add(cmbLoaiTimKiem);

        txtTim = new JTextField();
        txtTim.setBounds(120, 20, 200, 26);
        pnShowDV.add(txtTim);
        txtTim.setColumns(10);

        btnTim = new JButton("Tìm", searchIcon);
        btnTim.setBounds(325, 20, 90, 26);
        pnShowDV.add(btnTim);
        btnThem.setBackground(Color.WHITE);
        btnSua.setBackground(Color.WHITE);
        btnXoa.setBackground(Color.WHITE);
        btnLamLai.setBackground(Color.WHITE);
        btnTim.setBackground(Color.WHITE);
        

        JPanel pnTableDV = new JPanel();
        pnTableDV.setBounds(12, 55, 556, 515);
        pnShowDV.add(pnTableDV);
        pnTableDV.setLayout(new BorderLayout(0, 0));

        table = new JTable(modelTable) {
            // khĂ³a sá»­a dá»¯ liá»‡u trá»±c tiáº¿p trĂªn table
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };
        JScrollPane scpTableDV = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        pnTableDV.add(scpTableDV, BorderLayout.CENTER);

        btnXemTatCa = new JButton("Xem tất cả");
        btnXemTatCa.setBounds(426, 20, 121, 26);
        pnShowDV.add(btnXemTatCa);
        btnXemTatCa.setBackground(Color.WHITE);

        reSizeColumnTable();
        renderDataDV();
        
        
        table.getSelectionModel().addListSelectionListener((e) -> {
        	int idx = table.getSelectedRow();
        	if(idx != -1) {
        		DichVu dv = dsdv.get(idx);
        		txtMaDV.setText(String.valueOf(dv.getMaDV()));
        		txtTenDV.setText(dv.getTenDV());
        		txtDonGia.setText(String.valueOf((int) dv.getDonGia()));        		
        		
        	}
        });
        btnTim.addActionListener((e) -> {
        	String tenDV = txtTim.getText();
        	for(int i=0; i<dsdv.size(); i++) {
        		if(dsdv.get(i).getTenDV().contains(tenDV)) {
        			System.out.println(i);
        			table.setRowSelectionInterval(i, i);
        			return;
        		}
        	}
        	JOptionPane.showMessageDialog(pnMain, "Không tìm thấy");
        });
        btnLamLai.addActionListener((e) -> {
        	clear();
        });
        
        btnThem.addActionListener((e) -> {
        	
        	if(checkData() == false)
        		return;
        	
        	
            DichVu dv = new DichVu(txtTenDV.getText(),Double.parseDouble(txtDonGia.getText()));
            
            try {
				if(client.getDichVuDao().themDichVu(dv)) {
					JOptionPane.showMessageDialog(pnMain, "Thêm thành công");
					
					renderDataDV();
					
					return;
				}
			} catch (HeadlessException | RemoteException | MalformedURLException | NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            
            JOptionPane.showMessageDialog(pnMain, "Thêm thất bại");
        });
      btnSua.addActionListener((e) -> {
        	int idx = table.getSelectedRow();
        	if(idx == -1) {
        		JOptionPane.showMessageDialog(pnMain, "Vui lòng chọn dịch vụ để sửa");
        		return;
        	}
    	  
        	if(checkData() == false)
        		return;
        	
        	DichVu dv = new DichVu(
        			dsdv.get(idx).getMaDV(), 
        			txtTenDV.getText(), 
        			Double.parseDouble(txtDonGia.getText()));
            
            try {
				if(client.getDichVuDao().capNhatDichVu(dv)) {
					JOptionPane.showMessageDialog(pnMain, "Sửa thành công");
					renderDataDV();
					return;
				}
			} catch (HeadlessException | RemoteException | MalformedURLException | NotBoundException e1) {
				e1.printStackTrace();
			}
            
            JOptionPane.showMessageDialog(pnMain, "Sửa thất bại");
        });
      btnXoa.addActionListener((e) -> {
      	int idx = table.getSelectedRow();
      	if(idx != -1) {
      		int maDv = dsdv.get(idx).getMaDV();
      		try {
					if(client.getDichVuDao().xoaDichVu(maDv)) {
						JOptionPane.showMessageDialog(pnMain, "Xóa thành công");
						renderDataDV();
						return;
					}
				} catch (RemoteException | MalformedURLException | NotBoundException e1) {
					e1.printStackTrace();
				}
      		
      		JOptionPane.showMessageDialog(pnMain, "Xóa thất bại");
      	}
      });
      btnXemTatCa.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				renderDataDV();
			} catch (MalformedURLException | RemoteException | NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	});
      
        
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



    private void reSizeColumnTable() {
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(83);
        table.getColumnModel().getColumn(1).setPreferredWidth(350);
        table.getColumnModel().getColumn(2).setPreferredWidth(120);
    }
    public JPanel getpnMain() {
		return pnMain;
    }
    public void clear() {
    	txtMaDV.setText("");
    	txtTenDV.setText("");
    	txtDonGia.setText("");
    	txtTim.setText("");
    }
    public void renderDataDV() throws MalformedURLException, RemoteException, NotBoundException {
    	dsdv = client.getDichVuDao().getListDichVu();
        table.clearSelection();
        modelTable.getDataVector().removeAllElements();
        for(int j=0; j<dsdv.size(); j++) {
        	DichVu dichVu = dsdv.get(j);
        	modelTable.addRow(new Object[] {
        		dichVu.getMaDV(),
        		dichVu.getTenDV(),
        		Currency.format(dichVu.getDonGia())
        	});
        };
        table.revalidate();
        table.repaint();
    }
    public boolean checkData() {
    	if(txtTenDV.getText().trim().equals("")){
            renderError(txtTenDV, "Tên Dịch vụ không được để trống");
            return false;
        }
        
        if(!txtDonGia.getText().matches("^\\d+$")){
            renderError(txtDonGia, "Đơn giá không hợp lệ");
            return false;
        } 
        return true;
    }
    public void renderError(JTextField a, String message){
        a.requestFocus();
        a.selectAll();
        JOptionPane.showMessageDialog(pnMain, message);
    }
    private void showMessage(String message, JTextField txt, JLabel lbl) {
        lbl.setForeground(Color.RED);
        txt.requestFocus();
        txt.selectAll();
        lbl.setText(message);
        lbl.setIcon(errorIcon);
    }
   
}
