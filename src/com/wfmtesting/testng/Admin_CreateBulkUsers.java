package com.wfmtesting.testng;


import java.io.FileReader;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;

public class Admin_CreateBulkUsers 
{

	@SuppressWarnings("resource")
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
		    
		    //create user
		    driver.findElement(By.linkText("Users")).click();
			driver.findElement(By.linkText("Add User")).click();
    	    
			//open csv file
			WebElement element = driver.findElement(By.id("uploadBtn"));
			element.sendKeys("C:\\Users\\mfabrar\\workspace\\WFMTestingSuite\\CreateBulkUser.csv");
			
		    driver.findElement(By.cssSelector("input.btn.btn-wfm")).click();
		    
		    try {
		        //csv file containing data
		        String strFile = "C:\\Users\\mfabrar\\workspace\\WFMTestingSuite\\CreateBulkUser.csv";
		        CSVReader reader = new CSVReader(new FileReader(strFile));
		        String [] nextLine;
		        while ((nextLine = reader.readNext()) != null) 
		        {
		        	
		          System.out.println(nextLine[0]);
		          
		          driver.findElement(By.linkText("Users")).click();
		          driver.findElement(By.name("name")).clear();
		          driver.findElement(By.name("name")).sendKeys(nextLine[0]);
		          driver.findElement(By.xpath("//button[@value='Filter']")).click();

		          if(driver.findElements(By.xpath("/html/body/div/div[2]/section[2]/div[1]/div/div/p/b")).size() != 0)
		          {
		        	  
		          }
		          else
		          {
		        	  driver.findElement(By.xpath("//a[2]")).click();
			          driver.findElement(By.cssSelector("i.fa.fa-trash-o")).click();
			          //switch
			          driver.switchTo().activeElement();
			          Thread.sleep(1000);
			          //confirm delete
			          driver.findElement(By.xpath("//button[@id='confirm']")).click();
		          }
		         
		          
		        }
		        
		    	}
		        catch (Exception e) {
		        	System.out.println(e.getMessage());	
				}
			
			
			//quit driver
		    driver.quit();
		    
		}
		
		catch (Exception e) 
		{
			System.out.println(e.getMessage());	 
		}	
			
	}
}
