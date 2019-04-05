package it1k58.phanvanhoai.csdl;

import java.util.ArrayList;
import java.util.List;

public class TimTatCaKhoa {
	public static List<String> tatCaKhoa(List<PhuThuocHam> list, String u) {
		List<String> listKhoa = new ArrayList<String>();
		String trai = "";
		String phai = "";
		String trungGian = "";
		String goc = "";
		for (PhuThuocHam pth : list) {
			trai = CongCu.chuanHoaChuoi(trai.concat(pth.getVeTrai()));
			phai = CongCu.chuanHoaChuoi(phai.concat(pth.getVePhai()));
		}
		//tim trung gian
		for (int i = 0; i < trai.length(); i++) {
			if (phai.indexOf(trai.charAt(i)) != -1) {
				trungGian+= trai.charAt(i);
			}
		}
		//tim goc
		for (int i = 0; i < u.length(); i++) {
			if (phai.indexOf(u.charAt(i)) == -1) {
				goc+= u.charAt(i);
			}
		}
		//neu trungGian == 0 thi co duy nhat 1 khoa duy nhat la goc
		if (trungGian == "") {
			listKhoa.add(goc);
		}
		else {
			List<String> listTapCon = CongCu.chuoiCon(trungGian);
			for (String s : listTapCon) {
				s = CongCu.chuanHoaChuoi(goc.concat(s));
				//kiem tra xem s co chua khoa nao trong listKhoa chua
				if (!CongCu.kiemTraKhoaTonTai(s, listKhoa) && TimBaoDong.timBaoDong(list, s).equals(u)) {
					listKhoa.add(s);
				}
			}
		}
		return listKhoa;
	}
}
