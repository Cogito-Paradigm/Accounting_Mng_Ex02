package vo;

public class ValueVO {
	String type;
	String account;
	double cha;
	double dae;
	
	public ValueVO(){
	}
	public ValueVO(String type, String account, double cha, double dae){
		this.type = type;
		this.account = account;
		this.cha = cha;
		this.dae = dae;
	}
	
	public String toString(){
		return "분류 :  "+type+"  계정 :  "+account+"  차변 :  "+cha+"  대변 :  "+dae;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setCha(double cha) {
		this.cha = cha;
	}
	public void setDae(double dae) {
		this.dae = dae;
	}
	public double getCha() {
		return cha;
	}
	public double getDae() {
		return dae;
	}
}
