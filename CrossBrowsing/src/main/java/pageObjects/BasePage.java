package pageObjects;


import static org.testng.Assert.fail;

import drivers.Adapter;

public class BasePage {
	public Adapter adapter;
	public void closeBrowser() {
		adapter.closeDriver();
	}
	public BasePage(String browserName) {
		try {
			adapter = new Adapter(browserName);
		}catch (Exception e) {
			System.out.println("NO LEVANTO" + adapter.toString());
		}
	}
}
