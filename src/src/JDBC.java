package src;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
public class JDBC {
	static String databaseName = "ali";
	static String userName;
	static String password;
	
		public JDBC setAccessToDatabase(JDBC setAccessToDatabase) {
		Scanner sc = new Scanner(System.in);
		System.out.println("==================LOGIN TO THE DATABASE==================");
		//System.out.print("Enter database name: ");
		//setAccessToDatabase.databaseName = sc.next();
		System.out.print("Enter user name: (sa) ");
		JDBC.userName = sc.next();
		System.out.print("Enter password: (root)");
		JDBC.password = sc.next();
		System.out.println("=========================================================");
		return setAccessToDatabase;
		
	}
		public void initializeDatabase() {
		
		System.out.println("Initialize Database");
		Scanner scanner = new Scanner(System.in);


		String url = "jdbc:sqlserver://" + "localhost:1433;" +
		        "encrypt=true;" +
		        "trustServerCertificate=true";
		Connection con = null;

		try {
		    Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		    DriverManager.registerDriver(driver);

		    con = DriverManager.getConnection(url, userName, password);
		    Statement st = con.createStatement();

		    // Create database if it doesn't exist
		    String sql1 = "IF NOT EXISTS(SELECT * FROM sys.databases WHERE name='" + databaseName + "')\r\n"
		            + "CREATE DATABASE " + databaseName;
		    st.executeUpdate(sql1);
		    System.out.println("---------------DATABASE CREATED---------------");
		    // Update url with the database name
		    url += ";databaseName=" + databaseName;
		    con = DriverManager.getConnection(url, userName, password);
		    Statement st2 = con.createStatement();

		    // Create table if it doesn't exist
		    String sql2 = "IF OBJECT_ID(N'dbo.universities', N'U') IS NULL\r\n"
		            + "CREATE TABLE universities (\r\n"
		            + "  id INTEGER IDENTITY PRIMARY KEY,\r\n"
		            + "  name VARCHAR(255),\r\n"
		            + "  country VARCHAR(255),\r\n"
		            + "  state_province VARCHAR(255),\r\n"
		            + "  domains VARCHAR(MAX),\r\n"
		            + "  web_pages VARCHAR(MAX),\r\n"
		            + "  alpha_two_code VARCHAR(2)\r\n"
		            + ");";
		    st2.executeUpdate(sql2);
		    System.out.println("universities Table created successfully!");
		    
		    con.close();
		} catch (Exception ex) {
		    System.err.println(ex);
		}

		}
	
		public void INSERT_INTO_universities() {

			System.out.println("TRYING TO INSERT INTO universities");

			String url = "jdbc:sqlserver://" + "localhost:1433;" + "encrypt=true;" + "trustServerCertificate=true";
			Connection con = null;

			try {
				Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
				DriverManager.registerDriver(driver);

				con = DriverManager.getConnection(url, userName, password);
				Statement st = con.createStatement();

				// Update url with the database name
				url += ";databaseName=" + databaseName;
				con = DriverManager.getConnection(url, userName, password);

				String sql3 = "INSERT INTO universities(name, country, state_province, domains, web_pages, alpha_two_code) VALUES (?, ?, ?, ?, ?, ?)";
				PreparedStatement ps = con.prepareStatement(sql3);
				University[] universities = APIConsumer.universities;
				for (University university : universities) {
					ps.setString(1, university.name);
					ps.setString(2, university.country);
					ps.setString(3, university.state_province);
					ps.setString(4, String.join(",", university.domains));
					ps.setString(5, String.join(",", university.web_pages));
					ps.setString(6, university.alpha_two_code);
					ps.executeUpdate();
				}
				System.out.println("Data inserted into universities table!");
				con.close();
			} catch (Exception ex) {
				System.err.println(ex);
			}

		}
		public void removeTablesFromDatabase() {
			
			System.out.println("TRYING TO REMOVE universities TABLE ");

			String url = "jdbc:sqlserver://" + "localhost:1433;" + "encrypt=true;" + "trustServerCertificate=true";
			Connection con = null;

			try {
				Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
				DriverManager.registerDriver(driver);
				
				// Update url with the database name
				url += ";databaseName=" + databaseName;
				con = DriverManager.getConnection(url, userName, password);
				Statement st2 = con.createStatement();

				// Create table if it doesn't exist
				String sql2 = "drop table universities;";
				st2.executeUpdate(sql2);

				System.out.println("universities TABLE REMOVED SUCCESSFULLY");
				con.close();
			} catch (Exception ex) {
				System.err.println(ex);
			}
		}
	
