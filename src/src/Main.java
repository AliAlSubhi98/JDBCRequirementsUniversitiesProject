package src;

import java.util.InputMismatchException;

/*public class Main {

	public static void main(String[] args) {
		APIConsumer.ShowAllCountryInAPI();
		APIConsumer.ShowUniversitiesInCountryEntered();
		JDBC.initializeDatabase();
	}

}*/

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			JDBC myJDBC = new JDBC();
			// Prompt the user for database connection information
			myJDBC.setAccessToDatabase(myJDBC);
			while (true) {
				System.out.println(".........................................................");
				System.out.println("\nMenu:");
				System.out.println("1.  Initialize database");
				System.out.println("2.  Fetch list of countries from API");
				System.out.println("3.  Fetch list of Universities in X Country from API");
				System.out.println("4.  Insert Data into universities Table - JDBC");
				System.out.println("5.  Remove tables from database");
				System.out.println("6.  Print universities");
				System.out.println("7.  Take database backup");
				System.out.println("8.  Fetch data from database");
				System.out.println("9.  Search data by attribute");
				System.out.println("10. Dump data to file");
				System.out.println("11. Retrieve data from file");
				System.out.println("0.  Exit program");
				System.out.println("=========================================================");
				
				int choice;
				try {
					System.out.print("Enter option number: ");
					choice = sc.nextInt();
				} catch (InputMismatchException e) {
					System.err.println("Please enter an integer.");
					sc.next(); // discard non-integer input
					continue; // go back to the beginning of the loop
				}

				// continue with switch statement
				switch (choice) {
				case 1:
					myJDBC.initializeDatabase();
					break;

				case 2:
					// viewListOfCountries();
					APIConsumer.ShowAllCountryInAPI();
					break;
					
				case 3:
					APIConsumer.ShowUniversitiesInCountryEntered();
					break;

				case 4:
					myJDBC.INSERT_INTO_universities();
					break;

				case 5:
					myJDBC.removeTablesFromDatabase();
					break;
					
				case 6:
					APIConsumer.ShowUniversities();
					break;

				case 7:
					 myJDBC.backupDatabase();
					break;

				case 8:
					myJDBC.fetchTablesFromDatabase();// universities table
					break;

				case 9:
					myJDBC.searchFromDatabase();
					break;

				case 10:
					// dumpDataToFile();
					break;
				case 11:
					// retrieveDataFromFile();
					break;
				case 0:
					System.exit(0);
					break;

				default:
					System.err.println("Invalid option number");
					break;
				}
			}
		}
	}
}
