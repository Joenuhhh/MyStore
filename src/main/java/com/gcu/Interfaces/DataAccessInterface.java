package com.gcu.Interfaces;

import java.util.List;

import com.gcu.Modelss.ProductModel;

public interface DataAccessInterface<T> {
	public List<T> findAllProducts();
	public T findByID(int id);
	public List<ProductModel> findByName(String Name);
	public boolean create(ProductModel product);
	public void update(ProductModel product);
	public void delete(int id);

	
}
