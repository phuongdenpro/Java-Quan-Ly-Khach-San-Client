package gui;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

import app.Client;
import dao.ChiTietDVDao;
import dao.ChiTietHoaDonPhongDao;
import dao.DichVuDao;
import dao.KhachHangDao;
import dao.PhongDao;
import model.ChiTietDV;
import model.ChiTietHoaDonPhong;
import model.KhachHang;
import model.Phong;
import utils.Currency;

/**
 *
 * @author TuanNA
 */
public class BieuDoThongKe_UI extends ApplicationFrame {
	private Client client;
	private static List<ChiTietHoaDonPhong> dshdp;
	private static List<Phong> dsp = new ArrayList<Phong>();
	
	public BieuDoThongKe_UI(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}
	private static CategoryDataset createDataset() throws MalformedURLException, RemoteException, NotBoundException, IOException {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		PhongDao phongDao = new Client().getPhongDao();
		ChiTietHoaDonPhongDao cthoaDonPhongDao = new Client().getChiTietHoaDonPhongDao();
		
		try {
			dsp = phongDao.getListPhong();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (int j = 0; j < dsp.size(); j++) {
			Phong phong = dsp.get(j);
			try {
				dshdp = cthoaDonPhongDao.getListChiTietHDPByMaPhong(phong.getMaPhong());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			double tongTien = 0;
			for (ChiTietHoaDonPhong ds : dshdp) {
				int soNgay = (int) utils.Ngay.tinhKhoangNgay(ds.getHoaDonPhong().getNgayGioNhan(),
						ds.getHoaDonPhong().getNgayGioTra());
				tongTien += ds.getPhong().getLoaiPhong().getDonGia() * soNgay;
			}	
			dataset.addValue(tongTien, "Doanh thu", phong.getMaPhong());
		}
		;
        
        
        return dataset;
    }
	public static JFreeChart createChart() throws MalformedURLException, RemoteException, NotBoundException, IOException {
        JFreeChart barChart = ChartFactory.createBarChart(
                "BIỂU ĐỒ TỔNG DOANH THU THEO PHÒNG",
                "Mã phòng", "Doanh thu",
                createDataset(), PlotOrientation.VERTICAL, false, false, false);
        return barChart;
    }

    

    public void renderGUI() throws MalformedURLException, RemoteException, NotBoundException, IOException {
        ChartPanel chartPanel = new ChartPanel(createChart());
        chartPanel.setPreferredSize(new java.awt.Dimension(960, 600));
        JFrame frame = new JFrame();
        frame.add(chartPanel);
        frame.setTitle("Biểu đồ thống kê tổng doanh thu theo phòng");
        frame.setSize(1000, 650);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException, IOException {
		BieuDoThongKe_UI gui = new BieuDoThongKe_UI("");
		gui.renderGUI();
	}
   

		

}