package br.com.fiap.epictask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class LoginController {
	
	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}

	@PostMapping("/login")
	public String login(){
		return "login";
	}



	@GetMapping("/register")
	public String registerForm(){
		return "login";
	}

	@PostMapping("/register")
	public String register(){
		return "login";
	}


}
