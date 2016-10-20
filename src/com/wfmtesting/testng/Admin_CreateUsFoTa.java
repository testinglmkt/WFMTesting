package com.wfmtesting.testng;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Admin_CreateUsFoTa 
{
	Random ran = new Random();
	int num = ran.nextInt(1000);
	public String username = "RandomUser" + num;
	public String useremail = username + "@gmail.com";
	public String userpass = "123456";
	public String formname = "RandomForm" + num;
	public String taskname = "RandomTask" + num;
	public String[] valueToWrite = {username,useremail,userpass};
	
	@SuppressWarnings("resource")
	@Test
	public void CreateUserFormTaskAssign() 
	{
		
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://beta.sovoia.com/wfm/auth/login");
		
		//save data
	    try {
	        
	    	  FileInputStream file = new FileInputStream("C:\\Users\\mfabrar\\workspace\\WFMTestingSuite\\UserData.xlsx"); 
	    	  XSSFWorkbook workbook = new XSSFWorkbook(file);
	    	 
	    	  XSSFSheet sheet = workbook.getSheetAt(0);

	    	  //Retrieve the row and check for null
	    	  int a  = sheet.getLastRowNum();
	    	  XSSFRow sheetrow = sheet.getRow(a+1);
	    	  if(sheetrow == null){
	    	      sheetrow = sheet.createRow(a+1);
	    	  }
	    	  for (int i=0;i<3;i++)
	    	  {
	    		  //Update the value of cell
	    		  Cell cell = sheetrow.getCell(i);
	    		  if(cell == null){
	    		      cell = sheetrow.createCell(i);
	    		  }
	    		  cell.setCellValue(valueToWrite[i]);
	    	  }
	    	 
	    	  file.close();
	    	 
	    	  FileOutputStream outFile =new FileOutputStream(new File("C:\\Users\\mfabrar\\workspace\\WFMTestingSuite\\UserData.xlsx"));
	    	  workbook.write(outFile);
	    	  outFile.close();
	    	 
	    	 } catch (FileNotFoundException fnfe) {
	    	  fnfe.printStackTrace();
	    	 } catch (IOException ioe) {
	    	  ioe.printStackTrace();
	    	 }
		
		
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
    	    
    	    new Select(driver.findElement(By.id("role"))).selectByVisibleText("Worker");
    	    
    	    new Select(driver.findElement(By.id("reporting_to"))).selectByVisibleText("Manager2 (not a worker)");
    	    
    	    driver.findElement(By.cssSelector("input.btn.btn-wfm")).click();
    	    
    	    //create form
    	    driver.findElement(By.linkText("Forms")).click();
    		
    	    driver.findElement(By.cssSelector("i.fa.fa-plus")).click();
    	    
    	    driver.findElement(By.linkText("Text")).click();
    	    driver.findElement(By.id("name")).clear();
    	    driver.findElement(By.id("name")).sendKeys(formname);
    	    
    	    driver.findElement(By.cssSelector("div.fb-label-description > input.form-control")).clear();
    	    driver.findElement(By.cssSelector("div.fb-label-description > input.form-control")).sendKeys("What is your name?");
    	    
    	    driver.findElement(By.linkText("Add")).click();
    	    driver.findElement(By.linkText("Checkboxes")).click();
    	    
    	    driver.findElement(By.cssSelector("div.fb-label-description > input.form-control")).clear();
    	    driver.findElement(By.cssSelector("div.fb-label-description > input.form-control")).sendKeys("Gender?");
    	    
    	    driver.findElement(By.cssSelector("input.option-label-input")).clear();
    	    driver.findElement(By.cssSelector("input.option-label-input")).sendKeys("Male");
    	    
    	    driver.findElement(By.xpath("(//input[@type='text'])[4]")).clear();
    	    driver.findElement(By.xpath("(//input[@type='text'])[4]")).sendKeys("Female");
    	    
    	    driver.findElement(By.linkText("Add")).click();
    	    driver.findElement(By.linkText("Date")).click();
    	    
    	    driver.findElement(By.cssSelector("div.fb-label-description > input.form-control")).clear();
    	    driver.findElement(By.cssSelector("div.fb-label-description > input.form-control")).sendKeys("D.O.B?");
    	    
    	    driver.findElement(By.linkText("Add")).click();
    	    driver.findElement(By.linkText("Dropdown")).click();
    	    
    	    driver.findElement(By.cssSelector("div.fb-label-description > input.form-control")).clear();
    	    driver.findElement(By.cssSelector("div.fb-label-description > input.form-control")).sendKeys("Organization");
    	    
    	    driver.findElement(By.cssSelector("input.option-label-input")).clear();
    	    driver.findElement(By.cssSelector("input.option-label-input")).sendKeys("LMKR");
    	    
    	    driver.findElement(By.xpath("(//input[@type='text'])[4]")).clear();
    	    driver.findElement(By.xpath("(//input[@type='text'])[4]")).sendKeys("LMKT");
    	    
    	    driver.findElement(By.linkText("Add")).click();
    	    driver.findElement(By.xpath("//form[@id='dataform']/div[2]/div/button")).click();
    	    
    	    //create task
    	    driver.findElement(By.linkText("Tasks")).click();
    	    
    	    driver.findElement(By.linkText("Add Task")).click();
    	    
    	    driver.findElement(By.id("name")).click();
    	    driver.findElement(By.id("name")).clear();
    	    driver.findElement(By.id("name")).sendKeys(taskname);
    	    
    	    driver.findElement(By.id("time")).click();
    	    driver.findElement(By.id("time")).sendKeys("0010");
    	    
    	    driver.findElement(By.name("start_time")).click();
    	    
    	    new Select(driver.findElement(By.id("form"))).selectByVisibleText(formname);
    	    
    	    driver.findElement(By.id("description")).clear();
    	    driver.findElement(By.id("description")).sendKeys("Dummy Description");
    	    
    	    driver.findElement(By.cssSelector("button.btn.btn-save")).click();
    	  
    	    //assign task
    	    driver.findElement(By.linkText("Tasks")).click();
		    
		    driver.findElement(By.name("name")).clear();
		    driver.findElement(By.name("name")).sendKeys(taskname);
		    //driver.findElement(By.name("name")).sendKeys("Survey of Product 1 & 2");
		    driver.findElement(By.xpath("//button[@value='Filter']")).click();
		    driver.findElement(By.xpath("//a[2]")).click();
		    
		    driver.findElement(By.cssSelector("i.fa.fa-check-square-o")).click();
		    
		    driver.findElement(By.xpath("/html/body/div/div[2]/section[2]/div/form/div[1]/div/div[2]/div/button")).click();
		    Thread.sleep(2000);
		    
		    List<WebElement> checkboxes = driver.findElements(By.cssSelector("html body.skin-black-light.sidebar-mini div.wrapper div.content-wrapper section.content div.box form.form-horizontal div.form-inner div.row.form-group div.col-sm-9 div.ms-options-wrap div.ms-options ul"));

		    for(WebElement checkbox : checkboxes)
		    {
			    
		    	Thread.sleep(1000);
		    	String cname = null;
		    	cname = checkbox.getText();
		    	System.out.println(cname);
		    	System.out.println(checkbox.isDisplayed()); 

		    	WebDriverWait wait = new WebDriverWait(driver,300);
		    	wait.until(ExpectedConditions.elementToBeClickable(checkbox));
		    			
		    	if(!checkbox.isSelected())
		    	{
		    		checkbox.click();		    	
		    	}
		    	
		    		
		    }
		    		    
		    driver.findElement(By.cssSelector("button.btn.btn-wfm")).click();
		    
		    
		    //delete all data
		    driver.findElement(By.linkText("Tasks")).click();
		    driver.findElement(By.name("name")).clear();
		    driver.findElement(By.name("name")).sendKeys(taskname);
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
		    
		    
		    driver.findElement(By.linkText("Forms")).click();
		    driver.findElement(By.name("name")).clear();
		    driver.findElement(By.name("name")).sendKeys(formname);
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
		    
		    
		    driver.findElement(By.linkText("Users")).click();
		    driver.findElement(By.name("name")).clear();
		    driver.findElement(By.name("name")).sendKeys(username);
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
		    
		    
		    //quit driver
		    //driver.quit();
		    
		}
		
		catch (Exception e) 
		{
			System.out.println(e.getMessage());	 
		}	

	  }
	
	
}
