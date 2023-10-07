package com.gcu.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gcu.Modelss.RegisterModel;
import com.gcu.Modelss.UserModel;
import com.gcu.Services.UserDataService;

import jakarta.servlet.http.HttpSession;

@Controller
public class RegisterController {
	@Autowired
	private UserDataService service;

	@GetMapping("/register")
	public String loadRegister(Model model) {
		RegisterModel user = new RegisterModel();
		model.addAttribute("user", user);
		return "register";
	}

	@PostMapping("/registerUser")
	public String registerUser(@ModelAttribute("registerModel") RegisterModel registerModel,
			BindingResult bindingResult, HttpSession session) {
		if (bindingResult.hasErrors()) {
			return "register";
		}

		String email = registerModel.getEmail();
		String firstName = registerModel.getFirstName();
		String lastName = registerModel.getLastName();
		String password = registerModel.getPassword();
		String address = registerModel.getAddress();

		System.out.println("USER REGISTERED First Name: " + firstName + " Last Name: " + lastName + " Email: " + email
				+ " Password: " + password + " Address: " + address); //Log Added User to the Console - Jonah
		UserModel newUser = new UserModel(firstName, lastName, password, address, email);// create a usermodel from the registered person - Jonah
																							
		// add that usermodel to the database - Jonah
		service.addUsertoList(newUser);
		session.setAttribute("registeredUser", registerModel);

		return "home";
	}

}
