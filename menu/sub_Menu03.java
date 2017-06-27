package menu;

import java.util.Scanner;

import biz.BizMng;
import vo.TotalVO;

public class sub_Menu03 {
	String a = "1.자산 내용출력";
	String b = "2.부채 내용출력";
	String c = "3.자본 내용출력";
	String d = "4.모든 내용출력";
	String e = "5.상위메뉴로";
	
	public sub_Menu03(){
	}
	public sub_Menu03(TotalVO total){
		int escape = 0;
		while(escape != 5){	
			Scanner sc = new Scanner(System.in);
			System.out.println("원하는 메뉴를 선택하세요.");
			System.out.println(toString());
			int imsi = sc.nextInt();
			escape = act(imsi, total);
		}
	}
	
	public String toString(){
		return a+"  "+b+"  "+c+"  "+d+"  "+e;
	}
	
	public int act(int imsi, TotalVO total){
		BizMng biz = new BizMng();
		if(imsi == 1){
			biz.dispMapList(total.getAssetMapList());
		}
		else if(imsi == 2){
			biz.dispMapList(total.getLiabilityMapList());
		}
		else if(imsi == 3){
			biz.dispMapList(total.getCapitalMapList());
		}
		else if(imsi == 4){
			biz.dispTotal(total);
		}
		else if(imsi == 5){
		}
		else{
			System.out.println("잘못입력하셨습니다.");
		}
		return imsi;
	}
}
