package com.org.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.org.binding.DashboardResponse;
import com.org.binding.EnquiryForm;
import com.org.binding.EnquirySearchCriteria;
import com.org.binding.UpdateForm;
import com.org.constant.AppConstant;
import com.org.entity.StudentEnquriesEntity;
import com.org.service.EnquiryService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {
	@Autowired
	private HttpSession session;
	
	@Autowired
	private EnquiryService service;
	
	@GetMapping("/viewEnquries")
	public String viewEnquries(Model model) {
		init(model);
		List<StudentEnquriesEntity> enquries = service.getData();
		model.addAttribute("enquries", enquries);
		return "view-enquries";
	}
	
	@GetMapping("/filter-enquries")
	public String filterEnquries(@RequestParam String mode, @RequestParam String course, 
			                     @RequestParam String enquiry, Model model) {
		EnquirySearchCriteria criteria = new EnquirySearchCriteria();
		criteria.setClassMode(mode);
		criteria.setCourseName(course);
		criteria.setEnquiryStatus(enquiry);
		
		Integer userId = (Integer) session.getAttribute(AppConstant.USER_ID);
		List<StudentEnquriesEntity> enquires = service.getEnquires(userId, criteria);
		model.addAttribute("enquries", enquires);
		return "filter-enquries";		
	}
	
	@PostMapping("/addEnquiry")
	public String handleAddEnquiry( @ModelAttribute("enquiryForm")EnquiryForm form ,Model model) {
	
		boolean status = service.addEnquiry(form);
		
		if(status) {
			model.addAttribute("success", AppConstant.ENQ_SUCCESS);
		}else {
			model.addAttribute("error", AppConstant.ENQ_PROBLEM);
		}
		return "add-enquiry";
	} 
	
	@GetMapping("/addEnquiry")
	public String addEnquiry(Model model) {
		init(model);
		EnquiryForm form = new EnquiryForm();
		model.addAttribute("enquiryForm", form);
		return "add-enquiry";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute("updateForm")UpdateForm form ,Model model) {
			
			boolean status = service.updateEnquiry(form);
			
			if(status) {
				model.addAttribute("success", AppConstant.UPDATE_SUCCESS);
			}else {
				model.addAttribute("error", AppConstant.UPDATE_FAIL);
			}
			return "update-enquiry";
	}
	
	@GetMapping("/edit")
	public String updateEnquiry(@RequestParam Integer enquiryId, Model model) {
		StudentEnquriesEntity studentData = service.getStudentData(enquiryId);	
		model.addAttribute("updateForm", studentData);
		init(model);
		return "update-enquiry";
	}


	private void init(Model model) {
		// get course names
		List<String> names = service.getCourseName();
		// get enquires status
		List<String> status = service.getEnquiryStatus();
			
		model.addAttribute("names", names);
		model.addAttribute("status", status);		
	}
	
	
	@GetMapping("/dashboard")
	public String dashboardPage(Model model) {
		Integer userId = (Integer) session.getAttribute(AppConstant.USER_ID);
		DashboardResponse dashBoard = service.getDashBoard(userId);
		model.addAttribute("dashBoard", dashBoard);
		return "dashboard";
	}
	
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "index";
	}
}
