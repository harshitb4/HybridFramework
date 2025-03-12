package testcases;

import org.testng.Assert;

import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;
import testbase.BaseClass;

public class TC003_LoginTest extends  BaseClass {

	public void verify_login()
	{
       logger.info("***Starting TC002_LoginTest***");
		
		try
		{
		  HomePage hp = new HomePage(driver);
		  hp.clickMyAccount();
		  hp.clickLogin();
		
		  LoginPage lp = new LoginPage(driver);
		  lp.setEmail(p.getProperty("email"));
		  lp.setPassword(p.getProperty("password"));
		  lp.clickLogin();
		
		  MyAccountPage maac = new MyAccountPage(driver);
		  boolean accPage = maac.isMyAccountPageExist();
		  
		  Thread.sleep(3000);
		 
		  Assert.assertTrue(accPage);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("***Finished TC002_LoginTest***");
	}
}
