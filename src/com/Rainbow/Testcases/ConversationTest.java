package com.Rainbow.Testcases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
	public static WebDriverWait wait ;
	
	public ConversationTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	@BeforeMethod
	public void login() throws InterruptedException {	 
		inittializationWithOption();
		
		 wait  = new WebDriverWait(driver, 12);
		wait.until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//input[@id='username']"))).click();  //Click on the email TextBox
		
		wait.until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//input[@id='username']"))).sendKeys(prop.getProperty("userName")); //Send email to email TextBox
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath
				("//span[@class='c-button__label']"))).click(); //Click on the Continue Button
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("authPwd"))).click(); //Send Password to Password TextBox
        wait.until(ExpectedConditions.visibilityOfElementLocated
        		(By.id("authPwd"))).sendKeys(prop.getProperty("password")); //Send Password to Password TextBox
    
        
	    wait.until(ExpectedConditions.elementToBeClickable
	    		(By.xpath("//span[@class='c-button__label']"))).click(); //Click on the Continue Button
	    
	    wait.until(ExpectedConditions.elementToBeClickable
	    		(By.xpath("//*[@id=\"popup\"]/userwindow/userwindow-header/div/button"))).click(); //Click on the whatsNew Button After Login
	    
	    wait.until(ExpectedConditions.elementToBeClickable
	    		(By.xpath("//*[@id=\"popup\"]/userwindow/userwindow-footer/div/square-button[1]"))).click();//Click on the Welcome Screen Button After Login
	
	}
	
	@Test
	public  void ConversationTest(){   
		 wait  = new WebDriverWait(driver, 12);
		Actions action = new Actions(driver);
		
		WebElement searchName = wait.
				until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@placeholder='People, bubbles...'])[1]")));
		searchName.sendKeys("Maysam"); //Type a name in the search TextBox
		
		WebElement choiceName = wait.
				until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[contains(@class,'conversation-cell__infos')])[1]")));
		choiceName.click(); //choice First Name from result after search TextBox 

		WebElement messageText = wait.
				until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@placeholder='Enter your text here...'])[1]")));
		messageText.click(); //click on messageTexBox
		
		action.sendKeys("Hello Maysam!",Keys.chord(Keys.ENTER)).build().perform(); //Write and send a message
		boolean statusMessageText =	wait.
		        until(ExpectedConditions.visibilityOfElementLocated(
		        		By.xpath("(//div[@class='chat-view-item__bubble'])[1]"))).isDisplayed();//Check that the message has been sent successfully

		Assert.assertTrue(statusMessageText,"The message was not sent successfully"); 
	
      }
	@AfterMethod()
	public void afterMethod() {
		driver.quit();
	}
}
