package menu;

import java.util.Scanner;

import biz.BizMng;
import vo.TotalVO;

public class sub_Menu02 {
	String a = "1.특정 날짜 내용출력";
	String b = "2.일주일간 내용출력";
	String c = "3.한달간 내용출력";
	String d = "4.모든 내용출력";
	String e = "5.상위메뉴로";
	
	public sub_Menu02(){
	}
	public sub_Menu02(TotalVO total){
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
			biz.dispDate(total);
		}
		else if(imsi == 2){
			biz.dispWeek(total);
		}
		else if(imsi == 3){
			biz.dispMonth(total);
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