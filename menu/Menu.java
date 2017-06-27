package menu;

import java.util.Scanner;
import vo.TotalVO;


public class Menu {
	String a = "1.재무상태표 입력";
	String b = "2.기간별 출력";
	String c = "3.목록별 출력";
	String d = "4.재무상태표 확인";
	String e = "5.저장 및 종료";
	
	public Menu(){
	}
	public Menu(TotalVO total){
		int escape = 0;
		while(escape != 5){
			System.out.println("원하는 메뉴를 선택하세요.");
			System.out.println(toString());
			
			//숫자를 입력받아 저장
			Scanner sc = new Scanner(System.in);
			int imsi = sc.nextInt();
			
			//입력받은 숫자로 서브메뉴 호출, return값을 이용하여 main에서 while문 탈출 시 사용
			escape = act(imsi, total);
		}
	}
	
	public String toString(){
		return a+"  "+b+"  "+c+"  "+d+"  "+e;
	}
	
	//서브메뉴 호출을 위한 함수
	public int act(int imsi, TotalVO total){
		if(imsi==1){
			sub_Menu01 subMenu = new sub_Menu01(total);
		}
		else if(imsi==2){
			sub_Menu02 subMenu = new sub_Menu02(total);
		}
		else if(imsi==3){
			sub_Menu03 subMenu = new sub_Menu03(total);
		}
		else if(imsi==4){
			sub_Menu04 subMenu = new sub_Menu04(total);
		}
		else if(imsi==5){
			System.out.println("변경된 사항을 저장하고 종료합니다.");
		}
		else{
			System.out.println("잘못입력하셨습니다.");
		}
		return imsi;
	}
}
