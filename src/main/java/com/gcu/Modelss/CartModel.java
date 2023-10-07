package com.gcu.Modelss;

import java.util.List;

//A cart class to be a property of the user so that a current user has their own cart - Jonah
public class CartModel {
	private List<ProductModel> products;

	public List<ProductModel> getProducts() {
		return products;
	}

	public void addProducts(ProductModel product) {
		this.products.add(product);
	}

	public void removeProducts(ProductModel product) {
		this.products.remove(product);
	}
}
