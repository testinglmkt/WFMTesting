package com.wfmtesting.testng;


import java.io.FileReader;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;

public class SAdmin_CreateBulkIMEI 
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
		    driver.findElement(By.name("email")).sendKeys("ibrar@flikkable.com");
		    driver.findElement(By.name("password")).clear();
		    driver.findElement(By.name("password")).sendKeys("123456");
		    
		    driver.findElement(By.xpath("//button[@type='submit']")).click();
		    
		    //open organizations
		    driver.findElement(By.linkText("Organizations")).click();
		    driver.findElement(By.cssSelector("a[title=\"Add IMEI's\"] > i.fa.fa-plus")).click();
		    
		    driver.findElement(By.id("imei_file")).clear();
		    driver.findElement(By.id("imei_file")).sendKeys("C:\\Users\\mfabrar\\workspace\\WFMTestingSuite\\CreateBulkIMEI.csv");
    	    
		    driver.findElement(By.id("add-imei")).click();
		    
		    try {
		        //csv file containing data
		        String strFile = "C:\\Users\\mfabrar\\workspace\\WFMTestingSuite\\CreateBulkIMEI.csv";
		        CSVReader reader = new CSVReader(new FileReader(strFile));
		        String [] nextLine;
		        while ((nextLine = reader.readNext()) != null) 
		        {
		        	
		          System.out.println(nextLine[0]);
		          
		          driver.findElement(By.linkText("Devices")).click();
		          driver.findElement(By.name("imei")).clear();
		          driver.findElement(By.name("imei")).sendKeys(nextLine[0]);
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
