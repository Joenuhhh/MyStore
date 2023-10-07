package com.gcu;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import com.gcu.Interfaces.LoginBusinessServiceInterface;
import com.gcu.Interfaces.ProductBusinessServiceInterface;
import com.gcu.Interfaces.RegisterBusinessServiceInterface;
import com.gcu.Services.LoginBusinessService;
import com.gcu.Services.ProductsDataService;
import com.gcu.Services.RegisterBusinessService;
import com.gcu.Services.UserDataService;
import com.gcu.business.ProductBusinessService;

@Configuration
public class SpringConfig {
	
	//This Configuration file sets up the beans and their init and destroy methods
	//-ScribeEzra
	@Autowired
	DataSource data;

	@Autowired
	private LoginBusinessServiceInterface service;
	
	@Bean(name="loginService", initMethod="init", destroyMethod="destroy")
	@SessionScope
	public LoginBusinessServiceInterface getLoginBusiness()
	{
		return  new LoginBusinessService();
	}
	
	@Bean(name="registerService", initMethod="init", destroyMethod="destroy")
	@SessionScope
	public RegisterBusinessServiceInterface getRegisterBusiness()
	{
		return  new RegisterBusinessService();
	}
	
	@Bean(name="productService", initMethod="init", destroyMethod="destroy")
	@SessionScope
	public ProductBusinessServiceInterface getProductBusiness()
	{
		return  new ProductBusinessService();
	}
	@Bean(name="productsDAO")
	@RequestScope
	public ProductsDataService getDataService() 
	{
		return new ProductsDataService(data);
	}
	
	@Bean(name="usersDAO")
	@RequestScope
	public UserDataService getDataService2() 
	{
		return new UserDataService(data);
	}

}
