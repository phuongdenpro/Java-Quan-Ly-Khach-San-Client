package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


import javax.swing.border.EtchedBorder;

public class HoaDonDichVu_UI extends JFrame{
	private DefaultTableModel modelHD, modelDV;
	String[] colsHD = { "Mã hoá đơn", "Mã khách hàng", "Thời gian đặt", "Tổng tiền", "Tình trạng" };
	String[] colsDV = { "Mã dịch vụ", "Tên dịch vụ", "Số lượng", "Đơn giá", "Thời gian đặt", "Mã hoá đơn" };
	public JPanel pnMain;
	private JTable tableHDDV;
	private JTable tableDV;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JTextField txtSoLuong;
	private JButton btnThem, btnXacNhan;
	private JComboBox<String> cboMaKH;
	private JComboBox<String> cboDV;
	private JPanel panel;
	private JPanel panel_1;
	private JTextField txtTimMaHDDV;

	private JTextField txtGia;
	private JTextField txtTen;
	private AbstractButton btnSua;
	private JButton btnTimMaHDDV;
	private JButton btnXem;
	private JButton btnBoChon;
	

	public static void main(String[] args) {
		HoaDonDichVu_UI hoaDonDichVu_UI = new HoaDonDichVu_UI();

		hoaDonDichVu_UI.start();
		hoaDonDichVu_UI.setVisible(true);
	}

	public HoaDonDichVu_UI() {
		

		setTitle("Hoá đơn dịch vụ");
		setSize(1000, 670);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		
	}

	public void start(){
		pnMain = renderGUI();
	}

	public JPanel renderGUI(){
		pnMain = new JPanel();
		pnMain.setBounds(0, 0, 584, 411);
		getContentPane().add(pnMain);

		JLabel lbTitle = new JLabel("Hoá Đơn Thanh Toán Dịch Vụ");
		lbTitle.setBounds(335, 11, 348, 30);
		lbTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		pnMain.add(lbTitle);

		modelHD = new DefaultTableModel(colsHD, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
				// Không cho chỉnh sửa trên table
			}
		};

		modelDV = new DefaultTableModel(colsDV, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
				// Không cho chỉnh sửa trên table
			}
		};
		pnMain.setLayout(null);

		JPanel pn = new JPanel();
		pn.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"\u0110\u1EB7t d\u1ECBch v\u1EE5", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pn.setBounds(10, 65, 342, 286);
		pnMain.add(pn);
		pn.setLayout(null);

		JLabel lbMaKH = new JLabel("Mã khách hàng:");
		lbMaKH.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbMaKH.setBounds(10, 22, 93, 22);
		pn.add(lbMaKH);

		cboMaKH = new JComboBox<String>();
		cboMaKH.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cboMaKH.setBounds(122, 22, 205, 22);
		cboMaKH.addItem("");
		pn.add(cboMaKH);

		JLabel lbDV = new JLabel("Dịch vụ:");
		lbDV.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbDV.setBounds(10, 88, 93, 22);
		pn.add(lbDV);

		cboDV = new JComboBox<String>();
		cboDV.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cboDV.setBounds(122, 88, 205, 22);
		cboDV.addItem("");
		pn.add(cboDV);

		JLabel lbGia = new JLabel("Đơn giá:");
		lbGia.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbGia.setBounds(10, 154, 93, 22);
		pn.add(lbGia);

		txtGia = new JTextField();
		txtGia.setEditable(false);
		txtGia.setBounds(122, 154, 205, 22);
		pn.add(txtGia);
		txtGia.setColumns(10);

		JLabel lbSoLuong = new JLabel("Số lượng:");
		lbSoLuong.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbSoLuong.setBounds(10, 121, 93, 22);
		pn.add(lbSoLuong);

		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtSoLuong.setBounds(122, 121, 205, 22);
		pn.add(txtSoLuong);
		txtSoLuong.setColumns(10);

		btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon("data\\images\\blueAdd_16.png"));
		btnThem.setBounds(10, 198, 148, 33);
		pn.add(btnThem);

		btnSua = new JButton("Sửa");
		btnSua.setIcon(new ImageIcon("data\\images\\edit2_16.png"));
		btnSua.setBounds(168, 198, 159, 33);
		pn.add(btnSua);

		btnXacNhan = new JButton("Tạo hoá đơn");
		btnXacNhan.setBounds(10, 242, 317, 33);
		pn.add(btnXacNhan);
		btnXacNhan.setIcon(new ImageIcon("data\\images\\check.png"));

		JLabel lbTen = new JLabel("Tên khách hàng");
		lbTen.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbTen.setBounds(10, 55, 93, 22);
		pn.add(lbTen);

		txtTen = new JTextField();
		txtTen.setEditable(false);
		txtTen.setColumns(10);
		txtTen.setBounds(122, 55, 205, 22);
		pn.add(txtTen);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"D\u1ECBch v\u1EE5 kh\u00E1ch h\u00E0ng \u0111\u00E3 \u0111\u1EB7t", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(362, 64, 614, 251);
		pnMain.add(panel);
		panel.setLayout(null);

		tableDV = new JTable(modelDV);
		JScrollPane scDV = new JScrollPane(tableDV, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scDV.setBounds(10, 29, 594, 211);
		tableDV.getColumnModel().getColumn(1).setPreferredWidth(105);
		panel.add(scDV);

		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh s\u00E1ch ho\u00E1 \u0111\u01A1n d\u1ECBch v\u1EE5", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel_1.setBounds(362, 326, 614, 307);
		pnMain.add(panel_1);
		panel_1.setLayout(null);

		tableHDDV = new JTable(modelHD);
		JScrollPane scHD = new JScrollPane(tableHDDV, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scHD.setBounds(10, 57, 594, 239);
		panel_1.add(scHD);

		JLabel lbTimMaHDDV = new JLabel("Mã hoá đơn dịch vụ:");
		lbTimMaHDDV.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbTimMaHDDV.setBounds(10, 32, 120, 14);
		panel_1.add(lbTimMaHDDV);

		txtTimMaHDDV = new JTextField();
		txtTimMaHDDV.setBounds(140, 29, 120, 20);
		panel_1.add(txtTimMaHDDV);
		txtTimMaHDDV.setColumns(10);

		btnTimMaHDDV = new JButton("Tìm");
		btnTimMaHDDV.setIcon(new ImageIcon("data\\images\\search_16.png"));
		btnTimMaHDDV.setBounds(270, 28, 89, 23);
		panel_1.add(btnTimMaHDDV);

		btnXem = new JButton("Xem tất cả");
		btnXem.setIcon(null);
		btnXem.setBounds(366, 28, 106, 23);
		panel_1.add(btnXem);
		
		btnBoChon = new JButton("Bỏ chọn");
		btnBoChon.setBounds(482, 28, 89, 23);
		panel_1.add(btnBoChon);

		return pnMain;
		
	}




	private String convertTinhTrang(int tinhTrang) {
		String result = "";
		if (tinhTrang == 0)
			result = "Chưa thanh toán";
		else if (tinhTrang == 1)
			result = "Đã thanh toán";
		return result;
	}


	private boolean validDataSo() {
		String soLuong = txtSoLuong.getText().trim();
		
		if (soLuong.length() > 0) {
			try {
				int x = Integer.parseInt(soLuong);
				if (x <= 0) {
					JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
					return false;
				}
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(this, "Số lượng phải là số!");
				return false;
			}
		}
		return true;
	}
}
