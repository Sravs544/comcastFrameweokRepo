package com.comcast.crm.contacttest;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryUtility.Contactpage;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.LoginPage;
import com.comcast.crm.objectrepositoryUtility.OrganizationInfoPage;

public class CreateContact extends BaseClass {

	@Test()
	public void CreateContact() throws IOException, Throwable {
		// create an object

		// read the data from fileUtility

		/*
		 * String BROWSER = fLib.getDataFromPropertiesFile("browser"); String URL =
		 * fLib.getDataFromPropertiesFile("url"); String USERNAME =
		 * fLib.getDataFromPropertiesFile("usernam"); String PASSWORD =
		 * fLib.getDataFromPropertiesFile("passwd");
		 * 
		 * // generate random number
		 * 
		 * Random random = new Random(); int randomInt = random.nextInt(1000);
		 * 
		 * // read TestScript from excel file
		 */

		String lastname = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();
		/*
		 * WebDriver driver = null;
		 * 
		 * if (BROWSER.equals("chrome")) { driver = new ChromeDriver(); } else if
		 * (BROWSER.equals("firefox")) { driver = new FirefoxDriver(); } else if
		 * (BROWSER.equals("edge")) { driver = new EdgeDriver(); } else { driver = new
		 * ChromeDriver(); }
		 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		 * 
		 * driver.get(URL);
		 * 
		 * // login to App
		 * 
		 * LoginPage lp = new LoginPage(driver);
		 * 
		 * lp.loginToApp(("admin", "admin");;// Login page check you can undrst and
		 * taking this method from business class
		 * 
		 * // navigate to organization module
		 */

		HomePage hp = new HomePage(driver);
		hp.getContactlnk().click();

		Contactpage cp = new Contactpage(driver);
		cp.getContactimg().click();
		cp.getLastnametxt().sendKeys(lastname);
		cp.getSavebtn().click();

		Contactpage cp1 = new Contactpage(driver);

		String actlastname = cp1.getHeadertxt().getText();

		if (actlastname.contains(lastname)) {
			System.out.println(lastname + "name is verified==PASS");
		} else {
			System.out.println(lastname + "name is not verified==PASS");
		}
	}
		
		@Test
		
		public void CreateContactWithSupportStartnEndDate() throws Throwable, Throwable {
			
		
		String coloumn1Data = eLib.getDataFromExcel("contact", 1, 1) +jLib.getRandomNumber();

		String lastname = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();

		HomePage hp=new HomePage(driver);

		hp.getContactlnk().click();
		
		//go to contact page and plus on + then enter the lastname

		Contactpage cp = new Contactpage(driver);
		cp.getContactimg().click();
		
	

		// creating date object

		String startDate = jLib.getSystemDateYYYYMMDD();
		String endDate = jLib.getRequiredDateYYYYMMDD(+30);

        
		cp.ContactDate(lastname, startDate, endDate);
		
		// verify startdate message

        Contactpage cp1 = new Contactpage(driver);

		String actlastname = cp1.getHeadertxt().getText();

		if (actlastname.contains(lastname)) {
			System.out.println(lastname + "name is verified==PASS");
		} else {
			System.out.println(lastname + "name is not verified==PASS");
		}

		// verify enddate message


		String actlastname1 = cp1.getHeadertxt().getText();

		if (actlastname1.contains(lastname)) {
			System.out.println(lastname + "name is verified==PASS");
		} else {
			System.out.println(lastname + "name is not verified==PASS"); //12min
		}

	}

	}


