package gui;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;


public class TrangChu_UI extends JFrame{

    private int countAvail = 0;
    private int countBooking = 0;
    private int countUsing = 0;

    private JPanel pnPhongTrong;
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

    public JButton[] btnPhong;
    public JButton[] btn_ThanhToan;
    public JButton[] btn_DatPhong;
    public JButton[] btn_NhanPhong;
    public JButton[] btn_SuDungDV;
    public JButton[] btn_XemLichDat;
    public JFrame popup = new JFrame();
    private JLabel lbBooking;

    public static void main(String[] args) {
		TrangChu_UI trangChuUI = new TrangChu_UI();
		trangChuUI.setVisible(true);
	}
    
    public TrangChu_UI(){
    	start();
    }

    public void start(){
    	renderGUI();
        renderDSPhong();
        renderLoaiPhong();
    }

    public void renderGUI() {
        // nội dung page trang chủ ở đây
    	setSize(new Dimension(1350, 600));
        contentPane = new JPanel();
        setContentPane(contentPane);
        // pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
        
        // action
        JPanel pn_interact = new JPanel();
        pn_interact.setLayout(new BoxLayout(pn_interact, BoxLayout.Y_AXIS));
        contentPane.add(pn_interact);

        JPanel pnFields = new JPanel();
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
        lbDSP.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbDSP.setFont(fontSize(25));
        pn_sec_available.add(lbDSP);
//        pn_sec_available.add(space(10, 10));

        pnPhongTrong = new JPanel();
        // JScrollPane scroll = new JScrollPane(pnPhongTrong);
        // scroll.setBorder(BorderFactory.createEmptyBorder());
        pn_sec_available.add(pnPhongTrong);
        // pnPhongTrong.setLayout(new BoxLayout(pnPhongTrong, BoxLayout.X_AXIS));
        
        GridLayout grid_Phong = new GridLayout(0, 5);
        grid_Phong.setHgap(10);
        grid_Phong.setVgap(10);
        pnPhongTrong.setLayout(grid_Phong);
        
    }

    public void renderDSPhong(){
        
    }

    public void renderLoaiPhong(){
        
    }


    public Font fontSize(int size){
        return new Font(Font.DIALOG, Font.PLAIN, size);
    }

    public JLabel space(int w, int h){
        JLabel space = new JLabel("");
        space.setBorder(BorderFactory.createEmptyBorder(h/2, w/2, h/2, w/2));
        return space;
    }

}
