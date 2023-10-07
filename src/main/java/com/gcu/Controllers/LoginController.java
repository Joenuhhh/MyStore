package com.gcu.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;
import com.gcu.Interfaces.LoginBusinessServiceInterface;
import com.gcu.Modelss.LoginModel;
import com.gcu.Modelss.UserModel;
import com.gcu.Services.UserDataService;
import jakarta.servlet.http.HttpSession;

@Controller // Annotation to define this class as a Spring MVC Controller -Jonah
public class LoginController {

	@Autowired
	LoginBusinessServiceInterface loginBusinessService; // Autowired service to handle login-related business logic
														// -Jonah

	@Autowired
	private UserDataService service; // Autowired service to handle user data operations -Jonah

	// Handler method for GET request to "/login" endpoint
	@GetMapping("/login")
	public String display(Model model) {
		model.addAttribute("title", "Login Form"); // Add title attribute to the model -Jonah
		model.addAttribute("loginModel", loginBusinessService.getLoginModel()); // Add login model to the model -Jonah
		return "login"; // Return the name of the view template -Jonah
	}

	// Handler method for POST request to "/doLogin" endpoint
	@PostMapping("/dohome")
	public String doLogin(@ModelAttribute("loginModel") LoginModel loginModel, BindingResult bindingResult,
			HttpSession session) {
		if (bindingResult.hasErrors()) {
			return "login"; // Return to the login view if there are errors -Jonah
		}

		// Authentication logic
		if (!service.CheckUserModel(loginModel)) {
			bindingResult.rejectValue("password", "password.length", "Invalid Login Information. Please try again."); // Reject if authentication fails - Jonah
																														
			return "login"; // Return to the login view -Jonah
		}

		// Store login information in the session
		UserModel currentUser = service.getCurrentUserModel(loginModel); // Get current user -Jonah
		session.setAttribute("loggedInUser", loginModel); // Store login model in the session -Jonah
		session.setAttribute("currentUser", currentUser); // Store current user in the session -Jonah

		return "redirect:/store"; // Redirect to the store page after successful login -Jonah
	}

	@ModelAttribute("loggedInUser")
	public LoginModel getLoggedInUser(HttpSession session) {
		return (LoginModel) session.getAttribute("loggedInUser"); // Return logged-in user from the session -Jonah
	}

	@ControllerAdvice
	public class GlobalControllerAdvice {

		@ModelAttribute("loggedInUser")
		public LoginModel getLoggedInUser(HttpSession session) {
			return (LoginModel) session.getAttribute("loggedInUser"); // Return logged-in user from the session -Jonah
		}
	}

	// Handler method for GET request to "/orders" endpoint
	@GetMapping("/orders")
	public String display1(Model model) {
		model.addAttribute("title", "My Orders"); // Add title attribute to the model -Jonah
		// Add code to populate order list and set it as a model attribute
		return "orders"; // Return the name of the view template -Jonah
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); // Invalidate the session to log out the user -Jonah
		return "redirect:/store"; // Redirect to the store page after logout -Jonah
	}
}
