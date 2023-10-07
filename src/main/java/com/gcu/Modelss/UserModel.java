package com.gcu.Modelss;
import java.util.ArrayList;
import java.util.List;


//Model associated with the user database. Create one per log in session, and add items to said models cart. 
//Cart empties on checkout. And does not store to any database unless the user checks out. - Jonah
public class UserModel {
	private String Email;
	private String FirstName;
	private String LastName;
	private String Password;
	private String Address;
	private List<ProductModel> Cart;
	
	public UserModel(String FirstName, String LastName, String Password, String Address, String Email) {
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.Password = Password;
		this.Address = Address;
		this.Email = Email;
		this.Cart = new ArrayList<>();
	}

	

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

	public List<ProductModel> getCart() {
		return Cart;
	}

	public void AddToCart(ProductModel product) {
		Cart.add(product);
	}



	public void setCart(List<ProductModel> list) {
		Cart = list;
		
	}


}
