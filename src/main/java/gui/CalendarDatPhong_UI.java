
package gui;

import javax.swing.*;
import javax.swing.event.*;

import app.Client;
import model.HoaDonPhong;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.*;
import java.util.List;

public class CalendarDatPhong_UI extends JDialog implements ActionListener, ChangeListener {
    private int width = 1300, heightPn = 700, widthPn = width - 20;
    private JButton[] button = new JButton[49];
    private int[] state = new int[49];
    private String day = "";
    private int month = Calendar.getInstance().get(Calendar.MONTH);
    private int year = Calendar.getInstance().get(Calendar.YEAR);
    private JButton btnPre, btnNext, btnCancel, btnSubmit;
    private SpinnerNumberModel spinYearModel;
    private JSpinner spinYear;
    private int check = 0, viTri = -1;
    private JLabel lbMonth, lbYear, lbThu, lbNgayThang, lbToDay;
    private String blueColor = "#3f51b5";
    private String whiteColor = "#fafafa";
    private JPanel panel_1;
    private JPanel panel_2;
    private JPanel panel_3;
    private JLabel lbllMaHD;
    private JLabel lblMaHD;
    private JPanel panel_4;
    private JLabel lblTnKhchHng;
    private JLabel lblTenKH;
    private JPanel panel_5;
    private JLabel lblSinThoi;
    private JLabel lblSDT;
    private JPanel panel_6;
    private JLabel lblaCh;
    private JLabel lblDiaChi;
    private JPanel panel_7;
    private JLabel lblNgyn;
    private JLabel lblTuNgay;
    private JPanel panel_8;
    private JLabel lblNgyi;
    private JLabel lblDenNgay;
    private JPanel panel_9;
    private JLabel lblTnhTrng;
    private JLabel lblTinhTrang;
    private JLabel lblmaPhong;
    
    private String maPhong = "P102";
	private List<HoaDonPhong> dshdp = new ArrayList<HoaDonPhong>();
	private Client client;
	private int idxStart;
	private Calendar cal;

