package part02;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Random;


public class Console {
	
	public static int function;
	public static Scanner sc = new Scanner(System.in);
	public static boolean proceed = false;
	
	public static Pattern symbol = Pattern.compile("[!\"�’$%^&*()_\\-\\=\\+\\[\\]@~#;:,<.>/?\\|]");
	public static Pattern letter = Pattern.compile("[a-zA-Z]");
	public static Pattern number = Pattern.compile("[0-9]");
	
	public static Matcher hasNumber;
	public static Matcher hasLetter;
	public static Matcher hasSymbol;
	
	public static String sub;
	
	public static boolean addToExistingSupplier = false;
	
	public static int selectedProductIndex, selectedSupplierIndex;
	

	public static void main(String[] args) {
		initaliseObjectInformation();
		userMenu();
	}
		

		
	/**
	 * userMenu()
	 * This method is used to display the initial menu, and read user input to access further menus.
	 */
	public static void userMenu() {
		// Menu Display
		System.out.println("                                             |MAIN MENU|"
				+ "\n\nPlease enter value associaed with desired function:"
				+ "\n________________________________________________________________________________________\n"
				+ "\n1: Print Information."
				+ "\n2: Add Information."
				+ "\n3: Update Information."
				+ "\n4: Delete Information."
				+ "\n5: Exit Application.");

		// Valid value must be entered to continue.
		while (1 != 2) {
			int input = validateInt(); // Validates and stores input.

			switch(input){
			case 1:
				DisplayData.printObjectMainMenu();
				break;
			case 2:
				AddData.add_ObjectSelection();
				break;
			case 3: 
				UpdateData.updateObjectSelection();
				//userMenu();
				break;
			case 4: 
				DeleteData.deleteObjectSelection();
			case 5:
				sc.close();
				System.exit(0);
				
			default: System.out.println("ERROR: Value excceds range. Please try again.");
			}
		}
	}
	
