package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver)
	{
		super(driver);
	}
	
    @FindBy(xpath="//h2[text()='My Account']")
    WebElement msgHeading;

    @FindBy(xpath="//a[text()='Logout' and @class='list-group-item']")
    WebElement linkLogout;

	public boolean isMyAccountPageExist()
	{
		try {
			return msgHeading.isDisplayed();
		}catch(Exception e)
		{
			return false;
		}
	}

	public void clickLinkLogout()
	{
		linkLogout.click();
	}
}
