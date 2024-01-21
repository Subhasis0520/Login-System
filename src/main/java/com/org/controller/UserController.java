package com.org.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.org.binding.LoginForm;
import com.org.binding.SignUpForm;
import com.org.binding.UnlockForm;
import com.org.constant.AppConstant;
import com.org.service.UserService;


@Controller
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping("/signup")
	public String handleSignUp(@ModelAttribute("user") SignUpForm form, Model model) {
		boolean status = service.signUp(form);
		if (status) {
			model.addAttribute("success", AppConstant.ACC_SUCCESS);
		} else {
			model.addAttribute("error", AppConstant.ACC_PROBLEM);
		}
		return "signUpPage";
	}

	@GetMapping("/signup")
	public String signUpPage(Model model) {
		model.addAttribute("user", new SignUpForm());
		return "signUpPage";
	}

	@PostMapping("/login")
	public String handleLoginPage(@ModelAttribute("loginForm") LoginForm loginForm,Model model) {
		String status = service.login(loginForm);
		
		if(status.equals("success")) {
			return "redirect:/dashboard";
		}
		model.addAttribute("error", status);
		return "loginPage";
	}
	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("loginForm", new LoginForm());
		return "loginPage";
	}

	@GetMapping("/forgot")
	public String forgotPassword() {
		return "forgotPasswordPage";
	}
	
	@PostMapping("/forgot")
	public String handleForgotPassword(@RequestParam("email") String email, Model model) {
		String pwd = service.forgetPwd(email);
		if(pwd.equals("success")) {
			model.addAttribute("success",AppConstant.PASSWOED_SEND);
		}else {
			model.addAttribute("error", pwd);
		}
		
		return "forgotPasswordPage";
	}

	@GetMapping("/unlock")
	public String unlockPage(@RequestParam String email, Model model) {
		UnlockForm form = new UnlockForm();
		form.setEmail(email);
		
		model.addAttribute("unlock", form);
		return "unlockPage";
	}
	
	@PostMapping("/unlock")
	public String handleUnlockPage(@ModelAttribute("unlock") UnlockForm unlock, Model model) {
//		System.out.println(unlock);
		
		if(unlock.getNewPwd().equals(unlock.getConfirmPwd())) {
			boolean status = service.unlockAccount(unlock);
			
			if(status) {
				model.addAttribute("successMsg",AppConstant.UNLOCK_STATUS);
			}else {
				model.addAttribute("errorMsg",AppConstant.PASSWORD_NOT_MATCHED);
			}
		}else {
			model.addAttribute("errorMsg",AppConstant.CNF_PASSWORD_NOT_MATCHED);
		}
		return "unlockPage";
	}
}
