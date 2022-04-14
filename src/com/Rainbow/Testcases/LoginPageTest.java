package com.Rainbow.Testcases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.Rainbow.base.TestBase;

public class LoginPageTest extends TestBase {
	
	public LoginPageTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
		
	}
	@BeforeMethod
	public void setUp() {
		inittializationWithOption();
		
	}

	@Test(dataProvider="Mydata")

	public void LoginPage(String userName, String Password) throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, 12);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='username']"))).click();  //Click on the email TextBox
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='username']"))).sendKeys(userName); //Send email to email TextBox
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='c-button__label']"))).click(); //Click on the Continue Button
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("authPwd"))).click(); //Click on Password TextBox
           wait.until(ExpectedConditions.elementToBeClickable(By.id("authPwd"))).sendKeys(Password); //Send Password to Password TextBox
        
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='c-button__label']"))).click(); //Click on the Continue Button

	    
	    
	 if(userName==prop.getProperty("userName") && Password==prop.getProperty("password") ) 
	 {
		 boolean actualResult = wait.until(ExpectedConditions.urlToBe("https://web.openrainbow.net/rb/2.103.0/index.html#/main/home"));// Wait to Load Login Page
		 Assert.assertTrue(actualResult ,"Login to the System unsuccessful" ); //Positive Test (pass if Login successful )
		
	 }
	 	
	  if (userName==prop.getProperty("wrongUserName") | Password==prop.getProperty("wrongPassword")) {
		boolean actualResult = wait.until(ExpectedConditions.textToBe(By.xpath("(//div[@ng-if='!$ctrl.isErrorCode'])[1]"), "Incorrect username or password"));
		 Assert.assertTrue(actualResult ,"Login to the System successful Or Error message in the username or password did not appear" );//negative Test (pass if login unsuccessful)
	 }
	
	 }
	
	 @DataProvider
	  public Object[][] Mydata() {
		 
		 Object [][] data = new Object[4][2];
		 //TestCases 1 (Enter a valid userName and Password) 
		 data[0][0] = prop.getProperty("userName");
		 data[0][1] =  prop.getProperty("password");
		 //TestCases 2 (Enter a valid userName and wrongPassword) 
		 data[1][0] = prop.getProperty("userName");
		 data[1][1] = prop.getProperty("wrongPassword");
		 //TestCases 3 (Enter a wrong userName and valid Password) 
		 data[2][0] = prop.getProperty("wrongUserName");
		 data[2][1] = prop.getProperty("password");
		 //TestCases 4 (Enter a wrong userName and wrong Password)
		 data[3][0] = prop.getProperty("wrongUserName");
		 data[3][1] = prop.getProperty("wrongPassword");

		 
		 return data; 

	 }
	
		@AfterMethod
		public void tearDown()  {
		
	
			 driver.quit();
			
		}

}
