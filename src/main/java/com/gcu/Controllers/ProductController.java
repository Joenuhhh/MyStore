package com.gcu.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcu.Interfaces.ProductBusinessServiceInterface;
import com.gcu.Interfaces.UserDataInterface;
import com.gcu.Modelss.LoginModel;
import com.gcu.Modelss.ProductModel;
import com.gcu.Modelss.UserModel;
import com.gcu.Services.ProductsDataService;
import com.gcu.Services.UserDataService;
import com.gcu.business.ProductBusinessService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProductController {

	@Autowired
	private ProductBusinessService service;

	@Autowired
	private ProductsDataService productService;

	@GetMapping("/addproduct")
	public String products(Model model) {
		ProductModel product = new ProductModel();
		model.addAttribute("productModel", product);
		return "addproduct";
	}

	
	//display all products to the store page. -Jonah
	@GetMapping("/store")
	public String showProducts(Model model) {
		service.test();
		List<ProductModel> products = productService.findAllProducts(); //get all the products from the products table of the database. - Jonah
		
		//testing purposes. -Jonah
		for (ProductModel product : products) {
			// System.out.println(product.toString()); 
		}

		model.addAttribute("products", products);
		return "store";
	}

	//accessed via the add product button. -Jonah
	@PostMapping("/newProduct")
	public String newProduct(@ModelAttribute("productModel") ProductModel productModel, BindingResult bindingResult,
			Model model, HttpSession session) {
		if (bindingResult.hasErrors()) {
			return "home";
		}
		
		//add a new product to the database -Jonah
		productService.addProductToDB(productModel);
		//console log the product for testing purposes.
		System.out.println("Adding new product: " + productModel.getName() + productModel.getPrice()
				+ productModel.getDescription());
		session.setAttribute("newProduct", productModel);

		//after adding the new product, update the service to have the most recent database entries. -Jonah
		List<ProductModel> products = productService.findAllProducts();

		model.addAttribute("products", products);

		return "store";
	}

	//display all the items in the cart of the CURRENT SESSION - Jonah
	@GetMapping("/cart")
	public String showCart(Model model, HttpSession session) {
		UserModel currentUser = (UserModel) session.getAttribute("currentUser");
		try {
			if (currentUser != null) {
				List<ProductModel> cart = currentUser.getCart();
				double total = cart.stream().mapToDouble(ProductModel::getPrice).sum(); //get the sum of all items in the cart. - Jonah
				model.addAttribute("cart", cart); //save the cart and total attributes to be called on in the next page - Jonah
				model.addAttribute("total", total);
			}
		} catch (Exception E) {
			E.printStackTrace();
		}

		return "cart"; // Return the cart.html page - Jonah
	}

	@GetMapping("/removeFromCart/{itemNumber}")
	public String removeFromCart(@PathVariable("itemNumber") int itemNumber, HttpSession session) {
		UserModel currentUser = (UserModel) session.getAttribute("currentUser");
		try {
			if (currentUser != null) {
				List<ProductModel> cart = currentUser.getCart();
				// Check if the itemNumber is within the bounds of the cart list - Jonah
				if (itemNumber >= 0 && itemNumber < cart.size()) {
					// Remove the product at the specified itemNumber from the cart -Jonah
					cart.remove(itemNumber);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/cart"; // Redirect back to the cart page after removing the product - Jonah
	}

	@GetMapping("/checkout")
	public String showCheckout(HttpSession session, Model model) {
		@SuppressWarnings("unchecked")
		List<ProductModel> purchasedItems = (List<ProductModel>) session.getAttribute("purchasedItems");
		UserModel currentUser = (UserModel) session.getAttribute("currentUser");
		float total = Math.round((Double) session.getAttribute("total"));
		Integer OrderID = (Integer) session.getAttribute("OrderID");
		model.addAttribute("total", total);
		model.addAttribute("products", purchasedItems);
		model.addAttribute("OrderID", "Order Number: " + OrderID);

		productService.insertLog(currentUser, purchasedItems);

		// Optional: Clear the purchased items from the session if you don't need them
		// anymore
		session.removeAttribute("purchasedItems");

		return "checkout";
	}

	@PostMapping("/checkout")
	public String checkout(HttpSession session) {
		UserModel currentUser = (UserModel) session.getAttribute("currentUser"); // Retrieve the current user from the session -Jonah
		try {
			if (currentUser != null) {
				// Store the purchased items
				List<ProductModel> purchasedItems = new ArrayList<>(currentUser.getCart()); // Retrieve the items in the cart -Jonah
				double total = 0; 
				for (ProductModel product : purchasedItems) {
					total += product.getPrice(); // Calculate the total price of the cart -Jonah
					product.setQuantity(product.getQuantity() - 1); // Decrement the product quantity in the database by 1 -Jonah
					productService.update(product); // Update the product information in the database or service -Jonah
				}
				int OrderID = productService.getNextOrderId(); // Retrieve the next available order ID -Jonah
				session.setAttribute("OrderID", OrderID); // Store the order ID in the session -Jonah
				session.setAttribute("purchasedItems", purchasedItems); // Store the purchased items in the session -Jonah
				session.setAttribute("total", total); // Store the total price in the session -Jonah
				// Clear the cart
				currentUser.setCart(new ArrayList<ProductModel>()); // Clear the cart by setting it to an empty list -Jonah

			}
		} catch (Exception e) {
			e.printStackTrace(); // Print the exception stack trace if something goes wrong -Jonah
		}

		return "redirect:/checkout"; // Redirect to the checkout page -Jonah
	}


	@PostMapping("/addToCart")
	public String addToCart(@ModelAttribute("product") ProductModel product, HttpSession session) {
		// Retrieve the current user from the session -Jonah
		UserModel currentUser = (UserModel) session.getAttribute("currentUser");

		if (currentUser != null) {
			// Add the product to the user's cart -Jonah
			currentUser.AddToCart(product);

			// Update the user in the session -Jonah
			session.setAttribute("currentUser", currentUser);
		}
		return "redirect:/store"; // Redirect to the store page -Jonah
	}

	@PostMapping("/searchProduct")
	public String searchProduct(@RequestParam("search") String search, Model model) {
		// Find products by name using the search query -Jonah
		List<ProductModel> products = productService.findByName(search);
		model.addAttribute("products", products); // Add the found products to the model -Jonah
		return "storeSearchResult"; // Return the name of the view template for search results -Jonah
	}

	@PostMapping("/updateProduct")
	public String updateProduct(@ModelAttribute("product") ProductModel product, Model model) {
		// Log the product details received from the form -Jonah
		System.out.println("Product ID received from the form: " + product.toString());
		// Here, you can add the logic to update the product -Jonah
		// Add the updated product object to the model to be used in the Thymeleaf template -Jonah
		model.addAttribute("product", product);

		// Return the "updateProduct.html" template with the updated product details -Jonah
		return "updateProduct";
	}

	@PostMapping("/updatedProduct")
	public String updateProduct(@ModelAttribute("product") ProductModel product) {
		// Log the product ID received from the form -Jonah
		System.out.println("Product ID received from the form: " + product.getID());
		// Update the product using the service -Jonah
		productService.update(product);

		return "redirect:/store"; // Redirect to the store page -Jonah
	}

	@PostMapping("/deleteProduct")
	public String deleteProduct(@ModelAttribute("product") ProductModel product) {
		// Delete the product using the product ID -Jonah
		productService.delete(product.getID());
		return "redirect:/store"; // Redirect to the store page -Jonah
	}



}
