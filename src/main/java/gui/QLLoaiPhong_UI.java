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

public class QLLoaiPhong_UI extends JFrame {
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
	private JTextField txtMaLoaiPhong;
	private JPanel panel_4;
	private JLabel lblTenLoai;
	private JPanel panel_9;
	private JButton btnSuaP;
	private JButton btnLamMoi;
	private JPanel panel_11;
	private JLabel lblnGi;
	private JTextField txtDonGia;
	private JPanel panel_8;
	private JLabel lbTimPhong;
	private JTextField txtTimKiem;
	private JButton btnTimKiem;
	private JButton btnThemP;
	private JScrollPane scrollPane;
	private JTextField txtTenLoai;
	private Client client;
	private DefaultTableModel model;
	private JTable table;
	private DefaultComboBoxModel<String> modelLoc;
	private DefaultComboBoxModel<String> modelLoaiPhong;
	private List<LoaiPhong> dslp;
	private JButton btnXoa;
	private JComboBox<String> cmbLoaiTimKiem;

	public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
		new QLLoaiPhong_UI().setVisible(true);
	}

	public QLLoaiPhong_UI() throws RemoteException, MalformedURLException, NotBoundException {
		try {
			client = new Client();
		} catch (IOException | NotBoundException e) {
			e.printStackTrace();
		}

		setSize(1000, 670);
		setTitle("Quản Lý Loại Phòng");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JLabel lbTitle = new JLabel("Quản Lý Loại Phòng");
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

		lbMaPhong = new JLabel("Mã loại phòng: ");
		lbMaPhong.setPreferredSize(new Dimension(100, 30));
		panel_3.add(lbMaPhong);

		txtMaLoaiPhong = new JTextField();
		txtMaLoaiPhong.setEditable(false);
		txtMaLoaiPhong.setColumns(20);
		panel_3.add(txtMaLoaiPhong);

		panel_4 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_4.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		pnBL.add(panel_4);

		lblTenLoai = new JLabel("Tên loại phòng");
		lblTenLoai.setPreferredSize(new Dimension(100, 30));
		panel_4.add(lblTenLoai);

		txtTenLoai = new JTextField();
		panel_4.add(txtTenLoai);
		txtTenLoai.setColumns(20);

		modelLoaiPhong = new DefaultComboBoxModel<String>();

		panel_11 = new JPanel();
		FlowLayout flowLayout_7 = (FlowLayout) panel_11.getLayout();
		flowLayout_7.setAlignment(FlowLayout.LEFT);
		pnBL.add(panel_11);

		lblnGi = new JLabel("Đơn giá: ");
		lblnGi.setPreferredSize(new Dimension(100, 30));
		panel_11.add(lblnGi);

		txtDonGia = new JTextField();
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

		modelLoc = new DefaultComboBoxModel<String>();
		cmbLoaiTimKiem = new JComboBox(modelLoc);
		cmbLoaiTimKiem.setBackground(Color.WHITE);
		modelLoc.addElement("Mã loại phòng");
		modelLoc.addElement("Tên loại phòng");

		// lbTimPhong = new JLabel("Mã loại phòng: ");
		panel_8.add(cmbLoaiTimKiem);

		txtTimKiem = new JTextField();
		txtTimKiem.setPreferredSize(new Dimension(150, 26));
		txtTimKiem.setColumns(10);
		panel_8.add(txtTimKiem);

		btnTimKiem = new JButton("Tìm", null);
		btnTimKiem.setBackground(Color.WHITE);
		panel_8.add(btnTimKiem);

		modelLoc = new DefaultComboBoxModel<String>();
		cmbLoaiTimKiem = new JComboBox(modelLoc);
		modelLoc.addElement("Mã loại phòng");
		modelLoc.addElement("Tên loại phòng");

		pnBR = new JPanel();
		panel_1.add(pnBR);
		pnBR.setLayout(new BorderLayout(0, 0));

		String[] colsP = { "Mã loại phòng", "Tên loại phòng", "Giá phòng" };
		model = new DefaultTableModel(colsP, 0);
		table = new JTable(model);
		scrollPane = new JScrollPane(table);
		pnBR.add(scrollPane, BorderLayout.CENTER);

		table.getSelectionModel().addListSelectionListener((e) -> {
			int idx = table.getSelectedRow();
			if (idx != -1) {
				LoaiPhong lp = dslp.get(idx);
				txtMaLoaiPhong.setText(String.valueOf(lp.getMaLoaiPhong()));
				txtTenLoai.setText(lp.getTenLoaiPhong());
				txtDonGia.setText(String.valueOf((int) lp.getDonGia()));

			}
		});

		btnTimKiem.addActionListener((e) -> {
			if (cmbLoaiTimKiem.getSelectedIndex() == 1) {
				int id;
				try {
					id = Integer.parseInt(txtTimKiem.getText());
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(contentPane, "Dữ liệu không hợp lệ");
					return;
				}
				for (int i = 0; i < dslp.size(); i++) {
					if (dslp.get(i).getMaLoaiPhong() == id) {
						table.setRowSelectionInterval(i, i);
						return;
					}
				}
			} else {
				String ten;
				ten = txtTimKiem.getText();
				if (ten.equals("")) {
					JOptionPane.showMessageDialog(contentPane, "Dữ liệu không hợp lệ");
					return;
				} else {
					for (int i = 0; i < dslp.size(); i++) {
						if (dslp.get(i).getTenLoaiPhong().equalsIgnoreCase(ten)) {
							table.setRowSelectionInterval(i, i);
							return;
						}
					}
				}
			}
			JOptionPane.showMessageDialog(contentPane, "Không tìm thấy");
		});

		btnLamMoi.addActionListener((e) -> {
			clear();
		});

		btnThemP.addActionListener((e) -> {

			if (checkData() == false)
				return;

			LoaiPhong lp = new LoaiPhong(txtTenLoai.getText(), Double.parseDouble(txtDonGia.getText()));

			try {
				if (client.getLoaiPhongDao().themLoaiPhong(lp)) {
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

			if (checkData() == false)
				return;

			LoaiPhong lp = new LoaiPhong(Integer.parseInt(txtMaLoaiPhong.getText()), txtTenLoai.getText(),
					Double.parseDouble(txtDonGia.getText()));

			try {
				if (client.getLoaiPhongDao().capNhatLoaiPhong(lp)) {
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
			if (idx != -1) {
				int maLoai = dslp.get(idx).getMaLoaiPhong();
				try {
					if (client.getLoaiPhongDao().xoaLoaiPhong(maLoai)) {
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
		renderData();
	}

	public void renderData() throws MalformedURLException, RemoteException, NotBoundException {
		dslp = client.getLoaiPhongDao().getDSLoaiPhong();
		table.clearSelection();
		model.getDataVector().removeAllElements();
		for (int j = 0; j < dslp.size(); j++) {
			LoaiPhong loaiPhong = dslp.get(j);
			model.addRow(new Object[] { loaiPhong.getMaLoaiPhong(), loaiPhong.getTenLoaiPhong(),
					Currency.format(loaiPhong.getDonGia()) });
		}
		;
		table.revalidate();
		table.repaint();
	}

	private void showMessage(String message, JTextField txt, JLabel lbl) {
		lbl.setForeground(Color.RED);
		txt.requestFocus();
		txt.selectAll();
		lbl.setText(message);
		lbl.setIcon(errorIcon);
	}

	public void clear() {
		txtMaLoaiPhong.setText("");
		txtTenLoai.setText("");
		txtDonGia.setText("");
	}

	public void renderError(JTextField a, String message) {
		a.requestFocus();
		a.selectAll();
		JOptionPane.showMessageDialog(contentPane, message);
	}

	public boolean checkData() {
		if (txtTenLoai.getText().trim().equals("")) {
			renderError(txtTenLoai, "Tên loại phòng không được để trống");
			return false;
		}

		if (!txtDonGia.getText().matches("^\\d+$")) {
			renderError(txtDonGia, "Đơn giá không hợp lệ");
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
