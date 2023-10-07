package com.gcu.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcu.ProductList;
import com.gcu.Interfaces.ProductBusinessServiceInterface;
import com.gcu.Modelss.ProductModel;

@RestController
@RequestMapping("/service")
public class ProductRestService 
{
	@Autowired
	private ProductBusinessServiceInterface service; // Autowired service to handle product-related business logic -Jonah
	
	@GetMapping(path="json", produces={MediaType.APPLICATION_JSON_VALUE}) // Maps a GET request to "/service/json", produces JSON response -Jonah
	public List<ProductModel> getProductsAsJson()
	{
		return service.getProducts(); // Returns a list of products as JSON -Jonah
	}
	
	@GetMapping(path="xml", produces={MediaType.APPLICATION_XML_VALUE}) // Maps a GET request to "/service/xml", produces XML response -Jonah
	public List<ProductModel> getProductsAsXml()
	{
		ProductList list = new ProductList(); // Creates a ProductList object -Jonah
		list.setProducts(service.getProducts()); // Sets the products from the service into the list -Jonah
		return service.getProducts(); // Returns a list of products as XML -Jonah
	}
}
