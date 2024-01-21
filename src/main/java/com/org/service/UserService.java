package com.org.service;

import com.org.binding.LoginForm;
import com.org.binding.SignUpForm;
import com.org.binding.UnlockForm;

public interface UserService {

	public String login(LoginForm login);
	
	public boolean signUp(SignUpForm form);
	
	public boolean unlockAccount(UnlockForm form);
	
	public String forgetPwd(String mail);
}
