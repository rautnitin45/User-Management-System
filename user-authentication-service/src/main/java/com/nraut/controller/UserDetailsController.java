package com.nraut.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.nraut.excel.helper.ExcelHelper;
import com.nraut.model.UserDetails;
import com.nraut.service.ExcelService;
import com.nraut.service.UserDetailsService;

@Controller
public class UserDetailsController {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private ExcelService fileService;
	
	@RequestMapping("/")
	public String viewUser(Model model){
		List<UserDetails> listUsers=userDetailsService.listAll();
		model.addAttribute("listUsers",listUsers);		
		return "index";
	}
	
	@RequestMapping("/new")
	public String addNewUserDetails(Model model){
		UserDetails userDetails = new UserDetails();
		model.addAttribute("userDetails",userDetails);
		return "newUser";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("userDetails") UserDetails userDetail){		
		userDetailsService.save(userDetail);
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView editUserDetails(@PathVariable("id") int id){
		ModelAndView modeAndView = new ModelAndView("editUser");
		UserDetails userDetails = userDetailsService.get(id);
		
		modeAndView.addObject("userDetails",userDetails);
		return modeAndView;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") int id){
		userDetailsService.delete(id);
		return "redirect:/"; 
	}
	
	
	@RequestMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file) {
	    String message = "";
	    if (ExcelHelper.hasExcelFormat(file)) {
	      try {
	        fileService.save(file);

	        message = "Uploaded the file successfully: " + file.getOriginalFilename();
	        
	        System.out.println(" Uploaded File :"+ message);
	       
	      } catch (Exception e) {
	        System.out.println("Message : "+ e.getMessage());
	      }
	    }
	    return "redirect:/";
	}  
}
