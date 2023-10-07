package com.gcu.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.gcu.Interfaces.LoginBusinessServiceInterface;
import com.gcu.Modelss.LoginModel;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Service
@Primary
public class LoginBusinessService implements LoginBusinessServiceInterface {

	//Returns a login model
	//Would likely be where login data would be compared to database
	//-ScribeEzra
	@Override
	public LoginModel getLoginModel() {
		LoginModel loginModel = new LoginModel();
		
		return loginModel;
	}

	@Override
	@PostConstruct
	public void init() {
		//This is where it would connect to a hypothetical database of user data
		//-ScribeEzra
		System.out.println("LoginBusinessService init() activated");
	}

	@Override
	@PreDestroy
	public void destroy() {
		//This is where it would disconnect from a hypothetical database of user data
		//-ScribeEzra
		System.out.println("LoginBusinessService destroy() activated");
	}

}
