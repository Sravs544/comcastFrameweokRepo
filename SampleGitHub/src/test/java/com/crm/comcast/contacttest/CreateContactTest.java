package com.crm.comcast.contacttest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoryUtility.ContactOrganisationchildpage;
import com.comcast.crm.objectrepositoryUtility.Contactpage;
import com.comcast.crm.objectrepositoryUtility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryUtility.OrganizationPage;

public class CreateContactTest extends BaseClass {

	@Test(groups = { "smokeTest" })
	public void createContactTest() throws IOException, Throwable {

		// read TestScript from excel file

		String lastname = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();

		HomePage hp = new HomePage(driver);
		hp.getContactlnk().click();

		Contactpage cp = new Contactpage(driver);

		cp.getContactimg().click();
		cp.getLastnametxt().sendKeys(lastname);
		cp.getSavebtn().click();

		Contactpage cp1 = new Contactpage(driver);

		String actlastname = cp1.getHeadertxt().getText();
		boolean status = actlastname.contains(lastname);
		Assert.assertEquals(status, true);
		String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		SoftAssert soft = new SoftAssert();
		soft.assertAll();
	}

	@Test(groups = "regressionTest")
	public void createContactWithDateTest() throws IOException, Throwable {

		String lastname = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();

		HomePage hp = new HomePage(driver);
		hp.getContactlnk().click();

		Contactpage cp = new Contactpage(driver);
		cp.getContactimg().click();

		String startDate = jLib.getSystemDateYYYYMMDD();
		String endDate = jLib.getRequiredDateYYYYMMDD(+30);

		cp.ContactDate(lastname, startDate, endDate);

		// verify startdate message

		Contactpage cp1 = new Contactpage(driver);

		String actlastname = cp1.getHeadertxt().getText();

		Assert.assertEquals(actlastname.trim(), startDate);

		String actlastname1 = cp1.getHeadertxt().getText();
		Assert.assertEquals(actlastname1.trim(), endDate);

	}

	@Test(groups = "regressionTest")
	public void createContactWithOrgTest() throws IOException, Throwable {
		String coloumn1Data = eLib.getDataFromExcel("contact", 1, 1) + jLib.getRandomNumber();

		String lastname = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();

		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();// using getters here we are doing only click action

		// click on "create organization button"

		OrganizationPage cnp = new OrganizationPage(driver);
		cnp.getCreateNewOrgBtn().click();

		// enter all the details and create new organisation

		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(coloumn1Data);

		cnop.getSaveBtn().click();
		
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

		
	}
	}


