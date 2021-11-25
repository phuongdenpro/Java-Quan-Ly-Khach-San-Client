package app;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.util.ArrayList;
import java.util.List;

import dao.HoaDonPhongDao;
import dao.LoaiPhongDao;
import dao.impl.HoaDonPhongImpl;
import dao.impl.KhachHangImpl;
import dao.impl.PhongImpl;
import model.ChiTietHoaDonPhong;
import model.HoaDonPhong;
import model.KhachHang;
import model.LoaiPhong;
import utils.Ngay;



public class App {
	public static void main(String[] args) throws IOException, NotBoundException {
//		Client client = new Client();
//		KhachHang kh = new KhachHangImpl().getKhachHangByMaKH(1);
//		
//		List<ChiTietHoaDonPhong> dscthdp = new ArrayList<ChiTietHoaDonPhong>();
//		dscthdp.add(new ChiTietHoaDonPhong(new PhongImpl().getPhongByMaPhong("P101")));
//		HoaDonPhong hdp = new HoaDonPhong(Ngay.homNay(), Ngay.homNay(), kh, dscthdp);
//		HoaDonPhongDao hoaDonPhongDao = client.getHoaDonPhongDao();
//		System.out.println(hoaDonPhongDao.themHoaDonPhong(hdp));
	}
}