	//														| VALIDATION FUNCTIONALITY BLOCKS |
	/**
	 *  This method is used to collect user Input, and then complete various validation processes to identify both valid and invalid values.
	 * @return Double - If input passes all checks, return input as valid Double.
	 */
	public static double validateDouble() {
		while (1 != 2) {
			if(sc.hasNextDouble()) { 
				double input = sc.nextDouble(); // If value matches Double format. Initialise variable, and store user Input.
				
				if (input == -1) { // If Input = -1, return this value for navigational purposes.
					return -1;
				}
				else if (input > 0) { // If input > 0. Then pass this value through the method.
					// Value Accepted
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
	}
	
	/**
	 *  This method is used to collect user Input, and then complete various validation processes to identify both valid and invalid values.
	 * @return Int - If input passes all checks, return input as valid Int.
	 */
	public static int validateInt() {
		while (1 != 2) {
			if(sc.hasNextInt()) {
				int input = sc.nextInt(); // Initialise Variable, and store user Input.
				
				if (input == -1 || input == -2) { // If Input = -1 or -2, return value for navigational and display purposes. 
					return input;
				}
				
				else if (input > 0) { // If Input is positive, and not 0. Then pass Input through method.
					// Value Accepted
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
		
	}
	
	/**
	 *  This method is used to collect user Input, and then complete various validation processes to identify both valid and invalid values.
	 * @return String - If input passes all checks, return input as valid String.
	 */
	public static String validateString() {
		while (1 != 2) {
			String input = sc.nextLine(); // Initialise Variable, and stores user Input.
			
			/* Using Patterns, and Matchers, Checks are conducted upon the Input String.
			   These will run through each character of the String, comparing against the designated patterns. */
			hasNumber = number.matcher(input);
			hasSymbol = symbol.matcher(input);
			
			if (hasNumber.find()) { // If a numerical character is present. (hasNumber.find() == true)
				for(int i = 0; i < input.length()-1; i++) {
					sub = input.substring(i, (i+2));
					if (sub.equals("-1")) { // Check every set of two characters, as to determine if -1 is present. If so, return NULL.
						return null;       
					}
				}
				// ERROR - Number present
				System.out.println("ERROR: Value cannot contain numbers. Please try again.");
			}
			else if (hasSymbol.find()) {
				// ERROR -Symbol Present
				System.out.println("ERROR: Value cannot contain symbols. Please try again.");	
			}
			else if (!hasSymbol.find() && !hasNumber.find()) { // If no Numerical or symbolic characters are found. Return input value.
				return input;
			}
		}
		
		

	}
	
	/**
	 *  This method matches that of Validate String, with some minor modifications.
	 *  This is due to the fact, that the decision was made to allow numerical characters within a product Model.
	 * @return String - If input passes all checks, return input as valid String.
	 */
	public static String validateProductModel() {
		while (1 != 2) {
			String input = sc.nextLine();
			
			hasNumber = number.matcher(input); // Check String for characters detailed within the pattern "number".
			hasSymbol = symbol.matcher(input); // Check String for characters detailed within the pattern "symbol".
			if (hasNumber.find()) {
				// Exit
				for(int i = 0; i < input.length()-1; i++) {
					sub = input.substring(i, (i+2));
					if (sub.equals("-1")) {
						return null;
					}
				}
			}
			if (!hasSymbol.find()) { // In this instance, if the value contains no symbolic characters. It shall be passed through the method.
				return input;
			}
			else {
				// ERROR -Symbol Present
				System.out.println("ERROR: Value cannot contain symbols. Please try again.");	
			}
		}
	}
	
	
	/**
	 * This method is used to validate the selection of a Product Entity.
	 * @return Int - Index of selected Product.
	 */
	public static int validateProductSelection() {
		System.out.println("\n            --------------------------------------------------------------------------"
				+ "\n            Enter \"-2\" to PRINT Products, \"-1\" to RETURN, Else desired PRODUCT CODE "
				+ "\n            --------------------------------------------------------------------------");
		
		while (1 != 2) {
			int input = validateInt(); // Initialise Variable, and store user Input.
			
			if (input == -1 || input == -2) { // If value = -1 or -2, then return this input.
				return input;
			}

			for (int i = 0; i < Product.getProductList().size(); i++) { // Run through all Products.
				if (input == Product.getProductList().get(i).getProCode()) { // If input code, matches an existing Product Code.
					return i; // Return the associated index.
				}
			}
			System.out.println("ERROR: No match found. Please try again."); // If code reaches this point, then no matches were found. Loop again.
		}

	}
	
	/**
	 * This method is used to validate the selection of a Supplier Entity.
	 * @return Int - Index of selected Supplier.
	 */
	public static int validateSupplierSelection() {
		System.out.println("\n\n          ----------------------------------------------------------------------------"
				+ "\n          Enter \"-2\" to PRINT Suppliers, \"-1\" to RETURN, Else desired SUPPLIER CODE "
				+ "\n          ----------------------------------------------------------------------------");
		
		while (1 != 2) {
			int input = Console.validateInt();  // Initialise variable, and store user Input.
			
			if (input == -1 || input == -2) { // If input = -1. or -2, then return the value in question.
				return input;
			}
			
			for (int i = 0; i < Supplier.getSupplierArray().size(); i++) { // Run through all Supplier entities.
				if (input == Supplier.getSupplierArray().get(i).getSupCode()) { // If selected Code matches that of any Supplier Entity.
					return i; // Return associated Index.
				}	
			}
			
			// If the code proceeds to this section, then no matches are present.
			System.out.println("ERROR: No matches found. Please try again.");
			
		}
	}

	/**
	 * This method is merely used as a prompt associated with the input of extreme values.
	 * It will suggest that the value in question is extreme, and ask for confirmation of validity.
	 */
	public static int largeIntValidation(int value) {
		System.out.println("\nCAUTION: Are you sure " + value + " is the value you wish to enter?"
				+ "\n1: Yes."
				+ "\n2: No.");
		while (1 != 2) {
			int input = validateInt();
			if (input < 3) {
				return input;
			}
			else {
				System.out.println("ERROR: Invalid Selection. Please try again.");
			}
		}
	}
	
	public static int largeDoubleValidation(double value) {
		System.out.println("\nCAUTION: Are you sure " + value + " is the value you wish to enter?"
				+ "\n1: Yes."
				+ "\n2: No.");
		while (1 != 2) {
			int input = validateInt();
			if (input < 3) {
				return input;
			}
			else {
				System.out.println("ERROR: Invalid Selection. Please try again.");
			}
		}
	}

	
	/**
	 * This method is used to populate the program with an assortment or relatively random Object Instances.
	 */
	public static void initaliseObjectInformation() {
		Random rnd = new Random();
		int noOfAddresses = 0, noOfProducts = 0, noOfSuppliers = 0;
		
		// This initial block of code is used to randomly generate the total instances of each object.
		while (noOfAddresses < 8) {
			noOfAddresses = rnd.nextInt(20); // This will ensure that there are at-least 8 Addresses present.
		}
		while (noOfProducts < 15) {
			noOfProducts = rnd.nextInt(30); // This will ensure that there are at-least 15 Products present.
		}
		while (noOfSuppliers < 8) {
			noOfSuppliers = rnd.nextInt(20);// This will ensure that there are at-least 8 Suppliers present.
		}
		
		
		// Arrays Detailing varying values for Product based variables.
		String[] proMake = {"MakeOne","MakeTwo","MakeThree","MakeFour","MakeFive","MakeSix","MakeSeven","MakeEight","MakeNine","MakeTen","MakeEleven"};
		String[] proModel = {"Model1","Model2","Model3","Model4","Model5","Model6","Model7","Model8","Model9","Model10","Model11"};
		boolean[] proAvaliable = {true,false};
		
		// Arrays Detailing varying values for Address based variables.
		String[] street = {"Testing Street","Left Avenue","Right Avenue","Up Street","Down Street","Corner Avenue","Diagonal Avenue","Box Street","Triangle Street","Hex Avenue","Proof Street","Curb Avenue"};
		String[] town = {"Antrim","Coleraine","Belfast","BushMills","BallySally","New York","Paris","Moscow","Califonia"};
		String[] country = {"Northern Ireland","Republic of Ireland","England","United States","Russia","Spain","France","CountryA","CountryB","CountryC","CountryD","CountryE"};
		String[] postCode = {"BT12 3ER","BT23 4TR","BT34 5WE","BT45 6FW","BT56 7GR","BT67 8HT","BT78 9BV","BT32 1XC","BT43 2GR","BT54 3JU","BT76 5YT","BT41 3AD"};
		
		// Arrays Detailing varying values for Supplier based variables.
		String[] supNames = {"Alex","Bethany","Chalie","Dexter","Ella","Frank","George","Helan","Ian","Jane","Kane","Linda","Morgan","Nathan","Owen","Paul"};
		SupRegion[] supRegions = {SupRegion.EUROPE, SupRegion.OUTSIDE_EU, SupRegion.UNITED_KINGDOM};
		
		// These ArrayLists would be used to Store the generated object Instances.
		ArrayList<Address> addresses = new ArrayList<Address>();
		ArrayList<Product> products = new ArrayList<Product>();
		ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
		ArrayList<Product> supProducts = new ArrayList<Product>();
		
		// For the total number of instances, randomly generate and select values to use within the creation of new Addresses.
		for (int i = 0; i < noOfAddresses; i++) {
			addresses.add(new Address(rnd.nextInt(200),street[rnd.nextInt(street.length)], town[rnd.nextInt(town.length)], country[rnd.nextInt(country.length)], postCode[rnd.nextInt(postCode.length)]));
		}
		
		// For the total number of instances, randomly generate and select values to use within the creation of new Products.
		for (int i = 0; i < noOfProducts; i++) {
			int proCode = rnd.nextInt(200);
			
			// Check generated product code, against all those currently existing.
			for (int j = 0; j < products.size(); j++) {
				if (proCode == products.get(j).getProCode() || proCode <= 0 || proCode >= 99999) {
					proCode = rnd.nextInt(200); // If a match occurs, or the value exceeds the range designated. Generate new value.
				}
			}
			
			products.add(new Product(proCode, proMake[rnd.nextInt(proMake.length)], proModel[rnd.nextInt(proModel.length)], Math.round(rnd.nextDouble()*100.0), rnd.nextInt(50), proAvaliable[rnd.nextInt(2)]));
		}
		
		// For the total number of instances, randomly generate and select values to use within the creation of new Suppliers.
		for (int i = 0; i < noOfSuppliers; i++) {
			int supCode = rnd.nextInt(200);
			
			// Check new SupCode value against all pre-existing, or 0. If match exists, generate new value.
			for (int j = 0; j < suppliers.size(); j++) {
				if (supCode == suppliers.get(j).getSupCode() || supCode <= 0 || supCode >= 99999) {
					supCode = rnd.nextInt(200);
				}
			}
			
			// Take random products from the Products list, and store them within a separate ArrayList.
			for (int l = 0; l < (rnd.nextInt(8)+1); l++) {
				supProducts.add(products.get(rnd.nextInt(products.size())));
			}
			
			// Then duplicate this ArrayList into a placeholder, for use within the creation of each Suppliers.
			ArrayList<Product> placeholder = new ArrayList<Product>(supProducts);
			
			suppliers.add(new Supplier(supCode, supNames[rnd.nextInt(supNames.length)], addresses.get(rnd.nextInt(addresses.size())), supRegions[rnd.nextInt(supRegions.length)], placeholder));
			// Then clear supProducts, in preparation for the next Supplier.
			supProducts.clear();
		}
	
	}
	
	// These are simple methods used to format the display in various ways.
	public static void seperate() {
		System.out.println("\n\n");
	}
	public static void printCancelMessage() {
		System.out.println("\n====================================================" 
				+ "\nOperation Cancelled. Returning to Previous Menu."
				+ "\n====================================================\n\n");
	}



}
