package menu;

import java.util.Scanner;

import biz.BizMng;
import vo.TotalVO;

public class sub_Menu04 {
	String a = "1.자산 총계 확인";
	String b = "2.부채 총계 확인";
	String c = "3.자본 총계 확인";
	String d = "4.대차대조 확인";
	String e = "5.상위메뉴로";
	
	public sub_Menu04(){
	}
	public sub_Menu04(TotalVO total){
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
			System.out.println("자산의 합계 : "+biz.sumMapList(total.getAssetMapList()));
		}
		else if(imsi == 2){
			System.out.println("부채의 합계 : "+(-1*biz.sumMapList(total.getLiabilityMapList())));
		}
		else if(imsi == 3){
			System.out.println("자본의 합계 : "+(-1*biz.sumMapList(total.getCapitalMapList())));
		}
		else if(imsi == 4){
			biz.checkMapList(total);
		}
		else if(imsi == 5){
		}
		else{
			System.out.println("잘못입력하셨습니다.");
		}
		return imsi;
	}
}