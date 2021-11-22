package gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.Client;
import dao.impl.LoaiPhongImpl;
import dao.impl.PhongImpl;
import model.ChiTietHoaDonPhong;
import model.HoaDonDV;
import model.HoaDonPhong;
import model.LoaiPhong;
import model.Phong;


public class TrangChu_UI extends JFrame{

    private int countAvail = 0;
    private int countBooking = 0;
    private int countUsing = 0;
    public ImageIcon icon_green_check = new ImageIcon("data/images/check.png", "check");
    public ImageIcon icon_red_close = new ImageIcon("data/images/close.png", "close");
    public ImageIcon icon_question = new ImageIcon("data/images/question_16.png");
    public ImageIcon icon_pay = new ImageIcon("data/images/purse.png");
    public ImageIcon icon_order = new ImageIcon("data/images/booking.png");
    public ImageIcon icon_add = new ImageIcon("data/images/plus.png");
    public ImageIcon icon_checkin = new ImageIcon("data/images/add.png");
    

    public JPanel contentPane;
    private DefaultComboBoxModel<String> modelLP;
    public JComboBox<String> cboLP;
    private JPanel pn_sec_available;
    private JLabel lbAvail;
    private JLabel lbUsing;

    
    public JFrame popup = new JFrame();
    private JLabel lbBooking;
	private JPanel pnPhongTrong;
	private JButton btn_DatPhong;
	private JButton btn_NhanPhong;
	private JButton btn_ThanhToan;
	private JButton btn_SuDungDV;
	private JButton btn_XemLichDat;
	private List<Phong> dsp;
	private List<LoaiPhong> dslp;
	private Client client;

    public static void main(String[] args) {
		TrangChu_UI trangChuUI = new TrangChu_UI();
		trangChuUI.setVisible(true);
	}
    
    public TrangChu_UI(){
    	try {
			client = new Client();
		} catch (IOException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	start();
    }

    public void start(){
    	renderGUI();
        renderLoaiPhong();
    }

    public void renderGUI() {
        // nội dung page trang chủ ở đây
    	setSize(new Dimension(1350, 600));
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        // pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
        
        // action
        JPanel pn_interact = new JPanel();
        contentPane.add(pn_interact, BorderLayout.NORTH);
        pn_interact.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JPanel pnFields = new JPanel();
        pnFields.setBorder(new EmptyBorder(0, 0, 0, 20));
        pn_interact.add(pnFields);
        JLabel lbLoaiPhong = new JLabel("Loại phòng: ");
        modelLP = new DefaultComboBoxModel<String>();
        cboLP = new JComboBox<String>(modelLP);

        pnFields.add(lbLoaiPhong);
        pnFields.add(cboLP);


        JPanel pnThongKe = new JPanel();
        pnThongKe.setLayout(new BoxLayout(pnThongKe, BoxLayout.X_AXIS));
        pn_interact.add(pnThongKe);

        // so phong trong
        JPanel lb_sec_avail = new JPanel();
        lb_sec_avail.setBorder(BorderFactory.createEtchedBorder());
        pnThongKe.add(lb_sec_avail);
        lbAvail = new JLabel("Phòng trống (20)", icon_green_check, JLabel.CENTER);
        lb_sec_avail.add(lbAvail);
        lbAvail.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pnThongKe.add(space(5, 5));

        // so phong đã được đặt
        JPanel lb_sec_booking = new JPanel();
        lb_sec_booking.setBorder(BorderFactory.createEtchedBorder());
        pnThongKe.add(lb_sec_booking);
        lbBooking = new JLabel("Đã đặt (20)", icon_question, JLabel.CENTER);
        lb_sec_booking.add(lbBooking);
        lbBooking.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // so phong dang dung
        JPanel lb_sec_using = new JPanel();
        lb_sec_using.setBorder(BorderFactory.createEtchedBorder());
        pnThongKe.add(lb_sec_using);
        lbUsing = new JLabel("Đang ở (20)", icon_red_close, JLabel.CENTER);
        lb_sec_using.add(lbUsing);
        lbUsing.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));


        // hiển thị các phòng trống
        pn_sec_available = new JPanel();
        pn_sec_available.setLayout(new BoxLayout(pn_sec_available, BoxLayout.Y_AXIS));
        // pn_sec_available.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(pn_sec_available);

        JLabel lbDSP = new JLabel("Tình trạng phòng");
        lbDSP.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lbDSP.setAlignmentX(Component.CENTER_ALIGNMENT);
        pn_sec_available.add(lbDSP);
        
        JPanel panel = new JPanel();
        pn_sec_available.add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        
        Component horizontalStrut = Box.createHorizontalStrut(50);
        panel.add(horizontalStrut);
        
        JPanel panel_1 = new JPanel();
        panel.add(panel_1);
        
