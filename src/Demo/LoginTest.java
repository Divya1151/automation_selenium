package Demo;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest {
	WebDriver driver;

//	@BeforeTest
//	 @Parameters("browser")
//	
//	 public void setUpBrowser(String browserName) throws Exception{
//	
//	 if(browserName.equalsIgnoreCase("firefox")){
//	 System.setProperty(Utils.getPropertiesFromObject().getProperty("fsystemvariable"),
//	 Utils.getPropertiesFromObject().getProperty("fdriverpath"));
//	 driver = new FirefoxDriver();
//	 }
//	 else if(browserName.equalsIgnoreCase("chrome")){
//	
//	 System.setProperty(Utils.getPropertiesFromObject().getProperty("csystemvariable"),
//	 Utils.getPropertiesFromObject().getProperty("cdriverpath"));
//	 driver= new ChromeDriver();
//	 }
//	 else
//	 System.out.println("Browser is not correct");
//	 }

	@Test(dataProvider = "testData")
	public void launchUrl(String username, String password) throws Exception {
			//driver.get(Utils.getPropertiesFromObject().getProperty("url"));
			driver = InitializeBrowser.startBrowser(Utils.getPropertiesFromObject().getProperty("url"));
			LoginPage login = new LoginPage(driver);
			login.loginIntoDemo(username, password);
			Utils.captureScreenshot(driver);
			if(login.verifyManagerID(username)){
				System.out.println("Test is passed");
			} else System.out.println("Test is failed");
			Thread.sleep(2000);
			}

	@AfterTest

	public void closeBrowser() {
		driver.quit();
	}

	@DataProvider(name = "testData")
	public Object[][] testData() throws IOException {

		return Utils.readExcel(Utils.getPropertiesFromObject().getProperty("Excel"),
				Utils.getPropertiesFromObject().getProperty("Sheetname"));
		// System.out.println(data);
		// return data;
	}
}
