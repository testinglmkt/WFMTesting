package com.wfmtesting.testng;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Admin_LoginEnableTest 
{
	
	Random ran = new Random();
	int num = ran.nextInt(1000);
	public String username = "RandomUser" + num;
	public String useremail = username + "@gmail.com";
	public String userpass = "123456";
	
	WebDriver driver = new FirefoxDriver();
	
  @Test
  public void LoginEnableDisable() 
  {
	  try 
	  {
		
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  driver.get("http://beta.sovoia.com/wfm/auth/login");
	  
		   //login
			driver.findElement(By.name("email")).clear();
		    driver.findElement(By.name("email")).sendKeys("testinglmkt@gmail.com");
		    driver.findElement(By.name("password")).clear();
		    driver.findElement(By.name("password")).sendKeys("987654");
		    driver.findElement(By.xpath("//button[@type='submit']")).click();
		    
		    
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
		    
		    new Select(driver.findElement(By.id("unit"))).selectByVisibleText("Admin");
		    
		    new Select(driver.findElement(By.id("role"))).selectByVisibleText("Worker");
		    
		    new Select(driver.findElement(By.id("reporting_to"))).selectByVisibleText("Manager2 (not a worker)");
		    
		    driver.findElement(By.cssSelector("input.btn.btn-wfm")).click();	
		    
		    //disable newly created user
		    
		    driver.findElement(By.name("name")).clear();
		    driver.findElement(By.name("name")).sendKeys(username);
		    driver.findElement(By.xpath("//button[@value='Filter']")).click();
		    driver.findElement(By.xpath("//a[2]")).click();
		    driver.findElement(By.xpath("//a[4]/i")).click();
		  	
		  	//logout from admin
		  	driver.findElement(By.linkText("TestingLmkt")).click();
		    driver.findElement(By.linkText("Sign out")).click();
		    
		    //login from new user
		    driver.findElement(By.name("email")).clear();
		    driver.findElement(By.name("email")).sendKeys(useremail);
		    driver.findElement(By.name("password")).clear();
		    driver.findElement(By.name("password")).sendKeys(userpass);
		    driver.findElement(By.xpath("//button[@type='submit']")).click();
		  	
		    //check for login error
		    String error = driver.findElement(By.xpath("/html/body/div/div[2]")).getText();
		    System.out.println("Error is = " + error);   
		    String errorexpected = "Whoops! There were some problems with your input.\n\nUser is disbaled. Please contact administrator";
		    
		    Assert.assertEquals(error, errorexpected);
		    
		    //login from admin again
			driver.findElement(By.name("email")).clear();
		    driver.findElement(By.name("email")).sendKeys("testinglmkt@gmail.com");
		    driver.findElement(By.name("password")).clear();
		    driver.findElement(By.name("password")).sendKeys("987654");
		    driver.findElement(By.xpath("//button[@type='submit']")).click();
		    
		    //enable newly created user
		    driver.findElement(By.linkText("Users")).click();
		    driver.findElement(By.name("name")).clear();
		    driver.findElement(By.name("name")).sendKeys(username);
		    driver.findElement(By.xpath("//button[@value='Filter']")).click();
		    driver.findElement(By.xpath("//a[2]")).click();
		    driver.findElement(By.xpath("//a[4]/i")).click();
		    
		    //logout from admin
		  	driver.findElement(By.linkText("TestingLmkt")).click();
		    driver.findElement(By.linkText("Sign out")).click();
		    
		    //login from new user
		    driver.findElement(By.name("email")).clear();
		    driver.findElement(By.name("email")).sendKeys(useremail);
		    driver.findElement(By.name("password")).clear();
		    driver.findElement(By.name("password")).sendKeys(userpass);
		    driver.findElement(By.xpath("//button[@type='submit']")).click();
		  	
		    //check for login error
		    String error1 = driver.findElement(By.xpath("/html/body/div/div[2]")).getText();
		    System.out.println("Error is = " + error1);    
		    
		    //logout from user
		    driver.findElement(By.cssSelector(".dropdown-toggle")).click();
		    driver.findElement(By.linkText("Sign out")).click();
		    
		    //login from admin again
			driver.findElement(By.name("email")).clear();
		    driver.findElement(By.name("email")).sendKeys("testinglmkt@gmail.com");
		    driver.findElement(By.name("password")).clear();
		    driver.findElement(By.name("password")).sendKeys("987654");
		    driver.findElement(By.xpath("//button[@type='submit']")).click();
		    
		    
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
