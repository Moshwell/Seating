package main.model;

public class Person {
	
	private Integer id;
	
	private String createdAt;
	
	private String updatedAt;
	
	private String name;
	
	private String surname;
	
	public Integer getId() {
	    return id;
	}
	
	public void setId(Integer id) {
	    this.id = id;
	}
	
	public String getName() {
	    return name;
	}
	
	public void setName(String name) {
	    this.name = name;
	}
	
	public String getSurname() {
	    return surname;
	}
	
	public void setSurname(String surname) {
	    this.surname = surname;
	}
	
	public String getCreatedAt() {
	    return createdAt;
	}
	
	public void setCreatedAt(String date) {
	    this.createdAt = date;
	}
	
	public String getUpdatedAt() {
	    return updatedAt;
	}
	
	public void setUpdatedAt(String date) {
	    this.updatedAt = date;
	}
}