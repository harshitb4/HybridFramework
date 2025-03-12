package testbase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {

	public WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass
	public void login() throws InterruptedException, IOException
	{
		FileReader file = new FileReader("./src/test//resources//config.properties");
		p= new Properties();
		p.load(file);
		
		logger = LogManager.getLogger(this.getClass());
		
		driver = new ChromeDriver();
		driver.get(p.getProperty("appURL"));
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
	
	public String captureScreen(String tName)
	{
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+tName+"_"+timeStamp+".png";
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
	}
}
