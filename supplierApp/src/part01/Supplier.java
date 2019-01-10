package part01;

import java.util.ArrayList;

public class Supplier {
	// INSTANCE VARIABLES
	private int supCode;
	private String supName;
	private Address supAddress;
	private SupRegion supRegion;
	private ArrayList<Product> supProducts;
	private static ArrayList<Supplier> supplierArray = new ArrayList<Supplier>();
	
	
	// CONSTRUCTOR
	public Supplier(int supCode, String supName, Address supAddress, SupRegion supRegion, ArrayList<Product> supProducts) {
		this.supCode = supCode;
		this.supName = supName;
		this.supAddress = supAddress;
		this.supRegion = supRegion;
		this.supProducts = supProducts;
		supplierArray.add(this);
	}
	
	// PRINT PRODUCTLIST
	public void printProductLists() {
		for (int i = 0; i < getSupProducts().size();i++) {
			System.out.println(supProducts.get(i).getProductDetails());
		}
	}
	
	public void addSupProduct(Product item){
		getSupProducts().add(item);
	}
	
	// GETTERS
	public static ArrayList<Supplier> getSupplierArray(){
		return supplierArray;
	}
	
	public int getSupCode() {
		return supCode;
	}

	public String getSupName() {
		return supName;
	}

	public Address getSupAddress() {
		return supAddress;
	}

	public SupRegion getSupRegion() {
		return supRegion;
	}

	public ArrayList<Product> getSupProducts() {
		return supProducts;
	}

	// SETTERS
	public void setSupCode(int supCode) {
		this.supCode = supCode;
	}

	public void setSubName(String subName) {
		this.supName = subName;
	}

	public void setSupAddress(Address supAddress) {
		this.supAddress = supAddress;
	}

	public void setSupRegion(SupRegion supRegion) {
		this.supRegion = supRegion;
	}

	public void setSupProducts(ArrayList<Product> supProducts) {
		this.supProducts = supProducts;
	}
	
	
}
