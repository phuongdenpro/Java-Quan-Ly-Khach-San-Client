package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.prefs.NodeChangeEvent;

public class QuanLyKhachSan_UI extends JFrame{
    // thêm các page vô đây cho dễ nhớ

    private String[] nav = new String[] { "Trang chu", "Dat phong", "Quan ly hoa don phong", "Quan ly hoa don dich vu",
            "Quan ly phong", "Quan ly dich vu", "Quan ly nhan vien", "Quan ly khach hang" };
    // index ở đây tương ứng với mảng trên
    public int indx_nav = -1;

    // khai báo các lớp giao diện ở đây
    private TrangChu_UI pageTrangChu = new TrangChu_UI();
    private DatPhong_UI pageDatPhong;
    private ThanhToan_UI pageThanhToan = new ThanhToan_UI();
    private QuanLyDichVu_UI pageQLDichVu = new QuanLyDichVu_UI();
    private QuanLyKhachHang_UI pageQLKhachHang = new QuanLyKhachHang_UI();
    private ThongKeDichVu_UI pageTKeDichVu = new ThongKeDichVu_UI();
    private ThongKeKhachHang_UI pageTKeKhachHang = new ThongKeKhachHang_UI();
    private HoaDonDichVu_UI pageHDDichVu = new HoaDonDichVu_UI();
    private QLPhong_UI pageQLPhong = new QLPhong_UI();
    private QLLoaiPhong_UI pageQLLoaiPhong = new QLLoaiPhong_UI();
    private MauDangNhap_UI pageLogin = new MauDangNhap_UI();

    
    private JPanel contentPane = new JPanel();
    private JFrame popup = new JFrame();
    // components
    private JMenuBar menuBar;
    private JMenu menuTrangChu, menuDatPhong, menuQLHoaDon, menuQLDichVu, menuQLKhachHang, menuQLNhanVien, menuThongKe;
    private JMenuItem itemQLHDDV, itemQLHDDichVu, itemQLPhong, itemQLDichVu;
    private JMenuItem itemTrangChu, itemDatPhong, itemQLKhachHang, itemQLNhanVien, itemThongKeDV, itemThongKeKH;

    // private JPanel pnContainer;
    private ImageIcon icon_quest = new ImageIcon("data/images/question.png");
	private JMenuItem itemQLHD;
	private JMenuItem itemQLLoaiPhong;
	private JMenuItem itemQLDatPhong;
    
    public static void main(String[] args) throws Exception {
        System.out.println("start!");
        new QuanLyKhachSan_UI();
    }
    
