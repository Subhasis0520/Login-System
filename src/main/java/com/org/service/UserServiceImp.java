package com.org.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.binding.LoginForm;
import com.org.binding.SignUpForm;
import com.org.binding.UnlockForm;
import com.org.constant.AppConstant;
import com.org.entity.UserDetailsEntity;
import com.org.repository.UserDetailsRepo;
import com.org.util.EmailUtils;
import com.org.util.PasswordUtils;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImp implements UserService{

	@Autowired
	private  UserDetailsRepo userRepo;
	
	@Autowired
	private EmailUtils email;
	
	@Autowired
	private HttpSession session;
	
	@Override
	public String forgetPwd(String mail) {
		// TODO: check mail is valid or not
		UserDetailsEntity entity = userRepo.findByEmail(mail);
		// if mail is invalid
		if(entity == null) {
			return AppConstant.INVALID_MAIL;
		}
		// if mail is a valid mail
		String body = "<h2>Your password is: </h2> <h1>"+entity.getPassword()+"</h2>";
		String sub = AppConstant.RECOVER_ACC;
		email.sendMail(mail, sub, body);
		return "success";
	}
	
	@Override
	public String login(LoginForm login) {
		UserDetailsEntity entity = userRepo.findByEmailAndPassword(login.getEmail(), login.getPassword());
		
		if(entity == null) {
			return AppConstant.INVALID_CRED;
		}
		if(entity.getAccoutStatus().equals("LOCKED")) {
			return AppConstant.UNLOCK_ACC;
		}
		session.setAttribute(AppConstant.USER_ID, entity.getUserId());
		return "success";
	}
	
	@Override
	public boolean unlockAccount(UnlockForm form) {
		UserDetailsEntity user = userRepo.findByEmail(form.getEmail());
		
		if(user.getPassword().equals(form.getTempPwd())){
			user.setPassword(form.getNewPwd());
			user.setAccoutStatus(AppConstant.UNLOCKED);
			userRepo.save(user);
			return true;
		}else {
			return false;
		}
	
	}
	
	@Override
	public boolean signUp(SignUpForm form) {
        // TODO: Check duplicate email		
		UserDetailsEntity user = userRepo.findByEmail(form.getEmail());
		if(user != null) {
			return false;
		}
		
		//TODO: Copy the form data to entity
		UserDetailsEntity entity = new UserDetailsEntity();
		BeanUtils.copyProperties(form, entity);
		
		// TODO: generate random password
		String randomPwd = PasswordUtils.generateRandomPwd();
		entity.setPassword(randomPwd);
		
		// TODO: set account status as locked
		entity.setAccoutStatus(AppConstant.LOCKED);
		
		//TODO: insert the record into the table
		userRepo.save(entity);
		
		//TODO: Send email to unlock the account
		String to = form.getEmail();
		String subject = AppConstant.UNLOCK_ACCOUNT;
		StringBuffer body = new StringBuffer("");
		body.append("<h1>Use below password to unlock your account </h1>");
		body.append("<br> Password : "+randomPwd);
		body.append("<br>");
		body.append("<a href=\"http://localhost:8085/unlock?email="+to+"\" > Click here to unlock your account </a>");
		
		email.sendMail(to, subject, body.toString());
		
		return true;
	}

}
