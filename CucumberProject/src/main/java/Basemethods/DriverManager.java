package Basemethods;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager extends BaseClass {
	
	private static final Logger logger = LogManager.getLogger(DriverManager.class);

	public void launchBrowser(String url) throws Exception {
		try {
			String browserType = getDataFromPropertyFile("BrowserType");
			if (driver == null) {
				browser(browserType);
				logger.info("Browser Launched");
			}
			driver.get(url);
			driver.manage().window().maximize();

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public static WebDriver browser(String browserType) throws Exception {
		try {
			if (browserType.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			} else if (browserType.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			} else if (browserType.equalsIgnoreCase("safari")) {
				WebDriverManager.safaridriver().setup();
				driver = new SafariDriver();
			} else if (browserType.equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			} else if (browserType.equalsIgnoreCase("internetExplore")) {
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
			} else {
				WebDriverManager.chromedriver().setup();
//				ChromeOptions ChromeOptions = new ChromeOptions().addArguments("incognito");
				driver = new ChromeDriver(new ChromeOptions().addArguments("incognito"));
			}
		} catch (Exception exe) {
			exe.printStackTrace();
			throw new Exception(exe.getMessage());
		}
		return driver;
	}

	public static boolean quit() throws Exception {
		boolean flag = true;
		try {
			driver.quit();
		} catch (Exception e) {
			flag = false;
			throw new Exception("Error Message:" + e.getMessage());
		}
		return flag;
	}

}
