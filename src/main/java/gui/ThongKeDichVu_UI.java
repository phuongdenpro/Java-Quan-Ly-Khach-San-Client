package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import app.Client;
import dao.DichVuDao;
import dao.KhachHangDao;
import model.DichVu;
import model.KhachHang;




public class ThongKeDichVu_UI extends JFrame implements ActionListener, MouseListener{
	
	
private int soLuongSP = 0;
	
	private JPanel contentPane;
	private JTextField txtTuNgay;
	private JTextField txtToiNgay;
	
	private DefaultTableModel model;
	private JTable table;
	DialogDatePicker f = new DialogDatePicker();
	private kDatePicker dpTuNgay;
	private kDatePicker dpToiNgay;
	private JComboBox comboBox;
	private JComboBox cboLoaiTK;

	private JLabel lblTongSo;

	private JLabel lblTongSoTien;

	private int tongSoLanMua;

	private int tongSoTien;

	private DefaultComboBoxModel<Integer> modelLimit;

	private JComboBox cboLimit;

	private Client client;

	private JLabel lblTongSoDV;

	private List<DichVu> dsdv;

	
	 public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
	        new ThongKeDichVu_UI().setVisible(true);
	    }

	    
	    public ThongKeDichVu_UI() throws RemoteException, MalformedURLException, NotBoundException {
	    	try {
				client = new Client();
			} catch (IOException | NotBoundException e) {
				e.printStackTrace();
			}
	    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLocationRelativeTo(null);
			setBounds(0, 0, 1300, 700);
			
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(new BorderLayout(0, 0));
			
			JPanel panel = new JPanel();
			contentPane.add(panel, BorderLayout.NORTH);
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			
			JPanel panel_3 = new JPanel();
			panel.add(panel_3);
			
			JLabel lblNewLabel_2 = new JLabel("Thống kê dịch vụ");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
			panel_3.add(lblNewLabel_2);
			
			JPanel panel_2 = new JPanel();
			panel.add(panel_2);
			panel_2.add(Box.createHorizontalStrut(50));
			JLabel lblThongKeTheo = new JLabel("Thống kê theo: ");
			panel_2.add(lblThongKeTheo);
			
			
			DefaultComboBoxModel<String> modelLoai = new DefaultComboBoxModel<String>();
			cboLoaiTK = new JComboBox(modelLoai);
			panel_2.add(cboLoaiTK);
			modelLoai.addElement("Tùy chỉnh");
			modelLoai.addElement("Ngày hôm nay");
			modelLoai.addElement("Ngày hôm qua");
			modelLoai.addElement("7 ngày qua");
			modelLoai.addElement("1 tháng qua");
			modelLoai.addElement("1 năm qua");
			
			JLabel lblTuNgay = new JLabel("Từ ngày:  ");
			panel_2.add(lblTuNgay);
			
			
			dpTuNgay = new kDatePicker(100);
			dpTuNgay.setPreferredSize(new Dimension(100, 22));
			//dpTuNgay = new kDatePicker();
			panel_2.add(dpTuNgay);
			
			JLabel lblToiNgay = new JLabel("Tới ngày:");
			panel_2.add(lblToiNgay);
			
			dpToiNgay = new kDatePicker(100);
			dpToiNgay.setPreferredSize(new Dimension(100, 22));
			//dpToiNgay = new kDatePicker();
			panel_2.add(dpToiNgay);
			
			
			panel_2.add(Box.createHorizontalStrut(50));
			JButton btnThongKe = new JButton("Thống kê", new ImageIcon("data/images/statistics.png"));
			btnThongKe.setPreferredSize(new Dimension(160, 25));

			btnThongKe.setBackground(Color.WHITE);
			panel_2.add(btnThongKe);
			
			JButton btnLamMoi = new JButton("Làm mới dữ liệu", new ImageIcon("data/images/refresh.png"));
			btnLamMoi.setPreferredSize(new Dimension(160, 25));
			btnLamMoi.setBackground(Color.WHITE);
			panel_2.add(btnLamMoi);
			
			JPanel panel_1 = new JPanel();
			contentPane.add(panel_1, BorderLayout.CENTER);
			
			panel_1.setLayout(new BorderLayout(0, 0));
			
			String[] cols = {"STT", "Mã dịch vụ", "Tên dịch vụ", "Đơn giá","Số lần đặt", "Tổng tiền"};
			model = new DefaultTableModel(cols, 0);
			table = new JTable(model);
			JScrollPane scrollPane = new JScrollPane(table);
			panel_1.add(scrollPane);
			
			JPanel panel_4 = new JPanel();
			panel_1.add(panel_4, BorderLayout.SOUTH);
			
			JPanel panel_5 = new JPanel();
			panel_4.add(panel_5);
			
			panel_5.add(Box.createHorizontalStrut(250));
			
			JLabel lblTongDV = new JLabel("Tổng số dịch vụ: ");
			lblTongDV.setFont(new Font("Tahoma", Font.PLAIN, 15));
			panel_5.add(lblTongDV);
			
			lblTongSoDV = new JLabel("0");
			lblTongSoDV.setPreferredSize(new Dimension(50,30));
			lblTongSoDV.setFont(new Font("Tahoma", Font.PLAIN, 15));
			panel_5.add(lblTongSoDV);
			
			JLabel lblTong = new JLabel("Tổng số lần bán: ");
			lblTong.setFont(new Font("Tahoma", Font.PLAIN, 15));
			panel_5.add(lblTong);
			
			lblTongSo = new JLabel("0");
			lblTongSo.setPreferredSize(new Dimension(50,30));
			lblTongSo.setFont(new Font("Tahoma", Font.PLAIN, 15));
			panel_5.add(lblTongSo);
			
			JPanel panel_5_1 = new JPanel();
			panel_4.add(panel_5_1);
			
			JLabel lblTngSTin = new JLabel("Tổng doanh thu: ");
			lblTngSTin.setFont(new Font("Tahoma", Font.PLAIN, 15));
			panel_5_1.add(lblTngSTin);
			
			lblTongSoTien = new JLabel("0");
			lblTongSoTien.setPreferredSize(new Dimension(50,30));
			lblTongSoTien.setFont(new Font("Tahoma", Font.PLAIN, 15));
			panel_5_1.add(lblTongSoTien);
			panel_5_1.add(Box.createHorizontalStrut(200));
			JButton btnIn = new JButton("In báo cáo", new ImageIcon("data/images/printer.png"));
			panel_5_1.add(btnIn);
			renderData();
			
}
	    public JPanel getContentPane() {
			return contentPane;
	    }


		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}   
		public void renderData() throws MalformedURLException, RemoteException, NotBoundException {
			DichVuDao dichVuDao = client.getDichVuDao();
	    	
	    	
	        	dsdv = dichVuDao.getListDichVu();
	        
	        	AtomicInteger stt = new AtomicInteger(1);
	        table.clearSelection();
	        model.getDataVector().removeAllElements();
	        for(int j=0; j<dsdv.size(); j++) {
	        	DichVu dichvu = dsdv.get(j);
	        	model.addRow(new Object[] {
	        			stt.get(),
	        		dichvu.getMaDV(),
	        		dichvu.getTenDV(),
	        		dichvu.getDonGia()
	        	});
	        };
	        table.revalidate();
	        table.repaint();
	    }
}
