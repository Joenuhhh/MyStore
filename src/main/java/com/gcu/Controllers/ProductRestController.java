package com.gcu.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gcu.Modelss.ProductModel;
import com.gcu.Services.ProductsDataService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {
	// rest api logic. essentially identical to the activity - Jonah

	@Autowired
	private ProductsDataService productService;

	@GetMapping
	public ResponseEntity<List<ProductModel>> getAllProducts() {
		List<ProductModel> products = productService.findAllProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductModel> getProductById(@PathVariable("id") int id) {
		ProductModel product = productService.findByID(id);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductModel> updateProduct(@PathVariable("id") int id, @RequestBody ProductModel product) {
		product.setID(id); // Assuming you have a setter for the ID in your ProductModel
		productService.update(product);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable("id") int id) {
		productService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
