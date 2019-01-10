package part02;

import java.util.ArrayList;

public class Address {
	private int bldNum;
	private String bldStreet, bldTown, bldPcode, bldCountry;
	public static ArrayList<Address> addressList = new ArrayList<Address>();
	
	public Address(int bldNum, String bldStreet, String bldTown, String bldPcode, String bldCountry) {
		this.bldNum = bldNum;
		this.bldStreet = bldStreet;
		this.bldTown = bldTown;
		this.bldPcode = bldPcode;
		this.bldCountry = bldCountry;
		addressList.add(this);
	}
	
	public String getFullAddress() {
		return ("\nAddress:\n       " + getBldNum() + " " + getBldStreet()+ ",\n       " + getBldTown() + ",\n       " + getBldCountry() + ",\n       " + getBldPcode());
	}
	
	public int getBldNum() {
		return bldNum;
	}
	public String getBldStreet() {
		return bldStreet;
	}
	public String getBldTown() {
		return bldTown;
	}
	public String getBldPcode() {
		return bldPcode;
	}
	public String getBldCountry() {
		return bldCountry;
	}
	
	public void setBldNum(int bldNum) {
		this.bldNum = bldNum;
	}
	public void setBldStreet(String bldStreet) {
		this.bldStreet = bldStreet;
	}
	public void setBldTown(String bldTown) {
		this.bldTown = bldTown;
	}
	public void setBldPcode(String bldPcode) {
		this.bldPcode = bldPcode;
	}
	public void setBldCountry(String bldCountry) {
		this.bldCountry = bldCountry;
	}
}
