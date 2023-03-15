package src;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;
public class JDBC {
	String databaseName;
	String userName;
	String password;
	
	public JDBC setAccessToDatabase(JDBC setAccessToDatabase) {
		Scanner sc = new Scanner(System.in);
		System.out.println("==================LOGIN TO THE DATABASE==================");
		System.out.print("Enter database name: ");
		setAccessToDatabase.databaseName = sc.next();
		System.out.print("Enter user name: (sa) ");
		setAccessToDatabase.userName = sc.next();
		System.out.print("Enter password: (root)");
		setAccessToDatabase.password = sc.next();
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
		    
		  /*  String sql3 = "INSERT INTO universities(name, country, state_province, domains, web_pages, alpha_two_code) VALUES (?, ?, ?, ?, ?, ?)";
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
		    System.out.println("Data inserted into universities table!"); */
		    con.close();
		} catch (Exception ex) {
		    System.err.println(ex);
		}

		}
	
	public static void fgdhgfdhdfgh() {
		
		System.out.println("Initialize Database");
		Scanner scanner = new Scanner(System.in);

		// Prompt the user for database connection information
		System.out.println("Enter database name: ");
		String databaseName = scanner.next();
		System.out.print("Enter user name: (sa) ");
		String userName = scanner.next();
		System.out.print("Enter password: (root)");
		String password = scanner.next();
		scanner.close();
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
		    
		  /*  String sql3 = "INSERT INTO universities(name, country, state_province, domains, web_pages, alpha_two_code) VALUES (?, ?, ?, ?, ?, ?)";
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
		    System.out.println("Data inserted into universities table!"); */
		    con.close();
		} catch (Exception ex) {
		    System.err.println(ex);
		}

		}
	}