        pnPhongTrong = new JPanel();
        panel_1.add(pnPhongTrong);
        GridLayout gl_pnPhongTrong = new GridLayout(0, 6);
        gl_pnPhongTrong.setVgap(20);
        gl_pnPhongTrong.setHgap(20);
        pnPhongTrong.setLayout(gl_pnPhongTrong);
        
        renderDSPhong();
        
        Component horizontalStrut_1 = Box.createHorizontalStrut(50);
        panel.add(horizontalStrut_1);
        
        cboLP.addActionListener((e) -> {
			renderDSPhong();
        });
        
    }
    
    public void renderDSPhong() {
    	pnPhongTrong.removeAll();
    	countAvail = 0;
    	countBooking = 0;
    	countUsing = 0;
    	dsp = new ArrayList<Phong>();
		try {
			int idx = cboLP.getSelectedIndex(); 
			if(idx <= 0)
				dsp = client.getPhongDao().getListPhong();
			else {
				dsp = client.getPhongDao().getPhongByMaLoaiPhong(dslp.get(idx - 1).getMaLoaiPhong());
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
    	dsp.forEach(phong -> {
    		renderPhong(phong);
    		if(phong.getTinhTrang() == 1)
    			countBooking++;
    		else if(phong.getTinhTrang() == 2)
    			countUsing++;
    		else 
    			countAvail++;
    	});
    	lbAvail.setText("Phòng trống ("+ countAvail +")");
    	lbBooking.setText("Được đặt ("+ countBooking +")");
    	lbUsing.setText("Đang sử dụng ("+ countUsing +")");
    	pnPhongTrong.revalidate();
    	pnPhongTrong.repaint();
    }

    public void renderPhong(Phong phong){
    	try {
			phong.setTinhTrang(client.getPhongDao().getTinhTrangPhongHomNay(phong.getMaPhong()));
		} catch (RemoteException | MalformedURLException | NotBoundException e1) {
			e1.printStackTrace();
		}
    	
    	JButton btnPhong = new JButton();
    	btnPhong.setBackground(Color.WHITE);
    	btnPhong.setPreferredSize(new Dimension(150, 150));
        pnPhongTrong.add(btnPhong);
//        btnPhong.setLayout(new BoxLayout(btnPhong, BoxLayout.Y_AXIS));
        btnPhong.setLayout(new GridLayout(3, 0));
        btnPhong.setAlignmentX(CENTER_ALIGNMENT);
        
        JLabel lbMaPhong = new JLabel(String.valueOf(phong.getMaPhong()));
        lbMaPhong.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lbMaPhong.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbMaPhong.setForeground(Color.WHITE);

        JLabel lbLoai = new JLabel(phong.getLoaiPhong().getTenLoaiPhong());
        lbLoai.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lbLoai.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbLoai.setForeground(Color.WHITE);

        JLabel lbIcon;
        if(phong.getTinhTrang() == 2){
        	btnPhong.setBackground(Color.red);
            lbIcon = new JLabel(icon_red_close);
        }else if(phong.getTinhTrang() == 1){
        	btnPhong.setBackground(Color.orange);
            lbIcon = new JLabel(icon_question);
        }else{
        	btnPhong.setBackground(Color.green);
            lbIcon = new JLabel(icon_green_check);
            
        }
        lbIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbIcon.setForeground(Color.WHITE);
        lbIcon.setFont(new Font("Tahoma", Font.PLAIN, 20));
        
        btnPhong.add(lbMaPhong);
        btnPhong.add(lbLoai);
        btnPhong.add(lbIcon);
        
        btnPhong.addActionListener((e) -> {
        	showPopup(phong);
        });
    }

    public void renderLoaiPhong(){
        try {
			dslp = client.getLoaiPhongDao().getDSLoaiPhong();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
        
        modelLP.removeAllElements();
        modelLP.addElement("Tất cả");
        dslp.forEach(lp -> {
        	modelLP.addElement(lp.getTenLoaiPhong());
        });
    }
    
    public void showPopup(Phong phong) {
    	popup = new JFrame();
        popup.setTitle("Thông tin phòng");
        popup.setSize(400, 250);
        popup.setResizable(false);
        popup.setLocationRelativeTo(contentPane);
        popup.setAlwaysOnTop(true);
        // popup.setAutoRequestFocus(true);
        

        JPanel pn_p_main = new JPanel();
        popup.add(pn_p_main);
        pn_p_main.setLayout(new BoxLayout(pn_p_main, BoxLayout.Y_AXIS));

        JPanel pn_p_top = new JPanel();
        pn_p_main.add(pn_p_top);
        pn_p_top.setLayout(new GridLayout(3, 2));
        // pn_p_top.setLayout(new BoxLayout(pn_p_top, BoxLayout.Y_AXIS));
        pn_p_top.setBorder(BorderFactory.createTitledBorder("Chi tiết phòng"));

        String tinhTrang = "Có thể sử dụng";
        if(phong.getTinhTrang() == 1)
            tinhTrang = "Đã có người đặt";
        else if(phong.getTinhTrang() == 2)
            tinhTrang = "Đang ở";
            
//        String gia = new QuanLyKhachSan_UI().currencyFormat(phong.getLoaiPhong().getDonGia());
        pn_p_top.add(new JLabel("<html><p style='padding-left: 10px;'>Mã phòng: "+ phong.getMaPhong() +"</p></html>"));
        pn_p_top.add(new JLabel("<html><p style='padding-left: 10px;'>Vị trí: "+ phong.getViTri() +"</p></html>"));
        pn_p_top.add(new JLabel("<html><p style='padding-left: 10px;'>Số giường: "+ phong.getSoGiuong() +"</p></html>"));
        pn_p_top.add(new JLabel("<html><p style='padding-left: 10px;'>Tình trạng: "+ tinhTrang +"</p></html>"));
        pn_p_top.add(new JLabel("<html><p style='padding-left: 10px;'>Loại phòng: "+ phong.getLoaiPhong().getTenLoaiPhong() +"</p></html>"));
        pn_p_top.add(new JLabel("<html><p style='padding-left: 10px;'>Đơn giá: "+ phong.getLoaiPhong().getDonGia() +"</p></html>"));

        JPanel pn_p_c = new JPanel();
        pn_p_main.add(pn_p_c);
        JPanel pn_p_bottom = new JPanel();
        pn_p_c.add(pn_p_bottom);
        
       
        btn_DatPhong = new JButton("Đặt phòng này", icon_order);
        btn_NhanPhong = new JButton("Nhận phòng", icon_checkin);
        btn_ThanhToan = new JButton("Trả phòng", icon_pay);
        btn_SuDungDV = new JButton("Sử dụng dịch vụ", icon_add);
        btn_XemLichDat = new JButton("Xem lịch đặt", icon_order);
        if(phong.getTinhTrang() == 2){ // đang ở
            pn_p_bottom.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 0;
            c.insets = new Insets(5, 5, 5, 5);

            pn_p_bottom.add(btn_ThanhToan, c);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 1;
            c.gridy = 0;
            c.insets = new Insets(5, 5, 5, 5);
            pn_p_bottom.add(btn_DatPhong, c);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 1;
            c.gridwidth = 2;
            c.insets = new Insets(5, 5, 5, 5);
            pn_p_bottom.add(btn_XemLichDat, c);
            
        }else if(phong.getTinhTrang() == 1){ // đã đặt
            pn_p_bottom.add(btn_DatPhong);
            pn_p_bottom.add(btn_XemLichDat);
            
        }else{// trống
            pn_p_bottom.add(btn_DatPhong);
            pn_p_bottom.add(btn_XemLichDat);
        }
        popup.setVisible(true);
        
        btn_DatPhong.addActionListener(e -> {
        	popup.setVisible(false);
        	DatPhong_UI pgDatPhong = new DatPhong_UI();
        	pgDatPhong.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        	pgDatPhong.setVisible(true);
        	pgDatPhong.setPhong(phong.getMaPhong());
        });
        
        btn_XemLichDat.addActionListener(e -> {
        	popup.setVisible(false);
        	DialogLichDatPhong dialog = new DialogLichDatPhong();
        	dialog.setMaPhong(phong.getMaPhong());
        	dialog.renderData();
        	dialog.setVisible(true);
        });
        
        btn_ThanhToan.addActionListener(e -> {
        	popup.setVisible(false);
        	HoaDonPhong hdp = null;
        	HoaDonDV hddv = null;
        	
        	try {
				hdp = client.getHoaDonPhongDao().getHDPbyMaPhong(phong.getMaPhong());
			} catch (RemoteException | MalformedURLException | NotBoundException e1) {
				e1.printStackTrace();
			}
        	
        	try {
				hddv = client.getHoaDonDVDao().getHDDVChuaThanhToanByMaKH(hdp.getKhachHang().getMaKH());
			} catch (RemoteException | MalformedURLException | NotBoundException e1) {
				e1.printStackTrace();
			}
        	
        	
            ThanhToan_UI pageThanhToan = new ThanhToan_UI(hdp, hddv);
            pageThanhToan.renderData();
            pageThanhToan.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            pageThanhToan.setVisible(true);
        });
        
    }


    public JLabel space(int w, int h){
        JLabel space = new JLabel("");
        space.setBorder(BorderFactory.createEmptyBorder(h/2, w/2, h/2, w/2));
        return space;
    }
    
    public JPanel getContentPane() {
		return contentPane;
    }

}
