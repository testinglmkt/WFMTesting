package com.wfmtesting.testng;

import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class Admin_UnitTest 
{
	
	Random ran = new Random();
	int num = ran.nextInt(1000);
	public String unitname = "TestUnit" + num;
	public String username = "RandomUser" + num;
	public String useremail = username + "@gmail.com";
	public String userpass = "123456";
	
	WebDriver driver = new FirefoxDriver();
	
	 public static final String USERNAME = "testinglmkt";
	 public static final String ACCESS_KEY = "d10e250d-3200-4639-8f10-cd52353a3de8";
	 public static final String URL = "http://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";
	
  @Test
  public void CreateUnitUserAssign() 
  {
 
	  try 
	  {
		  System.out.println(URL);
		  
		  /*DesiredCapabilities caps = DesiredCapabilities.firefox();
		  caps.setCapability("platform", "Windows 10");
		  caps.setCapability("version", "46.0");
		 
		  WebDriver driver = new RemoteWebDriver(new URL(URL), caps);*/
		  
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  driver.get("http://beta.sovoia.com/wfm/auth/login");
	  
		   //login
			driver.findElement(By.name("email")).clear();
		    driver.findElement(By.name("email")).sendKeys("testinglmkt@gmail.com");
		    driver.findElement(By.name("password")).clear();
		    driver.findElement(By.name("password")).sendKeys("987654");
		    driver.findElement(By.xpath("//button[@type='submit']")).click();
		    
		    
		    //add unit
		    driver.findElement(By.linkText("Organogram")).click();
		    driver.findElement(By.linkText("Units")).click();
		    driver.findElement(By.linkText("Add Unit")).click();
		    driver.findElement(By.id("name")).clear();
		    driver.findElement(By.id("name")).sendKeys(unitname);
		    new Select(driver.findElement(By.id("form"))).selectByVisibleText("Admin");
		    driver.findElement(By.cssSelector("input.btn.btn-wfm")).click();
		    
		    
		    //create user
		    driver.findElement(By.linkText("Users")).click();
			driver.findElement(By.linkText("Add User")).click();
		    
		    driver.findElement(By.name("name")).clear();
		    driver.findElement(By.name("name")).sendKeys(username);
		    
		    driver.findElement(By.xpath("(//input[@id='empty_email'])[2]")).clear();
		    driver.findElement(By.xpath("(//input[@id='empty_email'])[2]")).sendKeys(useremail);
		    
		    driver.findElement(By.id("password")).clear();
		    driver.findElement(By.id("password")).sendKeys(userpass);
		    
		    driver.findElement(By.id("password_confirmation")).clear();
		    driver.findElement(By.id("password_confirmation")).sendKeys(userpass);
		    
		    driver.findElement(By.id("msisdn")).clear();
		    driver.findElement(By.id("msisdn")).sendKeys("03368567121");
		    
		    new Select(driver.findElement(By.id("unit"))).selectByVisibleText(unitname);
		    
		    new Select(driver.findElement(By.id("role"))).selectByVisibleText("Worker");
		    
		    new Select(driver.findElement(By.id("reporting_to"))).selectByVisibleText("Manager2 (not a worker)");
		    
		    driver.findElement(By.cssSelector("input.btn.btn-wfm")).click();
		    
		    //delete newly created user
		    driver.findElement(By.linkText("Users")).click();
		    driver.findElement(By.name("name")).clear();
		    driver.findElement(By.name("name")).sendKeys(username);
		    driver.findElement(By.xpath("//button[@value='Filter']")).click();
		    driver.findElement(By.xpath("//a[2]")).click();
		    driver.findElement(By.cssSelector(".fa-trash-o")).click();		    
		    //switch
			driver.switchTo().activeElement();
			Thread.sleep(1000);
			//confirm delete
			driver.findElement(By.xpath("//button[@id='confirm']")).click();
		    
		    //quit driver
		    driver.quit();
		    
		   
	  } 
	  catch (Exception e) 
	  {
		
		  System.out.println(e.getMessage());
		  
	  }
	  
  }
  
  
}
