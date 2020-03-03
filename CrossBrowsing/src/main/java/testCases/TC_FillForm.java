package testCases;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.HTML_Form;

public class TC_FillForm {
	
	@Parameters("browserName")
	@Test(priority = 1)
	
	public void TC4_w3schoold(@Optional("chrome") String browserName) throws Exception {
		try {
			HTML_Form c = new HTML_Form(browserName); 	
			c.loginAsAdmin();
			c.closeBrowser();
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
}
