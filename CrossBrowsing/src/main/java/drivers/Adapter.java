package drivers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class Adapter {

	static String urlEDGE = "http://192.168.8.101:4444";// TODO enviar a constantes IP del server con el PORT
	static String urlIE = "http://192.168.8.100:5561/wd/hub/";
	static String urlChrome = "http://192.168.8.100:5561/wd/hub/";
	static String urlFire = "http://192.168.8.100:5561/wd/hub/";

	private WebDriver driver;

	public Adapter(String browserType) throws IOException {	
		try {	    
		    //Properties properties = new Properties();
		    //FileInputStream fis = new FileInputStream("src\\main\\resources\\app.properties");
		    //properties.load(fis);            
				
			//String BROWSER = properties.getProperty("BROWSER");			
			//String URL = properties.getProperty("URL");			
		      
			switch (browserType) {
			case BrowserType.EDGE:
				driver = createInstanceEDGE();
				break;
			case BrowserType.IE:
				driver = createInstanceIE();
				break;
			case BrowserType.CHROME:
				driver = createInstanceCHROME();
				break;
			case BrowserType.FIREFOX:
				driver = createInstanceFIREFOX();
				break;
			default:
				break;
			}
			//driver.get(URL);
			driver.get("https://www.w3schools.com/html/html_forms.asp");
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
		} catch (Exception e) {
			ExceptionFail(e);
		}
	}

	public static RemoteWebDriver createInstanceEDGE() throws MalformedURLException {
		DesiredCapabilities capabilities = DesiredCapabilities.edge();
		RemoteWebDriver driver = new EdgeDriver(capabilities);
		return driver;
	}

	// REMOTE
	/*
	 * public static RemoteWebDriver createInstanceEDGE() throws
	 * MalformedURLException { DesiredCapabilities capabilities =
	 * DesiredCapabilities.edge(); RemoteWebDriver driver = new RemoteWebDriver(new
	 * URL(urlEDGE) , capabilities); return driver; }
	 */
	public static RemoteWebDriver createInstanceIE() throws MalformedURLException {
		System.setProperty("webdriver.ie.driver", "src//main//resources//IEDriverServer.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "internet explorer");
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		RemoteWebDriver driver = new InternetExplorerDriver(capabilities);
		return driver;
	}

	/*
	 * public static RemoteWebDriver createInstanceIE() throws MalformedURLException
	 * { DesiredCapabilities desiredCapabilities =
	 * DesiredCapabilities.internetExplorer();
	 * desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME,
	 * "internet explorer"); desiredCapabilities.setPlatform(Platform.WINDOWS);
	 * desiredCapabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL,
	 * "https://www.google.com/");
	 * desiredCapabilities.setCapability(InternetExplorerDriver.
	 * INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
	 * 
	 * RemoteWebDriver driver = new RemoteWebDriver(new URL(urlIE),
	 * desiredCapabilities); return driver;
	 * 
	 * }
	 */
	public static RemoteWebDriver createInstanceCHROME() {
		System.setProperty("webdriver.ie.driver", "src//main//resources//chromedriver.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability("browserName", BrowserType.CHROME);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		capabilities.setCapability(CapabilityType.SUPPORTS_NETWORK_CONNECTION, true);
		RemoteWebDriver driver = new ChromeDriver(capabilities);
		return driver;
	}

	/*
	 * public static RemoteWebDriver createInstanceCHROME() { DesiredCapabilities
	 * capabilities = DesiredCapabilities.chrome();
	 * capabilities.setCapability("browserName", BrowserType.CHROME);
	 * capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	 * capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
	 * capabilities.setCapability(CapabilityType.SUPPORTS_NETWORK_CONNECTION, true);
	 * RemoteWebDriver driver = new RemoteWebDriver(new URL(urlChrome),
	 * capabilities); return driver; }
	 */
	public static RemoteWebDriver createInstanceFIREFOX() {
		System.setProperty("webdriver.ie.driver", "src//main//resources//geckodriver.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("browserName", BrowserType.FIREFOX);
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("intl.accept_languages", "en");
		profile.setPreference("browser.startup.homepage_override.mstone", "ignore");
		profile.setPreference("signon.autologin.proxy", true);
		capabilities.setCapability(FirefoxDriver.PROFILE, profile);
		RemoteWebDriver driver = new FirefoxDriver(capabilities);
		return driver;
	}

	/*
	 * public static RemoteWebDriver createInstanceFIREFOX() {
	 * System.setProperty("webdriver.ie.driver", "src//resources//geckodriver.exe");
	 * DesiredCapabilities capabilities = DesiredCapabilities.firefox();
	 * capabilities.setCapability("browserName", BrowserType.FIREFOX);
	 * FirefoxProfile profile = new FirefoxProfile();
	 * profile.setPreference("intl.accept_languages", "en");
	 * profile.setPreference("browser.startup.homepage_override.mstone", "ignore");
	 * profile.setPreference("signon.autologin.proxy", true);
	 * capabilities.setCapability(FirefoxDriver.PROFILE, profile); RemoteWebDriver
	 * driver = new RemoteWebDriver(new URL(urlFire), capabilities); return driver;
	 * }
	 */
	public WebElement getWebElement(By element) {
		WebElement e = driver.findElement(element);
		return e;
	}

	public void get(String domine, String user, String pass) {

	}

	public void moveToElement(WebElement element) {
		// TODO Auto-generated method stub

	}

	public WebElement xpath() {
		// TODO Auto-generated method stub
		return null;
	}

	public void doubleClick(WebElement element) {
		// TODO Auto-generated method stub

	}

	public void clickAndHold(WebElement element) {
		// TODO Auto-generated method stub

	}

	public void dragAndDrop(WebElement source, WebElement target) {
		// TODO Auto-generated method stub

	}

	public void waitToElementBeVisible(WebElement... element) {
		// TODO Auto-generated method stub

	}

	public void waitToElementBeEnabled(WebElement... element) {
		// TODO Auto-generated method stub

	}

	public void waitToElementBeSelected(WebElement... element) {
		// TODO Auto-generated method stub

	}

	public String getBrowserVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	public void click(By element) {
		try {
			Actions builder = new Actions(driver);
			builder.moveToElement(getWebElement(element)).perform();
			builder.click(getWebElement(element)).build().perform();
		} catch (Exception e) {
			ExceptionFail(e);
		}
	}

	public void sendKeys(By element, String text) {
		try {

			Actions builder = new Actions(driver);
			builder.moveToElement(getWebElement(element)).perform();
			builder.click(getWebElement(element)).build().perform();
			getWebElement(element).clear();
			builder.sendKeys(getWebElement(element), text).build().perform();
		} catch (Exception e) {
			ExceptionFail(e);
		}
	}

	public void ExceptionFail(Exception e) {
		driver.quit();
		Assert.fail(e.toString());		
	}
	
	public void closeDriver() {
		driver.quit();
	}

}
