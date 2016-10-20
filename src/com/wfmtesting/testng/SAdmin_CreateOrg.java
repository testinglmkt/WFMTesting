package com.wfmtesting.testng;


import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SAdmin_CreateOrg 
{
	@Test
	public void CreateORG() 
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
			
		    //get org details
			File src = new File("Org_Details.xlsx");
			FileInputStream fis =new FileInputStream(src);
			@SuppressWarnings("resource")
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh1 = wb.getSheetAt(0);
			
			//add organization
			driver.findElement(By.xpath("//li[2]/a/span")).click();
			
		    driver.findElement(By.linkText("Add Organization")).click();
		    
		    //org details
		    driver.findElement(By.id("name")).clear();
		    String orgname = sh1.getRow(0).getCell(0).getStringCellValue();
		    driver.findElement(By.id("name")).sendKeys(orgname);
		    
		    driver.findElement(By.id("email")).clear();
		    String orgemail = sh1.getRow(0).getCell(1).getStringCellValue();
		    driver.findElement(By.id("email")).sendKeys(orgemail);
		    
		    driver.findElement(By.id("phone")).clear();
		    String orgphone = sh1.getRow(0).getCell(2).getStringCellValue();
		    driver.findElement(By.id("phone")).sendKeys(orgphone);
		    
		    driver.findElement(By.cssSelector("button.btn.btn-save")).click();
		    
		    //org admin details
		    driver.findElement(By.id("name")).clear();
		    String adminname = sh1.getRow(0).getCell(3).getStringCellValue();
		    driver.findElement(By.id("name")).sendKeys(adminname);
		    
		    driver.findElement(By.id("email")).clear();
		    String adminemail = sh1.getRow(0).getCell(4).getStringCellValue();
		    driver.findElement(By.id("email")).sendKeys(adminemail);
		    
		    driver.findElement(By.id("password")).clear();
		    String adminpass = sh1.getRow(0).getCell(5).getStringCellValue();
		    driver.findElement(By.id("password")).sendKeys(adminpass);
		    
		    driver.findElement(By.id("password_confirmation")).clear();
		    String adminpassC = sh1.getRow(0).getCell(6).getStringCellValue();
		    driver.findElement(By.id("password_confirmation")).sendKeys(adminpassC);
		    
		    driver.findElement(By.xpath("(//input[@id='msisdn'])[2]")).clear();
		    String MSISDN = sh1.getRow(0).getCell(7).getStringCellValue();
		    driver.findElement(By.xpath("(//input[@id='msisdn'])[2]")).sendKeys(MSISDN);
		   
		    driver.findElement(By.cssSelector("button.btn.btn-save")).click();
		    
		    //devices details
		    driver.findElement(By.id("imei")).clear();
		    String IMEI = sh1.getRow(0).getCell(8).getStringCellValue();
		    driver.findElement(By.id("imei")).sendKeys(IMEI);
		    
		    driver.findElement(By.id("msisdn2")).clear();
		    String Umsisdn = sh1.getRow(0).getCell(7).getStringCellValue();
		    driver.findElement(By.id("msisdn2")).sendKeys(Umsisdn);
		    
		    driver.findElement(By.cssSelector("span.fa.fa-plus-circle")).click();
		    
		    driver.findElement(By.cssSelector("div.col-md-8 > #imei")).clear();
		    driver.findElement(By.cssSelector("div.col-md-8 > #imei")).sendKeys(IMEI);
		    
		    driver.findElement(By.id("msisdn")).clear();
		    String Umsisdn1 = sh1.getRow(0).getCell(7).getStringCellValue();
		    driver.findElement(By.id("msisdn")).sendKeys(Umsisdn1);
		    
		    driver.findElement(By.id("add-imei")).click();
		    
			//update newly created admin
			
			driver.findElement(By.xpath("//li[2]/a/span")).click();
			
		    driver.findElement(By.name("name")).clear();
		    driver.findElement(By.name("name")).sendKeys("New Test");
		    
		    driver.findElement(By.xpath("//button[@value='Filter']")).click();
		    
		    driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
		    
		    driver.findElement(By.name("name")).click();
		    driver.findElement(By.name("name")).clear();
		    driver.findElement(By.name("name")).sendKeys("New Name");
		    
		    driver.findElement(By.name("email")).click();
		    driver.findElement(By.name("email")).clear();
		    driver.findElement(By.name("email")).sendKeys("newemail@outlook.com");
		    
		    driver.findElement(By.id("password")).click();
		    driver.findElement(By.id("password")).clear();
		    driver.findElement(By.id("password")).sendKeys("123456");

		    driver.findElement(By.id("password_confirmation")).click();
		    driver.findElement(By.id("password_confirmation")).clear();
		    driver.findElement(By.id("password_confirmation")).sendKeys("123456");
		    
		    driver.findElement(By.name("msisdn")).click();
		    driver.findElement(By.name("msisdn")).clear();
		    driver.findElement(By.name("msisdn")).sendKeys("03369654789");
		   
		    driver.findElement(By.xpath("//input[@value='Update Admin']")).click();
		    
		    
		    //search imei we recently added
		    
		    driver.findElement(By.cssSelector("i.fa.fa-mobile-phone")).click();
		    
		    driver.findElement(By.name("imei")).clear();
		    driver.findElement(By.name("imei")).sendKeys(IMEI);
		    
		    driver.findElement(By.xpath("//button[@value='Filter']")).click();

		    if(!driver.findElements(By.xpath("/html/body/div/div[2]/section[2]/div[1]/div/div/p")).isEmpty())
		    {
		    	String error = driver.findElement(By.xpath("/html/body/div/div[2]/section[2]/div[1]/div/div/p")).getText();
			    System.out.println("Error = " + error);
		    	Assert.assertEquals(1,0);
		    }
		    else
		    {
		    	Assert.assertEquals(1,1);
		    }

		    
		    //delete recently added imei
		    
		    driver.findElement(By.cssSelector("i.fa.fa-mobile-phone")).click();
		    
		    driver.findElement(By.name("imei")).clear();
		    driver.findElement(By.name("imei")).sendKeys(IMEI);
		    driver.findElement(By.xpath("//button[@value='Filter']")).click();
		    driver.findElement(By.cssSelector(".fa-trash-o")).click();
		    //switch
			driver.switchTo().activeElement();
			Thread.sleep(1000);
			//confirm delete
			driver.findElement(By.xpath("//button[@id='confirm']")).click();
		    
		    
		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage());	 
		}
		

	}
	
}
