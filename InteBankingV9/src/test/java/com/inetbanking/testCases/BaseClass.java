package com.inetbanking.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.*;


import com.inetbanking.utilities.ReadConfig;

public class BaseClass {
	ReadConfig readconfig=new ReadConfig();
	public String baseURL=readconfig.getapplicationURL();
	public String username=readconfig.getusername();
	public String password=readconfig.getPassword();
	public static WebDriver driver;
	public static Logger logger;
	@Parameters("browser")
	@BeforeClass
	public void setup(String br)
	{
		
		 logger=Logger.getLogger("ebanking");
		 PropertyConfigurator.configure("log4j.properties");
		 if (br.equals("firefox")) {
				// Firefox Browser
				System.setProperty("webdriver.gecko.driver",readconfig.getFirefoxPath());
				driver = new FirefoxDriver();
			}

			else if (br.equals("chrome")) {
				// opens the browser
				System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
				driver = new ChromeDriver();
			}

			driver.get(baseURL);
			
			// Global implicit Wait
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
@AfterClass
public void tearDown() {
	
	driver.quit();
}
public void captureScreen(WebDriver driver, String tname) throws IOException {
	TakesScreenshot ts = (TakesScreenshot) driver;
	File source = ts.getScreenshotAs(OutputType.FILE);
	File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
	FileUtils.copyFile(source, target);
	System.out.println("Screenshot taken");
}
}
