package part02;

public class UpdateData {
	
	public static boolean proceed;
	public static int selectedSupplierIndex, selectedProductIndex;
	public static String newStringValue;
	public static int newIntValue;
	public static double newDoubleValue;
	
	/**
	 * (Update Main Menu)
	 * This method provides the user with the ability to select the object type, which then invokes the instance selection.
	 */
	public static void updateObjectSelection() {
		System.out.println("\n\n\n                                        |UPDATE EXISTING DATA|\n"
				+ "\n\nEnter \"-1\" to return, else select object to Update."
				+ "\n________________________________________________________________________________________\n"
				+ "\n1: Supplier"
				+ "\n2: Product");
		
		while (1 != 2) {
			int input = Console.validateInt();
			
			switch(input) {
			case -1: 
				Console.printCancelMessage();
				Console.userMenu();
				break;
			case 1:
				objectSelectionMenu("Supplier");
				break;
			case 2:
				objectSelectionMenu("Product");
				break;
				default:
					System.out.println("ERROR: Value Exceeds Range. Please try again.");
					break;
			}	
		}
	}
	
	/** 
	* This method is invokes the instance selection methods, then directs the user to specific menus in association with their selection.
	* @param object - This variable determines the object being Updated.
	*/
	public static void objectSelectionMenu(String object) {
		// UPDATE SUPPLIER
		if (object == "Supplier") {
			System.out.println("\n\n                                          |SELECT SUPPLIER|"
					+ "\n\nSelect Supplier to Update."
					+ "\n________________________________________________________________________________________\n");
			
			while (1 != 2) {
				selectedSupplierIndex = Console.validateSupplierSelection(); // Validate and store Index associated with selected Supplier.
				
				if (selectedSupplierIndex == -1) {
					updateObjectSelection(); // If -1, return to previous menu.
				}
				else if (selectedSupplierIndex == -2) {
					DisplayData.printAllSupplierDetails(false); // If -2, Display Supplier details.
				}
				else {
					supplierAttributeSelection(); // Else, value must be valid. Proceed to Update Menu.
				}
			}
		}
		else if (object == "Product") {
			System.out.println("\n\n                                            |SELECT PRODUCT|"
					+ "\n\nSelect Product to Update."
					+ "\n________________________________________________________________________________________\n");
			
			while (1 != 2) {
				selectedProductIndex = Console.validateProductSelection(); // Validate and store Index associated with selected Product.
				
				if (selectedProductIndex == -1) {
					updateObjectSelection(); // If -1, return to previous menu.
				}
				else if (selectedProductIndex == -2) {
					DisplayData.printAllProductDetails(false); // If -2, Display Supplier details.
				}
				else {
					productAttributeSelection(); // Else, value must be valid. Proceed to Update Menu.
				}
			}
		}
	}

	
	//																 | UPDATE SUPPLIER METHODS |
	
	/**
	 * (Update Supplier Menu)
	 * This method provides the user with the ability to select the specific Supplier Attribute to be updated.
	 */
	public static void supplierAttributeSelection() {
		
		System.out.println("\n\n                                         |SELECT ATTRIBUTE|"
				+ "\n\nWhich attribute would you like to UPDATE?"
				+ "\n________________________________________________________________________________________\n"
				+ "\n1: Name."
				+ "\n2: Region"
				+ "\n3: Address");
		
		while (1 != 2) {
			int selectedSupplierSection = Console.validateInt();
			
			switch(selectedSupplierSection) {
			case -1:
				objectSelectionMenu("Supplier");
				break;
				
			case 1: 
				updateSupplierName();
				break;
				
			case 2: 
				updateSupplierRegion();
				break;
				
			case 3: 
				updateAddressMenu();
				break;
				
				default: 
					System.out.println("ERROR: Invalid selection. Please try again.");
			}
		}
	}
	
