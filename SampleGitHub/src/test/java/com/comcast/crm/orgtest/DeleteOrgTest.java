package com.comcast.crm.orgtest;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryUtility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.LoginPage;
import com.comcast.crm.objectrepositoryUtility.OrganizationFilterPage;
import com.comcast.crm.objectrepositoryUtility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryUtility.OrganizationPage;

public class DeleteOrgTest {

	public static void main(String[] args) throws Throwable {
		// read the data from fileUtility
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wlib=new WebDriverUtility();

		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("usernam");
		String PASSWORD = fLib.getDataFromPropertiesFile("passwd");


		// read TestScript from excel file
		String coloumn1Data = eLib.getDataFromExcel("org", 10, 2) + jLib.getRandomNumber();

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
		
       //verify header msg expected result
        
        OrganizationInfoPage oip=new OrganizationInfoPage(driver);
        
       String actOrgName= oip.getHeaderMsg().getText();
       
       if(actOrgName.contains(coloumn1Data)) {
    	   System.out.println(coloumn1Data + "name is verified==PASS");
       }else {
    	   System.out.println(coloumn1Data + "name is not verified==PASS");
       }
     //go back to Organization page
	   hp.getOrgLink().click();
	   
	   //search for organisation page and using organisation filter page class
	   OrganizationFilterPage ofp=new OrganizationFilterPage(driver);
	   
	   ofp.getSearchEdt().sendKeys(coloumn1Data);
	   wlib.select(ofp.getSearchdd(),"Organization Name");
	   ofp.getSearchbtn().click();
	   
	   //finding the del option by using xpath i.e dynamic element we have to use driver.findele
	   driver.findElement(By.xpath("//a[text()='"+coloumn1Data+"']/../../td[8]/a[text()='del']")).click();
	   wlib.switchToAlert(driver);
	   /*Alert ale=driver.switchTo().alert();
	   ale.accept();*/

}
}
