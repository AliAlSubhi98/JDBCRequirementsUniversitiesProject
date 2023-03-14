package src;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
public class JDBC {

	public static void initializeDatabase() {
		Scanner scanner = new Scanner(System.in);

		// Prompt the user for database connection information
		System.out.print("Enter database name: ");
		String databaseName = scanner.nextLine();
		System.out.print("Enter user name: (sa) ");
		String userName = scanner.next();
		System.out.print("Enter password: (root)");
		String password = scanner.next();

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

		    System.out.println("Tables created successfully!");
		    con.close();
		} catch (Exception ex) {
		    System.err.println(ex);
		}

		}
	}
