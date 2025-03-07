package testbase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {

	public WebDriver driver;
	public Logger logger;
	
	@BeforeClass
	public void login() throws InterruptedException
	{
		logger = LogManager.getLogger(this.getClass());
		
		driver = new ChromeDriver();
		driver.get("https://tutorialsninja.com/demo/");
		Thread.sleep(10000);
		driver.manage().window().maximize();
		
		logger.info("Application is Launched");
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
		
		logger.info("Driver quit Statement");
	}
	
	public String randomString()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomNumber()
	{
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}
	
	public String randomAlphaNumeric()
	{
		String randomAlphaNumeric = RandomStringUtils.randomAlphanumeric(6);
		return randomAlphaNumeric;
	}
}
