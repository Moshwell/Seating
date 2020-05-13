package main.services;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import main.model.Person;

public class PersonService {
	private URL url;
	
	public URL createURL(String path) {
		
		try {
		   URL baseURL = new URL("http://localhost:8097");
		   url = new URL(baseURL, path);
			
		} 
		catch (MalformedURLException e) {
		    // exception handler code here
		    // ...
		}
		return url;
		
	}

	public void createPerson(String Nom, String Prenom) throws IOException {
		url = createURL("/api/members");
		String body = "{" + 
				"\"createdAt\":\"" + new java.util.Date().getTime() + "\",\r\n" + 
				"\"updatedAt\":\"" + new java.util.Date().getTime() + "\",\r\n" + 
				"\"name\":\"" + Nom + "\",\r\n" + 
				"\"surname\":\""+ Prenom  + "\"\r\n" + 
				"}";
		HttpURLConnection connex = (HttpURLConnection) url.openConnection();
		
		connex.setRequestProperty("Content-type", "application/json; utf-8");
		connex.setRequestProperty("Content-Length", Integer.toString(body.length()));
		connex.setRequestMethod("POST");
		connex.setDoOutput(true);
		
		OutputStream os = connex.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");    
		osw.write(body);
		osw.flush();
		osw.close();
		os.close();
		
		connex.connect();
		System.out.println(connex.getResponseCode());
		System.out.println(connex.getResponseMessage());
		connex.disconnect();


		
	}

	public List<Person> getAllPersons() throws IOException {
		url = createURL("/api/members");
		HttpURLConnection connex = (HttpURLConnection) url.openConnection();
		connex.setRequestMethod("GET");
		
		BufferedReader in = new BufferedReader(
				  new InputStreamReader(connex.getInputStream()));
				String inputLine;
				StringBuffer content = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
				    content.append(inputLine);
				}
				in.close();
				List<Person> personList = new ArrayList<>();
				ObjectMapper objectMapper = new ObjectMapper();
		
		String contentString = content.toString().replaceAll("},", "};");
		String b = contentString.replaceAll("\\[|\\]", "");
		String[] response = b.split(";");
		
		for (int i = 0 ; i < response.length ; i++) {
			Person person = objectMapper.readValue(response[i].toString(), Person.class);	
			personList.add(person);
		}
		connex.connect();
		connex.disconnect();
		return personList;
	}
	
	public Person getPersonByID(Integer id) throws IOException {
		String urlString = "/api/members/" + id ;
		url = createURL(urlString);
		HttpURLConnection connex = (HttpURLConnection) url.openConnection();
		connex.setRequestMethod("GET");
		
		BufferedReader in = new BufferedReader(
				  new InputStreamReader(connex.getInputStream()));
				String inputLine;
				StringBuffer content = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
				    content.append(inputLine);
				}
				in.close();
				
		ObjectMapper objectMapper = new ObjectMapper();
		Person person = objectMapper.readValue(content.toString(), Person.class);
		connex.connect();
		connex.disconnect();
		return person;
		
	}
	
	public void setPersonByID() {
		
	}
	
	public void deletePersonByID(Integer id) throws IOException {
		String urlString = "/api/members/" + id ;
		url = createURL(urlString);
		HttpURLConnection connex = (HttpURLConnection) url.openConnection();
		connex.setDoOutput(true);
		connex.setRequestMethod("DELETE");
		connex.connect();
		System.out.println(connex.getResponseCode());
		System.out.println(connex.getResponseMessage());
		connex.disconnect();
	}
}
