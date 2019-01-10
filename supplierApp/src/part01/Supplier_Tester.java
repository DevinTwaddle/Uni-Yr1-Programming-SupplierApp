package part01;




public class Supplier_Tester {
	
	public static void initaliseSupplierData() {
		Supplier s1 = new Supplier(10, "Alex", Address_Tester.addressList.get(0), SupRegion.UNITED_KINGDOM, Product_Tester.pList1);
		Supplier s2 = new Supplier(20, "Brett", Address_Tester.addressList.get(1), SupRegion.OUTSIDE_EU, Product_Tester.pList2);
		Supplier s3 = new Supplier(30, "Charlie", Address_Tester.addressList.get(3), SupRegion.EUROPE, Product_Tester.pList1);
		Supplier s4 = new Supplier(40, "Dave", Address_Tester.addressList.get(2), SupRegion.UNITED_KINGDOM, Product_Tester.pList4);
		Supplier s5 = new Supplier(80, "Elvis", Address_Tester.addressList.get(2), SupRegion.OUTSIDE_EU, Product_Tester.pList2);
		Supplier s6 = new Supplier(60, "Frank", Address_Tester.addressList.get(1), SupRegion.EUROPE, Product_Tester.pList3);
		Supplier s7 = new Supplier(50, "Gillian", Address_Tester.addressList.get(3), SupRegion.OUTSIDE_EU, Product_Tester.pList4);
		Supplier s8 = new Supplier(90, "Hidla", Address_Tester.addressList.get(0), SupRegion.EUROPE, Product_Tester.pList3);
	}
		/**
		 * addNewSupplier()
		 * This method will be used to read and validate input, as to create a new instance of the Supplier object.
		 * @return 
		 */
		public static Supplier addNewSupplier() {
			System.out.println("\n\n                                            |ADD NEW SUPPLIER|"
					+ "\n\nPlease enter the following details:"
					+ "\n________________________________________________________________________________________\n"
					+ "\nSupplier Code:");

			// Input and validate Supplier Code variable.
			int supCode = Console.sc.nextInt();
			Console.sc.nextLine();
			
			// Input and Validate Supplier Name Variable.
			System.out.println("Supplier Name:");

			String supName = Console.sc.nextLine();
			
			// Input and validate selected value.
			SupRegion supRegion = validateRegion();
			
			// Run addNewAddress method.
			Address supAddress = Address_Tester.addNewAddress();
			
			System.out.println("============================" 
					+ "\nSupplier Succefully added."
					+ "\n============================\n\n");

			return new Supplier(supCode, supName, supAddress, supRegion, null);
		}

		// This method is used to validate the user input, as to assign a Region.
		public static SupRegion validateRegion() {
			// Output list of SubRegion values.
			System.out.println("Supplier Region (Enter associated value):");
			for (int i = 0; i < SupRegion.values().length; i++) {
				System.out.println((i+1) + " : " + SupRegion.values()[i].getRegionAsString());
			}

			int input = Console.validateInt();
			for (int i = 0; i < SupRegion.values().length; i++) {
				if (i == (input-1)) {
					return SupRegion.values()[input-1];
				}
			}
			return null;
		}

}