		public void fetchTablesFromDatabase() {
			
			System.out.println("TRYING TO FETCH universities TABLE ");

			String url = "jdbc:sqlserver://" + "localhost:1433;" + "encrypt=true;" + "trustServerCertificate=true";
			Connection con = null;

			try {
				Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
				DriverManager.registerDriver(driver);
				
				// Update url with the database name
				url += ";databaseName=" + databaseName;
				con = DriverManager.getConnection(url, userName, password);
				Statement st = con.createStatement();

				String sql = "SELECT * FROM universities";
				ResultSet rs = st.executeQuery(sql);

				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String country = rs.getString("country");
					String state_province = rs.getString("state_province");
					String domains = rs.getString("domains");
					String web_pages = rs.getString("web_pages");
					String alpha_two_code = rs.getString("alpha_two_code");
					System.out.println(id + ", " + name + ", " + country + ", " + state_province + ", " + domains + ", "
							+ web_pages + ", " + alpha_two_code);
				}
				System.out.println("universities TABLE FETCHED SUCCESSFULLY");
				con.close();
			} catch (Exception ex) {
				System.err.println(ex);
			}
		}
		
		public void backupDatabase() {
			
			System.out.println("TRYING TO BACKUP DATABASE ");

			String url = "jdbc:sqlserver://" + "localhost:1433;" + "encrypt=true;" + "trustServerCertificate=true";
			Connection con = null;

			try {
				Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
				DriverManager.registerDriver(driver);
				
				// Update url with the database name
				url += ";databaseName=" + databaseName;
				con = DriverManager.getConnection(url, userName, password);
			    Statement st2 = con.createStatement();

			    // Create table if it doesn't exist
			    String sql2 = "BACKUP DATABASE ali\r\n"
			    		+ "TO DISK = 'C:\\Users\\Lenovo\\eclipse-workspace\\JDBCRequirementsUniversitiesProject\\Backup\\Backup.bak';;";
			    st2.executeUpdate(sql2);
				
				System.out.println("BACKUP DATABASE SUCCESSFULLY");
				con.close();
			} catch (Exception ex) {
				System.err.println(ex);
			}
		}

		public void searchFromDatabase() {
			System.out.println("SEARCH FOR UNIVERSITIES IN DATABASE");

			String url = "jdbc:sqlserver://" + "localhost:1433;" + "encrypt=true;" + "trustServerCertificate=true";
			Connection con = null;

			try {
				Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
				DriverManager.registerDriver(driver);

				// Update url with the database name
				url += ";databaseName=" + databaseName;
				con = DriverManager.getConnection(url, userName, password);
				Statement st = con.createStatement();

				Scanner scanner = new Scanner(System.in);
				System.out.println("Search by name (n), country (c), or alpha_two_code (a)?");
				String searchType = scanner.next().toLowerCase();
				System.out.println("Enter search term:");
				String searchTerm = scanner.next();

				String sql = "";
				switch (searchType) {
				case "n":
					sql = "SELECT DISTINCT name, country, state_province, domains, web_pages, alpha_two_code FROM universities WHERE name LIKE '"
							+ searchTerm + "%';";
					break;
				case "c":
					sql = "SELECT DISTINCT name, country, state_province, domains, web_pages, alpha_two_code FROM universities WHERE country LIKE '"
							+ searchTerm + "%';";
					break;
				case "a":
					sql = "SELECT DISTINCT name, country, state_province, domains, web_pages, alpha_two_code FROM universities WHERE alpha_two_code LIKE '"
							+ searchTerm + "';";
					break;
				default:
					System.err.println("Invalid search type.");
					break;
				}

				ResultSet rs = st.executeQuery(sql);

				while (rs.next()) {
					String name = rs.getString("name");
					String country = rs.getString("country");
					String state_province = rs.getString("state_province");
					String domains = rs.getString("domains");
					String web_pages = rs.getString("web_pages");
					String alpha_two_code = rs.getString("alpha_two_code");
					System.out.println("---------------------------------------------------------");
					System.out.println("Name: " + name);
					System.out.println("Country: " + country);
					System.out.println("State/Province: " + state_province);
					System.out.println("Domains: " + domains);
					System.out.println("Web Pages: " + web_pages);
					System.out.println("Alpha Two Code: " + alpha_two_code);
					System.out.println(".........................................................");
				}

				con.close();
			} catch (Exception ex) {
				System.err.println(ex);
			}
		}

}
