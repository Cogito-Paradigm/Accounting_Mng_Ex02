package biz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import util.DateUtil;
import vo.TotalVO;
import vo.ValueVO;

public class BizMng {
	
	//TotalVO의 mapList를 받아 키값(날짜)를 기준으로 오름차순으로 정렬 후 정렬된 Map 반환
	public Map<String, List<ValueVO>> arrangeList(Map<String, List<ValueVO>> mapList){
		TreeMap<String, List<ValueVO>> treeMap = new TreeMap<String, List<ValueVO>>(mapList);
        Iterator<String> treeMapIter = treeMap.keySet().iterator();
        while( treeMapIter.hasNext()) {
            String key = treeMapIter.next();
    	}
        return treeMap;
	}
	
	//통합 mapList 생성 메소드
	public Map<String, List<ValueVO>> createTotal(TotalVO total){
		Map<String, List<ValueVO>>totalList = new HashMap<String, List<ValueVO>>();
		
		for(String key : total.getAssetMapList().keySet()){
			if(totalList.containsKey(key)){
				List<ValueVO> imsiList = new ArrayList<ValueVO>();
				for(ValueVO e : total.getAssetMapList().get(key)){
					imsiList.add(e);
				}
				for(ValueVO e : totalList.get(key)){
					imsiList.add(e);
				}
				totalList.put(key, imsiList);
			}else{
				totalList.put(key, total.getAssetMapList().get(key));
			}
		}
		for(String key : total.getLiabilityMapList().keySet()){
			if(totalList.containsKey(key)){
				List<ValueVO> imsiList = new ArrayList<ValueVO>();
				for(ValueVO e : total.getLiabilityMapList().get(key)){
					imsiList.add(e);
				}
				for(ValueVO e : totalList.get(key)){
					imsiList.add(e);
				}
				totalList.put(key, imsiList);
			}else{
				totalList.put(key, total.getLiabilityMapList().get(key));
			}
		}
		for(String key : total.getCapitalMapList().keySet()){
			if(totalList.containsKey(key)){
				List<ValueVO> imsiList = new ArrayList<ValueVO>();
				for(ValueVO e : total.getCapitalMapList().get(key)){
					imsiList.add(e);
				}
				for(ValueVO e : totalList.get(key)){
					imsiList.add(e);
				}
				totalList.put(key, imsiList);
			}else{
				totalList.put(key, total.getCapitalMapList().get(key));
			}
		}
		totalList = arrangeList(totalList);
		return totalList;
	}
	
	//전체 출력 메소드
	public void dispTotal(TotalVO total){
		Map<String, List<ValueVO>>totalList = new HashMap<String, List<ValueVO>>();
		totalList = createTotal(total);
		
        for( String key : totalList.keySet() ){
            for( ValueVO e : totalList.get(key)){
            	System.out.println("날짜 : "+key+"  분류 : "+e.getType()+"  계정 : "+e.getAccount()+"  차변 : "+e.getCha()+"  대변 : "+e.getDae());
            }
        }
	}
	
	//목록별 출력 메소드
	public void dispMapList(Map<String, List<ValueVO>> parameter){
		Map<String, List<ValueVO>> mapList = arrangeList(parameter);
        
        for( String key : mapList.keySet() ){
            for( ValueVO e : mapList.get(key)){
            	System.out.println("날짜 : "+key+"  분류 : "+e.getType()+"  계정 : "+e.getAccount()+"  차변 : "+e.getCha()+"  대변 : "+e.getDae());
            }
        }
   	}
	
	//날짜출력 메소드
	public void dispDate(TotalVO total){
		Scanner sc = new Scanner(System.in);
		System.out.println("원하는 날짜를 입력하세요. ex)2017-03-01");
		String imsi = sc.nextLine();
		
		Map<String, List<ValueVO>>totalList = new HashMap<String, List<ValueVO>>();
		totalList = createTotal(total);
		
		for(String key : totalList.keySet()){
			for(ValueVO e : totalList.get(key)){
				if(imsi.equals(key)){
	            	System.out.println("날짜 : "+key+"  분류 : "+e.getType()+"  계정 : "+e.getAccount()+"  차변 : "+e.getCha()+"  대변 : "+e.getDae());
				}
			}
		}
	}

	//일주일출력 메소드
	public void dispWeek(TotalVO total){
		Map<String, List<ValueVO>>totalList = new HashMap<String, List<ValueVO>>();
		totalList = createTotal(total);
		
		for(int i = 7; i>=0; i--){
			String imsi = DateUtil.dateBefore(i);
			for(String key : totalList.keySet()){
				for(ValueVO e : totalList.get(key)){
					if(imsi.equals(key)){
						System.out.println("날짜 : "+key+"  분류 : "+e.getType()+"  계정 : "+e.getAccount()+"  차변 : "+e.getCha()+"  대변 : "+e.getDae());
					}
				}
			}
		}
	}
	
	//한달출력 메소드
	public void dispMonth(TotalVO total){
		Map<String, List<ValueVO>>totalList = new HashMap<String, List<ValueVO>>();
		totalList = createTotal(total);
		
		for(int i = 30; i>=0; i--){
			String imsi = DateUtil.dateBefore(i);
			for(String key : totalList.keySet()){
				for(ValueVO e : totalList.get(key)){
					if(imsi.equals(key)){
						System.out.println("날짜 : "+key+"  분류 : "+e.getType()+"  계정 : "+e.getAccount()+"  차변 : "+e.getCha()+"  대변 : "+e.getDae());
					}
				}
			}
		}
	}

