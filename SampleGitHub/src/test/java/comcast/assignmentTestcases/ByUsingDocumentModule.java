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
import com.comcast.crm.objectrepositoryUtility.DocumentPage;
import com.comcast.crm.objectrepositoryUtility.LoginPage;

public class ByUsingDocumentModule {

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
		String coloumn1Data = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();

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

		lp.loginToApp("admin", "admin");

      //go to document link n plus and create the title then save

		DocumentPage dp = new DocumentPage(driver);
		dp.getDoclnk().click();
		dp.getDocimg().click();
		dp.getTitleEdt().sendKeys(coloumn1Data);
		dp.getSavebtn().click();

       //verifying the orgname
		String actheadertxt = dp.getHeadertxt().getText();
		if (actheadertxt.contains(coloumn1Data)) {
			System.out.println(coloumn1Data + "is verified ==PASS");
		} else {
			System.out.println(coloumn1Data + "is not verified ==FAIL");

		}
		driver.close();
	}
}