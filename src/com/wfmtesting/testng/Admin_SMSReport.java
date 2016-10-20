package com.wfmtesting.testng;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Admin_SMSReport 
{

	@Test
	public void CreateUsersFromCSV() 
	{
		
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://beta.sovoia.com/wfm/auth/login");
		
		
		try
		{	 
	
			//login
			driver.findElement(By.name("email")).clear();
		    driver.findElement(By.name("email")).sendKeys("testinglmkt@gmail.com");
		    driver.findElement(By.name("password")).clear();
		    driver.findElement(By.name("password")).sendKeys("987654");
		    
		    driver.findElement(By.xpath("//button[@type='submit']")).click();
		    
		    //open sms reports
		    driver.findElement(By.linkText("Reports")).click();
		    driver.findElement(By.linkText("SMS Report")).click();
		    
		    //select time period
		    
		    //this month date
		    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy ");
		    Date date = new Date();
		    String current= dateFormat.format(date);
		    
		    //last month date
		    Calendar cal = Calendar.getInstance();
		    cal.add(Calendar.MONTH, -1);
		    Date date1 = cal.getTime();
		    String previous= dateFormat.format(date1);
		    
		    System.out.println(previous);
		    System.out.println(current);
		    
		    //set dates
		    
		    driver.findElement(By.id("dateform")).click();
		    driver.findElement(By.id("dateform")).clear();
		    driver.findElement(By.id("dateform")).sendKeys(previous);
			
		    driver.findElement(By.id("dateto")).click();
		    driver.findElement(By.id("dateto")).clear();
		    driver.findElement(By.id("dateto")).sendKeys(current); 	
		    
		    driver.findElement(By.cssSelector("input.btn.btn-wfm")).click();
		    
			//quit driver
		    driver.quit();
		    
		}
		
		catch (Exception e) 
		{
			System.out.println(e.getMessage());	 
		}	
			
	}
}
