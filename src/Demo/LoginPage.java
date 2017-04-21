package Demo;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	WebDriver driver;
	String title = "";

	By username = By.name("uid");
	By password = By.name("password");
	By loginBtn = By.name("btnLogin");
	By managerId = By.cssSelector("tr.heading3 > td:nth-child(1)");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void loginIntoDemo(String userName, String passWord) throws Exception {
		
		driver.findElement(username).sendKeys(userName);
		driver.findElement(password).sendKeys(passWord);
		driver.findElement(loginBtn).click();
		//Thread.sleep(3000);
	}
	public Boolean verifyManagerID(String username) throws NoSuchElementException
	{
		try{
		driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		System.out.println("Error Message:  User or Password is not valid");
		return false;
		}
//		if(text.equals("User or Password is not valid")){
//			return true;
//		}else return false;
//		}
		catch(NoAlertPresentException Ex){
		
		WebDriverWait elementWait = new WebDriverWait(driver, 3);
		elementWait.until(ExpectedConditions.visibilityOfElementLocated(managerId));
			String actual = driver.findElement(managerId).getText();
			System.out.println("Manager ID ===>" + actual);
			String[] dynamicText= actual.split(":");
			String partFirst= dynamicText[1];
			
			return partFirst.contains(username);
			
		
					}
	}

}
