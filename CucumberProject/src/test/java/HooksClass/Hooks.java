package HooksClass;

import Basemethods.BaseClass;
import Basemethods.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks extends BaseClass {

	@Before
	public void initialization() throws Exception {
		String url = getDataFromPropertyFile("Url");
		DriverManager driver = new DriverManager();
		driver.launchBrowser(url);
	}

	@After
	public void quiTheBrowser() throws Exception {
		DriverManager.quit();
	}

}
