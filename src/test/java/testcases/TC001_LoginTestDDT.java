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
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(password);
		lp.clickLogin();
		
		MyAccountPage my_Acc = new MyAccountPage(driver);
		boolean accPage = my_Acc.isMyAccountPageExist();
		
		if(result.equalsIgnoreCase("valid"))
		{
			if(accPage)
			{
				Assert.assertTrue(true);
				my_Acc.clickLinkLogout();
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		else
		{
			if(accPage)
			{
				Assert.assertTrue(false);
				my_Acc.clickLinkLogout();
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		
	}
}
