package com.wfmtesting.testng;


import java.util.Iterator;
import java.util.List;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;

public class Admin_ExportCSVSingleForm {
	
	@Test
	public void UserStory_6595() 
	  {
		
		//firefox profile to download
		
		//Create object of FirefoxProfile in built class to access Its properties.
		  FirefoxProfile fprofile = new FirefoxProfile();
		  //Set Location to store files after downloading.
		  fprofile.setPreference("browser.download.dir", "C:\\Users\\mfabrar\\workspace\\WFMTestingSuite\\");
		  fprofile.setPreference("browser.download.folderList", 2);
		  //Set Preference to not show file download confirmation dialogue using MIME types Of different file extension types.
		  fprofile.setPreference("browser.helperApps.neverAsk.saveToDisk", 
		    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;"//MIME types Of MS Excel File.
		    + "application/pdf;" //MIME types Of PDF File.
		    + "application/vnd.openxmlformats-officedocument.wordprocessingml.document;" //MIME types Of MS doc File.
		    + "text/plain;" //MIME types Of text File.
		    + "text/csv"); //MIME types Of CSV File.
		  fprofile.setPreference( "browser.download.manager.showWhenStarting", false );
		  fprofile.setPreference( "pdfjs.disabled", true );
		
		WebDriver driver= new FirefoxDriver(fprofile);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://beta.sovoia.com/wfm/auth/login");
			
		try
		{
			//login
			driver.findElement(By.name("email")).clear();
		    driver.findElement(By.name("email")).sendKeys("testinglmkt@gmail.com");
		    driver.findElement(By.name("password")).clear();
		    driver.findElement(By.name("password")).sendKeys("987654");
		    driver.findElement(By.xpath("//button[@type='submit']")).click();
		    
		    //open forms
		    driver.findElement(By.xpath("//li[3]/a/span")).click();
		    
		    //search
		    driver.findElement(By.name("name")).clear();
		    driver.findElement(By.name("name")).sendKeys("Customer Satisfaction Survey");
		    driver.findElement(By.xpath("//button[@value='Filter']")).click();
		    driver.findElement(By.cssSelector(".table > thead:nth-child(1) > tr:nth-child(1) > th:nth-child(1) > small:nth-child(1) > a:nth-child(2)")).click();
		    
		    //export csv/download file
		    driver.findElement(By.cssSelector("i.fa.fa-table")).click();
		    driver.findElement(By.xpath("//button[@type='button']")).click();	
		    
		    //read csv file which was downloaded
		    // This will load csv file 
   		 	CSVReader reader = new CSVReader(new FileReader("C:\\Users\\mfabrar\\workspace\\WFMTstingSuite\\data.csv"));
   		 	
   		 	// this will load content into list
   		 	List<String[]> li=(List<String[]>) reader.readAll();
   		 	// create Iterator reference
   		 	Iterator<String[]>i1= li.iterator();
   		    
   		 	// Iterate all values 
   		 
   		 	String [] str = null;
   		 
   		 	while(i1.hasNext()){
   		     
   			 str=i1.next();
   			   
   			 System.out.print(" Values are ");
   			 
   			 for(int i=0;i<str.length;i++)
   			{    			 
   			   System.out.print(" "+str[i]);    			 
   			}
   			   System.out.println("   ");   			         			    
   			}	
   		 
    		reader.close();
		    
    		//delete downloaded file
    		File file = new File("C:\\Users\\mfabrar\\workspace\\WFMTstingSuite\\data.csv");
    		if(file.delete()){
                System.out.println(file.getName() + " is deleted!");
            }else{
                System.out.println("Delete operation is failed.");
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
