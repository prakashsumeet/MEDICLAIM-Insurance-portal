package com.form.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URI;

import com.form.Services.UserService;
import com.form.dto.UserDto;
import com.form.entity.User;
import com.form.repositories.UserRepository;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

@Valid
@Controller
//@RequestMapping(path="/Users")
public class UserController 
{

	private UserService userService;
	User authUser = new User();
	
	@Autowired
	UserRepository userrepo;

	public UserController(UserService userService) 
	{
		this.userService = userService;
	}
	

	//takes you to the home	
	@GetMapping("/home")
	public String home(Model model, UserDto userDto) 
	{
		if(authUser.getId() !=null && authUser.isAmin())
		{
			List<User> users = userService.getAllUsers();
			System.out.println(users);
			model.addAttribute("users", users);
			return "home";	
		}
		authUser =  new User();
		return "redirect:/login";
		
	}
	@GetMapping("/logout")
	public String logout() 
	{
		authUser =  new User();
		return "redirect:/login";
	}
	
	@GetMapping("/user-verified/{id}/{flag}")
	public String userverified(@PathVariable Long id, @PathVariable String flag) 
	{
		User user = userService.getById(id);
		user.setIsVerified(flag);
		
		userService.save(user);
		return "redirect:/home";
	}

	
	@GetMapping("/userhome/{id}")
	public String userLoginHome(@PathVariable Long id, Model model)
	{	
		System.out.println("authUser" + authUser + id);
		if(authUser.getId() !=null && authUser.getId().equals(id))
		{
			System.out.print("ohh userlogin");
			User users = userService.getById(id);
			System.out.println(users);
			model.addAttribute("users", users);
			return "userlogin";
		}
		else 
		{
			System.out.print("khus hua!");
			return "redirect:/login";
		}		
	}
	// takes you to the login page
	@GetMapping("/login")
	public String login(Model model, UserDto userDto)
	{
		System.out.println(authUser);
		if(authUser.getId() == null) 
		{
			model.addAttribute("user", userDto);
			model.addAttribute("sessionMessage" , null);
			return "login";
		}
		else if(authUser.isAmin())
		{
			return "redirect:/home";
		}
		else if(!authUser.isAmin()) 
		{
			return "redirect:/userhome/" + authUser.getId();
		}
		return "/";
	}

	@PostMapping("/login")
	public String welcomePage(Model model, @RequestParam String username, @RequestParam String password, UserDto userDto ) 
	{
		System.out.println(model);
		User user = userService.findByUsername(username);
		System.out.println("Admin Condition "+user.isAmin());
		System.out.println("Check data");
		System.out.println(user.getPassword());
		System.out.println(user.getCity());
		System.out.println(password);
		if(user.getPassword().equals(password) && user.isAmin()==true)
		{
			System.out.println("Inside if admin ");
			authUser=user;
			return "redirect:/home";
		}
		
		else if(user.getPassword().equals(password) )
		{
			if(user.getIsVerified().equals("approved"))
			{
				System.out.println("Inside if user");
				authUser=user;
				return "redirect:/userhome/" + user.getId();	
			}
			else 
			{	
				String message = user.getIsVerified().equals("pending") ? "Pending Hai" : "Rejected hai";
				model.addAttribute("sessionMessage" , message);
				model.addAttribute("user", userDto);
				return "login";
			}
		}
		
		/*
		 * System.out.println(user.getUsername());
		 * System.out.println(username);
		 * if(user.getPassword().equals(password) && user.getUsername.equals(username){
		 * List<User> users = userService.getAllUsers();
		 * System.out.println(users);
		 * model.addAttribute("users", user);
		 * System.out.println("inside if");
		 * return "home";
		 */
		
//		List<User> users = userService.getAllUsers();
//		System.out.println(users);
//		model.addAttribute("users", users);
		else 
		{
			System.out.println("Inside else ");
		    return "congrats";
		}
	}
    //takes you to the registration page
	@GetMapping("/register")
	public String register(Model model, UserDto userDto) 
	{
		if(authUser.getId() == null) 
		{
			model.addAttribute("user", userDto);
			return "register";
		}
		else if(authUser.isAmin()) 
		{
			return "redirect:/home";
		}
		else if(!authUser.isAmin()) 
		{
			return "redirect:/userhome/" + authUser.getId();
		}
		return "redirect:/login";
	}
	@PostMapping("/register")
	public String registerSava(User userDto, Model model)
	{
		User user = userService.findByUsername(userDto.getUsername());
		if (user != null)
		{
			model.addAttribute("Userexist", user);
			return "login";
		}
		userService.save(userDto);
		return "redirect:/login";
	}
	@PostMapping(path = "/uploadimage/{id}/{flag}")
	public String uploadImage1(@RequestBody MultipartFile mpf, @PathVariable Long id, @PathVariable String flag, Model model) throws IOException, InterruptedException 
	{
		System.out.println("This is flag value -> " +flag);	
		User user = userService.getById(id);
		userService.uploadImage1(mpf, id);
		Thread.sleep(1000);
	    if ("user".equals(flag)) 
	    {
			return "redirect:/userhome/" + user.getId();
		}
		else 
		{
			return "redirect:/home";
		}		
	}	
	//takes you to the main page
	@GetMapping("/")
	public String dashboard() 
	{
		return "dashboard";
	}	
	//deletebyID
	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable (value = "id" ) long id)
	{
		this.userService.deleteUserByID(id);
		return "redirect:/home";
	}	
	//updatingbyID
//	@GetMapping("/updateUser/{id}")
//	public String updateUserForm(@PathVariable (value = "id") long id, Model model)
//	{
//		User user = userService.getUserID(id);
//		model.addAttribute("user", user);
//		return "updateUser";
//	}	
}