package it1k58.phanvanhoai.csdl;

import java.util.ArrayList;
import java.util.List;

public class TimBaoDong {
	public static String timBaoDong(List<PhuThuocHam> list, String x) {
		String xPlus = "";
		List<PhuThuocHam> listPTH = new ArrayList<PhuThuocHam>(); 
		for (PhuThuocHam i : list) {
			listPTH.add(i);
		}
		
		//xu ly
		xPlus = x;
		boolean tiepTucXet; 
		// lap cho den khi chay tu dau den cuoi ma khong co cai nao thuoc X
		do {
			int index = 0;
			tiepTucXet = false;
			while (index < listPTH.size()) {
				//neu ve trai cua Y thuoc X thi them va X va xoa PTH do khoi F
				if (CongCu.thuoc(xPlus, listPTH.get(index).getVeTrai())) {
					xPlus = CongCu.chuanHoaChuoi(xPlus.concat(listPTH.get(index).getVePhai()));
					listPTH.remove(index);
					tiepTucXet = true;
				}
				else index++;
			}
		} while (tiepTucXet == true);
		return xPlus;
	}
}
