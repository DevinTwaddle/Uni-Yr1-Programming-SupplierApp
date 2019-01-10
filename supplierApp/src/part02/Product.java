package part02;

import java.util.ArrayList;

public class Product {
	// VARIABLES
	private int proCode, proQtyAvailable;
	private String proMake, proModel;
	private double proPrice;
	private boolean proDiscountinued;
	private static ArrayList<Product> productList = new ArrayList<Product>();
	
	
	// CONSTRUCTOR
	public Product(int proCode, String proMake, String proModel, double proPrice, int proQtyAvailable, boolean proDiscontinued) {
		this.proCode = proCode;
		this.proMake = proMake;
		this.proModel = proModel;
		this.proPrice = proPrice;
		this.proQtyAvailable = proQtyAvailable;
		this.proDiscountinued = proDiscontinued;
		productList.add(this);
	}
	
	
	// PRINT PRODUCT DETAILS
	public String getProductDetails() {
		String yesNo;
		if (isProDiscountinued()) {
			yesNo = "No";
		}
		else {
			yesNo = "Yes";
		}
		
		return ("\n---------------------\n  Product Code: " + getProCode() + "\n---------------------" + "\nMake: " + getProMake() + "\nModel: " + getProModel() 
		           + "\nPrice: " + getProPrice() + "\nQuanity: " + getProQtyAvailable() + "\nAvalaible: " + yesNo + "\n");

	}
	
	public String getPartialProductDetails() {
		return ("\n---------------------\n Product Code: " + getProCode() + " \n---------------------"
				+ "\nMake: " + getProMake()
				+ "\nModel: " + getProModel());
	}
	
	
	// GETTERS
	public static ArrayList<Product> getProductList(){
		return productList;
	}

	public int getProCode() {
		return proCode;
	}

	public int getProQtyAvailable() {
		return proQtyAvailable;
	}

	public String getProMake() {
		return proMake;
	}

	public String getProModel() {
		return proModel;
	}

	public double getProPrice() {
		return proPrice;
	}

	public boolean isProDiscountinued() {
		return proDiscountinued;
	}

	
	// SETTERS
	public void setProCode(int proCode) {
		this.proCode = proCode;
	}

	public void setProQtyAvailable(int proQtyAvailable) {
		this.proQtyAvailable = proQtyAvailable;
	}

	public void setProMake(String proMake) {
		this.proMake = proMake;
	}

	public void setProModel(String proModel) {
		this.proModel = proModel;
	}

	public void setProPrice(double proPrice) {
		this.proPrice = proPrice;
	}

	public void setProDiscountinued(boolean proDiscountinued) {
		this.proDiscountinued = proDiscountinued;
	}
	
	
}
