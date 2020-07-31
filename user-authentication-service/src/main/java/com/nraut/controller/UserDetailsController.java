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
import com.nraut.service.UserDetailService;

/**
 * @author Nitin
 *
 */
@Controller
public class UserDetailsController {

	@Autowired
	private UserDetailService userDetailService;
	
	@Autowired
	private ExcelService fileService;
	
	
	/**
	 * @param model
	 * @return list of Users.
	 */
	@RequestMapping("/list")
	public String viewUser(Model model){
		List<UserDetails> listUsers=userDetailService.listAll();
		model.addAttribute("listUsers",listUsers);		
		return "list";
	}
	
	
	/**
	 * @param model
	 * @return 
	 */
	@RequestMapping("/new")
	public String addNewUserDetails(Model model){
		UserDetails userDetails = new UserDetails();
		model.addAttribute("userDetails",userDetails);
		return "newUser";
	}
	
	/**
	 * @param userDetail
	 * @return after adding new user redirect to the list page.
	 */
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("userDetails") UserDetails userDetail){		
		userDetailService.save(userDetail);
		return "redirect:/list";
	}
	
	/**
	 * @param id
	 * @return updated details.
	 */
	@RequestMapping("/edit/{id}")
	public ModelAndView editUserDetails(@PathVariable("id") int id){
		ModelAndView modeAndView = new ModelAndView("editUser");
		UserDetails userDetails = userDetailService.get(id);
		
		modeAndView.addObject("userDetails",userDetails);
		return modeAndView;
	}
	
	/**
	 * @param id
	 * @return deleted the user and redirect to list page.
	 */
	@RequestMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") int id){
		userDetailService.delete(id);
		return "redirect:/list"; 
	}
	
	
	/**
	 * @param file
	 * @return upload the file details in DB and display into list page.
	 */
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
	    return "redirect:/list";
	}  
}
