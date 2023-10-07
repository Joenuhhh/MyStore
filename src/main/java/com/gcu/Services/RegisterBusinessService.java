package com.gcu.Services;

import org.springframework.stereotype.Service;

import com.gcu.Interfaces.RegisterBusinessServiceInterface;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Service
public class RegisterBusinessService implements RegisterBusinessServiceInterface{

	
	//A method to save new user data here
	
	@Override
	@PostConstruct
	public void init() {
		//This is where it would connect to a hypothetical database of user data
		//-ScribeEzra
		System.out.println("RegisterBusinessService init() activated");
	}

	@Override
	@PreDestroy
	public void destroy() {
		//This is where it would disconnect to a hypothetical database of user data
		//-ScribeEzra
		System.out.println("RegisterBusinessService destroy() activated");
	}

}