    public QuanLyKhachSan_UI() throws IOException, NotBoundException {
    	pageDatPhong = new DatPhong_UI();
    	
//    	setVisible(true);
        setTitle("Quản Lý Khách Sạn");
        setSize(1300, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setResizable(false);

        this.setVisible(false);
        pageLogin.setVisible(true);
        
        handleLogin();
//        createMenuGUI();
//        renderMain(pageLogin.getContentPane(), "dangnhap");

    }


    public void renderMain(JPanel contentPane, String tab) {
		this.remove(this.contentPane);
		this.revalidate();
		this.repaint();
		this.contentPane = contentPane;
		this.setContentPane(contentPane);
		this.revalidate();
		this.repaint();

		System.out.println("-> " + tab);

	}

    

    public void createMenuGUI() {
        menuBar = new JMenuBar();
        // menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.X_AXIS));
        this.setJMenuBar(menuBar);
        // trang chu
        menuTrangChu = new JMenu("Trang chủ");
        menuBar.add(menuTrangChu);
        itemTrangChu = new JMenuItem("Trang chủ");
        menuTrangChu.add(itemTrangChu);

        // trang chu
        menuDatPhong = new JMenu("Đặt phòng");
        menuBar.add(menuDatPhong);
        itemQLDatPhong = new JMenuItem("Quản lý đặt phòng");
        menuDatPhong.add(itemQLDatPhong);
        itemDatPhong = new JMenuItem("Đặt phòng mới");
        menuDatPhong.add(itemDatPhong);

        // quản lý hóa đơn
        menuQLHoaDon = new JMenu("Hóa đơn");
        menuBar.add(menuQLHoaDon);
        itemQLHD = new JMenuItem("Quản lý hóa đơn phòng");
        itemQLHDDichVu = new JMenuItem("Quản lý hóa đơn dịch vụ");
        
        menuQLHoaDon.add(itemQLHD);
        menuQLHoaDon.add(itemQLHDDichVu);

        // quản lý dịch vụ
        menuQLDichVu = new JMenu("Dịch vụ");
        menuBar.add(menuQLDichVu);
        itemQLPhong = new JMenuItem("Quản lý phòng");
        itemQLLoaiPhong = new JMenuItem("Quản lý loại phòng");
        itemQLDichVu = new JMenuItem("Quản lý dịch vụ");
        menuQLDichVu.add(itemQLPhong);
        menuQLDichVu.add(itemQLLoaiPhong);
        menuQLDichVu.add(itemQLDichVu);

        // quản lý khách hàng
        menuQLKhachHang = new JMenu("Khách hàng");
        menuBar.add(menuQLKhachHang);
        itemQLKhachHang = new JMenuItem("Quản lý khách hàng");
        menuQLKhachHang.add(itemQLKhachHang);

        // báo cáo
        menuThongKe = new JMenu("Thống kê");
        menuBar.add(menuThongKe);
        itemThongKeDV = new JMenuItem("Thống kê dịch vụ");
        itemThongKeKH = new JMenuItem("Thống kê khách hàng");
        menuThongKe.add(itemThongKeDV);
        menuThongKe.add(itemThongKeKH);

        // thêm sự kiện click
        itemTrangChu.addActionListener((e) -> {
        	renderMain(pageTrangChu.getContentPane(), "trang chu");
        });
        itemQLDatPhong.addActionListener((e) -> {
//        	renderMain(pageDatPhong.getContentPane(), "dat phong");
        });
        itemDatPhong.addActionListener((e) -> {
        	renderMain(pageDatPhong.getContentPane(), "dat phong");
        });
//        itemQLHDDV.addActionListener(this);
//        itemQLHDDichVu.addActionListener(this);
        itemQLPhong.addActionListener((e) -> {
        	renderMain(pageQLPhong.getContentPane(), "phong");
        });
        itemQLLoaiPhong.addActionListener((e) -> {
        	renderMain(pageQLLoaiPhong.getContentPane(), "loai phong");
        });
//        itemQLDichVu.addActionListener(this);
//        itemQLKhachHang.addActionListener(this);
//        itemThongKeDV.addActionListener(this);
//        itemThongKeKH.addActionListener(this);

    }

    public JLabel space(int w, int h) {
        JLabel space = new JLabel("");
        space.setBorder(BorderFactory.createEmptyBorder(h / 2, w / 2, h / 2, w / 2));
        return space;
    }

    public void addMenu(JPanel pMenu, JButton btn) {
        btn.setBackground(Color.lightGray);
        btn.setBorder(BorderFactory.createEmptyBorder());
        pMenu.add(btn);
        pMenu.add(space(10, 6));
    }



    private void handleEventThayDoiLoaiPhong() {
        pageTrangChu.cboLP.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handleEventTrangChu();
                    }
                }, 1000L); // 300 is the delay in millis
                
            }
        });
    }


    public void handleEventTrangChu(){
        
    }

    private void handleEventTraPhong() {
        
    }

    public void chonHDDV(){
        
        popup.dispose();
        popup = new JFrame();
        popup.setTitle("Chọn hóa đơn dịch vụ");
        popup.setSize(500, 400);
        popup.setResizable(false);
        popup.setLocationRelativeTo(contentPane);
        popup.setAlwaysOnTop(true);
        JPanel pn_p_main = new JPanel();
        popup.add(pn_p_main);
        pn_p_main.setLayout(new BoxLayout(pn_p_main, BoxLayout.Y_AXIS));

        JPanel pn_p_top = new JPanel();
        pn_p_main.add(pn_p_top);
        JLabel lbMaHddv = new JLabel("Mã hóa đơn dịch vụ");
        JTextField txtMaHddv = new JTextField(10);
        JButton btnTimHDDV = new JButton("Tìm kiếm");
        pn_p_top.add(lbMaHddv);
        pn_p_top.add(txtMaHddv);
        pn_p_top.add(btnTimHDDV);

        

        JPanel pn_p_center = new JPanel();
        pn_p_main.add(pn_p_center);
        String[] cols = {"Mã hóa đơn", "Tên khách hàng", "Ngày giờ đặt"};
        DefaultTableModel modelHDDV = new DefaultTableModel(cols, 0);
        JTable tblHDDV = new JTable(modelHDDV);
        pn_p_center.add(new JScrollPane(tblHDDV));

        JPanel pn_p_bottom = new JPanel();
        pn_p_main.add(pn_p_bottom);
        JButton btnOke = new JButton("Đồng ý");
        pn_p_bottom.add(btnOke);
        JButton btnSkip = new JButton("Bỏ qua");
        pn_p_bottom.add(btnSkip);

        
        popup.setVisible(true);


    }

    private void handleLogin() {
        pageLogin.btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(pageLogin.txtPassword.getPassword());
                if(pageLogin.txtUsername.getText().equals("admin") 
                && pageLogin.txtPassword.getText().equals("admin")){
                    setSize(1000, 700);
                    setLocationRelativeTo(null);
                    
                    createMenuGUI();
                    menuBar.setVisible(true);
                    pageLogin.setVisible(false);
                    setVisible(true);
                    
                    renderMain(pageTrangChu.getContentPane(), "trang chu");
                }else{
                    JOptionPane.showMessageDialog(contentPane, "Sai tài khoản hoặc mật khẩu");;
                    pageLogin.txtUsername.requestFocus();
                }
            }
        });

        pageLogin.btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public void print(String msg){
        System.out.println(msg);
    }

    // public void check
    public String currencyFormat(Double vnd){
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        return currencyVN.format(vnd);
    }
}
