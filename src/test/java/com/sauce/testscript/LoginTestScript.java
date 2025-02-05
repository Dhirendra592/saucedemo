package com.sauce.testscript;

import org.testng.annotations.Test;

import com.sauce.base.BaseTest;
import com.sauce.page.homepage.Homepage;
import com.sauce.page.login.Login;

public class LoginTestScript extends BaseTest {

	@Test
	public void verifySD001LoginAndLogOut() {
		Login lgn = new Login(webUtil);
		lgn.login(webUtil.getDataFromPropperties("username"), webUtil.getDataFromPropperties("password"));
		Homepage hm = new Homepage(webUtil);
		hm.logout();
	}

}
