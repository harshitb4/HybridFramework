package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstName;

	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastElement;

	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;

	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelephone;

	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;

	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtPassConfirm;

	@FindBy(xpath="//input[@name='agree']")
	WebElement chkPrivacyPolicy;

	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;

	@FindBy(xpath="//h1[text()='Your Account Has Been Created!']")
	WebElement msgConfirmation;	
}
