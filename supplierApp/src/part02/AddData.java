package part02;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddData {
	public static Pattern symbol = Pattern.compile("[!\"�’$%^&*()_\\-\\=\\+\\[\\]@~#;:,<.>/?\\|]");
	public static Pattern letter = Pattern.compile("[a-zA-Z]");
	public static Pattern number = Pattern.compile("[0-9]");
	
	public static Matcher hasNumber;
	public static Matcher hasLetter;
	public static Matcher hasSymbol;

	/**
	 * (Add Main Menu)
	 * This method acts as a menu, directing the user to various functions and operations in association with input.
	 */
	public static void add_ObjectSelection() {
		System.out.println("\n\n                                       |ADD INFORMATON|"
				+ "\n\nEnter \"-1\" to return, else please select object."
				+ "\n________________________________________________________________________________________\n"
				+ "\n1: Suppliers."
				+ "\n2: Products.");
		
		while (1 != 2) {
			int input = Console.validateInt();
			
			switch(input) {
			case -1: 
				Console.userMenu();
				break;
				
			case 1:
				Supplier sPlaceholder = addNewSupplier();
				if (sPlaceholder == null) {
					add_ObjectSelection();
				}
				else {
					Console.userMenu();
				}
				break;
				
			case 2:
				Product pPlaceholder = addNewProduct(true);
				if (pPlaceholder == null) {
					add_ObjectSelection();
				}
				else {
					Console.userMenu();
				}
				break;
				
				default: System.out.println("ERROR: Value exceeds range. Please try again.");
			}
		}
	}
	
	/**
	 * (Add new Supplier Instance)
	 * This method provides the user with the ability to create new Supplier Instances.
	 * @return new Supplier([Supplier parameters])  
	 */
	public static Supplier addNewSupplier() {
		System.out.println("\n\n                                            |ADD NEW SUPPLIER|"
				+ "\n\nEnter \"-1\" to RETURN, else Please enter the following details:"
				+ "\n________________________________________________________________________________________\n"
				+ "\nSupplier Code:");

		int supCode = 0;
		
		while (1 != 2) {
			supCode = Console.validateInt(); // Store user Input.
			
			if (supCode == -1) { // If -1, cancel operation and return to previous menu.
				return null;
			}
			
			boolean match = false;
			for (Supplier check: Supplier.getSupplierArray()) {
				if (check.getSupCode() == supCode) {
					match = true; // Check through all existing Supplier codes. If found, then set match to true.
				}
			}
			
			if (!match) { 
				if (supCode >= 9999) { 
					int confirm = Console.largeIntValidation(supCode);
					if (confirm == 1) { // If value is not a match, exceeds 9999 and has been confirmed. Then Accept and proceed.
						break; 
					}
					else { // If value is not a match, exceeds 9999 and has been denied, then print instructions and start new loop iteration.
						System.out.println("Enter new value:");
					}
				}
				else { // If value is not a match, and does not exceed 9999, then Accept and proceed.
					break;
				}
			}
			else { // If value is a match, print error message, and loop.
				System.out.println("ERROR: Code already in use. Please try again.");
			}
		}

		// Input and Validate Supplier Name Variable.
		System.out.println("Supplier Name:");
		String supName = Console.validateString();
		if (supName == null) {
			Console.seperate();
			return null;
		}
		
		// Input and validate selected value.
		SupRegion supRegion = validateRegion();
		if (supRegion == null) {
			Console.seperate();
			return null;
		}
		
		// Run addNewAddress method.
		Address supAddress = addNewAddress();
		if (supAddress == null) {
			Console.printCancelMessage();
			return null;
		}
			
		// Run Product method.
		ArrayList<Product> supProducts = new ArrayList<Product>();
		validateProductNavigation(supProducts);
		
		// If no products are added, then the user has cancelled the operation. Display cancel message, and return to previous menu.
		if (supProducts.size() == 0) {
			Console.printCancelMessage();
			add_ObjectSelection();
		}
		
		// Final message, confirming Supplier addition.
		System.out.println("============================" 
				+ "\nSupplier Succefully added."
				+ "\n============================\n\n");
		
		return new Supplier(supCode, supName, supAddress, supRegion, supProducts);
	}

	/**
	 * (Add New Product)
	 * This method provides the user with the ability to create new Product Instances.
	 * @return new Product([Product parameters])
	 */
	public static Product addNewProduct(boolean addToExistingSupplier) {
		System.out.println("\n\n                                           |ADD NEW PRODUCT|"
				+ "\n\nEnter \"-1\" to return, else Please enter the following details:"
				+ "\n________________________________________________________________________________________\n"
				+ "\nProduct Code:");
		
		// Input and Validate product Code.
		int proCode = 0;
		
		while (1 != 2) {
			proCode = Console.validateInt();
			
			if (proCode == -1) { // If input = -1, return to previous menu Cancelling operation.
				Console.seperate();
				return null;
			}
			
			boolean match = false;
			for(Product check: Product.getProductList()) { // Otherwise, compare the input to all existing procodes. If a match is found, update variable.
				if (check.getProCode() == proCode) {
					match = true;
				}
			}
			
			if (!match) { 
				if (proCode < 9999) { // If input is unique, check if it is less than 9999.
					int confirm = Console.largeIntValidation(proCode); // Then prompt the user to ensure that the input was entered correctly.
					if (confirm == 1) { // If user confirms the input, then proceed.
						break;
					}
					else { // ELse, if they deny the input, then print instructions and begin new loop iteration.
						System.out.println("Enter new Value:");
					}
				}
				else { // If value is less than 9999, then proceed.
					break;
				}
			}
			else { // If match is found, then print error message and begin new loop iteration.
				System.out.println("ERROR: Code already in use. Please try again.");
			}	
		}
		

		// Input and validate product make.
		System.out.println("Product Make:");
		String proMake = Console.validateString();
		if (proMake == null) {
			Console.seperate();
			return null;
		}

		// Input and validate product model.
		System.out.println("Product Model:");
		String proModel = Console.validateString();
		if (proModel == null) {
			Console.seperate();
			return null;
		}

		// Input and validate product price.
		System.out.println("Product Price:");
		double proPrice = Console.validateDouble();
		if (proPrice == -1) {
			Console.seperate();
			return null;
		}

		// Input and validate product QtyAvaliable.
		System.out.println("Product Quantity:");
		int proQty = Console.validateInt();
		if (proQty == -1) {
			Console.seperate();
			return null;
		}

		if(addToExistingSupplier) { // Used to divert method in association with desired function. If true, then adding Product to existing Supplier.
			System.out.println("\n\n                                          |SELECT SUPPLIER|"
					+ "\n\nSelect Supplier providing Product."
					+ "\n________________________________________________________________________________________\n");
			
			while (1 != 2) {
				int input = Console.validateSupplierSelection();
				
				if (input == -1) {
					return null;
				}
				else if (input == -2) {
					DisplayData.printAllSupplierDetails(false);
				}
				else {
					Product placeHolder = new Product(proCode, proMake, proModel, proPrice, proQty, true); // If match found, store new product within placeholder variable.
					Supplier.getSupplierArray().get(input).addSupProduct(placeHolder); // Then add the new product, to the selected Suppliers individualised Product ArrayList.
					
					addAdditionalProduct();
					return null;
				}
			}
		}

		// Else, if adding product to new supplier, then return the contents of a new Product.
		return  new Product(proCode, proMake, proModel, proPrice, proQty, true);
	}	
	
	/**
	 * (Add New Address)
	 * This method provides the user with the ability to create a new Address Instance.
	 * @return new Address([Address Parameters])
	 */
	public static Address addNewAddress() {
		System.out.println("\n\n                                            |ADD NEW ADDRESS|"
				+ "\n\nEnter \"-1\" to RETURN, else Please enter the following details:"
				+ "\n________________________________________________________________________________________\n"
				+ "\nBuilding Number:");

		// Input and Validate Building number variable.
		int bldNum = 0;
		
		while (1 != 2) {
			bldNum = Console.validateInt();
			if (bldNum == -1) { // Check cancel functionality.
				Console.seperate();
				return null;
			}
		
			if (bldNum >= 9999) { // Check if value is deemed as an extreme value.
				int check = Console.largeIntValidation(bldNum); // Store user answer from method.
				if (check == 1) {
					break; // If user confirmed input, then proceed.
				}
				else { // Else, print instructions and begin new loop iteration.
					System.out.println("\nEnter new Value:");
				}
			}
			else { // If value is less than 9999, then proceed.
				break;
			}
		}
		

		// Input and validate Street Name.
		System.out.println("Street Name:");
		String bldStreet = Console.validateString();
		if (bldStreet == null) {
			Console.seperate();
			return null;
		}
		
		// Input and validate Town Name
		System.out.println("Town Name:");
		String bldTown = Console.validateString();
		if (bldTown == null) {
			Console.seperate();
			return null;
		}
		
		// Input and validate PostCode.
		System.out.println("Post Code:");
		String bldPcode = validatePostCode();
		if (bldPcode == null) {
			Console.seperate();
			return null;
		}

		// Input and validate Town Name
		System.out.println("Country Name:");
		String bldCountry = Console.validateString();
		if (bldCountry == null) {
			Console.seperate();
			return null;
		}

		return new Address(bldNum, bldStreet, bldTown, bldPcode, bldCountry);
	}

	
	
	/**
	 * (Product Addition End Menu)
	 * This method operates as a menu, displaying after the addition of a new product. It provides the user with the ability to navigate through the program.
	 * @param supProducts - The ArrayList storing New Products.
	 * @return supProducts
	 */
	public static ArrayList<Product> validateProductNavigation(ArrayList<Product> supProducts) {			
		supProducts.add(addNewProduct(false)); // Create a new Product, and store it within the SupProducts List.

		if (supProducts.get(supProducts.size()-1) == null) { // Check the most recent entry to the ArrayList. If it is null, this indicates that the user has cancelled.
			supProducts.remove(supProducts.size()-1);        // Operation, and therefore return the current contents.
			return supProducts;
		}


		System.out.println("\n                      --------------------------------------------------------------"
				+ "\n                       Enter \"1\" to ADD additional product. Else \"2\" to FINISH. "
				+ "\n                      --------------------------------------------------------------");

		while(1 != 2) {
			int input = Console.validateInt();
			System.out.println("=========================" 
					+ "\nProduct Succefully Added."
					+ "\n=========================\n");

			if (input == -1 || input == 2) { // If user is finished, print confirmation message and return current List.
				System.out.println("=====================" 
						+ "\nFinal product Added."
						+ "\n=====================\n");
				return supProducts;
			}
			else if (input == 1) { // if user wishes to add additional products, then invoke this method again.
				validateProductNavigation(supProducts);
			}
			else {
				System.out.println("ERROR: Invalid selection. Please try again.");
			}
		}
	}


	/**
	 * (Product Addition End Menu)
	 * This method operates in a similar fashion to that present above, however with the intent of adding products seperate to that of a new Supplier.
	 */
	public static void addAdditionalProduct() {
		System.out.println("\n                          --------------------------------------------------------------"
				+ "\n                           Enter \"1\" to ADD additional product. Else \"2\" to FINISH. "
				+ "\n                          --------------------------------------------------------------");

		while (1 != 2) {
			int input = Console.validateInt();
			System.out.println("\n=========================" 
					+ "\nProduct Succefully Added."
					+ "\n=========================\n\n");

			if (input == -1 || input == 2) { // If user is finished, return to Main Menu.
				Console.userMenu();
			}
			else if (input == 1){ // Else, add new Product to a placeholder Variable.
				Product pPlaceholder = addNewProduct(true);
				if (pPlaceholder == null) { // If new product is Null, then user cancelled operation. Print message and return to Main Menu.
					System.out.println("====================================================" 
							+ "\nOperation Cancelled. Returning to Previous Menu."
							+ "\n====================================================\n");
					Console.userMenu();
				}
			}
			else {
				System.out.println("ERROR: Invalid Selection. Please try again.");
			}
		}
	}


	/**
	 * (Supplier Region Validation)
	 * This method is used to print possible Region values, and validate the users input.
	 * @return SupRegion.
	 */
	public static SupRegion validateRegion() {
		System.out.println("Supplier Region (Enter associated value):");
		
		for (int i = 0; i < SupRegion.values().length; i++) { // Print list of all possible Regions (With reference Indexes).
			System.out.println((i+1) + " : " + SupRegion.values()[i].getRegionAsString());
		}

		while (1 != 2) {
			int input = Console.validateInt();

			if (input == -1) { // User cancels operation.
				return null;
			}

			for (int i = 0; i < SupRegion.values().length; i++) { // Search through possible Region Values, looking for a match.
				if (i == (input-1)) {
					return SupRegion.values()[input-1]; // If match found, return the Region at the identified index location.
				}
			}
			System.out.println("ERROR: No match. Please try again."); // If the program reaches this line, then No match has been found. Print Error, and loop.
		}
	}

	/**
	 * (Address PostCode Validation)
	 * This method is used to validate PostCode Input.
	 * @return
	 */
	public static String validatePostCode() {
		while (1 != 2) {
			String input = Console.validateProductModel(); // Uses Initial validation of ProductModel, as it returns a valid string which can possess numerical characters.
			
			boolean space = false;
			String first = "", second = "", third = "";

			input = input.toUpperCase(); // Convert value to upper-case, for reasons regarding visuals and match functionality.

			for (int i = 0; i < input.length(); i++) { // Check the string, and determine whether a Space is present. (This will affect total String size).
				char character = input.charAt(i);
				if (character == ' ') {
					space = true;
				}
			}

			if (input.equals("-1")) { // Cancel, if user enters -1.
				return null;
			}
			else if (input.length() == 8 && space) { // If Input contains a space, it must equal 8 characters in length.
				first = (input.substring(0, 2)); // Separate the first two characters.
				second = (input.substring(2, 6)); // Then the next four (including space).
				third = (input.substring(6, 8)); // And the final two.
			}
			else if (input.length() == 7 && !space) { // Else if, input does not contain a space, then it must equal 7 characters in length.
				first = (input.substring(0, 2)); // Separate the first two characters.
				second = (input.substring(2, 5)); // Then the next three.
				third = (input.substring(5, 7)); // And the final two.
			}
			else {
				System.out.println("ERROR: Value must follow format of [LL00 0LL] (Space Optional). Please try again."); // Otherwise incorrect format.
				validatePostCode(); // Invoke method again.
			}


			hasNumber = number.matcher(first); 
			if (!hasNumber.find()) { // If the first segment does not contain Numerical Characters.
				hasLetter = letter.matcher(second);
				if (!hasLetter.find()) { // If the second segment does not contain Alphabetical Characters.
					hasNumber = number.matcher(third);
					if (!hasNumber.find()) { // If the final segment does not contain Numerical Characters.
						return input; // Then return input as valid PostCode.
					}
					else {
						System.out.println("ERROR: Final 2 Characters must be alphabetical. Please try again.");
					}
				}
				else {
					System.out.println("ERROR: Centre 3 Characters must be numerical. Please try again.");
				}
			}
			else {
				System.out.println("ERROR: First 2 characters must be Alphabetical. Please try again.");
			}




		}
	}





}
