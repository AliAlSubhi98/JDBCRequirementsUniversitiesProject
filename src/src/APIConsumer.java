package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;

public class APIConsumer implements Serializable {
	static ArrayList<String> countryList = new ArrayList<>();
	public static University universities[];
	static ArrayList<University> universitiesList = new ArrayList<>();

	public static void ShowUniversitiesInCountryEntered() {

		Scanner sc = new Scanner(System.in);
		System.out.println(
				"\t\t\t\tEnter Country to pick all the Universities Details \n\t\t\t\tFor Ex.( United%20states OR Oman OR all ):::");
		String countryInput = sc.next();
		if (countryInput.equals("all")) {
			countryInput = "";
		}

		String apiUrl = "http://universities.hipolabs.com/search?country=" + countryInput;// United%20states
		try {
			URL url = new URL(apiUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output; // uncahngable -unmutable?
			StringBuilder json = new StringBuilder(); // changable - mutable

			while ((output = br.readLine()) != null) {
				json.append(output);
			}

			conn.disconnect();

			Gson gson = new Gson();
			universities = gson.fromJson(json.toString(), University[].class);

			// Use universities array for further processing
			System.out.println("=============================================================================");
			for (int i = 0; i < universities.length; i++) {
				University university = universities[i];
				System.out.println((i + 1) + ":\t" + university.state_province + " - " + university.country + " - "
						+ university.name + " - " + university.alpha_two_code);

				// iterate over domains
				for (int j = 0; j < university.domains.length; j++) {
					System.out.println("\tDomain " + (j + 1) + ": " + university.domains[j]);
				}

				// iterate over web_pages
				for (int k = 0; k < university.web_pages.length; k++) {
					System.out.println("\tWeb page " + (k + 1) + ": " + university.web_pages[k]);

				}
				System.out.println("=============================================================================");
				universitiesList.add(university);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------
	public static void ShowAllCountryInAPI() {

		String apiUrl = "http://universities.hipolabs.com/search?country=";
		try {
			URL url = new URL(apiUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output; // uncahngable -unmutable?
			StringBuilder json = new StringBuilder(); // changable - mutable

			while ((output = br.readLine()) != null) {
				json.append(output);
			}

			conn.disconnect();

			Gson gson = new Gson();
			universities = gson.fromJson(json.toString(), University[].class);

			// Use universities array for further processing
			int j = 0;
			for (int i = 0; i < universities.length; i++) {
				University university = universities[i];
				if (!countryList.contains(university.country)) {
					j++;
					System.out.println(j + " - " + university.country);
					countryList.add(university.country);
				}
			}
			;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------
	public static void ShowUniversities() {
		if (universities == null || universities.length == 0) {
			System.out.println("No universities found.");
			return;
		}

		System.out.println("=============================================================================");
		for (int i = 0; i < universitiesList.size(); i++) {
			System.out.println(
					(i + 1) + ":\t" + universitiesList.get(i).state_province + " - " + universitiesList.get(i).country
							+ " - " + universitiesList.get(i).name + " - " + universitiesList.get(i).alpha_two_code);

			// iterate over domains
			for (int j = 0; j < universitiesList.get(i).domains.length; j++) {
				System.out.println("\tDomain " + (j + 1) + ": " + universitiesList.get(i).domains[j]);
			}

			// iterate over web_pages
			for (int k = 0; k < universitiesList.get(i).web_pages.length; k++) {
				System.out.println("\tWeb page " + (k + 1) + ": " + universitiesList.get(i).web_pages[k]);

			}
			System.out.println("=============================================================================");
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------
	public static void UniversitiesDataToFile(String fileName) {
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			PrintWriter writer = new PrintWriter(fos);

			// write each university to file
			for (int i = 0; i < universitiesList.size(); i++) {
				writer.println(universitiesList.get(i).state_province + "," + universitiesList.get(i).country + ","
						+ universitiesList.get(i).name + "," + universitiesList.get(i).alpha_two_code);

				for (String domain : universitiesList.get(i).domains) {
					writer.println("\t" + domain);
				}

				for (String webPage : universitiesList.get(i).web_pages) {
					writer.println("\t" + webPage);
				}

				writer.println();
			}

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// --------------------------------------------------------------------------------------------------------------------------

	public static void dumpUniversitiesDataToFile() {
		Scanner sc = new Scanner(System.in);
		String fileName;
		boolean fileExists = true;

		do {
			System.out.print("Enter the name of the file: ");
			fileName = sc.nextLine();

			File file = new File(fileName);
			if (file.exists()) {
				System.out.println("File already exists. Enter another name.");
			} else {
				fileExists = false;
			}
		} while (fileExists);

		try {
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

			objectOut.writeObject(universitiesList);

			objectOut.close();
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------
	/*
	 * public static ArrayList<University> deserializeUniversitiesData(String
	 * filename) { try { FileInputStream fis = new FileInputStream(filename);
	 * ObjectInputStream ois = new ObjectInputStream(fis); ArrayList<University>
	 * universities = (ArrayList<University>) ois.readObject(); ois.close();
	 * fis.close(); System.out.println(universities); return universities; } catch
	 * (IOException ioe) { ioe.printStackTrace(); } catch (ClassNotFoundException
	 * cne) { cne.printStackTrace(); } return null; }
	 */

	public static void deserializeUniversitiesData() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the name of the file to deserialize: ");
		String filename = scanner.next();

		try {
			FileInputStream fis = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(fis);
			ArrayList<University> universities = (ArrayList<University>) ois.readObject();
			ois.close();
			fis.close();

			for (University university : universities) {
				System.out.println(university.name);
				System.out.println(university.country);
				System.out.println(university.state_province);
				System.out.println("Domains:");
				for (String domain : university.domains) {
					System.out.println(domain);
				}
				System.out.println("Web Pages:");
				for (String webPage : university.web_pages) {
					System.out.println(webPage);
				}
				System.out.println("==========");
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException cne) {
			cne.printStackTrace();
		}
	}

}
