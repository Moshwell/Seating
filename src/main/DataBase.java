package main;

import java.sql.*;

// Trying to implement the singleton design pattern
public final class DataBase {
	// Instance
	private static DataBase Instance = new DataBase();
	// Attributes
	private Connection connection = null;
	private final String url = "jdbc:mysql://51.75.26.21:27713/SeatingPlan";
	private final String user = "cesiuser";
	private final String password = Decrypter.getDBPassword();

	// Constructor => Try to find the JDBC Driver and connect to the specified database
	private DataBase()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection(url,  user, password);
		}
		catch (ClassNotFoundException Ex)
		{
			System.out.println("Class not found: " + Ex.getMessage());
		}
		catch(SQLException SQLEx)
		{
			System.out.println("Connexion impossible à la base de données: " + SQLEx.getMessage());
		}
	}
	
	// Get instance => Return the instance and instantiate if it's null or closed
	public final static DataBase getInstance()
	{
		try
		{
			if (Instance == null) {
				Instance = new DataBase();
			} else if (Instance.connection.isClosed()) {
				Instance = new DataBase();
			}
		}
		catch (SQLException SQLEx)
		{
			System.out.println("Connexion impossible à la base de données: " + SQLEx.getMessage());
		}
		return Instance;
	}
	
	// Execute query
	public final ResultSet executeQuery(String query)
	{
		ResultSet myResultSet = null;
		try
		{
			Statement stmt = connection.createStatement();
			myResultSet = stmt.executeQuery(query);
		}
		catch (SQLException SQLEx)
		{
			System.out.println("Erreur à executeQuery(string query): " + SQLEx.getMessage());
		}
		return myResultSet;
	}
}
