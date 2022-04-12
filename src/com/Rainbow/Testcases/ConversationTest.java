package com.Rainbow.Testcases;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Rainbow.base.TestBase;
public class ConversationTest extends TestBase {
	
	public ConversationTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	@BeforeMethod
	public void login() throws InterruptedException {	 
		inittializationWithOption();
			WebElement EmailText = new WebDriverWait(driver, 20).
					until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='username']")));
			
			EmailText.click();
			EmailText.sendKeys(prop.getProperty("userName"));		
			
			WebElement continueButton = new WebDriverWait(driver, 20).
					until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='c-button__label']")));
			
			Thread.sleep(500);
		    continueButton.click(); 
		
		   WebElement PasswordText = new WebDriverWait(driver, 20).
				    until(ExpectedConditions.elementToBeClickable(By.id("authPwd")));
		
		 PasswordText.sendKeys(prop.getProperty("password"));
		 
		 Thread.sleep(500);
		 continueButton.click(); 
		 
		 WebElement Whatsnew = new WebDriverWait(driver, 20).
					until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"popup\"]/userwindow/userwindow-header/div/button")));
		 Whatsnew.click(); 
		 
		 WebElement WelcomeScreen = new WebDriverWait(driver, 20).
					until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"popup\"]/userwindow/userwindow-footer/div/square-button[1]")));
	     WelcomeScreen.click(); 	
	}
	
	@Test
	public  void ConversationTest() throws IOException {   
	//	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		Actions action = new Actions(driver);
		WebElement searchName = new WebDriverWait(driver, 30).
				until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@placeholder='People, bubbles...'])[1]")));
		searchName.sendKeys("Maysam"); 
		
		WebElement choiceName = new WebDriverWait(driver, 30).
				until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[contains(@class,'conversation-cell__infos')])[1]")));
		choiceName.click(); 

		WebElement messageText = new WebDriverWait(driver, 30).
				until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@placeholder='Enter your text here...'])[1]")));
		messageText.click();
		
		action.sendKeys("Hello Maysam!",Keys.chord(Keys.ENTER)).build().perform();
		WebElement statusMessageText =	new WebDriverWait(driver, 10).
		until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("(//div[@class='chat-view-item__bubble'])[24]")));
		
		System.out.println(statusMessageText.isDisplayed());
		
		Assert.assertTrue(statusMessageText.isDisplayed(),"The message was not sent successfully");
	
      }
	@AfterMethod()
	public void afterMethod() {
		driver.quit();
	}
}
