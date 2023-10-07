package com.gcu.Interfaces;

import java.util.List;

import com.gcu.Modelss.UserModel;

public interface UserServiceInterface {
	
	
	public List<UserModel>findAllUsers();
	public boolean CheckUserModel(UserModel model);
	public void init();
	public void destroy();
	
	
}
