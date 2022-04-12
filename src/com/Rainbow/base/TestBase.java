package com.Rainbow.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {
	public static WebDriver driver ,obtiondriver; 
	public static Properties prop ;
	public static WebDriverWait wait;
	public static  ChromeOptions option;
	public TestBase() throws IOException {
		
		prop = new Properties();
		FileInputStream fis = new FileInputStream("D:\\KhaledProjects\\Selenium\\Selenium\\RainbowApplicationTest\\src\\com\\Rainbow\\config\\config.properties"); 
		prop.load(fis);
	}
	
	public void inittializationWithOption( ) {
		System.setProperty("webdriver.chrome.driver","D:\\KhaledProjects\\Selenium\\Selenium\\WebDriver\\chromedriver_win32\\chromedriver.exe");
		ChromeOptions option = new ChromeOptions(); 
		 option.addArguments("--use-fake-ui-for-media-stream"); 
		 driver = new ChromeDriver(option);
		 driver.get(prop.getProperty("URL"));
		 driver.manage().window().maximize();

		}

}

