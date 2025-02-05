package com.sauce.page.homepage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sauce.utils.WebUtil;

public class Homepage {
	private WebUtil util;
	public Homepage(WebUtil util) {
		this.util=util;
		PageFactory.initElements( util.getDriver(), this);
		
	}
	
	@FindBy(id="react-burger-menu-btn")
	private WebElement menubarBT;
	
	@FindBy(id="logout_sidebar_link")
	private WebElement logoutBT;
	
	
	public void logout() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		menubarBT.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logoutBT.click();
		
	}

}
