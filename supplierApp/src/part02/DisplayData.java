package part02;

import java.util.ArrayList;
import java.util.Collections;

public class DisplayData {
	
	/**
	 * (Primary Menu) Select Object.
	 * This method is simply used to direct the user to differing menus, in association with specific input.
	 */
	public static void printObjectMainMenu() {
		System.out.println("                                        |PRINT INFORMATON|"
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
				printSupplierFilter();
				break;
				
			case 2:
				printProductFilter();
				break;
				
				default: System.out.println("ERROR: Value exceeds range. Please try again.");
			}
		}
	}
	
	/**
	 * (Secondary Menu) Select Supplier Display Method.
	 * This method is also used to direct the user to differing displays, this time in association with the Supplier Object.
	 */
	public static void printSupplierFilter() {
		System.out.println("                                     |SELECT DISPLAY FORMAT|"
				+ "\n\nEnter \"-1\" to return, else Please answer the following."
				+ "\n________________________________________________________________________________________\n"
				+ "\n1: Full Details."
				+ "\n2: Partical Details."
				+ "\n3: Search for Supplier.");
		
		while (1 != 2) {
			// Code block will loop, until input matches a case.
			int input = Console.validateInt(); // Initialise Variable, and then store user Input.
			
			switch(input) {
			case -1: 
				printObjectMainMenu();
				break;
				
			case 1: 
				printAllSupplierDetails(true);
				printContinue("Supplier");
				break;
				
			case 2: 
				printAllSupplierDetails(false);
				printContinue("Supplier");
				break;
				
			case 3: 
				printSupplierByCodeSearch();
				printContinue("Supplier");
				
				default: System.out.println("ERROR: Value excceds range. Please try again.");
			}
		}
	}
	
	/**
	 * (Secondary Menu) Select Product Display Method. 
	 * This method is also used to direct the user to differing displays, this time in association with the Product object.
	 */
	public static void printProductFilter() {
		System.out.println("                                     |SELECT DISPLAY FORMAT|"
				+ "\n\nEnter \"-1\" to return, else Please answer the following."
				+ "\n________________________________________________________________________________________\n"
				+ "\n1: Full Details."
				+ "\n2: Partial Details."
				+ "\n3: Sort by Suppliers."
				+ "\n4: Sort by avalaibility."
				+ "\n5: Search for Product."
				+ "\n6: Search by price range."
				+ "\n7: Search for Product Total Quantity.");
		
		while (1 != 2) {
			int input = Console.validateInt();
			
			switch(input) {
			case -1: 
				printObjectMainMenu();
				break;
				
			case 1: 
				printAllProductDetails(true);
				printContinue("Product");
				break;
				
			case 2: 
				printAllProductDetails(false);
				printContinue("Product");
				
			case 3: 
				printAllProductsBySuppliers();
				printContinue("Product");
				break;
				
			case 4: 
				printProductAvaliabilitySelection();
				printContinue("Product");
				break;
				
			case 5: 
				printProductByCodeSearch();
				printContinue("Product");
				break;
				
			case 6: 
				printProductsByPriceRange();
				printContinue("Product");
				break;
				
			case 7:
				printTotalProductQuantity();
				printContinue("Product");
				
				default: System.out.println("ERROR: Value excceds range. Please try again.");
			}
		}
	}
	
	/**
	 * (Supplier Display Method)
	 * This method is used to print the details of all existing Supplier entities.
	 * @param fullDetails - This parameter is merely used to determine the amount of detail being printed.
	 */
	public static void printAllSupplierDetails(boolean fullDetails) {
		// Initially, a new Supplier ArrayList is generated, storing a duplication of that Returned by the"organiseSuppliersByCode()" method.
		ArrayList<Supplier> organisedSuppliers = new ArrayList<Supplier>(organiseSuppliersByCode());
		
		// This block of code is merely ascetic, printing the correct Operation title.
		if (fullDetails) {
			System.out.println("                                  |PRINT SUPPLIER FULL|");
		}
		else {
			System.out.println("                                 |PRINT SUPPLIER PARTIAL|");
		}
		System.out.println("\n\nPrinting Partial details for all Suppliers."
				+ "\n________________________________________________________________________________________\n");
		
		
		// Run through every Supplier Instance, and print the information in the format previously determined.
		for (Supplier print: organisedSuppliers) {
			if (fullDetails) {
				System.out.println(print.getFullSupplierDetails());
			}
			else {
				System.out.println(print.getPartialSupplierDetails());
			}
		}
	}
	

	/**
	 * (Supplier Display Method)
	 * This method will accept a Supplier Code, entered by the user. Then search through existing entities for a match. 
	 * Then print information associated with the matching instance.
	 */
	public static void printSupplierByCodeSearch() {
		System.out.println("                                    |SUPPLIER SEARCH|"
				+ "\n\nEnter \"-1\" to return, else enter Desired Supplier code."
				+ "\n________________________________________________________________________________________\n");
		
		boolean error = true;
		
		do {
			int input = Console.validateInt();
		    error = true;
			
			if (input == -1) { // If input = -1, then cancel display operation, and return to previous menu.
				printSupplierFilter();
			}
			else { // Run through all instances of the Supplier Object.
				for(int i = 0; i < Supplier.getSupplierArray().size(); i++) {
					if (Supplier.getSupplierArray().get(i).getSupCode() == input) { // If input matches the SupCode of an instance, then break.
						System.out.println(Supplier.getSupplierArray().get(i).getFullSupplierDetails());
						error = false;
					}
				}
				if (error) {
					System.out.println("ERROR: No matches found. Enter \"-1\" to RETURN, else try again.");
				}
			}
			// Loop if no matches are found.
		} while(error);
	}

	/**
	 * (Supplier Sort Function)
	 * This method is used to organise the Supplier Array in incrementing numerical order.
	 * @return organisedSuppliers - An arrayList in which the suppliers are sorted.
	 */
	public static ArrayList<Supplier> organiseSuppliersByCode() {
		ArrayList<Integer> supplierCodes = new ArrayList<Integer>();
		ArrayList<Supplier> organisedSuppliers = new ArrayList<Supplier>();
		
		// Separate the Supplier codes from each supplier, and store them within the designated ArrayList.
		for (int i = 0; i < Supplier.getSupplierArray().size(); i++) {
			supplierCodes.add(Supplier.getSupplierArray().get(i).getSupCode());
		}
		
		// Then use the Collections Class to sort the ArrayList.
		Collections.sort(supplierCodes);
		
		// Then use this new List of SupCodes, matching the values to the corresponding Supplier, and storing them within a new ArrayList.
		for (int i = 0; i < supplierCodes.size(); i++) {
			for (int j = 0; j < Supplier.getSupplierArray().size(); j++) {
				if (supplierCodes.get(i) == Supplier.getSupplierArray().get(j).getSupCode()) {
					organisedSuppliers.add(Supplier.getSupplierArray().get(j));
				}
			}
		}
		return organisedSuppliers; // Then return this organised List.
	}
	
	/**
	 * (Product Sort Function)
	 * This method is used to organise the Product Array in incrementing numerical order.
	 * @return organisedProducts - An arrayList in which the Products are sorted.
	 */
	public static ArrayList<Product> organiseProductsByCode(){
		ArrayList<Integer> productCodes = new ArrayList<Integer>();
		ArrayList<Product> organisedProducts = new ArrayList<Product>();
		
		// Separate the Product codes from each Product, and store them within the designated ArrayList.
		for (int i = 0; i < Product.getProductList().size(); i++) {
			productCodes.add(Product.getProductList().get(i).getProCode());
		}
		
		// Then use the Collections Class to sort the ArrayList.
		Collections.sort(productCodes);
		
		// Then use this new List of ProCodes, matching the values to the corresponding Products, and storing them within a new ArrayList.
		for (int i = 0; i < productCodes.size(); i++) {
			for (int j =0; j < Product.getProductList().size(); j++) {
				if (productCodes.get(i) == Product.getProductList().get(j).getProCode()) {
					organisedProducts.add(Product.getProductList().get(j));
				}
			}
		}
		return organisedProducts; // Then return this organised List.
	}
	
	
	/**
	 * (Product Display Method)
	 * This method is used to print the details of all existing Product entities.
	 * @param fullDetail - This parameter is merely used to determine the amount of detail being printed.
	 */
	public static void printAllProductDetails(boolean fullDetail) {
		// Initially, create a new ArrayList, storing a duplication of that Returned by the "organiseProductsBycode()" method.
		ArrayList<Product> orderedProducts = new ArrayList<Product>(organiseProductsByCode());
		
		// This next block of code is simply used to display the correct Operation title.
		if (fullDetail) {
			System.out.println("                                  |PRINT PRODUCT FULL|");
		}
		else {
			System.out.println("                                 |PRINT PRODUCT PARTIAL|");
		}
		System.out.println("\n\nPrinting Full details for all Products."
				+ "\n________________________________________________________________________________________\n");
		
		// Then run through each instance of the Product object, printing information within the designated format.
		for (Product print: orderedProducts) {
			if (fullDetail) {
				System.out.println(print.getProductDetails());
			}
			else {
				System.out.println(print.getPartialProductDetails());
			}
		}
	}

	/**
	 * (Product Display Method)
	 * This method provides users with the ability to view a specific Product, by referring its code.
	 */
	public static void printProductByCodeSearch(){
		System.out.println("                                     |PRODUCT SEARCH|"
				+ "\n\nEnter \"-1\" to return, else enter Desired Product code."
				+ "\n________________________________________________________________________________________\n");
		
		boolean match = false;
		
		do {
			int input = Console.validateInt();
			
			if (input == -1) {
				printProductFilter(); // If user enters -1, cancel operation and return to previous menu.
			}
			else {
				for(int i = 0; i < Product.getProductList().size(); i++) {
					if (Product.getProductList().get(i).getProCode() == input) {
						System.out.println(Product.getProductList().get(i).getProductDetails()); // If match found, print details.
						match = true;
					}
				}
				if (!match) {
					System.out.println("ERROR: No matches found. Enter \"-1\" to RETURN, else try again.");
				}
			}
			
		} while(!match); // Loop if no match found.
	}
	
	/**
	 * (product Display / Filter Method)
	 * This method provides the user with the ability to identify all products possessing a price, found within a customisable range.
	 */
	public static void printProductsByPriceRange() {
		double firstInput = 0, secondInput = 0, check = 0;

		System.out.println("                                  |PRODUCT PRICE SEARCH|"
				+ "\n\nEnter \"-1\" to return, else enter FIRST price:"
				+ "\n________________________________________________________________________________________\n");

		while (1 != 2) {
			firstInput = Console.validateDouble(); // Stores initial value, as start of range.

			if (firstInput == -1) {
				printProductFilter(); // If input = -1, then return to previous menu.
			}
			else if (firstInput >= 100) { // If input is a relatively extreme number in this context.
				check = Console.largeDoubleValidation(firstInput); // Ask the user for confirmation, and store the returning value of 1 or 2.

				if (check == 1) { // If the user confirmed the value, break from this loop.
					break;
				}
				else { // Else, print instruction and begin new loop iteration.
					System.out.println("Enter new Value:");
				}
			}
			else {
				// If value is not identified as extreme, then break from loop.
				break;
			}
		}

		System.out.println("\nEnter SECOND price.");

		while (1 != 2) {
			secondInput = Console.validateDouble();

			if (secondInput == -1) {
				printProductFilter();
			}

			if (secondInput >= 200) {
				check = Console.largeDoubleValidation(secondInput);

				if (check == 1) {
					if (secondInput > firstInput) { // In this instance, we must check that the value is greater than the first.
						break; // If so, then break from this loop and proceed.
					}
					else { // If value is less than the first, then print error message and begin new iteration of the loop.
						System.out.println("ERROR: Value must be greater than previous. Please try again."); 
					}
				}
				else { // If user did not confirm extreme value, then print instruction, and begin new iteration of the loop.
					System.out.println("Enter new value:");
				}
			}
			else { // If value is not identified as extreme, then simply check that it is greater than the first.
				if (secondInput > firstInput) {
					break; // Exit loop, if valid input detected.
				}
				else {
					System.out.println("ERROR: Value must be greater than previous. Please try again.");
				}
			}
		}

		int noMatch = 0;
		
		System.out.println("                               |PRODUCT PRICE SEARCH RESULTS|"
				+ "\n\nProducts possessing prices within the range: [" + firstInput + " - " + secondInput + "]"
				+ "\n________________________________________________________________________________________\n");
		
		ArrayList<Product> organisedList = new ArrayList<Product>(organiseProductsByCode()); // Invoke the organise Products method, and store the results locally.
		
		// Then proceed to run through each Product instance, looking for values existing within the designated range.
		for (int i = 0; i < organisedList.size(); i++) {
			if (organisedList.get(i).getProPrice() >= firstInput && organisedList.get(i).getProPrice() <= secondInput) {
				System.out.println(organisedList.get(i).getProductDetails()); // If match exists, print details associated with the identified product.
			}
			else {
				// Each time a product is identified to possess details which do not exist within the selected range, then increment this value by 1.
				noMatch++;
			}
		}
		
		

		// This variable is used to detect if no matches where found. As in the scenario where a product is printed, the variable will not increment for that instance.
		// Thus, if a product is printed, the variable will not match the list size.
		if (noMatch == organisedList.size()) {
			System.out.println("No Matches found.");
		}
		
		// This segment of code then acts as the End Navgational menu.
		System.out.println("\n         ---------------------------------------------------------------------------------"
				+ "\n         Enter \"1\" to RETURN. OR \"2\" to start new SEARCH. Else \"3\" to DISPLAY Main menu. "
				+ "\n         ---------------------------------------------------------------------------------");
		while (1 != 2) {
			int input = Console.validateInt();
			
			switch(input) {
			case -1: printProductFilter();
			break;
			
			case 1: printProductFilter();
			break;
			
			case 2: printProductsByPriceRange();
			break;
			
			case 3: Console.userMenu();
			break;
			
			default: 
				System.out.println("ERROR: Invalid selection. Please try again.");
			}
		}
	}

	/**
	 * (Product Display Method)
	 * This method provides the user with the ability to filter products by their assigned availability.
	 */
	public static void printProductAvaliabilitySelection() {
		System.out.println("                                   |FILTER AVAILABILITY|"
				+ "\n\nEnter \"-1\" to return, else Please answer the following:"
				+ "\n________________________________________________________________________________________\n"
				+ "\n1: Avalaible."
				+ "\n2: Not Avalaible.");
		
		int input = 0;
		
		while (1 != 2) {
			input = Console.validateInt();
			
			if (input == -1) {
				printProductFilter();
			}
			if (input < 3) {
				break;
			}
			else {
				System.out.println("ERROR: Invalid Selection. Please try again.");
			}
			
		}
		
		ArrayList<Product> organisedList = new ArrayList<Product>(organiseProductsByCode()); // Invokes the organise Products method, and stores the result locally.
		
		// Run through every product instance.
		for (int i = 0; i < organisedList.size(); i++) {
			if (input == 1 && !organisedList.get(i).isProDiscountinued()){
				System.out.println(organisedList.get(i).getProductDetails()); // If user selects 1, and the product is available then print product in question.
			}
			else if (input == 2 && organisedList.get(i).isProDiscountinued()) {
				System.out.println(organisedList.get(i).getProductDetails()); // If user selects 2, and product is unavailable then print product in question.
			}
		}	
	}
	
	/**
	 * (Product Display Method)
	 * This method provides the user with the ability to see the total quantity of a specific Product of make and model.
	 */
	public static void printTotalProductQuantity() {
		System.out.println("                                  |TOTAL QUANTITY CHECK|"
				+ "\n\nEnter \"-1\" to return, else Please enter the following:"
				+ "\n________________________________________________________________________________________\n"
				+ "\nProduct Make:");
		String proMake = Console.validateString();
		if (proMake == null) {
			printProductFilter();
		}
		
		System.out.println("Product Model:");
		String proModel = Console.validateProductModel();
		if (proMake == null) {
			printProductFilter();
		}
		
		ArrayList<Product> matches = new ArrayList<Product>();
		int totalQuantity = 0;
		
		// Compare Product information to user input.
		for (Product check: Product.getProductList()) {
			if (check.getProMake().equals(proMake) && check.getProModel().equals(proModel) && check.isProDiscountinued() == false) {
				// Match
				matches.add(check); // If match found, add product to local arrayList.
				totalQuantity += check.getProQtyAvailable(); // Increase total quantity.
			}
		}
		
		// Display error message if no matches are found.
		if (totalQuantity == 0) {
			System.out.println("\nERROR: No matches found. Please try again.");
			return;
		}
		else {
			System.out.println("\n\n                                 |TOTAL QUANTITY RESULTS|"
					+ "\n________________________________________________________________________________________\n"
					+ "\nThe total Quantity avaliable for the Product [" + proMake + ", " + proModel + "] is: "+ totalQuantity);

			boolean printContinueMessage = true;
			
			while (1 != 2) {
				if (printContinueMessage){ // This is purely aesthetic, ensuring that this message does appear each time an error occurs.   
					System.out.println("\n\n     ---------------------------------------------------------------------------------------------"
							+ "\n     Enter \"1\" to VIEW additional details, \"2\" to RETURN main menu. Else \"3\" to DISPLAY matches. "
							+ "\n     ---------------------------------------------------------------------------------------------");	
				}
				
				printContinueMessage = true;
				
				int input = Console.validateInt();
				switch(input) {
				case 1: 
					printProductFilter();
					break;
					
				case 2: 
					Console.userMenu();
					break;
					
				case 3:
					// Print the information for the matching products.
					for (Product check: matches) {
						System.out.println(check.getProductDetails());
					}
					break;
					
				default: 
					System.out.println("ERROR: Invalid Selection. Please try again.");
					printContinueMessage = false;
				}
			}

		}

	}

	/**
	 * (Product Display Method)
	 * This method provides the user with the ability to sort the products by their associated supplier.
	 */
	public static void printAllProductsBySuppliers() {
		System.out.println("\n                                       |PRINTING ALL PRODUCTS|"
				+ "\n\n________________________________________________________________________________________\n");
		
		ArrayList<Supplier> organisedSuppliers = new ArrayList<Supplier>(organiseSuppliersByCode());
		
		for(int x = 0; x < organisedSuppliers.size(); x++) {
			System.out.println("\n                                       -----------------------"
					+ "\n                                        Supplier: (" + organisedSuppliers.get(x).getSupCode() + ") " 
					+ organisedSuppliers.get(x).getSupName() + "\n                                       -----------------------");
			
			organisedSuppliers.get(x).printProductLists();
		}	
		printContinue("Product");
	}	

	/**
	 * (Print Navigation Menu)
	 * This method will display at the end of most Display methods, providing the user with the ability to navigate around the program.
	 * @param object - Dictates the specific object previously displayed.
	 */
	public static void printContinue(String object) {
		System.out.println("\n            -----------------------------------------------------------------------"
				+ "\n            Enter \"1\" to VIEW additional details. Else \"2\" to RETURN main menu. "
				+ "\n            -----------------------------------------------------------------------");

		while (1 != 2) {
			int input = Console.validateInt();
			
			if (object == "Product") {
				if (input == 1) {
					printProductFilter();
				}
				else if (input == 2) {
					Console.userMenu();
				}
			}
			else if (object == "Supplier"){
				if (input == 1) {
					printSupplierFilter();
				}
				else if (input == 2) {
					Console.userMenu();
				}
			}
			System.out.println("ERROR: Value exceeds range. Please try again.");
		}
	}
}
