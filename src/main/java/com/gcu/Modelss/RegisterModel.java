package com.gcu.Modelss;


//Model created when users register. Properties are later reassigned to be UserModels for the database. - Jonah
public class RegisterModel {

	private String Email;
	private String FirstName;
	private String LastName;
	private String Password;
	private String Address;
	

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}
}
