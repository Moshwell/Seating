package main;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.model.Person;
import main.services.PersonService;

public class Controller {

	private static PersonService persService = new PersonService();
	
	public static final ResultSet getAllSalles()
	{
		ResultSet myResultSet = null;
		DataBase MyDB = DataBase.getInstance();
		String request = "SELECT * FROM Salles;";
		try
		{
			myResultSet = MyDB.executeQuery(request);
		}
		catch (Exception Ex)
		{
			System.out.println("Anomalie lors de l'execution de la requête: " + Ex.getMessage());
		}
		return myResultSet;
	}
	
	public static final List<String> getAllSallesNames()
	{
		List<String> mySallesList = new ArrayList<>();
		try
		{
			ResultSet myResultSet = getAllSalles();
			while (myResultSet.next())
			{
				mySallesList.add(myResultSet.getString(myResultSet.findColumn("NomSalle")));
			}
		}
		catch (Exception Ex)
		{
			System.out.println("Anomalie lors de l'execution de la requête: " + Ex.getMessage());
		}
		return mySallesList;
	}
	
	public static final ResultSet getOneSalle(String name)
	{
		ResultSet myResultSet = null;
		DataBase MyDB = DataBase.getInstance();
		String request = "SELECT * FROM Salles where NomSalle like '%" + name + "%';";
		try
		{
			myResultSet = MyDB.executeQuery(request);
		}
		catch (Exception Ex)
		{
			System.out.println("Anomalie lors de l'execution de la requête: " + Ex.getMessage());
		}
		return myResultSet;
	}
	
	public static final ResultSet getOneSalle(int id)
	{
		ResultSet myResultSet = null;
		DataBase MyDB = DataBase.getInstance();
		String request = "SELECT * FROM Salles where ID =" + id + ";";
		try
		{
			myResultSet = MyDB.executeQuery(request);
		}
		catch (Exception Ex)
		{
			System.out.println("Anomalie lors de l'execution de la requête: " + Ex.getMessage());
		}
		return myResultSet;
	}
	
	public static final int getColsFromSalle(String nomSalle)
	{
		try {
			ResultSet RS = getOneSalle(nomSalle);
			if (RS.first() == true)
			{
				return RS.getInt("NbCols");
			}
			else
			{
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static final int getRowsFromSalle(String nomSalle)
	{
		try {
			ResultSet RS = getOneSalle(nomSalle);
			if (RS.first() == true)
			{
				return RS.getInt("NbRows");
			}
			else
			{
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static final List<String> getAllNomPrenom() throws IOException
	{
		List<String> myNomPrenom = new ArrayList<>();
		List<Person> listP = persService.getAllPersons();
		for (int i = 0; i < listP.size(); i++) {
			Person person = listP.get(i);
			String concat = person.getId() +"-"+ person.getName() +" "+  person.getSurname();
			myNomPrenom.add(concat);
		}
		/*try
		{
			ResultSet myResultSet = null;
			DataBase MyDB = DataBase.getInstance();
			String request = "SELECT CONCAT(Nom, ' ', Prenom) AS NomPrenom FROM Personnes;";
			try
			{
				myResultSet = MyDB.executeQuery(request);
			}
			catch (Exception Ex)
			{
				System.out.println("Anomalie lors de l'execution de la requête: " + Ex.getMessage());
			}
			while (myResultSet.next())
			{
				myNomPrenom.add(myResultSet.getString(myResultSet.findColumn("NomPrenom")));
			}
		}
		catch (Exception Ex)
		{
			System.out.println("Anomalie lors de l'execution de la requête: " + Ex.getMessage());
		}*/
		return myNomPrenom;
	}
	
	public static final void createPerson(String Nom, String Prenom) throws IOException {
		persService.createPerson(Nom, Prenom);
		
	}
	
	public static final void deletePerson(String id) throws IOException {
		String[] idDef = id.split("-");
		persService.deletePersonByID(Integer.parseInt(idDef[0]));
	}
	

}
