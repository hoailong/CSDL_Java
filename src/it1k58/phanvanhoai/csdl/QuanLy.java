package it1k58.phanvanhoai.csdl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuanLy {
	static Scanner sc = new Scanner(System.in);
	static List<PhuThuocHam> listPTH = new ArrayList<PhuThuocHam>(); 
	static String u;		
	static String x;
	
	public static void main(String[] args) {
		nhapDuLieu();
		inDuLieu();
		//
		System.out.println("\n-Tim bao dong: ");
		System.out.println(x + "+ = {" + TimBaoDong.timBaoDong(listPTH, x) + "}");
		//
		System.out.println("\n-Tim phu toi thieu:");
		TimPhuToiThieu.inPhuToiThieu(TimPhuToiThieu.phuToithieu(listPTH));
		//
		System.out.println("\n-Tim mot khoa:");
		System.out.println("K = " + TimMotKhoa.timMotKhoa(listPTH, u));
		//
		System.out.println("\n-Tim tat ca khoa: ");
		for (String khoa : TimTatCaKhoa.tatCaKhoa(listPTH, u)) {
			System.out.print(khoa + " ");
		}
		System.out.println();
		//
		System.out.println("\n-Ban co muon kiem tra phep tach khong mat thong tin ? (1 = co - 0 = khong) :");
		if (Integer.parseInt(sc.nextLine()) == 1) {		
			if (KiemTraPhepTach.matMatThongTin(listPTH, u)) {
				System.out.println("=>> MAT MAT THONG TIN");
			}
			else {
				System.out.println("=>> BAO TOAN THONG TIN");
			}
		}
		
	}
	
	private static void nhapDuLieu() {
		//nhap du lieu san
/*		listPTH.add(new PhuThuocHam("B","EC"));
		listPTH.add(new PhuThuocHam("CD","AB"));
		listPTH.add(new PhuThuocHam("AC","BD"));
		listPTH.add(new PhuThuocHam("C","AD"));
		listPTH.add(new PhuThuocHam("BC","AE"));
		u = "ABCDEG";
		x = "AG";*/
		System.out.print("Nhap tap U:");
		u = CongCu.chuanHoaChuoi(sc.nextLine().toUpperCase());
		
		System.out.println("Nhap tap phu thuoc ham, nhap 0 de ket thuc nhap: ");
		int stt = 1;
		while (true) {
			String veTrai;
			String vePhai;
			System.out.print(stt + ".Ve trai: ");
			veTrai = sc.nextLine().toUpperCase();
			if(veTrai.equals("0")) break; // neu nhap 0 thi ket thuc nhap
			System.out.print("  Ve phai: ");
			vePhai = sc.nextLine().toUpperCase();
			listPTH.add(new PhuThuocHam(veTrai, vePhai));
			stt++;
		}
		
		System.out.print("Nhap thuoc tinh X: ");
		x = sc.nextLine().toUpperCase();
	}
	
	private static void inDuLieu() {
		System.out.println("===============================");
		System.out.println("U = {" + u + "}");
		
		System.out.print("F = {");
		for (int i = 0; i < listPTH.size()-1; i++) {
			System.out.print(listPTH.get(i).getVeTrai() + " -> " + listPTH.get(i).getVePhai() + ", ");
		}
		System.out.println(listPTH.get(listPTH.size()-1).getVeTrai() + " -> " + listPTH.get(listPTH.size()-1).getVePhai() + "}");
	}

	
}
