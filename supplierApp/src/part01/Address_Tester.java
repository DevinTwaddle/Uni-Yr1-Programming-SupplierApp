package part01;

import java.util.ArrayList;

public class Address_Tester {
	public static ArrayList<Address> addressList = new ArrayList<Address>();

	/**
	 * This method is used to initialise various instances of the Address Object.
	 */
	public static void initaliseAddressData() {
		addressList.add(new Address(10, "Union Street", "Coleraine", "Northern Ireland", "BT52 4RE"));
		addressList.add(new Address(20, "Testing Street", "Belfast", "Northern Ireland", "BT51 5UE"));
		addressList.add(new Address(30, "Corner Street", "Coleraine", "Northern Ireland", "BT54 5JF"));
		addressList.add(new Address(40, "Uptown Street", "Paris", "France", "BT83 9LI"));
	}
	
		
	/**
	 * This method provides the user with the ability to create a new Instance of the address Object, through the use of user Input.
	 * @return
	 */
	public static Address addNewAddress() {
		System.out.println("\n\n                                            |ADD NEW ADDRESS|"
				+ "\n\nPlease enter the following details:"
				+ "\n________________________________________________________________________________________\n"
				+ "\nBuilding Number:");

		// Input and Validate Building number variable.
		int bldNum = Console.sc.nextInt();
		Console.sc.nextLine();

		// Input and validate Street Name.
		System.out.println("Street Name:");

		String bldStreet = Console.sc.nextLine();

		// Input and validate Town Name
		System.out.println("Town Name:");

		String bldTown = Console.sc.nextLine();

		// Input and validate PostCode.
		System.out.println("Post Code:");

		String bldPcode = Console.sc.nextLine();

		// Input and validate Town Name
		System.out.println("Country Name:");

		String bldCountry = Console.sc.nextLine();

		return new Address(bldNum, bldStreet, bldTown, bldPcode, bldCountry);
	}


}
