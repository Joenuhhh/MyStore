package com.gcu.Modelss;


import jakarta.validation.constraints.Size;

//Model for the Login Page. Rejects entries that are too short - Jonah
public class LoginModel {
	@Size(min = 8, max = 20, message = "Username must be between 8 and 20 characters")
    private String username;

    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    private String password;
    
    private boolean loggedIn = false;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
}

   
