package com.desarrolloweb.spring.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	@GetMapping("/")
	public String Index(Model model) {
		model.addAttribute("message", "welcome to chafa world");
		return "index";
	}

	@GetMapping("/protect")
	public String protect(Model model) {
		model.addAttribute("message", "ok! you are login");
		return "protect";
	}
}
