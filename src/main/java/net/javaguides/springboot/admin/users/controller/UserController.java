package net.javaguides.springboot.admin.users.controller;

import java.util.List;

import net.javaguides.springboot.admin.users.model.User;
import net.javaguides.springboot.admin.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	// display list of users
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return "index";
	}

	@GetMapping("/home")
	public String homePage(Model model ){
		return findPaginated(1, "firstName", "asc", model);
	}

	@GetMapping("/Register")
	public String showNewUserForm(Model model) {
		// create model attribute to bind form data
		User user = new User();
		model.addAttribute("user", user);
		return "admin/customers/Register";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") User user) {
		// save user to database
		userService.saveUser(user);
		return "redirect:/login";
	}

	@PostMapping("/saveUser1")
	public String saveUser1(@ModelAttribute("user") User user) {
		// save user to database
		userService.saveUser(user);
		return "redirect:/home";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get user from the service
		User user = userService.getUserById(id);
		
		// set user as a model attribute to pre-populate the form
		model.addAttribute("user", user);
		return "admin/customers/update_profile";
	}
	
	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable (value = "id") long id) {
		
		// call delete user method
		this.userService.deleteUserById(id);
		return "redirect:/home";
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<User> page = userService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<User> user = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listEmployees", user);
		return "admin/customers/addUsers";
	}

	@GetMapping("/login")
	public String showLoginForm(Model model) {

		User user = new User();
		model.addAttribute("user", user);
		return "login";
	}

	@PostMapping("/UserLogin")
	public String LoginData(Model model ,  @ModelAttribute("user") User users) {
		List<User> AllAdmin = userService.getAllUsers();
		for (User admin:
				AllAdmin) {
			if(admin.getEmail().equals(users.getEmail()) && admin.getPassword().equals(users.getPassword()) ){
				return "redirect:/dashboard";
			}

		}

		User user = new User();
		model.addAttribute("user", user);



		return "admin/customers/Register";
	}

}
