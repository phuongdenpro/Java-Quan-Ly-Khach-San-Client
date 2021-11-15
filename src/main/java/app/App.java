package app;

import java.io.IOException;
import java.rmi.NotBoundException;

import dao.LoaiPhongDao;
import model.LoaiPhong;

//import dao.LoaiPhongDao;
//import dao.PhongDao;
//import model.LoaiPhong;
//import model.Phong;

public class App {
	public static void main(String[] args) throws IOException, NotBoundException {
		Client client = new Client();
		LoaiPhongDao loaiPhongDao = client.getLoaiPhongDao();
		System.out.println("Connected");
		LoaiPhong lp = new LoaiPhong("VIP", 150000.0);
		System.out.println(loaiPhongDao.themLoaiPhong(lp));
//		System.out.println(loaiPhongDao.xoaLoaiPhong(1));
	}
}
