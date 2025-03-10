package com.comcast.crm.objectrepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

WebDriver driver;
	
	public ProductPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//object identification


	@FindBy(linkText="Products")
	private WebElement prodlnk;
	
	@FindBy(xpath="//img[@title='Create Product...']")
	private WebElement imgbtn;
	
	@FindBy(name="productname")
	private WebElement prodnameEdt;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement radiobtn;

	public WebElement getProdlnk() {
		return prodlnk;
	}

	public WebElement getImgbtn() {
		return imgbtn;
	}

	public WebElement getProdnameEdt() {
		return prodnameEdt;
	}

	public WebElement getRadiobtn() {
		return radiobtn;
	}
	
	
	
	

}
