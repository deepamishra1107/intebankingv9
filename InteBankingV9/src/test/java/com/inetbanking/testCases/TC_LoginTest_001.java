package com.inetbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import com.inetbanking.pageObject.LoginPage;

public class TC_LoginTest_001 extends BaseClass {
	
	
	@Test
	public void loginTest() throws IOException
	{
		driver.get(baseURL);
		logger.info("opened URL");
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(username);
		logger.info("provided user name");
		lp.setPassword(password);
		logger.info("provided password");

		lp.clickSubmit();
		logger.info("click sumbit");

		if (driver.getTitle().equals("Guru99 Bank Manager HomePage")) {
			Assert.assertTrue(true);
			logger.info("login test pass");

		} else 
		{
			Assert.assertTrue(false);
			logger.info("login test failed");
			captureScreen(driver,"LoginTest");
		}

	}

}
