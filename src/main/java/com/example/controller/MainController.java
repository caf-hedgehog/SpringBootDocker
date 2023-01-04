package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.repository.UserRepository;

@Controller
@RequestMapping("/login/main")
public class MainController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("")
	public String index(Model model) {
		User user = new User();
		user.setId(7);
		user.setName("成功してくれ");
		user.setPassword("aaaa");
		user.setAuthority(5);
		// userRepository.insert(user);

		System.out.println(userRepository.load(7));
		model.addAttribute("user", userRepository.load(7));
		return "smaple";
	}

}