	/**
	 * (Update Supplier Name)
	 * This method provides the user with the ability to modify the selected Suppliers name.
	 */
	public static void updateSupplierName() {
		System.out.println("\n                                       |UPDATE SUPPLIER NAME|"
				+ "\n\nEnter new Supplier Name:"
				+ "\n________________________________________________________________________________________\n");
		
		newStringValue = Console.validateString(); // Get new Name.
		
		if (newStringValue == null) { // If user cancels, print cancel message and return to previous menu.
			Console.printCancelMessage();
			supplierAttributeSelection();
		}
		else { // Otherwise, replace Supplier name, and then run UpdateContinueMenu.
			Supplier.getSupplierArray().get(selectedSupplierIndex).setSubName(newStringValue);
			updateContinueMenu("Supplier");
		}
	}

	/**
	 * (Update Supplier Region)
	 * This method provides the user with the ability to modify the selected Suppliers Region.
	 */
	public static void updateSupplierRegion() {
		System.out.println("\n                                      |UPDATE SUPPLIER REGION|"
				+ "\nSelect new Supplier Region:"
				+ "\n________________________________________________________________________________________\n");
		
		SupRegion newValue = AddData.validateRegion(); // Get new Region.
		
		if (newValue == null) { 
			Console.printCancelMessage();
			supplierAttributeSelection();
		}
		else {
			Supplier.getSupplierArray().get(selectedProductIndex).setSupRegion(newValue);
			updateContinueMenu("Supplier");
		}
	}
	
	//																 | UPDATE ADDRESS METHODS |
	
	/**
	 * (Update Supplier Address Menu)
	 * This method provides the user with the ability to select the specific attribute, of the Selected suppliers address, which they would like to modify.
	 */
	public static void updateAddressMenu() {
		System.out.println("\n\n                                      |UPDATE SUPPLIER ADDRESS|"
				+ "\nWhich attribute would you like to UPDATE?"
				+ "\n1: Building Number."
				+ "\n2: Street Name."
				+ "\n3: Town."
				+ "\n4: Country."
				+ "\n5: PostCode."
				+ "\n6: Full Address.");
		
		while (1 != 2) {
			int input = Console.validateInt();
			
			switch(input) {
			case -1: 
				supplierAttributeSelection();
				break;
				
			case 1: 
				updateSupplierAddressBldNum();
				break;
				
			case 2: 
				updateSupplierAddressStreetName();
				break;
				
			case 3: 
				updateSupplierAddressTownName();
				
			case 4: 
				updateSupplierAddressCountryName();
				break;
				
			case 5: 
				updateSupplierAddressPostcode();
				break;
				
			case 6: 
				updateSupplierAddressFull();
				break;
				
				default: 
					System.out.println("ERROR: Invalid selection. Please try again.");
			}
		}

		
	}
	
	/**
	 * (Update Supplier Address Building Number)
	 * This method provides the user with the ability to modify the building number, of a selected address.
	 */
	public static void updateSupplierAddressBldNum() {
		System.out.println("\n                                       |UPDATE BUILDING NUMBER|"
				+ "\nEnter new Building Number:");
		
		while (1 != 2) {
			newIntValue = Console.validateInt(); // Initialise Variable, and store user Input.
			
			if (newIntValue == -1) { // If input = -1, then cancel operation.
				Console.printCancelMessage();
				supplierAttributeSelection();
			}
			else if (newIntValue < 9999) { // If Value is less than a set "Extreme" value, make the modifications and proceed.
				Supplier.getSupplierArray().get(selectedSupplierIndex).getSupAddress().setBldNum(newIntValue);
				updateContinueMenu("Address");
			}
			else { // Otherwise, ask the user for confirmation that their value was entered correctly.
				int check = Console.largeIntValidation(newIntValue);
				if (check == 1) { // If they confirm, then proceed.
					Supplier.getSupplierArray().get(selectedSupplierIndex).getSupAddress().setBldNum(newIntValue);
					updateContinueMenu("Address");
				}
				else { // Else, print instruction, and start next iteration of loop.
					System.out.println("Please enter new value:");
				}
				
			}
		}
	}
	
	/**
	 * (Update Supplier Address Street Name)
	 * This method provides the user with the ability to modify the street name.
	 */
	public static void updateSupplierAddressStreetName() {
		System.out.println("\n                                         |UPDATE STREET NAME|"
				+ "\nEnter New Street Name:");
		
		newStringValue = Console.validateString();
		
		if (newStringValue == null) {
			Console.printCancelMessage();
			supplierAttributeSelection();
		}
		else {
			Supplier.getSupplierArray().get(selectedSupplierIndex).getSupAddress().setBldStreet(newStringValue);
			updateContinueMenu("Address");
		}
	}
	
