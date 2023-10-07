package com.gcu.Controllers;
import com.gcu.*;
import com.gcu.Modelss.LoginModel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;


//did not end up using a home page. the page still exists for testing purposes only. The site default page is store. - Jonah

@Controller
public class HomeController {

	@GetMapping("/home")
	public String home(Model model, HttpSession session) {
	    // Check if the user is logged in
	    LoginModel loggedInUser = (LoginModel) session.getAttribute("loggedInUser");

	    if (loggedInUser != null) {
	        // User is logged in, so redirect to the "orders" page
	        return "orders";
	    }

	    // User is not logged in, display the home page
	    model.addAttribute("title", "Home Page");
	    return "home";
	}

    
}
