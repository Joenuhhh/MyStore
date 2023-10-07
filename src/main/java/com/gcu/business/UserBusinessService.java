package com.gcu.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.gcu.Interfaces.DataAccessInterface;
import com.gcu.Interfaces.UserDataInterface;
import com.gcu.Interfaces.UserServiceInterface;
import com.gcu.Modelss.ProductModel;
import com.gcu.Modelss.UserModel;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Service
@Primary
public class UserBusinessService implements UserServiceInterface
{
	@Autowired
	UserDataInterface Service; // Autowired service to handle user-related data operations -Jonah
	public List<UserModel> Users; // List to store user models -Jonah


	@Override
	public List<UserModel> findAllUsers() 
	{
		Users = Service.findAllUsers(); // Fetch all users from the data access layer (users table of the database.) -Jonah
		return Users; // Return the users -Jonah
	}

	@Override
	public boolean CheckUserModel(UserModel model) {
		return false; // Placeholder method to check a user model; currently always returns false. did not end up using, used the database / registration form to filter out bad models. -Jonah
	}

	@Override
	@PostConstruct
	public void init() {
		//This is where it would connect to a hypothetical database of user data
		//-ScribeEzra
		System.out.println("User DataBase connection running.");
		
	}

	@Override
	@PreDestroy
	public void destroy() {
		//This is where it would disconnect from a hypothetical database of user data
		//-ScribeEzra
		System.out.println("User Database connection terminated.");
		
	}

}
