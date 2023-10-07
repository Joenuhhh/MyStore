package com.gcu.Interfaces;

import java.util.List;

import com.gcu.Modelss.ProductModel;

public interface ProductBusinessServiceInterface 
{
	public void test();
	public List<ProductModel> getProducts();
	
	
	//Adding an init() and a destroy() to confirm it's initializing the same time as others
	//-ScribeEzra
	
	public void init();
	public void destroy();
	
	List<ProductModel> addProduct(int ID, String Name, String Category, String Description, int quantity,
			String Manufacturer, double Price);
}
