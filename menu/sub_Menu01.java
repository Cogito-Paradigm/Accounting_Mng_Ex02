package menu;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import biz.BizMng;
import vo.TotalVO;
import vo.ValueVO;

public class sub_Menu01 {
	String a = "1.자산입력";
	String b = "2.부채입력";
	String c = "3.자본입력";
	String d = "4.상위메뉴로";
	
	public sub_Menu01(){
	}
	public sub_Menu01(TotalVO total){
		int escape = 0;
		while(escape != 4){	
			Scanner sc = new Scanner(System.in);
			System.out.println("원하는 메뉴를 선택하세요.");
			System.out.println(toString());
			int imsi = sc.nextInt();
			escape = act(imsi, total);
		}
	}
	
	public String toString(){
		return a+"  "+b+"  "+c+"  "+d;
	}
	
	public int act(int imsi, TotalVO total){
		BizMng biz = new BizMng();
		if(imsi == 1){
			Map<String, List<ValueVO>> mapList = total.getAssetMapList();
			biz.writeAsset(mapList);
			total.setAssetMapList(mapList);
			biz.writeDouble(total);
		}
		else if(imsi == 2){
			Map<String, List<ValueVO>> mapList = total.getLiabilityMapList();
			biz.writeLiability(mapList);
			total.setLiabilityMapList(mapList);
			biz.writeDouble(total);
		}
		else if(imsi == 3){
			Map<String, List<ValueVO>> mapList = total.getCapitalMapList();
			biz.writeCapital(mapList);
			total.setCapitalMapList(mapList);
			biz.writeDouble(total);
		}
		else if(imsi == 4){
		}
		else{
			System.out.println("잘못입력하셨습니다.");
		}
		return imsi;
	}
}
