package it1k58.phanvanhoai.csdl;

import java.util.List;

public class TimMotKhoa {
	public static String timMotKhoa(List<PhuThuocHam> list, String u) {
		String khoa = u;
		
		int index = 0;
		while (index < khoa.length()) {
			String chuoiSauKhiBo = CongCu.catChuoi(khoa, index);
			if  (u.equals(TimBaoDong.timBaoDong(list, chuoiSauKhiBo))) {
				khoa = chuoiSauKhiBo;
			}
			else {
				index++;
			}
		}
		
		return khoa;
	}
}
