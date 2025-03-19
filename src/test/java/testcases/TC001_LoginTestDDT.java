package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;
import testbase.BaseClass;
import utilities.DataProviders;

public class TC001_LoginTestDDT extends BaseClass {

	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class,groups= {"dataDriven"} )
	public void verify_Login(String email, String password, String result) throws InterruptedException
	{
		logger.info("Starting Login Test with email: "+email+" and expected result: "+result);
		
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount");
		hp.clickLogin();
		logger.info("Navigated to Login Page");
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		logger.info("Entered email: "+email);
		lp.setPassword(password);
		logger.info("Entered password");
		lp.clickLogin();
		logger.info("Clicked on Login button");
		
		MyAccountPage my_Acc = new MyAccountPage(driver);
		boolean accPage = my_Acc.isMyAccountPageExist();
		
		if(result.equalsIgnoreCase("valid"))
		{
			if(accPage)
			{
				logger.info("Login Successful. User is on My Account page");
				Assert.assertTrue(true);
				my_Acc.clickLinkLogout();
				logger.info("Logged out successfully");
			}
			else
			{
				logger.info("Login failed for valid credentials.");
				Assert.assertTrue(false);
			}
		}
		else
		{
			if(accPage)
			{
				logger.info("Login successful for invalid credentials. Test failed");
				Assert.assertTrue(false);
				my_Acc.clickLinkLogout();
				logger.info("Logged out successfully.");
			}
			else
			{
				logger.info("Login failed as expected for invalid credentials");
				Assert.assertTrue(true);
			}
		}
		logger.info("Test execution completed for email: "+email);
	}
}
