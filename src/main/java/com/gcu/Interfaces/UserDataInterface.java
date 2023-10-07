package com.gcu.Interfaces;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.gcu.Modelss.LoginModel;
import com.gcu.Modelss.UserModel;

public interface UserDataInterface {
	
	public List<UserModel>findAllUsers();
	public boolean CheckUserModel(LoginModel model);
	public void addUsertoList(UserModel model);
	public UserModel getCurrentUserModel(LoginModel model);
	
	

}
