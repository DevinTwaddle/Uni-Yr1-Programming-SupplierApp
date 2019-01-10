package part01;

import java.util.ArrayList;

public class Product_Tester {

	public static ArrayList<Product> pList1 = new ArrayList<Product>();
	public static ArrayList<Product> pList2 = new ArrayList<Product>();
	public static ArrayList<Product> pList3 = new ArrayList<Product>();
	public static ArrayList<Product> pList4 = new ArrayList<Product>();
	
	/**
	 * This method is used to initialise example Product data.
	 */
	public static void initaliseProductData() {
		// Create new Products.
		Product p1 = new Product(10, "Make1", "Model1", 7.5, 1, false);
		Product p2 = new Product(20, "Make2", "Model2", 14, 4, true);
		Product p3 = new Product(30, "Make3", "Model3", 20.5, 8, false);
		Product p4 = new Product(40, "Make4", "Model4", 35.5, 12, true);
		Product p5 = new Product(50, "Make5", "Model5", 19.5, 16, false);
		Product p6 = new Product(60, "Make6", "Model6", 5.5, 20, true);
		Product p7 = new Product(70, "Make7", "Model7", 32.54, 24, false);
		Product p8 = new Product(80, "Make8", "Model8", 9.99, 28, true);
		
		// Store various products to one of four lists.
		pList1.add(p1); pList1.add(p3); pList1.add(p5); pList1.add(p7);
		pList2.add(p2); pList2.add(p4); pList2.add(p6); pList2.add(p8);
		pList3.add(p3); pList3.add(p8); pList3.add(p2);
		pList4.add(p1); pList4.add(p2); pList4.add(p3); pList4.add(p4); pList4.add(p5); pList4.add(p6); pList4.add(p7); pList4.add(p8);
	}
	
	/**
	 * This method us used to Create a new Product instance, and assign it to a selected Supplier.
	 */
	public static void addProductToSupplier() {
		Product placeHolder = addNewProduct(); // Create new Product.

		System.out.println("\nEnter Desired Supplier Code:");
		int inputCode = Console.sc.nextInt(); // Enter Supplier Code.

		for (Supplier check: Supplier.getSupplierArray()) {
			if (check.getSupCode() == inputCode) { // Check all Supplier instances for a match.
				if (check.getSupProducts() == null) { // If a match occurs, check if the Supplier currently possess no Product List.
					ArrayList<Product> placeholderList = new ArrayList<Product>(); // If no Product list exists, create a new List.
					placeholderList.add(placeHolder); // Assign the newly created Product to said list.
					check.setSupProducts(placeholderList); // Then set the new List as the selected Suppliers, Product List.
				}
				else {
					check.addSupProduct(placeHolder); // Otherwise, pass the newly created product into a Supplier specific method. Adding the Product.
				}
			}
		}
	}
	
	/**
	 * This method is used to create a new instance of the Product Object class, using user Input.
	 * @return new Product(proCode, proMake, proModel, proPrice, proQty, true)
	 */
	public static Product addNewProduct() {
		System.out.println("\n\n                                           |ADD NEW PRODUCT|"
				+ "\n\nPlease enter the following details:"
				+ "\n________________________________________________________________________________________\n"
				+ "\nProduct Code:");

		int proCode = Console.sc.nextInt();
		Console.sc.nextLine();

		// Input and validate product make.
		System.out.println("Product Make:");
		String proMake = Console.sc.nextLine();

		// Input and validate product model.
		System.out.println("Product Model:");
		String proModel = Console.sc.nextLine();

		// Input and validate product price.
		System.out.println("Product Price:");
		double proPrice = Console.sc.nextDouble();
		Console.sc.nextLine();

		// Input and validate product QtyAvaliable.
		System.out.println("Product Quantity:");
		int proQty = Console.sc.nextInt();

		return  new Product(proCode, proMake, proModel, proPrice, proQty, true);
	}	


	

}


