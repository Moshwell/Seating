package main.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import main.model.Room;

public class RoomService {
	
	private URL url;
	
	public URL createURL(String path) {
		
		try {
		    url = new URL("http://localhost:8097" + path);
			
		} 
		catch (MalformedURLException e) {
		    // exception handler code here
		    // ...
		}
		return url;
		
	}

	public void createRoom() {
		

		
	}

	public List<Room> getAllRooms() throws IOException {
		url = createURL("/api/rooms");
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
				
		System.out.println(content);
		List<Room> roomList = new ArrayList<>();
		connex.connect();
		connex.disconnect();
		return roomList;
	}
	
	public Room getRoomByID() {
		
		Room room = new Room();
		return room;
		
	}
	
	public void setRoomByID() {
		
	}
	
	public void deleteRoomByID() {
		
	}
}
