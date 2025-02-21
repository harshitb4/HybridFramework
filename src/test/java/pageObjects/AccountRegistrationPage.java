package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
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
	
	public void setFirstName(String firstName)
	{
		txtFirstName.sendKeys(firstName);
	}

	public void setLastName(String lastName)
	{
		txtLastElement.sendKeys(lastName);
	}

	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}


	public void setTelephone(String telephone)
	{
		txtTelephone.sendKeys(telephone);
	}

	public void setPassword(String pass)
	{
		txtPassword.sendKeys(pass);
	}

	public void setConfirmPass(String confPass)
	{
		txtPassConfirm.sendKeys(confPass);
	}

	public void clickPrivacyPolicy()
	{
		chkPrivacyPolicy.click();
	}

	public void clickContinue()
	{
		//btnContinue.click();
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", btnContinue);
	}

	public String msgConfirmation()
	{
		return msgConfirmation.getText();
	}
}