    public CalendarDatPhong_UI() {
    	try {
			client = new Client();
		} catch (IOException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        setTitle("Chọn ngày");
        setSize(1300, 700);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        createFormDatePicker();
    }

    public void createFormDatePicker() {
        JPanel pnMain = new JPanel();
        pnMain.setBounds(0, 0, widthPn, heightPn);
        pnMain.setBackground(Color.decode(whiteColor));
        pnMain.setLayout(null);

        JPanel pnShowTime = new JPanel();
        pnShowTime.setBounds(0, 0, 277, 661);
        pnShowTime.setBackground(Color.decode(blueColor));
        pnMain.add(pnShowTime);
        pnShowTime.setLayout(null);

        lbYear = new JLabel("year");
        lbYear.setFont(new Font("Dialog", Font.BOLD, 15));
        lbYear.setBounds(12, 12, 107, 16);
        lbYear.setForeground(Color.decode("#aeb5df"));
        pnShowTime.add(lbYear);

        lbThu = new JLabel("thứ");
        lbThu.setFont(new Font("Dialog", Font.BOLD, 18));
        lbThu.setBounds(12, 40, 107, 25);
        lbThu.setForeground(Color.WHITE);
        pnShowTime.add(lbThu);

        lbNgayThang = new JLabel("tháng ngày");
        lbNgayThang.setFont(new Font("Dialog", Font.BOLD, 18));
        lbNgayThang.setBounds(12, 68, 107, 25);
        lbNgayThang.setForeground(Color.WHITE);
        pnShowTime.add(lbNgayThang);
        
        JPanel panel_10 = new JPanel();
        panel_10.setBounds(0, 128, 277, 43);
        panel_10.setBackground(Color.decode(blueColor));
        pnShowTime.add(panel_10);
        
        lblmaPhong = new JLabel("Lịch đặt phòng P102");
        lblmaPhong.setHorizontalAlignment(SwingConstants.CENTER);
        lblmaPhong.setForeground(Color.WHITE);
        lblmaPhong.setFont(new Font("Tahoma", Font.PLAIN, 20));
        panel_10.add(lblmaPhong);
        
        JPanel panel = new JPanel();
        
        panel.setBounds(12, 196, 255, 252);
        panel.setBackground(Color.decode(blueColor));
        pnShowTime.add(panel);
        panel.setLayout(new BorderLayout(0, 0));
        
        JLabel lblNewLabel = new JLabel("Thông tin hóa đơn");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        panel.add(lblNewLabel, BorderLayout.NORTH);
        
        panel_1 = new JPanel();
        panel_1.setBackground(Color.decode(blueColor));
        panel.add(panel_1);
        panel.setVisible(false);
        
        panel_2 = new JPanel();
        panel_1.add(panel_2);
        panel_2.setBackground(Color.decode(blueColor));
        panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
        
        panel_3 = new JPanel();
        panel_3.setBackground(Color.decode(blueColor));
        FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        panel_2.add(panel_3);
        
        lbllMaHD = new JLabel("Mã hóa đơn");
        lbllMaHD.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lbllMaHD.setForeground(Color.WHITE);
        lbllMaHD.setPreferredSize(new Dimension(130, 14));
        panel_3.add(lbllMaHD);
        
        lblMaHD = new JLabel("123");
        lblMaHD.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblMaHD.setForeground(Color.WHITE);
        panel_3.add(lblMaHD);
        
        panel_4 = new JPanel();
        FlowLayout flowLayout_1 = (FlowLayout) panel_4.getLayout();
        flowLayout_1.setAlignment(FlowLayout.LEFT);
        panel_4.setBackground(new Color(63, 81, 181));
        panel_2.add(panel_4);
        
        lblTnKhchHng = new JLabel("Tên khách hàng");
        lblTnKhchHng.setPreferredSize(new Dimension(130, 14));
        lblTnKhchHng.setForeground(Color.WHITE);
        lblTnKhchHng.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel_4.add(lblTnKhchHng);
        
        lblTenKH = new JLabel("Trần Văn Nhân");
        lblTenKH.setForeground(Color.WHITE);
        lblTenKH.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel_4.add(lblTenKH);
        
        panel_5 = new JPanel();
        FlowLayout flowLayout_2 = (FlowLayout) panel_5.getLayout();
        flowLayout_2.setAlignment(FlowLayout.LEFT);
        panel_5.setBackground(new Color(63, 81, 181));
        panel_2.add(panel_5);
        
        lblSinThoi = new JLabel("Số điện thoại");
        lblSinThoi.setPreferredSize(new Dimension(130, 14));
        lblSinThoi.setForeground(Color.WHITE);
        lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel_5.add(lblSinThoi);
        
        lblSDT = new JLabel("0987654321");
        lblSDT.setForeground(Color.WHITE);
        lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel_5.add(lblSDT);
        
        panel_6 = new JPanel();
        FlowLayout flowLayout_3 = (FlowLayout) panel_6.getLayout();
        flowLayout_3.setAlignment(FlowLayout.LEFT);
        panel_6.setBackground(new Color(63, 81, 181));
        panel_2.add(panel_6);
        
        lblaCh = new JLabel("Địa chỉ");
        lblaCh.setPreferredSize(new Dimension(130, 14));
        lblaCh.setForeground(Color.WHITE);
        lblaCh.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel_6.add(lblaCh);
        
        lblDiaChi = new JLabel("Bình chiểu");
        lblDiaChi.setForeground(Color.WHITE);
        lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel_6.add(lblDiaChi);
        
        panel_7 = new JPanel();
        FlowLayout flowLayout_4 = (FlowLayout) panel_7.getLayout();
        flowLayout_4.setAlignment(FlowLayout.LEFT);
        panel_7.setBackground(new Color(63, 81, 181));
        panel_2.add(panel_7);
        
        lblNgyn = new JLabel("Ngày đến");
        lblNgyn.setPreferredSize(new Dimension(130, 14));
        lblNgyn.setForeground(Color.WHITE);
        lblNgyn.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel_7.add(lblNgyn);
        
        lblTuNgay = new JLabel("23/11/2021");
        lblTuNgay.setForeground(Color.WHITE);
        lblTuNgay.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel_7.add(lblTuNgay);
        
        panel_8 = new JPanel();
        FlowLayout flowLayout_5 = (FlowLayout) panel_8.getLayout();
        flowLayout_5.setAlignment(FlowLayout.LEFT);
        panel_8.setBackground(new Color(63, 81, 181));
        panel_2.add(panel_8);
        
        lblNgyi = new JLabel("Ngày đi");
        lblNgyi.setPreferredSize(new Dimension(130, 14));
        lblNgyi.setForeground(Color.WHITE);
        lblNgyi.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel_8.add(lblNgyi);
        
        lblDenNgay = new JLabel("23/11/2021");
        lblDenNgay.setForeground(Color.WHITE);
        lblDenNgay.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel_8.add(lblDenNgay);
        
        panel_9 = new JPanel();
        FlowLayout flowLayout_6 = (FlowLayout) panel_9.getLayout();
        flowLayout_6.setAlignment(FlowLayout.LEFT);
        panel_9.setBackground(new Color(63, 81, 181));
        panel_2.add(panel_9);
        
        lblTnhTrng = new JLabel("Tình trạng");
        lblTnhTrng.setPreferredSize(new Dimension(130, 14));
        lblTnhTrng.setForeground(Color.WHITE);
        lblTnhTrng.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel_9.add(lblTnhTrng);
        
        lblTinhTrang = new JLabel("Chưa nhận phòng");
        lblTinhTrang.setForeground(Color.WHITE);
        lblTinhTrang.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel_9.add(lblTinhTrang);

        String[] header = { "Chủ nhật", "Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7" };
        JPanel pnDateTable = new JPanel(new GridLayout(7, 7));
        pnDateTable.setBackground(Color.decode(whiteColor));

        for (int i = 0; i < button.length; i++) {
            final int selection = i;
            button[i] = new JButton();
            button[i].setFocusPainted(false);
            button[i].setBackground(Color.WHITE);
            if (i < 7) {
                button[i].setText(header[i]);
                button[i].setEnabled(false);
                button[i].setForeground(Color.decode(blueColor));
            } else {
                button[i].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        day = button[selection].getActionCommand();
                        if (viTri != -1) {
                        	if(state[viTri] == 2) { // dang o
                				button[viTri].setBackground(Color.RED);
                				button[viTri].setForeground(Color.WHITE);
                			}else if(state[viTri] == 1){ // chua nhan phong
                				button[viTri].setBackground(Color.YELLOW);
                				button[viTri].setForeground(Color.BLACK);
                			}else {
	                            button[viTri].setBackground(Color.decode(whiteColor));
	                            button[viTri].setForeground(Color.BLACK);
                			}
                        }
                        viTri = selection;
                        button[selection].setBackground(Color.decode(blueColor));
                        button[selection].setForeground(Color.decode(whiteColor));
                        int day = Integer.parseInt(button[selection].getText());
                        displayShowDate(day);
                    }
                });
            }
            button[i].setBorder(null);
            button[i].setPreferredSize(new Dimension(20, 20));
            button[i].setBackground(Color.decode(whiteColor));
            pnDateTable.add(button[i]);
        }

        JPanel pnBtn = new JPanel();
        pnBtn.setLayout(null);
        pnBtn.setBackground(Color.decode(whiteColor));

        btnPre = new JButton("<");
        btnNext = new JButton(">");

        spinYearModel = new SpinnerNumberModel(year, 1900, null, 1);
        spinYear = new JSpinner(spinYearModel);
        lbMonth = new JLabel("tháng");
        lbMonth.setHorizontalAlignment(SwingConstants.CENTER);
        lbMonth.setFont(new Font("Dialog", Font.BOLD, 14));

        int h = 25;
        btnPre.setBounds(339, 2, 41, h);
        btnPre.setBackground(Color.decode(whiteColor));
        btnPre.setForeground(Color.decode(blueColor));
        btnPre.setBorder(null);
        lbMonth.setBounds(390, 2, 175, 25);
        spinYear.setBounds(563, 2, 70, h);
        btnNext.setBounds(685, 0, 41, h);
        btnNext.setBackground(Color.decode(whiteColor));
        btnNext.setForeground(Color.decode(blueColor));
        btnNext.setBorder(null);

        pnBtn.setBounds(276, 0, 1008, 30);
        pnDateTable.setBounds(276, 30, 1008, 590);

        pnBtn.add(btnPre);
        pnBtn.add(lbMonth);
        pnBtn.add(spinYear);
        pnBtn.add(btnNext);

        pnMain.add(pnBtn);
        pnMain.add(pnDateTable);

        JPanel pnSubmit = new JPanel();
        pnSubmit.setBackground(Color.decode(whiteColor));
        pnSubmit.setLayout(null);
        pnSubmit.setBounds(276, 631, 1008, 30);
        pnMain.add(pnSubmit);

        btnSubmit = new JButton("OK");
        btnSubmit.setBounds(900, 0, 60, 26);
        btnSubmit.setBackground(Color.decode(whiteColor));
        btnSubmit.setForeground(Color.decode(blueColor));
        btnSubmit.setBorder(null);
        pnSubmit.add(btnSubmit);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(800, 0, 74, 26);
        btnCancel.setBackground(Color.decode(whiteColor));
        btnCancel.setForeground(Color.decode(blueColor));
        btnCancel.setBorder(null);
        pnSubmit.add(btnCancel);

        lbToDay = new JLabel("Today: ");
        lbToDay.setBounds(10, 6, 162, 14);
        pnSubmit.add(lbToDay);

        displayDate();
        showToDay();
        getContentPane().add(pnMain);

        btnNext.addActionListener(this);
        btnPre.addActionListener(this);
        btnSubmit.addActionListener(this);
        btnCancel.addActionListener(this);
        spinYear.addChangeListener(this);
    }

    public static void main(String[] args) {
        new CalendarDatPhong_UI().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnPre)) {
            month--;
            displayDate();
        } else if (o.equals(btnNext)) {
            month++;
            displayDate();
        } else if (o.equals(btnSubmit)) {
            check = 1;
            dispose();
        } else if (o.equals(btnCancel)) {
            check = 0;
            dispose();
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Object o = e.getSource();
        if (o.equals(spinYear)) {
            displayDate();
        }
    }

    // thay đổi lịch theo tháng năm
    public void displayDate() {
        for (int i = 7; i < button.length; i++)
            button[i].setText("");
        SimpleDateFormat sdfMonth = new SimpleDateFormat("MMMM");
        cal = Calendar.getInstance();
        int y = (int) spinYear.getValue();
        if (y != year)
            year = y;

        cal.set(year, month, 1);

        lbMonth.setText(sdfMonth.format(cal.getTime()));
        spinYear.setValue(year);

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 0; i < 6 + dayOfWeek; i++) {
            button[i].setEnabled(false);
        }
        for (int i = 6 + dayOfWeek, day = 1; day <= daysInMonth; i++, day++) {
            button[i].setText("" + day);
            button[i].setEnabled(true);
        }
        for (int i = 6 + dayOfWeek + daysInMonth; i < button.length; i++) {
            button[i].setEnabled(false);
        }
        renderData();
    }
    
    public void renderData() {
//    	reset mau
    	for (int i = 7; i < button.length; i++) {
    		state[0] = 0;
    		button[i].setBackground(Color.decode(whiteColor));
            button[i].setForeground(Color.BLACK);
    	}
    	
    	idxStart = 0;
    	for (int i = 0; i < button.length; i++) {
    		if(button[i].getText().equals("1")) {
    			idxStart = i;
    			break;
    		}
    	}
    	try {
			dshdp = client.getHoaDonPhongDao().getListHDPChuaThanhToanByMaPhong(maPhong);
		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			e.printStackTrace();
		}
    	
    	dshdp.forEach(hdp -> {
    		int start = hdp.getNgayGioNhan().getDate() + idxStart - 1;
    		int end = hdp.getNgayGioTra().getDate() + idxStart - 1;
    		
    		if(hdp.getNgayGioNhan().getMonth() < cal.getTime().getMonth())
    			start -= 48;
    		if(hdp.getNgayGioTra().getMonth() > cal.getTime().getMonth())
    			end += 48;
    		
    		System.out.println(cal.getTime().getMonth());
    		System.out.println(hdp.getNgayGioNhan().getMonth() +" "+ hdp.getNgayGioTra().getMonth());
    		
    		System.out.println(cal.getTime().getMonth() < hdp.getNgayGioNhan().getMonth() 
    				|| cal.getTime().getMonth() > hdp.getNgayGioTra().getMonth());
    		if(cal.getTime().getMonth() < hdp.getNgayGioNhan().getMonth() 
    				|| cal.getTime().getMonth() > hdp.getNgayGioTra().getMonth()) {
    			
    		}else {
	    		for(int i= Math.max(start, 7); i<=Math.min(end, 48); i++) {
	    			
	    			if(hdp.getTinhTrang() == 1) { // dang o
	    				state[i] = 2;
	    				button[i].setBackground(Color.RED);
	    				button[i].setForeground(Color.WHITE);
	    			}else { // chua nhan phong
	    				state[i] = 1;
	    				button[i].setBackground(Color.YELLOW);
	    				button[i].setForeground(Color.BLACK);
	    			}
	    		}
    		}
    	});
    	
    	int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
    	int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    	for (int i = 7; i < idxStart; i++) {
    		button[i].setBackground(Color.decode(whiteColor));
            button[i].setForeground(Color.BLACK);
        }
    	for (int i = 6 + dayOfWeek + daysInMonth; i < button.length; i++) {
    		button[i].setBackground(Color.decode(whiteColor));
            button[i].setForeground(Color.BLACK);
        }
    }

    // hiện ngày hiện tại
    public void showToDay() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd");
        lbNgayThang.setText(sdf.format(cal.getTime()));
        sdf = new SimpleDateFormat("E");

        lbThu.setText(sdf.format(cal.getTime()) + ",");
        sdf = new SimpleDateFormat("yyyy");

        lbYear.setText(sdf.format(cal.getTime()));
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        lbToDay.setText("Today: " + sdf.format(cal.getTime()));
    }

    // hiện ngày đã chọn
    public void displayShowDate(int day) {
        Calendar cal = Calendar.getInstance();
        int y = (int) spinYear.getValue();
        if (y != year)
            year = y;
        cal.set(year, month, day);
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd");
        lbNgayThang.setText(sdf.format(cal.getTime()));

        sdf = new SimpleDateFormat("E");
        lbThu.setText(sdf.format(cal.getTime()) + ",");

        sdf = new SimpleDateFormat("yyyy");
        lbYear.setText(sdf.format(cal.getTime()));
    }

    // lấy ngày chọn từ lịch
    private String getPickedDate() {
        if (day.equals(""))
            return day;
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        cal.set(year, month, Integer.parseInt(day));
        return sdf.format(cal.getTime());
    }

    // lấy ngày chọn từ lịch
    public Date getDate() {
        if (day.equals(""))
            day = "0";
        Calendar cal = Calendar.getInstance();
        int date = Integer.parseInt(day);
        cal.set(year, month, date);
        return (Date) cal.getTime();
    }

    // lấy ngày hiện tại dạng string
    public static String getToDay() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(cal.getTimeInMillis());
    }

    // trả về ngày được chọn
    public String getValueString() {
        String re = "";
        if (check == 1)
            re = getPickedDate();
        return re;
    }
    
    public void setMaPhong(String maPhong) {
    	this.maPhong = maPhong;
    	lblmaPhong.setText("Lịch đặt phòng "+maPhong);
    	renderData();
    }
}
