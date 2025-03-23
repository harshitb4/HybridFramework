package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.AccountRegistrationPage;
import pageobjects.HomePage;
import testbase.BaseClass;

public class TC002_AccountRegistrationTest extends BaseClass {

	@Test
	public void verify_account_registration() throws InterruptedException
	{
		logger.info("Starting TC002_AccountRegistrationTest");
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Account clicked successfully");
		hp.clickRegister();
		logger.info("Register clicked successfully");
		
		AccountRegistrationPage acc_Reg_Page = new AccountRegistrationPage(driver);
		acc_Reg_Page.setFirstName(randomString());
		logger.info("Entered first name successfully");
		acc_Reg_Page.setLastName(randomString());
		logger.info("Entered last name successfully");
		acc_Reg_Page.setEmail(randomAlphaNumeric()+"@gmail.com");
		logger.info("Entered email successfully");
		acc_Reg_Page.setTelephone(randomNumber());
		logger.info("Entered Telephone number succcessfully");
		String pass = randomAlphaNumeric();
		acc_Reg_Page.setPassword(pass);
		logger.info("Entered password successfully");
		acc_Reg_Page.setConfirmPass(pass);
		logger.info("Entered confirm password successfully");
		acc_Reg_Page.clickPrivacyPolicy();
		logger.info("Clicked on Privacy policy");
		acc_Reg_Page.clickContinue();
		logger.info("Clicked on continue");
		Thread.sleep(5000);
		if(acc_Reg_Page.msgConfirmation().equalsIgnoreCase("Your Account Has Been Created!"))
		{
			logger.info("Account created successfully");
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Failed to create account");
			Assert.assertTrue(false);
		}
		logger.info("Test execution completed for TC002_AccountRegistrationTest");
	}
}
