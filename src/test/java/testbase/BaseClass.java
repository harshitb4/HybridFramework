package testbase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass
	@Parameters({"os","browser"})
	public void login(String os,String browser) throws InterruptedException, IOException
	{
		logger = LogManager.getLogger(this.getClass());
		
		logger.info("Loading configuration properties");
		FileReader file = new FileReader("./src/test//resources//config.properties");
		p= new Properties();
		p.load(file);
		
		logger.info("Execution environment: "+p.getProperty("execution_env"));
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			logger.info("Setting up remote execution...");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("linux"))
			{
				capabilities.setPlatform(Platform.LINUX);
			}
			else
			{
				logger.error("Invalid platform "+os);
				return;
			}
			
			switch(browser.toLowerCase())
			{
			  case "chrome":capabilities.setBrowserName("chrome"); break;
			  case "edge":capabilities.setBrowserName("MicrosoftEdge"); break;
			  case "firefox":capabilities.setBrowserName("firefox"); break;
			  default : logger.error("Invalid browser name: "+browser); return;
			}
			
			@SuppressWarnings("deprecation")
			URL url = new URL("http://localhost:4444/wd/hub");
			driver = new RemoteWebDriver(url, capabilities);
			logger.info("Remote WebDriver initialized with "+browser+" on "+os);
		}
		
		else if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			logger.info("Setting up local execution...");
			switch(browser.toLowerCase())
			{
			case "chrome":driver = new ChromeDriver(); break;
			case "edge":driver = new EdgeDriver(); break;
			case "firefox":driver = new FirefoxDriver(); break;
			default:logger.error("Invalid browser name: "+browser); return;
			}
			logger.info("Browser launched successfully");
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		logger.info("Launching application: "+p.getProperty("appURL"));
		driver.get(p.getProperty("appURL"));
		Thread.sleep(5000);
		driver.manage().window().maximize();
		Thread.sleep(3000);
		
		logger.info("Application Launched successfully");
	}
	
	@AfterClass
	public void tearDown()
	{
		if(driver!=null)
		{
			driver.quit();
			logger.info("Driver quit successfully");
		}
		else
		{
			logger.warn("Driver was already null before quit");
		}
	}
	
	public String randomString()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		logger.debug("Generated randon string: "+generatedString);
		return generatedString;
	}
	
	public String randomNumber()
	{
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		logger.debug("Generated random number: "+generatedNumber);
		return generatedNumber;
	}
	
	public String randomAlphaNumeric()
	{
		String randomAlphaNumeric = RandomStringUtils.randomAlphanumeric(6);
		logger.debug("Generated alphanumeric: "+randomAlphaNumeric);
		return randomAlphaNumeric;
	}
	
	public String captureScreen(String tName)
	{
		logger.info("Capture screen invoked");
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+tName+"_"+timeStamp+".png";
		
		try {
		   TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		   File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		   File targetFile = new File(targetFilePath);
		   sourceFile.renameTo(targetFile);
		   
		   logger.info("Screenshot captured: "+targetFilePath);
		}catch(Exception e)
		{
			logger.error("Failed to capture screenshot for "+tName, e);
		}
		
		return targetFilePath;
	}
}
