package com.sauce.page.login;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sauce.utils.WebUtil;


public class Login {
    private WebUtil util;
      	
	public Login(WebUtil util) {
		this.util=util;
		PageFactory.initElements(util.getDriver(), this);

	}
	
	@FindBy(id="user-name")
	private WebElement userNameTB;
	
	@FindBy(id="password")
	private WebElement userPasswordTB;
	
	@FindBy(id="login-button")
	private WebElement loginBT;
	
	
	
	
	public void login(String user_name, String user_password) {
		util.inputValue(userNameTB, user_name);
		util.inputValue(userPasswordTB, user_password);
		util.click(loginBT);
		
	}
	
	
	
}
