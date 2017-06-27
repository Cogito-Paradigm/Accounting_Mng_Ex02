package vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TotalVO{
	Map<String, List<ValueVO>> assetMapList = new HashMap<String, List<ValueVO>>();
	Map<String, List<ValueVO>> liabilityMapList = new HashMap<String, List<ValueVO>>();
	Map<String, List<ValueVO>> capitalMapList = new HashMap<String, List<ValueVO>>();
	
	
	public Map<String, List<ValueVO>> getAssetMapList() {
		return assetMapList;
	}
	public void setAssetMapList(Map<String, List<ValueVO>> assetMapList) {
		this.assetMapList = assetMapList;
	}
	public Map<String, List<ValueVO>> getLiabilityMapList() {
		return liabilityMapList;
	}
	public void setLiabilityMapList(Map<String, List<ValueVO>> liabilityMapList) {
		this.liabilityMapList = liabilityMapList;
	}
	public Map<String, List<ValueVO>> getCapitalMapList() {
		return capitalMapList;
	}
	public void setCapitalMapList(Map<String, List<ValueVO>> capitalMapList) {
		this.capitalMapList = capitalMapList;
	}
}
