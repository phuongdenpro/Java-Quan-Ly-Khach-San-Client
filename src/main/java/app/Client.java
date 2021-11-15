package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ChiTietDVDao;
import dao.ChiTietHoaDonPhongDao;
import dao.DichVuDao;
import dao.HoaDonDVDao;
import dao.HoaDonPhongDao;
import dao.KhachHangDao;
import dao.LoaiPhongDao;
import dao.PhongDao;
import model.HoaDonPhong;
import model.LoaiPhong;
import model.Phong;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextArea;
import java.awt.Color;

public class Client{
	private String ip = "localhost";
	private int port = 9991;
	private ChiTietDVDao chiTietDVDao;
	private DichVuDao dichVuDao;
	private HoaDonDVDao hoaDonDVDao;
	private HoaDonPhongDao hoaDonPhongDao;
	private KhachHangDao khachHangDao;
	private LoaiPhongDao loaiPhongDao;
	private PhongDao phongDao;
	private ChiTietHoaDonPhongDao chiTietHDPDao;
	
	public static void main(String[] args) throws IOException, NotBoundException {
		Client client = new Client();
				
	}

	public Client() throws IOException, NotBoundException {
		SecurityManager securityManager = System.getSecurityManager();
		if(securityManager == null) {
			System.setProperty("java.security.policy", "rmi/policy.policy");
			System.setSecurityManager(new SecurityManager());
		}
		
//		loaiPhongDao = (LoaiPhongDao) Naming.lookup("rmi://"+ ip +":"+ port +"/LoaiPhong");
//		System.out.println(loaiPhongDao.test());
	}

	public ChiTietDVDao getChiTietDVDao() throws MalformedURLException, RemoteException, NotBoundException {
		if(chiTietDVDao == null)
			chiTietDVDao = (ChiTietDVDao) Naming.lookup("rmi://"+ ip +":"+ port +"/ChiTietDV");  
		return this.chiTietDVDao; 
	}
	
	public ChiTietHoaDonPhongDao getChiTietHoaDonPhongDao() throws MalformedURLException, RemoteException, NotBoundException {
		if(chiTietHDPDao == null)
			chiTietHDPDao = (ChiTietHoaDonPhongDao) Naming.lookup("rmi://"+ ip +":"+ port +"/chiTietHoaDonPhong");  
		return this.chiTietHDPDao; 
	}
	
	public DichVuDao getDichVuDao() throws MalformedURLException, RemoteException, NotBoundException {
		if(dichVuDao == null)
			dichVuDao = (DichVuDao) Naming.lookup("rmi://"+ ip +":"+ port +"/DichVu");
		return dichVuDao;
	}
	
	public HoaDonDVDao getHoaDonDVDao() throws MalformedURLException, RemoteException, NotBoundException {
		if(hoaDonDVDao == null)
			hoaDonDVDao = (HoaDonDVDao) Naming.lookup("rmi://"+ ip +":"+ port +"/HoaDonDV");
		return hoaDonDVDao;
	}
	
	public HoaDonPhongDao getHoaDonPhongDao() throws MalformedURLException, RemoteException, NotBoundException {
		if(hoaDonPhongDao == null)
			hoaDonPhongDao = (HoaDonPhongDao) Naming.lookup("rmi://"+ ip +":"+ port +"/HoaDonPhong");
		
		return hoaDonPhongDao;
	}
	
	public KhachHangDao getKhachHangDao() throws MalformedURLException, RemoteException, NotBoundException {
		if(khachHangDao == null)
			khachHangDao = (KhachHangDao) Naming.lookup("rmi://"+ ip +":"+ port +"/KhachHang");
		return khachHangDao;
	}
	
	public LoaiPhongDao getLoaiPhongDao() throws MalformedURLException, RemoteException, NotBoundException {
		if(loaiPhongDao == null)
			loaiPhongDao = (LoaiPhongDao) Naming.lookup("rmi://"+ ip +":"+ port +"/LoaiPhong");
		return loaiPhongDao;
	}
	
	public PhongDao getPhongDao() throws MalformedURLException, RemoteException, NotBoundException {
		if(phongDao == null)
			phongDao = (PhongDao) Naming.lookup("rmi://"+ ip +":"+ port +"/Phong");
		return phongDao;
	}

//	public ChiTietDVDao getChiTietDVDao() {
//		return chiTietDVDao;
//	}
//
//	public DichVuDao getDichVuDao() {
//		return dichVuDao;
//	}
//
//	public HoaDonDVDao getHoaDonDVDao() {
//		return hoaDonDVDao;
//	}
//
//	public HoaDonPhongDao getHoaDonPhongDao() {
//		return hoaDonPhongDao;
//	}
//
//	public KhachHangDao getKhachHangDao() {
//		return khachHangDao;
//	}
//
//	public LoaiPhongDao getLoaiPhongDao() {
//		return loaiPhongDao;
//	}
//
//	public PhongDao getPhongDao() {
//		return phongDao;
//	}
	
	
}
