package com.crm.comcast.orgtest;

import java.io.IOException;
import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.listenerutility.ListImpClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryUtility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.LoginPage;
import com.comcast.crm.objectrepositoryUtility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryUtility.OrganizationPage;

public class CreateOrgTest extends BaseClass {

	@Test(groups="smokeTest")
	public void createOrgTest() throws Throwable, Throwable {
		
		UtilityClassObject.getTest().log(Status.INFO, "read data from Excel");

		String coloumn1Data = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();

		//driver.get(URL);
		// login to App

		LoginPage lp = new LoginPage(driver);

		lp.loginToApp("admin", "admin");

		// navigate to organization module
		UtilityClassObject.getTest().log(Status.INFO, "navigate to org");

		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();// using getters here we are doing only click action

		// click on "create organization button"
		UtilityClassObject.getTest().log(Status.INFO, "navigate to create org page");


		OrganizationPage cnp = new OrganizationPage(driver);
		cnp.getCreateNewOrgBtn().click();

		// enter all the details and create new organisation
		
		UtilityClassObject.getTest().log(Status.INFO, "create a new org");


		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(coloumn1Data);

		// verify header msg expected result
		
		UtilityClassObject.getTest().log(Status.INFO, "verify org name");


		OrganizationInfoPage oip = new OrganizationInfoPage(driver);

		String actOrgName = oip.getHeaderMsg().getText();

		Assert.assertEquals(true, actOrgName.contains(coloumn1Data));

	}

	@Test(groups="regressionTest")
	public void createOrgWithIndustries() throws Throwable {
		
		String coloumn1Data = eLib.getDataFromExcel("org", 4, 2) + jLib.getRandomNumber();
		String industry = eLib.getDataFromExcel("org", 4, 3);
		String type = eLib.getDataFromExcel("org", 4, 4);
		//driver.get(URL);

		// login to App

		/*LoginPage lp = new LoginPage(driver);

		lp.loginToApp("admin", "admin");// Login page check you can undrst and taking this method from business class

		// navigate to organization module*/

		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();// using getters here we are doing only click action

		// click on "create organization button"

		OrganizationPage cnp = new OrganizationPage(driver);
		cnp.getCreateNewOrgBtn().click();
//enter the details of organisation name n industry n type

		CreatingNewOrganizationPage cnp1 = new CreatingNewOrganizationPage(driver);
		cnp1.createOrg(coloumn1Data);
		cnp1.createOrg(coloumn1Data, industry);
		cnp1.createOrg(coloumn1Data, industry, type);
		
		//verifying the header message
		
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actorgName= oip.getHeaderMsg().getText();
	       
	       if(actorgName.contains(coloumn1Data)) {
	    	   System.out.println(coloumn1Data + "name is verified==PASS");
	       }else {
	    	   System.out.println(coloumn1Data + "name is not verified==PASS");
	       }
	}

	@Test(groups="regressionTest")

	public void CreateOrgWithPhnum() throws Throwable, Throwable {
		String coloumn1Data = eLib.getDataFromExcel("org", 7, 2) + jLib.getRandomNumber();
		String phnum = eLib.getDataFromExcel("org", 7, 3);

		/*
		 * LoginPage lp = new LoginPage(driver);
		 * 
		 * lp.loginToApp("admin", "admin");// Login page check you can undrst and taking
		 * this method from business class
		 * 
		 * // navigate to organization module
		 */

		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();// using getters here we are doing only click action

		// click on "create organization button"

		OrganizationPage cnp = new OrganizationPage(driver);
		cnp.getCreateNewOrgBtn().click();
		// enter the details of organisation name n industry n type

		CreatingNewOrganizationPage cnp1 = new CreatingNewOrganizationPage(driver);
		cnp1.createOrg(coloumn1Data);
		cnp1.getPhonetxt().sendKeys(phnum);
		cnp1.getSaveBtn().click();
		

		// verify the phone num
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actphnum = oip.getHeaderMsg().getText();
		Assert.assertEquals(actphnum, phnum);
	}

}
