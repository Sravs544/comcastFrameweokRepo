package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryUtility.Contactpage;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.LoginPage;

public class CreateContactwithSupportStartnEndDate {

	public static void main(String[] args) throws Throwable {
		// read the data from fileUtility
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();

		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("usernam");
		String PASSWORD = fLib.getDataFromPropertiesFile("passwd");

		// generate random number

	

		// read TestScript from excel file
		String coloumn1Data = eLib.getDataFromExcel("contact", 1, 1) +jLib.getRandomNumber();

		String lastname = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();

		WebDriver driver = null;

		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.get(URL);
		
		driver.get(URL);

		// login to App

		LoginPage lp = new LoginPage(driver);

		lp.loginToApp("admin", "admin");
		
		//clicking on contact link
		
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
			System.out.println(lastname + "name is not verified==PASS");
		}

	}

}
