package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;
import testbase.BaseClass;

public class TC001_LoginTest extends BaseClass {

	@Test
	public void verify_Login() throws InterruptedException
	{
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmail("bittu@123gmail.com");
		lp.setPassword("Bittu@123");
		lp.clickLogin();
		
		MyAccountPage my_Acc = new MyAccountPage(driver);
		boolean accPage = my_Acc.isMyAccountPageExist();
		
		Thread.sleep(3000);
		
		Assert.assertTrue(accPage);
		
	}
}
