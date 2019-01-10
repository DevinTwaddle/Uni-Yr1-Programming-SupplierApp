package part01;


import java.util.Scanner;


public class Console {
	
	public static Scanner sc = new Scanner(System.in);
	public static boolean proceed = false;
	

	public static void main(String[] args) {
		Address_Tester.initaliseAddressData();
		Product_Tester.initaliseProductData();
		Supplier_Tester.initaliseSupplierData();
		userMenu();
	}
		

		
	/**
	 * userMenu()
	 * This method is used to display the initial menu, and read user input to access further menus.
	 */
	public static void userMenu() {
		System.out.println("                                             |MAIN MENU|"
				+ "\n\nPlease enter value associaed with desired function:"
				+ "\n________________________________________________________________________________________\n"
				+ "\n1: Print Products by Suppliers."
				+ "\n2: Add Supplier."
				+ "\n3: Add Product."
				+ "\n4: Exit Application.");

		while (1 != 2) {
			int input = validateInt();

			switch(input){
			case 1:
				printAllProductsBySuppliers();
				userMenu();
				break;
			case 2:
				Supplier_Tester.addNewSupplier();
				userMenu();
				break;
			case 3: 
				Product_Tester.addProductToSupplier();
				userMenu();
				//userMenu();
				break;
			case 4: 
				sc.close();
				System.exit(0);
				
			default: System.out.println("ERROR: Value excceds range. Please try again.");
			}
		}
	}
	
	public static void printAllProductsBySuppliers() {
		System.out.println("\n                                       |PRINTING ALL PRODUCTS|"
				+ "\n\n________________________________________________________________________________________\n");
		
		for(int x = 0; x < Supplier.getSupplierArray().size(); x++) {
			System.out.println("\n                                       -----------------------"
					+ "\n                                        Supplier: (" + Supplier.getSupplierArray().get(x).getSupCode() + ") " 
					+ Supplier.getSupplierArray().get(x).getSupName() + "\n                                       -----------------------");
			
			if (Supplier.getSupplierArray().get(x).getSupProducts() == null) {
				System.out.println("No Products found.\n");
			}
			else {
				Supplier.getSupplierArray().get(x).printProductLists();
			}
		}	
	}
	
	
	/**
	 *  Validate Int input.
	 * @return
	 */
	public static int validateInt() {
		int input = 0;
		proceed = false;
		
		while (!proceed) {
			if(sc.hasNextInt()) {
				input = sc.nextInt();
				
				 if (input > 0) {
					// Value Accepted
					proceed = true;
					sc.nextLine();
					return input;
				}
				else {
					// ERROR - out of range.
					System.out.println("ERROR: Value must be positive. Please try again.");
					sc.nextLine();
				}
			}
			else {
				// ERROR - Invalid format.
				System.out.println("ERROR: Value must be numerical. Please try again.");
				sc.nextLine();
			}
		}
		return 0;
	}

}
