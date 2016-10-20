package com.wfmtesting.testng;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Admin_DesignationTests 
{
	
	Random ran = new Random();
	int num = ran.nextInt(1000);
	public String designame = "TestDesignation" + num;
	public String username = "RandomUser" + num;
	public String useremail = username + "@gmail.com";
	public String userpass = "123456";
	
	WebDriver driver = new FirefoxDriver();
	
  @Test
  public void CreateDesignationUserAssign() 
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
		    
		  //add designation
		    driver.findElement(By.linkText("Organogram")).click();
		    driver.findElement(By.linkText("Designations")).click();
		    driver.findElement(By.linkText("Add Designation")).click();
		    driver.findElement(By.id("name")).clear();
		    driver.findElement(By.id("name")).sendKeys(designame);
		    new Select(driver.findElement(By.id("reporting_id"))).selectByVisibleText("Manager");
		    driver.findElement(By.xpath("//input[@value='Add']")).click();
		    
		  //assign permissions for designation  
		  	driver.findElement(By.name("name")).clear();
		    driver.findElement(By.name("name")).sendKeys(designame);
		    driver.findElement(By.xpath("//button[@value='Filter']")).click();
		    driver.findElement(By.xpath("//a[2]")).click();
		    driver.findElement(By.cssSelector("i.fa.fa-key")).click();
		    
		    //task permissions only
		    driver.findElement(By.id("5795f3e9bb322d546310d567")).click();
		    driver.findElement(By.id("573aad9abffebcc70b8b4567")).click();
		    driver.findElement(By.id("57397423bffebc37098b4569")).click();
		    driver.findElement(By.id("573aadb4bffebcc20b8b4569")).click();
		    driver.findElement(By.id("57397408bffebc02088b4568")).click();
		    driver.findElement(By.id("573974d1bffebc38098b456a")).click();
		    driver.findElement(By.id("57d2a15cbb322d9372b2459c")).click();
		    driver.findElement(By.id("573aad8cbffebcc40b8b4568")).click();
		    driver.findElement(By.id("573aad7ebffebcc20b8b4568")).click();
		    driver.findElement(By.id("5747daa4bffebc5a088b4567")).click();
		    driver.findElement(By.id("575fdf4cbb322d2c4374c849")).click();
		    driver.findElement(By.id("573973d6bffebc38098b4568")).click();
		    
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
		    
		    //assign newly created designation
		    new Select(driver.findElement(By.id("role"))).selectByVisibleText(designame);
		    
		    new Select(driver.findElement(By.id("reporting_to"))).selectByVisibleText("Manager2 (not a worker)");
		    
		    driver.findElement(By.cssSelector("input.btn.btn-wfm")).click();
		    
		    //delete newly created designation
		  	driver.findElement(By.linkText("Organogram")).click();
		    driver.findElement(By.linkText("Designations")).click();
		    driver.findElement(By.name("name")).clear();
		    driver.findElement(By.name("name")).sendKeys(designame);
		    driver.findElement(By.xpath("//button[@value='Filter']")).click();
		    driver.findElement(By.cssSelector("i.fa.fa-trash-o")).click();
		    //switch
		  	driver.switchTo().activeElement();
		  	Thread.sleep(1000);
		  	//confirm delete
		  	driver.findElement(By.id("confirm")).click();
		  		
		  	//get error message if there is one
		  	String error = driver.findElement(By.xpath("/html/body/div/div[2]/section[2]/div[1]")).getText();
		  
		  	//System.out.println("Error is = " + error);
		  		
		  	Assert.assertEquals(error, "Designation is Assigned to User(s) can't be deleted");
		  	
		  	//logout from admin
		  	driver.findElement(By.linkText("TestingLmkt")).click();
		    driver.findElement(By.linkText("Sign out")).click();
		    
		    //login from new user
		    driver.findElement(By.name("email")).clear();
		    driver.findElement(By.name("email")).sendKeys(useremail);
		    driver.findElement(By.name("password")).clear();
		    driver.findElement(By.name("password")).sendKeys(userpass);
		    driver.findElement(By.xpath("//button[@type='submit']")).click();
		  	
		    //check if Task is there(task permission was added earlier in designation)
		    
		    if(driver.findElements(By.linkText("Tasks")).size() != 0)
		    {
		    	Assert.assertEquals(1, 1);
		    }
		    else
		    	{Assert.assertEquals(1, 0);}
		    
		    
		    //logout from new user
		    driver.findElement(By.cssSelector(".dropdown-toggle")).click();
		    driver.findElement(By.linkText("Sign out")).click();
		    
		    //login from admin
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
			
			//delete designation
			driver.findElement(By.linkText("Organogram")).click();
		    driver.findElement(By.linkText("Designations")).click();
		    driver.findElement(By.name("name")).click();
		    driver.findElement(By.name("name")).clear();
		    driver.findElement(By.name("name")).sendKeys(designame);
		    driver.findElement(By.xpath("//button[@value='Filter']")).click();
		    driver.findElement(By.xpath("//a[2]")).click();
		    driver.findElement(By.cssSelector("i.fa.fa-trash-o")).click();
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
