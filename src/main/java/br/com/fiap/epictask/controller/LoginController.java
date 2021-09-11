package br.com.fiap.epictask.controller;

import br.com.fiap.epictask.controller.dto.UserForm;
import br.com.fiap.epictask.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class LoginController {

	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}

	@PostMapping("/login")
	public String login(){
		return "redirect:/users";
	}

	@GetMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute("userForm", new UserForm());
		return "register";
	}

	@PostMapping("/register")
	public String register(@Valid UserForm form, BindingResult result) {
		if (result.hasErrors()) {
			return "register";
		}
		userService.save(form.toUser());
		return "redirect:/users";
	}

}