	//자산 입력 메소드
	public Map<String, List<ValueVO>> writeAsset(Map<String, List<ValueVO>> parameter){
		Map<String, List<ValueVO>> mapList = parameter;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("발생한 회계활동의 날짜/계정/금액(+,-) 순서대로 입력하시오.");
		System.out.println("ex)2017-03-01/현금/+5000");
		String[] imsi = sc.nextLine().split("/");
		
		String date = imsi[0];
		String account = imsi[1];
		long money = Long.parseLong(imsi[2]);
		
		List<ValueVO> imsiList = new ArrayList<ValueVO>();
		for(String key : mapList.keySet()){
			if(key.equals(date)){
				imsiList = mapList.get(key);
				if(money>=0){
					imsiList.add(new ValueVO("자산", account, money, 0));
				}
				else if(money<0){
					imsiList.add(new ValueVO("자산", account, 0, -1*money));
				}
				mapList.put(key, imsiList);
				return mapList;
			}
		}
		
		if(money>=0){
			imsiList.add(new ValueVO("자산", account, money, 0));
		}
		else if(money<0){
			imsiList.add(new ValueVO("자산", account, 0, -1*money));
		}
		mapList.put(date, imsiList);
		
		return mapList;
	}
	
	//부채 입력 메소드
	public Map<String, List<ValueVO>> writeLiability(Map<String, List<ValueVO>> parameter){
		Map<String, List<ValueVO>> mapList = parameter;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("발생한 회계활동의 날짜/계정/금액(+,-) 순서대로 입력하시오.");
		System.out.println("ex)2017-03-01/현금/+5000");
		String[] imsi = sc.nextLine().split("/");
		
		String date = imsi[0];
		String account = imsi[1];
		long money = Long.parseLong(imsi[2]);
		
		List<ValueVO> imsiList = new ArrayList<ValueVO>();
		for(String key : mapList.keySet()){
			if(key.equals(date)){
				imsiList = mapList.get(key);
				if(money>=0){
					imsiList.add(new ValueVO("부채", account, 0, money));
				}
				else if(money<0){
					imsiList.add(new ValueVO("부채", account, -1*money, 0));
				}
				mapList.put(key, imsiList);
				return mapList;
			}
		}
		if(money>=0){
			imsiList.add(new ValueVO("부채", account, 0, money));
		}
		else if(money<0){
			imsiList.add(new ValueVO("부채", account, -1*money, 0));
		}
		mapList.put(date, imsiList);
		
		return mapList;
	}
	
	//자본 입력 메소드
	public Map<String, List<ValueVO>> writeCapital(Map<String, List<ValueVO>> parameter){
		Map<String, List<ValueVO>> mapList = parameter;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("발생한 회계활동의 날짜/계정/금액(+,-) 순서대로 입력하시오.");
		System.out.println("ex)2017-03-01/현금/+5000");
		String[] imsi = sc.nextLine().split("/");
		
		String date = imsi[0];
		String account = imsi[1];
		long money = Long.parseLong(imsi[2]);
		
		List<ValueVO> imsiList = new ArrayList<ValueVO>();
		for(String key : mapList.keySet()){
			if(key.equals(date)){
				imsiList = mapList.get(key);
				if(money>=0){
					imsiList.add(new ValueVO("자본", account, 0, money));
				}
				else if(money<0){
					imsiList.add(new ValueVO("자본", account, -1*money, 0));
				}
				mapList.put(key, imsiList);
				return mapList;
			}
		}
		if(money>=0){
			imsiList.add(new ValueVO("자본", account, 0, money));
		}
		else if(money<0){
			imsiList.add(new ValueVO("자본", account, -1*money, 0));
		}
		mapList.put(date, imsiList);
		return mapList;
	}

	//복식부기 메소드
	public void writeDouble(TotalVO total){
		while(true){
			Scanner sc = new Scanner(System.in);
			System.out.println("복식부기할 메뉴를 선택하세요.");
			System.out.println("1.자산입력  2.부채입력  3.자본입력");
			int imsi2 = sc.nextInt();
			if(imsi2 == 1){
				Map<String, List<ValueVO>> mapList = total.getAssetMapList();
				writeAsset(mapList);
				total.setAssetMapList(mapList);
				break;
			}else if(imsi2 ==2){
				Map<String, List<ValueVO>> mapList = total.getLiabilityMapList();
				writeLiability(mapList);
				total.setLiabilityMapList(mapList);
				break;
			}else if(imsi2 ==3){
				Map<String, List<ValueVO>> mapList = total.getCapitalMapList();
				writeCapital(mapList);
				total.setCapitalMapList(mapList);
				break;
			}else{
				System.out.println("잘못입력하셨습니다.");
			}
		}
	}
	
	//각 map의 합계 반환 메소드
	public double sumMapList(Map<String, List<ValueVO>> parameter){
		Map<String, List<ValueVO>> mapList = arrangeList(parameter);
		double sum = 0;
		for(String key : mapList.keySet()){
			for(ValueVO e : mapList.get(key)){
				sum = sum+e.getCha()-e.getDae();
			}
		}
		return sum;
	}

	//대차대조 확인 메소드
	public void checkMapList(TotalVO total){
		double asset =  sumMapList(total.getAssetMapList());
		double liability = -1*sumMapList(total.getLiabilityMapList());
		double capital = -1*sumMapList(total.getCapitalMapList());
		
		if(asset==liability+capital){
			System.out.println("자산 합 : "+asset+"  부채 합 : "+liability+"  자본 합 : "+capital);
			System.out.println("자산 = 부채 + 자본이므로 회계처리가 올바릅니다.");
		}else{
			System.out.println("자산 합 : "+asset+"  부채 합 : "+liability+"  자본 합 : "+capital);
			System.out.println("자산 = 부채 + 자본이므로 회계처리가 올바르지 않습니다.");
		}
	}
}
