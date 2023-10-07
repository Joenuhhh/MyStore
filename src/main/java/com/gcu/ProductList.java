package com.gcu;

import java.util.ArrayList;
import java.util.List;

import com.gcu.Modelss.ProductModel;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="products")
public class ProductList 
{
	private List<ProductModel> products = new ArrayList<ProductModel>();
	
	@XmlElement(name="order")
	public List<ProductModel> getProducts()
	{
		return this.products;
	}
	
	public void setProducts(List<ProductModel> products)
	{
		this.products = products;
	}
}
