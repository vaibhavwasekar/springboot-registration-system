package com.thymeleaf.controller;

import javax.sound.sampled.ReverbType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.thymeleaf.entities.User;
import com.thymeleaf.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MyController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String homePage() {
	    return "index";
	}
	
	@GetMapping("/regpage")
	public String openRegpage(Model model)
	{
		model.addAttribute("user",new User());
		return "register";
	}
	@PostMapping("/regform")
	public String submitRegform(@ModelAttribute("user") User user,Model model)
	{
		boolean status=userService.registerUser(user);
				if(status)
				{
					model.addAttribute("successMsg","user register success");
				}
				else
				{
					model.addAttribute("errorMsg","user not register error");
				}
		return "register";
	}

    @GetMapping("/loginpage")
	public String openLOginpage(Model model)
	{
		model.addAttribute("user",new User());
		return "login";
	}
    @PostMapping("/loginform")
    public String submitloginform(@ModelAttribute("user") User user,Model model)
    {
    	User validuser=userService.loginuser(user.getEmail(), user.getPassword());
    	
    	if(validuser!=null)
    	{
    		model.addAttribute("modelname", validuser.getName());
    		return "profile";
    	}
    	else
    	{
    		model.addAttribute("errorMsg","email and password not match");
            return "login";
    	}
    }
    
    @GetMapping("/logout")
    public String logout(HttpServletRequest request)
    {
    	HttpSession session=request.getSession(false);
    	if(session!=null)
    	{
    		session.invalidate();
    	}
    	return "redirect:/loginpage";
    }
}
