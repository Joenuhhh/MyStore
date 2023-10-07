package com.gcu.Interfaces;

import java.util.List;

import com.gcu.Modelss.LoginModel;

public interface LoginBusinessServiceInterface {

	//Interface is needed for IoC Container as it is ideal for inheritance
	//-ScribeEzra
	public LoginModel getLoginModel();
	
	public void init();
	
	public void destroy();
}
