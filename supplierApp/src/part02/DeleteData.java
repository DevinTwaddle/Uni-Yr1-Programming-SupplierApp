package part02;


public class DeleteData {
	
	private static int selectedProductIndex, selectedSupplierIndex;
	
	/**
	 * (Delete Main Menu)
	 * This method provides the user with the ability to select the object which they would like to delete.
	 */
	public static void deleteObjectSelection() {
		System.out.println("\n\n                                        |DELETE EXISTING DATA|"
				+ "\n\nEnter \"-1\" to return, else select Object to Delete."
				+ "\n________________________________________________________________________________________\n"
				+ "\n1: Supplier"
				+ "\n2: Product");
		
		while (1 != 2) {
			int input = Console.validateInt();
			
			switch(input) {
			case -1: 
				Console.userMenu();
				break;
				
			case 1:
				objectSelectionMenu("Supplier"); // Run Supplier instance selection method, with the intent to delete.
				break;
				
			case 2:
				objectSelectionMenu("Product"); // Run Product instance selection method, with the intent to delete.
				break;
				
				default:
					System.out.println("ERROR: Value Exceeds Range. Please try again.");
					break;
			}
		}
	}
	
	/**
	 * (Instance Deletion Function)
	 * This method is used to confirm and facilitate the deletion of a selected Object instance.
	 * @param object - The object type.
	 */
	public static void deleteSelectedObject(String object) {	
		if (object == "Supplier") {
			System.out.println("\n\n                                        |DELETE SUPPLIER " + Supplier.getSupplierArray().get(selectedSupplierIndex).getSupCode()+ "|\n"
					+ "\nPlease confirm that you wish to DELETE this Supplier, Else enter \"-1\" to RETURN to Main Menu."
					+ "\n________________________________________________________________________________________\n"
					+ "\n1: Confirm"
					+ "\n2: Cancel.");
			
			while(1 != 2) {
				int input = Console.validateInt();
				
				if (input == -1) {
					Console.userMenu();
				}
				else if (input == 1) { // If user confirms the deletion process, remove Supplier from main ArrayList, and print confirmation message.
					Supplier.getSupplierArray().remove(selectedSupplierIndex);
					System.out.println("\n=============================" 
							+ "\nSupplier Successfully Deleted."
							+ "\n=============================\n\n");
					Console.userMenu();
				}
				else if (input == 2) {
					objectSelectionMenu("Supplier"); // If user enters 2, then return to previous selection function.
				}
			}
			
		}
		else if(object == "Product") {
			System.out.println("\n\n                                        |DELETE PRODUCT " + Product.getProductList().get(selectedProductIndex).getProCode()+ "|\n"
					+ "\nPlease confirm that you wish to DELETE this Product, Else enter \"-1\" to RETURN to Main Menu."
					+ "\n________________________________________________________________________________________\n"
					+ "\n1: Confirm"
					+ "\n2: Cancel.");
			
			while(1 != 2) {
				int input = Console.validateInt();
				
				if (input == -1) {
					Console.userMenu();
				}
				else if (input == 1) { 
					// Here the program will individually select each supplier, then individually select each of their products. Then compare each product code, to that
					// of the product being deleted. If a match is found, then remove that product from the Suppliers Individualised Product List.
					for (int i = 0; i < Supplier.getSupplierArray().size(); i++) { 
						for (int j = 0; j < Supplier.getSupplierArray().get(i).getSupProducts().size(); j++) {
							if (Supplier.getSupplierArray().get(i).getSupProducts().get(j).getProCode() == Product.getProductList().get(selectedProductIndex).getProCode()) {
								Supplier.getSupplierArray().get(i).getSupProducts().remove(j);
							}
						}
					}
					
					// Then remove the product from the Main Product List, and print a confirmation message.
					Product.getProductList().remove(selectedProductIndex);
					System.out.println("\n=============================" 
							+ "\nProduct Successfully Deleted."
							+ "\n=============================\n\n");
					Console.userMenu();
				}
				else if (input == 2) {
					objectSelectionMenu("Product"); // If user enters 2, then return to previous selection function.
				}
			}
		}
		
	}
	
	/** 
	* This method is invokes the instance selection methods, then directs the user to specific menus in association with their selection.
	* @param object - This variable determines the object being Deleted.
	*/
	public static void objectSelectionMenu(String object) {
		// UPDATE SUPPLIER
		if (object == "Supplier") {
			System.out.println("\n\n                                          |SELECT SUPPLIER|"
					+ "\n\nSelect Supplier to Delete."
					+ "\n________________________________________________________________________________________\n");
			
			while (1 != 2) {
				selectedSupplierIndex = Console.validateSupplierSelection(); 
				
				if (selectedSupplierIndex == -1) {
					deleteObjectSelection();
				}
				else if (selectedSupplierIndex == -2) {
					DisplayData.printAllSupplierDetails(false);
				}
				else {
					deleteSelectedObject("Supplier"); // Else, value must be valid. Proceed to Delete Menu.
				}
			}
		}
			else if (object == "Product") {
			System.out.println("\n\n                                           |SELECT PRODUCT|"
					+ "\n\nSelect Product to Delete."
					+ "\n________________________________________________________________________________________\n");
			
			while (1 != 2) {
				selectedProductIndex = Console.validateProductSelection();
				
				if (selectedProductIndex == -1) {
					deleteObjectSelection();
				}
				else if (selectedProductIndex == -2) {
					DisplayData.printAllProductDetails(false);
				}
				else {
					deleteSelectedObject("Product"); // Else, value must be valid. Proceed to Delete Menu.
				}
			}
		}
	}
}
