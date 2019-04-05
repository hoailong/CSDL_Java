package it1k58.phanvanhoai.csdl;

import java.util.ArrayList;
import java.util.List;

public class TimPhuToiThieu {
	public static List<PhuThuocHam> phuToithieu(List<PhuThuocHam> list) {
		
		//tach ve phai PTH
		List<PhuThuocHam> listPTH = new ArrayList<PhuThuocHam>(); 
		
		for (int i = 0; i < list.size(); i++) {
			PhuThuocHam pth = list.get(i);
			for (int j = 0; j < pth.getVePhai().length(); j++) {
				listPTH.add(new PhuThuocHam(pth.getVeTrai(),Character.toString(pth.getVePhai().charAt(j))));
			}
		}
		
		//bo cac thuoc tinh du thua ben trai
		int index = 0;
		while (index < listPTH.size()) {
			PhuThuocHam pth = listPTH.get(index);
			//ve ben trai chi co 1 thuoc tinh thi khong can xet
			if (pth.getVeTrai().length() != 1) {
				for (int i = 0; i < pth.getVeTrai().length(); i++) {
					String chuoiBo = Character.toString(pth.getVeTrai().charAt(i));
					String chuoiSauKhiBo = CongCu.catChuoi(pth.getVeTrai(), i);
					if (TimBaoDong.timBaoDong(listPTH, chuoiSauKhiBo).contains(chuoiBo)) {
//						System.out.println("Delete " + chuoiBo + " From " + pth.getVeTrai() + " -> " + pth.getVePhai());
						listPTH.get(index).setVeTrai(chuoiSauKhiBo);
					}
				}
			}
			index++;
		}
		
		//bo phu thuoc ham du thua
		index = 0;
		while (index < listPTH.size()) {
			String trai = listPTH.get(index).getVeTrai();
			String phai = listPTH.get(index).getVePhai();
				listPTH.remove(index);
				if(!TimBaoDong.timBaoDong(listPTH, trai).contains(phai)) {
				listPTH.add(index, new PhuThuocHam(trai, phai));
				index++;
				}
				else {
//					System.out.println("Delete " + trai + " -> " + phai);
				}
			}
		
		return listPTH;
	}
	
	public static void inPhuToiThieu(List<PhuThuocHam> listPTH) {	
		//in PTH 
		System.out.print("F = {");
		for (int i = 0; i < listPTH.size()-1; i++) {
			System.out.print(listPTH.get(i).getVeTrai() + " -> " + listPTH.get(i).getVePhai() + ", ");
		}
		System.out.println(listPTH.get(listPTH.size()-1).getVeTrai() + " -> " + listPTH.get(listPTH.size()-1).getVePhai() + "}");
	}

}
