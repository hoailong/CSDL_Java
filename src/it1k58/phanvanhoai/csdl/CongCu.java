package it1k58.phanvanhoai.csdl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CongCu {
	//ham tim kiem Y co thuoc X+ khong
	public static boolean thuoc(String x, String y) {
		for (int i = 0; i < y.length(); i++) {
			if (x.indexOf(y.charAt(i)) == -1) {
				return false;
			}
		}
		return true;
	}
	
	//ham tim khoaX co chua khoa nao trong listKhoa khong
	public static boolean kiemTraKhoaTonTai(String khoaX, List<String> listKhoa) {
		for (String khoaThuI : listKhoa) {
			if (CongCu.thuoc(khoaX, khoaThuI)) return true;
		}
		return false;
	}
	
	//ham loai bo ky tu trung, sap xep 1 chuoi theo thu tu A->B->C
	public static String chuanHoaChuoi(String s) {
		String result = "";
		Set<Character> chuoiKyTu = new HashSet<>();
		for (int i = 0; i < s.length(); i++) {
			chuoiKyTu.add(s.charAt(i));
		}
		for (Character ch : chuoiKyTu) {
			result+= ch;
		}
		return result;
	}
	
	//ham tim chuoi s moi tu chuoi s cu loai bo ky tu o vi tri viTri 
	public static String catChuoi(String s, int viTri) {
		return s.substring(0, viTri) + s.substring(viTri+1, s.length());
	}
	
	//ham tim list chuoi con tu chuoi s bang phuong phap duong chay nhi phan
	public static List<String> chuoiCon(String s) {
		List<String> list = new ArrayList<String>();
		int n = s.length();
		for (int i = 0; i < Math.pow(2, n); i++) {
			String x1 = Integer.toBinaryString(i);
			while (x1.length() < n) x1 = '0' + x1;
			String s1 = "";
			for (int j = x1.length()-1; j >= 0; j--) {
				if (x1.charAt(j) == '1') {
					s1+= s.charAt(j);
				}
			}
			list.add(s1);
		}
		return list;
	}
}
