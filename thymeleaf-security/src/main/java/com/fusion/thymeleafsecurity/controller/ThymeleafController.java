package com.fusion.thymeleafsecurity.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fusion.thymeleafsecurity.model.RoleName;
import com.fusion.thymeleafsecurity.model.Roles;
import com.fusion.thymeleafsecurity.model.Users;
import com.fusion.thymeleafsecurity.repo.RolesRepository;
import com.fusion.thymeleafsecurity.repo.UsersRepository;

@Controller
public class ThymeleafController {

	@Autowired
	private UsersRepository userRepo;

	@Autowired
	private RolesRepository rolesRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/")
	public String home() {
		return "home";
	}

	@GetMapping("/about")
	public String about() {
		return "about";
	}

	@GetMapping("/signup")
	public String signup(Model model) {
		Users user = new Users();
		model.addAttribute("user", user);
		return "signup";
	}

	@PostMapping("/signup")
	public String signup(@ModelAttribute("user") Users user) {
		Set<Roles> rl = new HashSet<Roles>();
		rl.add(rolesRepo.findByName(RoleName.valueOf(user.getRole())));
		user.setRoles(rl);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepo.save(user);
		return "redirect:/";
	}

	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");
		return "login";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String admin() {
		return "admin";
	}

	@GetMapping("/user")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String user() {
		return "user";
	}

	@GetMapping("/403")
	public String error403() {
		return "/403";
	}

}
