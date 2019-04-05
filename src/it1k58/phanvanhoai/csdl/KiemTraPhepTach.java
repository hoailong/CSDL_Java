package it1k58.phanvanhoai.csdl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KiemTraPhepTach {
	public static boolean matMatThongTin(List<PhuThuocHam> list, String u) {
		Scanner sc = new Scanner(System.in);
		boolean matMat = true;
		int n; //so so do
		int m; //so thuoc tinh R
		String r = u;
		for (PhuThuocHam pth : list) {
			r += pth.getVePhai() + pth.getVeTrai();
		}
		r = CongCu.chuanHoaChuoi(r);
		m = r.length();
		
		System.out.println("R = " + r);
		
		//nhap du lieu
		System.out.print("So quan he con: ");
		n = Integer.parseInt(sc.nextLine());
		String[] arr = new String[n];
		
		for (int i = 0 ; i < n; i++) {
			System.out.print("+R" + (i+1) + " : ");
			arr[i] = sc.nextLine();
		}
		
		//nhap san du lieu
/*		n = 5;
		String[] arr = new String[n];
		arr[0] = "AC";
		arr[1] = "CD";
		arr[2] = "BE";
		arr[3] = "BCD";
		arr[4] = "AE";*/
		
		
		//tach ve phai PTH
		List<PhuThuocHam> listPTH = new ArrayList<PhuThuocHam>(); 
		
		for (int i = 0; i < list.size(); i++) {
			PhuThuocHam pth = list.get(i);
			for (int j = 0; j < pth.getVePhai().length(); j++) {
				listPTH.add(new PhuThuocHam(pth.getVeTrai(),Character.toString(pth.getVePhai().charAt(j))));
			}
		}
		
		char[][] bangQuanHe1 = new char[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i].indexOf(r.charAt(j)) != -1) {
					bangQuanHe1[i][j] = '*';
				}
				else {
					bangQuanHe1[i][j] = ' ';
				}
			}
		}
		
		// in bang trc khi lam bang
		System.out.println("-Bang truoc khi lam bang:");
		System.out.printf("%-10s  ","");
		for (int i = 0; i < m; i++) System.out.printf("%-5c",r.charAt(i));
		System.out.println();
		for (int i = 0; i < n; i++) {
			System.out.printf("%-9s%1c  ","R"+(i+1)+"("+arr[i]+")",'|');
			for (int j = 0; j < m; j++) {
				System.out.printf("%-5c",bangQuanHe1[i][j]);
			}
			System.out.println();
		}
		
		boolean tiepTucXet;
		//Lặp lại vòng lặp sau đây cho đến khi nào việc thực hiện vòng lặp không làm thay đổi S
		do {
			tiepTucXet = false;
			char[][] bangQuanHe0 = new char[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					bangQuanHe0[i][j] = bangQuanHe1[i][j];
				}
			}
			for (PhuThuocHam pth : listPTH) {
				if (kiemTraHopLe(bangQuanHe0, m, n, r, pth.getVePhai(), pth.getVeTrai())) {
					for (Integer i : viTriLamBang(bangQuanHe0, n, r, pth.getVeTrai())) {
						if (bangQuanHe1[i][r.indexOf(pth.getVePhai())] == ' ') {
							bangQuanHe1[i][r.indexOf(pth.getVePhai())] = '*';
							tiepTucXet = true;
						}

					}
					if (kiemTraHoanThanh(bangQuanHe1, m, n)) {
						matMat = false;
						tiepTucXet = false;
						break;
					}
				}
			}
		} while (tiepTucXet);
		
		//in bang sau khi lam bang
		System.out.println("\n-Bang sau khi lam bang:");
		System.out.printf("%-10s  ","");
		for (int i = 0; i < m; i++) System.out.printf("%-5c",r.charAt(i));
		System.out.println();
		for (int i = 0; i < n; i++) {
			System.out.printf("%-9s%1c  ","R"+(i+1)+"("+arr[i]+")",'|');
			for (int j = 0; j < m; j++) {
				System.out.printf("%-5c",bangQuanHe1[i][j]);
			}
			System.out.println();
		}
		return matMat;
	}

	
	private static boolean kiemTraHopLe(char[][] bangQuanHe, int m, int n, String r, String vePhai, String veTrai) {
		//DK 1: cot ve trai phai co >= 2 dau *
		//DK 2: cot ve trai phai va cot ve phai phai co it nhat 1 hang co cung dau *

		if (veTrai.length() == 1) {
			boolean dk2 = false;
			int dem = 0;
			int chiSoVeTrai = r.indexOf(veTrai);
			int chiSoVePhai = r.indexOf(vePhai);
			for (int i = 0; i < n; i++) {
				if (bangQuanHe[i][chiSoVeTrai] == '*') {
					dem++;
					if (bangQuanHe[i][chiSoVePhai] == '*') dk2 = true;
					if (dem >= 2 && dk2) {
						return true;
					}
				}
			}
			
			return false;
		}		
		//neu veTrai co nhieu thuoc tinh thi dung de qui kiem tra hop le cua ve trai
		else {
			return kiemTraHopLe(bangQuanHe, m, n, r, veTrai.substring(veTrai.length()-1), veTrai.substring(0, veTrai.length()-1));
		}
	}

	private static List<Integer> viTriLamBang(char[][] bangQuanHe, int n, String r, String veTrai) {
		List<Integer> viTriLamBang = new ArrayList<Integer>();
		if (veTrai.length() == 1) {
			int chiSoVeTrai = r.indexOf(veTrai);
			for (int i = 0; i < n; i++) {
				if (bangQuanHe[i][chiSoVeTrai] == '*') {
					viTriLamBang.add(i);
				}
			}
			return viTriLamBang;
		}
		else {
			return giaTriChung(viTriLamBang(bangQuanHe, n, r, veTrai.substring(veTrai.length()-1)),viTriLamBang(bangQuanHe, n, r, veTrai.substring(0, veTrai.length()-1)));
		}
	}

	private static List<Integer> giaTriChung(List<Integer> list1, List<Integer> list2) {
		List<Integer> list = new ArrayList<Integer>();
		for (Integer i : list1) {
			if (list2.contains(i)) list.add(i);
		}
		return list;
	}
	
	private static boolean kiemTraHoanThanh(char[][] bangQuanHe, int m, int n) {
		for (int i = 0; i < n; i++) {
			boolean check = true;
			for (int j = 0; j < m; j++) {
				if (bangQuanHe[i][j] == ' ') {
					check = false;
					break;
				}
			}
			if (check) return true;
		}
		return false;
	}
	
	
}
