package com.wfmtesting.testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Headless 
{	
	@Test
	  public void logintest() 
	  {
		
		WebDriver driver = new FirefoxDriver();
		
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
			    	    
			  //quit driver
			  driver.quit();
			    
		  } 
		  catch (Exception e) 
		  {
			
			  System.out.println(e.getMessage());
			  
		  }
		
	  }

}
