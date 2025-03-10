package comcast.assignmentTestcases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryUtility.LeadsPage;
import com.comcast.crm.objectrepositoryUtility.LoginPage;

public class ByUsingLeadsModule {

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

		Random random = new Random();
		int randomInt = random.nextInt(1000);

		// read TestScript from excel file
		String coloumn1Data = eLib.getDataFromExcel("contact", 1, 1) + jLib.getRandomNumber();

		String lastname = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();

		String company = eLib.getDataFromExcel("contact", 1, 3) + jLib.getRandomNumber();

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
		driver.get(URL);

		// login to App

		LoginPage lp = new LoginPage(driver);

		lp.loginToApp("admin", "admin");

		LeadsPage ldp = new LeadsPage(driver);
		ldp.getLeadlnk().click();

		ldp.getImgbtn().click();

		ldp.getLastnameEdt().sendKeys(lastname);
		ldp.getCompanyEdt().sendKeys(company);
		ldp.getSaveBtn().click();

		driver.close();
	}

}