	/**
	 * (Update Supplier Address Town Name)
	 * This method provides the user with the ability to modify the Town name.
	 */
	public static void updateSupplierAddressTownName() {
		System.out.println("\n                                             |UPDATE Town|"
				+ "\nEnter New Country:");
		
		newStringValue = Console.validateString();
		
		if (newStringValue == null) {
			Console.printCancelMessage();
			supplierAttributeSelection();
		}
		else {
			Supplier.getSupplierArray().get(selectedSupplierIndex).getSupAddress().setBldTown(newStringValue);
			updateContinueMenu("Address");
		}
	}
	
	/**
	 * (Update supplier Address Country Name)
	 * This method provides the user with the ability to modify the Country Name.
	 */
	public static void updateSupplierAddressCountryName() {
		System.out.println("\n                                           |UPDATE COUNTRY|"
				+ "\nEnter New Country:");
		
		newStringValue = Console.validateString();
		
		if (newStringValue == null) {
			Console.printCancelMessage();
			supplierAttributeSelection();
		}
		else {
			Supplier.getSupplierArray().get(selectedSupplierIndex).getSupAddress().setBldCountry(newStringValue);
			updateContinueMenu("Address");
		}
	}
	
	/**
	 * (Update Supplier Address PostCode)
	 * This method provides the use with the ability to modify the PostCode.
	 */
	public static void updateSupplierAddressPostcode() {
		System.out.println("\n                                           |UPDATE POSTCODE|"
				+ "\nEnter New PostCode:");
		
		newStringValue = AddData.validatePostCode();
		
		if (newStringValue == null) {
			Console.printCancelMessage();
			supplierAttributeSelection();
		}
		else {
			Supplier.getSupplierArray().get(selectedSupplierIndex).getSupAddress().setBldPcode(newStringValue);
			updateContinueMenu("Address");
		}
	}
	
	/**
	 * (Update Supplier Address Full Address)
	 * This method provides the user with the ability to modify the entire address.
	 */
	public static void updateSupplierAddressFull() {
		Address newAddress = AddData.addNewAddress(); // Create a new Address instance.
		
		if (newAddress == null) {
			Console.printCancelMessage();
			supplierAttributeSelection();
		}
		else {
			Supplier.getSupplierArray().get(selectedSupplierIndex).setSupAddress(newAddress);
			updateContinueMenu("Address");
		}
	}
	
	
	//																 | UPDATE PRODUCT METHODS |
	
	/**
	 * (Update Product Menu)
	 * This method provides the user with the ability to select the specific Product Attribute to be updated.
	 */
	public static void productAttributeSelection() {
		// Select specific section to modify
		System.out.println("\n\n                                         |SELECT ATTRIBUTE|"
				+ "\n\nWhich section do you want to modify?"
				+ "\n________________________________________________________________________________________\n"
				+ "\n1: Make."
				+ "\n2: Model."
				+ "\n3: Price."
				+ "\n4: Quantity."
				+ "\n5: Availability.");
		
		while (1 != 2) {
			int input = Console.validateInt();
			
			switch(input) {
			case -1:
				Console.printCancelMessage();
				objectSelectionMenu("Product");
				break;
				
			case 1: 
				updateProductMake();
				break;
				
			case 2: 
				updateProductModel();
				break;
				
			case 3:
				updateProductPrice();
				break;
				
			case 4:
				updateProductQuantity();
				break;
				
			case 5: 
				updateProductAvailability();
				
				default:
					System.out.println("ERROR: Invalid Selection. Please try again.");
					break;
			}
		}
		
	}
	
	/**
	 * (Update Product Make)
	 * This method provides the user with the ability to update the Make of a select product.
	 */
	public static void updateProductMake() {
		System.out.println("\n                                            |UPDATE MAKE|"
				+ "\nEnter new Make:"
				+ "\n________________________________________________________________________________________\n");
		
		newStringValue = Console.validateString();
		
		if (newStringValue == null) {
			Console.printCancelMessage();
			productAttributeSelection();
		}
		else {
			Product.getProductList().get(selectedProductIndex).setProMake(newStringValue);
			updateContinueMenu("Product");
		}
	}

