package pageObjects;

import org.openqa.selenium.By;

public class HTML_Form extends BasePage{

	private By user = By.name("fname");
	private By password = By.name("lname");
	private By semanticsOption = By.xpath("//*[@id='leftmenuinnerinner']/a[text()='HTML Semantics']");

	public HTML_Form(String browserName) {
		super(browserName);
	}

	public void loginAsAdmin() {
		adapter.sendKeys(user, "ADMIN");
		adapter.sendKeys(password, "ADMIN");
		adapter.click(semanticsOption);
	}
}
