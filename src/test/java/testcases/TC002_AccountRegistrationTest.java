package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.AccountRegistrationPage;
import pageobjects.HomePage;
import testbase.BaseClass;

public class TC002_AccountRegistrationTest extends BaseClass {

	@Test
	public void verify_account_registration()
	{
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegister();
		
		AccountRegistrationPage acc_Reg_Page = new AccountRegistrationPage(driver);
		acc_Reg_Page.setFirstName(randomString());
		acc_Reg_Page.setLastName(randomString());
		acc_Reg_Page.setEmail(randomAlphaNumeric()+"@gmail.com");
		acc_Reg_Page.setTelephone(randomNumber());
		String pass = randomAlphaNumeric();
		acc_Reg_Page.setPassword(pass);
		acc_Reg_Page.setConfirmPass(pass);
		acc_Reg_Page.clickPrivacyPolicy();
		acc_Reg_Page.clickContinue();
		
		if(acc_Reg_Page.msgConfirmation().equalsIgnoreCase("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void a()
	{
		Assert.assertTrue(true);
	}
	
	@Test
	public void b()
	{
		Assert.assertTrue(true);
	}
}
