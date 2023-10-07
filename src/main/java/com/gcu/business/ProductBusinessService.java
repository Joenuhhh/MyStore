package com.gcu.business;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import com.gcu.Interfaces.DataAccessInterface;
import com.gcu.Interfaces.ProductBusinessServiceInterface;
import com.gcu.Modelss.ProductModel;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Service
@Primary
public class ProductBusinessService implements ProductBusinessServiceInterface
{
	@Autowired
	DataAccessInterface<ProductModel> Service; // DataAccessInterface instance to interact with the data storage -Jonah
	public List<ProductModel> products; // List to store products -Jonah
	
	@Override
	public void test() 
	{
		System.out.println("ProductBusinessService test() called"); // Debug message to indicate that the test method has been called -Jonah
	}

	@Override
	public List<ProductModel> getProducts() 
	{
		products = Service.findAllProducts(); // Fetch all products from the data access layer -Jonah
		return products; // Return the products -Jonah
	}

	@Override
	public List<ProductModel> addProduct(int ID, String Name, String Category, String Description, int quantity, String Manufacturer, double Price) 
	{
		products.add(new ProductModel(ID, Name, Category, Description, quantity, Manufacturer, Price)); // Adding a new product to the list -Jonah
		
		return products; // Return the updated products -Jonah
	}

	@Override
	@PostConstruct
	public void init() {
		System.out.println("ProductBusinessService init() activated"); // Debug message to indicate that the initialization has been activated -Jonah
	}

	@Override
	@PreDestroy
	public void destroy() {
		System.out.println("ProductBusinessService destroy() activated"); // Debug message to indicate that the destruction has been activated -Jonah
	}
}