	/**
	 * (Update Product Model)
	 * This method provides the user with the ability to update the Model of a select product.
	 */
	public static void updateProductModel() {
		System.out.println("\n                                           |UPDATE MODEL|"
				+ "\nEnter new Model:"
				+ "\n________________________________________________________________________________________\n");
		
		newStringValue = Console.validateProductModel();
		
		if (newStringValue == null) {
			Console.printCancelMessage();
			productAttributeSelection();
		}
		else {
			Product.getProductList().get(selectedProductIndex).setProModel(newStringValue);
			updateContinueMenu("Product");
		}
	}
	
	/**
	 * (Update Product Price)
	 * This method provides the user with the ability to update the Price of a select product.
	 */
	public static void updateProductPrice() {
		System.out.println("\n                                           |UPDATE PRICE|"
				+ "\nEnter new Price:"
				+ "\n________________________________________________________________________________________\n");
		
		newDoubleValue = Console.validateDouble();
		
		if (newDoubleValue == -1) {
			Console.printCancelMessage();
			productAttributeSelection();
		}
		else {
			Product.getProductList().get(selectedProductIndex).setProPrice(newDoubleValue);
			updateContinueMenu("Product");
		}
	}
	
	/**
	 * (Update Product Quantity)
	 * This method provides the user with the ability to update the Quantity of a select product.
	 */
	public static void updateProductQuantity() {
		System.out.println("\n                                          |UPDATE QUANTITY|"
				+ "\nEnter new Quantity:"
				+ "\n________________________________________________________________________________________\n");
		
		newIntValue = Console.validateInt();
		
		if (newIntValue == -1) {
			Console.printCancelMessage();
			productAttributeSelection();
		}
		else {
			Product.getProductList().get(selectedProductIndex).setProQtyAvailable(newIntValue);
			updateContinueMenu("Product");
		}
	}
	
	/**
	 * (Update Product Availability)
	 * This method provides the user with the ability to update the Availability of a select product.
	 */
	public static void updateProductAvailability() {
		System.out.println("\n\n                                        |UPDATE AVALIABILITY|"
				+ "\nIs the product still avaliable?"
				+ "\n________________________________________________________________________________________\n"
				+ "\n1: Yes."
				+ "\n2: No.");
		while (1 != 2) {
			int input = Console.validateInt();
			
			switch(input) {
			case -1: 
				Console.printCancelMessage();
				productAttributeSelection();
				break;
			case 1: 
				Product.getProductList().get(selectedProductIndex).setProDiscountinued(true);
				updateContinueMenu("Product");
				break;
			case 2:
				Product.getProductList().get(selectedProductIndex).setProDiscountinued(false);
				updateContinueMenu("Product");
				break;
				
				default: 
					System.out.println("ERROR: Invalid selection. Please try again.\n\n");
			}
		}
	}
	
	/**
	 * (Update End Navigation Menu)
	 * This method acts as a menu, providing the user with the ability to navigate through various areas of the program.
	 * @param object - This dictates the object which has been updated.
	 */
	public static void updateContinueMenu(String object) {
		System.out.println("\n\n               -----------------------------------------------------------------------"
				+ "\n               Enter \"1\" to UPDATE additional details. Else \"2\" to RETURN main menu. "
				+ "\n               -----------------------------------------------------------------------");

		while (1 != 2) {
			int input = Console.validateInt();
			
			
			
			if (object == "Product") {
				if (input == 1) {
					productAttributeSelection();
				}
				else if (input == 2) {
					Console.userMenu();
				}
			}
			else if (object == "Supplier"){
				if (input == 1) {
					supplierAttributeSelection();
				}
				else if (input == 2) {
					Console.userMenu();
				}
			}
			else if (object == "Address") {
				if (input == 1) {
					updateAddressMenu();
				}
				else if (input == 2) {
					Console.userMenu();
				}
			}
			System.out.println("ERROR: Value exceeds range. Please try again.");
		}
	}
	
}
