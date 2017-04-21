package Demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class InitializeBrowser {

	public static WebDriver startBrowser(String url) throws Exception {

		System.setProperty(Utils.getPropertiesFromObject().getProperty("fsystemvariable"),
				Utils.getPropertiesFromObject().getProperty("fdriverpath"));
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(url);
		return driver;

	}

	// private void installPlugin() {
	// final String firebugPath = "C:\\FF_Profile\\firebug.xpi";
	// FirefoxProfile profile = new FirefoxProfile();
	// profile.addExtension(new File(firebugPath));
	// // Add more if needed
	// WebDriver driver = new FirefoxDriver(profile);
	// }

}
