package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryUtility.ContactOrganisationchildpage;
import com.comcast.crm.objectrepositoryUtility.Contactpage;
import com.comcast.crm.objectrepositoryUtility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.LoginPage;
import com.comcast.crm.objectrepositoryUtility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryUtility.OrganizationPage;

public class CreateContactWithOrg {

	public static void main(String[] args) throws Throwable {
		// read the data from fileUtility
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();

		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("usernam");
		String PASSWORD = fLib.getDataFromPropertiesFile("passwd");

		// generate random number

		Random random = new Random();
		int randomInt = random.nextInt(1000);

		// read TestScript from excel file

		String coloumn1Data = eLib.getDataFromExcel("contact", 1, 1) + jLib.getRandomNumber();

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

		// login to App

		LoginPage lp = new LoginPage(driver);

		lp.loginToApp("admin", "admin");// Login page check you can undrst and taking this method from business class

		// navigate to organization module

		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();// using getters here we are doing only click action

		// click on "create organization button"

		OrganizationPage cnp = new OrganizationPage(driver);
		cnp.getCreateNewOrgBtn().click();

		// enter all the details and create new organisation

		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(coloumn1Data);

		cnop.getSaveBtn().click();

		// verify header

		OrganizationInfoPage oip = new OrganizationInfoPage(driver);

		String actOrgName = oip.getHeaderMsg().getText();

		if (actOrgName.contains(coloumn1Data)) {
			System.out.println(coloumn1Data + "name is verified==PASS");
		} else {
			System.out.println(coloumn1Data + "name is not verified==PASS");
		}

		hp.getContactlnk().click();

		Contactpage cp = new Contactpage(driver);
		cp.getContactimg().click();
		cp.getLastnametxt().sendKeys(lastname);

		// trying to clicking on plus button to select org
		cp.getPlusbtn().click();

		// switch to childwindow

		wLib.switchNewBrowserTab(driver, "module=Accounts");

		// identify search text field
		ContactOrganisationchildpage cocp = new ContactOrganisationchildpage(driver);
		cocp.getSearchEdt().sendKeys(coloumn1Data);

		// identify search button
		cocp.getSearchbtn().click();

		// after entering createorgtest84 then click on link and identify the path but
		// doing dynamically

		driver.findElement(By.xpath("//a[text()='" + coloumn1Data + "']")).click();

		// control switch to parent window
		wLib.switchToTabOnTitle(driver, "Contacts&action");
  
		cp.getSavebtn().click();

		// verify org message after entering n save

		Contactpage cp1 = new Contactpage(driver);

		String actlastname = cp1.getHeadertxt().getText();

		if (actlastname.contains(lastname)) {
			System.out.println(lastname + "name is verified==PASS");
		} else {
			System.out.println(lastname + "name is not verified==PASS");
		}

		driver.quit();
	}

}
